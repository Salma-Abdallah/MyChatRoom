package gov.iti.jets.network.controllers.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import gov.iti.jets.api.requests.UserProfileRequest;
import gov.iti.jets.api.responses.UserProfileResponse;

public interface UserProfileController extends Remote{

    UserProfileResponse update(UserProfileRequest request)throws RemoteException;

}