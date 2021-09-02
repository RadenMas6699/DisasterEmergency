package com.radenmas.disaster_emergency.ui.admin.submain;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.radenmas.disaster_emergency.R;
import com.radenmas.disaster_emergency.adapter.DataRecycler;
import com.radenmas.disaster_emergency.model.FirebaseViewHolder;

public class PanduanFragment extends Fragment {
    private ImageView imgAddPanduan;
    private RecyclerView rvPanduan;
    private DatabaseReference dbReff;
    private FirebaseRecyclerOptions<DataRecycler> options;
    private FirebaseRecyclerAdapter<DataRecycler, FirebaseViewHolder> adapter;

    public PanduanFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag_admin_panduan, container, false);

        dbReff = FirebaseDatabase.getInstance().getReference().child("Panduan");

        initView(view);
        onClick();

        rvPanduan.setHasFixedSize(true);
        rvPanduan.setLayoutManager(new LinearLayoutManager(getContext()));

        options = new FirebaseRecyclerOptions.Builder<DataRecycler>().setQuery(dbReff, DataRecycler.class).build();

        adapter = new FirebaseRecyclerAdapter<DataRecycler, FirebaseViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseViewHolder holder, int position, @NonNull DataRecycler model) {
                holder.titlePanduan.setText(model.getTitle());
                holder.imagesPanduan.setOnClickListener(view1 -> Toast.makeText(getContext(), "Hapus " + model.getTitle() + " ?", Toast.LENGTH_SHORT).show());
            }

            @NonNull
            @Override
            public FirebaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                return new FirebaseViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.list_panduan, parent, false));
            }
        };

        rvPanduan.setAdapter(adapter);

        return view;
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
        imgAddPanduan.setOnClickListener(view -> {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_submain, new UploadPanduanFragment()).commit();
        });
    }

    private void initView(View view) {
        imgAddPanduan = view.findViewById(R.id.img_add_panduan);
        rvPanduan = view.findViewById(R.id.rv_panduan);
    }
}