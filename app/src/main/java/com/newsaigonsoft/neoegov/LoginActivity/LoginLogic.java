package com.newsaigonsoft.neoegov.LoginActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.newsaigonsoft.neoegov.AsynTaskManager.AsyncTaskCompleteListener;
import com.newsaigonsoft.neoegov.AsynTaskManager.CaseManager;
import com.newsaigonsoft.neoegov.AsynTaskManager.NeoTask;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.ResuiltObject;
import com.newsaigonsoft.neoegov.Subjects.SumManager;

import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.FULLNAME;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.JOBTITLE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MODULES;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.RESULT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.CHECKLOGIN;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.CHECKLOGINFALSE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.CHECKLOGINTRUE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DIALOG_CENTER;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LOGIN;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SEND_TOKEN;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAG;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.URL_CENTER;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.URL_CENTER_6_1;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.URL_CENTER_6_2;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.nULL_STRING;

/**
 * Created by Vinh on 10/3/2017.
 */

public class LoginLogic extends SumManager implements LoginImp, AsyncTaskCompleteListener<ResuiltObject> {

    LoginView mLoginView;
    Context context;
    private static final String USER_NAME = "usename";
    private static final String PASS_WORD = "password";
    private static final String LINK_LOGIN_6_1 = "/service-mobile-portlet/api/secure/jsonws/mobile/login";
    private static final String LINK_LOGIN_6_2 = "/mobile-service-portlet/services/mobileevent/getUserInfo";
    boolean isSecondLogin = false;


    public LoginLogic(LoginView mLoginView, Context context) {
        this.mLoginView = mLoginView;
        this.context = context;
    }

    @Override
    public void CheckSavedInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            mLoginView.MoveToLoginScreen();
        } else {
            mLoginView.ReplaceFragment();
        }

    }

    @Override
    public void Login(boolean mCheckResume) {
        if (!isNetworkAvailable(context)) {
            showDialogUpdateError("Không thể kết nối với internet.", context);
        }
        String checklogin = getDefaults(CHECKLOGIN, context);
        if (checklogin == null) {
            checklogin = CHECKLOGINFALSE;
        }
        if (checklogin.equals(CHECKLOGINTRUE)) {
            getInforAccountFromShareReferenced(context);
            mLoginView.LoginUI();
            if (!mCheckResume) {
                showProgressDialog(getString(R.string.please_wait), context, DIALOG_CENTER, true);
            }
            mLoginView.eventLogin(getLink(), getUsername(), getPass());
        } else {
            mLoginView.LoginUI();
        }
    }

    @Override
    public void RequestLogin(String Host, String User, String PassWord) {
        Userid = User;
        Pass = PassWord;
        Link = Host;
//        new ReadJson().execute(Host + LINK_LOGIN_6_1);
        new NeoTask(context, this).execute(new CaseManager(context, LOGIN, nULL_STRING, Link + LINK_LOGIN_6_1));
    }

    @Override
    public void onTaskCompleted(String s, String CaseRequest) {
        try {
            JSONObject mJsonObject = new JSONObject(s);
            int result = mJsonObject.getInt(RESULT);
            String UserName = mJsonObject.getString(FULLNAME);
            String jobTitle = mJsonObject.getString(JOBTITLE);
            JSONArray mModule = mJsonObject.getJSONArray(MODULES);
            if (result >= 0) {
                mLoginView.TrainferToOffical(UserName, jobTitle);
                mLoginView.InsertModule(mModule);
                if (!isSecondLogin) {
                    setDefaults(URL_CENTER, URL_CENTER_6_1, context);
                } else {
                    setDefaults(URL_CENTER, URL_CENTER_6_2, context);
                }
            } else {
                mLoginView.ShowErrorLogin();
            }
            mLoginView.enableFielLogin();
        } catch (JSONException e) {
            Log.d(TAG, e.getMessage());
            if (!isSecondLogin) {
                new NeoTask(context, this).execute(new CaseManager(context, LOGIN, nULL_STRING, Link + LINK_LOGIN_6_2));
//                new ReadJson().execute(Link +
//                        LINK_LOGIN_6_2);
                isSecondLogin = true;
            } else {
                mLoginView.enableFielLogin();
                mLoginView.ChangeErrorText(context.getString(R.string.confirm_login_error));
                isSecondLogin = false;
            }

        }
    }


    @Override
    public void SendTokenID() {
//        new sendToken().execute();
        new NeoTask(context, this).execute(new CaseManager(context, SEND_TOKEN, nULL_STRING, nULL_STRING));

    }



    //// TODO: 4/17/2017 send Token to server add start
//    class sendToken extends AsyncTask<String, Integer, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            String TokenID = getDefaults(TOKEN_ID, context);
//            makePostRequestTokenID(LINK_SEND_TOKEN, TokenID, LINK_PLATOM, context);
//            return null;
//        }
//    }

    //// TODO: 4/17/2017  send Token to server add end
    // // TODO: 4/17/2017  ReadJson Login add start
//    class ReadJson extends AsyncTask<String, Integer, String> {
//        @Override
//        protected String doInBackground(String... params) {
////            Log.d(TAG, params[0]);
//            JSONObject mJsonObject = new JSONObject();
//            try {
////                mJsonObject.put(USER_NAME, Userid);
////                mJsonObject.put(PASS_WORD, Pass);
//                mJsonObject.put(TOKEN, getDefaults(TOKEN_ID, context));
//                mJsonObject.put(PLATFORM, "android");
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            return makePostRequestLogin(params[0], mJsonObject.toString(), Userid, Pass, getDefaults(TOKEN_ID, context), ANDROID);
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            try {
//                JSONObject mJsonObject = new JSONObject(s);
//                int result = mJsonObject.getInt(RESULT);
//                String UserName = mJsonObject.getString(FULLNAME);
//                String jobTitle = mJsonObject.getString(JOBTITLE);
//                JSONArray mModule = mJsonObject.getJSONArray(MODULES);
//                if (result >= 0) {
//                    mLoginView.TrainferToOffical(UserName, jobTitle);
//                    mLoginView.InsertModule(mModule);
//                    if (!isSecondLogin) {
//                        setDefaults(URL_CENTER, URL_CENTER_6_1, context);
//                    } else {
//                        setDefaults(URL_CENTER, URL_CENTER_6_2, context);
//                    }
//                } else {
//                    mLoginView.ShowErrorLogin();
//                }
//            } catch (JSONException e) {
//                Log.d(TAG, e.getMessage());
//                if (!isSecondLogin) {
//                    new ReadJson().execute(Link +
//                            LINK_LOGIN_6_2);
//                    isSecondLogin = true;
//                } else {
//                    mLoginView.ChangeErrorText(context.getString(R.string.confirm_login_error));
//                    isSecondLogin = false;
//                }
//
//            }
//            super.onPostExecute(s);
//        }
//    }


}
