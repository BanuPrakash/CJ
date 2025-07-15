package com.cisco.orderapp.client;

import com.cisco.orderapp.entity.Customer;
import com.cisco.orderapp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
//import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
//@Order(1)
public class CustomerClient implements CommandLineRunner {
    private final OrderService service;

    // run() method is invoked as soon as Spring container /Context is created and initialized
    @Override
    public void run(String... args) throws Exception {
//        addCustomers();
//        printCustomers();
    }

    private void printCustomers() {
        List<Customer> customers = service.getCustomers();
        for(Customer c: customers) {
            System.out.println(c);
        }
    }

    private void addCustomers() {
        // if there are no customers
        if(service.customerCount() == 0) {
            service.addCustomer(Customer.builder().
                    email("raj@cisco.com").
                    firstName("Raj").
                    lastName("Kumar").
                    build());
            service.addCustomer(Customer.builder().
                    email("anne@cisco.com").
                    firstName("Anne").
                    lastName("Hathaway").
                    build());
            service.addCustomer(Customer.builder().
                    email("brad@cisco.com").
                    firstName("Brad").
                    lastName("Pitt").
                    build());
        }
    }
}
