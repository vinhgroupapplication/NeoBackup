package com.newsaigonsoft.neoegov.Fragment.Schedule;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.newsaigonsoft.neoegov.AVolleyManager.VolleyTask;
import com.newsaigonsoft.neoegov.AVolleyManager.VolleyTaskCompletedListenner;
import com.newsaigonsoft.neoegov.Adapter.ScheduleAdapter;
import com.newsaigonsoft.neoegov.Adapter.ScheduleDayAdapter;
import com.newsaigonsoft.neoegov.Adapter.ScheduleMonthAdapter;
import com.newsaigonsoft.neoegov.Adapter.ScheduleNewWeekAdapter;
import com.newsaigonsoft.neoegov.Adapter.ScheduleWeekAdapter;
import com.newsaigonsoft.neoegov.GsonObject.GsonScheduleDay;
import com.newsaigonsoft.neoegov.ScheduleDetailActivity.ScheduleDetailActivity;
import com.newsaigonsoft.neoegov.Subjects.KeyManager;
import com.newsaigonsoft.neoegov.Subjects.ResuiltObject;
import com.newsaigonsoft.neoegov.Subjects.ScheduleMonthRow;
import com.newsaigonsoft.neoegov.Subjects.ScheduleRow;
import com.newsaigonsoft.neoegov.Subjects.SumManager;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.AFTERNOON_CAL;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DATA;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.END_TIME;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.EVENING_CAL;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.FRIDAY_CAL;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MODULE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MONDAY_CAL;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MORNING_CAL;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.NEOTYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.SATURDAY_CAL;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.SCHEDULE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.SCHEDULE_CHAIR_MAN;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.SCHEDULE_CONTENT;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.SCHEDULE_DAY;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.SCHEDULE_DAY_DPM;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.SCHEDULE_DAY_ORG;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.SCHEDULE_PLACE_NAME;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.SCHEDULE_START_DATE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.SCHEDULE_STATUS;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.START_TIME;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.SUNDAY_CAL;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.THURSDAY_CAL;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TUESDAY_CAL;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.WEDNESDAY_CAL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.AFTERNOON_DAY;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.CODE_SCHEDULE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.CODE_SCHEDULE_DPM;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.CODE_SCHEDULE_ORG;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.CODE_SCHEDULE_PERSON;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.EVENING_DAY;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.MORNING_DAY;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.REMEMBER_PART_OF_DAY;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SCHEDULE_DAY_REQUEST;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SCHEDULE_DETAIL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SCHEDULE_MONTH_REQUEST;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SCHEDULE_WEEK_REQUEST;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAG;

/**
 * Created by Vinh on 10/20/2017.
 */

public class ScheduleFmLogic extends SumManager implements ScheduleFmImp, VolleyTaskCompletedListenner<ResuiltObject> {

    ScheduleFmView mScheduleFmView;
    Context Context;
    String mLeftDay;
    String mRightDay;
    String mCaseRequestTemporary;
    ArrayList<CalendarDay> dates = new ArrayList<>();
    ScheduleMonthAdapter monthAdapter;
    List<GsonScheduleDay.DataBean> ArrData;

    public ScheduleFmLogic(ScheduleFmView mScheduleFmView, android.content.Context context) {
        this.mScheduleFmView = mScheduleFmView;
        Context = context;
        getInforAccountFromShareReferenced(Context);
    }

    @Override
    public void ReadScheduleDay(String LeftDay, String ScheduleCode) {
        mLeftDay = LeftDay;
        mCaseRequestTemporary = ScheduleCode;
//        new ReadJsonScheduleDay().execute(getLink() + URL_CENTER_6_1);
        new VolleyTask(Context, SCHEDULE_DAY_REQUEST, addJsonRequestScheduleDay(ScheduleCode), this);

    }

    @Override
    public void ReadScheduleWeek(String LeftDay, String RightDay, String ScheduleCode, List<ScheduleMonthRow> arr) {
        arrDate = arr;
        mLeftDay = LeftDay;
        mRightDay = RightDay;
        mCaseRequestTemporary = ScheduleCode;
//        new ReadJsonScheduleWeek().execute(getLink() + URL_CENTER_6_1);
        new VolleyTask(Context, SCHEDULE_WEEK_REQUEST, addJsonScheduleRequestWeek(ScheduleCode), this);
    }


    List<ScheduleMonthRow> arrDate;

    @Override
    public void ReadScheduleMonth(String leftDay, String rightDay, String scheduleCode, List<ScheduleMonthRow> arr) {
        arrDate = arr;
        mLeftDay = leftDay;
        mRightDay = rightDay;
        mCaseRequestTemporary = scheduleCode;
        new VolleyTask(Context, SCHEDULE_MONTH_REQUEST, addJsonScheduleRequestWeek(scheduleCode), this);
    }

    @Override
    public void expandFollowDate(String format) {
        int positionScroll = 0;
        for (int i = 0; i < arrDate.size(); i++) {
            arrDate.get(i).setShowList(format.equals(arrDate.get(i).getDate()) ? true : false);
            if (format.equals(arrDate.get(i).getDate())) {
                positionScroll = i;
            }
        }
        monthAdapter.notifyDataSetChanged();
        mScheduleFmView.scrollToPosition(positionScroll, arrDate.size());

    }

    @Override
    public void OpenDetail(int position) {
        Intent intent  = new Intent(Context, ScheduleDetailActivity.class);
        intent.putExtra(SCHEDULE_DETAIL, getGson().toJson(ArrData.get(position)));
        Context.startActivity(intent);
    }

    JSONObject addJsonRequestScheduleDay(String ScheduleCode) {
        String OnlyDayString = mLeftDay.substring(mLeftDay.length() - 10);
        JSONObject mJsonObject = new JSONObject();
        JSONObject mJsonObjectData = new JSONObject();
        try {
            mJsonObject.put(MODULE, SCHEDULE);
            switch (ScheduleCode) {
                case CODE_SCHEDULE_PERSON:
                    mJsonObject.put(NEOTYPE, SCHEDULE_DAY);
                    mJsonObjectData.put(START_TIME, getMilisecondDay(OnlyDayString));
                    mJsonObjectData.put(END_TIME, getMilisecondDay(OnlyDayString));
                    mJsonObject.put(DATA, mJsonObjectData.toString());
                    break;
                case CODE_SCHEDULE_DPM:
                    mJsonObject.put(NEOTYPE, SCHEDULE_DAY_DPM);
                    mJsonObjectData.put(START_TIME, getMilisecondDay(OnlyDayString));
                    mJsonObjectData.put(END_TIME, getMilisecondDay(OnlyDayString));
                    mJsonObject.put(DATA, mJsonObjectData.toString());
                    break;
                case CODE_SCHEDULE_ORG:
                    mJsonObject.put(NEOTYPE, SCHEDULE_DAY_ORG);
                    mJsonObjectData.put(START_TIME, getMilisecondDay(OnlyDayString));
                    mJsonObjectData.put(END_TIME, getMilisecondDay(OnlyDayString));
                    mJsonObject.put(DATA, mJsonObjectData.toString());
                    break;
                default:
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mJsonObject;
    }

    @Override
    public void onVolleyCompleted(String s, String CaseRequest) {
        switch (CaseRequest) {
            case SCHEDULE_DAY_REQUEST:
                switch (mCaseRequestTemporary) {
                    case CODE_SCHEDULE_PERSON:
                    case CODE_SCHEDULE_DPM:
                    case CODE_SCHEDULE_ORG:
                        GsonScheduleDay mGsonScheduleDay = getGson().fromJson(s, GsonScheduleDay.class);
                        mScheduleFmView.setScheduleDay(getAdapterScheduleDay(mGsonScheduleDay));
                        break;
                    case CODE_SCHEDULE:
                        ScheduleAdapter adapter = new ScheduleAdapter(Context, getListSchedule(s));
                        mScheduleFmView.setScheduleDay(adapter);
                        break;
                    default:
                        break;
                }
                break;
            case SCHEDULE_WEEK_REQUEST:
                String PartDay = getDefaults(REMEMBER_PART_OF_DAY, Context);
                if (PartDay == null) {
                    PartDay = MORNING_DAY;
                }
                switch (mCaseRequestTemporary) {
                    case CODE_SCHEDULE_PERSON:
                    case CODE_SCHEDULE_DPM:
                    case CODE_SCHEDULE_ORG:
                        GsonScheduleDay mGsonScheduleDay = getGson().fromJson(s, GsonScheduleDay.class);
                        List<GsonScheduleDay.DataBean> mDataBeans = mGsonScheduleDay.getData();
                        for (int i = 0; i < arrDate.size(); i++) {
                            String name = arrDate.get(i).getDate();
                            Log.d(KeyManager.TAG, name);
                            List<GsonScheduleDay.DataBean> arrData = new ArrayList<>();
                            for (int j = 0; j < mDataBeans.size(); j++) {
                                String dateDate = getDateFromMiliSec(mDataBeans.get(j).getStartDate(), FORMATDATE);
                                if (name.equals(dateDate)) {
                                    arrData.add(mDataBeans.get(j));
                                }
                            }
                            if (mSimpleDateFormat.format(Calendar.getInstance().getTime()).equals(arrDate.get(i).getDate())) {
                                arrDate.set(i, new ScheduleMonthRow(arrDate.get(i).getDate(), arrData, true));
                            } else {
                                arrDate.set(i, new ScheduleMonthRow(arrDate.get(i).getDate(), arrData, false));
                            }

                        }
                        monthAdapter = new ScheduleMonthAdapter(Context, arrDate);
                        mScheduleFmView.setlistWeek(monthAdapter);
//                        List<GsonScheduleDay.DataBean> arrMon = new ArrayList<>();
//                        List<GsonScheduleDay.DataBean> arrTue = new ArrayList<>();
//                        List<GsonScheduleDay.DataBean> arrWed = new ArrayList<>();
//                        List<GsonScheduleDay.DataBean> arrThu = new ArrayList<>();
//                        List<GsonScheduleDay.DataBean> arrFri = new ArrayList<>();
//                        List<GsonScheduleDay.DataBean> arrSat = new ArrayList<>();
//                        List<GsonScheduleDay.DataBean> arrSun = new ArrayList<>();
//                        int i = 0;
//                        while (i < mDataBeans.size()) {
//                            long milisec = mDataBeans.get(i).getStartDate();
//                            String StringDate = getDateFromMiliSec(milisec, FORMATP_DATE_EEE);
//                            int hours = Integer.parseInt(StringDate.substring(0, 2));
//                            String dayofWeek = StringDate.substring(StringDate.length() - 3);
////                            switch (PartDay) {
////                                case MORNING_DAY:
//                            if (hours >= 0 && hours < 12) {
//                                switch (dayofWeek) {
//                                    case "Mon":
//                                        arrMon.add(mDataBeans.get(i));
//                                        break;
//                                    case "Tue":
//                                        arrTue.add(mDataBeans.get(i));
//                                        break;
//                                    case "Wed":
//                                        arrWed.add(mDataBeans.get(i));
//                                        break;
//                                    case "Thu":
//                                        arrThu.add(mDataBeans.get(i));
//                                        break;
//                                    case "Fri":
//                                        arrFri.add(mDataBeans.get(i));
//                                        break;
//                                    case "Sat":
//                                        arrSat.add(mDataBeans.get(i));
//                                        break;
//                                    case "Sun":
//                                        arrSun.add(mDataBeans.get(i));
//                                        break;
//                                }
//                            }
////                                    break;
////                                case AFTERNOON_DAY:
////                                    if (hours >= 12 && hours < 16) {
////                                        switch (dayofWeek) {
////                                            case "Mon":
////                                                arrMon.add(mDataBeans.get(i));
////                                                break;
////                                            case "Tue":
////                                                arrTue.add(mDataBeans.get(i));
////                                                break;
////                                            case "Wed":
////                                                arrWed.add(mDataBeans.get(i));
////                                                break;
////                                            case "Thu":
////                                                arrThu.add(mDataBeans.get(i));
////                                                break;
////                                            case "Fri":
////                                                arrFri.add(mDataBeans.get(i));
////                                                break;
////                                            case "Sat":
////                                                arrSat.add(mDataBeans.get(i));
////                                                break;
////                                            case "Sun":
////                                                arrSun.add(mDataBeans.get(i));
////                                                break;
////                                        }
////                                    }
////                                    break;
////                                case EVENING_DAY:
////                                    if (hours >= 16 && hours < 24) {
////                                        switch (dayofWeek) {
////                                            case "Mon":
////                                                arrMon.add(mDataBeans.get(i));
////                                                break;
////                                            case "Tue":
////                                                arrTue.add(mDataBeans.get(i));
////                                                break;
////                                            case "Wed":
////                                                arrWed.add(mDataBeans.get(i));
////                                                break;
////                                            case "Thu":
////                                                arrThu.add(mDataBeans.get(i));
////                                                break;
////                                            case "Fri":
////                                                arrFri.add(mDataBeans.get(i));
////                                                break;
////                                            case "Sat":
////                                                arrSat.add(mDataBeans.get(i));
////                                                break;
////                                            case "Sun":
////                                                arrSun.add(mDataBeans.get(i));
////                                                break;
////                                        }
////                                    }
////                                    break;
////                                default:
////                                    break;
////                            }
//                            i++;
//                        }
//                        mScheduleFmView.setListMonDay(new ScheduleNewWeekAdapter(Context, arrMon));
//                        mScheduleFmView.showListMonday(arrMon);
//                        mScheduleFmView.setListTuesDay(new ScheduleNewWeekAdapter(Context, arrTue));
//                        mScheduleFmView.showListTuesDay(arrTue);
//                        mScheduleFmView.setListWebnesday(new ScheduleNewWeekAdapter(Context, arrWed));
//                        mScheduleFmView.showListWebnesday(arrWed);
//                        mScheduleFmView.setListThursday(new ScheduleNewWeekAdapter(Context, arrThu));
//                        mScheduleFmView.showListThursday(arrThu);
//                        mScheduleFmView.setListFriday(new ScheduleNewWeekAdapter(Context, arrFri));
//                        mScheduleFmView.showListFriday(arrFri);
//                        mScheduleFmView.setListSaturday(new ScheduleNewWeekAdapter(Context, arrSat));
//                        mScheduleFmView.showListSaturday(arrSat);
//                        mScheduleFmView.setListSunday(new ScheduleNewWeekAdapter(Context, arrSun));
//                        mScheduleFmView.showListSunday(arrSun);
//                        mScheduleFmView.setDayNameForWeek();
                        break;
                    case CODE_SCHEDULE:
                        Log.d(TAG, s);
                        ArrayList<ScheduleRow> arrayMonday = new ArrayList<ScheduleRow>();
                        ArrayList<ScheduleRow> arrayTuesday = new ArrayList<ScheduleRow>();
                        ArrayList<ScheduleRow> arrayWebnesday = new ArrayList<ScheduleRow>();
                        ArrayList<ScheduleRow> arrayThursday = new ArrayList<ScheduleRow>();
                        ArrayList<ScheduleRow> arrayFriday = new ArrayList<ScheduleRow>();
                        ArrayList<ScheduleRow> arraySaturday = new ArrayList<ScheduleRow>();
                        ArrayList<ScheduleRow> arraySunday = new ArrayList<ScheduleRow>();
                        try {
                            JSONObject mJsonObject = new JSONObject(s);
                            JSONObject mJsonObjectData = mJsonObject.getJSONObject(DATA);

                            arrayMonday = getListWeek(mJsonObjectData, MONDAY_CAL);
                            ScheduleWeekAdapter adapterMonday = new ScheduleWeekAdapter(Context, arrayMonday);
                            mScheduleFmView.setListMonDay(adapterMonday);

//                mOfficalActivity.setListViewHeightBasedOnChildren(lvMonday);

                            arrayTuesday = getListWeek(mJsonObjectData, TUESDAY_CAL);
                            ScheduleWeekAdapter adapterTuesday = new ScheduleWeekAdapter(Context, arrayTuesday);
                            mScheduleFmView.setListTuesDay(adapterTuesday);

//                mOfficalActivity.setListViewHeightBasedOnChildren(lvTuesday);

                            arrayWebnesday = getListWeek(mJsonObjectData, WEDNESDAY_CAL);
                            ScheduleWeekAdapter adapterWebnesday = new ScheduleWeekAdapter(Context, arrayWebnesday);
                            mScheduleFmView.setListWebnesday(adapterWebnesday);

//                mOfficalActivity.setListViewHeightBasedOnChildren(lvWednesday);

                            arrayThursday = getListWeek(mJsonObjectData, THURSDAY_CAL);
                            ScheduleWeekAdapter adapterThursday = new ScheduleWeekAdapter(Context, arrayThursday);
                            mScheduleFmView.setListThursday(adapterThursday);
//                mOfficalActivity.setListViewHeightBasedOnChildren(lvThursday);

                            arrayFriday = getListWeek(mJsonObjectData, FRIDAY_CAL);
                            ScheduleWeekAdapter adapterFriday = new ScheduleWeekAdapter(Context, arrayFriday);
                            mScheduleFmView.setListFriday(adapterFriday);
//                mOfficalActivity.setListViewHeightBasedOnChildren(lvPriday);

                            arraySaturday = getListWeek(mJsonObjectData, SATURDAY_CAL);
                            ScheduleWeekAdapter adapterSaturday = new ScheduleWeekAdapter(Context, arraySaturday);
                            mScheduleFmView.setListSaturday(adapterSaturday);

//                mOfficalActivity.setListViewHeightBasedOnChildren(lvSaturday);

                            arraySunday = getListWeek(mJsonObjectData, SUNDAY_CAL);
                            ScheduleWeekAdapter adapterSunday = new ScheduleWeekAdapter(Context, arraySunday);
                            mScheduleFmView.setListSunday(adapterSunday);

//                mOfficalActivity.setListViewHeightBasedOnChildren(lvSunday);
                            mScheduleFmView.setDayNameForWeek();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            ShowErrorToast(Context);
                        }
                        break;
                    default:
                        break;
                }
                break;
            case SCHEDULE_MONTH_REQUEST:
                Log.d(KeyManager.TAG, s);
                switch (mCaseRequestTemporary) {
                    case CODE_SCHEDULE_PERSON:
                    case CODE_SCHEDULE_DPM:
                    case CODE_SCHEDULE_ORG:
                        GsonScheduleDay mGsonScheduleDay = getGson().fromJson(s, GsonScheduleDay.class);
                        List<GsonScheduleDay.DataBean> mDataBean = mGsonScheduleDay.getData();
                        for (int i = 0; i < arrDate.size(); i++) {
                            String name = arrDate.get(i).getDate();
                            Log.d(KeyManager.TAG, name);
                            List<GsonScheduleDay.DataBean> arrData = new ArrayList<>();
                            for (int j = 0; j < mDataBean.size(); j++) {
                                String dateDate = getDateFromMiliSec(mDataBean.get(j).getStartDate(), FORMATDATE);
                                if (name.equals(dateDate)) {
                                    arrData.add(mDataBean.get(j));
                                    Calendar calendar = Calendar.getInstance();
                                    calendar.setTimeInMillis(mDataBean.get(j).getStartDate());
                                    CalendarDay day = CalendarDay.from(calendar);
                                    dates.add(day);
                                }
                            }
                            if (mSimpleDateFormat.format(Calendar.getInstance().getTime()).equals(arrDate.get(i).getDate())) {
                                arrDate.set(i, new ScheduleMonthRow(arrDate.get(i).getDate(), arrData, true));
                            } else {
                                arrDate.set(i, new ScheduleMonthRow(arrDate.get(i).getDate(), arrData, false));
                            }
                        }
                        monthAdapter = new ScheduleMonthAdapter(Context, arrDate);
                        mScheduleFmView.setListMonth(monthAdapter, arrDate);
                        mScheduleFmView.addDotCalendar(dates);
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
        mScheduleFmView.closeProgress();
    }

    ScheduleDayAdapter getAdapterScheduleDay(GsonScheduleDay mGsonScheduleDay) {
        List<GsonScheduleDay.DataBean> mDataBeans = mGsonScheduleDay.getData();
        ArrData = new ArrayList<>();
//        String PartDay = getDefaults(REMEMBER_PART_OF_DAY, Context);
//        if (PartDay == null) {
//            PartDay = MORNING_DAY;
//        }
        int i = 0;
        while (i < mDataBeans.size()) {
            GsonScheduleDay.DataBean mData = mDataBeans.get(i);
            ArrData.add(mData);
//            int hour = Integer.parseInt(getDateFromMiliSec(mDataBeans.get(i).getStartDate(), FORMATP_DATE_DOCUMENT_6_2).substring(0, 2));
//            switch (PartDay) {
//                case MORNING_DAY:
//                    if (hour >= 0 && hour < 12) {
//                        ArrData.add(mData);
//                    }
//                    break;
//                case AFTERNOON_DAY:
//                    if (hour >= 12 && hour < 16) {
//                        ArrData.add(mData);
//                    }
//                    break;
//                case EVENING_DAY:
//                    if (hour >= 16 && hour < 24) {
//                        ArrData.add(mData);
//                    }
//                    break;
//                default:
//                    break;
//            }
            i++;
        }
        ScheduleDayAdapter mScheduleDayAdapter = new ScheduleDayAdapter(Context, ArrData);
        return mScheduleDayAdapter;
    }

    @Override
    public void onVolleyError(String s) {
        mScheduleFmView.closeProgress();
        mScheduleFmView.ToastError(s);
        mScheduleFmView.showNoDataDay();
        mScheduleFmView.hideScheduleList();
    }

    public ArrayList<ScheduleRow> getListSchedule(String s) {
        ArrayList<ScheduleRow> arrayList = new ArrayList<>();
        String PartDay = getDefaults(REMEMBER_PART_OF_DAY, Context);
        if (PartDay == null) {
            PartDay = MORNING_DAY;
        }
        switch (PartDay) {
            case MORNING_DAY:
                arrayList = analyzisJsonDay(MORNING_CAL, s);
                break;
            case AFTERNOON_DAY:
                arrayList = analyzisJsonDay(AFTERNOON_CAL, s);
                break;
            case EVENING_DAY:
                arrayList = analyzisJsonDay(EVENING_CAL, s);
                break;
            default:
                break;
        }
        return arrayList;
    }


    private ArrayList<ScheduleRow> analyzisJsonDay(String TYPE, String s) {
        ArrayList<ScheduleRow> arrayList = new ArrayList<>();
        JSONObject mJsonObject;
        JSONArray mJsonArray;
        try {
            mJsonObject = new JSONObject(s);
            JSONObject mJsonObjectData = mJsonObject.getJSONObject(DATA);
            mJsonArray = mJsonObjectData.getJSONArray(TYPE);
            for (int i = 0; i < mJsonArray.length(); i++) {
                JSONObject mJsonObjectMorning = mJsonArray.getJSONObject(i);
                String startDate = mJsonObjectMorning.getString(SCHEDULE_START_DATE);
                String content = mJsonObjectMorning.getString(SCHEDULE_CONTENT);
                String chairMan = mJsonObjectMorning.getString(SCHEDULE_CHAIR_MAN);
                String status = mJsonObjectMorning.getString(SCHEDULE_STATUS);
                String placeName = mJsonObjectMorning.getString(SCHEDULE_PLACE_NAME);
                arrayList.add(new ScheduleRow(startDate, content, chairMan, status, placeName));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            ShowErrorToast(Context);
        } finally {
            if (arrayList.size() == 0) {
                mScheduleFmView.hideScheduleList();
                mScheduleFmView.showNoDataDay();
            } else {
                mScheduleFmView.showScheduleList();
                mScheduleFmView.hideNoDataDay();
            }
        }
        return arrayList;
    }

    JSONObject addJsonScheduleRequestWeek(String ScheduleCode) {
        String OnlyDayStringLeft = mLeftDay.substring(mLeftDay.length() - 10);
        String OnlyDayStringRight = mRightDay.substring(mRightDay.length() - 10);
        JSONObject mJsonObject = new JSONObject();
        JSONObject mJsonObjectData = new JSONObject();
        try {
            mJsonObject.put(MODULE, SCHEDULE);
            switch (ScheduleCode) {
                case CODE_SCHEDULE_PERSON:
                    mJsonObject.put(NEOTYPE, SCHEDULE_DAY);
                    mJsonObjectData.put(START_TIME, getMilisecondDay(OnlyDayStringLeft));
                    mJsonObjectData.put(END_TIME, getMilisecondDay(OnlyDayStringRight));
                    break;
                case CODE_SCHEDULE_DPM:
                    mJsonObject.put(NEOTYPE, SCHEDULE_DAY_DPM);
                    mJsonObjectData.put(START_TIME, getMilisecondDay(OnlyDayStringLeft));
                    mJsonObjectData.put(END_TIME, getMilisecondDay(OnlyDayStringRight));
                    break;
                case CODE_SCHEDULE_ORG:
                    mJsonObject.put(NEOTYPE, SCHEDULE_DAY_ORG);
                    mJsonObjectData.put(START_TIME, getMilisecondDay(OnlyDayStringLeft));
                    mJsonObjectData.put(END_TIME, getMilisecondDay(OnlyDayStringRight));
                    break;
                default:
                    break;
            }
            mJsonObject.put(DATA, mJsonObjectData.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mJsonObject;
    }

    public ArrayList<ScheduleRow> analyzisJsonWeek(JSONObject mJsonObjectData, String DAY_CAL, String PART_DAY) {
        ArrayList<ScheduleRow> arrayList = new ArrayList<ScheduleRow>();
        try {
            JSONObject mJsonObject = mJsonObjectData.getJSONObject(DAY_CAL);
            JSONArray mArray = mJsonObject.getJSONArray(PART_DAY);
            if (!mArray.toString().equals("[]")) {
                for (int i = 0; i < mArray.length(); i++) {
                    JSONObject mObject = mArray.getJSONObject(i);
                    String startDate = mObject.getString(SCHEDULE_START_DATE);
                    String content = mObject.getString(SCHEDULE_CONTENT);
                    String chairMan = mObject.getString(SCHEDULE_CHAIR_MAN);
                    String status = mObject.getString(SCHEDULE_STATUS);
                    String placeName = mObject.getString(SCHEDULE_PLACE_NAME);
                    arrayList.add(new ScheduleRow(startDate, content, chairMan, status, placeName));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            ShowErrorToast(Context);
        }
        return arrayList;
    }

    public ArrayList<ScheduleRow> getListWeek(JSONObject mJsonObjectData, String DAY_CAL) {
        ArrayList<ScheduleRow> arrayList = new ArrayList<ScheduleRow>();
        String PartDay = getDefaults(REMEMBER_PART_OF_DAY, Context);
        if (PartDay == null) {
            PartDay = MORNING_DAY;
        }
        switch (PartDay) {
            case MORNING_DAY:
                arrayList = analyzisJsonWeek(mJsonObjectData, DAY_CAL, MORNING_CAL);
                break;
            case AFTERNOON_DAY:
                arrayList = analyzisJsonWeek(mJsonObjectData, DAY_CAL, AFTERNOON_CAL);
                break;
            case EVENING_DAY:
                arrayList = analyzisJsonWeek(mJsonObjectData, DAY_CAL, EVENING_CAL);
                break;
            default:
                break;
        }
        return arrayList;
    }

//    class ReadJsonScheduleDay extends AsyncTask<String, Integer, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            return eventPostRequest(getModuleInfor(SCHEDULE, Context), addJsonRequestScheduleDay().toString(), getUserid(), getPass());
//
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            ScheduleAdapter adapter = new ScheduleAdapter(Context, getListSchedule(s));
//            mScheduleFmView.setScheduleDay(adapter);
//            mScheduleFmView.closeProgress();
//            super.onPostExecute(s);
//        }
//    }
    //    class ReadJsonScheduleWeek extends AsyncTask<String, Integer, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            return eventPostRequest(getModuleInfor(SCHEDULE, Context), addJsonScheduleRequestWeek().toString(), getUserid(), getPass());
//
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            Log.d(TAG, s);
//            ArrayList<ScheduleRow> arrayMonday = new ArrayList<ScheduleRow>();
//            ArrayList<ScheduleRow> arrayTuesday = new ArrayList<ScheduleRow>();
//            ArrayList<ScheduleRow> arrayWebnesday = new ArrayList<ScheduleRow>();
//            ArrayList<ScheduleRow> arrayThursday = new ArrayList<ScheduleRow>();
//            ArrayList<ScheduleRow> arrayFriday = new ArrayList<ScheduleRow>();
//            ArrayList<ScheduleRow> arraySaturday = new ArrayList<ScheduleRow>();
//            ArrayList<ScheduleRow> arraySunday = new ArrayList<ScheduleRow>();
//            try {
//                JSONObject mJsonObject = new JSONObject(s);
//                JSONObject mJsonObjectData = mJsonObject.getJSONObject(DATA);
//
//                arrayMonday = getListWeek(mJsonObjectData, MONDAY_CAL);
//                ScheduleWeekAdapter adapterMonday = new ScheduleWeekAdapter(Context, arrayMonday);
//                mScheduleFmView.setListMonDay(adapterMonday);
//
////                mOfficalActivity.setListViewHeightBasedOnChildren(lvMonday);
//
//                arrayTuesday = getListWeek(mJsonObjectData, TUESDAY_CAL);
//                ScheduleWeekAdapter adapterTuesday = new ScheduleWeekAdapter(Context, arrayTuesday);
//                mScheduleFmView.setListTuesDay(adapterTuesday);
//
////                mOfficalActivity.setListViewHeightBasedOnChildren(lvTuesday);
//
//                arrayWebnesday = getListWeek(mJsonObjectData, WEDNESDAY_CAL);
//                ScheduleWeekAdapter adapterWebnesday = new ScheduleWeekAdapter(Context, arrayWebnesday);
//                mScheduleFmView.setListWebnesday(adapterWebnesday);
//
////                mOfficalActivity.setListViewHeightBasedOnChildren(lvWednesday);
//
//                arrayThursday = getListWeek(mJsonObjectData, THURSDAY_CAL);
//                ScheduleWeekAdapter adapterThursday = new ScheduleWeekAdapter(Context, arrayThursday);
//                mScheduleFmView.setListThursday(adapterThursday);
////                mOfficalActivity.setListViewHeightBasedOnChildren(lvThursday);
//
//                arrayFriday = getListWeek(mJsonObjectData, FRIDAY_CAL);
//                ScheduleWeekAdapter adapterFriday = new ScheduleWeekAdapter(Context, arrayFriday);
//                mScheduleFmView.setListFriday(adapterFriday);
////                mOfficalActivity.setListViewHeightBasedOnChildren(lvPriday);
//
//                arraySaturday = getListWeek(mJsonObjectData, SATURDAY_CAL);
//                ScheduleWeekAdapter adapterSaturday = new ScheduleWeekAdapter(Context, arraySaturday);
//                mScheduleFmView.setListSaturday(adapterSaturday);
//
////                mOfficalActivity.setListViewHeightBasedOnChildren(lvSaturday);
//
//                arraySunday = getListWeek(mJsonObjectData, SUNDAY_CAL);
//                ScheduleWeekAdapter adapterSunday = new ScheduleWeekAdapter(Context, arraySunday);
//                mScheduleFmView.setListSunday(adapterSunday);
//
////                mOfficalActivity.setListViewHeightBasedOnChildren(lvSunday);
//                mScheduleFmView.setDayNameForWeek();
//            } catch (JSONException e) {
//                e.printStackTrace();
//                ShowErrorToast(Context);
//            }
//            mScheduleFmView.closeProgress();
//            super.onPostExecute(s);
//        }
//    }
}
