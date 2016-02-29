package com.gem.nhom1.feedbackemail.base;

/**
 * Created by phuongtd on 23/02/2016.
 */
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;


import com.gem.nhom1.feedbackemail.commom.util.DeviceUtils;
import com.gem.nhom1.feedbackemail.commom.util.DialogUtils;

import butterknife.ButterKnife;

/**
 * Base activity
 * Created by neo on 2/5/2016.
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView<T> {
    private ProgressDialog mProgressDialog;
    private T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        // Inject views
        ButterKnife.bind(this);

        // Prepare layout
        onPrepareLayout();

        // Presenter for this view
        mPresenter = onCreatePresenter();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    public T getPresenter() {
        return mPresenter;
    }

    @Override
    public void onPrepareLayout() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setProgressStyle(android.R.style.Widget_DeviceDefault_Light_ProgressBar_Large);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setCanceledOnTouchOutside(false);
    }

    @Override
    public void showProgress() {
        if (mProgressDialog != null && !mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    @Override
    public void hideProgress() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void onRequestError(String errorMessage) {
        DialogUtils.showErrorAlert(this, errorMessage);
        hideProgress();
    }

    @Override
    public void onRequestSuccess() {
        hideProgress();
    }

    /**
     * Return layout resource id for activity
     */
    protected abstract int getLayoutId();
}