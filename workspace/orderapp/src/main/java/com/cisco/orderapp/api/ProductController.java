package com.cisco.orderapp.api;

import com.cisco.orderapp.entity.Product;
import com.cisco.orderapp.service.OrderService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.afford;

import java.util.List;

@RestController
@RequestMapping("api/products")
@RequiredArgsConstructor
@Tag(name = "Product API", description = "Product API Service")
public class ProductController {
    private final OrderService service;

    // GET http://localhost:8080/api/products
    // GET  http://localhost:8080/api/products?low=40000&high=100000
    @GetMapping()
    public List<Product> getProducts(@RequestParam(name = "low", defaultValue = "0.0") double low,
                                     @RequestParam(name = "high", defaultValue = "0.0") double high) {
        if(low == 0.0 && high == 0.0) {
            return service.getProducts();
        } else {
            return service.getByRange(low, high);
        }
    }

    @Operation(
            description = "Service that return a Product",
            summary = "This service returns a Product by the ID",
            responses = {
                    @ApiResponse(description = "Successful Operation", responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Product.class))),
                    @ApiResponse(responseCode = "404", description = "Product  Not found", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true)))
            })
    @GetMapping("/{pid}")
    public Product getProductById(@PathVariable("pid") int id) throws EntityNotFoundException {
        return  service.getProductById(id);
    }


    @GetMapping("/etag/{pid}")
    public ResponseEntity<Product> getProductByIdEhCache(@PathVariable("pid") int id) throws EntityNotFoundException {
        Product product =  service.getProductById(id);
        return ResponseEntity.ok().eTag(String.valueOf(product.hashCode())).body(product);
    }

    @GetMapping("/hateoas/{pid}")
    public ResponseEntity<EntityModel<Product>> getProductByHateosId(@PathVariable("pid") int id) throws EntityNotFoundException {
        Product p =  service.getProductById(id);
        EntityModel<Product> em = EntityModel.of(p,
                linkTo(methodOn(ProductController.class).getProductByHateosId((id))).withSelfRel()
                        .andAffordance(afford(methodOn(ProductController.class).updateProductPrice(id, 0)))
                        .andAffordance(afford(methodOn(ProductController.class).modifyProduct(id, null)))
                ,
                linkTo(methodOn(ProductController.class).getProducts(0,0)).withRel("products")
                );

        return  ResponseEntity.ok(em);
    }

    // SPeL
    @Cacheable(value="productCache", key="#id")
    // @Cacheable(value="productCache", key="#id", unless = "#result !=null")
    @GetMapping("/cache/{pid}")
    public Product getProductByIdCache(@PathVariable("pid") int id) throws EntityNotFoundException {
        System.out.println("Cache Miss...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return  service.getProductById(id);
    }

    @Cacheable(value = "productCache", key = "#p.id", condition = "#p.price > 100")
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED) // 201
    public Product addProduct(@RequestBody @Valid Product p) {
        return service.addProduct(p);
    }

    // PATCH http://localhost:8080/api/products/1?price=75000.90
    @CachePut(value = "productCache", key="#id")
    @PatchMapping("/{pid}")
    public Product updateProductPrice(@PathVariable("pid") int id, @RequestParam("price") double price) throws EntityNotFoundException {
        return service.updateProductPrice(id, price);
    }

    // PUT http://localhost:8080/api/products/1?price=75000.90
    // Accept:application/json
    // content-type: application/json
    /*
        {
            "name": "some",
            "price": 78111.33
        }
     */

    @Hidden
    @PutMapping("/{pid}")
    public Product modifyProduct(@PathVariable("pid") int id, @RequestBody Product p) {
        return null;
    }

    // Avoid cache evict using URI
    @CacheEvict(value = "productCache", key="#id")
    @DeleteMapping("/{pid}")
    public String deleteProduct(@PathVariable("pid") int id) {
        System.out.println("Delete code");
        return "Deleted!!!";
    }
}
