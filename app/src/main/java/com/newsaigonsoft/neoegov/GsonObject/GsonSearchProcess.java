package com.newsaigonsoft.neoegov.GsonObject;

public class GsonSearchProcess {


    /**
     * module : 01
     * type : 010003
     * data : {"labelCode":2014,"rowPerPage":20,"numPage":1,"advanced":true,"keywords":"","organizationId":"long","fromReceiveDate":"long","toReceiveDate":"long","fromProcessDate":"long","toProcessDate":"long","fromDueDate":"long","toDueDate":"long","content":""}
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
        public DataBean(String labelCode, int rowPerPage, int numPage, boolean advanced, String keywords, String organizationId, long fromReceiveDate, long toReceiveDate, long fromProcessDate, long toProcessDate, long fromDueDate, long toDueDate, String content) {
            this.labelCode = labelCode;
            this.rowPerPage = rowPerPage;
            this.numPage = numPage;
            this.advanced = advanced;
            this.keywords = keywords;
            this.organizationId = organizationId;
            this.fromReceiveDate = fromReceiveDate;
            this.toReceiveDate = toReceiveDate;
            this.fromProcessDate = fromProcessDate;
            this.toProcessDate = toProcessDate;
            this.fromDueDate = fromDueDate;
            this.toDueDate = toDueDate;
            this.content = content;
        }

        /**
         * labelCode : 2014
         * rowPerPage : 20
         * numPage : 1
         * advanced : true
         * keywords :
         * organizationId : long
         * fromReceiveDate : long
         * toReceiveDate : long
         * fromProcessDate : long
         * toProcessDate : long
         * fromDueDate : long
         * toDueDate : long
         * content :
         */


        private String labelCode;
        private int rowPerPage;
        private int numPage;
        private boolean advanced;
        private String keywords;
        private String organizationId;
        private long fromReceiveDate;
        private long toReceiveDate;
        private long fromProcessDate;
        private long toProcessDate;
        private long fromDueDate;
        private long toDueDate;
        private String content;

        public String getLabelCode() {
            return labelCode;
        }

        public void setLabelCode(String labelCode) {
            this.labelCode = labelCode;
        }

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

        public String getOrganizationId() {
            return organizationId;
        }

        public void setOrganizationId(String organizationId) {
            this.organizationId = organizationId;
        }

        public long getFromReceiveDate() {
            return fromReceiveDate;
        }

        public void setFromReceiveDate(long fromReceiveDate) {
            this.fromReceiveDate = fromReceiveDate;
        }

        public long getToReceiveDate() {
            return toReceiveDate;
        }

        public void setToReceiveDate(long toReceiveDate) {
            this.toReceiveDate = toReceiveDate;
        }

        public long getFromProcessDate() {
            return fromProcessDate;
        }

        public void setFromProcessDate(long fromProcessDate) {
            this.fromProcessDate = fromProcessDate;
        }

        public long getToProcessDate() {
            return toProcessDate;
        }

        public void setToProcessDate(long toProcessDate) {
            this.toProcessDate = toProcessDate;
        }

        public long getFromDueDate() {
            return fromDueDate;
        }

        public void setFromDueDate(long fromDueDate) {
            this.fromDueDate = fromDueDate;
        }

        public long getToDueDate() {
            return toDueDate;
        }

        public void setToDueDate(long toDueDate) {
            this.toDueDate = toDueDate;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
