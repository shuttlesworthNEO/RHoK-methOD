package com.siddhantchadha.rhok_method.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by siddh on 7/16/2017.
 */

public class LoginResponse {
    @SerializedName("user_key")
    @Expose
    private String userKey;
    @SerializedName("code")
    @Expose
    private Integer code;

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
