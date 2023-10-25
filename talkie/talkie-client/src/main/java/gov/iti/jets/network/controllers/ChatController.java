package gov.iti.jets.network.controllers;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import gov.iti.jets.api.requests.AddUserToGroupChatRequest;
import gov.iti.jets.api.requests.CreateGroupChatRequest;
import gov.iti.jets.api.requests.GetChatsRequest;
import gov.iti.jets.api.responses.AddUserToGroupChatResponse;
import gov.iti.jets.api.responses.CreateGroupChatResponse;
import gov.iti.jets.api.responses.GetChatsResponse;

public interface ChatController extends Remote {
    GetChatsResponse getAllChat(GetChatsRequest request) throws RemoteException;
    CreateGroupChatResponse createGroupChat(CreateGroupChatRequest request) throws RemoteException;
    public AddUserToGroupChatResponse addUserToGroupChat(AddUserToGroupChatRequest request) throws RemoteException;
}
