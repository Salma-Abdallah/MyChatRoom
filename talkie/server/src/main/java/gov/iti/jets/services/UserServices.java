package gov.iti.jets.services;

import org.modelmapper.ModelMapper;

import gov.iti.jets.dto.UserDto;
import gov.iti.jets.entities.UserEntity;
import gov.iti.jets.persistence.daos.UserDao;

public class UserServices {
    
    private UserDao userDao = new UserDao();
    private ModelMapper modelMapper = new ModelMapper();

    public UserServices(UserDao userDao, ModelMapper modelMapper){
        this.modelMapper = modelMapper;
        this.userDao= userDao;
    }
    public UserServices (){}



    public UserDto saveUser (UserDto userDto){
         UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
           return modelMapper.map(userDao.saveUser(userEntity), UserDto.class);  
    }


    public UserDto findUserById (int userId){
        return modelMapper.map(userDao.findUserById(userId), UserDto.class);
    }

    public UserDto findUserByPhoneNumber (String userPhone){
        return modelMapper.map(userDao.findUserByPhoneNumber(userPhone), UserDto.class);
    } 

    public UserDto findUserByEmail (String userEmail){
        return modelMapper.map(userDao.findUserByEmail(userEmail),UserDto.class);
    }
    public String getOnlineStatusByUserPhoneNumber (String phoneNumber){
        return userDao.getOnlineStatusByPhoneNumber(phoneNumber);
    }



    
}
