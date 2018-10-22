package com.newsaigonsoft.neoegov.Action.Return;

import org.json.JSONArray;

import java.util.ArrayList;

import com.newsaigonsoft.neoegov.SQLite.SQLite;
import com.newsaigonsoft.neoegov.Subjects.AttachFile;
import com.newsaigonsoft.neoegov.Subjects.SumManager;

import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LIST_DOCUMENT_SQLITE;

/**
 * Created by Vinh on 3/5/2018.
 */

public class ReturnBase extends SumManager {
    String resourceCodeId;
    long DocumentID;
    int workflowTransitionId;
    String titleActionBar;
    SQLite mSqLiteListDocument = new SQLite(this, LIST_DOCUMENT_SQLITE, null, 1);
    ArrayList<AttachFile> arrFileAttach;
    JSONArray mJsonArrayAttach;
    ReturnLogic mReturnLogic;
    String isScreen;
    int isTatistic;
}
