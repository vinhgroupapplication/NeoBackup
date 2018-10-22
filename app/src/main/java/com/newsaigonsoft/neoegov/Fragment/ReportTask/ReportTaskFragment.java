package com.newsaigonsoft.neoegov.Fragment.ReportTask;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.newsaigonsoft.neoegov.Adapter.ReportTaskAdapter;
import com.newsaigonsoft.neoegov.DetailActivity.DetailForwardActivity;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.ModuleRow;
import com.newsaigonsoft.neoegov.Subjects.ReportTasksRow;

/**
 * Created by Vinh on 10/9/2017.
 */

public class ReportTaskFragment extends ReportTaskFmBase implements ReportTaskFmView {
    @BindView(R.id.list_report_task)
    ListView lvReportTasks;
    @BindView(R.id.no_report_task)
    LinearLayout lnNoReportTasks;

    public static ReportTaskFragment newInstance(String TitleActionbar) {
        ReportTaskFragment myFragment = new ReportTaskFragment();
        Bundle args = new Bundle();
        args.putString("Title", TitleActionbar);
        myFragment.setArguments(args);
        return myFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        title = getArguments().getString("Title");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_report_task, container, false);
        mDetailForwardActivity = (DetailForwardActivity) getActivity();
        ButterKnife.bind(this,view);
        mReportTaskFmLogic = new ReportTaskFmLogic(this);
//        mDetailForwardActivity.titleActionbar.setText(title);
//        mDetailForwardActivity.changeListView(5);
        return view;
    }

    public void setAdapterReportTask(List<ReportTasksRow> arrDocConnect, ModuleRow moduleRow) {
        mReportTaskFmLogic.setReportTask(arrDocConnect, moduleRow);
    }

    @Override
    public void showNoReport() {
        lnNoReportTasks.setVisibility(View.VISIBLE);
    }

    @Override
    public void setListReport(List<ReportTasksRow> arrDocConnect, ModuleRow moduleRow) {
        ReportTaskAdapter adapter = new ReportTaskAdapter(mDetailForwardActivity, arrDocConnect, mDetailForwardActivity, moduleRow);
        lvReportTasks.setAdapter(adapter);
    }


//    public void setAdapterReportTask(List<ReportTasksRow> arrDocConnect) {
//        if (arrDocConnect.size() == 0) {
//            lnNoReportTasks.setVisibility(View.VISIBLE);
//        } else {
//            ReportTaskAdapter adapter = new ReportTaskAdapter(mDetailForwardActivity, arrDocConnect, mDetailForwardActivity);
//            lvReportTasks.setAdapter(adapter);
//        }
//    }

}
