package com.example.nacho.fortnitetrackerapp.Services;

import com.example.nacho.fortnitetrackerapp.DTO.Player;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;


public interface FortniteTrackerApiService {
    @Headers("TRN-Api-Key: cb5a8b17-d6bd-42d7-a8b7-1aefe2694de5")
    @GET("/v1/profile/{platform}/{epic-nickname}")
    Observable<Player> loadPlayer(@Path("platform") String platform, @Path("epic-nickname") String epic_name);



    static  FortniteTrackerApiService create(){
        /**Gson gsonBuilder = new GsonBuilder()
                .setLenient()
                .create();**/

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.fortnitetracker.com")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return  retrofit.create(FortniteTrackerApiService.class);
    }

}
