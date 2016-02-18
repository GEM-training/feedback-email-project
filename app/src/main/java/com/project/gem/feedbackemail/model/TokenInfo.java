package com.project.gem.feedbackemail.model;


/**
 * Created by phuongtd on 18/02/2016.
 */
public class TokenInfo {

    private long expirationTime;
    private String access_token;
    private User user;

    public TokenInfo() {
    }

    public TokenInfo(long expirationTime, String access_token, User user) {
        this.expirationTime = expirationTime;
        this.access_token = access_token;
        this.user = user;
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


}
