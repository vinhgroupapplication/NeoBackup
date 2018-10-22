package com.newsaigonsoft.neoegov.FeedBackAppActivity;

import android.content.Context;

public class FeedBackAppLogic implements FeedBackAppImp {

    FeedBackAppView mFeedBackAppView;
    Context context;

    public FeedBackAppLogic(FeedBackAppView mFeedBackAppView, Context context) {
        this.mFeedBackAppView = mFeedBackAppView;
        this.context = context;
    }


}
