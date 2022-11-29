package com.dwm.juicymuscle.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.dwm.juicymuscle.R;
import com.dwm.juicymuscle.model.Exercice;

import java.util.ArrayList;

public class AdapterListExoPgrm extends RecyclerView.Adapter<AdapterListExoPgrm.ViewHolder>{
    private ArrayList<Exercice> dataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nom;

        public ViewHolder(View v) {
            super(v);
            nom = (TextView) v.findViewById(R.id.itemlistexopgrm_textview_nom);
        }
    }

    public AdapterListExoPgrm(ArrayList<Exercice> myDataset){
        dataset = myDataset;
    }

    @Override
    public AdapterListExoPgrm.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout._item_list_exo_prgm, parent, false);
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

    public void updateData(ArrayList<Exercice> viewModels) {
        dataset.clear();
        dataset.addAll(viewModels);
        notifyDataSetChanged();
    }
}
