package gov.iti.jets.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import gov.iti.jets.dto.UserDto;
import gov.iti.jets.entities.UserEntity;
import gov.iti.jets.persistence.daos.UserDao;
import gov.iti.jets.utilities.ImageUtilities;

public class UserMapper {

    private UserDao userDao;

    public UserMapper() {
        userDao = new UserDao();
    }

    public UserDto insertUser(UserDto userDto) {
        UserEntity userEntity = userDtoToUserEntity(userDto);
        return userEntityToDto(userDao.saveUser(userEntity));
    }

    public Optional<UserDto> findUserById(int userId) {
        Optional<UserEntity> entity = userDao.findUserById(userId);
        if (entity.isPresent()) {
            return Optional.of(userEntityToDto(entity.get()));
        }
        return Optional.empty();
    }

    public Optional<UserDto> findUserByPhoneNumber(String phoneNumber) {
        Optional<UserEntity> entity = userDao.findUserByPhoneNumber(phoneNumber);
        if (entity.isPresent()) {
            return Optional.of(userEntityToDto(entity.get()));
        }
        return Optional.empty();
    }

    public List<UserDto> findUserByOnlineStatus(String onlineStatus) {
        List<UserEntity> entities = userDao.findUserByOnlineStatus(onlineStatus);
        List<UserDto> userDtos = new ArrayList<>();

        for (UserEntity entity : entities) {
            userDtos.add(userEntityToDto(entity));
        }

        return userDtos;
    }

    public Optional<UserDto> findUserByEmail(String email) {
        Optional<UserEntity> entity = userDao.findUserByEmail(email);
        if (entity.isPresent()) {
            return Optional.of(userEntityToDto(entity.get()));
        }
        return Optional.empty();
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

    public int update(UserDto userDto) {
        Optional<UserEntity> userEntityOptional = userDao.findUserById(userDto.getId());
        UserEntity userEntity = userEntityOptional
                .orElseThrow(() -> new RuntimeException("User not found!"));
        return userDao.update( userDto.getId(), userEntity);
    }


    public UserDto userEntityToDto(UserEntity userEntity) {
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
}
