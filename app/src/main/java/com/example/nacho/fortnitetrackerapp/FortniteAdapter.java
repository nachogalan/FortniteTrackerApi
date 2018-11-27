package com.example.nacho.fortnitetrackerapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import com.example.nacho.fortnitetrackerapp.DTO.FinalStats;
import java.util.List;

public class FortniteAdapter extends RecyclerView.Adapter<FortniteAdapter.FortniteViewHolder> {

    public List<FinalStats> dataList;



    public FortniteAdapter(List<FinalStats> dataList) {
        this.dataList = dataList;

    }

    @NonNull
    @Override
    public FortniteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.card_model, viewGroup, false);
        return new FortniteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FortniteViewHolder fortniteViewHolder, int i) {
        final FinalStats fnStats = dataList.get(i);
        putDataInAdapter(fortniteViewHolder, fnStats);
    }
    private void putDataInAdapter(FortniteViewHolder fortniteViewHolder, FinalStats item) {
        fortniteViewHolder.txtLabel.setText(item.getLabel());
        fortniteViewHolder.txtRank.setText(item.getRank());
        fortniteViewHolder.txtDisplayValue.setText(item.getDisplayValue());

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }    public class FortniteViewHolder extends RecyclerView.ViewHolder {



        private TextView txtLabel, txtRank, txtDisplayValue;

        public FortniteViewHolder(View itemView) {
            super(itemView);

            txtLabel = itemView.findViewById(R.id.tv_label);
            txtRank = itemView.findViewById(R.id.tv_rank);
            txtDisplayValue = itemView.findViewById(R.id.tv_displayValue);
        }

    }
}
