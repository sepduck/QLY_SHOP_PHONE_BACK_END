package com.qlyshopphone_backend.repository;

import com.qlyshopphone_backend.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    @Query(value = "SELECT u.*, u.user_id AS id, r.role_name\n" +
            "FROM users u\n" +
            "         INNER JOIN users_roles ur ON u.user_id = ur.user_id\n" +
            "         INNER JOIN roles r ON ur.role_id = r.role_id\n" +
            "WHERE u.active = 1", nativeQuery = true)
    List<Map<String, Object>> getAllUsers();

    @Query(value = "SELECT u.*, u.user_id AS id, r.role_name\n" +
            "FROM users u\n" +
            "         INNER JOIN users_roles ur ON u.user_id = ur.user_id\n" +
            "         INNER JOIN roles r ON ur.role_id = r.role_id\n" +
            "    WHERE u.active = 1\n" +
            "AND u.employee = 1", nativeQuery = true)
    List<Map<String, Object>> getEmployee();



    Users findByUsername(String username);
}
