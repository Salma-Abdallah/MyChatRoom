package gov.iti.jets.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;

import gov.iti.jets.dto.ChatDto;
import gov.iti.jets.dto.GroupChatDto;
import gov.iti.jets.dto.RegularChatDto;
import gov.iti.jets.dto.UserDto;
import gov.iti.jets.entities.GroupChatEntity;
import gov.iti.jets.entities.RegularChatEntity;
import gov.iti.jets.entities.UserEntity;
import gov.iti.jets.mappers.UserMapper;
import gov.iti.jets.persistence.daos.ChatDao;
import gov.iti.jets.persistence.daos.GroupCahtDao;
import gov.iti.jets.persistence.daos.RegularChatDao;
import gov.iti.jets.persistence.daos.UserDao;

public class ChatServices {

    private ModelMapper modelMapper = new ModelMapper();
    private ChatDao chatDao = new ChatDao();
    private RegularChatDao regularChatDao = new RegularChatDao();
    private GroupCahtDao groupChatDao = new GroupCahtDao();
    private UserMapper userMapper = new UserMapper();
    private UserDao userDao = new UserDao();


    public ChatServices(ModelMapper modelMapper, ChatDao chatDao, RegularChatDao regularChatDao,
            GroupCahtDao groupChatDao) {
        this.chatDao = chatDao;
        this.groupChatDao = groupChatDao;
        this.modelMapper = modelMapper;
        this.regularChatDao = regularChatDao;
    }
    public ChatServices(){}

    public Optional<RegularChatDto> saveRegularChat(String firstPhoneNumber, String secondPhoneNumber) {
        Optional<UserEntity> firstParticipantOptional = userDao.findUserByPhoneNumber(firstPhoneNumber);
        Optional<UserEntity> secondParticipantOptional = userDao.findUserByPhoneNumber(secondPhoneNumber);
        if (firstParticipantOptional.isPresent() && secondParticipantOptional.isPresent()) {
            UserEntity firstParticipant = firstParticipantOptional.get();
            UserEntity secondParticipant = secondParticipantOptional.get();
            List<RegularChatEntity> regularChatEntities = regularChatDao.findAllRegChatsByUserID(firstParticipant.getId());

            long count = regularChatEntities.stream().filter((entity) ->
                    secondParticipant.getId() == entity.getFirstParticipantId().getId() || secondParticipant.getId() == entity.getSecondParticipantId().getId()
            ).count();

            if (count == 0) {
                RegularChatEntity regularChatEntity = regularChatDao.save(new RegularChatEntity(firstParticipant, secondParticipant));
                return Optional.of(new RegularChatDto(regularChatEntity.getId(),
                        userMapper.userEntityToDto(firstParticipant),
                        userMapper.userEntityToDto(secondParticipant)));
            } else return Optional.empty();
        }
        return Optional.empty();
    }

    public ChatDto findChatById(String uuid) {
        return modelMapper.map(chatDao.findChatById(uuid), ChatDto.class);
    }

    public int deleteChat(String id) {
        return chatDao.delete(id);
    }

    public RegularChatDto saveRegularChat(RegularChatDto regularChatDto) {
        RegularChatEntity regularChatEntity = modelMapper.map(regularChatDto, RegularChatEntity.class);
        return modelMapper.map(regularChatDao.save(regularChatEntity), RegularChatDto.class);
    }

    public RegularChatDto findRegularChatByParticipantsIds(UserDto firstParticipantId,
            UserDto secondParticipantId) {
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

    public GroupChatDto saveGroupChat (GroupChatDto  groupCahtDto) {
        GroupChatEntity groupChatEntity = modelMapper.map(groupCahtDto , GroupChatEntity.class);
        return modelMapper.map(groupChatDao.save(groupChatEntity), GroupChatDto.class);
    }

    public List<GroupChatDto> findAllGroupChatsByOwnerId(Integer ownerId) {
        List<GroupChatEntity> groupChatEntities = groupChatDao.findAllGroupChatsByOwnerId(ownerId);
        List<GroupChatDto> groupChatDtos = new ArrayList<>();

        for (GroupChatEntity groupChatEntity : groupChatEntities) {
            GroupChatDto groupChatDto = modelMapper.map(groupChatEntity, GroupChatDto.class);
            groupChatDtos.add(groupChatDto);
        }
        return groupChatDtos;
    }

    public GroupChatDto findGroupChatByOwnerIdAndChatName(Integer ownerId, String name) {
        return modelMapper.map(groupChatDao.findGroupChatByOwnerIdAndChatName(ownerId,name), GroupChatDto.class);
    }

    public GroupChatDto findGroupChatByChatId(String chatId) {
        return modelMapper.map(groupChatDao.findGroupChatByChatId(chatId), GroupChatDto.class);
    }

    public int deleteGroupChat(String id) {
        return groupChatDao.delete(id);
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

    public List<GroupChatDto> getAllGroupChats(String phoneNumber) {
        return null;
    }
    public Optional<GroupChatDto> createGroupChat(String phoneNumber, String groupName) {
        return null;
    }





}
