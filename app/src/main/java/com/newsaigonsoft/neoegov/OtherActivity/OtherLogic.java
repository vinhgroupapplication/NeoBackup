package com.newsaigonsoft.neoegov.OtherActivity;

import android.content.Context;

import com.newsaigonsoft.neoegov.Subjects.SumManager;

/**
 * Created by Vinh on 11/8/2017.
 */

public class OtherLogic extends SumManager implements OtherImp {

    OtherView mOtherView;
    Context context;

    public OtherLogic(OtherView mOtherView, Context context) {
        this.mOtherView = mOtherView;
        this.context = context;
        getInforAccountFromShareReferenced(context);
    }


}
