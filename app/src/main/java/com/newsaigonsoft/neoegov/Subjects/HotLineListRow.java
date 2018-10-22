package com.newsaigonsoft.neoegov.Subjects;

/**
 * Created by Vinh on 10/31/2017.
 */

public class HotLineListRow {
    long messageReflectId;
    String peopleReflect;
    String phoneReflect;
    String content;
    String receiveDate;

    public HotLineListRow(long messageReflectId, String peopleReflect, String phoneReflect, String content, String receiveDate) {
        this.messageReflectId = messageReflectId;
        this.peopleReflect = peopleReflect;
        this.phoneReflect = phoneReflect;
        this.content = content;
        this.receiveDate = receiveDate;
    }

    public long getMessageReflectId() {
        return messageReflectId;
    }

    public void setMessageReflectId(long messageReflectId) {
        this.messageReflectId = messageReflectId;
    }

    public String getPeopleReflect() {
        return peopleReflect;
    }

    public void setPeopleReflect(String peopleReflect) {
        this.peopleReflect = peopleReflect;
    }

    public String getPhoneReflect() {
        return phoneReflect;
    }

    public void setPhoneReflect(String phoneReflect) {
        this.phoneReflect = phoneReflect;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(String receiveDate) {
        this.receiveDate = receiveDate;
    }
}
