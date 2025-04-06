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

        return view;
    }
}
