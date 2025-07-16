package com.cisco.orderapp.client;

import com.cisco.orderapp.entity.Customer;
import com.cisco.orderapp.entity.LineItem;
import com.cisco.orderapp.entity.Order;
import com.cisco.orderapp.entity.Product;
import com.cisco.orderapp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderClient implements CommandLineRunner {
    private final OrderService service;

    @Override
    public void run(String... args) throws Exception {
//        newOrder();
  //       printOrders();
    }

    private void printOrders() {
        List<Order> orders = service.getOrders();
        for(Order order : orders) {
            System.out.println(order.getCustomer().getFirstName() + " , " + order.getOrderDate() + " , " + order.getTotal() );
            List<LineItem> items = order.getItems();
            for(LineItem item : items) {
                System.out.println(item.getProduct().getName() + ", " + item.getQty() + ", " + item.getAmount());
            }
        }
    }

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
    private void newOrder() {
        Order order = new Order();
        // order is by customer
        order.setCustomer(Customer.builder().email("anne@cisco.com").build());
        LineItem item1 = new LineItem();
        item1.setProduct(Product.builder().id(1).build());
        item1.setQty(3);

        LineItem item2 = new LineItem();
        item2.setProduct(Product.builder().id(2).build());
        item2.setQty(1);

        // order has many line items
        order.getItems().add(item1);
        order.getItems().add(item2);

        service.placeOrder(order);
    }
}
