package gov.iti.jets.mappers;

import java.util.ArrayList;
import java.util.List;


import gov.iti.jets.dto.UserDto;
import gov.iti.jets.entities.UserEntity;
import gov.iti.jets.persistence.daos.UserDao;
import gov.iti.jets.utilities.ImageUtilities;

public class UserMapper {

    private UserDao userDao = new UserDao();
    public UserMapper(UserDao userDao) {
        this.userDao = userDao;
    }
    public UserMapper(){}

    public UserDto insertUser(UserDto userDto) {
        UserEntity userEntity = userDtoToUserEntity(userDto);
        return userEntityToDto(userDao.saveUser(userEntity));
    }
    private UserEntity findUserByPhoneNumberOrThrow(String phoneNumber, String errorMessage) {
        return userDao.findUserByPhoneNumber(phoneNumber).orElseThrow(() -> new RuntimeException(errorMessage));
    }

    public UserDto findUserById(int userId) {
        UserEntity userEntity = (userDao.findUserById(userId)
                .orElseThrow(() -> new RuntimeException("User with this id not found!")));
        return userEntityToDto(userEntity);
    }

    public UserDto findUserByPhoneNumber(String phoneNumber) {
        return userEntityToDto(
                findUserByPhoneNumberOrThrow(phoneNumber, "User with phone id not found!"));
    }

    public List<UserDto> findUserByOnlineStatus(String onlineStatus) {
        List<UserEntity> entities = userDao.findUserByOnlineStatus(onlineStatus);
        List<UserDto> userDtos = new ArrayList<>();
        for (UserEntity entity : entities) {
            userDtos.add(userEntityToDto(entity));
        }
        return userDtos;
    }

    public UserDto findUserByEmail(String email) {
        UserEntity entity = (userDao.findUserByEmail(email)).orElseThrow(
                ()-> new RuntimeException("User With this email not Found"));
        return userEntityToDto(entity);
    }

    public int update(UserDto userDto) {
        UserEntity userEntity = (userDao.findUserById(userDto.getId())
                .orElseThrow(() -> new RuntimeException("User not found!")));
        return userDao.update( userDto.getId(), userEntity);
    }

    public String getOnlineStatusByUserPhoneNumber (String phoneNumber){
        return userDao.getOnlineStatusByPhoneNumber(phoneNumber);
    }

    public UserDto userEntityToDto( UserEntity userEntity) {
        UserDto userDto = new UserDto();

        userDto.setUserName(userEntity.getUserName());
        userDto.setPhoneNumber(userEntity.getPhoneNumber());
        userDto.setPassword(userEntity.getPassword());
        userDto.setSalt(userEntity.getSalt());
        userDto.setEmail(userEntity.getEmail());
        userDto.setPictureExtension(userEntity.getPictureUrl().split("\\.")[1]);
        userDto.setPicture(ImageUtilities.loadImage(userEntity.getPictureUrl()));
        userDto.setGender(userEntity.getGender());
        userDto.setCountry(userEntity.getCountry());
        userDto.setBirthDate(userEntity.getBirthDate());
        userDto.setOnlineStatus(userEntity.getOnlineStatus());
        userDto.setBio(userEntity.getBio());

        return userDto;

    }

    public UserEntity userDtoToUserEntity(UserDto userDto) {
        UserEntity userEntity = new UserEntity();

        if (userDto.getPicture() == null) {
            userEntity.setPictureUrl("userPic/default.png");
        } else {
            userEntity.setPictureUrl(ImageUtilities.storeImage(userDto));
        }
        userEntity.setUserName(userDto.getUserName());
        userEntity.setBio(userDto.getBio());
        userEntity.setCountry(userDto.getCountry());
        userEntity.setBirthDate(userDto.getBirthDate());
        userEntity.setPassword(userDto.getPassword());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setGender(userDto.getGender());
        userEntity.setOnlineStatus(userDto.getOnlineStatus());
        userEntity.setSalt(userDto.getSalt());

        return userEntity;
    }
}
