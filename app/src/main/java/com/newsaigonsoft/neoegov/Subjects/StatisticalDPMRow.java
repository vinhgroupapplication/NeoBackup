package com.newsaigonsoft.neoegov.Subjects;

/**
 * Created by Vinh on 12/28/2017.
 */

public class StatisticalDPMRow {

    long objectId;
    String Name;
    int total;
    int totalType;
    int processed;
    int processedType;
    int processedOnTime;
    int processedOnTimeType;
    int processedDemurrage;
    int processedDemurrageType;
    int process;
    int processType;
    int processOnTime;
    int processOnTimeType;
    int processDemurrage;
    int processDemurrageType;
    int processCoordinate;
    int processCoordinateType;
    boolean ShowDetail;

    public StatisticalDPMRow(int total, int totalType, int processed, int processedType, int processedOnTime, int processedOnTimeType, int processedDemurrage, int processedDemurrageType, int process, int processType, int processOnTime, int processOnTimeType, int processDemurrage, int processDemurrageType, int processCoordinate, int processCoordinateType) {
        this.total = total;
        this.totalType = totalType;
        this.processed = processed;
        this.processedType = processedType;
        this.processedOnTime = processedOnTime;
        this.processedOnTimeType = processedOnTimeType;
        this.processedDemurrage = processedDemurrage;
        this.processedDemurrageType = processedDemurrageType;
        this.process = process;
        this.processType = processType;
        this.processOnTime = processOnTime;
        this.processOnTimeType = processOnTimeType;
        this.processDemurrage = processDemurrage;
        this.processDemurrageType = processDemurrageType;
        this.processCoordinate = processCoordinate;
        this.processCoordinateType = processCoordinateType;
    }

    public StatisticalDPMRow(long objectId, String name, int processedOnTime, int processedOnTimeType, int processedDemurrage, int processedDemurrageType, int processOnTime, int processOnTimeType, int processDemurrage, int processDemurrageType, int processCoordinate, int processCoordinateType, boolean showDetail) {
        this.objectId = objectId;
        Name = name;
        this.processedOnTime = processedOnTime;
        this.processedOnTimeType = processedOnTimeType;
        this.processedDemurrage = processedDemurrage;
        this.processedDemurrageType = processedDemurrageType;
        this.processOnTime = processOnTime;
        this.processOnTimeType = processOnTimeType;
        this.processDemurrage = processDemurrage;
        this.processDemurrageType = processDemurrageType;
        this.processCoordinate = processCoordinate;
        this.processCoordinateType = processCoordinateType;
        this.ShowDetail = showDetail;
    }

    public StatisticalDPMRow(long objectId, String name, int processedOnTime, int processedOnTimeType, int processedDemurrage, int processedDemurrageType, int processOnTime, int processOnTimeType, int processDemurrage, int processDemurrageType) {
        this.objectId = objectId;
        Name = name;
        this.processedOnTime = processedOnTime;
        this.processedOnTimeType = processedOnTimeType;
        this.processedDemurrage = processedDemurrage;
        this.processedDemurrageType = processedDemurrageType;
        this.processOnTime = processOnTime;
        this.processOnTimeType = processOnTimeType;
        this.processDemurrage = processDemurrage;
        this.processDemurrageType = processDemurrageType;
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

    public int getProcessed() {
        return processed;
    }

    public void setProcessed(int processed) {
        this.processed = processed;
    }

    public int getProcessedType() {
        return processedType;
    }

    public void setProcessedType(int processedType) {
        this.processedType = processedType;
    }

    public int getProcessedOnTime() {
        return processedOnTime;
    }

    public void setProcessedOnTime(int processedOnTime) {
        this.processedOnTime = processedOnTime;
    }

    public int getProcessedOnTimeType() {
        return processedOnTimeType;
    }

    public void setProcessedOnTimeType(int processedOnTimeType) {
        this.processedOnTimeType = processedOnTimeType;
    }

    public int getProcessedDemurrage() {
        return processedDemurrage;
    }

    public void setProcessedDemurrage(int processedDemurrage) {
        this.processedDemurrage = processedDemurrage;
    }

    public int getProcessedDemurrageType() {
        return processedDemurrageType;
    }

    public void setProcessedDemurrageType(int processedDemurrageType) {
        this.processedDemurrageType = processedDemurrageType;
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

    public int getProcessOnTime() {
        return processOnTime;
    }

    public void setProcessOnTime(int processOnTime) {
        this.processOnTime = processOnTime;
    }

    public int getProcessOnTimeType() {
        return processOnTimeType;
    }

    public void setProcessOnTimeType(int processOnTimeType) {
        this.processOnTimeType = processOnTimeType;
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

    public int getProcessCoordinate() {
        return processCoordinate;
    }

    public void setProcessCoordinate(int processCoordinate) {
        this.processCoordinate = processCoordinate;
    }

    public int getProcessCoordinateType() {
        return processCoordinateType;
    }

    public void setProcessCoordinateType(int processCoordinateType) {
        this.processCoordinateType = processCoordinateType;
    }

    public boolean isShowDetail() {
        return ShowDetail;
    }

    public void setShowDetail(boolean showDetail) {
        ShowDetail = showDetail;
    }
}
