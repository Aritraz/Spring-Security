package com.tutorial.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @GetMapping("/security")
    public String testController()
    {
        return "This is a demo page";
    }
}
