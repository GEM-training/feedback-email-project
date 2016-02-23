package com.gem.nhom1.feedbackemail.commom;

import com.gem.nhom1.feedbackemail.network.dto.TokenInfoDTO;
import com.gem.nhom1.feedbackemail.network.entities.User;

/**
 * Created by phuongtd on 28/01/2016.
 */
public class Constant {
    public final static String BILL_PENDING = "0";
    public final static String BILL_COMPLETE = "1";
    public final static String BILL_CANCEL = "2";

    public final static String RESPONSE_STATUS_SUSSCESS = "success";
    public final static String RESPONSE_STATUS_ERROR = "error";
    public final static String LOGIN_FAIL = "login fail";

    public  static  String MY_TOKEN ;

    public final static String USER_ID = "user_id";

    public static final String STRING_ACCESS_TOKEN = "access_token";

    public static final String TOKEN_KEY = "token";

    public static final String SQL_DB_NAME = "nhom1db";

    public static final String CURRENT_USER_ID = "current_user_id";


     /*
     *
     * SQL Status*/

    public static final int INSERT_ERROR = -1;
    public static final int DUPLICATE_INSERT = -2;


    ////////////////////////////////////////////////////////////////////////

    public static TokenInfoDTO tokenInfoDTO = null;
    public static User user = null;
    public static boolean offLineMode = false;

}
