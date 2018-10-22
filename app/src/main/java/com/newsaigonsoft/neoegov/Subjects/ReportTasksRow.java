package com.newsaigonsoft.neoegov.Subjects;

import org.json.JSONArray;

/**
 * Created by Vinh on 10/4/2017.
 */

public class ReportTasksRow {
    String organizationName;
    String docNumberReport;
    String dateReport;
    String contentReport;
    JSONArray mArrayAttachFile;

    public ReportTasksRow(String organizationName, String docNumberReport, String dateReport, String contentReport, JSONArray mArrayAttachFile) {
        this.organizationName = organizationName;
        this.docNumberReport = docNumberReport;
        this.dateReport = dateReport;
        this.contentReport = contentReport;
        this.mArrayAttachFile = mArrayAttachFile;


    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getDocNumberReport() {
        return docNumberReport;
    }

    public void setDocNumberReport(String docNumberReport) {
        this.docNumberReport = docNumberReport;
    }

    public String getDateReport() {
        return dateReport;
    }

    public void setDateReport(String dateReport) {
        this.dateReport = dateReport;
    }

    public String getContentReport() {
        return contentReport;
    }

    public void setContentReport(String contentReport) {
        this.contentReport = contentReport;
    }

    public JSONArray getmArrayAttachFile() {
        return mArrayAttachFile;
    }

    public void setmArrayAttachFile(JSONArray mArrayAttachFile) {
        this.mArrayAttachFile = mArrayAttachFile;
    }


}
