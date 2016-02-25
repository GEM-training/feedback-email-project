package com.gem.nhom1.feedbackemail.screen.customer.listdealer;

import com.gem.nhom1.feedbackemail.adapter.DealerListAdapter;
import com.gem.nhom1.feedbackemail.base.BaseFragmentView;
import com.gem.nhom1.feedbackemail.base.BaseView;
import com.gem.nhom1.feedbackemail.network.entities.Dealer;

import java.util.List;

/**
 * Created by phuongtd on 24/02/2016.
 */
public interface DealerListView extends BaseFragmentView<DealerListPresenter> {

    void onLoadDealerSuccess(List<Dealer>dealerList);

}
