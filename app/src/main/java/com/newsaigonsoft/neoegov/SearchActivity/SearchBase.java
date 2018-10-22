package com.newsaigonsoft.neoegov.SearchActivity;

import android.content.Intent;

import com.newsaigonsoft.neoegov.Adapter.DocumentAdapter;
import com.newsaigonsoft.neoegov.Adapter.DocumentLookupAdapter;
import com.newsaigonsoft.neoegov.Subjects.ReceivePerson;
import com.newsaigonsoft.neoegov.Subjects.SeachTypeDocument;
import com.newsaigonsoft.neoegov.Subjects.SumManager;

import java.util.ArrayList;

public class SearchBase extends SumManager {
    SearchLogic mSearchLogic;
    SearchHeader mSearchHeader;
    ArrayList<ReceivePerson> arrListDepartmentSearch;
    ArrayList<String> arrayTypeDocumentName;
    ArrayList<Long> arrayTypeDocumentID;
    String mTypeDocument;
    SeachTypeDocument mSeachTypeDocument = new SeachTypeDocument();
    String ReceiveRoomID = "0";
    DocumentAdapter adapterSearch;
    DocumentLookupAdapter adapterDocumentLookup;
    boolean isScroll = false;
    Intent intent;
    ArrayList<String> arrNameDepartment;


}
