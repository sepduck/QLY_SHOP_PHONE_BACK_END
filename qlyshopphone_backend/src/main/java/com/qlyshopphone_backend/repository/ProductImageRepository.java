package com.qlyshopphone_backend.repository;

import com.qlyshopphone_backend.model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
    @Query(value = "SELECT *\n" +
            "FROM product_image\n" +
            "WHERE product_id = ?", nativeQuery = true)
    List<ProductImage> findByProductId(int productId);

    @Query(value = "UPDATE product p\n" +
            "SET p.image_url = (SELECT pi.image_url\n" +
            "                   FROM product_image pi\n" +
            "                   WHERE pi.product_id = p.product_id\n" +
            "                   LIMIT 1)", nativeQuery = true)
    void updateProductImageUrls();

    boolean existsByImageUrl(String imageUrl);
}
