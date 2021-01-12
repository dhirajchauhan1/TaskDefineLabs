package com.webboconnect.taskdefinelabs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawer;
    NavigationView navigationView;
    FrameLayout frameLayout;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nvView);
        frameLayout = (FrameLayout) findViewById(R.id.nav_host_fragment);

        toggle = new ActionBarDrawerToggle(this, drawer, toolbar,R.string.drawer_open,  R.string.drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        ConnectivityManager conMgr =  (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
        if (netInfo == null){
            new AlertDialog.Builder(this)
                    .setTitle(getResources().getString(R.string.app_name))
                    .setMessage("Please On Your Internet Connections.")
                    .setPositiveButton("OK", null).show();
        }else{
            loadFragment(new AllFragment());
            setTitle("All Matches");
        }
        loadFragment(new AllFragment());
        setTitle("All Matches");

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.all:
                        loadFragment(new AllFragment());
                        item.setCheckable(true);
                        setTitle(item.getTitle());
                        break;

                    case R.id.saved:
                        loadFragment(new SavedFragment());
                        item.setCheckable(true);
                        setTitle(item.getTitle());
                        break;
                }
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    public void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, fragment);
        transaction.commit();

    }
}