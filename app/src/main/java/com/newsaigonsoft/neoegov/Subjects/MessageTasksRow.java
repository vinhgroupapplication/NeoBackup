package com.newsaigonsoft.neoegov.Subjects;

/**
 * Created by Vinh on 10/3/2017.
 */

public class MessageTasksRow {
    boolean sendSMS;
    boolean sendEmail;
    boolean sentSMS;
    boolean sentEmail;
    String executionUnit;
    String contentSMS;
    String contentEmail;

    public MessageTasksRow(boolean sendSMS, boolean sendEmail, boolean sentSMS, boolean sentEmail, String executionUnit, String contentSMS, String contentEmail) {
        this.sendSMS = sendSMS;
        this.sendEmail = sendEmail;
        this.sentSMS = sentSMS;
        this.sentEmail = sentEmail;
        this.executionUnit = executionUnit;
        this.contentSMS = contentSMS;
        this.contentEmail = contentEmail;
    }

    public boolean isSendSMS() {
        return sendSMS;
    }

    public void setSendSMS(boolean sendSMS) {
        this.sendSMS = sendSMS;
    }

    public boolean isSendEmail() {
        return sendEmail;
    }

    public void setSendEmail(boolean sendEmail) {
        this.sendEmail = sendEmail;
    }

    public boolean isSentSMS() {
        return sentSMS;
    }

    public void setSentSMS(boolean sentSMS) {
        this.sentSMS = sentSMS;
    }

    public boolean isSentEmail() {
        return sentEmail;
    }

    public void setSentEmail(boolean sentEmail) {
        this.sentEmail = sentEmail;
    }

    public String getExecutionUnit() {
        return executionUnit;
    }

    public void setExecutionUnit(String executionUnit) {
        this.executionUnit = executionUnit;
    }

    public String getContentSMS() {
        return contentSMS;
    }

    public void setContentSMS(String contentSMS) {
        this.contentSMS = contentSMS;
    }

    public String getContentEmail() {
        return contentEmail;
    }

    public void setContentEmail(String contentEmail) {
        this.contentEmail = contentEmail;
    }
}
