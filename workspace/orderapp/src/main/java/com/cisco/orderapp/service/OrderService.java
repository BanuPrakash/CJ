package com.cisco.orderapp.service;

import com.cisco.orderapp.dao.CustomerRepo;
import com.cisco.orderapp.dao.ProductRepo;
import com.cisco.orderapp.entity.Customer;
import com.cisco.orderapp.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    // not using @Autowired, depending on constructor DI
    private final ProductRepo productRepo;
    private final CustomerRepo customerRepo;

    public List<Product> getProducts() {
        return productRepo.findAll();
    }

    public Product getProductById(int id) {
        return  productRepo.findById(id).get();
    }

    public Product addProduct(Product p) {
        return  productRepo.save(p);
    }

    public Customer addCustomer(Customer c) {
        return  customerRepo.save(c);
    }

    public List<Customer> getCustomers() {
        return  customerRepo.findAll();
    }

    public long customerCount() {
        return customerRepo.count();
    }
}
