package com.gem.nhom1.feedbackemail.screen.customer.feedback;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

import com.gem.nhom1.feedbackemail.base.BaseFragment;
import com.gem.nhom1.feedbackemail.base.BaseFragmentView;

/**
 * Created by vanhop on 3/1/16.
 */
public interface FeedBackView extends BaseFragmentView<FeedBackPresenter> {

    void setNotification(String content);
    void showGooglePlayServicesAvailabilityErrorDialog(
            final int connectionStatusCode);

}
