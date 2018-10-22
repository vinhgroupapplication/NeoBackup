package com.newsaigonsoft.neoegov.Fragment.StatisticalDPM;

import android.content.Context;

import com.newsaigonsoft.neoegov.AVolleyManager.VolleyTask;
import com.newsaigonsoft.neoegov.AVolleyManager.VolleyTaskCompletedListenner;
import com.newsaigonsoft.neoegov.AsynTaskManager.AsyncTaskCompleteListener;
import com.newsaigonsoft.neoegov.Subjects.ResuiltObject;
import com.newsaigonsoft.neoegov.Subjects.StatisticalDPMRow;
import com.newsaigonsoft.neoegov.Subjects.SumManager;

import org.json.JSONException;
import org.json.JSONObject;

import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DATA;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.FROM_DATE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.IN_PROCESS;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.IN_PROCESS_CODE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.IN_PROCESS_COOR_DINATE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.IN_PROCESS_COOR_DINATE_CODE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.IN_PROCESS_LATE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.IN_PROCESS_LATE_CODE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.IN_PROCESS_ON_TIME;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.IN_PROCESS_ON_TIME_CODE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MODULE_LOOKUP_DOCUMENT;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MODULE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.NEOTYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.PROCESSED;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.PROCESSED_CODE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.PROCESSED_LATE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.PROCESSED_LATE_CODE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.PROCESSED_ON_TIME;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.PROCESSED_ON_TIME_CODE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TOTAL;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TOTAL_CODE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TO_DATE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_STATISTICAL_TASK;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.STATIS_DPM;

/**
 * Created by Vinh on 12/26/2017.
 */

public class StatisticalDPMLogic extends SumManager implements StatisticalDPMImp, AsyncTaskCompleteListener<ResuiltObject>,VolleyTaskCompletedListenner<ResuiltObject> {
    Context context;
    StatisticalDPMView mStatisticalDPMView;
    String StartDate;
    String EndDate;
    StatisticalDPMRow mStatisticalDPMRow;

    public StatisticalDPMLogic(Context context, StatisticalDPMView mStatisticalDPMView) {
        this.context = context;
        this.mStatisticalDPMView = mStatisticalDPMView;
    }

    private JSONObject addJsonRequestStatistical() {
        JSONObject mJsonObject = new JSONObject();
        try {
            mJsonObject.put(MODULE, MODULE_LOOKUP_DOCUMENT);
            mJsonObject.put(NEOTYPE, TYPE_STATISTICAL_TASK);
            JSONObject mData = new JSONObject();
            mData.put(FROM_DATE, getMilisecondDay(StartDate));
            mData.put(TO_DATE, getMilisecondDay(EndDate));
            mJsonObject.put(DATA, mData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mJsonObject;
    }


    @Override
    public void RequestStatisticalDPM(String startDate, String endDate) {
        mStatisticalDPMView.showProgress();
        StartDate = startDate;
        EndDate = endDate;
//        new NeoTask(context, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(context, STATIS_DPM, addJsonRequestStatistical().toString(), getLink() + URL_CENTER_6_1));
        new VolleyTask(context, STATIS_DPM, addJsonRequestStatistical(), this);
    }

    @Override
    public void onTaskCompleted(String s, String CaseRequest) {
        switch (CaseRequest) {
            case STATIS_DPM:
                setDataStatisticalDPM(s);
                mStatisticalDPMView.closeProgress();
                break;
        }
    }



    private void setDataStatisticalDPM(String s) {
        JSONObject mObject;
        try {
            mObject = new JSONObject(s);
            JSONObject mData = mObject.getJSONObject(DATA);
            int total = mData.getInt(TOTAL);
            int totalType = mData.getInt(TOTAL_CODE);
            int processed = mData.getInt(PROCESSED);
            int processedType = mData.getInt(PROCESSED_CODE);
            int processedOnTime = mData.getInt(PROCESSED_ON_TIME);
            int processedOnTimeType = mData.getInt(PROCESSED_ON_TIME_CODE);
            int processedDemurrage = mData.getInt(PROCESSED_LATE);
            int processedDemurrageType = mData.getInt(PROCESSED_LATE_CODE);
            int process = mData.getInt(IN_PROCESS);
            int processType = mData.getInt(IN_PROCESS_CODE);
            int processOnTime = mData.getInt(IN_PROCESS_ON_TIME);
            int processOnTimeType = mData.getInt(IN_PROCESS_ON_TIME_CODE);
            int processDemurrage = mData.getInt(IN_PROCESS_LATE);
            int processDemurrageType = mData.getInt(IN_PROCESS_LATE_CODE);
            int processCoordinate = mData.getInt(IN_PROCESS_COOR_DINATE);
            int processCoordinateType = mData.getInt(IN_PROCESS_COOR_DINATE_CODE);
            mStatisticalDPMRow = new StatisticalDPMRow(total, totalType, processed, processedType, processedOnTime,
                    processedOnTimeType, processedDemurrage, processedDemurrageType, process, processType, processOnTime,
                    processOnTimeType, processDemurrage, processDemurrageType, processCoordinate, processCoordinateType);
            mStatisticalDPMView.setStatistiCalView(mStatisticalDPMRow);
            mStatisticalDPMView.mStatisticalDPMRow(mStatisticalDPMRow);
        } catch (JSONException e) {
            e.printStackTrace();
            mStatisticalDPMView.closeProgress();
            mStatisticalDPMView.showToastError();
        }
    }

    @Override
    public void onVolleyCompleted(String s, String CaseRequest) {
        switch (CaseRequest) {
            case STATIS_DPM:
                setDataStatisticalDPM(s);
                mStatisticalDPMView.closeProgress();
                break;
        }
    }

    @Override
    public void onVolleyError(String s) {
            mStatisticalDPMView.closeProgress();
            mStatisticalDPMView.ToastError(s);
    }
}
