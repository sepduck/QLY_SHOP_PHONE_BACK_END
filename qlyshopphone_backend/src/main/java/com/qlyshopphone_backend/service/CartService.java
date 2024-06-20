package com.qlyshopphone_backend.service;

import com.qlyshopphone_backend.model.Cart;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface CartService {
    void addToCart(HttpSession session, Cart item);

    List<Cart> getCart(HttpSession session);

    void clearCart(HttpSession session);
}
