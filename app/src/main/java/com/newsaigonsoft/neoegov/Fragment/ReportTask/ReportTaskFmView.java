package com.newsaigonsoft.neoegov.Fragment.ReportTask;

import java.util.List;

import com.newsaigonsoft.neoegov.Subjects.ModuleRow;
import com.newsaigonsoft.neoegov.Subjects.ReportTasksRow;

/**
 * Created by Vinh on 10/23/2017.
 */

public interface ReportTaskFmView {
    void showNoReport();

    void setListReport(List<ReportTasksRow> arrDocConnect, ModuleRow moduleRow);
}
