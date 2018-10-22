package com.newsaigonsoft.neoegov.Action.FeedBack;

import java.util.ArrayList;

import com.newsaigonsoft.neoegov.Subjects.AttachFile;
import com.newsaigonsoft.neoegov.Subjects.SumManager;

/**
 * Created by Vinh on 3/1/2018.
 */

public class FeedBackBase  extends SumManager{
    long DocumentID;
    int workflowTransitionId;
    String resourceCodeId;
    String titleActionBar;
    FeedBackLogic mFeedBackLogic;
    String isScreen;
    int isTatistic;
    ArrayList<AttachFile> arrFileAttach;

}
