package com.radenmas.disaster_emergency.model;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.radenmas.disaster_emergency.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class FirebaseViewHolder extends RecyclerView.ViewHolder {

    public final TextView titleArtikel, descArtikel, titlePanduan, titleProfil, descChat, timesChat;
    public final ImageView imagesArtikel, imagesPanduan, imagesProfil;
    public final RelativeLayout rlObrolan;

    public FirebaseViewHolder(@NonNull View itemView) {
        super(itemView);

        //Artikel
        titleArtikel = itemView.findViewById(R.id.titleArtikel);
        descArtikel = itemView.findViewById(R.id.descArtikel);
        imagesArtikel = itemView.findViewById(R.id.imagesArtikel);

        //Panduan
        titlePanduan = itemView.findViewById(R.id.titlePanduan);
        imagesPanduan = itemView.findViewById(R.id.imagesPanduan);

        //Chat
        rlObrolan = itemView.findViewById(R.id.rl_obrolan);
        imagesProfil = itemView.findViewById(R.id.imagesProfil);
        titleProfil = itemView.findViewById(R.id.titleProfil);
        descChat = itemView.findViewById(R.id.descChat);
        timesChat = itemView.findViewById(R.id.timesChat);
    }
}
