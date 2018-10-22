package com.newsaigonsoft.neoegov.GsonObject;

public class GsonRemindPerson {


    /**
     * code : 0
     * data : {"inProcess":109,"late":10,"preLate":0}
     * message : success
     */

    private int code;
    private DataBean data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        /**
         * inProcess : 109
         * late : 10
         * preLate : 0
         */

        private int inProcess;
        private int late;
        private int preLate;

        public int getInProcess() {
            return inProcess;
        }

        public void setInProcess(int inProcess) {
            this.inProcess = inProcess;
        }

        public int getLate() {
            return late;
        }

        public void setLate(int late) {
            this.late = late;
        }

        public int getPreLate() {
            return preLate;
        }

        public void setPreLate(int preLate) {
            this.preLate = preLate;
        }
    }
}
