package com.newsaigonsoft.neoegov.Fragment.Search;

import android.widget.ArrayAdapter;

import com.newsaigonsoft.neoegov.Adapter.DepartmentAdapter;
import com.newsaigonsoft.neoegov.Subjects.ReceivePerson;
import com.newsaigonsoft.neoegov.Subjects.SearchRow;

import java.util.ArrayList;

/**
 * Created by Vinh on 10/23/2017.
 */

public interface SearchFmView {

    void enableSearchComing();

    void enableSearchSent();

    void enableSearchInternal();

    void enableSearchProcess();


    void setAdapterEdtComing(ArrayAdapter adapter);

    void setAdapterEdtSent(ArrayAdapter adapter);

    void setAdapterEdtInternal(ArrayAdapter adapter);

    void setArrTypeDocumentID(ArrayList<Long> arrayTypeDocumentID);

    void showNotifySearchError();

    void setMsgNotifySearchError(String s);

    void closeProgress();

    void hideNotifySearchError();

    void startEvent(SearchRow mSearchRow);

    void setAdatperDepartmentName(DepartmentAdapter adapter, ArrayList<String> arrNameDepartment);

    void showToast();

    void getSrrListDepartmentSearch(ArrayList<ReceivePerson> arrListDepartmentSearch);

    void ToastError(String s);
}
