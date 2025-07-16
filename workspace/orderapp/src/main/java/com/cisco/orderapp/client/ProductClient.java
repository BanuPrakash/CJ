package com.cisco.orderapp.client;

import com.cisco.orderapp.entity.Product;
import com.cisco.orderapp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
//import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
//@Order(2)
public class ProductClient implements CommandLineRunner {
    private final OrderService service;

    @Override
    public void run(String... args) throws Exception {
//        printProducts();
//        addProduct();
//        printByRange();
     //   updateProduct();
    }

    private void updateProduct() {
        Product product = service.updateProductPrice(3, 1255.00);
        System.out.println(product);
    }

    private void printByRange() {
        List<Product> products = service.getByRange(40_000.00, 1_00_000.00);
        for(Product p : products) {
            System.out.println(p); // toString()
        }
    }

    private void addProduct() {
       Product product = Product.builder().name("LG AC").price(45000.00).quantity(100).build();
       service.addProduct(product);
    }

    private void printProducts() {
        List<Product> products = service.getProducts();
        for(Product p : products) {
            System.out.println(p); // toString()
        }
    }


}
