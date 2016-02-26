package com.gem.nhom1.feedbackemail.screen.customer.listproduct;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ListView;

import com.gem.nhom1.feedbackemail.base.BaseActivity;
import com.gem.nhom1.feedbackemail.base.BasePresenter;
import com.gem.nhom1.feedbackemail.network.entities.Dealer;
import com.gem.nhom1.feedbackemail.network.entities.Unit;
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
        Log.d("nghicv", dealer.getName());

        adapter = new ProductListAdapter(getBaseContext(), unitPrices);
        lvProduct.setAdapter(adapter);

        getPresenter().doLoadListProduct(dealer);

    }
}
