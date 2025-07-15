package com.cisco.springdemo.service;

import com.cisco.springdemo.repo.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class AppService {
    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DataSource dataSource;

    public void doTask() {
        employeeDao.addEmployee();
        try {
            // Connection con = DriverManager.getConnection(URL, USER, PWD);
            Connection con = dataSource.getConnection(); // get connection from pool
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select * from products");
            while(rs.next()) {
                System.out.println(rs.getString("name"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}
