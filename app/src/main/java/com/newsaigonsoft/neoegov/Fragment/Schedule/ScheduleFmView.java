package com.newsaigonsoft.neoegov.Fragment.Schedule;

import com.newsaigonsoft.neoegov.Adapter.ScheduleAdapter;
import com.newsaigonsoft.neoegov.Adapter.ScheduleDayAdapter;
import com.newsaigonsoft.neoegov.Adapter.ScheduleMonthAdapter;
import com.newsaigonsoft.neoegov.Adapter.ScheduleNewWeekAdapter;
import com.newsaigonsoft.neoegov.Adapter.ScheduleWeekAdapter;
import com.newsaigonsoft.neoegov.GsonObject.GsonScheduleDay;
import com.newsaigonsoft.neoegov.Subjects.ScheduleMonthRow;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vinh on 10/20/2017.
 */

public interface ScheduleFmView {

    void setScheduleDay(ScheduleAdapter adapter);

    void closeProgress();

    void hideScheduleList();

    void showNoDataDay();

    void showScheduleList();

    void hideNoDataDay();

    void setListMonDay(ScheduleWeekAdapter adapterMonday);

    void setListTuesDay(ScheduleWeekAdapter adapterTuesday);

    void setListWebnesday(ScheduleWeekAdapter adapterWebnesday);

    void setListThursday(ScheduleWeekAdapter adapterThursday);

    void setListFriday(ScheduleWeekAdapter adapterFriday);

    void setListSaturday(ScheduleWeekAdapter adapterSaturday);

    void setListSunday(ScheduleWeekAdapter adapterSunday);

    void setDayNameForWeek();

    void ToastError(String s);

    void setScheduleDay(ScheduleDayAdapter mScheduleDayAdapter);

    void setListMonDay(ScheduleNewWeekAdapter adapterMonday);

    void setListTuesDay(ScheduleNewWeekAdapter adapterTuesday);

    void setListWebnesday(ScheduleNewWeekAdapter adapterWebnesday);

    void setListThursday(ScheduleNewWeekAdapter adapterThursday);

    void setListFriday(ScheduleNewWeekAdapter adapterFriday);

    void setListSaturday(ScheduleNewWeekAdapter adapterSaturday);

    void setListSunday(ScheduleNewWeekAdapter adapterSunday);

    void setListMonth(ScheduleMonthAdapter adapter, List<ScheduleMonthRow> arrDate);

    void addDotCalendar(ArrayList<CalendarDay> dates);

    void showListMonday(List<GsonScheduleDay.DataBean> arrMon);

    void showListTuesDay(List<GsonScheduleDay.DataBean> arrTue);

    void showListWebnesday(List<GsonScheduleDay.DataBean> arrWed);

    void showListThursday(List<GsonScheduleDay.DataBean> arrThu);

    void showListFriday(List<GsonScheduleDay.DataBean> arrFri);

    void showListSaturday(List<GsonScheduleDay.DataBean> arrSat);

    void showListSunday(List<GsonScheduleDay.DataBean> arrSun);

    void setlistWeek(ScheduleMonthAdapter monthAdapter);

    void scrollToPosition(int positionScroll, int size);

}
