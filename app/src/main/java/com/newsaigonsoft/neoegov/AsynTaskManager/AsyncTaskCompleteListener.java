package com.newsaigonsoft.neoegov.AsynTaskManager;

/**
 * Created by Vinh on 12/12/2017.
 */

public interface AsyncTaskCompleteListener<ResuiltObject> {
    void onTaskCompleted(String s, String CaseRequest);
}
