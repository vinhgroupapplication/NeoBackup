package com.newsaigonsoft.neoegov.LoginActivity;

import android.os.Bundle;

/**
 * Created by Vinh on 10/3/2017.
 */

public interface LoginImp {
    void CheckSavedInstanceState(Bundle savedInstanceState);
    void Login(boolean mCheckResume);
    void RequestLogin(String Host, String User, String PassWord);
    void SendTokenID();
}
