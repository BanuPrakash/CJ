package com.cisco.orderapp.dao;

import com.cisco.orderapp.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

// first argument says which entity has to manage
// second argument is type of Primary key
public interface ProductRepo extends JpaRepository<Product, Integer> {
    // select * from products where qty = ?
    List<Product> findByQuantity(int qty);
    // select * from products where price > ?
    List<Product> findByPriceGreaterThan(double price);
    // select * from products where price >= low and price <= high
    List<Product> findByPriceBetween(double low, double high);
    // select * from products where quantity = ? OR price = ?
    List<Product> findByQuantityOrPrice(int qty, double price);

    // custom queries
    @Query("from Product where price >= :l and price <= :h")
//    @Query(value = "select * from products where price >= :l and price <= :h", nativeQuery = true)
    List<Product> byRange(@Param("l") double low, @Param("h") double high);

    // executeUpdate
    @Modifying
    @Query("update Product set price = :pr where id = :id")
    void updateProduct(@Param("id") int id, @Param("pr") double price);
}
