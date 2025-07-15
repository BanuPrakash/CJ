package com.cisco.orderapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name="line_items")
public class LineItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemId;

    // refers to a Product
    @ManyToOne
    @JoinColumn(name="product_fk")
    private Product product;

    private  int qty;

    private double amount; // price * qty + tax - discount
}
