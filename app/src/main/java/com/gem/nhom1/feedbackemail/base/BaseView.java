package com.gem.nhom1.feedbackemail.base;

import android.content.Context;

/**
 * Created by phuongtd on 23/02/2016.
 */
public interface BaseView<P extends BasePresenter> {
    void showProgress();
    void hideProgress();
    void onPrepareLayout();

    P getPresenter();
    P onCreatePresenter();

    void onRequestError(String errorMessage);
    void onRequestSuccess();

    Context getContextBase();
}
