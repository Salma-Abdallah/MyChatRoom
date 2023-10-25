package gov.iti.jets.persistence.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gov.iti.jets.entities.GroupChatEntity;
import gov.iti.jets.entities.UserEntity;
import gov.iti.jets.persistence.DBConnection;

public class UserGroupDao {


    public int addUserToGroupChat(int userId, String groupChatId) {
        String query = """
                     INSERT INTO user_group(group_chat_id, user_id)
                     values(?, ?)
                """;
        try (Connection connection = DBConnection.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, groupChatId);
            statement.setInt(2, userId);

            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<UserEntity> findAllUsersByGroupChatId(String groupId) {
        List<UserEntity> users = new ArrayList<>();
        String query = """
                SELECT u.* FROM users u
                  INNER JOIN user_group ug
                  ON u.id = ug.user_id and ug.group_chat_id = ?
                  """;

        try (Connection connection = DBConnection.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, groupId);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                users.add(UserDao.resultSetToUserEntity(result));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public List<GroupChatEntity> findAllGroupChatsByUserID(int userId) {
        List<GroupChatEntity> groupChatEntities = new ArrayList<>();
        String query = """
                SELECT gc.* FROM group_chat gc
                INNER JOIN user_group ug
                ON gc.id = ug.group_chat_id and ug.user_id =?""";

        try (Connection connection = DBConnection.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                String groupChatId = result.getString("id");
                int ownerId = result.getInt("owner_id");
                String groupName = result.getString("name");

                groupChatEntities.add(new GroupChatEntity(groupChatId, ownerId, groupName));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return groupChatEntities;
    }

    public int deleteUserFromGroupChat(int userId, String groupChatId) {
        String query = """
                    DELETE FROM user_group WHERE group_chat_id = ? and user_id = ?
                """;

        try (Connection connection = DBConnection.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, groupChatId);
            statement.setInt(2, userId);

            return statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


    

