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
    public static String TOKEN_EMPTY = "";
    public static int USER_ID_EMPTY = -1;


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
            ResponseUserInfoDTO userInfoDTO = new Gson().fromJson(userInfoString , ResponseUserInfoDTO.class);
            return userInfoDTO;
        } catch (Exception e){
                return null;
        }

      /*  ResponseUserInfoDTO userInfoDTO= new ResponseUserInfoDTO();
        List<String> roles = new ArrayList<String>();
        roles.add("Customer");
        roles.add("Staff");
        roles.add("Dealer");

        userInfoDTO.setRole(roles);
        userInfoDTO.setUsername("phuongtd");
        userInfoDTO.setToken("sfjsbmf");

        return userInfoDTO;*/

    }

    public  static void clearUser(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.share_preferences_file),
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

}
