package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

public class EdzesekFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.edzesek, container, false);

        // Képkattintás kezelése
        view.findViewById(R.id.i1).setOnClickListener(v -> {
            // CardioFragment betöltése
            ((MainActivity)getActivity()).loadFragment(new CardioFragment(), true);
        });
        view.findViewById(R.id.i2).setOnClickListener(v -> {
            // ChestFragment betöltése
            ((MainActivity)getActivity()).loadFragment(new ChestFragment(), true);
        });
        view.findViewById(R.id.i4).setOnClickListener(v -> {
            // ArmsFragment betöltése
            ((MainActivity)getActivity()).loadFragment(new BackFragment(), true);
        });
        view.findViewById(R.id.i3).setOnClickListener(v -> {
            // BackFragment betöltése
            ((MainActivity)getActivity()).loadFragment(new ArmsFragment(), true);
        });

        return view;
    }
}
