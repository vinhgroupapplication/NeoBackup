package com.newsaigonsoft.neoegov.Action.Extend;

import android.content.Context;
import android.content.Intent;

import com.newsaigonsoft.neoegov.AVolleyManager.VolleyTask;
import com.newsaigonsoft.neoegov.AVolleyManager.VolleyTaskCompletedListenner;
import com.newsaigonsoft.neoegov.AsynTaskManager.AsyncTaskCompleteListener;
import com.newsaigonsoft.neoegov.GsonObject.GsonResuilt;
import com.newsaigonsoft.neoegov.OfficalActivity.OfficalActivity;
import com.newsaigonsoft.neoegov.Subjects.ResuiltObject;
import com.newsaigonsoft.neoegov.Subjects.SumManager;

import org.json.JSONException;
import org.json.JSONObject;

import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DATA;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.EXPIRATION_DATE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.EXTENSION_TYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.JOB_ID;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MODULE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.NEOTYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MODULE_PROCESSING_WORKING;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.PROCESS_DAYS;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.RESOURCE_CODE_ID;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.SCHEDULE_CONTENT;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.STATISTIC_TYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_EXTEND;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.ACTION_EXTEND;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.COME_BACK_FROM_SCREEN;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.EXTEND_SCREEN;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.FALSE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.FORWARD_RESUILD;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.INPUT_COME_BACK;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TRUE;

/**
 * Created by Vinh on 11/23/2017.
 */

public class ExtendLogic extends SumManager implements ExtendImp, AsyncTaskCompleteListener<ResuiltObject>, VolleyTaskCompletedListenner<ResuiltObject> {
    Context context;
    ExtendView mExtendView;

    public ExtendLogic(Context context, ExtendView mExtendView) {
        this.context = context;
        this.mExtendView = mExtendView;
        getInforAccountFromShareReferenced(context);
    }

    @Override
    public void RequestExtexd() {
        mExtendView.showProgress();
//        new NeoTask(context, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(context, ACTION_EXTEND, addJsonReuqestExtend().toString(), getLink() + URL_CENTER_6_1));
//        new Extend().execute(getLink() + URL_CENTER_6_1);
        new VolleyTask(context, ACTION_EXTEND, addJsonReuqestExtend(), this);
    }

    @Override
    public void onTaskCompleted(String s, String CaseRequest) {
        refreshUI(s, CaseRequest);
    }

    private void refreshUI(String s, String CaseRequest) {
        GsonResuilt mGsonResuilt = getGson().fromJson(s, GsonResuilt.class);
        String resuilt = mGsonResuilt.getCode() == 0 ? TRUE : FALSE;
        Intent intent = new Intent(context, OfficalActivity.class);
        intent.putExtra(COME_BACK_FROM_SCREEN, mExtendView.getScreen());
        intent.putExtra(INPUT_COME_BACK, EXTEND_SCREEN);
        intent.putExtra(FORWARD_RESUILD, resuilt);
        intent.putExtra(STATISTIC_TYPE, mExtendView.getTatistic());
        startActivity(intent);
        mExtendView.closeProgress();
    }


    private JSONObject addJsonReuqestExtend() {
        JSONObject mJsonObject = new JSONObject();
        try {
            mJsonObject.put(MODULE, MODULE_PROCESSING_WORKING);
            mJsonObject.put(NEOTYPE, TYPE_EXTEND);
            JSONObject mData = new JSONObject();
            mData.put(JOB_ID, mExtendView.getJobID());
            mData.put(RESOURCE_CODE_ID, mExtendView.getResourceCodeId());
            mData.put(PROCESS_DAYS, mExtendView.getProcessDay());
            mData.put(EXPIRATION_DATE, getMilisecondDay(mExtendView.getExpirationDate()));
            mData.put(SCHEDULE_CONTENT, mExtendView.getContent());
            mData.put(EXTENSION_TYPE, false);
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
        mExtendView.closeProgress();
        mExtendView.ToastError(s);
    }

//    class Extend extends AsyncTask<String, Integer, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            JSONObject mJsonObject = new JSONObject();
//            try {
//                mJsonObject.put(MODULE, MODULE_PROCESSING_WORKING);
//                mJsonObject.put(NEOTYPE, TYPE_EXTEND);
//                JSONObject mData = new JSONObject();
//                mData.put(JOB_ID, mExtendView.getJobID());
//                mData.put(RESOURCE_CODE_ID, mExtendView.getResourceCodeId());
//                mData.put(PROCESS_DAYS,mExtendView.getProcessDay());
//                mData.put(EXPIRATION_DATE, getMilisecondDay(mExtendView.getExpirationDate()));
//                mData.put(SCHEDULE_CONTENT, mExtendView.getContent());
//                mData.put(EXTENSION_TYPE, false);
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
//                intent.putExtra(COME_BACK_FROM_SCREEN, mExtendView.getScreen());
//                intent.putExtra(INPUT_COME_BACK, EXTEND_SCREEN);
//                intent.putExtra(FORWARD_RESUILD, resuilt);
//                intent.putExtra(STATISTIC_TYPE, mExtendView.getTatistic());
//                startActivity(intent);
//                mExtendView.closeProgress();
//            } catch (JSONException e) {
//                ShowErrorToast(context);
//                e.printStackTrace();
//            }
//            super.onPostExecute(s);
//        }
//    }
}
