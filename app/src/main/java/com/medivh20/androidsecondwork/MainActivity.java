package com.medivh20.androidsecondwork;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.medivh20.androidsecondwork.ui.count.CountFragment;
import com.medivh20.androidsecondwork.ui.home.HomeFragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {
    private Fragment homeFragment;
    private Fragment countFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("data", "onCreate: 这次第一次修改程序" );
        BottomNavigationView navView = findViewById(R.id.nav_view);
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        navView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {

            }
        });
        homeFragment = new HomeFragment();
        countFragment = new CountFragment();
//        NavigationUI.setupWithNavController(navView, navController);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.nav_host_fragment,countFragment);
        ft.add(R.id.nav_host_fragment,homeFragment);
        ft.hide(countFragment);
        ft.commit();
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                FragmentTransaction ft = fm.beginTransaction();
                switch (item.getItemId()){
                    case R.id.navigation_home:
                        ft.show(homeFragment);
                        ft.hide(countFragment);
                        break;
                    case R.id.navigation_count:
                        ft.show(countFragment);
                        ft.hide(homeFragment);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + item.getItemId());
                }
                ft.commit();
                return false;
            }
        });
    }

}