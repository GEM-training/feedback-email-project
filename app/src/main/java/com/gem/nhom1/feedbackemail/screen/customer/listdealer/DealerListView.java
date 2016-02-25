package com.gem.nhom1.feedbackemail.screen.customer.listdealer;

import com.gem.nhom1.feedbackemail.adapter.DealerListAdapter;
import com.gem.nhom1.feedbackemail.base.BaseFragmentView;
import com.gem.nhom1.feedbackemail.base.BaseView;

/**
 * Created by phuongtd on 24/02/2016.
 */
public interface DealerListView extends BaseFragmentView<DealerListPresenter> {

    public void onLoadDealerSuccess(DealerListAdapter adapter , int position);

}
