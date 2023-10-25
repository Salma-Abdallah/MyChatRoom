package gov.iti.jets.network.controllers.implementaions;

import gov.iti.jets.api.requests.LoginRequest;
import gov.iti.jets.api.requests.RegisterRequest;
import gov.iti.jets.api.responses.LoginResponse;
import gov.iti.jets.api.responses.RegisterResponse;
import gov.iti.jets.api.validation.RegisterValidation;
import gov.iti.jets.dto.UserDto;
import gov.iti.jets.network.controllers.interfaces.AuthenticationController;
import gov.iti.jets.network.manager.NetworkManager;
import gov.iti.jets.services.EncryptionServices;
import gov.iti.jets.services.HashServices;
import gov.iti.jets.services.UserServices;
import gov.iti.jets.services.ValidationServices;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Optional;

public class AuthenticationControllerImpl extends UnicastRemoteObject implements AuthenticationController {

    private static AuthenticationController instance;

    private AuthenticationControllerImpl() throws RemoteException {
    }

    public static AuthenticationController getInstance() {
        try {
            if (instance == null) {
                instance = new AuthenticationControllerImpl();
            }
            NetworkManager.getRegistry().rebind("AuthenticationController", instance);

        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        return instance;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) throws RemoteException {
        UserServices userService = new UserServices();
        HashServices hashServices = new HashServices();
        EncryptionServices encryptionServices = new EncryptionServices();

        String decryptedPassword = encryptionServices.decrypt(loginRequest.getPassword());
        Optional<UserDto> userOptional = userService.findUserByPhoneNumber(loginRequest.getPhoneNumber());
        if (userOptional.isPresent()) {
            UserDto user = userOptional.get();
            String hashedPassword = hashServices.getHashedValue(decryptedPassword, user.getSalt());
            if (hashedPassword.equals(user.getPassword())) {
                user.setPassword(encryptionServices.encrypt(decryptedPassword));
                LoginResponse response = new LoginResponse(user.getUserName(), user.getPhoneNumber(), user.getEmail(),
                        loginRequest.getPassword(),
                        user.getGender(), user.getCountry(), user.getBirthDate(), user.getOnlineStatus(), user.getBio(),
                        user.getPicture(), user.getPictureExtension());
                return response;
            }
        }
        return null;
    }

    @Override
    public RegisterResponse register(RegisterRequest registerRequest) throws RemoteException {
        UserServices userServices = new UserServices();
        ValidationServices validationService = new ValidationServices();
        HashServices hashService = new HashServices();
        EncryptionServices encryptionService = new EncryptionServices();

        RegisterValidation validation = new RegisterValidation(registerRequest);
        boolean isValidRequest = validationService.validate(validation);
        RegisterResponse response = new RegisterResponse();

        if (isValidRequest) {
            byte[] saltBytes = new byte[24];
            new SecureRandom().nextBytes(saltBytes);
            String salt = Base64.getEncoder().encodeToString(saltBytes);
            String decryptedPassword = encryptionService.decrypt(registerRequest.getPassword());
            String hashedPassword = hashService.getHashedValue(decryptedPassword, salt);
            UserDto user = new UserDto(registerRequest.getUserName(), registerRequest.getPhoneNumber(),
                    registerRequest.getEmail(),
                    hashedPassword, salt, registerRequest.getGender(), registerRequest.getCountry(),
                    registerRequest.getBirthDate());
            userServices.saveUser(user);
            user = userServices.findUserByPhoneNumber(user.getPhoneNumber()).get();

            response = new RegisterResponse(user.getUserName(), user.getPhoneNumber(), user.getEmail(),
                    registerRequest.getPassword(), user.getGender(), user.getCountry(), user.getBirthDate(),
                    user.getOnlineStatus(), user.getBio(), user.getPicture(), user.getPictureExtension(), null);
        } else {
            response.setValidation(validation);
        }
        return response;
    }

    @Override
    public void logOut(UserDto user) throws RemoteException {

    }

}
