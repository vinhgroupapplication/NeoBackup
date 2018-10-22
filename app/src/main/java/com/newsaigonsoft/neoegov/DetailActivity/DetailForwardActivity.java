package com.newsaigonsoft.neoegov.DetailActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.newsaigonsoft.neoegov.Action.AddTrainfer.AddTrainferActivity;
import com.newsaigonsoft.neoegov.Action.Cancel.CancelActivity;
import com.newsaigonsoft.neoegov.Action.ChangeHandle.InputChangeHandleActivity;
import com.newsaigonsoft.neoegov.Action.ConfirmConpleted.ConfirmCompletedActivity;
import com.newsaigonsoft.neoegov.Action.Extend.ExtendActivity;
import com.newsaigonsoft.neoegov.Action.FeedBack.FeedBackActivity;
import com.newsaigonsoft.neoegov.Action.ForwardDepartment.InputForwardDepartmentActivity;
import com.newsaigonsoft.neoegov.Action.InputForward.InputForwardActivity;
import com.newsaigonsoft.neoegov.Action.Remind.RemindActivity;
import com.newsaigonsoft.neoegov.Action.Return.ReturnActivity;
import com.newsaigonsoft.neoegov.Adapter.DetailRowAdapter;
import com.newsaigonsoft.neoegov.Adapter.DialogMenuDetailAdapter;
import com.newsaigonsoft.neoegov.Adapter.FileAttachAdapter;
import com.newsaigonsoft.neoegov.Adapter.FragmentDetailAdapter;
import com.newsaigonsoft.neoegov.Fragment.ContentTask.ContentTaskFragment;
import com.newsaigonsoft.neoegov.Fragment.Detail.DetailFragment;
import com.newsaigonsoft.neoegov.Fragment.FeedBack.FeedBackFragment;
import com.newsaigonsoft.neoegov.Fragment.ForwardTree.ForwardFragment;
import com.newsaigonsoft.neoegov.Fragment.MessageTask.MessageTaskFragment;
import com.newsaigonsoft.neoegov.Fragment.Other.OtherFragment;
import com.newsaigonsoft.neoegov.Fragment.ReportTask.ReportTaskFragment;
import com.newsaigonsoft.neoegov.MyInterface.OpenFileItemClick;
import com.newsaigonsoft.neoegov.OfficalActivity.OfficalActivity;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.SQLite.SQLite;
import com.newsaigonsoft.neoegov.Subjects.AttachFile;
import com.newsaigonsoft.neoegov.Subjects.ContentTasksRow;
import com.newsaigonsoft.neoegov.Subjects.ContextMenuForwardRow;
import com.newsaigonsoft.neoegov.Subjects.DetailsRows;
import com.newsaigonsoft.neoegov.Subjects.FeedBackRow;
import com.newsaigonsoft.neoegov.Subjects.GroupMsgTasksRow;
import com.newsaigonsoft.neoegov.Subjects.KeyManager;
import com.newsaigonsoft.neoegov.Subjects.ModuleRow;
import com.newsaigonsoft.neoegov.Subjects.ReportTasksRow;
import com.unnamed.b.atv.view.AndroidTreeView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

import static com.newsaigonsoft.neoegov.R.id.notify_layout;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.CONTENT_TASKS;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DOC_DEMURRAGE_TYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DOC_LOCAL_CONNECTS;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DOC_NOT_PROCESS_TYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DOC_NOT_SEEN_TYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DOC_RECEIPT_CONNECTS;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DOC_SEND_CONNECTS;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.EXECUTION_UNIT;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.HANDLE_WAY_CHANGE_LOG;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.INFO_FEED_BACK;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MESSAGE_TASKS;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.OTHER_INFO;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.PROCESS_DEMURRAGE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.PROCESS_ON_TIME;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.REPORTED_TASKS;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.STATISTIC_TYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TASK_PROCESS;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TASK_PROCESS_NEAR_DEMURRAGE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TASK_REPORTED;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.WORK_FLOW_TRAINSITION_ID;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.BODY_SQLLITE_INPUT_PERSON;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.CONTENTTASK;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.CREATE_TABLE_SQLLITE_INPUT_PERSON;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DEPARTMENT_POSITION;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DETAIL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DIALOG_CENTER;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DOCUMENTID;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DOCUMENT_NUMBER;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.FEEDBACK;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.FORWARD;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.INSERT_OR_UPDATE_INTO_INPUT_PERSON;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.IS_SCREEN;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.KEYURLNA;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LIST_DOCUMENT_DEPARTMENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LIST_DOCUMENT_SQLITE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LOOKUP_COMING;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LOOKUP_INTERNAL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LOOKUP_SCREEN;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LOOKUP_SENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.MSGTASK;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.NOTIFY_SCREEN;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.NOTIFY_SQL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.OTHER;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.OVER_NETWORK_INTENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.PROCESSPERSON;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.PROCESS_POSITION;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.REPORTTASK;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.REPORT_OR_FORWARD_OR_RELEASE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.REQUEST_STATIS_CODE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.RESOURCECODEID;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SHOW_CHOOSE_DUE_DATE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SHOW_CHOOSE_HANDLE_WAY;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.STA_DOC_DEMURRAGE_FULL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.STA_DOC_NOT_PROCESS_FULL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.STA_DOC_PROCESS_ON_TIME_FULL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_14;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_ADD_TRAINFER;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_CANCEL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_CONFIRM_COMPLETED;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_DPM_DELAYS;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_DPM_INDUE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_DPM_ONTIME;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_DPM_OUT_OF_DATE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_EXTEND;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_FEED_BACK;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_FORWARD_DEPARTMENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_FORWARD_PERSON;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_FORWARD_RELEASE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_HANDLE_CHANGE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_REMIND_TASK;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_REPORT_RESUILT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_RETURN;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_SAVE_DRAFTS;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TITLE_ACTION_BAR;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TYPE_HOME_LIST_DOCUMENT;

public class DetailForwardActivity extends DetailBase implements OpenFileItemClick, DetailView {

    @BindView(notify_layout)
    RelativeLayout mNotify_layout;
    public @BindView(R.id.title_actionbar)
    TextView titleActionbar;
    @BindView(R.id.number_notify)
    TextView mNumberOfNotify;
    @BindView(R.id.view_pager_detail)
    ViewPager mViewPager;
    @BindView(R.id.setting_layout)
    RelativeLayout btnSettingActionbar;
    @BindView(R.id.layout_button_forward)
    LinearLayout lnForward;
    @BindView(R.id.forward_button)
    LinearLayout btnForward;
    public long mLastClickTime = System.currentTimeMillis();

    @Optional
    @OnClick({notify_layout, R.id.forward_button})
    public void onClick(View v) {
        long now = System.currentTimeMillis();
        if (now - mLastClickTime < 500) {
            return;
        }
        mLastClickTime = now;
        int id = v.getId();
        switch (id) {
            case notify_layout:
                showNotiffy(mNotify_layout, this, mNumberOfNotify);
                break;
            case R.id.forward_button:
//                v.showContextMenu();
                showDialogMenu(arrMenuDialog);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_forward);
        ButterKnife.bind(this);
        initView();
        // set number notify
//        showNotifiCation(mNumberOfNotify);
        setInforTrainfer();
        mDetailLogic = new DetailLogic(this, this, mInforTrainfer);
        initActiobar(getIntent().getStringExtra(TITLE_ACTION_BAR), true);
        setActionbarTitle();
        setLookupScreen(getIntent().getStringExtra(LOOKUP_SCREEN));
        setStatisticType(getIntent().getIntExtra(STATISTIC_TYPE, 0));
        getInforAccountFromShareReferenced(this);
        mSqLite.QueryData(CREATE_TABLE_SQLLITE_INPUT_PERSON + mInforTrainfer.getDocumentID() + BODY_SQLLITE_INPUT_PERSON);
        mDetailLogic.changeListView(DETAIL);
    }

    void showDialogMenu(final List<DialogMenuDetailAdapter.ItemMenu> arrMenu) {
        dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_menu_process, null);
        ListView lv = (ListView) dialogView.findViewById(R.id.lv_menu_details);
        ImageView imgCancel = (ImageView) dialogView.findViewById(R.id.img_cancel);
        lv.setAdapter(new DialogMenuDetailAdapter(arrMenu, this));
        dialogBuilder.setView(dialogView);
        alertDialog = dialogBuilder.create();
        alertDialog.show();
        imgCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                long DocumentID = getmInforTrainfer().getDocumentID();
                String iScreen = getLookupScreen();
                String TitleName = arrMenu.get(position).getName();
                int workflowTransition = arrMenu.get(position).getId();
                showProgressDialog("", DetailForwardActivity.this, DIALOG_CENTER, false);
                switch (arrMenu.get(position).getType()) {
                    case TAP_RETURN:
                        intent = new Intent(DetailForwardActivity.this, ReturnActivity.class);
                        intent.putExtra(TITLE_ACTION_BAR, TitleName);
                        intent.putExtra(DOCUMENT_NUMBER, getmInforTrainfer().getDocumentNumber());
                        intent.putExtra(DOCUMENTID, DocumentID);
                        intent.putExtra(RESOURCECODEID, String.valueOf(getmInforTrainfer().getResourceCodeId()));
                        intent.putExtra(WORK_FLOW_TRAINSITION_ID, workflowTransition);
                        intent.putExtra(LOOKUP_SCREEN, iScreen);
                        intent.putExtra(STATISTIC_TYPE, getStatisticType());
                        mDetailLogic.ReadPersonReturn(workflowTransition);
                        break;
                    case TAP_SAVE_DRAFTS:
                        mDetailLogic.SaveDraft(workflowTransition);
                        break;
                    case TAP_HANDLE_CHANGE:
                        intent = new Intent(DetailForwardActivity.this, InputChangeHandleActivity.class);
                        intent.putExtra(TITLE_ACTION_BAR, TitleName);
                        intent.putExtra(DOCUMENT_NUMBER, getmInforTrainfer().getDocumentNumber());
                        intent.putExtra(DOCUMENTID, DocumentID);
                        intent.putExtra(RESOURCECODEID,String.valueOf(getmInforTrainfer().getResourceCodeId()));
                        intent.putExtra(WORK_FLOW_TRAINSITION_ID, workflowTransition);
                        intent.putExtra(LOOKUP_SCREEN, iScreen);
                        intent.putExtra(STATISTIC_TYPE, getStatisticType());
                        closeProgress();
                        startActivity(intent);
                        break;
                    case TAP_FEED_BACK:
                        intent = new Intent(DetailForwardActivity.this, FeedBackActivity.class);
                        intent.putExtra(TITLE_ACTION_BAR, TitleName);
                        intent.putExtra(DOCUMENT_NUMBER, getmInforTrainfer().getDocumentNumber());
                        intent.putExtra(DOCUMENTID, DocumentID);
                        intent.putExtra(RESOURCECODEID, String.valueOf(getmInforTrainfer().getResourceCodeId()));
                        intent.putExtra(WORK_FLOW_TRAINSITION_ID, workflowTransition);
                        intent.putExtra(LOOKUP_SCREEN, iScreen);
                        intent.putExtra(STATISTIC_TYPE, getStatisticType());
                        closeProgress();
                        startActivity(intent);
                        break;
                    case TAP_REPORT_RESUILT:
                    case TAP_FORWARD_PERSON:
                    case TAP_FORWARD_RELEASE:
                    case TAP_14:
                        intent = new Intent(DetailForwardActivity.this, InputForwardActivity.class);
                        intent.putExtra(PROCESSPERSON, getmInforTrainfer().getProcessPerson());
                        intent.putExtra(REPORT_OR_FORWARD_OR_RELEASE, arrMenu.get(position).getType());
                        intent.putExtra(WORK_FLOW_TRAINSITION_ID, workflowTransition);
                        intent.putExtra(DOCUMENTID, DocumentID);
                        intent.putExtra(RESOURCECODEID, String.valueOf(getmInforTrainfer().getResourceCodeId()));
                        intent.putExtra(KEYURLNA, OfficalActivity.urlNA);
                        intent.putExtra(DOCUMENT_NUMBER, getmInforTrainfer().getDocumentNumber());
                        intent.putExtra(LOOKUP_SCREEN, iScreen);
                        intent.putExtra(TITLE_ACTION_BAR, TitleName);
                        intent.putExtra(STATISTIC_TYPE, getStatisticType());
                        mDetailLogic.ReadJsonInput(workflowTransition);
                        break;
                    case TAP_FORWARD_DEPARTMENT:
                        intent = new Intent(DetailForwardActivity.this, InputForwardDepartmentActivity.class);
                        intent.putExtra(TITLE_ACTION_BAR, TitleName);
                        intent.putExtra(DOCUMENT_NUMBER, getmInforTrainfer().getDocumentNumber());
                        intent.putExtra(DOCUMENTID, DocumentID);
                        intent.putExtra(RESOURCECODEID, String.valueOf(getmInforTrainfer().getResourceCodeId()));
                        intent.putExtra(WORK_FLOW_TRAINSITION_ID, workflowTransition);
                        intent.putExtra(LOOKUP_SCREEN, iScreen);
                        intent.putExtra(SHOW_CHOOSE_HANDLE_WAY, arrMenu.get(position).isShowChooseHandleWay());
                        intent.putExtra(SHOW_CHOOSE_DUE_DATE, arrMenu.get(position).isShowChooseDueDate());
                        intent.putExtra(STATISTIC_TYPE, getStatisticType());
                        mDetailLogic.ReadListDepartment(workflowTransition);
                        break;
                    case TAP_CONFIRM_COMPLETED:
                        intent = new Intent(DetailForwardActivity.this, ConfirmCompletedActivity.class);
                        intent.putExtra(DEPARTMENT_POSITION, getIntent().getStringExtra(DEPARTMENT_POSITION));
                        intent.putExtra(DOCUMENTID, DocumentID);
                        intent.putExtra(TITLE_ACTION_BAR, TitleName);
                        intent.putExtra(LOOKUP_SCREEN, iScreen);
                        intent.putExtra(STATISTIC_TYPE, getStatisticType());
                        closeProgress();
                        startActivityForResult(intent, REQUEST_STATIS_CODE);
                        break;
                    case TAP_REMIND_TASK:
                        intent = new Intent(DetailForwardActivity.this, RemindActivity.class);
                        intent.putExtra(TITLE_ACTION_BAR, TitleName);
                        intent.putExtra(DOCUMENTID, DocumentID);
                        intent.putExtra(PROCESS_POSITION, getmInforTrainfer().getProcessPosition());
                        intent.putExtra(LOOKUP_SCREEN, iScreen);
                        intent.putExtra(STATISTIC_TYPE, getStatisticType());
                        intent.putExtra(EXECUTION_UNIT, getmArrayExecutionUnit());
                        closeProgress();
                        startActivityForResult(intent, REQUEST_STATIS_CODE);
                        break;
                    case TAP_ADD_TRAINFER:
                        intent = new Intent(DetailForwardActivity.this, AddTrainferActivity.class);
                        intent.putExtra(TITLE_ACTION_BAR, TitleName);
                        intent.putExtra(LOOKUP_SCREEN, iScreen);
                        intent.putExtra(STATISTIC_TYPE, getStatisticType());
                        intent.putExtra(DOCUMENTID, DocumentID);
                        closeProgress();
                        startActivityForResult(intent, REQUEST_STATIS_CODE);
                        break;
                    case TAP_CANCEL:
                        intent = new Intent(DetailForwardActivity.this, CancelActivity.class);
                        intent.putExtra(TITLE_ACTION_BAR, TitleName);
                        intent.putExtra(DOCUMENT_NUMBER, getmInforTrainfer().getDocumentNumber());
                        intent.putExtra(DOCUMENTID, DocumentID);
                        intent.putExtra(RESOURCECODEID, String.valueOf(getmInforTrainfer().getResourceCodeId()));
                        intent.putExtra(WORK_FLOW_TRAINSITION_ID, workflowTransition);
                        intent.putExtra(LOOKUP_SCREEN, iScreen);
                        intent.putExtra(STATISTIC_TYPE, getStatisticType());
                        mDetailLogic.ReadCancelList(workflowTransition);
                        break;
                    case TAP_EXTEND:
                        intent = new Intent(DetailForwardActivity.this, ExtendActivity.class);
                        intent.putExtra(TITLE_ACTION_BAR, TitleName);
                        intent.putExtra(DOCUMENTID, DocumentID);
                        intent.putExtra(RESOURCECODEID, String.valueOf(getmInforTrainfer().getResourceCodeId()));
                        intent.putExtra(LOOKUP_SCREEN, iScreen);
                        intent.putExtra(STATISTIC_TYPE, getStatisticType());
                        closeProgress();
                        startActivity(intent);
                        break;
                    default:
                        break;
                }
                alertDialog.dismiss();
            }

        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        int unmaskedRequestCode = requestCode & 0x0000ffff
        if (requestCode == REQUEST_STATIS_CODE) {
            if (data != null) {
                Intent intent = data;
                setResult(RESULT_OK, intent);
                finish();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    protected void onResume() {
        Log.d(KeyManager.TAG, "resume");
        super.onResume();
    }

    @Override
    protected void onRestart() {
        showNotifiCation(mNumberOfNotify);
        super.onRestart();
    }

    private void setInforTrainfer() {
        mInforTrainfer.setIsScreen(getIntent().getStringExtra(IS_SCREEN));
        mInforTrainfer.setDocumentID(getIntent().getLongExtra(DOCUMENTID, 0));
        long jobid = getIntent().getLongExtra(DOCUMENTID, 0);
        Log.d(KeyManager.TAG, jobid + "");
        mInforTrainfer.setResourceCodeId(getIntent().getIntExtra(RESOURCECODEID, 0));
        mInforTrainfer.setDocumentNumber(getIntent().getStringExtra(DOCUMENT_NUMBER));
        mInforTrainfer.setTypeHomeListDocument(getIntent().getStringExtra(TYPE_HOME_LIST_DOCUMENT));
        mInforTrainfer.setOverNetWork(getIntent().getExtras().getBoolean(OVER_NETWORK_INTENT));
    }

/*========================
    init Fragment
==========================*/

    private void initFragment(String TabName) {
        switch (TabName) {
            case DETAIL:
                int index = ScreenViewPagerLoad.indexOf(DETAIL);
                mDetailFragment = (DetailFragment) ViewPageAdapter.getRegisteredFragment(index);
                break;
            case FORWARD:
                mForwardFragment = (ForwardFragment) ViewPageAdapter.getRegisteredFragment(ScreenViewPagerLoad.indexOf(FORWARD));
                break;
            case FEEDBACK:
                mFeedBackFragment = (FeedBackFragment) ViewPageAdapter.getRegisteredFragment(ScreenViewPagerLoad.indexOf(FEEDBACK));
                break;
            case OTHER:
                mOtherFragment = (OtherFragment) ViewPageAdapter.getRegisteredFragment(ScreenViewPagerLoad.indexOf(OTHER));
                break;
            case MSGTASK:
                mMsgTaskFragment = (MessageTaskFragment) ViewPageAdapter.getRegisteredFragment(ScreenViewPagerLoad.indexOf(MSGTASK));
                break;
            case CONTENTTASK:
                mContentTaskFragment = (ContentTaskFragment) ViewPageAdapter.getRegisteredFragment(ScreenViewPagerLoad.indexOf(CONTENTTASK));
                break;
            case REPORTTASK:
                mReportTaskFragment = (ReportTaskFragment) ViewPageAdapter.getRegisteredFragment(ScreenViewPagerLoad.indexOf(REPORTTASK));
                break;
            default:
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

/*=============================================
    unregister Broadcast when Activity destroy
===============================================*/

    @Override
    protected void onDestroy() {
        closeProgress();
        super.onDestroy();
    }


    private void initView() {
        btnSettingActionbar.setVisibility(View.GONE);
    }


    private void initViewPager(List<Fragment> mFragmentList) {
        ViewPageAdapter = new FragmentDetailAdapter(getSupportFragmentManager(), mInforTrainfer.getIsScreen(), mFragmentList);
        mViewPager.setAdapter(ViewPageAdapter);
//        mDetailLogic.changeListView(0);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (!ScreenViewPagerLoad.get(position).equals(FORWARD)) {
                    mDetailLogic.changeListView(ScreenViewPagerLoad.get(position));
                }
                mTabHost.setCurrentTab(position);
                switch (ScreenViewPagerLoad.get(position)) {
                    case DETAIL:
                        titleActionbar.setText(getIntent().getStringExtra(TITLE_ACTION_BAR));
                        visibleForwardButton(View.VISIBLE);
                        break;
                    case FORWARD:
                        titleActionbar.setText(getIntent().getStringExtra(TITLE_ACTION_BAR));
                        visibleForwardButton(View.VISIBLE);
                        break;
                    case FEEDBACK:
                        titleActionbar.setText(getIntent().getStringExtra(TITLE_ACTION_BAR));
                        visibleForwardButton(View.VISIBLE);
                        break;
                    case OTHER:
                        titleActionbar.setText(getIntent().getStringExtra(TITLE_ACTION_BAR));
                        visibleForwardButton(View.VISIBLE);
                        break;
                    case MSGTASK:
                        titleActionbar.setText(getIntent().getStringExtra(TITLE_ACTION_BAR));
                        visibleForwardButton(View.GONE);
                        break;
                    case CONTENTTASK:
                        titleActionbar.setText(getIntent().getStringExtra(TITLE_ACTION_BAR));
                        visibleForwardButton(View.GONE);
                        break;
                    case REPORTTASK:
                        titleActionbar.setText(getIntent().getStringExtra(TITLE_ACTION_BAR));
                        visibleForwardButton(View.VISIBLE);
                        break;
                }
//                switch (mInforTrainfer.getIsScreen()) {
//                    case TASK_REPORTED:
//                    case TASK_PROCESS:
//                    case TASK_PROCESS_ON_TIME:
//                    case TASK_PROCESS_NEAR_DEMURRAGE:
//                    case TASK_PROCESS_DEMURRAGE:
//                        switch (position) {
//                            case 0:
//                                titleActionbar.setText("CHI TIẾT");
//                                break;
//                            case 1:
//                                titleActionbar.setText("NHẮC NHỞ");
//                                break;
//                            case 2:
//                                titleActionbar.setText("TRAO ĐỔI");
//                                break;
//                            case 3:
//                                titleActionbar.setText("BÁO CÁO");
//                                break;
//                        }
//                        break;
//                    case STA_DOC_PROCESS_ON_TIME_FULL:
//                    case STA_DOC_NOT_PROCESS_FULL:
//                    case STA_DOC_DEMURRAGE_FULL:
//                    case LIST_DOCUMENT_DEPARTMENT:
//                    case LOOKUP_COMING:
//                    case LOOKUP_SENT:
//                    case LOOKUP_INTERNAL:
//                    case DOC_NOT_SEEN_TYPE:
//                    case DOC_NOT_PROCESS_TYPE:
//                    case DOC_DEMURRAGE_TYPE:
//                    case NOTIFY_SCREEN:
//                    default:
//                        switch (position) {
//                            case 0:
//                                titleActionbar.setText("CHI TIẾT");
//                                break;
//                            case 1:
//                                titleActionbar.setText("LUÂN CHUYỂN");
//                                break;
//                            case 2:
//                                titleActionbar.setText("GÓP Ý");
//                                break;
//                            case 3:
//                                titleActionbar.setText("KHÁC");
//                                break;
//                        }
//                        break;
//                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setActionbarTitle() {
        switch (mInforTrainfer.getIsScreen()) {
            case DOC_NOT_SEEN_TYPE:
            case DOC_NOT_PROCESS_TYPE:
            case DOC_DEMURRAGE_TYPE:
                break;
            case STA_DOC_PROCESS_ON_TIME_FULL:
            case STA_DOC_NOT_PROCESS_FULL:
            case STA_DOC_DEMURRAGE_FULL:
            case LIST_DOCUMENT_DEPARTMENT:
            case LOOKUP_COMING:
            case LOOKUP_SENT:
            case LOOKUP_INTERNAL:
                titleActionbar.setText(getIntent().getStringExtra(TITLE_ACTION_BAR));
                break;
//            case LOOKUP_COMING:
//                titleActionbar.setText("CHI TIẾT VĂN BẢN ĐẾN");
//                break;
//            case LOOKUP_SENT:
//                titleActionbar.setText("CHI TIẾT VĂN BẢN ĐI");
//                break;
//            case LOOKUP_INTERNAL:
//                titleActionbar.setText("CHI TIẾT VĂN BẢN NỘI BỘ");
//                break;
            case TASK_REPORTED:
            case TASK_PROCESS:
            case PROCESS_ON_TIME:
            case TASK_PROCESS_NEAR_DEMURRAGE:
            case PROCESS_DEMURRAGE:
                break;
            case NOTIFY_SCREEN:
            default:
                break;
        }
    }


    @Override
    public void initTabHost(JSONObject mJsonObject) {
        int indexTabOther = 0;
        FragmentList = new ArrayList<>();
        ScreenViewPagerLoad = new ArrayList<>();
        mTabHost = (TabHost) findViewById(R.id.tab_host);
        mTabHost.setup();
        mTabName = new ArrayList<>();
        mIndicator = new ArrayList<>();
        mTabName.add(getString(R.string.chi_tiet));
        mIndicator.add(R.drawable.ic_event_black_24dp);
        FragmentList.add(DetailFragment.newInstance("CHI TIẾT"));
        ScreenViewPagerLoad.add(DETAIL);
        if (!mInforTrainfer.getIsScreen().equals(TASK_REPORTED) &&
                !mInforTrainfer.getIsScreen().equals(TASK_PROCESS) &&
                !mInforTrainfer.getIsScreen().equals(PROCESS_ON_TIME) &&
                !mInforTrainfer.getIsScreen().equals(TASK_PROCESS_NEAR_DEMURRAGE) &&
                !mInforTrainfer.getIsScreen().equals(PROCESS_DEMURRAGE) &&
                !mInforTrainfer.getIsScreen().equals(TAP_DPM_DELAYS) &&
                !mInforTrainfer.getIsScreen().equals(TAP_DPM_INDUE) &&
                !mInforTrainfer.getIsScreen().equals(TAP_DPM_ONTIME) &&
                !mInforTrainfer.getIsScreen().equals(TAP_DPM_OUT_OF_DATE)) {
            mTabName.add(getString(R.string.luan_chuyen));
            mIndicator.add(R.drawable.ic_clear_all_black_24dp);
            FragmentList.add(ForwardFragment.newInstance("LUÂN CHUYỂN"));
            ScreenViewPagerLoad.add(FORWARD);
        }
        try {
            JSONObject mOther = mJsonObject.getJSONObject(OTHER_INFO);
            Iterator<String> iter = mOther.keys();
            while (iter.hasNext()) {
                String key = iter.next();
//                Object value = mJsonObject.get(key);
                switch (key) {
                    case INFO_FEED_BACK:
                        mTabName.add(getString(R.string.gop_y));
                        mIndicator.add(R.drawable.ic_feedback_black_24dp);
                        FragmentList.add(FeedBackFragment.newInstance("GÓP Ý"));
                        ScreenViewPagerLoad.add(FEEDBACK);
                        break;
                    default:
                        break;
                }
            }
            if (!mOther.isNull(HANDLE_WAY_CHANGE_LOG) || !mOther.isNull(DOC_RECEIPT_CONNECTS) || !mOther.isNull(DOC_SEND_CONNECTS) || !mOther.isNull(DOC_LOCAL_CONNECTS)) {
                mTabName.add(getString(R.string.khac));
                mIndicator.add(R.drawable.ic_playlist_add_black_24dp);
                FragmentList.add(OtherFragment.newInstance("KHÁC"));
                indexTabOther = mTabName.size();
                ScreenViewPagerLoad.add(OTHER);
            }
            if (!mOther.isNull(MESSAGE_TASKS)) {
                mTabName.add(getString(R.string.nhac_nho));
                mIndicator.add(R.drawable.ic_trending_down_black_24dp);
                FragmentList.add(MessageTaskFragment.newInstance("NHẮC NHỞ"));
                ScreenViewPagerLoad.add(MSGTASK);
            }
            if (!mOther.isNull(CONTENT_TASKS)) {
                mTabName.add(getString(R.string.trao_doi));
                mIndicator.add(R.drawable.ic_question_answer_black_24dp);
                FragmentList.add(ContentTaskFragment.newInstance("TRAO ĐỔI"));
                ScreenViewPagerLoad.add(CONTENTTASK);
            }
            if (!mOther.isNull(REPORTED_TASKS)) {
                mTabName.add(getString(R.string.bao_cao));
                mIndicator.add(R.drawable.ic_speaker_notes_black_24dp);
                FragmentList.add(ReportTaskFragment.newInstance("BÁO CÁO"));
                ScreenViewPagerLoad.add(REPORTTASK);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < mTabName.size(); i++) {
            TabHost.TabSpec mTabSpec;
            mTabSpec = mTabHost.newTabSpec(mTabName.get(i));
//            mTabSpec.setIndicator("", getResources().getDrawable(mIndicator.get(i)));
            mTabSpec.setIndicator(createTabIndicator(mTabName.get(i)));
            mTabSpec.setContent(new FakeContent(getApplicationContext()));
            mTabHost.addTab(mTabSpec);
        }
        mTabHost.setCurrentTab(0);
        View vlBottom = mTabHost.getTabWidget().getChildAt(mTabHost.getCurrentTab()).findViewById(R.id.bottom_line);
        vlBottom.setBackgroundColor(Color.RED);
        TextView tvTab = (TextView) mTabHost.getTabWidget().getChildAt(mTabHost.getCurrentTab()).findViewById(R.id.tv_title);
        tvTab.setTextColor(Color.RED);
        mTabHost.getTabWidget().setStripEnabled(false);
        mTabHost.getTabWidget().setDividerDrawable(null);
        if (indexTabOther != 0) {
            //        TextView tvLastTab = (TextView) mTabHost.getTabWidget().getChildAt(mTabHost.getTabWidget().getChildCount() - 1).findViewById(R.id.tv_title);
            TextView tvLastTab = (TextView) mTabHost.getTabWidget().getChildAt(indexTabOther - 1).findViewById(R.id.tv_title);
            tvLastTab.setBackgroundResource(R.drawable.background_infor_list);
            tvLastTab.setTextColor(Color.WHITE);
            setBGColorDrawable(tvLastTab, "#68aef6");
        }
        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                int selectedPage = mTabHost.getCurrentTab();
                for (int i = 0; i < mTabHost.getTabWidget().getChildCount(); i++) {
                    View vlBottom = mTabHost.getTabWidget().getChildAt(i).findViewById(R.id.bottom_line);
                    TextView tvTab = (TextView) mTabHost.getTabWidget().getChildAt(i).findViewById(R.id.tv_title);
                    if (selectedPage == i) {
                        if (tabId.equals(getString(R.string.khac))) {
                            tvTab.setTextColor(Color.WHITE);
                            setBGColorDrawable(tvTab, "#32D98F");
                        } else {
                            tvTab.setTextColor(Color.RED);
                        }
                        vlBottom.setBackgroundColor(Color.RED);
                    } else {
                        if (tvTab.getText().toString().equals(getString(R.string.khac))) {
                            tvTab.setTextColor(Color.WHITE);
                            setBGColorDrawable(tvTab, "#68aef6");
                        } else {
                            tvTab.setTextColor(Color.parseColor("#777777"));
                        }
                        vlBottom.setBackgroundColor(Color.TRANSPARENT);
                    }
                }
                mViewPager.setCurrentItem(selectedPage);
            }
        });
        initViewPager(FragmentList);
    }

    private View createTabIndicator(String title) {
        View view = LayoutInflater.from(this).inflate(R.layout.custom_tab_detail, null);
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
        tvTitle.setText(title);
        return view;
    }

    @Override
    public void ExecutionUnitReuilt(String ExecutionUnit) {
        setmArrayExecutionUnit(ExecutionUnit);
    }

    @Override
    public void setAdapterListFileAttach(FileAttachAdapter attachAdapter, ArrayList<AttachFile> arrFile, String TabName) {
        initFragment(TabName);
        if (null != mDetailFragment) {
            mDetailFragment.setAdapterListFileAttach(attachAdapter);
        }
        setArrAtachFile(arrFile);
    }


    // TODO: 6/27/2018  
    @Override
    public void setVisibilitiesButtonForward(String Resuilt, String TabName) {
        initFragment(TabName);
        if (null != mDetailFragment) {
            mDetailLogic.setItemMenuContext(Resuilt);
        }
    }

    @Override
    public void setAdapterListDetais(List<DetailsRows> arrDetails, String TabName) {
        initFragment(TabName);
        if (null != mDetailFragment) {
            mDetailFragment.setAdapterListDetais(arrDetails);
        }

    }


    @Override
    public void setAdapterMsgGroupTask(List<GroupMsgTasksRow> arrDocConnect, String TabName) {
        initFragment(TabName);
        mMsgTaskFragment.setAdapterMsgGroupTask(arrDocConnect);
    }

    @Override
    public void setAdapterContentTask(List<ContentTasksRow> arrDocConnect, String TabName, ModuleRow moduleRow) {
        initFragment(TabName);
        mContentTaskFragment.setAdapterContentTask(arrDocConnect, moduleRow);
    }

    @Override
    public void setAdapterReportTask(List<ReportTasksRow> arrDocConnect, String TabName, ModuleRow moduleRow) {
        initFragment(TabName);
        mReportTaskFragment.setAdapterReportTask(arrDocConnect, moduleRow);
    }

    @Override
    public void SetViewList(AndroidTreeView treeView, String TabName) {
        initFragment(TabName);
        mForwardFragment.SetViewList(treeView);
    }

    @Override
    public void setAdapterFeedBack(List<FeedBackRow> arrFeedBack, String TabName, ModuleRow moduleRow) {
        initFragment(TabName);
        mFeedBackFragment.setAdapterFeedBack(arrFeedBack, moduleRow);
    }

    @Override
    public void deleteNotify() {
        mSqLiteNotify.QueryData("DELETE FROM '" + NOTIFY_SQL + "' WHERE objectid = '" + mInforTrainfer.getDocumentID() + "'");
    }

    @Override
    public void CheckShowMenuOther(JSONObject mOther, String TabName) {
        initFragment(TabName);
        mOtherFragment.CheckShowMenu(mOther);
    }

    @Override
    public void closeProgress() {
        closeProgressDialog();
    }

    @Override
    public void ToastError(String s) {
        ShowErrorToast(this);
        Log.d(KeyManager.TAG, s);
    }

    @Override
    public boolean isDestroy() {
        return DetailForwardActivity.this.isDestroyed();
    }

    @Override
    public void getContextMenu(ArrayList<ContextMenuForwardRow> arrContextMenus) {
        arrContextMenu = arrContextMenus;
    }

//    @Override
//    public void VisibleButtonFoward() {
//        lnForward.setVisibility(View.VISIBLE);
//    }
//
//    @Override
//    public void GoneButtonFoward() {
//        lnForward.setVisibility(View.GONE);
//    }


    @Override
    public void startIntent() {
        closeProgress();
        startActivity(intent);
    }

    @Override
    public void showError() {
        closeProgress();
        ShowErrorToast(this);
    }

    @Override
    public void inseartInputPersonDatabase(String s) {
        String input = INSERT_OR_UPDATE_INTO_INPUT_PERSON + getmInforTrainfer().getDocumentID() + "(id , jsonObject)" + " VALUES('" + getmInforTrainfer().getDocumentID() + "', '" + s + "')";
        Log.d("doc", input);
        getmSqLite().QueryData(input);
    }

    @Override
    public void DetleteRow(boolean Resuilt) {
        if (Resuilt) {
            mSqLiteListDocument.QueryData("DELETE FROM '" + "M" + getOnlyNumerics(OfficalActivity.urlNA) + "' WHERE jobId = '" + getmInforTrainfer().getDocumentID() + "'");
        }
    }

    @Override
    public void setVisible(int i) {
        lnForward.setVisibility(i);
    }

    @Override
    public void getArrMenuDialog(List<DialogMenuDetailAdapter.ItemMenu> arrMenu) {
        arrMenuDialog = arrMenu;
    }

    @Override
    public void startAddTransfer(int tapType) {
        for (int i = 0; i < arrMenuDialog.size(); i++) {
            if (arrMenuDialog.get(i).getType() == tapType) {
                long DocumentID = getmInforTrainfer().getDocumentID();
                String iScreen = getLookupScreen();
                String TitleName = arrMenuDialog.get(i).getName();
                int workflowTransition = arrMenuDialog.get(i).getId();
                switch (arrMenuDialog.get(i).getType()) {
                    case TAP_ADD_TRAINFER:
                        intent = new Intent(DetailForwardActivity.this, AddTrainferActivity.class);
                        intent.putExtra(TITLE_ACTION_BAR, TitleName);
                        intent.putExtra(LOOKUP_SCREEN, iScreen);
                        intent.putExtra(STATISTIC_TYPE, getStatisticType());
                        intent.putExtra(DOCUMENTID, DocumentID);
                        closeProgress();
                        startActivityForResult(intent, REQUEST_STATIS_CODE);
                        break;
                    case TAP_REMIND_TASK:
                        intent = new Intent(DetailForwardActivity.this, RemindActivity.class);
                        intent.putExtra(TITLE_ACTION_BAR, TitleName);
                        intent.putExtra(DOCUMENTID, DocumentID);
                        intent.putExtra(PROCESS_POSITION, getmInforTrainfer().getProcessPosition());
                        intent.putExtra(LOOKUP_SCREEN, iScreen);
                        intent.putExtra(STATISTIC_TYPE, getStatisticType());
                        intent.putExtra(EXECUTION_UNIT, getmArrayExecutionUnit());
                        closeProgress();
                        startActivityForResult(intent, REQUEST_STATIS_CODE);
                        break;
                }
            }
        }
    }


    public void showMoreDetailClick() {
        mDetailFragment.showMoreDetail();
    }

    public void visibleForwardButton(int visible) {
        btnForward.setVisibility(visible);
    }

    public class FakeContent implements TabHost.TabContentFactory {
        Context context;

        public FakeContent(Context context) {
            this.context = context;
        }

        @Override
        public View createTabContent(String tag) {
            View view = new View(context);
            view.setMinimumHeight(0);
            view.setMinimumWidth(0);
            return view;
        }
    }

    // TODO: 6/29/2018  create context menu comment out start
//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        super.onCreateContextMenu(menu, v, menuInfo);
//        menu.setHeaderTitle(getString(R.string.chon_chuc_nang).toUpperCase());
//        for (int i = 0; i < arrContextMenu.size(); i++) {
////            menu_main.add(0, arrContextMenu.get(i).getmType(), 0, arrContextMenu.get(i).getName());
////            ContextMap.put(arrContextMenu.get(i).getmType(), arrContextMenu.get(i).getID());
//            menu.add(0, i, 0, arrContextMenu.get(i).getName());
//            ContextMap.put(arrContextMenu.get(i).getmType(), arrContextMenu.get(i).getID());
//        }
//    }
//
//    @Override
//    public boolean onContextItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        long DocumentID = getmInforTrainfer().getDocumentID();
//        String iScreen = getLookupScreen();
//        String TitleName = arrContextMenu.get(id).getName();
//        int workflowTransition = arrContextMenu.get(id).getID();
//        showProgressDialog("", this, DIALOG_CENTER, false);
//        switch (arrContextMenu.get(id).getmType()) {
//            case TAP_RETURN:
////                workflowTransition = ContextMap.get(TAP_RETURN);
////                TitleName = (String) item.getTitle();
////                new ReadJsonPerSonReturn().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mDetailForwardActivity.getLink() + URL_CENTER_6_1);
//                intent = new Intent(this, ReturnActivity.class);
//                intent.putExtra(TITLE_ACTION_BAR, TitleName);
//                intent.putExtra(DOCUMENT_NUMBER, getmInforTrainfer().getDocumentNumber());
//                intent.putExtra(DOCUMENTID, DocumentID);
//                intent.putExtra(RESOURCECODEID, getmInforTrainfer().getResourceCodeId());
//                intent.putExtra(WORK_FLOW_TRAINSITION_ID, workflowTransition);
//                intent.putExtra(LOOKUP_SCREEN, iScreen);
//                intent.putExtra(STATISTIC_TYPE, getStatisticType());
//                mDetailLogic.ReadPersonReturn(workflowTransition);
//                break;
//            case TAP_SAVE_DRAFTS:
////                new SaveToDrafts().execute(mDetailForwardActivity.getLink() + URL_CENTER_6_1);
////                mDetailFmLogic.SaveDraft(ContextMap);
//                mDetailLogic.SaveDraft(workflowTransition);
//                break;
//            case TAP_HANDLE_CHANGE:
////                workflowTransition = ContextMap.get(TAP_HANDLE_CHANGE);
////                TitleName = (String) item.getTitle();
////                DocumentID = mDetailForwardActivity.getmInforTrainfer().getDocumentID();
//                intent = new Intent(this, InputChangeHandleActivity.class);
//                intent.putExtra(TITLE_ACTION_BAR, TitleName);
//                intent.putExtra(DOCUMENT_NUMBER, getmInforTrainfer().getDocumentNumber());
//                intent.putExtra(DOCUMENTID, DocumentID);
//                intent.putExtra(RESOURCECODEID, getmInforTrainfer().getResourceCodeId());
//                intent.putExtra(WORK_FLOW_TRAINSITION_ID, workflowTransition);
//                intent.putExtra(LOOKUP_SCREEN, iScreen);
//                intent.putExtra(STATISTIC_TYPE, getStatisticType());
//                closeProgress();
//                startActivity(intent);
//                break;
//            case TAP_FEED_BACK:
////                workflowTransition = ContextMap.get(TAP_FEED_BACK);
////                TitleName = (String) item.getTitle();
////                DocumentID = mDetailForwardActivity.getmInforTrainfer().getDocumentID();
//                intent = new Intent(this, FeedBackAppActivity.class);
//                intent.putExtra(TITLE_ACTION_BAR, TitleName);
//                intent.putExtra(DOCUMENT_NUMBER, getmInforTrainfer().getDocumentNumber());
//                intent.putExtra(DOCUMENTID, DocumentID);
//                intent.putExtra(RESOURCECODEID, getmInforTrainfer().getResourceCodeId());
//                intent.putExtra(WORK_FLOW_TRAINSITION_ID, workflowTransition);
//                intent.putExtra(LOOKUP_SCREEN, iScreen);
//                intent.putExtra(STATISTIC_TYPE, getStatisticType());
//                closeProgress();
//                startActivity(intent);
//                break;
//            case TAP_REPORT_RESUILT:
//            case TAP_FORWARD_PERSON:
//            case TAP_FORWARD_RELEASE:
//            case TAP_14:
////                TitleName = (String) item.getTitle();
////                String processor = mDetailForwardActivity.getmInforTrainfer().getProcessPerson();
////                intent.putExtra(TRAINFERID, mDetailForwardActivity.getmInforTrainfer().getTrainferID());
////                intent.putExtra(TRAINFERID_REPORT, mDetailForwardActivity.getmInforTrainfer().getTrainferReport());
////                intent.putExtra(TRAINFERID_RELEASE, mDetailForwardActivity.getmInforTrainfer().getTrainferReleaseID());
////                intent.putExtra(TRAINFERID_14, mDetailForwardActivity.getmInforTrainfer().getTrainfer_14_ID());
//                intent = new Intent(this, InputForwardActivity.class);
//                intent.putExtra(PROCESSPERSON, getmInforTrainfer().getProcessPerson());
//                intent.putExtra(REPORT_OR_FORWARD_OR_RELEASE, arrContextMenu.get(id).getmType());
//                intent.putExtra(WORK_FLOW_TRAINSITION_ID, workflowTransition);
//                intent.putExtra(DOCUMENTID, DocumentID);
//                intent.putExtra(RESOURCECODEID, getmInforTrainfer().getResourceCodeId());
//                intent.putExtra(KEYURLNA, OfficalActivity.urlNA);
//                intent.putExtra(DOCUMENT_NUMBER, getmInforTrainfer().getDocumentNumber());
//                intent.putExtra(LOOKUP_SCREEN, iScreen);
//                intent.putExtra(TITLE_ACTION_BAR, TitleName);
//                intent.putExtra(STATISTIC_TYPE, getStatisticType());
//                mDetailLogic.ReadJsonInput(workflowTransition);
//                break;
//            case TAP_FORWARD_DEPARTMENT:
////                workflowTransition = ContextMap.get(TAP_FORWARD_DEPARTMENT);
////                TitleName = (String) item.getTitle();
////                DocumentID = mDetailForwardActivity.getmInforTrainfer().getDocumentID();
//                intent = new Intent(this, InputForwardDepartmentActivity.class);
//                intent.putExtra(TITLE_ACTION_BAR, TitleName);
//                intent.putExtra(DOCUMENT_NUMBER, getmInforTrainfer().getDocumentNumber());
//                intent.putExtra(DOCUMENTID, DocumentID);
//                intent.putExtra(RESOURCECODEID, getmInforTrainfer().getResourceCodeId());
//                intent.putExtra(WORK_FLOW_TRAINSITION_ID, workflowTransition);
//                intent.putExtra(LOOKUP_SCREEN, iScreen);
//                intent.putExtra(STATISTIC_TYPE, getStatisticType());
//                mDetailLogic.ReadListDepartment(workflowTransition);
////                new ReadJsonDepartment().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mDetailForwardActivity.getLink() + URL_CENTER_6_1);
//                break;
//            case TAP_CONFIRM_COMPLETED:
////                DocumentID = mDetailForwardActivity.getmInforTrainfer().getDocumentID();
////                TitleName = (String) item.getTitle();
//                intent = new Intent(this, ConfirmCompletedActivity.class);
//                intent.putExtra(DEPARTMENT_POSITION, getIntent().getStringExtra(DEPARTMENT_POSITION));
//                intent.putExtra(DOCUMENTID, DocumentID);
//                intent.putExtra(TITLE_ACTION_BAR, TitleName);
//                intent.putExtra(LOOKUP_SCREEN, iScreen);
//                intent.putExtra(STATISTIC_TYPE, getStatisticType());
//                closeProgress();
//                startActivityForResult(intent, REQUEST_STATIS_CODE);
//                break;
//            case TAP_REMIND_TASK:
////                TitleName = (String) item.getTitle();
////                DocumentID = mDetailForwardActivity.getmInforTrainfer().getDocumentID();
//                intent = new Intent(this, RemindActivity.class);
//                intent.putExtra(TITLE_ACTION_BAR, TitleName);
//                intent.putExtra(DOCUMENTID, DocumentID);
//                intent.putExtra(PROCESS_POSITION, getmInforTrainfer().getProcessPosition());
//                intent.putExtra(LOOKUP_SCREEN, iScreen);
//                intent.putExtra(STATISTIC_TYPE, getStatisticType());
//                intent.putExtra(EXECUTION_UNIT, getmArrayExecutionUnit());
//                closeProgress();
//                startActivityForResult(intent, REQUEST_STATIS_CODE);
//                break;
//            case TAP_ADD_TRAINFER:
////                DocumentID = mDetailForwardActivity.getmInforTrainfer().getDocumentID();
////                TitleName = (String) item.getTitle();
//                intent = new Intent(this, AddTrainferActivity.class);
//                intent.putExtra(TITLE_ACTION_BAR, TitleName);
//                intent.putExtra(LOOKUP_SCREEN, iScreen);
//                intent.putExtra(STATISTIC_TYPE, getStatisticType());
//                intent.putExtra(DOCUMENTID, DocumentID);
//                closeProgress();
//                startActivityForResult(intent, REQUEST_STATIS_CODE);
//                break;
//            case TAP_CANCEL:
////                workflowTransition = ContextMap.get(TAP_CANCEL);
////                TitleName = (String) item.getTitle();
////                DocumentID = mDetailForwardActivity.getmInforTrainfer().getDocumentID();
//                intent = new Intent(this, CancelActivity.class);
//                intent.putExtra(TITLE_ACTION_BAR, TitleName);
//                intent.putExtra(DOCUMENT_NUMBER, getmInforTrainfer().getDocumentNumber());
//                intent.putExtra(DOCUMENTID, DocumentID);
//                intent.putExtra(RESOURCECODEID, getmInforTrainfer().getResourceCodeId());
//                intent.putExtra(WORK_FLOW_TRAINSITION_ID, workflowTransition);
//                intent.putExtra(LOOKUP_SCREEN, iScreen);
//                intent.putExtra(STATISTIC_TYPE, getStatisticType());
////                new ReadJsonCancelList().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mDetailForwardActivity.getLink() + URL_CENTER_6_1);
//                mDetailLogic.ReadCancelList(workflowTransition);
//                break;
//            case TAP_EXTEND:
//                intent = new Intent(this, ExtendActivity.class);
////                TitleName = (String) item.getTitle();
////                DocumentID = mDetailForwardActivity.getmInforTrainfer().getDocumentID();
//                intent.putExtra(TITLE_ACTION_BAR, TitleName);
//                intent.putExtra(DOCUMENTID, DocumentID);
//                intent.putExtra(RESOURCECODEID, getmInforTrainfer().getResourceCodeId());
//                intent.putExtra(LOOKUP_SCREEN, iScreen);
//                intent.putExtra(STATISTIC_TYPE, getStatisticType());
//                closeProgress();
//                startActivity(intent);
//                break;
//            default:
//                break;
//        }
//
//        return super.onContextItemSelected(item);
//    }

    // TODO: 6/29/2018  create context menu comment out start

    //    /*===========================
//    update notify number by BroadcastReceiver
//=============================*/
//
//    BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            String action = intent.getStringExtra(RELOADSLIDER);
//            Log.d(TAG, "BroadcastReceiver Enable");
//            switch (action) {
//                case NOTIFICATION_UP_DATE:
//                    showNotifiCation(mNumberOfNotify);
//                    break;
//                default:
//                    break;
//            }
//        }
//    };
//    private void initBroadcast() {
//        IntentFilter filter = new IntentFilter(BROADCASTLISTENNER);
//        this.registerReceiver(mBroadcastReceiver, filter);
//    }
///*=====================================
//    ChangeListView method will read json detail if it have connection else it read from file saved
//=======================================*/
//
//    public void changeListView(int CheckUI) {
//        showProgressDialog(nULL_STRING, this, DIALOG_CENTER, true);
//        String x = getLink();
//        mCheckUI = CheckUI;
//        Log.d(TAG, x);
//        if (isNetworkAvailable(DetailForwardActivity.this)) {
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    new ReadJsonDetail().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, getLink() + URL_CENTER_6_1);
//                    new ReadJsonHandleProcess().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, getLink() + URL_CENTER_6_1);
//                }
//            });
//        } else {
//            upDateViewDetail(readFromFile(DetailForwardActivity.this, String.valueOf(mInforTrainfer.getDocumentID())));
//        }
//
//    }
//
//    class ReadJsonDepartment extends AsyncTask<String, Integer, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            JSONObject mJsonObject = new JSONObject();
//            try {
//                mJsonObject.put(MODULE, PROCESSING_WORKING);
//                mJsonObject.put(NEOTYPE, TYPE_DEPARTMENT_LIST_RECEIVE);
//                JSONObject mData = new JSONObject();
//                mData.put(JOB_ID, mInforTrainfer.getDocumentID());
//                mData.put(WORK_FLOW_TRAINSITION_ID, mInforTrainfer.getForwardDepartmentID());
//                mJsonObject.put(DATA, mData.toString());
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            return makePostRequest(params[0], mJsonObject.toString(), getUserid(), getPass());
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            if (!s.equals("Error!") && !s.equals(nULL_STRING)) {
//                writeToFile(s, DetailForwardActivity.this, "department" + mInforTrainfer.getDocumentID());
//            }
//            super.onPostExecute(s);
//        }
//    }
//
//
//    class ReadJsonHandleProcess extends AsyncTask<String, Integer, String> {
//        @Override
//        protected String doInBackground(String... params) {
//            JSONObject mJsonObject = new JSONObject();
//            try {
//                mJsonObject.put(MODULE, LOOKUP_DOCUMENT);
//                mJsonObject.put(NEOTYPE, PROCESS_HANDLE);
//                JSONObject mData = new JSONObject();
//                mData.put(JOB_ID, mInforTrainfer.getDocumentID());
//                mJsonObject.put(DATA, mData);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
////            return makePostRequest(params[0], mJsonObject.toString(), getUserid(), getPass());
//            return eventPostRequest(getModuleInfor(LOOKUP_DOCUMENT), mJsonObject.toString(), getUserid(), getPass());
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            if (!s.equals("Error!") && !s.equals(nULL_STRING)) {
//                writeToFile(s, DetailForwardActivity.this, "handle" + mInforTrainfer.getDocumentID());
//            }
//            super.onPostExecute(s);
//        }
//    }
//
//
//
///*=============================
//    Analysis json detail if mCheckUI = true (inside DetailFragment)
//    it read all json but not read json fordward tree else (inside FordWardFragment)
//    it read json tree
//===============================*/
//
//    private void upDateViewDetail(String s) {
//        initFragment();
//        Log.d(TAG, s);
//        arrAtachFile = new ArrayList<AttachFile>();
//        arrDetails = new ArrayList<DetailsRows>();
//        arrDetailLimit_1 = new ArrayList<DetailsRows>();
//        arrDetailLimit_2 = new ArrayList<DetailsRows>();
//        arrFeedback = new ArrayList<FeedBackRow>();
//        arrHanleChange = new ArrayList<HandleChangeRow>();
//        arrContentTasks = new ArrayList<ContentTasksRow>();
//        arrReportedTasks = new ArrayList<ReportTasksRow>();
//        arrMsgGroupTask = new ArrayList<GroupMsgTasksRow>();
//        try {
//            JSONObject mJsonObjectFullData = new JSONObject(s);
//            JSONObject mJsonObject = mJsonObjectFullData.getJSONObject(DATA);
//            // check tab other show
//            JSONObject mOther = mJsonObject.getJSONObject(OTHER_INFO);
//            switch (mInforTrainfer.getIsScreen()) {
//                case TASK_REPORTED:
//                case TASK_PROCESS:
//                case TASK_PROCESS_ON_TIME:
//                case TASK_PROCESS_NEAR_DEMURRAGE:
//                case TASK_PROCESS_DEMURRAGE:
//                    switch (mCheckUI) {
//                        case 0:
//                            if (!isRegisterTabhost) {
//                                initTabHost(mOther);
//                                isRegisterTabhost = true;
//                            }
//                            // check tab other show
//                            Iterator<String> iter = mJsonObject.keys();
//                            while (iter.hasNext()) {
//                                String key = iter.next();
//                                Object value = mJsonObject.get(key);
//                                if (!key.equals(ATTACH_FILE) && !key.equals(TREEJOBCYCLE) && !key.equals(WORKFLOWTRANSITION) && !key.equals(OTHER_INFO) && !key.equals(REMOVE_JOB) && !key.equals(EXECUTION_UNIT)) {
//                                    arrDetails.add(new DetailsRows(key + ":", value + nULL_STRING));
//                                    if (key.contains("M")) {
//                                        mInforTrainfer.setProcessPerson(value.toString());
//                                    }
//                                    if (key.contains("L")) {
//                                        mInforTrainfer.setProcessPosition(value.toString());
//                                    }
//                                    if (key.contains("C")) {
//                                        mInforTrainfer.setDepartmentPosition(value.toString());
//                                    }
//                                } else {
//                                    if (key.equals(EXECUTION_UNIT)) {
//                                        mArrayExecutionUnit = new JSONArray(value.toString());
//                                        Log.d(TAG, mArrayExecutionUnit.toString());
//                                    }
//                                }
//
//                            }
//                            JSONArray mArrayFileAttach = mJsonObject.getJSONArray(ATTACH_FILE);
//                            for (int i = 0; i < mArrayFileAttach.length(); i++) {
//                                JSONObject mObjectFileAttach = mArrayFileAttach.getJSONObject(i);
//                                String fileName = mObjectFileAttach.getString(NAME);
//                                String fileType = mObjectFileAttach.getString(TYPE);
//                                String fileURL = mObjectFileAttach.getString(ATTACK_URL);
//                                String Base64Code = mObjectFileAttach.getString(BASE_64);
//                                arrAtachFile.add(new AttachFile(fileName, fileType, fileURL, Base64Code));
//                            }
//                            FileAttachAdapter attachAdapter = new FileAttachAdapter(DetailForwardActivity.this, arrAtachFile);
//                            mDetailFragment.setAdapterListFileAttach(attachAdapter);
//                            if (!mJsonObject.isNull(WORKFLOWTRANSITION)) {
//                                JSONArray mArrayTransferLines = mJsonObject.getJSONArray(WORKFLOWTRANSITION);
//                                for (int i = 0; i < mArrayTransferLines.length(); i++) {
//                                    if (mArrayTransferLines != null) {
//                                        JSONObject mObjectTrainferLine = mArrayTransferLines.getJSONObject(i);
//                                        switch (mObjectTrainferLine.getInt(TYPE)) {
//                                            case 4:
//                                            case 6:
//                                                mInforTrainfer.setTrainferID(mObjectTrainferLine.getString(ID));
//                                                break;
//                                            case 7:
//                                                mInforTrainfer.setForwardDepartmentID(mObjectTrainferLine.getLong(ID));
////                                    new ReadJsonDepartment().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, getLink() + URL_CENTER_6_1);
//                                                break;
//                                            case 11:
//                                                mInforTrainfer.setWorkFlowStationCancel(mObjectTrainferLine.getLong(ID));
////                                    new ReadJsonCancelList().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, getLink() + URL_CENTER_6_1);
//                                                break;
//                                            case 1:
//                                                mInforTrainfer.setWorkFlowStationReturn(mObjectTrainferLine.getLong(ID));
////                                    new ReadJsonPerSonReturn().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, getLink() + URL_CENTER_6_1);
//                                                break;
//                                        }
//                                    }
//                                }
//                                mDetailFragment.setVisibilitiesButtonForward(mArrayTransferLines.toString());
//                            } else {
//                                mDetailFragment.setVisibilitiesButtonForward("[]");
//                            }
////                arrDetailLimit_1 = arrDetails.subList(0, 4);
////                arrDetailLimit_2 = arrDetails.subList(4, arrDetails.size());
//////                detailRowAdapter_1 = new DetailRowAdapter(DetailForwardActivity.this, arrDetailLimit_1);
//////                detailRowAdapter_2 = new DetailRowAdapter(DetailForwardActivity.this, arrDetailLimit_2);
//////                mDetailFragment.setListDetail2nd(detailRowAdapter_2);
//////                mDetailFragment.setAdapterListDetais(detailRowAdapter_1);
//                            detailAdapter = new DetailRowAdapter(DetailForwardActivity.this, arrDetails);
//                            mDetailFragment.setAdapterListDetais(detailAdapter);
//                            mDetailFragment.showButtonMoreDetails(true);
////                runOnUiThread(new Runnable() {
////                    @Override
////                    public void run() {
////                        new ReadJsonInputPerson().execute(getLink() + URL_CENTER_6_1);
////                    }
////                });
//                            if (mInforTrainfer.getIsScreen().equals(NOTIFY_SCREEN)) {
//                                mSqLiteNotify.QueryData("DELETE FROM '" + NOTIFY_SQL + "' WHERE objectid = '" + mInforTrainfer.getDocumentID() + "'");
//                            }
//                            break;
//                        case 1:
//                            // get message tasks
//                            if (!mOther.isNull(MESSAGE_TASKS)) {
//                                JSONArray mJsonMessageTasks = mOther.getJSONArray(MESSAGE_TASKS);
//                                for (int i = 0; i < mJsonMessageTasks.length(); i++) {
//                                    JSONObject mObject = mJsonMessageTasks.getJSONObject(i);
//                                    JSONArray mArrayExecutionUnits = mJsonObject.getJSONArray(EXECUTION_UNITS);
//                                    if (!mArrayExecutionUnits.toString().equals("[]")) {
//                                        arrMsgGroupTask.add(new GroupMsgTasksRow("Nhắc nhở lần " + (i + 1), mJsonObject));
//                                        Log.d("JsonGroupTasks", mJsonObject.toString());
//                                    }
//                                }
//                            }
//                            mMsgTaskFragment.setAdapterMsgGroupTask(arrMsgGroupTask);
//                            break;
//                        case 2:
//                            // get content Tasks
//                            if (!mOther.isNull(CONTENT_TASKS)) {
//                                JSONArray mJsonMessageTasks = mOther.getJSONArray(CONTENT_TASKS);
//                                for (int i = 0; i < mJsonMessageTasks.length(); i++) {
//                                    JSONObject mObject = mJsonMessageTasks.getJSONObject(i);
//                                    String staff = mObject.getString(STAFF);
//                                    String exchangeDate = mObject.getString(EXCHANGE_DATE);
//                                    int classify = mObject.getInt(CLASSIFY);
//                                    String content = mObject.getString(SCHEDULE_CONTENT);
//                                    JSONArray mArrayAttachFile = mObject.getJSONArray(ATTACH_FILE);
//                                    arrContentTasks.add(new ContentTasksRow(staff, exchangeDate, classify, content, mArrayAttachFile));
//                                }
//                            }
//                            mContentTaskFragment.setAdapterContentTask(arrContentTasks);
//                            break;
//                        case 3:
//                            if (!mOther.isNull(REPORTED_TASKS)) {
//                                JSONArray mJsonReportTasks = mOther.getJSONArray(REPORTED_TASKS);
//                                if (!mJsonReportTasks.toString().equals("[]")) {
//                                    for (int i = 0; i < mJsonReportTasks.length(); i++) {
//                                        JSONObject mObject = mJsonReportTasks.getJSONObject(i);
//                                        String organizationName = mObject.getString(ORGANIZATION_NAME);
//                                        String docNumberReport = mObject.getString(DOC_NUMBER_REPORT);
//                                        String dateReport = mObject.getString(DATE_REPORT);
//                                        String contentReport = mObject.getString(CONTENT_REPORT);
//                                        JSONArray mArrayAttachFile = mObject.getJSONArray(ATTACH_FILE);
//                                        arrReportedTasks.add(new ReportTasksRow(organizationName, docNumberReport, dateReport, contentReport, mArrayAttachFile));
//                                    }
//                                }
//                            }
//                            mReportTaskFragment.setAdapterReportTask(arrReportedTasks);
//                            break;
//                        default:
//                            break;
//                    }
//                    break;
//                case STA_DOC_PROCESS_ON_TIME_FULL:
//                case STA_DOC_NOT_PROCESS_FULL:
//                case STA_DOC_DEMURRAGE_FULL:
//                case LIST_DOCUMENT_DEPARTMENT:
//                case LOOKUP_COMING:
//                case LOOKUP_SENT:
//                case LOOKUP_INTERNAL:
//                case DOC_NOT_SEEN_TYPE:
//                case DOC_NOT_PROCESS_TYPE:
//                case DOC_DEMURRAGE_TYPE:
//                case NOTIFY_SCREEN:
//                default:
//                    switch (mCheckUI) {
//                        case 0:
//                            if (!isRegisterTabhost) {
//                                initTabHost(mOther);
//                                isRegisterTabhost = true;
//                            }
//                            // check tab other show
//                            Iterator<String> iter = mJsonObject.keys();
//                            while (iter.hasNext()) {
//                                String key = iter.next();
//                                Object value = mJsonObject.get(key);
//                                if (!key.equals(ATTACH_FILE) && !key.equals(TREEJOBCYCLE) && !key.equals(WORKFLOWTRANSITION) && !key.equals(OTHER_INFO) && !key.equals(REMOVE_JOB) && !key.equals(EXECUTION_UNIT)) {
//                                    arrDetails.add(new DetailsRows(key + ":", value + nULL_STRING));
//                                    if (key.contains("M")) {
//                                        mInforTrainfer.setProcessPerson(value.toString());
//                                    }
//                                    if (key.contains("L")) {
//                                        mInforTrainfer.setProcessPosition(value.toString());
//                                    }
//                                    if (key.contains("C")) {
//                                        mInforTrainfer.setDepartmentPosition(value.toString());
//                                    }
//                                } else {
//                                    if (key.equals(EXECUTION_UNIT)) {
//                                        mArrayExecutionUnit = new JSONArray(value.toString());
//                                        Log.d(TAG, mArrayExecutionUnit.toString());
//                                    }
//                                }
//
//                            }
//                            JSONArray mArrayFileAttach = mJsonObject.getJSONArray(ATTACH_FILE);
//                            for (int i = 0; i < mArrayFileAttach.length(); i++) {
//                                JSONObject mObjectFileAttach = mArrayFileAttach.getJSONObject(i);
//                                String fileName = mObjectFileAttach.getString(NAME);
//                                String fileType = mObjectFileAttach.getString(TYPE);
//                                String fileURL = mObjectFileAttach.getString(ATTACK_URL);
//                                String Base64Code = mObjectFileAttach.getString(BASE_64);
//                                arrAtachFile.add(new AttachFile(fileName, fileType, fileURL, Base64Code));
//                            }
//                            FileAttachAdapter attachAdapter = new FileAttachAdapter(DetailForwardActivity.this, arrAtachFile);
//                            mDetailFragment.setAdapterListFileAttach(attachAdapter);
//                            if (!mJsonObject.isNull(WORKFLOWTRANSITION)) {
//                                JSONArray mArrayTransferLines = mJsonObject.getJSONArray(WORKFLOWTRANSITION);
//                                for (int i = 0; i < mArrayTransferLines.length(); i++) {
//                                    if (mArrayTransferLines != null) {
//                                        JSONObject mObjectTrainferLine = mArrayTransferLines.getJSONObject(i);
//                                        switch (mObjectTrainferLine.getInt(TYPE)) {
//                                            case 4:
//                                            case 6:
//                                                mInforTrainfer.setTrainferID(mObjectTrainferLine.getString(ID));
//                                                break;
//                                            case 7:
//                                                mInforTrainfer.setForwardDepartmentID(mObjectTrainferLine.getLong(ID));
////                                    new ReadJsonDepartment().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, getLink() + URL_CENTER_6_1);
//                                                break;
//                                            case 11:
//                                                mInforTrainfer.setWorkFlowStationCancel(mObjectTrainferLine.getLong(ID));
////                                    new ReadJsonCancelList().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, getLink() + URL_CENTER_6_1);
//                                                break;
//                                            case 1:
//                                                mInforTrainfer.setWorkFlowStationReturn(mObjectTrainferLine.getLong(ID));
////                                    new ReadJsonPerSonReturn().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, getLink() + URL_CENTER_6_1);
//                                                break;
//                                        }
//                                    }
//                                }
//                                mDetailFragment.setVisibilitiesButtonForward(mArrayTransferLines.toString());
//                            } else {
//                                mDetailFragment.setVisibilitiesButtonForward("[]");
//                            }
////                arrDetailLimit_1 = arrDetails.subList(0, 4);
////                arrDetailLimit_2 = arrDetails.subList(4, arrDetails.size());
//////                detailRowAdapter_1 = new DetailRowAdapter(DetailForwardActivity.this, arrDetailLimit_1);
//////                detailRowAdapter_2 = new DetailRowAdapter(DetailForwardActivity.this, arrDetailLimit_2);
//////                mDetailFragment.setListDetail2nd(detailRowAdapter_2);
//////                mDetailFragment.setAdapterListDetais(detailRowAdapter_1);
//                            detailAdapter = new DetailRowAdapter(DetailForwardActivity.this, arrDetails);
//                            mDetailFragment.setAdapterListDetais(detailAdapter);
//                            mDetailFragment.showButtonMoreDetails(true);
//                            if (mInforTrainfer.getIsScreen().equals(NOTIFY_SCREEN)) {
//                                mSqLiteNotify.QueryData("DELETE FROM '" + NOTIFY_SQL + "' WHERE objectid = '" + mInforTrainfer.getDocumentID() + "'");
//                            }
//                            break;
//                        case 1:
//                            // read json tree and some information when tap tree item
////                visibleLayoutTryAgain(s);
////                    JSONObject mJsonObjectFullData = new JSONObject(s);
////                    JSONObject mJsonObject = mJsonObjectFullData.getJSONObject(DATA);
//                            //                    String mProcessor = String.valueOf((Html.fromHtml(mJsonTreeForward.getString(PROCESSOR))));
//                            JSONObject mJsonTreeForward = mJsonObject.getJSONObject(TREEJOBCYCLE);
//                            String mProcessor = mJsonTreeForward.getString(PROCESSOR);
//                            String mPosition = mJsonTreeForward.getString(ROLE_NAME);
//                            String mDepartment = mJsonTreeForward.getString(PROCESS_ORG);
//                            String mRequestProcessing = mJsonTreeForward.getString(PROCESS_CONTENT);
//                            String mProcessingDay = mJsonTreeForward.getString(PROCESS_DATE);
//                            String mReceivedDay = mJsonTreeForward.getString(RECEIVE_DATE);
//                            Log.d(TAG, mProcessor);
//                            root = TreeNode.root();
//                            TreeHolder.IconTreeItem nodeItem = new TreeHolder.IconTreeItem(R.drawable.ic_keyboard_arrow_right_black_24dp, mProcessor, mPosition, mDepartment,
//                                    mRequestProcessing, mProcessingDay, mReceivedDay);
//                            Parent = new TreeNode(nodeItem).setViewHolder(new TreeHolder(DetailForwardActivity.this));
//                            root.addChild(Parent);
//                            BrowerJson(mJsonTreeForward, Parent);
//                            AndroidTreeView tView = new AndroidTreeView(DetailForwardActivity.this, root);
////                    tView.setDefaultAnimation(true);
//                            tView.setDefaultContainerStyle(R.style.TreeNodeStyleCustom);
//                            tView.setUse2dScroll(true);
//                            mForwardFragment.SetViewList(tView);
//                            tView.expandAll();
//                            closeProgressDialog();
//                            break;
//                        case 2:
//                            if (!mOther.isNull(INFO_FEED_BACK)) {
//                                JSONArray mArrayFeedBack = mOther.getJSONArray(INFO_FEED_BACK);
//                                for (int i = 0; i < mArrayFeedBack.length(); i++) {
//                                    JSONObject mObject = mArrayFeedBack.getJSONObject(i);
//                                    String content = mObject.getString(SCHEDULE_CONTENT);
//                                    String organizationName = mObject.getString(ORGANIZATION_NAME);
//                                    String userName = mObject.getString(USER_NAME);
//                                    String createDate = mObject.getString(CREATE_DATE);
//                                    JSONArray mArrayAttachFile = mObject.getJSONArray(ATTACH_FILE);
//                                    arrFeedback.add(new FeedBackRow(content, organizationName, userName, createDate, mArrayAttachFile));
//                                }
//                            }
//                            mFeedBackFragment.setAdapterFeedBack(arrFeedback);
//                            closeProgressDialog();
//                            break;
//                        case 3:
//                            if (!mOther.isNull(HANDLE_WAY_CHANGE_LOG)) {
//                                JSONArray mArrayHandleChange = mOther.getJSONArray(HANDLE_WAY_CHANGE_LOG);
//                                writeToFile(mArrayHandleChange.toString(), this, HANDLE_WAY_CHANGE_LOG);
//                            }
//                            // get document connection reciver
//                            if (!mOther.isNull(DOC_RECEIPT_CONNECTS)) {
//                                mArrayReceipt = mOther.getJSONArray(DOC_RECEIPT_CONNECTS);
//                                writeToFile(mArrayReceipt.toString(), this, DOC_RECEIPT_CONNECTS);
//                            }
////                    mOtherFragment.setAdapterDocConnectReceiver(arrDocConnectionReceiver);
//                            // get document connection Send
//                            if (!mOther.isNull(DOC_SEND_CONNECTS)) {
//                                mArraySend = mOther.getJSONArray(DOC_SEND_CONNECTS);
//                                writeToFile(mArraySend.toString(), this, DOC_SEND_CONNECTS);
//                            }
////                    mOtherFragment.setAdapterDocConnectSend(arrDocConnectionSend);
//                            break;
//                        default:
//                            break;
//                    }
//                    break;
//
//            }
//        } catch (JSONException e) {
//            closeProgressDialog();
//            ShowErrorToast(DetailForwardActivity.this);
////                String fileSave = readFromFile(DetailForwardActivity.this, mInforTrainfer.getDocumentID());
////                if (!fileSave.equals(nULL_STRING) && !fileSave.equals("Error!")) {
////                    upDateViewDetail(readFromFile(DetailForwardActivity.this, mInforTrainfer.getDocumentID()));
////                }
//            e.printStackTrace();
//        }
//        closeProgressDialog();
//    }
    //    class ReadJsonPerSonReturn extends AsyncTask<String, Integer, String>{
//
//        @Override
//        protected String doInBackground(String... params) {
//            JSONObject mJsonObject = new JSONObject();
//            try {
//                mJsonObject.put(MODULE, PROCESSING_WORKING );
//                mJsonObject.put(NEOTYPE, TYPE_INPUT_PERSON);
//                JSONObject mData = new JSONObject();
//                mData.put(JOB_ID, mInforTrainfer.getDocumentID());
//                mData.put(WORK_FLOW_TRAINSITION_ID, mInforTrainfer.getWorkFlowStationReturn());
//                mJsonObject.put(DATA,mData);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            return makePostRequest(params[0], mJsonObject.toString(), getUserid(), getPass());
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            if (!s.equals("Error!") && !s.equals(nULL_STRING)) {
//                writeToFile(s, DetailForwardActivity.this, "return_list" + mInforTrainfer.getDocumentID());
//            }
//            super.onPostExecute(s);
//        }
//    }
//
///*=================================
//    show title no connection if do not have file saved before
//===================================*/
//
//    private void visibleLayoutTryAgain(String s) {
//        if (s.equals(nULL_STRING)) {
//            if (mCheckUI == 0) {
//                mDetailFragment.visibleLayoutTryAgain(true);
//            } else {
//                mForwardFragment.visibleLayoutTryAgainForward(true);
//            }
//            return;
//        } else {
//            if (mCheckUI == 0) {
//                mDetailFragment.visibleLayoutTryAgain(false);
//            } else {
//                mForwardFragment.visibleLayoutTryAgainForward(false);
//            }
//        }
//    }
//    public void showMoreDataList() {
//        detailRowAdapter_1 = new DetailRowAdapter(this, arrDetails);
//        mDetailFragment.setAdapterListDetais(detailRowAdapter_1);
////        arrDetailLimit = arrDetails;
////        detailRowAdapter.notifyDataSetChanged();
//    }
///*
//    Asyntask Read list people user wanna forward
//*/
//    class ReadJsonInputPerson extends AsyncTask<String, Integer, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            JSONObject mJsonObject = new JSONObject();
//            try {
//                if (mInforTrainfer.getTrainferID() != null && mInforTrainfer.getDocumentID() != 0) {
////                    mJsonObject.put("congViecId", Integer.parseInt(mInforTrainfer.getDocumentID()));
////                    mJsonObject.put("duongChuyenId", Integer.parseInt(mInforTrainfer.getTrainferID()));
//                    mJsonObject.put(MODULE, PROCESSING_WORKING);
//                    mJsonObject.put(NEOTYPE, TYPE_INPUT_PERSON);
//                    JSONObject mJsonObjectData = new JSONObject();
//                    mJsonObjectData.put(JOB_ID, mInforTrainfer.getDocumentID());
//                    mJsonObjectData.put(WORK_FLOW_TRAINSITION_ID, Integer.parseInt(mInforTrainfer.getTrainferID()));
//                    mJsonObject.put(DATA, mJsonObjectData.toString());
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            return makePostRequest(params[0], mJsonObject.toString(), getUserid(), getPass());
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            try {
//                JSONObject mJsonObject = new JSONObject(s);
//                JSONArray mJsonArray = mJsonObject.getJSONArray(DATA);
//                for (int i = 0; i < mJsonArray.length(); i++) {
//                    JSONObject mJsonObjectRow = mJsonArray.getJSONObject(i);
//                    boolean mDefault = mJsonObjectRow.getBoolean(DEFAULT);
//                    String mReceivePersonName = mJsonObjectRow.getString(RECEIVER_NAME);
//                    String mReceiveRoomName = mJsonObjectRow.getString(ORGANIZATION_NAME);
//                    String mReceivePersonID = mJsonObjectRow.getString(RECEIVER_ID);
//                    String mReceiveRoomID = mJsonObjectRow.getString(ORGANIZATION_ID);
//                    String mRoleName = mJsonObjectRow.getString(ROLE_NAME);
//                    mSqLite.QueryData(INSERT_OR_IGONE_INTO_INPUT_PERSON + " VALUES('" + mInforTrainfer.getDocumentID() + "','" + mInforTrainfer.getTrainferID() + "','" + mDefault + "','" +
//                            mReceivePersonName + "','" + mReceivePersonID + "','" + mReceiveRoomName + "','" + mReceiveRoomID + "','" + mRoleName + "')");
//                }
//            } catch (JSONException e) {
//
//                e.printStackTrace();
//            }
//            super.onPostExecute(s);
//        }
//    }
//    class ReadJsonCancelList extends AsyncTask<String, Integer, String>{
//
//        @Override
//        protected String doInBackground(String... params) {
//            JSONObject mJsonObject = new JSONObject();
//            try {
//                mJsonObject.put(MODULE,PROCESSING_WORKING );
//                mJsonObject.put(NEOTYPE, TYPE_CANCEL_LIST);
//                JSONObject mData = new JSONObject();
//                mData.put(JOB_ID, mInforTrainfer.getDocumentID());
//                mData.put(WORK_FLOW_TRAINSITION_ID,mInforTrainfer.getWorkFlowStationCancel());
//                mJsonObject.put(DATA,mData);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            return makePostRequest(params[0], mJsonObject.toString(), getUserid(), getPass());
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            if (!s.equals("Error!") && !s.equals(nULL_STRING)) {
//                writeToFile(s, DetailForwardActivity.this, "cancel_list" + mInforTrainfer.getDocumentID());
//            }
//            super.onPostExecute(s);
//        }
//    }
///*
//    Asyntask read detail but not read tree forward
//*/
//
//    class ReadJsonDetail extends AsyncTask<String, Integer, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            String Resuilt = "";
//            JSONObject mJsonObject = new JSONObject();
//            JSONObject mJsonObjectData;
//            try {
//                switch (mInforTrainfer.getIsScreen()) {
//                    case DOC_NOT_SEEN_TYPE:
//                    case DOC_NOT_PROCESS_TYPE:
//                    case DOC_DEMURRAGE_TYPE:
//                        if (mInforTrainfer.getTypeHomeListDocument().equals(TYPE_HOME_LIST_DOCUMENT_SENT)) {
//                            mJsonObject.put(MODULE, LOOKUP_DOCUMENT);
//                            mJsonObject.put(NEOTYPE, TYPE_LOOKUP_DETAIL_SENT);
//                            mJsonObjectData = new JSONObject();
//                            mJsonObjectData.put(DOC_SEND_ID, mInforTrainfer.getDocumentID());
//                            mJsonObject.put(DATA, mJsonObjectData.toString());
//                        } else {
//                            mJsonObject.put(MODULE, LOOKUP_DOCUMENT);
//                            mJsonObject.put(NEOTYPE, TYPE_LOOKUP_DETAIL_COMING);
//                            mJsonObjectData = new JSONObject();
//                            mJsonObjectData.put(DOC_RECEIPT_ID, mInforTrainfer.getDocumentID());
//                            mJsonObject.put(DATA, mJsonObjectData.toString());
//                        }
//                        Resuilt = eventPostRequest(getModuleInfor(LOOKUP_DOCUMENT), mJsonObject.toString(), getUserid(), getPass());
//                        break;
//                    case STA_DOC_PROCESS_ON_TIME_FULL:
//                    case STA_DOC_NOT_PROCESS_FULL:
//                    case STA_DOC_DEMURRAGE_FULL:
//                    case LIST_DOCUMENT_DEPARTMENT:
//                    case LOOKUP_COMING:
//                        mJsonObject.put(MODULE, LOOKUP_DOCUMENT);
//                        mJsonObject.put(NEOTYPE, TYPE_LOOKUP_DETAIL_COMING);
//                        mJsonObjectData = new JSONObject();
//                        mJsonObjectData.put(DOC_RECEIPT_ID, mInforTrainfer.getDocumentID());
//                        mJsonObject.put(DATA, mJsonObjectData.toString());
//                        Resuilt = eventPostRequest(getModuleInfor(LOOKUP_DOCUMENT), mJsonObject.toString(), getUserid(), getPass());
//                        break;
//                    case LOOKUP_SENT:
//                        mJsonObject.put(MODULE, LOOKUP_DOCUMENT);
//                        mJsonObject.put(NEOTYPE, TYPE_LOOKUP_DETAIL_SENT);
//                        mJsonObjectData = new JSONObject();
//                        mJsonObjectData.put(DOC_SEND_ID, mInforTrainfer.getDocumentID());
//                        mJsonObject.put(DATA, mJsonObjectData.toString());
//                        Resuilt = eventPostRequest(getModuleInfor(LOOKUP_DOCUMENT), mJsonObject.toString(), getUserid(), getPass());
//                        break;
//                    case LOOKUP_INTERNAL:
//                        mJsonObject.put(MODULE, LOOKUP_DOCUMENT);
//                        mJsonObject.put(NEOTYPE, TYPE_LOOKUP_DETAIL_INTERNAL);
//                        mJsonObjectData = new JSONObject();
//                        mJsonObjectData.put(DOC_LOCAL_ID, mInforTrainfer.getDocumentID());
//                        mJsonObject.put(DATA, mJsonObjectData.toString());
//                        Resuilt = eventPostRequest(getModuleInfor(LOOKUP_DOCUMENT), mJsonObject.toString(), getUserid(), getPass());
//                        break;
//                    case TASK_REPORTED:
//                    case TASK_PROCESS:
//                    case TASK_PROCESS_ON_TIME:
//                    case TASK_PROCESS_NEAR_DEMURRAGE:
//                    case TASK_PROCESS_DEMURRAGE:
//                        mJsonObject.put(MODULE, LOOKUP_DOCUMENT);
//                        mJsonObject.put(NEOTYPE, TYPE_TASK_DETAIL);
//                        mJsonObjectData = new JSONObject();
//                        mJsonObjectData.put(TASK_ID, mInforTrainfer.getDocumentID());
//                        mJsonObject.put(DATA, mJsonObjectData.toString());
//                        Resuilt = eventPostRequest(getModuleInfor(LOOKUP_DOCUMENT), mJsonObject.toString(), getUserid(), getPass());
//                        break;
//                    case NOTIFY_SCREEN:
//                    default:
//                        mJsonObject.put(MODULE, PROCESSING_WORKING);
//                        mJsonObject.put(NEOTYPE, TYPE_DETAIL_WORKING);
//                        mJsonObjectData = new JSONObject();
//                        mJsonObjectData.put(JOB_ID, mInforTrainfer.getDocumentID());
//                        mJsonObject.put(DATA, mJsonObjectData.toString());
//                        Resuilt = eventPostRequest(getModuleInfor(PROCESSING_WORKING), mJsonObject.toString(), getUserid(), getPass());
//                        break;
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            String user = getUserid();
//            String pass = getPass();
//            Log.d(TAG, user + " " + pass);
////            return makePostRequest(params[0], mJsonObject.toString(), getUserid(), getPass());
//            return Resuilt;
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
////            s = "";
//            if (!s.equals(nULL_STRING) && !s.equals("Error!")) {
//                writeToFile(s, DetailForwardActivity.this, String.valueOf(mInforTrainfer.getDocumentID()));
//            }
//            upDateViewDetail(s);
//            super.onPostExecute(s);
//        }
//    }
///*
//    Analysis json forward tree by Recursive algorithm
//*/
//
//    public void BrowerJson(JSONObject xJsonObject, TreeNode node) {
//        try {
//            JSONArray mJsonArrayChildTree = xJsonObject.getJSONArray(CHILDREN);
//            for (int i = 0; i < mJsonArrayChildTree.length(); i++) {
//                JSONObject mJsonObject = mJsonArrayChildTree.getJSONObject(i);
//                Log.d(TAG, mJsonObject + nULL_STRING);
//                if (!mJsonObject.isNull(CHILDREN)) {
//                    String mProcessor = String.valueOf((Html.fromHtml(mJsonObject.getString(PROCESSOR))));
//                    String mPosition = mJsonObject.getString(ROLE_NAME);
//                    String mDepartment = mJsonObject.getString(PROCESS_ORG);
//                    String mRequestProcessing = mJsonObject.getString(PROCESS_CONTENT);
//                    String mProcessingDay = mJsonObject.getString(PROCESS_DATE);
//                    String mReceivedDay = mJsonObject.getString(RECEIVE_DATE);
//                    TreeHolder.IconTreeItem nodeItem = new TreeHolder.IconTreeItem(R.drawable.ic_keyboard_arrow_right_black_24dp, mProcessor,
//                            mPosition, mDepartment, mRequestProcessing, mProcessingDay, mReceivedDay);
//                    TreeNode Child = new TreeNode(nodeItem).setViewHolder(new TreeHolder(DetailForwardActivity.this));
//                    node.addChildren(Child);
//                    BrowerJson(mJsonObject, Child);
//                } else {
//                    String mProcessor = String.valueOf((Html.fromHtml(mJsonObject.getString(PROCESSOR))));
//                    String mPosition = mJsonObject.getString(ROLE_NAME);
//                    String mDepartment = mJsonObject.getString(PROCESS_ORG);
//                    String mRequestProcessing = mJsonObject.getString(PROCESS_CONTENT);
//                    String mProcessingDay = mJsonObject.getString(PROCESS_DATE);
//                    String mReceivedDay = mJsonObject.getString(RECEIVE_DATE);
//                    TreeHolder.IconTreeItem nodeItem = new TreeHolder.IconTreeItem(0, mProcessor,
//                            mPosition, mDepartment, mRequestProcessing, mProcessingDay, mReceivedDay);
//                    TreeNode Child = new TreeNode(nodeItem).setViewHolder(new TreeHolder(DetailForwardActivity.this));
//                    node.addChildren(Child);
//                }
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//
//
//    private void initActiobar() {
//        mToolbar = (Toolbar) findViewById(R.id.app_bar);
//        setSupportActionBar(mToolbar);
//        getSupportActionBar().setTitle("");
//        titleActionbar = (TextView) findViewById(R.id.title_actionbar);
////        titleActionbar.setText(getString(R.string.xu_ly_cong_viec));
//        titleActionbar.setText("CHI TIẾT");
//        mToolbar.setPadding(0, getStatusBarHeight(this), 0, 0);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });
//    }
//    /*===========================
//    update notify number by BroadcastReceiver
//=============================*/
//
//    BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            String action = intent.getStringExtra(RELOADSLIDER);
//            Log.d(TAG, "BroadcastReceiver Enable");
//            switch (action) {
//                case NOTIFICATION_UP_DATE:
//                    showNotifiCation(mNumberOfNotify);
//                    break;
//                default:
//                    break;
//            }
//        }
//    };
//    private void initBroadcast() {
//        IntentFilter filter = new IntentFilter(BROADCASTLISTENNER);
//        this.registerReceiver(mBroadcastReceiver, filter);
//    }
///*=====================================
//    ChangeListView method will read json detail if it have connection else it read from file saved
//=======================================*/
//
//    public void changeListView(int CheckUI) {
//        showProgressDialog(nULL_STRING, this, DIALOG_CENTER, true);
//        String x = getLink();
//        mCheckUI = CheckUI;
//        Log.d(TAG, x);
//        if (isNetworkAvailable(DetailForwardActivity.this)) {
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    new ReadJsonDetail().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, getLink() + URL_CENTER_6_1);
//                    new ReadJsonHandleProcess().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, getLink() + URL_CENTER_6_1);
//                }
//            });
//        } else {
//            upDateViewDetail(readFromFile(DetailForwardActivity.this, String.valueOf(mInforTrainfer.getDocumentID())));
//        }
//
//    }
//
//    class ReadJsonDepartment extends AsyncTask<String, Integer, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            JSONObject mJsonObject = new JSONObject();
//            try {
//                mJsonObject.put(MODULE, PROCESSING_WORKING);
//                mJsonObject.put(NEOTYPE, TYPE_DEPARTMENT_LIST_RECEIVE);
//                JSONObject mData = new JSONObject();
//                mData.put(JOB_ID, mInforTrainfer.getDocumentID());
//                mData.put(WORK_FLOW_TRAINSITION_ID, mInforTrainfer.getForwardDepartmentID());
//                mJsonObject.put(DATA, mData.toString());
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            return makePostRequest(params[0], mJsonObject.toString(), getUserid(), getPass());
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            if (!s.equals("Error!") && !s.equals(nULL_STRING)) {
//                writeToFile(s, DetailForwardActivity.this, "department" + mInforTrainfer.getDocumentID());
//            }
//            super.onPostExecute(s);
//        }
//    }
//
//
//    class ReadJsonHandleProcess extends AsyncTask<String, Integer, String> {
//        @Override
//        protected String doInBackground(String... params) {
//            JSONObject mJsonObject = new JSONObject();
//            try {
//                mJsonObject.put(MODULE, LOOKUP_DOCUMENT);
//                mJsonObject.put(NEOTYPE, PROCESS_HANDLE);
//                JSONObject mData = new JSONObject();
//                mData.put(JOB_ID, mInforTrainfer.getDocumentID());
//                mJsonObject.put(DATA, mData);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
////            return makePostRequest(params[0], mJsonObject.toString(), getUserid(), getPass());
//            return eventPostRequest(getModuleInfor(LOOKUP_DOCUMENT), mJsonObject.toString(), getUserid(), getPass());
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            if (!s.equals("Error!") && !s.equals(nULL_STRING)) {
//                writeToFile(s, DetailForwardActivity.this, "handle" + mInforTrainfer.getDocumentID());
//            }
//            super.onPostExecute(s);
//        }
//    }
//
//
//
///*=============================
//    Analysis json detail if mCheckUI = true (inside DetailFragment)
//    it read all json but not read json fordward tree else (inside FordWardFragment)
//    it read json tree
//===============================*/
//
//    private void upDateViewDetail(String s) {
//        initFragment();
//        Log.d(TAG, s);
//        arrAtachFile = new ArrayList<AttachFile>();
//        arrDetails = new ArrayList<DetailsRows>();
//        arrDetailLimit_1 = new ArrayList<DetailsRows>();
//        arrDetailLimit_2 = new ArrayList<DetailsRows>();
//        arrFeedback = new ArrayList<FeedBackRow>();
//        arrHanleChange = new ArrayList<HandleChangeRow>();
//        arrContentTasks = new ArrayList<ContentTasksRow>();
//        arrReportedTasks = new ArrayList<ReportTasksRow>();
//        arrMsgGroupTask = new ArrayList<GroupMsgTasksRow>();
//        try {
//            JSONObject mJsonObjectFullData = new JSONObject(s);
//            JSONObject mJsonObject = mJsonObjectFullData.getJSONObject(DATA);
//            // check tab other show
//            JSONObject mOther = mJsonObject.getJSONObject(OTHER_INFO);
//            switch (mInforTrainfer.getIsScreen()) {
//                case TASK_REPORTED:
//                case TASK_PROCESS:
//                case TASK_PROCESS_ON_TIME:
//                case TASK_PROCESS_NEAR_DEMURRAGE:
//                case TASK_PROCESS_DEMURRAGE:
//                    switch (mCheckUI) {
//                        case 0:
//                            if (!isRegisterTabhost) {
//                                initTabHost(mOther);
//                                isRegisterTabhost = true;
//                            }
//                            // check tab other show
//                            Iterator<String> iter = mJsonObject.keys();
//                            while (iter.hasNext()) {
//                                String key = iter.next();
//                                Object value = mJsonObject.get(key);
//                                if (!key.equals(ATTACH_FILE) && !key.equals(TREEJOBCYCLE) && !key.equals(WORKFLOWTRANSITION) && !key.equals(OTHER_INFO) && !key.equals(REMOVE_JOB) && !key.equals(EXECUTION_UNIT)) {
//                                    arrDetails.add(new DetailsRows(key + ":", value + nULL_STRING));
//                                    if (key.contains("M")) {
//                                        mInforTrainfer.setProcessPerson(value.toString());
//                                    }
//                                    if (key.contains("L")) {
//                                        mInforTrainfer.setProcessPosition(value.toString());
//                                    }
//                                    if (key.contains("C")) {
//                                        mInforTrainfer.setDepartmentPosition(value.toString());
//                                    }
//                                } else {
//                                    if (key.equals(EXECUTION_UNIT)) {
//                                        mArrayExecutionUnit = new JSONArray(value.toString());
//                                        Log.d(TAG, mArrayExecutionUnit.toString());
//                                    }
//                                }
//
//                            }
//                            JSONArray mArrayFileAttach = mJsonObject.getJSONArray(ATTACH_FILE);
//                            for (int i = 0; i < mArrayFileAttach.length(); i++) {
//                                JSONObject mObjectFileAttach = mArrayFileAttach.getJSONObject(i);
//                                String fileName = mObjectFileAttach.getString(NAME);
//                                String fileType = mObjectFileAttach.getString(TYPE);
//                                String fileURL = mObjectFileAttach.getString(ATTACK_URL);
//                                String Base64Code = mObjectFileAttach.getString(BASE_64);
//                                arrAtachFile.add(new AttachFile(fileName, fileType, fileURL, Base64Code));
//                            }
//                            FileAttachAdapter attachAdapter = new FileAttachAdapter(DetailForwardActivity.this, arrAtachFile);
//                            mDetailFragment.setAdapterListFileAttach(attachAdapter);
//                            if (!mJsonObject.isNull(WORKFLOWTRANSITION)) {
//                                JSONArray mArrayTransferLines = mJsonObject.getJSONArray(WORKFLOWTRANSITION);
//                                for (int i = 0; i < mArrayTransferLines.length(); i++) {
//                                    if (mArrayTransferLines != null) {
//                                        JSONObject mObjectTrainferLine = mArrayTransferLines.getJSONObject(i);
//                                        switch (mObjectTrainferLine.getInt(TYPE)) {
//                                            case 4:
//                                            case 6:
//                                                mInforTrainfer.setTrainferID(mObjectTrainferLine.getString(ID));
//                                                break;
//                                            case 7:
//                                                mInforTrainfer.setForwardDepartmentID(mObjectTrainferLine.getLong(ID));
////                                    new ReadJsonDepartment().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, getLink() + URL_CENTER_6_1);
//                                                break;
//                                            case 11:
//                                                mInforTrainfer.setWorkFlowStationCancel(mObjectTrainferLine.getLong(ID));
////                                    new ReadJsonCancelList().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, getLink() + URL_CENTER_6_1);
//                                                break;
//                                            case 1:
//                                                mInforTrainfer.setWorkFlowStationReturn(mObjectTrainferLine.getLong(ID));
////                                    new ReadJsonPerSonReturn().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, getLink() + URL_CENTER_6_1);
//                                                break;
//                                        }
//                                    }
//                                }
//                                mDetailFragment.setVisibilitiesButtonForward(mArrayTransferLines.toString());
//                            } else {
//                                mDetailFragment.setVisibilitiesButtonForward("[]");
//                            }
////                arrDetailLimit_1 = arrDetails.subList(0, 4);
////                arrDetailLimit_2 = arrDetails.subList(4, arrDetails.size());
//////                detailRowAdapter_1 = new DetailRowAdapter(DetailForwardActivity.this, arrDetailLimit_1);
//////                detailRowAdapter_2 = new DetailRowAdapter(DetailForwardActivity.this, arrDetailLimit_2);
//////                mDetailFragment.setListDetail2nd(detailRowAdapter_2);
//////                mDetailFragment.setAdapterListDetais(detailRowAdapter_1);
//                            detailAdapter = new DetailRowAdapter(DetailForwardActivity.this, arrDetails);
//                            mDetailFragment.setAdapterListDetais(detailAdapter);
//                            mDetailFragment.showButtonMoreDetails(true);
////                runOnUiThread(new Runnable() {
////                    @Override
////                    public void run() {
////                        new ReadJsonInputPerson().execute(getLink() + URL_CENTER_6_1);
////                    }
////                });
//                            if (mInforTrainfer.getIsScreen().equals(NOTIFY_SCREEN)) {
//                                mSqLiteNotify.QueryData("DELETE FROM '" + NOTIFY_SQL + "' WHERE objectid = '" + mInforTrainfer.getDocumentID() + "'");
//                            }
//                            break;
//                        case 1:
//                            // get message tasks
//                            if (!mOther.isNull(MESSAGE_TASKS)) {
//                                JSONArray mJsonMessageTasks = mOther.getJSONArray(MESSAGE_TASKS);
//                                for (int i = 0; i < mJsonMessageTasks.length(); i++) {
//                                    JSONObject mObject = mJsonMessageTasks.getJSONObject(i);
//                                    JSONArray mArrayExecutionUnits = mJsonObject.getJSONArray(EXECUTION_UNITS);
//                                    if (!mArrayExecutionUnits.toString().equals("[]")) {
//                                        arrMsgGroupTask.add(new GroupMsgTasksRow("Nhắc nhở lần " + (i + 1), mJsonObject));
//                                        Log.d("JsonGroupTasks", mJsonObject.toString());
//                                    }
//                                }
//                            }
//                            mMsgTaskFragment.setAdapterMsgGroupTask(arrMsgGroupTask);
//                            break;
//                        case 2:
//                            // get content Tasks
//                            if (!mOther.isNull(CONTENT_TASKS)) {
//                                JSONArray mJsonMessageTasks = mOther.getJSONArray(CONTENT_TASKS);
//                                for (int i = 0; i < mJsonMessageTasks.length(); i++) {
//                                    JSONObject mObject = mJsonMessageTasks.getJSONObject(i);
//                                    String staff = mObject.getString(STAFF);
//                                    String exchangeDate = mObject.getString(EXCHANGE_DATE);
//                                    int classify = mObject.getInt(CLASSIFY);
//                                    String content = mObject.getString(SCHEDULE_CONTENT);
//                                    JSONArray mArrayAttachFile = mObject.getJSONArray(ATTACH_FILE);
//                                    arrContentTasks.add(new ContentTasksRow(staff, exchangeDate, classify, content, mArrayAttachFile));
//                                }
//                            }
//                            mContentTaskFragment.setAdapterContentTask(arrContentTasks);
//                            break;
//                        case 3:
//                            if (!mOther.isNull(REPORTED_TASKS)) {
//                                JSONArray mJsonReportTasks = mOther.getJSONArray(REPORTED_TASKS);
//                                if (!mJsonReportTasks.toString().equals("[]")) {
//                                    for (int i = 0; i < mJsonReportTasks.length(); i++) {
//                                        JSONObject mObject = mJsonReportTasks.getJSONObject(i);
//                                        String organizationName = mObject.getString(ORGANIZATION_NAME);
//                                        String docNumberReport = mObject.getString(DOC_NUMBER_REPORT);
//                                        String dateReport = mObject.getString(DATE_REPORT);
//                                        String contentReport = mObject.getString(CONTENT_REPORT);
//                                        JSONArray mArrayAttachFile = mObject.getJSONArray(ATTACH_FILE);
//                                        arrReportedTasks.add(new ReportTasksRow(organizationName, docNumberReport, dateReport, contentReport, mArrayAttachFile));
//                                    }
//                                }
//                            }
//                            mReportTaskFragment.setAdapterReportTask(arrReportedTasks);
//                            break;
//                        default:
//                            break;
//                    }
//                    break;
//                case STA_DOC_PROCESS_ON_TIME_FULL:
//                case STA_DOC_NOT_PROCESS_FULL:
//                case STA_DOC_DEMURRAGE_FULL:
//                case LIST_DOCUMENT_DEPARTMENT:
//                case LOOKUP_COMING:
//                case LOOKUP_SENT:
//                case LOOKUP_INTERNAL:
//                case DOC_NOT_SEEN_TYPE:
//                case DOC_NOT_PROCESS_TYPE:
//                case DOC_DEMURRAGE_TYPE:
//                case NOTIFY_SCREEN:
//                default:
//                    switch (mCheckUI) {
//                        case 0:
//                            if (!isRegisterTabhost) {
//                                initTabHost(mOther);
//                                isRegisterTabhost = true;
//                            }
//                            // check tab other show
//                            Iterator<String> iter = mJsonObject.keys();
//                            while (iter.hasNext()) {
//                                String key = iter.next();
//                                Object value = mJsonObject.get(key);
//                                if (!key.equals(ATTACH_FILE) && !key.equals(TREEJOBCYCLE) && !key.equals(WORKFLOWTRANSITION) && !key.equals(OTHER_INFO) && !key.equals(REMOVE_JOB) && !key.equals(EXECUTION_UNIT)) {
//                                    arrDetails.add(new DetailsRows(key + ":", value + nULL_STRING));
//                                    if (key.contains("M")) {
//                                        mInforTrainfer.setProcessPerson(value.toString());
//                                    }
//                                    if (key.contains("L")) {
//                                        mInforTrainfer.setProcessPosition(value.toString());
//                                    }
//                                    if (key.contains("C")) {
//                                        mInforTrainfer.setDepartmentPosition(value.toString());
//                                    }
//                                } else {
//                                    if (key.equals(EXECUTION_UNIT)) {
//                                        mArrayExecutionUnit = new JSONArray(value.toString());
//                                        Log.d(TAG, mArrayExecutionUnit.toString());
//                                    }
//                                }
//
//                            }
//                            JSONArray mArrayFileAttach = mJsonObject.getJSONArray(ATTACH_FILE);
//                            for (int i = 0; i < mArrayFileAttach.length(); i++) {
//                                JSONObject mObjectFileAttach = mArrayFileAttach.getJSONObject(i);
//                                String fileName = mObjectFileAttach.getString(NAME);
//                                String fileType = mObjectFileAttach.getString(TYPE);
//                                String fileURL = mObjectFileAttach.getString(ATTACK_URL);
//                                String Base64Code = mObjectFileAttach.getString(BASE_64);
//                                arrAtachFile.add(new AttachFile(fileName, fileType, fileURL, Base64Code));
//                            }
//                            FileAttachAdapter attachAdapter = new FileAttachAdapter(DetailForwardActivity.this, arrAtachFile);
//                            mDetailFragment.setAdapterListFileAttach(attachAdapter);
//                            if (!mJsonObject.isNull(WORKFLOWTRANSITION)) {
//                                JSONArray mArrayTransferLines = mJsonObject.getJSONArray(WORKFLOWTRANSITION);
//                                for (int i = 0; i < mArrayTransferLines.length(); i++) {
//                                    if (mArrayTransferLines != null) {
//                                        JSONObject mObjectTrainferLine = mArrayTransferLines.getJSONObject(i);
//                                        switch (mObjectTrainferLine.getInt(TYPE)) {
//                                            case 4:
//                                            case 6:
//                                                mInforTrainfer.setTrainferID(mObjectTrainferLine.getString(ID));
//                                                break;
//                                            case 7:
//                                                mInforTrainfer.setForwardDepartmentID(mObjectTrainferLine.getLong(ID));
////                                    new ReadJsonDepartment().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, getLink() + URL_CENTER_6_1);
//                                                break;
//                                            case 11:
//                                                mInforTrainfer.setWorkFlowStationCancel(mObjectTrainferLine.getLong(ID));
////                                    new ReadJsonCancelList().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, getLink() + URL_CENTER_6_1);
//                                                break;
//                                            case 1:
//                                                mInforTrainfer.setWorkFlowStationReturn(mObjectTrainferLine.getLong(ID));
////                                    new ReadJsonPerSonReturn().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, getLink() + URL_CENTER_6_1);
//                                                break;
//                                        }
//                                    }
//                                }
//                                mDetailFragment.setVisibilitiesButtonForward(mArrayTransferLines.toString());
//                            } else {
//                                mDetailFragment.setVisibilitiesButtonForward("[]");
//                            }
////                arrDetailLimit_1 = arrDetails.subList(0, 4);
////                arrDetailLimit_2 = arrDetails.subList(4, arrDetails.size());
//////                detailRowAdapter_1 = new DetailRowAdapter(DetailForwardActivity.this, arrDetailLimit_1);
//////                detailRowAdapter_2 = new DetailRowAdapter(DetailForwardActivity.this, arrDetailLimit_2);
//////                mDetailFragment.setListDetail2nd(detailRowAdapter_2);
//////                mDetailFragment.setAdapterListDetais(detailRowAdapter_1);
//                            detailAdapter = new DetailRowAdapter(DetailForwardActivity.this, arrDetails);
//                            mDetailFragment.setAdapterListDetais(detailAdapter);
//                            mDetailFragment.showButtonMoreDetails(true);
//                            if (mInforTrainfer.getIsScreen().equals(NOTIFY_SCREEN)) {
//                                mSqLiteNotify.QueryData("DELETE FROM '" + NOTIFY_SQL + "' WHERE objectid = '" + mInforTrainfer.getDocumentID() + "'");
//                            }
//                            break;
//                        case 1:
//                            // read json tree and some information when tap tree item
////                visibleLayoutTryAgain(s);
////                    JSONObject mJsonObjectFullData = new JSONObject(s);
////                    JSONObject mJsonObject = mJsonObjectFullData.getJSONObject(DATA);
//                            //                    String mProcessor = String.valueOf((Html.fromHtml(mJsonTreeForward.getString(PROCESSOR))));
//                            JSONObject mJsonTreeForward = mJsonObject.getJSONObject(TREEJOBCYCLE);
//                            String mProcessor = mJsonTreeForward.getString(PROCESSOR);
//                            String mPosition = mJsonTreeForward.getString(ROLE_NAME);
//                            String mDepartment = mJsonTreeForward.getString(PROCESS_ORG);
//                            String mRequestProcessing = mJsonTreeForward.getString(PROCESS_CONTENT);
//                            String mProcessingDay = mJsonTreeForward.getString(PROCESS_DATE);
//                            String mReceivedDay = mJsonTreeForward.getString(RECEIVE_DATE);
//                            Log.d(TAG, mProcessor);
//                            root = TreeNode.root();
//                            TreeHolder.IconTreeItem nodeItem = new TreeHolder.IconTreeItem(R.drawable.ic_keyboard_arrow_right_black_24dp, mProcessor, mPosition, mDepartment,
//                                    mRequestProcessing, mProcessingDay, mReceivedDay);
//                            Parent = new TreeNode(nodeItem).setViewHolder(new TreeHolder(DetailForwardActivity.this));
//                            root.addChild(Parent);
//                            BrowerJson(mJsonTreeForward, Parent);
//                            AndroidTreeView tView = new AndroidTreeView(DetailForwardActivity.this, root);
////                    tView.setDefaultAnimation(true);
//                            tView.setDefaultContainerStyle(R.style.TreeNodeStyleCustom);
//                            tView.setUse2dScroll(true);
//                            mForwardFragment.SetViewList(tView);
//                            tView.expandAll();
//                            closeProgressDialog();
//                            break;
//                        case 2:
//                            if (!mOther.isNull(INFO_FEED_BACK)) {
//                                JSONArray mArrayFeedBack = mOther.getJSONArray(INFO_FEED_BACK);
//                                for (int i = 0; i < mArrayFeedBack.length(); i++) {
//                                    JSONObject mObject = mArrayFeedBack.getJSONObject(i);
//                                    String content = mObject.getString(SCHEDULE_CONTENT);
//                                    String organizationName = mObject.getString(ORGANIZATION_NAME);
//                                    String userName = mObject.getString(USER_NAME);
//                                    String createDate = mObject.getString(CREATE_DATE);
//                                    JSONArray mArrayAttachFile = mObject.getJSONArray(ATTACH_FILE);
//                                    arrFeedback.add(new FeedBackRow(content, organizationName, userName, createDate, mArrayAttachFile));
//                                }
//                            }
//                            mFeedBackFragment.setAdapterFeedBack(arrFeedback);
//                            closeProgressDialog();
//                            break;
//                        case 3:
//                            if (!mOther.isNull(HANDLE_WAY_CHANGE_LOG)) {
//                                JSONArray mArrayHandleChange = mOther.getJSONArray(HANDLE_WAY_CHANGE_LOG);
//                                writeToFile(mArrayHandleChange.toString(), this, HANDLE_WAY_CHANGE_LOG);
//                            }
//                            // get document connection reciver
//                            if (!mOther.isNull(DOC_RECEIPT_CONNECTS)) {
//                                mArrayReceipt = mOther.getJSONArray(DOC_RECEIPT_CONNECTS);
//                                writeToFile(mArrayReceipt.toString(), this, DOC_RECEIPT_CONNECTS);
//                            }
////                    mOtherFragment.setAdapterDocConnectReceiver(arrDocConnectionReceiver);
//                            // get document connection Send
//                            if (!mOther.isNull(DOC_SEND_CONNECTS)) {
//                                mArraySend = mOther.getJSONArray(DOC_SEND_CONNECTS);
//                                writeToFile(mArraySend.toString(), this, DOC_SEND_CONNECTS);
//                            }
////                    mOtherFragment.setAdapterDocConnectSend(arrDocConnectionSend);
//                            break;
//                        default:
//                            break;
//                    }
//                    break;
//
//            }
//        } catch (JSONException e) {
//            closeProgressDialog();
//            ShowErrorToast(DetailForwardActivity.this);
////                String fileSave = readFromFile(DetailForwardActivity.this, mInforTrainfer.getDocumentID());
////                if (!fileSave.equals(nULL_STRING) && !fileSave.equals("Error!")) {
////                    upDateViewDetail(readFromFile(DetailForwardActivity.this, mInforTrainfer.getDocumentID()));
////                }
//            e.printStackTrace();
//        }
//        closeProgressDialog();
//    }
//    private void initViewPager() {
//        mDetailFragment = new DetailFragment();
//        mForwardFragment = new ForwardFragment();
//        mViewPager = (ViewPager) findViewById(R.id.detail_viewpager);
//        arrFragment = new ArrayList<Fragme1nt>();
//        arrFragment.add(mDetailFragment);
//        arrFragment.add(mForwardFragment);
//        PagerFragmentAdapter adapter = new PagerFragmentAdapter(getSupportFragmentManager(), arrFragment);
//        mViewPager.setAdapter(adapter);
//        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                mTabHost.setCurrentTab(position);
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
//    }
///*
//    Tab host
//*/
//    private void initTabHost(JSONObject mOtherJson) {
//        mTabHost = (TabHost) findViewById(R.id.tab_host);
//        mTabHost.setup();
////        String[] mTabName;
////        int[] mIndicator;
//        ArrayList<String> mTabName = new ArrayList<>();
//        ArrayList<Integer> mIndicator = new ArrayList<>();
//        switch (mInforTrainfer.getIsScreen()) {
//            case TASK_REPORTED:
//            case TASK_PROCESS:
//            case TASK_PROCESS_ON_TIME:
//            case TASK_PROCESS_NEAR_DEMURRAGE:
//            case TASK_PROCESS_DEMURRAGE:
//                mTabName.add(getString(R.string.chi_tiet));
//                mTabName.add(getString(R.string.nhac_nho));
//                mTabName.add(getString(R.string.trao_doi));
//                mTabName.add(getString(R.string.bao_cao));
//                mIndicator.add(R.drawable.ic_event_black_24dp);
//                mIndicator.add(R.drawable.ic_trending_down_black_24dp);
//                mIndicator.add(R.drawable.ic_question_answer_black_24dp);
//                mIndicator.add(R.drawable.ic_speaker_notes_black_24dp);
//                break;
//            default:
//                mTabName.add(getString(R.string.chi_tiet));
//                mTabName.add(getString(R.string.luan_chuyen));
//                mTabName.add(getString(R.string.gop_y));
//                mTabName.add(getString(R.string.khac));
//                mIndicator.add(R.drawable.ic_event_black_24dp);
//                mIndicator.add(R.drawable.ic_clear_all_black_24dp);
//                mIndicator.add(R.drawable.ic_feedback_black_24dp);
//                mIndicator.add(R.drawable.ic_playlist_add_black_24dp);
////                String[] mTabName = {getString(R.string.chi_tiet), getString(R.string.luan_chuyen), getString(R.string.gop_y),
////                        getString(R.string.nhac_nho), getString(R.string.trao_doi), getString(R.string.bao_cao),
////                        getString(R.string.khac)};
////                int[] mIndicator = {R.drawable.ic_event_black_24dp, R.drawable.ic_clear_all_black_24dp,
////                        R.drawable.ic_feedback_black_24dp,
////                        R.drawable.ic_trending_down_black_24dp, R.drawable.ic_question_answer_black_24dp,
////                        R.drawable.ic_speaker_notes_black_24dp, R.drawable.ic_playlist_add_black_24dp};
//                break;
//        }
//
//        for (int i = 0; i < mTabName.size(); i++) {
//            TabHost.TabSpec mTabSpec;
//            mTabSpec = mTabHost.newTabSpec(mTabName.get(i));
//            mTabSpec.setIndicator("", getResources().getDrawable(mIndicator.get(i)));
//            mTabSpec.setContent(new FakeContent(getApplicationContext()));
//            mTabHost.addTab(mTabSpec);
//            mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
//                @Override
//                public void onTabChanged(String tabId) {
//                    int selectedPage = mTabHost.getCurrentTab();
//                    mViewPager.setCurrentItem(selectedPage);
////                    mViewPager.setCurrentItem(selectedPage);
////                    switch (selectedPage) {
////                        case 0:
////                            changeUIDetail();
////                            break;
////                        case 1:
////                            changeUIForward();
////                            break;
////                        case 2:
////                            changeUIFeedBack();
////                            break;
////                        case 3:
////                            changeUIMsgTask();
////                            break;
////                        case 4:
////                            changeUIContentTask();
////                            break;
////                        case 5:
////                            changeUIReportTask();
////                            break;
////                        case 6:
////                            changeUIOther();
////                            break;
////
////                    }
//                }
//            });
//        }
//        String isScreen = mInforTrainfer.getIsScreen();
//        Log.d("VinhCNLog: ", isScreen);
////        switch (mInforTrainfer.getIsScreen()) {
////            case TASK_REPORTED:
////            case TASK_PROCESS:
////            case TASK_PROCESS_ON_TIME:
////            case TASK_PROCESS_NEAR_DEMURRAGE:
////            case TASK_PROCESS_DEMURRAGE:
////                mTabHost.getTabWidget().getChildAt(1).setVisibility(View.GONE);
////                mTabHost.getTabWidget().getChildAt(2).setVisibility(View.GONE);
////                break;
////            default:
////                mTabHost.getTabWidget().getChildAt(1).setVisibility(View.VISIBLE);
////                mTabHost.getTabWidget().getChildAt(2).setVisibility(View.VISIBLE);
////                break;
////        }
////        if (mOtherJson.isNull(INFO_FEED_BACK)) {
////            mTabHost.getTabWidget().getChildAt(2).setVisibility(View.GONE);
////        }
////        if (!mOtherJson.isNull(MESSAGE_TASKS)
////                && !mOtherJson.isNull(CONTENT_TASKS)
////                && !mOtherJson.isNull(REPORTED_TASKS)) {
////            mTabHost.getTabWidget().getChildAt(3).setVisibility(View.VISIBLE);
////            mTabHost.getTabWidget().getChildAt(4).setVisibility(View.VISIBLE);
////            mTabHost.getTabWidget().getChildAt(5).setVisibility(View.VISIBLE);
////            mTabHost.getTabWidget().getChildAt(6).setVisibility(View.GONE);
////        } else {
////            mTabHost.getTabWidget().getChildAt(3).setVisibility(View.GONE);
////            mTabHost.getTabWidget().getChildAt(4).setVisibility(View.GONE);
////            mTabHost.getTabWidget().getChildAt(5).setVisibility(View.GONE);
////            mTabHost.getTabWidget().getChildAt(6).setVisibility(View.VISIBLE);
////        }
//
//    }
//    private void changeUIReportTask() {
//        mReportTaskFragment = new ReportTaskFragment();
//        FragmentTransaction transaction = manager.beginTransaction();
//        transaction.setCustomAnimations(R.animator.enter_anim, R.animator.exit_anim);
//        transaction.replace(R.id.tab_fragment, mReportTaskFragment);
//        transaction.commit();
//    }
//
//
//    private void changeUIDetail() {
//        mDetailFragment = new DetailFragment();
//        FragmentTransaction transaction = manager.beginTransaction();
//        transaction.setCustomAnimations(R.animator.enter_anim, R.animator.exit_anim);
//        transaction.replace(R.id.tab_fragment, mDetailFragment);
//        transaction.commit();
//
//    }
//
//    private void changeUIContentTask() {
//        mContentTaskFragment = new ContentTaskFragment();
//        FragmentTransaction transaction = manager.beginTransaction();
//        transaction.setCustomAnimations(R.animator.enter_anim, R.animator.exit_anim);
//        transaction.replace(R.id.tab_fragment, mContentTaskFragment);
//        transaction.commit();
//
//    }
//
//    private void changeUIForward() {
//        mForwardFragment = new ForwardFragment();
//        FragmentTransaction transaction = manager.beginTransaction();
//        transaction.setCustomAnimations(R.animator.enter_anim, R.animator.exit_anim);
//        transaction.replace(R.id.tab_fragment, mForwardFragment);
//        transaction.commit();
//    }
//
//    private void changeUIOther() {
//        mOtherFragment = new OtherFragment();
//        FragmentTransaction transaction = manager.beginTransaction();
//        transaction.setCustomAnimations(R.animator.enter_anim, R.animator.exit_anim);
//        transaction.replace(R.id.tab_fragment, mOtherFragment);
//        transaction.commit();
//    }
//
//    private void changeUIFeedBack() {
//        mFeedBackFragment = new FeedBackFragment();
//        FragmentTransaction transaction = manager.beginTransaction();
//        transaction.setCustomAnimations(R.animator.enter_anim, R.animator.exit_anim);
//        transaction.replace(R.id.tab_fragment, mFeedBackFragment);
//        transaction.commit();
//    }
//
//    private void changeUIMsgTask() {
//        mMsgTaskFragment = new MessageTaskFragment();
//        FragmentTransaction transaction = manager.beginTransaction();
//        transaction.setCustomAnimations(R.animator.enter_anim, R.animator.exit_anim);
//        transaction.replace(R.id.tab_fragment, mMsgTaskFragment);
//        transaction.commit();
//    }
//    @Override
//    public void initTabHost(JSONObject mJsonObject) {
//        FragmentList = new ArrayList<Fragment>();
//        ScreenViewPagerLoad = new ArrayList<>();
//        mTabHost = (TabHost) findViewById(R.id.tab_host);
//        mTabHost.setup();
//        mTabName = new ArrayList<>();
//        mIndicator = new ArrayList<>();
//        mTabName.add(getString(R.string.chi_tiet));
//        mIndicator.add(R.drawable.ic_event_black_24dp);
//        FragmentList.add(DetailFragment.newInstance("CHI TIẾT"));
//        ScreenViewPagerLoad.add(DETAIL);
////        if (!mJsonObject.isNull(TREEJOBCYCLE)) {
//        if (!mInforTrainfer.getIsScreen().equals(TASK_REPORTED) &&
//                !mInforTrainfer.getIsScreen().equals(TASK_PROCESS) &&
//                !mInforTrainfer.getIsScreen().equals(PROCESS_ON_TIME) &&
//                !mInforTrainfer.getIsScreen().equals(TASK_PROCESS_NEAR_DEMURRAGE) &&
//                !mInforTrainfer.getIsScreen().equals(PROCESS_DEMURRAGE) &&
//                !mInforTrainfer.getIsScreen().equals(TAP_DPM_DELAYS) &&
//                !mInforTrainfer.getIsScreen().equals(TAP_DPM_INDUE) &&
//                !mInforTrainfer.getIsScreen().equals(TAP_DPM_ONTIME) &&
//                !mInforTrainfer.getIsScreen().equals(TAP_DPM_OUT_OF_DATE)) {
//            mTabName.add(getString(R.string.luan_chuyen));
//            mIndicator.add(R.drawable.ic_clear_all_black_24dp);
//            FragmentList.add(ForwardFragment.newInstance("LUÂN CHUYỂN"));
//            ScreenViewPagerLoad.add(FORWARD);
//        }
////            mTabName.add(getString(R.string.luan_chuyen));
////            mIndicator.add(R.drawable.ic_clear_all_black_24dp);
////            FragmentList.add(ForwardFragment.newInstance("LUÂN CHUYỂN"));
////            ScreenViewPagerLoad.add(FORWARD);
////        }
//        try {
//            JSONObject mOther = mJsonObject.getJSONObject(OTHER_INFO);
//            Iterator<String> iter = mOther.keys();
//            while (iter.hasNext()) {
//                String key = iter.next();
////                Object value = mJsonObject.get(key);
//                switch (key) {
//                    case INFO_FEED_BACK:
//                        mTabName.add(getString(R.string.gop_y));
//                        mIndicator.add(R.drawable.ic_feedback_black_24dp);
//                        FragmentList.add(FeedBackFragment.newInstance("GÓP Ý"));
//                        ScreenViewPagerLoad.add(FEEDBACK);
//                        break;
//                    // static fragment
////                    case MESSAGE_TASKS:
////                        mTabName.add(getString(R.string.nhac_nho));
////                        mIndicator.add(R.drawable.ic_trending_down_black_24dp);
////                        FragmentList.add(MessageTaskFragment.newInstance("NHẮC NHỞ"));
////                        break;
////                    case CONTENT_TASKS:
////                        mTabName.add(getString(R.string.trao_doi));
////                        mIndicator.add(R.drawable.ic_question_answer_black_24dp);
////                        FragmentList.add(ContentTaskFragment.newInstance("TRAO ĐỔI"));
////                        break;
////                    case REPORTED_TASKS:
////                        mTabName.add(getString(R.string.bao_cao));
////                        mIndicator.add(R.drawable.ic_speaker_notes_black_24dp);
////                        FragmentList.add(ReportTaskFragment.newInstance("BÁO CÁO"));
////                        break;
//                    default:
//                        break;
//                }
//            }
//            if (!mOther.isNull(HANDLE_WAY_CHANGE_LOG)
//                    || !mOther.isNull(DOC_RECEIPT_CONNECTS)
//                    || !mOther.isNull(DOC_SEND_CONNECTS)
//                    || !mOther.isNull(DOC_LOCAL_CONNECTS)) {
//                mTabName.add(getString(R.string.khac));
//                mIndicator.add(R.drawable.ic_playlist_add_black_24dp);
//                FragmentList.add(OtherFragment.newInstance("KHÁC"));
//                ScreenViewPagerLoad.add(OTHER);
//
//            }
//            if (!mOther.isNull(MESSAGE_TASKS)) {
//                mTabName.add(getString(R.string.nhac_nho));
//                mIndicator.add(R.drawable.ic_trending_down_black_24dp);
//                FragmentList.add(MessageTaskFragment.newInstance("NHẮC NHỞ"));
//                ScreenViewPagerLoad.add(MSGTASK);
//            }
//            if (!mOther.isNull(CONTENT_TASKS)) {
//                mTabName.add(getString(R.string.trao_doi));
//                mIndicator.add(R.drawable.ic_question_answer_black_24dp);
//                FragmentList.add(ContentTaskFragment.newInstance("TRAO ĐỔI"));
//                ScreenViewPagerLoad.add(CONTENTTASK);
//            }
//            if (!mOther.isNull(REPORTED_TASKS)) {
//                mTabName.add(getString(R.string.bao_cao));
//                mIndicator.add(R.drawable.ic_speaker_notes_black_24dp);
//                FragmentList.add(ReportTaskFragment.newInstance("BÁO CÁO"));
//                ScreenViewPagerLoad.add(REPORTTASK);
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        for (int i = 0; i < mTabName.size(); i++) {
//            TabHost.TabSpec mTabSpec;
//            mTabSpec = mTabHost.newTabSpec(mTabName.get(i));
//            mTabSpec.setIndicator("", getResources().getDrawable(mIndicator.get(i)));
//            mTabSpec.setContent(new FakeContent(getApplicationContext()));
//            mTabHost.addTab(mTabSpec);
//            mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
//                @Override
//                public void onTabChanged(String tabId) {
//                    int selectedPage = mTabHost.getCurrentTab();
//                    mViewPager.setCurrentItem(selectedPage);
////                    mViewPager.setCurrentItem(selectedPage);
////                    switch (selectedPage) {
////                        case 0:
////                            changeUIDetail();
////                            break;
////                        case 1:
////                            changeUIForward();
////                            break;
////                        case 2:
////                            changeUIFeedBack();
////                            break;
////                        case 3:
////                            changeUIMsgTask();
////                            break;
////                        case 4:
////                            changeUIContentTask();
////                            break;
////                        case 5:
////                            changeUIReportTask();
////                            break;
////                        case 6:
////                            changeUIOther();
////                            break;
////
////                    }
//                }
//            });
//        }
//        initViewPager(FragmentList);
////        switch (mInforTrainfer.getIsScreen()) {
////            case TASK_REPORTED:
////            case TASK_PROCESS:
////            case TASK_PROCESS_ON_TIME:
////            case TASK_PROCESS_NEAR_DEMURRAGE:
////            case TASK_PROCESS_DEMURRAGE:
////                mTabName.add(getString(R.string.chi_tiet));
////                mTabName.add(getString(R.string.nhac_nho));
////                mTabName.add(getString(R.string.trao_doi));
////                mTabName.add(getString(R.string.bao_cao));
////                mIndicator.add(R.drawable.ic_event_black_24dp);
////                mIndicator.add(R.drawable.ic_trending_down_black_24dp);
////                mIndicator.add(R.drawable.ic_question_answer_black_24dp);
////                mIndicator.add(R.drawable.ic_speaker_notes_black_24dp);
////                break;
////            default:
////                mTabName.add(getString(R.string.chi_tiet));
////                mTabName.add(getString(R.string.luan_chuyen));
////                mTabName.add(getString(R.string.gop_y));
////                mTabName.add(getString(R.string.khac));
////                mIndicator.add(R.drawable.ic_event_black_24dp);
////                mIndicator.add(R.drawable.ic_clear_all_black_24dp);
////                mIndicator.add(R.drawable.ic_feedback_black_24dp);
////                mIndicator.add(R.drawable.ic_playlist_add_black_24dp);
//////                String[] mTabName = {getString(R.string.chi_tiet), getString(R.string.luan_chuyen), getString(R.string.gop_y),
//////                        getString(R.string.nhac_nho), getString(R.string.trao_doi), getString(R.string.bao_cao),
//////                        getString(R.string.khac)};
//////                int[] mIndicator = {R.drawable.ic_event_black_24dp, R.drawable.ic_clear_all_black_24dp,
//////                        R.drawable.ic_feedback_black_24dp,
//////                        R.drawable.ic_trending_down_black_24dp, R.drawable.ic_question_answer_black_24dp,
//////                        R.drawable.ic_speaker_notes_black_24dp, R.drawable.ic_playlist_add_black_24dp};
////                break;
////        }
//    }.06

}
