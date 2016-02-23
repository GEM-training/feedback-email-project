package com.gem.nhom1.feedbackemail.screen.customer.profile;

import com.gem.nhom1.feedbackemail.base.BaseFragment;
import com.gem.nhom1.feedbackemail.base.BasePresenter;
import com.gem.nhom1.feedbackemail.network.entities.Customer;

/**
 * Created by phuongtd on 23/02/2016.
 */
public interface ProfilePresenter extends BasePresenter {
    public void setLayoutOnCreate(Customer customer);
}
