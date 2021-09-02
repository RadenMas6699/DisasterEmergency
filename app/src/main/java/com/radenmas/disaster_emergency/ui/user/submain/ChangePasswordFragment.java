package com.radenmas.disaster_emergency.ui.user.submain;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.radenmas.disaster_emergency.R;

import java.util.HashMap;
import java.util.Map;

public class ChangePasswordFragment extends Fragment {

    private EditText etOldPass, etNewPass, etConfirmNewPass;
    private String strUid, strEmail, strOldPass, strNewPass, strConfirmNewPass;
    private MaterialButton btnChangePass;

    private DatabaseReference dbReff;
    private FirebaseAuth auth;

    public ChangePasswordFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag_change_password, container, false);

        dbReff = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();

        initView(view);

        strUid = auth.getUid();
        dbReff.child("Admin").child(strUid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                strEmail = (String) snapshot.child("email").getValue();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnChangePass.setOnClickListener(view1 -> {
            InputMethodManager manager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(etOldPass.getApplicationWindowToken(), 0);
            manager.hideSoftInputFromWindow(etNewPass.getApplicationWindowToken(), 0);
            manager.hideSoftInputFromWindow(etConfirmNewPass.getApplicationWindowToken(), 0);

            strOldPass = etOldPass.getText().toString().trim();
            strNewPass = etNewPass.getText().toString().trim();
            strConfirmNewPass = etConfirmNewPass.getText().toString().trim();

            if (strOldPass.isEmpty() || strNewPass.isEmpty() || strConfirmNewPass.isEmpty()) {
                Toast.makeText(getContext(), "Please Completed The Blank", Toast.LENGTH_SHORT).show();
            } else if (!strNewPass.equals(strConfirmNewPass)) {
                Toast.makeText(getContext(), "Passwords are not the same", Toast.LENGTH_SHORT).show();
            } else {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                AuthCredential credential = EmailAuthProvider
                        .getCredential(strEmail, strOldPass);

                // Prompt the user to re-provide their sign-in credentials
                user.reauthenticate(credential)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                user.updatePassword(strNewPass).addOnCompleteListener(task1 -> {
                                    if (task1.isSuccessful()) {
                                        Toast.makeText(getContext(), "Password updated", Toast.LENGTH_SHORT).show();
                                        Map<String, Object> userInfo = new HashMap<>();
                                        userInfo.put("password", strNewPass);
                                        dbReff.child("Admin").child(strUid).updateChildren(userInfo);
                                        etOldPass.setText("");
                                        etNewPass.setText("");
                                        etConfirmNewPass.setText("");
                                    } else {
                                        Toast.makeText(getContext(), "Error password not updated", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });
            }
        });

        return view;
    }

    private void initView(View view) {
        etOldPass = view.findViewById(R.id.et_old_pass);
        etNewPass = view.findViewById(R.id.et_new_pass);
        etConfirmNewPass = view.findViewById(R.id.et_confirm_new_pass);
        btnChangePass = view.findViewById(R.id.btn_change_password);
    }
}