package com.newsaigonsoft.neoegov.Fragment.StatisticalDocInternal;

import android.content.Context;

/**
 * Created by Vinh on 3/22/2018.
 */

public class StatisticalDocInternalLogic implements StatisticalDocInternalImp {

        Context mContext;
        StatisticalDocInternalView mStatisticalDocInternalView;

    public StatisticalDocInternalLogic(Context mContext, StatisticalDocInternalView mStatisticalDocInternalView) {
        this.mContext = mContext;
        this.mStatisticalDocInternalView = mStatisticalDocInternalView;
    }
}
