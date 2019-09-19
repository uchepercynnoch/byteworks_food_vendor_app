package com.byteworks.devops.repository;

import com.byteworks.devops.entities.Customer;
import com.byteworks.devops.entities.CustomerOrder;
import com.byteworks.devops.entities.FoodInformation;
import com.byteworks.devops.models.Cart;

import java.util.List;

public interface CustomRepository {
    Boolean CUSTOMER_EXIST(String username);

    List<String> FIND_CUSTOMER_ROLE(String username);

    Customer FIND_CUSTOMER_BY_USERNAME(String username);

    void UPDATE_CUSTOMER_PERMISSION(String customer);

    void SAVE_CUSTOMER_ORDER(Cart cartInformation);

    CustomerOrder GET_CUSTOMER_ORDER(String orderId);

    FoodInformation FIND_FOOD(String foodCode);

}
