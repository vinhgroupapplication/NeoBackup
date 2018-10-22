package com.newsaigonsoft.neoegov.Subjects;

import org.json.JSONArray;

/**
 * Created by Vinh on 12/1/2017.
 */

public class DocConnectionLocalRow {
    String docNumber;
    String documentBook;
    String documentType;
    String signerName;
    String createDateDoc;
    String briefContent;
    String briefContentFull;
    JSONArray mArrayAttachFile;

    public DocConnectionLocalRow(String docNumber, String documentBook, String documentType, String signerName, String createDateDoc, String briefContent, String briefContentFull, JSONArray mArrayAttachFile) {
        this.docNumber = docNumber;
        this.documentBook = documentBook;
        this.documentType = documentType;
        this.signerName = signerName;
        this.createDateDoc = createDateDoc;
        this.briefContent = briefContent;
        this.briefContentFull = briefContentFull;
        this.mArrayAttachFile = mArrayAttachFile;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public String getDocumentBook() {
        return documentBook;
    }

    public void setDocumentBook(String documentBook) {
        this.documentBook = documentBook;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getSignerName() {
        return signerName;
    }

    public void setSignerName(String signerName) {
        this.signerName = signerName;
    }

    public String getCreateDateDoc() {
        return createDateDoc;
    }

    public void setCreateDateDoc(String createDateDoc) {
        this.createDateDoc = createDateDoc;
    }

    public String getBriefContent() {
        return briefContent;
    }

    public void setBriefContent(String briefContent) {
        this.briefContent = briefContent;
    }

    public String getBriefContentFull() {
        return briefContentFull;
    }

    public void setBriefContentFull(String briefContentFull) {
        this.briefContentFull = briefContentFull;
    }

    public JSONArray getmArrayAttachFile() {
        return mArrayAttachFile;
    }

    public void setmArrayAttachFile(JSONArray mArrayAttachFile) {
        this.mArrayAttachFile = mArrayAttachFile;
    }
}
