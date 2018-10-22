package com.newsaigonsoft.neoegov.GsonObject;

import java.util.List;

/**
 * Created by Vinh on 3/30/2018.
 */

public class GsonHandle {


    /**
     * message : success
     * data : [{"id":103,"choosen":false,"name":"Lưu tham khảo"},{"id":106,"choosen":false,"name":"Sao y"},{"id":201,"choosen":false,"name":"Dự thảo văn bản "},{"id":301,"choosen":false,"name":"Phiếu chuyển"}]
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
         * id : 103
         * choosen : false
         * name : Lưu tham khảo
         */

        private int id;
        private boolean choosen;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public boolean isChoosen() {
            return choosen;
        }

        public void setChoosen(boolean choosen) {
            this.choosen = choosen;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
