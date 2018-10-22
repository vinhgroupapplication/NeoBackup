package com.newsaigonsoft.neoegov.SearchActivity;

import android.content.Intent;

import com.newsaigonsoft.neoegov.GsonObject.GsonDocument;
import com.newsaigonsoft.neoegov.GsonObject.GsonSearchInternal;
import com.newsaigonsoft.neoegov.GsonObject.GsonSearchLookup;
import com.newsaigonsoft.neoegov.GsonObject.GsonSearchProcess;
import com.newsaigonsoft.neoegov.GsonObject.GsonSearchSent;
import com.newsaigonsoft.neoegov.GsonObject.GsonSearchWorkArise;

import java.util.ArrayList;

public interface SeachImp {
    void setSearchView(String stringExtra, boolean b);

    void searchSimpleProcess(GsonSearchProcess.DataBean mDataBean);

    void eventGetDepartmentSearchList();

    void requestJsonDocumentType(String s);

    void searchSimpleComing(GsonSearchLookup.DataBean mLookup);

    void searchSimpleSent(GsonSearchSent.DataBean mSent);

    void searchSimpleInternal(GsonSearchInternal.DataBean mInternal);

    void putIntentDocument(Intent intent, int position, ArrayList<GsonDocument.DataBean> arrDocument, int positionPage, String stringExtra);

    void putIntentLookup(Intent intent, long id, int statistic, String departmentPosition, String stringExtra);

    void searchSimpleWorkArise(GsonSearchWorkArise.DataBean requestWorkArise);
}
