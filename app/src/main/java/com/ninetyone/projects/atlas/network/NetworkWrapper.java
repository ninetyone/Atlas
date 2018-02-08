package com.ninetyone.projects.atlas.network;

import com.ninetyone.projects.atlas.BuildConfig;
import com.ninetyone.projects.atlas.NationApp;
import com.ninetyone.projects.atlas.mvp.BaseView;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.ninetyone.projects.atlas.NationApp.BASE_URL;

/**
 * Created by Ninetyone on 19/11/17.
 */

public class NetworkWrapper {
    private static NetworkWrapper instance;
    private Retrofit retrofit;

    private NetworkWrapper() {
        // private constructor to avoid direct calls to this singleton
    }

    public static NetworkWrapper getInstance() {
        if (instance == null) {
            instance = new NetworkWrapper();
        }

        return instance;
    }

    public static void addInterceptors(final OkHttpClient.Builder httpClient) {
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(interceptor);
        }
    }

    public NationNetworkApi getClient() {
        retrofit = getRetrofit();
        return retrofit.create(NationNetworkApi.class);
    }

    public Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = initRetrofit();
        }
        return retrofit;
    }

    private Retrofit initRetrofit() {
        OkHttpClient.Builder httpClient = NationApp.getInstance().getOkhttpClient().newBuilder().build().newBuilder();
        addInterceptors(httpClient);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        return retrofit;
    }

    public <T> void enqueue(Call<T> call, final BaseView baseView, final NetworkCallback networkCallback) {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, retrofit2.Response<T> response) {
                if (!response.isSuccessful() && baseView != null) {
                    try {
                        String json = response.errorBody().string();
                        response = retrofit2.Response.error(response.code(), response.raw().newBuilder().body(ResponseBody.create(response.raw().body().contentType(), json)).build().body());
                    } catch (JsonSyntaxException | IOException e) {
                        e.printStackTrace();
                    }
                }
                networkCallback.onResponse(call, response);
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                t.printStackTrace();
                networkCallback.onFailure(call, t);
            }
        });
    }

    public interface NetworkCallback<T> {
        void onResponse(Call<T> call, retrofit2.Response<T> response);

        void onFailure(Call<T> call, Throwable t);
    }
}
