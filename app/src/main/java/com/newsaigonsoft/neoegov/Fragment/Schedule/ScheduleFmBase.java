package com.newsaigonsoft.neoegov.Fragment.Schedule;

import android.app.Fragment;
import android.widget.TabHost;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import com.newsaigonsoft.neoegov.OfficalActivity.OfficalActivity;
import com.newsaigonsoft.neoegov.Subjects.ScheduleMonthRow;

import static com.newsaigonsoft.neoegov.Subjects.KeyManager.FORMATDATE;

/**
 * Created by Vinh on 3/7/2018.
 */

public class ScheduleFmBase extends Fragment {
    SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(FORMATDATE);
    SimpleDateFormat mSimpleDateWeek = new SimpleDateFormat("dd/MM");
    int year, monthx, day;
    Calendar mCalendarNow = Calendar.getInstance();
    int yearNow = mCalendarNow.get(Calendar.YEAR);
    int monthNow = mCalendarNow.get(Calendar.MONTH);
    int dayNow = mCalendarNow.get(Calendar.DAY_OF_MONTH);
    Calendar mCalendar, mCalendarLastDay;
    Calendar monDay;
    OfficalActivity mOfficalActivity;
    ScheduleFmLogic mScheduleFmLogic;
    TabHost tabHostTop, tabHostDate;
    List<String> mTabName;
    String LeftDay;
    String RightDay;
    List<ScheduleMonthRow> arrDate;
}
