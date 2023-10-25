package gov.iti.jets.network.controllers.interfaces;


import gov.iti.jets.api.requests.*;
import gov.iti.jets.api.responses.*;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FriendRequestController extends Remote {

   SendFriendReqResponse sendFriendRequest (SendFriendReqRequest sendFriendReqRequest) throws  RemoteException;
   CancelFriendRequestResponse cancel (CancelFriendRequest cancelFriendRequest) throws RemoteException;
   RefuseFriendFriendResponse refuse (RefuseFriendRequest refuseFriendRequest) throws RemoteException;
   AcceptFriendResponse accept (AcceptFriendRequest acceptFriendRequest) throws  RemoteException;
   GetFriendResponse getSentFriendRequestByByPhone (GetFriendRequest getFriendRequest)throws RemoteException;
   GetFriendResponse getReceivedFriendReqByPhone(GetFriendRequest getFriendRequest) throws RemoteException;




}
