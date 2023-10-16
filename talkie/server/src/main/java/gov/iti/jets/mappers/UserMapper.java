package gov.iti.jets.mappers;

import org.modelmapper.ModelMapper;

import gov.iti.jets.dto.UserDto;
import gov.iti.jets.entities.UserEntity;
import gov.iti.jets.persistence.daos.UserDao;
import gov.iti.jets.utilities.ImageUtils;

public class UserMapper {

    private UserDao userDao;

    public UserMapper() {
        userDao = new UserDao();
    }

    // private ModelMapper modelMapper = new ModelMapper();
    // public UserMapper(ModelMapper modelMapper, UserDao userDao) {
    //     this.modelMapper = modelMapper;
    //     this.userDao= userDao;
    // }

    // public UserDto userEntityToDtoTst (UserDto userDto){
    //         UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
    //         return modelMapper.map(userDao.saveUser(userEntity), UserDto.class);
    //     }
    

    // public 


    public UserDto userEntityToDto (UserEntity userEntity){
        UserDto userDto = new UserDto();

        userDto.setUserName(userEntity.getUserName());
        userDto.setPhoneNumber(userEntity.getPhoneNumber());
        userDto.setPassword(userEntity.getPassword());
        userDto.setSalt(userEntity.getSalt());
        userDto.setEmail(userEntity.getEmail());
        userDto.setPictureExtension(userEntity.getPictureUrl().split("\\.")[1]);
        userDto.setPicture(ImageUtils.loadImage(userEntity.getPictureUrl()));
        userDto.setGender(userEntity.getGender());
        userDto.setCountry(userEntity.getCountry());
        userDto.setBirthDate(userEntity.getBirthDate());
        userDto.setOnlineStatus(userEntity.getOnlineStatus());
        userDto.setBio(userEntity.getBio());

        return userDto;

    }
}
