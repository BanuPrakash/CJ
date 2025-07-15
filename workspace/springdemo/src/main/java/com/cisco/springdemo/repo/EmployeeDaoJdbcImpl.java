package com.cisco.springdemo.repo;

import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDaoJdbcImpl implements EmployeeDao{
    @Override
    public void addEmployee() {
        System.out.println("Stored in Database!!!");
    }
}
