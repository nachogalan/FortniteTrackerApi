package com.example.nacho.fortnitetrackerapp.ViewModel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;
import android.widget.Toast;

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
    public MutableLiveData<List<FinalStats>> playerDetailsMutableLiveData = new MutableLiveData<>();
    public List<FinalStats> dataList = new ArrayList<>();

    public void getData(String platform, String epic_name) {

        ftRepository.getPlayer(platform, epic_name)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Player>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Player player) {
                        if(player!=null) {
                            dataList.clear();

                            try {
                                PlayerStatsDetail data = player.getStats().getPlayerStatsDetail();

                                dataList.add(data.getScore());
                                dataList.add(data.getTrnRating());
                                dataList.add(data.getMatches());
                                dataList.add(data.getKd());
                                playerDetailsMutableLiveData.postValue(dataList);
                            }catch (Exception e){
                                Log.d("Error ",e.getMessage());
                            }


                        }
                    }


                    @Override
                    public void onError(Throwable e) {
                        Log.d("Failed ",e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}

