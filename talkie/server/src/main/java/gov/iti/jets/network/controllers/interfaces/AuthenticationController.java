
package gov.iti.jets.network.controllers.interfaces;

import gov.iti.jets.api.requests.LoginRequest;
import gov.iti.jets.api.requests.RegisterRequest;
import gov.iti.jets.api.responses.LoginResponse;
import gov.iti.jets.api.responses.RegisterResponse;
import gov.iti.jets.dto.UserDto;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AuthenticationController extends Remote {

    LoginResponse login(LoginRequest loginRequest) throws RemoteException;
    RegisterResponse register(RegisterRequest registerRequest) throws RemoteException;
    void logOut(UserDto user) throws  RemoteException;
}
