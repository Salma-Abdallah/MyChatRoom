package gov.iti.jets.mappers;

import gov.iti.jets.entities.MessageEntity;
import gov.iti.jets.entities.UserEntity;
import gov.iti.jets.persistence.daos.MessageDao;

import java.util.List;
import java.util.Optional;

public class MessageMapper {
    private MessageDao messageDao;
    public MessageMapper() {
        messageDao = new MessageDao();
    }


    public List<MessageEntity> findMessagesByChatId(String chatId){
        List<MessageEntity> messageEntities = messageDao.findMessagesByChatID(chatId);
        return messageEntities.stream().map((messageEntity) ->
                new MessageEntity(messageEntity.getId(), userMapper.entityToModel(messageEntity.getAuthor()),
                        chatId, messageEntity.getFontStyle(), messageEntity.getFontColor(), messageEntity.getFontSize(),
                        messageEntity.isBold(), messageEntity.isItalic(), messageEntity.isUnderlined(), messageEntity.getTextBackground(),
                        messageEntity.getSentAt(), messageEntity.getContent(), messageEntity.getFileUrl(), messageEntity.isSeen())
        ).toList();
    }
    public Optional<MessageEntity> insert(MessageEntity message){
        UserEntity author = userMapper.getUserByPhoneNumber(message.getAuthor().getPhoneNumber()).get();
        Optional<MessageEntity> messageEntityOptional = messageDao.saveMessage(new MessageEntity(author, message.getChatId(),
                message.getFontStyle(), message.getFontColor(), message.getFontSize(),
                message.isBold(), message.isItalic(), message.isUnderlined(),
                message.getTextBackground(), message.getSentAt(), message.getContent(),
                message.getFileUrl(), message.isSeen()));
        if(messageEntityOptional.isPresent()){
            message.setId(messageEntityOptional.get().getId());
            return Optional.of(message);
        }
        return Optional.empty();
    }

    public int updateMessageStatusByPhoneNumberAndChatId(String phoneNumber, String chatId){
        return messageDao.updateMessageStatusByPhoneNumberAndChatId(phoneNumber, chatId);
    }

}
