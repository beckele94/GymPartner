package com.dwm.juicymuscle.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.dwm.juicymuscle.R;
import com.dwm.juicymuscle.model.Exercice;
import com.dwm.juicymuscle.model.ExoPgrm;
import com.dwm.juicymuscle.model.PutData;
import com.dwm.juicymuscle.service.ServiceApi;

import java.io.IOException;
import java.util.ArrayList;

public class AdapterListExercices extends RecyclerView.Adapter<AdapterListExercices.ViewHolder>{
    private ArrayList<Exercice> dataset;

    public static final String SHARED_PREFS = "shared_prefs";
    public static final String IDPGRM_KEY = "idpgrm_key";
    public SharedPreferences sharedpreferences;
    private String idPgrm;
    private Context context;
    private RecyclerView.Adapter adapterListeExoPgrm;
    private ArrayList<ExoPgrm> listeExoPgrm;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nom;
        public Button ajouter;

        public ViewHolder(View v) {
            super(v);
            nom = (TextView) v.findViewById(R.id.itemlistexercice_textview_nom);
            ajouter = (Button) v.findViewById(R.id.itemlistexercice_button_add);
        }
    }

    public AdapterListExercices(ArrayList<Exercice> myDataset, Context context, RecyclerView.Adapter adapterListeExoPgrm, ArrayList<ExoPgrm> listeExoPgrm){
        dataset = myDataset;
        this.context = context;
        this.adapterListeExoPgrm = adapterListeExoPgrm;
        this.listeExoPgrm = listeExoPgrm;
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

        int pos = position;
        sharedpreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        idPgrm = sharedpreferences.getString(IDPGRM_KEY, null);

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
                        data[0] = idPgrm;
                        field[1] = "idExo";
                        data[1] = dataset.get(holder.getAdapterPosition()).getId();
                        PutData putData = new PutData("https://ulysseguillot.fr/apiLoginJuicyMuscle/addExoPgrm.php", "POST", field, data);

                        if (putData.startPut()) {
                            if (putData.onComplete()) {
                                String result = putData.getResult(); //tester le resultat pour savoir si tout s'est bien passé
                                listeExoPgrm.add(new ExoPgrm("0", idPgrm, dataset.get(pos).getId(), "0", "0", "0", "0"));
                                adapterListeExoPgrm.notifyDataSetChanged();
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
