package com.newsaigonsoft.neoegov.Fragment.ContentTask;

import java.util.List;

import com.newsaigonsoft.neoegov.Subjects.ContentTasksRow;
import com.newsaigonsoft.neoegov.Subjects.ModuleRow;

/**
 * Created by Vinh on 10/23/2017.
 */

public class ContentTaskFmLogic implements ContentTaskFmImp {
    ContentTaskFmView mContentTaskFmView;

    public ContentTaskFmLogic(ContentTaskFmView mContentTaskFmView) {
        this.mContentTaskFmView = mContentTaskFmView;
    }

    @Override
    public void setAdapterContent(List<ContentTasksRow> arrDocConnect, ModuleRow moduleRow) {
        if (arrDocConnect.size() == 0) {
            mContentTaskFmView.showLnNoConTentTask();
        } else {
            mContentTaskFmView.setListContent(arrDocConnect, moduleRow);

        }
    }
}
