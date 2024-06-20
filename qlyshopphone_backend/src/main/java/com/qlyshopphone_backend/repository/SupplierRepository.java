package com.qlyshopphone_backend.repository;

import com.qlyshopphone_backend.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
    @Query(value = "SELECT s.*,\n" +
            "       s.supplier_id AS id,\n" +
            "       p.product_name,\n" +
            "       gs.group_supplier_name\n" +
            "FROM supplier s\n" +
            "         INNER JOIN group_supplier gs ON s.group_supplier_id = gs.group_supplier_id\n" +
            "         INNER JOIN product p ON s.product_id = p.product_id\n" +
            "WHERE s.active = 1\n" +
            "ORDER BY id DESC;", nativeQuery = true)
    List<Map<String, Object>> getSuppliers();

    @Query(value = "SELECT s.*,\n" +
            "       s.supplier_id AS id,\n" +
            "       p.product_name,\n" +
            "       gs.group_supplier_name\n" +
            "FROM supplier s\n" +
            "         INNER JOIN group_supplier gs ON s.group_supplier_id = gs.group_supplier_id\n" +
            "         INNER JOIN product p ON s.product_id = p.product_id\n" +
            "ORDER BY id DESC;", nativeQuery = true)
    List<Map<String, Object>> getAllSuppliers();

    // Delete supplier
    @Query(value = "UPDATE supplier\n" +
            "SET active = 0\n" +
            "WHERE supplier_id = ?", nativeQuery = true)
    void softDeleteSupplierId(int supplierId);


    @Query(value = "SELECT s.*,\n" +
            "       s.supplier_id AS id,\n" +
            "       p.product_name,\n" +
            "       gs.group_supplier_name\n" +
            "FROM supplier s\n" +
            "         INNER JOIN group_supplier gs ON s.group_supplier_id = gs.group_supplier_id\n" +
            "         INNER JOIN product p ON s.product_id = p.product_id\n" +
            "WHERE s.active = 1\n" +
            "AND s.phone_number LIKE %?%\n" +
            "ORDER BY id DESC;", nativeQuery = true)
    List<Map<String, Object>> searchAllByPhoneNumber(@Param("phone_number") String phoneNumber);

    @Query(value = "SELECT s.*,\n" +
            "       s.supplier_id AS id,\n" +
            "       p.product_name,\n" +
            "       gs.group_supplier_name\n" +
            "FROM supplier s\n" +
            "         INNER JOIN group_supplier gs ON s.group_supplier_id = gs.group_supplier_id\n" +
            "         INNER JOIN product p ON s.product_id = p.product_id\n" +
            "WHERE s.active = 1\n" +
            "AND s.tax_code LIKE %?%\n" +
            "ORDER BY id DESC;", nativeQuery = true)
    List<Map<String, Object>> searchAllByTaxCode(@Param("tax_code") String taxCode);

    @Query(value = "SELECT s.*,\n" +
            "       s.supplier_id AS id,\n" +
            "       p.product_name,\n" +
            "       gs.group_supplier_name\n" +
            "FROM supplier s\n" +
            "         INNER JOIN group_supplier gs ON s.group_supplier_id = gs.group_supplier_id\n" +
            "         INNER JOIN product p ON s.product_id = p.product_id\n" +
            "WHERE s.active = 1\n" +
            "AND s.supplier_name LIKE %?%\n" +
            "ORDER BY id DESC;", nativeQuery = true)
    List<Map<String, Object>> searchAllBySupplierNameLike(@Param("supplier_name") String supplierName);

    @Query(value = "SELECT s.*,\n" +
            "       s.supplier_id AS id,\n" +
            "       p.product_name,\n" +
            "       gs.group_supplier_name\n" +
            "FROM supplier s\n" +
            "         INNER JOIN group_supplier gs ON s.group_supplier_id = gs.group_supplier_id\n" +
            "         INNER JOIN product p ON s.product_id = p.product_id\n" +
            "WHERE s.active = 1\n" +
            "AND s.supplier_id = ?\n" +
            "ORDER BY id DESC;", nativeQuery = true)
    List<Map<String, Object>> searchByGroupSupplierId(@Param("group_supplier_id") int groupSupplierId);

    @Query(value = "SELECT s.*,\n" +
            "       s.supplier_id AS id,\n" +
            "       p.product_name,\n" +
            "       gs.group_supplier_name\n" +
            "FROM supplier s\n" +
            "         INNER JOIN group_supplier gs ON s.group_supplier_id = gs.group_supplier_id\n" +
            "         INNER JOIN product p ON s.product_id = p.product_id\n" +
            "WHERE s.active = 0\n" +
            "ORDER BY id DESC;", nativeQuery = true)
    List<Map<String, Object>> searchNoActive();

    boolean existsBySupplierNameAndTaxCode(String supplierName, String taxCode);
}
