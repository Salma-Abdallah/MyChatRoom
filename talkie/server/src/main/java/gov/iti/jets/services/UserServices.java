package gov.iti.jets.services;

import java.util.Optional;

import org.modelmapper.ModelMapper;

import gov.iti.jets.dto.UserDto;
import gov.iti.jets.entities.UserEntity;
import gov.iti.jets.mappers.UserMapper;
import gov.iti.jets.persistence.daos.UserDao;

public class UserServices {
    
    private UserDao userDao = new UserDao();
    private ModelMapper modelMapper = new ModelMapper();
    private UserMapper userMapper = new UserMapper();


    public UserServices(UserDao userDao, ModelMapper modelMapper, UserMapper userMapper){
        this.modelMapper = modelMapper;
        this.userDao = userDao;
        this.userMapper = userMapper;
    }
    public UserServices (){}



    public UserDto saveUser (UserDto userDto){
        return userMapper.insertUser(userDto);
    }

    public Optional<UserDto> findUserById (int userId){
        return userMapper.findUserById(userId);
    }

    public Optional<UserDto> findUserByPhoneNumber (String userPhone){
        return userMapper.findUserByPhoneNumber(userPhone);
    } 

    public Optional<UserDto> findUserByEmail (String userEmail){
        return userMapper.findUserByEmail(userEmail);
    }
    public String getOnlineStatusByUserPhoneNumber (String phoneNumber){
        return userDao.getOnlineStatusByPhoneNumber(phoneNumber);
    }  
}
