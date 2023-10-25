
package gov.iti.jets.network.controllers.interfaces;

import gov.iti.jets.api.requests.AddUserToGCRequest;
import gov.iti.jets.api.requests.GroupChatRequest;
import gov.iti.jets.api.requests.ChatRequest;
import gov.iti.jets.api.responses.AddUserToGCResponse;
import gov.iti.jets.api.responses.GroupChatResponse;
import gov.iti.jets.api.responses.ChatResponse;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatController extends Remote {
    ChatResponse getAllChat(ChatRequest request) throws RemoteException;

    GroupChatResponse createGroupChat(GroupChatRequest request) throws RemoteException;

    public AddUserToGCResponse addUserToGroupChat(AddUserToGCRequest request) throws RemoteException;
}
