package com.newsaigonsoft.neoegov.GsonObject;

/**
 * Created by Vinh on 4/2/2018.
 */

public class GsonHomeHotLine {


    /**
     * code : 1
     * message : success
     * data : {"organizationId":123,"organizationName":"dsdasdasdasd","inProcess":5,"inProcessCode":1,"inProcessOnDue":6,"inProcessOnDueCode":2,"inProcessLate":7,"inProcessLateCode":3}
     */

    private int code;
    private String message;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

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

    public static class DataBean {
        /**
         * organizationId : 123
         * organizationName : dsdasdasdasd
         * inProcess : 5
         * inProcessCode : 1
         * inProcessOnDue : 6
         * inProcessOnDueCode : 2
         * inProcessLate : 7
         * inProcessLateCode : 3
         */

        private int organizationId;
        private String organizationName;
        private int inProcess;
        private int inProcessCode;
        private int inProcessOnDue;
        private int inProcessOnDueCode;
        private int inProcessLate;
        private int inProcessLateCode;

        public int getOrganizationId() {
            return organizationId;
        }

        public void setOrganizationId(int organizationId) {
            this.organizationId = organizationId;
        }

        public String getOrganizationName() {
            return organizationName;
        }

        public void setOrganizationName(String organizationName) {
            this.organizationName = organizationName;
        }

        public int getInProcess() {
            return inProcess;
        }

        public void setInProcess(int inProcess) {
            this.inProcess = inProcess;
        }

        public int getInProcessCode() {
            return inProcessCode;
        }

        public void setInProcessCode(int inProcessCode) {
            this.inProcessCode = inProcessCode;
        }

        public int getInProcessOnDue() {
            return inProcessOnDue;
        }

        public void setInProcessOnDue(int inProcessOnDue) {
            this.inProcessOnDue = inProcessOnDue;
        }

        public int getInProcessOnDueCode() {
            return inProcessOnDueCode;
        }

        public void setInProcessOnDueCode(int inProcessOnDueCode) {
            this.inProcessOnDueCode = inProcessOnDueCode;
        }

        public int getInProcessLate() {
            return inProcessLate;
        }

        public void setInProcessLate(int inProcessLate) {
            this.inProcessLate = inProcessLate;
        }

        public int getInProcessLateCode() {
            return inProcessLateCode;
        }

        public void setInProcessLateCode(int inProcessLateCode) {
            this.inProcessLateCode = inProcessLateCode;
        }
    }
}
