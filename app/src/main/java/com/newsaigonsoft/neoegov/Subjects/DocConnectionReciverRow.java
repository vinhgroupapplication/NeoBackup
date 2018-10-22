package com.newsaigonsoft.neoegov.Subjects;

import org.json.JSONArray;

import java.io.Serializable;

/**
 * Created by Vinh on 10/2/2017.
 */

public class DocConnectionReciverRow implements Serializable {
    String documentType;
    String publishCategory;
    String briefContentFull;
    String docNumberFull;
    String numberOfSymbol;
    String briefContent;
    String publishDate;
//    JSONArray mArrayAttachFile;

    public DocConnectionReciverRow(String documentType, String briefContentFull, String briefContent, String publishDate
//            , JSONArray mArrayAttachFile
    ) {
        this.documentType = documentType;
        this.briefContentFull = briefContentFull;
        this.briefContent = briefContent;
        this.publishDate = publishDate;
//        this.mArrayAttachFile = mArrayAttachFile;
    }

    public DocConnectionReciverRow(String documentType, String publishCategory, String briefContentFull, String docNumberFull, String numberOfSymbol, String briefContent, String publishDate
//            , JSONArray mArrayAttachFile
    ) {
        this.documentType = documentType;
        this.publishCategory = publishCategory;
        this.briefContentFull = briefContentFull;
        this.docNumberFull = docNumberFull;
        this.numberOfSymbol = numberOfSymbol;
        this.briefContent = briefContent;
        this.publishDate = publishDate;
//        this.mArrayAttachFile = mArrayAttachFile;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getPublishCategory() {
        return publishCategory;
    }

    public void setPublishCategory(String publishCategory) {
        this.publishCategory = publishCategory;
    }

    public String getBriefContentFull() {
        return briefContentFull;
    }

    public void setBriefContentFull(String briefContentFull) {
        this.briefContentFull = briefContentFull;
    }

    public String getDocNumberFull() {
        return docNumberFull;
    }

    public void setDocNumberFull(String docNumberFull) {
        this.docNumberFull = docNumberFull;
    }

    public String getNumberOfSymbol() {
        return numberOfSymbol;
    }

    public void setNumberOfSymbol(String numberOfSymbol) {
        this.numberOfSymbol = numberOfSymbol;
    }

    public String getBriefContent() {
        return briefContent;
    }

    public void setBriefContent(String briefContent) {
        this.briefContent = briefContent;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

//    public JSONArray getmArrayAttachFile() {
//        return mArrayAttachFile;
//    }
//
//    public void setmArrayAttachFile(JSONArray mArrayAttachFile) {
//        this.mArrayAttachFile = mArrayAttachFile;
//    }
}
