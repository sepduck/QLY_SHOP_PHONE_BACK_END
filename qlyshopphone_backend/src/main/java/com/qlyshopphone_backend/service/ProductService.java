package com.qlyshopphone_backend.service;

import com.qlyshopphone_backend.dto.ProductDTO;
import com.qlyshopphone_backend.dto.ProductImageDTO;
import com.qlyshopphone_backend.model.Product;
import com.qlyshopphone_backend.model.ProductImage;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.Optional;

public interface ProductService {
    ResponseEntity<?> getAllProducts();

    Product saveProduct(ProductDTO productDTO) throws Exception;

    ResponseEntity<?> findByIdProduct(int productId);

    Optional<Product> findByProductId(int productId);

    ResponseEntity<?> deleteProduct(int productId);

    ResponseEntity<?> searchAllByProductName(String productName);

    ResponseEntity<?> searchAllByProductId(int productId);

    ProductImage saveProductImage(ProductImageDTO productImageDTO, int productId) throws Exception;

    Product getProductById(int productId) throws Exception;

    boolean existsByProductName(String name);

    boolean existsByImageUrl(String imageUrl);

    ResponseEntity<?> updateProductImageUrls();

    ResponseEntity<?> searchGroupProductId( int groupProductId);

    ResponseEntity<?> searchInventory(int number);

    ResponseEntity<?> searchActive(int number);

    ResponseEntity<?> searchDirectSales(int number);

    ResponseEntity<?> searchByLocationId( int locationId);

    ResponseEntity<?> searchCategory(int number);

    Map<String, Object> getProductDetailId(int productId);


}
