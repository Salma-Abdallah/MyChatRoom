package gov.iti.jets.network.controllers.implementaions;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.SecureRandom;
import java.util.Base64;

import gov.iti.jets.api.requests.UserProfileRequest;
import gov.iti.jets.api.responses.UserProfileResponse;
import gov.iti.jets.api.validation.RegisterValidation;
import gov.iti.jets.dto.UserDto;
import gov.iti.jets.network.controllers.interfaces.UserProfileController;
import gov.iti.jets.network.manager.NetworkManager;
import gov.iti.jets.services.EncryptionServices;
import gov.iti.jets.services.HashServices;
import gov.iti.jets.services.UserServices;
import gov.iti.jets.services.ValidationServices;

public class UseProfileControllerImpl extends UnicastRemoteObject implements UserProfileController{

 private static UseProfileControllerImpl instance;
    private UseProfileControllerImpl() throws RemoteException {
        // super();
    }

    public static UseProfileControllerImpl getInstance(){
        try {
            if(instance == null){
                instance = new UseProfileControllerImpl();
            }
            NetworkManager.getRegistry().rebind("UserProfileController", instance);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        return instance;
    }
    

    @Override
    public UserProfileResponse update(UserProfileRequest request) throws RemoteException {
       
            UserServices userProfileServices= new UserServices();
            ValidationServices validationService = new ValidationServices();
            HashServices hashService= new HashServices();
            EncryptionServices encryptionService = new EncryptionServices();
    
            RegisterValidation userValidation = new RegisterValidation(request);
            boolean isValidRequest = validationService.userProfileValidate(userValidation);
            UserProfileResponse response = new UserProfileResponse();
    
            if(isValidRequest) {
                byte[] saltBytes =new byte[24];
                new SecureRandom().nextBytes(saltBytes);
                String salt = Base64.getEncoder().encodeToString(saltBytes);
                String decryptedPassword = encryptionService.decrypt(request.getPassword());
                String hashedPassword = hashService.getHashedValue(decryptedPassword, salt);
    
                UserDto user = new UserDto(request.getUserName(), request.getNewPhoneNumber(), request.getEmail(),
                        hashedPassword, salt, request.getGender(), request.getCountry(),
                        request.getBirthdate());
    
                userProfileServices.update(user);
    
                response = new UserProfileResponse(user.getUserName() , user.getPhoneNumber(), user.getPassword(),
                        user.getEmail() , user.getCountry(), user.getGender(), user.getBirthDate() ,user.getBio(),user.getPicture());
    
            }else {
                response.setValidation(userValidation);
            }
    
            return response;
    }
}
