package com.newsaigonsoft.neoegov.Action.Return;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.newsaigonsoft.neoegov.AVolleyManager.VolleyTask;
import com.newsaigonsoft.neoegov.AVolleyManager.VolleyTaskCompletedListenner;
import com.newsaigonsoft.neoegov.Adapter.ReturnDocumentAdapter;
import com.newsaigonsoft.neoegov.AsynTaskManager.AsyncTaskCompleteListener;
import com.newsaigonsoft.neoegov.AsynTaskManager.CaseManager;
import com.newsaigonsoft.neoegov.AsynTaskManager.NeoTask;
import com.newsaigonsoft.neoegov.GsonObject.GsonResuilt;
import com.newsaigonsoft.neoegov.GsonObject.GsonReturnList;
import com.newsaigonsoft.neoegov.OfficalActivity.OfficalActivity;
import com.newsaigonsoft.neoegov.Subjects.ResuiltObject;
import com.newsaigonsoft.neoegov.Subjects.ReturnDocumentItems;
import com.newsaigonsoft.neoegov.Subjects.SumManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.ATTACH_FILE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DATA;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.JOB_ID;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MODULE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.NEOTYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MODULE_PROCESSING_WORKING;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.RESOURCE_CODE_ID;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.SCHEDULE_CONTENT;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.STATISTIC_TYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_RETURN;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.WORK_FLOW_TRAINSITION_ID;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.ACTION_RETURN;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.COME_BACK_FROM_SCREEN;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.FALSE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.FORWARD_RESUILD;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.INPUT_COME_BACK;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LIST_RETURN_SAVED;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.RETURN_SCREEN;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TRUE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.URL_CENTER_6_1;

/**
 * Created by Vinh on 12/19/2017.
 */

public class ReturnLogic extends SumManager implements ReturnImp, AsyncTaskCompleteListener<ResuiltObject>, VolleyTaskCompletedListenner<ResuiltObject> {
    Context context;
    ReturnView mReturnView;
    JSONArray JsonAttach;
    ArrayList<ReturnDocumentItems> arrayList;
    List<GsonReturnList.DataBean> mDataBeans;


    public ReturnLogic(Context context, ReturnView mReturnView) {
        this.context = context;
        this.mReturnView = mReturnView;
        getInforAccountFromShareReferenced(context);
    }

    @Override
    public void RequestReturn(JSONArray mJsonArrayAttach) {
        JsonAttach = mJsonArrayAttach;
//        new NeoTask(context, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(context, ACTION_RETURN, addJsonRequestReturn().toString(), getLink() + URL_CENTER_6_1));
        new VolleyTask(context, ACTION_RETURN, addJsonRequestReturn(), this);
    }

    @Override
    public void getListPerson(long DocumentID) {
        new NeoTask(context, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(context, LIST_RETURN_SAVED, readFromFile(context, "return_list" + DocumentID), getLink() + URL_CENTER_6_1));
    }

    private void refreshUI(String s, String CaseRequest) {
        switch (CaseRequest) {
            case ACTION_RETURN:
                GsonResuilt mGsonResuilt = getGson().fromJson(s, GsonResuilt.class);
                String resuilt = mGsonResuilt.getCode() == 0 ? TRUE : FALSE;
                Intent intent = new Intent(context, OfficalActivity.class);
                intent.putExtra(COME_BACK_FROM_SCREEN, mReturnView.getScreen());
                intent.putExtra(INPUT_COME_BACK, RETURN_SCREEN);
                intent.putExtra(FORWARD_RESUILD, resuilt);
                intent.putExtra(STATISTIC_TYPE, mReturnView.getIsTatistic());
                if (resuilt.equals(TRUE)) {
                    mReturnView.deleteData();
                }
                context.startActivity(intent);
                mReturnView.closeProgress();
                break;
            case LIST_RETURN_SAVED:
                GsonReturnList mGsonReturnList = getGson().fromJson(s, GsonReturnList.class);
                mDataBeans = mGsonReturnList.getData();
                ReturnDocumentAdapter adapter = new ReturnDocumentAdapter(context, mDataBeans);
                mReturnView.setListReturn(adapter);
//                arrayList = new ArrayList<ReturnDocumentItems>();
//                try {
//                    JSONObject mJsonObject = new JSONObject(s);
//                    JSONArray mJsonArray = mJsonObject.getJSONArray(DATA);
//                    for (int i = 0; i < mJsonArray.length(); i++) {
//                        JSONObject mJsonObjectRow = mJsonArray.getJSONObject(i);
//                        String mReceivePersonName = mJsonObjectRow.getString(RECEIVER_NAME);
//                        String mReceiveRoomName = mJsonObjectRow.getString(ORGANIZATION_NAME);
//                        String mReceivePersonID = mJsonObjectRow.getString(RECEIVER_ID);
//                        arrayList.add(new ReturnDocumentItems(mReceivePersonID, mReceivePersonName, mReceiveRoomName));
////                        String ReturnId;
////                        String ReturnName;
////                        String ReturnDepartment;
//                    }
//                    ReturnDocumentAdapter adapter = new ReturnDocumentAdapter(context, arrayList);
//                    mReturnView.setListReturn(adapter);
//
//                } catch (JSONException e) {
//
//                    e.printStackTrace();
//                }
                break;
        }
    }

    @Override
    public void onTaskCompleted(String s, String CaseRequest) {
        refreshUI(s, CaseRequest);
    }

    private JSONObject addJsonRequestReturn() {
        JSONObject mJsonObject = new JSONObject();
        try {
            mJsonObject.put(MODULE, MODULE_PROCESSING_WORKING);
            mJsonObject.put(NEOTYPE, TYPE_RETURN);
            JSONObject mData = new JSONObject();
            mData.put(JOB_ID, mReturnView.getDocumentID());
//            mData.put(WORK_FLOW_TRAINSITION_ID, workflowTransitionId);
            mData.put(WORK_FLOW_TRAINSITION_ID, mReturnView.getWorkFlow());
//            mData.put(RESOURCE_CODE_ID, resourceCodeId);
            mData.put(RESOURCE_CODE_ID, mReturnView.getResourceCodeId());
//            mData.put(SCHEDULE_CONTENT, edtContent.getText().toString());
            mData.put(SCHEDULE_CONTENT, mReturnView.getContent());
            mData.put(ATTACH_FILE, JsonAttach);
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
        mReturnView.closeProgress();
        mReturnView.showToast();

    }
}
