package com.newsaigonsoft.neoegov.SearchActivity;

import android.content.Intent;
import android.widget.ArrayAdapter;

import com.newsaigonsoft.neoegov.Adapter.DepartmentAdapter;
import com.newsaigonsoft.neoegov.Adapter.DocumentAdapter;
import com.newsaigonsoft.neoegov.Adapter.DocumentLookupAdapter;
import com.newsaigonsoft.neoegov.Subjects.ReceivePerson;

import java.util.ArrayList;

public interface SeachView {
    void showComing(boolean b);

    void showSent(boolean b);

    void showInternal(boolean b);

    void showProcess(boolean b);

    void getSrrListDepartmentSearch(ArrayList<ReceivePerson> arrListDepartmentSearch);

    void setAdatperDepartmentName(DepartmentAdapter adapter, ArrayList<String> arrNameDepartment);

    void showErrorToast();

    void setArrTypeDocumentID(ArrayList<Long> arrayTypeDocumentID);

    void setAdapterEdtComing(ArrayAdapter adapter);

    void setAdapterEdtSent(ArrayAdapter adapter);

    void setAdapterEdtInternal(ArrayAdapter adapter);

    void closeProgress();

    void ToastError(String s);

    int getPositionPage();

    void setListProcess(DocumentAdapter adapterSearch);

    void hideEmpty(int Visible);

    void setListComing(DocumentLookupAdapter adapterDocumentLookup);

    void turnOffSearchAdvance(boolean b);

    boolean getAfterSearch();

    void setAfterSearch(boolean b);

    void startIntent(Intent intent);

    void showWorkArise(boolean b);


}
