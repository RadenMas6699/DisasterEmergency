package com.radenmas.disaster_emergency.ui.admin.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.radenmas.disaster_emergency.R;
import com.radenmas.disaster_emergency.ui.admin.submain.AdminSubmainActivity;
import com.radenmas.disaster_emergency.ui.user.UserMainActivity;
import com.radenmas.disaster_emergency.ui.user.UserSubmainActivity;

public class AdminMainActivity extends AppCompatActivity {

    private TextView tvChat, tvArtikel, tvPanduan, tvValueNotif, tvValueLaporan;
    private RecyclerView rvArtikel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_admin_main);

        initView();
        onClick();

    }

    private void onClick() {
        tvChat.setOnClickListener(view -> {
            Intent subMain = new Intent(AdminMainActivity.this, AdminSubmainActivity.class);
            subMain.putExtra("submain", "chat");
            startActivity(subMain);
        });

        tvArtikel.setOnClickListener(view -> {
            Intent subMain = new Intent(AdminMainActivity.this, AdminSubmainActivity.class);
            subMain.putExtra("submain", "artikel");
            startActivity(subMain);
        });

        tvPanduan.setOnClickListener(view -> {
            Intent subMain = new Intent(AdminMainActivity.this, AdminSubmainActivity.class);
            subMain.putExtra("submain", "panduan");
            startActivity(subMain);
        });

    }

    private void initView() {
        tvChat = findViewById(R.id.tv_admin_chat);
        tvArtikel = findViewById(R.id.tv_admin_artikel);
        tvPanduan = findViewById(R.id.tv_admin_panduan);
        tvValueNotif = findViewById(R.id.tv_value_notif);
        tvValueLaporan = findViewById(R.id.tv_value_laporan);
        rvArtikel = findViewById(R.id.rv_admin_artikel);
    }
}