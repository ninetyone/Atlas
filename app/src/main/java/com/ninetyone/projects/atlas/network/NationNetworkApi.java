package com.ninetyone.projects.atlas.network;

import com.ninetyone.projects.atlas.models.NationData;

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
