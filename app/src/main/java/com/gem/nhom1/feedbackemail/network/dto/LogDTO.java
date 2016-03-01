package com.gem.nhom1.feedbackemail.network.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by phuongtd on 01/03/2016.
 */
public class LogDTO implements Serializable {
    private int id;
    private String username;
    private String deviceId;
    private String appVer;
    private String action;
    private String status;
    private Date timeStart;
    private Date timeEnd;
    private String description;

    public LogDTO() {
    }

    public LogDTO(int id, String username, String deviceId, String appVer, String action, String status, Date timeStart, Date timeEnd, String description) {
        this.id = id;
        this.username = username;
        this.deviceId = deviceId;
        this.appVer = appVer;
        this.action = action;
        this.status = status;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getAppVer() {
        return appVer;
    }

    public void setAppVer(String appVer) {
        this.appVer = appVer;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Date timeStart) {
        this.timeStart = timeStart;
    }

    public Date getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Date timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
