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

public class AuthRegisterFragment extends Fragment {

    private MaterialButton btnRegister;
    private TextView tvLogin;
    private EditText etEmail, etPassword, etPhone, etDate;

    public AuthRegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag_auth_register, container, false);

        etEmail = view.findViewById(R.id.et_email);
        etPassword = view.findViewById(R.id.et_password);
        etPhone = view.findViewById(R.id.et_phone);
        etDate = view.findViewById(R.id.et_date);
        btnRegister = view.findViewById(R.id.btn_register);
        tvLogin = view.findViewById(R.id.tv_login);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();


        btnRegister.setOnClickListener(view1 -> {
            startActivity(new Intent(getContext(), UserMainActivity.class));
            getActivity().finish();
        });

        tvLogin.setOnClickListener(view2 -> {
            AuthLoginFragment fragment = new AuthLoginFragment();
            ft.replace(R.id.content_auth, fragment);
            ft.commit();
        });

        return view;
    }
}