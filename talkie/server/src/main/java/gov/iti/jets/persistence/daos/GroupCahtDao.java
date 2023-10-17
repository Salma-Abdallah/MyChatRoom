package gov.iti.jets.persistence.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import gov.iti.jets.entities.GroupChatEntity;
import gov.iti.jets.persistence.DBConnection;

public class GroupCahtDao {

    public GroupChatEntity save(GroupChatEntity groupChatEntity) {
        String query = """
                     INSERT INTO group_chat(id, owner_id, name)
                     values(?, ?, ?)
                """;
        String uuid = UUID.randomUUID().toString();
        ChatDao chatDao = new ChatDao();
        try (Connection connection = DBConnection.INSTANCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, chatDao.save(uuid));
            statement.setInt(2, groupChatEntity.getOwnerId());
            statement.setString(3, groupChatEntity.getName());
            statement.executeUpdate();

            groupChatEntity.setChatId(uuid);
            return groupChatEntity;
        } catch (SQLException e) {
            chatDao.delete(uuid);
            throw new RuntimeException(e);
        }
    }

    public List<GroupChatEntity> findAllGroupChatsByOwnerId(Integer ownerId) {
        List<GroupChatEntity> groupChatEntities = new ArrayList<>();
        String query = """
                    SELECT *
                    FROM group_chat
                    WHERE owner_id = ?
                """;
        try (Connection connection = DBConnection.INSTANCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, ownerId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                String groupChatId = result.getString("id");
                String groupName = result.getString("name");
                groupChatEntities.add(new GroupChatEntity(groupChatId, ownerId, groupName));
            }
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
        return groupChatEntities;
    }

    public Optional<GroupChatEntity> findGroupChatByOwnerIdAndChatName(Integer ownerId, String name) {
        String query = """
                    SELECT *
                    FROM group_chat
                    WHERE owner_id = ? AND name = ?
                """;
        try (Connection connection = DBConnection.INSTANCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, ownerId);
            statement.setString(2, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String id = resultSet.getString("id");
                return Optional.of(new GroupChatEntity(id, ownerId, name));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<GroupChatEntity> findGroupChatByChatId(String chatId) {
        String query = """
                    SELECT *
                    FROM group_chat
                    WHERE id = ?
                """;
        try (Connection connection = DBConnection.INSTANCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, chatId);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                String groupChatId = result.getString("id");
                Integer ownerId = result.getInt("owner_id");
                String groupName = result.getString("name");
                return Optional.of(new GroupChatEntity(groupChatId, ownerId, groupName));
            }
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    public int delete(String id) {
        String query = """
                    DELETE FROM group_chat
                    WHERE id = ?
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

}
