package gov.iti.jets.network.controllers.implementaions;

import gov.iti.jets.api.requests.AddUserToGCRequest;
import gov.iti.jets.api.requests.GroupChatRequest;
import gov.iti.jets.api.requests.ChatRequest;
import gov.iti.jets.api.responses.AddUserToGCResponse;
import gov.iti.jets.api.responses.GroupChatResponse;
import gov.iti.jets.api.responses.ChatResponse;
import gov.iti.jets.entities.GroupChatEntity;

import gov.iti.jets.mappers.UserMapper;
import gov.iti.jets.dto.GroupChatDto;
// import gov.iti.jets.network.controllers.CallbackController;
import gov.iti.jets.network.controllers.interfaces.ChatController;
import gov.iti.jets.network.manager.NetworkManager;
import gov.iti.jets.persistence.daos.GroupCahtDao;
import gov.iti.jets.persistence.daos.UserDao;
import gov.iti.jets.services.ChatServices;
import gov.iti.jets.services.UserServices;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;

public class ChatControllerImpl extends UnicastRemoteObject implements ChatController {

    private static ChatControllerImpl instance;

    private ChatControllerImpl() throws RemoteException {
    }

    public static ChatControllerImpl getInstance() {
        try {
            if (instance == null) {
                instance = new ChatControllerImpl();
            }
            NetworkManager.getRegistry().rebind("ChatController", instance);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        return instance;
    }

    @Override
    public ChatResponse getAllChat(ChatRequest request) {
        ChatServices chatService = new ChatServices();
        return new ChatResponse(chatService.getAllRegularChats(request.getPhoneNumber()),
                chatService.getAllGroupChats(request.getPhoneNumber()));
    }

    @Override
    public GroupChatResponse createGroupChat(GroupChatRequest request) throws RemoteException {
        ChatServices chatService = new ChatServices();
        String phoneNumber = request.getUserPhoneNumber();
        String groupName = request.getGroupName();
        Optional<GroupChatDto> groupChatOptional = chatService.createGroupChat(phoneNumber, groupName);
        if (groupChatOptional.isPresent()) {
            return new GroupChatResponse(groupChatOptional.get());
        }
        return new GroupChatResponse(null);
    }

    @Override
    public AddUserToGCResponse addUserToGroupChat(AddUserToGCRequest request) throws RemoteException {
            GroupCahtDao groupChatDao = new GroupCahtDao();
            UserDao userDao = new UserDao();
            ChatServices chatServices = new ChatServices();
            UserServices userServices = new UserServices();
            UserMapper userMapper = new UserMapper();
            String phoneNumber = request.getUserPhoneToBeAdded();
            String chatId = request.getChatId();
            GroupChatEntity groupChatEntity = groupChatDao.findGroupChatByChatId(chatId).get();
            List<GroupChatDto> groupCahtDtos = chatServices.findAllGroupChatsByOwnerId(groupChatEntity.getOwnerId());
            GroupChatDto groupChatDto = groupCahtDtos.stream().filter(chat -> chat.getChatId().equals(chatId)).findFirst().get();
            // userGroupMapper.addUserToGroupChat(phoneNumber, chatId);
            // CallbackController cb = OnlineStatusControllerSingleton.getUsers().get(phoneNumber);
            // if(cb != null){
            //     cb.addCurrentUserToGroupChat(groupChat);
            // }
            return new AddUserToGCResponse(userServices.findUserByPhoneNumber(phoneNumber).get(), "error");

        // throw new UnsupportedOperationException("Unimplemented method 'addUserToGroupChat'");
    }

  
}
