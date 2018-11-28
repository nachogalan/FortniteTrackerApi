package com.example.nacho.fortnitetrackerapp.Repository;

import com.example.nacho.fortnitetrackerapp.DTO.Player;
import com.example.nacho.fortnitetrackerapp.Services.FortniteTrackerApiService;
import io.reactivex.Observable;


public class FTRepository {

    private static volatile FTRepository ourInstance = new FTRepository();

   public static FTRepository getInstance() {
        if (ourInstance == null) {
            synchronized (FTRepository.class) {
                if (ourInstance == null) {
                    ourInstance = new FTRepository();
                }
            }
        }
        return ourInstance;
    }

    FTRepository() {
    }

    private FortniteTrackerApiService fortniteApi =FortniteTrackerApiService.create();

    public Observable<Player> getPlayer(String platform, String epic_name){
        return fortniteApi.loadPlayer(platform,epic_name);
    }



}
