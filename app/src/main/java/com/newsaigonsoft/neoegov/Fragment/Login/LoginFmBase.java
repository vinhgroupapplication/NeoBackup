package com.newsaigonsoft.neoegov.Fragment.Login;

import android.app.Fragment;

import java.util.ArrayList;

import com.newsaigonsoft.neoegov.LoginActivity.MainActivity;
import com.newsaigonsoft.neoegov.SQLite.SQLite;

/**
 * Created by Vinh on 3/7/2018.
 */

public class LoginFmBase extends Fragment {
    ArrayList<String> arrLocalHost;
    String[] arrBeforLocal = {"https", "http"};
    LoginFmLogic mLoginFmLogic;
    SQLite mSqLite;
    boolean isCheckShowPassWord = true;
    MainActivity mMainActivity;
}
