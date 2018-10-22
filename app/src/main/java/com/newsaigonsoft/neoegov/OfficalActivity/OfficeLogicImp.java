package com.newsaigonsoft.neoegov.OfficalActivity;

import org.json.JSONArray;

/**
 * Created by Vinh on 12/6/2017.
 */

public interface OfficeLogicImp {

    void runRequestHttp(String UnderLoad);

    void SliderData(String creencomeback);

    void ReLoadComeBack(String creencomeback);

    void RequestDocumentLookup();

    void RequestJsonDocument();

    void arrResuiltDocumentLookup(JSONArray mJsonArray, boolean insert);

    void getListDocumentSQLite(int i, String urlNA);

    void notifySeen();

    void RequestComeBackFromListDepartmentStatist();

}
