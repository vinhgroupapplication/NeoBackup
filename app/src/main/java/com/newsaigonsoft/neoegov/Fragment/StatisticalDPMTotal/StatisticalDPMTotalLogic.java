package com.newsaigonsoft.neoegov.Fragment.StatisticalDPMTotal;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import com.newsaigonsoft.neoegov.AVolleyManager.VolleyTask;
import com.newsaigonsoft.neoegov.AVolleyManager.VolleyTaskCompletedListenner;
import com.newsaigonsoft.neoegov.Adapter.StatisticalDPMTotalAdapter;
import com.newsaigonsoft.neoegov.Adapter.StatisticalPersonTotalAdapter;
import com.newsaigonsoft.neoegov.AsynTaskManager.AsyncTaskCompleteListener;
import com.newsaigonsoft.neoegov.Subjects.JsonKeyManager;
import com.newsaigonsoft.neoegov.Subjects.KeyManager;
import com.newsaigonsoft.neoegov.Subjects.ResuiltObject;
import com.newsaigonsoft.neoegov.Subjects.StatisticalDPMRow;
import com.newsaigonsoft.neoegov.Subjects.SumManager;

import static com.newsaigonsoft.neoegov.Subjects.KeyManager.STATIS_LIST_TOTAL;

/**
 * Created by Vinh on 12/28/2017.
 */

public class StatisticalDPMTotalLogic extends SumManager implements StatisticalDPMTotalImp, AsyncTaskCompleteListener<ResuiltObject>, VolleyTaskCompletedListenner<ResuiltObject> {
    Context context;
    StatisticalDPMTotalView mStatisticalDPMTotalView;
    int TotalType;
    int Category;
    String StartDate;
    String EndDate;


    public StatisticalDPMTotalLogic(Context context, StatisticalDPMTotalView mStatisticalDPMTotalView) {
        this.context = context;
        this.mStatisticalDPMTotalView = mStatisticalDPMTotalView;
        getInforAccountFromShareReferenced(context);
    }


    @Override
    public void RequestList(int totalType, int category, String startDate, String endDate) {
        TotalType = totalType;
        Category = category;
        StartDate = startDate;
        EndDate = endDate;
        mStatisticalDPMTotalView.showProgress();
//        new NeoTask(context, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(context, STATIS_LIST_TOTAL, addJsonRequestTotalList(), getLink() + URL_CENTER_6_1));
        new VolleyTask(context, STATIS_LIST_TOTAL, addJsonRequestTotalList(), this);
    }

    private JSONObject addJsonRequestTotalList() {
        JSONObject mObject = new JSONObject();
        try {
            mObject.put(JsonKeyManager.MODULE, JsonKeyManager.MODULE_LOOKUP_DOCUMENT);
            mObject.put(JsonKeyManager.NEOTYPE, JsonKeyManager.TYPE_STATIS_LIST_TOTAL);
            JSONObject mData = new JSONObject();
            mData.put(JsonKeyManager.CATEGORY, Category);
            mData.put(JsonKeyManager.STATISTIC_CODE, TotalType);
            mData.put(JsonKeyManager.FROM_DATE, getMilisecondDay(StartDate));
            mData.put(JsonKeyManager.TO_DATE, getMilisecondDay(EndDate));
            mObject.put(JsonKeyManager.DATA, mData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mObject;
    }


    private void refreshUI(String s, String CaseRequest) {
        switch (CaseRequest) {
            case KeyManager.STATIS_LIST_TOTAL:
                switch (Category) {
                    case 1:
                        StatisticalDPMTotalAdapter DPMadapter = new StatisticalDPMTotalAdapter(context, getListDPMTotal(s));
                        mStatisticalDPMTotalView.setListDPM(DPMadapter);
                        break;
                    case 2:
                        StatisticalPersonTotalAdapter PsAdapter = new StatisticalPersonTotalAdapter(context, getListDPMTotal(s));
                        mStatisticalDPMTotalView.setListPs(PsAdapter);
                        break;
                }
                break;
        }
        mStatisticalDPMTotalView.closeProgress();
    }

    @Override
    public void onTaskCompleted(String s, String CaseRequest) {
        refreshUI(s, CaseRequest);
    }

    public ArrayList<StatisticalDPMRow> getListDPMTotal(String s) {
        ArrayList<StatisticalDPMRow> arrDPMTotal = new ArrayList<>();
        try {
            JSONObject mObject = new JSONObject(s);
            JSONArray mArray = mObject.getJSONArray(JsonKeyManager.DATA);
            for (int i = 0; i < mArray.length(); i++) {
                JSONObject mObjectData = mArray.getJSONObject(i);
                long objectId = mObjectData.getLong(JsonKeyManager.NOTIFY_OBJECT_ID);
                String name = mObjectData.getString(JsonKeyManager.NAME);
                int processedOnTime = mObjectData.getInt(JsonKeyManager.PROCESSED_ON_TIME);
                int processedOnTimeType = mObjectData.getInt(JsonKeyManager.PROCESSED_ON_TIME_CODE);
                int processedDemurrage = mObjectData.getInt(JsonKeyManager.PROCESSED_LATE);
                int processedDemurrageType = mObjectData.getInt(JsonKeyManager.PROCESSED_LATE_CODE);
                int processOnTime = mObjectData.getInt(JsonKeyManager.IN_PROCESS_ON_TIME);
                int processOnTimeType = mObjectData.getInt(JsonKeyManager.IN_PROCESS_ON_TIME_CODE);
                int processDemurrage = mObjectData.getInt(JsonKeyManager.IN_PROCESS_LATE);
                int processDemurrageType = mObjectData.getInt(JsonKeyManager.IN_PROCESS_LATE_CODE);
                int processCoordinate = mObjectData.getInt(JsonKeyManager.IN_PROCESS_COOR_DINATE);
                int processCoordinateType = mObjectData.getInt(JsonKeyManager.IN_PROCESS_COOR_DINATE_CODE);
                arrDPMTotal.add(new StatisticalDPMRow(objectId, name, processedOnTime, processedOnTimeType,
                        processedDemurrage, processedDemurrageType, processOnTime
                        , processOnTimeType, processDemurrage, processDemurrageType, processCoordinate, processCoordinateType, false));
            }
        } catch (JSONException e) {
            mStatisticalDPMTotalView.closeProgress();
            mStatisticalDPMTotalView.showToastError();
            e.printStackTrace();
        }
        return arrDPMTotal;
    }


    @Override
    public void onVolleyCompleted(String s, String CaseRequest) {
        refreshUI(s, CaseRequest);
    }

    @Override
    public void onVolleyError(String s) {
        mStatisticalDPMTotalView.closeProgress();
        mStatisticalDPMTotalView.showToastError();
    }
}
