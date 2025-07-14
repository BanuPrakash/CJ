package com.cisco.prj.dao;

import com.cisco.prj.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJdbcImpl implements ProductDao{
    private static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String URL = "jdbc:mysql://localhost:3306/cj";
    private static String USER = "root";
    private static String PWD = "Welcome123";

    static  {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addProduct(Product p) {
        Connection con = null;
        String SQL = "INSERT INTO products (id, name, price) VALUES (0, ?, ?)";
        try {
            con = DriverManager.getConnection(URL, USER, PWD);
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, p.getName());
            ps.setDouble(2, p.getPrice());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if(con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL, USER, PWD);
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT id, name, price FROM products");
            while (rs.next()) {
                // builder pattern
                products.add(Product.builder().
                        id(rs.getInt("id")).
                        name(rs.getString("name")).
                        price(rs.getDouble("price")).
                        build());
                // products.add(new Product(rs.getInt("id"), rs.getString("name"), rs.getDouble("price"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if(con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return products;
    }
}
