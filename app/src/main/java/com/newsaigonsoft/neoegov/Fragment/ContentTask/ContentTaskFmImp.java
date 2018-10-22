package com.newsaigonsoft.neoegov.Fragment.ContentTask;

import java.util.List;

import com.newsaigonsoft.neoegov.Subjects.ContentTasksRow;
import com.newsaigonsoft.neoegov.Subjects.ModuleRow;

/**
 * Created by Vinh on 10/23/2017.
 */

public interface ContentTaskFmImp{
    void setAdapterContent(List<ContentTasksRow> arrDocConnect, ModuleRow moduleRow);
}
