package com.newsaigonsoft.neoegov.Action.InputForward;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.InputType;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.newsaigonsoft.neoegov.Adapter.FileAttachAdapter;
import com.newsaigonsoft.neoegov.Adapter.ReceivePersonAdapter;
import com.newsaigonsoft.neoegov.CustomViewListExpand.NonScrollListView;
import com.newsaigonsoft.neoegov.CustomViewListExpand.SearchViewFormatter;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.SQLite.SQLite;
import com.newsaigonsoft.neoegov.Subjects.AttachFile;
import com.newsaigonsoft.neoegov.Subjects.KeyManager;

import org.json.JSONObject;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

import static com.newsaigonsoft.neoegov.R.id.notify_layout;
import static com.newsaigonsoft.neoegov.R.id.search_view;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.WORK_FLOW_TRAINSITION_ID;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.CREATE_TABLE_SQLLITE_SEND_WAITING;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DIALOG_CENTER;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DOCUMENTID;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DOCUMENT_NUMBER;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LOOKUP_SCREEN;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.PROCESSPERSON;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.REPORT_OR_FORWARD_OR_RELEASE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.RESOURCECODEID;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAG;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_14;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_FORWARD_PERSON;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_FORWARD_RELEASE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_REPORT_RESUILT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TITLE_ACTION_BAR;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.nULL_STRING;

public class InputForwardActivity extends InputForwardBase implements InputForwardView
        , AdapterView.OnItemClickListener {
    //    @BindView(R.id.croll_view)
//    NestedScrollView mScrollView;
    @BindView(notify_layout)
    RelativeLayout mNotify_layout;
    @BindView(R.id.number_notify)
    TextView mNumberOfNotify;
    @BindView(R.id.layout_list)
    LinearLayout mLinearList;
    @BindView(R.id.lv_process_person)
    ListView lv;
    @BindView(R.id.rlSearch)
    RelativeLayout rlSearch;
    @BindView(R.id.tvDone)
    TextView tvDone;
    @BindView(search_view)
    SearchView mSearchView;
    @BindView(R.id.approve_day)
    EditText edtApproveday;
    @BindView(R.id.time_process)
    TextView tvTimeProcess;
    @BindView(R.id.process_name)
    TextView tvProcessName;
    @BindView(R.id.khan_cb)
    CheckBox cbKhan;
    @BindView(R.id.part_top)
    LinearLayout mLinearPart_Top;
    @BindView(R.id.attach_list)
    NonScrollListView lvAttach;
    @BindView(R.id.process_content)
    EditText edtProcessContent;
    @BindView(R.id.email_cb)
    CheckBox cbEmail;
    @BindView(R.id.img_search_start)
    ImageView imgSearchStart;
    @BindView(R.id.button_forward)
    TextView tvForward;
    @BindView(R.id.cb_choose_all)
    CheckBox cbAll;
    @BindView(R.id.check_all)
    LinearLayout lnCheckAll;

    @Optional
    @OnClick({notify_layout, R.id.test_tap, R.id.cancel_forward, R.id.button_forward, R.id.approve_day, R.id.choose_file, R.id.img_search_start, R.id.cb_choose_all})
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case notify_layout:
                showNotiffy(mNotify_layout, this, mNumberOfNotify);
                break;
            case R.id.test_tap:
                break;
            case R.id.cancel_forward:
                onBackPressed();
                break;
            case R.id.button_forward:
                mInputForwardLogic.requestForwardPerson();
                break;
            case R.id.approve_day:
//                showDataPickDialog(edtApproveday, this);
                showTimeDialog(edtApproveday, this);
                break;
            case R.id.choose_file:
                showFileChooser();
                break;
            case R.id.img_search_start:
                searchBegin();
                break;
            case R.id.cb_choose_all:
                mInputForwardLogic.selectAllList(cbAll.isChecked());
                break;
            default:
                break;
        }
    }

    private void searchBegin() {
        if (!mSearchView.isShown()) {
            mSearchView.setVisibility(View.VISIBLE);
            imgSearchStart.setImageResource(R.drawable.cancel_x1);
            mSearchView.setIconified(false);
        } else {
            mSearchView.setVisibility(View.GONE);
            imgSearchStart.setImageResource(R.drawable.search_x1);
            mSearchView.setQuery("", false);
            mSearchView.clearFocus();
            mSearchView.setIconified(true);
            mInputForwardLogic.upDateListEventSearchView();
        }

    }

/*========================================
    Update notify number by broadcast
==========================================*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_forward);
        ButterKnife.bind(this);
        // create table send offline
        ReferenceView();
        showNotifiCation(mNumberOfNotify);
        tvTimeProcess.setText(getTimeNow());
        getTextIntent();
        initView();
        initActiobar(titleActionBar, true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.close_white_x1);
        tvProcessName.setText(Html.fromHtml(ProcessPerson));
        getInforAccountFromShareReferenced(this);
        setLookupScreen(getIntent().getStringExtra(LOOKUP_SCREEN));
        mSqLiteSendWaiting.QueryData(CREATE_TABLE_SQLLITE_SEND_WAITING);
        arrFileAttach = new ArrayList<>();
        mInputForwardLogic = new InputForwardLogic(this, this);
        mInputForwardLogic.setListInputPerSon();
        // check if report will be gone and besides visible, this code open aday
//        if (ForwardOrReport == TAP_REPORT_RESUILT) {
//            cbKhan.setVisibility(View.GONE);
//        } else {
//            cbKhan.setVisibility(View.VISIBLE);
//        }
        // temporary
    }

    private void initView() {
        switch (ForwardOrReport) {
            case TAP_FORWARD_PERSON:
            case TAP_REPORT_RESUILT:
            case TAP_14:
                break;
            case TAP_FORWARD_RELEASE:
                cbAll.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void getTextIntent() {
        iScreen = getIntent().getStringExtra(LOOKUP_SCREEN);
        WorkflowTransition = getIntent().getIntExtra(WORK_FLOW_TRAINSITION_ID, 0);
        DocumentID = getIntent().getLongExtra(DOCUMENTID, 0);
        ProcessPerson = getIntent().getStringExtra(PROCESSPERSON);
        if (getIntent().getStringExtra(RESOURCECODEID) != null) {
            resourceCodeId = getIntent().getStringExtra(RESOURCECODEID);
        }
        ForwardOrReport = getIntent().getIntExtra(REPORT_OR_FORWARD_OR_RELEASE, 0);
        if (ForwardOrReport==13){
            lnCheckAll.setVisibility(View.GONE);
        }
        titleActionBar = getIntent().getStringExtra(TITLE_ACTION_BAR);
        Log.d(KeyManager.TAG, "Here");
    }

    private void ReferenceView() {
        cbKhan.setVisibility(View.GONE);
        tvForward.setText(getIntent().getStringExtra(TITLE_ACTION_BAR));
//        tvDone.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (!mSearchView.isIconified()) {
//                    rlSearch.setBackgroundColor(Color.TRANSPARENT);
//                    tvDone.setVisibility(View.GONE);
//                    addOrRemoveProperty(rlSearch, RelativeLayout.CENTER_VERTICAL, true);
//                    mSearchView.setBackgroundResource(R.drawable.search_view_expand_background);
////                    mSearchView.onActionViewCollapsed();
//                    mSearchView.setQuery("", false);
//                    mSearchView.clearFocus();
//                    mSearchView.setIconified(true);
//                    //upDateListEventSearch();
//                    mInputForwardLogic.upDateListEventSearchView();
//                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                    params.removeRule(RelativeLayout.BELOW);
//                    lv.setLayoutParams(params);
//                    RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                    params2.setMargins(0, 0, 0, 30);
//                    lv.setLayoutParams(params2);
//                    mLinearPart_Top.setVisibility(View.VISIBLE);
//                    mLinearPart_Top.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down));
//                    mSearchView.setVisibility(View.GONE);
//                    lv.setVisibility(View.GONE);
//                    //lv.smoothScrollToPosition(0);
//                    mHandler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            mSearchView.setVisibility(View.VISIBLE);
//                            lv.setVisibility(View.VISIBLE);
//                            mSearchView.getLayoutParams().width = ViewGroup.LayoutParams.WRAP_CONTENT;
//                            mSearchView.setBackgroundResource(R.drawable.search_view_background);
//                            mSearchView.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down));
//                            lv.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down));
//
//                        }
//                    }, 300);
//                }
//            }
//        });
//        new SearchViewFormatter()
//                .setSearchBackGroundResource(R.drawable.search_view_expand_background)
//                .setSearchIconResource(R.drawable.ic_search_white_24dp, true, false) //true to icon inside edittext, false to outside
////                .setSearchVoiceIconResource(R.drawable.)
//                .setSearchTextColorResource(R.color.colorTextSearch)
//                .setSearchHintColorResource(R.color.colorTextSearch)
//                .setSearchCloseIconResource(R.drawable.ic_clear_white_24dp)
//                .setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS)
//                .format(mSearchView);
//        mSearchView.setQueryHint("Tìm kiếm");
//        mSearchView.setOnSearchClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                tvDone.setVisibility(View.VISIBLE);
//                addOrRemoveProperty(rlSearch, RelativeLayout.CENTER_VERTICAL, false);
//                rlSearch.setBackgroundColor(Color.parseColor("#8e8e93"));
//                mSearchView.setBackgroundResource(R.drawable.search_view_expand_background);
//                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                params.setMargins(30, 0, 30, 30);
//                params.addRule(RelativeLayout.BELOW, R.id.rlSearch);
//                mSearchView.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
//                mLinearPart_Top.setVisibility(View.GONE);
//                mLinearPart_Top.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down_out));
//                mSearchView.setVisibility(View.GONE);
//                lv.setVisibility(View.GONE);
//                mSearchView.setVisibility(View.VISIBLE);
//                lv.setVisibility(View.VISIBLE);
//                lv.setLayoutParams(params);
//                mSearchView.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up));
//                lv.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up));
//                RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                params2.setMargins(0, 0, 0, 30);
//                lv.setLayoutParams(params2);
//                mHandler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        mSearchView.setVisibility(View.VISIBLE);
//                        lv.setVisibility(View.VISIBLE);
//                        mSearchView.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up));
//                        lv.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up));
//                    }
//                }, 300);

//            }
//        });
//        mSearchView.setOnCloseListener(new SearchView.OnCloseListener() {
//            @Override
//            public boolean onClose() {
//                mInputForwardLogic.upDateListEventSearchView();
////                upDateListEventSearch();
//                addOrRemoveProperty(rlSearch, RelativeLayout.CENTER_VERTICAL, true);
//                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                params.removeRule(RelativeLayout.BELOW);
//                lv.setLayoutParams(params);
//                mLinearPart_Top.setVisibility(View.VISIBLE);
//                mLinearPart_Top.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down));
//                mSearchView.setVisibility(View.GONE);
//                lv.setVisibility(View.GONE);
//                tvDone.setVisibility(View.GONE);
//                rlSearch.setBackgroundColor(Color.TRANSPARENT);
//                mSearchView.setBackgroundResource(R.drawable.search_view_expand_background);
//                mHandler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        mSearchView.setVisibility(View.VISIBLE);
//                        lv.setVisibility(View.VISIBLE);
//                        mSearchView.getLayoutParams().width = ViewGroup.LayoutParams.WRAP_CONTENT;
//                        mSearchView.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down));
//                        lv.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down));
//                        mSearchView.setBackgroundResource(R.drawable.search_view_background);
//                        RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                        params2.setMargins(30, 0, 30, 30);
//                        lv.setLayoutParams(params2);
//                    }
//                }, 300);
//                return false;
//            }
//        });
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mInputForwardLogic.filterName(newText);
                return true;
            }
        });
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

    private void addOrRemoveProperty(View view, int property, boolean flag) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
        if (flag) {
            layoutParams.addRule(property);
        } else {
            layoutParams.removeRule(property);
        }
        view.setLayoutParams(layoutParams);
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
                            checkRunTimePermission(position, arrFileAttach, InputForwardActivity.this);
                        }
                    });
                }
                break;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


/*==========================================
    cover people reciever to json array
    select people send, people select first is reciever people, other people are Preparation reciever
============================================*/

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        if (!arrReceive.get(position).isDefault()) {
//            arrReceive.get(position).setDefault(true);
//            adapter.notifyDataSetChanged();
//            arrReceiveID.add("%7B'nguoinhanid':" + arrReceive.get(position).getReceivePersonID() + "," +
//                    "'tochucnhanid':" + arrReceive.get(position).getReceiveRoomID() + "%7D");
//            arrGetFirstID.add(new ReceivePerson(arrReceive.get(position).getReceivePersonID(),
//                    arrReceive.get(position).getReceiveRoomID()));
//
//        } else {
//            arrReceive.get(position).setDefault(false);
//            adapter.notifyDataSetChanged();
//            if (arrReceiveID.size() != 0) {
//                for (int i = 0; i < arrReceiveID.size(); i++) {
//                    if (arrReceiveID.get(i).toString().contains(arrReceive.get(position).getReceivePersonID())) {
//                        arrReceiveID.remove(i);
//                    }
//                }
//            }
//            if (arrGetFirstID.size() != 0) {
//                for (int i = 0; i < arrGetFirstID.size(); i++) {
//                    if (arrGetFirstID.get(i).getReceivePersonID().equals(arrReceive.get(position).getReceivePersonID())) {
//                        arrGetFirstID.remove(i);
//                    }
//                }
//            }
//        }

//        if (!arrReceive.get(position).isDefault()) {
//            arrReceive.get(position).setDefault(true);
//            for (int i = 0; i < arrReceiveTemporary.size(); i++) {
//                if (arrReceiveTemporary.get(i).getReceivePersonID() == arrReceive.get(position).getReceivePersonID())
//                    arrReceiveTemporary.get(i).setDefault(true);
//            }
//        } else {
//            arrReceive.get(position).setDefault(false);
//            for (int i = 0; i < arrReceiveTemporary.size(); i++) {
//                if (arrReceiveTemporary.get(i).getReceivePersonID() == arrReceive.get(position).getReceivePersonID())
//                    arrReceiveTemporary.get(i).setDefault(false);
//            }
//        }
//        adapter.notifyDataSetChanged();
    }

    @Override
    public SQLite getSqlite() {
        return mSqLite;
    }

    @Override
    public long getDocumentID() {
        return DocumentID;
    }

    @Override
    public int getForwardOrReportOrRelease() {
        return ForwardOrReport;
    }

    @Override
    public void setListInputPerson(ReceivePersonAdapter receivePersonAdapter) {
        lv.setAdapter(receivePersonAdapter);
    }

    @Override
    public ArrayList<AttachFile> getArrayAttach() {
        return arrFileAttach;
    }


    @Override
    public int getResourceCodeID() {
        return Integer.parseInt(resourceCodeId);
    }

    @Override
    public String getContent() {
        return edtProcessContent.getText().toString();
    }

    @Override
    public boolean isCheckEmail() {
        return cbEmail.isChecked();
    }

    @Override
    public boolean IsCheckUrgent() {
        return cbKhan.isChecked();
    }

    @Override
    public void UpdateDatabase() {
        mSqLiteSendWaiting.QueryData("INSERT OR REPLACE INTO sendwaiting(documentid, jsonRequest)" + " VALUES(" + "'" + DocumentID + "','" + mInputForwardLogic.getJsonWaiting() + "')");
        mSqLiteListDocument.QueryData("DELETE FROM '" + "M" + getOnlyNumerics(urlNA) + "' WHERE documentID = '" + DocumentID + "'");
    }

    @Override
    public String getScreen() {
        return iScreen;
    }

    @Override
    public void insertSendWaiting(JSONObject mJsonObject) {
        mSqLiteSendWaiting.QueryData("INSERT OR REPLACE INTO sendwaiting(documentid, jsonRequest)" + " VALUES(" + "'" + DocumentID + "','" + mJsonObject + "')");
    }

    @Override
    public void DeleteData() {
        if (null != urlNA) {
            mSqLiteListDocument.QueryData("DELETE FROM '" + "M" + getOnlyNumerics(urlNA) + "' WHERE jobId = '" + DocumentID + "'");
        }
    }

    @Override
    public void showProgress() {
        showProgressDialog(nULL_STRING, this, DIALOG_CENTER, true);
    }

    @Override
    public void closeProgress() {
        closeProgressDialog();
    }

    @Override
    public void showToast(String s) {
        ShowErrorToast(this, s);
    }

    @Override
    public String getDocumentNumber() {
        return getIntent().getStringExtra(DOCUMENT_NUMBER);
    }

    @Override
    public int getWorkFlowID() {
        return WorkflowTransition;
    }

    @Override
    public String getNameInput() {
        return titleActionBar;
    }

    @Override
    public void AllCheck(boolean checkAll) {
        cbAll.setChecked(checkAll);
    }


//    @Override
//    public String getTrainfer_14() {
//        return Trainfer_14;
//    }
//    public String docNoiDung_Tu_URL(String theUrl) {
//        StringBuilder content = new StringBuilder();
//        try {
//            // create a url object
//            URL url = new URL(theUrl);
//
//            // create a urlconnection object
//            URLConnection urlConnection = url.openConnection();
//            // wrap the urlconnection in a bufferedreader
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
//
//            String line;
//
//            // read from the urlconnection via the bufferedreader
//            while ((line = bufferedReader.readLine()) != null) {
//                content.append(line + "\n");
//            }
//            bufferedReader.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return content.toString();
//    }
//    private void initActiobar() {
//        mToolbar = (Toolbar) findViewById(R.id.app_bar);
//        mToolbar.setPadding(0, getStatusBarHeight(this), 0, 0);
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
    //    public void showDialogSaveSuccessDocument(final String msg, final String urlna) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(InputForwardActivity.this);
//        builder.setTitle(Html.fromHtml(msg));
//        builder.setCancelable(true);
//        builder.setPositiveButton(
//                getString(R.string.ok),
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        Intent intent = new Intent(InputForwardActivity.this, OfficalActivity.class);
//                        intent.putExtra(URLNA_COMEBACK_OFFICE, urlna);
//                        startActivity(intent);
//                    }
//                });
//        alertSaveSuccessDocument = builder.create();
//        alertSaveSuccessDocument.show();
//    }
    //    class ReadJsonInputDetail extends AsyncTask<String, Integer, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            return docNoiDung_Tu_URL(params[0]);
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            arrReceive = new ArrayList<ReceivePerson>();
//            try {
//                JSONArray mJsonArray = new JSONArray(s);
//                for (int i = 0; i < mJsonArray.length(); i++) {
//                    JSONObject mJsonObject = mJsonArray.getJSONObject(i);
//                    boolean mDefault = mJsonObject.getBoolean(DEFAULT);
//                    String mReceivePersonName = mJsonObject.getString(TENNGUOINHAN);
//                    String mReceiveRoomName = mJsonObject.getString(TENPHONGBAN);
//                    String mReceivePersonID = mJsonObject.getString(NGUOINHANID);
//                    String mReceiveRoomID = mJsonObject.getString(PHONGBANID);
//                    arrReceive.add(new ReceivePerson(mDefault, mReceivePersonName, mReceiveRoomName, mReceivePersonID, mReceiveRoomID));
//                }
//                adapter = new ReceivePersonAdapter(InputForwardActivity.this, arrReceive);
//                lv.setAdapter(adapter);
//                setListViewHeightBasedOnChildren(lv);
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            super.onPostExecute(s);
//        }
//    }
//    /*==========================================================
//    send resuilt contains fasle = fail else success
//============================================================*/
//
//    class ForwardJson extends AsyncTask<String, Integer, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
////            mJsonObject = new JSONObject();
////            try {
////                mJsonObject.put(MODULE, MODULE_PROCESSING_WORKING);
////                if (ForwardOrReport == 4) {
////                    mJsonObject.put(NEOTYPE, TYPE_REPORT_RESUILT);
////                } else {
////                    mJsonObject.put(NEOTYPE, TYPE_TRAINFER);
////                }
////                JSONObject mJsonObjectData = new JSONObject();
////                mJsonObjectData.put(JOB_ID, DocumentID);
////                mJsonObjectData.put(WORK_FLOW_TRAINSITION_ID, Integer.parseInt(TrainferID));
////                mJsonObjectData.put(RESOURCECODEID, Integer.parseInt(resourceCodeId));
////                mJsonObjectData.put(SCHEDULE_CONTENT, edtProcessContent.getText().toString());
//////                mJsonObjectData.put(RECEIVER_ID, Integer.parseInt(FirstPersonID));
//////                mJsonObjectData.put(RECEIVER_ORG_ID, Integer.parseInt(FisrtRoomID));
////                mJsonObjectData.put(TRAINFER_EMAIL, cbEmail.isChecked());
////                if (ForwardOrReport != 4) {
////                    mJsonObjectData.put(URGENT, cbKhan.isChecked());
////                }
////                mJsonObjectData.put(RECIPIENTS, mJsonArray);
////                mJsonObjectData.put(ATTACH_FILE, mJsonArrayAttach);
////                mJsonObject.put(DATA, mJsonObjectData);
////            } catch (JSONException e) {
////                e.printStackTrace();
////            }
////            return makePostRequest(params[0], mJsonObject.toString(), getUserid(), getPass());
//            return eventPostRequest(getModuleInfor(MODULE_PROCESSING_WORKING, InputForwardActivity.this), addJsonRequest(), getUserid(), getPass());
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
//            Intent intent = new Intent(InputForwardActivity.this, OfficalActivity.class);
//            //// TODO: 9/1/2017  comback screen
//            String iScreen = getIntent().getStringExtra(LOOKUP_SCREEN);
//            intent.putExtra(COME_BACK_FROM_SCREEN, iScreen);
//            intent.putExtra(INPUT_COME_BACK, iScreen);
//            closeProgressDialog();
//            if (resuilt.contains(FALSE)) {
////                mSumManager.showDialogUpdateError(getString(R.string.chuyen_that_bai), InputForwardActivity.this);
//                intent.putExtra(URLNA_COMEBACK_OFFICE, OfficalActivity.urlNA);
//                intent.putExtra(FORWARD_RESUILD, FALSE);
//            } else {
//                if (resuilt.equals(nULL_STRING)) {
////                    mSqLiteSendWaiting.QueryData("INSERT OR REPLACE INTO sendwaiting(documentid, jsonRequest)" + " VALUES(" +
////                            "'" + DocumentID + "','" + resourceCodeId + "','" + TrainferID + "','" + FirstPersonID + "','" + FisrtRoomID + "','" + ProcessContent + "','" + cbEmail.isChecked() + "','" + cbKhan.isChecked() + "','" + mJsonArray + "')");
//                    mSqLiteSendWaiting.QueryData("INSERT OR REPLACE INTO sendwaiting(documentid, jsonRequest)" + " VALUES(" + "'" + DocumentID + "','" + mJsonObject + "')");
//                    closeProgressDialog();
//                    intent.putExtra(URLNA_COMEBACK_OFFICE, OfficalActivity.urlNA);
//                } else {
//                    intent.putExtra(URLNA_COMEBACK_OFFICE, OfficalActivity.urlNA);
//
//                }
//                intent.putExtra(FORWARD_RESUILD, TRUE);
//                mSqLiteListDocument.QueryData("DELETE FROM '" + "M" + getOnlyNumerics(OfficalActivity.urlNA) + "' WHERE documentID = '" + DocumentID + "'");
//            }
//            intent.putExtra(DOCUMENT_NUMBER, getIntent().getStringExtra(DOCUMENT_NUMBER));
//            startActivity(intent);
//            super.onPostExecute(s);
//        }
//    }
    //    class LoadAllInputPerson extends AsyncTask<String, Integer, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            JSONObject mJsonObject = new JSONObject();
//            try {
//                mJsonObject.put(MODULE, MODULE_PROCESSING_WORKING);
//                mJsonObject.put(NEOTYPE, TYPE_INPUT_PERSON);
//                JSONObject mData = new JSONObject();
//                mData.put(JOB_ID, DocumentID);
//                mData.put(WORK_FLOW_TRAINSITION_ID, Integer.parseInt(TrainferID));
//                mData.put(KEYWORDS, "");
//                mData.put(ROW_PER_PAGE, -1);
//                mData.put(NUM_PAGE, -1);
//                mJsonObject.put(DATA, mData);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            return eventPostRequest(getModuleInfor(MODULE_PROCESSING_WORKING, InputForwardActivity.this), mJsonObject.toString(), getUserid(), getPass());
//        }
//
//
//        @Override
//        protected void onPostExecute(String s) {
//            AdapterPerson(s, true);
//            super.onPostExecute(s);
//        }
//    }
    // // TODO: 11/14/2017  doing here
//    class LoadMoreInputPerson extends AsyncTask<String, Integer, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            JSONObject mJsonObject = new JSONObject();
//            try {
//                mJsonObject.put(MODULE, MODULE_PROCESSING_WORKING);
//                mJsonObject.put(NEOTYPE, TYPE_INPUT_PERSON);
//                JSONObject mData = new JSONObject();
//                mData.put(JOB_ID, DocumentID);
//                mData.put(WORK_FLOW_TRAINSITION_ID, Integer.parseInt(TrainferID));
//                mData.put(KEYWORDS, "");
//                mData.put(ROW_PER_PAGE, 20);
//                mData.put(NUM_PAGE, PageLoad);
//                mJsonObject.put(DATA, mData);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            return eventPostRequest(getModuleInfor(MODULE_PROCESSING_WORKING, InputForwardActivity.this), mJsonObject.toString(), getUserid(), getPass());
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            AdapterPerson(s, false);
//            closeProgressDialog();
//            super.onPostExecute(s);
//        }
//    }
//    class LoadInputPerson extends AsyncTask<String, Integer, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            String s = "";
//            arrReceive = new ArrayList<ReceivePerson>();
//            Cursor mCursor = mSqLite.GetData(SELECT_FROM + "inputperson" + DocumentID);
//            mCursor.moveToFirst();
//            if (mCursor.getCount() != 0) {
//                s = mCursor.getString(1);
//            }
//            Log.d("object", s);
////            Cursor mCursor = mSqLite.GetData(SELECT_FROM + INPUT_PERSON_TABLE_SQLITE);
////            while (mCursor.moveToNext()) {
////                if (mCursor.getLong(0) == DocumentID) {
////                    arrReceive.add(new ReceivePerson(Boolean.valueOf(mCursor.getString(2)), mCursor.getString(3), mCursor.getString(5), mCursor.getString(4), mCursor.getString(6), mCursor.getString(7), false));
////                }
////            }
//            return s;
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
////            try {
////                JSONObject mJsonObject = new JSONObject(s);
////                JSONArray mJsonArray = mJsonObject.getJSONArray(DATA);
////                for (int i = 0; i < mJsonArray.length(); i++) {
////                    JSONObject mJsonObjectRow = mJsonArray.getJSONObject(i);
////                    boolean mDefault = mJsonObjectRow.getBoolean(DEFAULT);
////                    String mReceivePersonName = mJsonObjectRow.getString(RECEIVER_NAME);
////                    String mReceiveRoomName = mJsonObjectRow.getString(ORGANIZATION_NAME);
////                    String mReceivePersonID = mJsonObjectRow.getString(RECEIVER_ID);
////                    String mReceiveRoomID = mJsonObjectRow.getString(ORGANIZATION_ID);
////                    String mRoleName = mJsonObjectRow.getString(ROLE_NAME);
////                    arrReceive.add(new ReceivePerson(mDefault, mReceivePersonName, mReceiveRoomName, mReceivePersonID, mReceiveRoomID, mRoleName, false));
////                }
////                adapter = new ReceivePersonAdapter(InputForwardActivity.this, arrReceive);
////                // LinearLayout the same listview
//////                int adapterCount = adapter.getCount();
//////                for (int i = 0; i < adapterCount; i++) {
//////                    View item = adapter.getView(i, null, null);
//////                    mLinearList.addView(item);
//////                }
////
//////                setListViewHeightBasedOnChildren(lv);
////                //for recyclerView
//////                adapterRecycler = new RecyclerReceivePersonAdapter(InputForwardActivity.this, arrReceive);
//////                lv.setAdapter(adapterRecycler);
//////                lv.setLayoutManager(layoutManager);
////            } catch (JSONException e) {
////
////                e.printStackTrace();
////            }
//            lv.setAdapter(AdapterPerson(s));
//            closeProgressDialog();
//            super.onPostExecute(s);
//        }
//    }
//
//    private ReceivePersonAdapter AdapterPerson(String s) {
//        try {
//            JSONObject mJsonObject = new JSONObject(s);
//            JSONArray mJsonArray = mJsonObject.getJSONArray(DATA);
//            for (int i = 0; i < mJsonArray.length(); i++) {
//                JSONObject mJsonObjectRow = mJsonArray.getJSONObject(i);
//                boolean mDefault = mJsonObjectRow.getBoolean(DEFAULT);
//                String mReceivePersonName = mJsonObjectRow.getString(RECEIVER_NAME);
//                String mReceiveRoomName = mJsonObjectRow.getString(ORGANIZATION_NAME);
//                String mReceivePersonID = mJsonObjectRow.getString(RECEIVER_ID);
//                String mReceiveRoomID = mJsonObjectRow.getString(ORGANIZATION_ID);
//                String mRoleName = mJsonObjectRow.getString(ROLE_NAME);
//                arrReceive.add(new ReceivePerson(mDefault, mReceivePersonName, mReceiveRoomName, mReceivePersonID, mReceiveRoomID, mRoleName, false));
//                arrReceiveTemporary.add(new ReceivePerson(mDefault, mReceivePersonName, mReceiveRoomName, mReceivePersonID, mReceiveRoomID, mRoleName, false));
//                if (adapter != null && adapter.getCount() != 0) {
//                    adapter.notifyDataSetChanged();
//                } else {
//                    adapter = new ReceivePersonAdapter(InputForwardActivity.this, arrReceive, arrReceiveTemporary, new AdapterButtonClick() {
//                        @Override
//                        public void onBtnRemoveClick(int position) {
//
//                        }
//
//                        @Override
//                        public void addClick(int position) {
//
//                        }
//
//                        @Override
//                        public void mainCheck(int position) {
//                            switch (ForwardOrReport) {
//                                case TAP_FORWARD_PERSON:
//                                case TAP_REPORT_RESUILT:
//                                    if (!arrReceive.get(position).isDefault()) {
//                                        arrReceive.get(position).setDefault(true);
//                                        for (int i = 0; i < arrReceiveTemporary.size(); i++) {
//                                            if (arrReceiveTemporary.get(i).getReceivePersonID().equals(arrReceive.get(position).getReceivePersonID()))
//                                                arrReceiveTemporary.get(i).setDefault(true);
//                                        }
//                                    } else {
//                                        arrReceive.get(position).setDefault(false);
//                                        arrReceive.get(position).setMainPerson(false);
//                                        for (int i = 0; i < arrReceiveTemporary.size(); i++) {
//                                            if (arrReceiveTemporary.get(i).getReceivePersonID().equals(arrReceive.get(position).getReceivePersonID())) {
//                                                arrReceiveTemporary.get(i).setDefault(false);
//                                                arrReceiveTemporary.get(i).setMainPerson(false);
//                                            }
//                                        }
//                                    }
//                                    break;
//                                case TAP_FORWARD_RELEASE:
//                                    for (int i = 0; i < arrReceive.size(); i++) {
//                                        if (arrReceive.get(i).getReceivePersonID().equals(arrReceive.get(position).getReceivePersonID())) {
//                                            arrReceive.get(i).setDefault(true);
//                                            arrReceive.get(i).setMainPerson(true);
//                                        } else {
//                                            arrReceive.get(i).setDefault(false);
//                                            arrReceive.get(i).setMainPerson(false);
//                                        }
//                                    }
//                                    for (int i = 0; i < arrReceiveTemporary.size(); i++) {
//                                        if (arrReceiveTemporary.get(i).getReceivePersonID().equals(arrReceive.get(position).getReceivePersonID())) {
//                                            arrReceiveTemporary.get(i).setDefault(true);
//                                            arrReceiveTemporary.get(i).setMainPerson(true);
//                                        } else {
//                                            arrReceiveTemporary.get(i).setDefault(false);
//                                            arrReceiveTemporary.get(i).setMainPerson(false);
//                                        }
//                                    }
//                                    break;
//                            }
//                            adapter.notifyDataSetChanged();
//
//                        }
//                    });
//                }
////                if (LoadinBackGround) {
////                    arrReceiveTemporary.add(new ReceivePerson(mDefault, mReceivePersonName, mReceiveRoomName, mReceivePersonID, mReceiveRoomID, mRoleName, false));
////                } else {
////                    arrReceive.add(new ReceivePerson(mDefault, mReceivePersonName, mReceiveRoomName, mReceivePersonID, mReceiveRoomID, mRoleName, false));
////                    if (adapter != null && adapter.getCount() != 0) {
////                        adapter.notifyDataSetChanged();
////                    } else {
////                        adapter = new ReceivePersonAdapter(InputForwardActivity.this, arrReceive);
////                    }
////                }
//            }
//
//            // LinearLayout the same listview
////                int adapterCount = adapter.getCount();
////                for (int i = 0; i < adapterCount; i++) {
////                    View item = adapter.getView(i, null, null);
////                    mLinearList.addView(item);
////                }
//
////                setListViewHeightBasedOnChildren(lv);
//            //for recyclerView
////                adapterRecycler = new RecyclerReceivePersonAdapter(InputForwardActivity.this, arrReceive);
////                lv.setAdapter(adapterRecycler);
////                lv.setLayoutManager(layoutManager);
//
//        } catch (JSONException e) {
//
//            e.printStackTrace();
//        }
//        return adapter;
//    }
    //    private String addJsonRequest() {
//        mJsonObject = new JSONObject();
//        JSONObject mJsonObjectData = new JSONObject();
//        try {
//            mJsonObject.put(MODULE, MODULE_PROCESSING_WORKING);
//            mJsonObjectData.put(JOB_ID, DocumentID);
//            switch (ForwardOrReport) {
//                case TAP_REPORT_RESUILT:
//                    mJsonObjectData.put(WORK_FLOW_TRAINSITION_ID, Integer.parseInt(TrainferReport));
//                    mJsonObject.put(NEOTYPE, TYPE_REPORT_RESUILT);
//                    break;
//                case TAP_FORWARD_PERSON:
//                    mJsonObjectData.put(WORK_FLOW_TRAINSITION_ID, Integer.parseInt(TrainferID));
//                    mJsonObject.put(NEOTYPE, TYPE_TRAINFER);
//                    break;
//                case TAP_FORWARD_RELEASE:
//                    mJsonObjectData.put(WORK_FLOW_TRAINSITION_ID, Integer.parseInt(TrainferRelease));
//                    mJsonObject.put(NEOTYPE, TYPE_TRAINFER);
//                    break;
//            }
//            mJsonObjectData.put(RESOURCECODEID, Integer.parseInt(resourceCodeId));
//            mJsonObjectData.put(SCHEDULE_CONTENT, edtProcessContent.getText().toString());
////                mJsonObjectData.put(RECEIVER_ID, Integer.parseInt(FirstPersonID));
////                mJsonObjectData.put(RECEIVER_ORG_ID, Integer.parseInt(FisrtRoomID));
//            mJsonObjectData.put(TRAINFER_EMAIL, cbEmail.isChecked());
//            if (ForwardOrReport != 4) {
//                mJsonObjectData.put(URGENT, cbKhan.isChecked());
//            }
//            mJsonObjectData.put(RECIPIENTS, mJsonArray);
//            mJsonObjectData.put(ATTACH_FILE, mJsonArrayAttach);
//            mJsonObject.put(DATA, mJsonObjectData);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return mJsonObject.toString();
//    }
    //    public void filterName(String pattern) {
//        arrReceive.clear();
//        for (int i = 0; i < arrReceiveTemporary.size(); i++) {
//            if (removeAccent(arrReceiveTemporary.get(i).getReceivePersonName().toUpperCase()).contains(removeAccent(pattern.toUpperCase()))) {
//                arrReceive.add(arrReceiveTemporary.get(i));
//            }
//        }
//    }
    //        mSearchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (!hasFocus) {
//                    if (!mSearchView.isIconified()){
//                        mSearchView.setIconified(true);
//                    }
//
//                }
//            }
//        });
    ///*===================================================
//    Set List for people user wanna send document
//=====================================================*/
//
//    private void setListInputPerSon() {
//        showProgressDialog("", this, DIALOG_CENTER, false);
//        new LoadInputPerson().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
////        new LoadAllInputPerson().execute();
//    }
    //    private void upDateListEventSearch() {
//        //                if (arrReceive.size() > 0) {
////                    arrReceive.clear();
////                }
////                for (int i = 0; i < arrReceiveTemporary.size(); i++) {
////                    if (arrReceiveTemporary.get(i).isDefault()) {
////                        arrReceive.add(arrReceiveTemporary.get(i));
////                        arrReceiveTemporary.remove(i);
////                    }
////                }
////                arrReceive.addAll(arrReceiveTemporary);
////                arrReceiveTemporary.clear();
////                arrReceiveTemporary.addAll(arrReceive);
////                adapter.notifyDataSetChanged();
//    }
//private void ReferenceView() {
//    mLinearList = (LinearLayout) findViewById(R.id.layout_list);
//    lv = (ListView) findViewById(R.id.lv_process_person);
//    tvProcessName = (TextView) findViewById(R.id.process_name);
//    tvTimeProcess = (TextView) findViewById(R.id.time_process);
//    edtApproveday = (EditText) findViewById(R.id.approve_day);
//    btnForward = (TextView) findViewById(R.id.button_forward);
//    btnCancelForward = (TextView) findViewById(R.id.cancel_forward);
//    cbChiDao = (CheckBox) findViewById(R.id.chi_dao_cb);
//    cbEmail = (CheckBox) findViewById(R.id.email_cb);
//    cbKhan = (CheckBox) findViewById(R.id.khan_cb);
//    edtProcessContent = (EditText) findViewById(R.id.process_content);
//    mNumberOfNotify = (TextView) findViewById(R.id.number_notify);
//    mNotify_layout = (RelativeLayout) findViewById(notify_layout);
//    mLayoutForward = (RelativeLayout) findViewById(R.id._layout_forward_button);
//    mLayoutOther = (RelativeLayout) findViewById(R.id.layout_other);
//    testTap = (TextView) findViewById(R.id.test_tap);
//    mScrollView = (NestedScrollView) findViewById(R.id.croll_view);
//    lvAttach = (NonScrollListView) findViewById(R.id.attach_list);
//    tvChooseFile = (TextView) findViewById(R.id.choose_file);
//    mLinearPart_Top = (LinearLayout) findViewById(R.id.part_top);
//    rlSearch = (RelativeLayout) findViewById(R.id.rlSearch);
//    tvDone = (TextView) findViewById(R.id.tvDone);
//    mSearchView = (SearchView) findViewById(search_view);
//    mLayoutForward.setVisibility(View.VISIBLE);
//    mLayoutOther.setVisibility(View.VISIBLE);
//    tvDone.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            if (!mSearchView.isIconified()) {
//                rlSearch.setBackgroundColor(Color.TRANSPARENT);
//                tvDone.setVisibility(View.GONE);
//                mSearchView.setBackgroundResource(R.drawable.search_view_expand_background);
////                    mSearchView.onActionViewCollapsed();
//                mSearchView.setQuery("", false);
//                mSearchView.clearFocus();
//                mSearchView.setIconified(true);
//                //upDateListEventSearch();
//                mInputForwardLogic.upDateListEventSearchView();
//                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                params.removeRule(RelativeLayout.BELOW);
//                lv.setLayoutParams(params);
//                RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                params2.setMargins(0, 0, 0, 30);
//                lv.setLayoutParams(params2);
//                mLinearPart_Top.setVisibility(View.VISIBLE);
//                mLinearPart_Top.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down));
//                mSearchView.setVisibility(View.GONE);
//                lv.setVisibility(View.GONE);
//                //lv.smoothScrollToPosition(0);
//                mHandler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        mSearchView.setVisibility(View.VISIBLE);
//                        lv.setVisibility(View.VISIBLE);
//                        mSearchView.getLayoutParams().width = ViewGroup.LayoutParams.WRAP_CONTENT;
//                        mSearchView.setBackgroundResource(R.drawable.search_view_background);
//                        mSearchView.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down));
//                        lv.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down));
//
//                    }
//                }, 300);
//            }
//        }
//    });
//    new SearchViewFormatter()
//            .setSearchBackGroundResource(R.drawable.search_view_expand_background)
//            .setSearchIconResource(R.drawable.ic_search_white_24dp, true, false) //true to icon inside edittext, false to outside
////                .setSearchVoiceIconResource(R.drawable.)
//            .setSearchTextColorResource(R.color.colorTextSearch)
//            .setSearchHintColorResource(R.color.colorTextSearch)
//            .setSearchCloseIconResource(R.drawable.ic_clear_white_24dp)
//            .setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS)
//            .format(mSearchView);
//    mSearchView.setQueryHint("Tìm kiếm");
//    mSearchView.setOnSearchClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            tvDone.setVisibility(View.VISIBLE);
//            rlSearch.setBackgroundColor(Color.parseColor("#8e8e93"));
//            mSearchView.setBackgroundResource(R.drawable.search_view_expand_background);
//            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//            params.setMargins(0, 0, 0, 30);
//            params.addRule(RelativeLayout.BELOW, R.id.rlSearch);
//            mSearchView.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
//            mLinearPart_Top.setVisibility(View.GONE);
////                mLinearPart_Top.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down_out));
//            mSearchView.setVisibility(View.GONE);
//            lv.setVisibility(View.GONE);
//            mSearchView.setVisibility(View.VISIBLE);
//            lv.setVisibility(View.VISIBLE);
//            lv.setLayoutParams(params);
//            mSearchView.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up));
//            lv.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up));
////                RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
////                params2.setMargins(0, 0, 0, 30);
////                lv.setLayoutParams(params2);
//            mHandler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
////                        mSearchView.setVisibility(View.VISIBLE);
////                        lv.setVisibility(View.VISIBLE);
////                        mSearchView.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up));
////                        lv.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up));
//                }
//            }, 300);
//
//        }
//    });
//    mSearchView.setOnCloseListener(new SearchView.OnCloseListener() {
//        @Override
//        public boolean onClose() {
//            mInputForwardLogic.upDateListEventSearchView();
////                upDateListEventSearch();
//            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//            params.removeRule(RelativeLayout.BELOW);
//            lv.setLayoutParams(params);
//            mLinearPart_Top.setVisibility(View.VISIBLE);
//            mLinearPart_Top.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down));
//            mSearchView.setVisibility(View.GONE);
//            lv.setVisibility(View.GONE);
//            tvDone.setVisibility(View.GONE);
//            rlSearch.setBackgroundColor(Color.TRANSPARENT);
//            mSearchView.setBackgroundResource(R.drawable.search_view_expand_background);
//            mHandler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    mSearchView.setVisibility(View.VISIBLE);
//                    lv.setVisibility(View.VISIBLE);
//                    mSearchView.getLayoutParams().width = ViewGroup.LayoutParams.WRAP_CONTENT;
//                    mSearchView.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down));
//                    lv.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down));
//                    mSearchView.setBackgroundResource(R.drawable.search_view_background);
//                    RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                    params2.setMargins(0, 0, 0, 30);
//                    lv.setLayoutParams(params2);
//                }
//            }, 300);
//            return false;
//        }
//    });
//
//    mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//        @Override
//        public boolean onQueryTextSubmit(String query) {
//            return true;
//        }
//
//        @Override
//        public boolean onQueryTextChange(String newText) {
//            mInputForwardLogic.filterName(newText);
//            return true;
//        }
//    });
//
//    tvChooseFile.setOnClickListener(this);
//    testTap.setOnClickListener(this);
//    btnCancelForward.setOnClickListener(this);
//    btnForward.setOnClickListener(this);
//    edtApproveday.setOnClickListener(this);
//    mNotify_layout.setOnClickListener(this);
//}
//    /*===============================
//    forward button send document to people reciever if do
//    not have connection it saved with sqllite and send with
//    SendDocumentService
//=================================*/
//    public void onClick(View v) {
//        int id = v.getId();
//        switch (id) {
//            case R.id.test_tap:
//                break;
//            case R.id.cancel_forward:
//                onBackPressed();
////                mSqLiteSendWaiting.QueryData("DROP TABLE IF EXISTS sendwaiting");
//                break;
//            case R.id.button_forward:
//                mInputForwardLogic.requestForwardPerson();
////                RequestForward();
//                break;
//            case R.id.approve_day:
//                showDataPickDialog(edtApproveday, this);
//                break;
//            case notify_layout:
//                showNotiffy(mNotify_layout, this, mNumberOfNotify);
//                break;
//            case R.id.choose_file:
//                showFileChooser();
//                break;
//        }
//    }

//    private void RequestForward() {
    //                mJsonArrayAttach = new JSONArray();
//                for (int i = 0; i < arrFileAttach.size(); i++) {
//                    JSONObject mJsonObject = new JSONObject();
//                    try {
//                        mJsonObject.put(NAME, arrFileAttach.get(i).getFileOnlyName());
//                        mJsonObject.put(EXTENSION, arrFileAttach.get(i).getFileTyPe());
//                        mJsonObject.put(SCHEDULE_CONTENT, arrFileAttach.get(i).getBase64Code().replace("\n", ""));
//                        mJsonArrayAttach.put(mJsonObject);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//                showProgressDialog(nULL_STRING, InputForwardActivity.this, DIALOG_CENTER, true);
////                if (arrGetFirstID.size() != 0) {
////                CheckEmail = cbEmail.isChecked();
////                CheckKhan = cbKhan.isChecked();
////                    ProcessContent = edtProcessContent.getText().toString();
////                    FirstPersonID = arrGetFirstID.get(0).getReceivePersonID();
////                    Log.d(TAG, FirstPersonID);
////                    FisrtRoomID = arrGetFirstID.get(0).getReceiveRoomID();
////                    ArrayList<String> arrReceiveIDtemporary = new ArrayList<String>();
////                    int sizeReceiveID = arrReceiveID.size();
////                    if (sizeReceiveID > 1) {
////                        for (int i = 1; i < sizeReceiveID; i++) {
////                            arrReceiveIDtemporary.add(arrReceiveID.get(i).toString());
////                        }
////                        JSONArray jsArray = new JSONArray(arrReceiveIDtemporary);
////                        Log.d(TAG, jsArray + nULL_STRING);
////                        mStringReceive = (jsArray + nULL_STRING).replace("\"", nULL_STRING);
////                        Log.d(TAG, mStringReceive);
////                    } else {
////                        mStringReceive = "[]";
////                    }
//                mJsonArray = new JSONArray();
//                for (int i = 0; i < arrReceive.size(); i++) {
//                    if (arrReceive.get(i).isDefault()) {
//                        JSONObject mJsonObject = new JSONObject();
//                        try {
//                            mJsonObject.put(RECEIVER_ID, arrReceive.get(i).getReceivePersonID());
//                            mJsonObject.put(RECEIVER_ORG_ID, arrReceive.get(i).getReceiveRoomID());
//                            mJsonObject.put(MAIN_RECEIVE, arrReceive.get(i).isMainPerson());
//                            mJsonArray.put(mJsonObject);
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                }
//                Log.d("JsonArrayPerson: ", mJsonArray.toString());
//                if (isNetworkAvailable(this)) {
////                    runOnUiThread(new Runnable() {
////                        @Override
////                        public void run() {
////                            boolean isHaveMainReceiver = false;
////                            for (int i = 0; i < arrReceive.size(); i++) {
////                                if (arrReceive.get(i).isMainPerson()) {
////                                    isHaveMainReceiver = true;
////                                    break;
////                                } else {
////                                    isHaveMainReceiver = false;
////                                }
////                            }
////                            if (isHaveMainReceiver == true) {
////                                new ForwardJson().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, getLink() + URL_CENTER_6_1);
////                            } else {
////                                ShowErrorToast(InputForwardActivity.this, "Vui lòng chọn người xử lý chính");
////                            }
////                        }
////                    });
//                    boolean isHaveMainReceiver = false;
//                    for (int i = 0; i < arrReceive.size(); i++) {
//                        if (arrReceive.get(i).isMainPerson()) {
//                            isHaveMainReceiver = true;
//                            break;
//                        } else {
//                            isHaveMainReceiver = false;
//                        }
//                    }
//                    if (isHaveMainReceiver == true) {
//                        new NeoTask(this, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(this, ACTION_FORWARD_PERSON, addJsonRequest(), getLink() + URL_CENTER_6_1));
////                        new ForwardJson().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, getLink() + URL_CENTER_6_1);
//                    } else {
//                        ShowErrorToast(InputForwardActivity.this, "Vui lòng chọn người xử lý chính");
//                    }
//                } else {
//                    // insert to database SQLite
//                    addJsonRequest();
//                    mSqLiteSendWaiting.QueryData("INSERT OR REPLACE INTO sendwaiting(documentid, jsonRequest)" + " VALUES(" + "'" + DocumentID + "','" + mJsonObject + "')");
//                    mSqLiteListDocument.QueryData("DELETE FROM '" + "M" + getOnlyNumerics(OfficalActivity.urlNA) + "' WHERE documentID = '" + DocumentID + "'");
//                    closeProgressDialog();
////                        showDialogSaveSuccessDocument(getString(R.string.da_luu_vao_hop_thu_di), OfficalActivity.urlNA);
//                    Intent intent = new Intent(InputForwardActivity.this, OfficalActivity.class);
//                    intent.putExtra(URLNA_COMEBACK_OFFICE, OfficalActivity.urlNA);
//                    intent.putExtra(DOCUMENT_NUMBER, getIntent().getStringExtra(DOCUMENT_NUMBER));
//                    startActivity(intent);
//                }
//    }
    //    @Override
//    public int getTrainferReport() {
//        return Integer.parseInt(TrainferReport);
//    }
//
//    @Override
//    public int getTrainferID() {
//        return Integer.parseInt(TrainferID);
//    }
//
//    @Override
//    public int getTrainferRelease() {
//        return Integer.parseInt(TrainferRelease);
//    }
}
