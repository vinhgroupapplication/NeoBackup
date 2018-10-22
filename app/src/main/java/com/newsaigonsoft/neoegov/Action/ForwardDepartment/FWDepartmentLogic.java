package com.newsaigonsoft.neoegov.Action.ForwardDepartment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.newsaigonsoft.neoegov.AVolleyManager.VolleyTask;
import com.newsaigonsoft.neoegov.AVolleyManager.VolleyTaskCompletedListenner;
import com.newsaigonsoft.neoegov.Adapter.ChangeHandlerAdapter;
import com.newsaigonsoft.neoegov.Adapter.InputForwardDepartmentAdapter;
import com.newsaigonsoft.neoegov.Adapter.ReceiversDepartmentAdapter;
import com.newsaigonsoft.neoegov.AsynTaskManager.AsyncTaskCompleteListener;
import com.newsaigonsoft.neoegov.GsonObject.GsonDepartment;
import com.newsaigonsoft.neoegov.GsonObject.GsonHandle;
import com.newsaigonsoft.neoegov.GsonObject.GsonResuilt;
import com.newsaigonsoft.neoegov.MyInterface.AdapterButtonClick;
import com.newsaigonsoft.neoegov.OfficalActivity.OfficalActivity;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.ChangeHandleRow;
import com.newsaigonsoft.neoegov.Subjects.DialogFalseRow;
import com.newsaigonsoft.neoegov.Subjects.InputForwardDeparmentRow;
import com.newsaigonsoft.neoegov.Subjects.ReceiversRow;
import com.newsaigonsoft.neoegov.Subjects.ResuiltObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.ALLOW_EXTENSION_PROCESS;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.ALL_DAY;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.ATTACH_FILE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DATA;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.END_DATE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.EXPIRATION_DATE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.EXTENSION;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.HOUR_NUMBER;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.JOB_ID;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MAIN_RECEIVE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MODULE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.NAME;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.NEOTYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MODULE_PROCESSING_WORKING;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.PROCESS_DAYS;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.PROCESS_TYPE_ID;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.RECEIVERS_LIST;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.RECEIVER_ID;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.RECEIVE_ORG_ID;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.RECIPIENTS;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.RESOURCE_CODE_ID;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.SCHEDULE_CONTENT;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.SCHEDULE_START_DATE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.SUP_URGENT;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TRAINFER_EMAIL;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_DEPARTMENT_FORWARD;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_EXPIRATION_DATE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.URGENT;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.WORK_FLOW_TRAINSITION_ID;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.ACTION_FORWARD_DEPARTMENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.COME_BACK_FROM_SCREEN;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DOCUMENT_NUMBER;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.FALSE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.FORWARD_RESUILD;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.GET_COUNT_DATE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.GET_EXPIRATION_DATE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.INPUT_COME_BACK;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.INPUT_NAME;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TRUE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.URLNA_COMEBACK_OFFICE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.nULL_STRING;

/**
 * Created by Vinh on 12/20/2017.
 */

public class FWDepartmentLogic extends FWDepartmentBase implements FWDepartmentImp, AsyncTaskCompleteListener<ResuiltObject>, VolleyTaskCompletedListenner<ResuiltObject> {

    Context context;
    FWDepartmentView mFwDepartmentView;
    List<ChangeHandleRow> arrayHandleProcess;
    ChangeHandlerAdapter adapterHandleProcess;
    long processTypeId = 0;
    List<InputForwardDeparmentRow> arrDeparmentRow;
    ReceiversDepartmentAdapter adapterTrue;
    DialogFalseRow row;
    InputForwardDepartmentAdapter adapterReceivers;
    ReceiversDepartmentAdapter adapterFalseDialog;
    JSONArray mJsonArray;
    JSONArray mJsonArrayAttach;
    JSONObject mJsonObject;
    int NumDay;
    Dialog mDialog;
    boolean isDoneShowDialog = true;
    String DateChoose;
    SearchView mSearchView;
    CheckBox cbAll;


    public FWDepartmentLogic(Context context, FWDepartmentView mFwDepartmentView) {
        this.context = context;
        this.mFwDepartmentView = mFwDepartmentView;
        getInforAccountFromShareReferenced(context);
    }

    @Override
    public void OnClickHandle(int position) {
        if (arrayHandleProcess.get(position).isDefault()) {
            arrayHandleProcess.get(position).setDefault(false);
        } else {
            arrayHandleProcess.get(position).setDefault(true);
        }
        for (int i = 0; i < arrayHandleProcess.size(); i++) {
            if (arrayHandleProcess.get(position).getName().equals(arrayHandleProcess.get(i).getName())) {
                arrayHandleProcess.get(i).setDefault(true);
//                Toast.makeText(context, arrayHandleProcess.get(position).getName() + " " + arrayHandleProcess.get(i).getName(), Toast.LENGTH_SHORT).show();
                processTypeId = arrayHandleProcess.get(position).getID();
            } else {
                arrayHandleProcess.get(i).setDefault(false);
            }
        }
        adapterHandleProcess.notifyDataSetChanged();
    }

    @Override
    public void ReadJsonHandleAndDepartment() {
        ReadJsonHandle(readFromFile(context, "handle" + mFwDepartmentView.getDocumentID()));
        ReadJsonDepartment(readFromFile(context, "department" + mFwDepartmentView.getDocumentID()));
//        new ReadJsonDepartment().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, getLink() + URL_CENTER_6_1);
//        new ReadJsonHandleProcess().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, getLink() + URL_CENTER_6_1);
    }

    private void ReadJsonHandle(String s) {
        arrayHandleProcess = new ArrayList<ChangeHandleRow>();
        GsonHandle mGsonHandle = getGson().fromJson(s, GsonHandle.class);
        for (int i = 0; i < mGsonHandle.getData().size(); i++) {
            GsonHandle.DataBean mDataBean = mGsonHandle.getData().get(i);
            arrayHandleProcess.add(new ChangeHandleRow(false, mDataBean.getName(), mDataBean.getId()));
        }
        adapterHandleProcess = new ChangeHandlerAdapter(context, arrayHandleProcess);
        mFwDepartmentView.setListHandle(adapterHandleProcess);

    }

    private void ReadJsonDepartment(String s) {
        arrDeparmentRow = new ArrayList<>();
        GsonDepartment mGsonDepartment = getGson().fromJson(s, GsonDepartment.class);
        for (int i = 0; i < mGsonDepartment.getData().size(); i++) {
            GsonDepartment.DataBean mDataBean = mGsonDepartment.getData().get(i);
            int organizationId = mDataBean.getOrganizationId();
            String organizationName = mDataBean.getOrganizationName();
            final List<ReceiversRow> arrTrue = new ArrayList<>();
            final List<ReceiversRow> arrFalse = new ArrayList<>();
            for (int j = 0; j < mDataBean.getReceivers().size(); j++) {
                GsonDepartment.DataBean.ReceiversBean mReceiversBean = mDataBean.getReceivers().get(j);
                boolean Default = mReceiversBean.isDefaultX();
                String receiverId = mReceiversBean.getReceiverId();
                String receiverName = mReceiversBean.getReceiverName();
                String roleName = mReceiversBean.getRoleName();
                arrFalse.add(new ReceiversRow(Default, receiverId, receiverName, roleName, true, false));
                if (Default) {
                    arrTrue.add(new ReceiversRow(Default, receiverId, receiverName, roleName, false, false));
                }
            }
            adapterTrue = new ReceiversDepartmentAdapter(context, arrTrue, false, new AdapterButtonClick() {

                @Override
                public void addClick(int position) {

                }

                @Override
                public void mainCheck(int position) {
                    if (arrTrue.get(position).isMainChecked()) {
                        arrTrue.get(position).setMainChecked(false);
                        for (int i = 0; i < arrFalse.size(); i++) {
                            if (arrFalse.get(i).getReceiverId().equals(arrTrue.get(position).getReceiverId())) {
                                arrFalse.get(i).setMainChecked(false);
                            }
                        }
                    } else {
                        arrTrue.get(position).setMainChecked(true);
                        for (int i = 0; i < arrFalse.size(); i++) {
                            if (arrFalse.get(i).getReceiverId().equals(arrTrue.get(position).getReceiverId())) {
                                arrFalse.get(i).setMainChecked(true);
                            }
                        }
                    }
                }

                @Override
                public void AddDialog(int position) {

                }

                @Override
                public void onBtnRemoveClick(int position) {
                    mFwDepartmentView.showProgress();
                    for (int i = 0; i < arrTrue.size(); i++) {
                        if (arrTrue.get(i).getReceiverId().equals(arrTrue.get(position).getReceiverId())) {
                            arrTrue.remove(i);
                            arrFalse.get(i).setMainChecked(false);
                        }
                    }
                    adapterReceivers.notifyDataSetChanged();
                    mFwDepartmentView.closeProgress();

                }
            });
            arrDeparmentRow.add(new InputForwardDeparmentRow(false, organizationId, organizationName, arrFalse, adapterTrue, arrTrue));
        }
        adapterReceivers = new InputForwardDepartmentAdapter(context, arrDeparmentRow, new AdapterButtonClick() {
            @Override
            public void addClick(int position) {

            }

            @Override
            public void mainCheck(int position) {

            }

            @Override
            public void AddDialog(int position) {
                if (mDialog == null) {
                    mFwDepartmentView.showProgress();
                    final DialogFalseRow rowDialog = new DialogFalseRow();
                    rowDialog.setAdapterTrue(arrDeparmentRow.get(position).getAdapterTrue());
                    rowDialog.setArrListFalse(arrDeparmentRow.get(position).getArrFalse());
                    rowDialog.setArrListTrue(arrDeparmentRow.get(position).getArrTrue());
                    if (isDoneShowDialog) {
                        isDoneShowDialog = false;
                        upDateListFalse(rowDialog);
//                            new UpdateListFalse().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, row);
                    }

                }
            }

            @Override
            public void onBtnRemoveClick(int position) {
                Log.d("VinhCNLog: ", position + "");
            }
        });
        mFwDepartmentView.setAdapterReceivers(adapterReceivers);
    }

    private void upDateListFalse(DialogFalseRow rowDialog) {
        row = new DialogFalseRow();
        row.setAdapterTrue(rowDialog.getAdapterTrue());
        row.setArrListFalse(rowDialog.getArrListFalse());
        row.setArrListTrue(rowDialog.getArrListTrue());
        for (int i = 0; i < row.getArrListFalse().size(); i++) {
            boolean found = false;
            for (int j = 0; j < row.getArrListTrue().size(); j++) {
                if (row.getArrListFalse().get(i).getReceiverId().equals(row.getArrListTrue().get(j).getReceiverId())) {
                    found = true;
                }
            }
            if (!found) {
                row.getArrListFalse().get(i).setDefault(false);
            }
        }
        adapterFalseDialog = new ReceiversDepartmentAdapter(context, row.getArrListFalse(), true, new AdapterButtonClick() {
            @Override
            public void onBtnRemoveClick(int position) {

            }

            @Override
            public void addClick(int position) {
                if (row.getArrListFalse().get(position).isDefault()) {
                    row.getArrListFalse().get(position).setDefault(false);
                    row.getArrListFalse().get(position).setMainChecked(false);
                    row.getAdapterFalse().notifyDataSetChanged();
                    for (int i = 0; i < row.getArrListTrue().size(); i++) {
                        if (row.getArrListTrue().get(i).getReceiverId().equals(row.getArrListFalse().get(position).getReceiverId())) {
                            row.getArrListTrue().remove(i);
                        }
                    }
//                        adapterTrue.notifyDataSetChanged();
                } else {
                    row.getArrListFalse().get(position).setDefault(true);
                    row.getArrListTrue().add(new ReceiversRow(row.getArrListFalse().get(position).isDefault(), row.getArrListFalse().get(position).getReceiverId(), row.getArrListFalse().get(position).getReceiverName()
                            , row.getArrListFalse().get(position).getRoleName(), false, false));
//                        adapterTrue.notifyDataSetChanged();
                }
                cbAll.setChecked(isCheckAll(row.getArrListFalse()));
                adapterFalseDialog.notifyDataSetChanged();
            }

            @Override
            public void mainCheck(int position) {
                if (row.getArrListFalse().get(position).isMainChecked()) {
                    row.getArrListFalse().get(position).setMainChecked(false);
                    row.getAdapterFalse().notifyDataSetChanged();
                    for (int i = 0; i < row.getArrListTrue().size(); i++) {
                        if (row.getArrListTrue().get(i).getReceiverId().equals(row.getArrListFalse().get(position).getReceiverId())) {
                            row.getArrListTrue().get(i).setMainChecked(false);
                        }
                    }
                } else {
                    row.getArrListFalse().get(position).setMainChecked(true);
                    for (int i = 0; i < row.getArrListTrue().size(); i++) {
                        if (row.getArrListTrue().get(i).getReceiverId().equals(row.getArrListFalse().get(position).getReceiverId())) {
                            row.getArrListTrue().get(i).setMainChecked(true);
                        }
                    }
                }
            }

            @Override
            public void AddDialog(int position) {

            }

        });
        row.setArrListTrue(row.getArrListTrue());
        row.setAdapterTrue(row.getAdapterTrue());
        row.setArrListFalse(row.getArrListFalse());
        row.setAdapterFalse(adapterFalseDialog);
        showDialogList();
    }

    private void refreshUI(String s, String CaseRequest) {
        switch (CaseRequest) {
            case GET_EXPIRATION_DATE:
                CompletedGetExpirationDate(s);
                break;
            case ACTION_FORWARD_DEPARTMENT:
                CompletedForward(s);
                break;
            case GET_COUNT_DATE:
                CompletedGetCountDate(s);
                break;
        }
    }

    @Override
    public void onTaskCompleted(String s, String CaseRequest) {
        refreshUI(s, CaseRequest);
    }

    private void CompletedGetCountDate(String s) {
        try {
            JSONObject mJsonObject = new JSONObject(s);
            long days = mJsonObject.getLong(DATA);
//            long days = TimeUnit.MILLISECONDS.toDays(milliseconds);
            Log.d("VinhCN ", String.valueOf(days));
            mFwDepartmentView.setDaysCount(String.valueOf(days));
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    private void CompletedGetExpirationDate(String s) {
        if (mFwDepartmentView.isCancelChangeTextRequest()) {
            mFwDepartmentView.setCancelChangeTextRequest(false);
        } else {
            try {
                JSONObject mObject = new JSONObject(s);
                long milisecs = mObject.getLong(DATA);
                mFwDepartmentView.setExpirationDate(getDateFromMiliSec(milisecs, FORMATDATE));
            } catch (JSONException e) {
                mFwDepartmentView.showToast("Lỗi kết nối");
                e.printStackTrace();
            }
        }
    }


    public void CompletedForward(String s) {
        GsonResuilt mGsonResuilt = getGson().fromJson(s, GsonResuilt.class);
        String resuilt = mGsonResuilt.getCode() == 0 ? TRUE : FALSE;
        Intent intent = new Intent(context, OfficalActivity.class);
        intent.putExtra(COME_BACK_FROM_SCREEN, mFwDepartmentView.getScreen());
        intent.putExtra(INPUT_COME_BACK, mFwDepartmentView.getScreen());
        intent.putExtra(INPUT_NAME, mFwDepartmentView.getInputName());
        mFwDepartmentView.closeProgress();
        if (resuilt.contains(FALSE)) {
            intent.putExtra(URLNA_COMEBACK_OFFICE, urlNA);
            intent.putExtra(FORWARD_RESUILD, FALSE);
        } else {
            if (resuilt.equals(nULL_STRING)) {
//                mSqLiteSendWaitingDepartment.QueryData(INSERT_TABLE_SEND_WAITING_DEPARTMENT + " VALUES('" + DocumentID + "','" + mJsonObject + "')");
                mFwDepartmentView.insertTableWaiting();
                mFwDepartmentView.closeProgress();
            } else {
                if (resuilt.contains(TRUE))
                    intent.putExtra(FORWARD_RESUILD, TRUE);
            }
            intent.putExtra(URLNA_COMEBACK_OFFICE, OfficalActivity.urlNA);
            mFwDepartmentView.deleteData();

        }
//        intent.putExtra(DOCUMENT_NUMBER, getIntent().getStringExtra(DOCUMENT_NUMBER));
        intent.putExtra(DOCUMENT_NUMBER, mFwDepartmentView.getDocumentNumber());
        context.startActivity(intent);
    }


    @Override
    public void RequestForward() {
        boolean isHaveMainCheck = false;
        mJsonArray = new JSONArray();
        mJsonArrayAttach = new JSONArray();
//        for (int i = 0; i < arrFileAttach.size(); i++) {
        for (int i = 0; i < mFwDepartmentView.getArrAttachFile().size(); i++) {
            JSONObject mJsonObject = new JSONObject();
            try {
                mJsonObject.put(NAME, mFwDepartmentView.getArrAttachFile().get(i).getFileOnlyName());
                mJsonObject.put(EXTENSION, mFwDepartmentView.getArrAttachFile().get(i).getFileTyPe());
                mJsonObject.put(SCHEDULE_CONTENT, mFwDepartmentView.getArrAttachFile().get(i).getBase64Code().replace("\n", ""));
                mJsonArrayAttach.put(mJsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < arrDeparmentRow.size(); i++) {
            if (arrDeparmentRow.get(i).isDefault()) {
                JSONObject mJsonObjectParent = new JSONObject();
                try {
                    mJsonObjectParent.put(RECEIVE_ORG_ID, arrDeparmentRow.get(i).getOrganizationId());
                    JSONArray mJsonArrayReceivers = new JSONArray();
                    List<ReceiversRow> arrListTrue = arrDeparmentRow.get(i).getArrTrue();
                    for (int j = 0; j < arrListTrue.size(); j++) {
                        JSONObject mJsonObjectChild = new JSONObject();
                        mJsonObjectChild.put(RECEIVER_ID, arrListTrue.get(j).getReceiverId());
                        mJsonObjectChild.put(RECEIVE_ORG_ID, arrDeparmentRow.get(i).getOrganizationId());
                        mJsonObjectChild.put(MAIN_RECEIVE, arrListTrue.get(j).isMainChecked());
//                                mJsonArrayReceivers.put(mJsonObjectChild);
                        mJsonArray.put(mJsonObjectChild);
                        if (arrListTrue.get(j).isMainChecked()) {
                            isHaveMainCheck = true;
                        }
                    }
                    mJsonObjectParent.put(RECEIVERS_LIST, mJsonArrayReceivers);
//                            mJsonArray.put(mJsonObjectParent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
        if (isHaveMainCheck) {
            Log.d("Array ", mJsonArray.toString());
            if (!mJsonArray.toString().equals("[]")) {
                if (processTypeId != 0) {
                    if (!mFwDepartmentView.getCbRegulationDeadline().isChecked()) {
                        if (!mFwDepartmentView.getProcessDay().equals(nULL_STRING)
                                && !mFwDepartmentView.getExpirationDate().equals(nULL_STRING)) {
                            mFwDepartmentView.showProgress();
//                            new NeoTask(context, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(context, ACTION_FORWARD_DEPARTMENT, addJsonRequestForwardDPM().toString(), getLink() + URL_CENTER_6_1));
//                            new ForwardDepartment().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, getLink() + URL_CENTER_6_1);
                            new VolleyTask(context, ACTION_FORWARD_DEPARTMENT, addJsonRequestForwardDPM(), this);
                        } else {
                            if (mFwDepartmentView.isChooseDueDate()) {
                                mFwDepartmentView.showToast("Vui lòng nhập số ngày qui định");
                            }else{
                                new VolleyTask(context, ACTION_FORWARD_DEPARTMENT, addJsonRequestForwardDPM(), this);
                            }
                        }
                    } else {
                        mFwDepartmentView.showProgress();
//                                new ForwardDepartment().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, getLink() + URL_CENTER_6_1);
//                        new NeoTask(context, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(context, ACTION_FORWARD_DEPARTMENT, addJsonRequestForwardDPM().toString(), getLink() + URL_CENTER_6_1));
                        new VolleyTask(context, ACTION_FORWARD_DEPARTMENT, addJsonRequestForwardDPM(), this);
                    }
                } else {
                    if (mFwDepartmentView.isChooseHandleWay()) {
                        mFwDepartmentView.showToast("Vui lòng chọn cách thức xử lý");
                    } else {
                        new VolleyTask(context, ACTION_FORWARD_DEPARTMENT, addJsonRequestForwardDPM(), this);
                    }
                }
            } else {
                mFwDepartmentView.showToast("Vui lòng chọn phòng ban nhận");
            }
        } else {
            mFwDepartmentView.showToast("Vui lòng chọn người xử lý chính");
        }
    }

    @Override
    public void CheckallDepartment(CheckBox cbCheckAllDepartment) {
        if (cbCheckAllDepartment.isChecked()) {
            cbCheckAllDepartment.setChecked(true);
            for (int i = 0; i < arrDeparmentRow.size(); i++) {
                arrDeparmentRow.get(i).setDefault(true);
            }
        } else {
            cbCheckAllDepartment.setChecked(false);
            for (int i = 0; i < arrDeparmentRow.size(); i++) {
                arrDeparmentRow.get(i).setDefault(false);
            }
        }
        adapterReceivers.notifyDataSetChanged();
    }

    @Override
    public JSONObject getJsonObjectRequest() {
        return mJsonObject;
    }

    @Override
    public void getExpirationDate(int mNumber) {
        NumDay = mNumber;
//        new NeoTask(context, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(context, GET_EXPIRATION_DATE, addJsonRequestExpirationDate(true).toString(), getLink() + URL_CENTER_6_1));
        new VolleyTask(context, GET_EXPIRATION_DATE, addJsonRequestExpirationDate(true), this);
    }


    @Override
    public void getCountDay(String dateChoose) {
        DateChoose = dateChoose;
//        new NeoTask(context, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(context, GET_COUNT_DATE, addJsonRequestExpirationDate(false).toString(), getLink() + URL_CENTER_6_1));
        new VolleyTask(context, GET_COUNT_DATE, addJsonRequestExpirationDate(false), this);
    }

    @Override
    public void setAllCheck() {
        int i = 0;
        boolean check = true;
        while (i < arrDeparmentRow.size()) {
            if (!arrDeparmentRow.get(i).isDefault()) {
                check = false;
                break;
            } else {
                check = true;
            }
            i++;
        }
        mFwDepartmentView.CheckAllButton(check);
    }

    JSONObject addJsonRequestExpirationDate(boolean isDateProcess) {
        Calendar mCalendar = Calendar.getInstance();
        JSONObject mObject = new JSONObject();
        JSONObject mData = new JSONObject();
        try {
            mObject.put(MODULE, MODULE_PROCESSING_WORKING);
            mObject.put(NEOTYPE, TYPE_EXPIRATION_DATE);
            mData.put(SCHEDULE_START_DATE, mCalendar.getTimeInMillis());
            if (isDateProcess) {
                mData.put(HOUR_NUMBER, NumDay * 8);
                mData.put(NEOTYPE, 1);
            } else {
                mData.put(END_DATE, getMilisecondDay(DateChoose));
                mData.put(NEOTYPE, 2);
            }
            mData.put(ALL_DAY, false);
            mObject.put(DATA, mData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mObject;
    }


    public void setListDialog(List<ReceiversRow> arr, boolean isdialog) {
        for (int i = 0; i < arr.size(); i++) {
            arr.get(i).setDialog(isdialog);
        }
    }

    boolean isCheckAll(List<ReceiversRow> arr) {
        for (int i = 0; i < arr.size(); i++) {
            if (!arr.get(i).isDefault())
                return false;
        }
        return true;
    }

    public void showDialogList() {
        setListDialog(row.getArrListFalse(), true);
        arrReceiveTemporary = new ArrayList<>();
        arrReceiveTemporary.addAll(row.getArrListFalse());
        mDialog = new Dialog(context, R.style.full_screen_dialog);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(R.layout.dialog_select_receivers);
        ListView lvDialog = (ListView) mDialog.findViewById(R.id.list_select_receivers);
        cbAll = (CheckBox) mDialog.findViewById(R.id.cb_check_all);
        ImageView btnAgree = (ImageView) mDialog.findViewById(R.id.agree);
        TextView btnCancel = (TextView) mDialog.findViewById(R.id.cancel);
        mSearchView = (SearchView) mDialog.findViewById(R.id.search_view);
        cbAll.setChecked(isCheckAll(row.getArrListFalse()));
        cbAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbAll.isChecked()) {
                    for (int i = 0; i < row.getArrListFalse().size(); i++) {
                        if (!row.getArrListFalse().get(i).isDefault()) {
                            row.getArrListFalse().get(i).setDefault(true);
                            row.getArrListTrue().add(row.getArrListFalse().get(i));
                        }
                    }
                } else {
                    for (int i = 0; i < row.getArrListFalse().size(); i++) {
                        row.getArrListFalse().get(i).setDefault(false);
                    }
                    row.getArrListTrue().clear();
                }
                row.getAdapterFalse().notifyDataSetChanged();
                row.getAdapterTrue().notifyDataSetChanged();
            }
        });
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterName(newText);
                return true;
            }
        });
        btnAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDialog != null) {
//                adapterTrue.notifyDataSetChanged();
                    setListDialog(row.getArrListTrue(), false);
                    row.getAdapterTrue().notifyDataSetChanged();
                    mSearchView.setQuery("", true);
                    mDialog.dismiss();
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
        mDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        mDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                mDialog = null;
            }
        });
//        btnDismiss.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mDialog.dismiss();
//                row.getAdapterTrue().notifyDataSetChanged();
//            }
//        });
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if (row.getArrListFalse().get(position).isDefault()) {
//                    row.getArrListFalse().get(position).setDefault(false);
//                    row.getAdapterFalse().notifyDataSetChanged();
//                    for (int i = 0; i < row.getArrListTrue().size(); i++) {
//                        if (row.getArrListTrue().get(i).getReceiverId() == row.getArrListFalse().get(position).getReceiverId()) {
//                            row.getArrListTrue().remove(i);
//                        }
//                    }
//                    adapterTrue.notifyDataSetChanged();
//                } else {
//                    row.getArrListFalse().get(position).setDefault(true);
//                    row.getArrListTrue().add(new ReceiversRow(row.getArrListFalse().get(position).isDefault(), row.getArrListFalse().get(position).getReceiverId(), row.getArrListFalse().get(position).getReceiverName()
//                            , row.getArrListFalse().get(position).getRoleName(), false, false));
//                    row.getAdapterFalse().notifyDataSetChanged();
//                    adapterTrue.notifyDataSetChanged();
//                }
//            }
//        });


        mDialog.show();
        lvDialog.setAdapter(row.getAdapterFalse());
        mDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        mDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);
        isDoneShowDialog = true;
        if (mDialog.isShowing()) {
            mFwDepartmentView.closeProgress();
        }
    }

    private void filterName(String newText) {
        row.getArrListFalse().clear();
        for (int i = 0; i < arrReceiveTemporary.size(); i++) {
            String name = arrReceiveTemporary.get(i).getReceiverName();
            if (removeAccent(name.toUpperCase()).contains(removeAccent(newText.toUpperCase()))) {
                row.getArrListFalse().add(arrReceiveTemporary.get(i));
            }
        }
        if (row.getAdapterFalse() != null) {
            row.getAdapterFalse().notifyDataSetChanged();
        }
    }


    private JSONObject addJsonRequestForwardDPM() {
        mJsonObject = new JSONObject();
        try {
            mJsonObject.put(MODULE, MODULE_PROCESSING_WORKING);
            mJsonObject.put(NEOTYPE, TYPE_DEPARTMENT_FORWARD);
            JSONObject mData = new JSONObject();
            mData.put(JOB_ID, mFwDepartmentView.getDocumentID());
            mData.put(WORK_FLOW_TRAINSITION_ID, mFwDepartmentView.getWorkFlow());
            mData.put(RESOURCE_CODE_ID, mFwDepartmentView.getResourceCodeId());
            mData.put(SCHEDULE_CONTENT, mFwDepartmentView.getContent());
            mData.put(TRAINFER_EMAIL, mFwDepartmentView.getCheckedEmail());
            mData.put(URGENT, mFwDepartmentView.getCheckUrgent());
            mData.put(SUP_URGENT, mFwDepartmentView.getSuperUrgent());
            mData.put(PROCESS_TYPE_ID, processTypeId);
            mData.put(PROCESS_DAYS, mFwDepartmentView.getProcessDay());
            mData.put(EXPIRATION_DATE, getMilisecondDay(mFwDepartmentView.getExpirationDate()));
            mData.put(ALLOW_EXTENSION_PROCESS, mFwDepartmentView.getCheckallowExtension());
            mData.put(RECIPIENTS, mJsonArray);
            mData.put(ATTACH_FILE, mJsonArrayAttach);
            mJsonObject.put(DATA, mData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mJsonObject;
    }


    @Override
    public void onVolleyCompleted(String s, String CaseRequest) {
        refreshUI(s, CaseRequest);
    }

    @Override
    public void onVolleyError(String s) {
        mFwDepartmentView.closeProgress();
        mFwDepartmentView.ToastError(s);
    }
    //    class ReadJsonHandleProcess extends AsyncTask<String, Integer, String> {
//        @Override
//        protected String doInBackground(String... params) {
////            return readFromFile(context, "handle" + DocumentID);
//            return readFromFile(context, "handle" + mFwDepartmentView.getDocumentID());
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
//                adapterHandleProcess = new ChangeHandlerAdapter(context, arrayHandleProcess);
//                mFwDepartmentView.setListHandle(adapterHandleProcess);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            super.onPostExecute(s);
//        }
//    }
//
//    class ReadJsonDepartment extends AsyncTask<String, Integer, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            return readFromFile(context, "department" + mFwDepartmentView.getDocumentID());
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
//                    adapterTrue = new ReceiversDepartmentAdapter(context, arrTrue, new AdapterButtonClick() {
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
//                        public void AddDialog(int position) {
//
//                        }
//
//                        @Override
//                        public void onBtnRemoveClick(int position) {
//                            mFwDepartmentView.showProgress();
//                            for (int i = 0; i < arrTrue.size(); i++) {
//                                if (arrTrue.get(i).getReceiverId().equals(arrTrue.get(position).getReceiverId())) {
//                                    arrTrue.remove(i);
//                                    arrFalse.get(i).setMainChecked(false);
//                                }
//                            }
//                            adapterTrue.notifyDataSetChanged();
//                            mFwDepartmentView.closeProgress();
//
//                        }
//                    });
//                    arrDeparmentRow.add(new InputForwardDeparmentRow(false, organizationId, organizationName, arrFalse, adapterTrue, arrTrue));
//                }
//                adapterReceivers = new InputForwardDepartmentAdapter(context, arrDeparmentRow, new AdapterButtonClick() {
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
//                    public void AddDialog(int position) {
//                        if (mDialog == null) {
//                            mFwDepartmentView.showProgress();
//                            final DialogFalseRow row = new DialogFalseRow();
//                            row.setAdapterTrue(arrDeparmentRow.get(position).getAdapterTrue());
//                            row.setArrListFalse(arrDeparmentRow.get(position).getArrFalse());
//                            row.setArrListTrue(arrDeparmentRow.get(position).getArrTrue());
//                            if (isDoneShowDialog) {
//                                isDoneShowDialog = false;
//                                new UpdateListFalse().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, row);
//                            }
//
//                        }
//                    }
//
//                    @Override
//                    public void onBtnRemoveClick(int position) {
//
//                    }
//                });
//                mFwDepartmentView.setAdapterReceivers(adapterReceivers);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//            super.onPostExecute(s);
//        }
//    }
//
//
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
//            adapterFalseDialog = new ReceiversDepartmentAdapter(context, row.getArrListFalse(), new AdapterButtonClick() {
//                @Override
//                public void onBtnRemoveClick(int position) {
//
//                }
//
//                @Override
//                public void addClick(int position) {
//                    if (row.getArrListFalse().get(position).isDefault()) {
//                        row.getArrListFalse().get(position).setDefault(false);
//                        row.getArrListFalse().get(position).setMainChecked(false);
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
//                @Override
//                public void AddDialog(int position) {
//
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
//    private void ReadJsonHandle(String s) {
//        arrayHandleProcess = new ArrayList<ChangeHandleRow>();
//        try {
//            JSONObject mJsonObject = new JSONObject(s);
//            JSONArray mJsonArray = mJsonObject.getJSONArray(DATA);
//            for (int i = 0; i < mJsonArray.length(); i++) {
//                JSONObject mJsonObjectData = mJsonArray.getJSONObject(i);
//                long id = mJsonObjectData.getLong(ID);
//                String name = mJsonObjectData.getString(NAME);
//                arrayHandleProcess.add(new ChangeHandleRow(false, name, id));
//            }
//            adapterHandleProcess = new ChangeHandlerAdapter(context, arrayHandleProcess);
//            mFwDepartmentView.setListHandle(adapterHandleProcess);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
//private void ReadJsonDepartment(String s) {
//    arrDeparmentRow = new ArrayList<>();
//    try {
//        JSONObject mJsonObject = new JSONObject(s);
//        JSONArray mArrayData = mJsonObject.getJSONArray(DATA);
//        for (int i = 0; i < mArrayData.length(); i++) {
//            JSONObject mData = mArrayData.getJSONObject(i);
//            int organizationId = mData.getInt(ORGANIZATION_ID);
//            String organizationName = mData.getString(ORGANIZATION_NAME);
//            JSONArray receivers = mData.getJSONArray(RECEIVERS_LIST);
//            final List<ReceiversRow> arrTrue = new ArrayList<>();
//            final List<ReceiversRow> arrFalse = new ArrayList<>();
//            for (int j = 0; j < receivers.length(); j++) {
//                JSONObject mDataReceivers = receivers.getJSONObject(j);
//                boolean Default = mDataReceivers.getBoolean(DEFAULT);
//                String receiverId = mDataReceivers.getString(RECEIVER_ID);
//                String receiverName = mDataReceivers.getString(RECEIVER_NAME);
//                String roleName = mDataReceivers.getString(ROLE_NAME);
//                arrFalse.add(new ReceiversRow(Default, receiverId, receiverName, roleName, true, false));
//                if (Default) {
//                    arrTrue.add(new ReceiversRow(Default, receiverId, receiverName, roleName, false, false));
//                }
//            }
//            adapterTrue = new ReceiversDepartmentAdapter(context, arrTrue, new AdapterButtonClick() {
//
//                @Override
//                public void addClick(int position) {
//
//                }
//
//                @Override
//                public void mainCheck(int position) {
//                    if (arrTrue.get(position).isMainChecked()) {
//                        arrTrue.get(position).setMainChecked(false);
//                        for (int i = 0; i < arrFalse.size(); i++) {
//                            if (arrFalse.get(i).getReceiverId().equals(arrTrue.get(position).getReceiverId())) {
//                                arrFalse.get(i).setMainChecked(false);
//                            }
//                        }
//                    } else {
//                        arrTrue.get(position).setMainChecked(true);
//                        for (int i = 0; i < arrFalse.size(); i++) {
//                            if (arrFalse.get(i).getReceiverId().equals(arrTrue.get(position).getReceiverId())) {
//                                arrFalse.get(i).setMainChecked(true);
//                            }
//                        }
//                    }
//                }
//
//                @Override
//                public void AddDialog(int position) {
//
//                }
//
//                @Override
//                public void onBtnRemoveClick(int position) {
//                    mFwDepartmentView.showProgress();
//                    for (int i = 0; i < arrTrue.size(); i++) {
//                        if (arrTrue.get(i).getReceiverId().equals(arrTrue.get(position).getReceiverId())) {
//                            arrTrue.remove(i);
//                            arrFalse.get(i).setMainChecked(false);
//                        }
//                    }
//                    adapterReceivers.notifyDataSetChanged();
//                    mFwDepartmentView.closeProgress();
//
//                }
//            });
//            arrDeparmentRow.add(new InputForwardDeparmentRow(false, organizationId, organizationName, arrFalse, adapterTrue, arrTrue));
//        }
//        adapterReceivers = new InputForwardDepartmentAdapter(context, arrDeparmentRow, new AdapterButtonClick() {
//            @Override
//            public void addClick(int position) {
//
//            }
//
//            @Override
//            public void mainCheck(int position) {
//
//            }
//
//            @Override
//            public void AddDialog(int position) {
//                if (mDialog == null) {
//                    mFwDepartmentView.showProgress();
//                    final DialogFalseRow rowDialog = new DialogFalseRow();
//                    rowDialog.setAdapterTrue(arrDeparmentRow.get(position).getAdapterTrue());
//                    rowDialog.setArrListFalse(arrDeparmentRow.get(position).getArrFalse());
//                    rowDialog.setArrListTrue(arrDeparmentRow.get(position).getArrTrue());
//                    if (isDoneShowDialog) {
//                        isDoneShowDialog = false;
//                        upDateListFalse(rowDialog);
////                            new UpdateListFalse().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, row);
//                    }
//
//                }
//            }
//
//            @Override
//            public void onBtnRemoveClick(int position) {
//                Log.d("VinhCNLog: ", position + "");
//            }
//        });
//        mFwDepartmentView.setAdapterReceivers(adapterReceivers);
//    } catch (JSONException e) {
//        e.printStackTrace();
//    }
//
//}
}


