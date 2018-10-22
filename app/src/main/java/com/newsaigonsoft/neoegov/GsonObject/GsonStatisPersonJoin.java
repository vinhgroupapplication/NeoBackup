package com.newsaigonsoft.neoegov.GsonObject;

import java.util.List;

public class GsonStatisPersonJoin {

    /**
     * message : success
     * data : [{"inProcessLate":2,"total":37,"processer":"  Cù Thị Kiều Oanh","processedOnTime":19,"totalCode":0,"processedOnTimeCode":2,"inProcessLateCode":6,"processed":19,"inProcessOnTime":16,"processedCode":1,"processedLate":0,"inProcess":18,"processedLateCode":3,"inProcessCode":4,"processerId":30047,"inProcessOnTimeCode":5,"rateOnTime":94},{"inProcessLate":0,"total":16,"processer":"  Lê Ngọc Anh","processedOnTime":3,"totalCode":0,"processedOnTimeCode":2,"inProcessLateCode":6,"processed":3,"inProcessOnTime":13,"processedCode":1,"processedLate":0,"inProcess":13,"processedLateCode":3,"inProcessCode":4,"processerId":30029,"inProcessOnTimeCode":5,"rateOnTime":100},{"inProcessLate":0,"total":1,"processer":"  Nguyễn Kiều Dung","processedOnTime":0,"totalCode":0,"processedOnTimeCode":2,"inProcessLateCode":6,"processed":0,"inProcessOnTime":1,"processedCode":1,"processedLate":0,"inProcess":1,"processedLateCode":3,"inProcessCode":4,"processerId":30056,"inProcessOnTimeCode":5,"rateOnTime":100}]
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
         * inProcessLate : 2
         * total : 37
         * processer :   Cù Thị Kiều Oanh
         * processedOnTime : 19
         * totalCode : 0
         * processedOnTimeCode : 2
         * inProcessLateCode : 6
         * processed : 19
         * inProcessOnTime : 16
         * processedCode : 1
         * processedLate : 0
         * inProcess : 18
         * processedLateCode : 3
         * inProcessCode : 4
         * processerId : 30047
         * inProcessOnTimeCode : 5
         * rateOnTime : 94
         */

        private int inProcessLate;
        private int total;
        private String processer;
        private int processedOnTime;
        private int totalCode;
        private int processedOnTimeCode;
        private int inProcessLateCode;
        private int processed;
        private int inProcessOnTime;
        private int processedCode;
        private int processedLate;
        private int inProcess;
        private int processedLateCode;
        private int inProcessCode;
        private int processerId;
        private int inProcessOnTimeCode;
        private int rateOnTime;

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

        public String getProcesser() {
            return processer;
        }

        public void setProcesser(String processer) {
            this.processer = processer;
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

        public int getProcesserId() {
            return processerId;
        }

        public void setProcesserId(int processerId) {
            this.processerId = processerId;
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
