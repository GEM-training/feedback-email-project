package com.gem.nhom1.feedbackemail.network.dto;

import java.io.Serializable;

/**
 * Created by phuongtd on 23/02/2016.
 */
public class ResponseDTO<DTO>  implements Serializable{
    private String status;
    private String message;

    private DTO data;

    public ResponseDTO() {
    }

    public ResponseDTO(String status, String message, DTO data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DTO getData() {
        return data;
    }

    public void setData(DTO data) {
        this.data = data;
    }
}
