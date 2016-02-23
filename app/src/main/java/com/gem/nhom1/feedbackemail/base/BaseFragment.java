package com.gem.nhom1.feedbackemail.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.gem.feedbackemail.R;

import butterknife.ButterKnife;

/**
 * Created by phuongtd on 23/02/2016.
 */
public abstract class BaseFragment<T  extends BasePresenter> extends Fragment implements BaseFragmentView<T>{


    private T mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(setLayout() , null);

        ButterKnife.bind(this ,view);

        mPresenter = onCreatePresenter();

        return view;
    }

    @Override
    public T getPresenter() {
        return mPresenter;
    }

    @Override
    public Context getContextBase() {
        return getContext();
    }

    public abstract int setLayout();
}
