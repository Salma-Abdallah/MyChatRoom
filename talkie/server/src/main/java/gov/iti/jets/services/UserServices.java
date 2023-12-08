package gov.iti.jets.services;

import java.util.Optional;

import org.modelmapper.ModelMapper;

import gov.iti.jets.dto.UserDto;
import gov.iti.jets.entities.UserEntity;
import gov.iti.jets.mappers.UserMapper;
import gov.iti.jets.persistence.daos.UserDao;

public class UserServices {
    
    private UserDao userDao = new UserDao();
    private UserMapper userMapper = new UserMapper();


    public UserServices(UserDao userDao, UserMapper userMapper){
        this.userDao = userDao;
        this.userMapper = userMapper;
    }
    public UserServices (){}



    public UserDto saveUser (UserDto userDto){
        return userMapper.insertUser(userDto);
    }

    public UserDto findUserById (int userId){
        return userMapper.findUserById(userId);
    }

    public UserDto findUserByPhoneNumber (String userPhone){
        return userMapper.findUserByPhoneNumber(userPhone);
    } 

    public UserDto findUserByEmail (String userEmail){
        return userMapper.findUserByEmail(userEmail);
    }
    public String getOnlineStatusByUserPhoneNumber (String phoneNumber){
        return userMapper.getOnlineStatusByUserPhoneNumber(phoneNumber);
    }  
    public int update(UserDto userDto) {
        return userMapper.update(userDto);
    }

    public int updateStatusByUserPhoneNumber(String phoneNumber, String onlineStatus){
        return userDao.updateStatusByUserPhoneNumber(phoneNumber, onlineStatus);
    }
}
