package com.gem.nhom1.feedbackemail.screen.customer.liststore;

import com.gem.nhom1.feedbackemail.base.BasePresenter;

/**
 * Created by phuongtd on 24/02/2016.
 */
public interface ListStorePresenter extends BasePresenter {
    void onLoadMore(int startIndex, int pageSize);
}
