package com.gem.nhom1.feedbackemail.screen.customer.listdealer;

import android.util.Log;
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



    public DealerListPresenterImpl(DealerListView view){
        this.mView = view;
        baseView = (BaseView) mView.getContextBase();
    }

    @Override
    public void onLoadDealerOnStart() {

        if(Constant.offLineMode == false){
            baseView.showProgress();

            ServiceBuilder.getService().getListDealer(Constant.CURRENT_ACCESS_TOKEN).enqueue(mCallback);

        } else {
            Toast.makeText(mView.getContextBase() , "Get Dealer on Offline Mode" , Toast.LENGTH_SHORT).show();
            DealerAdapter dealerAdapter = new DealerAdapter(mView.getContextBase());

            // Lay tu id = 0 , size = 5
            List<Dealer> dealerList = dealerAdapter.getListDealer(-1 , 5);

            DealerListAdapter dealerListAdapter = new DealerListAdapter(mView.getContextBase() , dealerList);

            mView.onLoadDealerSuccess(dealerListAdapter);
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

            List<Dealer> list = new ArrayList<Dealer>();

            // Cap nhat cac dealer vao SQL
            DealerAdapter dealerAdapter = new DealerAdapter(mView.getContextBase());

            for(int i = 0 ; i< dealerListTemp.size() ; i++){
                list.add(new Gson().fromJson(new Gson().toJson(dealerListTemp.get(i)), Dealer.class));
                Log.d("phuongtd", "Insert " + dealerAdapter.insert(list.get(i)));
            }

            DealerListAdapter dealerListAdapter = new DealerListAdapter(mView.getContextBase() , list);

            mView.onLoadDealerSuccess(dealerListAdapter);


        }
    };
}
