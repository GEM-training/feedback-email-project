package com.gem.nhom1.feedbackemail.screen.customer.listdealer;

import android.widget.ListView;

import com.gem.nhom1.feedbackemail.base.BasePresenter;

/**
 * Created by phuongtd on 24/02/2016.
 */
public interface DealerListPresenter extends BasePresenter {
    public void onLoadDealerOnStart();
    public void onLoadMore(int startIndex,int pageSize);
}
