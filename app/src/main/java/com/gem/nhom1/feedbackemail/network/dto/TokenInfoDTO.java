package com.gem.nhom1.feedbackemail.network.dto;

import com.gem.nhom1.feedbackemail.base.BaseDTO;
import com.gem.nhom1.feedbackemail.network.entities.User;

import java.io.Serializable;

/**
 * Created by phuongtd on 23/02/2016.
 */
public class TokenInfoDTO extends BaseDTO implements Serializable {
    private long expirationTime;
    private String access_token;
    private User user;

    private String deviceId;

    public TokenInfoDTO() {
    }

    public TokenInfoDTO(long expirationTime, String access_token, User user) {
        this.expirationTime = expirationTime;
        this.access_token = access_token;
        this.user = user;
    }

    public TokenInfoDTO(long expirationTime, String access_token, User user, String deviceId) {
        this.expirationTime = expirationTime;
        this.access_token = access_token;
        this.user = user;
        this.deviceId = deviceId;
    }

    public long getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(long expirationTime) {
        this.expirationTime = expirationTime;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
