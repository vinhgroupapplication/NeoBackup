package com.newsaigonsoft.neoegov.OfficalActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.newsaigonsoft.neoegov.Adapter.DocumentAdapter;
import com.newsaigonsoft.neoegov.Adapter.DocumentLookupAdapter;
import com.newsaigonsoft.neoegov.Adapter.HotLineAdapter;
import com.newsaigonsoft.neoegov.Adapter.SlideMenuAdapter;
import com.newsaigonsoft.neoegov.AppInforActivity.AppInforActivity;
import com.newsaigonsoft.neoegov.ChangeInforActivity.ChangeInforActivity;
import com.newsaigonsoft.neoegov.FeedBackAppActivity.FeedBackAppActivity;
import com.newsaigonsoft.neoegov.Fragment.Document.DocumentFragment;
import com.newsaigonsoft.neoegov.Fragment.Home.HomeFragment;
import com.newsaigonsoft.neoegov.Fragment.Schedule.ScheduleFragment;
import com.newsaigonsoft.neoegov.Fragment.Search.SearchFragment;
import com.newsaigonsoft.neoegov.Fragment.StatisticalDPM.StatisticalDPMFm;
import com.newsaigonsoft.neoegov.Fragment.StatisticalDPMList.StatisticalDPMListFm;
import com.newsaigonsoft.neoegov.Fragment.StatisticalDPMTotal.StatisticalDPMTotalFm;
import com.newsaigonsoft.neoegov.Fragment.StatisticalDocComing.StatisticalDocComingFragment;
import com.newsaigonsoft.neoegov.Fragment.StatisticalDocInternal.StatisticalDocInternalFm;
import com.newsaigonsoft.neoegov.Fragment.StatisticalDocSent.StatisticalDocSentFm;
import com.newsaigonsoft.neoegov.Fragment.WebViewFragment.WebViewFragment;
import com.newsaigonsoft.neoegov.GsonObject.GsonScheduleDay;
import com.newsaigonsoft.neoegov.GuideActivity.GuideActivity;
import com.newsaigonsoft.neoegov.LoginActivity.MainActivity;
import com.newsaigonsoft.neoegov.NotifyAcitivity.NotifyActivity;
import com.newsaigonsoft.neoegov.OfficalActivity.TreeViewMenu.TreeMenu;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.ScheduleDetailActivity.ScheduleDetailActivity;
import com.newsaigonsoft.neoegov.SearchActivity.SeachActivity;
import com.newsaigonsoft.neoegov.Subjects.KeyManager;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

import static com.newsaigonsoft.neoegov.R.id.name_return;
import static com.newsaigonsoft.neoegov.R.id.notify_layout;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.CODE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.CODE_STATISTICAL_DEPARTMENT;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DOC_DEMURRAGE_TYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DOC_NOT_PROCESS_TYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DOC_NOT_SEEN_TYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.JOBTITLE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.PROCESS_DEMURRAGE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.PROCESS_ON_TIME;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.STATISTIC_TYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TASK_PROCESS;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TASK_PROCESS_NEAR_DEMURRAGE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TASK_REPORTED;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.*;

public class OfficalActivity extends BaseOffical implements OfficeView, DrawerLayout.DrawerListener {

    //    @BindView(R.id.recycler_view)
//    RecyclerView mRecyclerView;
    @BindView(R.id.user_name)
    TextView tvUserName;
    @BindView(R.id.id_user)
    TextView tvAccountUser;
    @BindView(R.id.log_out)
    LinearLayout btnLogOut;
    @BindView(R.id.number_notify)
    TextView numberNofify;
    @BindView(notify_layout)
    RelativeLayout mNotify_layout;
    @BindView(R.id.parent_drawer)
    LinearLayout lnParentDrawer;
    @BindView(R.id.setting_layout)
    public RelativeLayout mSetting_layout;
    @BindView(R.id.drawer_layout)
    public DrawerLayout mDrawerLayout;
    private long mLastClickTime = System.currentTimeMillis();
    @BindView(R.id.container_tree)
    RelativeLayout tMenu;

    /*=========================================
     when you tap log out it will delete all table sqlite in android phone
 ===========================================*/
    @Optional
    @OnClick({R.id.log_out, notify_layout, R.id.setting_layout})
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.log_out:
                LogOut();
                break;
            case notify_layout:
                long now = System.currentTimeMillis();
                if (now - mLastClickTime < 500) {
                    return;
                }
                mLastClickTime = now;
                showNotiffy(mNotify_layout, this, numberNofify);
                break;
            case R.id.setting_layout:
//                showPopupWinDownSettingEvent();
                break;
            default:
                break;
        }
    }



    /*=========================
    listener out side for this activity
    ===========================*/

    BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getStringExtra(RELOADSLIDER);
            Log.d(TAG, "BroadcastReceiver Enable");
            switch (action) {
                case RELOADSLIDEROK:
                    isShowDialog = false;
                    mOfficeLogic.runRequestHttp(TRUE);
                    break;
                case RELOADSLIDERNONOK:
                    Log.d(TAG + nULL_STRING, "Turn off Request");
                    mHandler.removeCallbacksAndMessages(null);
                    break;
                case NOTIFICATION_UP_DATE:
                    showNotifiCation(numberNofify);
                    break;
//                case READLOAD_LIST_DOCUMENT:
//                    if (isCheckShowListSearch()){
//                     eventSearchStart(mSearchRow.getJsonTieuChiTimKiem(), mSearchRow.getRowItems());
//                    }else {
//                        if (isCheckShowListDocument() == true) {
//                            if (isNetworkAvailable(OfficalActivity.this)) {
//                                eventReadJsonNumberOfTotal();
//                            } else {
//                                TotalAndChangeUIDocument(urlNA);
//                            }
//                        }
//                    }
//                    break;
                default:
                    break;
            }
        }
    };

    /*=============================
    unregister broadcast
===============================*/

    @Override
    protected void onDestroy() {
        closeProgressDialog();
        this.unregisterReceiver(mBroadcastReceiver);
        mHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }


    @Override
    public void ShowProgress() {
        showProgressDialog(nULL_STRING, this, DIALOG_CENTER, true);
    }

    @Override
    protected void onPause() {
        closeProgressDialog();
        super.onPause();
    }

    @Override
    protected void onStart() {
        Log.d("Offical", "onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d("Offical", "onResume");
        super.onResume();
    }

    @Override

    protected void onStop() {
        Log.d("Offical", "onStop");
        super.onStop();
    }

    @Override
    protected void onRestart() {
        if (getLookupScreen().equals(LOOKUP_PROCESS)) {
            mOfficeLogic.RequestJsonDocument();
        } else {
            mOfficeLogic.notifySeen();
        }
        showNotifiCation(numberNofify);
        Log.d("Offical", "onRestart");
        super.onRestart();
    }

    @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offical);
        handleSSLHandshake();
        ButterKnife.bind(this);
        mOfficeLogic = new OfficeLogic(this, this);
        // start download offline
        setDownloadOffLine(false);
        // start download offline
        showProgressDialog(nULL_STRING, this, DIALOG_CENTER, true);
        initDatabase();
//        initView();
        showNotifiCation(numberNofify);
        initFragment();
        getStringIntent();
        initActionbar();
//        register broadcast
        initBroadcast();
        CSREENCOMEBACK = getIntent().getStringExtra(COME_BACK_FROM_SCREEN);
        mOfficeLogic.SliderData(CSREENCOMEBACK);
/*==============================
        url_na comeback office by InputForwardActivity send back to office activity, if it not null,
        request json again else coming home activity, it happen the same if not connection
================================*/
        mOfficeLogic.ReLoadComeBack(CSREENCOMEBACK);
    }

    private void initDatabase() {
        mSqLite.QueryData(CREATE_TABLE_SQLITE_LOOKUP_COMING);
        mSqLite.QueryData(CREATE_TABLE_SQLITE_LOOKUP_SENT);
        mSqLite.QueryData(CREATE_TABLE_SQLITE_LOOKUP_INTERNAL);
        mSqLite.QueryData(CREATE_TABLE_SQLITE_WORK_ARISE_LIST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent Data) {
//        intent.putExtra(COME_BACK_FROM_SCREEN, mConfirmConpletedView.getScreen());
//        intent.putExtra(INPUT_COME_BACK, CONFIRM_COMPLETED);
//        intent.putExtra(FORWARD_RESUILD, resuilt);
//        intent.putExtra(STATISTIC_TYPE, mConfirmConpletedView.getTatistic());
        if (requestCode == REQUEST_STATIS_CODE && resultCode == RESULT_OK) {
            mOfficeLogic.initNewArrayList();
            mOfficeLogic.RequestComeBackFromListDepartmentStatist();
            mDocumentFragment.showSimPleMessage(Data);
        }
        super.onActivityResult(requestCode, resultCode, Data);
    }

    /*==============================
    register broadcast
================================*/

    private void initBroadcast() {
        IntentFilter filter = new IntentFilter(BROADCASTLISTENNER);
        this.registerReceiver(mBroadcastReceiver, filter);
    }

    private void initActionbar() {
        mToolbar = (Toolbar) findViewById(R.id.app_bar);
        mDrawerLayout.setDrawerListener(this);
        setSupportActionBar(mToolbar);
        setActionBarIcon(R.drawable.menu_icon_slider);
        getSupportActionBar().setTitle("");
        titleActionbar = (TextView) findViewById(R.id.title_actionbar);
        mToolbar.setPadding(0, getStatusBarHeight(this), 0, 0);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                long now = System.currentTimeMillis();
//                if (now - mLastClickTime < CLICK_TIME_INTERVAL) {
//                    return;
//                }
//                mLastClickTime = now;
//                if (mHomeFragment.isAdded() ||
//                        mStatisticalDocComingFragment.isAdded() ||
//                        mStatisticalDepartment.isAdded()) {
//                    if (!mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
//                        mDrawerLayout.openDrawer(GravityCompat.START);
//                        mOfficeLogic.runRequestHttp(FALSE);
//                    }
//                    return;
//                }
//                if (mStatisticalDPMListFm.isAdded() ||
//                        mStatisticalDepartmentTotalFm.isAdded()) {
//                    setActionBarIcon(R.drawable.menu_icon_slider);
//                    manager.popBackStack();
//                    return;
//                }
//                if (mSearchFragment.isAdded()) {
//                    switch (getLookupScreen()) {
//                        case LOOKUP_PROCESS:
//                            changeUIDocument(DOCUMENT_FRAGMENT_TAG, urlNA);
//                            break;
//                        case LOOKUP_COMING:
//                            changeUIDocument(LOOKUP_COMING, null);
//                            break;
//                        case LOOKUP_SENT:
//                            changeUIDocument(LOOKUP_SENT, null);
//                            break;
//                        case LOOKUP_INTERNAL:
//                            changeUIDocument(LOOKUP_INTERNAL, null);
//                            break;
//                        default:
//                            manager.popBackStack();
//                            break;
//                    }
//                    //                    changeUISearch(true);
//                    setActionBarIcon(R.drawable.menu_icon_slider);
//                    return;
//                }
//                // back when tap icon in the home screen
//                switch (getLookupScreen()) {
//                    case DOC_NOT_PROCESS_TYPE:
//                    case STA_DOC_PROCESS_ON_TIME_FULL:
//                    case STA_DOC_NOT_PROCESS_FULL:
//                    case STA_DOC_DEMURRAGE_FULL:
//                    case DOC_NOT_SEEN_TYPE:
//                    case DOC_DEMURRAGE_TYPE:
//                    case LIST_DOCUMENT_DEPARTMENT:
//                    case TAP_DPM_DELAYS:
//                    case TAP_DPM_INDUE:
//                    case TAP_DPM_ONTIME:
//                    case TAP_DPM_OUT_OF_DATE:
//                        setActionBarIcon(R.drawable.menu_icon_slider);
//                        BackPress();
////                        setLookupScreen(nULL_STRING);
//                        break;
//                    case HOT_PROCESS_ONDUE:
//                    case HOT_PROCESS:
//                    case HOT_PROCESS_DEMURRAGE:
//                    case TASK_REPORTED:
//                    case TASK_PROCESS:
//                    case PROCESS_ON_TIME:
//                    case TASK_PROCESS_NEAR_DEMURRAGE:
//                    case PROCESS_DEMURRAGE:
//                        setActionBarIcon(R.drawable.menu_icon_slider);
//                        BackPress();
////                        setLookupScreen(nULL_STRING);
//                        break;
//                    case LOOKUP_PROCESS:
//                        setActionBarIcon(R.drawable.menu_icon_slider);
//                        BackPress();
//                        break;
//                    default:
////                        if (getLookupScreen().equals(nULL_STRING)) {
////                            setActionBarIcon(R.drawable.menu_icon_slider);
////                            BackPress();
////                        } else {
////                            if (!mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
////                                mDrawerLayout.openDrawer(GravityCompat.START);
////                                mOfficeLogic.runRequestHttp(FALSE);
////                            }
////                        }
//                        BackPress();
//                        break;
//                }
//                closeProgress();
                if (((int) mToolbar.getTag() == R.drawable.bar_back_x1) || ((int) mToolbar.getTag() == R.drawable.close_white_x1)) {
//                    setActionBarIcon(R.drawable.menu_icon_slider);
                    BackPress();
                } else {
                    if (!mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                        mDrawerLayout.openDrawer(GravityCompat.START);
                        mOfficeLogic.runRequestHttp(FALSE);
                    }
//                            }

                }
            }
        });
    }

    private void getStringIntent() {
        getInforAccountFromShareReferenced(this);
        String strPotistionSeekBar = getDefaults(NUMBERIC_SHOW_LIST_DOCUMENT, this);
        if (strPotistionSeekBar == null) {
            setLimitPager(20);
        } else {
            setLimitPager(Integer.parseInt(strPotistionSeekBar));
        }
//        Log.d(TAG, getLink());
        tvUserName.setText(getUsername());
        tvAccountUser.setText(getDefaults(JOBTITLE, this));
        DocComeBackNumber = getIntent().getLongExtra(DOCUMENT_NUMBER, 0);
    }

    private void initFragment() {
        mWebViewFragment = new WebViewFragment();
        mDocumentFragment = new DocumentFragment();
        mSearchFragment = new SearchFragment();
        mHomeFragment = new HomeFragment();
        mScheduleFragment = new ScheduleFragment();
        mStatisticalDocComingFragment = new StatisticalDocComingFragment();
        mStatisticalDepartment = new StatisticalDPMFm();
        mStatisticalDepartmentTotalFm = new StatisticalDPMTotalFm();
        mStatisticalDPMListFm = new StatisticalDPMListFm();
    }

    private void initView() {
        // stop multiclick
//        mRecyclerView.setMotionEventSplittingEnabled(false);


    }

    public void changeUIStatisticalComing(String CODE) {
        mStatisticalDocComingFragment = new StatisticalDocComingFragment();
        transaction = manager.beginTransaction();
        transaction.replace(R.id.content_offical, mStatisticalDocComingFragment, STATISTICAL_FRAGMENT_TAG);
        Bundle bundle = new Bundle();
        bundle.putString(TAP_CODE, CODE);
        mStatisticalDocComingFragment.setArguments(bundle);
        transaction.addToBackStack(STATISTICAL_FRAGMENT_TAG);
        transaction.commit();
    }

    public void changeUIStatisticalSend() {
        mStatisticalDocSentFm = new StatisticalDocSentFm();
        transaction = manager.beginTransaction();
        transaction.replace(R.id.content_offical, mStatisticalDocSentFm, STATISTICAL_FRAGMENT_SEND_TAG);
        transaction.addToBackStack(STATISTICAL_FRAGMENT_SEND_TAG);
        transaction.commit();
    }

    public void changeUIStatisticalInternal() {
        mStatisticalDocInternalFm = new StatisticalDocInternalFm();
        transaction = manager.beginTransaction();
        transaction.replace(R.id.content_offical, mStatisticalDocInternalFm, STATISTICAL_FRAGMENT_INTERNAL_TAG);
        transaction.addToBackStack(STATISTICAL_FRAGMENT_INTERNAL_TAG);
        transaction.commit();
    }

    public void changeUIStatisticalDepartmentTotal() {
        mStatisticalDepartmentTotalFm = new StatisticalDPMTotalFm();
        transaction = manager.beginTransaction();
        transaction.replace(R.id.content_offical, mStatisticalDepartmentTotalFm, STATISTICAL_FRAGMENT_TOTAL_TAG);
        transaction.addToBackStack(STATISTICAL_FRAGMENT_TOTAL_TAG);
        transaction.commit();
    }

    public void changeUIStatisticalDpmList(String TapScreen) {
        mStatisticalDPMListFm = new StatisticalDPMListFm();
        transaction = manager.beginTransaction();
        transaction.replace(R.id.content_offical, mStatisticalDPMListFm, STATISTICAL_FRAGMENT_LIST_TAG);
        Bundle bundle = new Bundle();
        bundle.putString(TAP_SCREEN, TapScreen);
        mStatisticalDPMListFm.setArguments(bundle);
        transaction.addToBackStack(STATISTICAL_FRAGMENT_LIST_TAG);
        transaction.commit();
    }


    public void changeUiHome() {
        mHomeFragment = new HomeFragment();
        transaction = manager.beginTransaction();
        transaction.replace(R.id.content_offical, mHomeFragment, HOME_FRAGMENT_TAG);
        transaction.addToBackStack(HOME_FRAGMENT_TAG);
        transaction.commit();
        manager.executePendingTransactions();
    }

    public void changeUiStatisticalDepartment() {
        if (!isNetworkAvailable(this)) {
            ShowErrorToast(this, "Lỗi kết nối");
            return;
        }
        setStatisStartDate(null);
        setStatisEndDate(null);
        mStatisticalDepartment = new StatisticalDPMFm();
        transaction = manager.beginTransaction();
        transaction.replace(R.id.content_offical, mStatisticalDepartment, STATISTICAL_DEPARTMENT_TAG);
        transaction.addToBackStack(STATISTICAL_DEPARTMENT_TAG);
        transaction.commit();
    }

    public void changeUISchedule(String TapScreen) {
        mScheduleFragment = new ScheduleFragment();
        transaction = manager.beginTransaction();
        transaction.replace(R.id.content_offical, mScheduleFragment, SCHEDULE_FRAGMENT_TAG);
        Bundle bundle = new Bundle();
        bundle.putString(TAP_SCREEN, TapScreen);
        mScheduleFragment.setArguments(bundle);
        transaction.addToBackStack(SCHEDULE_FRAGMENT_TAG);
        transaction.commit();
    }

    public void changeUISearch(boolean check) {
        if (!check) {
            mSearchFragment = new SearchFragment();
            transaction = manager.beginTransaction();
//            transaction.setCustomAnimations(R.animator.card_flip_right_in, R.animator.card_flip_right_out, R.animator.card_flip_left_in, R.animator.card_flip_left_out);
            transaction.setCustomAnimations(R.animator.enter_anim, R.animator.exit_anim);
            transaction.replace(R.id.content_offical, mSearchFragment, SEARCH_FRAGMENT_TAG);
            transaction.addToBackStack(SEARCH_FRAGMENT_TAG);
            Bundle bundle = new Bundle();
            if (getSearchScreen() == null) {
                setSearchScreen(SEARCH_PROCESS);
            }
            switch (getSearchScreen()) {
                case SEARCH_COMING:
                    bundle.putString(CHECK_SREEN, SEARCH_COMING);
                    break;
                case SEARCH_SENT:
                    bundle.putString(CHECK_SREEN, SEARCH_SENT);
                    break;
                case SEARCH_INTERNAL:
                    bundle.putString(CHECK_SREEN, SEARCH_INTERNAL);
                    break;
                default:
                    bundle.putString(CHECK_SREEN, SEARCH_PROCESS);
                    break;
            }
            mSearchFragment.setArguments(bundle);
            transaction.commit();
        } else {
            manager.popBackStack();
        }

    }

    public void BackPress() {
        String isCscreen = getLookupScreen();
//        if (mStatisticalDepartmentTotalFm.isAdded() || mStatisticalDPMListFm.isAdded()) {
//            manager.popBackStack();
//            return;
//        }
        if (mStatisticalDepartment.isAdded() || mStatisticalDocComingFragment.isAdded() || mScheduleFragment.isAdded() || mStatisticalDepartmentTotalFm.isAdded() || mStatisticalDPMListFm.isAdded()) {
            switch (isCscreen) {
                // avoid reloading fragment
                case STA_DOC_PROCESS_ON_TIME_FULL_ARISE:
                case STA_DOC_PROCESS_ON_TIME_FULL:
                case LIST_DOCUMENT_DEPARTMENT:
                case STA_DOC_NOT_PROCESS_FULL:
                case STA_DOC_DEMURRAGE_FULL:
                    manager.popBackStack();
                    break;
                default:
                    changeUiHome();
                    break;
            }
            return;
        }
        if (mSearchFragment.isAdded()) {
            switch (isCscreen) {
                case LOOKUP_PROCESS:
                    changeUIDocument(DOCUMENT_FRAGMENT_TAG, urlNA);
                    break;
                case LOOKUP_COMING:
                    changeUIDocument(LOOKUP_COMING, null);
                    break;
                case LOOKUP_SENT:
                    changeUIDocument(LOOKUP_SENT, null);
                    break;
                case LOOKUP_INTERNAL:
                    changeUIDocument(LOOKUP_INTERNAL, null);
                    break;
            }
            return;
        }
//        else {
        switch (isCscreen) {
            case TASK_REPORTED:
            case TASK_PROCESS:
            case PROCESS_ON_TIME:
            case TASK_PROCESS_NEAR_DEMURRAGE:
            case PROCESS_DEMURRAGE:
            case CODE_STATISTICAL_DEPARTMENT:
            case LOOKUP_PROCESS:
            case LOOKUP_COMING:
            case LOOKUP_SENT:
            case WORK_ARISE:
            case LOOKUP_INTERNAL:
                if (isAfterSearch()) {
                    manager.popBackStack();
                    setActionBarIcon(R.drawable.bar_back_x1);
                    setAfterSearch(false);
                } else {
                    changeUiHome();
                }
                break;
            default:
                manager.popBackStack();
                break;
        }
//        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.search_button:
//                positionPage = 1;
//                if (!mSearchFragment.isAdded()) {
//                    changeUISearch(false);
//                    setAfterSearch(false);
//                    setActionBarIcon(R.drawable.bar_back_x1);
//                } else {
//                    Log.d("VinhCN ", "Do Notthing");
//                }
                if (getSearchScreen() == null) {
                    setSearchScreen(SEARCH_PROCESS);
                }
                intent = new Intent(this, SeachActivity.class);
                intent.putExtra(CHECK_SREEN, getSearchScreen());
                intent.putExtra(TITLE_ACTION_BAR, getMenuTitle());
                intent.putExtra(IS_SCREEN, getScreenNameInSide());
                intent.putExtra(TYPE_HOME_LIST_DOCUMENT, getTypeHomeListDocument());
                intent.putExtra(STATISTIC_TYPE, getStatisticType());
                startActivity(intent);
                break;
//            case R.id.center_menu:
//                showDialogSetting();
//                break;
//            case R.id.menu_config:
//            case R.id.menu_help:
//            case R.id.menu_infor:
//                ShowErrorToast(this, getString(R.string.building_function));
//                break;
//            case R.id.menu_notify:
//                intent = new Intent(this, NotifyActivity.class);
//                intent.putExtra(TITLE_ACTION_BAR, "Thông báo");
//                startActivity(intent);
//                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setDocLookup(DocumentLookupAdapter adapterClickNumber) {
        mDocumentFragment.setAdapterListDocumentLookup(adapterClickNumber);
    }

    @Override
    public void visibleNotConnection(boolean b) {
        mDocumentFragment.visibleNotConnection(b);
    }

    @Override
    public void showDocEmpty(boolean isShow) {
        mDocumentFragment.showEmpty(isShow);
    }

    @Override
    public void HotLineAdapter(HotLineAdapter adapterHotLine) {
        mDocumentFragment.setAdapterListHotLine(adapterHotLine);
    }

    @Override
    public void setLayoutManagerRecycle() {
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(OfficalActivity.this));
    }

    @Override
    public void setAdapterSlider(SlideMenuAdapter adapter) {
//        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void closeProgress() {
        closeProgressDialog();
    }

    @Override
    public void stopRequest() {
        closeProgressDialog();
        mHandler.removeCallbacksAndMessages(null);
        if (!isShowDialog) {
            showDialogError("Lỗi kết nối", getString(R.string.su_co_duong_truyen), true);
        }
    }

    @Override
    public void createDatabaseOffline(String TableName) {
        mSqLite.QueryData(CREATE_TABLE + " " + "M" + getOnlyNumerics(TableName) + SQLLITE_DOCUMENT_LIST);
    }

    @Override
    public int getPositionPage() {
        return positionPage;
    }

    @Override
    public void showErrorDialog() {
        showDialogError("Lỗi kết nối", getString(R.string.su_co_duong_truyen), true);
    }

    @Override
    public void goToHomeScreen() {
        changeUiHome();
    }

    @Override
    public void setPositionPage(int i) {
        positionPage = i;
    }

    @Override
    public void setActionBarIcon(int icon) {
        mToolbar.setNavigationIcon(icon);
        mToolbar.setTag(icon);
        LockDrawerLayout();
    }

    @Override
    public void changeUIDocument(String isScreen, String URLNa) {

        mDocumentFragment = new DocumentFragment();
        transaction = manager.beginTransaction();

        switch (isScreen) {
            // avoid reloading fragment
            case STA_DOC_NOT_PROCESS_FULL:
            case STA_DOC_PROCESS_ON_TIME_FULL_ARISE:
            case STA_DOC_PROCESS_ON_TIME_FULL:
            case LIST_DOCUMENT_DEPARTMENT:
            case STA_DOC_DEMURRAGE_FULL:
                transaction.hide(getFragmentManager().findFragmentByTag(STATISTICAL_FRAGMENT_TAG));
                transaction.add(R.id.content_offical, mDocumentFragment, DOCUMENT_FRAGMENT_TAG);
                transaction.addToBackStack(null);
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                break;
            default:
                transaction.replace(R.id.content_offical, mDocumentFragment, DOCUMENT_FRAGMENT_TAG);
                transaction.addToBackStack(DOCUMENT_FRAGMENT_TAG);
                break;
        }


        Bundle bundle = new Bundle();
        bundle.putString(CHECK_SREEN, isScreen);
        if (URLNa != null) {
            bundle.putString(KEYURLNA, URLNa);
        }
        mDocumentFragment.setArguments(bundle);
        if (!this.isDestroyed()) {
            transaction.commit();
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //No call for super(). Bug on API Level > 11.
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
        Log.d("Slider", "onDrawerSlidess");
    }

    @Override
    public void onDrawerOpened(View drawerView) {
        Log.d("Slider", "onDrawerOpened");
        if (mDrawerLayout.isDrawerOpen(Gravity.START)) {
            if (mDatePickerDialog != null && mDatePickerDialog.isShowing()) {
                mDrawerLayout.closeDrawer(Gravity.START, false);
            }
        }

    }

    @Override
    public void onDrawerClosed(View drawerView) {
        Log.d("Slider", "onDrawerClosed");
    }

    @Override
    public void onDrawerStateChanged(int newState) {
        Log.d("Slider", "onDrawerStateChanged");
    }

    public void LockDrawerLayout() {
        if ((int) mToolbar.getTag() == R.drawable.bar_back_x1) {
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        } else {
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        }
    }

    public void StatisticalTotalClick(View view) {
        mStatisticalDepartmentTotalFm.HeaderClick(view);
    }


    public void OpenDetailSchedule(GsonScheduleDay.DataBean mBean) {
        Intent intent = new Intent(this, ScheduleDetailActivity.class);
        intent.putExtra(SCHEDULE_DETAIL, getGson().toJson(mBean));
        startActivity(intent);
    }


    class DeleteTonkenID extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
//           return  makePostRequestTokenID(LINK_DETELE_TOKEN, getDefaults(TOKEN_ID, OfficalActivity.this), "", OfficalActivity.this);
            return makePostRequestLogin(params[0], "notStringNull", Userid, Pass, getDefaults(TOKEN_ID, OfficalActivity.this), nULL_STRING);
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject mJsonObject = new JSONObject(s);
                int resuilt = mJsonObject.getInt(CODE);
                if (resuilt == 0) {
                    Log.d("Logout Resuilt: ", String.valueOf(resuilt));
                }
            } catch (JSONException e) {
                if (!isSecondLogout()) {
                    new DeleteTonkenID().execute(getLink() + LOGOUT_6_2);
                    setSecondLogout(true);
                }
                e.printStackTrace();
            }

            super.onPostExecute(s);
        }
    }

/*=============================================
    start read json total document method
===============================================*/

    public void eventReadJsonNumberOfTotal() {
        showProgressDialog(nULL_STRING, this, DIALOG_CENTER, true);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mOfficeLogic.eventGetDataScreen(DOCUMENT_FRAGMENT_TAG);

            }
        });
    }


    public void MenuClick(String menuCode, String title) {
        if (menuCode == null || title == null) {
            ShowErrorToast(this, getString(R.string.building_function));
            return;
        }
        setMenuTitle(title);
        switch (menuCode) {
            case CODE_HOME:
                setLookupScreen(HOME_SCREEN);
                changeUiHome();
                break;
            case CODE_STATISTICAL_DEPARTMENT:
                setLookupScreen(CODE_STATISTICAL_DEPARTMENT);
                changeUiStatisticalDepartment();
                break;
            case CODE_SCHEDULE_PERSON:
                setLookupScreen(LOOKUP_SCHEDULE);
                changeUISchedule(CODE_SCHEDULE_PERSON);
                break;
            case CODE_SCHEDULE_DPM:
                setLookupScreen(LOOKUP_SCHEDULE);
                changeUISchedule(CODE_SCHEDULE_DPM);
                break;
            case CODE_SCHEDULE_ORG:
                setLookupScreen(LOOKUP_SCHEDULE);
                changeUISchedule(CODE_SCHEDULE_ORG);
                break;
            case CODE_SCHEDULE:
                setLookupScreen(LOOKUP_SCHEDULE);
                changeUISchedule(CODE_SCHEDULE);
                break;
            case CODE_STATISTICAL_WORK_ARISE:
                setLookupScreen(LOOKUP_STATISTICAL_WORK_ARISE);
                changeUIStatisticalComing(CODE_STATISTICAL_WORK_ARISE);
                break;
            case CODE_STATISTICAL_COMING:
                setLookupScreen(LOOKUP_STATISTICAL_COMING);
                changeUIStatisticalComing(CODE_STATISTICAL_COMING);
                break;
            case CODE_STATISTICAL_SENT:
                setLookupScreen(LOOKUP_STATISTICAL_SEND);
                changeUIStatisticalSend();
                break;
            case CODE_STATISTICAL_INTERNAL:
                setLookupScreen(LOOKUP_STATISTICAL_INTERNAL);
                changeUIStatisticalInternal();
                break;
            case CODE_WORK_ARISE:
                urlNA = menuCode;
                setLookupScreen(WORK_ARISE);
                setSearchScreen(SEARCH_WORK_ARISE);
                mOfficeLogic.eventGetDataScreen(WORK_ARISE);
                break;
            case CODE_LOOKUP_COMING:
                urlNA = menuCode;
                setLookupScreen(LOOKUP_COMING);
                setSearchScreen(SEARCH_COMING);
                mOfficeLogic.eventGetDataScreen(LOOKUP_COMING);
                break;
            case CODE_LOOKUP_SEND:
                urlNA = menuCode;
                setLookupScreen(LOOKUP_SENT);
                setSearchScreen(SEARCH_SENT);
                mOfficeLogic.eventGetDataScreen(LOOKUP_SENT);
                break;
            case CODE_LOOKUP_LOCAL:
                urlNA = menuCode;
                setLookupScreen(LOOKUP_INTERNAL);
                setSearchScreen(SEARCH_INTERNAL);
                mOfficeLogic.eventGetDataScreen(LOOKUP_INTERNAL);
                break;
            case CODE_GUIDE:
                startActivity(new Intent(this, GuideActivity.class));
                break;
            case CODE_FEED_BACK:
                startActivity(new Intent(this, FeedBackAppActivity.class));
                break;
            case CODE_INFOR_APP:
                startActivity(new Intent(this, AppInforActivity.class));
                break;
            // do notthing
            case CODE_PROCESS:
            case CODE_LOOKUP:
            case CODE_STATISTICAL:
            case nULL_STRING:
                break;
            default:
//                urlNA = menuCode;
//                setSearchScreen(SEARCH_PROCESS);
//                setLookupScreen(LOOKUP_PROCESS);
//                mSqLite.QueryData(CREATE_TABLE + " " + " M" + getOnlyNumerics(urlNA) + SQLLITE_DOCUMENT_LIST);
//                //CREATE TABLE LOCALDATA
//                if (isNetworkAvailable(OfficalActivity.this)) {
//                    eventReadJsonNumberOfTotal();
//                } else {
//                    TotalAndChangeUIDocument(urlNA);
//                }
//                setCheckShowListDocument(true);
                setCheckShowListSearch(false);
                setSearchScreen(SEARCH_PROCESS);
                setLookupScreen(LOOKUP_PROCESS);
                if (!menuCode.equals(nULL_STRING)) {
                    urlNA = menuCode;
                    mSqLite.QueryData(CREATE_TABLE + " " + " M" + getOnlyNumerics(urlNA) + SQLLITE_DOCUMENT_LIST);
                    // TODO: 5/22/2018 temporary delete all row before reload start
                    mSqLite.QueryData("DELETE FROM" + " M" + getOnlyNumerics(urlNA));
                    // TODO: 5/22/2018 temporary delete all row before reload end
                    mOfficeLogic.arrDocument = null;
                    if (isNetworkAvailable(OfficalActivity.this)) {
                        eventReadJsonNumberOfTotal();
                    } else {
                        TotalAndChangeUIDocument(urlNA);
                    }
                }
                setCheckShowListDocument(true);
                break;
        }
        switch (menuCode) {
            // do not close menu_main
            case CODE_PROCESS:
                mOfficeLogic.SliderData(CSREENCOMEBACK);
                break;
            case CODE_LOOKUP:
            case CODE_STATISTICAL:
            case nULL_STRING:
                break;
            default:
                mDrawerLayout.closeDrawer(Gravity.LEFT);
                break;
        }
        setCheckShowListDocument(false);
    }

    public void SliderChildClick(String menuCode) {
        if (menuCode == null) {
            ShowErrorToast(this, getString(R.string.building_function));
            return;
        }
        setCheckShowListSearch(false);
        setSearchScreen(SEARCH_PROCESS);
        setLookupScreen(LOOKUP_PROCESS);
        if (!menuCode.equals(nULL_STRING)) {
            urlNA = menuCode;
            mSqLite.QueryData(CREATE_TABLE + " " + " M" + getOnlyNumerics(urlNA) + SQLLITE_DOCUMENT_LIST);
            // TODO: 5/22/2018 temporary delete all row before reload start
            mSqLite.QueryData("DELETE FROM" + " M" + getOnlyNumerics(urlNA));
            // TODO: 5/22/2018 temporary delete all row before reload end
            if (isNetworkAvailable(OfficalActivity.this)) {
                eventReadJsonNumberOfTotal();
            } else {
                TotalAndChangeUIDocument(urlNA);
            }
        }
        setCheckShowListDocument(true);
        mDrawerLayout.closeDrawer(Gravity.LEFT);
    }

    public void SliderGroupClick(String menuCode) {
        if (menuCode == null) {
            ShowErrorToast(this, getString(R.string.building_function));
            return;
        }
        switch (menuCode) {
            case CODE_HOME:
                setLookupScreen(HOME_SCREEN);
                changeUiHome();
                break;
            case CODE_STATISTICAL_DEPARTMENT:
                setLookupScreen(CODE_STATISTICAL_DEPARTMENT);
                changeUiStatisticalDepartment();
                break;
            case CODE_SCHEDULE_PERSON:
                setLookupScreen(LOOKUP_SCHEDULE);
                changeUISchedule(CODE_SCHEDULE_PERSON);
                break;
            case CODE_SCHEDULE_DPM:
                setLookupScreen(LOOKUP_SCHEDULE);
                changeUISchedule(CODE_SCHEDULE_DPM);
                break;
            case CODE_SCHEDULE_ORG:
                setLookupScreen(LOOKUP_SCHEDULE);
                changeUISchedule(CODE_SCHEDULE_ORG);
                break;
            case CODE_SCHEDULE:
                setLookupScreen(LOOKUP_SCHEDULE);
                changeUISchedule(CODE_SCHEDULE);
                break;
            case CODE_STATISTICAL_COMING:
                setLookupScreen(LOOKUP_STATISTICAL_COMING);
                changeUIStatisticalComing(CODE_STATISTICAL_COMING);
                break;
            case CODE_STATISTICAL_SENT:
                setLookupScreen(LOOKUP_STATISTICAL_SEND);
                changeUIStatisticalSend();
                break;
            case CODE_STATISTICAL_INTERNAL:
                setLookupScreen(LOOKUP_STATISTICAL_INTERNAL);
                changeUIStatisticalInternal();
                break;
            case CODE_LOOKUP_COMING:
                urlNA = menuCode;
                setLookupScreen(LOOKUP_COMING);
                setSearchScreen(SEARCH_COMING);
                mOfficeLogic.eventGetDataScreen(LOOKUP_COMING);
                break;
            case CODE_LOOKUP_SEND:
                urlNA = menuCode;
                setLookupScreen(LOOKUP_SENT);
                setSearchScreen(SEARCH_SENT);
                mOfficeLogic.eventGetDataScreen(LOOKUP_SENT);
                break;
            case CODE_LOOKUP_LOCAL:
                urlNA = menuCode;
                setLookupScreen(LOOKUP_INTERNAL);
                setSearchScreen(SEARCH_INTERNAL);
                mOfficeLogic.eventGetDataScreen(LOOKUP_INTERNAL);
                break;
            // do notthing
            case CODE_PROCESS:
            case CODE_LOOKUP:
            case CODE_STATISTICAL:
            case nULL_STRING:
                break;
            default:
                urlNA = menuCode;
                setSearchScreen(SEARCH_PROCESS);
                setLookupScreen(LOOKUP_PROCESS);
                mSqLite.QueryData(CREATE_TABLE + " " + " M" + getOnlyNumerics(urlNA) + SQLLITE_DOCUMENT_LIST);
                //CREATE TABLE LOCALDATA
                if (isNetworkAvailable(OfficalActivity.this)) {
                    eventReadJsonNumberOfTotal();
                } else {
                    TotalAndChangeUIDocument(urlNA);
                }
                setCheckShowListDocument(true);
                break;
        }
        switch (menuCode) {
            // do not close menu_main
            case CODE_PROCESS:
                mOfficeLogic.SliderData(CSREENCOMEBACK);
                break;
            case CODE_LOOKUP:
            case CODE_STATISTICAL:
            case nULL_STRING:
                break;
            default:
                mDrawerLayout.closeDrawer(Gravity.LEFT);
                break;
        }
        setCheckShowListDocument(false);
    }

/*=========================
    get total from sqllite
===========================*/

    public void TotalAndChangeUIDocument(String urlNa) {
        Cursor cursor = mSqLite.GetData("select * from '" + "M" + getOnlyNumerics(urlNa) + "'");
        setNumberOfPage(cursor.getCount());
        mOfficeLogic.eventGetDataScreen(DOCUMENT_FRAGMENT_TAG);
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (mDrawerLayout.isDrawerOpen(Gravity.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mDrawerLayout.openDrawer(GravityCompat.START);
                    mOfficeLogic.runRequestHttp(FALSE);
                }
            }, 1000);
        }
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(Gravity.START)) {
            mDrawerLayout.closeDrawer(Gravity.START);
            return;
        }
        if (mHomeFragment.isAdded()) {
            exitAppAndFinishAllActivity(this);
        } else {
            setActionBarIcon(R.drawable.menu_icon_slider);
            BackPress();
//            setLookupScreen(nULL_STRING);
        }
    }

    private void showDialogSetting() {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        final Dialog mDialog = new Dialog(this);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(R.layout.dialog_setting_menu);
        TextView btnDismiss = (TextView) mDialog.findViewById(R.id.diss_miss);
        btnDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
        mDialog.show();
    }

    public void showDialogError(final String msg, String message, boolean Reload) {
        AlertDialog.Builder builder = new AlertDialog.Builder(OfficalActivity.this);
        builder.setTitle(Html.fromHtml(msg));
        builder.setCancelable(true);
//        mProgressDialog.setCanceledOnTouchOutside(false);
        if (Reload) {
            builder.setMessage(message);
            builder.setPositiveButton(
                    getString(R.string.continue_offline),
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            isShowDialog = true;
                            mOfficeLogic.upDateSliderMenu(readFromFile(OfficalActivity.this, SLIDERFILE), false);
                        }
                    });
        }
        builder.setNegativeButton(getString(R.string.thoat_chuong_trinh), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                exitAppAndFinishAllActivity(OfficalActivity.this);
            }
        });
        alertError = builder.create();
        alertError.setCanceledOnTouchOutside(false);
        alertError.show();
    }

    @Override
    public void setDocLookupHome(DocumentLookupAdapter adapterDocumentLookup) {
        mDocumentFragment.setAdapterListDocumentLookup(adapterDocumentLookup);
    }

    @Override
    public void setListDoc(DocumentAdapter adapterDocument) {
        mDocumentFragment.setAdapterListVanPham(adapterDocument);

    }

    @Override
    public void showProgress() {
        showProgressDialog("", this, DIALOG_CENTER, true);
    }

    @Override
    public void setTypeStatistical() {
        setStatisticType(getIntent().getIntExtra(STATISTIC_TYPE, 0));
    }

    @Override
    public DocumentFragment getDocumentFragment() {
        return mDocumentFragment;
    }

    @Override
    public void ToastError(String s) {
        ShowErrorToast(this);
        Log.d(TAG, s);
    }

    @Override
    public void disibleInputSearchTop(boolean b) {
        mSearchFragment.disibleInputSearchTop(b);
    }

    @Override
    public void closeSwipe() {
        mDocumentFragment.visibleSwipeLayout(false);
        if (mDocumentFragment.lv != null) {
            mDocumentFragment.lv.setEnabled(true);
        }

    }

    @Override
    public void setLoading() {
        setLoading(false);
    }

    @Override
    public void LockListDocument() {
        setLoading(true);
        if (mDocumentFragment.lv != null) {
            mDocumentFragment.lv.setEnabled(false);
        }

    }

    @Override
    public void SetTreeList(AndroidTreeView tView, String forward, TreeNode root) {
        if (tMenu != null) {
            tMenu.removeAllViews();
            TreeNode newroot = root;
            AndroidTreeView newTree = new AndroidTreeView(this, newroot);
            newTree.setDefaultViewHolder(TreeMenu.class);
            newTree.setDefaultContainerStyle(R.style.TreeNodeStyle);
            tMenu.addView(newTree.getView());
            if (saveState != null) {
                if (saveState.equals(nULL_STRING)) {
                    tView.collapseAll();
                } else {
                    newTree.restoreState(saveState);
                }
            }
            newTree.setDefaultAnimation(true);
        } else {
            tMenu.addView(tView.getView());
        }
    }


    @Override
    public void setHomeTitle(String pageName) {
        if (mHomeFragment != null && mHomeFragment.isAdded()) {
            titleActionbar.setText(pageName);
        }
    }

    @Override
    public void SettingCountClick(View v) {
        switch (v.getId()) {
            case R.id.change_position:
                Toast.makeText(this, "change_position", Toast.LENGTH_SHORT).show();
                break;
            case R.id.change_profile:
                startActivity(new Intent(this, ChangeInforActivity.class));
                break;
            case R.id.change_pass:
                Toast.makeText(this, "change_pass", Toast.LENGTH_SHORT).show();
                break;
            case R.id.log_out:
                LogOut();
                break;
            default:
                break;
        }
        if (mDrawerLayout.isDrawerOpen(Gravity.START)) {
            mDrawerLayout.closeDrawer(Gravity.START);
        }

    }

    private void LogOut() {
        new DeleteTonkenID().execute(getLink() + LOGOUT_6_1);
        setDownloadOffLine(true);
        writeToFile(nULL_STRING, this, SLIDERFILE);
        Cursor cursor = mSqLite.GetData(GET_NAME_FROM_DOCUMENT_LIST_SQLITE_DATABASE);
        List<String> tables = new ArrayList<>();
        while (cursor.moveToNext()) {
            tables.add(cursor.getString(0));
            mSqLite.QueryData(DROP_TABLE_IF_EXISTS + cursor.getString(0) + "'");
        }
        mSqLiteModule.QueryData(DROP_TABLE_MODULE);
        mSqLiteInputperson.QueryData(DROP_TABLE_INPUT_PERSON);
        mSqLiteSendWaiting.QueryData(DROP_TABLE_SEND_WAITING);
        mSqLiteSendWaiting.QueryData(DROP_TABLE_SEND_WAITING);
        mSqLite.QueryData(DROP_TABLE_LOOKUP_COMING);
        mSqLite.QueryData(DROP_TABLE_LOOKUP_SENT);
        mSqLite.QueryData(DROP_TABLE_LOOKUP_INTERNAL);
        mSqLiteNotify.QueryData(DROP_TABLE_NOTIFY);
        Intent intent = new Intent(OfficalActivity.this, MainActivity.class);
        setDefaults(CHECKLOGIN, CHECKLOGINFALSE, OfficalActivity.this);
        startActivity(intent);
        finish();
        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
        setDefaults(NUMBER_NOTIFY, String.valueOf(0), getApplicationContext());
        saveState = null;
    }


//    private void initView() {
////        lv = (ExpandableListView) findViewById(R.id.menu_list);
//        lnParentDrawer = (LinearLayout) findViewById(R.id.parent_drawer);
//        mNotify_layout = (RelativeLayout) findViewById(notify_layout);
//        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
//        numberNofify = (TextView) findViewById(R.id.number_notify);
//        mToolbar = (Toolbar) findViewById(R.id.app_bar);
//        tvUserName = (TextView) findViewById(R.id.user_name);
//        tvAccountUser = (TextView) findViewById(R.id.id_user);
//        btnLogOut = (LinearLayout) findViewById(R.id.log_out);
//        mSetting_layout = (RelativeLayout) findViewById(R.id.setting_layout);
//        btnLogOut.setOnClickListener(this);
//        mNotify_layout.setOnClickListener(this);
//        mSetting_layout.setOnClickListener(this);
//        // stop multiclick
//        mRecyclerView.setMotionEventSplittingEnabled(false);
//
//
//    }
//
//
//
//    @Override
//    public void onClick(View v) {
//        int id = v.getId();
//        switch (id) {
//            case R.id.log_out:
//                new DeleteTonkenID().execute(getLink() + LOGOUT_6_1);
//                setDownloadOffLine(true);
//                writeToFile(nULL_STRING, this, SLIDERFILE);
//                Cursor cursor = mSqLite.GetData(GET_NAME_FROM_DOCUMENT_LIST_SQLITE_DATABASE);
//                List<String> tables = new ArrayList<>();
//                while (cursor.moveToNext()) {
//                    tables.add(cursor.getString(0));
//                    mSqLite.QueryData(DROP_TABLE_IF_EXISTS + cursor.getString(0) + "'");
//                }
//                mSqLiteModule.QueryData(DROP_TABLE_MODULE);
//                mSqLiteInputperson.QueryData(DROP_TABLE_INPUT_PERSON);
//                mSqLiteSendWaiting.QueryData(DROP_TABLE_SEND_WAITING);
//                mSqLiteSendWaiting.QueryData(DROP_TABLE_SEND_WAITING);
//                mSqLite.QueryData(DROP_TABLE_LOOKUP_COMING);
//                mSqLite.QueryData(DROP_TABLE_LOOKUP_SENT);
//                mSqLite.QueryData(DROP_TABLE_LOOKUP_INTERNAL);
//                mSqLiteNotify.QueryData(DROP_TABLE_NOTIFY);
//                Intent intent = new Intent(OfficalActivity.this, MainActivity.class);
//                setDefaults(CHECKLOGIN, CHECKLOGINFALSE, OfficalActivity.this);
//                startActivity(intent);
//                finish();
//                break;
//            case notify_layout:
//                long now = System.currentTimeMillis();
//                if (now - mLastClickTime < 500) {
//                    return;
//                }
//                mLastClickTime = now;
//                showNotiffy(mNotify_layout, this, numberNofify);
//                break;
//            case R.id.setting_layout:
////                showPopupWinDownSettingEvent();
//                break;
//            default:
//                break;
//        }
//    }

}




