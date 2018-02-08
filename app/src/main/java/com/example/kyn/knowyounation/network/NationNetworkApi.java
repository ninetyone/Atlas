package com.example.kyn.knowyounation.network;

import com.example.kyn.knowyounation.models.NationData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Ninetyone on 19/11/17.
 */

public interface NationNetworkApi {

    @GET("rest/v2/all")
    Call<ArrayList<NationData>> fetchNationData();
}
