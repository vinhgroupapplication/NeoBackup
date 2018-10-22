package com.newsaigonsoft.neoegov.Subjects;

/**
 * Created by Vinh on 7/12/2017.
 */

public class ScheduleRow {
    String StartDate;
    String Content;
    String ChairMan;
    String Status;
    String PlaceName;
    String DuplicateCal;

    public ScheduleRow(String startDate, String content, String chairMan, String status, String placeName) {
        StartDate = startDate;
        Content = content;
        ChairMan = chairMan;
        Status = status;
        PlaceName = placeName;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getChairMan() {
        return ChairMan;
    }

    public void setChairMan(String chairMan) {
        ChairMan = chairMan;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getPlaceName() {
        return PlaceName;
    }

    public void setPlaceName(String placeName) {
        PlaceName = placeName;
    }

    public String getDuplicateCal() {
        return DuplicateCal;
    }

    public void setDuplicateCal(String duplicateCal) {
        DuplicateCal = duplicateCal;
    }
}
