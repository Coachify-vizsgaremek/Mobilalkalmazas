package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.fragment.app.Fragment;

public class LoginFragment extends Fragment {
    private EditText etEmail, etPassword;
    private Button btnLogin, btnSignupRedirect;
    private DatabaseHelper databaseHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login, container, false);

        // View elemek összekötése
        etEmail = view.findViewById(R.id.login_email);
        etPassword = view.findViewById(R.id.login_password);
        btnLogin = view.findViewById(R.id.login_button);
        btnSignupRedirect = view.findViewById(R.id.signupRedirectButton);

        databaseHelper = new DatabaseHelper(requireActivity());

        // Bejelentkezés gomb kezelése
        btnLogin.setOnClickListener(v -> handleLogin());

        // Regisztráció gomb kezelése
        btnSignupRedirect.setOnClickListener(v -> navigateToSignup());

        return view;
    }

    private void handleLogin() {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(getContext(), "Kérlek töltsd ki mindkét mezőt!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (databaseHelper.checkUser(email, password)) {
            ((MainActivity)requireActivity()).navigateToMainScreen();
        } else {
            Toast.makeText(getContext(), "Hibás email vagy jelszó", Toast.LENGTH_SHORT).show();
        }
    }

    private void navigateToSignup() {
        ((MainActivity)requireActivity()).loadFragment(new SignupFragment());
    }
}