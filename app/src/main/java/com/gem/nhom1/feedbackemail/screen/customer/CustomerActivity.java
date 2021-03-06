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
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.gem.nhom1.feedbackemail.base.BaseActivity;
import com.gem.nhom1.feedbackemail.screen.customer.feedback.FeedBackFragment;
import com.gem.nhom1.feedbackemail.base.SelectRoleView;
import com.gem.nhom1.feedbackemail.screen.customer.liststore.FragmentListStore;
import com.gem.nhom1.feedbackemail.screen.login.LoginActivity;
import com.gem.nhom1.feedbackemail.screen.roleselect.SelectRoleActivity;
import com.project.gem.feedbackemail.R;


import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by phuongtd on 23/02/2016.
 */
public class CustomerActivity extends BaseActivity<CustomerPresenter> implements CustomerView , NavigationView.OnNavigationItemSelectedListener {


    private FragmentManager fm;
    private FragmentTransaction ft;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.bt_select_role)
    ImageView btSelectRole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("List Store");
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

        toolbar.setTitle("List Store");
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

        if (id == R.id.nav_dealers) {
            FragmentListStore dealerList = new FragmentListStore();
            ft = fm.beginTransaction();
            ft.replace(R.id.content_menu, dealerList);
            ft.commit();
            toolbar.setTitle("Store list");
        }else if (id == R.id.logout) {
            getPresenter().logout();
        } else if(id == R.id.nav_feedback){
            FeedBackFragment dealerList = new FeedBackFragment(toolbar);
            ft = fm.beginTransaction();
            ft.replace(R.id.content_menu, dealerList);
            ft.commit();

            setSupportActionBar(toolbar);

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

    @OnClick(R.id.bt_select_role)
    public void selectRole(){
        RelativeLayout linearLayout = SelectRoleView.getSelectRoleView(this);

        RelativeLayout.LayoutParams layoutParams  = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        this.addContentView(linearLayout , layoutParams);

    }
}
