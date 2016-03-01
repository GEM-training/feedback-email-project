
package com.gem.nhom1.feedbackemail.screen.roleselect;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gem.nhom1.feedbackemail.base.BaseActivity;
import com.gem.nhom1.feedbackemail.commom.Constant;
import com.gem.nhom1.feedbackemail.commom.logger.LogData;
import com.gem.nhom1.feedbackemail.network.Session;
import com.gem.nhom1.feedbackemail.screen.customer.CustomerActivity;
import com.gem.nhom1.feedbackemail.screen.login.LoginActivity;
import com.project.gem.feedbackemail.R;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * Created by phuong on 2/27/2016.
 */

public class SelectRoleActivity extends BaseActivity<SelectRolePresenter> implements SelectRoleView {

    @Bind(R.id.view_list_role)
    LinearLayout layout_list_role;

    @Bind(R.id.layout_switch_account)
    LinearLayout layout_swirch_account;

    @Override
    protected int getLayoutId() {
        return R.layout.select_roles_activity;
    }

    @Override
    public SelectRolePresenter onCreatePresenter() {
        return new SelectRolePresenterImpl(this);
    }

    @Override
    public Context getContextBase() {
        return getBaseContext();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        LayoutInflater inflate = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final List<String> roles = Session.getCurrentUser().getRoles();

        for(int i= 0 ; i < roles.size() ; i++){
            LinearLayout layoutRoleItem = (LinearLayout) inflate.inflate(R.layout.item_role_layout , null);

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT ,
                    ViewGroup.LayoutParams.WRAP_CONTENT);

            lp.setMargins(10, 10, 10, 10);


            ImageView imageView = (ImageView) layoutRoleItem.findViewById(R.id.icon_role);

            if(roles.get(i).equals(Constant.NAME_ROLE_CUSROMER)){
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.customer));
            } else if (roles.get(i).equals(Constant.NAME_ROLE_STAFF)){
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.staff));
            } else {
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.sale));
            }


            TextView tv_role_name = (TextView) layoutRoleItem.findViewById(R.id.tv_roleName);

            final String roleName = roles.get(i);

            tv_role_name.setText(roleName);

            layoutRoleItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (roleName.equals(Constant.NAME_ROLE_CUSROMER)) {


                        Intent intent = new Intent(SelectRoleActivity.this, CustomerActivity.class);
                        startActivity(intent);
                        finish();


                    } else {
                        Toast.makeText(SelectRoleActivity.this, "Not Support", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            layout_list_role.addView(layoutRoleItem, lp);
        }


    }

    @Override
    public void changeAccountSuccess() {
        Intent intent = new Intent(SelectRoleActivity.this  , LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.layout_switch_account)
    public void changrAccount(){
        getPresenter().changeAccount();
    }
}

