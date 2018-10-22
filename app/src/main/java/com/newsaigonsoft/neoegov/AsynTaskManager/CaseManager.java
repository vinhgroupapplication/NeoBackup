package com.newsaigonsoft.neoegov.AsynTaskManager;

import android.content.Context;

import com.newsaigonsoft.neoegov.Subjects.SumManager;

/**
 * Created by Vinh on 12/12/2017.
 */

public class CaseManager extends SumManager {
    Context context;
    String Case;
    String Request;
    String Url;

    public CaseManager(Context context, String aCase, String request, String urls) {
        this.context = context;
        Case = aCase;
        Request = request;
        this.Url = urls;
        getInforAccountFromShareReferenced(context);
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getCase() {
        return Case;
    }

    public void setCase(String aCase) {
        Case = aCase;
    }

    public String getRequest() {
        return Request;
    }

    public void setRequest(String request) {
        Request = request;
    }

    public Context getContext() {
        return context;
    }
}
