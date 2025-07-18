package com.cisco.asyncdemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class SampleController {
    @GetMapping()
    public String getMessage() {
        return "Hello Everyone!!!";
    }

    @GetMapping("/user")
    public String getMessageUser() {
        return "Hello User!!!";
    }

    @GetMapping("/admin")
    public String getMessageAdmin() {
        return "Hello Admin!!!";
    }


}
