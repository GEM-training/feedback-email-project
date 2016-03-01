package com.gem.nhom1.feedbackemail.commom.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by phuongtd on 29/02/2016.
 */
public class DateUtils {
    public static List<String> getSomeDateAgo(int day){
        List<String> days = new ArrayList<>();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        for(int i = 0; i < day ; i++){
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, i * (-1));
            days.add(dateFormat.format(cal.getTime()));
        }



        return days;
    }
}
