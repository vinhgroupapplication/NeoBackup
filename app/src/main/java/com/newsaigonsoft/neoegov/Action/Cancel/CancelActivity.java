package com.newsaigonsoft.neoegov.Action.Cancel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.newsaigonsoft.neoegov.Adapter.CancelListAdapter;
import com.newsaigonsoft.neoegov.MyInterface.AllCheck;
import com.newsaigonsoft.neoegov.OfficalActivity.OfficalActivity;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.CancelListRow;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

import static com.newsaigonsoft.neoegov.R.id.notify_layout;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.STATISTIC_TYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.WORK_FLOW_TRAINSITION_ID;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DIALOG_CENTER;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DOCUMENTID;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LOOKUP_SCREEN;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.RESOURCECODEID;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TITLE_ACTION_BAR;

public class CancelActivity extends BaseCancel implements View.OnClickListener, CancelView, AllCheck {
    @BindView(R.id.cancel_list_view)
    ListView lv;
    @BindView(R.id.cb_select_all)
    CheckBox cbSelectAll;
    @BindView(notify_layout)
    RelativeLayout mNotify_layout;
    @BindView(R.id.number_notify)
    TextView mNumberOfNotify;

    @Optional
    @OnClick({R.id.notify_layout, R.id.save_and_close, R.id.cb_select_all})
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case notify_layout:
                showNotiffy(mNotify_layout, this, mNumberOfNotify);
                break;
            case R.id.save_and_close:
                mCancelLogic.SaveAndClose(workflowTransitionId, resourceCodeId);
                break;
            case R.id.cb_select_all:
                mCancelLogic.selectAll();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel);
        ButterKnife.bind(this);
        mCancelLogic = new CancelLogic(this, this);
        getInforAccountFromShareReferenced(this);
        getStringIntent();
//        initView();
        initActiobar(titleActionBar, true);
        showNotifiCation(mNumberOfNotify);
        mCancelLogic.RequestCancelList(jobID);

    }

    private void getStringIntent() {
        isScreen = getIntent().getStringExtra(LOOKUP_SCREEN);
        isTatistic = getIntent().getIntExtra(STATISTIC_TYPE, 0);
        jobID = getIntent().getLongExtra(DOCUMENTID, 0);
        workflowTransitionId = getIntent().getExtras().getInt(WORK_FLOW_TRAINSITION_ID);
        resourceCodeId = getIntent().getStringExtra(RESOURCECODEID);
        titleActionBar = getIntent().getStringExtra(TITLE_ACTION_BAR);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void setCancelList(CancelListAdapter adapter) {
        lv.setAdapter(adapter);
    }

    @Override
    public void startIntent(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void closeProgress() {
        closeProgressDialog();
    }

    @Override
    public void DeteleData() {
        if (null != urlNA)
            mSqLiteListDocument.QueryData("DELETE FROM '" + "M" + getOnlyNumerics(OfficalActivity.urlNA) + "' WHERE jobID = '" + jobID + "'");
    }

    @Override
    public boolean isSelectAllCheck() {
        return cbSelectAll.isChecked();
    }

    @Override
    public void showProgress() {
        showProgressDialog("", this, DIALOG_CENTER, true);
    }

    @Override
    public String ScreenInSide() {
        return isScreen;
    }

    @Override
    public int isTatistic() {
        return isTatistic;
    }

    @Override
    public void ShowToastCancel(String s) {
        ShowErrorToast(this, s);
    }

    @Override
    public void setAdapterCancelList(ArrayList<CancelListRow> arrCancelList) {
        adapter = new CancelListAdapter(this, arrCancelList, this);
        setCancelList(adapter);
    }

    @Override
    public CancelListAdapter getAdapterView() {
        return adapter;
    }

    @Override
    public void ToastError(String s) {
        closeProgress();
        ShowErrorToast(this);

    }

    @Override
    public void CheckAllButton(boolean check) {
        cbSelectAll.setChecked(check);
    }

    @Override
    public long getJobID() {
        return jobID;
    }

    @Override
    public void AllCheck(boolean Check) {
        cbSelectAll.setChecked(Check);
    }

    public void setCheckAllList() {
        mCancelLogic.setSelectAll();
    }
//    private void initActiobar() {
//        mToolbar = (Toolbar) findViewById(R.id.app_bar);
//        setSupportActionBar(mToolbar);
//        getSupportActionBar().setTitle(nULL_STRING);
//        TextView titleActionbar = (TextView) findViewById(R.id.title_actionbar);
//        titleActionbar.setText(titleActionBar.toUpperCase());
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });
//        mToolbar.setPadding(0, getStatusBarHeight(this), 0, 0);
//        btnSettingActionbar = (RelativeLayout) findViewById(R.id.setting_layout);
//        btnSettingActionbar.setVisibility(View.GONE);
//    }
//    private void initView() {
//        tvSaveAndClose = (TextView) findViewById(R.id.save_and_close);
//        lv = (ListView) findViewById(R.id.cancel_list_view);
//        cbSelectAll = (CheckBox) findViewById(R.id.cb_select_all);
//        mNotify_layout = (RelativeLayout) findViewById(notify_layout);
//        mNumberOfNotify = (TextView) findViewById(R.id.number_notify);
//        mNotify_layout.setOnClickListener(this);
//        cbSelectAll.setOnClickListener(this);
//        tvSaveAndClose.setOnClickListener(this);
//    }
//
//    @Override
//    public void onClick(View view) {
//        int id = view.getId();
//        switch (id) {
//            case notify_layout:
//                showNotiffy(mNotify_layout, this, mNumberOfNotify);
//                break;
//            case R.id.save_and_close:
//                mCancelLogic.SaveAndClose(workflowTransitionId, resourceCodeId);
//                break;
//            case R.id.cb_select_all:
//                mCancelLogic.selectAll();
//                break;
//        }
//    }
}
