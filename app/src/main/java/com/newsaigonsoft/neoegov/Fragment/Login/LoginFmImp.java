package com.newsaigonsoft.neoegov.Fragment.Login;

import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by Vinh on 10/11/2017.
 */

public interface LoginFmImp {
    void LoginOrShowError(EditText edtUser, EditText edtPass, Spinner spnBeforLocal,EditText edtLink);
    void setSpinnerBeforLocal(String[] arrBeforLocal);
    void setLinkTitle(String Link);
}
