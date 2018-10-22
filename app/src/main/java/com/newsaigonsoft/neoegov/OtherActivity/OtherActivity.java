package com.newsaigonsoft.neoegov.OtherActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.Optional;

import com.newsaigonsoft.neoegov.Adapter.DocCnLocalAdapter;
import com.newsaigonsoft.neoegov.Adapter.DocCnReceiverAdapter;
import com.newsaigonsoft.neoegov.Adapter.DocCnSendAdapter;
import com.newsaigonsoft.neoegov.Adapter.HandleChangeAdapter;
import com.newsaigonsoft.neoegov.DetailActivity.DetailForwardActivity;
import com.newsaigonsoft.neoegov.GsonObject.GsonDocReceiptConnects;
import com.newsaigonsoft.neoegov.GsonObject.GsonLocalConnects;
import com.newsaigonsoft.neoegov.GsonObject.GsonSendConnects;
import com.newsaigonsoft.neoegov.MyInterface.OpenFileItemClick;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.DocConnectionLocalRow;
import com.newsaigonsoft.neoegov.Subjects.DocConnectionSendRow;
import com.newsaigonsoft.neoegov.Subjects.HandleChangeRow;
import com.newsaigonsoft.neoegov.Subjects.KeyManager;

import static com.newsaigonsoft.neoegov.R.id.notify_layout;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.ATTACH_FILE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.BRIEF_CONTENT;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.BRIEF_CONTENT_FULL;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.CHANGE_DATE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.CREATE_DATE_DOC;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DOCUMENT_BOOK;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DOCUMENT_TYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DOC_LOCAL_CONNECTS;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DOC_NUMBER;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DOC_RECEIPT_CONNECTS;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DOC_SEND_CONNECTS;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.HANDLE_WAY_AFTER;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.HANDLE_WAY_BEFORE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.HANDLE_WAY_CHANGE_LOG;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.REASON;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.SIGNER_NAME;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.USER_CHANGE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DOCUMENTID;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.IS_SCREEN;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LOOKUP_COMING;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LOOKUP_INTERNAL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LOOKUP_SENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.NUMBER_NOTIFY;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.OHTER_FUNCTION;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TITLE_ACTION_BAR;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.nULL_STRING;

public class OtherActivity extends OtherBase implements OpenFileItemClick, OtherView {

    @BindView(R.id.no_list)
    LinearLayout lnNoList;
    @BindView(R.id.list_other)
    ListView lv;
    @BindView(R.id.setting_layout)
    RelativeLayout btnSettingActionbar;
    @BindView(R.id.title_error)
    TextView tvTitleError;
    @BindView(R.id.number_notify)
    TextView mNumberOfNotify;
    @BindView(notify_layout)
    RelativeLayout mNotify_layout;

    @Optional
    @OnClick(notify_layout)
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case notify_layout:
                showPopupWinDownNotificationEvent(this, mNotify_layout);
                setDefaults(NUMBER_NOTIFY, "0", this);
                showNotifiCation(mNumberOfNotify);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        ButterKnife.bind(this);
        initView();
        getListIntent();
        initActiobar();
        getInforAccountFromShareReferenced(this);
//        initBroadcast();
        showNotifiCation(mNumberOfNotify);
    }

    private void initView() {
        btnSettingActionbar.setVisibility(View.GONE);
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

    private void initActiobar() {
        mToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(nULL_STRING);
        TextView titleActionbar = (TextView) findViewById(R.id.title_actionbar);
        titleActionbar.setText(getIntent().getStringExtra(TITLE_ACTION_BAR));
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.close_white_x1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setPadding(0, getStatusBarHeight(this), 0, 0);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    private void getListIntent() {
        OtherFunction = getIntent().getStringExtra(OHTER_FUNCTION);
        try {
            switch (OtherFunction) {
                case HANDLE_WAY_CHANGE_LOG:
                    arrHandleChange = new ArrayList<>();
                    String json = readFromFile(this, HANDLE_WAY_CHANGE_LOG);
                    Log.d(KeyManager.TAG, json);
                    JSONArray mJsonArrayHandleChange = new JSONArray(readFromFile(this, HANDLE_WAY_CHANGE_LOG));
                    for (int i = 0; i < mJsonArrayHandleChange.length(); i++) {
                        JSONObject mObject = mJsonArrayHandleChange.getJSONObject(i);
                        String changeDate = mObject.getString(CHANGE_DATE);
                        String handleWayBefore = mObject.getString(HANDLE_WAY_BEFORE);
                        String reason = mObject.getString(REASON);
                        String userChange = mObject.getString(USER_CHANGE);
                        String handleWayAfter = mObject.getString(HANDLE_WAY_AFTER);
                        arrHandleChange.add(new HandleChangeRow(changeDate, handleWayBefore, reason, userChange, handleWayAfter));
                    }
                    setAdapterHandleChange(arrHandleChange);
                    break;
                case DOC_RECEIPT_CONNECTS:
                    List<GsonDocReceiptConnects> arrGsonDocReceiptConnects = Arrays.asList(getGson().fromJson(readFromFile(this, DOC_RECEIPT_CONNECTS), GsonDocReceiptConnects[].class));
                    setAdapterDocConnectReceiver(arrGsonDocReceiptConnects);
                    break;
                case DOC_SEND_CONNECTS:
                    List<GsonSendConnects> arrGsonDocSendConnects = Arrays.asList(getGson().fromJson(readFromFile(this, DOC_SEND_CONNECTS), GsonSendConnects[].class));
                    setAdapterDocConnectSend(arrGsonDocSendConnects);
                    break;
                case DOC_LOCAL_CONNECTS:
                    List<GsonLocalConnects> arrGsonLocalConnects = Arrays.asList(getGson().fromJson(readFromFile(this, DOC_LOCAL_CONNECTS), GsonLocalConnects[].class));
                    setAdatperDocConnectLocal(arrGsonLocalConnects);
                    break;
                default:
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setAdatperDocConnectLocal(List<GsonLocalConnects> arr) {
        arrDocCnLocal = arr;
        if (arrDocCnLocal.size() == 0) {
            lnNoList.setVisibility(View.VISIBLE);
            tvTitleError.setText("Không có văn bản liên kết nội bộ");
        } else {
            DocCnLocalAdapter adapter = new DocCnLocalAdapter(OtherActivity.this, arrDocCnLocal, OtherActivity.this);
            lv.setAdapter(adapter);
        }
    }

    public void setAdapterHandleChange(List<HandleChangeRow> arrHandleChange) {
        if (arrHandleChange.size() == 0) {
            lnNoList.setVisibility(View.VISIBLE);
            tvTitleError.setText("Không có thay đổi cách thức xử lý");
        } else {
            HandleChangeAdapter handleChangeAdapter = new HandleChangeAdapter(this, arrHandleChange);
            lv.setAdapter(handleChangeAdapter);
        }
    }

    public void setAdapterDocConnectReceiver(List<GsonDocReceiptConnects> arr) {
        arrDocReciept = arr;
        if (arrDocReciept.size() == 0) {
            lnNoList.setVisibility(View.VISIBLE);
            tvTitleError.setText("Không có văn bản liên kết đến");
        } else {
            DocCnReceiverAdapter adapter = new DocCnReceiverAdapter(OtherActivity.this, arrDocReciept, OtherActivity.this);
            lv.setAdapter(adapter);
        }
    }

    @OnItemClick(R.id.list_other)
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, DetailForwardActivity.class);
        long jobID;
        switch (OtherFunction) {
            case HANDLE_WAY_CHANGE_LOG:
                break;
            case DOC_RECEIPT_CONNECTS:
                jobID = arrDocReciept.get(position).getDocReceiptId();
                intent.putExtra(DOCUMENTID, jobID);
                intent.putExtra(IS_SCREEN, LOOKUP_COMING);
                break;
            case DOC_SEND_CONNECTS:
                jobID = arrDocSend.get(position).getDocSendId();
                intent.putExtra(DOCUMENTID, jobID);
                intent.putExtra(IS_SCREEN, LOOKUP_SENT);
                break;
            case DOC_LOCAL_CONNECTS:
                jobID = arrDocCnLocal.get(position).getDocLocalId();
                intent.putExtra(DOCUMENTID, jobID);
                intent.putExtra(IS_SCREEN, LOOKUP_INTERNAL);
                break;
            default:
                break;
        }
        startActivity(intent);
    }

    public void setAdapterDocConnectSend(List<GsonSendConnects> arr) {
        arrDocSend = arr;
        if (arrDocSend.size() == 0) {
            lnNoList.setVisibility(View.VISIBLE);
            tvTitleError.setText("Không có văn bản liên kết đi");
        } else {
            DocCnSendAdapter adapter = new DocCnSendAdapter(this, arrDocSend, this);
            lv.setAdapter(adapter);
        }
    }


    @Override
    public void showDialogUnLink(int position) {
        dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_cancel_doc_connect, null);
        TextView tvOk = (TextView) dialogView.findViewById(R.id.ok);
        TextView tvCancel = (TextView) dialogView.findViewById(R.id.cancel);
        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowErrorToast(OtherActivity.this, getString(R.string.building_function));
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        dialogBuilder.setView(dialogView);
        alertDialog = dialogBuilder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        alertDialog.getWindow().setLayout((width / 6) * 5, WindowManager.LayoutParams.WRAP_CONTENT);
    }
}
