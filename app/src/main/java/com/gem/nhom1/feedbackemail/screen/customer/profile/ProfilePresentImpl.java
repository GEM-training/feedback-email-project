package com.gem.nhom1.feedbackemail.screen.customer.profile;

import com.gem.nhom1.feedbackemail.base.BasePresenter;
import com.gem.nhom1.feedbackemail.network.entities.Customer;

/**
 * Created by phuongtd on 23/02/2016.
 */
public class ProfilePresentImpl implements ProfilePresenter {

    ProfileView mView;

    public ProfilePresentImpl(ProfileView view){
        this.mView = view;
    }

    @Override
    public void setLayoutOnCreate(Customer customer) {
        mView.setName("Name: " +customer.getName());
        mView.setPhone("Phone: "+customer.getPhone());
        mView.setAddress("Address: "+customer.getAddress());
    }
}
