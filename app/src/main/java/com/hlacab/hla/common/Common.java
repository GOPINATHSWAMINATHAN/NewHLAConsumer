package com.hlacab.hla.common;

import com.hlacab.hla.remote.FCMClient;
import com.hlacab.hla.remote.IFCMService;

/**
 * Created by gopinath on 02/12/17.
 */

public class Common {


    public static final String driver_tb1="Drivers";
    public static final String user_driver_tb1="DriversInformation";
    public static final String user_rider_tb1="RidersInformation";
    public static final String pickup_request_tb1="PickupRequest";
    public static final String token_tb1="Tokens";

    public static final String fcmURL = "https://fcm.googleapis.com/";


    public static IFCMService getFCMService()
    {
        return FCMClient.getClient(fcmURL).create(IFCMService.class);
    }

}
