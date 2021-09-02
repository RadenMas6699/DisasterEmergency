package com.radenmas.disaster_emergency.ui.admin.submain;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
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
import com.squareup.picasso.Picasso;

public class ObrolanFragment extends Fragment {
    private RecyclerView rvObrolan;
    private DatabaseReference dbReff;
    private FirebaseRecyclerOptions<DataRecycler> options;
    private FirebaseRecyclerAdapter<DataRecycler, FirebaseViewHolder> adapter;

    public ObrolanFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag_admin_obrolan, container, false);
        dbReff = FirebaseDatabase.getInstance().getReference().child("Artikel");

        initView(view);
        onClick();

        rvObrolan.setHasFixedSize(true);
        rvObrolan.setLayoutManager(new LinearLayoutManager(getContext()));

        options = new FirebaseRecyclerOptions.Builder<DataRecycler>().setQuery(dbReff, DataRecycler.class).build();

        adapter = new FirebaseRecyclerAdapter<DataRecycler, FirebaseViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseViewHolder holder, int position, @NonNull DataRecycler model) {
                Picasso.get().load(model.getImages()).transform(new CircleTransform()).into(holder.imagesProfil);
                holder.titleProfil.setText(model.getTitle());
                holder.descChat.setText(model.getIsi());
                holder.timesChat.setText("12:32 PM");
                holder.rlObrolan.setOnClickListener(view1 -> {
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_submain, new ChatFragment()).commit();
                });
            }

            @NonNull
            @Override
            public FirebaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                return new FirebaseViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.list_chat, parent, false));
            }
        };

        rvObrolan.setAdapter(adapter);

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

    }

    private void initView(View view) {
        rvObrolan = view.findViewById(R.id.rv_obrolan);
    }
}