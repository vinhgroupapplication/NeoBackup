package com.newsaigonsoft.neoegov.OfficalActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.text.Html;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.newsaigonsoft.neoegov.AVolleyManager.VolleyTask;
import com.newsaigonsoft.neoegov.AVolleyManager.VolleyTaskCompletedListenner;
import com.newsaigonsoft.neoegov.Adapter.DocumentAdapter;
import com.newsaigonsoft.neoegov.Adapter.DocumentLookupAdapter;
import com.newsaigonsoft.neoegov.Adapter.HotLineAdapter;
import com.newsaigonsoft.neoegov.Adapter.SlideMenuAdapter;
import com.newsaigonsoft.neoegov.AsynTaskManager.AsyncTaskCompleteListener;
import com.newsaigonsoft.neoegov.GsonObject.GsonDocument;
import com.newsaigonsoft.neoegov.GsonObject.GsonSlider;
import com.newsaigonsoft.neoegov.MyInterface.MyInterface;
import com.newsaigonsoft.neoegov.OfficalActivity.TreeViewMenu.TreeMenu;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.ChildMenu;
import com.newsaigonsoft.neoegov.Subjects.HeaderMenu;
import com.newsaigonsoft.neoegov.Subjects.HotLineListRow;
import com.newsaigonsoft.neoegov.Subjects.ItemsDocumentLookUp;
import com.newsaigonsoft.neoegov.Subjects.KeyManager;
import com.newsaigonsoft.neoegov.Subjects.ResuiltObject;
import com.newsaigonsoft.neoegov.Subjects.SearchRow;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.ADVANCED;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.BRIEF_CONTENT;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.CATEGORY;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.CODE_STATISTICAL_DEPARTMENT;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.COUNT;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.CREATE_DATE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.CREATE_DATE_DOC;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DATA;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DIRECTOR;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DOC_DEMURRAGE_TYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DOC_LOCAL_ID;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DOC_NOT_PROCESS_TYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DOC_NOT_SEEN_TYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DOC_NUMBER;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DOC_NUMBER_FULL;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DOC_RECEIPT_ID;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DOC_SEND_ID;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DOC_TYPE_ID;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.EXECUTION_UNIT;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.FROM_CREATE_DATE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.FROM_DATE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.FROM_DUE_DATE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.FROM_PROCESS_DATE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.FROM_PUBLISH_DATE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.FROM_RECEIVE_DATE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.FROM_SIGN_DATE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.HOTLINE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.JOB_ID;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.JOB_TYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.KEYWORDS;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.LABLE_URL_PARAM;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MODULE_LOOKUP_DOCUMENT;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MESSAGE_REFLECT_ID;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MODULE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.NEOTYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.NEW_OLD;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.NOTIFY_OBJECT_ID;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.NUMBER_OF_SYMBOL;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.NUM_PAGE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.ORGANIZATION_EDITOR_ID;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.ORGANIZATION_ID;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.ORG_CREATE_NAME;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.PEOPLE_REFLECT;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.PHONE_REFLECT;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MODULE_PROCESSING_WORKING;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.PROCESSED_ID;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.PROCESS_DEMURRAGE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.PROCESS_ON_TIME;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.PUBLISH_DATE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.PUBLISH_NUMBER;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.RECEIVE_DATE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.ROW_PER_PAGE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.SCHEDULE_CONTENT;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.SEARCH_CRITERIA;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.STATISTIC_CODE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.SUMARY;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TASK_CONTENT;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TASK_ID;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TASK_PROCESS;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TASK_PROCESS_NEAR_DEMURRAGE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TASK_REPORTED;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TITLE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TO_CREATE_DATE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TO_DATE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TO_DUE_DATE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TO_PROCESS_DATE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TO_PUBLISH_DATE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TO_RECEIVE_DATE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TO_SIGN_DATE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_DOCUMENT_LIST;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_HOME_LIST_DOCUMENT_COMING;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_HOME_LIST_DOCUMENT_SENT;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_HOTLIST;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_LOOKUP_DOCUMENT_LIST_COMING;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_LOOKUP_DOCUMENT_LIST_INTERNAL;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_LOOKUP_DOCUMENT_LIST_SENT;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_LOOKUP_TOTAL_COMING;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_LOOKUP_TOTAL_INTERNAL;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_LOOKUP_TOTAL_SENT;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_SLIDER_MENU_LIST;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_STATIS_LIST;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_STA_LIST_WORK_ARISE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_TASK_LIST;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_TOTAL_DOCUMENT_LIST;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_WORK_ARISE_LIST;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.VIEWED;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MODULE_WORK_ARISE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.WORK_ARISE_ID;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.WORK_CODE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.CANCEL_DOWNLOAD_OFFLINE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.CODE_HOME;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.CODE_LOOKUP;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.CODE_LOOKUP_COMING;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.CODE_LOOKUP_LOCAL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.CODE_LOOKUP_ONE_WINDOW;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.CODE_LOOKUP_SEND;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.CODE_PROCESS;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.CODE_SCHEDULE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.CODE_SCHEDULE_DAY;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.CODE_SCHEDULE_WEEK;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.CODE_STATISTICAL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.CODE_STATISTICAL_COMING;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.CODE_STATISTICAL_FOLLOW_DEPEARTMENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.CODE_STATISTICAL_FOLLOW_PERSON;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.CODE_STATISTICAL_LOCAL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.CODE_STATISTICAL_ONE_WINDOW;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.CODE_STATISTICAL_SEND;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.CREATE_TABLE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DATE_1_YEARS_FOR_TEST;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DIALOG_CENTER;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DOCUMENT_FRAGMENT_TAG;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DOC_PROCESS_LIST;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.FORWARD;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.GET_NAME_FROM_DOCUMENT_LIST_SQLITE_DATABASE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.HOME_SCREEN;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.HOTLINE_REQUEST;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.HOT_PROCESS;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.HOT_PROCESS_DEMURRAGE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.HOT_PROCESS_ONDUE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LIST_DOCUMENT_DEPARTMENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LOOKUP_COMING;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LOOKUP_INTERNAL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LOOKUP_PROCESS;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LOOKUP_SCHEDULE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LOOKUP_SENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LOOKUP_STATISTICAL_COMING;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LOOKUP_STATISTICAL_LIST;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LOOKUP_STATISTICAL_TOTAL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.REQUEST_STATIS_LIST;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.RESOURCECODEID;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SEARCH_COMING;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SEARCH_FRAGMENT_TAG;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SEARCH_INTERNAL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SEARCH_NUMBER_COMING;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SEARCH_NUMBER_INTERNAL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SEARCH_NUMBER_PROCESS;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SEARCH_NUMBER_SENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SEARCH_PROCESS;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SEARCH_SENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SLIDERFILE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SLIDER_MENU;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SQLLITE_DOCUMENT_LIST;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.STATIS_LIST_ROW;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.STA_DOC_DEMURRAGE_FULL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.STA_DOC_NOT_PROCESS_FULL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.STA_DOC_PROCESS_ON_TIME_FULL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.STA_DOC_PROCESS_ON_TIME_FULL_ARISE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAG;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_DPM_DELAYS;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_DPM_INDUE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_DPM_ONTIME;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_DPM_OUT_OF_DATE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TASK_REQUEST;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TRUE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.URL_CENTER_6_1;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.WORK_ARISE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.nULL_STRING;

/**
 * Created by Vinh on 12/6/2017.
 */

public class OfficeLogic extends BaseOffical implements OfficeLogicImp, MyInterface, AsyncTaskCompleteListener<ResuiltObject>, VolleyTaskCompletedListenner<ResuiltObject> {
    Context context;
    OfficeView mOfficeView;

    public OfficeLogic(Context context, OfficeView mOfficeView) {
        this.context = context;
        this.mOfficeView = mOfficeView;
        getInforAccountFromShareReferenced(context);
    }

    private JSONObject addJsonRequestSlider() {
        JSONObject mJsonObject = new JSONObject();
        try {
            mJsonObject.put(MODULE, MODULE_PROCESSING_WORKING);
            mJsonObject.put(NEOTYPE, TYPE_SLIDER_MENU_LIST);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mJsonObject;
    }

    @Override
    public void runRequestHttp(String UnderLoad) {
//        new ReadJsonSlider().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, UnderLoad);
//        new NeoTask(context, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(context, SLIDER_MENU, nULL_STRING, getLink() + URL_CENTER_6_1));
        new VolleyTask(context, SLIDER_MENU, addJsonRequestSlider(), this);
//        mHandler.postDelayed(new Runnable() {
//            public void run() {
//                if (!isDownloadOffLine()) {
//                    eventReadJsonOffLine(0);
//                }
//                mHandler.postDelayed(this, 60000);
//            }
//        }, 0);
    }

    @SuppressLint("LongLogTag")
    @Override
    public void SliderData(final String creencomeback) {
        if (isNetworkAvailable(context)) {
            runOnUiThread(new Runnable() {
                @SuppressLint("LongLogTag")
                @Override
                public void run() {
                    Log.d(TAG + "Have Connection", "LoadSlider Internet");
                    if (creencomeback == null) {
                        runRequestHttp(TRUE);
                    } else {
                        upDateSliderMenu(readFromFile(context, SLIDERFILE), true);
                    }
                }
            });
        } else {
            Log.d(TAG + "No Connection", "LoadSlider Offline");
            mOfficeView.closeProgress();
            mOfficeView.showErrorDialog();

        }
    }

    @Override
    public void ReLoadComeBack(String creencomeback) {
        mOfficeView.setLookupScreen(creencomeback);
        if (creencomeback != null) {
            switch (creencomeback) {
                case LOOKUP_PROCESS:
                    if (isNetworkAvailable(context)) {
                        Cursor cursor = mOfficeView.getmSqLite().GetData(GET_NAME_FROM_DOCUMENT_LIST_SQLITE_DATABASE);
//                        List<String> tables = new ArrayList<>();
                        while (cursor.moveToNext()) {
//                            tables.add(cursor.getString(0));
//                            getmSqLite().QueryData(DROP_TABLE_IF_EXISTS + cursor.getString(0) + "'");
                            if (cursor.getString(0).toString().substring(0, 1).equals("M")) {
                                String DeleteStrings = "delete from " + cursor.getString(0) + " where jobId = '" + mOfficeView.getDocComeBackNumber() + "'";
                                mOfficeView.getmSqLite().QueryData(DeleteStrings);
                            }
                            //mOfficeView.getmSqLite().QueryData("delete * from " + cursor.getString(0)  + " where documentID = '" + DocComeBackNumber + "'");
                        }
//                        for (int i = 0; i < arrUrlNa.size(); i++) {
//                            mSqLite.QueryData("delete * from " + arrUrlNa.get(i).toString()  + " where documentID = '" + DocComeBackNumber + "'");
//                            String Logx = "delete * from " + arrUrlNa.get(i).toString()  + " where documentID = '" + DocComeBackNumber + "'";
//                            Log.d(TAG, Logx);
//                        }
                        eventReadJsonNumberOfTotal();
                    } else {
                        mOfficeView.TotalAndChangeUIDocument(urlNA);
                    }
                    break;
                default:
                    mOfficeView.setTypeStatistical();
                    eventGetDataScreen(creencomeback);
                    break;
            }
        } else {
//            changeUiHome(true);
            mOfficeView.goToHomeScreen();
        }
    }

    @Override
    public void RequestDocumentLookup() {
//        new ReadJsonListDocumentLookUp().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, getLink() + URL_CENTER_6_1);
        String isCsreen = mOfficeView.getLookupScreen();
        switch (isCsreen) {
            case WORK_ARISE:
                new VolleyTask(context, WORK_ARISE, addJsonForRequestDocumentLookup(MODULE_WORK_ARISE, TYPE_WORK_ARISE_LIST), this);
                break;
            case LOOKUP_COMING:
//                new NeoTask(context, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(context, LOOKUP_COMING, addJsonForRequestDocumentLookup(TYPE_LOOKUP_DOCUMENT_LIST_COMING).toString(), getLink() + URL_CENTER_6_1));
                new VolleyTask(context, LOOKUP_COMING, addJsonForRequestDocumentLookup(MODULE_LOOKUP_DOCUMENT, TYPE_LOOKUP_DOCUMENT_LIST_COMING), this);
                break;
            case LOOKUP_SENT:
//                new NeoTask(context, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(context, LOOKUP_SENT, addJsonForRequestDocumentLookup(TYPE_LOOKUP_DOCUMENT_LIST_SENT), getLink() + URL_CENTER_6_1));
                new VolleyTask(context, LOOKUP_SENT, addJsonForRequestDocumentLookup(MODULE_LOOKUP_DOCUMENT, TYPE_LOOKUP_DOCUMENT_LIST_SENT), this);
                break;
            case LOOKUP_INTERNAL:
//                new NeoTask(context, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(context, LOOKUP_INTERNAL, addJsonForRequestDocumentLookup(TYPE_LOOKUP_DOCUMENT_LIST_INTERNAL).toString(), getLink() + URL_CENTER_6_1));
                new VolleyTask(context, LOOKUP_INTERNAL, addJsonForRequestDocumentLookup(MODULE_LOOKUP_DOCUMENT, TYPE_LOOKUP_DOCUMENT_LIST_INTERNAL), this);
                break;
            case DOC_NOT_PROCESS_TYPE:
            case DOC_NOT_SEEN_TYPE:
            case DOC_DEMURRAGE_TYPE:
            case STA_DOC_PROCESS_ON_TIME_FULL:
            case STA_DOC_NOT_PROCESS_FULL:
            case STA_DOC_DEMURRAGE_FULL:
            case LIST_DOCUMENT_DEPARTMENT:
//                new NeoTask(context, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(context, LIST_DOCUMENT_DEPARTMENT, addJsonForRequestHomeListDocument(mOfficeView.getTypeHomeListDocument()).toString(), getLink() + URL_CENTER_6_1));
                new VolleyTask(context, LIST_DOCUMENT_DEPARTMENT, addJsonForRequestHomeListDocument(mOfficeView.getTypeHomeListDocument()), this);
                break;
            case STA_DOC_PROCESS_ON_TIME_FULL_ARISE:
                new VolleyTask(context, STA_DOC_PROCESS_ON_TIME_FULL_ARISE, addJsonRequestStaListWorkArise(mOfficeView.getTypeHomeListDocument()), this);
                break;
            // statistical department
            case TAP_DPM_DELAYS:
            case TAP_DPM_INDUE:
            case TAP_DPM_ONTIME:
            case TAP_DPM_OUT_OF_DATE:
//                new NeoTask(context, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(context, STATIS_LIST_ROW, addJsonRequestStatisList().toString(), getLink() + URL_CENTER_6_1));
                new VolleyTask(context, STATIS_LIST_ROW, addJsonRequestStatisList(), this);
                break;
            // task
            case TASK_REPORTED:
            case TASK_PROCESS:
            case PROCESS_ON_TIME:
            case TASK_PROCESS_NEAR_DEMURRAGE:
            case PROCESS_DEMURRAGE:
//                new NeoTask(context, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(context, TASK_REQUEST, addJsonForRequestTaskList().toString(), getLink() + URL_CENTER_6_1));
                new VolleyTask(context, TASK_REQUEST, addJsonForRequestTaskList(), this);
                break;
            case HOT_PROCESS:
            case HOT_PROCESS_DEMURRAGE:
            case HOT_PROCESS_ONDUE:
//                new NeoTask(context, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(context, HOTLINE_REQUEST, addJsonForRequestHotLine().toString(), getLink() + URL_CENTER_6_1));
                new VolleyTask(context, HOTLINE_REQUEST, addJsonForRequestHotLine(), this);
                break;
            default:
                break;
        }
    }

    private JSONObject addJsonRequestStaListWorkArise(String TYPE) {
        JSONObject mJsonObject = new JSONObject();
        try {
            mJsonObject.put(MODULE, MODULE_WORK_ARISE);
            mJsonObject.put(NEOTYPE, TYPE);
            JSONObject mJsonObjectData = new JSONObject();
            mJsonObjectData.put(FROM_DATE, getMilisecondDay(mOfficeView.getDateFirst()));
            mJsonObjectData.put(TO_DATE, getMilisecondDay(mOfficeView.getDateLast()));
            mJsonObjectData.put(ORGANIZATION_ID, mOfficeView.getOganizationID());
            mJsonObjectData.put(PROCESSED_ID, mOfficeView.getProcesserID());
            mJsonObjectData.put(STATISTIC_CODE, mOfficeView.getStatisticType());
            mJsonObjectData.put(ROW_PER_PAGE, LimitPager);
            mJsonObjectData.put(NUM_PAGE, mOfficeView.getPositionPage());
            mJsonObject.put(DATA, mJsonObjectData.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mJsonObject;
    }


    public JSONObject addJsonRequestStatisList() {
        JSONObject mObject = new JSONObject();
        try {
            mObject.put(MODULE, MODULE_LOOKUP_DOCUMENT);
            mObject.put(NEOTYPE, TYPE_STATIS_LIST);
            JSONObject mData = new JSONObject();
            // this is object id
            mData.put(NOTIFY_OBJECT_ID, mOfficeView.getOganizationID());
            mData.put(CATEGORY, mOfficeView.getCategory());
            mData.put(STATISTIC_CODE, mOfficeView.getStatisticType());
            mData.put(FROM_DATE, getMilisecondDay(mOfficeView.getStatisStartDate()));
            mData.put(TO_DATE, getMilisecondDay(mOfficeView.getStatisEndDate()));
            mData.put(ROW_PER_PAGE, LimitPager);
            mData.put(NUM_PAGE, mOfficeView.getPositionPage());
            mObject.put(DATA, mData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // remember this request here.
        if (mOfficeView.getPositionPage() == 1) {
            setDefaults(REQUEST_STATIS_LIST, mObject.toString(), context);
        }
        return mObject;
    }

    @Override
    public void RequestJsonDocument() {
//        new NeoTask(context, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(context, DOC_PROCESS_LIST, addJsonRequestDocument().toString(), getLink() + URL_CENTER_6_1));
//        new ReadJsonDocument().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, getLink() + URL_CENTER_6_1);
        new VolleyTask(context, DOC_PROCESS_LIST, addJsonRequestDocument(), this);
    }


    /*=============================================
    start read json total document method
===============================================*/

    public void eventReadJsonNumberOfTotal() {
        mOfficeView.showProgress();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                changeUIDocment(DOCUMENT_FRAGMENT_TAG, null);
//                new ReadNumberOfPage().execute(getLink() + URL_CENTER + URL_TOTAL_DOCUMENT);
//                new ReadNumberOfPage().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mHost + URL_CENTER + URL_TOTAL_DOCUMENT);
                eventGetDataScreen(DOCUMENT_FRAGMENT_TAG);

            }
        });
    }

    public void eventReadJsonNumberOfTotalDocumentLookup() {
        showProgressDialog(nULL_STRING, this, DIALOG_CENTER, true);
        if (isNetworkAvailable(context)) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    new NumberOfComingDocument().execute(getLink() + URL_CENTER_6_1);
                }
            });
        } else {
            switch (getLookupScreen()) {
                case LOOKUP_COMING:
                    TotalOfflineLookupComingList(LOOKUP_COMING);
                    break;
                case LOOKUP_SENT:
                    TotalOfflineLookupComingList(LOOKUP_SENT);
                    break;
                case LOOKUP_INTERNAL:
                    TotalOfflineLookupComingList(LOOKUP_INTERNAL);
                    break;
                default:
                    break;
            }

        }

    }

    public String addJsonRequestNumberPageDocumentLookup(String Type) {
        JSONObject mJsonObject = new JSONObject();
        try {
            mJsonObject.put(MODULE, MODULE_LOOKUP_DOCUMENT);
            mJsonObject.put(NEOTYPE, Type);
            JSONObject mJsonObjectData = new JSONObject();
            mJsonObjectData.put(ROW_PER_PAGE, 1);
            mJsonObjectData.put(SEARCH_CRITERIA, nULL_STRING);
            mJsonObject.put(DATA, mJsonObjectData.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mJsonObject.toString();
    }

    @Override
    public void onTaskCompleted(String s, String CaseRequest) {
        refreshUI(s, CaseRequest);
    }


    private void refreshUI(String s, String CaseRequest) {
        switch (CaseRequest) {
            case SLIDER_MENU:
                if (!s.equals(nULL_STRING) && !s.equals("Error!")) {
                    writeToFile(s.toString(), context, SLIDERFILE);
                }
                upDateSliderMenu(s, true);
                break;
            case DOC_PROCESS_LIST:
                if (mOfficeView.getLookupScreen().equals(LOOKUP_PROCESS)) {
                    if (!isDownloadOffLine()) {
                        if (checkErrorServerDocumentList) {
                            mOfficeView.closeProgress();
                            Toast.makeText(context, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
                            checkErrorServerDocumentList = false;
                            return;
                        } else {
                            if (mOfficeView.getPositionPage() > 1
                                    || (arrDocument != null && arrDocument.size() > 0)) {
                                getListDocumentSQLite((mOfficeView.getPositionPage() - 1) * getLimitPager(), urlNA);
                            } else {
//                                if (arrDocument != null && arrDocument.size() > 0) {
//                                    getListDocumentSQLite((mOfficeView.getPositionPage() - 1) * getLimitPager(), urlNA);
//                                } else {
                                mOfficeView.changeUIDocument(DOCUMENT_FRAGMENT_TAG, urlNA);
//                                }
                            }
                        }
                    }
                }
                break;
            case STA_DOC_PROCESS_ON_TIME_FULL_ARISE:
            case WORK_ARISE:
            case LOOKUP_COMING:
            case LOOKUP_SENT:
            case LOOKUP_INTERNAL:
            case LIST_DOCUMENT_DEPARTMENT:
            case TASK_REQUEST:
            case HOTLINE_REQUEST:
            case STATIS_LIST_ROW:
                if (!mOfficeView.getLookupScreen().equals(LOOKUP_PROCESS)) {
                    try {
                        JSONObject mJsonObjectFulData = new JSONObject(s);
                        JSONArray mJsonArrayData = mJsonObjectFulData.getJSONArray(DATA);
                        arrResuiltDocumentLookup(mJsonArrayData, true);
                    } catch (JSONException e) {
                        checkErrorServerDocumentListLookup = true;
                        e.printStackTrace();
                    }
                    if (checkErrorServerDocumentListLookup) {
                        mOfficeView.closeProgress();
                        ShowErrorToast(context);
                        checkErrorServerDocumentListLookup = false;
                        return;
                    }
                    switch (mOfficeView.getLookupScreen()) {
                        case HOME_SCREEN:
                            mOfficeView.setActionBarIcon(R.drawable.menu_icon_slider);
                            break;
                        case LOOKUP_STATISTICAL_TOTAL:
                        case LOOKUP_STATISTICAL_LIST:
                            break;
                        case DOC_NOT_PROCESS_TYPE:
                        case DOC_NOT_SEEN_TYPE:
                        case DOC_DEMURRAGE_TYPE:
                        case STA_DOC_PROCESS_ON_TIME_FULL:
                        case STA_DOC_PROCESS_ON_TIME_FULL_ARISE:
                        case STA_DOC_NOT_PROCESS_FULL:
                        case STA_DOC_DEMURRAGE_FULL:
                        case LIST_DOCUMENT_DEPARTMENT:
                        case TASK_REPORTED:
                        case TASK_PROCESS:
                        case PROCESS_ON_TIME:
                        case TASK_PROCESS_NEAR_DEMURRAGE:
                        case PROCESS_DEMURRAGE:
                            // statistical department
                        case TAP_DPM_DELAYS:
                        case TAP_DPM_INDUE:
                        case TAP_DPM_ONTIME:
                        case TAP_DPM_OUT_OF_DATE:
                            if (mOfficeView.getDocumentFragment().isAdded()) {
                                if (mOfficeView.getPositionPage() > 1) {
                                    adapterClickNumber.notifyDataSetChanged();
                                } else {
                                    adapterClickNumber = new DocumentLookupAdapter(context, arrDocumentLookUp);
                                    mOfficeView.setDocLookup(adapterClickNumber);
                                    mOfficeView.setActionBarIcon(R.drawable.bar_back_x1);
                                }
                                if (arrDocumentLookUp.size() == 0) {
                                    mOfficeView.showDocEmpty(true);
//                            if (!isNetworkAvailable(context)) {
//                                mOfficeView.visibleNotConnection(true);
//                            } else {
//                                mOfficeView.showDocEmpty(true);
//
//                            }
                                }
                            }


                            break;
                        case CODE_STATISTICAL_DEPARTMENT:
                            mOfficeView.setActionBarIcon(R.drawable.menu_icon_slider);
                            break;
                        case HOT_PROCESS:
                        case HOT_PROCESS_DEMURRAGE:
                        case HOT_PROCESS_ONDUE:
                            if (mOfficeView.getPositionPage() > 1) {
                                adapterHotLine.notifyDataSetChanged();
                            } else {
                                adapterHotLine = new HotLineAdapter(context, arrHotLineList);
                                mOfficeView.HotLineAdapter(adapterHotLine);
                                mOfficeView.setActionBarIcon(R.drawable.bar_back_x1);
                            }
                            if (arrHotLineList.size() == 0) {
                                if (!isNetworkAvailable(context)) {
                                    mOfficeView.visibleNotConnection(true);
                                } else {
                                    mOfficeView.showDocEmpty(true);

                                }
                            }
                            break;
                        default:
                            if (mOfficeView.getPositionPage() > 1) {
                                getListDocumentLookupComing((mOfficeView.getPositionPage() - 1) * getLimitPager());
                            } else {
                                if (!mOfficeView.getLookupScreen().equals(nULL_STRING)) {
                                    mOfficeView.changeUIDocument(mOfficeView.getLookupScreen(), null);
                                }
                            }
                            break;
                    }
                }
                break;
            case SEARCH_COMING:
            case SEARCH_SENT:
            case SEARCH_INTERNAL:
            case SEARCH_PROCESS:
                setListSearch(s);
                break;
            case SEARCH_NUMBER_COMING:
            case SEARCH_NUMBER_SENT:
            case SEARCH_NUMBER_INTERNAL:
            case SEARCH_NUMBER_PROCESS:
                // add do not show this list when tap to statistical coming
                if (mOfficeView.getLookupScreen().equals(LOOKUP_STATISTICAL_COMING) || mOfficeView.getLookupScreen().equals(LOOKUP_SCHEDULE))
                    return;
                // end do not show this list when tap to statistical coming
                CountSearchTotal(s);
                break;
        }
        Log.d(TAG, CaseRequest);
        if (!CaseRequest.equals(SEARCH_NUMBER_PROCESS) && !CaseRequest.equals(SEARCH_NUMBER_COMING)
                && !CaseRequest.equals(SEARCH_NUMBER_INTERNAL) && !CaseRequest.equals(SEARCH_NUMBER_INTERNAL)) {
            mOfficeView.closeProgress();
        }
        mOfficeView.setLoading();
        mOfficeView.closeSwipe();

    }

    private void setListSearch(String s) {
        // set boolean for backpress after search success
        mOfficeView.setAfterSearch(true);
        if (!isDownloadOffLine()) {
            try {
                Log.d(TAG, s);
                switch (mOfficeView.getLookupScreen()) {
                    case LOOKUP_COMING:
                        setListViewLookUp(s, LOOKUP_COMING);
                        break;
                    case LOOKUP_SENT:
                        setListViewLookUp(s, LOOKUP_SENT);
                        break;
                    case LOOKUP_INTERNAL:
                        setListViewLookUp(s, LOOKUP_INTERNAL);
                        break;
                    default:
                        GsonDocument mGsonDocument = getGson().fromJson(s, GsonDocument.class);
                        List<GsonDocument.DataBean> mArray = mGsonDocument.getData();
                        for (int i = 0; i < mArray.size(); i++) {
                            GsonDocument.DataBean object = mArray.get(i);
                            mSqLite.QueryData(CREATE_TABLE + " " + " M" + getOnlyNumerics(String.valueOf(object.getLabelCode())) + SQLLITE_DOCUMENT_LIST);
                            arrDocument.add(object);
                        }
                        if (mOfficeView.getPositionPage() > 1) {
                            adapterSearch.notifyDataSetChanged();
                        } else {
                            adapterSearch = new DocumentAdapter(context, arrDocument);
                            mOfficeView.setListDoc(adapterSearch);
                        }
                        mOfficeView.setCheckShowListSearch(true);
                        break;
                }
            } catch (Exception e) {
                ShowErrorToast(context);
                e.printStackTrace();
            } finally {
                mOfficeView.setActionBarIcon(R.drawable.menu_icon_slider);
            }
        }
    }

    private void CountSearchTotal(String s) {
        try {
            JSONObject mJsonObjectFullData = new JSONObject(s);
            JSONObject mJsonObject = mJsonObjectFullData.getJSONObject(DATA);
            int numBerOfSearch = mJsonObject.getInt(COUNT);
            if (numBerOfSearch != 0) {
                setNumberOfPage(numBerOfSearch);
                mOfficeView.changeUIDocument(SEARCH_FRAGMENT_TAG, null);
            } else {
                Toast.makeText(context, "Không có văn bản", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            ShowErrorToast(context);
            e.printStackTrace();
        } finally {
            mOfficeView.closeProgress();
            mOfficeView.disibleInputSearchTop(true);
        }

    }

    @Override
    public void onVolleyCompleted(String s, String CaseRequest) {
        refreshUI(s, CaseRequest);
    }

    @Override
    public void onVolleyError(String s) {
        mOfficeView.closeProgress();
        mOfficeView.ToastError(s);
        mOfficeView.closeSwipe();
        mOfficeView.setLoading();
    }


    class NumberOfComingDocument extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            String mRequest = nULL_STRING;
            switch (getLookupScreen()) {
                case LOOKUP_COMING:
                    mRequest = addJsonRequestNumberPageDocumentLookup(TYPE_LOOKUP_TOTAL_COMING);
                    break;
                case LOOKUP_SENT:
                    mRequest = addJsonRequestNumberPageDocumentLookup(TYPE_LOOKUP_TOTAL_SENT);
                    break;
                case LOOKUP_INTERNAL:
                    mRequest = addJsonRequestNumberPageDocumentLookup(TYPE_LOOKUP_TOTAL_INTERNAL);
                    break;
                default:
                    break;
            }
//            return makePostRequest(params[0], mRequest, getUserid(), getPass());
            return eventPostRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, context), mRequest, getUserid(), getPass());

        }

        @Override
        protected void onPostExecute(String s) {
            int totalItems = 0;
            Log.d(TAG, s + "");
            try {
                JSONObject mJsonObjectFullData = new JSONObject(s);
                JSONObject mJsonObjectData = mJsonObjectFullData.getJSONObject(DATA);
                totalItems = mJsonObjectData.getInt(COUNT);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (totalItems != 0) {
                mOfficeView.setNumberOfPage(totalItems);
//                NumberOfPage = totalItems;
            } else {
                mOfficeView.setNumberOfPage(0);
//                NumberOfPage = 0;
            }
            switch (getLookupScreen()) {
                case LOOKUP_COMING:
                    eventGetDataScreen(LOOKUP_COMING);
//                    changeUIDocment(LOOKUP_COMING, null);
                    break;
                case LOOKUP_SENT:
                    eventGetDataScreen(LOOKUP_COMING);
//                    changeUIDocment(LOOKUP_SENT, null);
                    break;
                case LOOKUP_INTERNAL:
                    eventGetDataScreen(LOOKUP_COMING);
//                    changeUIDocment(LOOKUP_INTERNAL, null);
                    break;
                default:
                    break;
            }
            super.onPostExecute(s);
        }
    }


    public JSONObject addJsonRequestDocument() {
        JSONObject mJsonObject = new JSONObject();
        try {
            mJsonObject.put(MODULE, MODULE_PROCESSING_WORKING);
            mJsonObject.put(NEOTYPE, TYPE_DOCUMENT_LIST);
            JSONObject mJsonData = new JSONObject();
            mJsonData.put(LABLE_URL_PARAM, urlNA);
            mJsonData.put(ROW_PER_PAGE, 20);
            mJsonData.put(NUM_PAGE, mOfficeView.getPositionPage());
            mJsonObject.put(DATA, mJsonData.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            checkErrorServerDocumentList = true;
        }
        return mJsonObject;
    }


    public void getListDocumentSQLite(int position, String mURLNA) {
        if (mURLNA == null) {
            return;
        }
//        arrDocument = new ArrayList<ItemsDocument>();
//        Cursor cursor = mSqLite.GetData("SELECT * FROM '" + "M" + getOnlyNumerics(mURLNA) + "' LIMIT '" + position + "', '" + getLimitPager() + "'");
//        Cursor cursor = mSqLite.GetData("SELECT * FROM " + "M" + getOnlyNumerics(mURLNA) + " ORDER BY  documentID DESC LIMIT '" + position + "', '" + getLimitPager() + "'");
        Cursor cursor = mOfficeView.getmSqLite().GetData("SELECT * FROM " + "M" + getOnlyNumerics(mURLNA) + " ORDER BY receiveDateMilisec DESC LIMIT '" + position + "', '" + getLimitPager() + "'");
        Log.d("SQLOG", "SELECT * FROM " + "M" + getOnlyNumerics(mURLNA) + " ORDER BY datemilisec DESC LIMIT '" + position + "', '" + getLimitPager() + "'");
        int count = cursor.getCount();
        Log.d(TAG, count + " ");
        if (count == 0) {
            if (isNetworkAvailable(context)) {
                mOfficeView.showDocEmpty(true);
            } else {
                mOfficeView.visibleNotConnection(true);
                mOfficeView.closeProgress();
            }
        } else {
            if (position == 0) {
                arrDocument.clear();
            }
            while (cursor.moveToNext()) {
                if (mURLNA != null) {
                    arrDocument.add(getDocumentRowSql(cursor));
                }
            }
            if (position >= 1) {
                for (int i = 0; i < arrDocument.size(); i++) {
                    Log.d("arrDocument ", arrDocument.get(i).getTitle() + " " + arrDocument.get(i).getJobType());
                }
                adapterDocument.notifyDataSetChanged();
            } else {
                adapterDocument = new DocumentAdapter(context, arrDocument);
                mOfficeView.setListDoc(adapterDocument);
            }
        }
        if (arrDocument.size() == 0) {
            mOfficeView.visibleNotConnection(true);
        } else {
            mOfficeView.visibleNotConnection(false);
        }
        mOfficeView.closeProgress();

    }


    /*===============================
    update search list with pageposition
=================================*/

    public void upDateSearchView(final int numberPage, boolean mUpDateRow) {
//        if (arrDocument.size() == NumberOfPage || arrDocumentLookUp.size() == NumberOfPage) {
//        mOfficeView.showProgress();
        if (arrDocument.size() == getNumberOfPage() || arrDocumentLookUp.size() == getNumberOfPage()) {
            mOfficeView.closeProgress();
            return;
        }
        mOfficeView.setPositionPage(numberPage);
        switch (mOfficeView.getLookupScreen()) {
            case LOOKUP_COMING:
//                new NeoTask(context, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(context, SEARCH_COMING, addJsonRequestListSearch(MODULE_LOOKUP_DOCUMENT, TYPE_LOOKUP_DOCUMENT_LIST_COMING, false).toString(), getLink() + URL_CENTER_6_1));
                new VolleyTask(context, SEARCH_COMING, addJsonRequestListSearch(MODULE_LOOKUP_DOCUMENT, TYPE_LOOKUP_DOCUMENT_LIST_COMING, false), this);
                break;
            case LOOKUP_SENT:
//                new NeoTask(context, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(context, SEARCH_SENT, addJsonRequestListSearch(MODULE_LOOKUP_DOCUMENT, TYPE_LOOKUP_DOCUMENT_LIST_SENT, false).toString(), getLink() + URL_CENTER_6_1));
                new VolleyTask(context, SEARCH_SENT, addJsonRequestListSearch(MODULE_LOOKUP_DOCUMENT, TYPE_LOOKUP_DOCUMENT_LIST_SENT, false), this);
                break;
            case LOOKUP_INTERNAL:
//                new NeoTask(context, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(context, SEARCH_INTERNAL, addJsonRequestListSearch(MODULE_LOOKUP_DOCUMENT, TYPE_LOOKUP_DOCUMENT_LIST_INTERNAL, false).toString(), getLink() + URL_CENTER_6_1));
                new VolleyTask(context, SEARCH_INTERNAL, addJsonRequestListSearch(MODULE_LOOKUP_DOCUMENT, TYPE_LOOKUP_DOCUMENT_LIST_INTERNAL, false), this);
                break;
            default:
//                new NeoTask(context, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(context, SEARCH_PROCESS, addJsonRequestListSearch(MODULE_PROCESSING_WORKING, TYPE_DOCUMENT_LIST, true).toString(), getLink() + URL_CENTER_6_1));
                new VolleyTask(context, SEARCH_PROCESS, addJsonRequestListSearch(MODULE_PROCESSING_WORKING, TYPE_DOCUMENT_LIST, true), this);
                break;
        }
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                new ReadJsonFromSearch().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, getLink() + URL_CENTER_6_1);
//
//            }
//        });
    }

    /*=============================
     get list document from server with search information
===============================*/

    public JSONObject addJsonRequestListSearch(String MODULEs, String TYPEs, boolean putUrlNa) {
        JSONObject mJsonObject = new JSONObject();
        try {
            mJsonObject.put(MODULE, MODULEs);
            mJsonObject.put(NEOTYPE, TYPEs);
            JSONObject mData = SearchData(TYPEs, LimitPager, mOfficeView.getPositionPage(), putUrlNa, true);
            mJsonObject.put(DATA, mData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mJsonObject;
    }


    public void setListViewLookUp(String s, String screenNameInside) {
        JSONObject mObjectLookup;
        try {
            mObjectLookup = new JSONObject(s);
            JSONArray mJsonArrayData = mObjectLookup.getJSONArray(DATA);
            arrResuiltDocumentLookup(mJsonArrayData, false);
            if (mOfficeView.getPositionPage() > 1) {
                adapterDocumentLookup.notifyDataSetChanged();
            } else {
                adapterDocumentLookup = new DocumentLookupAdapter(context, arrDocumentLookUp);
                mOfficeView.setDocLookupHome(adapterDocumentLookup);
            }
            mOfficeView.visibleNotConnection(false);
            mOfficeView.closeProgress();
            mOfficeView.setCheckShowListSearch(true);
            mOfficeView.setScreenNameInSide(screenNameInside);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void eventSearchStart(SearchRow searchRow) {
        mSearchRow = searchRow;
        switch (mOfficeView.getLookupScreen()) {
            case LOOKUP_COMING:
//                new NeoTask(context, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(context, SEARCH_NUMBER_COMING, addJsonForSearch(MODULE_LOOKUP_DOCUMENT, TYPE_LOOKUP_TOTAL_COMING, false).toString(), getLink() + URL_CENTER_6_1));
                new VolleyTask(context, SEARCH_NUMBER_COMING, addJsonForSearch(MODULE_LOOKUP_DOCUMENT, TYPE_LOOKUP_TOTAL_COMING, false), this);
                break;
            case LOOKUP_SENT:
//                new NeoTask(context, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(context, SEARCH_NUMBER_SENT, addJsonForSearch(MODULE_LOOKUP_DOCUMENT, TYPE_LOOKUP_TOTAL_SENT, false).toString(), getLink() + URL_CENTER_6_1));
                new VolleyTask(context, SEARCH_NUMBER_SENT, addJsonForSearch(MODULE_LOOKUP_DOCUMENT, TYPE_LOOKUP_TOTAL_SENT, false), this);
                break;
            case LOOKUP_INTERNAL:
//                new NeoTask(context, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(context, SEARCH_NUMBER_INTERNAL, addJsonForSearch(MODULE_LOOKUP_DOCUMENT, TYPE_LOOKUP_TOTAL_INTERNAL, false).toString(), getLink() + URL_CENTER_6_1));
                new VolleyTask(context, SEARCH_NUMBER_INTERNAL, addJsonForSearch(MODULE_LOOKUP_DOCUMENT, TYPE_LOOKUP_TOTAL_INTERNAL, false), this);
                break;
            case LOOKUP_PROCESS:
//                new NeoTask(context, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(context, SEARCH_NUMBER_PROCESS, addJsonForSearch(MODULE_PROCESSING_WORKING, TYPE_TOTAL_DOCUMENT_LIST, true).toString(), getLink() + URL_CENTER_6_1));
                new VolleyTask(context, SEARCH_NUMBER_PROCESS, addJsonForSearch(MODULE_PROCESSING_WORKING, TYPE_TOTAL_DOCUMENT_LIST, true), this);
                break;
        }
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                new NumberPageOfSearch().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, getLink() + URL_CENTER_6_1);
//            }
//        });
    }

//    class NumberPageOfSearch extends AsyncTask<String, Integer, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            String mRequest = "";
//            String Resuilt = "";
//            switch (mOfficeView.getLookupScreen()) {
//                case LOOKUP_COMING:
//                    mRequest = addJsonForSearch(MODULE_LOOKUP_DOCUMENT, TYPE_LOOKUP_TOTAL_COMING, false);
//                    Resuilt = eventPostRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, context), mRequest, getUserid(), getPass());
//                    break;
//                case LOOKUP_SENT:
//                    mRequest = addJsonForSearch(MODULE_LOOKUP_DOCUMENT, TYPE_LOOKUP_TOTAL_SENT, false);
//                    Resuilt = eventPostRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, context), mRequest, getUserid(), getPass());
//                    break;
//                case LOOKUP_INTERNAL:
//                    mRequest = addJsonForSearch(MODULE_LOOKUP_DOCUMENT, TYPE_LOOKUP_TOTAL_INTERNAL, false);
//                    Resuilt = eventPostRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, context), mRequest, getUserid(), getPass());
//                    break;
//                case LOOKUP_PROCESS:
//                    mRequest = addJsonForSearch(MODULE_PROCESSING_WORKING, TYPE_TOTAL_DOCUMENT_LIST, true);
//                    Resuilt = eventPostRequest(getModuleInfor(MODULE_PROCESSING_WORKING, context), mRequest, getUserid(), getPass());
//                    break;
//            }
//            return Resuilt;
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            try {
//                JSONObject mJsonObjectFullData = new JSONObject(s);
//                JSONObject mJsonObject = mJsonObjectFullData.getJSONObject(DATA);
//                int numBerOfSearch = mJsonObject.getInt(COUNT);
//                if (numBerOfSearch != 0) {
////                    NumberOfPage = numBerOfSearch;
//                    setNumberOfPage(numBerOfSearch);
//                    mOfficeView.changeUIDocument(SEARCH_FRAGMENT_TAG, null);
//                } else {
//                    Toast.makeText(context, "Không có kết quả.", Toast.LENGTH_SHORT).show();
//                }
//                mOfficeView.closeProgress();
//            } catch (JSONException e) {
//                ShowErrorToast(context);
//                e.printStackTrace();
//            }
//            super.onPostExecute(s);
//        }
//    }

    /*==============================
    this Asyntask get total document when you search
================================*/
    private JSONObject addJsonForSearch(String MODULEs, String TYPEs, boolean putUrlNa) {
        JSONObject mJsonObject = new JSONObject();
        try {
            mJsonObject.put(MODULE, MODULEs);
            mJsonObject.put(NEOTYPE, TYPEs);
            JSONObject data = SearchData(TYPEs, LimitPager, mOfficeView.getPositionPage(), putUrlNa, false);
            mJsonObject.put(DATA, data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mJsonObject;
    }


    public JSONObject SearchData(String TYPEs, int limitPager, int positionPage, boolean putUrlNa, boolean putPage) {
        JSONObject mData = new JSONObject();
        try {
            if (putUrlNa) {
                mData.put(LABLE_URL_PARAM, urlNA);
            }
            mData.put(ROW_PER_PAGE, limitPager);
            if (putPage) {
                mData.put(NUM_PAGE, positionPage);
            }
            mData.put(ADVANCED, mSearchRow.isAdvanced());
            mData.put(KEYWORDS, mSearchRow.getKeywords());
            switch (TYPEs) {
                case TYPE_LOOKUP_TOTAL_COMING:
                case TYPE_LOOKUP_DOCUMENT_LIST_COMING:
                    mData.put(DOC_NUMBER_FULL, mSearchRow.getDocNumberFull());
                    mData.put(NUMBER_OF_SYMBOL, mSearchRow.getNumberOfSymbol());
                    mData.put(DOC_TYPE_ID, mSearchRow.getDocTypeId());
                    mData.put(BRIEF_CONTENT, mSearchRow.getBriefContent());
                    mData.put(FROM_PUBLISH_DATE, mSearchRow.getFromPublishDate());
                    mData.put(TO_PUBLISH_DATE, mSearchRow.getToPublishDate());
                    mData.put(FROM_RECEIVE_DATE, mSearchRow.getFromReceiveDate());
                    mData.put(TO_RECEIVE_DATE, mSearchRow.getToReceiveDate());
                    break;
                case TYPE_LOOKUP_TOTAL_SENT:
                case TYPE_LOOKUP_DOCUMENT_LIST_SENT:
                    mData.put(PUBLISH_NUMBER, mSearchRow.getPublishNumber());
                    mData.put(DOC_RECEIPT_ID, mSearchRow.getDocTypeId());
                    mData.put(BRIEF_CONTENT, mSearchRow.getBriefContent());
                    mData.put(FROM_PUBLISH_DATE, mSearchRow.getFromPublishDate());
                    mData.put(TO_PUBLISH_DATE, mSearchRow.getToPublishDate());
                    mData.put(FROM_CREATE_DATE, mSearchRow.getFromCreateDate());
                    mData.put(TO_CREATE_DATE, mSearchRow.getToCreateDate());
                    break;
                case TYPE_LOOKUP_TOTAL_INTERNAL:
                case TYPE_LOOKUP_DOCUMENT_LIST_INTERNAL:
                    mData.put(DOC_NUMBER, mSearchRow.getDocNumber());
                    mData.put(DOC_TYPE_ID, mSearchRow.getDocTypeId());
                    mData.put(BRIEF_CONTENT, mSearchRow.getBriefContent());
                    mData.put(FROM_SIGN_DATE, mSearchRow.getFromSignDate());
                    mData.put(TO_SIGN_DATE, mSearchRow.getToSignDate());
                    mData.put(FROM_CREATE_DATE, mSearchRow.getFromCreateDate());
                    mData.put(TO_CREATE_DATE, mSearchRow.getToCreateDate());
                    break;
                case TYPE_TOTAL_DOCUMENT_LIST:
                case TYPE_DOCUMENT_LIST:
                    mData.put(ORGANIZATION_EDITOR_ID, mSearchRow.getOrganizationId());
                    mData.put(FROM_RECEIVE_DATE, mSearchRow.getFromReceiveDate());
                    mData.put(TO_RECEIVE_DATE, mSearchRow.getToReceiveDate());
                    mData.put(FROM_PROCESS_DATE, mSearchRow.getFromProcessDate());
                    mData.put(TO_PROCESS_DATE, mSearchRow.getToProcessDate());
                    mData.put(FROM_DUE_DATE, mSearchRow.getFromDueDate());
                    mData.put(TO_DUE_DATE, mSearchRow.getToDueDate());
                    mData.put(SCHEDULE_CONTENT, mSearchRow.getContent());
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mData;
    }


    @Override
    public void notifySeen() {
        if (adapterDocument != null) {
            adapterDocument.notifyDataSetChanged();
        }
    }

    @Override
    public void RequestComeBackFromListDepartmentStatist() {
//        new NeoTask(context, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(context, STATIS_LIST_ROW, getDefaults(REQUEST_STATIS_LIST, context), getLink() + URL_CENTER_6_1));
        try {
            JSONObject mJsonObject = new JSONObject(getDefaults(REQUEST_STATIS_LIST, context));
            new VolleyTask(context, STATIS_LIST_ROW, mJsonObject, this);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void eventGetDataScreen(String isScreen) {
        mOfficeView.setPositionPage(1);
        switch (isScreen) {
            case SEARCH_FRAGMENT_TAG:
//                mDocumentFragment.eventSearch(positionPage, false);
//                changeUIDocument(SEARCH_FRAGMENT_TAG);
                break;
            case DOCUMENT_FRAGMENT_TAG:
                eventChangeToDocument(mOfficeView.getPositionPage(), getLimitPager() + nULL_STRING, urlNA, false);
                break;
            case LOOKUP_COMING:
            case LOOKUP_SENT:
            case LOOKUP_INTERNAL:
            case DOC_NOT_PROCESS_TYPE:
            case DOC_NOT_SEEN_TYPE:
            case DOC_DEMURRAGE_TYPE:
            case STA_DOC_PROCESS_ON_TIME_FULL:
            case STA_DOC_NOT_PROCESS_FULL:
            case STA_DOC_DEMURRAGE_FULL:
            case LIST_DOCUMENT_DEPARTMENT:
            case WORK_ARISE:
                mOfficeView.ShowProgress();
                setActionBarHomeScreen();
                eventChangeToLookupComing(mOfficeView.getPositionPage(), false);
                break;
            case TASK_REPORTED:
            case TASK_PROCESS:
            case PROCESS_ON_TIME:
            case TASK_PROCESS_NEAR_DEMURRAGE:
            case PROCESS_DEMURRAGE:
                mOfficeView.changeUIDocument(isScreen, null);
                break;
            default:
                break;
        }

    }

    public void setActionBarHomeScreen() {
        if (mOfficeView.getLookupScreen().equals(DOC_NOT_PROCESS_TYPE)
                || mOfficeView.getLookupScreen().equals(DOC_NOT_SEEN_TYPE)
                || mOfficeView.getLookupScreen().equals(DOC_DEMURRAGE_TYPE)
                || mOfficeView.getLookupScreen().equals(STA_DOC_PROCESS_ON_TIME_FULL)
                || mOfficeView.getLookupScreen().equals(STA_DOC_NOT_PROCESS_FULL)
                || mOfficeView.getLookupScreen().equals(LIST_DOCUMENT_DEPARTMENT)
                || mOfficeView.getLookupScreen().equals(STA_DOC_DEMURRAGE_FULL)) {
            mOfficeView.setActionBarIcon(R.drawable.bar_back_x1);
        }
    }

    private JSONObject addJsonForRequestHotLine() {
        Calendar mCalendar = Calendar.getInstance();
        String dateLast = mSimpleDateFormat.format(mCalendar.getTime()) + "";
        Calendar mCalendarFirstDay = Calendar.getInstance();
        mCalendarFirstDay.add(Calendar.MONTH, -1);
        String dateFirst = mSimpleDateFormat.format(mCalendarFirstDay.getTime()) + "";
        Log.d(TAG, dateLast + " " + dateFirst);
        String Statistic = mOfficeView.getStatisticTypeHotLine();
        JSONObject mJsonObject = new JSONObject();
        try {
            mJsonObject.put(MODULE, HOTLINE);
            mJsonObject.put(NEOTYPE, TYPE_HOTLIST);
            JSONObject mData = new JSONObject();
            mData.put(ORGANIZATION_ID, mOfficeView.getOganizationID());
            mData.put(FROM_DATE, getMilisecondDay(DATE_1_YEARS_FOR_TEST));
            mData.put(TO_DATE, getMilisecondDay(dateLast));
            mData.put(STATISTIC_CODE, Statistic);
            mData.put(ROW_PER_PAGE, LimitPager);
            mData.put(NUM_PAGE, mOfficeView.getPositionPage());
            mJsonObject.put(DATA, mData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mJsonObject;
    }


    public void arrResuiltDocumentLookup(JSONArray mJsonArray, boolean insert) {
        JSONObject mJsonObject;
        if (mOfficeView.getPositionPage() == 1 && arrDocumentLookUp != null)
            arrDocumentLookUp.clear();
        try {
            if (mOfficeView.getLookupScreen() != null) {
                switch (mOfficeView.getLookupScreen()) {
                    //Statistical department
                    case TAP_DPM_DELAYS:
                    case TAP_DPM_INDUE:
                    case TAP_DPM_ONTIME:
                    case TAP_DPM_OUT_OF_DATE:
                        //Task
                    case TASK_REPORTED:
                    case TASK_PROCESS:
                    case PROCESS_ON_TIME:
                    case TASK_PROCESS_NEAR_DEMURRAGE:
                    case PROCESS_DEMURRAGE:
                        for (int i = 0; i < mJsonArray.length(); i++) {
                            mJsonObject = mJsonArray.getJSONObject(i);
                            long taskId = mJsonObject.getLong(TASK_ID);
                            String publishNumber = mJsonObject.getString(PUBLISH_NUMBER);
                            String publishDate = mJsonObject.getString(PUBLISH_DATE);
                            String director = mJsonObject.getString(DIRECTOR);
                            String executionUnit = mJsonObject.getString(EXECUTION_UNIT);
                            String taskContent = mJsonObject.getString(TASK_CONTENT);
                            arrDocumentLookUp.add(new ItemsDocumentLookUp(taskId, publishDate, publishNumber, director, executionUnit, taskContent));
                        }
                        break;
                    case HOT_PROCESS:
                    case HOT_PROCESS_DEMURRAGE:
                    case HOT_PROCESS_ONDUE:
                        for (int i = 0; i < mJsonArray.length(); i++) {
                            mJsonObject = mJsonArray.getJSONObject(i);
                            long messageReflectId = mJsonObject.getLong(MESSAGE_REFLECT_ID);
                            String peopleReflect = mJsonObject.getString(PEOPLE_REFLECT);
                            String phoneReflect = mJsonObject.getString(PHONE_REFLECT);
                            String content = mJsonObject.getString(SCHEDULE_CONTENT);
                            String receiveDate = mJsonObject.getString(RECEIVE_DATE);
                            arrHotLineList.add(new HotLineListRow(messageReflectId, peopleReflect, phoneReflect, content, receiveDate));
                        }
                        break;
                    case DOC_NOT_SEEN_TYPE:
                    case DOC_NOT_PROCESS_TYPE:
                    case DOC_DEMURRAGE_TYPE:
                    case STA_DOC_PROCESS_ON_TIME_FULL:
                    case STA_DOC_NOT_PROCESS_FULL:
                    case STA_DOC_DEMURRAGE_FULL:
                    case LIST_DOCUMENT_DEPARTMENT:
                        switch (mOfficeView.getTypeHomeListDocument()) {
                            case TYPE_HOME_LIST_DOCUMENT_COMING:
                                for (int i = 0; i < mJsonArray.length(); i++) {
                                    mJsonObject = mJsonArray.getJSONObject(i);
                                    long docReceiptId = mJsonObject.getLong(DOC_RECEIPT_ID);
                                    String receiveDate = mJsonObject.getString(RECEIVE_DATE);
                                    String numberOfSymbol = mJsonObject.getString(NUMBER_OF_SYMBOL);
                                    String docNumberFull = mJsonObject.getString(DOC_NUMBER_FULL);
                                    String briefContent = mJsonObject.getString(BRIEF_CONTENT);
                                    arrDocumentLookUp.add(new ItemsDocumentLookUp(docReceiptId, receiveDate, numberOfSymbol, docNumberFull, briefContent, mOfficeView.getTypeHomeListDocument()));
                                }
                                break;
                            case TYPE_HOME_LIST_DOCUMENT_SENT:
                                for (int i = 0; i < mJsonArray.length(); i++) {
                                    mJsonObject = mJsonArray.getJSONObject(i);
                                    long docSendId = mJsonObject.getLong(DOC_SEND_ID);
                                    String publishNumber = mJsonObject.getString(PUBLISH_NUMBER);
                                    String publishDate = mJsonObject.getString(PUBLISH_DATE);
                                    String briefContent = mJsonObject.getString(BRIEF_CONTENT);
                                    arrDocumentLookUp.add(new ItemsDocumentLookUp(docSendId, publishDate, null, publishNumber, briefContent, mOfficeView.getTypeHomeListDocument()));
                                }
                                break;
                        }
                        break;
                    case LOOKUP_COMING:
                        for (int i = 0; i < mJsonArray.length(); i++) {
                            mJsonObject = mJsonArray.getJSONObject(i);
                            long docReceiptId = mJsonObject.getLong(DOC_RECEIPT_ID);
                            String receiveDate = mJsonObject.getString(RECEIVE_DATE);
                            String numberOfSymbol = mJsonObject.getString(NUMBER_OF_SYMBOL);
                            String docNumberFull = mJsonObject.getString(DOC_NUMBER_FULL);
                            String briefContent = mJsonObject.getString(BRIEF_CONTENT);
                            long DateMilisec = getMilisecDocument(receiveDate, context);
                            if (insert) {
                                if (!isDownloadOffLine()) {
                                    mOfficeView.getmSqLite().QueryData("INSERT OR REPLACE INTO '" + LOOKUP_COMING + "'" +
                                            "(docReceiptId , total, receiveDate, numberOfSymbol ,docNumberFull, briefContent, datemilisec) VALUES('" +
                                            docReceiptId + "','" + getNumberOfPage() + "','" + receiveDate + "','" + numberOfSymbol + "', '" +
                                            docNumberFull.replaceAll("'", "") + "', '" + specialValid(briefContent) + "', '" + DateMilisec + "')");
                                }
                            } else {
                                arrDocumentLookUp.add(new ItemsDocumentLookUp(docReceiptId, receiveDate, numberOfSymbol, docNumberFull, replaceStringSpecicalCharacterFromSQL(briefContent), LOOKUP_COMING));
                            }
                        }
                        break;
                    case LOOKUP_SENT:
                        for (int i = 0; i < mJsonArray.length(); i++) {
                            mJsonObject = mJsonArray.getJSONObject(i);
                            long docSendId = mJsonObject.getLong(DOC_SEND_ID);
                            String publishNumber = mJsonObject.getString(PUBLISH_NUMBER);
                            String publishDate = mJsonObject.getString(PUBLISH_DATE);
                            String briefContent = mJsonObject.getString(BRIEF_CONTENT);
                            // long DateMilisec = getMilisecDocument(publishDate, context);
                            long DateMilisec = getMilisecondDay(publishDate, FORMATDATE);
                            if (insert) {
                                if (!isDownloadOffLine()) {
                                    mOfficeView.getmSqLite().QueryData("INSERT OR REPLACE INTO '" + LOOKUP_SENT + "'" +
                                            "(docSendId, total, publishDate, publishNumber, briefContent, datemilisec) VALUES('" +
                                            docSendId + "','" + getNumberOfPage() + "','" + publishDate + "','" + publishNumber + "', '" + specialValid(briefContent) + "', '" + DateMilisec + "')");
                                }
                            } else {
                                arrDocumentLookUp.add(new ItemsDocumentLookUp(docSendId, publishDate, null, publishNumber, specialValid(briefContent), LOOKUP_SENT));
                            }
                        }
                        break;
                    case LOOKUP_INTERNAL:
                        for (int i = 0; i < mJsonArray.length(); i++) {
                            mJsonObject = mJsonArray.getJSONObject(i);
                            long docLocalId = mJsonObject.getLong(DOC_LOCAL_ID);
                            String docNumber = mJsonObject.getString(DOC_NUMBER);
                            String createDate = mJsonObject.getString(CREATE_DATE_DOC);
                            String briefContent = mJsonObject.getString(BRIEF_CONTENT);
                            long DateMilisec = getMilisecDocument(createDate, context);
                            if (insert) {
                                if (!isDownloadOffLine()) {
                                    mOfficeView.getmSqLite().QueryData("INSERT OR REPLACE INTO '" + LOOKUP_INTERNAL + "'" +
                                            "(docLocalId, total, createDate, docNumber, briefContent, datemilisec) VALUES('" +
                                            docLocalId + "','" + getNumberOfPage() + "','" + createDate + "','" + docNumber + "', '" + specialValid(briefContent) + "', '" + DateMilisec + "')");
                                }
                            } else {
                                arrDocumentLookUp.add(new ItemsDocumentLookUp(docLocalId, createDate, null, docNumber, specialValid(briefContent), LOOKUP_INTERNAL));
                            }
                        }
                        break;
                    case STA_DOC_PROCESS_ON_TIME_FULL_ARISE:
                        for (int i = 0; i < mJsonArray.length(); i++) {
                            mJsonObject = mJsonArray.getJSONObject(i);
                            String content = mJsonObject.getString(SCHEDULE_CONTENT);
                            String workCode = mJsonObject.getString(WORK_CODE);
                            String title = mJsonObject.getString(TITLE);
                            String createDate = mJsonObject.getString(CREATE_DATE);
                            long workAriseId = mJsonObject.getLong(WORK_ARISE_ID);
                            String orgCreateName = mJsonObject.getString(ORG_CREATE_NAME);
                            long DateMilisec = getMilisecDocument(createDate, context);
                            arrDocumentLookUp.add(new ItemsDocumentLookUp(workAriseId, createDate, workCode, title, specialValid(content), LOOKUP_COMING));
                        }
                        break;
                    case WORK_ARISE:
                        for (int i = 0; i < mJsonArray.length(); i++) {
                            mJsonObject = mJsonArray.getJSONObject(i);
                            String content = mJsonObject.getString(SCHEDULE_CONTENT);
                            String workCode = mJsonObject.getString(WORK_CODE);
                            String title = mJsonObject.getString(TITLE);
                            String createDate = mJsonObject.getString(CREATE_DATE);
                            long workAriseId = mJsonObject.getLong(WORK_ARISE_ID);
                            String orgCreateName = mJsonObject.getString(ORG_CREATE_NAME);
                            long DateMilisec = getMilisecDocument(createDate, context);
                            if (insert) {
                                if (!isDownloadOffLine()) {
                                    mOfficeView.getmSqLite().QueryData("INSERT OR REPLACE INTO '" + WORK_ARISE + "'" +
                                            "(workAriseId, total, createDate, workCode, content, title, orgCreateName, datemilisec) VALUES('" +
                                            workAriseId + "','" + getNumberOfPage() + "','" + createDate + "','" + workCode + "', '" + specialValid(content) + "', '" + specialValid(title) + "', '" + specialValid(orgCreateName) + "','" + DateMilisec + "')");
                                }
                            } else {
                                arrDocumentLookUp.add(new ItemsDocumentLookUp(workAriseId, createDate, workCode, title, specialValid(content), LOOKUP_COMING));
                            }
                        }


                        break;
                    default:
                        break;
                }
            }
        } catch (JSONException e) {
            Log.d("VinhCNLog: ", "Non have lookup document");
            e.printStackTrace();
        }

    }


    protected void upDateSliderMenu(String s, boolean Reload) {
        gson = new Gson();
        GsonSlider mSliderObject = gson.fromJson(s, GsonSlider.class);
        root = TreeNode.root();
        TreeMenu.MenuTreeItem nodeItem;
        TreeMenu.MenuTreeItem nodeItem_Account;
        int underLineHeight = 1;
        nodeItem = new TreeMenu.MenuTreeItem("Thiết lập người dùng", R.drawable.user_x1, 0, "config_user", 0, false, 5, true, false, "#959595", true);
//        root.addChild(new TreeNode(new TreeMenu.MenuTreeItem("Thiết lập người dùng", R.drawable.user_x1, 0, null, 0, false, 5, true, false, "#959595", true)).setViewHolder(new TreeMenu(context)));
        Parent = new TreeNode(nodeItem).setViewHolder(new TreeMenu(context));
        nodeItem_Account = new TreeMenu.MenuTreeItem("AcountMenu",
                android.R.color.transparent, 0,
                "0", 0,
                false, underLineHeight,
                false, true,
                "#121212",
                false);

        Treelevel_2 = new TreeNode(nodeItem_Account).setViewHolder(new TreeMenu(context));
        Parent.addChild(Treelevel_2);
        root.addChild(Parent);
        for (int i = 0; i < mSliderObject.getData().size(); i++) {
            GsonSlider.DataBean mDataBean = mSliderObject.getData().get(i);
            if ((mSliderObject.getData().size() - i == 1)) {
                underLineHeight = 5;
            }
            switch (mDataBean.getPageCode()) {
                case CODE_HOME:
                    mOfficeView.setHomeTitle(mDataBean.getPageName());
                    mOfficeView.setMainTitle(mDataBean.getPageName());
                    nodeItem = new TreeMenu.MenuTreeItem(mDataBean.getPageName(), R.drawable.nav_home_x1, mDataBean.getPagePriority(), mDataBean.getPageCode(), 0, false, underLineHeight, true, false, "#121212", false);
                    break;
                case CODE_SCHEDULE:
                    nodeItem = new TreeMenu.MenuTreeItem(mDataBean.getPageName(), R.drawable.nav_calendar_x1, mDataBean.getPagePriority(), mDataBean.getPageCode(), 0, false, underLineHeight, true, false, "#121212", false);
                    break;
                case CODE_PROCESS:
                    nodeItem = new TreeMenu.MenuTreeItem(mDataBean.getPageName(), R.drawable.nav_process_x1, mDataBean.getPagePriority(), mDataBean.getPageCode(), 0, false, underLineHeight, true, false, "#121212", false);
                    break;
                case CODE_LOOKUP:
                    nodeItem = new TreeMenu.MenuTreeItem(mDataBean.getPageName(), R.drawable.nav_search_x1, mDataBean.getPagePriority(), mDataBean.getPageCode(), 0, false, underLineHeight, true, false, "#121212", false);
                    break;
                case CODE_STATISTICAL:
                    nodeItem = new TreeMenu.MenuTreeItem(mDataBean.getPageName(), R.drawable.nav_stats_x1, mDataBean.getPagePriority(), mDataBean.getPageCode(), 0, false, underLineHeight, true, false, "#121212", false);
                    break;
                default:
                    nodeItem = new TreeMenu.MenuTreeItem(mDataBean.getPageName(), R.drawable.ic_play_arrow_white_24dp, mDataBean.getPagePriority(), mDataBean.getPageCode(), 0, false, underLineHeight, true, false, "#121212", false);
                    break;
            }
            Parent = new TreeNode(nodeItem).setViewHolder(new TreeMenu(context));
            root.addChild(Parent);
            underLineHeight = 1;
            if (mDataBean.getLabels() != null) {
                for (int j = 0; j < mDataBean.getLabels().size(); j++) {
                    GsonSlider.DataBean.LabelsBeanX mLabelsBeanX = mDataBean.getLabels().get(j);
                    if (mLabelsBeanX.getLabels() != null && mLabelsBeanX.getLabels().size() != 0) {
                        nodeItem = new TreeMenu.MenuTreeItem(mLabelsBeanX.getLabelName(), android.R.color.transparent, 0, mLabelsBeanX.getLabelCode(), mLabelsBeanX.getLabelCount(), false, underLineHeight, true, true, "#121212", false);
                        Treelevel_2 = new TreeNode(nodeItem).setViewHolder(new TreeMenu(context));
                        Parent.addChild(Treelevel_2);
                        for (int k = 0; k < mLabelsBeanX.getLabels().size(); k++) {
                            GsonSlider.DataBean.LabelsBeanX.LabelsBean mLabelsBean = mLabelsBeanX.getLabels().get(k);
                            addtoArrCode(mLabelsBean.getLabelCode());
                            nodeItem = new TreeMenu.MenuTreeItem(mLabelsBean.getLabelName(), 0, 0, mLabelsBean.getLabelCode(), mLabelsBean.getLabelCount(), false, underLineHeight, false, false, "#5C5C5C", true);
                            Treelevel_3 = new TreeNode(nodeItem).setViewHolder(new TreeMenu(context));
                            Treelevel_2.addChild(Treelevel_3);
                        }
                    } else {
                        if (!mLabelsBeanX.getLabelCode().equals("0502") && !mLabelsBeanX.getLabelCode().equals("0503")) {
                            nodeItem = new TreeMenu.MenuTreeItem(mLabelsBeanX.getLabelName(), android.R.color.transparent, 0, mLabelsBeanX.getLabelCode(), mLabelsBeanX.getLabelCount(), false, underLineHeight, false, true, "#121212", false);
                            Treelevel_2 = new TreeNode(nodeItem).setViewHolder(new TreeMenu(context));
                            Parent.addChild(Treelevel_2);
                        }

                    }
                }
            }
        }

//        root.addChild(new TreeNode(new TreeMenu.MenuTreeItem("Hướng dẫn", R.drawable.question_x1, 0, "guide", 0, false, underLineHeight, true, false, "#959595", true)).setViewHolder(new TreeMenu(context)));
//        root.addChild(new TreeNode(new TreeMenu.MenuTreeItem("Góp ý", R.drawable.nav_feedback_x1, 0, "feedback", 0, false, underLineHeight, true, false, "#959595", true)).setViewHolder(new TreeMenu(context)));
        root.addChild(new TreeNode(new TreeMenu.MenuTreeItem("Thông tin ứng dụng", R.drawable.nav_infors_x1, 0, "infor", 0, false, underLineHeight, true, false, "#959595", true)).setViewHolder(new TreeMenu(context)));
        root.addChild(new TreeNode(new TreeMenu.MenuTreeItem(nULL_STRING, R.drawable.nav_infors_x1, 0, "verson", 0, false, underLineHeight, true, false, "#959595", true)).setViewHolder(new TreeMenu(context)));
        AndroidTreeView tView = new AndroidTreeView(context, root);
        tView.setDefaultAnimation(true);
        mOfficeView.SetTreeList(tView, FORWARD, root);
        tView.setDefaultContainerStyle(R.style.TreeNodeStyleCustom);
        mOfficeView.closeProgress();
    }


    public void addtoArrCode(final String urlNaForGetListOffLine) {
        boolean CheckAddToList = false;
        if (!urlNaForGetListOffLine.equals(nULL_STRING) && urlNaForGetListOffLine != null) {
            // add devide "/" or ":"
//            if (urlNaForGetListOffLine.contains("/") || urlNaForGetListOffLine.contains(":")) {
            if (arrUrlNa.size() != 0) {
                for (int i = 0; i < arrUrlNa.size(); i++) {
                    String url_na = arrUrlNa.get(i).toString();
                    if (!url_na.equals(urlNaForGetListOffLine)) {
                        CheckAddToList = true;
                    } else {
                        CheckAddToList = false;
                        break;
                    }
                }
                if (CheckAddToList) {
                    arrUrlNa.add(urlNaForGetListOffLine);
                }
            } else {
                arrUrlNa.add(urlNaForGetListOffLine);
            }
//            }
        }
    }

    /*=================================
    next to position of array url na for get list of this url_na
===================================*/

    private void eventReadJsonOffLine(final int position) {
        if (isNetworkAvailable(context)) {
            if (!isDownloadOffLine()) {
                if (position < arrUrlNa.size()) {
                    urlNaForOffLine = arrUrlNa.get(position);
                    for (int i = 0; i < arrUrlNa.size(); i++) {
                        mOfficeView.createDatabaseOffline(arrUrlNa.get(i).toString());

                    }
                    new ReadJsonDocumentToOffLine().execute(getLink() + URL_CENTER_6_1);
                }
            }

        }
    }


    public JSONObject addJsonForRequestTaskList() {
        Calendar mCalendar = Calendar.getInstance();
        String dateLast = mSimpleDateFormat.format(mCalendar.getTime()) + "";
        Calendar mCalendarFirstDay = Calendar.getInstance();
        mCalendarFirstDay.add(Calendar.MONTH, -1);
//        String dateFirst = mSimpleDateFormat.format(mCalendarFirstDay.getTime()) + "";
        int Statistic = mOfficeView.getStatisticType();
        JSONObject mJsonObject = new JSONObject();
        try {
            mJsonObject.put(MODULE, MODULE_LOOKUP_DOCUMENT);
            mJsonObject.put(NEOTYPE, TYPE_TASK_LIST);
            JSONObject mData = new JSONObject();
            mData.put(FROM_DATE, getMilisecondDay(DATE_1_YEARS_FOR_TEST));
            mData.put(TO_DATE, getMilisecondDay(dateLast));
            mData.put(STATISTIC_CODE, Statistic);
            mData.put(ROW_PER_PAGE, LimitPager);
            mData.put(NUM_PAGE, mOfficeView.getPositionPage());
            mJsonObject.put(DATA, mData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        setDefaults(REQUEST_STATIS_LIST, mJsonObject.toString(), context);
        return mJsonObject;
    }

    private void TotalOfflineLookupComingList(String TableName) {
        Cursor cursor = mOfficeView.getmSqLite().GetData("select * from " + TableName);
        setNumberOfPage(cursor.getCount());
        eventGetDataScreen(TableName);
    }

    /*===========================================================
    get json document in background and set it for sqllite
=============================================================*/

    class ReadJsonDocumentToOffLine extends AsyncTask<String, Integer, String> {

        @SuppressLint("LongLogTag")
        @Override
        protected String doInBackground(String... params) {
            JSONObject mJsonObject = new JSONObject();
            try {
                mJsonObject.put(MODULE, MODULE_PROCESSING_WORKING);
                mJsonObject.put(NEOTYPE, TYPE_DOCUMENT_LIST);
                JSONObject mJsonData = new JSONObject();
                mJsonData.put(LABLE_URL_PARAM, urlNaForOffLine);
                mJsonData.put(ROW_PER_PAGE, 1000);
                mJsonData.put(NUM_PAGE, 1);
                mJsonObject.put(DATA, mJsonData.toString());
                String JsonResponse = eventPostRequest(getModuleInfor(MODULE_PROCESSING_WORKING, context), mJsonObject.toString(), getUserid(), getPass());
                Log.d("VinhCNLogRequestOffLine: ", mJsonObject + "");
                JSONObject mJsonObjectFullData = new JSONObject(JsonResponse);
                JSONArray mJsonArray = mJsonObjectFullData.getJSONArray(DATA);
                for (int i = 0; i < mJsonArray.length(); i++) {
                    if (isDownloadOffLine()) {
                        return CANCEL_DOWNLOAD_OFFLINE;
                    } else {
                        JSONObject mJsonObjectReponse = mJsonArray.getJSONObject(i);
                        Log.d(TAG, mJsonObjectReponse.toString());
                        String mTitle = mJsonObjectReponse.getString(TITLE);
                        String mNgayNhan = mJsonObjectReponse.getString(RECEIVE_DATE);
                        String mSumary = mJsonObjectReponse.getString(SUMARY);
                        String DocumentID = mJsonObjectReponse.getString(JOB_ID);
                        String resourceCodeId = mJsonObjectReponse.getString(RESOURCECODEID);
                        boolean oldornewDocument = mJsonObjectReponse.getBoolean(NEW_OLD);
                        boolean seenDocument = mJsonObjectReponse.getBoolean(VIEWED);
                        int jobType = mJsonObjectReponse.getInt(JOB_TYPE);
                        String lableURL = mJsonObjectReponse.getString(LABLE_URL_PARAM);
//                        long DateMilisec = 0;
//                        if (getDefaults(URL_CENTER, OfficalActivity.this).equals(URL_CENTER_6_1)) {
//                            DateMilisec = getMilisecondDay(mNgayNhan, FORMATP_DATE_DOCUMENT_6_1);
//                        } else {
//                            DateMilisec = getMilisecondDay(mNgayNhan, FORMATP_DATE_DOCUMENT_6_2);
//                        }
                        if (isDownloadOffLine()) {
                            return CANCEL_DOWNLOAD_OFFLINE;
                        } else {
                            mOfficeView.getmSqLite().QueryData("INSERT OR REPLACE INTO '" + "M" + getOnlyNumerics(lableURL) + "'(urlna,total,pagenumber,title,ngaynhan,sumary,documentID,resourceCodeId,oldornewdocument,seendocument,jobtype,datemilisec) VALUES('" + lableURL + "','" + mOfficeView.getNumberOfPage() + "','" + mOfficeView.getPositionPage() + "', '" +
                                    specialValid(Html.fromHtml(mTitle).toString()) + "', '" + mNgayNhan + "', '" + specialValid(Html.fromHtml(mSumary).toString()) + "', '" + DocumentID + "', '" + resourceCodeId + "','" + oldornewDocument + "','" + seenDocument + "','" + jobType + "','" + getMilisecDocument(mNgayNhan, context) + "')");
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            final int indexURLNA = arrUrlNa.indexOf(urlNaForOffLine) + 1;
            Log.d(TAG, indexURLNA + "");
            if (s == null) {
                if (!isDownloadOffLine()) {
                    eventReadJsonOffLine(indexURLNA);
                }

            }
            super.onPostExecute(s);
        }
    }

    public JSONObject addJsonForRequestDocumentLookup(String MODULEs, String mType) {
        JSONObject mJsonObject = new JSONObject();
        try {
            mJsonObject.put(MODULE, MODULEs);
            mJsonObject.put(NEOTYPE, mType);
            JSONObject mJsonObjectData = new JSONObject();
            mJsonObjectData.put(ROW_PER_PAGE, LimitPager);
            mJsonObjectData.put(NUM_PAGE, mOfficeView.getPositionPage());
//            mJsonObjectData.put(SEARCH_CRITERIA, nULL_STRING);
            mJsonObject.put(DATA, mJsonObjectData.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mJsonObject;
    }


    public JSONObject addJsonForRequestHomeListDocument(String TYPE) {
        JSONObject mJsonObject = new JSONObject();
        try {
            mJsonObject.put(MODULE, MODULE_LOOKUP_DOCUMENT);
            mJsonObject.put(NEOTYPE, TYPE);
            JSONObject mJsonObjectData = new JSONObject();
            mJsonObjectData.put(FROM_DATE, getMilisecondDay(mOfficeView.getDateFirst()));
            mJsonObjectData.put(TO_DATE, getMilisecondDay(mOfficeView.getDateLast()));
            mJsonObjectData.put(ORGANIZATION_ID, mOfficeView.getOganizationID());
            mJsonObjectData.put(STATISTIC_CODE, mOfficeView.getStatisticType());
            mJsonObjectData.put(ROW_PER_PAGE, LimitPager);
            mJsonObjectData.put(NUM_PAGE, mOfficeView.getPositionPage());
            mJsonObject.put(DATA, mJsonObjectData.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mJsonObject;
    }


    public void eventChangeToLookupComing(int position, boolean isScroll) {
        if (isNetworkAvailable(context)) {
            RequestDocumentLookup();
        } else {
            if (isScroll) {
                getListDocumentLookupComing((position - 1) * getLimitPager());
            } else {
                mOfficeView.changeUIDocument(getLookupScreen(), null);
            }
        }
    }


    class ReadNumberOfPage extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... params) {
            JSONObject mJsonObject = new JSONObject();
            try {
                mJsonObject.put(MODULE, MODULE_PROCESSING_WORKING);
                mJsonObject.put(NEOTYPE, TYPE_TOTAL_DOCUMENT_LIST);
                JSONObject mJsonData = new JSONObject();
                mJsonData.put(LABLE_URL_PARAM, urlNA);
                mJsonData.put(ROW_PER_PAGE, 1);
                mJsonObject.put(DATA, mJsonData.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return eventPostRequest(getModuleInfor(MODULE_PROCESSING_WORKING, context), mJsonObject.toString(), getUserid(), getPass());
        }

        @Override
        protected void onPostExecute(String s) {
            int totalItems = 0;
            try {
                JSONObject mJsonObject = new JSONObject(s);
                JSONObject mJsonObjectFullData = mJsonObject.getJSONObject(DATA);
                totalItems = mJsonObjectFullData.getInt(COUNT);
            } catch (JSONException e) {
                mOfficeView.closeProgress();
                e.printStackTrace();
            }
            setNumberOfPage(totalItems);
//            NumberOfPage = totalItems;
            eventGetDataScreen(DOCUMENT_FRAGMENT_TAG);
//            changeUIDocment(DOCUMENT_FRAGMENT_TAG, null);
            super.onPostExecute(s);
        }
    }

    public void getListDocumentLookupComing(int position) {
        Cursor cursor;
        if (!isDownloadOffLine()) {
            switch (mOfficeView.getLookupScreen()) {
                case WORK_ARISE:
                    cursor = mOfficeView.getmSqLite().GetData("SELECT * FROM " + WORK_ARISE + " ORDER BY  datemilisec DESC, workCode DESC, content DESC LIMIT '" + position + "', '" + getLimitPager() + "'");
//                    "(workAriseId INTEGER primary key, total INTEGER, createDate VARCHAR, workCode VARCHAR, content  VARCHAR, title VARCHAR, orgCreateName VARCHAR, datemilisec integer)"
                    while (cursor.moveToNext()) {
                        long workAriseId = cursor.getLong(0);
                        String createDate = cursor.getString(2);
                        String workCode = cursor.getString(3);
                        String content = cursor.getString(4);
                        String title = cursor.getString(5);
                        arrDocumentLookUp.add(new ItemsDocumentLookUp(workAriseId, createDate, title, workCode, specialValid(content), WORK_ARISE));
                    }
                    break;
                case LOOKUP_COMING:
                    cursor = mOfficeView.getmSqLite().GetData("SELECT * FROM " + LOOKUP_COMING + " ORDER BY  datemilisec DESC, docNumberFull DESC, briefContent DESC LIMIT '" + position + "', '" + getLimitPager() + "'");
//                cursor = mSqLite.GetData("SELECT * FROM '" + LOOKUP_COMING + "' LIMIT '" + position + "', '" + getLimitPager() + "'");
//                cursor = mSqLite.GetData("SELECT * FROM " + LOOKUP_COMING + " ORDER BY docReceiptId DESC LIMIT '" + position + "', '" + getLimitPager() + "'");
                    while (cursor.moveToNext()) {
                        long docReceiptId = cursor.getLong(0);
                        String receiveDate = cursor.getString(2);
                        String numberOfSymbol = cursor.getString(3);
                        String docNumberFull = cursor.getString(4);
                        String briefContent = cursor.getString(5);
                        arrDocumentLookUp.add(new ItemsDocumentLookUp(docReceiptId, receiveDate, numberOfSymbol, docNumberFull, replaceStringSpecicalCharacterFromSQL(briefContent), LOOKUP_COMING));
                        Log.d("VinhCNLogSQLChar", position + docReceiptId + receiveDate + numberOfSymbol + docNumberFull + briefContent);
                    }
                    break;
                case LOOKUP_SENT:
                    cursor = mOfficeView.getmSqLite().GetData("SELECT * FROM " + LOOKUP_SENT + " ORDER BY  datemilisec DESC, publishNumber DESC, briefContent DESC LIMIT '" + position + "', '" + getLimitPager() + "'");
//                cursor = mSqLite.GetData("SELECT * FROM '" + LOOKUP_SENT + "' LIMIT '" + position + "', '" + getLimitPager() + "'");
//                cursor = mSqLite.GetData("SELECT * FROM " + LOOKUP_SENT + " ORDER BY docSendId DESC LIMIT '" + position + "', '" + getLimitPager() + "'");
                    while (cursor.moveToNext()) {
                        long docSendId = cursor.getLong(0);
                        String publishNumber = cursor.getString(2);
                        String publishDate = cursor.getString(3);
                        String briefContent = cursor.getString(4);
                        arrDocumentLookUp.add(new ItemsDocumentLookUp(docSendId, publishNumber, null, publishDate, replaceStringSpecicalCharacterFromSQL(briefContent), LOOKUP_SENT));
                    }
                    break;
                case LOOKUP_INTERNAL:
                    cursor = mOfficeView.getmSqLite().GetData("SELECT * FROM " + LOOKUP_INTERNAL + " ORDER BY  datemilisec DESC, docNumber DESC, briefContent DESC LIMIT '" + position + "', '" + getLimitPager() + "'");
//                cursor = mSqLite.GetData("SELECT * FROM '" + LOOKUP_INTERNAL + "' LIMIT '" + position + "', '" + getLimitPager() + "'");
//                cursor = mSqLite.GetData("SELECT * FROM " + LOOKUP_INTERNAL + " ORDER BY docLocalId DESC LIMIT '" + position + "', '" + getLimitPager() + "'");
                    while (cursor.moveToNext()) {
                        long docSendId = cursor.getLong(0);
                        String publishNumber = cursor.getString(2);
                        String publishDate = cursor.getString(3);
                        String briefContent = cursor.getString(4);
                        arrDocumentLookUp.add(new ItemsDocumentLookUp(docSendId, publishNumber, null, publishDate, replaceStringSpecicalCharacterFromSQL(briefContent), LOOKUP_INTERNAL));
                    }
                    break;
                default:
                    break;
            }
        }
        if (arrDocumentLookUp.size() == 0) {
            mOfficeView.visibleNotConnection(true);
        } else {
            mOfficeView.visibleNotConnection(false);
        }
        if (position >= 1) {
            if (adapterDocumentLookup != null) {
                adapterDocumentLookup.notifyDataSetChanged();
            }
        } else {
            adapterDocumentLookup = new DocumentLookupAdapter(context, arrDocumentLookUp);
            mOfficeView.setDocLookupHome(adapterDocumentLookup);

        }
        mOfficeView.closeProgress();
    }

    public void eventGetHomeListDocument(int TotalDocument, String isScreen, int statisticType, long OganizationID) {
        if (isScreen.equals(LIST_DOCUMENT_DEPARTMENT)) {
            mOfficeView.setLookupScreen(LIST_DOCUMENT_DEPARTMENT);
            mOfficeView.setTypeHomeListDocument(TYPE_HOME_LIST_DOCUMENT_COMING);
        }
        if (isScreen.equals(STA_DOC_PROCESS_ON_TIME_FULL_ARISE)){
            mOfficeView.setLookupScreen(STA_DOC_PROCESS_ON_TIME_FULL_ARISE);
            mOfficeView.setTypeHomeListDocument(TYPE_STA_LIST_WORK_ARISE);
        }
        mOfficeView.setOganizationID(OganizationID);
        mOfficeView.setProcesserID(0);
        mOfficeView.showProgress();
        mOfficeView.setStatisticType(statisticType);
        if (TotalDocument != 0) {
            mOfficeView.setNumberOfPage(TotalDocument);
        } else {
            mOfficeView.setNumberOfPage(0);
        }
        mOfficeView.changeUIDocument(isScreen, null);

    }

    @Override
    public void eventGetHomeListDocument(int TotalDocument, String isScreen, int statisticType, long OganizationID, long processerID) {
        if (isScreen.equals(LIST_DOCUMENT_DEPARTMENT)) {
            mOfficeView.setLookupScreen(LIST_DOCUMENT_DEPARTMENT);
            mOfficeView.setTypeHomeListDocument(TYPE_HOME_LIST_DOCUMENT_COMING);
        }
        if (isScreen.equals(STA_DOC_PROCESS_ON_TIME_FULL_ARISE)){
            mOfficeView.setLookupScreen(STA_DOC_PROCESS_ON_TIME_FULL_ARISE);
            mOfficeView.setTypeHomeListDocument(TYPE_STA_LIST_WORK_ARISE);
        }
        mOfficeView.setProcesserID(processerID);
        mOfficeView.showProgress();
        mOfficeView.setStatisticType(statisticType);
        if (TotalDocument != 0) {
            mOfficeView.setNumberOfPage(TotalDocument);
        } else {
            mOfficeView.setNumberOfPage(0);
        }
        mOfficeView.changeUIDocument(isScreen, null);
    }


    public void eventGetHomeListDocument(int TotalDocument, String isScreen, String statisticType, long OganizationID) {
        if (isScreen.equals(LIST_DOCUMENT_DEPARTMENT)) {
            mOfficeView.setLookupScreen(LIST_DOCUMENT_DEPARTMENT);
            mOfficeView.setTypeHomeListDocument(TYPE_HOME_LIST_DOCUMENT_COMING);
        }
        mOfficeView.setOganizationID(OganizationID);
        mOfficeView.showProgress();
        mOfficeView.setStatisticTypeHotLine(statisticType);
        if (TotalDocument != 0) {
            mOfficeView.setNumberOfPage(TotalDocument);
        } else {
            mOfficeView.setNumberOfPage(0);
        }
        mOfficeView.changeUIDocument(isScreen, null);
    }


    public void eventChangeToDocument(final int position, final String numberItemOfPager, String mURLNA, boolean isScroll) {
        mOfficeView.setPositionPage(position);
        if (isNetworkAvailable(context)) {
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
            RequestJsonDocument();
//                }
//            });
        } else {
            if (isScroll) {
                getListDocumentSQLite((position - 1) * getLimitPager(), mURLNA);
            } else {
                mOfficeView.changeUIDocument(DOCUMENT_FRAGMENT_TAG, urlNA);
            }

        }

    }


    //before change to v7
    protected void upDateSliderMenuBeforeChangev7(String s, boolean Reload) {
        gson = new Gson();
        arrHeander = new ArrayList<>();
        List<ChildMenu> arrChild = null;
        GsonSlider mSliderObject = gson.fromJson(s, GsonSlider.class);
        for (int i = 0; i < mSliderObject.getData().size(); i++) {
            GsonSlider.DataBean mDataBean = mSliderObject.getData().get(i);
            switch (mDataBean.getPageCode()) {
                case CODE_HOME:
                    arrHeander.add(new HeaderMenu(mDataBean.getPageName(), arrChild, R.drawable.slider_home_icon, mDataBean.getPagePriority(), mDataBean.getPageCode(), 0, false));
                    break;
                case CODE_SCHEDULE:
                    arrHeander.add(new HeaderMenu(mDataBean.getPageName(), null, R.drawable.item_lich, mDataBean.getPagePriority(), mDataBean.getPageCode(), 0, false));
                    break;
                case CODE_PROCESS:
                    arrHeander.add(new HeaderMenu(mDataBean.getPageName(), null, R.drawable.item_xlcv, mDataBean.getPagePriority(), mDataBean.getPageCode(), 0, false));
                    break;
                case CODE_LOOKUP:
                    arrHeander.add(new HeaderMenu(mDataBean.getPageName(), null, R.drawable.item_tracuu, mDataBean.getPagePriority(), mDataBean.getPageCode(), 0, false));
                    break;
                case CODE_STATISTICAL:
                    arrHeander.add(new HeaderMenu(mDataBean.getPageName(), null, R.drawable.slider_statistical_icon, mDataBean.getPagePriority(), mDataBean.getPageCode(), 0, false));
                    break;
                default:
                    arrHeander.add(new HeaderMenu(mDataBean.getPageName(), null, R.drawable.ic_play_arrow_white_24dp, mDataBean.getPagePriority(), mDataBean.getPageCode(), 0, false));
                    break;
            }
            if (mDataBean.getLabels() != null) {
                for (int j = 0; j < mDataBean.getLabels().size(); j++) {
                    arrChild = new ArrayList<>();
                    GsonSlider.DataBean.LabelsBeanX mLabelsBeanX = mDataBean.getLabels().get(j);
                    switch (mLabelsBeanX.getLabelCode()) {
                        case CODE_LOOKUP_COMING:
                        case CODE_LOOKUP_SEND:
                        case CODE_LOOKUP_LOCAL:
                        case CODE_LOOKUP_ONE_WINDOW:
                        case CODE_SCHEDULE_DAY:
                        case CODE_SCHEDULE_WEEK:
                        case CODE_STATISTICAL_COMING:
                        case CODE_STATISTICAL_SEND:
                        case CODE_STATISTICAL_LOCAL:
                        case CODE_STATISTICAL_ONE_WINDOW:
                        case CODE_STATISTICAL_FOLLOW_PERSON:
                        case CODE_STATISTICAL_FOLLOW_DEPEARTMENT:
                            arrHeander.add(new HeaderMenu(mLabelsBeanX.getLabelName(), null, android.R.color.transparent, 0, mLabelsBeanX.getLabelCode(), mLabelsBeanX.getLabelCount(), false));
                            break;
                        default:
                            if (!mLabelsBeanX.getLabelCode().equals(nULL_STRING)) {
                                arrHeander.add(new HeaderMenu(mLabelsBeanX.getLabelName(), null, android.R.color.transparent, 0, mLabelsBeanX.getLabelCode(), mLabelsBeanX.getLabelCount(), false));

                            }
                            break;
                    }
                    if (mLabelsBeanX.getLabels() != null) {
                        for (int k = 0; k < mLabelsBeanX.getLabels().size(); k++) {
                            GsonSlider.DataBean.LabelsBeanX.LabelsBean mLabelsBean = mLabelsBeanX.getLabels().get(k);
                            addtoArrCode(mLabelsBean.getLabelCode());
                            arrChild.add(new ChildMenu(mLabelsBean.getLabelName(), 0, 0, mLabelsBean.getLabelCode(), mLabelsBean.getLabelCount()));
                        }
                        if (mLabelsBeanX.getLabels().size() != 0) {
                            arrHeander.add(new HeaderMenu(mLabelsBeanX.getLabelName(), arrChild, R.drawable.ic_play_arrow_white_24dp, 0, mLabelsBeanX.getLabelCode(), mLabelsBeanX.getLabelCount(), false));
                        }

                    }
                }
            }
        }
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        } else {
            mOfficeView.setLayoutManagerRecycle();
            adapter = new SlideMenuAdapter(arrHeander, context);
            mOfficeView.setAdapterSlider(adapter);
        }
        mOfficeView.closeProgress();
    }
    /*===========================
        get json slider menu_main
    =============================*/
//// TODO: 10/11/2017  doing new function
//    class ReadJsonSlider extends AsyncTask<String, Integer, String> {
//        @Override
//        protected String doInBackground(String... params) {
//            String resuilt = "";
//            JSONObject mJsonObject = new JSONObject();
//            try {
//                mJsonObject.put(MODULE, MODULE_PROCESSING_WORKING);
//                mJsonObject.put(NEOTYPE, TYPE_SLIDER_MENU_LIST);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            resuilt = eventPostRequest(getModuleInfor(MODULE_PROCESSING_WORKING, context), mJsonObject.toString(), getUserid(), getPass());
//            BooleanDownload = params[0];
//            return resuilt;
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
////            Toast.makeText(OfficalActivity.this, s + "", Toast.LENGTH_SHORT).show();
//            //// TODO: 4/21/2017 close
////            String sx = "[{\"page_urlna\":\"\",\"page_url\":\"/group/mobile/trang-chu\",\"page_itemicon\":\"item-tc\",\"page_priority\":1,\"page_name\":\"Trang chủ\"},{\"page_urlna\":\"\",\"page_url\":\"/group/mobile/lich-chinh-thuc\",\"page_itemicon\":\"item-lich\",\"page_priority\":2,\"page_name\":\"Lịch công tác\"},{\"page_urlna\":\"\",\"page_url\":\"/group/mobile//xu-ly-cong-viec\",\"page_itemicon\":\"item-xlcv\",\"labels\":[{\"label_count\":0,\"label_name\":\"Văn bản đến (6)\",\"labelCs\":[{\"labelC_count\":0,\"labelC_url\":\"/group/mobile//xu-ly-cong-viec/-/xlcv26m/4/1308\",\"labelC_name\":\"Văn bản chưa xem\",\"labelC_urlna\":\"4/1308\"},{\"labelC_count\":6,\"labelC_url\":\"/group/mobile//xu-ly-cong-viec/-/xlcv26m/4/1309\",\"labelC_name\":\"Văn bản đã xem\",\"labelC_urlna\":\"4/1309\"},{\"labelC_count\":0,\"labelC_url\":\"/group/mobile//xu-ly-cong-viec/-/xlcv26m/4/1311\",\"labelC_name\":\"Văn bản trễ hạn\",\"labelC_urlna\":\"4/1311\"},{\"labelC_count\":6,\"labelC_url\":\"/group/mobile//xu-ly-cong-viec/-/xlcv26m/4/2111\",\"labelC_name\":\"Xử lý\",\"labelC_urlna\":\"4/2111\"},{\"labelC_count\":1,\"labelC_url\":\"/group/mobile//xu-ly-cong-viec/-/xlcv26m/4/1313\",\"labelC_name\":\"Văn bản Báo cáo\",\"labelC_urlna\":\"4/1313\"},{\"labelC_count\":1,\"labelC_url\":\"/group/mobile//xu-ly-cong-viec/-/xlcv26m/4/1314\",\"labelC_name\":\"Văn bản Lưu\",\"labelC_urlna\":\"4/1314\"},{\"labelC_count\":0,\"labelC_url\":\"/group/mobile//xu-ly-cong-viec/-/xlcv26m/4/1312\",\"labelC_name\":\"Văn bản đã xử lý\",\"labelC_urlna\":\"4/1312\"}],\"label_urlna\":\"\",\"label_url\":\"\"},{\"label_count\":0,\"label_name\":\"Văn bản đi (198)\",\"labelCs\":[{\"labelC_count\":0,\"labelC_url\":\"/group/mobile//xu-ly-cong-viec/-/xlcv26m/3/1319\",\"labelC_name\":\"Văn bản chưa xem\",\"labelC_urlna\":\"3/1319\"},{\"labelC_count\":0,\"labelC_url\":\"/group/mobile//xu-ly-cong-viec/-/xlcv26m/3/1320\",\"labelC_name\":\"Văn bản đã xem\",\"labelC_urlna\":\"3/1320\"},{\"labelC_count\":0,\"labelC_url\":\"/group/mobile//xu-ly-cong-viec/-/xlcv26m/3/1321\",\"labelC_name\":\"Văn bản đã xử lý\",\"labelC_urlna\":\"3/1321\"},{\"labelC_count\":0,\"labelC_url\":\"/group/mobile//xu-ly-cong-viec/-/xlcv26m/3/1322\",\"labelC_name\":\"Dự thảo Văn bản\",\"labelC_urlna\":\"3/1322\"},{\"labelC_count\":0,\"labelC_url\":\"/group/mobile//xu-ly-cong-viec/-/xlcv26m/3/1323\",\"labelC_name\":\"Văn bản trình ký\",\"labelC_urlna\":\"3/1323\"}],\"label_urlna\":\"\",\"label_url\":\"\"},{\"label_count\":0,\"label_name\":\"Văn bản nội bộ (3)\",\"labelCs\":[{\"labelC_count\":0,\"labelC_url\":\"/group/mobile//xu-ly-cong-viec/-/xlcv26m/5/1330\",\"labelC_name\":\"Văn bản chưa xem\",\"labelC_urlna\":\"5/1330\"},{\"labelC_count\":3,\"labelC_url\":\"/group/mobile//xu-ly-cong-viec/-/xlcv26m/5/1331\",\"labelC_name\":\"Văn bản đã xem\",\"labelC_urlna\":\"5/1331\"},{\"labelC_count\":0,\"labelC_url\":\"/group/mobile//xu-ly-cong-viec/-/xlcv26m/5/1332\",\"labelC_name\":\"Văn bản sắp hết hạn\",\"labelC_urlna\":\"5/1332\"},{\"labelC_count\":0,\"labelC_url\":\"/group/mobile//xu-ly-cong-viec/-/xlcv26m/5/1333\",\"labelC_name\":\"Văn bản trễ hạn\",\"labelC_urlna\":\"5/1333\"},{\"labelC_count\":0,\"labelC_url\":\"/group/mobile//xu-ly-cong-viec/-/xlcv26m/5/1334\",\"labelC_name\":\"Văn bản đã xử lý\",\"labelC_urlna\":\"5/1334\"}],\"label_urlna\":\"\",\"label_url\":\"\"}],\"page_priority\":3,\"page_name\":\"Xử lý công việc\"},{\"page_urlna\":\"\",\"page_url\":\"\",\"page_itemicon\":\"item-tracuu\",\"labels\":[{\"label_count\":0,\"label_name\":\"Văn bản đến\",\"label_urlna\":\"\",\"label_url\":\"/group/mobile/tra-cuu/van-ban-den\"},{\"label_count\":0,\"label_name\":\"Văn bản đi\",\"label_urlna\":\"\",\"label_url\":\"/group/mobile/tra-cuu/van-ban-di\"},{\"label_count\":0,\"label_name\":\"Văn bản nội bộ\",\"label_urlna\":\"\",\"label_url\":\"/group/mobile/tra-cuu/van-ban-noi-bo\"}],\"page_priority\":4,\"page_name\":\"Tra cứu\"},{\"page_urlna\":\"\",\"page_url\":\"\",\"page_itemicon\":\"item-thongke\",\"labels\":[{\"label_count\":0,\"label_name\":\"Thống kê văn bản đến\",\"label_urlna\":\"\",\"label_url\":\"/group/mobile/thong-ke/van-ban-den\"}],\"page_priority\":10,\"page_name\":\"Thống kê\"}]";
////            s = sx;
//            //// TODO: 4/21/2017 close
////            s = "";
//            if (!s.equals(nULL_STRING) && !s.equals("Error!")) {
//                writeToFile(s.toString(), context, SLIDERFILE);
//            }
//            upDateSliderMenu(s, true);
////            if (BooleanDownload.equals(TRUE)) {
////                if (!isDownloadOffLine()) {
////                    eventReadJsonOffLine(0);
////                }
////            }
//            super.onPostExecute(s);
//        }
//    }
        /*==============================
     get json list document
================================*/

//    class ReadJsonDocument extends AsyncTask<String, Integer, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            String resuilt = "";
//            JSONObject mJsonObject = new JSONObject();
//            try {
//                mJsonObject.put(MODULE, MODULE_PROCESSING_WORKING);
//                mJsonObject.put(NEOTYPE, TYPE_DOCUMENT_LIST);
//                JSONObject mJsonData = new JSONObject();
//                mJsonData.put(LABLE_URL_PARAM, urlNA);
//                mJsonData.put(ROW_PER_PAGE, 20);
//                mJsonData.put(NUM_PAGE, mOfficeView.getPositionPage());
//                mJsonObject.put(DATA, mJsonData.toString());
//                resuilt = eventPostRequest(getModuleInfor(MODULE_PROCESSING_WORKING, context), mJsonObject.toString(), getUserid(), getPass());
//                JSONObject mJsonObjectFullData = new JSONObject(resuilt);
//                JSONArray mJsonArray = mJsonObjectFullData.getJSONArray(DATA);
//                for (int i = 0; i < mJsonArray.length(); i++) {
//                    JSONObject mJsonObjectResponse = mJsonArray.getJSONObject(i);
//                    String mTitle = mJsonObjectResponse.getString(TITLE);
//                    String mNgayNhan = mJsonObjectResponse.getString(RECEIVE_DATE);
//                    String mSumary = mJsonObjectResponse.getString(SUMARY);
//                    String DocumentID = mJsonObjectResponse.getString(JOB_ID);
//                    String resourceCodeId = mJsonObjectResponse.getString(RESOURCECODEID);
//                    boolean oldornewDocument = mJsonObjectResponse.getBoolean(NEW_OLD);
//                    boolean seenDocument = mJsonObjectResponse.getBoolean(VIEWED);
//                    int jobType = mJsonObjectResponse.getInt(JOB_TYPE);
//                    String lableURL = mJsonObjectResponse.getString(LABLE_URL_PARAM);
//                    mOfficeView.getmSqLite().QueryData(CREATE_TABLE + " " + " M" + getOnlyNumerics(lableURL) + SQLLITE_DOCUMENT_LIST);
//                    mOfficeView.getmSqLite().QueryData("INSERT OR REPLACE INTO '" + "M" + getOnlyNumerics(lableURL) + "'(urlna,total,pagenumber,title,ngaynhan,sumary,documentID,resourceCodeId,oldornewdocument,seendocument,jobtype,datemilisec) VALUES('" + lableURL + "','" + getNumberOfPage() + "','" + mOfficeView.getPositionPage() + "', '" +
//                            specialValid(Html.fromHtml(mTitle).toString()) + "', '" + mNgayNhan + "', '" + specialValid(Html.fromHtml(mSumary).toString()) + "', '" + DocumentID + "', '" + resourceCodeId + "','" + oldornewDocument + "','" + seenDocument + "','" + jobType + "','" + getMilisecDocument(mNgayNhan, context) + "')");
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//                checkErrorServerDocumentList = true;
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            if (checkErrorServerDocumentList) {
//                mOfficeView.closeProgress();
//                Toast.makeText(context, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
//                checkErrorServerDocumentList = false;
//                return;
//            } else {
//                if (mOfficeView.getPositionPage() > 1) {
//                    getListDocumentSQLite((mOfficeView.getPositionPage() - 1) * getLimitPager(), urlNA);
//                } else {
//                    mOfficeView.changeUIDocument(DOCUMENT_FRAGMENT_TAG, urlNA);
//                }
//            }
//            mOfficeView.closeProgress();
//            super.onPostExecute(s);
//        }
//    }
//protected void upDateSliderMenu(String s, boolean Reload) {
//    try {
//        JSONObject mObject = new JSONObject(s);
//        JSONArray mJsonArray = mObject.getJSONArray(DATA);
////            mData = new HashMap<>();
//        arrHeander = new ArrayList<>();
////            ArrayList<ItemsMenu> arrChild = null;
//        List<ChildMenu> arrChild = null;
//        final ArrayList<ItemsMenu> listHeader = new ArrayList<ItemsMenu>();
//        for (int i = 0; i < mJsonArray.length(); i++) {
//            JSONObject mJsonObject = mJsonArray.getJSONObject(i);
//            String pageName = mJsonObject.getString(PAGE_NAME);
//            String pageCode = mJsonObject.getString(PAGE_CODE);
//            int pagePriority = mJsonObject.getInt(PAGE_PRIORITY);
////                addtoArrCode(pageCode);
//            switch (pageCode) {
//                case CODE_HOME:
//                    arrHeander.add(new HeaderMenu(pageName.toUpperCase(), arrChild, R.drawable.slider_home_icon, pagePriority, pageCode, 0, false));
////                        listHeader.add(new ItemsMenu(pageName.toUpperCase(), R.drawable.slider_home_icon, pagePriority, pageCode, 0));
//                    break;
//                case CODE_SCHEDULE:
//                    arrHeander.add(new HeaderMenu(pageName.toUpperCase(), null, R.drawable.item_lich, pagePriority, pageCode, 0, false));
////                        listHeader.add(new ItemsMenu(pageName.toUpperCase(), R.drawable.item_lich, pagePriority, pageCode, 0));
//                    break;
//                case CODE_PROCESS:
//                    arrHeander.add(new HeaderMenu(pageName.toUpperCase(), null, R.drawable.item_xlcv, pagePriority, pageCode, 0, false));
////                        listHeader.add(new ItemsMenu(pageName.toUpperCase(), R.drawable.item_xlcv, pagePriority, pageCode, 0));
//                    break;
//                case CODE_LOOKUP:
//                    arrHeander.add(new HeaderMenu(pageName.toUpperCase(), null, R.drawable.item_tracuu, pagePriority, pageCode, 0, false));
////                        listHeader.add(new ItemsMenu(pageName.toUpperCase(), R.drawable.item_tracuu, pagePriority, pageCode, 0));
//                    break;
//                case CODE_STATISTICAL:
//                    arrHeander.add(new HeaderMenu(pageName.toUpperCase(), null, R.drawable.slider_statistical_icon, pagePriority, pageCode, 0, false));
////                        listHeader.add(new ItemsMenu(pageName.toUpperCase(), R.drawable.slider_statistical_icon, pagePriority, pageCode, 0));
//                    break;
//                default:
//                    arrHeander.add(new HeaderMenu(pageName.toUpperCase(), null, R.drawable.ic_play_arrow_white_24dp, pagePriority, pageCode, 0, false));
////                        listHeader.add(new ItemsMenu(pageName.toUpperCase(), R.drawable.ic_play_arrow_white_24dp, pagePriority, pageCode, 0));
//                    break;
//            }
////                mData.put(listHeader.get(listHeader.size() - 1), new ArrayList<ItemsMenu>());
//            if (!mJsonObject.isNull(LABLES)) {
//                JSONArray mArrayLable = mJsonObject.getJSONArray(LABLES);
//                for (int j = 0; j < mArrayLable.length(); j++) {
//                    arrChild = new ArrayList<>();
//                    JSONObject mObjectLable = mArrayLable.getJSONObject(j);
//                    String labelName = mObjectLable.getString(LABLE_NAME);
//                    int labelCount = mObjectLable.getInt(LABLE_COUNT);
//                    String labelCode = "";
//                    if (!mObjectLable.isNull(LABLE_CODE)) {
//                        labelCode = mObjectLable.getString(LABLE_CODE);
//                    }
//                    switch (labelCode) {
//                        case CODE_LOOKUP_COMING:
//                        case CODE_LOOKUP_SEND:
//                        case CODE_LOOKUP_LOCAL:
//                        case CODE_LOOKUP_ONE_WINDOW:
//                        case CODE_SCHEDULE_DAY:
//                        case CODE_SCHEDULE_WEEK:
//                        case CODE_STATISTICAL_COMING:
//                        case CODE_STATISTICAL_SEND:
//                        case CODE_STATISTICAL_LOCAL:
//                        case CODE_STATISTICAL_ONE_WINDOW:
//                        case CODE_STATISTICAL_FOLLOW_PERSON:
//                        case CODE_STATISTICAL_FOLLOW_DEPEARTMENT:
//                            arrHeander.add(new HeaderMenu(labelName, null, android.R.color.transparent, 0, labelCode, labelCount, false));
////                                    listHeader.add(new ItemsMenu(labelName, android.R.color.transparent, 0, labelCode, labelCount));
//                            break;
//                        default:
//                            if (!labelCode.equals(nULL_STRING)) {
//                                arrHeander.add(new HeaderMenu(labelName, null, android.R.color.transparent, 0, labelCode, labelCount, false));
//
//                            }
////                                    listHeader.add(new ItemsMenu(labelName, R.drawable.ic_play_arrow_white_24dp, 0, labelCode, labelCount));
//                            break;
//                    }
////                            mData.put(listHeader.get(listHeader.size() - 1), new ArrayList<ItemsMenu>());
//                    //========================
//                    if (!mObjectLable.isNull(LABLES)) {
//                        JSONArray mArrayLabelC = mObjectLable.getJSONArray(LABLES);
//                        if (mArrayLabelC.length() > 0) {
//                            for (int k = 0; k < mArrayLabelC.length(); k++) {
//                                JSONObject mJsonlabelCs = mArrayLabelC.getJSONObject(k);
//                                String labelCName = mJsonlabelCs.getString(LABLE_NAME);
//                                String labelCCode = mJsonlabelCs.getString(LABLE_CODE);
//                                int labelCCount = mJsonlabelCs.getInt(LABLE_COUNT);
//                                addtoArrCode(labelCCode);
////                                arrChild.add(new ItemsMenu(labelCName, 0, 0, labelCCode, labelCCount));
////                                mData.put(listHeader.get(i + j + 1), arrChild)
//                                arrChild.add(new ChildMenu(labelCName, 0, 0, labelCCode, labelCCount));
//                            }
//                            arrHeander.add(new HeaderMenu(labelName, arrChild, R.drawable.ic_play_arrow_white_24dp, 0, labelCode, labelCount, false));
//                        }
////                        }
////                        else {
////                            //==========================
////                            listHeader.add(new ItemsMenu(labelName, android.R.color.transparent, 0, labelCode, labelCount));
////                            mData.put(listHeader.get(listHeader.size() - 1), new ArrayList<ItemsMenu>());
////                            //==========================
//                    }
//                }
//            }
////                else {
////                    mData.put(listHeader.get(listHeader.size() - 1), new ArrayList<ItemsMenu>());
////                }
//        }
////            Log.d(TAG, listHeader.get(listHeader.size() - 1) + nULL_STRING);
//
////            if (adapter != null) {
////                adapter.notifyDataSetChanged();
////            } else {
////            adapter = new ListMenuAdapter(OfficalActivity.this, listHeader, mData);
////            lv.setAdapter(adapter);
//        if (adapter != null) {
//            adapter.notifyDataSetChanged();
//        } else {
//            mOfficeView.setLayoutManagerRecycle();
//            adapter = new SlideMenuAdapter(arrHeander, context);
//            mOfficeView.setAdapterSlider(adapter);
//        }
////            }
//        mOfficeView.closeProgress();
//        //  Onclick on item slider menu_main add start
////            final ArrayList<ItemsMenu> finalArrChild = arrChild;
////            lv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
////                @Override
////                public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
////                    setCheckShowListSearch(false);
//////                    String mURL = mData.get(listHeader.get(groupPosition)).get(childPosition).getUrlMenu();
////                    if (!mData.get(listHeader.get(groupPosition)).get(childPosition).getMenuCode().equals(null)) {
////                        setSearchScreen(SEARCH_PROCESS);
////                        setLookupScreen(LOOKUP_PROCESS);
////                        if (!mData.get(listHeader.get(groupPosition)).get(childPosition).getMenuCode().equals(nULL_STRING)) {
////                            urlNA = mData.get(listHeader.get(groupPosition)).get(childPosition).getMenuCode();
////                            mSqLite.QueryData(CREATE_TABLE + " " + " M" + getOnlyNumerics(urlNA) + SQLLITE_DOCUMENT_LIST);
////                            if (isNetworkAvailable(OfficalActivity.this)) {
////                                eventReadJsonNumberOfTotal();
////                            } else {
////                                TotalAndChangeUIDocument(urlNA);
////                            }
////                        }
////                        setCheckShowListDocument(true);
////                        mDrawerLayout.closeDrawer(GravityCompat.START);
////                    }
////                    return false;
////                }
////            });
////            lv.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
////                @Override
////                public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
////                    setCheckShowListSearch(false);
////                    NumberOfPage = 0;
////                    String pageCode = listHeader.get(groupPosition).getMenuCode();
////                    String urlnaGroup = listHeader.get(groupPosition).getMenuCode();
////                    if (!urlnaGroup.equals("")) {
////                        urlNA = urlnaGroup;
////                    }
////                    if (!pageCode.equals(nULL_STRING)) {
////                        switch (pageCode) {
////                            case CODE_HOME:
////                                changeUiHome();
////                                break;
////                            case CODE_STATISTICAL_DEPARTMENT:
////                                setLookupScreen(CODE_STATISTICAL_DEPARTMENT);
////                                changeUiStatisticalDepartment();
////                                break;
////                            case CODE_PROCESS:
////                            case CODE_LOOKUP:
////                            case CODE_STATISTICAL:
////                                break;
////                            case CODE_SCHEDULE:
////                                setLookupScreen(LOOKUP_SCHEDULE);
////                                changeUISchedule();
////                                break;
////                            case CODE_STATISTICAL_COMING:
////                                setLookupScreen(LOOKUP_STATISTICAL);
////                                changeUIStatistical();
////                                break;
////                            case CODE_LOOKUP_COMING:
////                                setLookupScreen(LOOKUP_COMING);
////                                setSearchScreen(SEARCH_COMING);
////                                eventGetDataScreen(LOOKUP_COMING);
////                                break;
////                            case CODE_LOOKUP_SEND:
////                                setLookupScreen(LOOKUP_SENT);
////                                setSearchScreen(SEARCH_SENT);
////                                eventGetDataScreen(LOOKUP_SENT);
////                                break;
////                            case CODE_LOOKUP_LOCAL:
////                                setLookupScreen(LOOKUP_INTERNAL);
////                                setSearchScreen(SEARCH_INTERNAL);
////                                eventGetDataScreen(LOOKUP_INTERNAL);
////                                break;
////                            default:
////                                setSearchScreen(SEARCH_PROCESS);
////                                setLookupScreen(LOOKUP_PROCESS);
////                                mSqLite.QueryData(CREATE_TABLE + " " + " M" + getOnlyNumerics(urlNA) + SQLLITE_DOCUMENT_LIST);
////                                //CREATE TABLE LOCALDATA
////                                if (isNetworkAvailable(OfficalActivity.this)) {
////                                    eventReadJsonNumberOfTotal();
////                                } else {
////                                    TotalAndChangeUIDocument(urlNA);
////                                }
////                                setCheckShowListDocument(true);
////                                break;
////                        }
////                        switch (pageCode) {
////                            case CODE_PROCESS:
////                            case CODE_LOOKUP:
////                            case CODE_STATISTICAL:
////                                break;
////                            default:
////                                mDrawerLayout.closeDrawer(GravityCompat.START);
////                                break;
////                        }
////                        setCheckShowListDocument(false);
////                    }
////                    return false;
////                }
////            });
//        //  Onclick on item slider menu_main add end
//    } catch (JSONException e) {
//        if (Reload) {
//            mOfficeView.stopRequest();
//        }
//        e.printStackTrace();
//    }
//}
    //    class ReadJsonListDocumentLookUp extends AsyncTask<String, Integer, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            String mRequest = "";
//            String Resuilt = "";
//            String screen = mOfficeView.getLookupScreen();
//            switch (screen) {
//                case LOOKUP_COMING:
//                    mRequest = addJsonForRequestDocumentLookup(TYPE_LOOKUP_DOCUMENT_LIST_COMING);
//                    Resuilt = eventPostRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, context), mRequest, getUserid(), getPass());
//                    break;
//                case LOOKUP_SENT:
//                    mRequest = addJsonForRequestDocumentLookup(TYPE_LOOKUP_DOCUMENT_LIST_SENT);
//                    Resuilt = eventPostRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, context), mRequest, getUserid(), getPass());
//                    break;
//                case LOOKUP_INTERNAL:
//                    mRequest = addJsonForRequestDocumentLookup(TYPE_LOOKUP_DOCUMENT_LIST_INTERNAL);
//                    Resuilt = eventPostRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, context), mRequest, getUserid(), getPass());
//                    break;
//                case DOC_NOT_PROCESS_TYPE:
//                case DOC_NOT_SEEN_TYPE:
//                case DOC_DEMURRAGE_TYPE:
//                case STA_DOC_PROCESS_ON_TIME_FULL:
//                case STA_DOC_NOT_PROCESS_FULL:
//                case STA_DOC_DEMURRAGE_FULL:
//                case LIST_DOCUMENT_DEPARTMENT:
//                    mRequest = addJsonForRequestHomeListDocument(mOfficeView.getTypeHomeListDocument());
//                    Resuilt = eventPostRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, context), mRequest, getUserid(), getPass());
//                    break;
//                // statistical department
//                case TAP_DPM_DELAYS:
//                case TAP_DPM_INDUE:
//                case TAP_DPM_ONTIME:
//                case TAP_DPM_OUT_OF_DATE:
//                    // task
//                case TASK_REPORTED:
//                case TASK_PROCESS:
//                case TASK_PROCESS_ON_TIME:
//                case TASK_PROCESS_NEAR_DEMURRAGE:
//                case TASK_PROCESS_DEMURRAGE:
//                    mRequest = addJsonForRequestTaskList();
//                    Resuilt = eventPostRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, context), mRequest, getUserid(), getPass());
//                    break;
//                case HOT_PROCESS:
//                case HOT_PROCESS_DEMURRAGE:
//                case HOT_PROCESS_ONDUE:
//                    mRequest = addJsonForRequestHotLine();
//                    Resuilt = eventPostRequest(getModuleInfor(HOTLINE, context), mRequest, getUserid(), getPass());
//                    break;
//                default:
//                    break;
//            }
////            Resuilt = eventPostRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, OfficalActivity.this), mRequest, getUserid(), getPass());
//            try {
//                JSONObject mJsonObjectFulData = new JSONObject(Resuilt);
//                JSONArray mJsonArrayData = mJsonObjectFulData.getJSONArray(DATA);
//                arrResuiltDocumentLookup(mJsonArrayData, true);
//            } catch (JSONException e) {
//                checkErrorServerDocumentListLookup = true;
//                e.printStackTrace();
//            }
//            return Resuilt;
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            if (checkErrorServerDocumentListLookup) {
//                mOfficeView.closeProgress();
//                ShowErrorToast(context);
//                checkErrorServerDocumentListLookup = false;
//                return;
//            }
//            switch (mOfficeView.getLookupScreen()) {
//                case DOC_NOT_PROCESS_TYPE:
//                case DOC_NOT_SEEN_TYPE:
//                case DOC_DEMURRAGE_TYPE:
//                case STA_DOC_PROCESS_ON_TIME_FULL:
//                case STA_DOC_NOT_PROCESS_FULL:
//                case STA_DOC_DEMURRAGE_FULL:
//                case LIST_DOCUMENT_DEPARTMENT:
//                case TASK_REPORTED:
//                case TASK_PROCESS:
//                case TASK_PROCESS_ON_TIME:
//                case TASK_PROCESS_NEAR_DEMURRAGE:
//                case TASK_PROCESS_DEMURRAGE:
//                    // statistical department
//                case TAP_DPM_DELAYS:
//                case TAP_DPM_INDUE:
//                case TAP_DPM_ONTIME:
//                case TAP_DPM_OUT_OF_DATE:
//                    if (mOfficeView.getPositionPage() > 1) {
//                        adapterClickNumber.notifyDataSetChanged();
//                    } else {
//                        adapterClickNumber = new DocumentLookupAdapter(context, arrDocumentLookUp);
//                        mOfficeView.setDocLookup(adapterClickNumber);
//                    }
//                    mOfficeView.setActionBarIcon(R.drawable.ic_arrow_back_white_24dp);
//                    if (arrDocumentLookUp.size() == 0) {
//                        if (!isNetworkAvailable(context)) {
//                            mOfficeView.visibleNotConnection(true);
//                        } else {
//                            mOfficeView.showDocEmpty(true);
//
//                        }
//                    }
//                    break;
//                case HOT_PROCESS:
//                case HOT_PROCESS_DEMURRAGE:
//                case HOT_PROCESS_ONDUE:
//                    if (mOfficeView.getPositionPage() > 1) {
//                        adapterHotLine.notifyDataSetChanged();
//                    } else {
//                        adapterHotLine = new HotLineAdapter(context, arrHotLineList);
//                        mOfficeView.HotLineAdapter(adapterHotLine);
//
//                    }
//                    mOfficeView.setActionBarIcon(R.drawable.ic_arrow_back_white_24dp);
//                    if (arrHotLineList.size() == 0) {
//                        if (!isNetworkAvailable(context)) {
//                            mOfficeView.visibleNotConnection(true);
//                        } else {
//                            mOfficeView.showDocEmpty(true);
//
//                        }
//                    }
//                    break;
//                default:
//                    if (mOfficeView.getPositionPage() > 1) {
//                        getListDocumentLookupComing((mOfficeView.getPositionPage() - 1) * getLimitPager());
//                    } else {
//                        if (!mOfficeView.getLookupScreen().equals(nULL_STRING)) {
//
//                            mOfficeView.changeUIDocument(mOfficeView.getLookupScreen(), null);
//                        }
//                    }
//                    break;
//            }
//            mOfficeView.closeProgress();
//            super.onPostExecute(s);
//
//        }
//    }

    // tree view
//    protected void upDateSliderMenu(String s, boolean Reload) {
//        gson = new Gson();
////        arrHeander = new ArrayList<>();
////        List<ChildMenu> arrChild = null;
//        GsonSlider mSliderObject = gson.fromJson(s, GsonSlider.class);
//        root = TreeNode.root();
//        TreeMenu.MenuTreeItem nodeItem;
//        for (int i = 0; i < mSliderObject.getData().size(); i++) {
//            GsonSlider.DataBean mDataBean = mSliderObject.getData().get(i);
//            switch (mDataBean.getPageCode()) {
//                case CODE_HOME:
//                    nodeItem = new TreeMenu.MenuTreeItem(mDataBean.getPageName(), R.drawable.slider_home_icon, mDataBean.getPagePriority(), mDataBean.getPageCode(), 0, false);
////                    arrHeander.add(new HeaderMenu(mDataBean.getPageName(), arrChild, R.drawable.slider_home_icon, mDataBean.getPagePriority(), mDataBean.getPageCode(), 0, false));
//                    break;
//                case CODE_SCHEDULE:
//                    nodeItem = new TreeMenu.MenuTreeItem(mDataBean.getPageName(), R.drawable.item_lich, mDataBean.getPagePriority(), mDataBean.getPageCode(), 0, false);
////                    arrHeander.add(new HeaderMenu(mDataBean.getPageName(), null, R.drawable.item_lich, mDataBean.getPagePriority(), mDataBean.getPageCode(), 0, false));
//                    break;
//                case CODE_PROCESS:
//                    nodeItem = new TreeMenu.MenuTreeItem(mDataBean.getPageName(), R.drawable.item_xlcv, mDataBean.getPagePriority(), mDataBean.getPageCode(), 0, false);
////                    arrHeander.add(new HeaderMenu(mDataBean.getPageName(), null, R.drawable.item_xlcv, mDataBean.getPagePriority(), mDataBean.getPageCode(), 0, false));
//                    break;
//                case CODE_LOOKUP:
//                    nodeItem = new TreeMenu.MenuTreeItem(mDataBean.getPageName(), R.drawable.item_tracuu, mDataBean.getPagePriority(), mDataBean.getPageCode(), 0, false);
////                    arrHeander.add(new HeaderMenu(mDataBean.getPageName(), null, R.drawable.item_tracuu, mDataBean.getPagePriority(), mDataBean.getPageCode(), 0, false));
//                    break;
//                case CODE_STATISTICAL:
//                    nodeItem = new TreeMenu.MenuTreeItem(mDataBean.getPageName(), R.drawable.slider_statistical_icon, mDataBean.getPagePriority(), mDataBean.getPageCode(), 0, false);
////                    arrHeander.add(new HeaderMenu(mDataBean.getPageName(), null, R.drawable.slider_statistical_icon, mDataBean.getPagePriority(), mDataBean.getPageCode(), 0, false));
//                    break;
//                default:
//                    nodeItem = new TreeMenu.MenuTreeItem(mDataBean.getPageName(), R.drawable.ic_play_arrow_white_24dp, mDataBean.getPagePriority(), mDataBean.getPageCode(), 0, false);
////                    arrHeander.add(new HeaderMenu(mDataBean.getPageName(), null, R.drawable.ic_play_arrow_white_24dp, mDataBean.getPagePriority(), mDataBean.getPageCode(), 0, false));
//                    break;
//            }
//            Parent = new TreeNode(nodeItem).setViewHolder(new TreeMenu(context));
//            root.addChild(Parent);
//            if (mDataBean.getLabels() != null) {
//                for (int j = 0; j < mDataBean.getLabels().size(); j++) {
////                    arrChild = new ArrayList<>();
//                    GsonSlider.DataBean.LabelsBeanX mLabelsBeanX = mDataBean.getLabels().get(j);
//                    switch (mLabelsBeanX.getLabelCode()) {
//                        case CODE_LOOKUP_COMING:
//                        case CODE_LOOKUP_SEND:
//                        case CODE_LOOKUP_LOCAL:
//                        case CODE_LOOKUP_ONE_WINDOW:
//                        case CODE_SCHEDULE_DAY:
//                        case CODE_SCHEDULE_WEEK:
//                        case CODE_STATISTICAL_COMING:
//                        case CODE_STATISTICAL_SEND:
//                        case CODE_STATISTICAL_LOCAL:
//                        case CODE_STATISTICAL_ONE_WINDOW:
//                        case CODE_STATISTICAL_FOLLOW_PERSON:
//                        case CODE_STATISTICAL_FOLLOW_DEPEARTMENT:
//                            nodeItem = new TreeMenu.MenuTreeItem(mLabelsBeanX.getLabelName(), android.R.color.transparent, 0, mLabelsBeanX.getLabelCode(), 0, false);
////                            arrHeander.add(new HeaderMenu(mLabelsBeanX.getLabelName(), null, android.R.color.transparent, 0, mLabelsBeanX.getLabelCode(), mLabelsBeanX.getLabelCount(), false));
//                            break;
//                        default:
//                            if (!mLabelsBeanX.getLabelCode().equals(nULL_STRING)) {
//                                nodeItem = new TreeMenu.MenuTreeItem(mLabelsBeanX.getLabelName(), android.R.color.transparent, 0, mLabelsBeanX.getLabelCode(), 0, false);
////                                arrHeander.add(new HeaderMenu(mLabelsBeanX.getLabelName(), null, android.R.color.transparent, 0, mLabelsBeanX.getLabelCode(), mLabelsBeanX.getLabelCount(), false));
//                            }
//                            break;
//                    }
//                    Treelevel_2 = new TreeNode(nodeItem).setViewHolder(new TreeMenu(context));
//                    Parent.addChild(Treelevel_2);
//                    if (mLabelsBeanX.getLabels() != null) {
//                        for (int k = 0; k < mLabelsBeanX.getLabels().size(); k++) {
//                            GsonSlider.DataBean.LabelsBeanX.LabelsBean mLabelsBean = mLabelsBeanX.getLabels().get(k);
//                            addtoArrCode(mLabelsBean.getLabelCode());
////                            arrChild.add(new ChildMenu(mLabelsBean.getLabelName(), 0, 0, mLabelsBean.getLabelCode(), mLabelsBean.getLabelCount()));
//                            nodeItem = new TreeMenu.MenuTreeItem(mLabelsBean.getLabelName(), 0, 0, mLabelsBean.getLabelCode(), mLabelsBean.getLabelCount(), false);
//                            Treelevel_3 = new TreeNode(nodeItem).setViewHolder(new TreeMenu(context));
//                            Treelevel_2.addChild(Treelevel_3);
//                        }
//                        if (mLabelsBeanX.getLabels().size() != 0) {
////                            arrHeander.add(new HeaderMenu(mLabelsBeanX.getLabelName(), arrChild, R.drawable.ic_play_arrow_white_24dp, 0, mLabelsBeanX.getLabelCode(), mLabelsBeanX.getLabelCount(), false));
//                            nodeItem = new TreeMenu.MenuTreeItem(mLabelsBeanX.getLabelName(), R.drawable.ic_play_arrow_white_24dp, 0, mLabelsBeanX.getLabelCode(), mLabelsBeanX.getLabelCount(), false);
//                            Treelevel_3 = new TreeNode(nodeItem).setViewHolder(new TreeMenu(context));
//                            Treelevel_2.addChild(Treelevel_3);
//                        }
//
//                    }
//                }
//            }
//        }
////        if (adapter != null) {
////            adapter.notifyDataSetChanged();
////        } else {
////            mOfficeView.setLayoutManagerRecycle();
////            adapter = new SlideMenuAdapter(arrHeander, context);
////            mOfficeView.setAdapterSlider(adapter);
////        }
//        AndroidTreeView tView = new AndroidTreeView(context, root);
////        tView.setDefaultContainerStyle(R.style.TreeNodeStyleCustom);
////        tView.setUse2dScroll(true);
//        mOfficeView.SetTreeList(tView, FORWARD, root);
//        mOfficeView.closeProgress();
//        tView.expandAll();
//        mOfficeView.closeProgress();
//    }
    //    class ReadJsonFromSearch extends AsyncTask<String, Integer, String> {
//        String mRequest = "";
//        String Resuilt = "";
//
//        @Override
//        protected String doInBackground(String... params) {
//            switch (mOfficeView.getLookupScreen()) {
//                case LOOKUP_COMING:
//                    mRequest = addJsonRequestListSearch(MODULE_LOOKUP_DOCUMENT, TYPE_LOOKUP_DOCUMENT_LIST_COMING, false);
//                    Resuilt = eventPostRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, context), mRequest, getUserid(), getPass());
//                    break;
//                case LOOKUP_SENT:
//                    mRequest = addJsonRequestListSearch(MODULE_LOOKUP_DOCUMENT, TYPE_LOOKUP_DOCUMENT_LIST_SENT, false);
//                    Resuilt = eventPostRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, context), mRequest, getUserid(), getPass());
//                    break;
//                case LOOKUP_INTERNAL:
//                    mRequest = addJsonRequestListSearch(MODULE_LOOKUP_DOCUMENT, TYPE_LOOKUP_DOCUMENT_LIST_INTERNAL, false);
//                    Resuilt = eventPostRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, context), mRequest, getUserid(), getPass());
//                    break;
//                default:
//                    mRequest = addJsonRequestListSearch(MODULE_PROCESSING_WORKING, TYPE_DOCUMENT_LIST, true);
//                    Resuilt = eventPostRequest(getModuleInfor(MODULE_PROCESSING_WORKING, context), mRequest, getUserid(), getPass());
//                    break;
//            }
//            return Resuilt;
//
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            // set boolean for backpress after search success
//            mOfficeView.setAfterSearch(true);
//            if (!isDownloadOffLine()) {
//                try {
//                    Log.d(TAG, s);
//                    switch (mOfficeView.getLookupScreen()) {
//                        case LOOKUP_COMING:
//                            setListViewLookUp(s, LOOKUP_COMING);
//                            break;
//                        case LOOKUP_SENT:
//                            setListViewLookUp(s, LOOKUP_SENT);
//                            break;
//                        case LOOKUP_INTERNAL:
//                            setListViewLookUp(s, LOOKUP_INTERNAL);
//                            break;
//                        default:
//                            JSONObject mJsonObjectFullData = new JSONObject(s);
//                            JSONArray mArrayData = mJsonObjectFullData.getJSONArray(DATA);
//                            for (int i = 0; i < mArrayData.length(); i++) {
//                                JSONObject mJsonObject = mArrayData.getJSONObject(i);
//                                String mTitle = mJsonObject.getString(TITLE);
//                                String mNgayNhan = mJsonObject.getString(RECEIVE_DATE);
//                                String mSumary = mJsonObject.getString(SUMARY);
//                                long DocumentID = mJsonObject.getLong(JOB_ID);
//                                String resourceCodeId = mJsonObject.getString(RESOURCECODEID);
//                                boolean oldornewDocument = mJsonObject.getBoolean(NEW_OLD);
//                                boolean seenDocument = mJsonObject.getBoolean(VIEWED);
//                                int jobType = mJsonObject.getInt(JOB_TYPE);
//                                arrDocument.add(new ItemsDocument(mTitle, mNgayNhan, mSumary, DocumentID, resourceCodeId, oldornewDocument, seenDocument, jobType));
//                            }
//                            if (mOfficeView.getPositionPage() > 1) {
//                                adapterSearch.notifyDataSetChanged();
//                            } else {
//                                adapterSearch = new DocumentAdapter(context, arrDocument);
//                                mOfficeView.setListDoc(adapterSearch);
//                            }
//                            mOfficeView.setCheckShowListSearch(true);
//                            break;
//                    }
//                    mOfficeView.setActionBarIcon(R.drawable.menu_icon_slider);
//                } catch (JSONException e) {
//                    ShowErrorToast(context);
//                    mOfficeView.setActionBarIcon(R.drawable.menu_icon_slider);
//                    e.printStackTrace();
//                }
//            }
//            mOfficeView.closeProgress();
//            super.onPostExecute(s);
//
//        }
//    }
}
