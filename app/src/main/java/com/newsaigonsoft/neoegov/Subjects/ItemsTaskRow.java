package com.newsaigonsoft.neoegov.Subjects;

/**
 * Created by Vinh on 8/23/2017.
 */

public class ItemsTaskRow {
    long taskId;
    String publishNumber;
    String publishDate;
    String director;
    String executionUnit;
    String taskContent;

    public ItemsTaskRow(long taskId, String publishNumber, String publishDate, String director, String executionUnit, String taskContent) {
        this.taskId = taskId;
        this.publishNumber = publishNumber;
        this.publishDate = publishDate;
        this.director = director;
        this.executionUnit = executionUnit;
        this.taskContent = taskContent;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public String getPublishNumber() {
        return publishNumber;
    }

    public void setPublishNumber(String publishNumber) {
        this.publishNumber = publishNumber;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getExecutionUnit() {
        return executionUnit;
    }

    public void setExecutionUnit(String executionUnit) {
        this.executionUnit = executionUnit;
    }

    public String getTaskContent() {
        return taskContent;
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
    }
}
