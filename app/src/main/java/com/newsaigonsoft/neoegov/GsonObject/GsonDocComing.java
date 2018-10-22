package com.newsaigonsoft.neoegov.GsonObject;

import java.util.List;

public class GsonDocComing {


    /**
     * message : success
     * data : [{"docReceiptId":578771,"receiveDate":"05/07/2018 23:17","numberOfSymbol":"12345","docNumberFull":"31","briefContent":"fsdfsdfs"},{"docReceiptId":578770,"receiveDate":"25/06/2018 11:27","numberOfSymbol":"không số","docNumberFull":"127/C","briefContent":"test 11h28 ngay 25-6-2018"},{"docReceiptId":578677,"receiveDate":"07/06/2018 14:12","numberOfSymbol":"không số","docNumberFull":"126/C","briefContent":"2h12 pm 7 tháng 6 năm 2018"},{"docReceiptId":578676,"receiveDate":"07/06/2018 14:09","numberOfSymbol":"không số","docNumberFull":"125/C","briefContent":"test 2:10 pm ngày 7 tháng 6 năm 2018"},{"docReceiptId":578675,"receiveDate":"06/06/2018 16:31","numberOfSymbol":"không số","docNumberFull":"124/C","briefContent":"yyyyyy"},{"docReceiptId":578674,"receiveDate":"06/06/2018 16:29","numberOfSymbol":"không số","docNumberFull":"123/C","briefContent":"regreg"},{"docReceiptId":578673,"receiveDate":"06/06/2018 16:26","numberOfSymbol":"không số","docNumberFull":"122/C","briefContent":"test 4h26 pm 6-6-2018"},{"docReceiptId":578672,"receiveDate":"06/06/2018 16:13","numberOfSymbol":"không số","docNumberFull":"121/C","briefContent":"123"},{"docReceiptId":578671,"receiveDate":"06/06/2018 15:00","numberOfSymbol":"0022","docNumberFull":"120/C","briefContent":"test 3h01 pm 6-6-2018"},{"docReceiptId":578670,"receiveDate":"06/06/2018 14:38","numberOfSymbol":"0033","docNumberFull":"119/C","briefContent":"2h39 pm 6/6/2018"},{"docReceiptId":578570,"receiveDate":"06/06/2018 09:52","numberOfSymbol":"0011","docNumberFull":"118/C","briefContent":"test 9h52 am 6-6-2018"},{"docReceiptId":578476,"receiveDate":"01/06/2018 14:17","numberOfSymbol":"không số","docNumberFull":"117/C","briefContent":"test 2h17 pm ngày 1-6-2018"},{"docReceiptId":578475,"receiveDate":"01/06/2018 09:35","numberOfSymbol":"không số","docNumberFull":"116/C","briefContent":"test 9h35"},{"docReceiptId":578474,"receiveDate":"31/05/2018 16:51","numberOfSymbol":"không số","docNumberFull":"115/C","briefContent":"test 4h52"},{"docReceiptId":578473,"receiveDate":"31/05/2018 16:49","numberOfSymbol":"không số","docNumberFull":"114/C","briefContent":"4h49 pm ngày 31-5-2018"},{"docReceiptId":578472,"receiveDate":"31/05/2018 16:45","numberOfSymbol":"không số","docNumberFull":"113/C","briefContent":"4h45 ngày 31-5-2018"},{"docReceiptId":578471,"receiveDate":"30/05/2018 11:21","numberOfSymbol":"không số","docNumberFull":"112/C","briefContent":"test 11h22 am ngày 30-5-2018"},{"docReceiptId":578470,"receiveDate":"30/05/2018 11:01","numberOfSymbol":"không số","docNumberFull":"111/C","briefContent":"11: 01 am ngày 30-5-2018"},{"docReceiptId":578375,"receiveDate":"25/05/2018 14:10","numberOfSymbol":"4444","docNumberFull":"110/C","briefContent":"test 456"},{"docReceiptId":578374,"receiveDate":"23/05/2018 11:31","numberOfSymbol":"không số","docNumberFull":"109/C","briefContent":"123"}]
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
         * docReceiptId : 578771
         * receiveDate : 05/07/2018 23:17
         * numberOfSymbol : 12345
         * docNumberFull : 31
         * briefContent : fsdfsdfs
         */

        private int docReceiptId;
        private String receiveDate;
        private String numberOfSymbol;
        private String docNumberFull;
        private String briefContent;

        public int getDocReceiptId() {
            return docReceiptId;
        }

        public void setDocReceiptId(int docReceiptId) {
            this.docReceiptId = docReceiptId;
        }

        public String getReceiveDate() {
            return receiveDate;
        }

        public void setReceiveDate(String receiveDate) {
            this.receiveDate = receiveDate;
        }

        public String getNumberOfSymbol() {
            return numberOfSymbol;
        }

        public void setNumberOfSymbol(String numberOfSymbol) {
            this.numberOfSymbol = numberOfSymbol;
        }

        public String getDocNumberFull() {
            return docNumberFull;
        }

        public void setDocNumberFull(String docNumberFull) {
            this.docNumberFull = docNumberFull;
        }

        public String getBriefContent() {
            return briefContent;
        }

        public void setBriefContent(String briefContent) {
            this.briefContent = briefContent;
        }
    }
}
