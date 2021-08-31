package com.radenmas.disaster_emergency.ui.admin.submain;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.radenmas.disaster_emergency.R;
import com.radenmas.disaster_emergency.ui.auth.AuthLoginFragment;

import java.util.HashMap;
import java.util.Map;

public class UploadArtikelFragment extends Fragment {
    private ImageView imgArtikel, imgUpload;
    private EditText etTitleArtikel, etCategoryArtikel, etIsiArtikel;
    private String strTitle, strCategory, strIsi;
    private DatabaseReference dbReff;
    private FirebaseAuth auth;
    private FirebaseUser user;

    public UploadArtikelFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag_admin_upload_artikel, container, false);

        dbReff = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();

        initView(view);
        onClick();

        return view;
    }

    private void onClick() {
        imgArtikel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

        imgUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strTitle = etTitleArtikel.getText().toString().trim();
                strCategory = etCategoryArtikel.getText().toString().trim();
                strIsi = etIsiArtikel.getText().toString().trim();

                user = FirebaseAuth.getInstance().getCurrentUser();
                String uid = user.getUid();

                Map<String, Object> dataArtikel = new HashMap<>();
                dataArtikel.put("email", strTitle);
                dataArtikel.put("password", strCategory);
                dataArtikel.put("phone", strIsi);
                dataArtikel.put("img", "");
                dataArtikel.put("id", uid);

                dbReff.child("Artikel").child(uid).setValue(dataArtikel).addOnSuccessListener(unused -> {
                    Toast.makeText(getContext(), "Artikel Berhasil Diupload", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(e -> Toast.makeText(getContext(), "Artikel Berhasil Diupload", Toast.LENGTH_SHORT).show());
            }
        });
    }

    private void initView(View view) {
        etTitleArtikel = view.findViewById(R.id.et_title_artikel);
        etCategoryArtikel = view.findViewById(R.id.et_category_artikel);
        etIsiArtikel = view.findViewById(R.id.et_isi_artikel);
        imgArtikel = view.findViewById(R.id.img_artikel);
        imgUpload = view.findViewById(R.id.img_upload);
    }
}