package com.example.nacho.fortnitetrackerapp;

import android.arch.lifecycle.ViewModelProviders;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;
import android.widget.Spinner;
import com.example.nacho.fortnitetrackerapp.DTO.FinalStats;
import com.example.nacho.fortnitetrackerapp.ViewModel.FortniteViewModel;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private String epic_name;
    private String platform;
    private FortniteAdapter adapter;
    private RecyclerView recyclerView;
    private Spinner spinner;
    private EditText etPlayer;
    private FloatingActionButton btnBuscar;
    private RecyclerView.LayoutManager layoutManager;
    private FortniteViewModel fortniteVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findByView();
        changeData("","");
        onClickBtn();

    }

    public void onClickBtn() {
        btnBuscar.setOnClickListener(v -> {
            epic_name = etPlayer.getText().toString();
            platform = spinner.getSelectedItem().toString();
            fortniteVM.getData(platform,epic_name);
        });
    }


    public void findByView() {

        spinner = findViewById(R.id.spinner_platform);
        etPlayer = findViewById(R.id.et_playerName_txt);
        btnBuscar = findViewById(R.id.btn_buscar);

    }

    private void changeData(String platform,String epic_name){
        fortniteVM = ViewModelProviders.of(this).get(FortniteViewModel.class);
        fortniteVM.playerDetailsMutableLiveData.observe(this, stadisticObjectData -> {
            if(stadisticObjectData!=null){
                Log.d("Servicio Fortnite","Cambios: "+stadisticObjectData);
                generateForniteList(stadisticObjectData);
            }
        });
        fortniteVM.getData(platform, epic_name);
    }

    private void generateForniteList(List<FinalStats> listFornite) {
        recyclerView = findViewById(R.id.my_recycler_view);
        adapter = new FortniteAdapter(listFornite);
        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
