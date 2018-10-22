package com.newsaigonsoft.neoegov.Subjects;

/**
 * Created by Vinh on 5/8/2017.
 */

public class SearchRow {
    boolean advanced = false;
    String keywords = "";
    long organizationId = 0;
    long fromReceiveDate = 0;
    long toReceiveDate = 0;
    long fromProcessDate = 0;
    long toProcessDate = 0;
    long fromDueDate = 0;
    long toDueDate = 0;
    String content = "";
    String docNumberFull = "";
    String numberOfSymbol = "";
    long docTypeId = 0;
    String briefContent = "";
    long fromPublishDate = 0;
    long toPublishDate = 0;
    String publishNumber = "";
    long fromCreateDate = 0;
    long toCreateDate = 0;
    String docNumber = "";
    long fromSignDate = 0;
    long toSignDate = 0;

    public SearchRow() {
        advanced = false;
        keywords = "";
        organizationId = 0;
        fromReceiveDate = 0;
        toReceiveDate = 0;
        fromProcessDate = 0;
        toProcessDate = 0;
        fromDueDate = 0;
        toDueDate = 0;
        content = "";
        docNumberFull = "";
        numberOfSymbol = "";
        docTypeId = 0;
        briefContent = "";
        fromPublishDate = 0;
        toPublishDate = 0;
        publishNumber = "";
        fromCreateDate = 0;
        toCreateDate = 0;
        docNumber = "";
        fromSignDate = 0;
        toSignDate = 0;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public long getFromSignDate() {
        return fromSignDate;
    }

    public void setFromSignDate(long fromSignDate) {
        this.fromSignDate = fromSignDate;
    }

    public long getToSignDate() {
        return toSignDate;
    }

    public void setToSignDate(long toSignDate) {
        this.toSignDate = toSignDate;
    }

    public String getPublishNumber() {
        return publishNumber;
    }

    public void setPublishNumber(String publishNumber) {
        this.publishNumber = publishNumber;
    }

    public long getFromCreateDate() {
        return fromCreateDate;
    }

    public void setFromCreateDate(long fromCreateDate) {
        this.fromCreateDate = fromCreateDate;
    }

    public long getToCreateDate() {
        return toCreateDate;
    }

    public void setToCreateDate(long toCreateDate) {
        this.toCreateDate = toCreateDate;
    }

    public long getFromPublishDate() {
        return fromPublishDate;
    }

    public void setFromPublishDate(long fromPublishDate) {
        this.fromPublishDate = fromPublishDate;
    }

    public long getToPublishDate() {
        return toPublishDate;
    }

    public void setToPublishDate(long toPublishDate) {
        this.toPublishDate = toPublishDate;
    }

    public boolean isAdvanced() {
        return advanced;
    }

    public void setAdvanced(boolean advanced) {
        this.advanced = advanced;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(long organizationId) {
        this.organizationId = organizationId;
    }

    public long getFromReceiveDate() {
        return fromReceiveDate;
    }

    public void setFromReceiveDate(long fromReceiveDate) {
        this.fromReceiveDate = fromReceiveDate;
    }

    public long getToReceiveDate() {
        return toReceiveDate;
    }

    public void setToReceiveDate(long toReceiveDate) {
        this.toReceiveDate = toReceiveDate;
    }

    public long getFromProcessDate() {
        return fromProcessDate;
    }

    public void setFromProcessDate(long fromProcessDate) {
        this.fromProcessDate = fromProcessDate;
    }

    public long getToProcessDate() {
        return toProcessDate;
    }

    public void setToProcessDate(long toProcessDate) {
        this.toProcessDate = toProcessDate;
    }

    public long getFromDueDate() {
        return fromDueDate;
    }

    public void setFromDueDate(long fromDueDate) {
        this.fromDueDate = fromDueDate;
    }

    public long getToDueDate() {
        return toDueDate;
    }

    public void setToDueDate(long toDueDate) {
        this.toDueDate = toDueDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public long getDocTypeId() {
        return docTypeId;
    }

    public void setDocTypeId(long docTypeId) {
        this.docTypeId = docTypeId;
    }

    public String getBriefContent() {
        return briefContent;
    }

    public void setBriefContent(String briefContent) {
        this.briefContent = briefContent;
    }
}
