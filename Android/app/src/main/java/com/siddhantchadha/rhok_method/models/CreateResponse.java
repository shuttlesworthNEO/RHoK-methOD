package com.siddhantchadha.rhok_method.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreateResponse {

    @SerializedName("code")
    @Expose
    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
