package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;

public class SignupFragment extends Fragment {
    private EditText etName, etEmail, etPassword, etConfirmPassword;
    private Button btnSignup;
    private TextView tvLoginRedirect;
    private DatabaseHelper databaseHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.signup, container, false);

        // View elemek összekötése
        etName = view.findViewById(R.id.signup_name);
        etEmail = view.findViewById(R.id.signup_email);
        etPassword = view.findViewById(R.id.signup_password);
        etConfirmPassword = view.findViewById(R.id.signup_confirm);
        btnSignup = view.findViewById(R.id.signup_button);  // <- EZ NAGYON FONTOS
        tvLoginRedirect = view.findViewById(R.id.loginRedirectText);

        databaseHelper = new DatabaseHelper(requireActivity());

        // Regisztráció gomb kezelése
        btnSignup.setOnClickListener(v -> {
            Log.d("SignupFragment", "Signup button clicked");
            handleSignup();
        });

        // Bejelentkezés szöveg kezelése
        tvLoginRedirect.setOnClickListener(v -> {
            Log.d("SignupFragment", "Login redirect clicked");
            navigateToLogin();
        });

        return view;
    }

    private void handleSignup() {
        Log.d("SignupFragment", "Handling signup");

        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();

        // Ellenőrzés
        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(getContext(), "Minden mező kitöltése kötelező", Toast.LENGTH_LONG).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(getContext(), "A jelszavak nem egyeznek", Toast.LENGTH_LONG).show();
            return;
        }

        if (databaseHelper.isEmailExists(email)) {
            Toast.makeText(getContext(), "Ez az email már regisztrálva van", Toast.LENGTH_LONG).show();
            return;
        }

        if (databaseHelper.addUser(name, email, password)) {

            ((MainActivity)requireActivity()).updateDrawerHeader(name, email);

            navigateToLogin();
        } else {
            Toast.makeText(getContext(), "Regisztrációs hiba történt", Toast.LENGTH_LONG).show();
        }
    }

    private void navigateToLogin() {
        try {
            ((MainActivity)requireActivity()).loadFragment(new LoginFragment(), true);
        } catch (Exception e) {
            Log.e("SignupFragment", "Error navigating to login", e);
        }
    }
}
