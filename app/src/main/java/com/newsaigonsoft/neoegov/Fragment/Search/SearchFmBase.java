package com.newsaigonsoft.neoegov.Fragment.Search;

import android.app.Fragment;

import com.newsaigonsoft.neoegov.OfficalActivity.OfficalActivity;
import com.newsaigonsoft.neoegov.Subjects.ReceivePerson;
import com.newsaigonsoft.neoegov.Subjects.SeachTypeDocument;

import java.util.ArrayList;

/**
 * Created by Vinh on 3/7/2018.
 */

public class SearchFmBase extends Fragment {
    OfficalActivity mOfficalActivity;
    SearchFmLogic mSearchFmLogic;
    ArrayList<Long> arrayTypeDocumentID;
    ArrayList<ReceivePerson> mArrListDepartmentSearch;
    SeachTypeDocument mSeachTypeDocument = new SeachTypeDocument();
    String ReceiveRoomID = "0";
    String screenNameInSide;
    boolean clicked = false;
}
