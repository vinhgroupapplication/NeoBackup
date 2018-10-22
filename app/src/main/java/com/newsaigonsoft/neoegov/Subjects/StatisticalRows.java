package com.newsaigonsoft.neoegov.Subjects;

/**
 * Created by Vinh on 6/13/2017.
 */

public class StatisticalRows {
    String StatisticalDepartment;
    int StatisticalTotal;
    int StatisticalLateTotal;
    int docNotProcessType;
    int docProcessedOnTimeType;
    int docDemurrageType;
    int totalDocType;
    long organizationId;
    int Rate;
    int inProcess;
    int Processed;
    int processedLate;
    int processeLate;
    boolean ShowDetail;

    public StatisticalRows(String statisticalDepartment, int statisticalTotal, int statisticalLateTotal, int docNotProcessType, int docProcessedOnTimeType, int docDemurrageType, int totalDocType, long organizationId, int rate, int inprocess,int processed, int processedLate, int processLate ,boolean showDetail) {
        StatisticalDepartment = statisticalDepartment;
        StatisticalTotal = statisticalTotal;
        StatisticalLateTotal = statisticalLateTotal;
        this.docNotProcessType = docNotProcessType;
        this.docProcessedOnTimeType = docProcessedOnTimeType;
        this.docDemurrageType = docDemurrageType;
        this.totalDocType = totalDocType;
        this.organizationId = organizationId;
        this.Rate = rate;
        this.inProcess = inprocess;
        this.Processed = processed;
        this.processedLate = processedLate;
        this.processeLate = processLate;
        this.ShowDetail = showDetail;
    }


    public int getProcesseLate() {
        return processeLate;
    }

    public void setProcesseLate(int processeLate) {
        this.processeLate = processeLate;
    }

    public int getProcessedLate() {
        return processedLate;
    }

    public void setProcessedLate(int processedLate) {
        this.processedLate = processedLate;
    }

    public int getProcessed() {
        return Processed;
    }

    public void setProcessed(int processed) {
        Processed = processed;
    }

    public int getInProcess() {
        return inProcess;
    }

    public void setInProcess(int inProcess) {
        this.inProcess = inProcess;
    }

    public boolean isShowDetail() {
        return ShowDetail;
    }

    public void setShowDetail(boolean showDetail) {
        ShowDetail = showDetail;
    }

    public long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(long organizationId) {
        this.organizationId = organizationId;
    }

    public String getStatisticalDepartment() {
        return StatisticalDepartment;
    }

    public void setStatisticalDepartment(String statisticalDepartment) {
        StatisticalDepartment = statisticalDepartment;
    }

    public int getStatisticalTotal() {
        return StatisticalTotal;
    }

    public void setStatisticalTotal(int statisticalTotal) {
        StatisticalTotal = statisticalTotal;
    }

    public int getStatisticalLateTotal() {
        return StatisticalLateTotal;
    }

    public void setStatisticalLateTotal(int statisticalLateTotal) {
        StatisticalLateTotal = statisticalLateTotal;
    }

    public int getDocNotProcessType() {
        return docNotProcessType;
    }

    public void setDocNotProcessType(int docNotProcessType) {
        this.docNotProcessType = docNotProcessType;
    }

    public int getDocProcessedOnTimeType() {
        return docProcessedOnTimeType;
    }

    public void setDocProcessedOnTimeType(int docProcessedOnTimeType) {
        this.docProcessedOnTimeType = docProcessedOnTimeType;
    }

    public int getDocDemurrageType() {
        return docDemurrageType;
    }

    public void setDocDemurrageType(int docDemurrageType) {
        this.docDemurrageType = docDemurrageType;
    }

    public int getTotalDocType() {
        return totalDocType;
    }

    public void setTotalDocType(int totalDocType) {
        this.totalDocType = totalDocType;
    }

    public int getRate() {
        return Rate;
    }

    public void setRate(int rate) {
        Rate = rate;
    }
}
