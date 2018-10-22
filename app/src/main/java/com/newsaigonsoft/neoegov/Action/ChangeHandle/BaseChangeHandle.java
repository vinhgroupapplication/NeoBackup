package com.newsaigonsoft.neoegov.Action.ChangeHandle;

import java.util.ArrayList;

import com.newsaigonsoft.neoegov.SQLite.SQLite;
import com.newsaigonsoft.neoegov.Subjects.ChangeHandleRow;
import com.newsaigonsoft.neoegov.Subjects.SumManager;

import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LIST_DOCUMENT_SQLITE;

/**
 * Created by Vinh on 3/1/2018.
 */

public class BaseChangeHandle extends SumManager {
    long DocumentID;
    String titleActionBar;
    int workflowTransitionId;
    String resourceCodeId;
    SQLite mSqLiteListDocument = new SQLite(this, LIST_DOCUMENT_SQLITE, null, 1);
    ArrayList<ChangeHandleRow> arrayHandleProcess;
    ChangeHandleLogic mChangeHandleLogic;
    String isScreen;
    int isTatistic;
}
