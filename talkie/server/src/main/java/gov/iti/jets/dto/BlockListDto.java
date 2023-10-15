package gov.iti.jets.dto;

import java.io.Serializable;

public class BlockListDto implements Serializable{
    private UserDto user;
    private UserDto blockedUser;

    public BlockListDto() {
    }

    public BlockListDto(UserDto user, UserDto blockedUser) {
        this.user = user;
        this.blockedUser = blockedUser;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public UserDto getBlockedUser() {
        return blockedUser;
    }

    public void setBlockedUser(UserDto blockedUser) {
        this.blockedUser = blockedUser;
    }
}
