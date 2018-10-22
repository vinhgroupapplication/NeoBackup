package com.newsaigonsoft.neoegov.Action.InputForward;

import android.os.Handler;

import java.util.ArrayList;

import com.newsaigonsoft.neoegov.SQLite.SQLite;
import com.newsaigonsoft.neoegov.Subjects.AttachFile;
import com.newsaigonsoft.neoegov.Subjects.SumManager;

import static com.newsaigonsoft.neoegov.Subjects.KeyManager.INPUT_PERSON_SQLITE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LIST_DOCUMENT_SQLITE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SEND_WAITING_SQLITE;

/**
 * Created by Vinh on 3/2/2018.
 */

public class InputForwardBase extends SumManager {
    SQLite mSqLiteSendWaiting = new SQLite(this, SEND_WAITING_SQLITE, null, 1);
    SQLite mSqLiteListDocument = new SQLite(this, LIST_DOCUMENT_SQLITE, null, 1);
    int ForwardOrReport;
    String titleActionBar;
    Handler mHandler = new Handler();
    InputForwardLogic mInputForwardLogic;
    SQLite mSqLite = new SQLite(this, INPUT_PERSON_SQLITE, null, 1);
    int WorkflowTransition;
    String resourceCodeId = "0";
    ArrayList<AttachFile> arrFileAttach;
    long DocumentID;
    String ProcessPerson;
    String iScreen;
}
