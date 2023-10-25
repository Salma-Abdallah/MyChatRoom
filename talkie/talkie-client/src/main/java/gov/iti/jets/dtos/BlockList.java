package gov.iti.jets.dtos;

public class BlockList {
    private int userId;
    private int blockedUserId;

    public BlockList(int userId, int blockedUserId) {
        this.userId = userId;
        this.blockedUserId = blockedUserId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBlockedUserId() {
        return blockedUserId;
    }

    public void setBlockedUserId(int blockedUserId) {
        this.blockedUserId = blockedUserId;
    }
}
