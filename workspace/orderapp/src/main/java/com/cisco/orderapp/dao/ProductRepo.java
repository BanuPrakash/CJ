package com.cisco.orderapp.dao;

import com.cisco.orderapp.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

// first argument says which entity has to manage
// second argument is type of Primary key
public interface ProductRepo extends JpaRepository<Product, Integer> {
}
