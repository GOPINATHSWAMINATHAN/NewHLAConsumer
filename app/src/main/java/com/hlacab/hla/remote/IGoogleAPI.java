package com.hlacab.hla.remote;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by gopinath on 10/12/17.
 */

public interface IGoogleAPI {

    @GET
    Call<String> getPath(@Url String url);

}
