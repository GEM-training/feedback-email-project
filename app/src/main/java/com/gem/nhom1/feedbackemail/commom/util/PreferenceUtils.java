package com.gem.nhom1.feedbackemail.commom.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.project.gem.feedbackemail.R;
import com.gem.nhom1.feedbackemail.commom.Constant;

/**
 * Created by phuongtd on 23/02/2016.
 */
public class PreferenceUtils {

    private static final String MyPREFERENCES = "MailboxPrfs";

    private static final String CURRENT_CUSTOMER_ID = "customerCurrentID";

    public static void saveToken(Context context , String token){
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.share_preferences_file),
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constant.TOKEN_KEY, token);
        editor.commit();
    }

    public static void saveCurrentUserId(Context context , int currentId){
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.share_preferences_file),
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Constant.CURRENT_USER_ID, currentId);
        editor.commit();
    }

    public static String getToken(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.share_preferences_file),
                Context.MODE_PRIVATE);
        return sharedPreferences.getString(Constant.TOKEN_KEY , "");
    }

    public static int getCurrentUserId(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.share_preferences_file),
                Context.MODE_PRIVATE);
        return sharedPreferences.getInt(Constant.CURRENT_USER_ID, -1);
    }

}
