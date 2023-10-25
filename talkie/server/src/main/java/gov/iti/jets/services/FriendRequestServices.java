package gov.iti.jets.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;

import gov.iti.jets.dto.FriendRequestDto;
import gov.iti.jets.dto.RegularChatDto;
import gov.iti.jets.entities.FriendRequestEntity;
import gov.iti.jets.entities.RegularChatEntity;
import gov.iti.jets.entities.UserEntity;
import gov.iti.jets.mappers.UserMapper;
import gov.iti.jets.persistence.daos.FriendRequestDao;
import gov.iti.jets.persistence.daos.RegularChatDao;
import gov.iti.jets.persistence.daos.UserDao;

public class FriendRequestServices {

    private UserDao userDao = new UserDao();
    private FriendRequestDao friendRequestDao = new FriendRequestDao();
    private RegularChatDao regularChatDao = new RegularChatDao();
    private UserMapper userMapper = new UserMapper();
    private ModelMapper modelMapper = new ModelMapper();

    public RegularChatDto acceptFriendRequest(String SenderPhoneNum, String RecievePhoneNum) {

        UserEntity senderUserEntity = userDao.findUserByPhoneNumber(SenderPhoneNum).get();
        UserEntity receiverUserEntity = userDao.findUserByPhoneNumber(RecievePhoneNum).get();
        friendRequestDao.delete(senderUserEntity.getId(), receiverUserEntity.getId());
        RegularChatEntity regularChatEntity = regularChatDao
                .save(new RegularChatEntity(senderUserEntity, receiverUserEntity));

        return new RegularChatDto(regularChatEntity.getId(), userMapper.userEntityToDto(senderUserEntity),
                userMapper.userEntityToDto(receiverUserEntity));
    }

    public boolean refuseFriendRequestOrCancel (String senderPhoneNum, String receiverPhoneNum) {
        UserEntity senderUserEntity = userDao.findUserByPhoneNumber(senderPhoneNum).get();
        UserEntity receiverUserEntity = userDao.findUserByPhoneNumber(receiverPhoneNum).get();
        if (friendRequestDao.delete(receiverUserEntity.getId(), senderUserEntity.getId()) != 0) {
            return true;
        }
        return false;
    }
    public String refuse (String userPhoneNumber, String friendPhoneNumber) {
        if(refuseFriendRequestOrCancel(userPhoneNumber, friendPhoneNumber)){
            return friendPhoneNumber;
        }
        return null;
    }

    public Optional<FriendRequestDto> saveFriendRequest(String senderPhoneNum, String receiverPhoneNum) {
        Optional<UserEntity> senderEntityOptional = userDao.findUserByPhoneNumber(senderPhoneNum);
        Optional<UserEntity> receiverEntityOptional = userDao.findUserByPhoneNumber(receiverPhoneNum);
        if (senderEntityOptional.isPresent() && receiverEntityOptional.isPresent()) {
            UserEntity senderEntity = senderEntityOptional.get();
            UserEntity receiverEntity = receiverEntityOptional.get();
            friendRequestDao.save(senderEntity.getId(), receiverEntity.getId());
            FriendRequestEntity friendRequestEntity = friendRequestDao
                    .findFriendRequestBySenderIdAndReceiverId(senderEntity.getId(), receiverEntity.getId()).get();
            return Optional.of(new FriendRequestDto(userMapper.userEntityToDto(friendRequestEntity.getSender()),
                    userMapper.userEntityToDto(friendRequestEntity.getReceiver()),
                    friendRequestEntity.isStatus(), friendRequestEntity.getSentAt()));
        }
        return Optional.empty();
    }

    public List<FriendRequestDto> getSentFriendRequestByUserPhoneNumber(String userPhoneNumber) {

        Optional<UserEntity> userEntityOptional = userDao.findUserByPhoneNumber(userPhoneNumber);
        if (userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();
            List<FriendRequestEntity> friendEntities = friendRequestDao.findSentFriendRequestByUserID(userEntity);
            List<FriendRequestDto> friendRequestList = new ArrayList<>();
            for (FriendRequestEntity friendRequestEntity : friendEntities) {

                friendRequestList.add(modelMapper.map(friendRequestEntity, FriendRequestDto.class));
            }
            return friendRequestList;
        }
        return null;
    }

    public List<FriendRequestDto> getReceivedFriendReqByUserPhoneNumber(String userPhoneNumber) {
        Optional<UserEntity> userEntityOptional = userDao.findUserByPhoneNumber(userPhoneNumber);
        if (userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();
            List<FriendRequestEntity> friendEntities = friendRequestDao.findReceivedFriendRequestByUserID(userEntity);
            List<FriendRequestDto> friendRequestList = new ArrayList<>();
            for (FriendRequestEntity friendRequestEntity : friendEntities) {

                friendRequestList.add(modelMapper.map(friendRequestEntity, FriendRequestDto.class));

            }
            return friendRequestList;
        }
        return null;
    }
    public int delete (String senderPhoneNumber, String receiverPhoneNumber) {
        UserEntity senderUserEntity = userDao.findUserByPhoneNumber(senderPhoneNumber).get();
        UserEntity receiverUserEntity = userDao.findUserByPhoneNumber(receiverPhoneNumber).get();
        return friendRequestDao.delete(senderUserEntity.getId(), receiverUserEntity.getId());
    }

    public Optional<FriendRequestDto> findFriendRequestByBoth(String senderPhoneNumber, String receiverPhoneNumber){
        Optional<UserEntity> senderEntityOptional = userDao.findUserByPhoneNumber(senderPhoneNumber);
        Optional<UserEntity> receiverEntityOptional = userDao.findUserByPhoneNumber(receiverPhoneNumber);
        if(senderEntityOptional.isPresent() && receiverEntityOptional.isPresent()){
            UserEntity senderEntity = senderEntityOptional.get();
            UserEntity receiverEntity = receiverEntityOptional.get();
            Optional<FriendRequestEntity> friendRequestEntityOptional = friendRequestDao.findFriendRequestBySenderIdAndReceiverId(senderEntity.getId(), receiverEntity.getId());
            if(friendRequestEntityOptional.isPresent()){
                FriendRequestEntity friendRequestEntity = friendRequestEntityOptional.get();
                return Optional.of(new FriendRequestDto(userMapper.userEntityToDto(friendRequestEntity.getSender()),
                        userMapper.userEntityToDto(friendRequestEntity.getReceiver()),
                        friendRequestEntity.isStatus(), friendRequestEntity.getSentAt()));
            }
        }
        return Optional.empty();
    }
}
