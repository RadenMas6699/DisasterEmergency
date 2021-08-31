package com.radenmas.disaster_emergency.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.radenmas.disaster_emergency.R;
import com.radenmas.disaster_emergency.ui.admin.submain.AdminSubmainActivity;

public class UserMainActivity extends AppCompatActivity {
    private TextView tvUserConfirm, tvUserPanduan, tvUserPanic, tvUserMaps, tvUserActivation;
    private RecyclerView rvArtikel;

    private FrameLayout contentAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_user_main);

        initView();
        onClick();

    }

    private void onClick() {
        tvUserConfirm.setOnClickListener(view -> {
            Intent subMain = new Intent(UserMainActivity.this, UserSubmainActivity.class);
            subMain.putExtra("submain", "confirm");
            startActivity(subMain);
        });

        tvUserPanduan.setOnClickListener(view -> {
            Intent subMain = new Intent(UserMainActivity.this, UserSubmainActivity.class);
            subMain.putExtra("submain", "panduan");
            startActivity(subMain);
        });

        tvUserPanic.setOnClickListener(view -> {
            Intent subMain = new Intent(UserMainActivity.this, UserSubmainActivity.class);
            subMain.putExtra("submain", "panic");
            startActivity(subMain);
        });

        tvUserMaps.setOnClickListener(view -> {
            Intent subMain = new Intent(UserMainActivity.this, UserSubmainActivity.class);
            subMain.putExtra("submain", "maps");
            startActivity(subMain);
        });

        tvUserActivation.setOnClickListener(view -> {
            Intent subMain = new Intent(UserMainActivity.this, UserSubmainActivity.class);
            subMain.putExtra("submain", "active");
            startActivity(subMain);
        });
    }

    private void initView() {
        tvUserConfirm = findViewById(R.id.tv_user_confirm);
        tvUserPanduan = findViewById(R.id.tv_user_panduan);
        tvUserPanic = findViewById(R.id.tv_user_panic);
        tvUserMaps = findViewById(R.id.tv_user_maps);
        tvUserActivation = findViewById(R.id.tv_user_activation);
        rvArtikel = findViewById(R.id.rv_user_artikel);
    }
}