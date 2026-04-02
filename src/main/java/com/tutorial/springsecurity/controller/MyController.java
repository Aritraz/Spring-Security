package com.tutorial.springsecurity.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @GetMapping("/security")
    public String testController(HttpServletRequest req)
    {
        return "This is a demo page "+ "with Session Id "+req.getSession().getId();
    }
}
