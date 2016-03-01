package com.gem.nhom1.feedbackemail.screen.customer.feedback;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gem.nhom1.feedbackemail.base.BaseFragment;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.project.gem.feedbackemail.R;

/**
 * Created by vanhop on 3/1/16.
 */
public class FeedBackFragment extends BaseFragment<FeedBackPresenter> implements FeedBackView {


    private TextView mOutputText;
    ProgressDialog mProgress;
    Toolbar toolbar;

    public FeedBackFragment() {
    }

    public FeedBackFragment(Toolbar toolbar) {
        this.toolbar = toolbar;
    }

    @Override
    public int setLayout() {
        return R.layout.send_mail_fragment;
    }

    @Override
    public FeedBackPresenter onCreatePresenter() {
        return new FeedBackPresenterImpl(this,this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        LinearLayout activityLayout = new LinearLayout(getContextBase());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        activityLayout.setLayoutParams(lp);
        activityLayout.setOrientation(LinearLayout.VERTICAL);
        activityLayout.setPadding(16, 16, 16, 16);

        ViewGroup.LayoutParams tlp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        mOutputText = new TextView(getContext());
        mOutputText.setLayoutParams(tlp);
        mOutputText.setPadding(16, 16, 16, 16);
        mOutputText.setVerticalScrollBarEnabled(true);
        mOutputText.setMovementMethod(new ScrollingMovementMethod());
        activityLayout.addView(mOutputText);
        mProgress = new ProgressDialog(getContextBase());
        mProgress.setMessage("Calling Gmail API ...");

        container.addView(activityLayout);

        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
      /*  if (id == R.id.action_attach) {

            //listFiles.add("ngay mai nghi viec.jpg");
            //customAdapter.notifyDataSetChanged();
           *//**//* if(listFiles.size() > 0){
                if(!listView.isShown())
                    listView.setVisibility(View.VISIBLE);
            }*//**//*
            getLayoutAttachmentFile().addView(new AttachmentFile(this, "ngay mai nghi hoc.jpg"));
        }
        if(id == R.id.action_send){
        }*/
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
        getPresenter().authenticateGoogleAccount();
    }

    @Override
    public void setToolBar(View view) {
        /*toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setTitle("Feedback");*/
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getPresenter().isGooglePlayServicesAvailable()) {
            getPresenter().refreshResults();
        } else {
            mOutputText.setText("Google Play Services required: " +
                    "after installing, close and relaunch this app.");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getPresenter().processActivityResult(requestCode, resultCode, data);

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void setNotification(String content) {
        mOutputText.setText(content);
    }

    @Override
    public void showGooglePlayServicesAvailabilityErrorDialog(int connectionStatusCode) {
        Dialog dialog = GooglePlayServicesUtil.getErrorDialog(
                connectionStatusCode,
                this.getActivity(),
                1002);
        dialog.show();
    }
}
