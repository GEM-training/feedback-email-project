package com.gem.nhom1.feedbackemail.network.entities;

/**
 * Created by vanhop on 2/18/16.
 */
public class UserInfo {

    private String username;
    private String password;
    private String deviceId;

    public UserInfo() {
    }

    public UserInfo(String username, String password, String deviceId) {
        this.username = username;
        this.password = password;
        this.deviceId = deviceId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
