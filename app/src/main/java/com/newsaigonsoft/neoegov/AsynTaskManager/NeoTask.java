package com.newsaigonsoft.neoegov.AsynTaskManager;

import android.content.Context;
import android.os.AsyncTask;
import android.text.Html;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.newsaigonsoft.neoegov.SQLite.SQLite;
import com.newsaigonsoft.neoegov.Subjects.ResuiltObject;

import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DATA;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.HOTLINE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.JOB_ID;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.JOB_TYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.LABLE_URL_PARAM;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MODULE_LOOKUP_DOCUMENT;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MODULE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.NEOTYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.NEW_OLD;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.OVER_NETWORK;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.PLATFORM;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MODULE_PROCESSING_WORKING;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.RECEIVE_DATE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.SCHEDULE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.SUMARY;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TITLE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TOKEN;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_SLIDER_MENU_LIST;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.VIEWED;
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
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.ANDROID;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.CREATE_TABLE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DETAIL_HOME_DOC_COMING;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DETAIL_HOME_DOC_SENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DETAIL_HOTLINE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DETAIL_LOOKUP_COMING;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DETAIL_LOOKUP_INTERNAL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DETAIL_LOOKUP_SENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DETAIL_PROCESS;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DETAIL_TASK;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DOC_PROCESS_LIST;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.GET_COUNT_DATE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.GET_EXPIRATION_DATE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.HANDLER_PROCESS;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.HOTLINE_OR;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.HOTLINE_REQUEST;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LINK_PLATOM;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LINK_SEND_TOKEN;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LIST_CANEL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LIST_DEPARTMENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LIST_DOCUMENT_DEPARTMENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LIST_DOCUMENT_SQLITE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LIST_PERSON;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LIST_RETURN_PERSON;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LIST_RETURN_SAVED;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LOGIN;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LOOKUP_COMING;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LOOKUP_INTERNAL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LOOKUP_SENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.RESOURCECODEID;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SCHEDULE_HOME;
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
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SEND_TOKEN;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SLIDER_MENU;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SQLLITE_DOCUMENT_LIST;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.STATIS_DOC_COMING;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.STATIS_DOC_SENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.STATIS_DPM;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.STATIS_LIST;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.STATIS_LIST_ROW;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.STATIS_LIST_TOTAL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.STA_COMING_FULL_DPM;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.STA_COMING_LIST_DPM;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TASK_DETECT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TASK_REQUEST;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TOKEN_ID;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TREE_HOME_STATISTICAL_COMING;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TREE_HOME_STATISTICAL_SEND;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TREE_HOTLINE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TREE_LOOKUP_COMING;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TREE_LOOKUP_INTERNAL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TREE_LOOKUP_SEND;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TREE_PROCESS;

/**
 * Created by Vinh on 12/12/2017.
 */

public class NeoTask extends AsyncTask<CaseManager, Integer, ResuiltObject> {
    //    SumManager manager = new SumManager();
    Context context;
    AsyncTaskCompleteListener<ResuiltObject> AsynCallBack;
    SQLite mSqLite;

    public NeoTask(Context context, AsyncTaskCompleteListener<ResuiltObject> asynCallBack) {
        this.context = context;
        AsynCallBack = asynCallBack;
        mSqLite = new SQLite(context, LIST_DOCUMENT_SQLITE, null, 1);
    }


    @Override
    protected ResuiltObject doInBackground(CaseManager... caseManagers) {
        String mResuilt = null;
        String mRequest = caseManagers[0].getRequest();
        ResuiltObject mResuiltObject = null;
        String DoingTask = LOGIN;
        JSONObject mJsonObject = new JSONObject();
        try {
            switch (caseManagers[0].getCase()) {
                case LOGIN:
                    mJsonObject.put(TOKEN, caseManagers[0].getDefaults(TOKEN_ID, context));
                    mJsonObject.put(PLATFORM, ANDROID);
                    mResuilt = caseManagers[0].makePostRequestLogin(caseManagers[0].getUrl(), mJsonObject.toString(), caseManagers[0].getUserid(), caseManagers[0].getPass(), caseManagers[0].getDefaults(TOKEN_ID, context), ANDROID);
                    mResuiltObject = new ResuiltObject(LOGIN, mResuilt);
                    break;
                case SEND_TOKEN:
                    String TokenID = caseManagers[0].getDefaults(TOKEN_ID, context);
                    caseManagers[0].makePostRequestTokenID(LINK_SEND_TOKEN, TokenID, LINK_PLATOM, context);
                    break;
                case SLIDER_MENU:
                    mJsonObject.put(MODULE, MODULE_PROCESSING_WORKING);
                    mJsonObject.put(NEOTYPE, TYPE_SLIDER_MENU_LIST);
                    mResuilt = caseManagers[0].eventPostRequest(caseManagers[0].getModuleInfor(MODULE_PROCESSING_WORKING, context), mJsonObject.toString(), caseManagers[0].getUserid(), caseManagers[0].getPass());
                    mResuiltObject = new ResuiltObject(SLIDER_MENU, mResuilt);
                    break;
                case STATIS_DOC_COMING:
                    mResuilt = caseManagers[0].eventPostRequest(caseManagers[0].getModuleInfor(MODULE_LOOKUP_DOCUMENT, context), caseManagers[0].getRequest(), caseManagers[0].getUserid(), caseManagers[0].getPass());
                    mResuiltObject = new ResuiltObject(STATIS_DOC_COMING, mResuilt);
                    break;
                case STATIS_DOC_SENT:
                    mResuilt = caseManagers[0].eventPostRequest(caseManagers[0].getModuleInfor(MODULE_LOOKUP_DOCUMENT, context), caseManagers[0].getRequest(), caseManagers[0].getUserid(), caseManagers[0].getPass());
                    mResuiltObject = new ResuiltObject(STATIS_DOC_SENT, mResuilt);
                    break;
                case TASK_DETECT:
                    mResuilt = caseManagers[0].eventPostRequest(caseManagers[0].getModuleInfor(MODULE_LOOKUP_DOCUMENT, context), caseManagers[0].getRequest(), caseManagers[0].getUserid(), caseManagers[0].getPass());
                    mResuiltObject = new ResuiltObject(TASK_DETECT, mResuilt);
                    break;
                case HOTLINE_OR:
                    mResuilt = caseManagers[0].eventPostRequest(caseManagers[0].getModuleInfor(HOTLINE, context), caseManagers[0].getRequest(), caseManagers[0].getUserid(), caseManagers[0].getPass());
                    mResuiltObject = new ResuiltObject(HOTLINE_OR, mResuilt);
                    break;
                case SCHEDULE_HOME:
                    mResuilt = caseManagers[0].eventPostRequest(caseManagers[0].getModuleInfor(SCHEDULE, context), caseManagers[0].getRequest(), caseManagers[0].getUserid(), caseManagers[0].getPass());
                    mResuiltObject = new ResuiltObject(SCHEDULE_HOME, mResuilt);
                    break;
                case STA_COMING_FULL_DPM:
                    mResuilt = caseManagers[0].eventPostRequest(caseManagers[0].getModuleInfor(MODULE_LOOKUP_DOCUMENT, context), caseManagers[0].getRequest(), caseManagers[0].getUserid(), caseManagers[0].getPass());
                    mResuiltObject = new ResuiltObject(STA_COMING_FULL_DPM, mResuilt);
                    break;
                case STA_COMING_LIST_DPM:
                    mResuilt = caseManagers[0].eventPostRequest(caseManagers[0].getModuleInfor(MODULE_LOOKUP_DOCUMENT, context), caseManagers[0].getRequest(), caseManagers[0].getUserid(), caseManagers[0].getPass());
                    mResuiltObject = new ResuiltObject(STA_COMING_LIST_DPM, mResuilt);
                    break;
                case DOC_PROCESS_LIST:
                    mResuilt = caseManagers[0].eventPostRequest(caseManagers[0].getModuleInfor(MODULE_PROCESSING_WORKING, context), caseManagers[0].getRequest(), caseManagers[0].getUserid(), caseManagers[0].getPass());
                    mResuiltObject = new ResuiltObject(DOC_PROCESS_LIST, mResuilt);
                    insertDocList(mResuilt, caseManagers[0]);
                    break;
                case LOOKUP_COMING:
                    mResuilt = caseManagers[0].eventPostRequest(caseManagers[0].getModuleInfor(MODULE_LOOKUP_DOCUMENT, context), caseManagers[0].getRequest(), caseManagers[0].getUserid(), caseManagers[0].getPass());
                    mResuiltObject = new ResuiltObject(LOOKUP_COMING, mResuilt);
                    break;
                case LOOKUP_SENT:
                    mResuilt = caseManagers[0].eventPostRequest(caseManagers[0].getModuleInfor(MODULE_LOOKUP_DOCUMENT, context), caseManagers[0].getRequest(), caseManagers[0].getUserid(), caseManagers[0].getPass());
                    mResuiltObject = new ResuiltObject(LOOKUP_SENT, mResuilt);
                    break;
                case LOOKUP_INTERNAL:
                    mResuilt = caseManagers[0].eventPostRequest(caseManagers[0].getModuleInfor(MODULE_LOOKUP_DOCUMENT, context), caseManagers[0].getRequest(), caseManagers[0].getUserid(), caseManagers[0].getPass());
                    mResuiltObject = new ResuiltObject(LOOKUP_INTERNAL, mResuilt);
                    break;
                case LIST_DOCUMENT_DEPARTMENT:
                    mResuilt = caseManagers[0].eventPostRequest(caseManagers[0].getModuleInfor(MODULE_LOOKUP_DOCUMENT, context), caseManagers[0].getRequest(), caseManagers[0].getUserid(), caseManagers[0].getPass());
                    mResuiltObject = new ResuiltObject(LIST_DOCUMENT_DEPARTMENT, mResuilt);
                    break;
                case TASK_REQUEST:
                    mResuilt = caseManagers[0].eventPostRequest(caseManagers[0].getModuleInfor(MODULE_LOOKUP_DOCUMENT, context), caseManagers[0].getRequest(), caseManagers[0].getUserid(), caseManagers[0].getPass());
                    mResuiltObject = new ResuiltObject(TASK_REQUEST, mResuilt);
                    break;
                case HOTLINE_REQUEST:
                    mResuilt = caseManagers[0].eventPostRequest(caseManagers[0].getModuleInfor(HOTLINE, context), caseManagers[0].getRequest(), caseManagers[0].getUserid(), caseManagers[0].getPass());
                    mResuiltObject = new ResuiltObject(HOTLINE_REQUEST, mResuilt);
                    break;
                case SEARCH_COMING:
                    mResuilt = caseManagers[0].eventPostRequest(caseManagers[0].getModuleInfor(MODULE_LOOKUP_DOCUMENT, context), caseManagers[0].getRequest(), caseManagers[0].getUserid(), caseManagers[0].getPass());
                    mResuiltObject = new ResuiltObject(SEARCH_COMING, mResuilt);
                    break;
                case SEARCH_SENT:
                    mResuilt = caseManagers[0].eventPostRequest(caseManagers[0].getModuleInfor(MODULE_LOOKUP_DOCUMENT, context), caseManagers[0].getRequest(), caseManagers[0].getUserid(), caseManagers[0].getPass());
                    mResuiltObject = new ResuiltObject(SEARCH_SENT, mResuilt);
                    break;
                case SEARCH_INTERNAL:
                    mResuilt = caseManagers[0].eventPostRequest(caseManagers[0].getModuleInfor(MODULE_LOOKUP_DOCUMENT, context), caseManagers[0].getRequest(), caseManagers[0].getUserid(), caseManagers[0].getPass());
                    mResuiltObject = new ResuiltObject(SEARCH_INTERNAL, mResuilt);
                    break;
                case SEARCH_PROCESS:
                    mResuilt = caseManagers[0].eventPostRequest(caseManagers[0].getModuleInfor(MODULE_PROCESSING_WORKING, context), caseManagers[0].getRequest(), caseManagers[0].getUserid(), caseManagers[0].getPass());
                    mResuiltObject = new ResuiltObject(SEARCH_PROCESS, mResuilt);
                    break;
                case SEARCH_NUMBER_COMING:
                    mResuilt = caseManagers[0].eventPostRequest(caseManagers[0].getModuleInfor(MODULE_LOOKUP_DOCUMENT, context), caseManagers[0].getRequest(), caseManagers[0].getUserid(), caseManagers[0].getPass());
                    mResuiltObject = new ResuiltObject(SEARCH_NUMBER_COMING, mResuilt);
                    break;
                case SEARCH_NUMBER_SENT:
                    mResuilt = caseManagers[0].eventPostRequest(caseManagers[0].getModuleInfor(MODULE_LOOKUP_DOCUMENT, context), caseManagers[0].getRequest(), caseManagers[0].getUserid(), caseManagers[0].getPass());
                    mResuiltObject = new ResuiltObject(SEARCH_NUMBER_SENT, mResuilt);
                    break;
                case SEARCH_NUMBER_INTERNAL:
                    mResuilt = caseManagers[0].eventPostRequest(caseManagers[0].getModuleInfor(MODULE_LOOKUP_DOCUMENT, context), caseManagers[0].getRequest(), caseManagers[0].getUserid(), caseManagers[0].getPass());
                    mResuiltObject = new ResuiltObject(SEARCH_NUMBER_INTERNAL, mResuilt);
                    break;
                case SEARCH_NUMBER_PROCESS:
                    mResuilt = caseManagers[0].eventPostRequest(caseManagers[0].getModuleInfor(MODULE_PROCESSING_WORKING, context), caseManagers[0].getRequest(), caseManagers[0].getUserid(), caseManagers[0].getPass());
                    mResuiltObject = new ResuiltObject(SEARCH_NUMBER_PROCESS, mResuilt);
                    break;
                case SEARCH_LIST_DEPARTMENT:
                    mResuilt = caseManagers[0].eventPostRequest(caseManagers[0].getModuleInfor(MODULE_PROCESSING_WORKING, context), caseManagers[0].getRequest(), caseManagers[0].getUserid(), caseManagers[0].getPass());
                    mResuiltObject = new ResuiltObject(SEARCH_LIST_DEPARTMENT, mResuilt);
                    break;
                case SEARCH_DOCUMENT_TYPE:
                    mResuilt = caseManagers[0].eventPostRequest(caseManagers[0].getModuleInfor(MODULE_LOOKUP_DOCUMENT, context), caseManagers[0].getRequest(), caseManagers[0].getUserid(), caseManagers[0].getPass());
                    mResuiltObject = new ResuiltObject(SEARCH_DOCUMENT_TYPE, mResuilt);
                    break;
                case DETAIL_HOTLINE:
                    mResuilt = caseManagers[0].eventPostRequest(caseManagers[0].getModuleInfor(HOTLINE, context), caseManagers[0].getRequest(), caseManagers[0].getUserid(), caseManagers[0].getPass());
                    mResuiltObject = new ResuiltObject(DETAIL_HOTLINE, mResuilt);
                    break;
                case DETAIL_HOME_DOC_SENT:
                    mResuilt = caseManagers[0].eventPostRequest(caseManagers[0].getModuleInfor(MODULE_LOOKUP_DOCUMENT, context), caseManagers[0].getRequest(), caseManagers[0].getUserid(), caseManagers[0].getPass());
                    mResuiltObject = new ResuiltObject(DETAIL_HOME_DOC_SENT, mResuilt);
                    break;
                case DETAIL_HOME_DOC_COMING:
                    mResuilt = caseManagers[0].eventPostRequest(caseManagers[0].getModuleInfor(MODULE_LOOKUP_DOCUMENT, context), caseManagers[0].getRequest(), caseManagers[0].getUserid(), caseManagers[0].getPass());
                    mResuiltObject = new ResuiltObject(DETAIL_HOME_DOC_COMING, mResuilt);
                    break;
                case DETAIL_LOOKUP_COMING:
                    mResuilt = caseManagers[0].eventPostRequest(caseManagers[0].getModuleInfor(MODULE_LOOKUP_DOCUMENT, context), caseManagers[0].getRequest(), caseManagers[0].getUserid(), caseManagers[0].getPass());
                    mResuiltObject = new ResuiltObject(DETAIL_LOOKUP_COMING, mResuilt);
                    break;
                case DETAIL_LOOKUP_SENT:
                    mResuilt = caseManagers[0].eventPostRequest(caseManagers[0].getModuleInfor(MODULE_LOOKUP_DOCUMENT, context), caseManagers[0].getRequest(), caseManagers[0].getUserid(), caseManagers[0].getPass());
                    mResuiltObject = new ResuiltObject(DETAIL_LOOKUP_SENT, mResuilt);
                    break;
                case DETAIL_LOOKUP_INTERNAL:
                    mResuilt = caseManagers[0].eventPostRequest(caseManagers[0].getModuleInfor(MODULE_LOOKUP_DOCUMENT, context), caseManagers[0].getRequest(), caseManagers[0].getUserid(), caseManagers[0].getPass());
                    mResuiltObject = new ResuiltObject(DETAIL_LOOKUP_INTERNAL, mResuilt);
                    break;
                case DETAIL_TASK:
                    mResuilt = caseManagers[0].eventPostRequest(caseManagers[0].getModuleInfor(MODULE_LOOKUP_DOCUMENT, context), caseManagers[0].getRequest(), caseManagers[0].getUserid(), caseManagers[0].getPass());
                    mResuiltObject = new ResuiltObject(DETAIL_TASK, mResuilt);
                    break;
                case DETAIL_PROCESS:
                    mResuilt = caseManagers[0].eventPostRequest(caseManagers[0].getModuleInfor(MODULE_PROCESSING_WORKING, context), caseManagers[0].getRequest(), caseManagers[0].getUserid(), caseManagers[0].getPass());
                    mResuiltObject = new ResuiltObject(DETAIL_PROCESS, mResuilt);
                    break;
                case HANDLER_PROCESS:
                    mResuilt = caseManagers[0].eventPostRequest(caseManagers[0].getModuleInfor(MODULE_LOOKUP_DOCUMENT, context), caseManagers[0].getRequest(), caseManagers[0].getUserid(), caseManagers[0].getPass());
                    mResuiltObject = new ResuiltObject(HANDLER_PROCESS, mResuilt);
                    break;
                case TREE_HOTLINE:
                    mResuilt = caseManagers[0].eventPostRequest(caseManagers[0].getModuleInfor(HOTLINE, context), caseManagers[0].getRequest(), caseManagers[0].getUserid(), caseManagers[0].getPass());
                    mResuiltObject = new ResuiltObject(TREE_HOTLINE, mResuilt);
                    break;
                case TREE_HOME_STATISTICAL_COMING:
                    mResuilt = caseManagers[0].eventPostRequest(caseManagers[0].getModuleInfor(MODULE_LOOKUP_DOCUMENT, context), caseManagers[0].getRequest(), caseManagers[0].getUserid(), caseManagers[0].getPass());
                    mResuiltObject = new ResuiltObject(TREE_HOME_STATISTICAL_COMING, mResuilt);
                    break;
                case TREE_HOME_STATISTICAL_SEND:
                    mResuilt = caseManagers[0].eventPostRequest(caseManagers[0].getModuleInfor(MODULE_LOOKUP_DOCUMENT, context), caseManagers[0].getRequest(), caseManagers[0].getUserid(), caseManagers[0].getPass());
                    mResuiltObject = new ResuiltObject(TREE_HOME_STATISTICAL_SEND, mResuilt);
                    break;
                case TREE_LOOKUP_COMING:
                    mResuilt = caseManagers[0].eventPostRequest(caseManagers[0].getModuleInfor(MODULE_LOOKUP_DOCUMENT, context), caseManagers[0].getRequest(), caseManagers[0].getUserid(), caseManagers[0].getPass());
                    mResuiltObject = new ResuiltObject(TREE_LOOKUP_COMING, mResuilt);
                    break;
                case TREE_LOOKUP_SEND:
                    mResuilt = caseManagers[0].eventPostRequest(caseManagers[0].getModuleInfor(MODULE_LOOKUP_DOCUMENT, context), caseManagers[0].getRequest(), caseManagers[0].getUserid(), caseManagers[0].getPass());
                    mResuiltObject = new ResuiltObject(TREE_LOOKUP_SEND, mResuilt);
                    break;
                case TREE_LOOKUP_INTERNAL:
                    mResuilt = caseManagers[0].eventPostRequest(caseManagers[0].getModuleInfor(MODULE_LOOKUP_DOCUMENT, context), caseManagers[0].getRequest(), caseManagers[0].getUserid(), caseManagers[0].getPass());
                    mResuiltObject = new ResuiltObject(TREE_LOOKUP_INTERNAL, mResuilt);
                    break;
                case TREE_PROCESS:
                    mResuilt = caseManagers[0].eventPostRequest(caseManagers[0].getModuleInfor(MODULE_PROCESSING_WORKING, context), caseManagers[0].getRequest(), caseManagers[0].getUserid(), caseManagers[0].getPass());
                    mResuiltObject = new ResuiltObject(TREE_PROCESS, mResuilt);
                    break;
                case ACTION_ADD_TRAINFER:
                    mResuilt = caseManagers[0].eventPostRequest(caseManagers[0].getModuleInfor(MODULE_LOOKUP_DOCUMENT, context), caseManagers[0].getRequest(), caseManagers[0].getUserid(), caseManagers[0].getPass());
                    mResuiltObject = new ResuiltObject(ACTION_ADD_TRAINFER, mResuilt);
                    break;
                case ACTION_CANCEL:
                    String request = caseManagers[0].getRequest();
                    mResuilt = caseManagers[0].eventPostRequest(caseManagers[0].getModuleInfor(MODULE_PROCESSING_WORKING, context), caseManagers[0].getRequest(), caseManagers[0].getUserid(), caseManagers[0].getPass());
                    mResuiltObject = new ResuiltObject(ACTION_CANCEL, mResuilt);
                    break;
                case ACTION_CHANGE_HANDLE:
                    mResuilt = caseManagers[0].eventPostRequest(caseManagers[0].getModuleInfor(MODULE_LOOKUP_DOCUMENT, context), caseManagers[0].getRequest(), caseManagers[0].getUserid(), caseManagers[0].getPass());
                    mResuiltObject = new ResuiltObject(ACTION_CHANGE_HANDLE, mResuilt);
                    break;
                case ACTION_CONFIRM_COMPLETED:
                    mResuilt = caseManagers[0].eventPostRequest(caseManagers[0].getModuleInfor(MODULE_LOOKUP_DOCUMENT, context), caseManagers[0].getRequest(), caseManagers[0].getUserid(), caseManagers[0].getPass());
                    mResuiltObject = new ResuiltObject(ACTION_CONFIRM_COMPLETED, mResuilt);
                    break;
                case ACTION_EXTEND:
                    mResuilt = caseManagers[0].eventPostRequest(caseManagers[0].getModuleInfor(MODULE_PROCESSING_WORKING, context), caseManagers[0].getRequest(), caseManagers[0].getUserid(), caseManagers[0].getPass());
                    mResuiltObject = new ResuiltObject(ACTION_EXTEND, mResuilt);
                    break;
                case ACTION_FEED_BACK:
                    mResuilt = caseManagers[0].eventPostRequest(caseManagers[0].getModuleInfor(MODULE_PROCESSING_WORKING, context), caseManagers[0].getRequest(), caseManagers[0].getUserid(), caseManagers[0].getPass());
                    mResuiltObject = new ResuiltObject(ACTION_FEED_BACK, mResuilt);
                    break;
                case ACTION_FORWARD_DEPARTMENT:
                    mResuilt = caseManagers[0].eventPostRequest(caseManagers[0].getModuleInfor(MODULE_PROCESSING_WORKING, context), caseManagers[0].getRequest(), caseManagers[0].getUserid(), caseManagers[0].getPass());
                    mResuiltObject = new ResuiltObject(ACTION_FORWARD_DEPARTMENT, mResuilt);
                    break;
                case ACTION_FORWARD_PERSON:
                    mResuilt = caseManagers[0].eventPostRequest(caseManagers[0].getModuleInfor(MODULE_PROCESSING_WORKING, context), caseManagers[0].getRequest(), caseManagers[0].getUserid(), caseManagers[0].getPass());
                    mResuiltObject = new ResuiltObject(ACTION_FORWARD_PERSON, mResuilt);
                    break;
                case ACTION_REMIND:
                    mResuilt = caseManagers[0].eventPostRequest(caseManagers[0].getModuleInfor(MODULE_LOOKUP_DOCUMENT, context), caseManagers[0].getRequest(), caseManagers[0].getUserid(), caseManagers[0].getPass());
                    mResuiltObject = new ResuiltObject(ACTION_REMIND, mResuilt);
                    break;
                case ACTION_RETURN:
                    mResuilt = caseManagers[0].eventPostRequest(caseManagers[0].getModuleInfor(MODULE_PROCESSING_WORKING, context), caseManagers[0].getRequest(), caseManagers[0].getUserid(), caseManagers[0].getPass());
                    mResuiltObject = new ResuiltObject(ACTION_RETURN, mResuilt);
                    break;
                case LIST_CANEL:
                    mResuilt = caseManagers[0].eventPostRequest(caseManagers[0].getModuleInfor(MODULE_PROCESSING_WORKING, context), caseManagers[0].getRequest(), caseManagers[0].getUserid(), caseManagers[0].getPass());
                    mResuiltObject = new ResuiltObject(LIST_CANEL, mResuilt);
                    break;
                case GET_EXPIRATION_DATE:
                    mResuilt = caseManagers[0].eventPostRequest(caseManagers[0].getModuleInfor(MODULE_PROCESSING_WORKING, context), caseManagers[0].getRequest(), caseManagers[0].getUserid(), caseManagers[0].getPass());
                    mResuiltObject = new ResuiltObject(GET_EXPIRATION_DATE, mResuilt);
                    break;
                case GET_COUNT_DATE:
                    mResuilt = caseManagers[0].eventPostRequest(caseManagers[0].getModuleInfor(MODULE_PROCESSING_WORKING, context), caseManagers[0].getRequest(), caseManagers[0].getUserid(), caseManagers[0].getPass());
                    mResuiltObject = new ResuiltObject(GET_COUNT_DATE, mResuilt);
                    break;
                case LIST_RETURN_PERSON:
                    mResuilt = caseManagers[0].eventPostRequest(caseManagers[0].getModuleInfor(MODULE_PROCESSING_WORKING, context), caseManagers[0].getRequest(), caseManagers[0].getUserid(), caseManagers[0].getPass());
                    mResuiltObject = new ResuiltObject(LIST_RETURN_PERSON, mResuilt);
                    break;
                case LIST_PERSON:
                    mResuilt = caseManagers[0].eventPostRequest(caseManagers[0].getModuleInfor(MODULE_PROCESSING_WORKING, context), caseManagers[0].getRequest(), caseManagers[0].getUserid(), caseManagers[0].getPass());
                    mResuiltObject = new ResuiltObject(LIST_PERSON, mResuilt);
                    break;
                case LIST_RETURN_SAVED:
                    mResuilt = caseManagers[0].getRequest();
                    mResuiltObject = new ResuiltObject(LIST_RETURN_SAVED, mResuilt);
                    break;
                case LIST_DEPARTMENT:
                    mResuilt = caseManagers[0].eventPostRequest(caseManagers[0].getModuleInfor(MODULE_PROCESSING_WORKING, context), caseManagers[0].getRequest(), caseManagers[0].getUserid(), caseManagers[0].getPass());
                    mResuiltObject = new ResuiltObject(LIST_DEPARTMENT, mResuilt);
                    break;
                case ACTION_SAVE_DRAIF:
                    mResuilt = caseManagers[0].eventPostRequest(caseManagers[0].getModuleInfor(MODULE_PROCESSING_WORKING, context), caseManagers[0].getRequest(), caseManagers[0].getUserid(), caseManagers[0].getPass());
                    mResuiltObject = new ResuiltObject(ACTION_SAVE_DRAIF, mResuilt);
                    break;
                case STATIS_DPM:
                    mResuilt = caseManagers[0].eventPostRequest(caseManagers[0].getModuleInfor(MODULE_LOOKUP_DOCUMENT, context), caseManagers[0].getRequest(), caseManagers[0].getUserid(), caseManagers[0].getPass());
                    mResuiltObject = new ResuiltObject(STATIS_DPM, mResuilt);
                    break;
                case STATIS_LIST_TOTAL:
                    mResuilt = caseManagers[0].eventPostRequest(caseManagers[0].getModuleInfor(MODULE_LOOKUP_DOCUMENT, context), caseManagers[0].getRequest(), caseManagers[0].getUserid(), caseManagers[0].getPass());
                    mResuiltObject = new ResuiltObject(STATIS_LIST_TOTAL, mResuilt);
                    break;
                case STATIS_LIST:
                    mResuilt = caseManagers[0].eventPostRequest(caseManagers[0].getModuleInfor(MODULE_LOOKUP_DOCUMENT, context), caseManagers[0].getRequest(), caseManagers[0].getUserid(), caseManagers[0].getPass());
                    mResuiltObject = new ResuiltObject(STATIS_LIST, mResuilt);
                    break;
                case STATIS_LIST_ROW:
                    mResuilt = caseManagers[0].eventPostRequest(caseManagers[0].getModuleInfor(MODULE_LOOKUP_DOCUMENT, context), caseManagers[0].getRequest(), caseManagers[0].getUserid(), caseManagers[0].getPass());
                    mResuiltObject = new ResuiltObject(STATIS_LIST_ROW, mResuilt);
                    break;
            }
            if (mResuiltObject != null) {
                DoingTask = mResuiltObject.getCase();
            }
            Log.d("Request_Resuilt_Case: ", mResuilt + " " + mRequest + " " + DoingTask);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mResuiltObject;
    }

    @Override
    protected void onPostExecute(ResuiltObject resuiltObject) {
        if (resuiltObject != null) {
            AsynCallBack.onTaskCompleted(resuiltObject.getResuilt(), resuiltObject.getCase());
        }
        super.onPostExecute(resuiltObject);
    }

    public void insertDocList(String Resuilt, CaseManager caseManagers) {
        JSONObject mJsonObjectFullData = null;
        try {
            mJsonObjectFullData = new JSONObject(Resuilt);
            JSONArray mJsonArray = mJsonObjectFullData.getJSONArray(DATA);
            for (int i = 0; i < mJsonArray.length(); i++) {
                JSONObject mJsonObjectResponse = mJsonArray.getJSONObject(i);
                String mTitle = mJsonObjectResponse.getString(TITLE);
                String mNgayNhan = mJsonObjectResponse.getString(RECEIVE_DATE);
                String mSumary = mJsonObjectResponse.getString(SUMARY);
                String DocumentID = mJsonObjectResponse.getString(JOB_ID);
                String resourceCodeId = mJsonObjectResponse.getString(RESOURCECODEID);
                boolean oldornewDocument = mJsonObjectResponse.getBoolean(NEW_OLD);
                boolean seenDocument = mJsonObjectResponse.getBoolean(VIEWED);
                int jobType = mJsonObjectResponse.getInt(JOB_TYPE);
                String lableURL = mJsonObjectResponse.getString(LABLE_URL_PARAM);
                boolean overNetwork = mJsonObjectResponse.getBoolean(OVER_NETWORK);
                mSqLite.QueryData(CREATE_TABLE + " " + " M" + caseManagers.getOnlyNumerics(lableURL) + SQLLITE_DOCUMENT_LIST);
                if (!caseManagers.isDownloadOffLine()) {
                    mSqLite.QueryData("INSERT OR REPLACE INTO '" + "M" + caseManagers.getOnlyNumerics(lableURL) + "'(urlna,total,pagenumber,title,ngaynhan,sumary,documentID,resourceCodeId,oldornewdocument,seendocument,jobtype,datemilisec,overnetwork) VALUES('" + lableURL + "','" + 20 + "','" + 1 + "', '" +
                            caseManagers.specialValid(Html.fromHtml(mTitle).toString()) + "', '" + mNgayNhan + "', '" + caseManagers.specialValid(Html.fromHtml(mSumary).toString()) + "', '" + DocumentID + "', '" + resourceCodeId + "','" + oldornewDocument + "','" + seenDocument + "','" + jobType + "','" + caseManagers.getMilisecDocument(mNgayNhan, context) + "','" + overNetwork + "')");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
