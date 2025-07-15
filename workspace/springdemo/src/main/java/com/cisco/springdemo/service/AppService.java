package com.cisco.springdemo.service;

import com.cisco.springdemo.repo.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AppService {
    @Autowired
    @Qualifier("employeeDaoJdbcImpl")
    private EmployeeDao employeeDao;

    public void doTask() {
        employeeDao.addEmployee();
    }
}
