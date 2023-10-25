package gov.iti.jets.network.controllers.interfaces;

import gov.iti.jets.dto.*;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CallbackController extends Remote {

    void respond() throws RemoteException;

    void createNewFriendRequest(FriendRequestDto friendRequest) throws RemoteException;

    void createNewRegularChat(RegularChatDto chat) throws RemoteException;

    void friendOnlineStatus(String chatId, String status) throws RemoteException;

    void deleteRecievedFriendRequest(String senderPhoneNumber) throws RemoteException;

    void addCurrentUserToGroupChat(GroupChatDto chat) throws RemoteException;

}