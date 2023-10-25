package gov.iti.jets;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import gov.iti.jets.dto.RegularChatDto;
import gov.iti.jets.network.manager.NetworkManager;
import static gov.iti.jets.network.manager.LoadLogin.IntializeApp;




public class App {

    public static void main(String[] args) throws RemoteException {
   
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
    
