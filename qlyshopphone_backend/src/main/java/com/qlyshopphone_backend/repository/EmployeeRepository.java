package com.qlyshopphone_backend.repository;

import com.qlyshopphone_backend.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface EmployeeRepository extends JpaRepository<Users, Integer> {
    @Query(value = "SELECT u.*, u.user_id AS id, g.gender_name\n" +
            "FROM users u\n" +
            "         LEFT JOIN gender g on u.gender_id = g.gender_id\n" +
            "WHERE u.employee = 1\n" +
            "  AND u.active = 1\n" +
            "ORDER BY id DESC;", nativeQuery = true)
    List<Map<String, Object>> getEmployeeList();

    // Tìm kiếm theo tên nhân viên
    @Query(value = "SELECT u.*, u.user_id AS id, g.gender_name\n" +
            "FROM users u\n" +
            "         LEFT JOIN gender g on u.gender_id = g.gender_id\n" +
            "WHERE u.employee = 1\n" +
            "  AND u.active = 1\n" +
            "  AND u.full_name LIKE %?%\n" +
            "ORDER BY id DESC", nativeQuery = true)
    List<Map<String, Object>> searchEmployeeName(@Param("full_name") String fullName);

    // Tìm kiếm theo mã nhân viên
    @Query(value = "SELECT u.*, u.user_id AS id, g.gender_name\n" +
            "FROM users u\n" +
            "         LEFT JOIN gender g on u.gender_id = g.gender_id\n" +
            "WHERE u.employee = 1\n" +
            "  AND u.active = 1\n" +
            "  AND u.user_id = ?\n" +
            "ORDER BY id DESC", nativeQuery = true)
    List<Map<String, Object>> searchEmployeeId(@Param("user_id") int userId);

    // Tìm kiếm theo số điện thoại
    @Query(value = "SELECT u.*, u.user_id AS id, g.gender_name\n" +
            "FROM users u\n" +
            "         LEFT JOIN gender g on u.gender_id = g.gender_id\n" +
            "WHERE u.employee = 1\n" +
            "  AND u.active = 1\n" +
            "  AND u.phone_number LIKE %?%\n" +
            "ORDER BY id DESC", nativeQuery = true)
    List<Map<String, Object>> searchPhoneNumber(@Param("phone_number") String phoneNumber);

    // Tìm kiếm theo trạng thái
    @Query(value = "SELECT u.*, u.user_id AS id, g.gender_name\n" +
            "FROM users u\n" +
            "         LEFT JOIN gender g on u.gender_id = g.gender_id\n" +
            "WHERE u.employee = 1\n" +
            "  AND u.active = 0\n" +
            "ORDER BY id DESC", nativeQuery = true)
    List<Map<String, Object>> searchByNoActive();

    @Query(value = "SELECT u.*, u.user_id AS id, g.gender_name\n" +
            "FROM users u\n" +
            "         LEFT JOIN gender g on u.gender_id = g.gender_id\n" +
            "WHERE u.employee = 1\n" +
            "ORDER BY id DESC", nativeQuery = true)
    List<Map<String, Object>> searchByAllActive();
}
