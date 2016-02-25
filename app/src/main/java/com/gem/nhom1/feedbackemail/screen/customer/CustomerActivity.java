package com.gem.nhom1.feedbackemail.screen.customer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.gem.nhom1.feedbackemail.base.BaseActivity;
import com.gem.nhom1.feedbackemail.base.BaseView;
import com.gem.nhom1.feedbackemail.screen.customer.listdealer.FragmentDealerList;
import com.gem.nhom1.feedbackemail.screen.customer.profile.FragmentProfileCustomer;
import com.gem.nhom1.feedbackemail.screen.login.LoginActivity;
import com.project.gem.feedbackemail.R;


import butterknife.Bind;

/**
 * Created by phuongtd on 23/02/2016.
 */
public class CustomerActivity extends BaseActivity<CustomerPresenter> implements CustomerView , NavigationView.OnNavigationItemSelectedListener {


    private FragmentManager fm;
    private FragmentTransaction ft;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSupportActionBar(toolbar);

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

        FragmentProfileCustomer fragmentProfile = new FragmentProfileCustomer();
        ft.replace(R.id.content_menu, fragmentProfile);
        ft.commit();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public CustomerPresenter onCreatePresenter() {
        return new CustomerPresenterImpl(this);
    }

    @Override
    public Context getContextBase() {
        return CustomerActivity.this;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            FragmentProfileCustomer customerProfile = new FragmentProfileCustomer();
            ft = fm.beginTransaction();
            ft.replace(R.id.content_menu, customerProfile);
            ft.commit();
            toolbar.setTitle("Profiles");
        } else if (id == R.id.nav_dealers) {
            FragmentDealerList dealerList = new FragmentDealerList();
            ft = fm.beginTransaction();
            ft.replace(R.id.content_menu, dealerList);
            ft.commit();
            toolbar.setTitle("Dealer list");
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        } else if (id == R.id.logout) {
            if(getPresenter() == null){
                Toast.makeText(CustomerActivity.this , "Null" , Toast.LENGTH_SHORT).show();
            }
            getPresenter().logout();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

    @Override
    public void onLogoutSuccess() {
        Intent intent = new Intent(CustomerActivity.this  , LoginActivity.class);
        startActivity(intent);
        finish();
    }
}