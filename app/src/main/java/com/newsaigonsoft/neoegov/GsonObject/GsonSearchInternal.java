package com.newsaigonsoft.neoegov.GsonObject;

public class GsonSearchInternal {


    /**
     * module : 02
     * type : 020302
     * data : {"rowPerPage":1,"numPage":1,"advanced":true,"keywords":"","docNumber":"","docTypeId":111111111111111,"briefContent":"","fromSignDate":111111111111111,"toSignDate":111111111111111,"fromCreateDate":111111111111111,"toCreateDate":111111111111111}
     */

    private String module;
    private String type;
    private DataBean data;

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        public DataBean(int rowPerPage, int numPage, boolean advanced, String keywords, String docNumber, String docTypeId, String briefContent, long fromSignDate, long toSignDate, long fromCreateDate, long toCreateDate) {
            this.rowPerPage = rowPerPage;
            this.numPage = numPage;
            this.advanced = advanced;
            this.keywords = keywords;
            this.docNumber = docNumber;
            this.docTypeId = docTypeId;
            this.briefContent = briefContent;
            this.fromSignDate = fromSignDate;
            this.toSignDate = toSignDate;
            this.fromCreateDate = fromCreateDate;
            this.toCreateDate = toCreateDate;
        }

        /**
         * rowPerPage : 1
         * numPage : 1
         * advanced : true
         * keywords :
         * docNumber :
         * docTypeId : 111111111111111
         * briefContent :
         * fromSignDate : 111111111111111
         * toSignDate : 111111111111111
         * fromCreateDate : 111111111111111
         * toCreateDate : 111111111111111
         */
        private int rowPerPage;
        private int numPage;
        private boolean advanced;
        private String keywords;
        private String docNumber;
        private String docTypeId;
        private String briefContent;
        private long fromSignDate;
        private long toSignDate;
        private long fromCreateDate;
        private long toCreateDate;

        public int getRowPerPage() {
            return rowPerPage;
        }

        public void setRowPerPage(int rowPerPage) {
            this.rowPerPage = rowPerPage;
        }

        public int getNumPage() {
            return numPage;
        }

        public void setNumPage(int numPage) {
            this.numPage = numPage;
        }

        public boolean isAdvanced() {
            return advanced;
        }

        public void setAdvanced(boolean advanced) {
            this.advanced = advanced;
        }

        public String getKeywords() {
            return keywords;
        }

        public void setKeywords(String keywords) {
            this.keywords = keywords;
        }

        public String getDocNumber() {
            return docNumber;
        }

        public void setDocNumber(String docNumber) {
            this.docNumber = docNumber;
        }

        public String getDocTypeId() {
            return docTypeId;
        }

        public void setDocTypeId(String docTypeId) {
            this.docTypeId = docTypeId;
        }

        public String getBriefContent() {
            return briefContent;
        }

        public void setBriefContent(String briefContent) {
            this.briefContent = briefContent;
        }

        public long getFromSignDate() {
            return fromSignDate;
        }

        public void setFromSignDate(long fromSignDate) {
            this.fromSignDate = fromSignDate;
        }

        public long getToSignDate() {
            return toSignDate;
        }

        public void setToSignDate(long toSignDate) {
            this.toSignDate = toSignDate;
        }

        public long getFromCreateDate() {
            return fromCreateDate;
        }

        public void setFromCreateDate(long fromCreateDate) {
            this.fromCreateDate = fromCreateDate;
        }

        public long getToCreateDate() {
            return toCreateDate;
        }

        public void setToCreateDate(long toCreateDate) {
            this.toCreateDate = toCreateDate;
        }
    }
}
