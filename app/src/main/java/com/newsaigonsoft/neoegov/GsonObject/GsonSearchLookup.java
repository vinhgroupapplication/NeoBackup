package com.newsaigonsoft.neoegov.GsonObject;

public class GsonSearchLookup {

    /**
     * module : 02
     * type : 020102
     * data : {"rowPerPage":20,"numPage":1,"advanced":true,"keywords":"","docNumberFull":"","numberOfSymbol":"","docTypeId":1231,"briefContent":"","fromPublishDate":1111111100001,"toPublishDate":1111111100001,"fromReceiveDate":1111111100001,"toReceiveDate":1111111100001}
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

        /**
         * rowPerPage : 20
         * numPage : 1
         * advanced : true
         * keywords :
         * docNumberFull :
         * numberOfSymbol :
         * docTypeId : 1231
         * briefContent :
         * fromPublishDate : 1111111100001
         * toPublishDate : 1111111100001
         * fromReceiveDate : 1111111100001
         * toReceiveDate : 1111111100001
         */

        private int rowPerPage;
        private int numPage;
        private boolean advanced;
        private String keywords;
        private String docNumberFull;
        private String numberOfSymbol;
        private long docTypeId;
        private String briefContent;
        private long fromPublishDate;
        private long toPublishDate;
        private long fromReceiveDate;
        private long toReceiveDate;

        public DataBean(int rowPerPage, int numPage, boolean advanced, String keywords, String docNumberFull, String numberOfSymbol, long docTypeId, String briefContent, long fromPublishDate, long toPublishDate, long fromReceiveDate, long toReceiveDate) {
            this.rowPerPage = rowPerPage;
            this.numPage = numPage;
            this.advanced = advanced;
            this.keywords = keywords;
            this.docNumberFull = docNumberFull;
            this.numberOfSymbol = numberOfSymbol;
            this.docTypeId = docTypeId;
            this.briefContent = briefContent;
            this.fromPublishDate = fromPublishDate;
            this.toPublishDate = toPublishDate;
            this.fromReceiveDate = fromReceiveDate;
            this.toReceiveDate = toReceiveDate;
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

        public String getDocNumberFull() {
            return docNumberFull;
        }

        public void setDocNumberFull(String docNumberFull) {
            this.docNumberFull = docNumberFull;
        }

        public String getNumberOfSymbol() {
            return numberOfSymbol;
        }

        public void setNumberOfSymbol(String numberOfSymbol) {
            this.numberOfSymbol = numberOfSymbol;
        }

        public long getDocTypeId() {
            return docTypeId;
        }

        public void setDocTypeId(int docTypeId) {
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
    }
}
