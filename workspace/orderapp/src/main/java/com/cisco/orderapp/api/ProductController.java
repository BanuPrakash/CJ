package com.cisco.orderapp.api;

import com.cisco.orderapp.entity.Product;
import com.cisco.orderapp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
@RequiredArgsConstructor
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

    @GetMapping("/{pid}")
    public Product getProductById(@PathVariable("pid") int id) throws EntityNotFoundException {
        return  service.getProductById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED) // 201
    public Product addProduct(@RequestBody Product p) {
        return service.addProduct(p);
    }

    // PATCH http://localhost:8080/api/products/1?price=75000.90
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

    @PutMapping("/{pid}")
    public Product modifyProduct(@PathVariable("pid") int id, @RequestBody Product p) {
        return null;
    }
}
