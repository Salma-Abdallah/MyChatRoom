package gov.iti.jets.api.requests;

import java.io.Serializable;

public class ChatRequest implements Serializable{
    private static final long serialVersionUID = -4561991490072553009L;
    private String phoneNumber;

    public ChatRequest(){}

    public ChatRequest(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    @Override
    public String toString() {
        return "GetChatsRequest{" +
                "phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
