package com.newsaigonsoft.neoegov.Subjects;

import org.json.JSONArray;

/**
 * Created by Vinh on 9/23/2017.
 */

public class FeedBackRow {
    String Content;
    String OrganizationName;
    String UserName;
    String CreateDate;
    JSONArray mArrayAttachFile;

    public FeedBackRow(String content, String organizationName, String userName, String createDate, JSONArray mArrayAttachFile) {
        Content = content;
        OrganizationName = organizationName;
        UserName = userName;
        CreateDate = createDate;
        this.mArrayAttachFile = mArrayAttachFile;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getOrganizationName() {
        return OrganizationName;
    }

    public void setOrganizationName(String organizationName) {
        OrganizationName = organizationName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String createDate) {
        CreateDate = createDate;
    }

    public JSONArray getmArrayAttachFile() {
        return mArrayAttachFile;
    }

    public void setmArrayAttachFile(JSONArray mArrayAttachFile) {
        this.mArrayAttachFile = mArrayAttachFile;
    }
}
