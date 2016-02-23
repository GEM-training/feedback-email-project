package com.gem.nhom1.feedbackemail.screen.customer.profile;

import com.gem.nhom1.feedbackemail.base.BaseFragmentView;

/**
 * Created by phuongtd on 23/02/2016.
 */
public interface ProfileView extends BaseFragmentView<ProfilePresenter>{
    public void setName(String name);
    public void setPhone(String phone);
    public void setAddress(String address);
}
