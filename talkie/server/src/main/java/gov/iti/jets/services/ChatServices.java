package gov.iti.jets.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import gov.iti.jets.dto.ChatDto;
import gov.iti.jets.dto.GroupChatDto;
import gov.iti.jets.dto.RegularChatDto;
import gov.iti.jets.entities.GroupChatEntity;
import gov.iti.jets.entities.RegularChatEntity;
import gov.iti.jets.persistence.daos.ChatDao;
import gov.iti.jets.persistence.daos.GroupCahtDao;
import gov.iti.jets.persistence.daos.RegularChatDao;

public class ChatServices {

    private ModelMapper modelMapper = new ModelMapper();
    private ChatDao chatDao = new ChatDao();
    private RegularChatDao regularChatDao = new RegularChatDao();
    private GroupCahtDao groupChatDao = new GroupCahtDao();

    public ChatServices(ModelMapper modelMapper, ChatDao chatDao, RegularChatDao regularChatDao,
            GroupCahtDao groupChatDao) {
        this.chatDao = chatDao;
        this.groupChatDao = groupChatDao;
        this.modelMapper = modelMapper;
        this.regularChatDao = regularChatDao;
    }
    public ChatServices(){}

    // public String save(String uuid) {
    // return chatDao.save(uuid);
    // }

    public ChatDto findChatById(String uuid) {
        return modelMapper.map(chatDao.save(uuid), ChatDto.class);
    }

    public int deleteChat(String id) {
        return chatDao.delete(id);
    }

    public RegularChatDto saveRegularChat(RegularChatDto regularChatDto) {
        RegularChatEntity regularChatEntity = modelMapper.map(regularChatDto, RegularChatEntity.class);
        return modelMapper.map(regularChatDao.save(regularChatEntity), RegularChatDto.class);
    }

    public RegularChatDto findRegularChatByParticipantsIds(Integer firstParticipantId,
            Integer secondParticipantId) {
        return modelMapper.map(regularChatDao.findRegularChatByParticipantsIds(firstParticipantId, secondParticipantId),
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

    public List<RegularChatDto> findAllRegChatsByUserID(int userId) {
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





}
