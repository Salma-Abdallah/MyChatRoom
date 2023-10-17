package gov.iti.jets.persistence.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import gov.iti.jets.entities.ChatEntity;
import gov.iti.jets.persistence.DBConnection;

public class ChatDao {

    public String save(String uuid){
        String query = """
                             INSERT INTO chat(id) VALUES (?)
                       """;
        try (Connection connection = DBConnection.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, uuid);
            statement.executeUpdate();
            return uuid;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<ChatEntity> findChatById(String uuid){
        String query = """
                            SELECT * FROM chat WHERE id = ?
                       """;
        try (Connection connection = DBConnection.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, uuid);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return Optional.of(new ChatEntity(resultSet.getString("id")));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int delete(String id){
        String query = """
                        DELETE FROM chat WHERE id = ?
                        """;
        try(Connection connection = DBConnection.INSTANCE.getConnection();
                    PreparedStatement statement = connection.prepareStatement(query)){
                statement.setString(1, id);
                return statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
        }
    }
}
