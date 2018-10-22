package com.newsaigonsoft.neoegov.Action.ForwardDepartment;

import android.os.Handler;

import com.newsaigonsoft.neoegov.SQLite.SQLite;
import com.newsaigonsoft.neoegov.Subjects.AttachFile;
import com.newsaigonsoft.neoegov.Subjects.DialogFalseRow;
import com.newsaigonsoft.neoegov.Subjects.ReceivePerson;
import com.newsaigonsoft.neoegov.Subjects.ReceiversRow;
import com.newsaigonsoft.neoegov.Subjects.SumManager;

import java.util.ArrayList;
import java.util.List;

import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LIST_DOCUMENT_SQLITE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SEND_WAITING_DEPARTMENT_SQLITE;

/**
 * Created by Vinh on 3/2/2018.
 */

public class FWDepartmentBase extends SumManager {
    List<ReceiversRow> arrReceiveTemporary;
    String titleActionbar;
    long DocumentID;
    String DocumentNumber;
    int workflowTransitionId;
    String resourceCodeId;
    SQLite mSqLiteListDocument = new SQLite(this, LIST_DOCUMENT_SQLITE, null, 1);
    SQLite mSqLiteSendWaitingDepartment = new SQLite(this, SEND_WAITING_DEPARTMENT_SQLITE, null, 1);
    ArrayList<AttachFile> arrFileAttach;
    FWDepartmentLogic mFwDepartmentLogic;
    String iScreen;
    boolean CancelChangeTextRequest = false;
    Handler handler = new Handler();


    public boolean isCancelChangeTextRequest() {
        return CancelChangeTextRequest;
    }

    public void setCancelChangeTextRequest(boolean cancelChangeTextRequest) {
        CancelChangeTextRequest = cancelChangeTextRequest;
    }
}
