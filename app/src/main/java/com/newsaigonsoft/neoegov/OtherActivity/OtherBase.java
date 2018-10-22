package com.newsaigonsoft.neoegov.OtherActivity;

import java.util.ArrayList;
import java.util.List;

import com.newsaigonsoft.neoegov.GsonObject.GsonDocReceiptConnects;
import com.newsaigonsoft.neoegov.GsonObject.GsonLocalConnects;
import com.newsaigonsoft.neoegov.GsonObject.GsonSendConnects;
import com.newsaigonsoft.neoegov.Subjects.DocConnectionLocalRow;
import com.newsaigonsoft.neoegov.Subjects.DocConnectionReciverRow;
import com.newsaigonsoft.neoegov.Subjects.DocConnectionSendRow;
import com.newsaigonsoft.neoegov.Subjects.HandleChangeRow;
import com.newsaigonsoft.neoegov.Subjects.SumManager;

/**
 * Created by Vinh on 3/8/2018.
 */

public class OtherBase extends SumManager {
//    ArrayList<DocConnectionReciverRow> arrDocCnReceipt;
//    ArrayList<DocConnectionSendRow> arrDocCnSend;
    ArrayList<HandleChangeRow> arrHandleChange;
//    ArrayList<DocConnectionLocalRow> arrDocCnLocal;
    String OtherFunction;
    String titleActionBar;
    List<GsonDocReceiptConnects> arrDocReciept;
    List<GsonSendConnects> arrDocSend;
    List<GsonLocalConnects> arrDocCnLocal;
}
