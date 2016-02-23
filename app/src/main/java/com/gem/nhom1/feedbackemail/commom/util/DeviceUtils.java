package com.gem.nhom1.feedbackemail.commom.util;

import android.content.Context;
import android.telephony.TelephonyManager;

/**
 * Created by phuongtd on 23/02/2016.
 */
public class DeviceUtils {
    public static String getDeviceId(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }
}