package com.newsaigonsoft.neoegov.Action.ConfirmConpleted;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.newsaigonsoft.neoegov.Action.ConfirmConpleted.decorators.EventDecorator;
import com.newsaigonsoft.neoegov.OtherActivity.OtherActivity;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.KeyManager;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.format.ArrayWeekDayFormatter;
import com.prolificinteractive.materialcalendarview.format.MonthArrayTitleFormatter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.logging.SimpleFormatter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.STATISTIC_TYPE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DEPARTMENT_POSITION;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DIALOG_CENTER;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DOCUMENTID;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LOOKUP_SCREEN;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TITLE_ACTION_BAR;

public class ConfirmCompletedActivity extends ConfirmCompletedBase implements ConfirmConpletedView {
    @BindView(R.id.confirm_content)
    EditText edtConfirmContent;
    @BindView(R.id.comfirm_day)
    TextView edtConfirmDay;
    @BindView(R.id.department_position)
    TextView tvDepartmentPosition;
    @BindView(R.id.number_notify)
    TextView mNumberOfNotify;
    @BindView(R.id.notify_layout)
    RelativeLayout mNotify_layout;


    @Optional
    @OnClick({R.id.notify_layout, R.id.save_and_close, R.id.comfirm_day})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.notify_layout:
                showNotiffy(mNotify_layout, this, mNumberOfNotify);
                break;
            case R.id.save_and_close:
                mConfirmConpletedLogic.RequestConfirm();
                break;
            case R.id.comfirm_day:
//                showDataPickDialogComfirm(edtConfirmDay, ConfirmCompletedActivity.this);
                showTimeDialog(edtConfirmDay, ConfirmCompletedActivity.this);
                break;
        }
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_completed);
        ButterKnife.bind(this);
        mConfirmConpletedLogic = new ConfirmConpletedLogic(this, this);
        mNotify_layout.setVisibility(View.VISIBLE);
        getStringIntent();
        initActiobar(titleActionBar, true);
        showNotifiCation(mNumberOfNotify);
        getInforAccountFromShareReferenced(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void getStringIntent() {
        titleActionBar = getIntent().getStringExtra(TITLE_ACTION_BAR);
        taskID = getIntent().getLongExtra(DOCUMENTID, 0);
        DepartmentName = getIntent().getStringExtra(DEPARTMENT_POSITION);
        tvDepartmentPosition.setText(Html.fromHtml(DepartmentName.toUpperCase()));
        isScreen = getIntent().getStringExtra(LOOKUP_SCREEN);
        isTatistic = getIntent().getIntExtra(STATISTIC_TYPE, 0);
    }

    @Override
    public String getConfirmDay() {
        return edtConfirmDay.getText().toString();
    }

    @Override
    public long getTaskID() {
        return taskID;
    }

    @Override
    public void showProgress() {
        showProgressDialog("", this, DIALOG_CENTER, true);
    }

    @Override
    public String getConfirmContent() {
        return edtConfirmContent.getText().toString();
    }

    @Override
    public void closeProgress() {
        closeProgressDialog();
    }

    @Override
    public int getTatistic() {
        return isTatistic;
    }

    @Override
    public String getScreen() {
        return isScreen;
    }

    @Override
    public void startIntent(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void startIntentOnbackPress(Intent intent) {
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void ToastError(String s) {
        ShowErrorToast(this);
        Log.d(KeyManager.TAG, s);
    }
//    private void initView() {
//        mNumberOfNotify = (TextView) findViewById(R.id.number_notify);
//        mNotify_layout = (RelativeLayout) findViewById(R.id.notify_layout);
//        edtConfirmDay = (TextView) findViewById(R.id.comfirm_day);
//        btnSaveAndClose = (Button) findViewById(R.id.save_and_close);
//        edtConfirmContent = (EditText) findViewById(R.id.confirm_content);
//        tvDepartmentPosition = (TextView) findViewById(R.id.department_position);
//        mNotify_layout.setVisibility(View.VISIBLE);
//        mNotify_layout.setOnClickListener(this);
//        btnSaveAndClose.setOnClickListener(this);
//        edtConfirmDay.setOnClickListener(this);
//    }
//    private void initBroadcast() {
//        IntentFilter filter = new IntentFilter(BROADCASTLISTENNER);
//        this.registerReceiver(mBroadcastReceiver, filter);
//    }


//    class ComfirmCompleted extends AsyncTask<String, Integer, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            JSONObject mJsonObject = new JSONObject();
//            try {
//                mJsonObject.put(MODULE, MODULE_LOOKUP_DOCUMENT);
//                mJsonObject.put(NEOTYPE, CONFIRM_COMPLETED);
//                JSONObject mData = new JSONObject();
//                mData.put(TASK_ID, taskID);
//                mData.put(DATE_REPORT, getMilisecondDay(edtConfirmDay.getText().toString()));
//                mData.put(CONTENT_REPORT, edtConfirmContent.getText().toString());
//                mJsonObject.put(DATA, mData);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
////            return makePostRequest(params[0],mJsonObject.toString(), getUserid(), getPass());
//            return eventPostRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, ConfirmCompletedActivity.this), mJsonObject.toString(), getUserid(), getPass());
//
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
//                Intent intent  = new Intent(ConfirmCompletedActivity.this, OfficalActivity.class);
//                intent.putExtra(COME_BACK_FROM_SCREEN, isScreen);
//                intent.putExtra(INPUT_COME_BACK, CONFIRM_COMPLETED);
//                intent.putExtra(FORWARD_RESUILD, resuilt);
//                intent.putExtra(STATISTIC_TYPE,isTatistic );
//                startActivity(intent);
//            } catch (JSONException e) {
//                ShowErrorToast(ConfirmCompletedActivity.this);
//                e.printStackTrace();
//            }
//            super.onPostExecute(s);
//        }
//    }
//
//    @Override
//    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.notify_layout:
//                showNotiffy(mNotify_layout, this, mNumberOfNotify);
//                break;
//            case R.id.save_and_close:
//                mConfirmConpletedLogic.RequestConfirm();
//                break;
//            case R.id.comfirm_day:
//                showDataPickDialogComfirm(edtConfirmDay, ConfirmCompletedActivity.this);
//                break;
//        }
//
//    }
//
//
//
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
