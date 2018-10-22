package com.newsaigonsoft.neoegov.Fragment.MessageTask;

import java.util.List;

import com.newsaigonsoft.neoegov.Subjects.GroupMsgTasksRow;

/**
 * Created by Vinh on 10/23/2017.
 */

public interface MessageTaskFmView {

    void showNoMsgTask();


    void setListMsgTask(List<GroupMsgTasksRow> arrDocConnect);
}
