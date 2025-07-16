package com.cisco.orderapp.client;

import com.cisco.orderapp.entity.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Test {
    public static void main(String[] args)  throws  Exception{
        Product product = Product.builder().id(32).name("Mac").price(2_24_000.00).build();

        ObjectMapper mapper = new ObjectMapper();

        String JSON = mapper.writeValueAsString(product);

        System.out.println(JSON);
    }
}
