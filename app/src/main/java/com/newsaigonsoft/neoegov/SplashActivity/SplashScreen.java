package com.newsaigonsoft.neoegov.SplashActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebView;

import com.newsaigonsoft.neoegov.LoginActivity.MainActivity;
import com.newsaigonsoft.neoegov.OfficalActivity.OfficalActivity;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.SumManager;

import static com.newsaigonsoft.neoegov.Subjects.KeyManager.CHECKLOGIN;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.CHECKLOGINFALSE;

public class SplashScreen extends SumManager implements SplashView {
    WebView mWebView;
    //    ACProgressFlower dialog;
//    private ProgressDialog mdialog;
//    private static final String LINK_LOGIN_BEGIN = "/service-mobile-portlet/api/jsonws/mobile/login-for-mobile/jsonParam/%7B'usename':'";
//    private static final String PASS_WORLD = "','password':'";
//    SumManager sumManager = new SumManager();
//    Handler mHandler = new Handler(Looper.myLooper());
//    public SumManager getSumManager() {
//        return sumManager;
//    }]
    Handler mHandler = new Handler();
    SplashLogic mSplashLogic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        mWebView = (WebView) findViewById(R.id.web_view_open);
        /*
         get key checklogin from sharePreference, if it is null, set it = 0, 0 = no login, 1 = yes login
         if it = yes, go to OfficalActivity, if no goto MainActivity
         */
        String checklogin = getDefaults(CHECKLOGIN, this);
        if (checklogin == null) {
            checklogin = CHECKLOGINFALSE;
        }
        getInforAccountFromShareReferenced(this);
        mSplashLogic = new SplashLogic(this);
        mSplashLogic.CheckLogin(checklogin);
    }

    @Override
    public void Logined() {
        Intent intent = new Intent(this, OfficalActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

    @Override
    public void unLongin() {
        this.startActivity(new Intent(this, MainActivity.class));
        this.overridePendingTransition(0, 0);
    }
}
