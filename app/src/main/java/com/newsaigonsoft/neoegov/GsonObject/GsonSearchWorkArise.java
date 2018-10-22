package com.newsaigonsoft.neoegov.GsonObject;

public class GsonSearchWorkArise {


    /**
     * module : 05
     * type : 050002
     * data : {"rowPerPage":1,"advanced":true,"keywords":"ssssss","codeWork":"ssss","title":"ssss","content":"ssss","orgCreateId":111111111111111,"fromCreateDate":111111111111111,"toCreateDate":111111111111111}
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
         * rowPerPage : 1
         * advanced : true
         * keywords : ssssss
         * codeWork : ssss
         * title : ssss
         * content : ssss
         * orgCreateId : 111111111111111
         * fromCreateDate : 111111111111111
         * toCreateDate : 111111111111111
         */

        private int rowPerPage;
        private int numPage;
        private boolean advanced;
        private String keywords;
        private String codeWork;
        private String title;
        private String content;
        private String orgCreateId;
        private long fromCreateDate;
        private long toCreateDate;

        public DataBean(int rowPerPage,int numpage, boolean advanced, String keywords, String codeWork, String title, String content, String orgCreateId, long fromCreateDate, long toCreateDate) {
            this.rowPerPage = rowPerPage;
            this.numPage = numpage;
            this.advanced = advanced;
            this.keywords = keywords;
            this.codeWork = codeWork;
            this.title = title;
            this.content = content;
            this.orgCreateId = orgCreateId;
            this.fromCreateDate = fromCreateDate;
            this.toCreateDate = toCreateDate;
        }

        public int getNumPage() {
            return numPage;
        }

        public void setNumPage(int numPage) {
            this.numPage = numPage;
        }

        public int getRowPerPage() {
            return rowPerPage;
        }

        public void setRowPerPage(int rowPerPage) {
            this.rowPerPage = rowPerPage;
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

        public String getCodeWork() {
            return codeWork;
        }

        public void setCodeWork(String codeWork) {
            this.codeWork = codeWork;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getOrgCreateId() {
            return orgCreateId;
        }

        public void setOrgCreateId(String orgCreateId) {
            this.orgCreateId = orgCreateId;
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
