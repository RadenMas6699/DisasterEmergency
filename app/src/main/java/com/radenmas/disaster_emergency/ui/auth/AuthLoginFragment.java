package com.radenmas.disaster_emergency.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.button.MaterialButton;
import com.radenmas.disaster_emergency.R;
import com.radenmas.disaster_emergency.ui.user.UserMainActivity;

public class AuthLoginFragment extends Fragment {

    private MaterialButton btnLogin;
    private TextView tvRegister;
    private EditText etEmail, etPassword;

    public AuthLoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag_auth_login, container, false);

        etEmail = view.findViewById(R.id.et_email);
        etPassword = view.findViewById(R.id.et_password);
        btnLogin = view.findViewById(R.id.btn_login);
        tvRegister = view.findViewById(R.id.tv_register);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();


        btnLogin.setOnClickListener(view1 -> {
            startActivity(new Intent(getContext(), UserMainActivity.class));
            getActivity().finish();
        });

        tvRegister.setOnClickListener(view2 -> {
            AuthRegisterFragment fragment = new AuthRegisterFragment();
            ft.replace(R.id.content_auth, fragment);
            ft.commit();
        });

        return view;
    }
}