package com.newsaigonsoft.neoegov.Action.ChangeHandle;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.newsaigonsoft.neoegov.Adapter.ChangeHandlerAdapter;
import com.newsaigonsoft.neoegov.CustomViewListExpand.NonScrollListView;
import com.newsaigonsoft.neoegov.OfficalActivity.OfficalActivity;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.ChangeHandleRow;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.Optional;

import static com.newsaigonsoft.neoegov.R.id.notify_layout;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.STATISTIC_TYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.WORK_FLOW_TRAINSITION_ID;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DIALOG_CENTER;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DOCUMENTID;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LOOKUP_SCREEN;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.RESOURCECODEID;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TITLE_ACTION_BAR;

public class InputChangeHandleActivity extends BaseChangeHandle implements ChangeHandleView {
    @BindView(R.id.list_change_handle)
    NonScrollListView lv;
    @BindView(notify_layout)
    RelativeLayout mNotify_layout;
    @BindView(R.id.number_notify)
    TextView mNumberOfNotify;
    @BindView(R.id.approve_day)
    EditText edtContent;

    @Optional
    @OnClick({R.id.notify_layout, R.id.button_forward, R.id.cancel_forward})
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case notify_layout:
                showNotiffy(mNotify_layout, this, mNumberOfNotify);
                break;
            case R.id.button_forward:
                mChangeHandleLogic.RequestChangeHandle();
                break;
            case R.id.cancel_forward:
                onBackPressed();
                break;
        }
    }

    @OnItemClick(R.id.list_change_handle)
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mChangeHandleLogic.itemClick(position);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_change_handle);
        ButterKnife.bind(this);
        getInforAccountFromShareReferenced(this);
        mChangeHandleLogic = new ChangeHandleLogic(this, this);
        getStringIntent();
//        initView();
        initActiobar(titleActionBar, true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.close_white_x1);
        showNotifiCation(mNumberOfNotify);
        mChangeHandleLogic.requestHandleProcess();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.menu_notify).setVisible(false);
        menu.findItem(R.id.search_button).setVisible(false);
        menu.findItem(R.id.menu_infor).setVisible(false);
//        menu.findItem(R.id.menu_help).setVisible(false);
//        menu.findItem(R.id.menu_config).setVisible(false);
        menu.findItem(R.id.center_menu).setVisible(false);
        return true;
    }

    private void getStringIntent() {
        DocumentID = getIntent().getLongExtra(DOCUMENTID, 0);
        workflowTransitionId = getIntent().getExtras().getInt(WORK_FLOW_TRAINSITION_ID);
        resourceCodeId = getIntent().getStringExtra(RESOURCECODEID);
        titleActionBar = getIntent().getStringExtra(TITLE_ACTION_BAR);
        isScreen = getIntent().getStringExtra(LOOKUP_SCREEN);
        isTatistic = getIntent().getIntExtra(STATISTIC_TYPE, 0);

    }


    @Override
    public long getDocumentID() {
        return DocumentID;
    }

    @Override
    public void setListViewHandleProcess(ChangeHandlerAdapter adapterHandleProcess, ArrayList<ChangeHandleRow> mArrayHandleProcess) {
        lv.setAdapter(adapterHandleProcess);
        arrayHandleProcess = mArrayHandleProcess;
    }

    @Override
    public void showProgress() {
        showProgressDialog("", this, DIALOG_CENTER, true);
    }

    @Override
    public void closeProgress() {
        closeProgressDialog();
    }

    @Override
    public int getWorkflowTransitionId() {
        return workflowTransitionId;
    }

    @Override
    public String getRespirceCodeID() {
        return resourceCodeId;
    }

    @Override
    public String getContent() {
        return edtContent.getText().toString();
    }



    @Override
    public void DeletedDatabse() {
        mSqLiteListDocument.QueryData("DELETE FROM '" + "M" + getOnlyNumerics(OfficalActivity.urlNA) + "' WHERE documentID = '" + DocumentID + "'");
    }

    @Override
    public String getIsScreen() {
        return isScreen;
    }

    @Override
    public int getIsTatistic() {
        return isTatistic;
    }

    @Override
    public void ShowToast(String s) {
        ShowErrorToast( this,s);
    }

    @Override
    public void ShowToastError(String s) {
        ShowErrorToast(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

//    class ChangHandle extends AsyncTask<String, Integer, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            JSONObject mJsonObject = new JSONObject();
//            try {
//                mJsonObject.put(MODULE, PROCESSING_WORKING);
//                mJsonObject.put(NEOTYPE, TYPE_CHANGE_HANDLE);
//                JSONObject mData = new JSONObject();
//                mData.put(JOB_ID, DocumentID);
//                mData.put(WORK_FLOW_TRAINSITION_ID, workflowTransitionId);
//                mData.put(RESOURCE_CODE_ID, resourceCodeId);
//                mData.put(SCHEDULE_CONTENT, edtContent.getText().toString());
//                mData.put(PROCESS_TYPE_ID, processTypeId);
//                mJsonObject.put(DATA, mData);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
////            return makePostRequest(params[0], mJsonObject.toString(), getUserid(), getPass());
//            return eventPostRequest(getModuleInfor(PROCESSING_WORKING, InputChangeHandleActivity.this), mJsonObject.toString(), getUserid(), getPass());
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            String resuilt = "";
//            try {
//                JSONObject mJsonObject = new JSONObject(s);
//                int Response = mJsonObject.getInt(CODE);
//                resuilt = Response == 0 ? TRUE : FALSE;
//                String isScreen = getIntent().getStringExtra(LOOKUP_SCREEN);
//                int isTatistic = getIntent().getIntExtra(STATISTIC_TYPE,0);
//                Intent intent  = new Intent(InputChangeHandleActivity.this, OfficalActivity.class);
//                intent.putExtra(COME_BACK_FROM_SCREEN, isScreen);
//                intent.putExtra(INPUT_COME_BACK, CHANGE_HANDLE);
//                intent.putExtra(FORWARD_RESUILD, resuilt);
//                intent.putExtra(STATISTIC_TYPE,isTatistic );
//                if (resuilt.equals(TRUE)) {
//                    mSqLiteListDocument.QueryData("DELETE FROM '" + "M" + getOnlyNumerics(OfficalActivity.urlNA) + "' WHERE documentID = '" + DocumentID + "'");
//                }
//                startActivity(intent);
//                closeProgressDialog();
//            } catch (JSONException e) {
//                ShowErrorToast(InputChangeHandleActivity.this);
//                e.printStackTrace();
//            }
//            super.onPostExecute(s);
//        }
//    }

//    class ReadJsonHandleProcess extends AsyncTask<String, Integer, String> {
//        @Override
//        protected String doInBackground(String... params) {
//            return readFromFile(InputChangeHandleActivity.this, "handle" + DocumentID);
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            arrayHandleProcess = new ArrayList<ChangeHandleRow>();
//            try {
//                JSONObject mJsonObject = new JSONObject(s);
//                JSONArray mJsonArray = mJsonObject.getJSONArray(DATA);
//                for (int i = 0; i < mJsonArray.length(); i++) {
//                    JSONObject mJsonObjectData = mJsonArray.getJSONObject(i);
//                    long id = mJsonObjectData.getLong(ID);
//                    String name = mJsonObjectData.getString(NAME);
//                    arrayHandleProcess.add(new ChangeHandleRow(false, name, id));
//                }
//                adapterHandleProcess = new ChangeHandlerAdapter(InputChangeHandleActivity.this, arrayHandleProcess);
//                lv.setAdapter(adapterHandleProcess);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            super.onPostExecute(s);
//        }
//    }


//    private void initActiobar() {
//        mToolbar = (Toolbar) findViewById(R.id.app_bar);
//        setSupportActionBar(mToolbar);
//        getSupportActionBar().setTitle(nULL_STRING);
//        TextView titleActionbar = (TextView) findViewById(R.id.title_actionbar);
//        titleActionbar.setText(titleActionBar.toUpperCase());
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        mToolbar.setPadding(0, getStatusBarHeight(this), 0, 0);
//        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });
//        btnSettingActionbar = (RelativeLayout) findViewById(R.id.setting_layout);
//        btnSettingActionbar.setVisibility(View.GONE);
//    }
    //    private void initView() {
//        lv = (NonScrollListView) findViewById(R.id.list_change_handle);
//        mNotify_layout = (RelativeLayout) findViewById(notify_layout);
//        mNumberOfNotify = (TextView) findViewById(R.id.number_notify);
//        mNotify_layout.setOnClickListener(this);
//        tvSave = (TextView) findViewById(R.id.button_forward);
//        tvCancel = (TextView) findViewById(R.id.cancel_forward);
//        edtContent = (EditText) findViewById(R.id.approve_day);
//        tvSave.setOnClickListener(this);
//        tvCancel.setOnClickListener(this);
//        tvCancel.setOnClickListener(this);
//    }
//
//    @Override
//    public void onClick(View view) {
//        int id = view.getId();
//        switch (id) {
//            case notify_layout:
//                showNotiffy(mNotify_layout, this, mNumberOfNotify);
//                break;
//            case R.id.button_forward:
//                mChangeHandleLogic.RequestChangeHandle();
//                break;
//            case R.id.cancel_forward:
//                onBackPressed();
//                break;
//        }
//    }

}
