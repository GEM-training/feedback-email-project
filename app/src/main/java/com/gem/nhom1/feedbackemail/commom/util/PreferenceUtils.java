package com.gem.nhom1.feedbackemail.commom.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.gem.nhom1.feedbackemail.network.dto.ResponseUserInfoDTO;
import com.gem.nhom1.feedbackemail.network.dto.UserInfoDTO;
import com.google.gson.Gson;
import com.project.gem.feedbackemail.R;
import com.gem.nhom1.feedbackemail.commom.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by phuongtd on 23/02/2016.
 */
public class PreferenceUtils {

    public static void saveUserInfo(Context context , ResponseUserInfoDTO userInfo){
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.share_preferences_file),
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constant.USER_INFO, new Gson().toJson(userInfo));
        editor.commit();
    }


    public static ResponseUserInfoDTO getUserInfo(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.share_preferences_file),
                Context.MODE_PRIVATE);
        String userInfoString =  sharedPreferences.getString(Constant.USER_INFO, "");

        if(userInfoString == "")
            return null;
        try {
            return new Gson().fromJson(userInfoString , ResponseUserInfoDTO.class);

        } catch (Exception e){
                return null;
        }

    }

    public  static void clearUser(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.share_preferences_file),
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

}
