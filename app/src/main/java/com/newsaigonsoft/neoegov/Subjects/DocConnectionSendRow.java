package com.newsaigonsoft.neoegov.Subjects;

import org.json.JSONArray;

/**
 * Created by Vinh on 10/3/2017.
 */

public class DocConnectionSendRow extends DocConnectionReciverRow {

    String publishNumber;
    String signerName;

    public DocConnectionSendRow(String documentType,String briefContent, String briefContentFull , String publishDate, String publishNumber, String signerName) {
        super(documentType,briefContent, briefContentFull,  publishDate);
        this.publishNumber = publishNumber;
        this.signerName = signerName;
    }


    public String getPublishNumber() {
        return publishNumber;
    }

    public void setPublishNumber(String publishNumber) {
        this.publishNumber = publishNumber;
    }

    public String getSignerName() {
        return signerName;
    }

    public void setSignerName(String signerName) {
        this.signerName = signerName;
    }
}

