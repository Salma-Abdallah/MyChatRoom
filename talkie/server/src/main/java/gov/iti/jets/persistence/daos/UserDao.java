package gov.iti.jets.persistence.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import gov.iti.jets.entities.UserEntity;
import gov.iti.jets.persistence.DBConnection;

public class UserDao {


    public UserEntity saveUser(UserEntity userEntity) {

        String query = """
                    INSERT INTO users (username , password, phone_number, email, gender,
                country, birth_date, online_status, bio, picture, created_at, salt)
                values (?,?,?,?,?,?,?,?,?,?,?,?)
                """;
        try (Connection connection = DBConnection.INSTANCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, userEntity.getUserName());
            statement.setString(4, userEntity.getPassword());
            statement.setString(2, userEntity.getPhoneNumber());
            statement.setString(3, userEntity.getEmail());
            statement.setString(5, userEntity.getGender());
            statement.setString(6, userEntity.getCountry());
            statement.setString(7, userEntity.getBirthDate().toString());
            statement.setString(8, userEntity.getOnlineStatus());
            statement.setString(9, userEntity.getBio());
            statement.setString(10, userEntity.getPictureUrl());
            statement.setTimestamp(11, Timestamp.valueOf(LocalDateTime.now()));
            statement.setString(12, userEntity.getSalt());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userEntity;
    }

    public Optional<UserEntity> findUserById(Integer userId) {
        String query = """
                 SELECT * FROM users where id = ?
                """;
        try (Connection connection = DBConnection.INSTANCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return Optional.of(resultSetToUserEntity(result));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public Optional<UserEntity> findUserByPhoneNumber(String phoneNumber) {
        String query = """
                 SELECT * FROM users where phone_number = ?
                """;
        return getUserEntity(phoneNumber, query);
    }

    public Optional<UserEntity> findUserByEmail(String email) {
        String query = """
                 SELECT * FROM users where email = ?
                """;
        return getUserEntity(email, query);
    }

    // public Optional<List<UserEntity>> findUserByOnlineStatus(String onlineStatus) {
    //     String query = """

    //             SELECT * FROM users where online_status = ?
    //             """;
    //     return getUsersEntity(onlineStatus, query);
    // }
    public Optional<List<UserEntity>> findUserByOnlineStatus(String onlineStatus, String query) {
        try (Connection connection = DBConnection.INSTANCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, onlineStatus);
            ResultSet result = statement.executeQuery();
            List<UserEntity> users = new ArrayList<>();
            while (result.next()) {
                users.add(resultSetToUserEntity(result));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getOnlineStatusByPhoneNumber(String phoneNumber) {
        String query = """
                    SELECT online_status FROM users WHERE phone_number = ?
                """;
        try (Connection connection = DBConnection.INSTANCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, phoneNumber);
            ResultSet result = statement.executeQuery();

            return result.getString("online_status");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Optional<UserEntity> getUserEntity(String providedInput, String query) {
        try (Connection connection = DBConnection.INSTANCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, providedInput);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return Optional.of(resultSetToUserEntity(result));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static UserEntity resultSetToUserEntity(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String userName = resultSet.getString("username");
        String password = resultSet.getString("password");
        String phoneNumber = resultSet.getString("phone_number");
        String email = resultSet.getString("email");
        String gender = resultSet.getString("gender");
        String country = resultSet.getString("country");
        LocalDate birthDate = resultSet.getDate("birth_date").toLocalDate();
        String onlineStatus = resultSet.getString("online_status");
        String imageUrl = resultSet.getString("picture");
        Timestamp createdAt = resultSet.getTimestamp("created_at");
        String bio = resultSet.getString("bio");
        String salt = resultSet.getString("salt");
        return new UserEntity(id, userName, phoneNumber, email, password, gender, country, birthDate, onlineStatus, bio,
                imageUrl, createdAt, salt);
    }
       
}
