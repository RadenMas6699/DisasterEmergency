package com.radenmas.disaster_emergency.ui.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.radenmas.disaster_emergency.R;
import com.radenmas.disaster_emergency.ui.admin.main.AdminMainActivity;
import com.radenmas.disaster_emergency.ui.user.UserMainActivity;

public class AuthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_auth);

//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("message");
//        myRef.setValue("AAAAAAA");

        FirebaseAuth fAuth = FirebaseAuth.getInstance();
        if (fAuth.getCurrentUser() != null) {
            startActivity(new Intent(AuthActivity.this, AdminMainActivity.class));
            finish();
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_auth, new AuthFragment()).commit();
        }
    }
}