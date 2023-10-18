package gov.iti.jets.persistence.daos;

import gov.iti.jets.entities.MessageEntity;
import gov.iti.jets.entities.UserEntity;
import gov.iti.jets.persistence.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MessageDao {
    public Optional<MessageEntity> saveMessage(MessageEntity messageEntity){
        String query = """
                            INSERT INTO messages(chat_id, author_id, font_style, font_color,
                             font_size, bold, italic, text_background, underlined, sent_at, content, file_url)
                            values(?, ?, ? ,?, ?, ?, ?, ?, ?, ?, ?, ?)
                       """;


        try (Connection connection = DBConnection.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1, messageEntity.getChatId());
            statement.setInt(2, messageEntity.getAuthor().getId());
            statement.setString(3, messageEntity.getFontStyle());
            statement.setString(4, messageEntity.getFontColor());
            statement.setDouble(5, messageEntity.getFontSize());
            statement.setBoolean(6, messageEntity.isBold());
            statement.setBoolean(7, messageEntity.isItalic());
            statement.setString(8, messageEntity.getTextBackground());
            statement.setBoolean(9, messageEntity.isUnderlined());
            statement.setTimestamp(10, messageEntity.getSentAt());
            statement.setString(11, messageEntity.getContent());
            statement.setString(12, messageEntity.getFileUrl());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                messageEntity.setId(generatedKeys.getInt(1));
            }
            return Optional.of(messageEntity);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }


    public List<MessageEntity> findMessagesByChatID(String chatId){
        String query = """
                            SELECT u.id uId, m.id mId, u.*, m.*
                            FROM messages m
                            INNER JOIN users u
                            ON u.id = m.author_id
                            WHERE chat_id = ?
                            ORDER BY m.sent_at
                       """;
        List<MessageEntity> messageEntities = new ArrayList<>();
        try(Connection connection = DBConnection.INSTANCE.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){

            statement.setString(1, chatId);
            ResultSet result = statement.executeQuery();
            UserEntity author;
            while (result.next()){
                int authorId = result.getInt("uId");
                String userName = result.getString("username");
                String phoneNumber = result.getString("phone_number");
                String email = result.getString("email");
                String password = result.getString("password");
                String gender = result.getString("gender");
                String country = result.getString("country");
                LocalDate birthDate = result.getDate("birth_date").toLocalDate();
                Timestamp createdAt = result.getTimestamp("created_at");
                String onlineStatus = result.getString("online_status");
                String bio = result.getString("bio");
                String salt = result.getString("salt");
                String imageUrl = result.getString("picture");

                author = new UserEntity(authorId, userName, phoneNumber, email, password, gender, country, birthDate, onlineStatus, bio, imageUrl, createdAt, salt);

                int id = result.getInt("mId");
                String fontStyle = result.getString("font_style");
                String fontColor = result.getString("font_color");
                double fontSize = result.getDouble("font_size");
                boolean isBold = result.getBoolean("bold");
                boolean isItalic = result.getBoolean("italic");
                String textBackground = result.getString("text_background");
                boolean isUnderlined = result.getBoolean("underlined");
                Timestamp sentAt = result.getTimestamp("sent_at");
                String content = result.getString("content");
                String fileUrl = result.getString("file_url");
                boolean seen = result.getBoolean("seen");
                messageEntities.add(new MessageEntity(id, author, chatId, fontStyle, fontColor, fontSize, isBold, isItalic, isUnderlined, textBackground, sentAt , content, fileUrl, seen));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return messageEntities;
    }
    public int updateMessageStatusByPhoneNumberAndChatId(String phoneNumber, String chatId){
        String query = """
                        update messages
                        set seen = 1
                        where chatId = ? AND author_id = 	(	select id
                        										from users
                        										where phone_number = ?
                        									);
                       """;
        try (Connection connection = DBConnection.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, chatId);
            statement.setString(2, phoneNumber);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int updateFileURLinMessageByPhoneNumberAndChatId(String phoneNumber , String chatId , String fileUrl) {
        String query = """
                        update messages
                        set file_url = ?
                        where chatId = ? AND author_id = 	(	select id
                        										from users
                        										where phone_number = ?
                        									);
                       """;

        try (Connection connection = DBConnection.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, fileUrl);
            statement.setString(2, chatId);
            statement.setString(3, phoneNumber);

            return statement.executeUpdate();

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }


}
