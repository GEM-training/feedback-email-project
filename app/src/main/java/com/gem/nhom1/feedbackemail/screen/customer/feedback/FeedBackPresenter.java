package com.gem.nhom1.feedbackemail.screen.customer.feedback;

import android.content.Intent;

import com.gem.nhom1.feedbackemail.base.BasePresenter;

/**
 * Created by vanhop on 3/1/16.
 */
public interface FeedBackPresenter extends BasePresenter{

    void authenticateGoogleAccount();
    void refreshResults();
    void chooseAccount();
    boolean isGooglePlayServicesAvailable();
    void processActivityResult(int requestCode, int resultCode, Intent data);
}
