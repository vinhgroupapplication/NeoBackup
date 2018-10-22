package com.newsaigonsoft.neoegov.Subjects;

/**
 * Created by Vinh on 8/14/2017.
 */

public class NotifyRow {
    String Title;
    String Content;
    boolean showNull;
    long objectId;

    public NotifyRow(String title, String content, boolean showNull, long objectId) {
        Title = title;
        Content = content;
        this.showNull = showNull;
        this.objectId = objectId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public boolean isShowNull() {
        return showNull;
    }

    public void setShowNull(boolean showNull) {
        this.showNull = showNull;
    }

    public long getObjectId() {
        return objectId;
    }

    public void setObjectId(long objectId) {
        this.objectId = objectId;
    }
}
