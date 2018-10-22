package com.newsaigonsoft.neoegov.Fragment.Login;

import android.widget.ArrayAdapter;

/**
 * Created by Vinh on 10/11/2017.
 */

public interface LoginFmView {
    void showErrorMsg(String msg);
    void setAdapterBeforeLocal(ArrayAdapter adapter);
    void eventFmLogin(String user, String pass, String Link);
    void setHttpSpinner(String http_https);
    void disibleFeilAfterTap();
}
