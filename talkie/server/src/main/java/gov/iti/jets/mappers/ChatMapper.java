package gov.iti.jets.mappers;

import gov.iti.jets.dto.ChatDto;
import gov.iti.jets.dto.RegularChatDto;
import gov.iti.jets.mappers.interfaces.ChatMapperInt;
import gov.iti.jets.persistence.daos.ChatDao;
import org.modelmapper.ModelMapper;

public class ChatMapper implements ChatMapperInt<ChatDto> {

     ModelMapper modelMapper = new ModelMapper();
    ChatDao chatDao = new ChatDao();
    public ChatMapper (ModelMapper modelMappe){
        this.modelMapper = modelMappe;
    }
    public ChatMapper(){}


    @Override
    public ChatDto findChatById(String uuid) {
        return modelMapper.map(chatDao.findChatById(uuid), ChatDto.class);
    }


    public int deleteChat(String id) {
        return chatDao.delete(id);
    }


}
