package gov.iti.jets.network.controllers;

import java.rmi.Remote;
import java.rmi.RemoteException;

import gov.iti.jets.api.requests.ChangeUserStatusRequest;
import gov.iti.jets.api.responses.ChangeUserStatusResponse;
import gov.iti.jets.dtos.User;


public interface OnlineStatusController extends Remote {

    void connect(User user, CallbackController callbackController) throws RemoteException;

    void disconnect(String phoneNumber) throws RemoteException;

    void ping() throws RemoteException;

    ChangeUserStatusResponse changeStatus(ChangeUserStatusRequest request) throws RemoteException;
}
