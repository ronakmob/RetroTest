package com.rx.retro.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paeder on 4/12/16.
 */
public class User {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("locale")
    @Expose
    private String locale;
    @SerializedName("termsAndPolicyAccepted")
    @Expose
    private boolean termsAndPolicyAccepted;
    @SerializedName("loginStreak")
    @Expose
    private int loginStreak;
    @SerializedName("lastLogin")
    @Expose
    private String lastLogin;
    @SerializedName("version")
    @Expose
    private String version;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("acceptMarComm")
    @Expose
    private boolean acceptMarComm;
    @SerializedName("messagesLastViewed")
    @Expose
    private List<Object> messagesLastViewed = new ArrayList<Object>();
    @SerializedName("daysSinceInspPush")
    @Expose
    private int daysSinceInspPush;
    @SerializedName("daysSinceEducPush")
    @Expose
    private int daysSinceEducPush;
    @SerializedName("receiveDeviceMessage")
    @Expose
    private boolean receiveDeviceMessage;
    @SerializedName("acknowledgeTeamInvite")
    @Expose
    private boolean acknowledgeTeamInvite;
    @SerializedName("acknowledgeReminder")
    @Expose
    private boolean acknowledgeReminder;
    @SerializedName("acknowledgeOptOut")
    @Expose
    private boolean acknowledgeOptOut;
    @SerializedName("acknowledgeInvite")
    @Expose
    private boolean acknowledgeInvite;
    @SerializedName("acknowledgeAccess")
    @Expose
    private boolean acknowledgeAccess;
    @SerializedName("roleSelectionScreenPassed")
    @Expose
    private boolean roleSelectionScreenPassed;
    @SerializedName("alerts")
    @Expose
    private List<Object> alerts = new ArrayList<Object>();
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("emailVerification")
    @Expose
    private boolean emailVerification;

    @SerializedName("externalSources")
    @Expose
    private List<Object> externalSources = new ArrayList<Object>();
    @SerializedName("tzOffset")
    @Expose
    private String tzOffset;
    @SerializedName("tempPassword")
    @Expose
    private boolean tempPassword;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }
}
