package com.example.myapplication;



import com.example.myapplication.CalendarFragment;
import com.example.myapplication.StartScreenFragment;
import androidx.fragment.app.FragmentTransaction;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import android.view.View;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.MenuItem;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ImageButton buttonDrawerToggle;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);







        drawerLayout = findViewById(R. id.drawerLayout);
        buttonDrawerToggle = findViewById(R.id.buttonDrawerToggle);
        navigationView= findViewById(R.id.navigationView);


        TextView textViewCoachify = findViewById(R.id.textView2);
        ImageView imageViewLogo = findViewById(R.id.myImageView);


        buttonDrawerToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.open();
            }
        });

        View headerView = navigationView.getHeaderView(0);
        ImageView useImage = headerView.findViewById(R. id.userImage);
        TextView textUsername = headerView.findViewById(R.id.textUsername);
        NavigationView navigationView = findViewById(R.id.navigationView);



        textViewCoachify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                        .replace(R.id.content_frame, new StartScreenFragment())
                        .commit();
            }
        });

        imageViewLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                        .replace(R.id.content_frame, new StartScreenFragment())
                        .commit();
            }
        });

        useImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, textUsername.getText(), Toast.LENGTH_SHORT).show();
            }
        });







        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemId = item.getItemId();

                if (itemId == R.id.navLogin) {
                    Toast.makeText(MainActivity.this, "Belépés", Toast.LENGTH_SHORT).show();
                }

                if (item.getItemId() == R.id.navMenu) {
                    getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                            .replace(R.id.content_frame, new StartScreenFragment())
                            .commit();

                }


                if (itemId == R.id.navHir) {
                    Toast.makeText(MainActivity.this, "Legfrissebb hírek", Toast.LENGTH_SHORT).show();
                }



                if (itemId == R.id.navNap) {
                    getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                            .replace(R.id.content_frame, new CalendarFragment())
                            .commit();

                }




                if (itemId == R.id.navEdzes) {
                    Toast.makeText(MainActivity.this, "Edzései", Toast.LENGTH_SHORT).show();
                }



                if (itemId == R.id.navFotok) {
                    Toast.makeText(MainActivity.this, "Fotóink", Toast.LENGTH_SHORT).show();
                }



                if (itemId == R.id.navFaq) {
                    Toast.makeText(MainActivity.this, "FAQ", Toast.LENGTH_SHORT).show();
                }



                if (itemId == R.id.navElerhetoseg) {
                    Toast.makeText(MainActivity.this, "Elérhetőségeink", Toast.LENGTH_SHORT).show();
                }

                drawerLayout.close();



                return false;
            }

        });



    }
}