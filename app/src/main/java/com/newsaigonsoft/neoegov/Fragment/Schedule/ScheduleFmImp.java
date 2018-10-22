package com.newsaigonsoft.neoegov.Fragment.Schedule;

import com.newsaigonsoft.neoegov.Subjects.ScheduleMonthRow;

import java.util.List;

/**
 * Created by Vinh on 10/20/2017.
 */

public interface ScheduleFmImp {
    void ReadScheduleDay(String LeftDay, String ScheduleCode);
    void ReadScheduleWeek(String LeftDay, String RightDay, String ScheduleCode, List<ScheduleMonthRow> arrDate);

    void ReadScheduleMonth(String leftDay, String rightDay, String scheduleCode, List<ScheduleMonthRow> arrDate);

    void expandFollowDate(String format);

    void OpenDetail(int position);
}
