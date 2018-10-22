package com.newsaigonsoft.neoegov.Action.Cancel;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.newsaigonsoft.neoegov.AVolleyManager.VolleyTask;
import com.newsaigonsoft.neoegov.AVolleyManager.VolleyTaskCompletedListenner;
import com.newsaigonsoft.neoegov.AsynTaskManager.AsyncTaskCompleteListener;
import com.newsaigonsoft.neoegov.GsonObject.GsonResuilt;
import com.newsaigonsoft.neoegov.OfficalActivity.OfficalActivity;
import com.newsaigonsoft.neoegov.Subjects.CancelListRow;
import com.newsaigonsoft.neoegov.Subjects.ResuiltObject;
import com.newsaigonsoft.neoegov.Subjects.SumManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DATA;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.JOB_ID;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.JOB_RETTRACTS;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.JOB_RETTRACT_SELECTEDS;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MODULE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.NEOTYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.ORGANIZATION_NAME;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MODULE_PROCESSING_WORKING;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.PROCESSOR;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.RESOURCE_CODE_ID;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.STATISTIC_TYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_CANCEL;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.WORK_FLOW_TRAINSITION_ID;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.ACTION_CANCEL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.CANCEL_SCREEN;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.COME_BACK_FROM_SCREEN;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DOCUMENT_NUMBER;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.FALSE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.FORWARD_RESUILD;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.INPUT_COME_BACK;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TRUE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.URL_CENTER_6_1;

/**
 * Created by Vinh on 11/18/2017.
 */

public class CancelLogic extends SumManager implements CancelLogicImp, AsyncTaskCompleteListener<ResuiltObject>, VolleyTaskCompletedListenner<ResuiltObject> {

    CancelView mCancelView;
    Context context;
    ArrayList<CancelListRow> arrCancelList;
    long jobID;
    JSONArray jobRetractsArray, jobRetractsArraySelected;
    int workflowTransitionId;
    String resourceCodeId;


    public CancelLogic(CancelView mCancelView, Context context) {
        this.mCancelView = mCancelView;
        this.context = context;
        getInforAccountFromShareReferenced(context);
    }


    public void RequestCancelList(long jobid) {
        jobID = jobid;
        new ReadJsonCancelList().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, getLink() + URL_CENTER_6_1);
    }

    public void SaveAndClose(int workflowTransitionId_, String resourceCodeId_) {
        boolean isCheckList = false;
        workflowTransitionId = workflowTransitionId_;
        resourceCodeId = resourceCodeId_;
        mCancelView.showProgress();
        jobRetractsArray = new JSONArray();
        jobRetractsArraySelected = new JSONArray();
        for (int i = 0; i < arrCancelList.size(); i++) {
            JSONObject mJsonNonSelect = new JSONObject();
            try {
                mJsonNonSelect.put(JOB_ID, arrCancelList.get(i).getJobId());
                jobRetractsArray.put(mJsonNonSelect);
                if (arrCancelList.get(i).isDefault()) {
                    JSONObject mJsonSelected = new JSONObject();
                    mJsonSelected.put(JOB_ID, arrCancelList.get(i).getJobId());
                    jobRetractsArraySelected.put(mJsonSelected);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < arrCancelList.size(); i++) {
            if (arrCancelList.get(i).isDefault()) {
                isCheckList = true;
                break;
            } else {
                isCheckList = false;
            }
        }
        if (isCheckList) {
            // TODO: 3/16/2018  do not remove this comment
//            new NeoTask(context, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(context, ACTION_CANCEL, addJsonRequestCancel().toString(), getLink() + URL_CENTER_6_1));
            new VolleyTask(context, ACTION_CANCEL, addJsonRequestCancel(), this);
        } else {
            mCancelView.ShowToastCancel("Vui lòng chọn người cần rút lại");
        }
    }

    public void selectAll() {
        for (int i = 0; i < arrCancelList.size(); i++) {
            if (!mCancelView.isSelectAllCheck()) {
                arrCancelList.get(i).setDefault(false);
            } else {
                arrCancelList.get(i).setDefault(true);
            }
        }
        mCancelView.getAdapterView().notifyDataSetChanged();
    }

    @Override
    public void onTaskCompleted(String s, String CaseRequest) {
        refreshUI(s, CaseRequest);
    }


    private void refreshUI(String s, String CaseRequest) {
        switch (CaseRequest) {
            case ACTION_CANCEL:
                GsonResuilt mGsonResuilt = getGson().fromJson(s, GsonResuilt.class);
                String resuilt = mGsonResuilt.getCode() == 0 ? TRUE : FALSE;
                Intent intent = new Intent(context, OfficalActivity.class);
                intent.putExtra(COME_BACK_FROM_SCREEN, mCancelView.ScreenInSide());
                intent.putExtra(INPUT_COME_BACK, CANCEL_SCREEN);
                intent.putExtra(FORWARD_RESUILD, resuilt);
                intent.putExtra(STATISTIC_TYPE, mCancelView.isTatistic());
                intent.putExtra(DOCUMENT_NUMBER, mCancelView.getJobID());
                if (resuilt.equals(TRUE))
                    mCancelView.DeteleData();
                mCancelView.startIntent(intent);
                mCancelView.closeProgress();
                break;
            default:
                break;
        }
    }


    @Override
    public void onVolleyCompleted(String s, String CaseRequest) {
        refreshUI(s, CaseRequest);
    }

    @Override
    public void onVolleyError(String s) {
        mCancelView.ToastError(s);
    }

    @Override
    public void setSelectAll() {
        int i = 0;
        boolean check = true;
        while (i < arrCancelList.size()) {
            if (!arrCancelList.get(i).isDefault()) {
                check = false;
                break;
            } else {
                check = true;
            }
            i++;
        }
        mCancelView.CheckAllButton(check);
    }


    class ReadJsonCancelList extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... params) {
            return readFromFile(context, "cancel_list" + jobID);
        }

        @Override
        protected void onPostExecute(String s) {
            arrCancelList = new ArrayList<CancelListRow>();
            try {
                JSONObject mJsonObject = new JSONObject(s);
                JSONArray mJsonArray = mJsonObject.getJSONArray(DATA);
                for (int i = 0; i < mJsonArray.length(); i++) {
                    JSONObject mObject = mJsonArray.getJSONObject(i);
                    long jobId = mObject.getLong(JOB_ID);
                    String processer = mObject.getString(PROCESSOR);
                    String organizationName = mObject.getString(ORGANIZATION_NAME);
                    arrCancelList.add(new CancelListRow(false, jobId, processer, organizationName));
                }
                mCancelView.setAdapterCancelList(arrCancelList);
//                adapter = new CancelListAdapter(context, arrCancelList);
//                mCancelView.setCancelList(adapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            super.onPostExecute(s);
        }

    }

    private JSONObject addJsonRequestCancel() {
        JSONObject mJsonObject = new JSONObject();
        try {
            mJsonObject.put(MODULE, MODULE_PROCESSING_WORKING);
            mJsonObject.put(NEOTYPE, TYPE_CANCEL);
            JSONObject mData = new JSONObject();
            mData.put(JOB_ID, jobID);
            mData.put(WORK_FLOW_TRAINSITION_ID, workflowTransitionId);
            mData.put(RESOURCE_CODE_ID, resourceCodeId);
            mData.put(JOB_RETTRACTS, jobRetractsArray);
            mData.put(JOB_RETTRACT_SELECTEDS, jobRetractsArraySelected);
            mJsonObject.put(DATA, mData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mJsonObject;
    }

//    class Cancel extends AsyncTask<String, Integer, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            JSONObject mJsonObject = new JSONObject();
//            try {
//                mJsonObject.put(MODULE, MODULE_PROCESSING_WORKING);
//                mJsonObject.put(NEOTYPE, TYPE_CANCEL);
//                JSONObject mData = new JSONObject();
//                mData.put(JOB_ID, jobID);
//                mData.put(WORK_FLOW_TRAINSITION_ID, workflowTransitionId);
//                mData.put(RESOURCE_CODE_ID, resourceCodeId);
//                mData.put(JOB_RETTRACTS, jobRetractsArray);
//                mData.put(JOB_RETTRACT_SELECTEDS, jobRetractsArraySelected);
//                mJsonObject.put(DATA, mData);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
////            return makePostRequest(params[0], mJsonObject.toString(), getUserid(), getPass());
//            return eventPostRequest(getModuleInfor(MODULE_PROCESSING_WORKING, context), mJsonObject.toString(), getUserid(), getPass());
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            String resuilt = "";
//            try {
//                JSONObject mJsonObject = new JSONObject(s);
//                int Response = mJsonObject.getInt(CODE);
//                resuilt = Response == 0 ? TRUE : FALSE;
//                Intent intent = new Intent(context, OfficalActivity.class);
////                intent.putExtra(COME_BACK_FROM_SCREEN, isScreen );
//                intent.putExtra(COME_BACK_FROM_SCREEN, mCancelView.ScreenInSide());
//                intent.putExtra(INPUT_COME_BACK, CANCEL_SCREEN);
//                intent.putExtra(FORWARD_RESUILD, resuilt);
//                intent.putExtra(STATISTIC_TYPE, mCancelView.isTatistic());
////                intent.putExtra(STATISTIC_TYPE, isTatistic);
//                if (resuilt.equals(TRUE)) {
//                    mCancelView.DeteleData();
//                }
//                mCancelView.startIntent(intent);
//                mCancelView.closeProgress();
//            } catch (JSONException e) {
//                ShowErrorToast(context);
//                mCancelView.closeProgress();
//                e.printStackTrace();
//            }
//            super.onPostExecute(s);
//        }
//}
//private void refreshUI(String s, String CaseRequest) {
//    switch (CaseRequest) {
//        case ACTION_CANCEL:
//            String resuilt = "";
//            try {
//                JSONObject mJsonObject = new JSONObject(s);
//                int Response = mJsonObject.getInt(CODE);
//                resuilt = Response == 0 ? TRUE : FALSE;
//                Intent intent = new Intent(context, OfficalActivity.class);
////                intent.putExtra(COME_BACK_FROM_SCREEN, isScreen );
//                intent.putExtra(COME_BACK_FROM_SCREEN, mCancelView.ScreenInSide());
//                intent.putExtra(INPUT_COME_BACK, CANCEL_SCREEN);
//                intent.putExtra(FORWARD_RESUILD, resuilt);
//                intent.putExtra(STATISTIC_TYPE, mCancelView.isTatistic());
////                intent.putExtra(STATISTIC_TYPE, isTatistic);
//                if (resuilt.equals(TRUE)) {
//                    mCancelView.DeteleData();
//                }
//                mCancelView.startIntent(intent);
//                mCancelView.closeProgress();
//            } catch (JSONException e) {
//                ShowErrorToast(context);
//                mCancelView.closeProgress();
//                e.printStackTrace();
//            }
//            break;
//        default:
//            break;
//    }
//}
}
