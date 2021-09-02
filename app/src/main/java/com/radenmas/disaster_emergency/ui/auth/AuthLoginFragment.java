package com.radenmas.disaster_emergency.ui.auth;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.radenmas.disaster_emergency.R;
import com.radenmas.disaster_emergency.ui.admin.main.AdminMainActivity;
import com.radenmas.disaster_emergency.ui.user.UserMainActivity;

public class AuthLoginFragment extends Fragment implements OnSuccessListener<AuthResult>, OnFailureListener {

    private DatabaseReference dbReff;
    private FirebaseAuth auth;
    private MaterialButton btnLogin;
    private TextView tvRegister;
    private EditText etEmail, etPassword;
    private String strEmail, strPassword, strPhone, strDate;
    private String role;

    public AuthLoginFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag_auth_login, container, false);

        dbReff = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();

        etEmail = view.findViewById(R.id.et_email);
        etPassword = view.findViewById(R.id.et_password);
        btnLogin = view.findViewById(R.id.btn_login);
        tvRegister = view.findViewById(R.id.tv_register);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        btnLogin.setOnClickListener(view1 -> {
            login();
        });

        tvRegister.setOnClickListener(view2 -> {
            AuthRegisterFragment fragment = new AuthRegisterFragment();
            ft.replace(R.id.content_auth, fragment);
            ft.commit();
        });

        return view;
    }

    private void login() {
        InputMethodManager manager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(etEmail.getApplicationWindowToken(), 0);
        manager.hideSoftInputFromWindow(etPassword.getApplicationWindowToken(), 0);

        strEmail = etEmail.getText().toString().trim();
        strPassword = etPassword.getText().toString().trim();

        if (strEmail.isEmpty()) {
            etEmail.setError("Email kosong");
        } else if (strPassword.isEmpty()) {
            etPassword.setError("Password kosong");
        } else {
            auth.signInWithEmailAndPassword(strEmail, strPassword).addOnSuccessListener(this).addOnFailureListener(this);
        }
    }

    @Override
    public void onSuccess(AuthResult authResult) {
        Toast.makeText(getContext(), "Login Success", Toast.LENGTH_SHORT).show();

        startActivity(new Intent(getContext(), AdminMainActivity.class));
        getActivity().finish();
    }

    @Override
    public void onFailure(@NonNull Exception e) {
        Toast.makeText(getContext(), "Login Failure", Toast.LENGTH_SHORT).show();
    }
}