package com.siddhantchadha.rhok_method.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by siddh on 7/16/2017.
 */

public class FeedResponse {
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("data")
    @Expose
    private List<Query> data = null;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<Query> getData() {
        return data;
    }

    public void setData(List<Query> data) {
        this.data = data;
    }
}
