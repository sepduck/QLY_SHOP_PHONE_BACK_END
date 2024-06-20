package com.qlyshopphone_backend.repository;

import com.qlyshopphone_backend.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CustomerRepository extends JpaRepository<Users, Integer> {
    @Query(value = "SELECT u.*, u.user_id AS id, g.gender_name\n" +
            "FROM users u\n" +
            "         LEFT JOIN gender g on u.gender_id = g.gender_id\n" +
            "WHERE u.employee = 0\n" +
            "  AND u.active = 1\n" +
            "ORDER BY id DESC;", nativeQuery = true)
    List<Map<String, Object>> getCustomer();

    // Tìm kiếm theo tên khách hàng
    @Query(value = "SELECT u.*, u.user_id AS id, g.gender_name\n" +
            "FROM users u\n" +
            "         LEFT JOIN gender g on u.gender_id = g.gender_id\n" +
            "WHERE u.employee = 0\n" +
            "  AND u.active = 1\n" +
            "  AND u.full_name LIKE %?%\n" +
            "ORDER BY id DESC", nativeQuery = true)
    List<Map<String, Object>> searchCustomerName(@Param("full_name") String fullName);

    // Tìm kiếm theo mã khách hàng
    @Query(value = "SELECT u.*, u.user_id AS id, g.gender_name\n" +
            "FROM users u\n" +
            "         LEFT JOIN gender g on u.gender_id = g.gender_id\n" +
            "WHERE u.employee = 0\n" +
            "  AND u.active = 1\n" +
            "  AND u.user_id = ?\n" +
            "ORDER BY id DESC", nativeQuery = true)
    List<Map<String, Object>> searchCustomerId(@Param("user_id") int userId);

    // Tìm kiếm theo số điện thoại
    @Query(value = "SELECT u.*, u.user_id AS id, g.gender_name\n" +
            "FROM users u\n" +
            "         LEFT JOIN gender g on u.gender_id = g.gender_id\n" +
            "WHERE u.employee = 0\n" +
            "  AND u.active = 1\n" +
            "  AND u.phone_number LIKE %?%\n" +
            "ORDER BY id DESC", nativeQuery = true)
    List<Map<String, Object>> searchByPhoneNumber(@Param("phone_number") String phoneNumber);

    // Tìm kiếm theo email
    @Query(value = "SELECT u.*, u.user_id AS id, g.gender_name\n" +
            "FROM users u\n" +
            "         LEFT JOIN gender g on u.gender_id = g.gender_id\n" +
            "WHERE u.employee = 0\n" +
            "  AND u.active = 1\n" +
            "  AND u.email LIKE %?%\n" +
            "ORDER BY id DESC", nativeQuery = true)
    List<Map<String, Object>> searchByEmail(@Param("email") String email);

    // Tìm kiếm theo số điện thoại
    @Query(value = "SELECT u.*, u.user_id AS id, g.gender_name\n" +
            "FROM users u\n" +
            "         LEFT JOIN gender g on u.gender_id = g.gender_id\n" +
            "WHERE u.employee = 0\n" +
            "  AND u.active = 1\n" +
            "  AND u.address LIKE %?%\n" +
            "ORDER BY id DESC", nativeQuery = true)
    List<Map<String, Object>> searchByAddress(@Param("address") String address);

    // Tìm kiếm theo trạng thái
    @Query(value = "SELECT u.*, u.user_id AS id, g.gender_name\n" +
            "FROM users u\n" +
            "         LEFT JOIN gender g on u.gender_id = g.gender_id\n" +
            "WHERE u.employee = 0\n" +
            "  AND u.active = 0\n" +
            "ORDER BY id DESC", nativeQuery = true)
    List<Map<String, Object>> searchByActive(@Param("active") byte active);

    // Tìm kiếm theo giới tính
    @Query(value = "SELECT u.*, u.user_id AS id, g.gender_name\n" +
            "FROM users u\n" +
            "         LEFT JOIN gender g on u.gender_id = g.gender_id\n" +
            "WHERE u.employee = 0\n" +
            "  AND u.active = 1\n" +
            "  AND u.gender_id = 1\n" +
            "ORDER BY id DESC;", nativeQuery = true)
    List<Map<String, Object>> searchByGenderId1();

    @Query(value = "SELECT u.*, u.user_id AS id, g.gender_name\n" +
            "FROM users u\n" +
            "         LEFT JOIN gender g on u.gender_id = g.gender_id\n" +
            "WHERE u.employee = 0\n" +
            "  AND u.active = 1\n" +
            "  AND u.gender_id = 2\n" +
            "ORDER BY id DESC;", nativeQuery = true)
    List<Map<String, Object>> searchByGenderId2();

    @Query(value = "SELECT u.*, u.user_id AS id, g.gender_name\n" +
            "FROM users u\n" +
            "         LEFT JOIN gender g on u.gender_id = g.gender_id\n" +
            "WHERE u.employee = 0\n" +
            "  AND u.active = 1\n" +
            "  AND u.gender_id = 3\n" +
            "ORDER BY id DESC;", nativeQuery = true)
    List<Map<String, Object>> searchByGenderId3();
}
