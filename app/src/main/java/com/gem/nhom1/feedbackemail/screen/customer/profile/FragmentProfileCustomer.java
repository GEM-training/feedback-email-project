package com.gem.nhom1.feedbackemail.screen.customer.profile;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gem.nhom1.feedbackemail.base.BaseFragment;
import com.gem.nhom1.feedbackemail.base.BasePresenter;
import com.gem.nhom1.feedbackemail.commom.Constant;
import com.project.gem.feedbackemail.R;

import butterknife.Bind;

/**
 * Created by phuongtd on 23/02/2016.
 */
public class FragmentProfileCustomer extends BaseFragment<ProfilePresenter> implements ProfileView{

    @Bind(R.id.name)
    TextView tvName;

    @Bind(R.id.phone)
    TextView tvPhone;

    @Bind(R.id.address)
    TextView tvAddress;

    @Override
    public int setLayout() {
        return R.layout.layout_profile;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = super.onCreateView(inflater, container, savedInstanceState);
        getPresenter().setLayoutOnCreate();
        return v;
    }

    @Override
    public ProfilePresenter onCreatePresenter() {
        return new ProfilePresentImpl(this);
    }

    @Override
    public void setName(String name) {
        tvName.setText(name);
    }

    @Override
    public void setPhone(String phone) {
        tvPhone.setText(phone);
    }

    @Override
    public void setAddress(String address) {
        tvAddress.setText(address);
    }
}
