package com.newsaigonsoft.neoegov.OfficalActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.database.Cursor;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.widget.TextView;

import com.google.gson.Gson;
import com.newsaigonsoft.neoegov.Adapter.DocumentAdapter;
import com.newsaigonsoft.neoegov.Adapter.DocumentLookupAdapter;
import com.newsaigonsoft.neoegov.Adapter.HotLineAdapter;
import com.newsaigonsoft.neoegov.Adapter.SlideMenuAdapter;
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
import com.newsaigonsoft.neoegov.GsonObject.GsonDocument;
import com.newsaigonsoft.neoegov.SQLite.SQLite;
import com.newsaigonsoft.neoegov.Subjects.HeaderMenu;
import com.newsaigonsoft.neoegov.Subjects.SearchRow;
import com.newsaigonsoft.neoegov.Subjects.StatisticalDPMRow;
import com.newsaigonsoft.neoegov.Subjects.SumManager;
import com.unnamed.b.atv.model.TreeNode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static com.newsaigonsoft.neoegov.Subjects.KeyManager.INFOR_MODULE_SQLITE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.INPUT_PERSON_SQLITE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LIST_DOCUMENT_SQLITE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.NOTIFY_SQL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SEND_WAITING_SQLITE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TRUE;

/**
 * Created by Vinh on 12/6/2017.
 */

public class BaseOffical extends SumManager {

    public TreeNode root;
    public TreeNode Parent;
    public TreeNode Treelevel_2;
    public TreeNode Treelevel_3;
    long DocComeBackNumber;
    public String menuTitle;
    String MainTitle;
    private long processerID = 0;

    public long getProcesserID() {
        return processerID;
    }

    public void setProcesserID(long processerID) {
        this.processerID = processerID;
    }

    String dateFirst;
    String dateLast;

    public String getDateFirst() {
        return dateFirst;
    }

    public void setDateFirst(String dateFirst) {
        this.dateFirst = dateFirst;
    }

    public String getDateLast() {
        return dateLast;
    }

    public void setDateLast(String dateLast) {
        this.dateLast = dateLast;
    }

    public String getMainTitle() {
        return MainTitle;
    }

    public void setMainTitle(String mainTitle) {
        MainTitle = mainTitle;
    }

    public String getMenuTitle() {
        return menuTitle;
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    public static String saveState = null;
    public static String lastClickNode = null;
    public static String lastClickNodeName = null;

    public long getDocComeBackNumber() {
        return DocComeBackNumber;
    }

    public void setDocComeBackNumber(long docComeBackNumber) {
        DocComeBackNumber = docComeBackNumber;
    }

    public AlertDialog alertError;
    FragmentTransaction transaction;
    StatisticalDocComingFragment mStatisticalDocComingFragment;

    StatisticalDocSentFm mStatisticalDocSentFm;
    StatisticalDocInternalFm mStatisticalDocInternalFm;
    StatisticalDPMTotalFm mStatisticalDepartmentTotalFm;
    StatisticalDPMListFm mStatisticalDPMListFm;
    public OfficeLogic mOfficeLogic;
    FragmentManager manager = getFragmentManager();
    DocumentFragment mDocumentFragment;
    StatisticalDPMFm mStatisticalDepartment;
    HomeFragment mHomeFragment;
    WebViewFragment mWebViewFragment;
    SearchFragment mSearchFragment;
    ScheduleFragment mScheduleFragment;
    Handler mHandler = new Handler();
    public String CSREENCOMEBACK;
    public TextView titleActionbar;
    SQLite mSqLite = new SQLite(this, LIST_DOCUMENT_SQLITE, null, 1);
    SQLite mSqLiteNotify = new SQLite(this, NOTIFY_SQL, null, 1);
    SQLite mSqLiteInputperson = new SQLite(this, INPUT_PERSON_SQLITE, null, 1);
    SQLite mSqLiteSendWaiting = new SQLite(this, SEND_WAITING_SQLITE, null, 1);
    SQLite mSqLiteModule = new SQLite(this, INFOR_MODULE_SQLITE, null, 1);
    Gson gson;


    public GsonDocument.DataBean getDocumentRowSql(Cursor cursor){
      return new GsonDocument().new DataBean(
                cursor.getString(0),
                cursor.getInt(1),
                cursor.getString(2),
                cursor.getInt(3),
                cursor.getInt(4), Boolean.parseBoolean(cursor.getString(5)),
                cursor.getInt(6), Boolean.parseBoolean(cursor.getString(7)),
                cursor.getString(8), cursor.getString(9),
                Boolean.parseBoolean(cursor.getString(10)),
                cursor.getString(11),
                Boolean.parseBoolean(cursor.getString(12)),
                cursor.getString(13));
    }

    public boolean isClickable = true;

    boolean isShowDialog = false;


    boolean SecondLogout = false;

    public boolean isSecondLogout() {
        return SecondLogout;
    }

    public void setSecondLogout(boolean secondLogout) {
        SecondLogout = secondLogout;
    }

    public SQLite getmSqLite() {
        return mSqLite;
    }

    public SQLite getmSqLiteNotify() {
        return mSqLiteNotify;
    }

    public SQLite getmSqLiteInputperson() {
        return mSqLiteInputperson;
    }

    public SQLite getmSqLiteSendWaiting() {
        return mSqLiteSendWaiting;
    }

    public SQLite getmSqLiteModule() {
        return mSqLiteModule;
    }

    DocumentAdapter adapterDocument;
    DocumentAdapter adapterSearch;
    HotLineAdapter adapterHotLine;
    DocumentLookupAdapter adapterClickNumber;
    DocumentLookupAdapter adapterDocumentLookup;
    SearchRow mSearchRow;
    public String URLNA_COMEBACK_OFFICE_FINAL;
    boolean checkErrorServerDocumentList = false;
    boolean checkErrorServerDocumentListLookup = false;
    public SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(FORMATDATE);
    List<HeaderMenu> arrHeander;
    String urlNaForOffLine;
    String BooleanDownload = TRUE;
    ArrayList<String> arrUrlNa = new ArrayList<>();
    SlideMenuAdapter adapter;
    public int NumberOfPage;
    boolean Loading = false;

    public boolean isLoading() {
        return Loading;
    }

    public void setLoading(boolean loading) {
        Loading = loading;
    }

    public SearchRow getmSearchRow() {
        return mSearchRow;
    }

    public int getNumberOfPage() {
        return NumberOfPage;
    }

    public void setNumberOfPage(int numberOfPage) {
        NumberOfPage = numberOfPage;
    }

    String startDate;

    String endDate;

    public String getStatisStartDate() {
        return startDate;
    }

    public void setStatisStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStatisEndDate() {
        return endDate;
    }

    public void setStatisEndDate(String endDate) {
        this.endDate = endDate;
    }

    StatisticalDPMRow statisticalDPMRow;

    public StatisticalDPMRow getStatisticalDPMRow() {
        return statisticalDPMRow;
    }

    public void setStatisticalDPMRow(StatisticalDPMRow mStatisticalDPMRow) {
        this.statisticalDPMRow = mStatisticalDPMRow;
    }
}
