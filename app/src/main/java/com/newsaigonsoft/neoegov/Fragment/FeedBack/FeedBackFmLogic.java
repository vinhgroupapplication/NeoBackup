package com.newsaigonsoft.neoegov.Fragment.FeedBack;

import java.util.List;

import com.newsaigonsoft.neoegov.Subjects.FeedBackRow;
import com.newsaigonsoft.neoegov.Subjects.ModuleRow;

/**
 * Created by Vinh on 10/23/2017.
 */

public class FeedBackFmLogic implements FeedBackFmImp {

    FeedBackFmView mFeedBackFmView;

    public FeedBackFmLogic(FeedBackFmView mFeedBackFmView) {
        this.mFeedBackFmView = mFeedBackFmView;
    }


    @Override
    public void setFeedBack(List<FeedBackRow> arrFeedBack, ModuleRow moduleRow) {
        if (arrFeedBack.size() == 0) {
            mFeedBackFmView.showNoFeedBack();
        } else {
            mFeedBackFmView.setListFeedBack(arrFeedBack, moduleRow);
        }
    }
}
