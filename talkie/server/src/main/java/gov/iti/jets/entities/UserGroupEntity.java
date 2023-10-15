package gov.iti.jets.entities;


public class UserGroupEntity {

    private String userId;
    private Integer userGroupId;
    private Integer groupChatId;

    public UserGroupEntity(String userId, Integer userGroupId, Integer groupChatId) {
        this.userId = userId;
        this.userGroupId = userGroupId;
        this.groupChatId = groupChatId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(Integer userGroupId) {
        this.userGroupId = userGroupId;
    }

    public Integer getGroupChatId() {
        return groupChatId;
    }

    public void setGroupChatId(Integer groupChatId) {
        this.groupChatId = groupChatId;
    }

       @Override
    public String toString() {
        return "UserGroupEntity [userId=" + userId + ", userGroupId=" + userGroupId + ", groupChatId=" + groupChatId
                + "]";
    }
}