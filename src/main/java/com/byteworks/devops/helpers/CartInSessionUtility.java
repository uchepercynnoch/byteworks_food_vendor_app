package com.byteworks.devops.helpers;

import com.byteworks.devops.models.Cart;

import javax.servlet.http.HttpServletRequest;

public class CartInSessionUtility {
    public static Cart getCartInSession(HttpServletRequest request) {
        Cart cart = (Cart) request.getSession().getAttribute("myFoodCart");
        if (cart == null) {
            cart = new Cart();
            request.getSession().setAttribute("myFoodCart", cart);
        }

        return cart;
    }

    public static void removeCartInSession(HttpServletRequest request) {
        request.getSession().removeAttribute("myFoodCart");
    }

    public static void storeLastOrderedCartInSession(HttpServletRequest request, Cart cartInfo) {
        request.getSession().setAttribute("lastOrderedCart", cartInfo);
    }

    public static Cart getLastOrderedCartInSession(HttpServletRequest request) {
        return (Cart) request.getSession().getAttribute("lastOrderedCart");
    }
}
