package com.newsaigonsoft.neoegov.Subjects;

/**
 * Created by VinhCN on 4/22/2017.
 */

public class ItemsDocument extends ItemsDocumentLookUp {
    String TitleDocument;
    String TimeDocument;
    String sumaryDocument;
    long DocumentID;
    int ResourceCodeId;
    boolean New;
    boolean Viewed;
    int JobType;
    boolean OverNetWork;
    String Urgency;


    public ItemsDocument(String titleDocument, String timeDocument, String sumaryDocument,
                         long documentID, int resourceCodeId, boolean newx, boolean seenDocument
            , int jobType, boolean overNetWork, String urgency
    ) {
        TitleDocument = titleDocument;
        TimeDocument = timeDocument;
        this.sumaryDocument = sumaryDocument;
        DocumentID = documentID;
        ResourceCodeId = resourceCodeId;
        New = newx;
        Viewed = seenDocument;
        JobType = jobType;
        OverNetWork = overNetWork;
        this.Urgency = urgency;
    }


    public ItemsDocument() {

    }


    public String getUrgency() {
        return Urgency;
    }

    public void setUrgency(String urgency) {
        Urgency = urgency;
    }

    public boolean isOverNetWork() {
        return OverNetWork;
    }

    public void setOverNetWork(boolean overNetWork) {
        OverNetWork = overNetWork;
    }

    public boolean isNew() {
        return New;
    }

    public void setNew(boolean aNew) {
        New = aNew;
    }

    public boolean isViewed() {
        return Viewed;
    }

    public void setViewed(boolean viewed) {
        Viewed = viewed;
    }

    public int getJobType() {
        return JobType;
    }

    public void setJobType(int jobType) {
        JobType = jobType;
    }

    public String getTitleDocument() {
        return TitleDocument;
    }

    public void setTitleDocument(String titleDocument) {
        TitleDocument = titleDocument;
    }

    public String getTimeDocument() {
        return TimeDocument;
    }

    public void setTimeDocument(String timeDocument) {
        TimeDocument = timeDocument;
    }

    public String getSumaryDocument() {
        return sumaryDocument;
    }

    public void setSumaryDocument(String sumaryDocument) {
        this.sumaryDocument = sumaryDocument;
    }

    public long getDocumentID() {
        return DocumentID;
    }

    public void setDocumentID(long documentID) {
        DocumentID = documentID;
    }

    public int getResourceCodeId() {
        return ResourceCodeId;
    }

    public void setResourceCodeId(int resourceCodeId) {
        ResourceCodeId = resourceCodeId;
    }
}
