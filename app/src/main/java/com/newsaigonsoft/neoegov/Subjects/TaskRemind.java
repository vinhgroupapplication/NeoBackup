package com.newsaigonsoft.neoegov.Subjects;

/**
 * Created by Vinh on 8/23/2017.
 */

public class TaskRemind {
    int reported;
    int reportedType;
    int process;
    int processType;
    int processOnDue;
    int processOnDueType;
    int processNearDemurrage;
    int processNearDemurrageType;
    int processDemurrage;
    int processDemurrageType;
    int total;
    int totalType;


    public TaskRemind(int reported, int reportedType, int process, int processType, int processOnDue, int processOnDueType, int processNearDemurrage, int processNearDemurrageType, int processDemurrage, int processDemurrageType, int total, int totalType) {
        this.reported = reported;
        this.reportedType = reportedType;
        this.process = process;
        this.processType = processType;
        this.processOnDue = processOnDue;
        this.processOnDueType = processOnDueType;
        this.processNearDemurrage = processNearDemurrage;
        this.processNearDemurrageType = processNearDemurrageType;
        this.processDemurrage = processDemurrage;
        this.processDemurrageType = processDemurrageType;
        this.total = total;
        this.totalType = totalType;
    }

    public int getReported() {
        return reported;
    }

    public void setReported(int reported) {
        this.reported = reported;
    }

    public int getReportedType() {
        return reportedType;
    }

    public void setReportedType(int reportedType) {
        this.reportedType = reportedType;
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

    public int getProcessNearDemurrage() {
        return processNearDemurrage;
    }

    public void setProcessNearDemurrage(int processNearDemurrage) {
        this.processNearDemurrage = processNearDemurrage;
    }

    public int getProcessNearDemurrageType() {
        return processNearDemurrageType;
    }

    public void setProcessNearDemurrageType(int processNearDemurrageType) {
        this.processNearDemurrageType = processNearDemurrageType;
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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalType() {
        return totalType;
    }

    public void setTotalType(int totalType) {
        this.totalType = totalType;
    }
}

