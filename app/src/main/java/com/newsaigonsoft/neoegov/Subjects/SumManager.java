package com.newsaigonsoft.neoegov.Subjects;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.newsaigonsoft.neoegov.AVolleyManager.InforRequest;
import com.newsaigonsoft.neoegov.AboutActivity.AboutActivity;
import com.newsaigonsoft.neoegov.Action.ConfirmConpleted.decorators.EventDecorator;
import com.newsaigonsoft.neoegov.Adapter.PopupNotifyAdapter;
import com.newsaigonsoft.neoegov.AppInforActivity.AppInforActivity;
import com.newsaigonsoft.neoegov.CustomViewListExpand.CountDrawable;
import com.newsaigonsoft.neoegov.CustomViewListExpand.SingleToast;
import com.newsaigonsoft.neoegov.DetailActivity.DetailForwardActivity;
import com.newsaigonsoft.neoegov.GsonObject.GsonDocument;
import com.newsaigonsoft.neoegov.NotifyAcitivity.NotifyActivity;
import com.newsaigonsoft.neoegov.OfficalActivity.OfficalActivity;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.SQLite.SQLite;
import com.newsaigonsoft.neoegov.SearchActivity.SeachActivity;
import com.newsaigonsoft.neoegov.SplashActivity.SplashScreen;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.format.ArrayWeekDayFormatter;
import com.prolificinteractive.materialcalendarview.format.MonthArrayTitleFormatter;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import me.leolin.shortcutbadger.ShortcutBadger;

import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.PLATFORM;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.STATISTIC_TYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TOKEN;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.AUTHORIZATION;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.AVI;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.BASIC;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.BROADCASTLISTENNER;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.CANCEL_SCREEN;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.CHANGE_HANDLE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.CHECKLOGIN;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.CHECKLOGINFALSE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.CHECKLOGINTRUE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.CHECK_SREEN;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.COME_BACK_FROM_SCREEN;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.CREATE_TABLE_NOTIFY_SQL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DIALOG_BOOTOM;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DIALOG_CENTER;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DIALOG_TOP;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DOC;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DOCUMENTID;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DOCX;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.EXTEND_SCREEN;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.FALSE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.FEED_BACK;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.FULLNAME_USER;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.INFOR_MODULE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.INFOR_MODULE_SQLITE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.IS_SCREEN;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.JPEG;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.JPG;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LINK;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LOOKUP_PROCESS;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.MP3;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.MP4;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.MPE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.MPEG;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.MPG;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.NOTIFICATION_UP_DATE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.NOTIFY_SCREEN;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.NOTIFY_SQL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.NUMBER_NOTIFY;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.PASSWORD_USER;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.PDF;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.PNG;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.PPT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.PPTX;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.RAR;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.RELOADSLIDER;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.RETURN_SCREEN;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.RTF;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SEARCH_PROCESS;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SELECT_FROM;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAG;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.THREEGP;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TITLE_ACTION_BAR;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TXT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TYPE_HOME_LIST_DOCUMENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.URL_CENTER;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.URL_CENTER_6_1;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.USERNAME;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.WAV;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.XLS;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.XLSX;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.ZIP;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.nULL_STRING;

/**
 * Created by VinhCN on 5/4/2017.
 */

public class SumManager extends AppCompatActivity {
    //// TODO: 5/31/2017 Login for manager case Offline
//   Context context;
//    public SumManager(Context context) {
//        this.context = context;
//        ForManagerLogin(context);
//    }
    //// TODO: 5/31/2017 Login for manager case Offline
    public boolean clicked;
    public MaterialCalendarView widget;
    Gson gson = new Gson();

//    public List<StatisCMPersonAdapter.PersonStatis> arrPersonComing;

    public android.app.AlertDialog alertDialog;
    public android.app.AlertDialog.Builder dialogBuilder;

    public ArrayList<AttachFile> arrFileAttach;

    public Gson getGson() {
        return gson;
    }

    public int positionPage = 1;

    public int getPositionPage() {
        return positionPage;
    }

    public void setPositionPage(int positionPage) {
        this.positionPage = positionPage;
    }

    public void setGson(Gson gson) {
        this.gson = gson;
    }

    public final SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(FORMATDATE);
    public static final long CLICK_TIME_INTERVAL = 300;
    BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getStringExtra(RELOADSLIDER);
            Log.d(TAG, "BroadcastReceiver Enable");
            switch (action) {
                case NOTIFICATION_UP_DATE:
                    TextView numberNofify = (TextView) findViewById(R.id.number_notify);
                    if (numberNofify != null) {
                        showNotifiCation(numberNofify);
                    }
                    break;
                default:
                    break;
            }
        }
    };

    public static void setMargins(View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }

    public void showNotiffy(RelativeLayout mNotify_Layout, Context context, TextView mNumberOfNotify) {
        showPopupWinDownNotificationEvent(context, mNotify_Layout);
        setDefaults(NUMBER_NOTIFY, "0", context);
        showNotifiCation(mNumberOfNotify);
    }

    public void showSettingMenu(RelativeLayout mNotify_Layout, Context context, TextView mNumberOfNotify) {
        showPopupWinDownSettingEvent(context, mNotify_Layout);
    }

    // get height item listview add start
    private static final int UNBOUNDED = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);

    // To calculate the total height of all items in ListView call with items = adapter.getCount()
    public static int getItemHeightofListView(ListView listView, int items) {
        ListAdapter adapter = listView.getAdapter();

        int grossElementHeight = 0;
        for (int i = 0; i < items; i++) {
            View childView = adapter.getView(i, null, listView);
            childView.measure(UNBOUNDED, UNBOUNDED);
            grossElementHeight += childView.getMeasuredHeight();
        }
        return grossElementHeight;
    }
    // get height item listview add start

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initBroadcast();
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        try {
            this.unregisterReceiver(mBroadcastReceiver);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

    private void initBroadcast() {
        IntentFilter filter = new IntentFilter(BROADCASTLISTENNER);
        this.registerReceiver(mBroadcastReceiver, filter);
    }

    public void DisibleTouchScreen() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    public void enableTouchScreen() {
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    public static String getUrlNA() {
        return urlNA;
    }

    public static void setUrlNA(String urlNA) {
        SumManager.urlNA = urlNA;
    }

    public static String urlNA;
    public String Userid;
    public String Pass;
    public String Link;
    public String Username;
    public DatePickerDialog mDatePickerDialog;
    String LookupScreen = "";
    int statisticType;
    long OganizationID;
    String TokenID;
    boolean AfterSearch = false;
    public Toolbar mToolbar;
    String CenterURL;




    int Category;

    public int getCategory() {
        return Category;
    }

    public void setCategory(int category) {
        Category = category;
    }

    public String getCenterURL() {
        return CenterURL;
    }

    public void setCenterURL(String centerURL) {
        CenterURL = centerURL;
    }

    public boolean isAfterSearch() {
        return AfterSearch;
    }

    public void setAfterSearch(boolean afterSearch) {
        AfterSearch = afterSearch;
    }

    String mArrayExecutionUnit;

    public String getmArrayExecutionUnit() {
        return mArrayExecutionUnit;
    }

    public void setmArrayExecutionUnit(String mArrayExecutionUnit) {
        this.mArrayExecutionUnit = mArrayExecutionUnit;
    }

    public String getTokenID() {
        return TokenID;
    }

    public void setTokenID(String tokenID) {
        TokenID = tokenID;
    }

    public static boolean DownloadOffLine = false;

    public static boolean isDownloadOffLine() {
        return DownloadOffLine;
    }

    public static void setDownloadOffLine(boolean downloadOffLine) {
        DownloadOffLine = downloadOffLine;
    }

    public static boolean DeteleNotifyTable = false;

    public static boolean isDeteleNotifyTable() {
        return DeteleNotifyTable;
    }

    public static void setDeteleNotifyTable(boolean deteleNotifyTable) {
        DeteleNotifyTable = deteleNotifyTable;
    }

    public long getOganizationID() {
        return OganizationID;
    }

    public void setOganizationID(long oganizationID) {
        OganizationID = oganizationID;
    }

    public int getStatisticType() {
        return statisticType;
    }

    public void setStatisticType(int statisticType) {
        this.statisticType = statisticType;
    }

    String typeHomeListDocument;

    public String getTypeHomeListDocument() {
        return typeHomeListDocument;
    }

    public void setTypeHomeListDocument(String typeHomeListDocument) {
        this.typeHomeListDocument = typeHomeListDocument;
    }

    public String getLookupScreen() {
        return LookupScreen;
    }

    String SearchScreen;

    public String getSearchScreen() {
        return SearchScreen;
    }

    public void setSearchScreen(String searchScreen) {
        SearchScreen = searchScreen;
    }

    public void setLookupScreen(String lookupScreen) {
        LookupScreen = lookupScreen;
    }

    public String getUserid() {
        return Userid;
    }

    public void setUserid(String userid) {
        Userid = userid;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public ArrayList<ItemsDocumentLookUp> arrDocumentLookUp;

    public ArrayList<ItemsDocumentLookUp> getArrDocumentLookUp() {
        return arrDocumentLookUp;
    }


    public void initNewArrayList() {
        arrHotLineList = new ArrayList<HotLineListRow>();
        arrDocumentLookUp = new ArrayList<ItemsDocumentLookUp>();
        arrDocument = new ArrayList<GsonDocument.DataBean>();
    }

    public ArrayList<GsonDocument.DataBean> arrDocument;

    public ArrayList<GsonDocument.DataBean> getArrDocument() {
        return arrDocument;
    }


    public void setBGColorDrawable(TextView tv, String Color) {
        GradientDrawable color = (GradientDrawable) tv.getBackground();
        color.setColor(android.graphics.Color.parseColor(Color));
    }

    String statisticTypeHotLine;

    public String getStatisticTypeHotLine() {
        return statisticTypeHotLine;
    }

    public ArrayList<HotLineListRow> getArrHotLineList() {
        return arrHotLineList;
    }

    public ArrayList<HotLineListRow> arrHotLineList;

    public void setStatisticTypeHotLine(String statisticTypeHotLine) {
        this.statisticTypeHotLine = statisticTypeHotLine;
    }


    public void getInforAccountFromShareReferenced(Context context) {
        setUserid(getDefaults(USERNAME, context));
        setPass(getDefaults(PASSWORD_USER, context));
        setLink(getDefaults(LINK, context));
        setCenterURL(getDefaults(URL_CENTER, context));
        setUsername(getDefaults(FULLNAME_USER, context));
    }

    public String getDateFromMiliSec(long milliSeconds, String dateFormat) {
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }


    public String specialValid(String s) {
        s = s.replaceAll("'", "&apos;");
        s = s.replaceAll("<", "&lt;");
        s = s.replaceAll(">", "&gt;");
        s = s.replaceAll("&", "&amp;");
        s = s.replaceAll("\"", "&#034;");
        s = s.replaceAll("\u00bb", "&#187;");
        s = s.replaceAll("\u2013", "&#x2013;");
        s = s.replaceAll("\u2014", "&#x2014;");
        return s;
    }

    public String getTimeNow() {
        Calendar mCalendar = Calendar.getInstance();
        String time = new SimpleDateFormat("HH:mm dd/MM/yyyy").format(mCalendar.getTime());
        return time;
    }


    public String getMsgAfterTrainfer(String Resuilt, String InputComBack, String DocumentNumber, Context context, String inPutName) {
        String msg = "";
        if (Resuilt != null) {
            if (Resuilt.equals(FALSE)) {
                if (InputComBack == null) {
                    if (DocumentNumber != null) {
                        msg = inPutName + " " + context.getString(R.string.chuyen_that_bai) + " " + Html.fromHtml(DocumentNumber) + ".";
                    } else {
                        msg = inPutName + " " + context.getString(R.string.chuyen_that_bai) + ".";
                    }
                    return msg;
                }
                switch (InputComBack) {
                    case CHANGE_HANDLE:
                        msg = "Yêu cầu thay đổi thất bại.";
                        break;
                    case CANCEL_SCREEN:
                        msg = "Rút lại văn bản không thành công.";
                        break;
                    case RETURN_SCREEN:
                        msg = "Yêu cầu trả lại thất bại.";
                        break;
                    case FEED_BACK:
                        msg = "Góp ý thất bại.";
                        break;
                    case EXTEND_SCREEN:
                        msg = "Gia hạn thất bại.";
                        break;
                    default:
                        if (DocumentNumber != null) {
                            msg = inPutName + " " + context.getString(R.string.chuyen_that_bai) + " " + Html.fromHtml(DocumentNumber) + ".";
                        }
                        break;
                }
            } else {
                if (InputComBack == null) {
                    if (DocumentNumber != null) {
                        msg = inPutName + " " + context.getString(R.string.chuyen_thanh_cong) + " " + Html.fromHtml(DocumentNumber) + ".";
                    } else {
                        msg = inPutName + " " + context.getString(R.string.chuyen_thanh_cong) + ".";
                    }
                    return msg;
                }
                switch (InputComBack) {
                    case CHANGE_HANDLE:
                        msg = "Yêu cầu thay đổi thành công.";
                        break;
                    case CANCEL_SCREEN:
                        msg = "Rút lại văn bản thành công.";
                        break;
                    case RETURN_SCREEN:
                        msg = "Yêu cầu trả lại thành công.";
                        break;
                    case FEED_BACK:
                        msg = "Góp ý thành công.";
                        break;
                    case EXTEND_SCREEN:
                        msg = "Gia hạn thành công.";
                        break;
                    default:
                        if (DocumentNumber != null) {
                            msg = inPutName + " " + context.getString(R.string.chuyen_thanh_cong) + " " + Html.fromHtml(DocumentNumber) + ".";
                        }
                        break;
                }
            }
        }
        return msg;
    }


    public ModuleRow getModuleInfor(String MODULE, Context context) {
        ModuleRow moduleRow;
        SQLite mSqLiteModule = new SQLite(context, INFOR_MODULE_SQLITE, null, 1);
        // add 23/10/17
        Cursor existsTable = mSqLiteModule.GetData("SELECT name FROM sqlite_master WHERE name='infor_module_table'");
        int countTable = existsTable.getCount();
        if (countTable == 0) {
            Log.d("VinhCNLog: ", String.valueOf(countTable));
            moduleRow = new ModuleRow(null, null, null, null, null);
        } else {
            // add 24/10/17
            Cursor mCursor = mSqLiteModule.GetData(SELECT_FROM + INFOR_MODULE + " where modulecode = '" + MODULE + "'");
            int count = mCursor.getCount();
            if (count > 0) {
                mCursor.moveToFirst();
                moduleRow = new ModuleRow(mCursor.getString(0), mCursor.getString(1), mCursor.getString(2), mCursor.getString(3), mCursor.getString(4));
            } else {
                moduleRow = new ModuleRow(null, null, null, null, null);
            }
        }
        return moduleRow;
    }


    public static final int FILE_SELECT_CODE = 0;

    public void showFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(
                    Intent.createChooser(intent, "Select a File to Upload"),
                    FILE_SELECT_CODE);
        } catch (ActivityNotFoundException ex) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(this, "Please install a File Manager.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    //

    public int getStatusBarHeight(Activity activity) {
        int result = 0;
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = activity.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    //
    public String replaceStringSpecicalCharacterFromSQL(String s) {
        s = s.replaceAll("&amp;lt;", "&lt;");
        return s;
    }


    private static final String TAGSUM = "VinhCNLog: ";

    public void ForManagerLogin(Context context) {
        setDefaults(CHECKLOGIN, CHECKLOGINTRUE, context);
    }

    public static int numberOfNotify = 0;

    public static int LoadingTime = 60000;

    public static int LimitPager = 20;

    public static int getLimitPager() {
        return LimitPager;
    }

    public static void setLimitPager(int limitPager) {
        LimitPager = limitPager;
    }

//    String documentID;
//
//    int PagePosition;

//    boolean isShowSettingPopup = false;
//    boolean isShowNotifyPopup = false;
//
//    public boolean isShowSettingPopup() {
//        return isShowSettingPopup;
//    }
//
//    public void setShowSettingPopup(boolean showSettingPopup) {
//        isShowSettingPopup = showSettingPopup;
//    }
//
//    public boolean isShowNotifyPopup() {
//        return isShowNotifyPopup;
//    }
//
//    public void setShowNotifyPopup(boolean showNotifyPopup) {
//        isShowNotifyPopup = showNotifyPopup;
//    }

    ListPopupWindow mPopupWindowNotification, mPopupWindowSetting;

//    public String getDocumentID() {
//        return documentID;
//    }
//
//    public void setDocumentID(String documentID) {
//        this.documentID = documentID;
//    }
//
//    public int getPagePosition() {
//        return PagePosition;
//    }
//
//    public void setPagePosition(int pagePosition) {
//        PagePosition = pagePosition;
//    }

    String screenNameInSide;


    public String getScreenNameInSide() {
        return screenNameInSide;
    }

    public void setScreenNameInSide(String screenNameInSide) {
        this.screenNameInSide = screenNameInSide;
    }

    boolean CheckShowListDocument = false;

    public boolean isCheckShowListDocument() {
        return CheckShowListDocument;
    }

    public void setCheckShowListDocument(boolean checkShowListDocument) {
        CheckShowListDocument = checkShowListDocument;
    }

    boolean CheckShowListSearch = false;

    public boolean isCheckShowListSearch() {
        return CheckShowListSearch;
    }

    public void setCheckShowListSearch(boolean checkShowListSearch) {
        CheckShowListSearch = checkShowListSearch;
    }

    private ProgressDialog mProgressDialog;
    public AlertDialog alertErrorInternet;
    public static final String FORMATDATE = "dd/MM/yyyy";
    public static final String FORMATP_DATE_DOCUMENT_6_1 = "dd/MM/yyyy HH:mm";
    public static final String FORMATP_DATE_DOCUMENT_6_2 = "HH:mm dd/MM/yyyy";
    public static final String FORMATP_DATE_EEE = "HH:mm dd/MM/yyyy EEE";

/*
     Progress dialog for all activity
*/

    public void showProgressDialog(String msg, Context context, String positionDialog, boolean unlock) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(context, R.style.MyDialog);
            mProgressDialog.setCanceledOnTouchOutside(false);
//            mProgressDialog.setIndeterminate(true);
//            mProgressDialog.setCancelable(true);
            mProgressDialog.show();
            switch (positionDialog) {
                case DIALOG_TOP:
                    mProgressDialog.getWindow().setGravity(Gravity.TOP);
                    break;
                case DIALOG_CENTER:
                    mProgressDialog.getWindow().setGravity(Gravity.CENTER);
                    break;
                case DIALOG_BOOTOM:
                    mProgressDialog.getWindow().setGravity(Gravity.CENTER);
                    double height = getResources().getDisplayMetrics().heightPixels;
                    WindowManager.LayoutParams wmlp = mProgressDialog.getWindow().getAttributes();
                    wmlp.y = (int) (height / 2.5);
                    mProgressDialog.getWindow().setAttributes(wmlp);
                    break;
            }
            if (unlock) {
                Window window = mProgressDialog.getWindow();
                window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
                window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            }
            mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(
                    android.graphics.Color.TRANSPARENT
            ));
            mProgressDialog.setContentView(R.layout.custom_progress_dialog);
            mProgressDialog.setOnKeyListener(new Dialog.OnKeyListener() {

                @Override
                public boolean onKey(DialogInterface arg0, int keyCode,
                                     KeyEvent event) {
                    // TODO Auto-generated method stub
                    if (keyCode == KeyEvent.KEYCODE_BACK) {

                    }
                    return true;
                }
            });
        }

    }

    public long getMilisecDocument(String NgayNhan, Context context) {
        long DateMilisec = 0;
        if (getDefaults(URL_CENTER, context).equals(URL_CENTER_6_1)) {
            DateMilisec = getMilisecondDay(NgayNhan, FORMATP_DATE_DOCUMENT_6_1);
        } else {
            DateMilisec = getMilisecondDay(NgayNhan, FORMATP_DATE_DOCUMENT_6_2);
        }
        return DateMilisec;
    }

    public String removeAccent(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }


    /*
         cover string to endcode
         */
    public String encodeString(String s) {
        byte[] data = new byte[0];

        try {
            data = s.getBytes("UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
//            String base64Encoded = Base64.encodeToString(data, Base64.DEFAULT);
            String base64Encoded = Base64.encodeToString(data, Base64.NO_WRAP | Base64.URL_SAFE);

            return base64Encoded;

        }
    }


    public byte[] decodeString(String s) {
        byte[] data = new byte[0];

        try {
            data = s.getBytes("UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {

            data = Base64.decode(s, Base64.DEFAULT);

            return data;

        }
    }

    public String decodeStringDemo(String s) {
        String text = null;
        byte[] data = Base64.decode(s, Base64.DEFAULT);
        try {
            text = new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return text;
    }


    // get Milisecond day for day
    public long getMilisecondDay(EditText mDay) {
        long milisecDay = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(FORMATDATE);
            Date d = sdf.parse(mDay.getText().toString());
            milisecDay = d.getTime();
            Log.d(TAG + "milisecDay ", milisecDay + nULL_STRING);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return milisecDay;
    }

    // get Milisecond day for day
    public long getMilisecondDay(String mDay) {
        long milisecDay = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(FORMATDATE);
            Date d = sdf.parse(mDay);
            milisecDay = d.getTime();
            Log.d(TAG + "milisecDay ", milisecDay + nULL_STRING);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return milisecDay;
    }

    public long getMilisecondDay(String mDay, String format_date) {
        long milisecDay = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format_date);
            Date d = sdf.parse(mDay);
            milisecDay = d.getTime();
            Log.d(TAG + "milisecDay ", milisecDay + nULL_STRING);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return milisecDay;
    }


    public String getNameOfDay(int day) {
        String DayName = "";
        switch (day) {
            case 1:
                DayName = "Chủ Nhật";
                break;
            case 2:
                DayName = "Thứ 2";
                break;
            case 3:
                DayName = "Thứ 3";
                break;
            case 4:
                DayName = "Thứ 4";
                break;
            case 5:
                DayName = "Thứ 5";
                break;
            case 6:
                DayName = "Thứ 6";
                break;
            case 7:
                DayName = "Thứ 7";
                break;
        }
        return DayName;
    }


    /*==================================
        Open file by browers
    ====================================*/
    List<AttachFile> arrFile;
    int positionAttackFile;
    Context mContext;

    public void checkRunTimePermission(int position, List<AttachFile> arrFileAttach, Context context) {
        positionAttackFile = position;
        arrFile = new ArrayList<>(arrFileAttach);
        mContext = context;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(mContext, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((Activity) mContext, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        11111);
                return;
            } else {
//                oPenPDFFILE(position, arrFile, mContext);
                DownAndOpenFile(position, arrFile, mContext);
            }
        } else {
//            oPenPDFFILE(position, arrFile, mContext);
            DownAndOpenFile(position, arrFile, mContext);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 11111) {
//            oPenPDFFILE(positionAttackFile, arrFile, mContext);
            DownAndOpenFile(positionAttackFile, arrFile, mContext);
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void DownAndOpenFile(int positionAttackFile, List<AttachFile> arrFile, Context mContext) {
        ShowErrorToast(mContext, "Đang tải tệp tin...");
        getInforAccountFromShareReferenced(mContext);
        Uri uri;
        String server = arrFile.get(positionAttackFile).getModuleRow().getServerUrl();
        String url = arrFile.get(positionAttackFile).getFileURL();
        String fillnalUrl = server + url;
        String userName = arrFile.get(positionAttackFile).getModuleRow().getUsername();
        String passWord = arrFile.get(positionAttackFile).getModuleRow().getPassword();
        userName = userName.equals(nULL_STRING) ? getUserid() : userName;
        passWord = passWord.equals(nULL_STRING) ? getPass() : passWord;
        try {
            uri = Uri.parse(url);
        } catch (Exception e) {
            Log.e(KeyManager.TAG, "Error parsing pdf url " + url, e);
            return;
        }

        DownloadManager.Request r = new DownloadManager.Request(uri);

        // This put the download in the same Download dir the browser uses
        r.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, arrFile.get(positionAttackFile).getFileName());

        // When downloading music and videos they will be listed in the player
        // (Seems to be available since Honeycomb only)
        r.allowScanningByMediaScanner();


        String authorization = userName + ":" + passWord;
        String encodedAuth = "Basic " + encodeString(authorization);
        r.addRequestHeader("Authorization", encodedAuth);
        // Notify user when download is completed
        // (Seems to be available since Honeycomb only)
        r.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

        // Start download
        final DownloadManager dm = (DownloadManager) mContext.getSystemService(Context.DOWNLOAD_SERVICE);
        final long downloadId = dm.enqueue(r);

        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)) {
                    if (intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, 0) != downloadId) {
                        // Quick exit - its not our download!
                        return;
                    }
                    DownloadManager.Query query = new DownloadManager.Query();
                    query.setFilterById(downloadId);
                    Cursor c = dm.query(query);
                    if (c.moveToFirst()) {
                        int columnIndex = c
                                .getColumnIndex(DownloadManager.COLUMN_STATUS);
                        if (DownloadManager.STATUS_SUCCESSFUL == c
                                .getInt(columnIndex)) {

                            String uriString = c
                                    .getString(c
                                            .getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
                            Uri uri = Uri.parse(uriString);
                            Intent target = new Intent(Intent.ACTION_VIEW);
                            switch (arrFile.get(positionAttackFile).getFileTyPe()) {
                                case PDF:
                                    target.setDataAndType(uri, "application/" + arrFileAttach.get(positionAttackFile).getFileTyPe());
                                    break;
                                case DOC:
                                case DOCX:
                                    target.setDataAndType(uri, "application/msword");
                                    break;
                                case XLS:
                                case XLSX:
                                    target.setDataAndType(uri, "application/vnd.ms-excel");
                                    break;
                                case PPT:
                                case PPTX:
                                    target.setDataAndType(uri, "application/vnd.ms-powerpoint");
                                    break;
                                case ZIP:
                                case RAR:
                                    target.setDataAndType(uri, "application/x-wav");
                                    break;
                                case RTF:
                                    target.setDataAndType(uri, "application/rtf");
                                    break;
                                case WAV:
                                case MP3:
                                    target.setDataAndType(uri, "image/gif");
                                    break;
                                case JPG:
                                case JPEG:
                                case PNG:
                                    target.setDataAndType(uri, "image/jpeg");
                                    break;
                                case TXT:
                                    target.setDataAndType(uri, "text/plain");
                                    break;
                                case THREEGP:
                                case MPG:
                                case MPEG:
                                case MPE:
                                case MP4:
                                case AVI:
                                    target.setDataAndType(uri, "video/*");
                                    break;
                                default:
                                    target.setDataAndType(uri, "*/*");
                                    break;
                            }
//
                            target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                            intent = Intent.createChooser(target, "Open File");
                            try {
                                context.startActivity(intent);
                            } catch (ActivityNotFoundException e) {
                                // Instruct the user to install a PDF reader here, or something
                            }
                            Log.d(KeyManager.TAG, "Done!");
                        } else {
                            Toast.makeText(mContext, "Lỗi file đính kèm!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(mContext, "Lỗi file đính kèm!", Toast.LENGTH_SHORT).show();
                    }
                    context.unregisterReceiver(this);
                }
            }
        };

        mContext.registerReceiver(receiver, new IntentFilter(
                DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }

    public void oPenPDFFILE(int position, List<AttachFile> arrFileAttach, Context context) {
        String BaseAttack = arrFileAttach.get(position).getBase64Code();
        String outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + arrFileAttach.get(position).getFileName();
        byte[] decode = decodeString(BaseAttack);
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(outputFile));
            bos.write(decode);
            bos.flush();
            bos.close();
            File file = new File(outputFile);
            Intent target = new Intent(Intent.ACTION_VIEW);
            switch (arrFileAttach.get(position).getFileTyPe()) {
                case PDF:
                    target.setDataAndType(Uri.fromFile(file), "application/" + arrFileAttach.get(position).getFileTyPe());
                    break;
                case DOC:
                case DOCX:
                    target.setDataAndType(Uri.fromFile(file), "application/msword");
                    break;
                case XLS:
                case XLSX:
                    target.setDataAndType(Uri.fromFile(file), "application/vnd.ms-excel");
                    break;
                case PPT:
                case PPTX:
                    target.setDataAndType(Uri.fromFile(file), "application/vnd.ms-powerpoint");
                    break;
                case ZIP:
                case RAR:
                    target.setDataAndType(Uri.fromFile(file), "application/x-wav");
                    break;
                case RTF:
                    target.setDataAndType(Uri.fromFile(file), "application/rtf");
                    break;
                case WAV:
                case MP3:
                    target.setDataAndType(Uri.fromFile(file), "image/gif");
                    break;
                case JPG:
                case JPEG:
                case PNG:
                    target.setDataAndType(Uri.fromFile(file), "image/jpeg");
                    break;
                case TXT:
                    target.setDataAndType(Uri.fromFile(file), "text/plain");
                    break;
                case THREEGP:
                case MPG:
                case MPEG:
                case MPE:
                case MP4:
                case AVI:
                    target.setDataAndType(Uri.fromFile(file), "video/*");
                    break;
                default:
                    target.setDataAndType(Uri.fromFile(file), "*/*");
                    break;
            }
//
            target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            Intent intent = Intent.createChooser(target, "Open File");
            try {
                context.startActivity(intent);
            } catch (ActivityNotFoundException e) {
                // Instruct the user to install a PDF reader here, or something
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Lỗi file đính kèm!", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "Lỗi file đính kèm!", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    /*=====================================

    event Post Request

    =======================================*/


    public String eventPostRequest(ModuleRow moduleRow, String jsonParam, String QLVBuser, String QLVBpass) {
        String moduleCode = moduleRow.getModuleCode();
        String moduleName = moduleRow.getModuleName();
        String serverUrl = moduleRow.getServerUrl();
        String username = moduleRow.getUsername();
        String password = moduleRow.getPassword();
        String UrlCenter = getCenterURL();
        if (moduleCode == null) {
            String Link = getLink();
            return makePostRequest(Link + UrlCenter, jsonParam, QLVBuser, QLVBpass);
        } else {
            if (!username.equals(nULL_STRING) && !password.equals(nULL_STRING)) {
                return makePostRequest(moduleRow.getServerUrl() + UrlCenter, jsonParam, moduleRow.getUsername(), moduleRow.getPassword());
            } else {
                return makePostRequest(serverUrl + UrlCenter, jsonParam, QLVBuser, QLVBpass);
            }
        }
    }

    public InforRequest getInforRequest(ModuleRow moduleRow, JSONObject jsonParam, Context context, String caseRequest) {
        getInforAccountFromShareReferenced(context);
        String moduleCode = moduleRow.getModuleCode();
        String moduleName = moduleRow.getModuleName();
        String serverUrl = moduleRow.getServerUrl();
        String username = moduleRow.getUsername();
        String password = moduleRow.getPassword();
        String UrlCenter = getCenterURL();
        InforRequest mInforRequest;
        if (moduleCode == null) {
            String Link = getLink();
            mInforRequest = new InforRequest(context, Link + UrlCenter, jsonParam, getUserid(), getPass(), caseRequest);
        } else {
            if (!username.equals(nULL_STRING) && !password.equals(nULL_STRING)) {
                mInforRequest = new InforRequest(context, moduleRow.getServerUrl() + UrlCenter, jsonParam, moduleRow.getUsername(), moduleRow.getPassword(), caseRequest);
            } else {
                mInforRequest = new InforRequest(context, serverUrl + UrlCenter, jsonParam, getUserid(), getPass(), caseRequest);
            }
        }
        return mInforRequest;
    }


    /*==========================

     this method is carefully for all project android, it will follow you long time

    ============================*/

    public String makePostRequest(String link, String jsonParam, String userID, String passWord) {
        Log.d(TAGSUM, link);
        HttpURLConnection connect;
        URL url = null;
        try {
            url = new URL(link);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return "Error!";
        }
        try {
            // cấu hình HttpURLConnection
            connect = (HttpURLConnection) url.openConnection();
            connect.setReadTimeout(20000);
            connect.setConnectTimeout(30000);
            connect.setDoOutput(true);
            connect.setInstanceFollowRedirects(false);
            // unable POST method to send
            connect.setRequestMethod("POST");
//            it must have for add param
            connect.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
            //set header for login user and pass, set password and account if request exists login.
            String authorization = userID + ":" + passWord;
            String encodedAuth = "Basic " + encodeString(authorization);
            connect.setRequestProperty("Authorization", encodedAuth);
            // if param != null let write param to the last character
            if (jsonParam != null && !jsonParam.equals("")) {
                Uri.Builder builder = new Uri.Builder();
                builder.appendQueryParameter("param", jsonParam);
//                            .appendQueryParameter("jsonParam", jsonParam);
                String query = builder.build().getEncodedQuery();

                // open connect data
                OutputStream os = connect.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                connect.connect();
            }
            Log.d("VinhCNLog JsonParam: ", connect + "");
        } catch (IOException e1) {
            e1.printStackTrace();
            return "Error!";
        }
        try {
            int response_code = connect.getResponseCode();

            // kiểm tra kết nối ok
            if (response_code == HttpURLConnection.HTTP_OK) {
                // Đọc nội dung trả về
                InputStream input = connect.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                StringBuilder result = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                return result.toString();
            } else {
                return "Error!";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Error!";
        } finally {
            connect.disconnect();
        }
    }

    /**
     * Enables https connections volley
     */
    @SuppressLint("TrulyRandom")
    public static void handleSSLHandshake() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }};

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });
        } catch (Exception ignored) {
        }
    }

    // strust for change certificate ssl http connection
    private void trustEveryone() {
        try {
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, new X509TrustManager[]{new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            }}, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(
                    context.getSocketFactory());
        } catch (Exception e) { // should never happen
            e.printStackTrace();
        }
    }


    public Date getFirstDayOfQuarter(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) / 3 * 3);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    public Date getLastDayOfQuarter(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) / 3 * 3 + 2);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    public String makePostRequestLogin(String link, String jsonParam, String userID, String passWord, String tokenID, String platForm) {
        Log.d(TAGSUM, link);
        trustEveryone();
        HttpURLConnection connect;
        URL url = null;
        try {
            url = new URL(link);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return "Error!";
        }
        try {
            // cấu hình HttpURLConnection

            connect = (HttpURLConnection) url.openConnection();
//            connect.setSSLSocketFactory(SSLCertificateSocketFactory.getInsecure(0, null));
//            connect.setHostnameVerifier(new AllowAllHostnameVerifier());
            connect.setReadTimeout(15000);
            connect.setConnectTimeout(15000);
            connect.setDoOutput(true);
            connect.setInstanceFollowRedirects(false);
            // unable POST method to send
            connect.setRequestMethod("POST");
//            it must have for add param
            connect.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            //set header for login user and pass, set password and account if request exists login.
            String authorization = userID + ":" + passWord;
            String encodedAuth = "Basic " + encodeString(authorization);
            connect.setRequestProperty("Authorization", encodedAuth);
            // if param != null let write param to the last character
            if (!jsonParam.equals("")) {
                Uri.Builder builder = new Uri.Builder();
//                builder.appendQueryParameter("param", jsonParam);
                builder.appendQueryParameter(TOKEN, tokenID);
                if (!platForm.equals(nULL_STRING)) {
                    builder.appendQueryParameter(PLATFORM, platForm);
                }
                String query = builder.build().getEncodedQuery();
                // open connect data
                OutputStream os = connect.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                connect.connect();
            }
            Log.d("VinhCNLog JsonParam: ", connect + "");
        } catch (IOException e1) {
            e1.printStackTrace();
            return "Error!";
        }
        try {
            int response_code = connect.getResponseCode();

            // kiểm tra kết nối ok
            if (response_code == HttpURLConnection.HTTP_OK) {
                // Đọc nội dung trả về
                InputStream input = connect.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                StringBuilder result = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                return result.toString();
            } else {
                return "Error!";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Error!";
        } finally {
            connect.disconnect();
        }
    }


    /*================================
        get only number from string
    ==================================*/

    public String getOnlyNumerics(String str) {
        if (str == null) {
            return null;
        }
        StringBuffer strBuff = new StringBuffer();
        char c;
        for (int i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            if (Character.isDigit(c) || c == '.') {
                strBuff.append(c);
            }
        }
        return strBuff.toString();
    }

/*
    close dialog for all activity
*/

    public void closeProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }

    //    public static ArrayList<NotifyRow> arrayListNotify = new ArrayList<NotifyRow>();
    /*
        create popup window and show it
    */


//    public void initPopupWinDowNotification(final Context context, RelativeLayout mNotify_layout) {
//        mSqLiteNotify.QueryData(CREATE_TABLE_NOTIFY_SQL);
//        ArrayList<NotifyRow> arrayListNotify = new ArrayList<NotifyRow>();
//        Cursor cursor = mSqLiteNotify.GetData("SELECT * FROM " + NOTIFY_SQL);
//        while (cursor.moveToNext()) {
//            int No = cursor.getInt(0);
//            String TitleNotify = cursor.getString(1);
//            String BodyNotify = cursor.getString(2);
//            long objectID = cursor.getLong(3);
//            arrayListNotify.add(new NotifyRow(TitleNotify, BodyNotify, false, objectID));
//        }
//        ShortcutBadger.removeCount(context);
//        mPopupWindowNotification = new ListPopupWindow(context);
//        mPopupWindowNotification.setAnchorView(mNotify_layout);
//        mPopupWindowNotification.setWidth(1000);
//        mPopupWindowNotification.setModal(true);
//
//        // if you wannt check it show or not, you must add this line code
//        mPopupWindowNotification.setForceIgnoreOutsideTouch(false);
//        PopupNotifyAdapter mArrayAdapter = null;
//        if (arrayListNotify.size() == 0) {
//            arrayListNotify = new ArrayList<NotifyRow>();
//            arrayListNotify.add(new NotifyRow("", "", true, 0));
//            mArrayAdapter = new PopupNotifyAdapter(context, arrayListNotify);
//        } else {
//            mArrayAdapter = new PopupNotifyAdapter(context, arrayListNotify);
//        }
//        mPopupWindowNotification.setAdapter(mArrayAdapter);
//        mPopupWindowNotification.setOnDismissListener(new PopupWindow.OnDismissListener() {
//            @Override
//            public void onDismiss() {
//
//            }
//        });
//
//        final ArrayList<NotifyRow> finalArrayListNotify = arrayListNotify;
//        mPopupWindowNotification.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if (finalArrayListNotify.size() != 0) {
//                    if (!nULL_STRING.equals(finalArrayListNotify.get(position).getContent())) {
//                        Intent intent = new Intent(context, DetailForwardActivity.class);
//                        intent.putExtra(IS_SCREEN, NOTIFY_SCREEN);
//                        intent.putExtra(DOCUMENTID, finalArrayListNotify.get(position).getObjectId());
//                        startActivity(intent);
//                    }
//                }
//                mPopupWindowNotification.dismiss();
//            }
//        });
//    }

    PopupWindow mPopupWindow;
    public SQLite mSqLiteNotify = new SQLite(this, NOTIFY_SQL, null, 1);
    ArrayList<NotifyRow> arrayListNotify_Main = new ArrayList<NotifyRow>();

    public void initPopupWinDowNotification(final Context context, RelativeLayout mNotify_layout) {
        mSqLiteNotify.QueryData(CREATE_TABLE_NOTIFY_SQL);
        final ArrayList<NotifyRow> arrayListNotify = new ArrayList<NotifyRow>();
        Cursor cursor = mSqLiteNotify.GetData("SELECT * FROM " + NOTIFY_SQL);
        while (cursor.moveToNext()) {
            int No = cursor.getInt(0);
            String TitleNotify = cursor.getString(1);
            String BodyNotify = cursor.getString(2);
            long objectID = cursor.getLong(3);
            arrayListNotify.add(new NotifyRow(TitleNotify, BodyNotify, false, objectID));
        }
        ShortcutBadger.removeCount(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_popup_window_notification, null);
        mPopupWindow = new PopupWindow(this);
        mPopupWindow.setContentView(view);
        mPopupWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        ListView lvNotify = (ListView) mPopupWindow.getContentView().findViewById(R.id.notify_list);
        LinearLayout LnNotiNull = (LinearLayout) mPopupWindow.getContentView().findViewById(R.id.layout_notify_null);
        LinearLayout LnNotiNotNull = (LinearLayout) mPopupWindow.getContentView().findViewById(R.id.layout_notify_not_null);
        if (arrayListNotify.size() == 0) {
            LnNotiNull.setVisibility(View.VISIBLE);
        } else {
            LnNotiNotNull.setVisibility(View.VISIBLE);
            PopupNotifyAdapter mArrayAdapter = new PopupNotifyAdapter(context, arrayListNotify);
            lvNotify.setAdapter(mArrayAdapter);
        }
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), ""));
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setFocusable(true); //Make Here True For back press dismiss
        mPopupWindow.showAsDropDown(mNotify_layout);
        mPopupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    mPopupWindow.dismiss();
                    return true;
                }
                return false;
            }
        });
        lvNotify.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, DetailForwardActivity.class);
                intent.putExtra(IS_SCREEN, NOTIFY_SCREEN);
                intent.putExtra(DOCUMENTID, arrayListNotify.get(position).getObjectId());
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);`
                startActivity(intent);
                mPopupWindow.dismiss();
            }
        });
//        mPopupWindowNotification.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if (finalArrayListNotify.size() != 0) {
//                    if (!nULL_STRING.equals(finalArrayListNotify.get(position).getContent())) {
//                        Intent intent = new Intent(context, DetailForwardActivity.class);
//                        intent.putExtra(IS_SCREEN, NOTIFY_SCREEN);
//                        intent.putExtra(DOCUMENTID, finalArrayListNotify.get(position).getObjectId());
//                        startActivity(intent);
//                    }
//                }
//                mPopupWindowNotification.dismiss();
//            }
//        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
//            case R.id.menu_config:
//            case R.id.menu_help:
            case R.id.menu_infor:
                startActivity(new Intent(this, AppInforActivity.class));
                break;
            case R.id.menu_notify:
                intent = new Intent(this, NotifyActivity.class);
                intent.putExtra(TITLE_ACTION_BAR, "Thông báo");
                ShortcutBadger.removeCount(this);
                setDefaults(NUMBER_NOTIFY, "0", getApplicationContext());
                startActivity(intent);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void initPopupSetting(final Context context, RelativeLayout mNotify_layout) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_popup_window_notification, null);
        mPopupWindow = new PopupWindow(this);
        mPopupWindow.setContentView(view);
        mPopupWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        ListView lvNotify = (ListView) mPopupWindow.getContentView().findViewById(R.id.notify_list);
        LinearLayout LnNotiNull = (LinearLayout) mPopupWindow.getContentView().findViewById(R.id.layout_notify_null);
        LinearLayout LnNotiNotNull = (LinearLayout) mPopupWindow.getContentView().findViewById(R.id.layout_notify_not_null);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), ""));
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setFocusable(true); //Make Here True For back press dismiss
        mPopupWindow.showAsDropDown(mNotify_layout);
        mPopupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    mPopupWindow.dismiss();
                    return true;
                }
                return false;
            }
        });


    }


    /*
                here you can check this show or not
            */
    public void showPopupWinDownNotificationEvent(final Context context, RelativeLayout mNotify_layout) {
//        initPopupWinDowNotification(context, mNotify_layout);
//        boolean checkshowing = mPopupWindowNotification.isShowing();
//        if (checkshowing) {
//            mPopupWindowNotification.dismiss();
//        } else {
//            mPopupWindowNotification.show();
//        }

        if (mPopupWindow == null || !mPopupWindow.isShowing()) {
            initPopupWinDowNotification(context, mNotify_layout);
        } else {
            mPopupWindow.dismiss();
        }
        ShortcutBadger.removeCount(context);
    }


    //    here you can check this show or not
    public void showPopupWinDownSettingEvent(final Context context, RelativeLayout mNotify_layout) {
        if (mPopupWindow == null || !mPopupWindow.isShowing()) {
            initPopupSetting(context, mNotify_layout);
        } else {
            mPopupWindow.dismiss();
        }
    }

//    public void initPopupWinDowSetting(final Context context, RelativeLayout mSetting_layout) {
//        mPopupWindowSetting = new ListPopupWindow(context);
//        mPopupWindowSetting.setAnchorView(mSetting_layout);
//        mPopupWindowSetting.setWidth(1000);
//        mPopupWindowSetting.setModal(true);
//        mPopupWindowSetting.setForceIgnoreOutsideTouch(false);
//        ArrayList<PopupItemSetting> arrayList = new ArrayList<PopupItemSetting>();
//        arrayList.add(new PopupItemSetting("Số dòng hiển thị", 1));
//        arrayList.add(new PopupItemSetting("Thời gian load", 2));
//        SettingAdapter adapter = new SettingAdapter(context, arrayList);
//        mPopupWindowSetting.setAdapter(adapter);
//        mPopupWindowSetting.setOnDismissListener(new PopupWindow.OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                Intent intentSend = new Intent();
//                intentSend.setAction(BROADCASTLISTENNER);
//                intentSend.putExtra(RELOADSLIDER, READLOAD_LIST_DOCUMENT);
//                context.sendBroadcast(intentSend);
//            }
//        });
//
//    }
//
//    public void showPopupWinDownSettingEvent() {
//        if (mPopupWindowSetting.isShowing()) {
//            mPopupWindowSetting.dismiss();
//        } else {
//            mPopupWindowSetting.show();
//        }
//
//    }
    /*
         get content url basic
    */

    public String docNoiDung_Tu_URL(String theUrl) {
        Log.d(TAGSUM, theUrl);
        StringBuilder content = new StringBuilder();

        try {
            // create a url object
            URL url = new URL(theUrl);

            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();
            urlConnection.setConnectTimeout(20000);
            urlConnection.setReadTimeout(20000);
            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();

    }


    public void showDialogUpdateError(final String msg, final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(Html.fromHtml(msg));
        builder.setCancelable(true);
//        mProgressDialog.setCanceledOnTouchOutside(false);
        builder.setPositiveButton(
                context.getString(R.string.ok),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (msg.equals(context.getString(R.string.exit_app))) {
                            exitAppAndFinishAllActivity(context);
                        } else {
                            if (msg.equals(context.getString(R.string.server_error))) {
                                setDefaults(CHECKLOGIN, CHECKLOGINFALSE, context);
                                context.startActivity(new Intent(context, SplashScreen.class));
                            } else {
                                dialog.cancel();
                                if (msg.equals("Yêu cầu thực hiện thành công") || msg.equals("Yêu cầu thực hiện thất bại")) {
                                    Intent intent = new Intent(context, OfficalActivity.class);
                                    intent.putExtra(COME_BACK_FROM_SCREEN, LOOKUP_PROCESS);
                                    context.startActivity(intent);
                                }
                            }
                        }
                    }
                });
        builder.setOnKeyListener(new Dialog.OnKeyListener() {

            @Override
            public boolean onKey(DialogInterface arg0, int keyCode,
                                 KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {

                }
                return true;
            }
        });
        alertErrorInternet = builder.create();
        alertErrorInternet.setCanceledOnTouchOutside(false);
        alertErrorInternet.show();
    }


    public void showBackDialog(Context context, String msg) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
//        alertDialog.setTitle("Alert");
        alertDialog.setMessage(msg);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        onBackPressed();
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    /*
        this method help you get out application at the current time
    */
    public void exitAppAndFinishAllActivity(Context context) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        ActivityCompat.finishAffinity((Activity) context);
    }




    /*
        show choose time dialog
    */

    public void showDataPickDialog(final TextView textView, Context context) {
        final Calendar mCalendar = Calendar.getInstance();
        final SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(FORMATDATE);
        Calendar calendar = Calendar.getInstance();
        int year = mCalendar.get(Calendar.YEAR);
        int monthx = mCalendar.get(Calendar.MONTH);
        int day = mCalendar.get(Calendar.DAY_OF_MONTH);
        if (mDatePickerDialog == null) {
            mDatePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    mCalendar.set(year, month, dayOfMonth);
                    textView.setText(mSimpleDateFormat.format(mCalendar.getTime()));
                    mDatePickerDialog = null;
                }
            }, year, monthx, day);
            mDatePickerDialog.show();
            mDatePickerDialog.setCanceledOnTouchOutside(false);
        }
        mDatePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, getString(android.R.string.cancel), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (which == DialogInterface.BUTTON_NEGATIVE) {
                    mDatePickerDialog = null;
                }
            }
        });
        mDatePickerDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                mDatePickerDialog = null;
            }
        });

    }


    public void showTimeDialog(TextView textView, Context context) {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int year = calendar.get(Calendar.YEAR);
        int month = Integer.parseInt(new SimpleDateFormat("MM").format(calendar.getTime()));
        dialogBuilder = new android.app.AlertDialog.Builder(context);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_time_choose, null);
        // title
        TextView tvCYear = (TextView) dialogView.findViewById(R.id.current_year);
        tvCYear.setText(String.valueOf(year));
        TextView tvCDate = (TextView) dialogView.findViewById(R.id.current_date);
        tvCDate.setText(getNameOfDay(calendar.get(Calendar.DAY_OF_WEEK)) + ", " + day + " Tháng " + month);
        // Dismiss button
        TextView tvCancel = (TextView) dialogView.findViewById(R.id.cancel);
        tvCancel.setOnClickListener(v -> alertDialog.dismiss());
        /*com.applandeo.materialcalendarview.CalendarView*/
//        CalendarView calendarView = (CalendarView) dialogView.findViewById(R.id.calendarView);
        // set event
//        List<EventDay> events = new ArrayList<>();
//        events.add(new EventDay(calendar, R.drawable.sample_icon_3));
//        calendarView.setEvents(events);
        // show current month
//        calendarView.showCurrentMonthPage();
        //show dialog
        /* com.prolificinteractive.materialcalendarview.MaterialCalendarView*/
        widget = (MaterialCalendarView) dialogView.findViewById(R.id.calendarView);
        widget.setTitleFormatter(new MonthArrayTitleFormatter(getResources().getTextArray(R.array.custom_months)));
        widget.setWeekDayFormatter(new ArrayWeekDayFormatter(getResources().getTextArray(R.array.custom_weekdays)));
        widget.setSelectedDate(calendar);
        // ok button
        TextView tvOk = (TextView) dialogView.findViewById(R.id.ok);
        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalendarDay day = widget.getSelectedDate();
                textView.setText(day.getDay() + "/" + Integer.parseInt(new SimpleDateFormat("MM").format(day.getCalendar().getTime())) + "/" + day.getYear());
                alertDialog.dismiss();
            }
        });
        dialogBuilder.setView(dialogView);
        alertDialog = dialogBuilder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        alertDialog.getWindow().setLayout((width / 6) * 5, WindowManager.LayoutParams.WRAP_CONTENT);
//        new ApiSimulator().executeOnExecutor(Executors.newSingleThreadExecutor());
    }

    /**
     * Simulate an API call to show how to add decorators
     */
    private class ApiSimulator extends AsyncTask<Void, Void, List<CalendarDay>> {

        @Override
        protected List<CalendarDay> doInBackground(@NonNull Void... voids) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH, -2);
            ArrayList<CalendarDay> dates = new ArrayList<>();
            for (int i = 0; i < 30; i++) {
                CalendarDay day = CalendarDay.from(calendar);
                dates.add(day);
                calendar.add(Calendar.DATE, 5);
            }

            return dates;
        }

        @Override
        protected void onPostExecute(@NonNull List<CalendarDay> calendarDays) {
            super.onPostExecute(calendarDays);

            if (isFinishing()) {
                return;
            }

            widget.addDecorator(new EventDecorator(Color.GREEN, calendarDays));
        }
    }

    public void showDataPickDialogComfirm(final TextView textView, final Context context) {
        final Calendar mCalendar = Calendar.getInstance();
        final Calendar calendar = Calendar.getInstance();
        int year = mCalendar.get(Calendar.YEAR);
        int monthx = mCalendar.get(Calendar.MONTH);
        int day = mCalendar.get(Calendar.DAY_OF_MONTH);
        if (mDatePickerDialog == null) {
            mDatePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    mCalendar.set(year, month, dayOfMonth);
                    if (mCalendar.getTimeInMillis() >= calendar.getTimeInMillis()) {
                        textView.setText(mSimpleDateFormat.format(mCalendar.getTime()));
                        mDatePickerDialog = null;
                    } else {
                        mDatePickerDialog = null;
                        Toast.makeText(context, "Vui lòng không chọn ngày nhỏ hơn ngày hiện tại", Toast.LENGTH_SHORT).show();
                    }
                }
            }, year, monthx, day);
            mDatePickerDialog.show();
            mDatePickerDialog.setCanceledOnTouchOutside(false);
        }
        mDatePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, getString(android.R.string.cancel), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (which == DialogInterface.BUTTON_NEGATIVE) {
                    // Do Stuff
                    mDatePickerDialog = null;
                }
            }
        });
        mDatePickerDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                mDatePickerDialog = null;
            }
        });

    }

    public void CallBackDialog(String Date) {

    }

    public void showDataPickDialog(final TextView textView, final EditText edtProcessDays, final Context context) {
        final Calendar mCalendar = Calendar.getInstance();
        int year = mCalendar.get(Calendar.YEAR);
        int monthx = mCalendar.get(Calendar.MONTH);
        final int day = mCalendar.get(Calendar.DAY_OF_MONTH);
        final Calendar mCalendarNow = Calendar.getInstance();
        final SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(FORMATDATE);
        if (mDatePickerDialog == null) {
            mDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    mCalendar.set(year, month, dayOfMonth);
                    if (mCalendar.getTimeInMillis() >= mCalendarNow.getTimeInMillis()) {
                        String dateChoose = mSimpleDateFormat.format(mCalendar.getTime());
//                        textView.setText(dateChoose);
                        mDatePickerDialog = null;
                        long dayShow = mCalendar.getTimeInMillis() - mCalendarNow.getTimeInMillis();
//                        edtProcessDays.setText(TimeUnit.MILLISECONDS.toDays(dayShow) + 1 + "");
//                        ((InputForwardDepartmentActivity) context).handleGetNumDay(dateChoose);
                        CallBackDialog(dateChoose);
                    } else {
                        mDatePickerDialog = null;
                        Toast.makeText(context, "Vui lòng không chọn ngày nhỏ hơn ngày hiện tại", Toast.LENGTH_SHORT).show();
                    }
                }
            }, year, monthx, day);
            mDatePickerDialog.setCanceledOnTouchOutside(false);
            mDatePickerDialog.setCancelable(false);
            mDatePickerDialog.show();

        }
        mDatePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, getString(android.R.string.cancel), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (which == DialogInterface.BUTTON_NEGATIVE) {
                    // Do Stuff
                    mDatePickerDialog = null;
                }
            }
        });
        mDatePickerDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                mDatePickerDialog = null;
            }
        });


    }


    public void closeDataPickDialog() {
        if (mDatePickerDialog != null) {
            mDatePickerDialog.dismiss();
            mDatePickerDialog = null;
        }
    }

    /*
        write file local in android phone
    */
    public void writeToFile(String data, Context context, String filename) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(filename, Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }


    /*
        read file local in android phone
    */
    public String readFromFile(Context context, String filename) {

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput(filename);

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }

    /*
        get connection enable or not
    */
    public boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }


    //  CONVERT FILE LOCAL TO BYTE[]
    public byte[] FileLocal_To_Byte(String path) {
        File file = new File(path);
        int size = (int) file.length();
        byte[] bytes = new byte[size];
        try {
            BufferedInputStream buf = new BufferedInputStream(new FileInputStream(file));
            buf.read(bytes, 0, bytes.length);
            buf.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return bytes;
    }


    public void ShowErrorToast(Context context) {
        closeProgressDialog();
        SingleToast.show(context, "Lỗi kết nối", Toast.LENGTH_SHORT);
//        Toast.makeText(context, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
    }

    public void ShowErrorToast(Context context, String msg) {
        closeProgressDialog();
//        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        SingleToast.show(context, msg, Toast.LENGTH_SHORT);
    }


    public String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        @SuppressWarnings("deprecation")
        Cursor cursor = managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    public void makePostRequestTokenID(String LINK_SEND_TOKEN, String refreshToken, String LINK_PLATOM, Context context) {
        String url = Link +
                LINK_SEND_TOKEN +
                refreshToken
                + LINK_PLATOM;
        HttpPost post = new HttpPost(url);
        String user = getDefaults(USERNAME, context);
        String pass = getDefaults(PASSWORD_USER, context);
//        String encode = encodeString(getUserid() + ":" + getPass());
        String encode = encodeString(user + ":" + pass);

        Log.d("Log: ", user + pass);
        Log.d(TAG, encode + nULL_STRING);
        post.setHeader(AUTHORIZATION, BASIC + " " + encode);
        HttpResponse httpResponse = null;
        HttpClient httpClient = new DefaultHttpClient();
        try {
            httpResponse = httpClient.execute(post);
        } catch (ClientProtocolException e) {
            System.out.println("ERROR: Loi trong qua trinh gui len VPDKSDD");
        } catch (IOException e) {
            System.out.println("ERROR: Loi trong qua trinh gui len VPDKSDD");
        }
    }


    /*
     write shareedPreferences and save it for all activity
 */
    public static void setDefaults(String key, String value, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.commit();
    }

     /*
        read shareedPreferences and save it for all activity
    */

    public static String getDefaults(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }

    /*
        this method will help your listview inside to scrollview
    */
    public void setListViewHeightBasedOnChildren(ListView listView) {
//        ListAdapter listAdapter = listView.getAdapter();
//        if (listAdapter == null) {
//            return;
//        }
//
//        int totalHeight = 0;
//        for (int i = 0; i < listAdapter.getCount(); i++) {
//            View listItem = listAdapter.getView(i, null, listView);
//            listItem.measure(0, 0);
//            totalHeight += listItem.getMeasuredHeight();
//        }
//
//        ViewGroup.LayoutParams params = listView.getLayoutParams();
//        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
//        listView.setLayoutParams(params);
//        listView.requestLayout();
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, RecyclerView.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    public void showNotifiCation(TextView tvNotify) {
        String StrNotify = getDefaults(NUMBER_NOTIFY, this);
        if (StrNotify == null) {
            StrNotify = "0";
        }
        int numberNotify = Integer.parseInt(StrNotify);
        if (numberNotify > 0) {
            tvNotify.setVisibility(View.VISIBLE);
        } else {
            tvNotify.setVisibility(View.GONE);
        }
        tvNotify.setText(numberNotify++ + "");
    }

    public String getNumberOfNotify() {
        String StrNotify = getDefaults(NUMBER_NOTIFY, this);
        if (StrNotify == null) {
            StrNotify = "0";
        }
        return StrNotify;
    }

    public void showSoftwareKeyboard(boolean showKeyboard, Activity context) {
        final InputMethodManager inputManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(context.getWindow().getDecorView().getRootView().getWindowToken(), showKeyboard ? InputMethodManager.SHOW_FORCED : InputMethodManager.HIDE_NOT_ALWAYS);
    }


    public String getFilePath(Context context, Uri uri) throws URISyntaxException {
        String selection = null;
        String[] selectionArgs = null;
        // Uri is different in versions after KITKAT (Android 4.4), we need to
        if (Build.VERSION.SDK_INT >= 19 && DocumentsContract.isDocumentUri(context.getApplicationContext(), uri)) {
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                return Environment.getExternalStorageDirectory() + "/" + split[1];
            } else if (isDownloadsDocument(uri)) {
                final String id = DocumentsContract.getDocumentId(uri);
                uri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
            } else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                if ("image".equals(type)) {
                    uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                selection = "_id=?";
                selectionArgs = new String[]{
                        split[1]
                };
            }
        }
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = {
                    MediaStore.Images.Media.DATA
            };
            Cursor cursor = null;
            try {
                cursor = context.getContentResolver()
                        .query(uri, projection, selection, selectionArgs, null);
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index);
                }
            } catch (Exception e) {
            }
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }


    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public void initActiobar(String titleActionBar, boolean isHide, int iCon) {
        initActiobar(titleActionBar, isHide);
        if (iCon != 0) {
            getSupportActionBar().setHomeAsUpIndicator(iCon);
        }

    }

    public void initActiobar(String titleActionBar, boolean isHide) {
        mToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(nULL_STRING);
        TextView titleActionbar = (TextView) findViewById(R.id.title_actionbar);
        titleActionbar.setText(titleActionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.close_white_x1);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mToolbar.setPadding(0, getStatusBarHeight(this), 0, 0);
        if (isHide) {
            RelativeLayout btnSettingActionbar = (RelativeLayout) findViewById(R.id.setting_layout);
            btnSettingActionbar.setVisibility(View.GONE);
        }

    }


    //clear all the EditText fields
    public void clearForm(ViewGroup group) {
        for (int i = 0, count = group.getChildCount(); i < count; ++i) {
            View view = group.getChildAt(i);
            if (view instanceof EditText) {
                ((EditText) view).setText(nULL_STRING);
            }

            if (view instanceof ViewGroup && (((ViewGroup) view).getChildCount() > 0))
                clearForm((ViewGroup) view);
        }
    }

    public void ListViewInsideScrollView(ListView lv) {
        ListAdapter listadp = lv.getAdapter();
        if (listadp != null) {
            int totalHeight = 0;
            for (int i = 0; i < listadp.getCount(); i++) {
                View listItem = listadp.getView(i, null, lv);
                listItem.measure(0, 0);
                totalHeight += listItem.getMeasuredHeight();
            }
            ViewGroup.LayoutParams params = lv.getLayoutParams();
            params.height = totalHeight + (lv.getDividerHeight() * (listadp.getCount() - 1));
            lv.setLayoutParams(params);
            lv.requestLayout();
        }

    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem itemSearchButton = menu.findItem(R.id.search_button).setVisible(false);
        setCount(this, getNumberOfNotify(), menu);
        return super.onPrepareOptionsMenu(menu);
    }


    public void setCount(Context context, String count, Menu defaultMenu) {
        MenuItem menuItem = defaultMenu.findItem(R.id.menu_notify);
        LayerDrawable icon = (LayerDrawable) menuItem.getIcon();
        CountDrawable badge;

        // Reuse drawable if possible
        Drawable reuse = icon.findDrawableByLayerId(R.id.ic_group_count);
        if (reuse != null && reuse instanceof CountDrawable) {
            badge = (CountDrawable) reuse;
        } else {
            badge = new CountDrawable(context);
        }
        badge.setCount(count);
        icon.mutate();
        icon.setDrawableByLayerId(R.id.ic_group_count, badge);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        invalidateOptionsMenu();
        getMenuInflater().inflate(R.menu.menu_main, menu);
        if (menu instanceof MenuBuilder) {
            MenuBuilder m = (MenuBuilder) menu;
            //noinspection RestrictedApi
            m.setOptionalIconsVisible(true);
        }

//        new Handler().post(new Runnable() {
//            @Override
//            public void run() {
//                final View v = findViewById(R.id.search_button);
//                if (v != null) {
//                    v.setOnLongClickListener(new View.OnLongClickListener() {
//                        @Override
//                        public boolean onLongClick(View v) {
//                            return false;
//                        }
//                    });
//                }
//            }
//        });
        return true;
    }


    public static void expandAnimation(final View v, int duration, int targetHeight) {

        int prevHeight = v.getHeight();

        v.setVisibility(View.VISIBLE);
        ValueAnimator valueAnimator = ValueAnimator.ofInt(prevHeight, targetHeight);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                v.getLayoutParams().height = (int) animation.getAnimatedValue();
                v.requestLayout();
            }
        });
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.setDuration(duration);
        valueAnimator.start();
    }

    public static void collapseAnimation(final View v, int duration, int targetHeight) {
        int prevHeight = v.getHeight();
        ValueAnimator valueAnimator = ValueAnimator.ofInt(prevHeight, targetHeight);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                v.getLayoutParams().height = (int) animation.getAnimatedValue();
                v.requestLayout();
            }
        });
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.setDuration(duration);
        valueAnimator.start();
    }
}
