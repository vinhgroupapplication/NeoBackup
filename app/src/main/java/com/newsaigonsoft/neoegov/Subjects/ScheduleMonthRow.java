package com.newsaigonsoft.neoegov.Subjects;

import com.newsaigonsoft.neoegov.GsonObject.GsonScheduleDay;

import java.util.List;

public class ScheduleMonthRow {
    String date;
    List<GsonScheduleDay.DataBean> arrData;
    boolean showList;

    public ScheduleMonthRow(String date, List<GsonScheduleDay.DataBean> arrData, boolean showList) {
        this.date = date;
        this.arrData = arrData;
        this.showList = showList;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<GsonScheduleDay.DataBean> getArrData() {
        return arrData;
    }

    public void setArrData(List<GsonScheduleDay.DataBean> arrData) {
        this.arrData = arrData;
    }

    public boolean isShowList() {
        return showList;
    }

    public void setShowList(boolean showList) {
        this.showList = showList;
    }
}
