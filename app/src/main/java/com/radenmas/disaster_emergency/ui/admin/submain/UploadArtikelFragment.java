package com.radenmas.disaster_emergency.ui.admin.submain;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.radenmas.disaster_emergency.R;

import java.util.HashMap;
import java.util.Map;

public class UploadArtikelFragment extends Fragment {
    private ImageView imgArtikel, imgUpload, btn_back;
    private EditText etTitleArtikel, etCategoryArtikel, etIsiArtikel;
    private String strTitle, strCategory, strIsi;
    private DatabaseReference dbReff;

    public UploadArtikelFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag_admin_upload_artikel, container, false);

        dbReff = FirebaseDatabase.getInstance().getReference();

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

        imgUpload.setOnClickListener(view -> {
            strTitle = etTitleArtikel.getText().toString().trim();
            strCategory = etCategoryArtikel.getText().toString().trim();
            strIsi = etIsiArtikel.getText().toString().trim();

            String uid = dbReff.push().getKey();

            Map<String, Object> dataArtikel = new HashMap<>();
            dataArtikel.put("title", strTitle);
            dataArtikel.put("category", strCategory);
            dataArtikel.put("isi", strIsi);
            dataArtikel.put("images", "https://firebasestorage.googleapis.com/v0/b/pkm-disaster-emergency.appspot.com/o/ic_app.png?alt=media&token=0ec4f1b2-a746-4e25-b220-f7bca6e28782");
            dataArtikel.put("uid", uid);

            dbReff.child("Artikel").child(uid).setValue(dataArtikel).addOnSuccessListener(unused -> {
                Toast.makeText(getContext(), "Artikel Berhasil Diupload", Toast.LENGTH_SHORT).show();
                etTitleArtikel.setText("");
                etCategoryArtikel.setText("");
                etIsiArtikel.setText("");
            }).addOnFailureListener(e -> Toast.makeText(getContext(), "Artikel Gagal Diupload", Toast.LENGTH_SHORT).show());
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