package gov.iti.jets.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import gov.iti.jets.mappers.FriendRequestMapper;
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

    private FriendRequestMapper friendRequestMapper = new FriendRequestMapper();

    public FriendRequestServices(FriendRequestMapper friendRequestMapper){
        this.friendRequestMapper = friendRequestMapper;
    }
    public FriendRequestServices(){}

    public RegularChatDto acceptFriendRequest(String senderPhoneNum, String recievePhoneNum) {
        return friendRequestMapper.acceptFriendRequest(senderPhoneNum, recievePhoneNum);
    }


//    public boolean refuseFriendRequestOrCancel (String senderPhoneNum, String receiverPhoneNum) {
//       return friendRequestMapper.refuseFriendRequestOrCancel(senderPhoneNum, receiverPhoneNum);
//    }
//
    public String refuse (String senderPhoneNum, String receiverPhoneNum) {
       return friendRequestMapper.refuse(senderPhoneNum, receiverPhoneNum);
    }


    public FriendRequestDto saveFriendRequest(String senderPhoneNum, String receiverPhoneNum) {
        return friendRequestMapper.saveFriendRequest(senderPhoneNum, receiverPhoneNum);
    }

    public List<FriendRequestDto> getSentFriendRequestByUserPhoneNumber(String userPhoneNumber) {
        return friendRequestMapper.getSentFriendRequestByUserPhoneNumber(userPhoneNumber);
    }


    public List<FriendRequestDto> getReceivedFriendReqByUserPhoneNumber(String userPhoneNumber) {
       return friendRequestMapper.getReceivedFriendReqByUserPhoneNumber(userPhoneNumber);
    }

    public int delete (String senderPhoneNumber, String receiverPhoneNumber) {
        return friendRequestMapper.delete(senderPhoneNumber,receiverPhoneNumber);
    }

    public FriendRequestDto findFriendRequestByBoth(String senderPhoneNumber, String receiverPhoneNumber){
        return friendRequestMapper.findFriendRequestByBoth(senderPhoneNumber, receiverPhoneNumber);
    }
}
