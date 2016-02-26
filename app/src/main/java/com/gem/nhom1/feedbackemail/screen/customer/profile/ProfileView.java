package com.gem.nhom1.feedbackemail.screen.customer.profile;

import com.gem.nhom1.feedbackemail.base.BaseFragmentView;

/**
 * Created by phuongtd on 23/02/2016.
 */
public interface ProfileView extends BaseFragmentView<ProfilePresenter>{
    void setName(String name);
    void setPhone(String phone);
    void setAddress(String address);
}
