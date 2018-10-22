package com.newsaigonsoft.neoegov.Action.InputForward;

import org.json.JSONObject;

import java.util.ArrayList;

import com.newsaigonsoft.neoegov.Adapter.ReceivePersonAdapter;
import com.newsaigonsoft.neoegov.SQLite.SQLite;
import com.newsaigonsoft.neoegov.Subjects.AttachFile;

/**
 * Created by Vinh on 12/21/2017.
 */

public interface InputForwardView {

    SQLite getSqlite();

    long getDocumentID();

    int getForwardOrReportOrRelease();

    void setListInputPerson(ReceivePersonAdapter receivePersonAdapter);

    ArrayList<AttachFile> getArrayAttach();

//    int getTrainferReport();
//
//    int getTrainferID();
//
//    int getTrainferRelease();

    int getResourceCodeID();

    String getContent();

    boolean isCheckEmail();

    boolean IsCheckUrgent();

    void UpdateDatabase();

    String getScreen();

    void insertSendWaiting(JSONObject mJsonObject);

    void DeleteData();

    void showProgress();

    void closeProgress();

    void showToast(String s);

    String getDocumentNumber();

    int getWorkFlowID();

    String getNameInput();

    void AllCheck(boolean checkAll);

//    String getTrainfer_14();
}
