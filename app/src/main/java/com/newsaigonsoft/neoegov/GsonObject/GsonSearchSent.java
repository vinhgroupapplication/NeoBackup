package com.newsaigonsoft.neoegov.GsonObject;

public class GsonSearchSent {


    /**
     * module : 02
     * type : 020202
     * data : {"rowPerPage":20,"numPage":1,"advanced":true,"keywords":"","publishNumber":"","docTypeId":111111111111111111,"briefContent":"","fromPublishDate":11111111111111,"toPublishDate":11111111111111,"fromCreateDate":11111111111111,"toCreateDate":11111111111111}
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
        public DataBean(int rowPerPage, int numPage, boolean advanced, String keywords, String publishNumber, long docTypeId, String briefContent, long fromPublishDate, long toPublishDate, long fromCreateDate, long toCreateDate) {
            this.rowPerPage = rowPerPage;
            this.numPage = numPage;
            this.advanced = advanced;
            this.keywords = keywords;
            this.publishNumber = publishNumber;
            this.docTypeId = docTypeId;
            this.briefContent = briefContent;
            this.fromPublishDate = fromPublishDate;
            this.toPublishDate = toPublishDate;
            this.fromCreateDate = fromCreateDate;
            this.toCreateDate = toCreateDate;
        }

        /**
         * rowPerPage : 20
         * numPage : 1
         * advanced : true
         * keywords :
         * publishNumber :
         * docTypeId : 111111111111111111
         * briefContent :
         * fromPublishDate : 11111111111111
         * toPublishDate : 11111111111111
         * fromCreateDate : 11111111111111
         * toCreateDate : 11111111111111
         */



        private int rowPerPage;
        private int numPage;
        private boolean advanced;
        private String keywords;
        private String publishNumber;
        private long docTypeId;
        private String briefContent;
        private long fromPublishDate;
        private long toPublishDate;
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

        public String getPublishNumber() {
            return publishNumber;
        }

        public void setPublishNumber(String publishNumber) {
            this.publishNumber = publishNumber;
        }

        public long getDocTypeId() {
            return docTypeId;
        }

        public void setDocTypeId(long docTypeId) {
            this.docTypeId = docTypeId;
        }

        public String getBriefContent() {
            return briefContent;
        }

        public void setBriefContent(String briefContent) {
            this.briefContent = briefContent;
        }

        public long getFromPublishDate() {
            return fromPublishDate;
        }

        public void setFromPublishDate(long fromPublishDate) {
            this.fromPublishDate = fromPublishDate;
        }

        public long getToPublishDate() {
            return toPublishDate;
        }

        public void setToPublishDate(long toPublishDate) {
            this.toPublishDate = toPublishDate;
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
