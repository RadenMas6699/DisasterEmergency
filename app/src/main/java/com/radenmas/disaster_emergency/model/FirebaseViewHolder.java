package com.radenmas.disaster_emergency.model;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.radenmas.disaster_emergency.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class FirebaseViewHolder extends RecyclerView.ViewHolder {

    public final TextView tvTitle, tvDesc, tvTimestamp, titlePanduan;
    public final CircleImageView imgImages, imagesPanduan;

    public FirebaseViewHolder(@NonNull View itemView) {
        super(itemView);

        tvTitle = itemView.findViewById(R.id.title);
        tvDesc = itemView.findViewById(R.id.desc);
        imgImages = itemView.findViewById(R.id.images);
        tvTimestamp = itemView.findViewById(R.id.timestamp);

        titlePanduan = itemView.findViewById(R.id.title);
        imagesPanduan = itemView.findViewById(R.id.images);
    }
}
