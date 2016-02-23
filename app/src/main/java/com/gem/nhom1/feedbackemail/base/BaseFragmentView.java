package com.gem.nhom1.feedbackemail.base;

import android.content.Context;

/**
 * Created by phuongtd on 23/02/2016.
 */
public interface BaseFragmentView<P extends BasePresenter>{
    P getPresenter();
    P onCreatePresenter();

    Context getContextBase();
}
