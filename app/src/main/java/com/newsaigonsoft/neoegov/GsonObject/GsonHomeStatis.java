package com.newsaigonsoft.neoegov.GsonObject;

/**
 * Created by Vinh on 4/2/2018.
 */

public class GsonHomeStatis {

    /**
     * message : success
     * data : {"docNotProcessType":2,"docDemurrage":0,"docNotSeenType":1,"docNotSeen":0,"totalDoc":56,"docDemurrageType":3,"docNotProcess":9,"totalDocType":4}
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
         * docNotProcessType : 2
         * docDemurrage : 0
         * docNotSeenType : 1
         * docNotSeen : 0
         * totalDoc : 56
         * docDemurrageType : 3
         * docNotProcess : 9
         * totalDocType : 4
         */

        private int docNotProcessType;
        private int docDemurrage;
        private int docNotSeenType;
        private int docNotSeen;
        private int totalDoc;
        private int docDemurrageType;
        private int docNotProcess;
        private int totalDocType;

        public int getDocNotProcessType() {
            return docNotProcessType;
        }

        public void setDocNotProcessType(int docNotProcessType) {
            this.docNotProcessType = docNotProcessType;
        }

        public int getDocDemurrage() {
            return docDemurrage;
        }

        public void setDocDemurrage(int docDemurrage) {
            this.docDemurrage = docDemurrage;
        }

        public int getDocNotSeenType() {
            return docNotSeenType;
        }

        public void setDocNotSeenType(int docNotSeenType) {
            this.docNotSeenType = docNotSeenType;
        }

        public int getDocNotSeen() {
            return docNotSeen;
        }

        public void setDocNotSeen(int docNotSeen) {
            this.docNotSeen = docNotSeen;
        }

        public int getTotalDoc() {
            return totalDoc;
        }

        public void setTotalDoc(int totalDoc) {
            this.totalDoc = totalDoc;
        }

        public int getDocDemurrageType() {
            return docDemurrageType;
        }

        public void setDocDemurrageType(int docDemurrageType) {
            this.docDemurrageType = docDemurrageType;
        }

        public int getDocNotProcess() {
            return docNotProcess;
        }

        public void setDocNotProcess(int docNotProcess) {
            this.docNotProcess = docNotProcess;
        }

        public int getTotalDocType() {
            return totalDocType;
        }

        public void setTotalDocType(int totalDocType) {
            this.totalDocType = totalDocType;
        }
    }
}
