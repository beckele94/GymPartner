package com.dwm.juicymuscle.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.dwm.juicymuscle.R;
import com.dwm.juicymuscle.model.Exercice;

import java.util.ArrayList;

public class AdapterListExercices extends RecyclerView.Adapter<AdapterListExercices.ViewHolder>{
    private ArrayList<Exercice> dataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nom;
        //TODO: supprimer cet attribut si bien inutile
        //public Button ajouter;

        public ViewHolder(View v) {
            super(v);
            nom = (TextView) v.findViewById(R.id.itemlistexercice_textview_nom);
            //ajouter = (Button) v.findViewById(R.id.itemlistexercice_button_add);
        }
    }

    public AdapterListExercices(ArrayList<Exercice> myDataset){
        dataset = myDataset;
    }

    @Override
    public AdapterListExercices.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout._item_list_erxercices, parent, false);
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
