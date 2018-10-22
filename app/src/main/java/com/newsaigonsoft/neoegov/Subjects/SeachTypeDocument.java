package com.newsaigonsoft.neoegov.Subjects;

/**
 * Created by Vinh on 7/26/2017.
 */

public class SeachTypeDocument {
    String TypeDocumentName;
    long TypeDocumentId;


    public SeachTypeDocument(String typeDocumentName, long typeDocumentId) {
        TypeDocumentName = typeDocumentName;
        TypeDocumentId = typeDocumentId;
    }

    public SeachTypeDocument() {
    }

    public String getTypeDocumentName() {
        return TypeDocumentName;
    }

    public void setTypeDocumentName(String typeDocumentName) {
        TypeDocumentName = typeDocumentName;
    }

    public long getTypeDocumentId() {
        return TypeDocumentId;
    }

    public void setTypeDocumentId(long typeDocumentId) {
        TypeDocumentId = typeDocumentId;
    }
}
