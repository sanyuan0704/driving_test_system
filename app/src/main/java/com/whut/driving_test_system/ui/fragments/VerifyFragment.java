package com.whut.driving_test_system.ui.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whut.driving_test_system.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class InspectFragment extends Fragment {


    public InspectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inspect, container, false);
    }

}
