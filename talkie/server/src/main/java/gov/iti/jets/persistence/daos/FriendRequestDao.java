package gov.iti.jets.persistence.daos;


import gov.iti.jets.persistence.DBConnection;
import gov.iti.jets.entities.FriendRequestEntity;
import gov.iti.jets.entities.UserEntity;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FriendRequestDao {


    public int save(Integer senderId, Integer receiverId){
        String query = """
                            INSERT INTO friend_request(sender_id, receiver_id, status, sent_at) VALUES (?, ?, ?, ?)
                        """;
        try(Connection connection = DBConnection.INSTANCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)){
                statement.setInt(1, senderId);
                statement.setInt(2, receiverId);
                statement.setBoolean(3, false);
                statement.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
                return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<FriendRequestEntity> findSentFriendRequestByUserID(UserEntity user) {
        String query = """
                           SELECT fr.id fr_id, fr.status, fr.sent_at, u.* 
                           FROM users u
                           JOIN friend_request fr
                           ON u.id = fr.receiver_id
                           WHERE fr.sender_id = ?
                       """;
        try(Connection connection = DBConnection.INSTANCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, user.getId());
            ResultSet result = statement.executeQuery();
            List<FriendRequestEntity> friendRequests = new ArrayList<>();
            while (result.next()){
                int id = result.getInt("fr_id");
                int senderId = result.getInt("id");
                boolean requestStatus = result.getBoolean("status");
                Timestamp sentAt = result.getTimestamp("sent_at");
                friendRequests.add(new FriendRequestEntity(id, user, new UserDao().findUserById(senderId).get(), requestStatus, sentAt));
            }
            return friendRequests;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<FriendRequestEntity> findReceivedFriendRequestByUserID(UserEntity receiver) {
        String query = """
                            SELECT fr.id fr_id, fr.status, fr.sent_at, u.* 
                            FROM users u
                            JOIN friend_request fr
                            ON u.id = fr.sender_id
                            WHERE fr.receiver_id = ?;
                       """;
        try(Connection connection = DBConnection.INSTANCE.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){
            List<FriendRequestEntity> friendRequests = new ArrayList<>();
            statement.setInt(1, receiver.getId());
            ResultSet result = statement.executeQuery();
            while (result.next()){
                int id = result.getInt("fr_id");
                int recieverId = result.getInt("id");
                boolean requestStatus = result.getBoolean("status");
                Timestamp sentAt = result.getTimestamp("sent_at");
                friendRequests.add(new FriendRequestEntity(id,new UserDao().findUserById(recieverId).get(), receiver, requestStatus, sentAt));
            }
            return friendRequests;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int delete (Integer senderId, Integer receiverId){
        String query = """
                            DELETE FROM friend_request
                            WHERE sender_id = ? AND receiver_id = ?;
                        """;
        try(Connection connection = DBConnection.INSTANCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, senderId);
            statement.setInt(2, receiverId);
            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public Optional<FriendRequestEntity> findFriendRequestBySenderIdAndReceiverId (Integer userId, Integer friendId){
        UserDao userDao = new UserDao();
        String query =  """
                            select * from friend_request where sender_id = ? and receiver_id = ?;
                        """;
        try (Connection connection = DBConnection.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            statement.setInt(2, friendId);
            ResultSet result = statement.executeQuery();
            if(result.next()){
                Integer id = result.getInt("id");
                Integer senderId = result.getInt("sender_id");
                Integer receiverId = result.getInt("receiver_id");
                boolean status = result.getBoolean("status");
                Timestamp sentAt = result.getTimestamp("sent_at");
                return Optional.of(new FriendRequestEntity(id, userDao.findUserById(senderId).get(), userDao.findUserById(receiverId).get(), status, sentAt));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
