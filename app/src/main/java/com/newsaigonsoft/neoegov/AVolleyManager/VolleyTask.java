package com.newsaigonsoft.neoegov.AVolleyManager;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonSyntaxException;
import com.newsaigonsoft.neoegov.GsonObject.GsonDocument;
import com.newsaigonsoft.neoegov.GsonObject.GsonResuilt;
import com.newsaigonsoft.neoegov.SQLite.SQLite;
import com.newsaigonsoft.neoegov.Subjects.KeyManager;
import com.newsaigonsoft.neoegov.Subjects.ResuiltObject;
import com.newsaigonsoft.neoegov.Subjects.SumManager;

import org.json.JSONObject;

import java.util.List;

import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.HOTLINE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MODULE_LOOKUP_DOCUMENT;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MODULE_PROCESSING_WORKING;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.SCHEDULE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MODULE_WORK_ARISE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.ACTION_ADD_TRAINFER;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.ACTION_CANCEL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.ACTION_CHANGE_HANDLE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.ACTION_CONFIRM_COMPLETED;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.ACTION_EXTEND;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.ACTION_FEED_BACK;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.ACTION_FORWARD_DEPARTMENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.ACTION_FORWARD_PERSON;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.ACTION_REMIND;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.ACTION_RETURN;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.ACTION_SAVE_DRAIF;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.CREATE_TABLE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DETAIL_HOME_DOC_COMING;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DETAIL_HOME_DOC_SENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DETAIL_HOTLINE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DETAIL_LOOKUP_COMING;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DETAIL_LOOKUP_INTERNAL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DETAIL_LOOKUP_SENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DETAIL_PROCESS;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DETAIL_TASK;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DETAIL_WORK_ARISE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DOC_PROCESS_LIST;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.GET_COUNT_DATE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.GET_EXPIRATION_DATE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.HANDLER_PROCESS;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.HOTLINE_OR;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.HOTLINE_REQUEST;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LIST_CANEL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LIST_DEPARTMENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LIST_DOCUMENT_DEPARTMENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LIST_DOCUMENT_SQLITE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LIST_PERSON;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LIST_RETURN_PERSON;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LOOKUP_COMING;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LOOKUP_INTERNAL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LOOKUP_SENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SCHEDULE_DAY_REQUEST;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SCHEDULE_HOME;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SCHEDULE_MONTH_REQUEST;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SCHEDULE_WEEK_REQUEST;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SEARCH_COMING;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SEARCH_DOCUMENT_TYPE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SEARCH_INTERNAL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SEARCH_LIST_DEPARTMENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SEARCH_NUMBER_COMING;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SEARCH_NUMBER_INTERNAL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SEARCH_NUMBER_PROCESS;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SEARCH_NUMBER_SENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SEARCH_PROCESS;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SEARCH_SENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SEARCH_WORK_ARISE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SLIDER_MENU;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SQLLITE_DOCUMENT_LIST;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.STATIS_DOC_COMING;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.STATIS_DOC_SENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.STATIS_DPM;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.STATIS_LIST;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.STATIS_LIST_ROW;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.STATIS_LIST_TOTAL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.STA_COMING_DPM_LIST;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.STA_COMING_DPM_LIST_ARISE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.STA_COMING_FULL_DPM;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.STA_COMING_FULL_DPM_ARISE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.STA_COMING_LIST_DPM;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.STA_COMING_LIST_DPM_ARISE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.STA_COMING_PERSON_JOIN;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.STA_COMING_PERSON_JOIN_ARISE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.STA_DOC_PROCESS_ON_TIME_FULL_ARISE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAG;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TASK_DETECT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TASK_REQUEST;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TREE_HOME_STATISTICAL_COMING;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TREE_HOME_STATISTICAL_SEND;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TREE_HOTLINE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TREE_LOOKUP_COMING;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TREE_LOOKUP_INTERNAL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TREE_LOOKUP_SEND;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TREE_PROCESS;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TREE_WORK_ARISE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.WORK_ARISE;

/**
 * Created by Vinh on 3/13/2018.
 */

public class VolleyTask extends SumManager {
    Context mContext;
    VolleyTaskCompletedListenner<ResuiltObject> mVolleyCallBack;
    String Action;
    SQLite mSqLite;
    JSONObject mRequest;
    InforRequest mInforRequest;

    public VolleyTask(Context context, String action, JSONObject request, VolleyTaskCompletedListenner<ResuiltObject> volleyCallBack) {
        this.mContext = context;
        this.Action = action;
        this.mRequest = request;
        this.mVolleyCallBack = volleyCallBack;
        mSqLite = new SQLite(context, LIST_DOCUMENT_SQLITE, null, 1);
        RunningTask();
    }

    public void RunningTask() {
        RequestQueue queue = Volley.newRequestQueue(mContext);
        switch (Action) {
            case STA_DOC_PROCESS_ON_TIME_FULL_ARISE:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_WORK_ARISE, mContext), mRequest, mContext, STA_DOC_PROCESS_ON_TIME_FULL_ARISE);
                break;
            case DETAIL_WORK_ARISE:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_WORK_ARISE, mContext), mRequest, mContext, DETAIL_WORK_ARISE);
                break;
            case WORK_ARISE:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_WORK_ARISE, mContext), mRequest, mContext, WORK_ARISE);
                break;
            case SEARCH_WORK_ARISE:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_WORK_ARISE, mContext), mRequest, mContext, SEARCH_WORK_ARISE);
                break;
            case STA_COMING_PERSON_JOIN:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, mContext), mRequest, mContext, STA_COMING_PERSON_JOIN);
                break;
            case KeyManager.REMIND_PERSON:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_PROCESSING_WORKING, mContext), mRequest, mContext, KeyManager.REMIND_PERSON);
                break;
            case ACTION_ADD_TRAINFER:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, mContext), mRequest, mContext, ACTION_ADD_TRAINFER);
                break;
            case ACTION_CANCEL:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_PROCESSING_WORKING, mContext), mRequest, mContext, ACTION_CANCEL);
                break;
            case ACTION_CHANGE_HANDLE:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, mContext), mRequest, mContext, ACTION_CHANGE_HANDLE);
                break;
            case ACTION_CONFIRM_COMPLETED:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, mContext), mRequest, mContext, ACTION_CONFIRM_COMPLETED);
                break;
            case ACTION_EXTEND:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_PROCESSING_WORKING, mContext), mRequest, mContext, ACTION_EXTEND);
                break;
            case ACTION_FEED_BACK:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_PROCESSING_WORKING, mContext), mRequest, mContext, ACTION_FEED_BACK);
                break;
            case ACTION_FORWARD_DEPARTMENT:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_PROCESSING_WORKING, mContext), mRequest, mContext, ACTION_FORWARD_DEPARTMENT);
                break;
            case GET_EXPIRATION_DATE:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_PROCESSING_WORKING, mContext), mRequest, mContext, GET_EXPIRATION_DATE);
                break;
            case GET_COUNT_DATE:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_PROCESSING_WORKING, mContext), mRequest, mContext, GET_COUNT_DATE);
                break;
            case ACTION_FORWARD_PERSON:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_PROCESSING_WORKING, mContext), mRequest, mContext, ACTION_FORWARD_PERSON);
                break;
            case ACTION_REMIND:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, mContext), mRequest, mContext, ACTION_REMIND);
                break;
            case ACTION_RETURN:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_PROCESSING_WORKING, mContext), mRequest, mContext, ACTION_RETURN);
                break;
            case ACTION_SAVE_DRAIF:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_PROCESSING_WORKING, mContext), mRequest, mContext, ACTION_SAVE_DRAIF);
                break;
            case LIST_DEPARTMENT:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_PROCESSING_WORKING, mContext), mRequest, mContext, LIST_DEPARTMENT);
                break;
            case LIST_PERSON:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_PROCESSING_WORKING, mContext), mRequest, mContext, LIST_PERSON);
                break;
            case LIST_RETURN_PERSON:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_PROCESSING_WORKING, mContext), mRequest, mContext, LIST_RETURN_PERSON);
                break;
            case LIST_CANEL:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_PROCESSING_WORKING, mContext), mRequest, mContext, LIST_CANEL);
                break;
            case TREE_HOTLINE:
                mInforRequest = getInforRequest(getModuleInfor(HOTLINE, mContext), mRequest, mContext, TREE_HOTLINE);
                break;
            case TREE_HOME_STATISTICAL_COMING:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, mContext), mRequest, mContext, TREE_HOME_STATISTICAL_COMING);
                break;
            case TREE_HOME_STATISTICAL_SEND:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, mContext), mRequest, mContext, TREE_HOME_STATISTICAL_SEND);
                break;
            case TREE_LOOKUP_COMING:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, mContext), mRequest, mContext, TREE_LOOKUP_COMING);
                break;
            case TREE_LOOKUP_SEND:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, mContext), mRequest, mContext, TREE_LOOKUP_SEND);
                break;
            case TREE_LOOKUP_INTERNAL:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, mContext), mRequest, mContext, TREE_LOOKUP_INTERNAL);
                break;
            case TREE_PROCESS:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_PROCESSING_WORKING, mContext), mRequest, mContext, TREE_PROCESS);
                break;
            case TREE_WORK_ARISE:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_WORK_ARISE, mContext), mRequest, mContext, TREE_WORK_ARISE);
                break;
            case SCHEDULE_DAY_REQUEST:
                mInforRequest = getInforRequest(getModuleInfor(SCHEDULE, mContext), mRequest, mContext, SCHEDULE_DAY_REQUEST);
                break;
            case SCHEDULE_WEEK_REQUEST:
                mInforRequest = getInforRequest(getModuleInfor(SCHEDULE, mContext), mRequest, mContext, SCHEDULE_WEEK_REQUEST);
                break;
            case SCHEDULE_MONTH_REQUEST:
                mInforRequest = getInforRequest(getModuleInfor(SCHEDULE, mContext), mRequest, mContext, SCHEDULE_MONTH_REQUEST);
                break;
            case DETAIL_HOTLINE:
                mInforRequest = getInforRequest(getModuleInfor(HOTLINE, mContext), mRequest, mContext, DETAIL_HOTLINE);
                break;
            case DETAIL_HOME_DOC_SENT:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, mContext), mRequest, mContext, DETAIL_HOME_DOC_SENT);
                break;
            case DETAIL_HOME_DOC_COMING:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, mContext), mRequest, mContext, DETAIL_HOME_DOC_COMING);
                break;
            case DETAIL_LOOKUP_COMING:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, mContext), mRequest, mContext, DETAIL_LOOKUP_COMING);
                break;
            case DETAIL_LOOKUP_SENT:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, mContext), mRequest, mContext, DETAIL_LOOKUP_SENT);
                break;
            case DETAIL_LOOKUP_INTERNAL:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, mContext), mRequest, mContext, DETAIL_LOOKUP_INTERNAL);
                break;
            case DETAIL_TASK:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, mContext), mRequest, mContext, DETAIL_TASK);
                break;
            case DETAIL_PROCESS:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_PROCESSING_WORKING, mContext), mRequest, mContext, DETAIL_PROCESS);
                break;
            case HANDLER_PROCESS:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, mContext), mRequest, mContext, HANDLER_PROCESS);
                break;
            case SEARCH_DOCUMENT_TYPE:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, mContext), mRequest, mContext, SEARCH_DOCUMENT_TYPE);
                break;
            case SEARCH_LIST_DEPARTMENT:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_PROCESSING_WORKING, mContext), mRequest, mContext, SEARCH_LIST_DEPARTMENT);
                break;
            case STATIS_DOC_COMING:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, mContext), mRequest, mContext, STATIS_DOC_COMING);
                break;
            case STATIS_DOC_SENT:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, mContext), mRequest, mContext, STATIS_DOC_SENT);
                break;
            case STA_COMING_DPM_LIST:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, mContext), mRequest, mContext, STA_COMING_DPM_LIST);
                break;
            case TASK_DETECT:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, mContext), mRequest, mContext, TASK_DETECT);
                break;
            case HOTLINE_OR:
                mInforRequest = getInforRequest(getModuleInfor(HOTLINE, mContext), mRequest, mContext, HOTLINE_OR);
                break;
            case SCHEDULE_HOME:
                mInforRequest = getInforRequest(getModuleInfor(SCHEDULE, mContext), mRequest, mContext, SCHEDULE_HOME);
                break;
            case STA_COMING_FULL_DPM:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, mContext), mRequest, mContext, STA_COMING_FULL_DPM);
                break;
            case STA_COMING_LIST_DPM:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, mContext), mRequest, mContext, STA_COMING_LIST_DPM);
                break;
            case STATIS_DPM:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, mContext), mRequest, mContext, STATIS_DPM);
                break;
            case STA_COMING_DPM_LIST_ARISE:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_WORK_ARISE, mContext), mRequest, mContext, STA_COMING_DPM_LIST_ARISE);
                break;
            case STA_COMING_FULL_DPM_ARISE:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_WORK_ARISE, mContext), mRequest, mContext, STA_COMING_FULL_DPM_ARISE);
                break;
            case STA_COMING_LIST_DPM_ARISE:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_WORK_ARISE, mContext), mRequest, mContext, STA_COMING_LIST_DPM_ARISE);
                break;
            case STA_COMING_PERSON_JOIN_ARISE:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_WORK_ARISE, mContext), mRequest, mContext, STA_COMING_PERSON_JOIN_ARISE);
                break;
            case STATIS_LIST:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, mContext), mRequest, mContext, STATIS_LIST);
                break;
            case STATIS_LIST_TOTAL:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, mContext), mRequest, mContext, STATIS_LIST_TOTAL);
                break;
            case SLIDER_MENU:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_PROCESSING_WORKING, mContext), mRequest, mContext, SLIDER_MENU);
                break;
            case LOOKUP_COMING:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, mContext), mRequest, mContext, LOOKUP_COMING);
                break;
            case LOOKUP_SENT:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, mContext), mRequest, mContext, LOOKUP_SENT);
                break;
            case LOOKUP_INTERNAL:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, mContext), mRequest, mContext, LOOKUP_INTERNAL);
                break;
            case LIST_DOCUMENT_DEPARTMENT:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, mContext), mRequest, mContext, LIST_DOCUMENT_DEPARTMENT);
                break;
            case STATIS_LIST_ROW:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, mContext), mRequest, mContext, STATIS_LIST_ROW);
                break;
            case TASK_REQUEST:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, mContext), mRequest, mContext, TASK_REQUEST);
                break;
            case HOTLINE_REQUEST:
                mInforRequest = getInforRequest(getModuleInfor(HOTLINE, mContext), mRequest, mContext, HOTLINE_REQUEST);
                break;
            case DOC_PROCESS_LIST:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_PROCESSING_WORKING, mContext), mRequest, mContext, DOC_PROCESS_LIST);
                break;
            case SEARCH_COMING:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, mContext), mRequest, mContext, SEARCH_COMING);
                break;
            case SEARCH_SENT:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, mContext), mRequest, mContext, SEARCH_SENT);
                break;
            case SEARCH_INTERNAL:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, mContext), mRequest, mContext, SEARCH_INTERNAL);
                break;
            case SEARCH_PROCESS:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_PROCESSING_WORKING, mContext), mRequest, mContext, SEARCH_PROCESS);
                break;
            case SEARCH_NUMBER_COMING:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, mContext), mRequest, mContext, SEARCH_NUMBER_COMING);
                break;
            case SEARCH_NUMBER_SENT:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, mContext), mRequest, mContext, SEARCH_NUMBER_SENT);
                break;
            case SEARCH_NUMBER_INTERNAL:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, mContext), mRequest, mContext, SEARCH_NUMBER_INTERNAL);
                break;
            case SEARCH_NUMBER_PROCESS:
                mInforRequest = getInforRequest(getModuleInfor(MODULE_PROCESSING_WORKING, mContext), mRequest, mContext, SEARCH_NUMBER_PROCESS);
                break;
            default:
                break;
        }
        CustomVolley mCustomVolley = new CustomVolley(mContext, mInforRequest, Request.Method.POST, mInforRequest.getUrl()
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (mInforRequest.getCaseRequest().equals(DOC_PROCESS_LIST))
                    insertDocList(response, mInforRequest);
                try {
                    GsonResuilt mGsonResuilt = getGson().fromJson(response, GsonResuilt.class);
                    mVolleyCallBack.onVolleyCompleted(response, mInforRequest.getCaseRequest());
                } catch (IllegalStateException | JsonSyntaxException exception) {
                    Log.d(TAG, "Module not found");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mVolleyCallBack.onVolleyError(error.toString());
            }
        }
        );
        mCustomVolley.setRetryPolicy(new DefaultRetryPolicy(
                20000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(mCustomVolley);
    }

    public void insertDocList(String Resuilt, InforRequest caseManagers) {
        GsonDocument mGsonDocument = getGson().fromJson(Resuilt, GsonDocument.class);
        List<GsonDocument.DataBean> mArray = mGsonDocument.getData();
        for (int i = 0; i < mArray.size(); i++) {
            GsonDocument.DataBean object = mArray.get(i);
            mSqLite.QueryData(CREATE_TABLE + " " + " M" + caseManagers.getOnlyNumerics(String.valueOf(object.getLabelCode())) + SQLLITE_DOCUMENT_LIST);
            if (!caseManagers.isDownloadOffLine()) {
                try {
                    mSqLite.QueryData("INSERT OR REPLACE INTO '" + "M" + caseManagers.getOnlyNumerics(String.valueOf(object.getLabelCode())) +
                            "'(summary," +
                            "jobType," +
                            "receiveDate," +
                            "jobId," +
                            "status," +
                            "viewed," +
                            "resourceCodeId," +
                            "overNetwork," +
                            "urgency," +
                            "title," +
                            "newX," +
                            "labelCode," +
                            "hasAttachFile, " +
                            "numberSymbol, " +
                            "receiveDateMilisec) " +
                            "VALUES('" +
                            caseManagers.specialValid(object.getSummary())
                            + "','" +
                            object.getJobType()
                            + "','" +
                            object.getReceiveDate()
                            + "', '" +
                            object.getJobId()
                            + "', '" +
                            object.getStatus()
                            + "', '" +
                            object.isViewed()
                            + "', '" +
                            object.getResourceCodeId()
                            + "', '" +
                            object.isOverNetwork()
                            + "','" +
                            object.getUrgency()
                            + "','" +
                            caseManagers.specialValid(object.getTitle())
                            + "','" +
                            object.isNewX()
                            + "','" +
                            object.getLabelCode()
                            + "','" +
                            object.isHasAttachFile()
                            + "','" +
                            caseManagers.specialValid(object.getNumberSymbol())
                            + "','" +
                            caseManagers.getMilisecDocument(object.getReceiveDate(), mContext)
                            + "')");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }

//    public void insertDocList(String Resuilt, InforRequest caseManagers) {
//        JSONObject mJsonObjectFullData = null;
//        try {
//            mJsonObjectFullData = new JSONObject(Resuilt);
//            JSONArray mJsonArray = mJsonObjectFullData.getJSONArray(DATA);
//            for (int i = 0; i < mJsonArray.length(); i++) {
//                JSONObject mJsonObjectResponse = mJsonArray.getJSONObject(i);
//                String mTitle = mJsonObjectResponse.getString(TITLE);
//                String mNgayNhan = mJsonObjectResponse.getString(RECEIVE_DATE);
//                String mSumary = mJsonObjectResponse.getString(SUMARY);
//                String DocumentID = mJsonObjectResponse.getString(JOB_ID);
//                String resourceCodeId = mJsonObjectResponse.getString(RESOURCECODEID);
//                boolean oldornewDocument = mJsonObjectResponse.getBoolean(NEW_OLD);
//                boolean seenDocument = mJsonObjectResponse.getBoolean(VIEWED);
//                int jobType = mJsonObjectResponse.getInt(JOB_TYPE);
//                String lableURL = mJsonObjectResponse.getString(LABLE_URL_PARAM);
//                boolean overNetwork = mJsonObjectResponse.getBoolean(OVER_NETWORK);
//                mSqLite.QueryData(CREATE_TABLE + " " + " M" + caseManagers.getOnlyNumerics(lableURL) + SQLLITE_DOCUMENT_LIST);
//                if (!caseManagers.isDownloadOffLine()) {
//                    mSqLite.QueryData("INSERT OR REPLACE INTO '" + "M" + caseManagers.getOnlyNumerics(lableURL) +
//                            "'(urlna,total,pagenumber," +
//                            "title,ngaynhan,sumary" +
//                            ",documentID,resourceCodeId,oldornewdocument," +
//                            "seendocument,jobtype," +
//                            "datemilisec,overnetwork) VALUES('" + lableURL + "','" + 20 + "','" + 1 + "', '" +
//                            caseManagers.specialValid(Html.fromHtml(mTitle).toString()) + "'," +
//                            " '" + mNgayNhan + "', '" + caseManagers.specialValid(Html.fromHtml(mSumary).toString()) + "'," +
//                            " '" + DocumentID + "', '" + resourceCodeId + "','" + oldornewDocument + "','" + seenDocument + "','" + jobType + "'," +
//                            "'" + caseManagers.getMilisecDocument(mNgayNhan, mContext) + "','" + overNetwork + "')");
//                }
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//    }

}
