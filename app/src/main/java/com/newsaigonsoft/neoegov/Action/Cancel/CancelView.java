package com.newsaigonsoft.neoegov.Action.Cancel;

import android.content.Intent;

import com.newsaigonsoft.neoegov.Adapter.CancelListAdapter;
import com.newsaigonsoft.neoegov.Subjects.CancelListRow;

import java.util.ArrayList;

/**
 * Created by Vinh on 11/18/2017.
 */

public interface CancelView {
    void setCancelList(CancelListAdapter adapter);

    void startIntent(Intent intent);

    void closeProgress();

    void DeteleData();

    boolean isSelectAllCheck();

    void showProgress();

    String ScreenInSide();

    int isTatistic();

    void ShowToastCancel(String s);

    void setAdapterCancelList(ArrayList<CancelListRow> arrCancelList);

    CancelListAdapter getAdapterView();

    void ToastError(String s);

    void CheckAllButton(boolean check);

    long getJobID();
}
