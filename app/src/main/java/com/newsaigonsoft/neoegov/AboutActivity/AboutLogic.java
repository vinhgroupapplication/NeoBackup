package com.newsaigonsoft.neoegov.AboutActivity;

import android.content.Context;

public class AboutLogic implements AboutImp {
    AboutView mAboutView;
    Context context;

    public AboutLogic(AboutView mAboutView, Context context) {
        this.mAboutView = mAboutView;
        this.context = context;
    }
}
