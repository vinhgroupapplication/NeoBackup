package com.newsaigonsoft.neoegov.Action.Cancel;

import com.newsaigonsoft.neoegov.Adapter.CancelListAdapter;
import com.newsaigonsoft.neoegov.SQLite.SQLite;
import com.newsaigonsoft.neoegov.Subjects.SumManager;

import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LIST_DOCUMENT_SQLITE;

/**
 * Created by Vinh on 3/1/2018.
 */

public class BaseCancel extends SumManager {
    SQLite mSqLiteListDocument = new SQLite(this, LIST_DOCUMENT_SQLITE, null, 1);
    CancelLogic mCancelLogic;
    int isTatistic;
    String isScreen;
    String titleActionBar, resourceCodeId;
    long jobID;
    int workflowTransitionId;
    CancelListAdapter adapter;
}
