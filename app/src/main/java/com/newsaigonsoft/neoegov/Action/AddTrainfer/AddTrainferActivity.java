package com.newsaigonsoft.neoegov.Action.AddTrainfer;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.newsaigonsoft.neoegov.Adapter.AddTrainferAdapter;
import com.newsaigonsoft.neoegov.Adapter.DialogMenuDetailAdapter;
import com.newsaigonsoft.neoegov.Adapter.FileAttachAdapter;
import com.newsaigonsoft.neoegov.Adapter.TrainferSelectAdapter;
import com.newsaigonsoft.neoegov.CustomViewListExpand.NonScrollListView;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.AttachFile;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

import static com.newsaigonsoft.neoegov.R.id.notify_layout;
import static com.newsaigonsoft.neoegov.R.id.wrap_content;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DIALOG_CENTER;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DOCUMENTID;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAG;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TITLE_ACTION_BAR;

public class AddTrainferActivity extends BaseAddTrainfer implements AddTrainferView {

    @BindView(R.id.add_trainfer_list)
    NonScrollListView lv;
    @BindView(R.id.trainfer_content)
    EditText edtContent;
    @BindView(R.id.save)
    TextView tvSave;
    @BindView(R.id.save_and_send)
    TextView tvSaveAndSend;
    @BindView(R.id.choose_file)
    TextView tvChooseFile;
    @BindView(R.id.number_notify)
    TextView mNumberOfNotify;
    @BindView(R.id.notify_layout)
    RelativeLayout mNotify_layout;
    @BindView(R.id.spn_type)
    Spinner spnSelect;
    @BindView(R.id.attach_list)
    NonScrollListView lvAttach;
    @BindView(R.id.tv_select_add_trainfer)
    TextView tvSelect;

    @Optional
    @OnClick({R.id.choose_file, R.id.save, R.id.save_and_send, R.id.notify_layout, R.id.tv_select_add_trainfer})
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case notify_layout:
                showNotiffy(mNotify_layout, this, mNumberOfNotify);
                break;
            case R.id.save:
                mAddTrainferLogic.requestAddTrainfer(false, edtContent.getText().toString().trim().length(), arrFileAttach);
                break;
            case R.id.save_and_send:
                mAddTrainferLogic.requestAddTrainfer(true, edtContent.getText().toString().trim().length(), arrFileAttach);
                break;
            case R.id.choose_file:
                showFileChooser();
                break;
            case R.id.tv_select_add_trainfer:
                showDialogSelect(listSelectTranferNews);
                break;

        }
    }

    private void showDialogSelect(final List<TrainferSelectAdapter.TrainferItems> listSelectTranfer) {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_trainfer, null);
        ListView lv = (ListView) dialogView.findViewById(R.id.lv_select_trainfer_type);
        ImageView imgCancel  = (ImageView) dialogView.findViewById(R.id.cancel_select);
        final TrainferSelectAdapter adapter = new TrainferSelectAdapter(this, listSelectTranfer);
        lv.setAdapter(adapter);
        dialogBuilder.setView(dialogView);
        alertDialog = dialogBuilder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for (int i = 0; i < listSelectTranfer.size(); i++){
                    listSelectTranfer.get(i).setSelect(i==position ? true : false);
                    if (i==position){
                        tvSelect.setText(listSelectTranfer.get(i).getTitle());
                    }
                }
                adapter.notifyDataSetChanged();
                alertDialog.dismiss();
            }
        });





//        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
//
//        lp.copyFrom(alertDialog.getWindow().getAttributes());
//        lp.width = ;
//        lp.height = wrap_content;
//        lp.x=-170;
//        lp.y=100;
//        alertDialog.getWindow().setAttributes(lp);


        imgCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trainfer);
        ButterKnife.bind(this);
        mAddTrainferLogic = new AddTrainferLogic(this, this);
        getStringIntent();
        initActiobar(titleActionBar, true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.close_white_x1);
        getInforAccountFromShareReferenced(this);
        showNotifiCation(mNumberOfNotify);
        arrFileAttach = new ArrayList<>();
        mAddTrainferLogic.setListSelectTrainfer(listSelectTranferNews);
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
    public long getTaskID() {
        return TaskID;
    }

    @Override
    public String getContent() {
        return edtContent.getText().toString();
    }

    @Override
    public int getSelectItem() {
        int type = 0;
        for (int i = 0; i < listSelectTranferNews.size(); i++){
            if (listSelectTranferNews.get(i).isSelect())
                type = i + 1;
        }
        return type;
    }

    @Override
    public void setListResuiltTrainfer(AddTrainferAdapter adapter) {
        lv.setAdapter(adapter);
        lvAttach.setAdapter(null);
    }

    @Override
    public void showProgress(String s, AddTrainferLogic addTrainferLogic, String dialogCenter, boolean b) {
        showProgressDialog("", this, DIALOG_CENTER, true);
    }

    @Override
    public void closeProgress() {
        closeProgressDialog();
    }

    @Override
    public void ToastError(String s) {
        ShowErrorToast(this);
    }

    @Override
    public void setFirstItem(List<TrainferSelectAdapter.TrainferItems> listSelectTranfer) {
        listSelectTranferNews = listSelectTranfer;
        tvSelect.setText(listSelectTranfer.get(0).getTitle());
    }


    private void getStringIntent() {
        titleActionBar = getIntent().getStringExtra(TITLE_ACTION_BAR);
        TaskID = getIntent().getLongExtra(DOCUMENTID, 0);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
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
                            checkRunTimePermission(position, arrFileAttach, AddTrainferActivity.this);
                        }
                    });
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
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
//        lv = (NonScrollListView) findViewById(R.id.add_trainfer_list);
//        edtContent = (EditText) findViewById(R.id.trainfer_content);
//        tvSave = (TextView) findViewById(R.id.save);
//        tvSaveAndSend = (TextView) findViewById(R.id.save_and_send);
//        mNotify_layout = (RelativeLayout) findViewById(R.id.notify_layout);
//        mNotify_layout.setVisibility(View.VISIBLE);
//        mNumberOfNotify = (TextView) findViewById(R.id.number_notify);
//        spnSelect = (Spinner) findViewById(R.id.spn_type);
//        tvChooseFile = (TextView) findViewById(R.id.choose_file);
//        lvAttach = (NonScrollListView) findViewById(R.id.attach_list);
//        tvChooseFile.setOnClickListener(this);
//        tvSave.setOnClickListener(this);
//        tvSaveAndSend.setOnClickListener(this);
//        mNotify_layout.setOnClickListener(this);
//    }
    //    @Override
//    public void onClick(View v) {
//        int id = v.getId();
//        switch (id) {
//            case notify_layout:
//                showNotiffy(mNotify_layout, this, mNumberOfNotify);
//                break;
//            case R.id.save:
//                mAddTrainferLogic.requestAddTrainfer(false, edtContent.getText().toString().trim().length(), arrFileAttach);
//                break;
//            case R.id.save_and_send:
//                mAddTrainferLogic.requestAddTrainfer(true, edtContent.getText().toString().trim().length(), arrFileAttach);
//                break;
//            case R.id.choose_file:
//                showFileChooser();
//                break;
//
//        }
//    }
    //    class AddTranfer extends AsyncTask<String, Integer, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            JSONObject mJsonObject = new JSONObject();
//            try {
//                mJsonObject.put(MODULE, MODULE_LOOKUP_DOCUMENT);
//                mJsonObject.put(NEOTYPE, TYPE_ADD_TRANFER);
//                JSONObject mData = new JSONObject();
//                mData.put(TASK_ID, TaskID);
//                mData.put(EXCHANGE_TASK_INFO_ID, 0);
//                mData.put(SCHEDULE_CONTENT, edtContent.getText().toString());
//                mData.put(TYPE, spnSelect.getSelectedItemPosition() + 1);
//                mData.put(SEND_DATA, sendData);
//                mData.put(ATTACH_FILE, mJsonArrayAttach);
//                mJsonObject.put(DATA, mData);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
////            return makePostRequest(params[0], mJsonObject.toString(), getUserid(), getPass());
//            return eventPostRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, AddTrainferActivity.this), mJsonObject.toString(), getUserid(), getPass());
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            try {
//                JSONObject mJsonObject = new JSONObject(s);
//                int mResuilt = mJsonObject.getInt(DATA);
//                if (mResuilt==0) {
//                    String content = edtContent.getText().toString();
//                    Calendar mCalendar = Calendar.getInstance();
//                    String timeNow = mSimpleDateFormat.format(mCalendar.getTime());
//                    if (!content.equals("")) {
//                        arrTrainfer.add(new AddTrainferRow(getDefaults(FULLNAME_USER, AddTrainferActivity.this), "[" + timeNow + "]", content, arrFileAttach));
//                        AddTrainferAdapter adapter = new AddTrainferAdapter(AddTrainferActivity.this, arrTrainfer);
//                        lv.setAdapter(adapter);
//                        lvAttach.setAdapter(null);
//                    }
//                } else {
//                    Toast.makeText(AddTrainferActivity.this, "Yêu cầu xử lý không thành công", Toast.LENGTH_SHORT).show();
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            closeProgressDialog();
//            super.onPostExecute(s);
//        }
//    }

//    private void initBroadcast() {
//        IntentFilter filter = new IntentFilter(BROADCASTLISTENNER);
//        this.registerReceiver(mBroadcastReceiver, filter);
//    }
}
