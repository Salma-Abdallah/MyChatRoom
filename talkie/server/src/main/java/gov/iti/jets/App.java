package gov.iti.jets;

import java.rmi.RemoteException;
import java.time.LocalDate;

import gov.iti.jets.api.requests.LoginRequest;
import gov.iti.jets.api.requests.RegisterRequest;
import gov.iti.jets.dto.RegularChatDto;
import gov.iti.jets.dto.UserDto;
import gov.iti.jets.entities.RegularChatEntity;
import gov.iti.jets.entities.UserEntity;
//import gov.iti.jets.mappers.ChatMapper;
import gov.iti.jets.mappers.RegularChatMapper;
import gov.iti.jets.network.controllers.implementaions.AuthenticationControllerImpl;
import gov.iti.jets.network.manager.NetworkManager;
import gov.iti.jets.persistence.daos.ChatDao;
import gov.iti.jets.persistence.daos.GroupCahtDao;
import gov.iti.jets.persistence.daos.RegularChatDao;
import gov.iti.jets.persistence.daos.UserDao;
import gov.iti.jets.services.ChatServices;
import gov.iti.jets.services.UserServices;
import org.modelmapper.ModelMapper;

import static gov.iti.jets.network.manager.LoadLogin.IntializeApp;




public class App {

    public static void main(String[] args) throws RemoteException {
//        AuthenticationControllerImpl authenticationControllerImpl = (AuthenticationControllerImpl) AuthenticationControllerImpl.getInstance();

//        UserEntity userDto = new UserEntity();
//        userDto.setPhoneNumber("01159634872");
//        userDto.setUserName("saraa1");
//        userDto.setEmail("Saaaa@dd.com");
//        userDto.setPassword("12345678");
//        userDto.setGender("f");
//        userDto.setBirthDate(LocalDate.of(2023, 10, 17));
//        userDto.setCountry("Egypt");

//        authenticationControllerImpl.register(new RegisterRequest("Saraaa*","01012569874","Saaaa@dd.com","12345678","f","egypt",LocalDate.of(2023, 10, 17)));

//        UserDto userDto2 = new UserDto();
//        authenticationControllerImpl.register(new RegisterRequest("Saraaaa*","01159634872","Saaaa@dd.com","12345678","f","egypt",LocalDate.of(2023, 10, 17)));

//         authenticationControllerImpl.logOut(userDto);
        // LoginRequest loginRequest = new LoginRequest("01012345678", "123456789");
        // authenticationControllerImpl.login(loginRequest);


//


//        UserDao userServices = new UserDao();
//        userServices.saveUser(userDto);
//        ChatServices chatServices = new ChatServices();
        ChatServices chatServices = new ChatServices();
    RegularChatMapper regularChatMapper = new RegularChatMapper();
//        chatMapper.saveRegularChat("01278158298", "01025896385");
        RegularChatDto RegularChatDto = chatServices.findChatById("5bd9c414-268e-4842-8593-17c9eec036af", regularChatMapper);
        System.out.println(RegularChatDto);
//
//        System.out.println(chatMapper.saveRegularChat("01278158298","01011707547"));
       NetworkManager.start();
        new Thread(()-> IntializeApp()).start();
    }

    
        
}
     // ModelMapper modelMapper = new ModelMapper();
        // int userId; 
        // UserDao userDao = new UserDao();
        // UserDto userDto = new UserDto();
        // UserServices userServices = new UserServices();
        // GroupCahtDao groupCahtDao = new GroupCahtDao();
        // RegularChatDao regularChatDao = new RegularChatDao();
        // ChatDao chatDao = new ChatDao();
        // ChatServices chatServices = new ChatServices();
        // System.out.println("Hello JAvA!");
        // // System.out.println(userServices.findUserById(50));
        // System.out.println(chatServices.findChatById("1408b4e6-0c49-4381-82c4-8072840d22dc"));
        // System.out.println(chatServices.findAllRegChatsByUserID(51));    



//     ModelMapper modelMapper = new ModelMapper();
//         int userId;
//         UserDao userDao = new UserDao();
//         UserDto userDto = new UserDto();
//         UserServices userServices = new UserServices();
//         GroupCahtDao groupCahtDao = new GroupCahtDao();
//         RegularChatDao regularChatDao = new RegularChatDao();
//         ChatDao chatDao = new ChatDao();
//         ChatServices chatServices = new ChatServices();
//         System.out.println("Hello JAvA!");
//         // System.out.println(userServices.findUserById(50));
//         System.out.println(chatServices.findChatById("1408b4e6-0c49-4381-82c4-8072840d22dc"));
//         System.out.println(chatServices.findAllRegChatsByUserID(51));
//         System.out.println("ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
//
