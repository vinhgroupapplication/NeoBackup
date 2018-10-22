package com.newsaigonsoft.neoegov.Action.ChangeHandle;

import com.newsaigonsoft.neoegov.Adapter.ChangeHandlerAdapter;
import com.newsaigonsoft.neoegov.Subjects.ChangeHandleRow;

import java.util.ArrayList;

/**
 * Created by Vinh on 11/23/2017.
 */

public interface ChangeHandleView {

    long getDocumentID();

    void setListViewHandleProcess(ChangeHandlerAdapter adapterHandleProcess, ArrayList<ChangeHandleRow> arrayHandleProcess);

    void showProgress();

    void closeProgress();

    int getWorkflowTransitionId();

    String getRespirceCodeID();

    String getContent();

    void DeletedDatabse();

    String getIsScreen();

    int getIsTatistic();

    void ShowToast(String s);

    void ShowToastError(String s);
}
