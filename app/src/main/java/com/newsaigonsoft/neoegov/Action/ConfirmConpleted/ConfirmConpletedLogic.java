package com.newsaigonsoft.neoegov.Action.ConfirmConpleted;

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

import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.CONFIRM_COMPLETED;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.CONTENT_REPORT;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DATA;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DATE_REPORT;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MODULE_LOOKUP_DOCUMENT;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MODULE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.NEOTYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.STATISTIC_TYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TASK_ID;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.ACTION_CONFIRM_COMPLETED;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.COME_BACK_FROM_SCREEN;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.FALSE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.FORWARD_RESUILD;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.INPUT_COME_BACK;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_DPM_DELAYS;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_DPM_INDUE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_DPM_ONTIME;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_DPM_OUT_OF_DATE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TRUE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.nULL_STRING;

/**
 * Created by Vinh on 11/22/2017.
 */

public class ConfirmConpletedLogic extends SumManager implements ConfirmConpletedImp, AsyncTaskCompleteListener<ResuiltObject>, VolleyTaskCompletedListenner<ResuiltObject> {
    Context context;
    ConfirmConpletedView mConfirmConpletedView;

    public ConfirmConpletedLogic(Context context, ConfirmConpletedView mConfirmConpletedView) {
        this.context = context;
        this.mConfirmConpletedView = mConfirmConpletedView;
        getInforAccountFromShareReferenced(context);
    }

    @Override
    public void RequestConfirm() {
        if (mConfirmConpletedView.getConfirmDay().equals(nULL_STRING)) {
            ShowErrorToast(context, "Vui lòng chọn ngày xác nhận hoàn thành");
        } else {
            mConfirmConpletedView.showProgress();
//            new ComfirmCompleted().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, getLink() + URL_CENTER_6_1);
//            new NeoTask(context, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(context, ACTION_CONFIRM_COMPLETED, addJsonRequestConfirmCompleted().toString(), getLink() + URL_CENTER_6_1));
            new VolleyTask(context, ACTION_CONFIRM_COMPLETED, addJsonRequestConfirmCompleted(), this);
        }
    }

    private JSONObject addJsonRequestConfirmCompleted() {
        JSONObject mJsonObject = new JSONObject();
        try {
            mJsonObject.put(MODULE, MODULE_LOOKUP_DOCUMENT);
            mJsonObject.put(NEOTYPE, CONFIRM_COMPLETED);
            JSONObject mData = new JSONObject();
            mData.put(TASK_ID, mConfirmConpletedView.getTaskID());
            mData.put(DATE_REPORT, getMilisecondDay(mConfirmConpletedView.getConfirmDay()));
            mData.put(CONTENT_REPORT, mConfirmConpletedView.getConfirmContent());
            mJsonObject.put(DATA, mData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mJsonObject;
    }

    @Override
    public void onTaskCompleted(String s, String CaseRequest) {
        refreshUI(s, CaseRequest);
    }

    public void ResponseFromHome(String resuilt) {
        String Screen = mConfirmConpletedView.getScreen();
        Intent intent;
        switch (Screen) {
            case TAP_DPM_DELAYS:
            case TAP_DPM_INDUE:
            case TAP_DPM_ONTIME:
            case TAP_DPM_OUT_OF_DATE:
                intent = new Intent();
                intent.putExtra(COME_BACK_FROM_SCREEN, mConfirmConpletedView.getScreen());
                intent.putExtra(INPUT_COME_BACK, CONFIRM_COMPLETED);
                intent.putExtra(FORWARD_RESUILD, resuilt);
                intent.putExtra(STATISTIC_TYPE, mConfirmConpletedView.getTatistic());
                mConfirmConpletedView.startIntentOnbackPress(intent);
                mConfirmConpletedView.closeProgress();
                break;
            default:
                intent = new Intent(context, OfficalActivity.class);
                intent.putExtra(COME_BACK_FROM_SCREEN, mConfirmConpletedView.getScreen());
                intent.putExtra(INPUT_COME_BACK, CONFIRM_COMPLETED);
                intent.putExtra(FORWARD_RESUILD, resuilt);
                intent.putExtra(STATISTIC_TYPE, mConfirmConpletedView.getTatistic());
                mConfirmConpletedView.startIntent(intent);
                mConfirmConpletedView.closeProgress();
                break;
        }
    }

    @Override
    public void onVolleyCompleted(String s, String CaseRequest) {
        refreshUI(s, CaseRequest);
    }

    @Override
    public void onVolleyError(String s) {
        mConfirmConpletedView.closeProgress();
        mConfirmConpletedView.ToastError(s);
    }

    private void refreshUI(String s, String CaseRequest) {
        GsonResuilt mGsonResuilt = getGson().fromJson(s, GsonResuilt.class);
        String resuilt = mGsonResuilt.getCode() == 0 ? TRUE : FALSE;
        ResponseFromHome(resuilt);
    }


//    class ComfirmCompleted extends AsyncTask<String, Integer, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            JSONObject mJsonObject = new JSONObject();
//            try {
//                mJsonObject.put(MODULE, MODULE_LOOKUP_DOCUMENT);
//                mJsonObject.put(NEOTYPE, CONFIRM_COMPLETED);
//                JSONObject mData = new JSONObject();
//                mData.put(TASK_ID, mConfirmConpletedView.getTaskID());
//                mData.put(DATE_REPORT, getMilisecondDay(mConfirmConpletedView.getConfirmDay()));
//                mData.put(CONTENT_REPORT, mConfirmConpletedView.getConfirmContent());
//                mJsonObject.put(DATA, mData);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            return eventPostRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, context), mJsonObject.toString(), getUserid(), getPass());
//
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
//                String Screen = mConfirmConpletedView.getScreen();
//                intent.putExtra(COME_BACK_FROM_SCREEN, mConfirmConpletedView.getScreen());
//                intent.putExtra(INPUT_COME_BACK, CONFIRM_COMPLETED);
//                intent.putExtra(FORWARD_RESUILD, resuilt);
//                intent.putExtra(STATISTIC_TYPE, mConfirmConpletedView.getTatistic());
//                mConfirmConpletedView.startIntent(intent);
//                mConfirmConpletedView.closeProgress();
//            } catch (JSONException e) {
//                mConfirmConpletedView.closeProgress();
//                ShowErrorToast(context);
//                e.printStackTrace();
//            }
//            super.onPostExecute(s);
//        }
//    }
}
