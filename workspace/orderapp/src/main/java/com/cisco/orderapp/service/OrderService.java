package com.cisco.orderapp.service;

import com.cisco.orderapp.dao.CustomerRepo;
import com.cisco.orderapp.dao.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    // not using @Autowired, depending on constructor DI
    private final ProductRepo productRepo;
    private final CustomerRepo customerRepo;

}
