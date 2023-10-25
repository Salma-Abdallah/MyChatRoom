package gov.iti.jets.network.controllers;



import java.rmi.Remote;
import java.rmi.RemoteException;

import gov.iti.jets.api.requests.AcceptFriendRequest;
import gov.iti.jets.api.requests.CancelFriendRequest;
import gov.iti.jets.api.requests.GetFriendReqRequest;
import gov.iti.jets.api.requests.RefuseFriendRequest;
import gov.iti.jets.api.requests.SendFriendReqRequest;
import gov.iti.jets.api.responses.AcceptFriendResponse;
import gov.iti.jets.api.responses.CancelFriendRequestResponse;
import gov.iti.jets.api.responses.GetFriendReqResponse;
import gov.iti.jets.api.responses.RefuseFriendFriendResponse;
import gov.iti.jets.api.responses.SendFriendReqResponse;

public interface FriendRequestController extends Remote {


    SendFriendReqResponse sendFriendRequest (SendFriendReqRequest sendFriendReqRequest) throws  RemoteException;
    CancelFriendRequestResponse cancel (CancelFriendRequest cancelFriendRequest) throws RemoteException;
    RefuseFriendFriendResponse refuse (RefuseFriendRequest refuseFriendRequest) throws RemoteException;
    AcceptFriendResponse accept (AcceptFriendRequest acceptFriendRequest) throws  RemoteException;
    GetFriendReqResponse getSentFriendRequestByUserID (GetFriendReqRequest getFriendReqRequest)throws RemoteException;
    GetFriendReqResponse getReceivedFriendReqByUserID(GetFriendReqRequest getFriendReqRequest) throws RemoteException;




}
