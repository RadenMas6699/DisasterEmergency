package com.radenmas.disaster_emergency.ui.admin.submain;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.radenmas.disaster_emergency.R;

public class ObrolanFragment extends Fragment {

    public ObrolanFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag_admin_obrolan, container, false);

        initView(view);
        onClick();

        return view;
    }

    private void onClick() {
    }

    private void initView(View view) {
    }
}