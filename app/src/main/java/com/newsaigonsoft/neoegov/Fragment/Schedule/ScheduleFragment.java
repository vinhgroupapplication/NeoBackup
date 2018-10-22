package com.newsaigonsoft.neoegov.Fragment.Schedule;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import com.newsaigonsoft.neoegov.Action.ConfirmConpleted.decorators.EventDecorator;
import com.newsaigonsoft.neoegov.Adapter.ScheduleAdapter;
import com.newsaigonsoft.neoegov.Adapter.ScheduleDayAdapter;
import com.newsaigonsoft.neoegov.Adapter.ScheduleMonthAdapter;
import com.newsaigonsoft.neoegov.Adapter.ScheduleNewWeekAdapter;
import com.newsaigonsoft.neoegov.Adapter.ScheduleWeekAdapter;
import com.newsaigonsoft.neoegov.GsonObject.GsonScheduleDay;
import com.newsaigonsoft.neoegov.OfficalActivity.OfficalActivity;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.ScheduleMonthRow;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;
import com.prolificinteractive.materialcalendarview.format.ArrayWeekDayFormatter;
import com.prolificinteractive.materialcalendarview.format.MonthArrayTitleFormatter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.Optional;

import static com.newsaigonsoft.neoegov.Subjects.KeyManager.AFTERNOON_DAY;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.CHOOSE_DAY;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.CHOOSE_MONTH;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.CHOOSE_WEEK;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DIALOG_CENTER;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.EVENING_DAY;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.MORNING_DAY;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.MORNING_WEEK;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.REMEMBER_CHOOSE_DAY_OR_WEEK;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.REMEMBER_PART_OF_DAY;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.REMEMBER_PART_OF_WEEK;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAG;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_SCREEN;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.nULL_STRING;
import static com.newsaigonsoft.neoegov.Subjects.SumManager.getItemHeightofListView;

/**
 * Created by Vinh on 6/1/2017.
 */

public class ScheduleFragment extends ScheduleFmBase implements ScheduleFmView {
    @BindView(R.id.text_messages)
    TextView tvErrorMessages;
    @BindView(R.id.layout_nodata)
    LinearLayout mLinearNoDataDay;
    @BindView(R.id.textview_day)
    TextView btnDay;
    @BindView(R.id.textview_week)
    TextView btnWeek;
    @BindView(R.id.tv_morning)
    TextView btnMorning;
    @BindView(R.id.tv_afternoon)
    TextView btnAfternoon;
    @BindView(R.id.tv_evening)
    TextView btnEvening;
    @BindView(R.id.text_day_left)
    TextView tvDayChooseLeft;
    @BindView(R.id.text_day_right)
    TextView tvDayChooseRight;
    @BindView(R.id.text_day_center)
    TextView tvDayChooseCenter;
    @BindView(R.id.title_Schedule)
    TextView tvTitleSchedule;
    @BindView(R.id.monday)
    TextView tvMonday;
    @BindView(R.id.tuesday)
    TextView tvTuesday;
    @BindView(R.id.wednesday)
    TextView tvWednesday;
    @BindView(R.id.thursday)
    TextView tvThursday;
    @BindView(R.id.friday)
    TextView tvFriday;
    @BindView(R.id.saturday)
    TextView tvSaturday;
    @BindView(R.id.sunday)
    TextView tvSunday;
    @BindView(R.id.title_day)
    TextView tvTitleDay;
    @BindView(R.id.lv_noidung)
    ListView lvContent;
    @BindView(R.id.monday_list)
    ListView lvMonday;
    @BindView(R.id.Tuesday_list)
    ListView lvTuesday;
    @BindView(R.id.Wednesday_list)
    ListView lvWednesday;
    @BindView(R.id.Thursday_list)
    ListView lvThursday;
    @BindView(R.id.friday_list)
    ListView lvFriday;
    @BindView(R.id.Saturday_list)
    ListView lvSaturday;
    @BindView(R.id.Sunday_list)
    ListView lvSunday;
    @BindView(R.id.btn_choose_day)
    ImageView btnChooseDay;
    @BindView(R.id.choose_day_previous)
    ImageView btnChoosePrevious;
    @BindView(R.id.choose_day_next)
    ImageView btnChooseNext;
    @BindView(R.id.linear_week_list)
    LinearLayout tableListWeek;
    @BindView(R.id.choose_week_layout)
    LinearLayout lnChooseWeekLayout;
    @BindView(R.id.tab_day)
    LinearLayout lnTabDay;
    @BindView(R.id.arrow_monday)
    ImageView imgArrowMonday;
    @BindView(R.id.arrow_tuesday)
    ImageView imgArrowTuesDay;
    @BindView(R.id.arrow_wednesday)
    ImageView imgArrowWednesDay;
    @BindView(R.id.arrow_thursday)
    ImageView imgArrowThursDay;
    @BindView(R.id.arrow_friday)
    ImageView imgArrowFriday;
    @BindView(R.id.arrow_saturday)
    ImageView imgArrowSaturday;
    @BindView(R.id.arrow_sunday)
    ImageView imgArrowSunday;
    @BindView(R.id.icon_monday)
    ImageView imgIconMonday;
    @BindView(R.id.icon_tuesday)
    ImageView imgIconTuesday;
    @BindView(R.id.icon_wednesday)
    ImageView imgIconWednesday;
    @BindView(R.id.icon_thursday)
    ImageView imgIconThursday;
    @BindView(R.id.icon_friday)
    ImageView imgIconFriday;
    @BindView(R.id.icon_saturday)
    ImageView imgIconSaturday;
    @BindView(R.id.icon_sunday)
    ImageView imgIconSunday;
    @BindView(R.id.layout_day)
    LinearLayout lnDayLayout;
    @BindView(R.id.layout_week)
    LinearLayout lnWeekLayout;
    @BindView(R.id.layout_month)
    LinearLayout lnMonthLayout;
    @BindView(R.id.calendarView)
    MaterialCalendarView widget;
    @BindView(R.id.lv_month)
    ListView lvMonth;
    @BindView(R.id.layout_monday)
    LinearLayout lnMonday;
    @BindView(R.id.layout_tuesday)
    LinearLayout lnTuesday;
    @BindView(R.id.layout_wednesday)
    LinearLayout lnWednesday;
    @BindView(R.id.layout_thursday)
    LinearLayout lnThursday;
    @BindView(R.id.layout_friday)
    LinearLayout lnFriday;
    @BindView(R.id.layout_saturday)
    LinearLayout lnSaturday;
    @BindView(R.id.layout_sunday)
    LinearLayout lnSunday;
    @BindView(R.id.lv_week)
    ListView lvWeek;

    @OnItemClick(R.id.lv_noidung)
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mScheduleFmLogic.OpenDetail(position);
    }


    @Optional
    @OnClick({R.id.arrow_monday, R.id.arrow_tuesday, R.id.arrow_wednesday,
            R.id.arrow_thursday, R.id.arrow_friday, R.id.arrow_saturday,
            R.id.arrow_sunday, R.id.textview_day, R.id.textview_week,
            R.id.tv_morning, R.id.tv_afternoon, R.id.tv_evening,
            R.id.btn_choose_day, R.id.choose_day_previous, R.id.choose_day_next})
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.arrow_monday:
                showListMonday(imgArrowMonday, lvMonday, imgIconMonday, tvMonday);
                break;
            case R.id.arrow_tuesday:
                showListMonday(imgArrowTuesDay, lvTuesday, imgIconTuesday, tvTuesday);
                break;
            case R.id.arrow_wednesday:
                showListMonday(imgArrowWednesDay, lvWednesday, imgIconWednesday, tvWednesday);
                break;
            case R.id.arrow_thursday:
                showListMonday(imgArrowThursDay, lvThursday, imgIconThursday, tvThursday);
                break;
            case R.id.arrow_friday:
                showListMonday(imgArrowFriday, lvFriday, imgIconFriday, tvFriday);
                break;
            case R.id.arrow_saturday:
                showListMonday(imgArrowSaturday, lvSaturday, imgIconSaturday, tvSaturday);
                break;
            case R.id.arrow_sunday:
                showListMonday(imgArrowSunday, lvSunday, imgIconSunday, tvSunday);
                break;
            case R.id.textview_day:
                tvTitleDay.setVisibility(View.GONE);
                tvTitleSchedule.setText("LỊCH CHÍNH THỨC TRONG NGÀY");
                tableListWeek.setVisibility(View.GONE);
                lvContent.setVisibility(View.VISIBLE);
                btnDay.setBackgroundResource(R.drawable.setting_loading_bar_blue);
                btnDay.setTextColor(Color.WHITE);
                btnWeek.setBackgroundResource(R.drawable.setting_loading_bar);
                btnWeek.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorButtonLoadingSetting));
                mOfficalActivity.setDefaults(REMEMBER_CHOOSE_DAY_OR_WEEK, CHOOSE_DAY, getActivity());
//                setDayDefault();
                setListDay();
                break;
            case R.id.textview_week:
                tvTitleDay.setVisibility(View.VISIBLE);
                tvTitleSchedule.setText("LỊCH CHÍNH THỨC TRONG TUẦN");
                tableListWeek.setVisibility(View.VISIBLE);
                lvContent.setVisibility(View.GONE);
                btnWeek.setBackgroundResource(R.drawable.setting_loading_bar_blue);
                btnWeek.setTextColor(Color.WHITE);
                btnDay.setBackgroundResource(R.drawable.setting_loading_bar);
                btnDay.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorButtonLoadingSetting));
                mOfficalActivity.setDefaults(REMEMBER_CHOOSE_DAY_OR_WEEK, CHOOSE_WEEK, getActivity());
//                setDayDefault();
                setListDay();
                break;
            case R.id.tv_morning:
                btnMorning.setBackgroundResource(R.drawable.setting_loading_bar_blue);
                btnMorning.setTextColor(Color.WHITE);
                btnAfternoon.setBackgroundResource(android.R.color.transparent);
                btnAfternoon.setTextColor(Color.BLACK);
                btnEvening.setBackgroundResource(android.R.color.transparent);
                btnEvening.setTextColor(Color.BLACK);
//                if (mOfficalActivity.getDefaults(REMEMBER_CHOOSE_DAY_OR_WEEK, getActivity()).equals(CHOOSE_DAY)) {
                mOfficalActivity.setDefaults(REMEMBER_PART_OF_DAY, MORNING_DAY, getActivity());
//                } else {
//                    mOfficalActivity.setDefaults(REMEMBER_PART_OF_WEEK, MORNING_WEEK, getActivity());
//                }
                setListDay();
                break;
            case R.id.tv_afternoon:
                btnAfternoon.setBackgroundResource(R.drawable.setting_loading_bar_blue);
                btnAfternoon.setTextColor(Color.WHITE);
                btnMorning.setBackgroundResource(android.R.color.transparent);
                btnMorning.setTextColor(Color.BLACK);
                btnEvening.setBackgroundResource(android.R.color.transparent);
                btnEvening.setTextColor(Color.BLACK);
//                if (mOfficalActivity.getDefaults(REMEMBER_CHOOSE_DAY_OR_WEEK, getActivity()).equals(CHOOSE_DAY)) {
                mOfficalActivity.setDefaults(REMEMBER_PART_OF_DAY, AFTERNOON_DAY, getActivity());
//                } else {
//                    mOfficalActivity.setDefaults(REMEMBER_PART_OF_WEEK, AFTERNOON_WEEK, getActivity());
//                }
                setListDay();
                break;
            case R.id.tv_evening:
                btnEvening.setBackgroundResource(R.drawable.setting_loading_bar_blue);
                btnEvening.setTextColor(Color.WHITE);
                btnAfternoon.setBackgroundResource(android.R.color.transparent);
                btnAfternoon.setTextColor(Color.BLACK);
                btnMorning.setBackgroundResource(android.R.color.transparent);
                btnMorning.setTextColor(Color.BLACK);
//                if (mOfficalActivity.getDefaults(REMEMBER_CHOOSE_DAY_OR_WEEK, getActivity()).equals(CHOOSE_DAY)) {
                mOfficalActivity.setDefaults(REMEMBER_PART_OF_DAY, EVENING_DAY, getActivity());
//                } else {
//                    mOfficalActivity.setDefaults(REMEMBER_PART_OF_WEEK, EVENING_WEEK, getActivity());
//                }
                setListDay();
                break;
            case R.id.btn_choose_day:
                if (mOfficalActivity.mDatePickerDialog == null) {
                    showDataPickDialog(tvDayChooseLeft, getActivity());
                }
                break;
            case R.id.choose_day_previous:
                switch (getChoose(REMEMBER_CHOOSE_DAY_OR_WEEK)) {
                    case CHOOSE_DAY:
                        mCalendar.add(Calendar.DATE, -1);
                        tvDayChooseLeft.setText(
//                                mOfficalActivity.getNameOfDay(mCalendar.get(Calendar.DAY_OF_WEEK)) + ", " +
                                mSimpleDateFormat.format(mCalendar.getTime()));
                        tvDayChooseRight.setVisibility(View.GONE);
                        tvDayChooseCenter.setVisibility(View.GONE);
                        break;
                    case CHOOSE_WEEK:
                        monDay.add(Calendar.DATE, -7);
                        tvDayChooseLeft.setText(
//                                mOfficalActivity.getNameOfDay(monDay.get(Calendar.DAY_OF_WEEK)) + ", " +
                                mSimpleDateFormat.format(monDay.getTime()));
                        tvDayChooseRight.setVisibility(View.VISIBLE);
                        tvDayChooseCenter.setVisibility(View.VISIBLE);
                        getSunDayOfWeek(tvDayChooseRight);
                        break;
                    default:
                        break;
                }
                setListDay();
                break;
            case R.id.choose_day_next:
                switch (getChoose(REMEMBER_CHOOSE_DAY_OR_WEEK)) {
                    case CHOOSE_DAY:
                        tvDayChooseRight.setVisibility(View.GONE);
                        tvDayChooseCenter.setVisibility(View.GONE);
                        mCalendar.add(Calendar.DATE, 1);
                        tvDayChooseLeft.setText(
//                                mOfficalActivity.getNameOfDay(mCalendar.get(Calendar.DAY_OF_WEEK)) + ", " +
                                mSimpleDateFormat.format(mCalendar.getTime()));
                        break;
                    case CHOOSE_WEEK:
                        monDay.add(Calendar.DATE, 7);
                        tvDayChooseLeft.setText(
//                                mOfficalActivity.getNameOfDay(monDay.get(Calendar.DAY_OF_WEEK)) + ", " +
                                mSimpleDateFormat.format(monDay.getTime()));
                        tvDayChooseRight.setVisibility(View.VISIBLE);
                        tvDayChooseCenter.setVisibility(View.VISIBLE);
                        getSunDayOfWeek(tvDayChooseRight);
                        break;
                    default:
                        break;
                }
                setListDay();
                break;

            default:
                break;
        }

    }

    private void showListMonday(ImageView imgArrow, ListView lv, ImageView icon, TextView tv) {
        if ((int) imgArrow.getTag() == R.drawable.right_arrow_x1) {
            imgArrow.setImageResource(R.drawable.down_arrow_x1);
            imgArrow.setTag(R.drawable.down_arrow_x1);
            lv.setVisibility(View.VISIBLE);
            icon.setImageResource(R.drawable.wall_clock_green_x1);
            tv.setTextColor(getResources().getColor(R.color.text_green));
        } else {
            imgArrow.setImageResource(R.drawable.right_arrow_x1);
            imgArrow.setTag(R.drawable.right_arrow_x1);
            lv.setVisibility(View.GONE);
            icon.setImageResource(R.drawable.wall_clock_x1);
            tv.setTextColor(getResources().getColor(R.color.text_gray));
        }
    }

    public static ScheduleFragment newInstance() {
        ScheduleFragment myFragment = new ScheduleFragment();
        return myFragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);
        mOfficalActivity = (OfficalActivity) getActivity();
        ButterKnife.bind(this, view);
        mScheduleFmLogic = new ScheduleFmLogic(this, mOfficalActivity);
        mOfficalActivity.setDefaults(REMEMBER_CHOOSE_DAY_OR_WEEK, CHOOSE_DAY, getActivity());
        mOfficalActivity.titleActionbar.setText(mOfficalActivity.getMenuTitle());
        mOfficalActivity.setActionBarIcon(R.drawable.bar_back_x1);
        setDefaultView();
        initCalander();
        initTab(view);
        setTagArrow();
        initTabSchedule(view);
        setDayDefault();
        switch (getChoose(REMEMBER_CHOOSE_DAY_OR_WEEK)) {
            case CHOOSE_DAY:
                btnDay.setBackgroundResource(R.drawable.setting_loading_bar_blue);
                btnDay.setTextColor(Color.WHITE);
                btnWeek.setBackgroundResource(R.drawable.setting_loading_bar);
                btnWeek.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorButtonLoadingSetting));
                mOfficalActivity.setDefaults(REMEMBER_CHOOSE_DAY_OR_WEEK, CHOOSE_DAY, getActivity());
                Calendar cDay = Calendar.getInstance();
                int timeOfDay = cDay.get(Calendar.HOUR_OF_DAY);
                if (timeOfDay >= 0 && timeOfDay < 12) {
//                    morning
                    mOfficalActivity.setDefaults(REMEMBER_PART_OF_DAY, MORNING_DAY, getActivity());
                    btnMorning.setBackgroundResource(R.drawable.setting_loading_bar_blue);
                    btnMorning.setTextColor(Color.WHITE);
                    btnAfternoon.setBackgroundResource(android.R.color.transparent);
                    btnAfternoon.setTextColor(Color.BLACK);
                    btnEvening.setBackgroundResource(android.R.color.transparent);
                    btnEvening.setTextColor(Color.BLACK);
                } else if (timeOfDay >= 12 && timeOfDay < 16) {
//                    afternoon
                    mOfficalActivity.setDefaults(REMEMBER_PART_OF_DAY, AFTERNOON_DAY, getActivity());
                    btnAfternoon.setBackgroundResource(R.drawable.setting_loading_bar_blue);
                    btnAfternoon.setTextColor(Color.WHITE);
                    btnMorning.setBackgroundResource(android.R.color.transparent);
                    btnMorning.setTextColor(Color.BLACK);
                    btnEvening.setBackgroundResource(android.R.color.transparent);
                    btnEvening.setTextColor(Color.BLACK);
//                } else if (timeOfDay >= 16 && timeOfDay < 21) {
                } else if (timeOfDay >= 16 && timeOfDay < 24) {
                    mOfficalActivity.setDefaults(REMEMBER_PART_OF_DAY, EVENING_DAY, getActivity());
                    btnEvening.setBackgroundResource(R.drawable.setting_loading_bar_blue);
                    btnEvening.setTextColor(Color.WHITE);
                    btnAfternoon.setBackgroundResource(android.R.color.transparent);
                    btnAfternoon.setTextColor(Color.BLACK);
                    btnMorning.setBackgroundResource(android.R.color.transparent);
                    btnMorning.setTextColor(Color.BLACK);
                }
//                else if (timeOfDay >= 21 && timeOfDay < 24) {
//                    //night
//                }
                break;
            case CHOOSE_WEEK:
                btnWeek.setBackgroundResource(R.drawable.setting_loading_bar_blue);
                btnWeek.setTextColor(Color.WHITE);
                btnDay.setBackgroundResource(R.drawable.setting_loading_bar);
                btnDay.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorButtonLoadingSetting));
                mOfficalActivity.setDefaults(REMEMBER_CHOOSE_DAY_OR_WEEK, CHOOSE_WEEK, getActivity());
                Calendar c = Calendar.getInstance();
                int timeDayOfWeek = c.get(Calendar.HOUR_OF_DAY);
                if (timeDayOfWeek >= 0 && timeDayOfWeek < 12) {
//                    morning
                    mOfficalActivity.setDefaults(REMEMBER_PART_OF_DAY, MORNING_DAY, getActivity());
                    btnMorning.setBackgroundResource(R.drawable.setting_loading_bar_blue);
                    btnMorning.setTextColor(Color.WHITE);
                    btnAfternoon.setBackgroundResource(android.R.color.transparent);
                    btnAfternoon.setTextColor(Color.BLACK);
                    btnEvening.setBackgroundResource(android.R.color.transparent);
                    btnEvening.setTextColor(Color.BLACK);
                } else if (timeDayOfWeek >= 12 && timeDayOfWeek < 16) {
//                    afternoon
                    mOfficalActivity.setDefaults(REMEMBER_PART_OF_DAY, AFTERNOON_DAY, getActivity());
                    btnAfternoon.setBackgroundResource(R.drawable.setting_loading_bar_blue);
                    btnAfternoon.setTextColor(Color.WHITE);
                    btnMorning.setBackgroundResource(android.R.color.transparent);
                    btnMorning.setTextColor(Color.BLACK);
                    btnEvening.setBackgroundResource(android.R.color.transparent);
                    btnEvening.setTextColor(Color.BLACK);
//                } else if (timeOfDay >= 16 && timeOfDay < 21) {
                } else if (timeDayOfWeek >= 16 && timeDayOfWeek < 24) {
                    mOfficalActivity.setDefaults(REMEMBER_PART_OF_DAY, EVENING_DAY, getActivity());
                    btnEvening.setBackgroundResource(R.drawable.setting_loading_bar_blue);
                    btnEvening.setTextColor(Color.WHITE);
                    btnAfternoon.setBackgroundResource(android.R.color.transparent);
                    btnAfternoon.setTextColor(Color.BLACK);
                    btnMorning.setBackgroundResource(android.R.color.transparent);
                    btnMorning.setTextColor(Color.BLACK);
                }
//                else if (timeOfDay >= 21 && timeOfDay < 24) {
//                    //night
//                }
                break;
            default:
                break;
        }
        setListDay();
        return view;
    }

    private void initCalander() {
        widget.setSelectedDate(Calendar.getInstance().getTime());
        widget.setTitleFormatter(new MonthArrayTitleFormatter(getResources().getTextArray(R.array.custom_months)));
        widget.setWeekDayFormatter(new ArrayWeekDayFormatter(getResources().getTextArray(R.array.custom_weekdays)));
        widget.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
                Calendar start = Calendar.getInstance();
                start.setTime(date.getDate());
                start.set(Calendar.DAY_OF_MONTH, 1);
                Calendar end = Calendar.getInstance();
                end.setTime(date.getDate());
                end.set(Calendar.DAY_OF_MONTH, end.getActualMaximum(Calendar.DAY_OF_MONTH));
                initDateRequestMonth(start, end);
                setListDay();
            }
        });
        widget.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date.getDate());
                mScheduleFmLogic.expandFollowDate(mOfficalActivity.mSimpleDateFormat.format(calendar.getTime()));

            }
        });
//        new ApiSimulator().executeOnExecutor(Executors.newSingleThreadExecutor());
    }


//    private class ApiSimulator extends AsyncTask<Void, Void, List<CalendarDay>> {
//
//        @Override
//        protected List<CalendarDay> doInBackground(@NonNull Void... voids) {
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            Calendar calendar = Calendar.getInstance();
//            calendar.add(Calendar.MONTH, -2);
//            ArrayList<CalendarDay> dates = new ArrayList<>();
//            for (int i = 0; i < 30; i++) {
//                CalendarDay day = CalendarDay.from(calendar);
//                dates.add(day);
//                calendar.add(Calendar.DATE, 5);
//            }
//
//            return dates;
//        }
//
//        @Override
//        protected void onPostExecute(@NonNull List<CalendarDay> calendarDays) {
//            super.onPostExecute(calendarDays);
//
//            if (mOfficalActivity.isFinishing()) {
//                return;
//            }
//
//            widget.addDecorator(new EventDecorator(Color.GREEN, calendarDays));
//        }
//    }

    private void setDefaultView() {
        lnDayLayout.setVisibility(View.VISIBLE);
        lnWeekLayout.setVisibility(View.GONE);
        lnMonthLayout.setVisibility(View.GONE);
    }

    private void setTagArrow() {
        imgArrowMonday.setTag(R.drawable.right_arrow_x1);
        imgArrowTuesDay.setTag(R.drawable.right_arrow_x1);
        imgArrowWednesDay.setTag(R.drawable.right_arrow_x1);
        imgArrowThursDay.setTag(R.drawable.right_arrow_x1);
        imgArrowFriday.setTag(R.drawable.right_arrow_x1);
        imgArrowSaturday.setTag(R.drawable.right_arrow_x1);
        imgArrowSunday.setTag(R.drawable.right_arrow_x1);
    }

    private void initTabSchedule(View view) {
        List<String> arrDate = new ArrayList<>();
        tabHostDate = (TabHost) view.findViewById(R.id.tabhost);
        tabHostDate.setup();
        // get width screen
        DisplayMetrics displayMetrics = new DisplayMetrics();
        mOfficalActivity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        // get all days of month
//        Calendar start = Calendar.getInstance();
//        start.set(Calendar.DAY_OF_MONTH, 1);
//        Calendar end = Calendar.getInstance();
//        end.set(Calendar.DAY_OF_MONTH, end.getActualMaximum(Calendar.DAY_OF_MONTH));
//        end.add(Calendar.DAY_OF_YEAR, 1);
        // get 7 days
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        end.add(Calendar.DAY_OF_YEAR, 7);
        while (start.before(end)) {
            TabHost.TabSpec tab = tabHostDate.newTabSpec(mOfficalActivity.mSimpleDateFormat.format(start.getTime()));
            arrDate.add(mOfficalActivity.mSimpleDateFormat.format(start.getTime()));
            tab.setIndicator(createTabIndicator(start, width));
            tab.setContent(new EmptyTabFactory());
            tabHostDate.addTab(tab);
            start.add(Calendar.DAY_OF_MONTH, 1);
        }
        for (int i = 0; i < tabHostDate.getTabWidget().getChildCount(); i++) {
            tabHostDate.getTabWidget().getChildAt(i).setFocusableInTouchMode(true);
        }
        final Calendar crDate = Calendar.getInstance();
        tabHostDate.setCurrentTabByTag(mOfficalActivity.mSimpleDateFormat.format(crDate.getTime()));
        final int defaultPosition = tabHostDate.getCurrentTab();
        final TextView tv = (TextView) tabHostDate.getTabWidget().getChildAt(tabHostDate.getCurrentTab()).findViewById(R.id.tv_date_bottom);
        View redline = (View) tabHostDate.getTabWidget().getChildAt(tabHostDate.getCurrentTab()).findViewById(R.id.red_line);
        redline.setVisibility(View.VISIBLE);
        tv.setTextColor(Color.RED);
        tabHostDate.getTabWidget().setStripEnabled(false);
        tabHostDate.getTabWidget().setDividerDrawable(null);
        mOfficalActivity.showProgressDialog("", getActivity(), DIALOG_CENTER, true);
        mScheduleFmLogic.ReadScheduleDay(mOfficalActivity.mSimpleDateFormat.format(crDate.getTime()), getScheduleCode());
        tabHostDate.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                int selectedPage = tabHostDate.getCurrentTab();
                for (int i = 0; i < tabHostDate.getTabWidget().getChildCount(); i++) {
                    TextView tv = (TextView) tabHostDate.getTabWidget().getChildAt(i).findViewById(R.id.tv_date_bottom);
                    View redline = (View) tabHostDate.getTabWidget().getChildAt(i).findViewById(R.id.red_line);
                    tv.setTextColor(selectedPage == i ? Color.RED : Color.BLACK);
                    redline.setVisibility(selectedPage == i ? View.VISIBLE : View.INVISIBLE);
                    if (selectedPage == i) {
                        tv.setTextColor(Color.WHITE);
                        tv.setBackgroundResource(R.drawable.red_circle_tabhost);
                    } else {
                        if (i == defaultPosition) {
                            tv.setTextColor(Color.RED);
                            tv.setBackgroundResource(R.drawable.white_circle_tabhost);
                        } else {
                            tv.setTextColor(Color.BLACK);
                            tv.setBackgroundResource(R.drawable.white_circle_tabhost);
                        }
                    }
//                    View tabView = tabHost.getCurrentTabView();
//                    int scrollPos = tabView.getLeft() - (mHorizontalScrollView.getWidth() - tabView.getWidth()) / 2;
//                    mHorizontalScrollView.smoothScrollTo(scrollPos, 0);
                }
                mOfficalActivity.showProgressDialog("", getActivity(), DIALOG_CENTER, true);
                mScheduleFmLogic.ReadScheduleDay(s, getScheduleCode());
            }
        });
//        svParent.smoothScrollBy(0, 0);
        for (int i = 0; i < tabHostDate.getTabWidget().getChildCount(); i++) {
            tabHostDate.getTabWidget().getChildAt(i).setFocusableInTouchMode(false);
        }

    }


    private View createTabIndicator(Calendar calendar, int width) {
        View view = LayoutInflater.from(mOfficalActivity).inflate(R.layout.custom_tab, null);
        TextView tvBottom = (TextView) view.findViewById(R.id.tv_date_bottom);
        TextView tvTop = (TextView) view.findViewById(R.id.tv_date_top);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tvTop.getLayoutParams();
        params.width = (width / 6);
        tvTop.setLayoutParams(params);
        tvBottom.setText(String.valueOf(calendar.get(Calendar.DATE)));
        tvTop.setText(mOfficalActivity.getNameOfDay(calendar.get(Calendar.DAY_OF_WEEK)));
        return view;
    }

    private class EmptyTabFactory implements TabHost.TabContentFactory {

        @Override
        public View createTabContent(String tag) {
            return new View(mOfficalActivity);
        }

    }

    private View createTabIndicator(String title) {
        View view = LayoutInflater.from(mOfficalActivity).inflate(R.layout.custom_tab_statis_coming, null);
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_time_select);
        tvTitle.setText(title);
        return view;
    }

    public class FakeContent implements TabHost.TabContentFactory {
        Context context;

        public FakeContent(Context context) {
            this.context = context;
        }

        @Override
        public View createTabContent(String tag) {
            View view = new View(context);
            view.setMinimumHeight(0);
            view.setMinimumWidth(0);
            return view;
        }

    }


    private void initTab(View view) {
        tabHostTop = (TabHost) view.findViewById(R.id.tabhost_top_select);
        tabHostTop.setup();
        mTabName = new ArrayList<>();
        mTabName.add(mOfficalActivity.getString(R.string.in) + " " + mOfficalActivity.getString(R.string.day).toLowerCase());
        mTabName.add(mOfficalActivity.getString(R.string.in) + " " + mOfficalActivity.getString(R.string.week).toLowerCase());
        mTabName.add(mOfficalActivity.getString(R.string.in) + " " + mOfficalActivity.getString(R.string.month).toLowerCase());
        for (int i = 0; i < mTabName.size(); i++) {
            TabHost.TabSpec mTabSpec;
            mTabSpec = tabHostTop.newTabSpec(mTabName.get(i));
            mTabSpec.setIndicator(createTabIndicator(mTabName.get(i)));
            mTabSpec.setContent(new FakeContent(mOfficalActivity));
            tabHostTop.addTab(mTabSpec);
        }
        tabHostTop.getTabWidget().setStripEnabled(false);
        tabHostTop.getTabWidget().setDividerDrawable(null);
        View vlBottom = tabHostTop.getTabWidget().getChildAt(0).findViewById(R.id.bottom_line);
        vlBottom.setBackgroundColor(Color.parseColor("#ffff4444"));
        TextView tvTitle = (TextView) tabHostTop.getTabWidget().getChildAt(0).findViewById(R.id.tv_time_select);
        tvTitle.setTextColor(Color.parseColor("#ffff4444"));
        tabHostTop.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                int selectedPage = tabHostTop.getCurrentTab();
                switch (selectedPage) {
                    case 0:
//                        tvTitleDay.setVisibility(View.GONE);
//                        tvTitleSchedule.setText("LỊCH CHÍNH THỨC TRONG NGÀY");
//                        mLinearListWeek.setVisibility(View.GONE);
//                        lvContent.setVisibility(View.VISIBLE);
//                        btnDay.setBackgroundResource(R.drawable.setting_loading_bar_blue);
//                        btnDay.setTextColor(Color.WHITE);
//                        btnWeek.setBackgroundResource(R.drawable.setting_loading_bar);
//                        btnWeek.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorButtonLoadingSetting));
//                        lnTabDay.setVisibility(View.VISIBLE);
//                        lnChooseWeekLayout.setVisibility(View.GONE);
                        lnDayLayout.setVisibility(View.VISIBLE);
                        lnWeekLayout.setVisibility(View.GONE);
                        lnMonthLayout.setVisibility(View.GONE);
                        mOfficalActivity.setDefaults(REMEMBER_CHOOSE_DAY_OR_WEEK, CHOOSE_DAY, getActivity());
                        setDayDefault();
                        setListDay();
                        break;
                    case 1:
//                        tvTitleDay.setVisibility(View.VISIBLE);
//                        tvTitleSchedule.setText("LỊCH CHÍNH THỨC TRONG TUẦN");
//                        mLinearListWeek.setVisibility(View.VISIBLE);
//                        lvContent.setVisibility(View.GONE);
//                        btnWeek.setBackgroundResource(R.drawable.setting_loading_bar_blue);
//                        btnWeek.setTextColor(Color.WHITE);
//                        btnDay.setBackgroundResource(R.drawable.setting_loading_bar);
//                        btnDay.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorButtonLoadingSetting));
//                        lnTabDay.setVisibility(View.GONE);
//                        lnChooseWeekLayout.setVisibility(View.VISIBLE);
                        lnMonthLayout.setVisibility(View.GONE);
                        lnDayLayout.setVisibility(View.GONE);
                        lnWeekLayout.setVisibility(View.VISIBLE);
                        mOfficalActivity.setDefaults(REMEMBER_CHOOSE_DAY_OR_WEEK, CHOOSE_WEEK, getActivity());
                        setDayDefault();
                        setListDay();
                        break;
                    case 2:
                        Calendar start = Calendar.getInstance();
                        start.set(Calendar.DAY_OF_MONTH, 1);
                        Calendar end = Calendar.getInstance();
                        end.set(Calendar.DAY_OF_MONTH, end.getActualMaximum(Calendar.DAY_OF_MONTH));
//                        end.add(Calendar.DAY_OF_YEAR, 1);
                        initDateRequestMonth(start, end);
                        lnMonthLayout.setVisibility(View.VISIBLE);
                        lnDayLayout.setVisibility(View.GONE);
                        lnWeekLayout.setVisibility(View.GONE);
                        mOfficalActivity.setDefaults(REMEMBER_CHOOSE_DAY_OR_WEEK, CHOOSE_MONTH, getActivity());
                        setDayDefault();
                        setListDay();
                        break;
                }
                for (int i = 0; i < tabHostTop.getTabWidget().getChildCount(); i++) {
                    View vlBottom = tabHostTop.getTabWidget().getChildAt(i).findViewById(R.id.bottom_line);
                    vlBottom.setBackgroundColor(Color.parseColor(i == selectedPage ? "#ffff4444" : "#00FFFFFF"));
                    TextView tvTitle = (TextView) tabHostTop.getTabWidget().getChildAt(i).findViewById(R.id.tv_time_select);
                    tvTitle.setTextColor(Color.parseColor(i == selectedPage ? "#ffff4444" : "#777777"));
                }
            }
        });
    }


    void initDateRequestMonth(Calendar start, Calendar end) {
        arrDate = new ArrayList<>();
        LeftDay = mOfficalActivity.mSimpleDateFormat.format(start.getTime());
        RightDay = mOfficalActivity.mSimpleDateFormat.format(end.getTime());
        end.add(Calendar.DAY_OF_YEAR, 1);
        while (start.before(end)) {
            arrDate.add(new ScheduleMonthRow(mOfficalActivity.mSimpleDateFormat.format(start.getTime()), null, false));
            start.add(Calendar.DAY_OF_MONTH, 1);
        }
        Log.d("ScheduleMonth", LeftDay + " " + RightDay);
    }

    void initDateQuestWeek(Calendar start, Calendar end) {
        arrDate = new ArrayList<>();
        LeftDay = mOfficalActivity.mSimpleDateFormat.format(start.getTime());
        RightDay = mOfficalActivity.mSimpleDateFormat.format(end.getTime());
        Calendar begin = Calendar.getInstance();
        Calendar over = Calendar.getInstance();
        begin.setTime(start.getTime());
        over.setTime(end.getTime());
        over.add(Calendar.DAY_OF_YEAR, 1);
        while (begin.before(over)) {
            arrDate.add(new ScheduleMonthRow(mOfficalActivity.mSimpleDateFormat.format(begin.getTime()), null, false));
            begin.add(Calendar.DAY_OF_MONTH, 1);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem itemCenterMenu = menu.findItem(R.id.center_menu);
        itemCenterMenu.setVisible(false);
        super.onPrepareOptionsMenu(menu);
    }

    public String getChoose(String Remember) {
        String mChoose = nULL_STRING;
        switch (Remember) {
            case REMEMBER_CHOOSE_DAY_OR_WEEK:
                mChoose = mOfficalActivity.getDefaults(REMEMBER_CHOOSE_DAY_OR_WEEK, getActivity());
                if (mChoose == null) {
                    mChoose = CHOOSE_DAY;
                }
                break;
            case REMEMBER_PART_OF_DAY:
                mChoose = mOfficalActivity.getDefaults(REMEMBER_PART_OF_DAY, getActivity());
                if (mChoose == null) {
                    mChoose = MORNING_DAY;
                }
                break;
            case REMEMBER_PART_OF_WEEK:
                mChoose = mOfficalActivity.getDefaults(REMEMBER_PART_OF_WEEK, getActivity());
                if (mChoose == null) {
                    mChoose = MORNING_WEEK;
                }
                break;
        }

        return mChoose;
    }


    private void setDayDefault() {
        mCalendar = Calendar.getInstance();
        int year = mCalendar.get(Calendar.YEAR);
        int monthx = mCalendar.get(Calendar.MONTH);
        int day = mCalendar.get(Calendar.DAY_OF_MONTH);
        mCalendar.set(year, monthx, day);
        monDay = Calendar.getInstance();
        monDay.setTime(mCalendar.getTime());
        monDay.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        mCalendarLastDay = Calendar.getInstance();
//        mCalendarLastDay.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        mCalendarLastDay.setTime(monDay.getTime());
        mCalendarLastDay.add(Calendar.DAY_OF_WEEK, 6);
        switch (getChoose(REMEMBER_CHOOSE_DAY_OR_WEEK)) {
            case CHOOSE_DAY:
                tvTitleDay.setVisibility(View.GONE);
                tvTitleSchedule.setText("LỊCH CHÍNH THỨC TRONG NGÀY");
                tableListWeek.setVisibility(View.GONE);
                lvContent.setVisibility(View.VISIBLE);
                tvDayChooseLeft.setText(
//                        mOfficalActivity.getNameOfDay(mCalendar.get(Calendar.DAY_OF_WEEK)) + ", " +
                        mSimpleDateFormat.format(mCalendar.getTime()));
                tvDayChooseRight.setVisibility(View.GONE);
                tvDayChooseCenter.setVisibility(View.GONE);
                Calendar cDay = Calendar.getInstance();
                int timeDay = cDay.get(Calendar.HOUR_OF_DAY);
                if (timeDay >= 0 && timeDay < 12) {
//                    morning
                    mOfficalActivity.setDefaults(REMEMBER_PART_OF_DAY, MORNING_DAY, getActivity());
                    btnMorning.setBackgroundResource(R.drawable.setting_loading_bar_blue);
                    btnMorning.setTextColor(Color.WHITE);
                    btnAfternoon.setBackgroundResource(android.R.color.transparent);
                    btnAfternoon.setTextColor(Color.BLACK);
                    btnEvening.setBackgroundResource(android.R.color.transparent);
                    btnEvening.setTextColor(Color.BLACK);
                } else if (timeDay >= 12 && timeDay < 16) {
//                    afternoon
                    mOfficalActivity.setDefaults(REMEMBER_PART_OF_DAY, AFTERNOON_DAY, getActivity());
                    btnAfternoon.setBackgroundResource(R.drawable.setting_loading_bar_blue);
                    btnAfternoon.setTextColor(Color.WHITE);
                    btnMorning.setBackgroundResource(android.R.color.transparent);
                    btnMorning.setTextColor(Color.BLACK);
                    btnEvening.setBackgroundResource(android.R.color.transparent);
                    btnEvening.setTextColor(Color.BLACK);
//                } else if (timeOfDay >= 16 && timeOfDay < 21) {
                } else if (timeDay >= 16 && timeDay < 24) {
                    mOfficalActivity.setDefaults(REMEMBER_PART_OF_DAY, EVENING_DAY, getActivity());
                    btnEvening.setBackgroundResource(R.drawable.setting_loading_bar_blue);
                    btnEvening.setTextColor(Color.WHITE);
                    btnAfternoon.setBackgroundResource(android.R.color.transparent);
                    btnAfternoon.setTextColor(Color.BLACK);
                    btnMorning.setBackgroundResource(android.R.color.transparent);
                    btnMorning.setTextColor(Color.BLACK);
                }
                break;
            case CHOOSE_WEEK:
                tvTitleDay.setVisibility(View.VISIBLE);
                tvTitleSchedule.setText("LỊCH CHÍNH THỨC TRONG TUẦN");
                tableListWeek.setVisibility(View.VISIBLE);
                lvContent.setVisibility(View.GONE);
                tvDayChooseLeft.setText(
//                        mOfficalActivity.getNameOfDay(monDay.get(Calendar.DAY_OF_WEEK)) + ", " +
                        mSimpleDateFormat.format(monDay.getTime()));
                tvDayChooseCenter.setVisibility(View.VISIBLE);
                tvDayChooseRight.setVisibility(View.VISIBLE);
                tvDayChooseRight.setText(
//                        mOfficalActivity.getNameOfDay(mCalendarLastDay.get(Calendar.DAY_OF_WEEK)) + ", " +
                        mSimpleDateFormat.format(mCalendarLastDay.getTime()));
                initDateQuestWeek(monDay, mCalendarLastDay);
                Calendar cDayWeek = Calendar.getInstance();
                int timeDayOfWeek = cDayWeek.get(Calendar.HOUR_OF_DAY);
                if (timeDayOfWeek >= 0 && timeDayOfWeek < 12) {
//                    morning
                    mOfficalActivity.setDefaults(REMEMBER_PART_OF_DAY, MORNING_DAY, getActivity());
                    btnMorning.setBackgroundResource(R.drawable.setting_loading_bar_blue);
                    btnMorning.setTextColor(Color.WHITE);
                    btnAfternoon.setBackgroundResource(android.R.color.transparent);
                    btnAfternoon.setTextColor(Color.BLACK);
                    btnEvening.setBackgroundResource(android.R.color.transparent);
                    btnEvening.setTextColor(Color.BLACK);
                } else if (timeDayOfWeek >= 12 && timeDayOfWeek < 16) {
//                    afternoon
                    mOfficalActivity.setDefaults(REMEMBER_PART_OF_DAY, AFTERNOON_DAY, getActivity());
                    btnAfternoon.setBackgroundResource(R.drawable.setting_loading_bar_blue);
                    btnAfternoon.setTextColor(Color.WHITE);
                    btnMorning.setBackgroundResource(android.R.color.transparent);
                    btnMorning.setTextColor(Color.BLACK);
                    btnEvening.setBackgroundResource(android.R.color.transparent);
                    btnEvening.setTextColor(Color.BLACK);
//                } else if (timeOfDay >= 16 && timeOfDay < 21) {
                } else if (timeDayOfWeek >= 16 && timeDayOfWeek < 24) {
                    mOfficalActivity.setDefaults(REMEMBER_PART_OF_DAY, EVENING_DAY, getActivity());
                    btnEvening.setBackgroundResource(R.drawable.setting_loading_bar_blue);
                    btnEvening.setTextColor(Color.WHITE);
                    btnAfternoon.setBackgroundResource(android.R.color.transparent);
                    btnAfternoon.setTextColor(Color.BLACK);
                    btnMorning.setBackgroundResource(android.R.color.transparent);
                    btnMorning.setTextColor(Color.BLACK);
                }
                break;
            default:
                break;
        }


    }


    private void setDayForWeek(Calendar calendar) {
        Calendar mDaily = Calendar.getInstance();
        mDaily.setTime(calendar.getTime());
        tvMonday.setText(
//                "Thứ 2\n" +
                "Lịch ngày: " +
                        mSimpleDateWeek.format(mDaily.getTime()));
        mDaily.add(mDaily.DAY_OF_YEAR, 1);
        tvTuesday.setText(
//                "Thứ 3\n" +
                "Lịch ngày: " +
                        mSimpleDateWeek.format(mDaily.getTime()));
        mDaily.add(mDaily.DAY_OF_YEAR, 1);
        tvWednesday.setText(
//                "Thứ 4\n" +
                "Lịch ngày: " +
                        mSimpleDateWeek.format(mDaily.getTime()));
        mDaily.add(mDaily.DAY_OF_YEAR, 1);
        tvThursday.setText(
//                "Thứ 5\n" +
                "Lịch ngày: " +
                        mSimpleDateWeek.format(mDaily.getTime()));
        mDaily.add(mDaily.DAY_OF_YEAR, 1);
        tvFriday.setText(
//                "Thứ 6\n" +
                "Lịch ngày: " +
                        mSimpleDateWeek.format(mDaily.getTime()));
        mDaily.add(mDaily.DAY_OF_YEAR, 1);
        tvSaturday.setText(
//                "Thứ 7\n" +
                "Lịch ngày: " +
                        mSimpleDateWeek.format(mDaily.getTime()));
        mDaily.add(mDaily.DAY_OF_YEAR, 1);
        tvSunday.setText(
//                "Chủ Nhật\n" +
                "Lịch ngày: " +
                        mSimpleDateWeek.format(mDaily.getTime()));
        Calendar ThisDay = Calendar.getInstance();
        String StringThisDay = "";
        StringThisDay = mOfficalActivity.getNameOfDay(ThisDay.get(Calendar.DAY_OF_WEEK));
        Log.d(TAG, StringThisDay);
//        switch (StringThisDay) {
//            case "Thứ 2":
//                tvMonday.setBackgroundColor(Color.parseColor("#fdb603"));
//                break;
//            case "Thứ 3":
//                tvTuesday.setBackgroundColor(Color.parseColor("#fdb603"));
//                break;
//            case "Thứ 4":
//                tvWednesday.setBackgroundColor(Color.parseColor("#fdb603"));
//                break;
//            case "Thứ 5":
//                tvThursday.setBackgroundColor(Color.parseColor("#fdb603"));
//                break;
//            case "Thứ 6":
//                tvFriday.setBackgroundColor(Color.parseColor("#fdb603"));
//                break;
//            case "Thứ 7":
//                tvSaturday.setBackgroundColor(Color.parseColor("#fdb603"));
//                break;
//            case "Chủ Nhật":
//                tvSunday.setBackgroundColor(Color.parseColor("#fdb603"));
//                break;
//            default:
//                break;
//        }
    }

    public void showDataPickDialog(final TextView textView, Context context) {
        mCalendar = Calendar.getInstance();
        year = mCalendar.get(Calendar.YEAR);
        monthx = mCalendar.get(Calendar.MONTH);
        day = mCalendar.get(Calendar.DAY_OF_WEEK);
        mOfficalActivity.mDatePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mCalendar.set(year, month, dayOfMonth);
                switch (getChoose(REMEMBER_CHOOSE_DAY_OR_WEEK)) {
                    case CHOOSE_DAY:
                        tvDayChooseRight.setVisibility(View.GONE);
                        tvDayChooseCenter.setVisibility(View.GONE);
                        tvDayChooseLeft.setText(nULL_STRING);
                        tvDayChooseLeft.setText(
//                                mOfficalActivity.getNameOfDay(mCalendar.get(Calendar.DAY_OF_WEEK)) + ", " +
                                mSimpleDateFormat.format(mCalendar.getTime()));
                        break;
                    case CHOOSE_WEEK:
                        monDay = Calendar.getInstance();
                        monDay.setTime(mCalendar.getTime());
                        monDay.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                        tvDayChooseRight.setVisibility(View.VISIBLE);
                        tvDayChooseCenter.setVisibility(View.VISIBLE);
                        tvDayChooseLeft.setText(nULL_STRING);
                        tvDayChooseLeft.setText(
//                                mOfficalActivity.getNameOfDay(monDay.get(Calendar.DAY_OF_WEEK)) + ", " +
                                mSimpleDateFormat.format(monDay.getTime()));
                        getSunDayOfWeek(tvDayChooseRight);
                        break;
                    default:
                        break;
                }
                setListDay();
                mOfficalActivity.mDatePickerDialog = null;
            }
        }, yearNow, monthNow, dayNow);
        mOfficalActivity.mDatePickerDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                mOfficalActivity.mDatePickerDialog = null;
            }
        });
        mOfficalActivity.mDatePickerDialog.show();

    }

    @Override
    public void setScheduleDay(ScheduleAdapter adapter) {
        if (getChoose(REMEMBER_CHOOSE_DAY_OR_WEEK).equals(CHOOSE_DAY)) {
            lvContent.setVisibility(View.VISIBLE);
            lvContent.setAdapter(adapter);
        } else {
            lvContent.setVisibility(View.GONE);
        }

    }

    @Override
    public void closeProgress() {
        mOfficalActivity.closeProgressDialog();
    }

    @Override
    public void hideScheduleList() {
        lvContent.setVisibility(View.GONE);
    }

    @Override
    public void showNoDataDay() {
        if (getChoose(REMEMBER_CHOOSE_DAY_OR_WEEK).equals(CHOOSE_DAY)) {
            mLinearNoDataDay.setVisibility(View.VISIBLE);
            tvErrorMessages.setText(mOfficalActivity.isNetworkAvailable(mOfficalActivity) ? "Không có lịch." : "Không có kết nối.");
        } else {
            tableListWeek.setVisibility(View.GONE);
            mLinearNoDataDay.setVisibility(View.VISIBLE);
            tvErrorMessages.setText(mOfficalActivity.isNetworkAvailable(mOfficalActivity) ? "Không có lịch." : "Không có kết nối.");
        }
    }

    @Override
    public void showScheduleList() {
        lvContent.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideNoDataDay() {
        mLinearNoDataDay.setVisibility(View.GONE);
    }

    @Override
    public void setListMonDay(ScheduleWeekAdapter adapterMonday) {
        lvMonday.setAdapter(adapterMonday);

    }

    @Override
    public void setListTuesDay(ScheduleWeekAdapter adapterTuesday) {
        lvTuesday.setAdapter(adapterTuesday);

    }

    @Override
    public void setListWebnesday(ScheduleWeekAdapter adapterWebnesday) {
        lvWednesday.setAdapter(adapterWebnesday);

    }

    @Override
    public void setListThursday(ScheduleWeekAdapter adapterThursday) {
        lvThursday.setAdapter(adapterThursday);

    }

    @Override
    public void setListFriday(ScheduleWeekAdapter adapterFriday) {
        lvFriday.setAdapter(adapterFriday);

    }

    @Override
    public void setListSaturday(ScheduleWeekAdapter adapterSaturday) {
        lvSaturday.setAdapter(adapterSaturday);

    }

    @Override
    public void setListSunday(ScheduleWeekAdapter adapterSunday) {
        lvSunday.setAdapter(adapterSunday);

    }

    @Override
    public void setDayNameForWeek() {
        setDayForWeek(monDay);
    }

    @Override
    public void ToastError(String s) {
        mOfficalActivity.ShowErrorToast(mOfficalActivity);
        Log.d(TAG, s);
    }

    @Override
    public void setScheduleDay(ScheduleDayAdapter mScheduleDayAdapter) {
        lvContent.setAdapter(mScheduleDayAdapter);
    }

    @Override
    public void setListMonDay(ScheduleNewWeekAdapter adapterMonday) {
        lvMonday.setAdapter(adapterMonday);
    }

    @Override
    public void setListTuesDay(ScheduleNewWeekAdapter adapterTuesday) {
        lvTuesday.setAdapter(adapterTuesday);
    }

    @Override
    public void setListWebnesday(ScheduleNewWeekAdapter adapterWebnesday) {
        lvWednesday.setAdapter(adapterWebnesday);
    }

    @Override
    public void setListThursday(ScheduleNewWeekAdapter adapterThursday) {
        lvThursday.setAdapter(adapterThursday);
    }

    @Override
    public void setListFriday(ScheduleNewWeekAdapter adapterFriday) {
        lvFriday.setAdapter(adapterFriday);
    }

    @Override
    public void setListSaturday(ScheduleNewWeekAdapter adapterSaturday) {
        lvSaturday.setAdapter(adapterSaturday);
    }

    @Override
    public void setListSunday(ScheduleNewWeekAdapter adapterSunday) {
        lvSunday.setAdapter(adapterSunday);
    }

    @Override
    public void setListMonth(ScheduleMonthAdapter adapter, List<ScheduleMonthRow> arrDate) {
        lvMonth.setAdapter(adapter);
        for (int i = 0; i < arrDate.size(); i++) {
            if (arrDate.get(i).getDate().equals(mSimpleDateFormat.format(Calendar.getInstance().getTime()))) {
                scrollToPosition(i, arrDate.size());
                break;
            }
        }
    }

    @Override
    public void addDotCalendar(ArrayList<CalendarDay> dates) {
        widget.addDecorator(new EventDecorator(Color.GREEN, dates));
    }

    @Override
    public void showListMonday(List<GsonScheduleDay.DataBean> arrMon) {
        lnMonday.setVisibility(arrMon.size() == 0 ? View.GONE : View.VISIBLE);
    }

    @Override
    public void showListTuesDay(List<GsonScheduleDay.DataBean> arrTue) {
        lnTuesday.setVisibility(arrTue.size() == 0 ? View.GONE : View.VISIBLE);
    }

    @Override
    public void showListWebnesday(List<GsonScheduleDay.DataBean> arrWed) {
        lnWednesday.setVisibility(arrWed.size() == 0 ? View.GONE : View.VISIBLE);
    }

    @Override
    public void showListThursday(List<GsonScheduleDay.DataBean> arrThu) {
        lnThursday.setVisibility(arrThu.size() == 0 ? View.GONE : View.VISIBLE);
    }

    @Override
    public void showListFriday(List<GsonScheduleDay.DataBean> arrFri) {
        lnFriday.setVisibility(arrFri.size() == 0 ? View.GONE : View.VISIBLE);
    }

    @Override
    public void showListSaturday(List<GsonScheduleDay.DataBean> arrSat) {
        lnSaturday.setVisibility(arrSat.size() == 0 ? View.GONE : View.VISIBLE);
    }

    @Override
    public void showListSunday(List<GsonScheduleDay.DataBean> arrSun) {
        lnSunday.setVisibility(arrSun.size() == 0 ? View.GONE : View.VISIBLE);
    }

    @Override
    public void setlistWeek(ScheduleMonthAdapter monthAdapter) {
        lvWeek.setAdapter(monthAdapter);
    }

    @Override
    public void scrollToPosition(int positionScroll, int size) {
        int h1 = lvMonth.getHeight();
        int h2 = getItemHeightofListView(lvMonth, size);
        lvMonth.smoothScrollToPositionFromTop(positionScroll, h1 / 2 - h2 / 10, 300);
    }

    private String getScheduleCode() {
        Bundle b = getArguments();
        return b.getString(TAP_SCREEN);
    }

    public void setListDay() {
        mOfficalActivity.showProgressDialog("", getActivity(), DIALOG_CENTER, true);
        String DayOrWeek;
        DayOrWeek = mOfficalActivity.getDefaults(REMEMBER_CHOOSE_DAY_OR_WEEK, getActivity());
        if (DayOrWeek == null) {
            DayOrWeek = CHOOSE_DAY;
        }
        switch (DayOrWeek) {
            case CHOOSE_DAY:
                LeftDay = tvDayChooseLeft.getText().toString();
                mScheduleFmLogic.ReadScheduleDay(LeftDay, getScheduleCode());
                break;
            case CHOOSE_WEEK:
                LeftDay = tvDayChooseLeft.getText().toString();
                RightDay = tvDayChooseRight.getText().toString();
                mLinearNoDataDay.setVisibility(View.GONE);
                mScheduleFmLogic.ReadScheduleWeek(LeftDay, RightDay, getScheduleCode(), arrDate);
                break;
            case CHOOSE_MONTH:
                mScheduleFmLogic.ReadScheduleMonth(LeftDay, RightDay, getScheduleCode(), arrDate);
                break;
            default:
                break;
        }
    }


    private void getSunDayOfWeek(TextView textView) {
        Calendar sundayCalendar = Calendar.getInstance();
        sundayCalendar.setTime(monDay.getTime());
//        sundayCalendar.set(Calendar.DAY_OF_WEEK, monDay.get(Calendar.SUNDAY));
        sundayCalendar.add(Calendar.DAY_OF_WEEK, 6);
        textView.setText(
//                mOfficalActivity.getNameOfDay(sundayCalendar.get(Calendar.DAY_OF_WEEK)) + ", " +
                mSimpleDateFormat.format(sundayCalendar.getTime()));
        initDateQuestWeek(monDay, sundayCalendar);
    }

//    private void initView(View view) {
//        btnDay = (TextView) view.findViewById(R.id.textview_day);
//        btnWeek = (TextView) view.findViewById(R.id.textview_week);
//        lvContent = (ListView) view.findViewById(R.id.lv_noidung);
//        btnMorning = (TextView) view.findViewById(R.id.tv_morning);
//        btnAfternoon = (TextView) view.findViewById(R.id.tv_afternoon);
//        btnEvening = (TextView) view.findViewById(R.id.tv_evening);
//        btnChooseDay = (ImageView) view.findViewById(R.id.btn_choose_day);
//        tvDayChooseLeft = (TextView) view.findViewById(R.id.text_day_left);
//        tvDayChooseRight = (TextView) view.findViewById(R.id.text_day_right);
//        tvDayChooseCenter = (TextView) view.findViewById(R.id.text_day_center);
//        btnChoosePrevious = (LinearLayout) view.findViewById(R.id.choose_day_previous);
//        btnChooseNext = (LinearLayout) view.findViewById(R.id.choose_day_next);
//        tvTitleSchedule = (TextView) view.findViewById(R.id.title_Schedule);
//        mLinearListWeek = (TableLayout) view.findViewById(R.id.linear_week_list);
//        lvMonday = (ListView) view.findViewById(R.id.monday_list);
//        lvTuesday = (ListView) view.findViewById(R.id.Tuesday_list);
//        lvWednesday = (ListView) view.findViewById(R.id.Wednesday_list);
//        lvThursday = (ListView) view.findViewById(R.id.Thursday_list);
//        lvPriday = (ListView) view.findViewById(R.id.friday_list);
//        lvSaturday = (ListView) view.findViewById(R.id.Saturday_list);
//        lvSunday = (ListView) view.findViewById(R.id.Sunday_list);
//        tvMonday = (TextView) view.findViewById(R.id.monday);
//        tvTuesday = (TextView) view.findViewById(R.id.tuesday);
//        tvWednesday = (TextView) view.findViewById(R.id.wednesday);
//        tvThursday = (TextView) view.findViewById(R.id.thursday);
//        tvFriday = (TextView) view.findViewById(R.id.friday);
//        tvSaturday = (TextView) view.findViewById(R.id.saturday);
//        tvSunday = (TextView) view.findViewById(R.id.sunday);
//        tvTitleDay = (TextView) view.findViewById(R.id.title_day);
//        mLinearNoDataDay = (LinearLayout) view.findViewById(R.id.layout_nodata);
//        btnChoosePrevious.setOnClickListener(this);
//        btnChooseNext.setOnClickListener(this);
//        btnDay.setOnClickListener(this);
//        btnWeek.setOnClickListener(this);
//        btnMorning.setOnClickListener(this);
//        btnAfternoon.setOnClickListener(this);
//        btnEvening.setOnClickListener(this);
//        btnChooseDay.setOnClickListener(this);
//    }

}
