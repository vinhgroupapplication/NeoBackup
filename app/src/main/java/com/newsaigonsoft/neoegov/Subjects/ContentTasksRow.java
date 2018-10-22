package com.newsaigonsoft.neoegov.Subjects;

import org.json.JSONArray;

/**
 * Created by Vinh on 10/4/2017.
 */

public class ContentTasksRow {
    String staff;
    String exchangeDate;
    int classify;
    String content;
    JSONArray mArrayAttachFile;

    public ContentTasksRow(String staff, String exchangeDate, int classify, String content, JSONArray mArrayAttachFile) {
        this.staff = staff;
        this.exchangeDate = exchangeDate;
        this.classify = classify;
        this.content = content;
        this.mArrayAttachFile = mArrayAttachFile;
    }

    public String getStaff() {
        return staff;
    }

    public void setStaff(String staff) {
        this.staff = staff;
    }

    public String getExchangeDate() {
        return exchangeDate;
    }

    public void setExchangeDate(String exchangeDate) {
        this.exchangeDate = exchangeDate;
    }

    public int getClassify() {
        return classify;
    }

    public void setClassify(int classify) {
        this.classify = classify;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public JSONArray getmArrayAttachFile() {
        return mArrayAttachFile;
    }

    public void setmArrayAttachFile(JSONArray mArrayAttachFile) {
        this.mArrayAttachFile = mArrayAttachFile;
    }
}
