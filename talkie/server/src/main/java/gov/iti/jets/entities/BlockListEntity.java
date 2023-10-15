package gov.iti.jets.entities;



public class BlockListEntity {
     private Integer id;
    private UserEntity user;
    private UserEntity blockedUser;

    public BlockListEntity() {
    }

    public BlockListEntity(Integer id, UserEntity user, UserEntity blockedUser) {
        this.id = id;
        this.user = user;
        this.blockedUser = blockedUser;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public UserEntity getBlockedUser() {
        return blockedUser;
    }

    public void setBlockedUser(UserEntity blockedUser) {
        this.blockedUser = blockedUser;
    }

    @Override
    public String toString() {
        return "BlockListEntity{" +
                "id=" + id +
                ", userEntity=" + user.toString() +
                ", blockedUserEntity=" + blockedUser.toString() +
                '}';
    }

}
