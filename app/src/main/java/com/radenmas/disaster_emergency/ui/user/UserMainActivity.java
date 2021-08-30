package com.radenmas.disaster_emergency.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.radenmas.disaster_emergency.R;

public class UserMainActivity extends AppCompatActivity {

    private FrameLayout contentAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_user_main);


    }
}