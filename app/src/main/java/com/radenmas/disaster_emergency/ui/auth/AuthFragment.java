package com.radenmas.disaster_emergency.ui.auth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.button.MaterialButton;
import com.radenmas.disaster_emergency.R;

public class AuthFragment extends Fragment {

    private MaterialButton btnLogin;
    private TextView tvRegister;

    public AuthFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag_auth, container, false);

        btnLogin = view.findViewById(R.id.btn_login);
        tvRegister = view.findViewById(R.id.tv_register);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();


        btnLogin.setOnClickListener(view1 -> {
            AuthLoginFragment fragment = new AuthLoginFragment();
            ft.replace(R.id.content_auth, fragment);
            ft.commit();
        });

        tvRegister.setOnClickListener(view2 -> {
            AuthRegisterFragment fragment = new AuthRegisterFragment();
            ft.replace(R.id.content_auth, fragment);
            ft.commit();
        });

        return view;
    }
}