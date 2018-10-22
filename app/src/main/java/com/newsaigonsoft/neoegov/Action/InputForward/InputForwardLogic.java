package com.newsaigonsoft.neoegov.Action.InputForward;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;

import com.newsaigonsoft.neoegov.AVolleyManager.VolleyTask;
import com.newsaigonsoft.neoegov.AVolleyManager.VolleyTaskCompletedListenner;
import com.newsaigonsoft.neoegov.Adapter.ReceivePersonAdapter;
import com.newsaigonsoft.neoegov.AsynTaskManager.AsyncTaskCompleteListener;
import com.newsaigonsoft.neoegov.GsonObject.GsonResuilt;
import com.newsaigonsoft.neoegov.MyInterface.AdapterButtonClick;
import com.newsaigonsoft.neoegov.OfficalActivity.OfficalActivity;
import com.newsaigonsoft.neoegov.Subjects.ReceivePerson;
import com.newsaigonsoft.neoegov.Subjects.ResuiltObject;
import com.newsaigonsoft.neoegov.Subjects.SumManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.ATTACH_FILE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DATA;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DEFAULT;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.EXTENSION;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.JOB_ID;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MAIN_RECEIVE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MODULE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.NAME;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.NEOTYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.ORGANIZATION_ID;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.ORGANIZATION_NAME;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MODULE_PROCESSING_WORKING;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.RECEIVER_ID;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.RECEIVER_NAME;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.RECEIVER_ORG_ID;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.RECIPIENTS;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.ROLE_NAME;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.SCHEDULE_CONTENT;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TRAINFER_EMAIL;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_REPORT_RESUILT;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_TRAINFER;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.URGENT;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.WORK_FLOW_TRAINSITION_ID;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.ACTION_FORWARD_PERSON;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.COME_BACK_FROM_SCREEN;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DOCUMENT_NUMBER;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.FALSE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.FORWARD_RESUILD;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.INPUT_COME_BACK;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.INPUT_NAME;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.RESOURCECODEID;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SELECT_FROM;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_14;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_FORWARD_PERSON;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_FORWARD_RELEASE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_REPORT_RESUILT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TRUE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.URLNA_COMEBACK_OFFICE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.nULL_STRING;

/**
 * Created by Vinh on 12/21/2017.
 */

public class InputForwardLogic extends SumManager implements InputForwardImp, AsyncTaskCompleteListener<ResuiltObject>, VolleyTaskCompletedListenner<ResuiltObject> {
    Context context;
    InputForwardView mInputForwardView;
    ArrayList<ReceivePerson> arrReceive, arrReceiveTemporary;
    ReceivePersonAdapter adapter;
    JSONArray mJsonArrayAttach;
    JSONArray mJsonArray;
    JSONObject mJsonObject;

    public InputForwardLogic(Context context, InputForwardView mInputForwardView) {
        this.context = context;
        this.mInputForwardView = mInputForwardView;
    }

    /*===================================================
    Set List for people user wanna send document
=====================================================*/

    @Override
    public void setListInputPerSon() {
        String s = "";
        arrReceive = new ArrayList<ReceivePerson>();
//        Cursor mCursor =  mInputForwardView.getSqlite().GetData(SELECT_FROM + "inputperson" + DocumentID);
        Cursor mCursor = mInputForwardView.getSqlite().GetData(SELECT_FROM + "inputperson" + mInputForwardView.getDocumentID());
        mCursor.moveToFirst();
        if (mCursor.getCount() != 0) {
            s = mCursor.getString(1);
        }
        Log.d("object", s);
        mInputForwardView.setListInputPerson(AdapterPerson(s));

        mInputForwardView.closeProgress();
    }

    @Override
    public void filterName(String newText) {
        arrReceive.clear();
        for (int i = 0; i < arrReceiveTemporary.size(); i++) {
            if (removeAccent(arrReceiveTemporary.get(i).getReceivePersonName().toUpperCase()).contains(removeAccent(newText.toUpperCase()))) {
                arrReceive.add(arrReceiveTemporary.get(i));
            }
        }
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void upDateListEventSearchView() {
        if (arrReceive.size() > 0) {
            arrReceive.clear();
        }
        for (int i = 0; i < arrReceiveTemporary.size(); i++) {
            if (arrReceiveTemporary.get(i).isDefault()) {
                arrReceive.add(arrReceiveTemporary.get(i));
                arrReceiveTemporary.remove(i);
            }
        }
        arrReceive.addAll(arrReceiveTemporary);
        arrReceiveTemporary.clear();
        arrReceiveTemporary.addAll(arrReceive);
        //adapter.notifyDataSetChanged();
        mInputForwardView.setListInputPerson(adapter);
    }

    @Override
    public void requestForwardPerson() {
        mJsonArrayAttach = new JSONArray();
        for (int i = 0; i < mInputForwardView.getArrayAttach().size(); i++) {
            JSONObject mJsonObject = new JSONObject();
            try {
                mJsonObject.put(NAME, mInputForwardView.getArrayAttach().get(i).getFileOnlyName());
                mJsonObject.put(EXTENSION, mInputForwardView.getArrayAttach().get(i).getFileTyPe());
                mJsonObject.put(SCHEDULE_CONTENT, mInputForwardView.getArrayAttach().get(i).getBase64Code().replace("\n", ""));
                mJsonArrayAttach.put(mJsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        mInputForwardView.showProgress();
        mJsonArray = new JSONArray();
        for (int i = 0; i < arrReceive.size(); i++) {
            if (arrReceive.get(i).isDefault()) {
                JSONObject mJsonObject = new JSONObject();
                try {
                    mJsonObject.put(RECEIVER_ID, arrReceive.get(i).getReceivePersonID());
                    mJsonObject.put(RECEIVER_ORG_ID, arrReceive.get(i).getReceiveRoomID());
                    mJsonObject.put(MAIN_RECEIVE, arrReceive.get(i).isMainPerson());
                    mJsonArray.put(mJsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        Log.d("JsonArrayPerson: ", mJsonArray.toString());
        if (isNetworkAvailable(context)) {
            boolean isHaveMainReceiver = false;
            for (int i = 0; i < arrReceive.size(); i++) {
                if (arrReceive.get(i).isMainPerson()) {
                    isHaveMainReceiver = true;
                    break;
                } else {
                    isHaveMainReceiver = false;
                }
            }
            if (isHaveMainReceiver == true) {
//                new NeoTask(context, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(context, ACTION_FORWARD_PERSON, addJsonRequest().toString(), getLink() + URL_CENTER_6_1));
                new VolleyTask(context, ACTION_FORWARD_PERSON, addJsonRequest(), this);
            } else {
                mInputForwardView.showToast("Vui lòng chọn người xử lý chính");

            }
        } else {
            // insert to database SQLite
            addJsonRequest();
            mInputForwardView.UpdateDatabase();
            mInputForwardView.closeProgress();
            Intent intent = new Intent(context, OfficalActivity.class);
            intent.putExtra(URLNA_COMEBACK_OFFICE, OfficalActivity.urlNA);
            intent.putExtra(DOCUMENT_NUMBER, mInputForwardView.getDocumentNumber());
            context.startActivity(intent);
        }
    }

    @Override
    public JSONObject getJsonWaiting() {
        return mJsonObject;
    }

    private ReceivePersonAdapter AdapterPerson(String s) {
        arrReceiveTemporary = new ArrayList<>();
        try {
            JSONObject mJsonObject = new JSONObject(s);
            JSONArray mJsonArray = mJsonObject.getJSONArray(DATA);
            for (int i = 0; i < mJsonArray.length(); i++) {
                JSONObject mJsonObjectRow = mJsonArray.getJSONObject(i);
                boolean mDefault = mJsonObjectRow.getBoolean(DEFAULT);
                String mReceivePersonName = mJsonObjectRow.getString(RECEIVER_NAME);
                String mReceiveRoomName = mJsonObjectRow.getString(ORGANIZATION_NAME);
                String mReceivePersonID = mJsonObjectRow.getString(RECEIVER_ID);
                String mReceiveRoomID = mJsonObjectRow.getString(ORGANIZATION_ID);
                String mRoleName = mJsonObjectRow.getString(ROLE_NAME);
                arrReceive.add(new ReceivePerson(mDefault, mReceivePersonName, mReceiveRoomName, mReceivePersonID, mReceiveRoomID, mRoleName, false));
                arrReceiveTemporary.add(new ReceivePerson(mDefault, mReceivePersonName, mReceiveRoomName, mReceivePersonID, mReceiveRoomID, mRoleName, false));
                if (adapter != null && adapter.getCount() != 0) {
                    adapter.notifyDataSetChanged();
                } else {
                    adapter = new ReceivePersonAdapter(context, arrReceive, arrReceiveTemporary, mInputForwardView.getForwardOrReportOrRelease(), new AdapterButtonClick() {
                        @Override
                        public void onBtnRemoveClick(int position) {

                        }

                        @Override
                        public void addClick(int position) {

                        }

                        @Override
                        public void mainCheck(int position) {
                            switch (mInputForwardView.getForwardOrReportOrRelease()) {
                                case TAP_FORWARD_PERSON:
                                case TAP_REPORT_RESUILT:
                                case TAP_14:
                                    if (!arrReceive.get(position).isDefault()) {
                                        arrReceive.get(position).setDefault(true);
                                        for (int i = 0; i < arrReceiveTemporary.size(); i++) {
                                            if (arrReceiveTemporary.get(i).getReceivePersonID().equals(arrReceive.get(position).getReceivePersonID()))
                                                arrReceiveTemporary.get(i).setDefault(true);
                                        }
                                    } else {
                                        arrReceive.get(position).setDefault(false);
                                        arrReceive.get(position).setMainPerson(false);
                                        for (int i = 0; i < arrReceiveTemporary.size(); i++) {
                                            if (arrReceiveTemporary.get(i).getReceivePersonID().equals(arrReceive.get(position).getReceivePersonID())) {
                                                arrReceiveTemporary.get(i).setDefault(false);
                                                arrReceiveTemporary.get(i).setMainPerson(false);
                                            }
                                        }
                                    }
                                    mInputForwardView.AllCheck(isCheckAll());
                                    break;
                                case TAP_FORWARD_RELEASE:
                                    for (int i = 0; i < arrReceive.size(); i++) {
                                        if (arrReceive.get(i).getReceivePersonID().equals(arrReceive.get(position).getReceivePersonID())) {
                                            arrReceive.get(i).setDefault(true);
                                            arrReceive.get(i).setMainPerson(true);
                                        } else {
                                            arrReceive.get(i).setDefault(false);
                                            arrReceive.get(i).setMainPerson(false);
                                        }
                                    }
                                    for (int i = 0; i < arrReceiveTemporary.size(); i++) {
                                        if (arrReceiveTemporary.get(i).getReceivePersonID().equals(arrReceive.get(position).getReceivePersonID())) {
                                            arrReceiveTemporary.get(i).setDefault(true);
                                            arrReceiveTemporary.get(i).setMainPerson(true);
                                        } else {
                                            arrReceiveTemporary.get(i).setDefault(false);
                                            arrReceiveTemporary.get(i).setMainPerson(false);
                                        }
                                    }
                                    break;
                            }
                            adapter.notifyDataSetChanged();

                        }

                        @Override
                        public void AddDialog(int position) {

                        }
                    });
                }
//                if (LoadinBackGround) {
//                    arrReceiveTemporary.add(new ReceivePerson(mDefault, mReceivePersonName, mReceiveRoomName, mReceivePersonID, mReceiveRoomID, mRoleName, false));
//                } else {
//                    arrReceive.add(new ReceivePerson(mDefault, mReceivePersonName, mReceiveRoomName, mReceivePersonID, mReceiveRoomID, mRoleName, false));
//                    if (adapter != null && adapter.getCount() != 0) {
//                        adapter.notifyDataSetChanged();
//                    } else {
//                        adapter = new ReceivePersonAdapter(InputForwardActivity.this, arrReceive);
//                    }
//                }
            }
            // LinearLayout the same listview
//                int adapterCount = adapter.getCount();
//                for (int i = 0; i < adapterCount; i++) {
//                    View item = adapter.getView(i, null, null);
//                    mLinearList.addView(item);
//                }

//                setListViewHeightBasedOnChildren(lv);
            //for recyclerView
//                adapterRecycler = new RecyclerReceivePersonAdapter(InputForwardActivity.this, arrReceive);
//                lv.setAdapter(adapterRecycler);
//                lv.setLayoutManager(layoutManager);

        } catch (JSONException e) {

            e.printStackTrace();
        }
        return adapter;
    }

    private boolean isCheckAll() {
        for (int i = 0 ; i< arrReceive.size(); i++){
            if (!arrReceive.get(i).isDefault()){
                return false;
            }
        }
        return true;
    }

    private JSONObject addJsonRequest() {
        mJsonObject = new JSONObject();
        JSONObject mJsonObjectData = new JSONObject();
        try {
            mJsonObject.put(MODULE, MODULE_PROCESSING_WORKING);
            mJsonObjectData.put(JOB_ID, mInputForwardView.getDocumentID());
            mJsonObjectData.put(WORK_FLOW_TRAINSITION_ID, mInputForwardView.getWorkFlowID());
            switch (mInputForwardView.getForwardOrReportOrRelease()) {
                case TAP_REPORT_RESUILT:
                    mJsonObject.put(NEOTYPE, TYPE_REPORT_RESUILT);
                    break;
                case TAP_FORWARD_PERSON:
                    mJsonObject.put(NEOTYPE, TYPE_TRAINFER);
                    break;
                case TAP_FORWARD_RELEASE:
                    mJsonObject.put(NEOTYPE, TYPE_TRAINFER);
                    break;
                case TAP_14:
                    mJsonObject.put(NEOTYPE, TYPE_TRAINFER);
                    break;
            }
            mJsonObjectData.put(RESOURCECODEID, mInputForwardView.getResourceCodeID());
            mJsonObjectData.put(SCHEDULE_CONTENT, mInputForwardView.getContent());
            mJsonObjectData.put(TRAINFER_EMAIL, mInputForwardView.isCheckEmail());
            if (mInputForwardView.getForwardOrReportOrRelease() != 4) {
                mJsonObjectData.put(URGENT, mInputForwardView.IsCheckUrgent());
            }
            mJsonObjectData.put(RECIPIENTS, mJsonArray);
            mJsonObjectData.put(ATTACH_FILE, mJsonArrayAttach);
            mJsonObject.put(DATA, mJsonObjectData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mJsonObject;
    }


    @Override
    public void onTaskCompleted(String s, String CaseRequest) {
        refreshUI(s, CaseRequest);
    }

    @Override
    public void onVolleyCompleted(String s, String CaseRequest) {
        refreshUI(s, CaseRequest);
    }

    @Override
    public void onVolleyError(String s) {
        mInputForwardView.closeProgress();
        mInputForwardView.showToast("Lỗi kết nối");
    }

    private void refreshUI(String s, String CaseRequest) {
        GsonResuilt mGsonResuilt = getGson().fromJson(s, GsonResuilt.class);
        String resuilt = mGsonResuilt.getCode() == 0 ? TRUE : FALSE;
        Intent intent = new Intent(context, OfficalActivity.class);
        //// TODO: 9/1/2017  comback screen
        intent.putExtra(COME_BACK_FROM_SCREEN, mInputForwardView.getScreen());
        intent.putExtra(INPUT_COME_BACK, mInputForwardView.getScreen());
        intent.putExtra(INPUT_NAME, mInputForwardView.getNameInput());
        mInputForwardView.closeProgress();
        if (resuilt.contains(FALSE)) {
//                mSumManager.showDialogUpdateError(getString(R.string.chuyen_that_bai), InputForwardActivity.this);
            intent.putExtra(URLNA_COMEBACK_OFFICE, OfficalActivity.urlNA);
            intent.putExtra(FORWARD_RESUILD, FALSE);
        } else {
            if (resuilt.equals(nULL_STRING)) {
//                    mSqLiteSendWaiting.QueryData("INSERT OR REPLACE INTO sendwaiting(documentid, jsonRequest)" + " VALUES(" +
//                            "'" + DocumentID + "','" + resourceCodeId + "','" + TrainferID + "','" + FirstPersonID + "','" + FisrtRoomID + "','" + ProcessContent + "','" + cbEmail.isChecked() + "','" + cbKhan.isChecked() + "','" + mJsonArray + "')");
                mInputForwardView.insertSendWaiting(mJsonObject);
                mInputForwardView.closeProgress();
                intent.putExtra(URLNA_COMEBACK_OFFICE, urlNA);
            } else {
                intent.putExtra(URLNA_COMEBACK_OFFICE, urlNA);

            }
            intent.putExtra(FORWARD_RESUILD, TRUE);
            mInputForwardView.DeleteData();

        }
        intent.putExtra(DOCUMENT_NUMBER, mInputForwardView.getDocumentNumber());
        context.startActivity(intent);
    }

    public void selectAllList(boolean isCheckAll) {
        for (int i = 0; i < arrReceive.size(); i++){
            arrReceive.get(i).setDefault(isCheckAll);
        }
        adapter.notifyDataSetChanged();
    }
}
