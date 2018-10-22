package com.newsaigonsoft.neoegov.Action.FeedBack;

/**
 * Created by Vinh on 11/23/2017.
 */

public interface FeedBackView {

    void showProgress();

    String getFeedBackContent();

    long getDocumentID();

    int getWorkflowTransitionId();

    String getResourceCodeId();

    void closeProgress();

    String getScreen();

    int getTatistic();

    void ToastError(String s);
}
