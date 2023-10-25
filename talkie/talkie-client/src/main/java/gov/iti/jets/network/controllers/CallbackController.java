package gov.iti.jets.network.controllers;

import java.rmi.Remote;
import java.rmi.RemoteException;


import gov.iti.jets.dtos.FriendRequest;
import gov.iti.jets.dtos.GroupChat;
import gov.iti.jets.dtos.Message;
import gov.iti.jets.dtos.RegularChat;
import gov.iti.jets.network.controllers.CallbackController;

public interface CallbackController extends Remote {

    void respond() throws RemoteException;

    void createNewRegularChat(RegularChat chat) throws RemoteException;

    void createNewFriendRequest(FriendRequest friendRequest) throws RemoteException;

    void deleteRecievedFriendRequest(String senderPhoneNumber) throws RemoteException;
}
