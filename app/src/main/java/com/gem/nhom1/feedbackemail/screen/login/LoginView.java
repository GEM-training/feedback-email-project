package com.gem.nhom1.feedbackemail.screen.login;

import com.gem.nhom1.feedbackemail.base.BaseView;
import com.gem.nhom1.feedbackemail.network.dto.ResponseUserInfoDTO;
import com.gem.nhom1.feedbackemail.network.dto.UserInfoDTO;

/**
 * Created by phuongtd on 23/02/2016.
 */
public interface LoginView extends BaseView<LoginPresenter> {
    void showError(String mess);
    void onLoginSuccess(ResponseUserInfoDTO userInfoDTO);

    //void onLoginSuccessOffLine(User user);
}
