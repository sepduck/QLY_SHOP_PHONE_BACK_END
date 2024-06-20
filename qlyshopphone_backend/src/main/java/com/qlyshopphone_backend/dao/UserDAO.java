package com.qlyshopphone_backend.dao;

import com.qlyshopphone_backend.JDBC.DatabaseUtil;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class UserDAO {
    public void updateEmployeeStatusAndRole(int userId) {
        String updateEmployeeSql = "UPDATE users SET employee = 1 WHERE user_id = ?";
        String updateRoleSql = "UPDATE users_roles SET role_id = 2 WHERE user_id = ?";

        try (Connection connection = DatabaseUtil.getConnection()) {
            connection.setAutoCommit(false);

            try (PreparedStatement updateEmployeeStatement = connection.prepareStatement(updateEmployeeSql);
                 PreparedStatement updateRoleStatement = connection.prepareStatement(updateRoleSql)) {
                updateEmployeeStatement.setInt(1, userId);
                int rowsUpdatedEmployee = updateEmployeeStatement.executeUpdate();

                if (rowsUpdatedEmployee > 0) {
                    updateRoleStatement.setInt(1, userId);
                    int rowsUpdatedRole = updateRoleStatement.executeUpdate();
                    if (rowsUpdatedRole > 0) {
                        connection.commit();
                        System.out.println("Employee status and role updated successfully for user with ID: " + userId);
                    } else {
                        connection.rollback();
                        System.out.println("Failed to update role for user with ID: " + userId);
                    }
                } else {
                    connection.rollback();
                    System.out.println("Failed to update employee status for user with ID: " + userId);
                }
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
