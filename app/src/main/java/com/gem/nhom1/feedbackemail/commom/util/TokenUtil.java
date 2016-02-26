package com.gem.nhom1.feedbackemail.commom.util;


import com.gem.nhom1.feedbackemail.network.dto.TokenInfoDTO;
import com.gem.nhom1.feedbackemail.network.entities.User;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by vanhop on 2/17/16.
 */
public class TokenUtil {

    public static String generateAccessToken(User user){

        String str = user.getUsername() + user.getPassword() + System.currentTimeMillis();

        MessageDigest crypt = null;
        try {
            crypt = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        crypt.reset();
        try {
            crypt.update(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return new BigInteger(1, crypt.digest()).toString(16);
    }

    public static boolean isExpiration(TokenInfoDTO tokenInfo) {
        return tokenInfo != null && (tokenInfo.getExpirationTime() < System.currentTimeMillis() ? true : false);
    }

}
