package com.example.aperobox.PanierLayout;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.aperobox.Model.Box;
import com.example.aperobox.R;

public class PanierViewHolder extends RecyclerView.ViewHolder {

    public TextView panierBoxNomTextView;

    PanierViewHolder(@NonNull View itemView){
        super(itemView);
        panierBoxNomTextView = itemView.findViewById(R.id.panier_fragment_nom_text_view);
    }

    public void bind(Box box, Integer quantite){
        panierBoxNomTextView.setHint(box.getNom());
    }
}