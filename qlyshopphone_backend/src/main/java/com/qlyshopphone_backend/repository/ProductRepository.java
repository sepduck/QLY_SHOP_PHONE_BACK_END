package com.qlyshopphone_backend.repository;

import com.qlyshopphone_backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "SELECT p.*,\n" +
            "       p.product_id AS id,\n" +
            "       gp.group_product_name,\n" +
            "       tm.trademark_name,\n" +
            "       l.location_name,\n" +
            "       pp.properties_name,\n" +
            "       u.unit_name,\n" +
            "       c.category_name,\n" +
            "       pi.image_url\n" +
            "FROM product p\n" +
            "         INNER JOIN group_product gp ON p.group_product_id = gp.group_product_id\n" +
            "         INNER JOIN trademark tm ON p.trademark_id = tm.trademark_id\n" +
            "         INNER JOIN location l ON p.location_id = l.location_id\n" +
            "         INNER JOIN properties pp ON p.properties_id = pp.properties_id\n" +
            "         INNER JOIN unit u ON p.unit_id = u.unit_id\n" +
            "         INNER JOIN category c ON p.category_id = c.category_id\n" +
            "         LEFT JOIN product_image pi ON pi.product_id = p.product_id\n" +
            "WHERE p.active = 1\n" +
            "ORDER BY id DESC", nativeQuery = true)
    List<Map<String, Object>> getAllProducts();

    // Xóa mềm hàng hóa
    @Query(value = "UPDATE product\n" +
            "SET active = 0\n" +
            "WHERE product_id = ?", nativeQuery = true)
    void softDeleteProductById(int productId);

    // Tìm kiếm theo tên
    @Query(value = "SELECT p.*,\n" +
            "       p.product_id AS id,\n" +
            "       gp.group_product_name,\n" +
            "       tm.trademark_name,\n" +
            "       l.location_name,\n" +
            "       pp.properties_name,\n" +
            "       c.category_name,\n" +
            "       u.unit_name,\n" +
            "       pi.image_url\n" +
            "FROM product p\n" +
            "         INNER JOIN group_product gp ON p.group_product_id = gp.group_product_id\n" +
            "         INNER JOIN trademark tm ON p.trademark_id = tm.trademark_id\n" +
            "         INNER JOIN location l ON p.location_id = l.location_id\n" +
            "         INNER JOIN properties pp ON p.properties_id = pp.properties_id\n" +
            "         INNER JOIN unit u ON p.unit_id = u.unit_id\n" +
            "         INNER JOIN category c ON p.category_id = c.category_id\n" +
            "         LEFT JOIN product_image pi ON pi.product_id = p.product_id\n" +
            "WHERE p.active = 1\n" +
            "AND p.product_name LIKE %?%" +
            "ORDER BY id DESC", nativeQuery = true)
    List<Map<String, Object>> searchAllByProductName(@Param("product_name") String productName);

    // Tìm kiếm theo ID
    @Query(value = "SELECT p.*,\n" +
            "       p.product_id AS id,\n" +
            "       gp.group_product_name,\n" +
            "       tm.trademark_name,\n" +
            "       l.location_name,\n" +
            "       pp.properties_name,\n" +
            "       c.category_name,\n" +
            "       u.unit_name,\n" +
            "       pi.image_url\n" +
            "FROM product p\n" +
            "         INNER JOIN group_product gp ON p.group_product_id = gp.group_product_id\n" +
            "         INNER JOIN trademark tm ON p.trademark_id = tm.trademark_id\n" +
            "         INNER JOIN location l ON p.location_id = l.location_id\n" +
            "         INNER JOIN properties pp ON p.properties_id = pp.properties_id\n" +
            "         INNER JOIN unit u ON p.unit_id = u.unit_id\n" +
            "         INNER JOIN category c ON p.category_id = c.category_id\n" +
            "         LEFT JOIN product_image pi ON pi.product_id = p.product_id\n" +
            "WHERE p.active = 1\n" +
            "AND p.product_id = ?" +
            "ORDER BY id DESC", nativeQuery = true)
    List<Map<String, Object>> searchAllByProductId(@Param("product_id") int productId);

    // Check fake
    boolean existsByProductName(String name);

    // Tìm kiếm theo Group Product
    @Query(value = "SELECT p.*,\n" +
            "       p.product_id AS id,\n" +
            "       gp.group_product_name,\n" +
            "       tm.trademark_name,\n" +
            "       l.location_name,\n" +
            "       pp.properties_name,\n" +
            "       u.unit_name,\n" +
            "       c.category_name,\n" +
            "       pi.image_url\n" +
            "FROM product p\n" +
            "         INNER JOIN group_product gp ON p.group_product_id = gp.group_product_id\n" +
            "         INNER JOIN trademark tm ON p.trademark_id = tm.trademark_id\n" +
            "         INNER JOIN location l ON p.location_id = l.location_id\n" +
            "         INNER JOIN properties pp ON p.properties_id = pp.properties_id\n" +
            "         INNER JOIN unit u ON p.unit_id = u.unit_id\n" +
            "         INNER JOIN category c ON p.category_id = c.category_id\n" +
            "         LEFT JOIN product_image pi ON pi.product_id = p.product_id\n" +
            "WHERE p.active = 1\n" +
            "  AND p.group_product_id = ?\n" +
            "ORDER BY p.product_id DESC", nativeQuery = true)
    List<Map<String, Object>> searchGroupProductId(@Param("group_product_id") int groupProductId);

    // Dưới định mức tồn
    @Query(value = "SELECT p.*,\n" +
            "       p.product_id AS id,\n" +
            "       gp.group_product_name,\n" +
            "       tm.trademark_name,\n" +
            "       l.location_name,\n" +
            "       pp.properties_name,\n" +
            "       u.unit_name,\n" +
            "       c.category_name,\n" +
            "       pi.image_url\n" +
            "FROM product p\n" +
            "         INNER JOIN group_product gp ON p.group_product_id = gp.group_product_id\n" +
            "         INNER JOIN trademark tm ON p.trademark_id = tm.trademark_id\n" +
            "         INNER JOIN location l ON p.location_id = l.location_id\n" +
            "         INNER JOIN properties pp ON p.properties_id = pp.properties_id\n" +
            "         INNER JOIN unit u ON p.unit_id = u.unit_id\n" +
            "         INNER JOIN category c ON p.category_id = c.category_id\n" +
            "         LEFT JOIN product_image pi ON pi.product_id = p.product_id\n" +
            "WHERE p.active = 1\n" +
            "  AND p.inventory < 0\n" +
            "ORDER BY id DESC", nativeQuery = true)
    List<Map<String, Object>> searchBelowInventoryThreshold();

    // Vượt định mức tồn
    @Query(value = "SELECT p.*,\n" +
            "       p.product_id AS id,\n" +
            "       gp.group_product_name,\n" +
            "       tm.trademark_name,\n" +
            "       l.location_name,\n" +
            "       pp.properties_name,\n" +
            "       u.unit_name,\n" +
            "       c.category_name,\n" +
            "       pi.image_url\n" +
            "FROM product p\n" +
            "         INNER JOIN group_product gp ON p.group_product_id = gp.group_product_id\n" +
            "         INNER JOIN trademark tm ON p.trademark_id = tm.trademark_id\n" +
            "         INNER JOIN location l ON p.location_id = l.location_id\n" +
            "         INNER JOIN properties pp ON p.properties_id = pp.properties_id\n" +
            "         INNER JOIN unit u ON p.unit_id = u.unit_id\n" +
            "         INNER JOIN category c ON p.category_id = c.category_id\n" +
            "         LEFT JOIN product_image pi ON pi.product_id = p.product_id\n" +
            "WHERE p.active = 1\n" +
            "  AND p.inventory > 200\n" +
            "ORDER BY id DESC", nativeQuery = true)
    List<Map<String, Object>> searchExceedingInventoryLimit();

    // Còn hàng trong kho
    @Query(value = "SELECT p.*,\n" +
            "       p.product_id AS id,\n" +
            "       gp.group_product_name,\n" +
            "       tm.trademark_name,\n" +
            "       l.location_name,\n" +
            "       pp.properties_name,\n" +
            "       u.unit_name,\n" +
            "       c.category_name,\n" +
            "       pi.image_url\n" +
            "FROM product p\n" +
            "         INNER JOIN group_product gp ON p.group_product_id = gp.group_product_id\n" +
            "         INNER JOIN trademark tm ON p.trademark_id = tm.trademark_id\n" +
            "         INNER JOIN location l ON p.location_id = l.location_id\n" +
            "         INNER JOIN properties pp ON p.properties_id = pp.properties_id\n" +
            "         INNER JOIN unit u ON p.unit_id = u.unit_id\n" +
            "         INNER JOIN category c ON p.category_id = c.category_id\n" +
            "         LEFT JOIN product_image pi ON pi.product_id = p.product_id\n" +
            "WHERE p.active = 1\n" +
            "  AND p.inventory > 0\n" +
            "ORDER BY id DESC", nativeQuery = true)
    List<Map<String, Object>> searchStockAvailable();

    // Hết hàng trong kho
    @Query(value = "SELECT p.*,\n" +
            "       p.product_id AS id,\n" +
            "       gp.group_product_name,\n" +
            "       tm.trademark_name,\n" +
            "       l.location_name,\n" +
            "       pp.properties_name,\n" +
            "       u.unit_name,\n" +
            "       c.category_name,\n" +
            "       pi.image_url\n" +
            "FROM product p\n" +
            "         INNER JOIN group_product gp ON p.group_product_id = gp.group_product_id\n" +
            "         INNER JOIN trademark tm ON p.trademark_id = tm.trademark_id\n" +
            "         INNER JOIN location l ON p.location_id = l.location_id\n" +
            "         INNER JOIN properties pp ON p.properties_id = pp.properties_id\n" +
            "         INNER JOIN unit u ON p.unit_id = u.unit_id\n" +
            "         INNER JOIN category c ON p.category_id = c.category_id\n" +
            "         LEFT JOIN product_image pi ON pi.product_id = p.product_id\n" +
            "WHERE p.active = 1\n" +
            "  AND p.inventory = 0\n" +
            "ORDER BY id DESC", nativeQuery = true)
    List<Map<String, Object>> searchNoInventoryAvailable();

    // Trạng thái ngưng hoạt động
    @Query(value = "SELECT p.*,\n" +
            "       p.product_id AS id,\n" +
            "       gp.group_product_name,\n" +
            "       tm.trademark_name,\n" +
            "       l.location_name,\n" +
            "       pp.properties_name,\n" +
            "       u.unit_name,\n" +
            "       c.category_name,\n" +
            "       pi.image_url\n" +
            "FROM product p\n" +
            "         INNER JOIN group_product gp ON p.group_product_id = gp.group_product_id\n" +
            "         INNER JOIN trademark tm ON p.trademark_id = tm.trademark_id\n" +
            "         INNER JOIN location l ON p.location_id = l.location_id\n" +
            "         INNER JOIN properties pp ON p.properties_id = pp.properties_id\n" +
            "         INNER JOIN unit u ON p.unit_id = u.unit_id\n" +
            "         INNER JOIN category c ON p.category_id = c.category_id\n" +
            "         LEFT JOIN product_image pi ON pi.product_id = p.product_id\n" +
            "WHERE p.active = 0\n" +
            "ORDER BY id DESC", nativeQuery = true)
    List<Map<String, Object>> searchNoActive();

    // Trạng thái tất cả hoạt động
    @Query(value = "SELECT p.*,\n" +
            "       p.product_id AS id,\n" +
            "       gp.group_product_name,\n" +
            "       tm.trademark_name,\n" +
            "       l.location_name,\n" +
            "       pp.properties_name,\n" +
            "       u.unit_name,\n" +
            "       c.category_name,\n" +
            "       pi.image_url\n" +
            "FROM product p\n" +
            "         INNER JOIN group_product gp ON p.group_product_id = gp.group_product_id\n" +
            "         INNER JOIN trademark tm ON p.trademark_id = tm.trademark_id\n" +
            "         INNER JOIN location l ON p.location_id = l.location_id\n" +
            "         INNER JOIN properties pp ON p.properties_id = pp.properties_id\n" +
            "         INNER JOIN unit u ON p.unit_id = u.unit_id\n" +
            "         INNER JOIN category c ON p.category_id = c.category_id\n" +
            "         LEFT JOIN product_image pi ON pi.product_id = p.product_id\n" +
            "ORDER BY id DESC",nativeQuery = true)
    List<Map<String, Object>> searchActive();

    @Query(value = "SELECT p.*,\n" +
            "       p.product_id AS id,\n" +
            "       gp.group_product_name,\n" +
            "       tm.trademark_name,\n" +
            "       l.location_name,\n" +
            "       pp.properties_name,\n" +
            "       u.unit_name,\n" +
            "       c.category_name,\n" +
            "       pi.image_url\n" +
            "FROM product p\n" +
            "         INNER JOIN group_product gp ON p.group_product_id = gp.group_product_id\n" +
            "         INNER JOIN trademark tm ON p.trademark_id = tm.trademark_id\n" +
            "         INNER JOIN location l ON p.location_id = l.location_id\n" +
            "         INNER JOIN properties pp ON p.properties_id = pp.properties_id\n" +
            "         INNER JOIN unit u ON p.unit_id = u.unit_id\n" +
            "         INNER JOIN category c ON p.category_id = c.category_id\n" +
            "         LEFT JOIN product_image pi ON pi.product_id = p.product_id\n" +
            "WHERE p.active = 1\n" +
            "AND p.product_id = ?" +
            "ORDER BY id DESC", nativeQuery = true)
    Map<String, Object> getDetailProductId(@Param("product_id") int productId);

    // Tìm kiếm theo vị trí
    @Query(value = "SELECT p.*,\n" +
            "       p.product_id AS id,\n" +
            "       gp.group_product_name,\n" +
            "       tm.trademark_name,\n" +
            "       l.location_name,\n" +
            "       pp.properties_name,\n" +
            "       c.category_name,\n" +
            "       u.unit_name,\n" +
            "       pi.image_url\n" +
            "FROM product p\n" +
            "         INNER JOIN group_product gp ON p.group_product_id = gp.group_product_id\n" +
            "         INNER JOIN trademark tm ON p.trademark_id = tm.trademark_id\n" +
            "         INNER JOIN location l ON p.location_id = l.location_id\n" +
            "         INNER JOIN properties pp ON p.properties_id = pp.properties_id\n" +
            "         INNER JOIN unit u ON p.unit_id = u.unit_id\n" +
            "         INNER JOIN category c ON p.category_id = c.category_id\n" +
            "         LEFT JOIN product_image pi ON pi.product_id = p.product_id\n" +
            "WHERE p.active = 1\n" +
            "  AND p.location_id = ?\n" +
            "ORDER BY id DESC", nativeQuery = true)
    List<Map<String, Object>> searchByLocationId(@Param("location_id") int locationId);


    // Tìm kiếm theo loại sản phẩm
    @Query(value = "SELECT p.*,\n" +
            "       p.product_id AS id,\n" +
            "       gp.group_product_name,\n" +
            "       tm.trademark_name,\n" +
            "       l.location_name,\n" +
            "       pp.properties_name,\n" +
            "       c.category_name,\n" +
            "       u.unit_name,\n" +
            "       pi.image_url\n" +
            "FROM product p\n" +
            "         INNER JOIN group_product gp ON p.group_product_id = gp.group_product_id\n" +
            "         INNER JOIN trademark tm ON p.trademark_id = tm.trademark_id\n" +
            "         INNER JOIN location l ON p.location_id = l.location_id\n" +
            "         INNER JOIN properties pp ON p.properties_id = pp.properties_id\n" +
            "         INNER JOIN unit u ON p.unit_id = u.unit_id\n" +
            "         INNER JOIN category c ON p.category_id = c.category_id\n" +
            "         LEFT JOIN product_image pi ON pi.product_id = p.product_id\n" +
            "WHERE p.active = 1\n" +
            "  AND p.category_id = 1\n" +
            "ORDER BY id DESC;", nativeQuery = true)
    List<Map<String, Object>> searchCategory1();

    @Query(value = "SELECT p.*,\n" +
            "       p.product_id AS id,\n" +
            "       gp.group_product_name,\n" +
            "       tm.trademark_name,\n" +
            "       l.location_name,\n" +
            "       pp.properties_name,\n" +
            "       c.category_name,\n" +
            "       u.unit_name,\n" +
            "       pi.image_url\n" +
            "FROM product p\n" +
            "         INNER JOIN group_product gp ON p.group_product_id = gp.group_product_id\n" +
            "         INNER JOIN trademark tm ON p.trademark_id = tm.trademark_id\n" +
            "         INNER JOIN location l ON p.location_id = l.location_id\n" +
            "         INNER JOIN properties pp ON p.properties_id = pp.properties_id\n" +
            "         INNER JOIN unit u ON p.unit_id = u.unit_id\n" +
            "         INNER JOIN category c ON p.category_id = c.category_id\n" +
            "         LEFT JOIN product_image pi ON pi.product_id = p.product_id\n" +
            "WHERE p.active = 1\n" +
            "  AND p.category_id = 2\n" +
            "ORDER BY id DESC", nativeQuery = true)
    List<Map<String, Object>> searchCategory2();

    @Query(value = "SELECT p.*,\n" +
            "       p.product_id AS id,\n" +
            "       gp.group_product_name,\n" +
            "       tm.trademark_name,\n" +
            "       l.location_name,\n" +
            "       pp.properties_name,\n" +
            "       c.category_name,\n" +
            "       u.unit_name,\n" +
            "       pi.image_url\n" +
            "FROM product p\n" +
            "         INNER JOIN group_product gp ON p.group_product_id = gp.group_product_id\n" +
            "         INNER JOIN trademark tm ON p.trademark_id = tm.trademark_id\n" +
            "         INNER JOIN location l ON p.location_id = l.location_id\n" +
            "         INNER JOIN properties pp ON p.properties_id = pp.properties_id\n" +
            "         INNER JOIN unit u ON p.unit_id = u.unit_id\n" +
            "         INNER JOIN category c ON p.category_id = c.category_id\n" +
            "         LEFT JOIN product_image pi ON pi.product_id = p.product_id\n" +
            "WHERE p.active = 1\n" +
            "  AND p.category_id = 3\n" +
            "ORDER BY id DESC", nativeQuery = true)
    List<Map<String, Object>> searchCategory3();

    // Hàng bán trực tiếp
    @Query(value = "SELECT p.*,\n" +
            "       p.product_id AS id,\n" +
            "       gp.group_product_name,\n" +
            "       tm.trademark_name,\n" +
            "       l.location_name,\n" +
            "       pp.properties_name,\n" +
            "       u.unit_name,\n" +
            "       c.category_name,\n" +
            "       pi.image_url\n" +
            "FROM product p\n" +
            "         INNER JOIN group_product gp ON p.group_product_id = gp.group_product_id\n" +
            "         INNER JOIN trademark tm ON p.trademark_id = tm.trademark_id\n" +
            "         INNER JOIN location l ON p.location_id = l.location_id\n" +
            "         INNER JOIN properties pp ON p.properties_id = pp.properties_id\n" +
            "         INNER JOIN unit u ON p.unit_id = u.unit_id\n" +
            "         INNER JOIN category c ON p.category_id = c.category_id\n" +
            "         LEFT JOIN product_image pi ON pi.product_id = p.product_id\n" +
            "WHERE p.active = 1\n" +
            "  AND p.direct_sales = 1\n" +
            "ORDER BY id DESC", nativeQuery = true)
    List<Map<String, Object>> searchDirectSales();

    // Hàng bán không trực tiếp
    @Query(value = "SELECT p.*,\n" +
            "       p.product_id AS id,\n" +
            "       gp.group_product_name,\n" +
            "       tm.trademark_name,\n" +
            "       l.location_name,\n" +
            "       pp.properties_name,\n" +
            "       u.unit_name,\n" +
            "       c.category_name,\n" +
            "       pi.image_url\n" +
            "FROM product p\n" +
            "         INNER JOIN group_product gp ON p.group_product_id = gp.group_product_id\n" +
            "         INNER JOIN trademark tm ON p.trademark_id = tm.trademark_id\n" +
            "         INNER JOIN location l ON p.location_id = l.location_id\n" +
            "         INNER JOIN properties pp ON p.properties_id = pp.properties_id\n" +
            "         INNER JOIN unit u ON p.unit_id = u.unit_id\n" +
            "         INNER JOIN category c ON p.category_id = c.category_id\n" +
            "         LEFT JOIN product_image pi ON pi.product_id = p.product_id\n" +
            "WHERE p.active = 1\n" +
            "  AND p.direct_sales = 0\n" +
            "ORDER BY id DESC", nativeQuery = true)
    List<Map<String, Object>> searchNoDirectSales();
}
