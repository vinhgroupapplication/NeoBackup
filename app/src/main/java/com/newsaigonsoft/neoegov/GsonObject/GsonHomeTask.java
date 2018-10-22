package com.newsaigonsoft.neoegov.GsonObject;

/**
 * Created by Vinh on 4/2/2018.
 */

public class GsonHomeTask {


    /**
     * message : success
     * data : {"inProcessLateCode":5,"inProcessLate":0,"total":2,"reported":2,"inProcessOnTime":0,"inProcessNearLate":0,"inProcessNearLateCode":4,"inProcess":0,"reportedCode":1,"totalCode":6,"inProcessCode":2,"inProcessOnTimeCode":3}
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
         * inProcessLateCode : 5
         * inProcessLate : 0
         * total : 2
         * reported : 2
         * inProcessOnTime : 0
         * inProcessNearLate : 0
         * inProcessNearLateCode : 4
         * inProcess : 0
         * reportedCode : 1
         * totalCode : 6
         * inProcessCode : 2
         * inProcessOnTimeCode : 3
         */

        private int inProcessLateCode;
        private int inProcessLate;
        private int total;
        private int reported;
        private int inProcessOnTime;
        private int inProcessNearLate;
        private int inProcessNearLateCode;
        private int inProcess;
        private int reportedCode;
        private int totalCode;
        private int inProcessCode;
        private int inProcessOnTimeCode;

        public int getInProcessLateCode() {
            return inProcessLateCode;
        }

        public void setInProcessLateCode(int inProcessLateCode) {
            this.inProcessLateCode = inProcessLateCode;
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

        public int getReported() {
            return reported;
        }

        public void setReported(int reported) {
            this.reported = reported;
        }

        public int getInProcessOnTime() {
            return inProcessOnTime;
        }

        public void setInProcessOnTime(int inProcessOnTime) {
            this.inProcessOnTime = inProcessOnTime;
        }

        public int getInProcessNearLate() {
            return inProcessNearLate;
        }

        public void setInProcessNearLate(int inProcessNearLate) {
            this.inProcessNearLate = inProcessNearLate;
        }

        public int getInProcessNearLateCode() {
            return inProcessNearLateCode;
        }

        public void setInProcessNearLateCode(int inProcessNearLateCode) {
            this.inProcessNearLateCode = inProcessNearLateCode;
        }

        public int getInProcess() {
            return inProcess;
        }

        public void setInProcess(int inProcess) {
            this.inProcess = inProcess;
        }

        public int getReportedCode() {
            return reportedCode;
        }

        public void setReportedCode(int reportedCode) {
            this.reportedCode = reportedCode;
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

        public int getInProcessOnTimeCode() {
            return inProcessOnTimeCode;
        }

        public void setInProcessOnTimeCode(int inProcessOnTimeCode) {
            this.inProcessOnTimeCode = inProcessOnTimeCode;
        }
    }
}
