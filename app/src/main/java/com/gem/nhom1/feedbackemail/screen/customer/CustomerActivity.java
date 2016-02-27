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
import android.view.MenuItem;

import com.gem.nhom1.feedbackemail.base.BaseActivity;
import com.gem.nhom1.feedbackemail.screen.customer.liststore.FragmentListStore;
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

        FragmentListStore fragmentListStore = new FragmentListStore();
        ft.replace(R.id.content_menu, fragmentListStore);
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

        if (id == R.id.nav_profile) {

        } else if (id == R.id.nav_dealers) {
            FragmentListStore dealerList = new FragmentListStore();
            ft = fm.beginTransaction();
            ft.replace(R.id.content_menu, dealerList);
            ft.commit();
            toolbar.setTitle("Dealer list");
        } else if (id == R.id.nav_send) {

        } else if (id == R.id.logout) {
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
