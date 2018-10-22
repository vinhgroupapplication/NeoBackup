package com.newsaigonsoft.neoegov.GsonObject;

/**
 * Created by Vinh on 4/11/2018.
 */

public class GsonStaComing {


    /**
     * message : success
     * data : {"inProcessLateCode":6,"total":11,"inProcessLate":3,"processed":0,"inProcessOnTime":8,"processedOnTime":0,"processedLate":0,"processedCode":1,"inProcess":11,"processedLateCode":3,"totalCode":0,"inProcessCode":4,"processedOnTimeCode":2,"inProcessOnTimeCode":5,"rateOnTime":"72%"}
     * code : 0
     */

    private String message;
    private DataBean data;
    private int code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static class DataBean {
        /**
         * inProcessLateCode : 6
         * total : 11
         * inProcessLate : 3
         * processed : 0
         * inProcessOnTime : 8
         * processedOnTime : 0
         * processedLate : 0
         * processedCode : 1
         * inProcess : 11
         * processedLateCode : 3
         * totalCode : 0
         * inProcessCode : 4
         * processedOnTimeCode : 2
         * inProcessOnTimeCode : 5
         * rateOnTime : 72%
         */

        private int inProcessLateCode;
        private int total;
        private int inProcessLate;
        private int processed;
        private int inProcessOnTime;
        private int processedOnTime;
        private int processedLate;
        private int processedCode;
        private int inProcess;
        private int processedLateCode;
        private int totalCode;
        private int inProcessCode;
        private int processedOnTimeCode;
        private int inProcessOnTimeCode;
        private String rateOnTime;


        public int getInProcessLateCode() {
            return inProcessLateCode;
        }

        public void setInProcessLateCode(int inProcessLateCode) {
            this.inProcessLateCode = inProcessLateCode;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getInProcessLate() {
            return inProcessLate;
        }

        public void setInProcessLate(int inProcessLate) {
            this.inProcessLate = inProcessLate;
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

        public int getProcessedOnTime() {
            return processedOnTime;
        }

        public void setProcessedOnTime(int processedOnTime) {
            this.processedOnTime = processedOnTime;
        }

        public int getProcessedLate() {
            return processedLate;
        }

        public void setProcessedLate(int processedLate) {
            this.processedLate = processedLate;
        }

        public int getProcessedCode() {
            return processedCode;
        }

        public void setProcessedCode(int processedCode) {
            this.processedCode = processedCode;
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

        public int getTotalCode() {
            return totalCode;
        }

        public void setTotalCode(int totalCode) {
            this.totalCode = totalCode;
        }

        public int getInProcessCode() {
            return inProcessCode;
        }

        public void setInProcessCode(int inProcessCode) {
            this.inProcessCode = inProcessCode;
        }

        public int getProcessedOnTimeCode() {
            return processedOnTimeCode;
        }

        public void setProcessedOnTimeCode(int processedOnTimeCode) {
            this.processedOnTimeCode = processedOnTimeCode;
        }

        public int getInProcessOnTimeCode() {
            return inProcessOnTimeCode;
        }

        public void setInProcessOnTimeCode(int inProcessOnTimeCode) {
            this.inProcessOnTimeCode = inProcessOnTimeCode;
        }

        public String getRateOnTime() {
            return rateOnTime;
        }

        public void setRateOnTime(String rateOnTime) {
            this.rateOnTime = rateOnTime;
        }
    }
}
