package com.newsaigonsoft.neoegov.GsonObject;

import java.util.List;

/**
 * Created by Vinh on 4/11/2018.
 */

public class GsonStaComingList {


    /**
     * message : success
     * data : [{"inProcessLate":3,"total":12,"organizationName":"Văn phòng, Cơ quan Tổng Công ty Viễn thông Mobifone","processedOnTime":2,"totalCode":0,"organizationId":23010,"processedOnTimeCode":2,"inProcessLateCode":6,"processed":2,"inProcessOnTime":7,"processedCode":1,"processedLate":0,"inProcess":10,"processedLateCode":3,"inProcessCode":4,"inProcessOnTimeCode":5,"rateOnTime":"75%"}]
     * code : 0
     */

    private String message;
    private int code;
    private List<DataBean> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * inProcessLate : 3
         * total : 12
         * organizationName : Văn phòng, Cơ quan Tổng Công ty Viễn thông Mobifone
         * processedOnTime : 2
         * totalCode : 0
         * organizationId : 23010
         * processedOnTimeCode : 2
         * inProcessLateCode : 6
         * processed : 2
         * inProcessOnTime : 7
         * processedCode : 1
         * processedLate : 0
         * inProcess : 10
         * processedLateCode : 3
         * inProcessCode : 4
         * inProcessOnTimeCode : 5
         * rateOnTime : 75
         */

        private int inProcessLate;
        private int total;
        private String organizationName;
        private int processedOnTime;
        private int totalCode;
        private int organizationId;
        private int processedOnTimeCode;
        private int inProcessLateCode;
        private int processed;
        private int inProcessOnTime;
        private int processedCode;
        private int processedLate;
        private int inProcess;
        private int processedLateCode;
        private int inProcessCode;
        private int inProcessOnTimeCode;
        private int rateOnTime;
        private boolean Show = false;

        public boolean isShow() {
            return Show;
        }

        public void setShow(boolean show) {
            Show = show;
        }

        public int getInProcessLate() {
            return inProcessLate;
        }

        public void setInProcessLate(int inProcessLate) {
            this.inProcessLate = inProcessLate;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public String getOrganizationName() {
            return organizationName;
        }

        public void setOrganizationName(String organizationName) {
            this.organizationName = organizationName;
        }

        public int getProcessedOnTime() {
            return processedOnTime;
        }

        public void setProcessedOnTime(int processedOnTime) {
            this.processedOnTime = processedOnTime;
        }

        public int getTotalCode() {
            return totalCode;
        }

        public void setTotalCode(int totalCode) {
            this.totalCode = totalCode;
        }

        public int getOrganizationId() {
            return organizationId;
        }

        public void setOrganizationId(int organizationId) {
            this.organizationId = organizationId;
        }

        public int getProcessedOnTimeCode() {
            return processedOnTimeCode;
        }

        public void setProcessedOnTimeCode(int processedOnTimeCode) {
            this.processedOnTimeCode = processedOnTimeCode;
        }

        public int getInProcessLateCode() {
            return inProcessLateCode;
        }

        public void setInProcessLateCode(int inProcessLateCode) {
            this.inProcessLateCode = inProcessLateCode;
        }

        public int getProcessed() {
            return processed;
        }

        public void setProcessed(int processed) {
            this.processed = processed;
        }

        public int getInProcessOnTime() {
            return inProcessOnTime;
        }

        public void setInProcessOnTime(int inProcessOnTime) {
            this.inProcessOnTime = inProcessOnTime;
        }

        public int getProcessedCode() {
            return processedCode;
        }

        public void setProcessedCode(int processedCode) {
            this.processedCode = processedCode;
        }

        public int getProcessedLate() {
            return processedLate;
        }

        public void setProcessedLate(int processedLate) {
            this.processedLate = processedLate;
        }

        public int getInProcess() {
            return inProcess;
        }

        public void setInProcess(int inProcess) {
            this.inProcess = inProcess;
        }

        public int getProcessedLateCode() {
            return processedLateCode;
        }

        public void setProcessedLateCode(int processedLateCode) {
            this.processedLateCode = processedLateCode;
        }

        public int getInProcessCode() {
            return inProcessCode;
        }

        public void setInProcessCode(int inProcessCode) {
            this.inProcessCode = inProcessCode;
        }

        public int getInProcessOnTimeCode() {
            return inProcessOnTimeCode;
        }

        public void setInProcessOnTimeCode(int inProcessOnTimeCode) {
            this.inProcessOnTimeCode = inProcessOnTimeCode;
        }

        public int getRateOnTime() {
            return rateOnTime;
        }

        public void setRateOnTime(int rateOnTime) {
            this.rateOnTime = rateOnTime;
        }
    }
}
