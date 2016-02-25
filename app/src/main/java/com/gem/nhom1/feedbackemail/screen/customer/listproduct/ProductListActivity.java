package com.gem.nhom1.feedbackemail.screen.customer.listproduct;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.gem.nhom1.feedbackemail.base.BaseActivity;
import com.gem.nhom1.feedbackemail.base.BasePresenter;
import com.gem.nhom1.feedbackemail.network.entities.Dealer;
import com.project.gem.feedbackemail.R;

import butterknife.Bind;

/**
 * Created by nghicv on 25/02/2016.
 */
public class ProductListActivity extends BaseActivity<ProductListPresenter> implements ProductListView{

    private Dealer dealer;

    @Bind(R.id.lv_product)
    ListView lvProduct;

    @Override
    protected int getLayoutId() {
        return R.layout.product_list;
    }

    @Override
    public ProductListPresenter onCreatePresenter() {
        return new ProductListPresenterImp(this, dealer);
    }

    @Override
    public Context getContextBase() {
        return getBaseContext();
    }

    @Override
    public void onLoadProductListSuccess(ProductListAdapter adapter) {
        lvProduct.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = new Intent();
        Bundle bundle = i.getExtras();
        dealer = (Dealer)bundle.getSerializable("dealer");

    }
}
