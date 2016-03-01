package com.gem.nhom1.feedbackemail.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gem.nhom1.feedbackemail.commom.Constant;
import com.gem.nhom1.feedbackemail.commom.logger.LogData;
import com.gem.nhom1.feedbackemail.commom.util.DeviceUtils;
import com.gem.nhom1.feedbackemail.commom.util.DialogUtils;
import com.gem.nhom1.feedbackemail.commom.util.NetworkUtil;
import com.gem.nhom1.feedbackemail.commom.util.PreferenceUtils;
import com.gem.nhom1.feedbackemail.network.ServiceBuilder;
import com.gem.nhom1.feedbackemail.network.Session;
import com.gem.nhom1.feedbackemail.screen.customer.CustomerActivity;
import com.gem.nhom1.feedbackemail.screen.login.LoginActivity;
import com.project.gem.feedbackemail.R;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by phuongtd on 01/03/2016.
 */
public class SelectRoleView {
    public static RelativeLayout getSelectRoleView (final Activity activity) {


        LayoutInflater inflate = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final List<String> roles = Session.getCurrentUser().getRoles();

        final RelativeLayout ll_select_role = (RelativeLayout) inflate.inflate(R.layout.select_roles_activity, null);

        LinearLayout layout_list_role = (LinearLayout) ll_select_role.findViewById(R.id.view_list_role);


        for (int i = 0; i < roles.size(); i++) {
            LinearLayout layoutRoleItem = (LinearLayout) inflate.inflate(R.layout.item_role_layout, null);

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);

            lp.setMargins(10, 10, 10, 10);


            ImageView imageView = (ImageView) layoutRoleItem.findViewById(R.id.icon_role);

            if (roles.get(i).equals(Constant.NAME_ROLE_CUSROMER)) {
                imageView.setImageDrawable(activity.getResources().getDrawable(R.drawable.customer));
            } else if (roles.get(i).equals(Constant.NAME_ROLE_STAFF)) {
                imageView.setImageDrawable(activity.getResources().getDrawable(R.drawable.staff));
            } else {
                imageView.setImageDrawable(activity.getResources().getDrawable(R.drawable.sale));
            }


            TextView tv_role_name = (TextView) layoutRoleItem.findViewById(R.id.tv_roleName);

            final String roleName = roles.get(i);

            tv_role_name.setText(roleName);

            layoutRoleItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (roleName.equals(Constant.NAME_ROLE_CUSROMER)) {
                        
                        Intent intent = new Intent(activity, CustomerActivity.class);
                        activity.startActivity(intent);
                        activity.finish();


                    } else {
                        Toast.makeText(activity, "Not Support", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            layout_list_role.addView(layoutRoleItem, lp);

        }

        LinearLayout layout_switch_account = (LinearLayout) ll_select_role.findViewById(R.id.layout_switch_account);

        layout_switch_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(NetworkUtil.isNetworkAvaiable(activity)){
                    ServiceBuilder.getService()
                            .logout(Session.getCurrentUser().getToken() , DeviceUtils.getDeviceId(activity)).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if(response.isSuccess()){
                                PreferenceUtils.clearUser(activity);

                                Session.removeUser();

                                Intent intent = new Intent(activity , LoginActivity.class);
                                activity.startActivity(intent);
                                activity.finish();

                            } else {
                                DialogUtils.showErrorAlert(activity , response.code() + " , " + response.message());
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            DialogUtils.showErrorAlert(activity, Constant.CONNECT_TO_SERVER_ERROE);
                        }
                    });
                } else{
                    DialogUtils.showErrorAlert(activity , Constant.NET_WORK_ERROR);
                }
            }
        });

        ImageView  imageView = (ImageView) ll_select_role.findViewById(R.id.select_role);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_select_role.setVisibility(View.GONE);
            }
        });
        return ll_select_role;
    }
}
