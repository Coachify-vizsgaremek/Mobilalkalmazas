package com.example.myapplication;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.example.myapplication.network.models.LoginRequest;
import com.example.myapplication.network.models.LoginResponse;
import com.example.myapplication.network.ApiService;
import com.example.myapplication.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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
            DatabaseHelper.User user = databaseHelper.getUserData(email);
            if (user != null) {
                ((MainActivity)requireActivity()).updateDrawerHeader(user.name, user.email);
            }
            ((MainActivity)requireActivity()).navigateToMainScreen();
        } else {
            Toast.makeText(getContext(), "Hibás email vagy jelszó", Toast.LENGTH_SHORT).show();
        }
    }


    private void navigateToSignup() {
        ((MainActivity)requireActivity()).loadFragment(new SignupFragment());
    }
}
class User {
    String name;
    String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}