package com.gem.nhom1.feedbackemail.screen.customer.listdealer;

import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

import com.gem.nhom1.feedbackemail.SQLDatabase.DealerAdapter;
import com.gem.nhom1.feedbackemail.adapter.DealerListAdapter;
import com.gem.nhom1.feedbackemail.base.BaseView;
import com.gem.nhom1.feedbackemail.commom.Constant;
import com.gem.nhom1.feedbackemail.commom.util.NetworkUtil;
import com.gem.nhom1.feedbackemail.commom.util.PreferenceUtils;
import com.gem.nhom1.feedbackemail.network.ServiceBuilder;
import com.gem.nhom1.feedbackemail.network.callback.BaseCallback;
import com.gem.nhom1.feedbackemail.network.dto.ListDealerDTO;
import com.gem.nhom1.feedbackemail.network.dto.TokenInfoDTO;
import com.gem.nhom1.feedbackemail.network.entities.Dealer;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by phuongtd on 24/02/2016.
 */
public class DealerListPresenterImpl implements DealerListPresenter {
    private DealerListView mView;
    private BaseView baseView;

    private boolean empty = false;

    private int loadDefaultSize = 10;
    private int loadMoreSize = 5;

    private int firstVisible = 0;

    List<Dealer> list  = new ArrayList<Dealer>();;

    public DealerListPresenterImpl(DealerListView view){
        this.mView = view;
        baseView = (BaseView) mView.getContextBase();
    }

    @Override
    public void onLoadDealerOnStart() {

        if(Constant.offLineMode == false){
            baseView.showProgress();

            ServiceBuilder.getService().getListDealer(Constant.CURRENT_ACCESS_TOKEN , -1 , loadDefaultSize).enqueue(mCallback);

        } else {
            Toast.makeText(mView.getContextBase() , "Get Dealer on Offline Mode" , Toast.LENGTH_SHORT).show();
            DealerAdapter dealerAdapter = new DealerAdapter(mView.getContextBase());

            // Lay tu id = 0 , size = 5
            list = dealerAdapter.getListDealer(-1 ,loadDefaultSize);

            DealerListAdapter dealerListAdapter = new DealerListAdapter(mView.getContextBase() , list);

            mView.onLoadDealerSuccess(dealerListAdapter , firstVisible);
        }

    }

    private BaseCallback mCallback = new BaseCallback<ListDealerDTO>() {
        @Override
        public void onError(int errorCode, String errorMessage) {
            baseView.onRequestError(errorCode, errorMessage);
        }

        @Override
        public void onResponse(Object o) {

            baseView.onRequestSuccess();

            List<Dealer> dealerListTemp = new Gson().fromJson(new Gson().toJson(o) , ( new ArrayList<Dealer>()).getClass());

            // Cap nhat cac dealer vao SQL
            DealerAdapter dealerAdapter = new DealerAdapter(mView.getContextBase());

            if(dealerListTemp.size() == 0){
                empty = true;
            } else {

                for (int i = 0; i < dealerListTemp.size(); i++) {
                    Dealer temp = new Gson().fromJson(new Gson().toJson(dealerListTemp.get(i)), Dealer.class);

                    if(list.size() == 0){
                        list.add(temp);
                        dealerAdapter.insert(list.get(i));
                    } else {
                        if(temp.getDealerId() > list.get(list.size() - 1).getDealerId()){
                            list.add(temp);
                            dealerAdapter.insert(list.get(i));
                        }
                    }
                }

                DealerListAdapter dealerListAdapter = new DealerListAdapter(mView.getContextBase(), list);

                mView.onLoadDealerSuccess(dealerListAdapter , firstVisible);
            }

        }
    };

    @Override
    public void onLoadMore(final ListView  listView) {
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(firstVisibleItem + visibleItemCount == totalItemCount && empty == false){ //check if we've reached the bottom
                    Log.d("phuongtd", "Load more");

                    firstVisible = firstVisibleItem;

                    if(Constant.offLineMode == false) {
                        if (list.size() > 0 && empty == false) {
                            ServiceBuilder.getService().getListDealer(Constant.CURRENT_ACCESS_TOKEN, list.get(list.size() - 1).getDealerId(), loadMoreSize).enqueue(mCallback);
                        }
                    } else {
                        if(list.size() > 0 && empty == false){
                            List<Dealer> listMore = new DealerAdapter(mView.getContextBase()).getListDealer(list.get(list.size() - 1).getDealerId(), loadMoreSize);
                            if(listMore.size() == 0){
                                empty = true;
                            } else {
                                list.addAll(listMore);
                                DealerListAdapter dealerListAdapter = new DealerListAdapter(mView.getContextBase(), list);

                                mView.onLoadDealerSuccess(dealerListAdapter , firstVisible);
                            }
                        }
                    }
                }
            }
        });
    }

}
