package com.newsaigonsoft.neoegov.Action.Extend;

/**
 * Created by Vinh on 11/23/2017.
 */

public interface ExtendView {
    void showProgress();

    String getScreen();

    long getTatistic();

    long getJobID();

    void closeProgress();

    String getResourceCodeId();

    String getProcessDay();

    String getExpirationDate();

    String getContent();

    void ToastError(String s);
}
