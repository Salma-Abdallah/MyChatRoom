package gov.iti.jets.mappers;

import gov.iti.jets.dto.FriendRequestDto;
import gov.iti.jets.dto.RegularChatDto;
import gov.iti.jets.entities.FriendRequestEntity;
import gov.iti.jets.entities.RegularChatEntity;
import gov.iti.jets.entities.UserEntity;
import gov.iti.jets.persistence.daos.FriendRequestDao;
import gov.iti.jets.persistence.daos.RegularChatDao;
import gov.iti.jets.persistence.daos.UserDao;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class FriendRequestMapper {

    private final FriendRequestDao friendRequestDao = new FriendRequestDao();
    private final RegularChatDao regularChatDao = new RegularChatDao();
    private final ModelMapper modelMapper = new ModelMapper();
    private final UserMapper userMapper = new UserMapper();

    private final UserDao userDao = new UserDao();


    public RegularChatDto acceptFriendRequest(String senderPhoneNum, String receiverPhoneNum) {

        UserEntity senderUserEntity = findUserByPhoneNumberOrThrow(senderPhoneNum, "SenderUser Not Found");
        UserEntity receiverUserEntity = findUserByPhoneNumberOrThrow(receiverPhoneNum, "ReceiverUser Not Found");
        friendRequestDao.delete(senderUserEntity.getId(), receiverUserEntity.getId());
        RegularChatEntity regularChatEntity = regularChatDao.save(new RegularChatEntity(senderUserEntity, receiverUserEntity));

        return new RegularChatDto(regularChatEntity.getId(), userMapper.userEntityToDto(senderUserEntity), userMapper.userEntityToDto(receiverUserEntity));
    }

    public boolean refuseFriendRequestOrCancel(String senderPhoneNum, String receiverPhoneNum) {
        UserEntity senderUserEntity = findUserByPhoneNumberOrThrow(senderPhoneNum, "SenderUser Not Found");
        UserEntity receiverUserEntity = findUserByPhoneNumberOrThrow(receiverPhoneNum, "ReceiverUser Not Found");
        return friendRequestDao.delete(receiverUserEntity.getId(), senderUserEntity.getId()) != 0;
    }

    public String refuse(String senderPhoneNum, String receiverPhoneNum) {
        if (refuseFriendRequestOrCancel(senderPhoneNum, receiverPhoneNum)) {
            return receiverPhoneNum;
        }
        return null;
    }

    private UserEntity findUserByPhoneNumberOrThrow(String phoneNumber, String errorMessage) {
        return userDao.findUserByPhoneNumber(phoneNumber).orElseThrow(() -> new RuntimeException(errorMessage));
    }

    public FriendRequestDto saveFriendRequest(String senderPhoneNum, String receiverPhoneNum) {
        UserEntity sender = findUserByPhoneNumberOrThrow(senderPhoneNum, "Sender not found!");
        UserEntity receiver = findUserByPhoneNumberOrThrow(receiverPhoneNum, "Receiver not found!");

        friendRequestDao.save(sender.getId(), receiver.getId());
        FriendRequestEntity friendRequestEntity = friendRequestDao.findFriendRequestBySenderIdAndReceiverId(sender.getId(), receiver.getId()).get();
        return modelMapper.map(friendRequestEntity, FriendRequestDto.class);
    }

    public List<FriendRequestDto> getSentFriendRequestByUserPhoneNumber(String userPhoneNumber) {
        UserEntity user = findUserByPhoneNumberOrThrow(userPhoneNumber, "User not found!");
        List<FriendRequestEntity> friendEntities = friendRequestDao.findSentFriendRequestByUserID(user);
        List<FriendRequestDto> friendRequestList = new ArrayList<>();
        for (FriendRequestEntity friendRequestEntity : friendEntities) {
            friendRequestList.add(modelMapper.map(friendRequestEntity, FriendRequestDto.class));
        }
        return friendRequestList;
    }

    public List<FriendRequestDto> getReceivedFriendReqByUserPhoneNumber(String userPhoneNumber) {
        UserEntity user = findUserByPhoneNumberOrThrow(userPhoneNumber, "User not found!");
        List<FriendRequestEntity> friendEntities = friendRequestDao.findReceivedFriendRequestByUserID(user);
        List<FriendRequestDto> friendRequestList = new ArrayList<>();
        for (FriendRequestEntity friendRequestEntity : friendEntities) {
            friendRequestList.add(modelMapper.map(friendRequestEntity, FriendRequestDto.class));
        }
        return friendRequestList;
    }

    public int delete(String senderPhoneNum, String receiverPhoneNum) {
        UserEntity sender = findUserByPhoneNumberOrThrow(senderPhoneNum, "Sender not found!");
        UserEntity receiver = findUserByPhoneNumberOrThrow(receiverPhoneNum, "Receiver not found!");
        return friendRequestDao.delete(sender.getId(), receiver.getId());
    }

    public FriendRequestDto findFriendRequestByBoth(String senderPhoneNum, String receiverPhoneNum) {
        UserEntity sender = findUserByPhoneNumberOrThrow(senderPhoneNum, "Sender not found!");
        UserEntity receiver = findUserByPhoneNumberOrThrow(receiverPhoneNum, "Receiver not found!");
        FriendRequestEntity friendRequestEntity = friendRequestDao.findFriendRequestBySenderIdAndReceiverId(
                sender.getId(), receiver.getId()).orElseThrow(() -> new RuntimeException("Friend Request with these Users Not Found !!!"));
        return modelMapper.map(friendRequestEntity, FriendRequestDto.class);
    }
}
