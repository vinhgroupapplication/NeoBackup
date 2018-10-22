package com.newsaigonsoft.neoegov.Subjects;

/**
 * Created by Vinh on 10/23/2017.
 */

public class StatisticalFmRow {
    int docNotProcessType_Full;
    int docProcessedOnTimeType_Full;
    int docDemurrageType_Full;
    int totalDocType_Full;
    int docDemurrage_Full;
    int docProcessedOnTime_Full;
    int totalDoc_Full;
    int docNotProcess_Full;
    int rateOnTime;

    public StatisticalFmRow(int docNotProcessType_Full, int docProcessedOnTimeType_Full, int docDemurrageType_Full, int totalDocType_Full, int docDemurrage_Full, int docProcessedOnTime_Full, int totalDoc_Full, int docNotProcess_Full, int rateOnTime) {
        this.docNotProcessType_Full = docNotProcessType_Full;
        this.docProcessedOnTimeType_Full = docProcessedOnTimeType_Full;
        this.docDemurrageType_Full = docDemurrageType_Full;
        this.totalDocType_Full = totalDocType_Full;
        this.docDemurrage_Full = docDemurrage_Full;
        this.docProcessedOnTime_Full = docProcessedOnTime_Full;
        this.totalDoc_Full = totalDoc_Full;
        this.docNotProcess_Full = docNotProcess_Full;
        this.rateOnTime = rateOnTime;
    }

    public int getDocNotProcessType_Full() {
        return docNotProcessType_Full;
    }

    public void setDocNotProcessType_Full(int docNotProcessType_Full) {
        this.docNotProcessType_Full = docNotProcessType_Full;
    }

    public int getDocProcessedOnTimeType_Full() {
        return docProcessedOnTimeType_Full;
    }

    public void setDocProcessedOnTimeType_Full(int docProcessedOnTimeType_Full) {
        this.docProcessedOnTimeType_Full = docProcessedOnTimeType_Full;
    }

    public int getDocDemurrageType_Full() {
        return docDemurrageType_Full;
    }

    public void setDocDemurrageType_Full(int docDemurrageType_Full) {
        this.docDemurrageType_Full = docDemurrageType_Full;
    }

    public int getTotalDocType_Full() {
        return totalDocType_Full;
    }

    public void setTotalDocType_Full(int totalDocType_Full) {
        this.totalDocType_Full = totalDocType_Full;
    }

    public int getDocDemurrage_Full() {
        return docDemurrage_Full;
    }

    public void setDocDemurrage_Full(int docDemurrage_Full) {
        this.docDemurrage_Full = docDemurrage_Full;
    }

    public int getDocProcessedOnTime_Full() {
        return docProcessedOnTime_Full;
    }

    public void setDocProcessedOnTime_Full(int docProcessedOnTime_Full) {
        this.docProcessedOnTime_Full = docProcessedOnTime_Full;
    }

    public int getTotalDoc_Full() {
        return totalDoc_Full;
    }

    public void setTotalDoc_Full(int totalDoc_Full) {
        this.totalDoc_Full = totalDoc_Full;
    }

    public int getDocNotProcess_Full() {
        return docNotProcess_Full;
    }

    public void setDocNotProcess_Full(int docNotProcess_Full) {
        this.docNotProcess_Full = docNotProcess_Full;
    }

    public int getRateOnTime() {
        return rateOnTime;
    }

    public void setRateOnTime(int rateOnTime) {
        this.rateOnTime = rateOnTime;
    }
}
