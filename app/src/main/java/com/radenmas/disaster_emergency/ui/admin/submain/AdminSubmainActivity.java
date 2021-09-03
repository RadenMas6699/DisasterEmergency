package com.radenmas.disaster_emergency.ui.admin.submain;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.radenmas.disaster_emergency.R;
import com.radenmas.disaster_emergency.ui.admin.AdminMapsFragment;
import com.radenmas.disaster_emergency.ui.user.submain.ConfirmFragment;
import com.radenmas.disaster_emergency.ui.user.submain.PanicFragment;

public class AdminSubmainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_submain);

        String submain = getIntent().getExtras().getString("submain");

        Fragment contentSubmain = null;

        switch (submain) {
            case "chat":
                contentSubmain = new AdminMapsFragment();
                break;
            case "artikel":
                contentSubmain = new UploadArtikelFragment();
                break;
            case "panduan":
                contentSubmain = new PanduanFragment();
                break;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.content_submain, contentSubmain).commit();
    }
}