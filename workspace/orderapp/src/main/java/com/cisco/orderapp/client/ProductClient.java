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
        printProducts();
//        addProduct();
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
