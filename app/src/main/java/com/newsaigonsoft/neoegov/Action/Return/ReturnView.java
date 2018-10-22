package com.newsaigonsoft.neoegov.Action.Return;

import com.newsaigonsoft.neoegov.Adapter.ReturnDocumentAdapter;

/**
 * Created by Vinh on 12/19/2017.
 */

public interface ReturnView {
    long getDocumentID();

    int getWorkFlow();

    String getResourceCodeId();

    String getContent();

    void deleteData();

    void showToast();

    void closeProgress();

    String getScreen();

    int getIsTatistic();

    void setListReturn(ReturnDocumentAdapter adapter);
}
