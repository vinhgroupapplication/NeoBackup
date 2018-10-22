package com.newsaigonsoft.neoegov.Subjects;

/**
 * Created by Vinh on 7/5/2017.
 */

public class ItemsDocumentLookUp {
    long docReceiptId;
    String receiveDate;
    String numberOfSymbol;
    String docNumberFull;
    String briefContent;
    String IsScreen;




    public ItemsDocumentLookUp() {
    }

    public ItemsDocumentLookUp(long docReceiptId, String receiveDate, String numberOfSymbol, String docNumberFull, String briefContent, String isScreen) {
        this.docReceiptId = docReceiptId;
        this.receiveDate = receiveDate;
        this.numberOfSymbol = numberOfSymbol;
        this.docNumberFull = docNumberFull;
        this.briefContent = briefContent;
        IsScreen = isScreen;
    }




    public long getDocReceiptId() {
        return docReceiptId;
    }

    public void setDocReceiptId(long docReceiptId) {
        this.docReceiptId = docReceiptId;
    }

    public String getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(String receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getNumberOfSymbol() {
        return numberOfSymbol;
    }

    public void setNumberOfSymbol(String numberOfSymbol) {
        this.numberOfSymbol = numberOfSymbol;
    }

    public String getDocNumberFull() {
        return docNumberFull;
    }

    public void setDocNumberFull(String docNumberFull) {
        this.docNumberFull = docNumberFull;
    }

    public String getBriefContent() {
        return briefContent;
    }

    public void setBriefContent(String briefContent) {
        this.briefContent = briefContent;
    }

    public String getIsScreen() {
        return IsScreen;
    }

    public void setIsScreen(String isScreen) {
        IsScreen = isScreen;
    }
}
