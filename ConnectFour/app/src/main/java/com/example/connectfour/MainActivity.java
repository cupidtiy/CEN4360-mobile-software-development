package com.example.connectfour;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the Toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initialize BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Initialize NavHostFragment
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);

        if (navHostFragment != null) {
            // Initialize NavController
            NavController navController = navHostFragment.getNavController();

            // Configure the AppBarConfiguration with the correct fragment IDs
            AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.boardFragment, R.id.gameOptionsFragment) // Use correct fragment IDs
                    .build();

            // Set up ActionBar with NavController using the Toolbar
            NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

            // Set up BottomNavigationView with NavController
            NavigationUI.setupWithNavController(bottomNavigationView, navController);

            // Listener to change the toolbar title dynamically
            navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
                if (destination.getId() == R.id.boardFragment) {
                    toolbar.setTitle("Connect Four");
                } else if (destination.getId() == R.id.gameOptionsFragment) {
                    toolbar.setTitle("Options");
                }
            });
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = ((NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment)).getNavController();
        return navController.navigateUp() || super.onSupportNavigateUp();
    }
}
