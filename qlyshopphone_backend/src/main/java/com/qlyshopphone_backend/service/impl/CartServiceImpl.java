package com.qlyshopphone_backend.service.impl;

import com.qlyshopphone_backend.model.Cart;
import com.qlyshopphone_backend.service.CartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    private static final String CART_SESSION_KEY = "cart";

    @Override
    public void addToCart(HttpSession session, Cart item) {
        List<Cart> cart = getCart(session);
        cart.add(item);
        session.setAttribute(CART_SESSION_KEY, cart);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Cart> getCart(HttpSession session) {
        List<Cart> cart = (List<Cart>) session.getAttribute(CART_SESSION_KEY);
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute(CART_SESSION_KEY, cart);
        }
        return cart;
    }

    @Override
    public void clearCart(HttpSession session) {
        session.removeAttribute(CART_SESSION_KEY);
    }
}
