package gov.iti.jets.network.controllers.interfaces;



import gov.iti.jets.api.requests.ChangeUserStatusRequest;
import gov.iti.jets.api.responses.ChangeUserStatusResponse;
import gov.iti.jets.dto.UserDto;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface OnlineStatusController extends Remote {

    void connect(UserDto user, CallbackController callbackController) throws RemoteException;

    void disconnect(String phoneNumber) throws RemoteException;

    void ping() throws RemoteException;

    ChangeUserStatusResponse changeStatus(ChangeUserStatusRequest request) throws RemoteException;
}
