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

import java.util.ArrayList;

public class AdapterListExoPgrm extends RecyclerView.Adapter<AdapterListExoPgrm.ViewHolder>{
    private ArrayList<ExoPgrm> dataset;
    private ArrayList<Exercice> referenceDataset;
    private String detailExo;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nom;
        public TextView details;


        public ViewHolder(View v) {
            super(v);
            nom = (TextView) v.findViewById(R.id.itemlistexopgrm_textview_nom);
            details = (TextView) v.findViewById(R.id.itemlistexopgrm_textview_details);
        }
    }

    public AdapterListExoPgrm(ArrayList<ExoPgrm> myDataset, ArrayList<Exercice> myReferenceDataset){
        dataset = myDataset;
        referenceDataset = myReferenceDataset;
    }

    @Override
    public AdapterListExoPgrm.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout._item_list_exo_prgm, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){

        int i;
        String detailExo ="Série" + "     " + "Rep" + "     " + "     " + "Poids" + "            " + "Repos" + "\n";
        for (i=0; i < Integer.parseInt(dataset.get(position).getNbSerie()); i++){
            detailExo += i+1 + "               " + dataset.get(position).getNbRep() + "               " + dataset.get(position).getPoids() + "               " + dataset.get(position).getTempsRepos() + "\n";
            Log.d("test", dataset.get(position).getNbSerie());
        };
        Log.d("test1", dataset.get(position).getNbSerie());
        holder.details.setText(detailExo);

        /*holder.details.setText(dataset.get(position).getNbSerie());*/

        String idExo = dataset.get(position).getIdExo();
        for(Exercice exercice : referenceDataset) {
            if (exercice.getId().equals(idExo)) {
                holder.nom.setText(exercice.getNom());
            }
        }
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    /*public void updateData(ArrayList<ExoPgrm> viewModels) {
        dataset.clear();
        dataset.addAll(viewModels);
        notifyDataSetChanged();
    }*/
}
