package com.dwm.juicymuscle.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.dwm.juicymuscle.R;
import com.dwm.juicymuscle.controller.HomeActivity;
import com.dwm.juicymuscle.model.Exercice;
import com.dwm.juicymuscle.model.ExoPgrm;
import com.dwm.juicymuscle.model.Programme;

import java.util.ArrayList;

public class AdapterListProgrammes extends RecyclerView.Adapter<AdapterListProgrammes.ViewHolder>{
    private ArrayList<Programme> dataset;
    public Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nom;
        public ImageButton settingsButton;

        public ViewHolder(View v) {
            super(v);
            nom = (TextView) v.findViewById(R.id.itemlisteprogrammes_textview_nom);
            settingsButton = v.findViewById(R.id.itemlisteprogrammes_button_settings);
        }
    }

    public AdapterListProgrammes(ArrayList<Programme> myDataset, Context context){
        dataset = myDataset;
        this.context = context;
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

        holder.settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, holder.settingsButton);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu_home, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.home_menu_modifier:
                                //handle menu1 click
                                return true;
                            case R.id.home_menu_supprimer:
                                //handle menu2 click
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popupMenu.show();
            }
        });
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
