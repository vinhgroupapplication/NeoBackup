package com.newsaigonsoft.neoegov.Fragment.FeedBack;

import java.util.List;

import com.newsaigonsoft.neoegov.Subjects.FeedBackRow;
import com.newsaigonsoft.neoegov.Subjects.ModuleRow;

/**
 * Created by Vinh on 10/23/2017.
 */

public interface FeedBackFmImp {
    void setFeedBack(List<FeedBackRow> arrFeedBack, ModuleRow moduleRow);
}
