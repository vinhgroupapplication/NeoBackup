package com.newsaigonsoft.neoegov.GsonObject;

/**
 * Created by Vinh on 4/27/2018.
 */

public class GsonStaHome {


    /**
     * message : success
     * data : {"inProcessLateCode":3,"inProcessLate":2,"total":71,"notSeen":0,"inProcess":15,"totalCode":4,"inProcessCode":2,"notSeenCode":1}
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
         * inProcessLateCode : 3
         * inProcessLate : 2
         * total : 71
         * notSeen : 0
         * inProcess : 15
         * totalCode : 4
         * inProcessCode : 2
         * notSeenCode : 1
         */

        private int inProcessLateCode;
        private int inProcessLate;
        private int total;
        private int notSeen;
        private int inProcess;
        private int totalCode;
        private int inProcessCode;
        private int notSeenCode;


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

        public int getNotSeen() {
            return notSeen;
        }

        public void setNotSeen(int notSeen) {
            this.notSeen = notSeen;
        }

        public int getInProcess() {
            return inProcess;
        }

        public void setInProcess(int inProcess) {
            this.inProcess = inProcess;
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

        public int getNotSeenCode() {
            return notSeenCode;
        }

        public void setNotSeenCode(int notSeenCode) {
            this.notSeenCode = notSeenCode;
        }
    }
}
