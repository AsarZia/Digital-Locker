package com.example.locker;

import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.activity.OnBackPressedDispatcher;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.provider.MediaStore;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.security.acl.Group;

import de.hdodenhof.circleimageview.CircleImageView;

public class Dashboard extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,TabLayout.BaseOnTabSelectedListener {
    TabLayout tabs;
    FragmentManager fragmentManager;
    ViewPager viewPager;
    Menu action_logout;
    public FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        firebaseAuth = FirebaseAuth.getInstance();

      //  nav_profile = findViewById(R.id.nav_profile);


       /* FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        View headView = navigationView.getHeaderView(0);



        tabs = findViewById(R.id.tabs);
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_main, new Dash1()).commit();
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
              int pos = tab.getPosition();
              FragmentManager manager = getSupportFragmentManager();
              switch (pos){
                  case 0:
                      manager.beginTransaction().replace(R.id.frame_main, new Dash1()).addToBackStack(null).commit();
                      break;
                  case 1:
                      manager.beginTransaction().replace(R.id.frame_main,new Dash2()).addToBackStack(null).commit();
                      break;
                  case 2:
                      manager.beginTransaction().replace(R.id.frame_main, new Dash3()).addToBackStack(null).commit();
                      break;

              }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

       viewPager = findViewById(R.id.viewpager);

        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), tabs.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));
        tabs.setOnTabSelectedListener(this);



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(Dashboard.this,Settings.class);
            startActivity(intent);
            finish();
            return true;
        }else if (id == R.id.action_logout) {

            showPopup();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

        }else if(id == R.id.nav_import){
            Intent intent = new Intent(Dashboard.this,Camera_Gallery.class);
            startActivity(intent);
            finish();
        }
        else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(Dashboard.this,Gallery_Images.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_profile) {
            Intent intent = new Intent(Dashboard.this,Profile.class);
            startActivity(intent);
            finish();

        } /*else if (id == R.id.nav_slideshow) {

        }*/ else if (id == R.id.nav_tools) {
            Intent intent = new Intent(Dashboard.this,Settings.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.nav_share) {
            Intent myIntent = new Intent(Intent.ACTION_SEND);
            myIntent.setType("text/plane");
            String shareBody = "Hi! Digital Locker is awesome. It helps to preserve your documents " +
                    "like  Aadhaar, PAN, Driving License and Educational records in digital format at one place." +
                    "\n" +"\n"+
                    "LINK YET TO COME";
            String shareSub = "hello";
            myIntent.putExtra(Intent.EXTRA_SUBJECT,shareSub);
            myIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
            startActivity(Intent.createChooser(myIntent, "share using"));

        } /*else if (id == R.id.nav_send) {

        }*/ else if(id == R.id.nav_call){
            Intent intent =new Intent(Dashboard.this,Contact_Us.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    private void showPopup() {
        AlertDialog.Builder alert = new AlertDialog.Builder(Dashboard.this);
        alert.setTitle("Are you sure !! ");
                alert.setMessage("Do you want to logout ?")
                .setPositiveButton("YES", new DialogInterface.OnClickListener(){

                    public void onClick(DialogInterface dialog, int which) {

                        logout(); // Last step. Logout function

                    }
                }).setNegativeButton("NO", null);

        AlertDialog alert1 = alert.create();
        alert1.show();
    }

    private void logout() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }


}
