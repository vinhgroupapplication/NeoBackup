package com.newsaigonsoft.neoegov.Action.FeedBack;

import android.content.Context;
import android.content.Intent;

import com.newsaigonsoft.neoegov.AVolleyManager.VolleyTask;
import com.newsaigonsoft.neoegov.AVolleyManager.VolleyTaskCompletedListenner;
import com.newsaigonsoft.neoegov.AsynTaskManager.AsyncTaskCompleteListener;
import com.newsaigonsoft.neoegov.GsonObject.GsonResuilt;
import com.newsaigonsoft.neoegov.OfficalActivity.OfficalActivity;
import com.newsaigonsoft.neoegov.Subjects.AttachFile;
import com.newsaigonsoft.neoegov.Subjects.ResuiltObject;
import com.newsaigonsoft.neoegov.Subjects.SumManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.ATTACH_FILE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DATA;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.EXTENSION;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.FEED_BACK_PROCESS_ID;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.JOB_ID;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MODULE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.NAME;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.NEOTYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MODULE_PROCESSING_WORKING;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.RESOURCE_CODE_ID;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.SCHEDULE_CONTENT;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.STATISTIC_TYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_FEED_BACK;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.WORK_FLOW_TRAINSITION_ID;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.ACTION_FEED_BACK;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.COME_BACK_FROM_SCREEN;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.FALSE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.FEED_BACK;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.FORWARD_RESUILD;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.INPUT_COME_BACK;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TRUE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.nULL_STRING;

/**
 * Created by Vinh on 11/23/2017.
 */

public class FeedBackLogic extends SumManager implements FeedBackImp, AsyncTaskCompleteListener<ResuiltObject>, VolleyTaskCompletedListenner<ResuiltObject> {
    Context context;
    FeedBackView mFeedBackView;
    JSONArray mJsonArrayAttach;

    public FeedBackLogic(Context context, FeedBackView mFeedBackView) {
        this.context = context;
        this.mFeedBackView = mFeedBackView;
        getInforAccountFromShareReferenced(context);
    }

    @Override
    public void RequestFeedBack(ArrayList<AttachFile> arrFileAttach) {
        mFeedBackView.showProgress();
        mJsonArrayAttach = new JSONArray();
        for (int i = 0; i < arrFileAttach.size(); i++) {
            JSONObject mJsonObject = new JSONObject();
            try {
                mJsonObject.put(NAME, arrFileAttach.get(i).getFileOnlyName());
                mJsonObject.put(EXTENSION, arrFileAttach.get(i).getFileTyPe());
                mJsonObject.put(SCHEDULE_CONTENT, arrFileAttach.get(i).getBase64Code().replace("\n", ""));
                mJsonArrayAttach.put(mJsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (!mFeedBackView.getFeedBackContent().trim().equals(nULL_STRING)) {
//            new FeedBack().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, getLink() + URL_CENTER_6_1);
//            new NeoTask(context, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(context, ACTION_FEED_BACK, addJsonRequestFeedBack().toString(), getLink() + URL_CENTER_6_1));
            new VolleyTask(context, ACTION_FEED_BACK, addJsonRequestFeedBack(), this);
        } else {
            mFeedBackView.closeProgress();
            ShowErrorToast(context, "Vui lòng nhập nội dung góp ý");
        }
    }

    private void refreshUI(String s, String CaseRequest) {
        GsonResuilt mGsonResuilt = getGson().fromJson(s, GsonResuilt.class);
        String resuilt = mGsonResuilt.getCode() == 0 ? TRUE : FALSE;
        Intent intent = new Intent(context, OfficalActivity.class);
        //// TODO: 9/1/2017  comback screen
        intent.putExtra(COME_BACK_FROM_SCREEN, mFeedBackView.getScreen());
        intent.putExtra(INPUT_COME_BACK, FEED_BACK);
        //// TODO: 9/1/2017  comback screen
        intent.putExtra(FORWARD_RESUILD, resuilt);
        intent.putExtra(STATISTIC_TYPE, mFeedBackView.getTatistic());
        context.startActivity(intent);
        mFeedBackView.closeProgress();
    }

    @Override
    public void onTaskCompleted(String s, String CaseRequest) {
        refreshUI(s, CaseRequest);
    }

    private JSONObject addJsonRequestFeedBack() {
        JSONObject mJsonObject = new JSONObject();
        try {
            mJsonObject.put(MODULE, MODULE_PROCESSING_WORKING);
            mJsonObject.put(NEOTYPE, TYPE_FEED_BACK);
            JSONObject mData = new JSONObject();
            mData.put(JOB_ID, mFeedBackView.getDocumentID());
            mData.put(FEED_BACK_PROCESS_ID, 0);
            mData.put(WORK_FLOW_TRAINSITION_ID, mFeedBackView.getWorkflowTransitionId());
            mData.put(RESOURCE_CODE_ID, mFeedBackView.getResourceCodeId());
            mData.put(SCHEDULE_CONTENT, mFeedBackView.getFeedBackContent());
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
        mFeedBackView.closeProgress();
        mFeedBackView.ToastError(s);
    }

//    class FeedBack extends AsyncTask<String, Integer, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            JSONObject mJsonObject = new JSONObject();
//            try {
//                mJsonObject.put(MODULE, MODULE_PROCESSING_WORKING);
//                mJsonObject.put(NEOTYPE, TYPE_FEED_BACK);
//                JSONObject mData = new JSONObject();
//                mData.put(JOB_ID, mFeedBackView.getDocumentID());
//                mData.put(FEED_BACK_PROCESS_ID, 0);
//                mData.put(WORK_FLOW_TRAINSITION_ID, mFeedBackView.getWorkflowTransitionId());
//                mData.put(RESOURCE_CODE_ID, mFeedBackView.getResourceCodeId());
//                mData.put(SCHEDULE_CONTENT, mFeedBackView.getFeedBackContent());
//                mData.put(ATTACH_FILE, mJsonArrayAttach);
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
//                //// TODO: 9/1/2017  comback screen
//                intent.putExtra(COME_BACK_FROM_SCREEN, mFeedBackView.getScreen());
//                intent.putExtra(INPUT_COME_BACK, FEED_BACK);
//                //// TODO: 9/1/2017  comback screen
//                intent.putExtra(FORWARD_RESUILD, resuilt);
//                intent.putExtra(STATISTIC_TYPE, mFeedBackView.getTatistic());
//                context.startActivity(intent);
//                mFeedBackView.closeProgress();
//            } catch (JSONException e) {
//                mFeedBackView.closeProgress();
//                ShowErrorToast(context);
//                e.printStackTrace();
//            }
//            super.onPostExecute(s);
//        }
//    }
}

