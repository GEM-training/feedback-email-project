package com.project.gem.feedbackemail.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.gem.feedbackemail.R;

/**
 * Created by nghicv on 19/02/2016.
 */
public class FragmentProfile extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_profile, null);
    }
}
