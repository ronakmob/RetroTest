package com.rx.retro.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by paeder on 4/12/16.
 */

public class UserResponse {

    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("data")
    @Expose
    private User data;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }
}
