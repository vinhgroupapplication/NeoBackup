package com.newsaigonsoft.neoegov.SplashActivity;

import android.os.Handler;

import static com.newsaigonsoft.neoegov.Subjects.KeyManager.CHECKLOGINTRUE;

/**
 * Created by Vinh on 10/3/2017.
 */

public class SplashLogic implements SplashImp {

    SplashView mSplashView;
    Handler mHandler = new Handler();
    public SplashLogic(SplashView mSplashView) {
        this.mSplashView = mSplashView;
    }

    @Override
    public void CheckLogin(String CheckLogin) {
        if (CheckLogin.equals(CHECKLOGINTRUE)) {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mSplashView.Logined();
                }
            },500);
        } else {
            mSplashView.unLongin();
        }
    }
}
