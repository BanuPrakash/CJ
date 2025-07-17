package com.cisco.datarest.api;

import com.cisco.datarest.dao.ProductRepo;
import com.cisco.datarest.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@BasePathAwareController
public class ProductController {
    @Autowired
    private ProductRepo productRepo;

    @RequestMapping(path = "products" , method = RequestMethod.GET)
    public  @ResponseBody List<Product> get() {
        List<Product> products = productRepo.findAll();
        // add explcility links using WebMvcLinkBuilder
        return Arrays.asList(new Product(42, "A", 3523.22, 100,0),
                new Product(72, "B", 5211.22,100,1));
    }
}
