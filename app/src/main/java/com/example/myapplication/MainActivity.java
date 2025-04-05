package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ImageButton buttonDrawerToggle;
    private NavigationView navigationView;

    private TextView textUsername;
    private TextView textUserEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Drawer és navigáció inicializálása
        initDrawerAndNavigation();

        // Alapértelmezettként a LoginFragment betöltése
        if (savedInstanceState == null) {
            loadFragment(new LoginFragment(), false);
        }
    }


    private void initDrawerAndNavigation() {
        drawerLayout = findViewById(R.id.drawerLayout);
        buttonDrawerToggle = findViewById(R.id.buttonDrawerToggle);
        navigationView = findViewById(R.id.navigationView);

        TextView textViewCoachify = findViewById(R.id.textView2);
        ImageView imageViewLogo = findViewById(R.id.myImageView);

        textViewCoachify.setOnClickListener(v -> loadFragment(new StartScreenFragment(), true));
        imageViewLogo.setOnClickListener(v -> loadFragment(new StartScreenFragment(), true));


        // Drawer toggle gomb
        buttonDrawerToggle.setOnClickListener(v -> drawerLayout.open());

        // Header elemek
        View headerView = navigationView.getHeaderView(0);
        ImageView userImage = headerView.findViewById(R.id.userImage);
        textUsername = headerView.findViewById(R.id.textUsername);
        textUserEmail = headerView.findViewById(headerView.getResources()
                .getIdentifier("textUserEmail", "id", getPackageName()));

        userImage.setOnClickListener(v ->
                Toast.makeText(this, textUsername.getText(), Toast.LENGTH_SHORT).show());

        // Navigációs menü kezelése
        navigationView.setNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.navLogin) {
                loadFragment(new LoginFragment(), true);
            }
            else if (itemId == R.id.navMenu) {
                loadFragment(new StartScreenFragment(), true);
            }
            else if (itemId == R.id.navHir) {
                loadFragment(new NewsFragment(), true);
            }
            else if (itemId == R.id.navNap) {
                loadFragment(new CalendarFragment(), true);
            }
            else if (itemId == R.id.navEdzes) {
                loadFragment(new EdzesekFragment(), true);
            }
            else if (itemId == R.id.navFotok) {
                loadFragment(new PicturesFragment(), true);
            }
            else if (itemId == R.id.navFaq) {
                loadFragment(new FaqFragment(), true);
            }
            else if (itemId == R.id.navElerhetoseg) {
                loadFragment(new ElerhetosegekFragment(), true);
            }

            drawerLayout.close();
            return true;
        });
    }

    public void loadFragment(Fragment fragment, boolean addToBackStack) {
        try {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
            transaction.replace(R.id.content_frame, fragment);

            if (addToBackStack) {
                transaction.addToBackStack(null);
            }

            transaction.commit();
        } catch (Exception e) {
            Log.e("MainActivity", "Error loading fragment", e);
        }
    }

    public void navigateToMainScreen() {
        loadFragment(new StartScreenFragment(), true);
    }

    public void loadFragment(SignupFragment signupFragment) {
        loadFragment(signupFragment, true);
    }

    public void updateDrawerHeader(String name, String email) {
        runOnUiThread(() -> {
            if (textUsername != null) textUsername.setText(name);
            if (textUserEmail != null) textUserEmail.setText(email);
        });
    }
}