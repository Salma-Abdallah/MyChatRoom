package gov.iti.jets.mappers;

import gov.iti.jets.dto.GroupChatDto;
import gov.iti.jets.entities.GroupChatEntity;
import gov.iti.jets.persistence.daos.GroupCahtDao;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class GroupChatMapper {

    private GroupCahtDao groupChatDao = new GroupCahtDao();
    private ModelMapper modelMapper = new ModelMapper();

    public GroupChatMapper(GroupCahtDao groupCahtDao, ModelMapper modelMapper){
        this.groupChatDao = groupCahtDao;
        this.modelMapper = modelMapper;
    }
    public GroupChatMapper(){}

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
