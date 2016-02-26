package com.gem.nhom1.feedbackemail.screen.customer.listproduct;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.gem.nhom1.feedbackemail.base.BaseActivity;
import com.gem.nhom1.feedbackemail.network.entities.Dealer;
import com.gem.nhom1.feedbackemail.network.entities.UnitPrice;
import com.project.gem.feedbackemail.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by nghicv on 25/02/2016.
 */
public class ProductListActivity extends BaseActivity<ProductListPresenter> implements ProductListView{

    private Dealer dealer;
    private List<UnitPrice> unitPrices = new ArrayList<>();
    private ProductListAdapter adapter;

    @Bind(R.id.lv_product)
    ListView lvProduct;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.ll_menu_product)
    LinearLayout llMenuProduct;

    @Override
    protected int getLayoutId() {
        return R.layout.product_list;
    }

    @Override
    public ProductListPresenter onCreatePresenter() {
        return new ProductListPresenterImp(this);
    }

    @Override
    public Context getContextBase() {
        return getBaseContext();
    }

    @Override
    public void onLoadProductListSuccess(List<UnitPrice> unitPrices) {
        this.unitPrices.addAll(unitPrices);
        adapter.notifyDataSetChanged();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);
        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        dealer = (Dealer)bundle.getSerializable("dealer");

        adapter = new ProductListAdapter(getBaseContext(), unitPrices);
        lvProduct.setAdapter(adapter);

        getPresenter().doLoadListProduct(dealer);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(llMenuProduct.getVisibility() == View.GONE){
            Animation animation = AnimationUtils.loadAnimation(getBaseContext(),R.anim.side_down);
            llMenuProduct.setVisibility(View.VISIBLE);
            llMenuProduct.startAnimation(animation);
        } else{
            Animation animation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.side_up);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    llMenuProduct.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            llMenuProduct.startAnimation(animation);
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_product, menu);
        return true;
    }
}
