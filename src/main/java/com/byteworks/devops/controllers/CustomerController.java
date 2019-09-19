package com.byteworks.devops.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class CustomerController {
    @GetMapping("/menu")
    public ResponseEntity actionShowMenu() {
        return null;
    }

    @PostMapping("/order_food")
    public ResponseEntity actionOrderFood() {
        return null;
    }
}
