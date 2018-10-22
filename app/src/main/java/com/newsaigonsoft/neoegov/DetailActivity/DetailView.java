package com.newsaigonsoft.neoegov.DetailActivity;

import com.newsaigonsoft.neoegov.Adapter.DetailRowAdapter;
import com.newsaigonsoft.neoegov.Adapter.DialogMenuDetailAdapter;
import com.newsaigonsoft.neoegov.Adapter.FileAttachAdapter;
import com.newsaigonsoft.neoegov.Subjects.AttachFile;
import com.newsaigonsoft.neoegov.Subjects.ContentTasksRow;
import com.newsaigonsoft.neoegov.Subjects.ContextMenuForwardRow;
import com.newsaigonsoft.neoegov.Subjects.DetailsRows;
import com.newsaigonsoft.neoegov.Subjects.FeedBackRow;
import com.newsaigonsoft.neoegov.Subjects.GroupMsgTasksRow;
import com.newsaigonsoft.neoegov.Subjects.ModuleRow;
import com.newsaigonsoft.neoegov.Subjects.ReportTasksRow;
import com.unnamed.b.atv.view.AndroidTreeView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vinh on 10/17/2017.
 */

public interface DetailView {
    void initTabHost(JSONObject mJsonObject);

    void ExecutionUnitReuilt(String ExecutionUnit);

    void setAdapterListFileAttach(FileAttachAdapter attachAdapter, ArrayList<AttachFile> arrAttach, String TabName);

    void setVisibilitiesButtonForward(String Resuilt, String TabName);

    void setAdapterListDetais(List<DetailsRows> arrDetails, String TabName);

    void setAdapterMsgGroupTask(List<GroupMsgTasksRow> arrDocConnect,String TabName);

    void setAdapterContentTask(List<ContentTasksRow> arrDocConnect, String TabName, ModuleRow moduleRow);

    void setAdapterReportTask(List<ReportTasksRow> arrDocConnect,String TabName, ModuleRow moduleRow);

    void SetViewList(AndroidTreeView treeView,String TabName);

    void setAdapterFeedBack(List<FeedBackRow> arrFeedBack,String TabName, ModuleRow moduleRow);

    void deleteNotify();

    void CheckShowMenuOther(JSONObject mOther, String other);

    void closeProgress();

    void ToastError(String s);

    boolean isDestroy();

    void getContextMenu(ArrayList<ContextMenuForwardRow> arrContextMenu);

    void startIntent();

    void showError();

    void inseartInputPersonDatabase(String s);

    void DetleteRow(boolean mResuilt);

    void setVisible(int i);

    void getArrMenuDialog(List<DialogMenuDetailAdapter.ItemMenu> arrMenu);

    void startAddTransfer(int tapType);
}
