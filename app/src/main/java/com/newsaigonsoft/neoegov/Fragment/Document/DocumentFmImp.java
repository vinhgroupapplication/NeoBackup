package com.newsaigonsoft.neoegov.Fragment.Document;

import android.content.Intent;

import java.util.ArrayList;

import com.newsaigonsoft.neoegov.GsonObject.GsonDocument;
import com.newsaigonsoft.neoegov.Subjects.ItemsDocument;
import com.newsaigonsoft.neoegov.Subjects.ItemsDocumentLookUp;

/**
 * Created by Vinh on 10/24/2017.
 */

public interface DocumentFmImp {

    void startEvent(String TypeHomeListDocument, int positionPage);

    void startSrollList(int positionPage, ArrayList<ItemsDocumentLookUp> arrDocumentLookUp, int numberOfPage);

    void setMsgAfterTrainfer(String creencomeback, String Resuilt, String InputComBack, String DocumentNumber, String inputName);

    void setMsgAferTask(String creencomeback, String Resuilt, String InputComBack);

    void putIntentDocument(Intent intent, int position, ArrayList<GsonDocument.DataBean> arrDocument, int positionPage, String iScreen);

    void putIntentLookup(Intent intent, long id, int statistic, String departmentPosition, String iScreen);
}
