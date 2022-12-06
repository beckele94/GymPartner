package com.dwm.juicymuscle.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.dwm.juicymuscle.R;
import com.dwm.juicymuscle.model.Exercice;
import com.dwm.juicymuscle.model.ExoPgrm;
import com.dwm.juicymuscle.model.Programme;

import java.util.ArrayList;

public class AdapterListProgrammes extends RecyclerView.Adapter<AdapterListProgrammes.ViewHolder>{
    private ArrayList<Programme> dataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nom;

        public ViewHolder(View v) {
            super(v);
            nom = (TextView) v.findViewById(R.id.itemlisteprogrammes_textview_nom);
        }
    }

    public AdapterListProgrammes(ArrayList<Programme> myDataset){
        dataset = myDataset;
        Log.d("erreur", Integer.toString(dataset.size()));

    }

    @Override
    public AdapterListProgrammes.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout._item_list_programmes, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){


        holder.nom.setText(dataset.get(position).getNom());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void updateData(ArrayList<Programme> viewModels) {
        dataset.clear();
        dataset.addAll(viewModels);
        notifyDataSetChanged();
    }
}
