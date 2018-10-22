package com.newsaigonsoft.neoegov.GsonObject;

public class GsonSendConnects {

    /**
     * docConnectId : 30501
     * docSendId : 39002
     * publishNumber : 032/ VBDI/SL/VPTCT/2018
     * documentType : Bản sao lục
     * connecter : Cù Thị Kiều Oanh
     * signerName : Lê Văn Thơ
     * briefContentFull : 032/Test
     * briefContent : 032/Test
     * publishDate : 20/08/2018
     */

    private int docConnectId;
    private int docSendId;
    private String publishNumber;
    private String documentType;
    private String connecter;
    private String signerName;
    private String briefContentFull;
    private String briefContent;
    private String publishDate;

    public int getDocConnectId() {
        return docConnectId;
    }

    public void setDocConnectId(int docConnectId) {
        this.docConnectId = docConnectId;
    }

    public int getDocSendId() {
        return docSendId;
    }

    public void setDocSendId(int docSendId) {
        this.docSendId = docSendId;
    }

    public String getPublishNumber() {
        return publishNumber;
    }

    public void setPublishNumber(String publishNumber) {
        this.publishNumber = publishNumber;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getConnecter() {
        return connecter;
    }

    public void setConnecter(String connecter) {
        this.connecter = connecter;
    }

    public String getSignerName() {
        return signerName;
    }

    public void setSignerName(String signerName) {
        this.signerName = signerName;
    }

    public String getBriefContentFull() {
        return briefContentFull;
    }

    public void setBriefContentFull(String briefContentFull) {
        this.briefContentFull = briefContentFull;
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
}
