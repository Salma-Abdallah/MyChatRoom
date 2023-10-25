package gov.iti.jets.persistence.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import gov.iti.jets.entities.RegularChatEntity;
import gov.iti.jets.entities.UserEntity;
import gov.iti.jets.persistence.DBConnection;

public class RegularChatDao {

    public RegularChatEntity save(RegularChatEntity regularChatEntity) {
        String query = """
                     INSERT INTO regular_chat(id, first_participant_id, second_participant_id)
                     values(?, ?, ?)
                """;
        String uuid = UUID.randomUUID().toString();
        ChatDao chatDao = new ChatDao();
        try (Connection connection = DBConnection.INSTANCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, chatDao.save(uuid));
            statement.setInt(2, regularChatEntity.getFirstParticipantId().getId());
            statement.setInt(3, regularChatEntity.getSecondParticipantId().getId());
            statement.executeUpdate();
            regularChatEntity.setId(uuid);
            return regularChatEntity;
        } catch (SQLException e) {
            chatDao.delete(uuid);
            throw new RuntimeException(e);
        }
    }

    public Optional<RegularChatEntity> findRegularChatByParticipantsIds(UserEntity firstParticipant,
            UserEntity secondParticipant) {
        String query = """
                    SELECT * FROM regular_chat rc
                    WHERE (rc.first_participant_id = ? AND rc.second_participant_id = ?)
                            OR (rc.first_participant_id = ? AND rc.second_participant_id = ?)
                """;
        try (Connection connection = DBConnection.INSTANCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, firstParticipant.getId());
            statement.setInt(2, secondParticipant.getId());
            statement.setInt(3, secondParticipant.getId());
            statement.setInt(4, firstParticipant.getId());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int firstParticipantId = resultSet.getInt("first_participant_id");
                int secondParticipantId = resultSet.getInt("second_participant_id");

                UserDao userDao = new UserDao();
                Optional<UserEntity> firstPartiUserOptional = userDao.findUserById(firstParticipantId);
                Optional<UserEntity> secondPartiUserOptional = userDao.findUserById(secondParticipantId);

                UserEntity firstPartiUser = firstPartiUserOptional
                        .orElseThrow(() -> new RuntimeException("User not found."));
                UserEntity secondPartiUser = secondPartiUserOptional
                        .orElseThrow(() -> new RuntimeException("User not found."));

                return Optional.of(new RegularChatEntity(
                        resultSet.getString("id"),
                        firstPartiUser,
                        secondPartiUser));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<RegularChatEntity> findRegularChatByParticipantsPhoneNumbers(String phoneNumber1,
            String phoneNumber2) {
        String query = """
                    SELECT rc.*
                    FROM regular_chat rc
                    INNER JOIN users u1 ON rc.first_participant_id = u1.id
                    INNER JOIN users u2 ON rc.second_participant_id = u2.id
                    WHERE u1.phone_number = ? AND u2.phone_number = ?
                """;
        try (Connection connection = DBConnection.INSTANCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, phoneNumber1);
            statement.setString(2, phoneNumber2);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int firstParticipantId = resultSet.getInt("first_participant_id");
                int secondParticipantId = resultSet.getInt("second_participant_id");

                UserDao userDao = new UserDao();
                Optional<UserEntity> firstPartiUserOptional = userDao.findUserById(firstParticipantId);
                Optional<UserEntity> secondPartiUserOptional = userDao.findUserById(secondParticipantId);

                UserEntity firstPartiUser = firstPartiUserOptional
                        .orElseThrow(() -> new RuntimeException("User not found."));
                UserEntity secondPartiUser = secondPartiUserOptional
                        .orElseThrow(() -> new RuntimeException("User not found."));

                return Optional.of(new RegularChatEntity(
                        resultSet.getString("id"),
                        firstPartiUser,
                        secondPartiUser));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<RegularChatEntity> findRegularChatByParticipantPhoneNumber(String phoneNumber) {
        String query = """
                    SELECT rc.*
                    FROM regular_chat rc
                    INNER JOIN users u1 ON rc.first_participant_id = u1.id
                    INNER JOIN users u2 ON rc.second_participant_id = u2.id
                    WHERE u1.phone_number = ? OR u2.phone_number = ?
                """;

        try (Connection connection = DBConnection.INSTANCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, phoneNumber);
            statement.setString(2, phoneNumber);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int firstParticipantId = resultSet.getInt("first_participant_id");
                int secondParticipantId = resultSet.getInt("second_participant_id");

                UserDao userDao = new UserDao();
                Optional<UserEntity> firstPartiUserOptional = userDao.findUserById(firstParticipantId);
                Optional<UserEntity> secondPartiUserOptional = userDao.findUserById(secondParticipantId);

                UserEntity firstPartiUser = firstPartiUserOptional
                        .orElseThrow(() -> new RuntimeException("User not found."));
                UserEntity secondPartiUser = secondPartiUserOptional
                        .orElseThrow(() -> new RuntimeException("User not found."));

                return Optional.of(new RegularChatEntity(
                        resultSet.getString("id"),
                        firstPartiUser,
                        secondPartiUser));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<RegularChatEntity> findAllRegChatsByUserID(int userId) {
        List<RegularChatEntity> regularChatEntities = new ArrayList<>();
        String query = """
                     SELECT * FROM regular_chat where first_participant_id = ? or second_participant_id = ?
                """;
        try (Connection connection = DBConnection.INSTANCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, userId);
            statement.setInt(2, userId);

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                String chat_id = result.getString("id");
                int firstParticipantId = result.getInt("first_participant_id");
                int secondParticipantId = result.getInt("second_participant_id");
                UserDao userDao = new UserDao();
                Optional<UserEntity> firstPartiUserOptional = userDao.findUserById(firstParticipantId);
                Optional<UserEntity> secondPartiUserOptional = userDao.findUserById(secondParticipantId);
                UserEntity firstPartiUser = firstPartiUserOptional
                        .orElseThrow(() -> new RuntimeException("User not found."));
                UserEntity secondPartiUser = secondPartiUserOptional
                        .orElseThrow(() -> new RuntimeException("User not found."));

                regularChatEntities.add(new RegularChatEntity(chat_id, firstPartiUser, secondPartiUser));
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return regularChatEntities;
    }

    public int delete(String id) {
        String query = """
                    DELETE FROM regular_chat WHERE id = ?
                """;
        try (Connection connection = DBConnection.INSTANCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, id);
            statement.executeUpdate();
            return new ChatDao().delete(id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<RegularChatEntity> findAllRegularChatByPhoneNum(String phoneNum) {
        String query = """
                    SELECT rc.*
                    FROM regular_chat rc
                    INNER JOIN users u1 ON rc.first_participant_id = u1.id
                    INNER JOIN users u2 ON rc.second_participant_id = u2.id
                    WHERE u1.phone_number = ? OR u2.phone_number = ?
                """;
        try (Connection connection = DBConnection.INSTANCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, phoneNum);
            statement.setString(2, phoneNum);
            ResultSet resultSet = statement.executeQuery();

            List<RegularChatEntity> chatEntities = new ArrayList<>();
            UserDao userDao = new UserDao();
            while (resultSet.next()) {
                int firstParticipantId = resultSet.getInt("first_participant_id");
                int secondParticipantId = resultSet.getInt("second_participant_id");
                Optional<UserEntity> firstPartiUserOptional = userDao.findUserById(firstParticipantId);
                Optional<UserEntity> secondPartiUserOptional = userDao.findUserById(secondParticipantId);
                if (firstPartiUserOptional.isPresent() && secondPartiUserOptional.isPresent()) {
                    UserEntity firstPartiUser = firstPartiUserOptional.get();
                    UserEntity secondPartiUser = secondPartiUserOptional.get();

                    chatEntities.add(new RegularChatEntity(
                            resultSet.getString("id"),
                            firstPartiUser,
                            secondPartiUser));
                }
            }
            return chatEntities;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
