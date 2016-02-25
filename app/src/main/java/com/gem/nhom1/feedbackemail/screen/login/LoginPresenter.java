package com.gem.nhom1.feedbackemail.screen.login;

import com.gem.nhom1.feedbackemail.base.BasePresenter;

/**
 * Created by phuongtd on 23/02/2016.
 */
public interface LoginPresenter extends BasePresenter {
    void doLogin(String username, String password , boolean remember);
}
