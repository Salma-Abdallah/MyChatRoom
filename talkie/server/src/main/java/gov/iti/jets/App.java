package gov.iti.jets;

// import org.modelmapper.ModelMapper;

import gov.iti.jets.dto.UserDto;
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
        UserDao userDao = new UserDao();
        // ModelMapper modelMapper = new ModelMapper();
        UserDto userDto = new UserDto();
        UserServices userServices = new UserServices();
        ChatServices chatServices = new ChatServices();
        System.out.println("Hello JAvA!");
        // System.out.println(userServices.findUserById(50));
        System.out.println(chatServices.findRegularChatByParticipantPhoneNumber("01011707547"));

        // System.out.println(userDto);
        // System.out.println(userDao.findUserById(50));

    }
}
