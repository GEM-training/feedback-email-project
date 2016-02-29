package com.gem.nhom1.feedbackemail.network.dto;

import com.gem.nhom1.feedbackemail.base.BaseDTO;

import java.util.List;


/**
 * Created by huylv on 19/02/2016.
 */
public class ResponseUserInfoDTO extends BaseDTO {
    String username;
    String token;
    List<String> roles;

    public ResponseUserInfoDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
