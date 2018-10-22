package com.newsaigonsoft.neoegov.GsonObject;

import java.util.List;

/**
 * Created by Vinh on 3/29/2018.
 */

public class GsonSlider {


    /**
     * message : success
     * data : [{"pageName":"Trang Chủ ","pageIcon":"item-tc","pagePriority":1,"pageCode":"01"},{"pageName":"Xử Lý Công Việc","pageIcon":"item-xlcv","pagePriority":2,"labels":[{"labelCount":0,"labels":[{"labelCount":2297,"labelName":"Tất cả","labelCode":"2/1001"},{"labelCount":1,"labelName":"Phiếu chuyển","labelCode":"2/1501"},{"labelCount":30,"labelName":"Văn bản trễ hạn","labelCode":"2/1005"},{"labelCount":0,"labelName":"Đã xử lý","labelCode":"2/1006"}],"labelName":"Văn bản đến<span>2297<\/span>","labelCode":""},{"labelCount":0,"labels":[{"labelCount":287,"labelName":"Tất cả","labelCode":"3/1015"},{"labelCount":255,"labelName":"Trình ký ","labelCode":"3/1503"},{"labelCount":0,"labelName":"Đã xử lý","labelCode":"3/1020"}],"labelName":"Văn bản đi<span>287<\/span>","labelCode":""}],"pageCode":"02"},{"pageName":"Tra Cứu","pageIcon":"item-tracuu","pagePriority":3,"labels":[{"labelCount":0,"labelName":"Văn bản đến","labelCode":"0301"},{"labelCount":0,"labelName":"Văn bản đi","labelCode":"0302"}],"pageCode":"03"},{"pageName":"Lịch công tác","pageIcon":"","pagePriority":4,"pageCode":"04"},{"pageName":"Thống Kê Chung","pageIcon":"item-thongke","pagePriority":100,"labels":[{"labelCount":0,"labelName":"Văn bản đến","labelCode":"0501"},{"labelCount":0,"labelName":"Thống kê chỉ đạo","labelCode":"0505"}],"pageCode":"05"}]
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
         * pageName : Trang Chủ
         * pageIcon : item-tc
         * pagePriority : 1
         * pageCode : 01
         * labels : [{"labelCount":0,"labels":[{"labelCount":2297,"labelName":"Tất cả","labelCode":"2/1001"},{"labelCount":1,"labelName":"Phiếu chuyển","labelCode":"2/1501"},{"labelCount":30,"labelName":"Văn bản trễ hạn","labelCode":"2/1005"},{"labelCount":0,"labelName":"Đã xử lý","labelCode":"2/1006"}],"labelName":"Văn bản đến<span>2297<\/span>","labelCode":""},{"labelCount":0,"labels":[{"labelCount":287,"labelName":"Tất cả","labelCode":"3/1015"},{"labelCount":255,"labelName":"Trình ký ","labelCode":"3/1503"},{"labelCount":0,"labelName":"Đã xử lý","labelCode":"3/1020"}],"labelName":"Văn bản đi<span>287<\/span>","labelCode":""}]
         */

        private String pageName;
        private String pageIcon;
        private int pagePriority;
        private String pageCode;
        private List<LabelsBeanX> labels;

        public String getPageName() {
            return pageName;
        }

        public void setPageName(String pageName) {
            this.pageName = pageName;
        }

        public String getPageIcon() {
            return pageIcon;
        }

        public void setPageIcon(String pageIcon) {
            this.pageIcon = pageIcon;
        }

        public int getPagePriority() {
            return pagePriority;
        }

        public void setPagePriority(int pagePriority) {
            this.pagePriority = pagePriority;
        }

        public String getPageCode() {
            return pageCode;
        }

        public void setPageCode(String pageCode) {
            this.pageCode = pageCode;
        }

        public List<LabelsBeanX> getLabels() {
            return labels;
        }

        public void setLabels(List<LabelsBeanX> labels) {
            this.labels = labels;
        }

        public static class LabelsBeanX {
            /**
             * labelCount : 0
             * labels : [{"labelCount":2297,"labelName":"Tất cả","labelCode":"2/1001"},{"labelCount":1,"labelName":"Phiếu chuyển","labelCode":"2/1501"},{"labelCount":30,"labelName":"Văn bản trễ hạn","labelCode":"2/1005"},{"labelCount":0,"labelName":"Đã xử lý","labelCode":"2/1006"}]
             * labelName : Văn bản đến<span>2297</span>
             * labelCode :
             */

            private int labelCount;
            private String labelName;
            private String labelCode;
            private List<LabelsBean> labels;

            public int getLabelCount() {
                return labelCount;
            }

            public void setLabelCount(int labelCount) {
                this.labelCount = labelCount;
            }

            public String getLabelName() {
                return labelName;
            }

            public void setLabelName(String labelName) {
                this.labelName = labelName;
            }

            public String getLabelCode() {
                return labelCode;
            }

            public void setLabelCode(String labelCode) {
                this.labelCode = labelCode;
            }

            public List<LabelsBean> getLabels() {
                return labels;
            }

            public void setLabels(List<LabelsBean> labels) {
                this.labels = labels;
            }

            public static class LabelsBean {
                /**
                 * labelCount : 2297
                 * labelName : Tất cả
                 * labelCode : 2/1001
                 */

                private int labelCount;
                private String labelName;
                private String labelCode;

                public int getLabelCount() {
                    return labelCount;
                }

                public void setLabelCount(int labelCount) {
                    this.labelCount = labelCount;
                }

                public String getLabelName() {
                    return labelName;
                }

                public void setLabelName(String labelName) {
                    this.labelName = labelName;
                }

                public String getLabelCode() {
                    return labelCode;
                }

                public void setLabelCode(String labelCode) {
                    this.labelCode = labelCode;
                }
            }
        }
    }
}
