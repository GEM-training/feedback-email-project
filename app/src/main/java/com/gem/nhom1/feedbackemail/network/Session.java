package com.gem.nhom1.feedbackemail.network;


import com.gem.nhom1.feedbackemail.network.dto.ResponseUserInfoDTO;

/**
 * Created by huylv on 19/02/2016.
 */
public class Session {
    private static ResponseUserInfoDTO user;

    public static void setUser(ResponseUserInfoDTO user) {
        Session.user=user;
    }

    public static void removeUser(){
        if(user!=null){
            user =null;
        }
    }

    public static ResponseUserInfoDTO getCurrentUser(){
        return user;
    }
}