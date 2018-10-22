package com.newsaigonsoft.neoegov.GsonObject;

import java.util.List;

public class GsonDocSent {

    /**
     * message : success
     * data : [{"publishNumber":"","docSendId":307200,"briefContent":"test 10h45 ngày 7-6-2018","publishDate":""},{"publishNumber":"6777/GP-ĐT","docSendId":307146,"briefContent":"TB 07 (Anh Nhân KT3) CN Đầu Tư Và XD M Nam - Cty TNHH MTV - TCT XD Lũng Lô","publishDate":"18/11/2015 00:00"},{"publishNumber":"","docSendId":306801,"briefContent":"456456","publishDate":""},{"publishNumber":"","docSendId":306800,"briefContent":"test 9h9 24-4-2018","publishDate":""},{"publishNumber":"20/bcs","docSendId":306303,"briefContent":"kiểm thử","publishDate":"14/03/2018 00:00"},{"publishNumber":"19/bcs","docSendId":306302,"briefContent":"123","publishDate":"14/03/2018 00:00"},{"publishNumber":"18/BC-VP","docSendId":306301,"briefContent":"123","publishDate":"14/03/2018 00:00"},{"publishNumber":"17/bcs","docSendId":306300,"briefContent":"test 1h24pm","publishDate":"14/03/2018 00:00"},{"publishNumber":"03/-CV/BCSĐ","docSendId":306206,"briefContent":"test vb đến 3h12pm","publishDate":"28/02/2018 00:00"},{"publishNumber":"04/UBND-HCM","docSendId":306205,"briefContent":"test 3h07pm","publishDate":"28/02/2018 00:00"},{"publishNumber":"03/UBND-HCM","docSendId":306204,"briefContent":"test abc","publishDate":"28/02/2018 00:00"},{"publishNumber":"02/UBND-HCM","docSendId":306203,"briefContent":"test 28-2-2018","publishDate":"28/02/2018 00:00"},{"publishNumber":"16/bcs","docSendId":306202,"briefContent":"Ban cán sự Đảng 123","publishDate":"23/02/2018 00:00"},{"publishNumber":"11/BC-UBND","docSendId":306201,"briefContent":"11h44","publishDate":"22/02/2018 00:00"},{"publishNumber":"15/bcs","docSendId":306200,"briefContent":"test 11h42","publishDate":"22/02/2018 00:00"},{"publishNumber":"01/UBND-HCM","docSendId":306108,"briefContent":"54365436","publishDate":"10/02/2018 00:00"},{"publishNumber":"14/bcs","docSendId":306107,"briefContent":"213213v","publishDate":"10/02/2018 00:00"},{"publishNumber":"13/bcs","docSendId":306106,"briefContent":"v213213","publishDate":"10/02/2018 00:00"},{"publishNumber":"03/VP-HCTC","docSendId":306105,"briefContent":"03 aaaa","publishDate":"09/02/2018 00:00"},{"publishNumber":"02/VP-HCTC","docSendId":306104,"briefContent":"qweqwe","publishDate":"09/02/2018 00:00"}]
     * code : 0
     */

    private String message;
    private int code;
    private List<DataBean> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * publishNumber :
         * docSendId : 307200
         * briefContent : test 10h45 ngày 7-6-2018
         * publishDate :
         */

        private String publishNumber;
        private int docSendId;
        private String briefContent;
        private String publishDate;

        public String getPublishNumber() {
            return publishNumber;
        }

        public void setPublishNumber(String publishNumber) {
            this.publishNumber = publishNumber;
        }

        public int getDocSendId() {
            return docSendId;
        }

        public void setDocSendId(int docSendId) {
            this.docSendId = docSendId;
        }

        public String getBriefContent() {
            return briefContent;
        }

        public void setBriefContent(String briefContent) {
            this.briefContent = briefContent;
        }

        public String getPublishDate() {
            return publishDate;
        }

        public void setPublishDate(String publishDate) {
            this.publishDate = publishDate;
        }
    }
}
