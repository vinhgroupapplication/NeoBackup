package com.newsaigonsoft.neoegov.Subjects;

import java.io.Serializable;

/**
 * Created by Vinh on 9/25/2017.
 */

public class HandleChangeRow implements Serializable {
    String changeDate;
    String handleWayBefore;
    String reason;
    String userChange;
    String handleWayAfter;

    public HandleChangeRow(String changeDate, String handleWayBefore, String reason, String userChange, String handleWayAfter) {
        this.changeDate = changeDate;
        this.handleWayBefore = handleWayBefore;
        this.reason = reason;
        this.userChange = userChange;
        this.handleWayAfter = handleWayAfter;
    }

    public String getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(String changeDate) {
        this.changeDate = changeDate;
    }

    public String getHandleWayBefore() {
        return handleWayBefore;
    }

    public void setHandleWayBefore(String handleWayBefore) {
        this.handleWayBefore = handleWayBefore;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getUserChange() {
        return userChange;
    }

    public void setUserChange(String userChange) {
        this.userChange = userChange;
    }

    public String getHandleWayAfter() {
        return handleWayAfter;
    }

    public void setHandleWayAfter(String handleWayAfter) {
        this.handleWayAfter = handleWayAfter;
    }
}
