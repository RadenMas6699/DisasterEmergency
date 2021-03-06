package com.radenmas.disaster_emergency.ui.user.submain;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.radenmas.disaster_emergency.R;
import com.radenmas.disaster_emergency.adapter.DataRecycler;
import com.radenmas.disaster_emergency.model.FirebaseViewHolder;

public class UserPanduanFragment extends Fragment {
    private RecyclerView rvPanduan;
    private DatabaseReference dbReff;
    private FirebaseRecyclerOptions<DataRecycler> options;
    private FirebaseRecyclerAdapter<DataRecycler, FirebaseViewHolder> adapter;

    public UserPanduanFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag_user_panduan, container, false);

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
                holder.imagesPanduan.setVisibility(View.INVISIBLE);
                holder.rlPanduan.setOnClickListener(view1 -> {
                    String uid = model.getUid();
                    Bundle bundle = new Bundle();
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    OpenPanduanFragment fragment = new OpenPanduanFragment();

                    bundle.putString("uid", uid);
                    fragment.setArguments(bundle);
                    ft.replace(R.id.content_submain, fragment);
                    ft.commit();
                });
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

    private void onClick() {
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

    private void initView(View view) {
        rvPanduan = view.findViewById(R.id.rv_panduan);
    }
}