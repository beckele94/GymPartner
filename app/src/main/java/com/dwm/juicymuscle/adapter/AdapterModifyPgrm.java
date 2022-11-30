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
import com.dwm.juicymuscle.model.ExoPgrm;
import com.dwm.juicymuscle.model.PutData;
import com.dwm.juicymuscle.service.ServiceApi;

import java.io.IOException;
import java.util.ArrayList;

public class AdapterModifyPgrm extends RecyclerView.Adapter<AdapterModifyPgrm.ViewHolder>{
    private ArrayList<ExoPgrm> listExoPgrm;
    private ArrayList<Exercice> referenceExos;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nom;
        public Button del;
        public Button up;
        public Button down;


        public ViewHolder(View v) {
            super(v);
            nom = (TextView) v.findViewById(R.id.itemmodifypgrm_textview_nom);
            del = (Button) v.findViewById(R.id.itemmodifypgrm_button_del);
            up = (Button) v.findViewById(R.id.itemmodifypgrm_button_up);
            down = (Button) v.findViewById(R.id.itemmodifypgrm_button_down);
        }
    }

    public AdapterModifyPgrm(ArrayList<ExoPgrm> myDataset, ArrayList<Exercice> myReferenceDataset){
        listExoPgrm = myDataset;
        referenceExos = myReferenceDataset;
    }

    @Override
    public AdapterModifyPgrm.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout._item_list_modify_pgrm, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        String idExo = listExoPgrm.get(position).getIdExo();
        for(Exercice exercice : referenceExos) {
            if (exercice.getId().equals(idExo)) {
                holder.nom.setText(exercice.getNom());
            }
        }
    }

    @Override
    public int getItemCount() {
        return listExoPgrm.size();
    }

    /*public void updateData(ArrayList<ExoPgrm> viewModels) {
        listExoPgrm.clear();
        listExoPgrm.addAll(viewModels);
        notifyDataSetChanged();
    }*/
}
