package com.radenmas.disaster_emergency.ui.admin.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.radenmas.disaster_emergency.CircleTransform;
import com.radenmas.disaster_emergency.R;
import com.radenmas.disaster_emergency.adapter.DataRecycler;
import com.radenmas.disaster_emergency.model.FirebaseViewHolder;
import com.radenmas.disaster_emergency.ui.admin.submain.AdminSubmainActivity;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class AdminMainActivity extends AppCompatActivity {
    private DatabaseReference dbReff;
    private FirebaseRecyclerOptions<DataRecycler> options;
    private FirebaseRecyclerAdapter<DataRecycler, FirebaseViewHolder> adapter;

    private TextView tvChat, tvArtikel, tvPanduan, tvValueNotif, tvValueLaporan;
    private RecyclerView rvArtikel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_admin_main);

        dbReff = FirebaseDatabase.getInstance().getReference().child("Artikel");

        initView();
        onClick();

        rvArtikel.setHasFixedSize(true);
        rvArtikel.setLayoutManager(new LinearLayoutManager(AdminMainActivity.this));

        options = new FirebaseRecyclerOptions.Builder<DataRecycler>().setQuery(dbReff, DataRecycler.class).build();

        adapter = new FirebaseRecyclerAdapter<DataRecycler, FirebaseViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseViewHolder holder, int position, @NonNull DataRecycler model) {
                holder.titleArtikel.setText(model.getCategory());
                holder.descArtikel.setText(model.getIsi());
                Picasso.get().load(model.getImages()).transform(new CircleTransform()).into(holder.imagesArtikel);
            }

            @NonNull
            @Override
            public FirebaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                return new FirebaseViewHolder(LayoutInflater.from(AdminMainActivity.this).inflate(R.layout.list_artikel, parent, false));
            }
        };

        rvArtikel.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
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