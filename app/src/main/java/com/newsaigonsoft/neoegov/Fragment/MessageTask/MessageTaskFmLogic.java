package com.newsaigonsoft.neoegov.Fragment.MessageTask;

import java.util.List;

import com.newsaigonsoft.neoegov.Subjects.GroupMsgTasksRow;

/**
 * Created by Vinh on 10/23/2017.
 */

public class MessageTaskFmLogic implements MessageTaskFmImp {

    MessageTaskFmView messageTaskFmView;

    public MessageTaskFmLogic(MessageTaskFmView messageTaskFmView) {
        this.messageTaskFmView = messageTaskFmView;
    }

    @Override
    public void setAdapterMsgGroupTask(List<GroupMsgTasksRow> arrDocConnect) {
        if (arrDocConnect.size() == 0) {
            messageTaskFmView.showNoMsgTask();
        } else {
            messageTaskFmView.setListMsgTask(arrDocConnect);
        }
    }
}
