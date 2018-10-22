package com.newsaigonsoft.neoegov.Action.Remind;

import android.content.Intent;

import com.newsaigonsoft.neoegov.Adapter.RemindAdapter;

/**
 * Created by Vinh on 12/16/2017.
 */

public interface RemindView {
    void setLvReturn(RemindAdapter adapter);

    String getEmailContent();

    String getSmsContent();

    String getTitleEmail();

    void showToastError(String s);

    void showProgress();

    long getTaskID();

    void ShowErrorToasts();

    String getIsScreen();

    int getIsTatistic();

    void closeProgress();

    void startIntentOnbackPress(Intent intent);

    void startIntent(Intent intent);
}
