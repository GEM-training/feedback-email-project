package com.gem.nhom1.feedbackemail.screen.customer.liststore;

import com.gem.nhom1.feedbackemail.base.BaseFragmentView;
import com.gem.nhom1.feedbackemail.network.entities.Store;

import java.util.List;

/**
 * Created by phuongtd on 24/02/2016.
 */
public interface ListStoreView extends BaseFragmentView<ListStorePresenter> {

    void onLoadDealerSuccess(List<Store> listStore);

    void showProgressBar();

    void hideProgoressBar();

}
