package gov.iti.jets.mappers;

import gov.iti.jets.dto.ChatDto;
import gov.iti.jets.dto.RegularChatDto;
import gov.iti.jets.dto.UserDto;
import gov.iti.jets.entities.RegularChatEntity;
import gov.iti.jets.entities.UserEntity;
import gov.iti.jets.mappers.interfaces.ChatMapperInt;
import gov.iti.jets.persistence.daos.ChatDao;
import gov.iti.jets.persistence.daos.RegularChatDao;
import gov.iti.jets.persistence.daos.UserDao;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
public class RegularChatMapper implements ChatMapperInt<RegularChatDto> {

    private RegularChatDao regularChatDao = new RegularChatDao();
    private UserDao userDao = new UserDao();
    private ChatDao chatDao = new ChatDao();
    private ModelMapper modelMapper = new ModelMapper();


    public  RegularChatMapper (ModelMapper modelMapper, RegularChatDao regularChatDao, ChatDao chatDao, UserDao userDao){
        this.modelMapper = modelMapper;
        this.chatDao = chatDao;
        this.regularChatDao = regularChatDao;
        this.userDao = userDao;
    }
    public RegularChatMapper(){}

    public long checkThe2ParticipantsExist(String firstPhoneNumber, String secondPhoneNumber) {
        UserEntity firstParticipant = userDao.findUserByPhoneNumber(firstPhoneNumber).orElseThrow(() -> new RuntimeException("First participant not found!"));
        UserEntity secondParticipant = userDao.findUserByPhoneNumber(secondPhoneNumber).orElseThrow(() -> new RuntimeException("Second participant not found!"));
        List<RegularChatEntity> regularChatEntities = regularChatDao.findAllRegChatsByUserID(firstParticipant.getId());
        return regularChatEntities.stream().filter(entity ->
                        (firstParticipant.getId() == entity.getFirstParticipantId().getId() && secondParticipant.getId() == entity.getSecondParticipantId().getId()) ||
                                (firstParticipant.getId() == entity.getSecondParticipantId().getId() && secondParticipant.getId() == entity.getFirstParticipantId().getId()))
                .count();
    }


    public RegularChatDto saveRegularChat(String firstParticipantPhoneNum, String secondParticipantPhoneNum) {

        if (checkThe2ParticipantsExist(firstParticipantPhoneNum, secondParticipantPhoneNum) == 0) {
            UserEntity firstParticipant = userDao.findUserByPhoneNumber(firstParticipantPhoneNum).orElseThrow(
                    () -> new RuntimeException("First participant not found!"));
            UserEntity secondParticipant = userDao.findUserByPhoneNumber(secondParticipantPhoneNum).orElseThrow(
                    () -> new RuntimeException("Second participant not found!"));
            RegularChatEntity regularChatEntity = regularChatDao.save(new RegularChatEntity(firstParticipant, secondParticipant));
            RegularChatDto regularChatDto = modelMapper.map(regularChatEntity,RegularChatDto.class);
            System.out.println(regularChatDto);
            return regularChatDto;
        } else {
            throw new RuntimeException("Chat already exists between the participants!");
        }
    }

    @Override
    public RegularChatDto findChatById(String uuid) {
        return modelMapper.map(chatDao.findChatById(uuid), RegularChatDto.class);
    }


    public RegularChatDto findRegularChatByParticipantsIds(UserDto firstParticipantId,
                                                           UserDto secondParticipantId) {
        UserMapper userMapper = new UserMapper();
        UserEntity fEntity = userMapper.userDtoToUserEntity(firstParticipantId);
        UserEntity sEntity = userMapper.userDtoToUserEntity(secondParticipantId);
        return modelMapper.map(regularChatDao.findRegularChatByParticipantsIds(fEntity, sEntity),
                RegularChatDto.class);
    }



    public RegularChatDto findRegularChatByParticipantsPhoneNumbers(String phoneNumber1,
                                                                    String phoneNumber2) {
        return modelMapper.map(regularChatDao.findRegularChatByParticipantsPhoneNumbers(phoneNumber1, phoneNumber2),
                RegularChatDto.class);
    }


    public RegularChatDto findRegularChatByParticipantPhoneNumber(String phoneNumber) {
        return modelMapper.map(regularChatDao.findRegularChatByParticipantPhoneNumber(phoneNumber),
                RegularChatDto.class);
    }


    public List<RegularChatDto> findAllRegChatsByUserID (int userId) {
        List<RegularChatEntity> regularChatEntities = regularChatDao.findAllRegChatsByUserID(userId);
        List<RegularChatDto> regularChatDtos = new ArrayList<>();

        for (RegularChatEntity regularChatEntity : regularChatEntities) {
            RegularChatDto regularChatDto = modelMapper.map(regularChatEntity, RegularChatDto.class);
            regularChatDtos.add(regularChatDto);
        }
        return regularChatDtos;
    }


    public int deleteRegularChat(String id) {
        return regularChatDao.delete(id);
    }

    public List<RegularChatDto> getAllRegularChats(String phoneNumber) {
        List<RegularChatEntity> regularChatEntities = regularChatDao.findAllRegularChatByPhoneNum(phoneNumber);
        List<RegularChatDto> regularChatDtos = new ArrayList<>();
        for (RegularChatEntity regularChatEntity : regularChatEntities){
            RegularChatDto regularChatDto = modelMapper.map(regularChatEntity, RegularChatDto.class);
            regularChatDtos.add(regularChatDto);
        }
        return regularChatDtos;
    }
}