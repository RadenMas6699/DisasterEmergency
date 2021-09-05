package com.radenmas.disaster_emergency.ui.user.submain;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.radenmas.disaster_emergency.R;

public class OpenPanduanFragment extends Fragment {
    private TextView tvTitle, tvIsi;
    private String strUid;
    private DatabaseReference dbReff;

    public OpenPanduanFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag_open_panduan, container, false);

        // get String from Fragment
        Bundle getBundle = this.getArguments();
        if (getBundle != null) {
            strUid = getBundle.getString("uid");
        }

        dbReff = FirebaseDatabase.getInstance().getReference().child("Panduan").child(strUid);

        initView(view);
        onClick();

        dbReff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String isi = snapshot.child("isi").getValue().toString();
                String title = snapshot.child("title").getValue().toString();

                tvTitle.setText(title);
                tvIsi.setText(isi);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }

    private void onClick() {
    }

    private void initView(View view) {
        tvTitle = view.findViewById(R.id.title_fragment);
        tvIsi = view.findViewById(R.id.tv_isi_panduan);
        tvIsi.setMovementMethod(new ScrollingMovementMethod());
    }
}