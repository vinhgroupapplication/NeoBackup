package com.newsaigonsoft.neoegov.AVolleyManager;

import android.content.Context;

import org.json.JSONObject;

import com.newsaigonsoft.neoegov.Subjects.SumManager;

/**
 * Created by Vinh on 3/13/2018.
 */

public class InforRequest extends SumManager {
    Context context;
    String Url;
    JSONObject Request;
    String AccountID;
    String PassWord;
    String CaseRequest;

    public InforRequest(Context context, String url, JSONObject request, String accountID, String passWord, String caseRequest) {
        this.context = context;
        Url = url;
        Request = request;
        AccountID = accountID;
        PassWord = passWord;
        CaseRequest = caseRequest;
        getInforAccountFromShareReferenced(context);
    }

    public String getCaseRequest() {
        return CaseRequest;
    }

    public void setCaseRequest(String caseRequest) {
        CaseRequest = caseRequest;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public JSONObject getRequest() {
        return Request;
    }

    public void setRequest(JSONObject request) {
        Request = request;
    }

    public String getAccountID() {
        return AccountID;
    }

    public void setAccountID(String accountID) {
        AccountID = accountID;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String passWord) {
        PassWord = passWord;
    }
}
