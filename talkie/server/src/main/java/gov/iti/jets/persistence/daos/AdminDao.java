package gov.iti.jets.persistence.daos;

import gov.iti.jets.entities.AdminEntity;
import gov.iti.jets.persistence.DBConnection;

import java.sql.*;
import java.util.Optional;

public class AdminDao {

    public Optional<AdminEntity> findAdminByPhoneNumber(String phone) {
        String query = """
                 SELECT * FROM admins where phone_number = ?
                """;
        try (Connection connection = DBConnection.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, phone);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                int id = result.getInt("id");
                String adminName = result.getString("admin_name");
                String phoneNumber = result.getString("phone_number");
                String password = result.getString("password");
                String salt = result.getString("salt");
                return Optional.of(new AdminEntity(id, adminName, phoneNumber, password, salt));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}