package com.newsaigonsoft.neoegov.Subjects;

/**
 * Created by Vinh on 9/12/2017.
 */

public class CancelListRow {
    boolean Default;
    long jobId;
    String processer;
    String organizationName;

    public CancelListRow(boolean aDefault, long jobId, String processer, String organizationName) {
        Default = aDefault;
        this.jobId = jobId;
        this.processer = processer;
        this.organizationName = organizationName;
    }

    public boolean isDefault() {
        return Default;
    }

    public void setDefault(boolean aDefault) {
        Default = aDefault;
    }

    public long getJobId() {
        return jobId;
    }

    public void setJobId(long jobId) {
        this.jobId = jobId;
    }

    public String getProcesser() {
        return processer;
    }

    public void setProcesser(String processer) {
        this.processer = processer;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }
}
