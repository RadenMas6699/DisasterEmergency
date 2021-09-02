package com.radenmas.disaster_emergency.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.radenmas.disaster_emergency.CircleTransform;
import com.radenmas.disaster_emergency.R;
import com.radenmas.disaster_emergency.adapter.DataRecycler;
import com.radenmas.disaster_emergency.model.FirebaseViewHolder;
import com.radenmas.disaster_emergency.ui.admin.main.AdminMainActivity;
import com.radenmas.disaster_emergency.ui.admin.submain.AdminSubmainActivity;
import com.squareup.picasso.Picasso;

public class UserMainActivity extends AppCompatActivity {
    private DatabaseReference dbReff;
    private FirebaseRecyclerOptions<DataRecycler> options;
    private FirebaseRecyclerAdapter<DataRecycler, FirebaseViewHolder> adapter;

    private TextView tvNameUser, tvRoleUser, tvWelcomeUser,
            tvUserConfirm, tvUserPanduan, tvUserPanic, tvUserMaps, tvUserActivation;
    private RecyclerView rvArtikel;
    String username = "Raden Mas Dev";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_user_main);

        dbReff = FirebaseDatabase.getInstance().getReference().child("Artikel");

        initView();
        onClick();

        rvArtikel.setHasFixedSize(true);
        rvArtikel.setLayoutManager(new LinearLayoutManager(UserMainActivity.this));

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

                return new FirebaseViewHolder(LayoutInflater.from(UserMainActivity.this).inflate(R.layout.list_artikel, parent, false));
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
        tvUserConfirm.setOnClickListener(view -> {
            Intent subMain = new Intent(UserMainActivity.this, PairBluetoothActivity.class);
//            subMain.putExtra("submain", "confirm");
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
        tvNameUser = findViewById(R.id.name_user);
        tvRoleUser = findViewById(R.id.role_user);
        tvWelcomeUser = findViewById(R.id.tv_welcome_user);
        tvUserConfirm = findViewById(R.id.tv_user_confirm);
        tvUserPanduan = findViewById(R.id.tv_user_panduan);
        tvUserPanic = findViewById(R.id.tv_user_panic);
        tvUserMaps = findViewById(R.id.tv_user_maps);
        tvUserActivation = findViewById(R.id.tv_user_activation);
        rvArtikel = findViewById(R.id.rv_user_artikel);

        tvNameUser.setText(username);
        tvRoleUser.setText("User");
        tvWelcomeUser.setText("Selamat Datang " + username + " di");
    }
}