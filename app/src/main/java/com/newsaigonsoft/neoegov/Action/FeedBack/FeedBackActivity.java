package com.newsaigonsoft.neoegov.Action.FeedBack;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import com.newsaigonsoft.neoegov.Adapter.FileAttachAdapter;
import com.newsaigonsoft.neoegov.CustomViewListExpand.NonScrollListView;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.AttachFile;

import static com.newsaigonsoft.neoegov.R.id.notify_layout;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.STATISTIC_TYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.WORK_FLOW_TRAINSITION_ID;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DIALOG_CENTER;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DOCUMENTID;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LOOKUP_SCREEN;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.RESOURCECODEID;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAG;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TITLE_ACTION_BAR;

public class FeedBackActivity extends FeedBackBase implements FeedBackView {

    @BindView(R.id.approve_day)
    EditText edtContentFeedBack;
    @BindView(R.id.number_notify)
    TextView mNumberOfNotify;
    @BindView(notify_layout)
    RelativeLayout mNotify_layout;
    @BindView(R.id.attach_list)
    NonScrollListView lvAttach;

    @Optional
    @OnClick({R.id.button_forward, R.id.cancel_forward, R.id.notify_layout, R.id.choose_file})
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.button_forward:
                mFeedBackLogic.RequestFeedBack(arrFileAttach);
                break;
            case R.id.cancel_forward:
                onBackPressed();
                break;
            case notify_layout:
                showNotiffy(mNotify_layout, this, mNumberOfNotify);
                break;
            case R.id.choose_file:
                showFileChooser();
                break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        mFeedBackLogic = new FeedBackLogic(this, this);
        ButterKnife.bind(this);
        getInforAccountFromShareReferenced(this);
        getStringIntent();
        initActiobar(titleActionBar, true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.close_white_x1);
        showNotifiCation(mNumberOfNotify);
        arrFileAttach = new ArrayList<>();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void getStringIntent() {
        isScreen = getIntent().getStringExtra(LOOKUP_SCREEN);
        isTatistic = getIntent().getIntExtra(STATISTIC_TYPE, 0);
        DocumentID = getIntent().getLongExtra(DOCUMENTID, 0);
        workflowTransitionId = getIntent().getExtras().getInt(WORK_FLOW_TRAINSITION_ID);
        resourceCodeId = getIntent().getStringExtra(RESOURCECODEID);
        titleActionBar = getIntent().getStringExtra(TITLE_ACTION_BAR);
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
                    lvAttach.setAdapter(adapter);
                    lvAttach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            checkRunTimePermission(position, arrFileAttach, FeedBackActivity.this);
                        }
                    });
                }
                break;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void showProgress() {
        showProgressDialog("", this, DIALOG_CENTER, false);
    }

    @Override
    public String getFeedBackContent() {
        return edtContentFeedBack.getText().toString();
    }

    @Override
    public long getDocumentID() {
        return DocumentID;
    }

    @Override
    public int getWorkflowTransitionId() {
        return workflowTransitionId;
    }

    @Override
    public String getResourceCodeId() {
        return resourceCodeId;
    }

    @Override
    public void closeProgress() {
        closeProgressDialog();
    }

    @Override
    public String getScreen() {
        return isScreen;
    }

    @Override
    public int getTatistic() {
        return isTatistic;
    }

    @Override
    public void ToastError(String s) {
        ShowErrorToast(this);
        Log.d(TAG, s);
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

//    class FeedBack extends AsyncTask<String, Integer, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            JSONObject mJsonObject = new JSONObject();
//            try {
//                mJsonObject.put(MODULE, PROCESSING_WORKING);
//                mJsonObject.put(NEOTYPE,TYPE_FEED_BACK );
//                JSONObject mData = new JSONObject();
//                mData.put(JOB_ID, DocumentID);
//                mData.put(FEED_BACK_PROCESS_ID, 0);
//                mData.put(WORK_FLOW_TRAINSITION_ID, workflowTransitionId);
//                mData.put(RESOURCE_CODE_ID, resourceCodeId);
//                mData.put(SCHEDULE_CONTENT, edtContentFeedBack.getText().toString());
//                mData.put(ATTACH_FILE, mJsonArrayAttach);
//                mJsonObject.put(DATA, mData);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            return eventPostRequest(getModuleInfor(PROCESSING_WORKING, FeedBackAppActivity.this), mJsonObject.toString(), getUserid(), getPass());
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
//                Intent intent = new Intent(FeedBackAppActivity.this, OfficalActivity.class);
//                //// TODO: 9/1/2017  comback screen
//                intent.putExtra(COME_BACK_FROM_SCREEN, isScreen);
//                intent.putExtra(INPUT_COME_BACK, FEED_BACK);
//                //// TODO: 9/1/2017  comback screen
//                intent.putExtra(FORWARD_RESUILD, resuilt);
//                intent.putExtra(STATISTIC_TYPE, isTatistic);
//                startActivity(intent);
//            } catch (JSONException e) {
//                ShowErrorToast(FeedBackAppActivity.this);
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
//        edtContentFeedBack = (EditText) findViewById(R.id.approve_day);
//        btnFeedBack = (TextView) findViewById(R.id.button_forward);
//        btnCancel = (TextView) findViewById(R.id.cancel_forward);
//        mNotify_layout = (RelativeLayout) findViewById(notify_layout);
//        lvAttach = (NonScrollListView) findViewById(R.id.attach_list);
//        tvChooseFile = (TextView) findViewById(R.id.choose_file);
//        mNumberOfNotify = (TextView) findViewById(R.id.number_notify);
//        tvChooseFile.setOnClickListener(this);
//        mNotify_layout.setOnClickListener(this);
//        btnFeedBack.setOnClickListener(this);
//        btnCancel.setOnClickListener(this);
//    }
//    @Override
//    public void onClick(View view) {
//        int id = view.getId();
//        switch (id) {
//            case R.id.button_forward:
//                mFeedBackLogic.RequestFeedBack(arrFileAttach);
//                break;
//            case R.id.cancel_forward:
//                onBackPressed();
//                break;
//            case notify_layout:
//                showNotiffy(mNotify_layout, this, mNumberOfNotify);
//                break;
//            case R.id.choose_file:
//                showFileChooser();
//                break;
//        }
//    }
}
