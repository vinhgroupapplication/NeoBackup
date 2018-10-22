package com.newsaigonsoft.neoegov.Fragment.StatisticalDPMList;

import android.content.Context;
import android.util.Log;

import com.newsaigonsoft.neoegov.AVolleyManager.VolleyTask;
import com.newsaigonsoft.neoegov.AVolleyManager.VolleyTaskCompletedListenner;
import com.newsaigonsoft.neoegov.Adapter.StatisticalDPMListADapter;
import com.newsaigonsoft.neoegov.AsynTaskManager.AsyncTaskCompleteListener;
import com.newsaigonsoft.neoegov.Subjects.KeyManager;
import com.newsaigonsoft.neoegov.Subjects.ResuiltObject;
import com.newsaigonsoft.neoegov.Subjects.StatisticalDPMListRow;
import com.newsaigonsoft.neoegov.Subjects.SumManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.CATEGORY;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DATA;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.FROM_DATE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.IN_PROCESS_LATE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.IN_PROCESS_LATE_CODE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.IN_PROCESS_ON_TIME;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.IN_PROCESS_ON_TIME_CODE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MODULE_LOOKUP_DOCUMENT;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MODULE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.NAME;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.NEOTYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.NOTIFY_OBJECT_ID;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.PROCESSED_LATE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.PROCESSED_LATE_CODE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.PROCESSED_ON_TIME;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.PROCESSED_ON_TIME_CODE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.STATISTIC_CODE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TO_DATE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_STATIS_LIST_TOTAL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DPM_DELAYS;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DPM_IN_DUE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DPM_ON_TIME;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DPM_OUT_OF_DATE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DPM_PROCESS;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DPM_UN_PROCESS;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.STATIS_LIST;

/**
 * Created by Vinh on 12/29/2017.
 */

public class StatisticalDPMListLogic extends SumManager implements StatisticalDPMListImp, AsyncTaskCompleteListener<ResuiltObject>, VolleyTaskCompletedListenner<ResuiltObject> {

    Context context;
    StatisticalDPMListView mStatisticalDPMListView;
    int Category, ProcessType;
    String StartDate, EndDate;


    public StatisticalDPMListLogic(Context context, StatisticalDPMListView mStatisticalDPMListView) {
        this.context = context;
        this.mStatisticalDPMListView = mStatisticalDPMListView;
        getInforAccountFromShareReferenced(context);
    }

    @Override
    public void RequestList(int category, int processedType, String startDate, String endDate) {
        Category = category;
        ProcessType = processedType;
        StartDate = startDate;
        EndDate = endDate;
//        new NeoTask(context, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(context, KeyManager.STATIS_LIST, addJsonRequest().toString(), getLink() + KeyManager.URL_CENTER_6_1));
        new VolleyTask(context, KeyManager.STATIS_LIST, addJsonRequest(), this);
    }

    private JSONObject addJsonRequest() {
        JSONObject mObject = new JSONObject();
        try {
            mObject.put(MODULE, MODULE_LOOKUP_DOCUMENT);
            mObject.put(NEOTYPE, TYPE_STATIS_LIST_TOTAL);
            JSONObject mData = new JSONObject();
            mData.put(CATEGORY, Category);
            mData.put(STATISTIC_CODE, ProcessType);
            mData.put(FROM_DATE, getMilisecondDay(StartDate));
            mData.put(TO_DATE, getMilisecondDay(EndDate));
            mObject.put(DATA, mData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mObject;
    }


    private void refreshUI(String s, String CaseRequest) {
        switch (CaseRequest) {
            case STATIS_LIST:
                Log.d("VinhCNLog: ", s);
                switch (mStatisticalDPMListView.isCsreen()) {
                    case DPM_PROCESS:
                    case DPM_ON_TIME:
                    case DPM_DELAYS:
                        Log.d("VinhCNLog: ", s);
                        mStatisticalDPMListView.setAdapterProcessed(getAdapterProcessed(s));
                        break;
                    case DPM_UN_PROCESS:
                    case DPM_IN_DUE:
                    case DPM_OUT_OF_DATE:
                        Log.d("VinhCNLog: ", s);
                        mStatisticalDPMListView.setAdapterprocess(getAdapterProcess(s));
                        break;
                }
                break;
        }
        mStatisticalDPMListView.closeProgress();
    }

    @Override
    public void onTaskCompleted(String s, String CaseRequest) {
        refreshUI(s, CaseRequest);
    }

    public StatisticalDPMListADapter getAdapterProcessed(String s) {
        ArrayList<StatisticalDPMListRow> arrList = new ArrayList<>();
        StatisticalDPMListADapter adapter = null;
        int processedOnTime = 0;
        int processedOnTimeType = 0;
        int processedDemurrage = 0;
        int processedDemurrageType = 0;
        try {
            JSONObject mObject = new JSONObject(s);
            JSONArray mArray = mObject.getJSONArray(DATA);
            for (int i = 0; i < mArray.length(); i++) {
                JSONObject mJsonObject = mArray.getJSONObject(i);
                long objectId = mJsonObject.getLong(NOTIFY_OBJECT_ID);
                String name = mJsonObject.getString(NAME);
                if (!mJsonObject.isNull(PROCESSED_ON_TIME) && !mJsonObject.isNull(PROCESSED_ON_TIME_CODE)) {
                    processedOnTime = mJsonObject.getInt(PROCESSED_ON_TIME);
                    processedOnTimeType = mJsonObject.getInt(PROCESSED_ON_TIME_CODE);
                }
                if (!mJsonObject.isNull(PROCESSED_LATE) && !mJsonObject.isNull(PROCESSED_LATE_CODE)) {
                    processedDemurrage = mJsonObject.getInt(PROCESSED_LATE);
                    processedDemurrageType = mJsonObject.getInt(PROCESSED_LATE_CODE);
                }
                arrList.add(new StatisticalDPMListRow(objectId, name, processedOnTime,
                        processedOnTimeType, processedDemurrage, processedDemurrageType));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        adapter = new StatisticalDPMListADapter(context, arrList, mStatisticalDPMListView.isCsreen());
        return adapter;
    }

    public StatisticalDPMListADapter getAdapterProcess(String s) {
        ArrayList<StatisticalDPMListRow> arrList = new ArrayList<>();
        StatisticalDPMListADapter adapter = null;
        int processedOnTime = 0;
        int processedOnTimeType = 0;
        int processedDemurrage = 0;
        int processedDemurrageType = 0;
        try {
            JSONObject mObject = new JSONObject(s);
            JSONArray mArray = mObject.getJSONArray(DATA);
            for (int i = 0; i < mArray.length(); i++) {
                JSONObject mJsonObject = mArray.getJSONObject(i);
                long objectId = mJsonObject.getLong(NOTIFY_OBJECT_ID);
                String name = mJsonObject.getString(NAME);
                if (!mJsonObject.isNull(IN_PROCESS_ON_TIME) && !mJsonObject.isNull(IN_PROCESS_ON_TIME_CODE)) {
                    processedOnTime = mJsonObject.getInt(IN_PROCESS_ON_TIME);
                    processedOnTimeType = mJsonObject.getInt(IN_PROCESS_ON_TIME_CODE);
                }
                if (!mJsonObject.isNull(IN_PROCESS_LATE) && !mJsonObject.isNull(IN_PROCESS_LATE_CODE)) {
                    processedDemurrage = mJsonObject.getInt(IN_PROCESS_LATE);
                    processedDemurrageType = mJsonObject.getInt(IN_PROCESS_LATE_CODE);
                }
                arrList.add(new StatisticalDPMListRow(objectId, name, processedOnTime,
                        processedOnTimeType, processedDemurrage, processedDemurrageType));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        adapter = new StatisticalDPMListADapter(context, arrList, mStatisticalDPMListView.isCsreen());
        return adapter;
    }


    @Override
    public void onVolleyCompleted(String s, String CaseRequest) {
        refreshUI(s, CaseRequest);
    }

    @Override
    public void onVolleyError(String s) {
        mStatisticalDPMListView.closeProgress();
        mStatisticalDPMListView.ToastError(s);
    }
}
