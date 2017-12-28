package com.hlacab.hla.remote;

import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by gopinath on 10/12/17.
 */

public class GoogleMapAPI {
private static Retrofit retrofit=null;

public static Retrofit getClient(String baseURL)
{
    if(retrofit==null)
    {
        retrofit=new Retrofit.Builder().baseUrl(baseURL).addConverterFactory(ScalarsConverterFactory.create()).build();
    }
    return retrofit;
}

}
