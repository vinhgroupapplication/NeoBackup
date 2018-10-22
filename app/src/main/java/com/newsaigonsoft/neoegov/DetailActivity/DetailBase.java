package com.newsaigonsoft.neoegov.DetailActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.widget.TabHost;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.newsaigonsoft.neoegov.Adapter.DialogMenuDetailAdapter;
import com.newsaigonsoft.neoegov.Adapter.FragmentDetailAdapter;
import com.newsaigonsoft.neoegov.Fragment.ContentTask.ContentTaskFragment;
import com.newsaigonsoft.neoegov.Fragment.Detail.DetailFragment;
import com.newsaigonsoft.neoegov.Fragment.FeedBack.FeedBackFragment;
import com.newsaigonsoft.neoegov.Fragment.ForwardTree.ForwardFragment;
import com.newsaigonsoft.neoegov.Fragment.MessageTask.MessageTaskFragment;
import com.newsaigonsoft.neoegov.Fragment.Other.OtherFragment;
import com.newsaigonsoft.neoegov.Fragment.ReportTask.ReportTaskFragment;
import com.newsaigonsoft.neoegov.SQLite.SQLite;
import com.newsaigonsoft.neoegov.Subjects.AttachFile;
import com.newsaigonsoft.neoegov.Subjects.ContextMenuForwardRow;
import com.newsaigonsoft.neoegov.Subjects.InforTrainfer;
import com.newsaigonsoft.neoegov.Subjects.ModuleRow;
import com.newsaigonsoft.neoegov.Subjects.SumManager;

import static com.newsaigonsoft.neoegov.Subjects.KeyManager.INPUT_PERSON_SQLITE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LIST_DOCUMENT_SQLITE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.NOTIFY_SQL;

/**
 * Created by Vinh on 3/5/2018.
 */

public class DetailBase extends SumManager {
    SQLite mSqLiteNotify = new SQLite(this, NOTIFY_SQL, null, 1);
    ArrayList<String> mTabName;
    InforTrainfer mInforTrainfer = new InforTrainfer();
    ArrayList<String> ScreenViewPagerLoad;
    ArrayList<AttachFile> arrAtachFile;
    ArrayList<Integer> mIndicator;
    List<Fragment> FragmentList;
    public DetailLogic mDetailLogic;
    ArrayList<ContextMenuForwardRow> arrContextMenu;
    DetailFragment mDetailFragment;
    ForwardFragment mForwardFragment;
    OtherFragment mOtherFragment;
    FeedBackFragment mFeedBackFragment;
    ReportTaskFragment mReportTaskFragment;
    MessageTaskFragment mMsgTaskFragment;
    ContentTaskFragment mContentTaskFragment;
    Map<Integer, Integer> ContextMap = new HashMap<>();
    TabHost mTabHost;
    Intent intent;
    List<DialogMenuDetailAdapter.ItemMenu> arrMenuDialog;
    ModuleRow moduleRow;

    public ModuleRow getModuleRow() {
        return moduleRow;
    }

    FragmentDetailAdapter ViewPageAdapter;
    SQLite mSqLite = new SQLite(this, INPUT_PERSON_SQLITE, null, 1);
    SQLite mSqLiteListDocument = new SQLite(this, LIST_DOCUMENT_SQLITE, null, 1);

    public InforTrainfer getmInforTrainfer() {
        return mInforTrainfer;
    }

    public ArrayList<AttachFile> getArrAtachFile() {
        return arrAtachFile;
    }

    public void setArrAtachFile(ArrayList<AttachFile> arrAtachFile) {
        this.arrAtachFile = arrAtachFile;
    }

    public SQLite getmSqLite() {
        return mSqLite;
    }

}
