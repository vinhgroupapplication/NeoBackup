package com.newsaigonsoft.neoegov.GsonObject;

import java.util.List;

/**
 * Created by Vinh on 4/2/2018.
 */

public class GsonForwardTree {

    /**
     * message : success
     * data : {"processer":"Nguyễn Dương","isProcessing":false,"receiveDate":"30/03/2018 10:44","processContent":"N/A","children":[{"processer":"Võ Văn Hoan","isProcessing":false,"receiveDate":"30/03/2018 10:44","processContent":"N/A","children":[{"processer":"Nguyễn Thanh Chương [Đã xem - Thời hạn xử lý: 12/04/2018]","isProcessing":false,"receiveDate":"30/03/2018 10:45","processContent":"N/A","processOrg":"UBND TP.HCM - Phòng Đô thị","roleName":"Trưởng Phòng","processDate":""},{"processer":"Lê Tấn Cường (Nguyễn Dương: Nhập liệu thay)","isProcessing":false,"receiveDate":"30/03/2018 10:46","processContent":"N/A","children":[{"processer":"Võ Văn Hoan [Đã xem]","isProcessing":true,"receiveDate":"30/03/2018 10:48","processContent":"N/A","processOrg":"UBND TP.HCM - Thường trực UB và LĐVP","roleName":"Chánh văn phòng","processDate":""}],"processOrg":"UBND TP.HCM - Phòng Kinh tế","roleName":"Trưởng Phòng","processDate":"30/03/2018 10:48"},{"processer":"Lê Văn Nhân [Đã xem]","isProcessing":false,"receiveDate":"30/03/2018 10:51","processContent":"N/A","processOrg":"UBND TP.HCM - Ban Tiếp Công Dân","roleName":"Chuyên Viên","processDate":""}],"processOrg":"UBND TP.HCM - Thường trực UB và LĐVP","roleName":"Chánh văn phòng","processDate":"30/03/2018 10:45"}],"processOrg":"UBND TP.HCM - Phòng Hành chính - Tổ chức","roleName":"Văn Thư","processDate":"30/03/2018 10:44"}
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
         * processer : Nguyễn Dương
         * isProcessing : false
         * receiveDate : 30/03/2018 10:44
         * processContent : N/A
         * children : [{"processer":"Võ Văn Hoan","isProcessing":false,"receiveDate":"30/03/2018 10:44","processContent":"N/A","children":[{"processer":"Nguyễn Thanh Chương [Đã xem - Thời hạn xử lý: 12/04/2018]","isProcessing":false,"receiveDate":"30/03/2018 10:45","processContent":"N/A","processOrg":"UBND TP.HCM - Phòng Đô thị","roleName":"Trưởng Phòng","processDate":""},{"processer":"Lê Tấn Cường (Nguyễn Dương: Nhập liệu thay)","isProcessing":false,"receiveDate":"30/03/2018 10:46","processContent":"N/A","children":[{"processer":"Võ Văn Hoan [Đã xem]","isProcessing":true,"receiveDate":"30/03/2018 10:48","processContent":"N/A","processOrg":"UBND TP.HCM - Thường trực UB và LĐVP","roleName":"Chánh văn phòng","processDate":""}],"processOrg":"UBND TP.HCM - Phòng Kinh tế","roleName":"Trưởng Phòng","processDate":"30/03/2018 10:48"},{"processer":"Lê Văn Nhân [Đã xem]","isProcessing":false,"receiveDate":"30/03/2018 10:51","processContent":"N/A","processOrg":"UBND TP.HCM - Ban Tiếp Công Dân","roleName":"Chuyên Viên","processDate":""}],"processOrg":"UBND TP.HCM - Thường trực UB và LĐVP","roleName":"Chánh văn phòng","processDate":"30/03/2018 10:45"}]
         * processOrg : UBND TP.HCM - Phòng Hành chính - Tổ chức
         * roleName : Văn Thư
         * processDate : 30/03/2018 10:44
         */

        private String processer;
        private boolean isProcessing = false;
        private String receiveDate;
        private String processContent;
        private String processOrg;
        private String roleName;
        private String processDate;
        private List<DataBean> children;

        public String getProcesser() {
            return processer;
        }

        public void setProcesser(String processer) {
            this.processer = processer;
        }

        public boolean isIsProcessing() {
            return isProcessing;
        }

        public void setIsProcessing(boolean isProcessing) {
            this.isProcessing = isProcessing;
        }

        public String getReceiveDate() {
            return receiveDate;
        }

        public void setReceiveDate(String receiveDate) {
            this.receiveDate = receiveDate;
        }

        public String getProcessContent() {
            return processContent;
        }

        public void setProcessContent(String processContent) {
            this.processContent = processContent;
        }

        public String getProcessOrg() {
            return processOrg;
        }

        public void setProcessOrg(String processOrg) {
            this.processOrg = processOrg;
        }

        public String getRoleName() {
            return roleName;
        }

        public void setRoleName(String roleName) {
            this.roleName = roleName;
        }

        public String getProcessDate() {
            return processDate;
        }

        public void setProcessDate(String processDate) {
            this.processDate = processDate;
        }

        public List<DataBean> getChildren() {
            return children;
        }

        public void setChildren(List<DataBean> children) {
            this.children = children;
        }
    }
}
