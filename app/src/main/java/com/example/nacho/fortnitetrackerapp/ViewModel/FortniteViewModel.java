package com.example.nacho.fortnitetrackerapp.ViewModel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.nacho.fortnitetrackerapp.DTO.FinalStats;
import com.example.nacho.fortnitetrackerapp.DTO.Player;
import com.example.nacho.fortnitetrackerapp.DTO.PlayerStatsDetail;
import com.example.nacho.fortnitetrackerapp.Repository.FTRepository;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FortniteViewModel extends ViewModel {

    private FTRepository ftRepository = FTRepository.getInstance();
    public List<FinalStats> finalDataList = new ArrayList<>();
    public MutableLiveData<List<FinalStats>> playerDetailsMutableLiveData = new MutableLiveData<>();


    public void getPlayerStats(String platform, String epic_name) {

        ftRepository.getPlayer(platform, epic_name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Player>() {


                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Player player) {
                        if(player!=null) {
                            finalDataList.clear();

                            try {

                                PlayerStatsDetail data = player.getStats().getPlayerStatsDetail();

                                finalDataList.add(data.getScore());
                                finalDataList.add(data.getKd());
                                finalDataList.add(data.getMatches());
                                finalDataList.add(data.getTrnRating());

                                playerDetailsMutableLiveData.postValue(finalDataList);
                            }catch (Exception e){
                                Log.d("Error ",e.getLocalizedMessage());
                            }


                        }
                    }


                    @Override
                    public void onError(Throwable e) {
                        Log.d("Failed ",e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {}
                });
    }
}

