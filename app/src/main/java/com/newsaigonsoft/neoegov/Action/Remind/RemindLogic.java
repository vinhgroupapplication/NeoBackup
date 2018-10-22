package com.newsaigonsoft.neoegov.Action.Remind;

import android.content.Context;
import android.content.Intent;

import com.newsaigonsoft.neoegov.AVolleyManager.VolleyTask;
import com.newsaigonsoft.neoegov.AVolleyManager.VolleyTaskCompletedListenner;
import com.newsaigonsoft.neoegov.Adapter.RemindAdapter;
import com.newsaigonsoft.neoegov.AsynTaskManager.AsyncTaskCompleteListener;
import com.newsaigonsoft.neoegov.GsonObject.GsonResuilt;
import com.newsaigonsoft.neoegov.OfficalActivity.OfficalActivity;
import com.newsaigonsoft.neoegov.Subjects.RemindRow;
import com.newsaigonsoft.neoegov.Subjects.ResuiltObject;
import com.newsaigonsoft.neoegov.Subjects.SumManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.CONTENT_EMAIL;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.CONTENT_SMS;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DATA;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.EXECUTION_UNIT;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MODULE_LOOKUP_DOCUMENT;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MODULE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.NAME;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.NEOTYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.PHONE_NUMBER;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.SCHEDULE_CONTENT;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.STATISTIC_TYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.SUBJECT_EMAIL;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TASK_ID;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TRAINFER_EMAIL;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_REMIND;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.ACTION_REMIND;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.COME_BACK_FROM_SCREEN;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.FALSE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.FORWARD_RESUILD;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.INPUT_COME_BACK;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.REMIND;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_DPM_DELAYS;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_DPM_INDUE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_DPM_ONTIME;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_DPM_OUT_OF_DATE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TRUE;

/**
 * Created by Vinh on 12/16/2017.
 */

public class RemindLogic extends SumManager implements RemindImp, AsyncTaskCompleteListener<ResuiltObject>, VolleyTaskCompletedListenner<ResuiltObject> {
    Context context;
    RemindView mRemindView;
    List<RemindRow> arrRemind;
    JSONArray mJsonArrayExecutionUnit;

    public RemindLogic(Context context, RemindView mRemindView) {
        this.context = context;
        this.mRemindView = mRemindView;
        getInforAccountFromShareReferenced(context);
    }


    @Override
    public void getListRemind(String jsonExecutionUnit) {
        if (jsonExecutionUnit != null) {
            getListExExecutionUnit(jsonExecutionUnit);
            RemindAdapter adapter = new RemindAdapter(context, arrRemind);
            mRemindView.setLvReturn(adapter);

        }
    }

    @Override
    public void SaveAndClose() {
        if (arrRemind.size() != 0) {
            if (!arrRemind.get(0).isSms() && !arrRemind.get(0).isEmail()) {
                mRemindView.showToastError("Vui lòng chọn Email, SMS");

            } else {
                if (arrRemind.get(0).isSms() && arrRemind.get(0).isEmail()) {
                    if (
//                           mRemindView.getEmailContent().equals(nULL_STRING)
//                            || mRemindView.getSmsContent().equals(nULL_STRING)
//                            || mRemindView.getTitleEmail().equals(nULL_STRING)||
                            mRemindView.getEmailContent().trim().length() == 0
                                    || mRemindView.getSmsContent().trim().length() == 0
                                    || mRemindView.getTitleEmail().trim().length() == 0) {
                        mRemindView.showToastError("Vui lòng nhập nội dung sms và nội dung email");
                    } else {
                        requestRemindForward();
                    }
                } else {
                    if (arrRemind.get(0).isSms() && !arrRemind.get(0).isEmail()) {
                        if (
//                                mRemindView.getSmsContent().equals(nULL_STRING) ||
                                mRemindView.getSmsContent().trim().length() == 0) {
                            mRemindView.showToastError("Vui lòng nhập nội dung SMS");
                        } else {
                            requestRemindForward();
                        }
                    } else {
                        if (arrRemind.get(0).isEmail() && !arrRemind.get(0).isSms()) {
                            if (
//                                    mRemindView.getTitleEmail().equals(nULL_STRING)
//                                    || mRemindView.getEmailContent().equals(nULL_STRING) ||
                                    mRemindView.getTitleEmail().trim().length() == 0
                                            || mRemindView.getEmailContent().trim().length() == 0) {
                                mRemindView.showToastError("Vui lòng nhập Tiêu đề, nội dung email");
                            } else {
                                requestRemindForward();
                            }
                        }
                    }
                }
            }
        }
    }

    private void getListExExecutionUnit(String JsonExecutionUnit) {
        arrRemind = new ArrayList<RemindRow>();
        try {
            JSONArray mArrayExecutionUnit = new JSONArray(JsonExecutionUnit);
            for (int i = 0; i < mArrayExecutionUnit.length(); i++) {
                JSONObject mJsonObject = mArrayExecutionUnit.getJSONObject(i);
                String content = mJsonObject.getString(SCHEDULE_CONTENT);
                String phoneNumber = mJsonObject.getString(PHONE_NUMBER);
                String email = mJsonObject.getString(TRAINFER_EMAIL);
                String name = mJsonObject.getString(NAME);
                arrRemind.add(new RemindRow(true, true, content, name, phoneNumber, email));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void requestRemindForward() {
        mRemindView.showProgress();
        mJsonArrayExecutionUnit = new JSONArray();
        for (int i = 0; i < arrRemind.size(); i++) {
            JSONObject mJsonObject = new JSONObject();
            try {
                mJsonObject.put(NAME, arrRemind.get(i).getProcessPosition());
                mJsonObject.put(SCHEDULE_CONTENT, arrRemind.get(i).getContent());
                if (arrRemind.get(i).isSms()) {
                    mJsonObject.put(PHONE_NUMBER, arrRemind.get(i).getNumber());
                }
                if (arrRemind.get(i).isEmail()) {
                    mJsonObject.put(TRAINFER_EMAIL, arrRemind.get(i).getEmailName());
                }
                mJsonArrayExecutionUnit.put(mJsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
//        new NeoTask(context, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(context, ACTION_REMIND, addJsonRequestRemind().toString(), getLink() + URL_CENTER_6_1));
//        new RemindForward().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, getLink() + URL_CENTER_6_1);
        new VolleyTask(context, ACTION_REMIND, addJsonRequestRemind(), this);
    }


    @Override
    public void onTaskCompleted(String s, String CaseRequest) {
        refreshUI(s, CaseRequest);
    }

    private void refreshUI(String s, String CaseRequest) {
        GsonResuilt mGsonResuilt = getGson().fromJson(s, GsonResuilt.class);
        String resuilt = mGsonResuilt.getCode() == 0 ? TRUE : FALSE;
        ResponseFromHome(resuilt);
//            Intent intent = new Intent(context, OfficalActivity.class);
//            //// TODO: 9/1/2017  comback screen
//            intent.putExtra(COME_BACK_FROM_SCREEN, mRemindView.getIsScreen());
//            intent.putExtra(INPUT_COME_BACK, REMIND);
//            //// TODO: 9/1/2017  comback screen
//            intent.putExtra(FORWARD_RESUILD, resuilt);
//            intent.putExtra(STATISTIC_TYPE, mRemindView.getIsTatistic());
//            context.startActivity(intent);
//            mRemindView.closeProgress();
    }


    public void ResponseFromHome(String resuilt) {
        String Screen = mRemindView.getIsScreen();
        Intent intent;
        switch (Screen) {
            case TAP_DPM_DELAYS:
            case TAP_DPM_INDUE:
            case TAP_DPM_ONTIME:
            case TAP_DPM_OUT_OF_DATE:
                intent = new Intent();
                intent.putExtra(COME_BACK_FROM_SCREEN, mRemindView.getIsScreen());
                intent.putExtra(INPUT_COME_BACK, REMIND);
                intent.putExtra(FORWARD_RESUILD, resuilt);
                intent.putExtra(STATISTIC_TYPE, mRemindView.getIsTatistic());
                mRemindView.startIntentOnbackPress(intent);
                mRemindView.closeProgress();
                break;
            default:
                intent = new Intent(context, OfficalActivity.class);
                intent.putExtra(COME_BACK_FROM_SCREEN, mRemindView.getIsScreen());
                intent.putExtra(INPUT_COME_BACK, REMIND);
                intent.putExtra(FORWARD_RESUILD, resuilt);
                intent.putExtra(STATISTIC_TYPE, mRemindView.getIsTatistic());
                mRemindView.startIntent(intent);
                mRemindView.closeProgress();
                break;
        }
    }

    private JSONObject addJsonRequestRemind() {
        JSONObject mJsonObject = new JSONObject();
        try {
            mJsonObject.put(MODULE, MODULE_LOOKUP_DOCUMENT);
            mJsonObject.put(NEOTYPE, TYPE_REMIND);
            JSONObject mData = new JSONObject();
            mData.put(TASK_ID, mRemindView.getTaskID());
            mData.put(CONTENT_SMS, mRemindView.getSmsContent());
            mData.put(SUBJECT_EMAIL, mRemindView.getTitleEmail());
            mData.put(CONTENT_EMAIL, mRemindView.getEmailContent());
            mData.put(EXECUTION_UNIT, mJsonArrayExecutionUnit);
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
        mRemindView.closeProgress();
        mRemindView.showToastError("Lỗi kết nối");
    }
}
