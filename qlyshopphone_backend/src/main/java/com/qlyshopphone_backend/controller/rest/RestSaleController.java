package com.qlyshopphone_backend.controller.rest;

import com.qlyshopphone_backend.service.GroupProductService;
import com.qlyshopphone_backend.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping()
public class RestSaleController {
    @Autowired
    private ProductService productService;
    @Autowired
    private GroupProductService groupProductService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_USER')")
    @GetMapping("/sale")
    public ResponseEntity<?> listOfProduct(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ArrayList<Integer> list = (ArrayList<Integer>) session.getAttribute("list");
        return null;
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNTANT', 'ROLE_USER')")
    @GetMapping("/view-cart")
    public ResponseEntity<?> listCart(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        ArrayList<Integer> list = (ArrayList<Integer>) session.getAttribute("list");
        ArrayList<Map<String, Object>> productList = new ArrayList<>();
        if (list != null && !list.isEmpty()) {
            for (int id : list) {
                Map<String, Object> map = productService.getProductDetailId(id);
                productList.add(map);
            }
            return ResponseEntity.ok(productList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No items in cart");
        }
    }


}
