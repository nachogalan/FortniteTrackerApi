package com.example.nacho.fortnitetrackerapp;

import android.arch.lifecycle.ViewModelProviders;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.Spinner;
import com.example.nacho.fortnitetrackerapp.DTO.FinalStats;
import com.example.nacho.fortnitetrackerapp.ViewModel.FortniteViewModel;

import java.util.List;


public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;

    private EditText etPlayer;
    private Spinner spinner;
    private FloatingActionButton btnBuscar;

    private FortniteViewModel fortniteVM;
    private String epic_name;
    private String platform;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findByView();
        onClickBtn();
        otherPlayer();

    }

    public void onClickBtn() {
        btnBuscar.setOnClickListener(v -> {
            epic_name = etPlayer.getText().toString();
            platform = spinner.getSelectedItem().toString();
            fortniteVM.getPlayerStats(platform,epic_name);
        });
    }


    public void findByView() {
        recyclerView = findViewById(R.id.my_recycler_view);
        spinner = findViewById(R.id.spinner_platform);
        etPlayer = findViewById(R.id.et_playerName_txt);
        btnBuscar = findViewById(R.id.btn_buscar);


    }

    private void otherPlayer(){
        fortniteVM = ViewModelProviders.of(this).get(FortniteViewModel.class);
        fortniteVM.playerDetailsMutableLiveData.observe(this, stadisticObjectData -> {
            if(stadisticObjectData!=null){
                generateForniteList(stadisticObjectData);
            }
        });
    }

    private void generateForniteList(List<FinalStats> listFornite) {

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        FortniteAdapter adapter = new FortniteAdapter(listFornite);
        recyclerView.setAdapter(adapter);
    }
}
