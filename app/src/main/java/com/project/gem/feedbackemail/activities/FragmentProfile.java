package com.project.gem.feedbackemail.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.gem.feedbackemail.R;

/**
 * Created by nghicv on 19/02/2016.
 */
public class FragmentProfile extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_profile, null);

        TextView tvName = (TextView) view.findViewById(R.id.tvName);
        TextView tvId = (TextView) view.findViewById(R.id.tvId);
        TextView tvBirthday = (TextView) view.findViewById(R.id.tvBirthday);
        TextView tvCountry = (TextView) view.findViewById(R.id.tvCountry);
        return view;
    }
}
