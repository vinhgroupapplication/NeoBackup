package com.newsaigonsoft.neoegov.Fragment.StatisticalDocSent;

import android.content.Context;

/**
 * Created by Vinh on 3/22/2018.
 */

public class StatisticalDocSentLogic implements StatisticalDocSentImp {
    Context mContext;
    StatisticalDocSentView mStatisticalDocSentView;

    public StatisticalDocSentLogic(Context mContext, StatisticalDocSentView mStatisticalDocSentView) {
        this.mContext = mContext;
        this.mStatisticalDocSentView = mStatisticalDocSentView;
    }
}
