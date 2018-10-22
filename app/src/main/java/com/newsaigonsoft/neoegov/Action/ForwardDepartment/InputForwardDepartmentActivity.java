package com.newsaigonsoft.neoegov.Action.ForwardDepartment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.newsaigonsoft.neoegov.Adapter.ChangeHandlerAdapter;
import com.newsaigonsoft.neoegov.Adapter.FileAttachAdapter;
import com.newsaigonsoft.neoegov.Adapter.InputForwardDepartmentAdapter;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.AttachFile;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

import static com.newsaigonsoft.neoegov.R.id.notify_layout;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.WORK_FLOW_TRAINSITION_ID;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.CREATE_TABLE_SENDWAITING_DEPARTMENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DIALOG_CENTER;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DOCUMENTID;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DOCUMENT_NUMBER;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.INSERT_TABLE_SEND_WAITING_DEPARTMENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LOOKUP_SCREEN;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.RESOURCECODEID;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SHOW_CHOOSE_DUE_DATE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SHOW_CHOOSE_HANDLE_WAY;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAG;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TITLE_ACTION_BAR;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.nULL_STRING;

public class InputForwardDepartmentActivity extends FWDepartmentBase implements FWDepartmentView {

    HeaderViewHolder headerViewHolder;
    FooterViewHolder footerViewHolder;
    @BindView(R.id.lv_process_person)
    ListView lv;
    @BindView(notify_layout)
    RelativeLayout mNotify_layout;
    @BindView(R.id.number_notify)
    TextView mNumberOfNotify;
    @BindView(R.id.button_forward)
    TextView tvForward;


    @Optional
    @OnClick({notify_layout, R.id.button_forward, R.id.cancel_forward})
    public void OnCLick(View view) {
        switch (view.getId()) {
            case notify_layout:
                showNotiffy(mNotify_layout, this, mNumberOfNotify);
                break;
            case R.id.button_forward:
                mFwDepartmentLogic.RequestForward();
                break;
            case R.id.cancel_forward:
                onBackPressed();
                break;
            default:
                break;
        }
    }

    public void onHeaderClicked(View view) {
        if (clicked) {
            return;
        }
        clicked = true;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                footerViewHolder.cbRegulationDeadline.setClickable(true);
                headerViewHolder.cbCheckAllDepartment.setClickable(true);
                footerViewHolder.edtProcessDays.setClickable(true);
                clicked = false;
            }
        }, 500);
//        long now = System.currentTimeMillis();
        switch (view.getId()) {
            case R.id.check_all:
                headerViewHolder.cbCheckAllDepartment.setClickable(false);
                mFwDepartmentLogic.CheckallDepartment(headerViewHolder.cbCheckAllDepartment);
                break;
            case R.id.choose_file:
                showFileChooser();
                break;
            case R.id.repulation_day:
                footerViewHolder.cbRegulationDeadline.setClickable(false);
                footerViewHolder.edtProcessDays.setClickable(false);
//                if (now - mLastClickTime < 500) {
//                    return;
//                }
//                mLastClickTime = now;
                if (!footerViewHolder.cbRegulationDeadline.isChecked()) {
                    showDataPickDialog(footerViewHolder.edtExpirationDate, footerViewHolder.edtProcessDays, this);
                }
                footerViewHolder.edtProcessDays.clearFocus();
                break;
            case R.id.regulations_deadline:
                footerViewHolder.cbRegulationDeadline.setClickable(false);
                if (footerViewHolder.cbRegulationDeadline.isChecked()) {
                    footerViewHolder.edtProcessDays.setText("");
                    footerViewHolder.edtExpirationDate.setText("");
                    footerViewHolder.edtProcessDays.setFocusable(false);
//                    edtExpirationDate.setFocusable(false);
                    showSoftwareKeyboard(false, this);
                } else {
                    footerViewHolder.edtProcessDays.setFocusableInTouchMode(true);
                    footerViewHolder.edtProcessDays.setFocusable(true);
//                    edtExpirationDate.setFocusableInTouchMode(true);
//                    edtExpirationDate.setFocusable(true);
                }
                break;
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_forward_department);
        getStringIntent();
        initView();
        initActiobar(titleActionbar, false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.close_white_x1);
        headerViewHolder.tvProcessDay.setText(getTimeNow());
        getInforAccountFromShareReferenced(this);
        mSqLiteSendWaitingDepartment.QueryData(CREATE_TABLE_SENDWAITING_DEPARTMENT);
        showNotifiCation(mNumberOfNotify);
        mFwDepartmentLogic = new FWDepartmentLogic(this, this);
        arrFileAttach = new ArrayList<>();
        mFwDepartmentLogic.ReadJsonHandleAndDepartment();
        footerViewHolder.lvHandle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mFwDepartmentLogic.OnClickHandle(position);
            }
        });

    }


    public void setAllChecking() {
        mFwDepartmentLogic.setAllCheck();
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case FILE_SELECT_CODE:
                if (resultCode == RESULT_OK) {
                    // Get the Uri of the selected file
                    Uri uri = data.getData();
                    Log.d(TAG, "File Uri: " + uri.toString());
//                    ContentResolver cR = this.getContentResolver();
//                    MimeTypeMap mime = MimeTypeMap.getSingleton();
//                    String type = mime.getExtensionFromMimeType(cR.getType(uri));
                    // Get the path
//                    String path = uri.getPath();
//                    String realPath = getRealPathFromURI(uri);
                    String realPath = null;
                    try {
                        realPath = getFilePath(this, uri);
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                    Log.d("Patch ", realPath);
                    // get byte file
                    byte[] bytefile = FileLocal_To_Byte(realPath);
                    Log.d("Byte Array ", bytefile + " ");
                    // get base64
                    String FileBase64 = Base64.encodeToString(bytefile, Base64.DEFAULT);
                    Log.d("Base64", FileBase64);
                    // get file name
//                    String filename = uri.getLastPathSegment();
                    File file = new File(realPath);
                    String filename = file.getName();
                    Log.d("FileName ", filename);
                    // get extension and file name
                    String titleAfterSpecical = filename.substring(filename.lastIndexOf(".") + 1);
                    int iend = filename.indexOf(".");
                    String titleBeforeSpecical = filename.substring(0, iend);
                    Log.d("Extension + Filename", titleBeforeSpecical + " " + titleAfterSpecical);
                    arrFileAttach.add(new AttachFile(filename, titleAfterSpecical, "", FileBase64, titleBeforeSpecical));
                    FileAttachAdapter adapter = new FileAttachAdapter(this, arrFileAttach);
                    headerViewHolder.lvAttach.setAdapter(adapter);
                    headerViewHolder.lvAttach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            checkRunTimePermission(position, arrFileAttach, InputForwardDepartmentActivity.this);
                        }
                    });
                }
                break;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public long getDocumentID() {
        return DocumentID;
    }

    @Override
    public void setListHandle(ChangeHandlerAdapter adapterHandleProcess) {
        footerViewHolder.lvHandle.setAdapter(adapterHandleProcess);
    }

    @Override
    public void setAdapterReceivers(InputForwardDepartmentAdapter adapterReceivers) {
        lv.setAdapter(adapterReceivers);
    }

    @Override
    public ArrayList<AttachFile> getArrAttachFile() {
        return arrFileAttach;
    }

    @Override
    public CheckBox getCbRegulationDeadline() {
        return footerViewHolder.cbRegulationDeadline;
    }

    @Override
    public String getProcessDay() {
        if (isChooseDueDate()){
            return footerViewHolder.edtProcessDays.getText().toString();
        }else {
            return "0";
        }

    }

    @Override
    public String getExpirationDate() {
        return footerViewHolder.edtExpirationDate.getText().toString();
    }

    @Override
    public int getWorkFlow() {
        return workflowTransitionId;
    }

    @Override
    public String getResourceCodeId() {
        return resourceCodeId;
    }

    @Override
    public String getContent() {
        return headerViewHolder.edtContent.getText().toString();
    }

    @Override
    public boolean getCheckedEmail() {
        return headerViewHolder.cbEmail.isChecked();
    }

    @Override
    public boolean getCheckUrgent() {
        return headerViewHolder.cbUrgent.isChecked();
    }

    @Override
    public boolean getSuperUrgent() {
        return headerViewHolder.cbSupUrgent.isChecked();
    }

    @Override
    public boolean getCheckallowExtension() {
        return footerViewHolder.cbAllowExtension.isChecked();
    }

    @Override
    public void insertTableWaiting() {
        mSqLiteSendWaitingDepartment.QueryData(INSERT_TABLE_SEND_WAITING_DEPARTMENT + " VALUES('" + DocumentID + "','" + mFwDepartmentLogic.getJsonObjectRequest() + "')");
    }

    @Override
    public void closeProgress() {
        closeProgressDialog();
    }

    @Override
    public void deleteData() {
        if (null != urlNA)
            mSqLiteListDocument.QueryData("DELETE FROM '" + "M" + getOnlyNumerics(urlNA) + "' WHERE jobId = '" + DocumentID + "'");
    }

    @Override
    public void showProgress() {
        showProgressDialog("", this, DIALOG_CENTER, true);
    }

    @Override
    public String getScreen() {
        return iScreen;
    }

    @Override
    public void showToast(String s) {
        ShowErrorToast(this, s);
    }

    @Override
    public String getDocumentNumber() {
        return DocumentNumber;
    }

    @Override
    public void setExpirationDate(String dateFromMiliSec) {
        if (!footerViewHolder.cbRegulationDeadline.isChecked()) {
            footerViewHolder.edtExpirationDate.setText(dateFromMiliSec);
        }
    }

    @Override
    public String getInputName() {
        return titleActionbar;
    }

    @Override
    public ListView getListView() {
        return lv;
    }


    @Override
    public void handleGetNumDay(String dateChoose) {
        mFwDepartmentLogic.getCountDay(dateChoose);
    }

    @Override
    public void setDaysCount(String s) {
        if (!footerViewHolder.cbRegulationDeadline.isChecked()) {
            footerViewHolder.edtProcessDays.setText(s);
            setCancelChangeTextRequest(true);
        }
    }

    @Override
    public void ToastError(String s) {
        ShowErrorToast(this);
    }

    @Override
    public void CheckAllButton(boolean check) {
        headerViewHolder.cbCheckAllDepartment.setChecked(check);
    }

    @Override
    public boolean isChooseHandleWay() {
        return getIntent().getBooleanExtra(SHOW_CHOOSE_HANDLE_WAY, false);
    }

    @Override
    public boolean isChooseDueDate() {
        return getIntent().getBooleanExtra(SHOW_CHOOSE_DUE_DATE, false);
    }

    @Override
    public void CallBackDialog(String Date) {
        mFwDepartmentLogic.getCountDay(Date);
        footerViewHolder.edtExpirationDate.setText(Date);
    }

    @Override
    protected void onDestroy() {
        //showSoftwareKeyboard(false, this);
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        //showSoftwareKeyboard(false, this);
        super.onPause();
    }

    private void getStringIntent() {
        DocumentNumber = getIntent().getStringExtra(DOCUMENT_NUMBER);
        iScreen = getIntent().getStringExtra(LOOKUP_SCREEN);
        titleActionbar = getIntent().getStringExtra(TITLE_ACTION_BAR);
        DocumentID = getIntent().getLongExtra(DOCUMENTID, 0);
        workflowTransitionId = getIntent().getExtras().getInt(WORK_FLOW_TRAINSITION_ID);
        resourceCodeId = getIntent().getStringExtra(RESOURCECODEID);
        Log.d(TAG, workflowTransitionId + DocumentID + titleActionbar);
    }


    @Override
    public void onBackPressed() {
        Log.d(TAG, "back press");
        super.onBackPressed();
    }

    private void initView() {
        ButterKnife.bind(this);
        tvForward.setText(getIntent().getStringExtra(TITLE_ACTION_BAR));
        View header = getLayoutInflater().inflate(R.layout.activity_input_forward_department_header, null);
        final View footer = getLayoutInflater().inflate(R.layout.activity_input_forward_department_footer, null);
        headerViewHolder = new HeaderViewHolder(header, this);
        footerViewHolder = new FooterViewHolder(footer, this);
        lv.addHeaderView(header);
        lv.addFooterView(footer);
        footerViewHolder.lnChooseHandleWay.setVisibility(isChooseHandleWay() ? View.VISIBLE : View.GONE);
        footerViewHolder.lnChooseDueDate.setVisibility(isChooseDueDate() ? View.VISIBLE : View.GONE);
        footerViewHolder.edtProcessDays.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (!footerViewHolder.cbRegulationDeadline.isChecked()) {
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (!footerViewHolder.cbRegulationDeadline.isChecked()) {
                                footerViewHolder.edtProcessDays.setFocusableInTouchMode(true);
                                footerViewHolder.edtProcessDays.requestFocus();
                                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
                            }
                        }
                    }, 100);
                }
                return false;
            }
        });
        footerViewHolder.edtProcessDays.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String strInput = s.toString();
                if (!strInput.equals(nULL_STRING)) {
                    int mNumber = Integer.parseInt(footerViewHolder.edtProcessDays.getText().toString());
                    if (mNumber > 0) {
//                        if (mNumber <= 200) {
                        mFwDepartmentLogic.getExpirationDate(mNumber);
//                            Calendar mCalendar = Calendar.getInstance();
//                            mCalendar.add(Calendar.DAY_OF_YEAR, -1);
//                            mCalendar.add(Calendar.DAY_OF_YEAR, Integer.parseInt(edtProcessDays.getText().toString()));
//                            edtExpirationDate.setText(mSimpleDateFormat.format(mCalendar.getTime()));
//                        }
//                        else {
////                            edtProcessDays.setText("" + 200);
//                            Calendar mCalendar = Calendar.getInstance();
//                            mCalendar.add(Calendar.DAY_OF_YEAR, -1);
//                            mCalendar.add(Calendar.DAY_OF_YEAR, Integer.parseInt(footerViewHolder.edtProcessDays.getText().toString()));
////                            edtExpirationDate.setText(mSimpleDateFormat.format(mCalendar.getTime()));
//                            // set null
//                            footerViewHolder.edtProcessDays.setText(nULL_STRING);
//                            footerViewHolder.edtExpirationDate.setText(nULL_STRING);
////                            showDialogUpdateError("Chọn số ngày qui định nhỏ hơn 200", InputForwardDepartmentActivity.this);
//                            showDialogUpdateError("Vui lòng chọn số ngày quy định nhỏ hơn 200 và lớn hơn 0", InputForwardDepartmentActivity.this);
//                        }
                    }
//                    else {
////                        showDialogUpdateError("Chọn số ngày qui định lớn hơn 0", InputForwardDepartmentActivity.this);
//                        showDialogUpdateError("Vui lòng chọn số ngày quy định nhỏ hơn 200 và lớn hơn 0", InputForwardDepartmentActivity.this);
////                        edtProcessDays.setText(1 + "");
//                        Calendar mCalendar = Calendar.getInstance();
//                        mCalendar.add(Calendar.DAY_OF_YEAR, -1);
//                        mCalendar.add(Calendar.DAY_OF_YEAR, Integer.parseInt(footerViewHolder.edtProcessDays.getText().toString()));
////                        edtExpirationDate.setText(mSimpleDateFormat.format(mCalendar.getTime()));
//                        footerViewHolder.edtProcessDays.setText(nULL_STRING);
//                        footerViewHolder.edtExpirationDate.setText(nULL_STRING);
//
//                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    //    private void initActiobar() {
//        mToolbar = (Toolbar) findViewById(R.id.app_bar);
//        setSupportActionBar(mToolbar);
//        getSupportActionBar().setTitle(nULL_STRING);
//        TextView tvActionbar = (TextView) findViewById(R.id.title_actionbar);
//        tvActionbar.setText(titleActionbar.toUpperCase());
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        mToolbar.setPadding(0, getStatusBarHeight(this), 0, 0);
//        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });
//    }
    //    class ForwardDepartment extends AsyncTask<String, Integer, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            mJsonObject = new JSONObject();
//            try {
//                mJsonObject.put(MODULE, PROCESSING_WORKING);
//                mJsonObject.put(NEOTYPE, TYPE_DEPARTMENT_FORWARD);
//                JSONObject mData = new JSONObject();
//                mData.put(JOB_ID, DocumentID);
//                mData.put(WORK_FLOW_TRAINSITION_ID, workflowTransitionId);
//                mData.put(RESOURCE_CODE_ID, resourceCodeId);
//                mData.put(SCHEDULE_CONTENT, edtContent.getText().toString());
//                mData.put(TRAINFER_EMAIL, cbEmail.isChecked() ? true : false);
//                mData.put(URGENT, cbUrgent.isChecked() ? true : false);
//                mData.put(SUP_URGENT, cbSupUrgent.isChecked() ? true : false);
//                mData.put(PROCESS_TYPE_ID, processTypeId);
//                mData.put(PROCESS_DAYS, edtProcessDays.getText().toString());
//                mData.put(EXPIRATION_DATE, getMilisecondDay(edtExpirationDate.getText().toString()));
//                mData.put(ALLOW_EXTENSION_PROCESS, cbAllowExtension.isChecked());
////                mData.put(ORG_RECIPIENTS, mJsonArray);
//                mData.put(RECIPIENTS, mJsonArray);
//                mData.put(ATTACH_FILE, mJsonArrayAttach);
//                mJsonObject.put(DATA, mData);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
////            return makePostRequest(params[0], mJsonObject.toString(), getUserid(), getPass());
//            return eventPostRequest(getModuleInfor(PROCESSING_WORKING, InputForwardDepartmentActivity.this), mJsonObject.toString(), getUserid(), getPass());
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            String resuilt = "";
//            try {
//                JSONObject mJsonObject = new JSONObject(s);
//                int Response = mJsonObject.getInt(CODE);
//                resuilt = Response == 0 ? TRUE : FALSE;
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            Intent intent = new Intent(InputForwardDepartmentActivity.this, OfficalActivity.class);
//            String iScreen = getIntent().getStringExtra(LOOKUP_SCREEN);
//            intent.putExtra(COME_BACK_FROM_SCREEN, iScreen);
//            intent.putExtra(INPUT_COME_BACK, iScreen);
//            closeProgressDialog();
//            if (resuilt.contains(FALSE)) {
//                intent.putExtra(URLNA_COMEBACK_OFFICE, OfficalActivity.urlNA);
//                intent.putExtra(FORWARD_RESUILD, FALSE);
//            } else {
//                if (resuilt.equals(nULL_STRING)) {
//                    mSqLiteSendWaitingDepartment.QueryData(INSERT_TABLE_SEND_WAITING_DEPARTMENT + " VALUES('" + DocumentID + "','" + mJsonObject + "')");
//                    closeProgressDialog();
//                } else {
//                    if (resuilt.contains(TRUE))
//                        intent.putExtra(FORWARD_RESUILD, TRUE);
//                }
//                intent.putExtra(URLNA_COMEBACK_OFFICE, OfficalActivity.urlNA);
//                mSqLiteListDocument.QueryData("DELETE FROM '" + "M" + getOnlyNumerics(OfficalActivity.urlNA) + "' WHERE documentID = '" + DocumentID + "'");
//            }
//            intent.putExtra(DOCUMENT_NUMBER, getIntent().getStringExtra(DOCUMENT_NUMBER));
//            startActivity(intent);
//            super.onPostExecute(s);
//        }
//    }
    //    private String addJsonRequestForwardDPM(){
//        mJsonObject = new JSONObject();
//        try {
//            mJsonObject.put(MODULE, PROCESSING_WORKING);
//            mJsonObject.put(NEOTYPE, TYPE_DEPARTMENT_FORWARD);
//            JSONObject mData = new JSONObject();
//            mData.put(JOB_ID, DocumentID);
//            mData.put(WORK_FLOW_TRAINSITION_ID, workflowTransitionId);
//            mData.put(RESOURCE_CODE_ID, resourceCodeId);
//            mData.put(SCHEDULE_CONTENT, edtContent.getText().toString());
//            mData.put(TRAINFER_EMAIL, cbEmail.isChecked() ? true : false);
//            mData.put(URGENT, cbUrgent.isChecked() ? true : false);
//            mData.put(SUP_URGENT, cbSupUrgent.isChecked() ? true : false);
//            mData.put(PROCESS_TYPE_ID, processTypeId);
//            mData.put(PROCESS_DAYS, edtProcessDays.getText().toString());
//            mData.put(EXPIRATION_DATE, getMilisecondDay(edtExpirationDate.getText().toString()));
//            mData.put(ALLOW_EXTENSION_PROCESS, cbAllowExtension.isChecked());
////                mData.put(ORG_RECIPIENTS, mJsonArray);
//            mData.put(RECIPIENTS, mJsonArray);
//            mData.put(ATTACH_FILE, mJsonArrayAttach);
//            mJsonObject.put(DATA, mData);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return mJsonObject.toString();
//    }
//    @Override
//    public void onTaskCompleted(String s, String CaseRequest) {
//        String resuilt = "";
//        try {
//            JSONObject mJsonObject = new JSONObject(s);
//            int Response = mJsonObject.getInt(CODE);
//            resuilt = Response == 0 ? TRUE : FALSE;
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        Intent intent = new Intent(InputForwardDepartmentActivity.this, OfficalActivity.class);
//        String iScreen = getIntent().getStringExtra(LOOKUP_SCREEN);
//        intent.putExtra(COME_BACK_FROM_SCREEN, iScreen);
//        intent.putExtra(INPUT_COME_BACK, iScreen);
//        closeProgressDialog();
//        if (resuilt.contains(FALSE)) {
//            intent.putExtra(URLNA_COMEBACK_OFFICE, OfficalActivity.urlNA);
//            intent.putExtra(FORWARD_RESUILD, FALSE);
//        } else {
//            if (resuilt.equals(nULL_STRING)) {
////                mSqLiteSendWaitingDepartment.QueryData(INSERT_TABLE_SEND_WAITING_DEPARTMENT + " VALUES('" + DocumentID + "','" + mJsonObject + "')");
//                mSqLiteSendWaitingDepartment.QueryData(INSERT_TABLE_SEND_WAITING_DEPARTMENT + " VALUES('" + DocumentID + "','" + mFwDepartmentLogic.getJsonObjectRequest() + "')");
//                closeProgressDialog();
//            } else {
//                if (resuilt.contains(TRUE))
//                    intent.putExtra(FORWARD_RESUILD, TRUE);
//            }
//            intent.putExtra(URLNA_COMEBACK_OFFICE, OfficalActivity.urlNA);
//            mSqLiteListDocument.QueryData("DELETE FROM '" + "M" + getOnlyNumerics(OfficalActivity.urlNA) + "' WHERE documentID = '" + DocumentID + "'");
//        }
//        intent.putExtra(DOCUMENT_NUMBER, getIntent().getStringExtra(DOCUMENT_NUMBER));
//        startActivity(intent);
//    }
    //    class ReadJsonDepartment extends AsyncTask<String, Integer, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            return readFromFile(InputForwardDepartmentActivity.this, "department" + DocumentID);
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            arrDeparmentRow = new ArrayList<InputForwardDeparmentRow>();
//            try {
//                JSONObject mJsonObject = new JSONObject(s);
//                JSONArray mArrayData = mJsonObject.getJSONArray(DATA);
//                for (int i = 0; i < mArrayData.length(); i++) {
//                    JSONObject mData = mArrayData.getJSONObject(i);
//                    int organizationId = mData.getInt(ORGANIZATION_ID);
//                    String organizationName = mData.getString(ORGANIZATION_NAME);
//                    JSONArray receivers = mData.getJSONArray(RECEIVERS_LIST);
//                    final List<ReceiversRow> arrTrue = new ArrayList<>();
//                    final List<ReceiversRow> arrFalse = new ArrayList<>();
//                    for (int j = 0; j < receivers.length(); j++) {
//                        JSONObject mDataReceivers = receivers.getJSONObject(j);
//                        boolean Default = mDataReceivers.getBoolean(DEFAULT);
//                        String receiverId = mDataReceivers.getString(RECEIVER_ID);
//                        String receiverName = mDataReceivers.getString(RECEIVER_NAME);
//                        String roleName = mDataReceivers.getString(ROLE_NAME);
//                        arrFalse.add(new ReceiversRow(Default, receiverId, receiverName, roleName, true, false));
//                        if (Default) {
//                            arrTrue.add(new ReceiversRow(Default, receiverId, receiverName, roleName, false, false));
//                        }
//                    }
//                    adapterTrue = new ReceiversDepartmentAdapter(InputForwardDepartmentActivity.this, arrTrue, new AdapterButtonClick() {
//
//                        @Override
//                        public void addClick(int position) {
//
//                        }
//
//                        @Override
//                        public void mainCheck(int position) {
//                            if (arrTrue.get(position).isMainChecked()) {
//                                arrTrue.get(position).setMainChecked(false);
//                                for (int i = 0; i < arrFalse.size(); i++) {
//                                    if (arrFalse.get(i).getReceiverId().equals(arrTrue.get(position).getReceiverId())) {
//                                        arrFalse.get(i).setMainChecked(false);
//                                    }
//                                }
//                            } else {
//                                arrTrue.get(position).setMainChecked(true);
//                                for (int i = 0; i < arrFalse.size(); i++) {
//                                    if (arrFalse.get(i).getReceiverId().equals(arrTrue.get(position).getReceiverId())) {
//                                        arrFalse.get(i).setMainChecked(true);
//                                    }
//                                }
////                                Map<String, List<List<String>>> lisMap = new HashMap<String, List<List<String>>>();
////
////                                //List user choise
////                                List<String> listChoise = new ArrayList<>();
////                                //...
////                                //List All user
////                                List<String> listAll = new ArrayList<>();
////                               //...
////                                //List container
////                                List<List<String>> lists = new ArrayList<List<String>>();
////                                lists.add(listChoise);
////                                lists.add(listAll);
////
////                                lisMap.put("pBID", lists);
//
//                                //
//                            }
//
//                        }
//
//                        @Override
//                        public void onBtnRemoveClick(int position) {
//                            showProgressDialog("", InputForwardDepartmentActivity.this, DIALOG_CENTER, true);
//                            for (int i = 0; i < arrTrue.size(); i++) {
//                                if (arrTrue.get(i).getReceiverId().equals(arrTrue.get(position).getReceiverId())) {
//                                    arrTrue.remove(i);
//                                    adapterTrue.notifyDataSetChanged();
//                                }
//                            }
//                            closeProgressDialog();
//                        }
//
//                    });
//                    arrDeparmentRow.add(new InputForwardDeparmentRow(false, organizationId, organizationName, arrFalse, adapterTrue, arrTrue));
//                }
//                adapterReceivers = new InputForwardDepartmentAdapter(InputForwardDepartmentActivity.this, arrDeparmentRow, new AdapterButtonClick() {
//                    @Override
//                    public void addClick(int position) {
//
//                    }
//
//                    @Override
//                    public void mainCheck(int position) {
//
//                    }
//
//                    @Override
//                    public void onBtnRemoveClick(int position) {
//                        showProgressDialog("", InputForwardDepartmentActivity.this, DIALOG_CENTER, true);
//                        DialogFalseRow row = new DialogFalseRow();
//                        row.setAdapterTrue(arrDeparmentRow.get(position).getAdapterTrue());
//                        row.setArrListFalse(arrDeparmentRow.get(position).getArrFalse());
//                        row.setArrListTrue(arrDeparmentRow.get(position).getArrTrue());
//                        new UpdateListFalse().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, row);
//                    }
//                });
//                lv.setAdapter(adapterReceivers);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//            super.onPostExecute(s);
//        }
//    }
    //    class UpdateListFalse extends AsyncTask<DialogFalseRow, Integer, DialogFalseRow> {
//
//        @Override
//        protected DialogFalseRow doInBackground(DialogFalseRow... params) {
//            row = new DialogFalseRow();
//            row.setAdapterTrue(params[0].getAdapterTrue());
//            row.setArrListFalse(params[0].getArrListFalse());
//            row.setArrListTrue(params[0].getArrListTrue());
//            for (int i = 0; i < row.getArrListFalse().size(); i++) {
//                boolean found = false;
//                for (int j = 0; j < row.getArrListTrue().size(); j++) {
//                    if (row.getArrListFalse().get(i).getReceiverId().equals(row.getArrListTrue().get(j).getReceiverId())) {
//                        found = true;
//                    }
//                }
//                if (!found) {
//                    row.getArrListFalse().get(i).setDefault(false);
//                }
//            }
//            adapterFalseDialog = new ReceiversDepartmentAdapter(InputForwardDepartmentActivity.this, row.getArrListFalse(), new AdapterButtonClick() {
//                @Override
//                public void onBtnRemoveClick(int position) {
//
//                }
//
//                @Override
//                public void addClick(int position) {
//                    if (row.getArrListFalse().get(position).isDefault()) {
//                        row.getArrListFalse().get(position).setDefault(false);
//                        row.getAdapterFalse().notifyDataSetChanged();
//                        for (int i = 0; i < row.getArrListTrue().size(); i++) {
//                            if (row.getArrListTrue().get(i).getReceiverId().equals(row.getArrListFalse().get(position).getReceiverId())) {
//                                row.getArrListTrue().remove(i);
//                            }
//                        }
////                        adapterTrue.notifyDataSetChanged();
//                    } else {
//                        row.getArrListFalse().get(position).setDefault(true);
//                        row.getArrListTrue().add(new ReceiversRow(row.getArrListFalse().get(position).isDefault(), row.getArrListFalse().get(position).getReceiverId(), row.getArrListFalse().get(position).getReceiverName()
//                                , row.getArrListFalse().get(position).getRoleName(), false, false));
////                        adapterTrue.notifyDataSetChanged();
//                    }
//                    adapterFalseDialog.notifyDataSetChanged();
//                }
//
//                @Override
//                public void mainCheck(int position) {
//                    if (row.getArrListFalse().get(position).isMainChecked()) {
//                        row.getArrListFalse().get(position).setMainChecked(false);
//                        row.getAdapterFalse().notifyDataSetChanged();
//                        for (int i = 0; i < row.getArrListTrue().size(); i++) {
//                            if (row.getArrListTrue().get(i).getReceiverId().equals(row.getArrListFalse().get(position).getReceiverId())) {
//                                row.getArrListTrue().get(i).setMainChecked(false);
//                            }
//                        }
//                    } else {
//                        row.getArrListFalse().get(position).setMainChecked(true);
//                        for (int i = 0; i < row.getArrListTrue().size(); i++) {
//                            if (row.getArrListTrue().get(i).getReceiverId().equals(row.getArrListFalse().get(position).getReceiverId())) {
//                                row.getArrListTrue().get(i).setMainChecked(true);
//                            }
//                        }
//                    }
////                    adapterTrue.notifyDataSetChanged();
//                }
//
//            });
//            row.setArrListTrue(row.getArrListTrue());
//            row.setAdapterTrue(row.getAdapterTrue());
//            row.setArrListFalse(row.getArrListFalse());
//            row.setAdapterFalse(adapterFalseDialog);
//            return row;
//        }
//
//        @Override
//        protected void onPostExecute(DialogFalseRow s) {
//            showDialogList(s);
//            super.onPostExecute(s);
//        }
//    }
//
//    public void showDialogList(final DialogFalseRow row) {
//        final Dialog mDialog = new Dialog(this);
//        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        mDialog.setContentView(R.layout.dialog_select_receivers);
////        TextView btnDismiss = (TextView) mDialog.findViewById(R.id.diss_miss);
////        NonScrollListView lv = (NonScrollListView) mDialog.findViewById(R.id.list_select_receivers);
//        ListView lv = (ListView) mDialog.findViewById(R.id.list_select_receivers);
//        TextView btnAgree = (TextView) mDialog.findViewById(R.id.agree);
//        TextView btnCancel = (TextView) mDialog.findViewById(R.id.cancel);
//        btnAgree.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mDialog.dismiss();
////                adapterTrue.notifyDataSetChanged();
//                row.getAdapterTrue().notifyDataSetChanged();
//            }
//        });
//        btnCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mDialog.dismiss();
//            }
//        });
//        mDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
////        btnDismiss.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                mDialog.dismiss();
////                row.getAdapterTrue().notifyDataSetChanged();
////            }
////        });
////        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
////            @Override
////            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
////                if (row.getArrListFalse().get(position).isDefault()) {
////                    row.getArrListFalse().get(position).setDefault(false);
////                    row.getAdapterFalse().notifyDataSetChanged();
////                    for (int i = 0; i < row.getArrListTrue().size(); i++) {
////                        if (row.getArrListTrue().get(i).getReceiverId() == row.getArrListFalse().get(position).getReceiverId()) {
////                            row.getArrListTrue().remove(i);
////                        }
////                    }
////                    adapterTrue.notifyDataSetChanged();
////                } else {
////                    row.getArrListFalse().get(position).setDefault(true);
////                    row.getArrListTrue().add(new ReceiversRow(row.getArrListFalse().get(position).isDefault(), row.getArrListFalse().get(position).getReceiverId(), row.getArrListFalse().get(position).getReceiverName()
////                            , row.getArrListFalse().get(position).getRoleName(), false, false));
////                    row.getAdapterFalse().notifyDataSetChanged();
////                    adapterTrue.notifyDataSetChanged();
////                }
////            }
////        });
//        mDialog.show();
//        lv.setAdapter(row.getAdapterFalse());
//        if (mDialog.isShowing()) {
//            closeProgressDialog();
//        }
//
//    }
    //    private void showDialogSetting(final List<ReceiversRow> arrListFalse, final ReceiversDepartmentAdapter adapterTrue, final List<ReceiversRow> arrListTrue) {
////        DisplayMetrics metrics = this.getResources().getDisplayMetrics();
////        int width = metrics.widthPixels;
////        int height = metrics.heightPixels;
//        final Dialog mDialog = new Dialog(this);
//        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        mDialog.setContentView(R.layout.dialog_select_receivers);
//        TextView btnDismiss = (TextView) mDialog.findViewById(R.id.diss_miss);
//        NonScrollListView lv = (NonScrollListView) mDialog.findViewById(R.id.list_select_receivers);
//        for (int i = 0; i < arrListFalse.size(); i++) {
//            boolean found = false;
//            for (int j = 0; j < arrListTrue.size(); j++) {
//                if (arrListFalse.get(i).getReceiverId() == arrListTrue.get(j).getReceiverId()) {
//                    found = true;
//                }
//            }
//            if (!found) {
//                arrListFalse.get(i).setDefault(false);
//            }
//        }
//        final ReceiversDepartmentAdapter adapter = new ReceiversDepartmentAdapter(this, arrListFalse, new AdapterButtonClick() {
//            @Override
//            public void onBtnClick(int position) {
//
//            }
//        });
//
//        lv.setAdapter(adapter);
//        closeProgressDialog();
//        btnDismiss.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mDialog.dismiss();
//            }
//        });
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if (arrListFalse.get(position).isDefault()) {
//                    arrListFalse.get(position).setDefault(false);
//                    adapter.notifyDataSetChanged();
//                    for (int i = 0; i < arrListTrue.size(); i++) {
//                        if (arrListTrue.get(i).getReceiverId() == arrListFalse.get(position).getReceiverId()) {
//                            arrListTrue.remove(i);
//                        }
//                    }
//                    adapterTrue.notifyDataSetChanged();
//                } else {
//                    arrListFalse.get(position).setDefault(true);
//                    arrListTrue.add(new ReceiversRow(arrListFalse.get(position).isDefault(), arrListFalse.get(position).getReceiverId(), arrListFalse.get(position).getReceiverName()
//                            , arrListFalse.get(position).getRoleName(), false, false));
//                    adapter.notifyDataSetChanged();
//                    adapterTrue.notifyDataSetChanged();
//                }
//            }
//        });
//        mDialog.show();
//
//    }
//    class ReadJsonHandleProcess extends AsyncTask<String, Integer, String> {
//        @Override
//        protected String doInBackground(String... params) {
//            return readFromFile(InputForwardDepartmentActivity.this, "handle" + DocumentID);
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
//                adapterHandleProcess = new ChangeHandlerAdapter(InputForwardDepartmentActivity.this, arrayHandleProcess);
//                lvHandle.setAdapter(adapterHandleProcess);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            super.onPostExecute(s);
//        }
//    }
//@Override
//public void onClick(View v) {
//    int id = v.getId();
//    switch (id) {
//        case R.id.button_forward:
//            mFwDepartmentLogic.RequestForward();
//            break;
//        case R.id.cancel_forward:
//            onBackPressed();
//            break;
//        case R.id.repulation_day:
//            if (!footerViewHolder.cbRegulationDeadline.isChecked()) {
//                showDataPickDialog(footerViewHolder.edtExpirationDate, footerViewHolder.edtProcessDays, this);
//            }
//            break;
//        case R.id.regulations_deadline:
//            if (footerViewHolder.cbRegulationDeadline.isChecked()) {
//                footerViewHolder.edtProcessDays.setText("");
//                footerViewHolder.edtExpirationDate.setText("");
//                footerViewHolder.edtProcessDays.setFocusable(false);
////                    edtExpirationDate.setFocusable(false);
//                showSoftwareKeyboard(false, this);
//            } else {
//                footerViewHolder.edtProcessDays.setFocusableInTouchMode(true);
//                footerViewHolder.edtProcessDays.setFocusable(true);
////                    edtExpirationDate.setFocusableInTouchMode(true);
////                    edtExpirationDate.setFocusable(true);
//            }
//            break;
//        case R.id.check_all:
//            mFwDepartmentLogic.CheckallDepartment(headerViewHolder.cbCheckAllDepartment);
////                if (cbCheckAllDepartment.isChecked()) {
////                    cbCheckAllDepartment.setChecked(true);
////                    for (int i = 0; i < arrDeparmentRow.size(); i++) {
////                        arrDeparmentRow.get(i).setDefault(true);
////                    }
////                } else {
////                    cbCheckAllDepartment.setChecked(false);
////                    for (int i = 0; i < arrDeparmentRow.size(); i++) {
////                        arrDeparmentRow.get(i).setDefault(false);
////                    }
////                }
////                adapterReceivers.notifyDataSetChanged();
//            break;
//        case notify_layout:
//            showNotiffy(mNotify_layout, this, mNumberOfNotify);
//            break;
//        case R.id.choose_file:
//            showFileChooser();
//            break;
//
//    }
//}
//    private void initView() {
//        ButterKnife.bind(this);
////        lv = (ListView) findViewById(R.id.lv_process_person);
////        mNotify_layout = (RelativeLayout) findViewById(notify_layout);
////        mNumberOfNotify = (TextView) findViewById(R.id.number_notify);
//        View header = getLayoutInflater().inflate(R.layout.activity_input_forward_department_header, null);
//        View footer = getLayoutInflater().inflate(R.layout.activity_input_forward_department_footer, null);
//        lv.addHeaderView(header);
//        lv.addFooterView(footer);
////        btnForward = (TextView) header.findViewById(R.id.button_forward);
////        edtContent = (EditText) header.findViewById(R.id.process_content);
////        cbEmail = (CheckBox) header.findViewById(R.id.send_mail_cb);
////        cbUrgent = (CheckBox) header.findViewById(R.id.khan_cb);
////        cbSupUrgent = (CheckBox) header.findViewById(R.id.thuong_khan_cb);
////        cbCheckAllDepartment = (CheckBox) header.findViewById(R.id.check_all);
////        tvProcessDay = (TextView) header.findViewById(R.id.process_name);
////        tvCancel = (TextView) header.findViewById(R.id.cancel_forward);
////        tvChooseFile = (TextView) header.findViewById(R.id.choose_file);
////        lvAttach = (NonScrollListView) header.findViewById(R.id.attach_list);
//
//
////        lvHandle = (NonScrollListView) footer.findViewById(R.id.list_Handle);
////        edtProcessDays = (EditText) footer.findViewById(R.id.number_day_repulations);
////        edtExpirationDate = (TextView) footer.findViewById(R.id.repulation_day);
////        cbAllowExtension = (CheckBox) footer.findViewById(R.id.allow_extenstion);
////        cbRegulationDeadline = (CheckBox) footer.findViewById(R.id.regulations_deadline);
////        tvChooseFile.setOnClickListener(this);
////        mNotify_layout.setOnClickListener(this);
////        edtExpirationDate.setOnClickListener(this);
////        tvCancel.setOnClickListener(this);
////        btnForward.setOnClickListener(this);
////        cbRegulationDeadline.setOnClickListener(this);
////        cbCheckAllDepartment.setOnClickListener(this);
//        footerViewHolder.edtProcessDays.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                String strInput = s.toString();
//                if (!strInput.equals(nULL_STRING)) {
//                    int mNumber = Integer.parseInt(footerViewHolder.edtProcessDays.getText().toString());
//                    if (mNumber > 0) {
//                        if (mNumber <= 200) {
//                            mFwDepartmentLogic.getExpirationDate(mNumber);
////                            Calendar mCalendar = Calendar.getInstance();
////                            mCalendar.add(Calendar.DAY_OF_YEAR, -1);
////                            mCalendar.add(Calendar.DAY_OF_YEAR, Integer.parseInt(edtProcessDays.getText().toString()));
////                            edtExpirationDate.setText(mSimpleDateFormat.format(mCalendar.getTime()));
//                        } else {
////                            edtProcessDays.setText("" + 200);
//                            Calendar mCalendar = Calendar.getInstance();
//                            mCalendar.add(Calendar.DAY_OF_YEAR, -1);
//                            mCalendar.add(Calendar.DAY_OF_YEAR, Integer.parseInt(footerViewHolder.edtProcessDays.getText().toString()));
////                            edtExpirationDate.setText(mSimpleDateFormat.format(mCalendar.getTime()));
//                            // set null
//                            footerViewHolder.edtProcessDays.setText(nULL_STRING);
//                            footerViewHolder.edtExpirationDate.setText(nULL_STRING);
////                            showDialogUpdateError("Chọn số ngày qui định nhỏ hơn 200", InputForwardDepartmentActivity.this);
//                            showDialogUpdateError("Vui lòng chọn số ngày quy định nhỏ hơn 200 và lớn hơn 0", InputForwardDepartmentActivity.this);
//                        }
//                    } else {
////                        showDialogUpdateError("Chọn số ngày qui định lớn hơn 0", InputForwardDepartmentActivity.this);
//                        showDialogUpdateError("Vui lòng chọn số ngày quy định nhỏ hơn 200 và lớn hơn 0", InputForwardDepartmentActivity.this);
////                        edtProcessDays.setText(1 + "");
//                        Calendar mCalendar = Calendar.getInstance();
//                        mCalendar.add(Calendar.DAY_OF_YEAR, -1);
//                        mCalendar.add(Calendar.DAY_OF_YEAR, Integer.parseInt(footerViewHolder.edtProcessDays.getText().toString()));
////                        edtExpirationDate.setText(mSimpleDateFormat.format(mCalendar.getTime()));
//                        footerViewHolder.edtProcessDays.setText(nULL_STRING);
//                        footerViewHolder.edtExpirationDate.setText(nULL_STRING);
//
//                    }
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//    }
}
