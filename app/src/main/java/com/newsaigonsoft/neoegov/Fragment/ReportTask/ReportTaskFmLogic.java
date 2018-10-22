package com.newsaigonsoft.neoegov.Fragment.ReportTask;

import java.util.List;

import com.newsaigonsoft.neoegov.Subjects.ModuleRow;
import com.newsaigonsoft.neoegov.Subjects.ReportTasksRow;

/**
 * Created by Vinh on 10/23/2017.
 */

public class ReportTaskFmLogic implements ReportTaskFmImp {

    ReportTaskFmView mReportTaskFmView;

    public ReportTaskFmLogic(ReportTaskFmView mReportTaskFmView) {
        this.mReportTaskFmView = mReportTaskFmView;
    }

    @Override
    public void setReportTask(List<ReportTasksRow> arrDocConnect, ModuleRow moduleRow) {
        if (arrDocConnect.size() == 0) {
            mReportTaskFmView.showNoReport();
        } else {
            mReportTaskFmView.setListReport(arrDocConnect, moduleRow);

        }
    }
}
