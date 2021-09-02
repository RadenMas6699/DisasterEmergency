package com.radenmas.disaster_emergency.ui.auth;

import android.content.Context;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.radenmas.disaster_emergency.R;

import java.util.HashMap;
import java.util.Map;

public class AuthRegisterFragment extends Fragment implements OnSuccessListener<AuthResult>, OnFailureListener {
    private DatabaseReference dbReff;
    private FirebaseAuth auth;
    private FirebaseUser user;
    private MaterialButton btnRegister;
    private TextView tvLogin;
    private EditText etEmail, etPassword, etPhone, etDateofBirth;
    private String strEmail, strPassword, strPhone, strBorn;

    public AuthRegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag_auth_register, container, false);

        dbReff = FirebaseDatabase.getInstance().getReference("Admin");
        auth = FirebaseAuth.getInstance();

        etEmail = view.findViewById(R.id.et_email);
        etPassword = view.findViewById(R.id.et_password);
        etPhone = view.findViewById(R.id.et_phone);
        etDateofBirth = view.findViewById(R.id.et_date_of_birth);
        btnRegister = view.findViewById(R.id.btn_register);
        tvLogin = view.findViewById(R.id.tv_login);

        btnRegister.setOnClickListener(view1 -> {
            register();
        });

        tvLogin.setOnClickListener(view2 -> {
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            AuthLoginFragment fragment = new AuthLoginFragment();
            ft.replace(R.id.content_auth, fragment);
            ft.commit();
        });

        return view;
    }

    private void register() {
        InputMethodManager manager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(etEmail.getApplicationWindowToken(), 0);
        manager.hideSoftInputFromWindow(etPassword.getApplicationWindowToken(), 0);
        manager.hideSoftInputFromWindow(etPhone.getApplicationWindowToken(), 0);
        manager.hideSoftInputFromWindow(etDateofBirth.getApplicationWindowToken(), 0);

        strEmail = etEmail.getText().toString().trim();
        strPassword = etPassword.getText().toString().trim();
        strPhone = etPhone.getText().toString().trim();
        strBorn = etDateofBirth.getText().toString().trim();

        if (strEmail.isEmpty()) {
            etEmail.setError("Email kosong");
        } else if (strPassword.isEmpty()) {
            etPassword.setError("Password kosong");
        } else if (strPhone.isEmpty()) {
            etPhone.setError("Phone kosong");
        } else if (strBorn.isEmpty()) {
            etDateofBirth.setError("Date of Birth kosong");
        } else {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(strEmail, strPassword).addOnSuccessListener(this).addOnFailureListener(this);
        }
    }

    @Override
    public void onSuccess(AuthResult authResult) {

        user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();


        char preff = strPhone.charAt(0);
        String prefix;
        if (preff == '0') {
            prefix = strPhone.replaceFirst("0", "+62");
        } else {
            prefix = "+62".concat(strPhone);
        }

        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("email", strEmail);
        userInfo.put("password", strPassword);
        userInfo.put("phone", prefix);
        userInfo.put("born", strBorn);
        userInfo.put("uid", uid);
        userInfo.put("role", "Admin");

        dbReff.child(uid).setValue(userInfo).addOnSuccessListener(unused -> {
            Toast.makeText(getContext(), "Registrasi Success", Toast.LENGTH_SHORT).show();
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            AuthLoginFragment fragment = new AuthLoginFragment();
            ft.replace(R.id.content_auth, fragment);
            ft.commit();
        });
    }

    @Override
    public void onFailure(@NonNull Exception e) {
        Toast.makeText(getContext(), "Registrasi Failure "+e, Toast.LENGTH_LONG).show();
    }
}