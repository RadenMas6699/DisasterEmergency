package com.radenmas.disaster_emergency.ui.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.radenmas.disaster_emergency.R;
import com.radenmas.disaster_emergency.ui.user.UserMainActivity;

public class AuthActivity extends AppCompatActivity {

    private FrameLayout contentAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_auth);

        FirebaseAuth fAuth = FirebaseAuth.getInstance();
        if (fAuth.getCurrentUser() != null) {
            startActivity(new Intent(AuthActivity.this, UserMainActivity.class));
            finish();
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_auth, new AuthFragment()).commit();
        }
    }
}