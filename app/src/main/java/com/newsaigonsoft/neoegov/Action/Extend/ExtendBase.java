package com.newsaigonsoft.neoegov.Action.Extend;

import java.text.SimpleDateFormat;

import com.newsaigonsoft.neoegov.Subjects.SumManager;

/**
 * Created by Vinh on 3/1/2018.
 */

public class ExtendBase extends SumManager {
    String isScreen;
    int isTatistic;
    SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(FORMATDATE);
    ExtendLogic mExtendLogic;
    String resourceCodeId, titleActionBar;
    long jobID;
    int workflowTransitionId;
}
