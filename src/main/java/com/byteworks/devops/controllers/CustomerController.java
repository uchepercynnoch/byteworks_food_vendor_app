package com.byteworks.devops.controllers;

import com.byteworks.devops.entities.FoodInformation;
import com.byteworks.devops.repository.FoodInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class CustomerController {
    private FoodInformationRepository foodInformationRepository;

    @Autowired
    public void setFoodInformationRepository(FoodInformationRepository foodInformationRepository) {
        this.foodInformationRepository = foodInformationRepository;
    }

    @GetMapping("/menu")
    public ResponseEntity actionShowMenu() {
        List<FoodInformation> foodInformationList = foodInformationRepository.findAll();
        return new ResponseEntity<>(foodInformationList, HttpStatus.OK);
    }

    @PostMapping("/order_food")
    public ResponseEntity actionOrderFood() {
        return null;
    }
}
