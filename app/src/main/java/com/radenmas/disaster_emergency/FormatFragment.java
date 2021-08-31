package com.radenmas.disaster_emergency;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class FormatFragment extends Fragment {

    public FormatFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.frag_auth, container, false);

        initView(view);
        onClick();

        return view;
    }

    private void onClick() {
    }

    private void initView(View view) {
    }
}