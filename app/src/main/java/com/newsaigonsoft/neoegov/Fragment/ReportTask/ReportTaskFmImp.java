package com.newsaigonsoft.neoegov.Fragment.ReportTask;

import java.util.List;

import com.newsaigonsoft.neoegov.Subjects.ModuleRow;
import com.newsaigonsoft.neoegov.Subjects.ReportTasksRow;

/**
 * Created by Vinh on 10/23/2017.
 */

public interface ReportTaskFmImp {

    void setReportTask(List<ReportTasksRow> arrDocConnect, ModuleRow moduleRow);
}
