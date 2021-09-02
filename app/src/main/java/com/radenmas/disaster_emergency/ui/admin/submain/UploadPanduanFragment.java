package com.radenmas.disaster_emergency.ui.admin.submain;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.radenmas.disaster_emergency.R;
import com.radenmas.disaster_emergency.ui.auth.AuthLoginFragment;

import java.util.HashMap;
import java.util.Map;

public class UploadPanduanFragment extends Fragment {
    private EditText etTitlePanduan, etIsiPanduan;
    private RelativeLayout imgUploadPanduan;
    private String strTitle, strCategory, strIsi;
    private DatabaseReference dbReff;


    public UploadPanduanFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag_admin_upload_panduan, container, false);

        dbReff = FirebaseDatabase.getInstance().getReference();

        initView(view);
        onClick();

        return view;
    }

    private void onClick() {
        imgUploadPanduan.setOnClickListener(view -> {
            strTitle = etTitlePanduan.getText().toString().trim();
            strIsi = etIsiPanduan.getText().toString().trim();

            String uid = dbReff.push().getKey();

            Map<String, Object> dataPanduan = new HashMap<>();
            dataPanduan.put("title", strTitle);
            dataPanduan.put("isi", strIsi);
            dataPanduan.put("uid", uid);

            dbReff.child("Panduan").child(uid).setValue(dataPanduan).addOnSuccessListener(unused -> {
                Toast.makeText(getContext(), "Upload Panduan Success", Toast.LENGTH_SHORT).show();
                etTitlePanduan.setText("");
                etIsiPanduan.setText("");
            });
        });
    }

    private void initView(View view) {
        etTitlePanduan = view.findViewById(R.id.et_title_panduan);
        etIsiPanduan = view.findViewById(R.id.et_isi_panduan);
        imgUploadPanduan = view.findViewById(R.id.img_upload_panduan);
    }
}