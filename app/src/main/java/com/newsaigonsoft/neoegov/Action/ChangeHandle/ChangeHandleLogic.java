package com.newsaigonsoft.neoegov.Action.ChangeHandle;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.newsaigonsoft.neoegov.AVolleyManager.VolleyTask;
import com.newsaigonsoft.neoegov.AVolleyManager.VolleyTaskCompletedListenner;
import com.newsaigonsoft.neoegov.Adapter.ChangeHandlerAdapter;
import com.newsaigonsoft.neoegov.AsynTaskManager.AsyncTaskCompleteListener;
import com.newsaigonsoft.neoegov.GsonObject.GsonResuilt;
import com.newsaigonsoft.neoegov.OfficalActivity.OfficalActivity;
import com.newsaigonsoft.neoegov.Subjects.ChangeHandleRow;
import com.newsaigonsoft.neoegov.Subjects.ResuiltObject;
import com.newsaigonsoft.neoegov.Subjects.SumManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DATA;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.ID;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.JOB_ID;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MODULE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.NAME;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.NEOTYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MODULE_PROCESSING_WORKING;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.PROCESS_TYPE_ID;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.RESOURCE_CODE_ID;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.SCHEDULE_CONTENT;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.STATISTIC_TYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_CHANGE_HANDLE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.WORK_FLOW_TRAINSITION_ID;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.ACTION_CHANGE_HANDLE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.CHANGE_HANDLE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.COME_BACK_FROM_SCREEN;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.FALSE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.FORWARD_RESUILD;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.INPUT_COME_BACK;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TRUE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.URL_CENTER_6_1;

/**
 * Created by Vinh on 11/23/2017.
 */

public class ChangeHandleLogic extends SumManager implements ChangeHandleImp, AsyncTaskCompleteListener<ResuiltObject>, VolleyTaskCompletedListenner<ResuiltObject> {
    Context context;
    ChangeHandleView mChangeHandleView;
    ArrayList<ChangeHandleRow> arrayHandleProcess;
    ChangeHandlerAdapter adapterHandleProcess;
    long processTypeId;

    public ChangeHandleLogic(Context context, ChangeHandleView mChangeHandleView) {
        this.context = context;
        this.mChangeHandleView = mChangeHandleView;
        getInforAccountFromShareReferenced(context);
    }

    @Override
    public void requestHandleProcess() {
        mChangeHandleView.showProgress();
        new ReadJsonHandleProcess().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, getLink() + URL_CENTER_6_1);
    }

    @Override
    public void RequestChangeHandle() {
        mChangeHandleView.showProgress();
        boolean isCheckList = false;
        for (int i = 0; i < arrayHandleProcess.size(); i++) {
            if (arrayHandleProcess.get(i).isDefault()) {
                isCheckList = true;
                break;
            } else {
                isCheckList = false;
            }
        }
        if (isCheckList) {
            //  new NeoTask(context, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(context, ACTION_CHANGE_HANDLE, addJsonRequestChangeHandle().toString(), getLink() + URL_CENTER_6_1));
            new VolleyTask(context, ACTION_CHANGE_HANDLE, addJsonRequestChangeHandle(), this);
        } else {
            mChangeHandleView.ShowToast("Vui lòng chọn cách thức xử lý");
        }
    }

    private JSONObject addJsonRequestChangeHandle() {
        JSONObject mJsonObject = new JSONObject();
        try {
            mJsonObject.put(MODULE, MODULE_PROCESSING_WORKING);
            mJsonObject.put(NEOTYPE, TYPE_CHANGE_HANDLE);
            JSONObject mData = new JSONObject();
            mData.put(JOB_ID, mChangeHandleView.getDocumentID());
            mData.put(WORK_FLOW_TRAINSITION_ID, mChangeHandleView.getWorkflowTransitionId());
            mData.put(RESOURCE_CODE_ID, mChangeHandleView.getRespirceCodeID());
            mData.put(SCHEDULE_CONTENT, mChangeHandleView.getContent());
            mData.put(PROCESS_TYPE_ID, processTypeId);
            mJsonObject.put(DATA, mData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mJsonObject;
    }

    @Override
    public ChangeHandlerAdapter getHandleAdapter() {
        return adapterHandleProcess;
    }

    @Override
    public void itemClick(int position) {
        for (int i = 0; i < arrayHandleProcess.size(); i++) {
            arrayHandleProcess.get(i).setDefault(arrayHandleProcess.get(position).getName().equals(arrayHandleProcess.get(i).getName()) ? true : false);
            if (arrayHandleProcess.get(position).getName().equals(arrayHandleProcess.get(i).getName()))
                processTypeId = arrayHandleProcess.get(position).getID();
        }
        adapterHandleProcess.notifyDataSetChanged();
    }

    @Override
    public void onTaskCompleted(String s, String CaseRequest) {
        refreshUI(s, CaseRequest);
    }

    private void refreshUI(String s, String CaseRequest) {
        GsonResuilt mGsonResuilt = getGson().fromJson(s, GsonResuilt.class);
        String resuilt = mGsonResuilt.getCode() == 0 ? TRUE : FALSE;
        Intent intent = new Intent(context, OfficalActivity.class);
        intent.putExtra(COME_BACK_FROM_SCREEN, mChangeHandleView.getIsScreen());
        intent.putExtra(INPUT_COME_BACK, CHANGE_HANDLE);
        intent.putExtra(FORWARD_RESUILD, resuilt);
        intent.putExtra(STATISTIC_TYPE, mChangeHandleView.getIsTatistic());
        if (resuilt.equals(TRUE)) {
            mChangeHandleView.DeletedDatabse();
        }
        context.startActivity(intent);
        mChangeHandleView.closeProgress();
    }

    @Override
    public void onVolleyCompleted(String s, String CaseRequest) {
        refreshUI(s, CaseRequest);
    }

    @Override
    public void onVolleyError(String s) {
        mChangeHandleView.ShowToastError(s);
        mChangeHandleView.closeProgress();
    }

    class ReadJsonHandleProcess extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... params) {
            return readFromFile(context, "handle" + mChangeHandleView.getDocumentID());
        }

        @Override
        protected void onPostExecute(String s) {
            arrayHandleProcess = new ArrayList<ChangeHandleRow>();
            try {
                JSONObject mJsonObject = new JSONObject(s);
                JSONArray mJsonArray = mJsonObject.getJSONArray(DATA);
                for (int i = 0; i < mJsonArray.length(); i++) {
                    JSONObject mJsonObjectData = mJsonArray.getJSONObject(i);
                    long id = mJsonObjectData.getLong(ID);
                    String name = mJsonObjectData.getString(NAME);
                    arrayHandleProcess.add(new ChangeHandleRow(false, name, id));
                }
                adapterHandleProcess = new ChangeHandlerAdapter(context, arrayHandleProcess);
                mChangeHandleView.setListViewHandleProcess(adapterHandleProcess, arrayHandleProcess);
                mChangeHandleView.closeProgress();
            } catch (JSONException e) {
                mChangeHandleView.closeProgress();
                e.printStackTrace();
            }
            super.onPostExecute(s);
        }
    }
//    private void refreshUI(String s, String CaseRequest){
//        String resuilt = "";
//        try {
//            JSONObject mJsonObject = new JSONObject(s);
//            int Response = mJsonObject.getInt(CODE);
//            resuilt = Response == 0 ? TRUE : FALSE;
//            Intent intent = new Intent(context, OfficalActivity.class);
//            intent.putExtra(COME_BACK_FROM_SCREEN, mChangeHandleView.getIsScreen());
//            intent.putExtra(INPUT_COME_BACK, CHANGE_HANDLE);
//            intent.putExtra(FORWARD_RESUILD, resuilt);
//            intent.putExtra(STATISTIC_TYPE, mChangeHandleView.getIsTatistic());
//            if (resuilt.equals(TRUE)) {
//                mChangeHandleView.DeletedDatabse();
//            }
//            context.startActivity(intent);
//            mChangeHandleView.closeProgress();
//        } catch (JSONException e) {
//            mChangeHandleView.closeProgress();
//            ShowErrorToast(context);
//            e.printStackTrace();
//        }
//    }
//    class ChangHandle extends AsyncTask<String, Integer, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            JSONObject mJsonObject = new JSONObject();
//            try {
//                mJsonObject.put(MODULE, MODULE_PROCESSING_WORKING);
//                mJsonObject.put(NEOTYPE, TYPE_CHANGE_HANDLE);
//                JSONObject mData = new JSONObject();
//                mData.put(JOB_ID, mChangeHandleView.getDocumentID());
//                mData.put(WORK_FLOW_TRAINSITION_ID, mChangeHandleView.getWorkflowTransitionId());
//                mData.put(RESOURCE_CODE_ID, mChangeHandleView.getRespirceCodeID());
//                mData.put(SCHEDULE_CONTENT, mChangeHandleView.getContent());
//                mData.put(PROCESS_TYPE_ID, mChangeHandleView.getProcessTypeId());
//                mJsonObject.put(DATA, mData);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
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
//                intent.putExtra(COME_BACK_FROM_SCREEN, mChangeHandleView.getIsScreen());
//                intent.putExtra(INPUT_COME_BACK, CHANGE_HANDLE);
//                intent.putExtra(FORWARD_RESUILD, resuilt);
//                intent.putExtra(STATISTIC_TYPE, mChangeHandleView.getIsTatistic());
//                if (resuilt.equals(TRUE)) {
//                    mChangeHandleView.DeletedDatabse();
//                }
//                context.startActivity(intent);
//                mChangeHandleView.closeProgress();
//            } catch (JSONException e) {
//                mChangeHandleView.closeProgress();
//                ShowErrorToast(context);
//                e.printStackTrace();
//            }
//            super.onPostExecute(s);
//        }
//    }
}
