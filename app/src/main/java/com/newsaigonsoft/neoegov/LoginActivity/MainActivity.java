package com.newsaigonsoft.neoegov.LoginActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.newsaigonsoft.neoegov.Fragment.Login.LoginFragment;
import com.newsaigonsoft.neoegov.OfficalActivity.OfficalActivity;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.SQLite.SQLite;
import com.newsaigonsoft.neoegov.Service.SendDocumentService;
import com.newsaigonsoft.neoegov.Subjects.SumManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.JOBTITLE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MODULE_CODE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MODULE_NAME;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.PASSWORD;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.SERVER_URL;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.USERNAME;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.CHECKLOGIN;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.CHECKLOGINTRUE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.CREATE_TABLE_LOCAL_HOST_SQLITE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.CREATE_TABLE_MODULE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DIALOG_CENTER;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.FULLNAME_USER;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.INFOR_MODULE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.INFOR_MODULE_SQLITE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LOCAL_HOST_SQLITE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LOGIN_FRAGMENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SELECT_FROM;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TRAINFERDOCUMENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TRAINFERDOCUMENTOK;

public class MainActivity extends SumManager implements LoginView {
    String UserID, UserPass;
    // , Link;
    FragmentManager manager = getFragmentManager();
    String refreshToken;
    LoginFragment loginFragment = new LoginFragment();
    SQLite mSqLite = new SQLite(this, LOCAL_HOST_SQLITE, null, 1);
    LoginLogic mLoginLogic;
    SQLite mSqLiteModule = new SQLite(this, INFOR_MODULE_SQLITE, null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN , WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN );
        setContentView(R.layout.activity_main);
        getInforAccountFromShareReferenced(this);
        createDataBaseRememeberLocalhostSQLite();
        // get Token from FCM add start
        refreshToken = FirebaseInstanceId.getInstance().getToken();
        // get Token from FCM add end
        Log.d("Token", refreshToken + "");
        mLoginLogic = new LoginLogic(this, this);
        mLoginLogic.CheckSavedInstanceState(savedInstanceState);
//        if (savedInstanceState == null) {
//            Login(false);
//        } else {
//            loginFragment = (LoginFragment) getFragmentManager().findFragmentByTag(LOGIN_FRAGMENT);
//        }
    }

//    public void Login(boolean mCheckResume) {
//        if (!isNetworkAvailable(this)) {
//            showDialogUpdateError(getString(R.string.no_connection), this);
//        }
//        String checklogin = getDefaults(CHECKLOGIN, MainActivity.this);
//        if (checklogin == null) {
//            checklogin = CHECKLOGINFALSE;
//        }
//        if (checklogin.equals(CHECKLOGINTRUE)) {
//            getInforAccountFromShareReferenced(this);
//            LoginUI();
//            if (!mCheckResume) {
//                showProgressDialog(getString(R.string.please_wait), MainActivity.this, DIALOG_CENTER, true);
//            }
//            eventLogin(getLink(), getUsername(), getPass());
//        } else {
//            LoginUI();
//        }
//    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
//
//    private void LoginUI() {
//        FragmentTransaction transaction = manager.beginTransaction();
//        transaction.replace(R.id.content, loginFragment, LOGIN_FRAGMENT);
//        transaction.commit();
//
//    }

//    public void eventLogin(final String host, final String user, final String pass) {
//        showProgressDialog(getString(R.string.please_wait), MainActivity.this, DIALOG_CENTER, true);
//        JSONObject mJsonObject = new JSONObject();
//        try {
//            mJsonObject.put(USER_NAME, user);
//            mJsonObject.put(PASS_WORD, pass);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        new ReadJson().execute(host +
//                LINK_LOGIN);
//        UserID = user;
//        UserPass = pass;
//        Link = host;
//    }

//    public void senDeviceToken() {
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                new sendToken().execute();
//            }
//        });
//    }

    @Override
    public void MoveToLoginScreen() {
        mLoginLogic.Login(false);
    }

    @Override
    public void ReplaceFragment() {
        loginFragment = (LoginFragment) getFragmentManager().findFragmentByTag(LOGIN_FRAGMENT);
    }

    @Override
    public void LoginUI() {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.content, loginFragment, LOGIN_FRAGMENT);
        transaction.commit();
    }

    @Override
    public void eventLogin(String Host, String User, String Pass) {
        showProgressDialog(getString(R.string.please_wait), MainActivity.this, DIALOG_CENTER, false);
//        JSONObject mJsonObject = new JSONObject();
//        try {
//            mJsonObject.put(USER_NAME, user);
//            mJsonObject.put(PASS_WORD, pass);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        mLoginLogic.RequestLogin(Host, User, Pass);
        UserID = User;
        UserPass = Pass;
        Link = Host;
    }

    @Override
    public void TrainferToOffical(String UserName, String jobTitle) {
        Intent intent = new Intent(MainActivity.this, OfficalActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        setDefaults(CHECKLOGIN, CHECKLOGINTRUE, MainActivity.this);
        setDefaults(FULLNAME_USER, UserName, MainActivity.this);
        setDefaults(JOBTITLE, jobTitle, MainActivity.this);
        loginFragment.rememberLocal();
        loginFragment.getLocalName();
//        senDeviceToken();
        mLoginLogic.SendTokenID();
        Intent serviceIntent = new Intent(MainActivity.this, SendDocumentService.class);
        serviceIntent.putExtra(TRAINFERDOCUMENT, TRAINFERDOCUMENTOK);
        startService(serviceIntent);
        startActivity(intent);
        closeProgressDialog();
    }

    @Override
    public void ShowErrorLogin() {
        loginFragment.changeErrorText(getString(R.string.confirm_login_error));
        closeProgressDialog();
    }

    @Override
    public void ChangeErrorText(String msg) {
        loginFragment.changeErrorText(msg);
    }

    @Override
    public void InsertModule(JSONArray mJsonArray) {
        mSqLiteModule.QueryData(CREATE_TABLE_MODULE);
        Cursor mCursor = mSqLiteModule.GetData(SELECT_FROM + INFOR_MODULE);
        int mCount = mCursor.getCount();
        if (mCount == 0) {
            try {
                for (int i = 0; i < mJsonArray.length(); i++) {
                    JSONObject mObject = mJsonArray.getJSONObject(i);
                    String moduleCode = mObject.getString(MODULE_CODE);
                    String moduleName = mObject.getString(MODULE_NAME);
                    String serverUrl = mObject.getString(SERVER_URL);
                    String username = mObject.getString(USERNAME);
                    String password = mObject.getString(PASSWORD);
                    mSqLiteModule.QueryData("insert into " + INFOR_MODULE + " VALUES('" +
                            moduleCode + "','" + moduleName + "','" + serverUrl + "','" + username + "', '" +
                            password + "')");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void closeProgress() {
        closeProgressDialog();
    }

    @Override
    public void showProgress() {
        showProgressDialog("", this, DIALOG_CENTER, false);
    }

    @Override
    public void enableFielLogin() {
        loginFragment.enableFeil();
    }

//    //// TODO: 4/17/2017 send Token to server add start
//    class sendToken extends AsyncTask<String, Integer, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            makePostRequestTokenID(LINK_SEND_TOKEN, getDefaults(TOKEN_ID, MainActivity.this), LINK_PLATOM);
//            return null;
//        }
//    }

//    //// TODO: 4/17/2017  send Token to server add end
//    // // TODO: 4/17/2017  ReadJson Login add start
//    class ReadJson extends AsyncTask<String, Integer, String> {
//        @Override
//        protected String doInBackground(String... params) {
//            Log.d(TAG, params[0]);
//            JSONObject mJsonObject = new JSONObject();
//            try {
//                mJsonObject.put(USER_NAME, UserID);
//                mJsonObject.put(PASS_WORD, UserPass);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            return makePostRequestLogin(params[0], mJsonObject.toString(), UserID, UserPass);
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            try {
//                // // TODO: 8/24/2017  fake to login
////                JSONObject mJsonFake = new JSONObject();
////                mJsonFake.put(RESULT, 2);
////                mJsonFake.put(FULLNAME, "Nguyễn Thi Loan");
////                mJsonFake.put(JOBTITLE, "Tổng Giám Đốc");
////                s = mJsonFake.toString();
//                //// TODO: 8/24/2017 fake to login
//                JSONObject mJsonObject = new JSONObject(s);
//                int result = mJsonObject.getInt(RESULT);
//                String UserName = mJsonObject.getString(FULLNAME);
//                String jobTitle = mJsonObject.getString(JOBTITLE);
//                if (result > 0) {
//                    Intent intent = new Intent(MainActivity.this, OfficalActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                    setDefaults(CHECKLOGIN, CHECKLOGINTRUE, MainActivity.this);
//                    setDefaults(FULLNAME_USER, UserName, MainActivity.this);
//                    setDefaults(JOBTITLE, jobTitle, MainActivity.this);
//                    loginFragment.rememberLocal();
//                    loginFragment.getLocalName();
//                    senDeviceToken();
//                    Intent serviceIntent = new Intent(MainActivity.this, SendDocumentService.class);
//                    serviceIntent.putExtra(TRAINFERDOCUMENT, TRAINFERDOCUMENTOK);
//                    startService(serviceIntent);
//                    startActivity(intent);
//                    closeProgressDialog();
//                } else {
//                    closeProgressDialog();
//                    loginFragment.changeErrorText(getString(R.string.confirm_login_error));
//                }
//            } catch (JSONException e) {
//                Log.d(TAG, e.getMessage());
//                closeProgressDialog();
//                loginFragment.changeErrorText(getString(R.string.confirm_login_error));
//            }
//            super.onPostExecute(s);
//        }
//    }

    // // TODO: 4/17/2017  ReadJson Login add end

//    public String docNoiDung_Tu_URL(String theUrl) {
//        Log.d("VinhCNLog: ", theUrl);
//        StringBuilder content = new StringBuilder();
//        try {
//            // create a url object
//            URL url = new URL(theUrl);
//
//            // create a urlconnection object
//            URLConnection urlConnection = url.openConnection();
//            urlConnection.setConnectTimeout(60000);
//            urlConnection.setReadTimeout(60000);
//            // wrap the urlconnection in a bufferedreader
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
//
//            String line;
//            // read from the urlconnection via the bufferedreader
//            while ((line = bufferedReader.readLine()) != null) {
//                content.append(line + "\n");
//            }
//            bufferedReader.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    closeProgressDialog();
//                    loginFragment.changeErrorText(getString(R.string.confirm_login_error));
//                }
//            });
//
//        }
//        return content.toString();
//    }

    @Override
    public void onBackPressed() {
        exitAppAndFinishAllActivity(this);
    }

    public void createDataBaseRememeberLocalhostSQLite() {

        mSqLite.QueryData(CREATE_TABLE_LOCAL_HOST_SQLITE);
        //        mSqLite.QueryData("CREATE TABLE localhost(id INTEGER PRIMARY KEY IDENTITY(1,1), local VARCHAR)" +
        //                "  ALTER TABLE localhost ADD UNIQUE NONCLUSTERED (local)");
    }
}
