package gov.iti.jets.dtos;


import java.io.File;

public class CurrentUser {
    private static CurrentUser instance = new CurrentUser();
    private User user = new User();

    public static CurrentUser getInstance() {
        return instance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
