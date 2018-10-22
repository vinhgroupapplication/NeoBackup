package com.newsaigonsoft.neoegov.Action.ConfirmConpleted;

import android.content.Intent;

/**
 * Created by Vinh on 11/22/2017.
 */

public interface ConfirmConpletedView {

    String getConfirmDay();

    long getTaskID();

    void showProgress();

    String getConfirmContent();

    void closeProgress();

    int getTatistic();

    String getScreen();


    void startIntent(Intent intent);

    void startIntentOnbackPress(Intent intent);

    void ToastError(String s);
}
