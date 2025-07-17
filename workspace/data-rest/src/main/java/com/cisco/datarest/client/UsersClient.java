package com.cisco.datarest.client;

import com.cisco.datarest.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UsersClient implements CommandLineRunner {
    private final RestTemplate restTemplate;

    @Override
    public void run(String... args) throws Exception {
//        getUsers();
//        getProducts();
        getProduct();
    }
    private  void getProduct() {
        ResponseEntity<Product> response =
                restTemplate.getForEntity("http://localhost:8080/api/products/1", Product.class);
        System.out.println(response.getBody().getName());
    }
    private void getProducts() {
        ResponseEntity<List<Product>> response =
                restTemplate.exchange("http://localhost:8080/api/products",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Product>>() {
                        });
            List<Product> products = response.getBody();
            for(Product p : products) {
                System.out.println(p.getName());
            }
    }

    private void getUsers() {
       String users = restTemplate.getForObject("https://jsonplaceholder.typicode.com/users", String.class);
        System.out.println(users);
    }
}
