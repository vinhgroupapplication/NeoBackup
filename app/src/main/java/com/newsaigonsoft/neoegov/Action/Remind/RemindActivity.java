package com.newsaigonsoft.neoegov.Action.Remind;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import com.newsaigonsoft.neoegov.Adapter.RemindAdapter;
import com.newsaigonsoft.neoegov.CustomViewListExpand.NonScrollListView;
import com.newsaigonsoft.neoegov.R;

import static com.newsaigonsoft.neoegov.R.id.notify_layout;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.EXECUTION_UNIT;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.STATISTIC_TYPE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DIALOG_CENTER;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DOCUMENTID;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LOOKUP_SCREEN;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.PROCESS_POSITION;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TITLE_ACTION_BAR;

public class RemindActivity extends RemindBase implements RemindView {

    @BindView(R.id.remind_list)
    NonScrollListView lv;
    @BindView(R.id.number_notify)
    TextView mNumberOfNotify;
    @BindView(notify_layout)
    RelativeLayout mNotify_layout;
    @BindView(R.id.save_and_close)
    TextView tvSaveAndClose;
    @BindView(R.id.remind_content_sms)
    EditText edtSmsContent;
    @BindView(R.id.remind_mail_title)
    EditText edtTitleEmail;
    @BindView(R.id.remind_mail_content)
    EditText edtMailContent;

    @Optional
    @OnClick({notify_layout, R.id.save_and_close})
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case notify_layout:
                showNotiffy(mNotify_layout, this, mNumberOfNotify);
                break;
            case R.id.save_and_close:
                mRemindLogic.SaveAndClose();
                break;
        }
    }


    @Override
    public void startIntent(Intent intent) {
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remind);
        ButterKnife.bind(this);
        getInforAccountFromShareReferenced(this);
        getStringIntent();
        initActiobar(titleActionBar, true);
        initView();
        showNotifiCation(mNumberOfNotify);
        mRemindLogic = new RemindLogic(this, this);
        mRemindLogic.getListRemind(JsonExecutionUnit);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    private void initView() {
        mNotify_layout.setVisibility(View.VISIBLE);
    }

    private void getStringIntent() {
        titleActionBar = getIntent().getStringExtra(TITLE_ACTION_BAR);
        ProcessPosition = getIntent().getStringExtra(PROCESS_POSITION);
        TaskID = getIntent().getLongExtra(DOCUMENTID, 0);
        JsonExecutionUnit = getIntent().getStringExtra(EXECUTION_UNIT);
        isScreen = getIntent().getStringExtra(LOOKUP_SCREEN);
        isTatistic = getIntent().getIntExtra(STATISTIC_TYPE, 0);
    }

    @Override
    public void setLvReturn(RemindAdapter adapter) {
        lv.setAdapter(adapter);
    }

    @Override
    public String getEmailContent() {
        return edtMailContent.getText().toString();
    }

    @Override
    public String getSmsContent() {
        return edtSmsContent.getText().toString();
    }

    @Override
    public String getTitleEmail() {
        return edtTitleEmail.getText().toString();
    }

    @Override
    public void showToastError(String s) {
        ShowErrorToast(this, s);
    }

    @Override
    public void showProgress() {
        showProgressDialog("", RemindActivity.this, DIALOG_CENTER, true);
    }

    @Override
    public long getTaskID() {
        return TaskID;
    }

    @Override
    public void ShowErrorToasts() {
        ShowErrorToast(this);
    }

    public String getIsScreen() {
        return isScreen;
    }

    public int getIsTatistic() {
        return isTatistic;
    }

    @Override
    public void closeProgress() {
        closeProgressDialog();
    }

    @Override
    public void startIntentOnbackPress(Intent intent) {
        setResult(RESULT_OK, intent);
        finish();
    }
//    private void initBroadcast() {
//        IntentFilter filter = new IntentFilter(BROADCASTLISTENNER);
//        this.registerReceiver(mBroadcastReceiver, filter);
//    }
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
//class RemindForward extends AsyncTask<String, Integer, String> {
//        @Override
//        protected String doInBackground(String... params) {
//            JSONObject mJsonObject = new JSONObject();
//            try {
//                mJsonObject.put(MODULE, MODULE_LOOKUP_DOCUMENT);
//                mJsonObject.put(NEOTYPE, TYPE_REMIND);
//                JSONObject mData = new JSONObject();
//                mData.put(TASK_ID, TaskID);
//                mData.put(CONTENT_SMS, edtSmsContent.getText().toString());
//                mData.put(SUBJECT_EMAIL, edtTitleEmail.getText().toString());
//                mData.put(CONTENT_EMAIL, edtMailContent.getText().toString());
//                mData.put(EXECUTION_UNIT, mJsonArrayExecutionUnit);
//                mJsonObject.put(DATA, mData);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
////            return makePostRequest(params[0], mJsonObject.toString(), getUserid(), getPass());
//            return eventPostRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, RemindActivity.this), mJsonObject.toString(), getUserid(), getPass());
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
//                int isTatistic = getIntent().getIntExtra(STATISTIC_TYPE, 0);
//                Intent intent = new Intent(RemindActivity.this, OfficalActivity.class);
//                //// TODO: 9/1/2017  comback screen
//                intent.putExtra(COME_BACK_FROM_SCREEN, isScreen);
//                intent.putExtra(INPUT_COME_BACK, REMIND);
//                //// TODO: 9/1/2017  comback screen
//                intent.putExtra(FORWARD_RESUILD, resuilt);
//                intent.putExtra(STATISTIC_TYPE, isTatistic);
//                startActivity(intent);
//            } catch (JSONException e) {
//                ShowErrorToast(RemindActivity.this);
//                e.printStackTrace();
//            }
//            super.onPostExecute(s);
//        }
//    }
    //    private void getListExExecutionUnit(String JsonExecutionUnit) {
//        arrRemind = new ArrayList<RemindRow>();
//        try {
//            JSONArray mArrayExecutionUnit = new JSONArray(JsonExecutionUnit);
//            for (int i = 0; i < mArrayExecutionUnit.length(); i++) {
//                JSONObject mJsonObject = mArrayExecutionUnit.getJSONObject(i);
//                String content = mJsonObject.getString(SCHEDULE_CONTENT);
//                String phoneNumber = mJsonObject.getString(PHONE_NUMBER);
//                String email = mJsonObject.getString(TRAINFER_EMAIL);
//                String name = mJsonObject.getString(NAME);
//                arrRemind.add(new RemindRow(true, true, content, name, phoneNumber, email));
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private String addJsonRequestRemind(){
//        JSONObject mJsonObject = new JSONObject();
//        try {
//            mJsonObject.put(MODULE, MODULE_LOOKUP_DOCUMENT);
//            mJsonObject.put(NEOTYPE, TYPE_REMIND);
//            JSONObject mData = new JSONObject();
//            mData.put(TASK_ID, TaskID);
//            mData.put(CONTENT_SMS, edtSmsContent.getText().toString());
//            mData.put(SUBJECT_EMAIL, edtTitleEmail.getText().toString());
//            mData.put(CONTENT_EMAIL, edtMailContent.getText().toString());
//            mData.put(EXECUTION_UNIT, mJsonArrayExecutionUnit);
//            mJsonObject.put(DATA, mData);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return mJsonObject.toString();
//    }
//
//    @Override
//    public void onTaskCompleted(String s, String CaseRequest) {
//        String resuilt = "";
//        try {
//            JSONObject mJsonObject = new JSONObject(s);
//            int Response = mJsonObject.getInt(CODE);
//            resuilt = Response == 0 ? TRUE : FALSE;
//            String isScreen = getIntent().getStringExtra(LOOKUP_SCREEN);
//            int isTatistic = getIntent().getIntExtra(STATISTIC_TYPE, 0);
//            Intent intent = new Intent(RemindActivity.this, OfficalActivity.class);
//            //// TODO: 9/1/2017  comback screen
//            intent.putExtra(COME_BACK_FROM_SCREEN, isScreen);
//            intent.putExtra(INPUT_COME_BACK, REMIND);
//            //// TODO: 9/1/2017  comback screen
//            intent.putExtra(FORWARD_RESUILD, resuilt);
//            intent.putExtra(STATISTIC_TYPE, isTatistic);
//            startActivity(intent);
//        } catch (JSONException e) {
//            ShowErrorToast(RemindActivity.this);
//            e.printStackTrace();
//        }
//    }
//
//
    //    private void requestRemindForward() {
//        showProgressDialog("", RemindActivity.this, DIALOG_CENTER, true);
//        mJsonArrayExecutionUnit = new JSONArray();
//        for (int i = 0; i < arrRemind.size(); i++) {
//            JSONObject mJsonObject = new JSONObject();
//            try {
//                mJsonObject.put(NAME, arrRemind.get(i).getProcessPosition());
//                mJsonObject.put(SCHEDULE_CONTENT, arrRemind.get(i).getContent());
//                if (arrRemind.get(i).isSms()) {
//                    mJsonObject.put(PHONE_NUMBER, arrRemind.get(i).getNumber());
//                }
//                if (arrRemind.get(i).isEmail()) {
//                    mJsonObject.put(TRAINFER_EMAIL, arrRemind.get(i).getEmailName());
//                }
//                mJsonArrayExecutionUnit.put(mJsonObject);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//        new NeoTask(this, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(this, ACTION_REMIND, addJsonRequestRemind(), getLink() + URL_CENTER_6_1));
////        new RemindForward().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, getLink() + URL_CENTER_6_1);
//    }
    //    private void initView() {
//        lv = (NonScrollListView) findViewById(R.id.remind_list);
//        mNotify_layout = (RelativeLayout) findViewById(notify_layout);
//        mNumberOfNotify = (TextView) findViewById(R.id.number_notify);
//        tvSaveAndClose = (TextView) findViewById(R.id.save_and_close);
//        edtSmsContent = (EditText) findViewById(R.id.remind_content_sms);
//        edtTitleEmail = (EditText) findViewById(R.id.remind_mail_title);
//        edtMailContent = (EditText) findViewById(R.id.remind_mail_content);
//        tvSaveAndClose.setOnClickListener(this);
//        mNotify_layout.setOnClickListener(this);
//        mNotify_layout.setVisibility(View.VISIBLE);
//    }
}
