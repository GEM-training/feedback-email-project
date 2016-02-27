package com.gem.nhom1.feedbackemail.screen.customer.liststore;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.gem.nhom1.feedbackemail.adapter.ListStoreAdapter;
import com.gem.nhom1.feedbackemail.base.BaseFragment;
import com.gem.nhom1.feedbackemail.network.entities.Store;
import com.gem.nhom1.feedbackemail.screen.customer.listproduct.ProductListActivity;
import com.project.gem.feedbackemail.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by phuongtd on 24/02/2016.
 */
public class FragmentListStore extends BaseFragment<ListStorePresenter> implements ListStoreView {

    @Bind(R.id.list_dealer)
    ListView listView;
    private ListStoreAdapter adapter;
    List<Store> listStore;
    int currentScrollState;

    private int page = 0;

    private int pageSize = 10;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listStore = new ArrayList<>();
        adapter = new ListStoreAdapter(getActivity(),listStore);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getPresenter().onLoadMore(page , pageSize);

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
                    getPresenter().onLoadMore(page , pageSize);
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Store store = listStore.get(position);
                Intent intent = new Intent(getActivity(), ProductListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("store", store);
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
    public ListStorePresenter onCreatePresenter() {
        return new ListStorePresenterImpl(this);
    }

    @Override
    public void onLoadDealerSuccess(List<Store> stores) {
        page++;
        listStore.addAll(stores);
        adapter.notifyDataSetChanged();
    }

}
