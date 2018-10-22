package com.newsaigonsoft.neoegov.Subjects;

/**
 * Created by Vinh on 10/30/2017.
 */

public class HotLineRow {
    long organizationId;
    String organizationName;
    int process;
    int processType;
    int processOnDue;
    int processOnDueType;
    int processDemurrage;
    int processDemurrageType;

    public HotLineRow(long organizationId, String organizationName, int process, int processType, int processOnDue, int processOnDueType, int processDemurrage, int processDemurrageType) {
        this.organizationId = organizationId;
        this.organizationName = organizationName;
        this.process = process;
        this.processType = processType;
        this.processOnDue = processOnDue;
        this.processOnDueType = processOnDueType;
        this.processDemurrage = processDemurrage;
        this.processDemurrageType = processDemurrageType;
    }


    public long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(long organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public int getProcess() {
        return process;
    }

    public void setProcess(int process) {
        this.process = process;
    }

    public int getProcessType() {
        return processType;
    }

    public void setProcessType(int processType) {
        this.processType = processType;
    }

    public int getProcessOnDue() {
        return processOnDue;
    }

    public void setProcessOnDue(int processOnDue) {
        this.processOnDue = processOnDue;
    }

    public int getProcessOnDueType() {
        return processOnDueType;
    }

    public void setProcessOnDueType(int processOnDueType) {
        this.processOnDueType = processOnDueType;
    }

    public int getProcessDemurrage() {
        return processDemurrage;
    }

    public void setProcessDemurrage(int processDemurrage) {
        this.processDemurrage = processDemurrage;
    }

    public int getProcessDemurrageType() {
        return processDemurrageType;
    }

    public void setProcessDemurrageType(int processDemurrageType) {
        this.processDemurrageType = processDemurrageType;
    }
}
