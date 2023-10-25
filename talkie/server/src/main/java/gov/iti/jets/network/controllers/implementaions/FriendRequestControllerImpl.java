package gov.iti.jets.network.controllers.implementaions;



import gov.iti.jets.api.requests.*;
import gov.iti.jets.api.responses.*;
import gov.iti.jets.dto.FriendRequestDto;
import gov.iti.jets.dto.RegularChatDto;
import gov.iti.jets.dto.UserDto;
import gov.iti.jets.network.controllers.interfaces.CallbackController;
import gov.iti.jets.network.controllers.interfaces.FriendRequestController;
import gov.iti.jets.network.controllers.interfaces.OnlineStatusController;
import gov.iti.jets.network.manager.NetworkManager;
import gov.iti.jets.services.ChatServices;
import gov.iti.jets.services.FriendRequestServices;
import gov.iti.jets.services.UserServices;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Optional;


public class FriendRequestControllerImpl extends UnicastRemoteObject implements FriendRequestController {

    private static FriendRequestControllerImpl instance;
    public static FriendRequestControllerImpl getInstance(){
        try {
            if(instance == null){
                instance = new FriendRequestControllerImpl();
            }
            NetworkManager.getRegistry().rebind("FriendRequestController", instance);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        return instance;
    }

    private FriendRequestControllerImpl() throws RemoteException {}
    private ChatServices chatServices = new ChatServices();
    @Override
    public SendFriendReqResponse sendFriendRequest (SendFriendReqRequest request) throws RemoteException {
        
        String senderPhoneNumber = request.getSenderPhoneNumber();
        String receiverPhoneNumber = request.getReceiverPhoneNumber();
        FriendRequestServices friendRequestService = new FriendRequestServices();
        UserServices userService = new UserServices();
        List<RegularChatDto> chats = chatServices.getAllRegularChats(senderPhoneNumber);
        chats = chats.stream().filter((chat) -> chat.getFirstParticipant().getPhoneNumber().equals(receiverPhoneNumber)).toList();
        if(userService.findUserByPhoneNumber(receiverPhoneNumber).isEmpty()){
            return new SendFriendReqResponse(null, null, "User not found");
        }
        if(friendRequestService.findFriendRequestByBoth(senderPhoneNumber, receiverPhoneNumber).isPresent()){
            return new SendFriendReqResponse(null, null, "Request already sent.");
        }
        else if(friendRequestService.findFriendRequestByBoth(receiverPhoneNumber, senderPhoneNumber).isPresent()){
            friendRequestService.delete(receiverPhoneNumber, senderPhoneNumber);
            Optional<RegularChatDto> regularChat = chatServices.saveRegularChat(senderPhoneNumber, receiverPhoneNumber);
            CallbackController cb = OnlineStatusControllerImpl.getUsers().get(senderPhoneNumber);
            if(cb != null){
                cb.createNewRegularChat(regularChat.get());
            }
            return new SendFriendReqResponse(null, regularChat.get(), null);
        } else if (chats.size() == 0) {
            Optional<FriendRequestDto> friendRequest = friendRequestService.saveFriendRequest(senderPhoneNumber, receiverPhoneNumber);
            if(friendRequest.isPresent()){
                CallbackController cb = OnlineStatusControllerImpl.getUsers().get(receiverPhoneNumber);
                if(cb != null){
                    cb.createNewFriendRequest(friendRequestService
                            .findFriendRequestByBoth(senderPhoneNumber, receiverPhoneNumber)
                            .get());
                }
                return new SendFriendReqResponse(friendRequest.get(), null, null);
            }
        }
        return new SendFriendReqResponse(null, null, "Already in your friend list");
    }
    @Override
    public CancelFriendRequestResponse cancel(CancelFriendRequest cancelFriendRequest) throws RemoteException {
        CallbackController cb = OnlineStatusControllerImpl.getUsers().get(cancelFriendRequest.getFriendPhoneNumber());
        if(cb != null){
            cb.deleteRecievedFriendRequest(cancelFriendRequest.getUserPhoneNumber());
        }
        return new CancelFriendRequestResponse(new FriendRequestServices().refuse(cancelFriendRequest.getUserPhoneNumber(),cancelFriendRequest.getFriendPhoneNumber()));
    }

    @Override
    public RefuseFriendFriendResponse refuse(RefuseFriendRequest refuseFriendRequest) throws RemoteException {
        String receiverPhoneNumber = refuseFriendRequest.getUserPhoneNumber();
        CallbackController cb = OnlineStatusControllerImpl.getUsers().get(refuseFriendRequest.getFriendPhoneNumber());
        if(cb != null){
            cb.deleteRecievedFriendRequest(receiverPhoneNumber);
        }
        return new RefuseFriendFriendResponse(new FriendRequestServices().refuse(refuseFriendRequest.getUserPhoneNumber(),refuseFriendRequest.getFriendPhoneNumber()));
    }
    @Override
    public AcceptFriendResponse accept(AcceptFriendRequest acceptFriendRequest) throws RemoteException {
        RegularChatDto regularChat = new FriendRequestServices().acceptFriendRequest(acceptFriendRequest.getUserPhoneNumber(), acceptFriendRequest.getFriendPhoneNumber());
        CallbackController cb = OnlineStatusControllerImpl.getUsers().get(acceptFriendRequest.getFriendPhoneNumber());
        if(cb != null){
            cb.createNewRegularChat(regularChat);
        }
        return new AcceptFriendResponse(regularChat);
    }
    @Override
    public GetFriendResponse getSentFriendRequestByByPhone (GetFriendRequest getFriendRequest) {
        return new GetFriendResponse(new FriendRequestServices().getSentFriendRequestByUserPhoneNumber(getFriendRequest.getUserPhoneNumber()));
    }

    @Override
    public GetFriendResponse getReceivedFriendReqByPhone (GetFriendRequest getFriendRequest) {
        return new GetFriendResponse(new FriendRequestServices().getReceivedFriendReqByUserPhoneNumber(getFriendRequest.getUserPhoneNumber()));
    }
}
