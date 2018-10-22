package com.newsaigonsoft.neoegov.Action.Extend;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.KeyManager;

import static com.newsaigonsoft.neoegov.R.id.notify_layout;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.STATISTIC_TYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.WORK_FLOW_TRAINSITION_ID;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DIALOG_CENTER;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DOCUMENTID;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LOOKUP_SCREEN;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.RESOURCECODEID;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TITLE_ACTION_BAR;

public class ExtendActivity extends ExtendBase implements ExtendView {

    @BindView(R.id.repulation_day)
    TextView tvChooseDay;
    @BindView(R.id.process_content)
    EditText edtContent;
    @BindView(R.id.number_day_repulations)
    EditText edtNumDay;
    @BindView(R.id.number_notify)
    TextView mNumberOfNotify;
    @BindView(R.id.notify_layout)
    RelativeLayout mNotify_layout;

    @Optional
    @OnClick({R.id.button_forward, R.id.cancel_forward, R.id.repulation_day, R.id.notify_layout})
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.button_forward:
                mExtendLogic.RequestExtexd();
                break;
            case R.id.cancel_forward:
                onBackPressed();
                break;
            case R.id.repulation_day:
                showDataPickDialog(tvChooseDay, edtNumDay, this);
                break;
            case notify_layout:
                showNotiffy(mNotify_layout, this, mNumberOfNotify);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extend);
        ButterKnife.bind(this);
        mExtendLogic = new ExtendLogic(this, this);
        getInforAccountFromShareReferenced(this);
        editAddTextChange();
        getStringIntent();
        initActiobar(titleActionBar, true);
        showNotifiCation(mNumberOfNotify);
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
    public void showProgress() {
        showProgressDialog("", this, DIALOG_CENTER, false);
    }

    @Override
    public String getScreen() {
        return isScreen;
    }

    @Override
    public long getTatistic() {
        return isTatistic;
    }

    @Override
    public long getJobID() {
        return jobID;
    }

    @Override
    public void closeProgress() {
        closeProgressDialog();
    }

    @Override
    public String getResourceCodeId() {
        return resourceCodeId;
    }

    @Override
    public String getProcessDay() {
        return edtNumDay.getText().toString();
    }

    @Override
    public String getExpirationDate() {
        return tvChooseDay.getText().toString();
    }

    @Override
    public String getContent() {
        return edtContent.getText().toString();
    }

    @Override
    public void ToastError(String s) {
        ShowErrorToast(this);
        Log.d(KeyManager.TAG, s);
    }


    private void editAddTextChange() {

        edtNumDay.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String strInput = s.toString();
                if (!strInput.equals("")) {
                    int mNumber = Integer.parseInt(edtNumDay.getText().toString());
                    if (mNumber > 0) {
                        if (mNumber <= 200) {
                            Calendar mCalendar = Calendar.getInstance();
                            mCalendar.add(Calendar.DAY_OF_YEAR, -1);
                            mCalendar.add(Calendar.DAY_OF_YEAR, Integer.parseInt(edtNumDay.getText().toString()));
                            tvChooseDay.setText(mSimpleDateFormat.format(mCalendar.getTime()));
                        } else {
                            edtNumDay.setText("" + 200);
                            Calendar mCalendar = Calendar.getInstance();
                            mCalendar.add(Calendar.DAY_OF_YEAR, -1);
                            mCalendar.add(Calendar.DAY_OF_YEAR, Integer.parseInt(edtNumDay.getText().toString()));
                            tvChooseDay.setText(mSimpleDateFormat.format(mCalendar.getTime()));
                            showDialogUpdateError("Chọn số ngày qui định nhỏ hơn 200", ExtendActivity.this);
                        }
                    } else {
                        showDialogUpdateError("Chọn số ngày qui định lớn hơn 0", ExtendActivity.this);
                        edtNumDay.setText(1 + "");
                        Calendar mCalendar = Calendar.getInstance();
                        mCalendar.add(Calendar.DAY_OF_YEAR, -1);
                        mCalendar.add(Calendar.DAY_OF_YEAR, Integer.parseInt(edtNumDay.getText().toString()));
                        tvChooseDay.setText(mSimpleDateFormat.format(mCalendar.getTime()));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    //    @Override
//    public void onClick(View view) {
//        int id = view.getId();
//        switch (id) {
//            case R.id.button_forward:
//                mExtendLogic.RequestExtexd();
//                break;
//            case R.id.cancel_forward:
//                onBackPressed();
//                break;
//            case R.id.repulation_day:
//                showDataPickDialog(tvChooseDay, edtNumDay, this);
//                break;
//            case notify_layout:
//                showNotiffy(mNotify_layout, this, mNumberOfNotify);
//                break;
//        }
//    }
//    class Extend extends AsyncTask<String, Integer, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            JSONObject mJsonObject = new JSONObject();
//            try {
//                mJsonObject.put(MODULE, PROCESSING_WORKING);
//                mJsonObject.put(NEOTYPE, TYPE_EXTEND);
//                JSONObject mData = new JSONObject();
//                mData.put(JOB_ID, jobID);
//                mData.put(RESOURCE_CODE_ID, resourceCodeId);
//                mData.put(PROCESS_DAYS, edtNumDay.getText().toString());
//                mData.put(EXPIRATION_DATE, getMilisecondDay(tvChooseDay.getText().toString()));
//                mData.put(SCHEDULE_CONTENT, edtContent.getText().toString());
//                mData.put(EXTENSION_TYPE, false);
//                mJsonObject.put(DATA, mData);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
////            return makePostRequest(params[0], mJsonObject.toString(), getUserid(), getPass());
//            return eventPostRequest(getModuleInfor(PROCESSING_WORKING, ExtendActivity.this), mJsonObject.toString(), getUserid(), getPass());
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
//                Intent intent = new Intent(ExtendActivity.this, OfficalActivity.class);
//                intent.putExtra(COME_BACK_FROM_SCREEN, isScreen);
//                intent.putExtra(INPUT_COME_BACK, EXTEND_SCREEN);
//                intent.putExtra(FORWARD_RESUILD, resuilt);
//                intent.putExtra(STATISTIC_TYPE, isTatistic);
//                startActivity(intent);
//                closeProgressDialog();
//            } catch (JSONException e) {
//                ShowErrorToast(ExtendActivity.this);
//                e.printStackTrace();
//            }
//            super.onPostExecute(s);
//        }
//    }
//private void initView(){
    //        btnForward = (TextView) findViewById(R.id.button_forward);
//        btnCancel = (TextView) findViewById(R.id.cancel_forward);
//        edtContent = (EditText) findViewById(R.id.process_content);
//        edtNumDay = (EditText) findViewById(R.id.number_day_repulations);
//        tvChooseDay = (TextView) findViewById(R.id.repulation_day);
//        mNotify_layout = (RelativeLayout) findViewById(notify_layout);
//        mNumberOfNotify = (TextView) findViewById(R.id.number_notify);
//        mNotify_layout.setOnClickListener(this);
//        btnForward.setOnClickListener(this);
//        btnCancel.setOnClickListener(this);
//        tvChooseDay.setOnClickListener(this);
//}
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
}
