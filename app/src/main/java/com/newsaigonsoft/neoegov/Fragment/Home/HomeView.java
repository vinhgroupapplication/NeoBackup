package com.newsaigonsoft.neoegov.Fragment.Home;

import android.content.Intent;

import com.newsaigonsoft.neoegov.Adapter.ScheduleAdapter;
import com.newsaigonsoft.neoegov.Adapter.ScheduleDayAdapter;
import com.newsaigonsoft.neoegov.Subjects.HomeLookupRow;
import com.newsaigonsoft.neoegov.Subjects.HotLineRow;
import com.newsaigonsoft.neoegov.Subjects.TaskRemind;

/**
 * Created by Vinh on 10/20/2017.
 */

public interface HomeView {
    void setDataForHomeScreenComing(int total, int docNotProcess, int docNotSeen, int docDemurrage);

    void setDaySchedule(String mday);

    void closeProgress();

    void setDataForHomeScreenSent(int total, int docNotProcess, int docNotSeen, int docDemurrage);

    void setComingTime(String msg);

    void setSentTime(String s);

    void setTaskTime(String s);

    void showScheduleLayout();

    void hideScheduleLayout();

    void setAdapterSchedule(ScheduleAdapter adapter);

    void showLayoutTask();

    void hideLayoutTask();

    void setTaskHomeNumber(String TaskTotal
            , String TaskProcess
            , String TaskProcessOnDue
            , String TaskProcessNearDemurrage
            , String TaskProcessDemurrage, String Reported);

    void setTaskRemind(TaskRemind mTaskRemind);

    void setHomeLookupRow(HomeLookupRow mHomeLookupRow);

    void getViewAndHotLineData(HotLineRow mHotLineRow);

    void setNullCreenComeBack();

    void showSnackBar(String msgSnackBar);

    Intent getIntentFromActivity();

    void ToastError(String s);

    void setAdapterScheduleDay(ScheduleDayAdapter adapter);

    void setTaskTotalTime(String s);

    void setRemindPersonView(int inProcess, int preLate, int late);

    void setVisibleRecieveTable(int i);

    void setVisibleSendTable(int i);

    void setVisibleHotLineTable(int i);

    void setVisibleHomeTask(int i);

    void setVisibleTimeHomeTask(int i);

    void setVisibleRemind(int gone);

    void setVisibleScheduleLayout(int i);
}
