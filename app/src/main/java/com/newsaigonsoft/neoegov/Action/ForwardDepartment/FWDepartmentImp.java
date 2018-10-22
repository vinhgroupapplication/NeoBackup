package com.newsaigonsoft.neoegov.Action.ForwardDepartment;

import android.widget.CheckBox;

import org.json.JSONObject;

/**
 * Created by Vinh on 12/20/2017.
 */

public interface FWDepartmentImp {

    void OnClickHandle(int position);

    void ReadJsonHandleAndDepartment();

    void RequestForward();

    void CheckallDepartment(CheckBox cbCheckAllDepartment);

    JSONObject getJsonObjectRequest();

    void getExpirationDate(int mNumber);

    void getCountDay(String dateChoose);

    void setAllCheck();
}
