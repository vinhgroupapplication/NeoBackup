package com.newsaigonsoft.neoegov.LoginActivity;

import org.json.JSONArray;

/**
 * Created by Vinh on 10/3/2017.
 */

public interface LoginView {

    void MoveToLoginScreen();

    void ReplaceFragment();

    void LoginUI();

    void eventLogin(String Host, String User, String Pass);

    void TrainferToOffical(String UserName, String jobTitle);

    void ShowErrorLogin();

    void ChangeErrorText(String msg);

    void InsertModule(JSONArray mJsonArray);

    void closeProgress();

    void showProgress();

    void enableFielLogin();
}
