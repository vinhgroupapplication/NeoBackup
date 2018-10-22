package com.newsaigonsoft.neoegov.Subjects;

/**
 * Created by Vinh on 11/27/2017.
 */

public class StatisticalDPMListRow {
    long objectId;
    String Name;
    int processeOnTime;
    int processeOnTimeType;
    int processeDemurrage;
    int processeDemurrageType;

    public StatisticalDPMListRow(long objectId, String name, int processeOnTime, int processeOnTimeType, int processeDemurrage, int processeDemurrageType) {
        this.objectId = objectId;
        Name = name;
        this.processeOnTime = processeOnTime;
        this.processeOnTimeType = processeOnTimeType;
        this.processeDemurrage = processeDemurrage;
        this.processeDemurrageType = processeDemurrageType;
    }

    public long getObjectId() {
        return objectId;
    }

    public void setObjectId(long objectId) {
        this.objectId = objectId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getProcesseOnTime() {
        return processeOnTime;
    }

    public void setProcesseOnTime(int processeOnTime) {
        this.processeOnTime = processeOnTime;
    }

    public int getProcesseOnTimeType() {
        return processeOnTimeType;
    }

    public void setProcesseOnTimeType(int processeOnTimeType) {
        this.processeOnTimeType = processeOnTimeType;
    }

    public int getProcesseDemurrage() {
        return processeDemurrage;
    }

    public void setProcesseDemurrage(int processeDemurrage) {
        this.processeDemurrage = processeDemurrage;
    }

    public int getProcesseDemurrageType() {
        return processeDemurrageType;
    }

    public void setProcesseDemurrageType(int processeDemurrageType) {
        this.processeDemurrageType = processeDemurrageType;
    }
}
