package gov.iti.jets.api.requests;

import java.io.Serializable;

public class GetChatsRequest implements Serializable{


    private static final long serialVersionUID = -4561991490072553009L;

    private String phoneNumber;

    public GetChatsRequest(){}

    public GetChatsRequest(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "GetChatsRequest{" +
                "phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
