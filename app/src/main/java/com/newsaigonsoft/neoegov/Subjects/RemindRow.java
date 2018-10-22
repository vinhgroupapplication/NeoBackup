package com.newsaigonsoft.neoegov.Subjects;

/**
 * Created by Vinh on 8/26/2017.
 */

public class RemindRow {
    boolean Sms;
    boolean Email;
    String Content;
    String ProcessPosition;
    String Number;
    String EmailName;

    public RemindRow(boolean sms, boolean email, String content, String processPosition, String number, String emailName) {
        Sms = sms;
        Email = email;
        Content = content;
        ProcessPosition = processPosition;
        Number = number;
        EmailName = emailName;
    }

    public boolean isSms() {
        return Sms;
    }

    public void setSms(boolean sms) {
        Sms = sms;
    }

    public boolean isEmail() {
        return Email;
    }

    public void setEmail(boolean email) {
        Email = email;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getProcessPosition() {
        return ProcessPosition;
    }

    public void setProcessPosition(String processPosition) {
        ProcessPosition = processPosition;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getEmailName() {
        return EmailName;
    }

    public void setEmailName(String emailName) {
        EmailName = emailName;
    }
}
