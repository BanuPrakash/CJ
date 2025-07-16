package com.cisco.orderapp.service;

import com.cisco.orderapp.dao.CustomerRepo;
import com.cisco.orderapp.dao.OrderRepo;
import com.cisco.orderapp.dao.ProductRepo;
import com.cisco.orderapp.entity.Customer;
import com.cisco.orderapp.entity.LineItem;
import com.cisco.orderapp.entity.Order;
import com.cisco.orderapp.entity.Product;
import jakarta.transaction.Transactional;
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
    private final OrderRepo orderRepo;
    /*
    {
        "customer": {"email": "anne@cisco.com"},
        "items": [
            {
                "product": {"id": 3},
                "qty" : 2
            },
            {
                "product": {"id": 1},
                "qty" : 3
            },
        ]
    }
     */
    // Atomic operation
    // if exception is thrown, rollback, else commit
    @Transactional
    public String placeOrder(Order order) {
        double total = 0.0; // has to be computed
        for(LineItem item : order.getItems()) {
            Product product = productRepo.findById(item.getProduct().getId()).get();
            product.setQuantity(product.getQuantity() - item.getQty()); // Dirty checking, Update SQL is sent
            if(product.getQuantity() <= 0) {
                throw  new IllegalArgumentException("Product " + product.getName() + " not is Stock!!!");
            }
            item.setAmount(product.getPrice() * item.getQty()); // tax, discount
            total += item.getAmount();
        }
        order.setTotal(total);
        orderRepo.save(order); // saves order, it's line items --> cascade
        return "order placed!!!";
    }
    public List<Product> getByRange(double low, double high) {
        return productRepo.byRange(low, high);
    }

    @Transactional
    public Product updateProductPrice(int id, double price) {
        productRepo.updateProduct(id, price);
        return  getProductById(id);
    }

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
