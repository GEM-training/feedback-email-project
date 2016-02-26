package com.gem.nhom1.feedbackemail.screen.customer.listdealer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.gem.nhom1.feedbackemail.adapter.DealerListAdapter;
import com.gem.nhom1.feedbackemail.base.BaseFragment;
import com.gem.nhom1.feedbackemail.network.entities.Dealer;
import com.gem.nhom1.feedbackemail.screen.customer.listproduct.ProductListActivity;
import com.project.gem.feedbackemail.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnItemClick;
import butterknife.OnItemSelected;

/**
 * Created by phuongtd on 24/02/2016.
 */
public class FragmentDealerList extends BaseFragment<DealerListPresenter> implements DealerListView {

    @Bind(R.id.list_dealer)
    ListView listView;
    private DealerListAdapter adapter;
    List<Dealer> listDealers;
    int currentScrollState;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listDealers = new ArrayList<>();
        adapter = new DealerListAdapter(getActivity(),listDealers);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getPresenter().onLoadDealerOnStart();

        listView.setAdapter(adapter);

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            int currentFirstVisibleItem;
            int currentVisibleItemCount;
            int total=10;
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                currentScrollState = scrollState;
                onScrollComplete();
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                currentFirstVisibleItem = firstVisibleItem;
                currentVisibleItemCount = visibleItemCount;
                total = totalItemCount;

            }
            public void onScrollComplete(){
                if (this.currentVisibleItemCount+currentFirstVisibleItem==total && currentScrollState == SCROLL_STATE_IDLE) {
                    getPresenter().onLoadMore(listDealers.get(listDealers.size() -1).getDealerId() , 5);
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Dealer dealer = listDealers.get(position);
                Intent intent = new Intent(getActivity(), ProductListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("dealer", dealer);
                intent.putExtras(bundle);
                getActivity().startActivity(intent);
            }
        });
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
    public void onLoadDealerSuccess(List<Dealer> dealerList) {
        listDealers.addAll(dealerList);
        adapter.notifyDataSetChanged();
    }

}
