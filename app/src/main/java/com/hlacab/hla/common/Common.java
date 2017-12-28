package com.hlacab.hla.common;

import com.google.android.gms.maps.GoogleMap;
import com.hlacab.hla.remote.FCMClient;
import com.hlacab.hla.remote.GoogleMapAPI;
import com.hlacab.hla.remote.IFCMService;
import com.hlacab.hla.remote.IGoogleAPI;

/**
 * Created by gopinath on 02/12/17.
 */

public class Common {


    public static final String driver_tb1 = "Drivers";
    public static final String user_driver_tb1 = "DriversInformation";
    public static final String user_rider_tb1 = "RidersInformation";
    public static final String pickup_request_tb1 = "PickupRequest";
    public static final String token_tb1 = "Tokens";

    public static final String fcmURL = "https://fcm.googleapis.com/";

    public static final String googleAPIUrl = "https://maps.googleapis.com";

    private static double base_fare = 8.00;
    private static double time_rate = 1.35;
    private static double distance_rate = 5.55;


    public static double getPrice(double km, int min) {
        return (base_fare + (time_rate * min) + (distance_rate * km));
    }


    public static IFCMService getFCMService() {
        return FCMClient.getClient(fcmURL).create(IFCMService.class);
    }

    public static IGoogleAPI getGoogleService() {
        return GoogleMapAPI.getClient(googleAPIUrl).create(IGoogleAPI.class);
    }

}
