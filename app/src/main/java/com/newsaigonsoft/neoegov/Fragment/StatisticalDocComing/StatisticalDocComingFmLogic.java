package com.newsaigonsoft.neoegov.Fragment.StatisticalDocComing;

import android.content.Context;
import android.util.Log;

import com.newsaigonsoft.neoegov.AVolleyManager.VolleyTask;
import com.newsaigonsoft.neoegov.AVolleyManager.VolleyTaskCompletedListenner;
import com.newsaigonsoft.neoegov.Adapter.DepartmentAdapter;
import com.newsaigonsoft.neoegov.Adapter.StatisCMPersonAdapter;
import com.newsaigonsoft.neoegov.Adapter.StatisticalListAdapter;
import com.newsaigonsoft.neoegov.AsynTaskManager.AsyncTaskCompleteListener;
import com.newsaigonsoft.neoegov.GsonObject.GsonStaComing;
import com.newsaigonsoft.neoegov.GsonObject.GsonStaComingList;
import com.newsaigonsoft.neoegov.GsonObject.GsonStatisPersonJoin;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.JsonKeyManager;
import com.newsaigonsoft.neoegov.Subjects.ReceivePerson;
import com.newsaigonsoft.neoegov.Subjects.ResuiltObject;
import com.newsaigonsoft.neoegov.Subjects.StatisticalRows;
import com.newsaigonsoft.neoegov.Subjects.SumManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DATA;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.FROM_DATE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MODULE_LOOKUP_DOCUMENT;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MODULE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MODULE_WORK_ARISE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.NEOTYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.ORGANIZATION_EDITOR_ID;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.ORGANIZATION_ID;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.ORGANIZATION_NAME;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TO_DATE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_STATISTICAL;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_STATISTICAL_ARISE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_STATISTICAL_DEPARTMENT;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_STATISTICAL_DEPARTMENT_ARISE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_STATISTICAL_DEPARTMENT_LIST_ARISE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_STATISTICAL_PERSON_JOIN;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_STATISTICAL_PERSON_JOIN_ARISE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.CODE_STATISTICAL_COMING;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.CODE_STATISTICAL_WORK_ARISE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LIST_DOCUMENT_DEPARTMENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SEARCH_DOCUMENT_TYPE;
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
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.nULL_STRING;

/**
 * Created by Vinh on 10/23/2017.
 */

public class StatisticalDocComingFmLogic extends SumManager implements StatisticalDocComingFmImp, AsyncTaskCompleteListener<ResuiltObject>, VolleyTaskCompletedListenner<ResuiltObject> {
    StatisticalDocComingFmView mStatisticalFmView;
    Context context;
    String dateLastX, dateFirstX;
    ArrayList<StatisticalRows> rowsArrayList;
    ArrayList<ReceivePerson> arrListDepartmentSearch;
    ArrayList<String> arrNameDepartment;

    public StatisticalDocComingFmLogic(StatisticalDocComingFmView mStatisticalFmView, Context context) {
        this.mStatisticalFmView = mStatisticalFmView;
        this.context = context;
        getInforAccountFromShareReferenced(context);
    }


    @Override
    public void Request(String dateFirst, String dateLast) {
        dateFirstX = dateFirst;
        dateLastX = dateLast;
        mStatisticalFmView.showProgress();
        switch (mStatisticalFmView.getCodeMenu()) {
            case CODE_STATISTICAL_COMING:
                new VolleyTask(context, STA_COMING_FULL_DPM, addJsonRequestStatistical(MODULE_LOOKUP_DOCUMENT, TYPE_STATISTICAL, dateFirst, dateLast), this);
                new VolleyTask(context, STA_COMING_LIST_DPM, addJsonRequestStatistical(MODULE_LOOKUP_DOCUMENT, TYPE_STATISTICAL_DEPARTMENT, dateFirst, dateLast), this);
//                new VolleyTask(context, STA_COMING_PERSON_JOIN, addJsonRequestStatistical(MODULE_LOOKUP_DOCUMENT, TYPE_STATISTICAL_PERSON_JOIN, dateFirst, dateLast), this);
//                new VolleyTask(context, STA_COMING_DPM_LIST, addJsonRequestDPMList(MODULE_LOOKUP_DOCUMENT, JsonKeyManager.TYPE_STATISTICAL_DEPARTMENT_LIST), this);
                requestPersonList();
                break;
            case CODE_STATISTICAL_WORK_ARISE:
                new VolleyTask(context, STA_COMING_FULL_DPM_ARISE, addJsonRequestStatistical(MODULE_WORK_ARISE, TYPE_STATISTICAL_ARISE, dateFirst, dateLast), this);
                new VolleyTask(context, STA_COMING_LIST_DPM_ARISE, addJsonRequestStatistical(MODULE_WORK_ARISE, TYPE_STATISTICAL_DEPARTMENT_ARISE, dateFirst, dateLast), this);
//                new VolleyTask(context, STA_COMING_PERSON_JOIN_ARISE, addJsonRequestStatistical(MODULE_WORK_ARISE, TYPE_STATISTICAL_PERSON_JOIN_ARISE, dateFirst, dateLast), this);
//                new VolleyTask(context, STA_COMING_DPM_LIST_ARISE, addJsonRequestDPMList(MODULE_WORK_ARISE, TYPE_STATISTICAL_DEPARTMENT_LIST_ARISE), this);
                requestPersonList();
                break;
            default:
                break;
        }

    }

    @Override
    public void requestPersonList() {
        switch (mStatisticalFmView.getCodeMenu()) {
            case CODE_STATISTICAL_COMING:
                new VolleyTask(context, STA_COMING_PERSON_JOIN, addJsonRequestStatistical(MODULE_LOOKUP_DOCUMENT, TYPE_STATISTICAL_PERSON_JOIN, dateFirstX, dateLastX), this);
                break;
            case CODE_STATISTICAL_WORK_ARISE:
                new VolleyTask(context, STA_COMING_PERSON_JOIN_ARISE, addJsonRequestStatistical(MODULE_WORK_ARISE, TYPE_STATISTICAL_PERSON_JOIN_ARISE, dateFirstX, dateLastX), this);
                break;
            default:
                break;
        }

    }

    @Override
    public void requestDPMList() {
        switch (mStatisticalFmView.getCodeMenu()) {
            case CODE_STATISTICAL_COMING:
                new VolleyTask(context, STA_COMING_DPM_LIST, addJsonRequestDPMList(MODULE_LOOKUP_DOCUMENT, JsonKeyManager.TYPE_STATISTICAL_DEPARTMENT_LIST), this);
                break;
            case CODE_STATISTICAL_WORK_ARISE:
                new VolleyTask(context, STA_COMING_DPM_LIST_ARISE, addJsonRequestDPMList(MODULE_WORK_ARISE, TYPE_STATISTICAL_DEPARTMENT_LIST_ARISE), this);
                break;
            default:
                break;
        }
    }

    private JSONObject addJsonRequestDPMList(String MODULEs, String TYPE) {
        JSONObject mJsonObject = new JSONObject();
        try {
            mJsonObject.put(MODULE, MODULEs);
            mJsonObject.put(NEOTYPE, TYPE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mJsonObject;
    }

    private void refreshUI(String s, String CaseRequest) {
        Log.d(TAG, s);
        switch (CaseRequest) {
            case STA_COMING_PERSON_JOIN_ARISE:
            case STA_COMING_PERSON_JOIN:
                GsonStatisPersonJoin mGsonStatisPersonJoin = getGson().fromJson(s, GsonStatisPersonJoin.class);
                if (mGsonStatisPersonJoin.getCode() != 0) {
                    mStatisticalFmView.closeProgress();
//                    mStatisticalFmView.hideLayoutPersonJoin(true);
                } else {
                    mStatisticalFmView.hideLayoutPersonJoin(false);
                    List<GsonStatisPersonJoin.DataBean> mDataPersonJoin = mGsonStatisPersonJoin.getData();
                    mStatisticalFmView.setListPerson(mDataPersonJoin);
                }
                break;
            case STA_COMING_FULL_DPM_ARISE:
            case STA_COMING_FULL_DPM:
                GsonStaComing mGsonStaComing = getGson().fromJson(s, GsonStaComing.class);
                if (mGsonStaComing.getCode() != 0) {
                    mStatisticalFmView.closeProgress();
                    mStatisticalFmView.showLayoutFull(true);
                    return;
                }
                GsonStaComing.DataBean mDataBean = mGsonStaComing.getData();
                mStatisticalFmView.setViewStatistical(mDataBean, dateFirstX, dateLastX);
                break;
            case STA_COMING_LIST_DPM_ARISE:
            case STA_COMING_LIST_DPM:
                rowsArrayList = new ArrayList<StatisticalRows>();
                GsonStaComingList mGsonStaComingList = getGson().fromJson(s, GsonStaComingList.class);
                if (mGsonStaComingList.getCode() != 0) {
                    mStatisticalFmView.closeProgress();
                    mStatisticalFmView.showLayoutDepartment(true);
                    return;
                }
                List<GsonStaComingList.DataBean> mDataList = mGsonStaComingList.getData();
//                for (int i = 0; i < mDataList.size(); i++) {
//                    GsonStaComingList.DataBean mData = mDataList.get(i);
//                    rowsArrayList.add(new StatisticalRows(mData.getOrganizationName(), mData.getTotal(), mData.getInProcessLate(), mData.getInProcessCode(), mData.getProcessedOnTimeCode(),
//                            mData.getInProcessLateCode(), mData.getTotalCode(), mData.getOrganizationId(), mData.getRateOnTime(), mData.getInProcess() , mData.getProcessed(),mData.getProcessedLate(),   mData.getInProcessLate()  ,  false));
//                }
                mStatisticalFmView.setListDepartment(mDataList, dateFirstX, dateLastX);

                break;
            case STA_COMING_DPM_LIST:
            case STA_COMING_DPM_LIST_ARISE:
                arrListDepartmentSearch = new ArrayList<>();
                arrNameDepartment = new ArrayList<String>();
                arrListDepartmentSearch.add(new ReceivePerson(context.getString(R.string.chon), nULL_STRING));
                try {
                    JSONObject mJsonObjectFullData = new JSONObject(s);
                    JSONArray mJsonArray = mJsonObjectFullData.getJSONArray(DATA);
                    for (int i = 0; i < mJsonArray.length(); i++) {
                        JSONObject mJsonObject = mJsonArray.getJSONObject(i);
                        String DepartmentName = mJsonObject.getString(ORGANIZATION_NAME);
                        String DetpartmentID = mJsonObject.getString(ORGANIZATION_ID);
                        arrListDepartmentSearch.add(new ReceivePerson(DepartmentName, DetpartmentID));
                    }
                    for (int i = 0; i < arrListDepartmentSearch.size(); i++) {
                        String Name = arrListDepartmentSearch.get(i).getReceivePersonID();
                        arrNameDepartment.add(Name);

                    }
                    mStatisticalFmView.getSrrListDepartmentSearch(arrListDepartmentSearch);
                    DepartmentAdapter adapter = new DepartmentAdapter(context, arrNameDepartment, android.R.layout.simple_spinner_dropdown_item);
                    mStatisticalFmView.setAdatperDepartmentName(adapter, arrNameDepartment);
                } catch (JSONException e) {
                    mStatisticalFmView.showErrorToast();
                    e.printStackTrace();
                }
                break;
            case SEARCH_DOCUMENT_TYPE:
                break;
            default:
                break;
        }
        mStatisticalFmView.closeProgress();
    }

    @Override
    public void onTaskCompleted(String s, String CaseRequest) {
        refreshUI(s, CaseRequest);
    }


    private JSONObject addJsonRequestStatistical(String MODULEs, String TYPE, String dateFirst, String dateLast) {
        JSONObject mJsonObject = new JSONObject();
        try {
            mJsonObject.put(MODULE, MODULEs);
            mJsonObject.put(NEOTYPE, TYPE);
            JSONObject mJsonObjectData = new JSONObject();
            if (TYPE.equals(TYPE_STATISTICAL_PERSON_JOIN) || TYPE.equals(TYPE_STATISTICAL_PERSON_JOIN_ARISE))
                mJsonObjectData.put(ORGANIZATION_EDITOR_ID, Integer.parseInt(mStatisticalFmView.getOrganizationId()));
            mJsonObjectData.put(FROM_DATE, getMilisecondDay(dateFirst));
            mJsonObjectData.put(TO_DATE, getMilisecondDay(dateLast));
            mJsonObject.put(DATA, mJsonObjectData.toString());
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
        mStatisticalFmView.closeProgress();
        mStatisticalFmView.ToastError(s);
    }
}

//    private void refreshUI(String s, String CaseRequest) {
//        switch (CaseRequest) {
//            case STA_COMING_FULL_DPM:
//                Log.d(TAG, s);
//                try {
//                    JSONObject mJsonObjectFullData = new JSONObject(s);
//                    JSONObject mjsonObjectData = mJsonObjectFullData.getJSONObject(DATA);
//                    StatisticalFmRow mStatisticalRows = new StatisticalFmRow(mjsonObjectData.getInt(IN_PROCESS_ON_TIME_CODE),
//                            mjsonObjectData.getInt(PROCESSED_ON_TIME_CODE),
//                            mjsonObjectData.getInt(IN_PROCESS_LATE_CODE),
//                            mjsonObjectData.getInt(TOTAL_CODE),
//                            mjsonObjectData.getInt(PROCESSED_LATE),
//                            mjsonObjectData.getInt(PROCESSED_ON_TIME),
//                            mjsonObjectData.getInt(TOTAL),
//                            mjsonObjectData.getInt(IN_PROCESS_ON_TIME),
//                            mjsonObjectData.getInt(RATE_ON_TIME));
//                    mStatisticalFmView.setStatisticalRow(mStatisticalRows);
//                    mStatisticalFmView.setViewStatistical(mStatisticalRows.getDocDemurrage_Full() + "",
//                            "Tổng " + mStatisticalRows.getTotalDoc_Full() + " Văn bản",
//                            mStatisticalRows.getDocNotProcess_Full() + "",
//                            mStatisticalRows.getDocProcessedOnTime_Full() + "",
//                            mStatisticalRows.getRateOnTime() + "%",
//                            "(" + dateFirst + " - " + dateLast + ")"
//
//                    );
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
////                new NeoTask(context, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(context, STA_COMING_LIST_DPM, addJsonRequestStatistical(TYPE_STATISTICAL_DEPARTMENT).toString(), getLink() + URL_CENTER_6_1));
////                new StatisticalDepartment().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, getLink() + URL_CENTER_6_1);
//                new VolleyTask(context, STA_COMING_LIST_DPM, addJsonRequestStatistical(TYPE_STATISTICAL_DEPARTMENT), this);
//                break;
//            case STA_COMING_LIST_DPM:
//                rowsArrayList = new ArrayList<StatisticalRows>();
//                Log.d(TAG, s);
//                try {
//                    JSONObject mJsonObject = new JSONObject(s);
//                    JSONArray mJsonArray = mJsonObject.getJSONArray(DATA);
//                    for (int i = 0; i < mJsonArray.length(); i++) {
//                        JSONObject mObject = mJsonArray.getJSONObject(i);
//                        String organizationName = mObject.getString(ORGANIZATION_NAME);
//                        int total = mObject.getInt(DOC_TOTAL);
//                        int docDemurrage = mObject.getInt(DOC_DEMURRAGE);
//                        int docNotProcessType = mObject.getInt(DOC_NOT_PROCESS_TYPE);
//                        int docProcessedOnTimeType = mObject.getInt(DOC_PROSESSED_ON_TIME_TYPE);
//                        int docDemurrageType = mObject.getInt(DOC_DEMURRAGE_TYPE);
//                        int totalDocType = mObject.getInt(TOTAL_DOC_TYPE);
//                        long organizationId = mObject.getLong(ORGANIZATION_ID);
//                        rowsArrayList.add(new StatisticalRows(organizationName, total, docDemurrage, docNotProcessType, docProcessedOnTimeType,
//                                docDemurrageType, totalDocType, organizationId));
//                    }
//                    mStatisticalFmView.setListDepartment(rowsArrayList, "(" + dateFirst + " - " + dateLast + ")");
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                mStatisticalFmView.closeProgress();
//                break;
//        }
//    }
//    class ReadJsonStatisticalFullDeparment extends AsyncTask<String, Integer, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            String mRequest = addJsonRequestStatistical(TYPE_STATISTICAL);
//            return eventPostRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, context), mRequest, getUserid(), getPass());
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            Log.d(TAG, s);
//            try {
//                JSONObject mJsonObjectFullData = new JSONObject(s);
//                JSONObject mjsonObjectData = mJsonObjectFullData.getJSONObject(DATA);
//                StatisticalFmRow mStatisticalRows = new StatisticalFmRow(mjsonObjectData.getInt(DOC_NOT_PROCESS_TYPE),
//                        mjsonObjectData.getInt(DOC_PROSESSED_ON_TIME_TYPE),
//                        mjsonObjectData.getInt(DOC_DEMURRAGE_TYPE),
//                        mjsonObjectData.getInt(TOTAL_DOC_TYPE),
//                        mjsonObjectData.getInt(DOC_DEMURRAGE),
//                        mjsonObjectData.getInt(DOC_PROCESSED_ON_TIME),
//                        mjsonObjectData.getInt(DOC_TOTAL),
//                        mjsonObjectData.getInt(DOC_NOT_PROCESS),
//                        mjsonObjectData.getInt(RATE_ON_TIME));
//                mStatisticalFmView.setStatisticalRow(mStatisticalRows);
//                mStatisticalFmView.setViewStatistical(mStatisticalRows.getDocDemurrage_Full() + "",
//                        "Tổng " + mStatisticalRows.getTotalDoc_Full() + " Văn bản",
//                        mStatisticalRows.getDocNotProcess_Full() + "",
//                        mStatisticalRows.getDocProcessedOnTime_Full() + "",
//                        mStatisticalRows.getRateOnTime() + "%",
//                        "(" + dateFirst + " - " + dateLast + ")"
//
//                );
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            new StatisticalDepartment().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, getLink() + URL_CENTER_6_1);
//            super.onPostExecute(s);
//        }
//    }
//    class StatisticalDepartment extends AsyncTask<String, Integer, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            String mRequest = addJsonRequestStatistical(TYPE_STATISTICAL_DEPARTMENT);
////            return mOfficalActivity.makePostRequest(params[0], mRequest, mOfficalActivity.getUserid(), mOfficalActivity.getPass());
//            return eventPostRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, context), mRequest, getUserid(), getPass());
//
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            rowsArrayList = new ArrayList<StatisticalRows>();
//            Log.d(TAG, s);
//            try {
//                JSONObject mJsonObject = new JSONObject(s);
//                JSONArray mJsonArray = mJsonObject.getJSONArray(DATA);
//                for (int i = 0; i < mJsonArray.length(); i++) {
//                    JSONObject mObject = mJsonArray.getJSONObject(i);
//                    String organizationName = mObject.getString(ORGANIZATION_NAME);
//                    int total = mObject.getInt(DOC_TOTAL);
//                    int docDemurrage = mObject.getInt(DOC_DEMURRAGE);
//                    int docNotProcessType = mObject.getInt(DOC_NOT_PROCESS_TYPE);
//                    int docProcessedOnTimeType = mObject.getInt(DOC_PROSESSED_ON_TIME_TYPE);
//                    int docDemurrageType = mObject.getInt(DOC_DEMURRAGE_TYPE);
//                    int totalDocType = mObject.getInt(TOTAL_DOC_TYPE);
//                    long organizationId = mObject.getLong(ORGANIZATION_ID);
//                    rowsArrayList.add(new StatisticalRows(organizationName, total, docDemurrage, docNotProcessType, docProcessedOnTimeType,
//                            docDemurrageType, totalDocType, organizationId));
//                }
//                mStatisticalFmView.setListDepartment(rowsArrayList, "(" + dateFirst + " - " + dateLast + ")");
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            mStatisticalFmView.closeProgress();
//            super.onPostExecute(s);
//        }
//    }