package gov.iti.jets;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import gov.iti.jets.dto.RegularChatDto;

// import org.modelmapper.ModelMapper;

import gov.iti.jets.dto.UserDto;
import gov.iti.jets.entities.RegularChatEntity;
import gov.iti.jets.persistence.daos.ChatDao;
import gov.iti.jets.persistence.daos.GroupCahtDao;
import gov.iti.jets.persistence.daos.RegularChatDao;
import gov.iti.jets.persistence.daos.UserDao;
import gov.iti.jets.services.ChatServices;
// import gov.iti.jets.persistence.daos.UserDao;
import gov.iti.jets.services.UserServices;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        ModelMapper modelMapper = new ModelMapper();
        int userId; 
        UserDao userDao = new UserDao();
        // ModelMapper modelMapper = new ModelMapper();
        UserDto userDto = new UserDto();
        UserServices userServices = new UserServices();
        GroupCahtDao groupCahtDao = new GroupCahtDao();
        RegularChatDao regularChatDao = new RegularChatDao();
        ChatDao chatDao = new ChatDao();
        ChatServices chatServices = new ChatServices();
        System.out.println("Hello JAvA!");
        // System.out.println(userServices.findUserById(50));
        System.out.println(chatServices.findChatById("1408b4e6-0c49-4381-82c4-8072840d22dc"));
        System.out.println(chatServices.findAllRegChatsByUserID(51));    
            // System.out.println(regularChatDao.findAllRegChatsByUserID(51));


        // System.out.println(chatServices.findRegularChatByParticipantsIds(51,50));
        // System.out.println(userDto);
        // System.out.println(userDao.findUserById(50));


        //  public List<RegularChatDto> findAllRegChatsByUserID(int userId) {

       
        // System.out.println(regularChatDtos);
        // return regularChatDtos;
    // }
    }
}
