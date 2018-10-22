package com.newsaigonsoft.neoegov.Fragment.Home;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.google.gson.JsonSyntaxException;
import com.newsaigonsoft.neoegov.AVolleyManager.VolleyTask;
import com.newsaigonsoft.neoegov.AVolleyManager.VolleyTaskCompletedListenner;
import com.newsaigonsoft.neoegov.Adapter.ScheduleAdapter;
import com.newsaigonsoft.neoegov.Adapter.ScheduleDayAdapter;
import com.newsaigonsoft.neoegov.AsynTaskManager.AsyncTaskCompleteListener;
import com.newsaigonsoft.neoegov.GsonObject.GsonHomeHotLine;
import com.newsaigonsoft.neoegov.GsonObject.GsonHomeStatis;
import com.newsaigonsoft.neoegov.GsonObject.GsonHomeTask;
import com.newsaigonsoft.neoegov.GsonObject.GsonRemindPerson;
import com.newsaigonsoft.neoegov.GsonObject.GsonScheduleDay;
import com.newsaigonsoft.neoegov.GsonObject.GsonStaHome;
import com.newsaigonsoft.neoegov.ScheduleDetailActivity.ScheduleDetailActivity;
import com.newsaigonsoft.neoegov.Subjects.HomeLookupRow;
import com.newsaigonsoft.neoegov.Subjects.HotLineRow;
import com.newsaigonsoft.neoegov.Subjects.ResuiltObject;
import com.newsaigonsoft.neoegov.Subjects.ScheduleRow;
import com.newsaigonsoft.neoegov.Subjects.SumManager;
import com.newsaigonsoft.neoegov.Subjects.TaskRemind;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DATA;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.END_TIME;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.FROM_DATE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.HOTLINE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MODULE_LOOKUP_DOCUMENT;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MODULE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.NEOTYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MODULE_PROCESSING_WORKING;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.SCHEDULE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.SCHEDULE_CHAIR_MAN;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.SCHEDULE_CONTENT;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.SCHEDULE_DAY;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.SCHEDULE_PLACE_NAME;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.SCHEDULE_START_DATE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.SCHEDULE_STATUS;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.START_TIME;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TO_DATE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_LOOKUP_DOCUMENT_COMING_PERSON;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_LOOKUP_DOCUMENT_SENT_PERSON;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_ORGAN;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_REMIND_PERSON;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_TASK_DIRECT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DATA_COMING_DOCUMENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DATA_SENT_DOCUMENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DATE_1_YEARS_FOR_TEST;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.FORWARD_RESUILD;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.HOTLINE_OR;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.REMIND_PERSON;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SCHEDULE_DETAIL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SCHEDULE_HOME;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.STATIS_DOC_COMING;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.STATIS_DOC_SENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAG;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TASK_DETECT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.nULL_STRING;

/**
 * Created by Vinh on 10/20/2017.
 */

public class HomeFmLogic extends SumManager implements HomeFmImp, AsyncTaskCompleteListener<ResuiltObject>, VolleyTaskCompletedListenner<ResuiltObject> {
    HomeView mHomeView;
    Context Context;
    String dateLast, dateFirst;
    ArrayList<ScheduleRow> arrayListSchedule;
    TaskRemind mTaskRemind;
    HomeLookupRow mHomeLookupRow = new HomeLookupRow();
    String dateRequest;
    List<GsonScheduleDay.DataBean> arrSchedule;

    public HomeFmLogic(HomeView mHomeView, Context mContext) {
        this.mHomeView = mHomeView;
        this.Context = mContext;
        getInforAccountFromShareReferenced(mContext);
    }


    @Override
    public void RequestData() {
//        new NeoTask(Context, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(Context, STATIS_DOC_COMING, addJsonRequestStatistical(TYPE_LOOKUP_DOCUMENT_COMING_PERSON).toString(), getLink() + URL_CENTER_6_1));
//        new NeoTask(Context, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(Context, STATIS_DOC_SENT, addJsonRequestStatistical(TYPE_LOOKUP_DOCUMENT_SENT_PERSON).toString(), getLink() + URL_CENTER_6_1));
//        new NeoTask(Context, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(Context, TASK_DETECT, addJsonTaskDerect().toString(), getLink() + URL_CENTER_6_1));
//        new NeoTask(Context, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(Context, HOTLINE_OR, addJsonHotLineOrGan().toString(), getLink() + URL_CENTER_6_1));
//        new NeoTask(Context, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(Context, SCHEDULE_HOME, addJsonCheduleDay().toString(), getLink() + URL_CENTER_6_1));
        // TODO: 3/16/2018
        new VolleyTask(Context, REMIND_PERSON, addJsonRequestRemindPerson(), this);
        new VolleyTask(Context, STATIS_DOC_COMING, addJsonRequestStatistical(TYPE_LOOKUP_DOCUMENT_COMING_PERSON), this);
        new VolleyTask(Context, STATIS_DOC_SENT, addJsonRequestStatistical(TYPE_LOOKUP_DOCUMENT_SENT_PERSON), this);
        new VolleyTask(Context, TASK_DETECT, addJsonTaskDerect(), this);
        new VolleyTask(Context, HOTLINE_OR, addJsonHotLineOrGan(), this);
    }

    private JSONObject addJsonRequestRemindPerson() {
        JSONObject mJsonObject = new JSONObject();
        try {
            mJsonObject.put(MODULE, MODULE_PROCESSING_WORKING);
            mJsonObject.put(NEOTYPE, TYPE_REMIND_PERSON);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return mJsonObject;

    }

    private JSONObject addJsonCheduleDay(String date) {
        JSONObject mJsonObject = new JSONObject();
        JSONObject mJsonObjectData = new JSONObject();
//        Calendar mCalendar = Calendar.getInstance();
        try {
            mJsonObject.put(MODULE, SCHEDULE);
//            if (getDefaults(URL_CENTER, Context).equals(URL_CENTER_6_1)) {
//                mJsonObject.put(NEOTYPE, SCHEDULE_DAY);
////                mJsonObjectData.put(VIEW_DAY_CAL, getMilisecondDay(mSimpleDateFormat.format(mCalendar.getTime())));
//                mJsonObjectData.put(VIEW_DAY_CAL, getMilisecondDay(date));
//                mJsonObject.put(DATA, mJsonObjectData.toString());
//            } else {
                mJsonObject.put(NEOTYPE, SCHEDULE_DAY);
                mJsonObjectData.put(START_TIME, getMilisecondDay(date));
                mJsonObjectData.put(END_TIME, getMilisecondDay(date));
                mJsonObject.put(DATA, mJsonObjectData.toString());
//            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mJsonObject;
    }

    @Override
    public void getOneMonth() {
        Calendar mCalendar = Calendar.getInstance();
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(FORMATDATE);
        dateLast = mSimpleDateFormat.format(mCalendar.getTime()) + "";
        Calendar mCalendarFirstDay = Calendar.getInstance();
        mCalendarFirstDay.add(Calendar.MONTH, -1);
        dateFirst = mSimpleDateFormat.format(mCalendarFirstDay.getTime()) + "";
        Log.d(TAG, dateLast + " " + dateFirst);
        mHomeView.setComingTime("(" + dateFirst + " - " + dateLast + ")");
        mHomeView.setSentTime("(" + dateFirst + " - " + dateLast + ")");
        mHomeView.setTaskTime("(" + dateFirst + " - " + dateLast + ")");
    }

    @Override
    public void setDataForHomeScreenOfLine() {
        GsonHomeStatis mGsonHomeStaComing = getGson().fromJson(readFromFile(Context, DATA_COMING_DOCUMENT), GsonHomeStatis.class);
        GsonHomeStatis.DataBean mDataBeanHomeComing = mGsonHomeStaComing.getData();
        if (mDataBeanHomeComing == null) {
            mHomeView.closeProgress();
            return;
        }
        int docDemurrage = mDataBeanHomeComing.getDocDemurrage();
        int docNotSeen = mDataBeanHomeComing.getDocNotSeen();
        int totalDoc = mDataBeanHomeComing.getTotalDoc();
        int docNotProcess = mDataBeanHomeComing.getDocNotProcess();
        mHomeView.setDataForHomeScreenComing(totalDoc, docNotProcess, docNotSeen, docDemurrage);
        GsonHomeStatis mGsonHomeStaSent = getGson().fromJson(readFromFile(Context, DATA_SENT_DOCUMENT), GsonHomeStatis.class);
        GsonHomeStatis.DataBean mDataBeanSent = mGsonHomeStaSent.getData();
        int docDemurrageSend = mDataBeanSent.getDocDemurrage();
        int docNotSeenSend = mDataBeanSent.getDocNotSeen();
        int totalDocSend = mDataBeanSent.getTotalDoc();
        int docNotProcessSend = mDataBeanSent.getDocNotProcess();
        mHomeView.setDataForHomeScreenSent(totalDocSend, docNotProcessSend, docNotSeenSend, docDemurrageSend);
        mHomeView.closeProgress();
    }

    @Override
    public void setMsgAfterTrainfer(String Resuilt, String InputComBack, String DocumentNumber, String inputName) {
        if (Resuilt != null) {
            mHomeView.showSnackBar(getMsgAfterTrainfer(Resuilt, InputComBack, DocumentNumber, Context, inputName));
            mHomeView.getIntentFromActivity().removeExtra(FORWARD_RESUILD);
            mHomeView.setNullCreenComeBack();
        }
    }

    @Override
    public void requestSchedule(String format) {
        dateRequest = format;
        new VolleyTask(Context, SCHEDULE_HOME, addJsonCheduleDay(format), this);
    }

    @Override
    public void OpenDetail(int position) {
        Intent intent  = new Intent(Context, ScheduleDetailActivity.class);
        intent.putExtra(SCHEDULE_DETAIL, getGson().toJson(arrSchedule.get(position)));
        Context.startActivity(intent);
    }


    private void refreshUI(String s, String CaseRequest) {
        Log.d(TAG, s);
        try {

            switch (CaseRequest) {
                case REMIND_PERSON:
                    Log.d(TAG, s);
                    try{
                        GsonRemindPerson mGsonRemindPerson = getGson().fromJson(s, GsonRemindPerson.class);
                        mHomeView.setVisibleRemind(mGsonRemindPerson.getCode() !=0 ? View.GONE : View.VISIBLE);
                        GsonRemindPerson.DataBean mDataRemindPerson = mGsonRemindPerson.getData();
                        mHomeView.setRemindPersonView(mDataRemindPerson.getInProcess(), mDataRemindPerson.getPreLate(), mDataRemindPerson.getLate());
                    }catch (Exception e){
                        mHomeView.setVisibleRemind(View.GONE);
                    }
                    break;
                case STATIS_DOC_COMING:
                    Log.d(TAG, s);
                    try{
                        GsonStaHome mGsonStaHomeComing = getGson().fromJson(s, GsonStaHome.class);
                        mHomeView.setVisibleRecieveTable(mGsonStaHomeComing.getCode() != 0 ? View.GONE : View.VISIBLE);
                        GsonStaHome.DataBean mDataComing = mGsonStaHomeComing.getData();
                        if (!s.equals(nULL_STRING) && !s.equals("Error!")) {
                            writeToFile(s, Context, DATA_COMING_DOCUMENT);
                        }
//                    GsonHomeStatis mGsonHomeStatisComing = getGson().fromJson(s, GsonHomeStatis.class);
//                    GsonHomeStatis.DataBean mDataBean = mGsonHomeStatisComing.getData();
//                    if (mGsonHomeStatisComing.getData() != null) {
//                        mHomeLookupRow.setDocDemurrage_Coming(mDataBean.getDocDemurrage());
//                        mHomeLookupRow.setDocNotSeen_Coming(mDataBean.getDocNotSeen());
//                        mHomeLookupRow.setDocNotProcess_Coming(mDataBean.getDocNotProcess());
//                        mHomeLookupRow.setTotalDoc_Coming(mDataBean.getTotalDoc());
//                        mHomeLookupRow.setDocNotProcessType_Coming(mDataBean.getDocNotProcessType());
//                        mHomeLookupRow.setDocNotSeenType_Coming(mDataBean.getDocNotSeenType());
//                        mHomeLookupRow.setDocDemurrageType_Coming(mDataBean.getDocDemurrageType());
//                        mHomeLookupRow.setTotalDocType_Coming(mDataBean.getTotalDocType());
//                        mHomeView.setDataForHomeScreenComing(mHomeLookupRow.getTotalDoc_Coming()
//                                , mHomeLookupRow.getDocNotProcess_Coming(), mHomeLookupRow.getDocNotSeen_Coming(), mHomeLookupRow.getDocDemurrage_Coming());
//                        Calendar mCalendar = Calendar.getInstance();
//                        mHomeView.setDaySchedule("" +
////                        "(" + getNameOfDay(mCalendar.get(Calendar.DAY_OF_WEEK)) + ", " +
//                                "(" + mSimpleDateFormat.format(mCalendar.getTime()) + ")");
////                tvDaySchedule.setText("(" + getNameOfDay(mCalendar.get(Calendar.DAY_OF_WEEK)) + ", "
////                        + mSimpleDateFormat.format(mCalendar.getTime()) + ")");
////                tvDaySchedule.setText("("
////                        + mSimpleDateFormat.format(mCalendar.getTime()) + ")");
//                    }
                        if (mGsonStaHomeComing.getData() != null) {
                            mHomeLookupRow.setDocDemurrage_Coming(mDataComing.getInProcessLate());
                            mHomeLookupRow.setDocNotSeen_Coming(mDataComing.getNotSeen());
                            mHomeLookupRow.setDocNotProcess_Coming(mDataComing.getInProcess());
                            mHomeLookupRow.setTotalDoc_Coming(mDataComing.getTotal());
                            mHomeLookupRow.setDocNotProcessType_Coming(mDataComing.getInProcessCode());
                            mHomeLookupRow.setDocNotSeenType_Coming(mDataComing.getNotSeenCode());
                            mHomeLookupRow.setDocDemurrageType_Coming(mDataComing.getInProcessLateCode());
                            mHomeLookupRow.setTotalDocType_Coming(mDataComing.getTotalCode());
                            mHomeView.setDataForHomeScreenComing(mHomeLookupRow.getTotalDoc_Coming()
                                    , mHomeLookupRow.getDocNotProcess_Coming(), mHomeLookupRow.getDocNotSeen_Coming(), mHomeLookupRow.getDocDemurrage_Coming());
                            Calendar mCalendar = Calendar.getInstance();
                            mHomeView.setDaySchedule("" +
//                        "(" + getNameOfDay(mCalendar.get(Calendar.DAY_OF_WEEK)) + ", " +
                                    "(" + mSimpleDateFormat.format(mCalendar.getTime()) + ")");
//                tvDaySchedule.setText("(" + getNameOfDay(mCalendar.get(Calendar.DAY_OF_WEEK)) + ", "
//                        + mSimpleDateFormat.format(mCalendar.getTime()) + ")");
//                tvDaySchedule.setText("("
//                        + mSimpleDateFormat.format(mCalendar.getTime()) + ")");
                        }
                    }catch (Exception e){
                        mHomeView.setVisibleRecieveTable(View.GONE);
                    }
                    break;
                case STATIS_DOC_SENT:
                    Log.d(TAG, s);
                    try{
                        GsonStaHome mGsonStaHomeSent = getGson().fromJson(s, GsonStaHome.class);
                        mHomeView.setVisibleSendTable(mGsonStaHomeSent.getCode() != 0 ? View.GONE : View.VISIBLE);
                        GsonStaHome.DataBean mDataSent = mGsonStaHomeSent.getData();
                        if (!s.equals(nULL_STRING) && !s.equals("Error!")) {
                            writeToFile(s, Context, DATA_SENT_DOCUMENT);
                        }
                        if (mGsonStaHomeSent.getData() != null) {
                            mHomeLookupRow.setDocDemurrage_Sent(mDataSent.getInProcessLate());
                            mHomeLookupRow.setDocNotSeen_Sent(mDataSent.getNotSeen());
                            mHomeLookupRow.setTotalDoc_Sent(mDataSent.getTotal());
                            mHomeLookupRow.setDocNotProcess_Sent(mDataSent.getInProcess());
                            mHomeLookupRow.setDocNotProcessType_Sent(mDataSent.getInProcessCode());
                            mHomeLookupRow.setDocNotSeenType_Sent(mDataSent.getNotSeenCode());
                            mHomeLookupRow.setDocDemurrageType_Sent(mDataSent.getInProcessLateCode());
                            mHomeLookupRow.setTotalDocType_Sent(mDataSent.getTotalCode());
                            mHomeView.setDataForHomeScreenSent(mHomeLookupRow.getTotalDoc_Sent(), mHomeLookupRow.getDocNotProcess_Sent(), mHomeLookupRow.getDocNotSeen_Sent(), mHomeLookupRow.getDocDemurrage_Sent());
                            mHomeView.setHomeLookupRow(mHomeLookupRow);
                        }
                    }catch (Exception e){
                        mHomeView.setVisibleSendTable(View.GONE);
                    }
                    break;
                case TASK_DETECT:
                    Log.d(TAG, s);
//                    GsonHomeTask mGsonHomeTask = getGson().fromJson(s, GsonHomeTask.class);
//                    GsonHomeTask.DataBean mDataBeanTask = mGsonHomeTask.getData();
//                    if (mGsonHomeTask.getData() != null) {
//                        mTaskRemind = new TaskRemind(mDataBeanTask.getReported(), mDataBeanTask.getReportedType(), mDataBeanTask.getProcess(), mDataBeanTask.getProcessType(), mDataBeanTask.getProcessOnTime(), mDataBeanTask.getProcessOnTimeType(), mDataBeanTask.getProcessNearDemurrage(), mDataBeanTask.getProcessNearDemurrageType(), mDataBeanTask.getProcessDemurrage(), mDataBeanTask.getProcessDemurrageType(), mDataBeanTask.getTotal(), mDataBeanTask.getTotalType());
//                        mHomeView.setTaskRemind(mTaskRemind);
//                        mHomeView.setTaskHomeNumber("Tổng " + mTaskRemind.getTotal() + " nhiệm vụ", mTaskRemind.getProcess() + "", mTaskRemind.getProcessOnDue() + "", mTaskRemind.getProcessNearDemurrage() + "", mTaskRemind.getProcessDemurrage() + "", mTaskRemind.getReported() + "");
//                        mHomeView.showLayoutTask();
//                    }
                    try{
                        GsonHomeTask mGsonHomeTask = getGson().fromJson(s, GsonHomeTask.class);
                        mHomeView.setVisibleHomeTask(mGsonHomeTask.getCode() != 0 ? View.GONE : View.VISIBLE);
                        mHomeView.setVisibleTimeHomeTask(mGsonHomeTask.getCode() != 0 ? View.GONE : View.VISIBLE);
                        GsonHomeTask.DataBean mDataBeanTask = mGsonHomeTask.getData();
                        if (mGsonHomeTask.getData() != null) {
                            mTaskRemind = new TaskRemind(mDataBeanTask.getReported(), mDataBeanTask.getReportedCode(), mDataBeanTask.getInProcess(), mDataBeanTask.getInProcessCode(), mDataBeanTask.getInProcessOnTime(), mDataBeanTask.getInProcessOnTimeCode(), mDataBeanTask.getInProcessNearLate(), mDataBeanTask.getInProcessNearLateCode(), mDataBeanTask.getInProcessLate(), mDataBeanTask.getInProcessLateCode(), mDataBeanTask.getTotal(), mDataBeanTask.getTotalCode());
                            mHomeView.setTaskRemind(mTaskRemind);
                            mHomeView.setTaskHomeNumber("Tổng " + mTaskRemind.getTotal() + " nhiệm vụ", mTaskRemind.getProcess() + "", mTaskRemind.getProcessOnDue() + "", mTaskRemind.getProcessNearDemurrage() + "", mTaskRemind.getProcessDemurrage() + "", mTaskRemind.getReported() + "");
                            mHomeView.setTaskTotalTime("* Tổng hợp từ ngày: " + DATE_1_YEARS_FOR_TEST + " - " + dateLast);
                            mHomeView.showLayoutTask();
                        }
                    }catch (Exception e){
                        mHomeView.setVisibleHomeTask(View.GONE);
                        mHomeView.setVisibleTimeHomeTask(View.GONE);
                    }
                    break;
                case HOTLINE_OR:
                    Log.d(TAG, s);
                    try{
                        GsonHomeHotLine mGsonHomeHotLine = getGson().fromJson(s, GsonHomeHotLine.class);
                        mHomeView.setVisibleHotLineTable(mGsonHomeHotLine.getCode() != 0 ? View.GONE : View.VISIBLE);
                        GsonHomeHotLine.DataBean mDataBeanHotLine = mGsonHomeHotLine.getData();
                        if (mGsonHomeHotLine.getData() != null) {
                            HotLineRow mHotLineRow = new HotLineRow(mDataBeanHotLine.getOrganizationId(), mDataBeanHotLine.getOrganizationName(), mDataBeanHotLine.getInProcess(), mDataBeanHotLine.getInProcessCode(), mDataBeanHotLine.getInProcessOnDue(), mDataBeanHotLine.getInProcessOnDueCode(), mDataBeanHotLine.getInProcessLate(), mDataBeanHotLine.getInProcessLateCode());
                            mHomeView.getViewAndHotLineData(mHotLineRow);
                        }
                    }catch (Exception e){
                        mHomeView.setVisibleHotLineTable(View.GONE);
                    }
                    break;
                case SCHEDULE_HOME:
                    Log.d(TAG, s);
//                    if (getDefaults(URL_CENTER, Context).equals(URL_CENTER_6_1)) {
//                        arrayListSchedule = new ArrayList<ScheduleRow>();
//                        Log.d(TAG, s);
//                        try {
//                            JSONObject mObject = new JSONObject(s);
//                            JSONObject mJsonObjectData = mObject.getJSONObject(DATA);
//                            analysizSchedule(mJsonObjectData, MORNING_CAL);
//                            analysizSchedule(mJsonObjectData, AFTERNOON_CAL);
//                            analysizSchedule(mJsonObjectData, EVENING_CAL);
//                            ScheduleAdapter adapter = new ScheduleAdapter(Context, arrayListSchedule);
//                            mHomeView.setAdapterSchedule(adapter);
//                            mHomeView.showScheduleLayout();
//                            mHomeView.closeProgress();
//                        } catch (JSONException e) {
//                            mHomeView.closeProgress();
//                            mHomeView.hideScheduleLayout();
//                            e.printStackTrace();
//                        }
//                    } else {
                        try{
                            GsonScheduleDay mGsonScheduleDay = getGson().fromJson(s, GsonScheduleDay.class);
                            mHomeView.setVisibleScheduleLayout(mGsonScheduleDay.getCode() != 0 ? View.GONE : View.VISIBLE);
                            arrSchedule = mGsonScheduleDay.getData();
                            mHomeView.setAdapterScheduleDay(new ScheduleDayAdapter(Context, arrSchedule));
                            mHomeView.showScheduleLayout();
                            mHomeView.closeProgress();
                        }catch (Exception e) {
                            mHomeView.setVisibleScheduleLayout(View.GONE);
                            mHomeView.closeProgress();
                        }
//                    }
                    break;
            }
        } catch (IllegalStateException | JsonSyntaxException exception) {
            Log.d(TAG, "Module not found");
        }
    }

    @Override
    public void onTaskCompleted(String s, String CaseRequest) {
        refreshUI(s, CaseRequest);
    }

    public JSONObject addJsonTaskDerect() {
        JSONObject mJsonObject = new JSONObject();
        try {
            mJsonObject.put(MODULE, MODULE_LOOKUP_DOCUMENT);
            mJsonObject.put(NEOTYPE, TYPE_TASK_DIRECT);
            JSONObject mData = new JSONObject();
            mData.put(FROM_DATE, getMilisecondDay(DATE_1_YEARS_FOR_TEST));
            mData.put(TO_DATE, getMilisecondDay(dateLast));
            mJsonObject.put(DATA, mData);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return mJsonObject;
    }


    public JSONObject addJsonHotLineOrGan() {
        JSONObject mJsonObject = new JSONObject();
        try {
            mJsonObject.put(MODULE, HOTLINE);
            mJsonObject.put(NEOTYPE, TYPE_ORGAN);
            JSONObject mData = new JSONObject();
            mData.put(FROM_DATE, getMilisecondDay(DATE_1_YEARS_FOR_TEST));
            mData.put(TO_DATE, getMilisecondDay(dateLast));
            mJsonObject.put(DATA, mData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mJsonObject;
    }


    private JSONObject addJsonRequestStatistical(String Type) {
        JSONObject mJsonObject = new JSONObject();
        try {
            mJsonObject.put(MODULE, MODULE_LOOKUP_DOCUMENT);
            mJsonObject.put(NEOTYPE, Type);
            JSONObject mJsonObjectData = new JSONObject();
            mJsonObjectData.put(FROM_DATE, getMilisecondDay(dateFirst));
            mJsonObjectData.put(TO_DATE, getMilisecondDay(dateLast));
            mJsonObject.put(DATA, mJsonObjectData.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mJsonObject;
    }

    private void analysizSchedule(JSONObject mJsonObject, String DATE_TIME) {
        try {
            JSONArray mJsonArray = mJsonObject.getJSONArray(DATE_TIME);
            if (!mJsonArray.toString().equals("[]")) {
                for (int i = 0; i < mJsonArray.length(); i++) {
                    JSONObject mObject = mJsonArray.getJSONObject(i);
                    arrayListSchedule.add(new ScheduleRow(mObject.getString(SCHEDULE_START_DATE), mObject.getString(SCHEDULE_CONTENT), mObject.getString(SCHEDULE_CHAIR_MAN), mObject.getString(SCHEDULE_STATUS), mObject.getString(SCHEDULE_PLACE_NAME)));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onVolleyCompleted(String s, String CaseRequest) {
        refreshUI(s, CaseRequest);
    }

    @Override
    public void onVolleyError(String s) {
        mHomeView.closeProgress();
//        mHomeView.ToastError(s);
    }
    //    class ReadJsonHotLineOrGan extends AsyncTask<String, Integer, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            JSONObject mJsonObject = new JSONObject();
//            try {
//                mJsonObject.put(MODULE, HOTLINE);
//                mJsonObject.put(NEOTYPE, TYPE_ORGAN);
//                JSONObject mData = new JSONObject();
//                mData.put(FROM_DATE, getMilisecondDay(DATE_1_YEARS_FOR_TEST));
//                mData.put(TO_DATE, getMilisecondDay(dateLast));
//                mJsonObject.put(DATA, mData);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            return eventPostRequest(getModuleInfor(HOTLINE, Context), mJsonObject.toString(), getUserid(), getPass());
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            Log.d("VinhCNLog ", s);
//            try {
//                JSONObject mJsonObject = new JSONObject(s);
//                JSONObject mData = mJsonObject.getJSONObject(DATA);
//                HotLineRow mHotLineRow
//                        = new HotLineRow(mData.getLong(ORGANIZATION_ID),
//                        mData.getString(ORGANIZATION_NAME),
//                        mData.getInt(PROCESS),
//                        mData.getString(PROCESS_TYPE),
//                        mData.getInt(PROCESS_ON_DUE),
//                        mData.getString(PROCESS_ON_DUE_TYPE),
//                        mData.getInt(TASK_PROCESS_DEMURRAGE),
//                        mData.getString(PROCESS_DEMURRAGE_TYPE));
//                mHomeView.getViewAndHotLineData(mHotLineRow);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//            super.onPostExecute(s);
//        }
//    }
//    class ReadJsonStatisticsDocumentComing extends AsyncTask<String, Integer, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            String Request = addJsonRequestStatistical(TYPE_LOOKUP_DOCUMENT_COMING_PERSON);
//            return eventPostRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, Context), Request, getUserid(), getPass());
//
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            Log.d(TAG, s);
//            if (!s.equals(nULL_STRING) && !s.equals("Error!")) {
//                writeToFile(s, Context, DATA_COMING_DOCUMENT);
//            }
//            try {
//                JSONObject mJsonObject = new JSONObject(s);
//                JSONObject mJsonObjectData = mJsonObject.getJSONObject(DATA);
//                mHomeLookupRow.setDocDemurrage_Coming(mJsonObjectData.getInt(DOC_DEMURRAGE));
//                mHomeLookupRow.setDocNotSeen_Coming(mJsonObjectData.getInt(DOC_NOT_SEEN));
//                mHomeLookupRow.setDocNotProcess_Coming(mJsonObjectData.getInt(DOC_NOT_PROCESS));
//                mHomeLookupRow.setTotalDoc_Coming(mJsonObjectData.getInt(DOC_TOTAL));
//                mHomeLookupRow.setDocNotProcessType_Coming(mJsonObjectData.getInt(DOC_NOT_PROCESS_TYPE));
//                mHomeLookupRow.setDocNotSeenType_Coming(mJsonObjectData.getInt(DOC_NOT_SEEN_TYPE));
//                mHomeLookupRow.setDocDemurrageType_Coming(mJsonObjectData.getInt(DOC_DEMURRAGE_TYPE));
//                mHomeLookupRow.setTotalDocType_Coming(mJsonObjectData.getInt(TOTAL_DOC_TYPE));
//                mHomeView.setDataForHomeScreenComing(mHomeLookupRow.getTotalDoc_Coming()
//                        , mHomeLookupRow.getDocNotProcess_Coming(), mHomeLookupRow.getDocNotSeen_Coming(), mHomeLookupRow.getDocDemurrage_Coming());
//                Calendar mCalendar = Calendar.getInstance();
//                mHomeView.setDaySchedule("" +
////                        "(" + getNameOfDay(mCalendar.get(Calendar.DAY_OF_WEEK)) + ", " +
//                        "(" + mSimpleDateFormat.format(mCalendar.getTime()) + ")");
////                tvDaySchedule.setText("(" + getNameOfDay(mCalendar.get(Calendar.DAY_OF_WEEK)) + ", "
////                        + mSimpleDateFormat.format(mCalendar.getTime()) + ")");
////                tvDaySchedule.setText("("
////                        + mSimpleDateFormat.format(mCalendar.getTime()) + ")");
//            } catch (JSONException e) {
//                mHomeView.closeProgress();
//                ShowErrorToast(Context);
//                e.printStackTrace();
//            }
//            super.onPostExecute(s);
//        }
//    }

//    class ReadJsonStatisticsDocumentSent extends AsyncTask<String, Integer, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            String Request = addJsonRequestStatistical(TYPE_LOOKUP_DOCUMENT_SENT_PERSON);
//            return eventPostRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, Context), Request, getUserid(), getPass());
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            if (!s.equals(nULL_STRING) && !s.equals("Error!")) {
//                writeToFile(s, Context, DATA_SENT_DOCUMENT);
//            }
//            try {
//                JSONObject mJsonObject = new JSONObject(s);
//                JSONObject mJsonObjectData = mJsonObject.getJSONObject(DATA);
//                mHomeLookupRow.setDocDemurrage_Sent(mJsonObjectData.getInt(DOC_DEMURRAGE));
//                mHomeLookupRow.setDocNotSeen_Sent(mJsonObjectData.getInt(DOC_NOT_SEEN));
//                mHomeLookupRow.setTotalDoc_Sent(mJsonObjectData.getInt(DOC_TOTAL));
//                mHomeLookupRow.setDocNotProcess_Sent(mJsonObjectData.getInt(DOC_NOT_PROCESS));
//                mHomeLookupRow.setDocNotProcessType_Sent(mJsonObjectData.getInt(DOC_NOT_PROCESS_TYPE));
//                mHomeLookupRow.setDocNotSeenType_Sent(mJsonObjectData.getInt(DOC_NOT_SEEN_TYPE));
//                mHomeLookupRow.setDocDemurrageType_Sent(mJsonObjectData.getInt(DOC_DEMURRAGE_TYPE));
//                mHomeLookupRow.setTotalDocType_Sent(mJsonObjectData.getInt(TOTAL_DOC_TYPE));
//                mHomeView.setDataForHomeScreenSent(mHomeLookupRow.getTotalDoc_Sent(), mHomeLookupRow.getDocNotProcess_Sent(), mHomeLookupRow.getDocNotSeen_Sent(), mHomeLookupRow.getDocDemurrage_Sent());
//                mHomeView.setHomeLookupRow(mHomeLookupRow);
//                new ReadJsonScheduleDay().execute(getLink() + URL_CENTER_6_1);
//            } catch (JSONException e) {
//                mHomeView.closeProgress();
//                e.printStackTrace();
//            }
//
//            super.onPostExecute(s);
//        }
//    }
//    class ReadJsonTaskDerect extends AsyncTask<String, Integer, String> {
//        @Override
//        protected String doInBackground(String... params) {
//            JSONObject mJsonObject = new JSONObject();
//            try {
//                mJsonObject.put(MODULE, MODULE_LOOKUP_DOCUMENT);
//                mJsonObject.put(NEOTYPE, TYPE_TASK_DIRECT);
//                JSONObject mData = new JSONObject();
//                mData.put(FROM_DATE, getMilisecondDay(DATE_1_YEARS_FOR_TEST));
//                mData.put(TO_DATE, getMilisecondDay(dateLast));
//                mJsonObject.put(DATA, mData);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            return eventPostRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, Context), mJsonObject.toString(), getUserid(), getPass());
//
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            try {
//                JSONObject mJsonObject = new JSONObject(s);
//                JSONObject mData = mJsonObject.getJSONObject(DATA);
//                mTaskRemind = new TaskRemind(mData.getInt(TASK_REPORTED), mData.getInt(REPORTED_TYPE), mData.getInt(TASK_PROCESS), mData.getInt(PROCESS_TYPE), mData.getInt(TASK_PROCESS_ON_TIME), mData.getInt(PROCESS_ON_TIME_TYPE), mData.getInt(TASK_PROCESS_NEAR_DEMURRAGE), mData.getInt(PROCESS_NEAR_DEMURRAGE_TYPE), mData.getInt(TASK_PROCESS_DEMURRAGE), mData.getInt(PROCESS_DEMURRAGE_TYPE), mData.getInt(TOTAL), mData.getInt(TOTAL_TYPE));
//                mHomeView.setTaskRemind(mTaskRemind);
//                mHomeView.setTaskHomeNumber("Tổng " + mTaskRemind.getTotal() + " nhiệm vụ", mTaskRemind.getProcess() + ""
//                        , mTaskRemind.getProcessOnDue() + "", mTaskRemind.getProcessNearDemurrage() + "", mTaskRemind.getProcessDemurrage() + "", mTaskRemind.getReported() + "");
//                mHomeView.showLayoutTask();
//            } catch (JSONException e) {
//                mHomeView.hideLayoutTask();
//                e.printStackTrace();
//            }
//            super.onPostExecute(s);
//        }
//    }
//    class ReadJsonScheduleDay extends AsyncTask<String, Integer, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            JSONObject mJsonObject = new JSONObject();
//            try {
//                Calendar mCalendar = Calendar.getInstance();
//                String Daily = mSimpleDateFormat.format(mCalendar.getTime());
//                mJsonObject.put(MODULE, SCHEDULE);
//                mJsonObject.put(NEOTYPE, SCHEDULE_DAY);
//                JSONObject mJsonObjectData = new JSONObject();
//                mJsonObjectData.put(VIEW_DAY_CAL, getMilisecondDay(Daily));
//                mJsonObject.put(DATA, mJsonObjectData.toString());
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
////            return mOfficalActivity.makePostRequest(params[0], mJsonObject.toString(), mOfficalActivity.getUserid(), mOfficalActivity.getPass());
//            return eventPostRequest(getModuleInfor(SCHEDULE, Context), mJsonObject.toString(), getUserid(), getPass());
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            arrayListSchedule = new ArrayList<ScheduleRow>();
//            Log.d(TAG, s);
//            try {
//                JSONObject mObject = new JSONObject(s);
//                JSONObject mJsonObjectData = mObject.getJSONObject(DATA);
//                analysizSchedule(mJsonObjectData, MORNING_CAL);
//                analysizSchedule(mJsonObjectData, AFTERNOON_CAL);
//                analysizSchedule(mJsonObjectData, EVENING_CAL);
//                ScheduleAdapter adapter = new ScheduleAdapter(Context, arrayListSchedule);
//                mHomeView.setAdapterSchedule(adapter);
//                mHomeView.showScheduleLayout();
//                mHomeView.closeProgress();
//            } catch (JSONException e) {
//                mHomeView.closeProgress();
//                mHomeView.hideScheduleLayout();
//                e.printStackTrace();
//            }
//            super.onPostExecute(s);
//        }
//    }
//private void refreshUI(String s, String CaseRequest){
//    Log.d(TAG, s);
//    switch (CaseRequest) {
//        case STATIS_DOC_COMING:
//            if (!s.equals(nULL_STRING) && !s.equals("Error!")) {
//                writeToFile(s, Context, DATA_COMING_DOCUMENT);
//            }
//            try {
////                    JSONObject mJsonObject = new JSONObject(s);
////                    JSONObject mJsonObjectData = mJsonObject.getJSONObject(DATA);
//                GsonHomeStatis mGsonHomeStatisComing = getGson().fromJson(s, GsonHomeStatis.class);
//                GsonHomeStatis.DataBean mDataBean = mGsonHomeStatisComing.getData();
////                    mHomeLookupRow.setDocDemurrage_Coming(mJsonObjectData.getInt(DOC_DEMURRAGE));
//                mHomeLookupRow.setDocDemurrage_Coming(mDataBean.getDocDemurrage());
////                    mHomeLookupRow.setDocNotSeen_Coming(mJsonObjectData.getInt(DOC_NOT_SEEN));
//                mHomeLookupRow.setDocNotSeen_Coming(mDataBean.getDocNotSeen());
////                    mHomeLookupRow.setDocNotProcess_Coming(mJsonObjectData.getInt(DOC_NOT_PROCESS));
//                mHomeLookupRow.setDocNotProcess_Coming(mDataBean.getDocNotProcess());
////                    mHomeLookupRow.setTotalDoc_Coming(mJsonObjectData.getInt(DOC_TOTAL));
//                mHomeLookupRow.setTotalDoc_Coming(mDataBean.getTotalDoc());
////                    mHomeLookupRow.setDocNotProcessType_Coming(mJsonObjectData.getInt(DOC_NOT_PROCESS_TYPE));
//                mHomeLookupRow.setDocNotProcessType_Coming(mDataBean.getDocNotProcessType());
////                    mHomeLookupRow.setDocNotSeenType_Coming(mJsonObjectData.getInt(DOC_NOT_SEEN_TYPE));
//                mHomeLookupRow.setDocNotSeenType_Coming(mDataBean.getDocNotSeenType());
////                    mHomeLookupRow.setDocDemurrageType_Coming(mJsonObjectData.getInt(DOC_DEMURRAGE_TYPE));
//                mHomeLookupRow.setDocDemurrageType_Coming(mDataBean.getDocDemurrageType());
////                    mHomeLookupRow.setTotalDocType_Coming(mJsonObjectData.getInt(TOTAL_DOC_TYPE));
//                mHomeLookupRow.setTotalDocType_Coming(mDataBean.getTotalDocType());
//                mHomeView.setDataForHomeScreenComing(mHomeLookupRow.getTotalDoc_Coming()
//                        , mHomeLookupRow.getDocNotProcess_Coming(), mHomeLookupRow.getDocNotSeen_Coming(), mHomeLookupRow.getDocDemurrage_Coming());
//                Calendar mCalendar = Calendar.getInstance();
//                mHomeView.setDaySchedule("" +
////                        "(" + getNameOfDay(mCalendar.get(Calendar.DAY_OF_WEEK)) + ", " +
//                        "(" + mSimpleDateFormat.format(mCalendar.getTime()) + ")");
////                tvDaySchedule.setText("(" + getNameOfDay(mCalendar.get(Calendar.DAY_OF_WEEK)) + ", "
////                        + mSimpleDateFormat.format(mCalendar.getTime()) + ")");
////                tvDaySchedule.setText("("
////                        + mSimpleDateFormat.format(mCalendar.getTime()) + ")");
//            } catch (JSONException e) {
//                mHomeView.closeProgress();
//                ShowErrorToast(Context);
//                e.printStackTrace();
//            }
//            break;
//        case STATIS_DOC_SENT:
//            if (!s.equals(nULL_STRING) && !s.equals("Error!")) {
//                writeToFile(s, Context, DATA_SENT_DOCUMENT);
//            }
//            try {
//                JSONObject mJsonObject = new JSONObject(s);
//                JSONObject mJsonObjectData = mJsonObject.getJSONObject(DATA);
//                mHomeLookupRow.setDocDemurrage_Sent(mJsonObjectData.getInt(DOC_DEMURRAGE));
//                mHomeLookupRow.setDocNotSeen_Sent(mJsonObjectData.getInt(DOC_NOT_SEEN));
//                mHomeLookupRow.setTotalDoc_Sent(mJsonObjectData.getInt(DOC_TOTAL));
//                mHomeLookupRow.setDocNotProcess_Sent(mJsonObjectData.getInt(DOC_NOT_PROCESS));
//                mHomeLookupRow.setDocNotProcessType_Sent(mJsonObjectData.getInt(DOC_NOT_PROCESS_TYPE));
//                mHomeLookupRow.setDocNotSeenType_Sent(mJsonObjectData.getInt(DOC_NOT_SEEN_TYPE));
//                mHomeLookupRow.setDocDemurrageType_Sent(mJsonObjectData.getInt(DOC_DEMURRAGE_TYPE));
//                mHomeLookupRow.setTotalDocType_Sent(mJsonObjectData.getInt(TOTAL_DOC_TYPE));
//                mHomeView.setDataForHomeScreenSent(mHomeLookupRow.getTotalDoc_Sent(), mHomeLookupRow.getDocNotProcess_Sent(), mHomeLookupRow.getDocNotSeen_Sent(), mHomeLookupRow.getDocDemurrage_Sent());
//                mHomeView.setHomeLookupRow(mHomeLookupRow);
//            } catch (JSONException e) {
//                mHomeView.closeProgress();
//                e.printStackTrace();
//            }
//            break;
//        case TASK_DETECT:
//            try {
//                JSONObject mJsonObject = new JSONObject(s);
//                JSONObject mData = mJsonObject.getJSONObject(DATA);
//                mTaskRemind = new TaskRemind(mData.getInt(TASK_REPORTED), mData.getInt(REPORTED_TYPE), mData.getInt(TASK_PROCESS), mData.getInt(PROCESS_TYPE), mData.getInt(PROCESS_ON_TIME), mData.getInt(PROCESS_ON_TIME_TYPE), mData.getInt(TASK_PROCESS_NEAR_DEMURRAGE), mData.getInt(PROCESS_NEAR_DEMURRAGE_TYPE), mData.getInt(PROCESS_DEMURRAGE), mData.getInt(PROCESS_DEMURRAGE_TYPE), mData.getInt(TOTAL), mData.getInt(TOTAL_TYPE));
//                mHomeView.setTaskRemind(mTaskRemind);
//                mHomeView.setTaskHomeNumber("Tổng " + mTaskRemind.getTotal() + " nhiệm vụ", mTaskRemind.getProcess() + ""
//                        , mTaskRemind.getProcessOnDue() + "", mTaskRemind.getProcessNearDemurrage() + "", mTaskRemind.getProcessDemurrage() + "", mTaskRemind.getReported() + "");
//                mHomeView.showLayoutTask();
//            } catch (JSONException e) {
//                mHomeView.hideLayoutTask();
//                e.printStackTrace();
//            }
//            break;
//        case HOTLINE_OR:
//            try {
//                JSONObject mJsonObject = new JSONObject(s);
//                JSONObject mData = mJsonObject.getJSONObject(DATA);
//                HotLineRow mHotLineRow
//                        = new HotLineRow(mData.getLong(ORGANIZATION_ID),
//                        mData.getString(ORGANIZATION_NAME),
//                        mData.getInt(IN_PROCESS),
//                        mData.getString(IN_PROCESS_CODE),
//                        mData.getInt(IN_PROCESS_ON_DUE),
//                        mData.getString(IN_PROCESS_ON_DUE_TYPE),
//                        mData.getInt(IN_PROCESS_LATE),
//                        mData.getString(IN_PROCESS_LATE_CODE));
//                mHomeView.getViewAndHotLineData(mHotLineRow);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            break;
//        case SCHEDULE_HOME:
//            arrayListSchedule = new ArrayList<ScheduleRow>();
//            Log.d(TAG, s);
//            try {
//                JSONObject mObject = new JSONObject(s);
//                JSONObject mJsonObjectData = mObject.getJSONObject(DATA);
//                analysizSchedule(mJsonObjectData, MORNING_CAL);
//                analysizSchedule(mJsonObjectData, AFTERNOON_CAL);
//                analysizSchedule(mJsonObjectData, EVENING_CAL);
//                ScheduleAdapter adapter = new ScheduleAdapter(Context, arrayListSchedule);
//                mHomeView.setAdapterSchedule(adapter);
//                mHomeView.showScheduleLayout();
//                mHomeView.closeProgress();
//            } catch (JSONException e) {
//                mHomeView.closeProgress();
//                mHomeView.hideScheduleLayout();
//                e.printStackTrace();
//            }
//            break;
//    }
//}
//private void analysizSchedule(JSONObject mJsonObject, String DATE_TIME) {
//    try {
//        JSONArray mJsonArray = mJsonObject.getJSONArray(DATE_TIME);
//        if (!mJsonArray.toString().equals("[]")) {
//            for (int i = 0; i < mJsonArray.length(); i++) {
//                JSONObject mObject = mJsonArray.getJSONObject(i);
//                String startDate = mObject.getString(SCHEDULE_START_DATE);
//                String content = mObject.getString(SCHEDULE_CONTENT);
//                String chairMan = mObject.getString(SCHEDULE_CHAIR_MAN);
//                String status = mObject.getString(SCHEDULE_STATUS);
//                String placeName = mObject.getString(SCHEDULE_PLACE_NAME);
//                arrayListSchedule.add(new ScheduleRow(startDate, content, chairMan, status, placeName));
//            }
//        }
//    } catch (JSONException e) {
//        e.printStackTrace();
//    }
//}
//    private void refreshUI(String s, String CaseRequest) {
//        Log.d(TAG, s);
//        try {
//            switch (CaseRequest) {
//                case STATIS_DOC_COMING:
//                    if (!s.equals(nULL_STRING) && !s.equals("Error!")) {
//                        writeToFile(s, Context, DATA_COMING_DOCUMENT);
//                    }
//                    GsonHomeStatis mGsonHomeStatisComing = getGson().fromJson(s, GsonHomeStatis.class);
//                    GsonHomeStatis.DataBean mDataBean = mGsonHomeStatisComing.getData();
//                    if (mGsonHomeStatisComing.getData() != null) {
//                        mHomeLookupRow.setDocDemurrage_Coming(mDataBean.getDocDemurrage());
//                        mHomeLookupRow.setDocNotSeen_Coming(mDataBean.getDocNotSeen());
//                        mHomeLookupRow.setDocNotProcess_Coming(mDataBean.getDocNotProcess());
//                        mHomeLookupRow.setTotalDoc_Coming(mDataBean.getTotalDoc());
//                        mHomeLookupRow.setDocNotProcessType_Coming(mDataBean.getDocNotProcessType());
//                        mHomeLookupRow.setDocNotSeenType_Coming(mDataBean.getDocNotSeenType());
//                        mHomeLookupRow.setDocDemurrageType_Coming(mDataBean.getDocDemurrageType());
//                        mHomeLookupRow.setTotalDocType_Coming(mDataBean.getTotalDocType());
//                        mHomeView.setDataForHomeScreenComing(mHomeLookupRow.getTotalDoc_Coming()
//                                , mHomeLookupRow.getDocNotProcess_Coming(), mHomeLookupRow.getDocNotSeen_Coming(), mHomeLookupRow.getDocDemurrage_Coming());
//                        Calendar mCalendar = Calendar.getInstance();
//                        mHomeView.setDaySchedule("" +
////                        "(" + getNameOfDay(mCalendar.get(Calendar.DAY_OF_WEEK)) + ", " +
//                                "(" + mSimpleDateFormat.format(mCalendar.getTime()) + ")");
////                tvDaySchedule.setText("(" + getNameOfDay(mCalendar.get(Calendar.DAY_OF_WEEK)) + ", "
////                        + mSimpleDateFormat.format(mCalendar.getTime()) + ")");
////                tvDaySchedule.setText("("
////                        + mSimpleDateFormat.format(mCalendar.getTime()) + ")");
//                    }
//                    break;
//                case STATIS_DOC_SENT:
//                    if (!s.equals(nULL_STRING) && !s.equals("Error!")) {
//                        writeToFile(s, Context, DATA_SENT_DOCUMENT);
//                    }
//                    GsonHomeStatis mGsonHomeStatisSent = getGson().fromJson(s, GsonHomeStatis.class);
//                    GsonHomeStatis.DataBean mDataBeanSent = mGsonHomeStatisSent.getData();
//                    if (mGsonHomeStatisSent.getData() != null) {
//                        mHomeLookupRow.setDocDemurrage_Sent(mDataBeanSent.getDocDemurrage());
//                        mHomeLookupRow.setDocNotSeen_Sent(mDataBeanSent.getDocNotSeen());
//                        mHomeLookupRow.setTotalDoc_Sent(mDataBeanSent.getTotalDoc());
//                        mHomeLookupRow.setDocNotProcess_Sent(mDataBeanSent.getDocNotProcess());
//                        mHomeLookupRow.setDocNotProcessType_Sent(mDataBeanSent.getDocNotProcessType());
//                        mHomeLookupRow.setDocNotSeenType_Sent(mDataBeanSent.getDocNotSeenType());
//                        mHomeLookupRow.setDocDemurrageType_Sent(mDataBeanSent.getDocDemurrageType());
//                        mHomeLookupRow.setTotalDocType_Sent(mDataBeanSent.getTotalDocType());
//                        mHomeView.setDataForHomeScreenSent(mHomeLookupRow.getTotalDoc_Sent(), mHomeLookupRow.getDocNotProcess_Sent(), mHomeLookupRow.getDocNotSeen_Sent(), mHomeLookupRow.getDocDemurrage_Sent());
//                        mHomeView.setHomeLookupRow(mHomeLookupRow);
//                    }
//                    break;
//                case TASK_DETECT:
//                    GsonHomeTask mGsonHomeTask = getGson().fromJson(s, GsonHomeTask.class);
//                    GsonHomeTask.DataBean mDataBeanTask = mGsonHomeTask.getData();
//                    if (mGsonHomeTask.getData() != null) {
//                        mTaskRemind = new TaskRemind(mDataBeanTask.getReported(), mDataBeanTask.getReportedType(), mDataBeanTask.getProcess(), mDataBeanTask.getProcessType(), mDataBeanTask.getProcessOnTime(), mDataBeanTask.getProcessOnTimeType(), mDataBeanTask.getProcessNearDemurrage(), mDataBeanTask.getProcessNearDemurrageType(), mDataBeanTask.getProcessDemurrage(), mDataBeanTask.getProcessDemurrageType(), mDataBeanTask.getTotal(), mDataBeanTask.getTotalType());
//                        mHomeView.setTaskRemind(mTaskRemind);
//                        mHomeView.setTaskHomeNumber("Tổng " + mTaskRemind.getTotal() + " nhiệm vụ", mTaskRemind.getProcess() + "", mTaskRemind.getProcessOnDue() + "", mTaskRemind.getProcessNearDemurrage() + "", mTaskRemind.getProcessDemurrage() + "", mTaskRemind.getReported() + "");
//                        mHomeView.showLayoutTask();
//                    }
//                    break;
//                case HOTLINE_OR:
//                    GsonHomeHotLine mGsonHomeHotLine = getGson().fromJson(s, GsonHomeHotLine.class);
//                    GsonHomeHotLine.DataBean mDataBeanHotLine = mGsonHomeHotLine.getData();
//                    if (mGsonHomeHotLine.getData() != null) {
//                        HotLineRow mHotLineRow = new HotLineRow(mDataBeanHotLine.getOrganizationId(), mDataBeanHotLine.getOrganizationName(), mDataBeanHotLine.getInProcess(), mDataBeanHotLine.getInProcessCode(), mDataBeanHotLine.getInProcessOnDue(), mDataBeanHotLine.getInProcessOnDueCode(), mDataBeanHotLine.getInProcessLate(), mDataBeanHotLine.getInProcessLateCode());
//                        mHomeView.getViewAndHotLineData(mHotLineRow);
//                    }
//                    break;
//                case SCHEDULE_HOME:
//                    if (getDefaults(URL_CENTER, Context).equals(URL_CENTER_6_1)) {
//                        arrayListSchedule = new ArrayList<ScheduleRow>();
//                        Log.d(TAG, s);
//                        try {
//                            JSONObject mObject = new JSONObject(s);
//                            JSONObject mJsonObjectData = mObject.getJSONObject(DATA);
//                            analysizSchedule(mJsonObjectData, MORNING_CAL);
//                            analysizSchedule(mJsonObjectData, AFTERNOON_CAL);
//                            analysizSchedule(mJsonObjectData, EVENING_CAL);
//                            ScheduleAdapter adapter = new ScheduleAdapter(Context, arrayListSchedule);
//                            mHomeView.setAdapterSchedule(adapter);
//                            mHomeView.showScheduleLayout();
//                            mHomeView.closeProgress();
//                        } catch (JSONException e) {
//                            mHomeView.closeProgress();
//                            mHomeView.hideScheduleLayout();
//                            e.printStackTrace();
//                        }
//                    } else {
//                        GsonScheduleDay mGsonScheduleDay = getGson().fromJson(s, GsonScheduleDay.class);
//                        List<GsonScheduleDay.DataBean> mDataBeans = mGsonScheduleDay.getData();
//                        mHomeView.setAdapterScheduleDay(new ScheduleDayAdapter(Context, mDataBeans));
//                        mHomeView.showScheduleLayout();
//                        mHomeView.closeProgress();
//                    }
//                    break;
//            }
//        } catch (IllegalStateException | JsonSyntaxException exception) {
//            Log.d(TAG, "Module not found");
//        }
//    }
}
