package com.radenmas.disaster_emergency.ui.user;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.radenmas.disaster_emergency.R;
import com.radenmas.disaster_emergency.ui.user.submain.ActiveFragment;
import com.radenmas.disaster_emergency.ui.user.submain.ConfirmFragment;
import com.radenmas.disaster_emergency.ui.admin.submain.MapsFragment;
import com.radenmas.disaster_emergency.ui.admin.submain.PanduanFragment;
import com.radenmas.disaster_emergency.ui.user.submain.PanicFragment;

public class UserSubmainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_submain);

        String submain = getIntent().getExtras().getString("submain");

        Fragment contentSubmain = null;

        switch (submain) {
//            case "confirm":
//                contentSubmain = new ConfirmFragment();
//                break;
            case "panduan":
                contentSubmain = new PanduanFragment();
                break;
            case "panic":
                contentSubmain = new PanicFragment();
                break;
            case "maps":
                contentSubmain = new MapsFragment();
                break;
            case "active":
                contentSubmain = new ActiveFragment();
                break;

        }
        getSupportFragmentManager().beginTransaction().replace(R.id.content_submain, contentSubmain).commit();
    }
}