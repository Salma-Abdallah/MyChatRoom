package gov.iti.jets.api.requests;
import java.io.Serializable;

public class GetFriendReqRequest implements Serializable {


    private static final long serialVersionUID = 213930628588567517L;

    private String userPhoneNumber;
    public GetFriendReqRequest (){}

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }



    public GetFriendReqRequest(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }
    public String toString() {
        return "LoadFriendRequest" +
                "userPhoneNumber='" + userPhoneNumber + '\'' +
                '}';
    }

}
