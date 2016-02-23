
/*
package com.project.gem.feedbackemail.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.project.gem.feedbackemail.R;
import com.project.gem.feedbackemail.model.ResponseDTO;
import com.project.gem.feedbackemail.retrofit.RestClient;
import com.project.gem.feedbackemail.util.Constant;
import com.project.gem.feedbackemail.util.NetworkUtil;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager fm;
    private FragmentTransaction ft;

    SharedPreferences sharedPreferences ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        sharedPreferences = getSharedPreferences(getString(R.string.share_preferences_file),
                Context.MODE_PRIVATE);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        FragmentProfileDealer fragmentProfile = new FragmentProfileDealer();
        ft.replace(R.id.content_menu, fragmentProfile);
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            FragmentProfileDealer fragmentProfile = new FragmentProfileDealer();
            setContentForMenu(fragmentProfile);
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        } else if (id == R.id.logout) {
            logout();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void logout(){

        if (NetworkUtil.isNetworkAvaiable(MainActivity.this)) {
            RestClient.GitApiInterface service = RestClient.getClient();
            Call<ResponseDTO> call = service.logout(sharedPreferences.getString(Constant.TOKEN_KEY, ""));

            call.enqueue(new Callback<ResponseDTO>() {
                @Override
                public void onResponse(Response<ResponseDTO> response) {

                    Log.d("phuongtd", "Status logout: " + response.code());

                    if (response.isSuccess()) {
                        ResponseDTO dto = response.body();

                        if (dto.getStatus().equals(Constant.RESPONSE_STATUS_SUSSCESS)) {
                            Toast.makeText(MainActivity.this, "Logout Success", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);

                            deleteToken();

                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(MainActivity.this, "Khong the logout tren Server", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, response.message(), Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });
        } else {

            Toast.makeText(MainActivity.this, "Logout Success", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);

            deleteToken();
            deleteCurrentUserId();

            startActivity(intent);
            finish();
        }
    }

    private void deleteToken(){
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.share_preferences_file),
                Context.MODE_PRIVATE);
        sharedPreferences.edit().remove(Constant.TOKEN_KEY).commit();
    }

    private void deleteCurrentUserId(){
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.share_preferences_file),
                Context.MODE_PRIVATE);
        sharedPreferences.edit().remove(Constant.CURRENT_USER_ID).commit();
    }
    private void setContentForMenu(Fragment fragment){
        ft = fm.beginTransaction();
        ft.replace(R.id.content_menu, fragment);
        ft.commit();
    }
}
*/

