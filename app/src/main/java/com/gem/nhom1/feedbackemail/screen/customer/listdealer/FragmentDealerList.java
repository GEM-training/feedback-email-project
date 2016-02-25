package com.gem.nhom1.feedbackemail.screen.customer.listdealer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.gem.nhom1.feedbackemail.adapter.DealerListAdapter;
import com.gem.nhom1.feedbackemail.base.BaseFragment;
import com.gem.nhom1.feedbackemail.network.entities.Dealer;
import com.gem.nhom1.feedbackemail.screen.customer.listproduct.ProductListActivity;
import com.project.gem.feedbackemail.R;

import butterknife.Bind;
import butterknife.OnItemClick;
import butterknife.OnItemSelected;

/**
 * Created by phuongtd on 24/02/2016.
 */
public class FragmentDealerList extends BaseFragment<DealerListPresenter> implements DealerListView {

    @Bind(R.id.list_dealer)
    ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        getPresenter().onLoadDealerOnStart();
        getPresenter().onItemSelected(listView);

        return view;
    }

    @Override
    public int setLayout() {
        return R.layout.dealer_list_fragment;
    }

    @Override
    public DealerListPresenter onCreatePresenter() {
        return new DealerListPresenterImpl(this);
    }

    @Override
    public void onLoadDealerSuccess(DealerListAdapter adapter) {
        listView.setAdapter(adapter);
    }

}
