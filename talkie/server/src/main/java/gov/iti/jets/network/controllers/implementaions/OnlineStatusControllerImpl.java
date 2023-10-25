package gov.iti.jets.network.controllers.implementaions;

import gov.iti.jets.api.requests.ChangeUserStatusRequest;
import gov.iti.jets.api.responses.ChangeUserStatusResponse;
import gov.iti.jets.dto.RegularChatDto;
import gov.iti.jets.dto.UserDto;
import gov.iti.jets.network.controllers.interfaces.CallbackController;
import gov.iti.jets.network.controllers.interfaces.OnlineStatusController;
import gov.iti.jets.network.manager.NetworkManager;
import gov.iti.jets.services.ChatServices;
import gov.iti.jets.services.UserServices;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class OnlineStatusControllerImpl extends UnicastRemoteObject implements OnlineStatusController {

    private static OnlineStatusControllerImpl instance;
    private static ConcurrentMap<String, CallbackController> users = new ConcurrentHashMap<>();
    ChatServices chatService = new ChatServices();
    UserServices userService = new UserServices();

    private OnlineStatusControllerImpl() throws RemoteException {
    }

    public static OnlineStatusControllerImpl getInstance() {
        try {
            if (instance == null) {
                instance = new OnlineStatusControllerImpl();
            }
            NetworkManager.getRegistry().rebind("OnlineStatusController", instance);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        return instance;
    }

    public static Map<String, CallbackController> getUsers() {
        return Collections.unmodifiableMap(users);
    }

    @Override
    public void connect(UserDto user, CallbackController callbackController) throws RemoteException {
        userService.updateStatusByUserPhoneNumber(user.getPhoneNumber(), "Available");
        updateStatusCallback(user.getPhoneNumber(), "Available");
        users.put(user.getPhoneNumber(), callbackController);
    }

    @Override
    public void disconnect(String phoneNumber) throws RemoteException {
        userService.updateStatusByUserPhoneNumber(phoneNumber, "Offline");
        updateStatusCallback(phoneNumber, "Offline");
        users.remove(phoneNumber);
    }

    @Override
    public void ping() throws RemoteException {
        new Thread(() -> {
            while (true) {
                users.forEach((key, value) -> {
                    try {
                        System.out.println(key);
                        value.respond();
                    } catch (RemoteException e) {
                        System.out.println("[INFO] Connection: " + "[" + key + "]" + "disconnected.");
                        try {
                            disconnect(key);
                        } catch (RemoteException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    @Override
    public ChangeUserStatusResponse changeStatus(ChangeUserStatusRequest request) throws RemoteException {
        String phoneNumber = request.getCurrentUserPhoneNumber();
        String status = request.getStatus();
        userService.updateStatusByUserPhoneNumber(phoneNumber, status);
        updateStatusCallback(phoneNumber, status);
        return new ChangeUserStatusResponse(true);
    }

    private void updateStatusCallback(String phoneNumber, String status) throws RemoteException {
        for (RegularChatDto chat : chatService.getAllRegularChats(phoneNumber)) {
            CallbackController callBack = users.get(chat.getFirstParticipant().getPhoneNumber());
            if (callBack != null) {
                callBack.friendOnlineStatus(chat.getChatId(), status);
            }
        }
    }

}
