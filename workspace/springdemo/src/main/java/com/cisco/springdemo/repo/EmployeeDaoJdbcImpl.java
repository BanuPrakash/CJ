package com.cisco.springdemo.repo;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@ConditionalOnMissingBean(EmployeeDaoMongoImpl.class)
public class EmployeeDaoJdbcImpl implements EmployeeDao{
    @Override
    public void addEmployee() {
        System.out.println("Stored in Database!!!");
    }
}
