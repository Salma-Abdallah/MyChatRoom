package gov.iti.jets.services;

import java.util.List;
import java.util.Optional;

import gov.iti.jets.mappers.ChatMapper;
import gov.iti.jets.mappers.GroupChatMapper;
import gov.iti.jets.mappers.RegularChatMapper;
import gov.iti.jets.mappers.interfaces.ChatMapperInt;

import gov.iti.jets.dto.GroupChatDto;
import gov.iti.jets.dto.RegularChatDto;
import gov.iti.jets.dto.UserDto;

public class ChatServices {

    private RegularChatMapper regularChatMapper = new RegularChatMapper();
    private GroupChatMapper groupChatMapper = new GroupChatMapper();
    private ChatMapper chatMapper = new ChatMapper();

    public ChatServices(RegularChatMapper regularChatMapper,ChatMapper chatMapper, GroupChatMapper groupChatMapper) {
        this.regularChatMapper = regularChatMapper;
        this.chatMapper = chatMapper;
        this.groupChatMapper = groupChatMapper;
    }
    public ChatServices(){}

    public RegularChatDto saveRegularChat(String firstPhoneNumber, String secondPhoneNumber) {
        return regularChatMapper.saveRegularChat(firstPhoneNumber,secondPhoneNumber);
    }

    public <T> T findChatById(String uuid, ChatMapperInt<T> chatMapper) {
        return chatMapper.findChatById(uuid);
    }

    public int deleteChat(String id) {
        return chatMapper.deleteChat(id);
    }

    public RegularChatDto saveRegularChat(RegularChatDto regularChatDto) {
       return regularChatMapper.saveRegularChat(regularChatDto.getFirstParticipant().getPhoneNumber(),regularChatDto.getSecondParticipant().getPhoneNumber());
    }

    public RegularChatDto findRegularChatByParticipantsIds(UserDto firstParticipantId, UserDto secondParticipantId) {
          return regularChatMapper.findRegularChatByParticipantsIds(firstParticipantId, secondParticipantId);
    }

    public RegularChatDto findRegularChatByParticipantsPhoneNumbers(String phoneNumber1,
            String phoneNumber2) {
        return regularChatMapper.findRegularChatByParticipantsPhoneNumbers(phoneNumber1, phoneNumber2);
    }

    public RegularChatDto findRegularChatByParticipantPhoneNumber(String phoneNumber) {
        return regularChatMapper.findRegularChatByParticipantPhoneNumber(phoneNumber);
    }

    public List<RegularChatDto> findAllRegChatsByUserID (int userId) {
       return regularChatMapper.findAllRegChatsByUserID(userId);
    }

    public int deleteRegularChat(String id) {
        return regularChatMapper.deleteRegularChat(id);
    }
    public GroupChatDto saveGroupChat (GroupChatDto groupCahtDto) {
       return groupChatMapper.saveGroupChat(groupCahtDto);
    }
    public List<GroupChatDto> findAllGroupChatsByOwnerId(Integer ownerId) {
        return groupChatMapper.findAllGroupChatsByOwnerId(ownerId);
    }
    public GroupChatDto findGroupChatByOwnerIdAndChatName(Integer ownerId, String name) {
        return groupChatMapper.findGroupChatByOwnerIdAndChatName(ownerId,name);
    }
    public GroupChatDto findGroupChatByChatId(String chatId) {
        return groupChatMapper.findGroupChatByChatId(chatId);
    }
    public int deleteGroupChat(String id) {
        return groupChatMapper.deleteGroupChat(id);
    }
    public List<RegularChatDto> getAllRegularChats(String phoneNumber) {
        return regularChatMapper.getAllRegularChats(phoneNumber);
    }

    public List<GroupChatDto> getAllGroupChats(String phoneNumber) {
        return null;
    }

    public Optional<GroupChatDto> createGroupChat(String phoneNumber, String groupName) {
        return null;
    }

}
