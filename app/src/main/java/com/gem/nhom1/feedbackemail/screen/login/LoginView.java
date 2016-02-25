package com.gem.nhom1.feedbackemail.screen.login;

import com.gem.nhom1.feedbackemail.base.BaseView;
import com.gem.nhom1.feedbackemail.network.dto.TokenInfoDTO;
import com.gem.nhom1.feedbackemail.network.entities.User;

/**
 * Created by phuongtd on 23/02/2016.
 */
public interface LoginView extends BaseView<LoginPresenter> {
    public void showError(String mess);
    public void onLoginSuccess(TokenInfoDTO  tokenInfoDTO);

    public void onLoginSuccessOffLine(User user);
}