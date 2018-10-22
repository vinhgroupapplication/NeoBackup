package com.newsaigonsoft.neoegov.Action.InputForward;

import org.json.JSONObject;

/**
 * Created by Vinh on 12/21/2017.
 */

public interface InputForwardImp {

    void setListInputPerSon();

    void filterName(String newText);

    void upDateListEventSearchView();

    void requestForwardPerson();

    JSONObject getJsonWaiting();
}
