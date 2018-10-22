package com.newsaigonsoft.neoegov.GsonObject;

public class GsonNotify {


    public GsonNotify(String sender, String body, String title, int objectId, String sound, long dateSent, int badge, String labelCode ,  String content) {
        this.sender = sender;
        this.content = content;
        this.body = body;
        this.title = title;
        this.objectId = objectId;
        this.sound = sound;
        this.dateSent = dateSent;
        this.badge = badge;
        this.labelCode = labelCode;
    }

    /**
     * sender : Huỳnh Nguyễn Tùng Nhơn
     * content : Bạn nhận được 1 văn bản từ Huỳnh Nguyễn Tùng Nhơn
     * body : Bạn nhận được 1 văn bản từ Huỳnh Nguyễn Tùng Nhơn
     * title : Văn bản đến
     * objectId : 82603
     * sound : default
     * dateSent : 19/09/2018 14:29
     * badge : 1
     * labelCode : 16/502
     */




    private String sender;
    private String content;
    private String body;
    private String title;
    private int objectId;
    private String sound;
    private long dateSent;
    private int badge;
    private String labelCode;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public long getDateSent() {
        return dateSent;
    }

    public void setDateSent(long dateSent) {
        this.dateSent = dateSent;
    }

    public int getBadge() {
        return badge;
    }

    public void setBadge(int badge) {
        this.badge = badge;
    }

    public String getLabelCode() {
        return labelCode;
    }

    public void setLabelCode(String labelCode) {
        this.labelCode = labelCode;
    }
}
