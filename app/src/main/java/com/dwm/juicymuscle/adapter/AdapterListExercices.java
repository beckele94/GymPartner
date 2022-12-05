package com.dwm.juicymuscle.adapter;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.dwm.juicymuscle.R;
import com.dwm.juicymuscle.model.Exercice;
import com.dwm.juicymuscle.model.PutData;
import com.dwm.juicymuscle.service.ServiceApi;

import java.io.IOException;
import java.util.ArrayList;

public class AdapterListExercices extends RecyclerView.Adapter<AdapterListExercices.ViewHolder>{
    private ArrayList<Exercice> dataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nom;
        public Button ajouter;

        public ViewHolder(View v) {
            super(v);
            nom = (TextView) v.findViewById(R.id.itemlistexercice_textview_nom);
            ajouter = (Button) v.findViewById(R.id.itemlistexercice_button_add);
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

        holder.ajouter.setOnClickListener(new View.OnClickListener() { //ajout de l'exercice lors de l'appui sur "+"
            @Override
            public void onClick(View v) {
                Handler handler = new Handler(); //recuperation des exercices et appel du recycler
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        String[] field = new String[2];
                        String[] data = new String[2];
                        field[0] = "idPgrm";
                        data[0] = "5"; //TODO : recuper la valeur de l'idPgrm a partir de la selection de l'entrainement
                        field[1] = "idExo";
                        data[1] = dataset.get(holder.getAdapterPosition()).getId();
                        PutData putData = new PutData("https://ulysseguillot.fr/apiLoginJuicyMuscle/addExoPgrm.php", "POST", field, data);

                        if (putData.startPut()) {
                            if (putData.onComplete()) {
                                String result = putData.getResult(); //tester le resultat pour savoir si tout s'est bien passé
                            }
                        }
                    }
                });
            }
        });
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
