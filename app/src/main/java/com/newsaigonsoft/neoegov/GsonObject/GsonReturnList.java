package com.newsaigonsoft.neoegov.GsonObject;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Vinh on 4/2/2018.
 */

public class GsonReturnList {


    /**
     * message : success
     * data : [{"default":true,"organizationName":"Phòng Đô thị","receiverId":"141745","receiverName":"Nguyễn Thanh Chương","roleName":"Trưởng Phòng","organizationId":143608}]
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
         * default : true
         * organizationName : Phòng Đô thị
         * receiverId : 141745
         * receiverName : Nguyễn Thanh Chương
         * roleName : Trưởng Phòng
         * organizationId : 143608
         */

        @SerializedName("default")
        private boolean defaultX;
        private String organizationName;
        private String receiverId;
        private String receiverName;
        private String roleName;
        private int organizationId;

        public boolean isDefaultX() {
            return defaultX;
        }

        public void setDefaultX(boolean defaultX) {
            this.defaultX = defaultX;
        }

        public String getOrganizationName() {
            return organizationName;
        }

        public void setOrganizationName(String organizationName) {
            this.organizationName = organizationName;
        }

        public String getReceiverId() {
            return receiverId;
        }

        public void setReceiverId(String receiverId) {
            this.receiverId = receiverId;
        }

        public String getReceiverName() {
            return receiverName;
        }

        public void setReceiverName(String receiverName) {
            this.receiverName = receiverName;
        }

        public String getRoleName() {
            return roleName;
        }

        public void setRoleName(String roleName) {
            this.roleName = roleName;
        }

        public int getOrganizationId() {
            return organizationId;
        }

        public void setOrganizationId(int organizationId) {
            this.organizationId = organizationId;
        }
    }
}
