package com.hlacab.hla;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hlacab.hla.common.Common;
import com.hlacab.hla.remote.IGoogleAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by gopinath on 02/12/17.
 */

public class BottomSheetRiderFragment extends BottomSheetDialogFragment {


    String mLocation, mDestination;
    TextView txtCalculate,txtLocation,txtDestination;
    IGoogleAPI mService;

    boolean isTapOnMap;

    public static BottomSheetRiderFragment newInstance(String location, String destination,boolean isTapOnMap) {
        BottomSheetRiderFragment f = new BottomSheetRiderFragment();
        Bundle args = new Bundle();
        args.putString("location", location);
        args.putString("destination", destination);
        args.putBoolean("isTapOnMap",isTapOnMap);
        f.setArguments(args);
        return f;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLocation = getArguments().getString("location");
        mDestination = getArguments().getString("destination");
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_rider, container, false);
        txtLocation = (TextView) view.findViewById(R.id.txtLocation);
        txtDestination = (TextView) view.findViewById(R.id.txtDestination);
        txtCalculate = (TextView) view.findViewById(R.id.txtCalculate);

        mService = Common.getGoogleService();
        getPrice(mLocation, mDestination);
        if(!isTapOnMap) {

            //Call this fragment from PlaceAutoComplete TextView
            txtLocation.setText(mLocation);
            txtDestination.setText(mDestination);
        }
        return view;
    }

    private void getPrice(String mLocation, String mDestination) {

        String requestUrl=null;
        try
        {
            requestUrl="https://maps.googleapis.com/maps/api/directions/json?" +
                    "mode=driving&" +
                    "transit_routing_preference=less_driving&" +
                    "origin="+mLocation+"&" +
                    "destination="+mDestination+"&" +
                    "key="+getResources().getString(R.string.google_direction_api);
            Log.e("LINK",requestUrl);
            mService.getPath(requestUrl).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    try {
                        JSONObject jsonObject=new JSONObject(response.body().toString());
                        JSONArray routes=jsonObject.getJSONArray("routes");
                        JSONObject object=routes.getJSONObject(0);
                        JSONArray legs=object.getJSONArray("legs");

                        JSONObject legsObject=legs.getJSONObject(0);
                        JSONObject distance=legsObject.getJSONObject("distance");
                        String distance_text=distance.getString("text");

                        Double distance_value=Double.parseDouble(distance_text.replaceAll("[^0-9\\\\.]+",""));

                        JSONObject time=legsObject.getJSONObject("duration");
                        String time_text=time.getString("text");

                        Integer time_value=Integer.parseInt(time_text.replaceAll("\\D+",""));

                        String final_calculate=String.format("%s + %s=$%.2f",distance_text,time_text,Common.getPrice(distance_value,time_value));

                        txtCalculate.setText(final_calculate);

                        if(isTapOnMap)
                        {
                            String start_address=legsObject.getString("start_address");
                            String end_address=legsObject.getString("end_address");

                            txtLocation.setText(start_address);
                            txtDestination.setText(end_address);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                Log.e("ERROR",t.getMessage());

                }
            });

        }
        catch(Exception e)
        {

        }

    }
}
