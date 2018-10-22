package com.newsaigonsoft.neoegov.Subjects;

/**
 * Created by Vinh on 5/9/2017.
 */

public class KeyManager {

    // Key Link Read Json
    public static final String URL_DEPARTMENT = "/get-phong-ban-soans";
    //    public static final String DROP_TABLE_IF_EXISTS = "DROP TABLE IF EXISTS '";
    public static final String URL_CENTER = "URL_CENTER";
    //    public static final String URL_CENTER = "/ui-26-portlet/api/secure/jsonws/supportmobileapi";
//    public static final String URL_CENTER_6_1 = "/service-mobile-portlet/api/secure/jsonws/mobile/get-data-for-mobile";
    public static final String URL_CENTER_6_1 = "/service-mobile-portlet/api/secure/jsonws/mobile/data";
    public static final String URL_CENTER_6_2 = "/mobile-service-portlet/services/mobileevent/getData";
    public static final String LINK_SEND_TOKEN = "/push-notifications-portlet/api/secure/jsonws/pushnotificationsdevice/add-push-notifications-device?token=";
    //    public static final String LINK_DETELE_TOKEN = "/push-notifications-portlet/api/secure/jsonws/pushnotificationsdevice/delete-push-notifications-device?token=";
//    public static final String LOGOUT_6_1 = "/push-notifications-portlet/api/secure/jsonws/pushnotificationsdevice/delete-push-notifications-device?token=";
    public static final String LOGOUT_6_1 = "/service-mobile-portlet/api/secure/jsonws/mobile/logout";
    public static final String LOGOUT_6_2 = "/mobile-service-portlet/services/mobileevent/logout";
    public static final String URL_MENU_ITEMS = "/get-menu_main-items";
    public static final String URL_TOTAL_DOCUMENT = "/count-cong-viec";
    public static final String URL_DOCUMENT_LIST = "/find-cong-viec";
    public static final String URL_DETAIL = "/get-chi-tiet-cong-viec";
    public static final String URL_INPUT_PERSON = "/get-danh-sach-nguoi-nhan";
    public static final String URL_FORWARD = "/signal-cong-viec";
    public static final String LINK_PLATOM = "&platform=android";
    public static final String TAG = "VinhCNLog: ";
    public static final String CHECKLOGIN = "checklogin";
    public static final String NUMBER_NOTIFY = "NumberNotify";
    public static final String REQUEST_KEEP_LOGIN = "requestkeeplogin";
    public static final String NUMBERIC_SHOW_LIST_DOCUMENT = "number_show_list_document";
    public static final String FORMATDATE = "dd/MM/yyyy";
    public static final String LINK = "link";
    public static final String USERNAME = "username";
    public static final String PASSWORD_USER = "pass";
    public static final String CHECKLOGINFALSE = "0";
    public static final String FIRST_ITEM_SEARCH_SPINNER = "0";
    public static final String CHECKLOGINTRUE = "1";
    public static final String USERID = "userid";
    public static final String USERPASS = "userpass";
    public static final String HOST = "host";
    public static final String FULLNAME_USER = "fullname";
    public static final String REMEMBERHTTPORHTTPS = "remeberhttporhttps";

    public static final String DETAIL = "DETAIL";
    public static final String FORWARD = "FORWARD";
    public static final String FEEDBACK = "FEEDBACK";
    public static final String OTHER = "OTHER";
    public static final String MSGTASK = "MSGTASK";
    public static final String CONTENTTASK = "CONTENTTASK";
    public static final String REPORTTASK = "REPORTTASK";
    public static final String REQUEST_STATIS_LIST = "REQUEST_STATIS_LIST";
    public static final String SCHEDULE_DAY_REQUEST = "SCHEDULE_DAY_REQUEST";
    public static final String SCHEDULE_WEEK_REQUEST = "SCHEDULE_WEEK_REQUEST";
    public static final String SCHEDULE_MONTH_REQUEST = "SCHEDULE_MONTH_REQUEST";

    public static final int REQUEST_STATIS_CODE = 999;

    public static final String PDF = "pdf";
    public static final String DOC = "doc";
    public static final String DOCX = "docx";
    public static final String XLSX = "xlsx";
    public static final String HOME_SCREEN = "HOME_SCREEN";
    public static final String CODE_HOME = "01";
    public static final String CODE_PROCESS = "02";
    public static final String CODE_LOOKUP = "03";
    public static final String CODE_LOOKUP_COMING = "0301";
    public static final String CODE_LOOKUP_SEND = "0302";
    public static final String CODE_LOOKUP_LOCAL = "0303";
    public static final String CODE_LOOKUP_ONE_WINDOW = "0304";
    public static final String CODE_GUIDE = "guide";
    public static final String CODE_FEED_BACK = "feedback";
    public static final String CODE_INFOR_APP = "infor";
    public static final String CODE_PERSONAL_SCHEDULE = "0410";

    public static final String CODE_SCHEDULE = "04";

    public static final String CODE_SCHEDULE_PERSON = "0401";
    public static final String CODE_SCHEDULE_DPM = "0402";
    public static final String CODE_SCHEDULE_ORG = "0403";


    public static final String CODE_SCHEDULE_DAY = "0401";
    public static final String CODE_SCHEDULE_WEEK = "0402";

    public static final String CODE_STATISTICAL = "05";
    public static final String CODE_STATISTICAL_COMING = "0501";
    public static final String CODE_STATISTICAL_WORK_ARISE = "0507";
    public static final String CODE_STATISTICAL_SEND = "0502";
    public static final String CODE_STATISTICAL_LOCAL = "0503";
    public static final String CODE_STATISTICAL_ONE_WINDOW = "0504";
    public static final String CODE_STATISTICAL_FOLLOW_PERSON = "0505";
    public static final String CODE_STATISTICAL_FOLLOW_DEPEARTMENT = "0506";
    public static final String CODE_STATISTICAL_SENT = "0502";
    public static final String CODE_STATISTICAL_INTERNAL = "0503";
    public static final String CODE_WORK_ARISE = "0305";

    public static final String XLS = "xls";
    public static final String PPT = "ppt";
    public static final String PPTX = "pptx";
    public static final String ZIP = "zip";
    public static final String RAR = "rar";
    public static final String RTF = "rtf";
    public static final String WAV = "wav";
    public static final String MP3 = "mp3";
    public static final String JPG = "jpg";
    public static final String JPEG = "jpeg";
    public static final String PNG = "png";
    public static final String TXT = "txt";
    public static final String THREEGP = "3gp";
    public static final String MPG = "mpg";
    public static final String MPEG = "mpeg";
    public static final String MPE = "mpe";
    public static final String MP4 = "mp4";
    public static final String AVI = "avi";


    public static final String TRAINFERID = "TrainferID";
    public static final String TRAINFERID_REPORT = "TRAINFERID_REPORT";
    public static final String TRAINFERID_RELEASE = "TRAINFERID_RELEASE";
    public static final String STATIS_LIST_TOTAL = "STATIS_LIST_TOTAL";

    public static final String TRAINFERID_14 = "TRAINFERID_14";
    public static final String LIST_RETURN_PERSON = "LIST_RETURN_PERSON";
    public static final String LIST_PERSON = "LIST_PERSON";
    public static final String LIST_DEPARTMENT = "LIST_DEPARTMENT";
    public static final String ACTION_SAVE_DRAIF = "ACTION_SAVE_DRAIF";

    public static final String REPORT_OR_FORWARD_OR_RELEASE = "REPORT_OR_FORWARD_OR_RELEASE";
    public static final String DOCUMENTID = "DocumentID";
    public static final String DEPARTMENT_POSITION = "DEPARTMENT_POSITION";
    public static final String IS_SCREEN = "IS_SCREEN";
    public static final String TYPE_HOME_LIST_DOCUMENT = "TypeHomeListDocument";

    public static final String PROCESSPERSON = "ProcessPerson";
    public static final String DATA_COMING_DOCUMENT = "DATA_COMING_DOCUMENT";
    public static final String DATA_SENT_DOCUMENT = "DATA_SENT_DOCUMENT";
    public static final String EXTEND_SCREEN = "EXTEND_SCREEN";

//    public static final int TAP_RETURN = 1;
//    public static final int TAP_SAVE_DRAFTS = 2;
//    public static final int TAP_HANDLE_CHANGE = 3;
//    public static final int TAP_REPORT_RESUILT = 4;
//    public static final int TAP_FEED_BACK = 5;
//    public static final int TAP_FORWARD_PERSON = 6;
//    public static final int TAP_FORWARD_DEPARTMENT = 7;
//    public static final int TAP_CONFIRM_COMPLETED = 8;
//    public static final int TAP_REMIND_TASK = 9;
//    public static final int TAP_ADD_TRAINFER = 10;
//    public static final int TAP_CANCEL = 11;
//    public static final int TAP_FORWARD_ADDITIONAL = 12;
//    public static final int TAP_EXTEND = 13;

    public static final int TAP_FORWARD_PERSON = 1;
    public static final int TAP_FORWARD_DEPARTMENT = 2;
    public static final int TAP_RETURN = 3;
    public static final int TAP_CANCEL = 4;
    public static final int TAP_REPORT_RESUILT = 5;
    public static final int TAP_FEED_BACK = 6;
    public static final int TAP_SAVE_DRAFTS = 7;
    public static final int TAP_HANDLE_CHANGE = 8;
    public static final int TAP_EXTEND = 9;
    public static final int TAP_REMIND_TASK = 10;
    public static final int TAP_CONFIRM_COMPLETED = 11;
    public static final int TAP_ADD_TRAINFER = 12;
    public static final int TAP_FORWARD_RELEASE = 13;
    public static final int TAP_14 = 14;


    public static final String RESOURCECODEID = "resourceCodeId";
    public static final String CHECKSEARCH = "CheckSearch";
    public static final String AUTHORIZATION = "Authorization";
    public static final String BASIC = "basic";
    public static final String LOGIN = "LOGIN";
    public static final String SEND_TOKEN = "SEND_TOKEN";
    public static final String SLIDERFILE = "slidermenu.txt";
    public static final String KEYURLNA = "urlna";
    public static final String CHECK_SREEN = "CHECK_SREEN";
    public static final String TAP_SCREEN = "TAP_SREEN";
    public static final String DPM_PROCESS = "DPM_PROCESS";
    public static final String DPM_UN_PROCESS = "DPM_UN_PROCESS";
    public static final String DPM_ON_TIME = "DPM_ON_TIME";
    public static final String DPM_DELAYS = "DPM_DELAYS";
    public static final String DPM_IN_DUE = "DPM_IN_DUE";
    public static final String DPM_OUT_OF_DATE = "DPM_OUT_OF_DATE";
    public static final String LIST_CANEL = "LIST_CANEL";
    public static final String GET_EXPIRATION_DATE = "GET_EXPIRATION_DATE";
    public static final String GET_COUNT_DATE = "GET_COUNT_DATE";
    public static final String STATIS_LIST = "STATIS_LIST";


    public static final String TAP_DPM_ONTIME = "TAP_DPM_ONTIME";
    public static final String TAP_DPM_DELAYS = "TAP_DPM_DELAYS";
    public static final String TAP_DPM_INDUE = "TAP_DPM_INDUE";
    public static final String TAP_DPM_OUT_OF_DATE = "TAP_DPM_OUT_OF_DATE";
    public static final String FAKE_DATA = "FAKE_DATA";


    public static final String DROP_TABLE_IF_EXISTS = "DROP TABLE IF EXISTS '";
    public static final String FALSE = "false";
    public static final String TRUE = "true";
    public static final String PAGEPOSITION = "PAGEPOSITION";
    public static final String DOCUMENT_NUMBER = "DOCUMENT_NUMBER";
    public static final String LOCAL_HOST_SQLITE = "localhost.sqlite";
    public static final String LOCAL_HOST_TABLE_SQLITE = "localhost";
    public static final String SELECT_FROM = "SELECT * FROM ";
    public static final String INSERT_INTO = "INSERT INTO ";
    public static final String nULL_STRING = "";
    public static final String DEVICE_MOBILE_TRUE = "?deviceMobile=true";
    public static final String INPUT_PERSON_SQLITE = "inputperson.sqlite";
    public static final String SEND_WAITING_SQLITE = "sendwaiting.sqlite";
    public static final String SEND_WAITING_DEPARTMENT_SQLITE = "sendwaitingdepartment.sqlite";
    public static final String LIST_DOCUMENT_SQLITE = "listDocumentSQL";
    public static final String INFOR_MODULE_SQLITE = "infor_module_SQL";
    public static final String INFOR_MODULE = "infor_module_table";
    public static final String NOTIFY_SQL = "notifySQL";
    public static final String INPUT_PERSON_TABLE_SQLITE = "inputperson";
    public static final String BROADCASTLISTENNER = "BROADCASTLISTENNER";
    public static final String RELOADSLIDER = "RELOADSLIDER";
    public static final String NOTIFICATION_UP_DATE = "NOTIFICATION_UP_DATE";
    public static final String CANCEL_DOWNLOAD_OFFLINE = "DO NOT LOOP";
    //    private static final String FIRST_LINK = "/group/mobile/trang-chu";
//    private static final String SLIDER_TYPE = "&type=1";
//    private static final String DOCUMENT_TYPE = "&type=3&label_url=";
//    private static final String TOTAL_DOCUMENT_TYPE = "&type=2&label_url=";
//    private static final String LINK_LAST_NUMBER_OF_SEARCH = "%7D";
    public static final String RELOADSLIDERNONOK = "RELOADSLIDERNONOK";
    public static final String READLOAD_LIST_DOCUMENT = "READLOAD_LIST_DOCUMENT";
    public static final String RELOADSLIDEROK = "RELOADSLIDEROK";
    public static final String TRAINFERDOCUMENT = "TRAINFERDOCUMENT";
    public static final String TRAINFERDOCUMENTOK = "TRAINFERDOCUMENTOK";
    public static final String DOCUMENT_FRAGMENT_TAG = "DocumentFragment";
    public static final String HOME_FRAGMENT_TAG = "HomeFragment";
    public static final String STATISTICAL_DEPARTMENT_TAG = "StatisticalDPMFm";
    public static final String STATISTICAL_FRAGMENT_TAG = "StatisticalDocComingFragment";
    public static final String STATISTICAL_FRAGMENT_SEND_TAG = "StatisticalDocSentFragment";
    public static final String STATISTICAL_FRAGMENT_INTERNAL_TAG = "StatisticalDocInternalFragment";
    public static final String STATISTICAL_FRAGMENT_TOTAL_TAG = "StatisticalDepartmentFragmentTotal";
    public static final String STATISTICAL_FRAGMENT_LIST_TAG = "StatisticalDepartmentFragmentList";
    public static final String LOOKUP_SCREEN = "LOOKUP_SCREEN";
    public static final String OVER_NETWORK_INTENT = "OVER_NETWORK_INTENT";
    public static final String NOTIFY_SCREEN = "NOTIFY_SCREEN";
    public static final String TASK_REQUEST = "TASK_REQUEST";
    public static final String HOTLINE_REQUEST = "HOTLINE_REQUEST";
    public static final String SHOW_CHOOSE_HANDLE_WAY = "SHOW_CHOOSE_HANDLE_WAY";
    public static final String SHOW_CHOOSE_DUE_DATE = "SHOW_CHOOSE_DUE_DATE";
    public static final String TAP_CODE = "TAP_CODE";

    public static final String LOOKUP_COMING = "LOOKUP_COMING";
    public static final String WORK_ARISE = "WORK_ARISE";
    public static final String LOOKUP_SENT = "LOOKUP_SENT";
    public static final String LOOKUP_INTERNAL = "LOOKUP_INTERNAL";
    public static final String LOOKUP_PROCESS = "LOOKUP_PROCESS";
    public static final String TOKEN_ID = "TOKEN_ID";
    public static final String LOOKUP_SCHEDULE = "LOOKUP_SCHEDULE";
    public static final String LOOKUP_STATISTICAL_COMING = "LOOKUP_STATISTICAL_COMING";
    public static final String LOOKUP_STATISTICAL_WORK_ARISE = "LOOKUP_STATISTICAL_WORK_ARISE";
    public static final String LOOKUP_STATISTICAL_SEND = "LOOKUP_STATISTICAL_SEND";
    public static final String LOOKUP_STATISTICAL_INTERNAL = "LOOKUP_STATISTICAL_INTERNAL";
    public static final String LOOKUP_STATISTICAL_TOTAL = "LOOKUP_STATISTICAL_TOTAL";
    public static final String LOOKUP_STATISTICAL_LIST = "LOOKUP_STATISTICAL_LIST";

    public static final String STA_DOC_PROCESS_ON_TIME_FULL = "statistical_docProcessedOnTime";
    public static final String STA_DOC_PROCESS_ON_TIME_FULL_ARISE = "STA_DOC_PROCESS_ON_TIME_FULL_ARISE";
    public static final String STA_DOC_NOT_PROCESS_FULL = "statistical_docNotProcess";
    public static final String STA_DOC_DEMURRAGE_FULL = "statistical_docDemurrage";
    public static final String LIST_DOCUMENT_DEPARTMENT = "LIST_DOCUMENT_DEPARTMENT";
    public static final String STATIS_LIST_ROW = "STATIS_LIST_ROW";

    public static final String HOT_PROCESS = "HOT_PROCESS";
    public static final String HOT_PROCESS_DEMURRAGE = "HOT_PROCESS_DEMURRAGE";
    public static final String HOT_PROCESS_ONDUE = "HOT_PROCESS_ONDUE";


    public static final String DIALOG_TOP = "TOP_DIALOG";
    public static final String DIALOG_BOOTOM = "BOOTOM_DIALOG";
    public static final String DIALOG_CENTER = "CENTER_DIALOG";
    public static final String PROCESS_POSITION = "PROCESS_POSITION";
    public static final String CHANGE_HANDLE = "CHANGE_HANDLE";
    public static final String RETURN_SCREEN = "RETURN_SCREEN";
    public static final String SAVE_DRAFTS_SCREEN = "SAVE_DRAFTS_SCREEN";


    public static final String SEARCH_PROCESS = "SEARCH_PROCESS";
    public static final String SEARCH_COMING = "SEARCH_COMING";
    public static final String SEARCH_WORK_ARISE = "SEARCH_WORK_ARISE";
    public static final String SEARCH_SENT = "SEARCH_SENT";
    public static final String SEARCH_INTERNAL = "SEARCH_INTERNAL";
    public static final String SEARCH_NUMBER_COMING = "SEARCH_NUMBER_COMING";
    public static final String SEARCH_NUMBER_SENT = "SEARCH_NUMBER_SENT";
    public static final String SEARCH_NUMBER_INTERNAL = "SEARCH_NUMBER_INTERNAL";
    public static final String SEARCH_NUMBER_PROCESS = "SEARCH_NUMBER_PROCESS";
    public static final String DETAIL_HOTLINE = "DETAIL_HOTLINE";
    public static final String DETAIL_HOME_DOC_SENT = "DETAIL_HOME_DOC_SENT";
    public static final String DETAIL_HOME_DOC_COMING = "DETAIL_HOME_DOC_COMING";
    public static final String DETAIL_LOOKUP_COMING = "DETAIL_LOOKUP_COMING";
    public static final String DETAIL_LOOKUP_SENT = "DETAIL_LOOKUP_SENT";
    public static final String DETAIL_LOOKUP_INTERNAL = "DETAIL_LOOKUP_INTERNAL";
    public static final String DETAIL_WORK_ARISE = "DETAIL_WORK_ARISE";
    public static final String DETAIL_TASK = "DETAIL_TASK";
    public static final String DETAIL_PROCESS = "DETAIL_PROCESS";
    public static final String HANDLER_PROCESS = "HANDLER_PROCESS";
    public static final String SEARCH_LIST_DEPARTMENT = "SEARCH_LIST_DEPARTMENT";
    public static final String SEARCH_LIST_DEPARTMENT_WORK_ARISE = "SEARCH_LIST_DEPARTMENT_WORK_ARISE";
    public static final String SEARCH_DOCUMENT_TYPE = "SEARCH_DOCUMENT_TYPE";
    public static final String TREE_HOTLINE = "TREE_HOTLINE";
    public static final String TREE_HOME_STATISTICAL_COMING = "TREE_HOME_STATISTICAL_COMING";
    public static final String TREE_HOME_STATISTICAL_SEND = "TREE_HOME_STATISTICAL_SEND";
    public static final String TREE_LOOKUP_COMING = "TREE_LOOKUP_COMING";
    public static final String TREE_LOOKUP_SEND = "TREE_LOOKUP_SEND";
    public static final String TREE_LOOKUP_INTERNAL = "TREE_LOOKUP_INTERNAL";
    public static final String TREE_WORK_ARISE = "TREE_WORK_ARISE";
    public static final String TREE_PROCESS = "TREE_PROCESS";
    public static final String ACTION_ADD_TRAINFER = "ACTION_ADD_TRAINFER";
    public static final String ACTION_CANCEL = "ACTION_CANCEL";
    public static final String ACTION_CHANGE_HANDLE = "ACTION_CHANGE_HANDLE";
    public static final String ACTION_CONFIRM_COMPLETED = "ACTION_CONFIRM_COMPLETED";
    public static final String ACTION_EXTEND = "ACTION_EXTEND";
    public static final String ACTION_FEED_BACK = "ACTION_FEED_BACK";
    public static final String ACTION_FORWARD_DEPARTMENT = "ACTION_FORWARD_DEPARTMENT";
    public static final String ACTION_FORWARD_PERSON = "ACTION_FORWARD_PERSON";
    public static final String ACTION_REMIND = "ACTION_REMIND";
    public static final String ACTION_RETURN = "ACTION_RETURN";
    public static final String LIST_RETURN_SAVED = "LIST_RETURN_SAVED";
    public static final String POSITION_SPINNER_LOGIN = "POSITION_SPINNER_LOGIN";


    public static final String WEBVIEW_FRAGMENT_TAG = "WebViewFragment";
    public static final String SCHEDULE_FRAGMENT_TAG = "ScheduleFragment";
    public static final String REMEMBER_CHOOSE_DAY_OR_WEEK = "REMEMBER_CHOOSE_DAY_OR_WEEK";
    public static final String CHOOSE_DAY = "choose_day";
    public static final String CHOOSE_WEEK = "choose_week";
    public static final String CHOOSE_MONTH = "choose_month";
    public static final String REMEMBER_PART_OF_DAY = "REMEMBER_PART_OF_DAY";
    public static final String MORNING_DAY = "MORNING_DAY";
    public static final String AFTERNOON_DAY = "AFTERNOON_DAY";
    public static final String EVENING_DAY = "EVENING_DAY";
    public static final String REMEMBER_PART_OF_WEEK = "REMEMBER_PART_OF_WEEK";
    public static final String MORNING_WEEK = "MORNING_WEEK";
    public static final String AFTERNOON_WEEK = "AFTERNOON_WEEK";
    public static final String EVENING_WEEK = "EVENING_WEEK";
    public static final String LOGIN_FRAGMENT = "FragmentLogin";
    public static final String SEARCH_FRAGMENT_TAG = "mSearchFragment";
    public static final String LINK_BEGIN_LINK = "/delegate/mobile?username=";
    public static final String LINK_PASS_WORD_KEY = "&password=";
    public static final String LINK_NUMPAGE = "&numpage=";
    public static final String LINK_NUMROW = "&numrow=";
    public static final String LINK_NOIDUNG_XU_LY = "&noidungxuly=%7B'advanced':true,'keywords':";
    public static final String LINK_PHONG_SOAN_ID = ",'phongbansoanid':";
    public static final String LINK_NGAY_NHAN_FROM = ",'ngaynhanfrom':";
    public static final String LINK_NGAY_NHAN_TO = ",'ngaynhanto':";
    public static final String LINK_NOI_DUNG = ",'noidung':";
    public static final String LINK_NOI_DUNG_CONG_VIEC_ID = "&type=6&noidungxuly=%7B'congviecid':";
    public static final String LINK_RESOURCE_CODE_ID = ",'resourcecodeid':";
    public static final String LINK_DUONG_CHUYEN_ID = ",'duongchuyenid':";
    public static final String LINK_NGUOI_NHAN_ID = ",'nguoinhanid':";
    public static final String LINK_TO_CHUC_NHAN_ID = ",'tochucnhanid':";
    public static final String LINK_NOI_DUNG_XU_LY_SERVICE = ",'noidungxuly':'";
    public static final String LINK_EMAIL_SERVICE = "','email':";
    public static final String LINK_KHAN = ",'khan':";
    public static final String LINK_NGUOI_NHAN_PHU = ",'nguoinhanphu':";
    public static final String GET_NAME_FROM_DOCUMENT_LIST_SQLITE_DATABASE = "SELECT name FROM sqlite_master WHERE type='table'";
    public static final String DROP_TABLE_INPUT_PERSON = "DROP TABLE IF EXISTS inputperson";
    public static final String DROP_TABLE_MODULE = "DROP TABLE IF EXISTS " + INFOR_MODULE;
    public static final String DROP_TABLE_SEND_WAITING = "DROP TABLE IF EXISTS sendwaiting";
    public static final String DROP_TABLE_LOOKUP_COMING = "DROP TABLE IF EXISTS LOOKUP_COMING";
    public static final String DROP_TABLE_LOOKUP_SENT = "DROP TABLE IF EXISTS LOOKUP_SENT";
    public static final String DROP_TABLE_LOOKUP_INTERNAL = "DROP TABLE IF EXISTS LOOKUP_INTERNAL";
    public static final String DROP_TABLE_NOTIFY = "DROP TABLE IF EXISTS notifySQL";
    public static final String TITLE_ACTION_BAR = "TITLE_ACTION_BAR";
    public static final String OHTER_FUNCTION = "OHTER_FUNCTION";
    public static final String DETAIL_HANDLE_LIST = "DETAIL_HANDLE_LIST";
    public static final String DETAIL_DOC_CONNECT_RECEIPT = "DETAIL_DOC_CONNECT_RECEIPT";
    public static final String DETAIL_DOC_CONNECT_SEND = "DETAIL_DOC_CONNECT_SEND";
    public static final String ANDROID = "android";
    public static final String SLIDER_MENU = "SLIDER_MENU";
    public static final String STATIS_DOC_COMING = "STATIS_DOC_COMING";
    public static final String REMIND_PERSON = "REMIND_PERSON";

    public static final String STATIS_DOC_SENT = "STATIS_DOC_SENT";
    public static final String TASK_DETECT = "TASK_DETECT";
    public static final String HOTLINE_OR = "HOTLINE_OR";
    public static final String SCHEDULE_HOME = "SCHEDULE_HOME";
    public static final String SCHEDULE_DETAIL = "SCHEDULE_DETAIL";
    public static final String STA_COMING_FULL_DPM = "STA_COMING_FULL_DPM";
    public static final String STA_COMING_FULL_DPM_ARISE = "STA_COMING_FULL_DPM_ARISE";
    public static final String STA_COMING_LIST_DPM_ARISE = "STA_COMING_LIST_DPM_ARISE";
    public static final String STA_COMING_PERSON_JOIN_ARISE = "STA_COMING_PERSON_JOIN_ARISE";
    public static final String STA_COMING_LIST_DPM = "STA_COMING_LIST_DPM";
    public static final String STA_COMING_PERSON_JOIN = "STA_COMING_PERSON_JOIN";
    public static final String DOC_PROCESS_LIST = "DOC_PROCESS_LIST";
    public static final String STA_COMING_DPM_LIST = "STA_COMING_DPM_LIST";
    public static final String STA_COMING_DPM_LIST_ARISE = "STA_COMING_DPM_LIST_ARISE";

    public static final String SELECT_SENDWAITING = "SELECT * From sendwaiting";
    public static final String SELECT_SENDWAITING_DEPARTMENT = "SELECT * From SEND_WAITING_DEPARTMENT_TABLE";
    public static final String SELECT_COUNT_SENDWAITING = "SELECT count(*) FROM sendwaiting";
    public static final String SELECT_COUNT_SENDWAITING_DEPARTMENT = "SELECT count(*) FROM SEND_WAITING_DEPARTMENT_TABLE";
    public static final String URLNA_COMEBACK_OFFICE = "URLNA_COMEBACK_OFFICE";
    public static final String COME_BACK_FROM_SCREEN = "COMEBACK_FROM_SCREEN";
    public static final String INPUT_COME_BACK = "INPUT_COME_BACK";
    public static final String INPUT_NAME = "INPUT_NAME";
    public static final String REMIND = "REMIND";
    public static final String FEED_BACK = "FEED_BACK";
    public static final String CANCEL_SCREEN = "CANCEL_SCREEN";
    public static final String INPUT_FORWARD_ACTIVITY = "INPUT_FORWARD_ACTIVITY";
    public static final String INPUT_FORWARD_DEPARTMENT_ACTIVITY = "INPUT_FORWARD_DEPARTMENT_ACTIVITY";
    public static final String FORWARD_RESUILD = "FORWARD_RESUILD";
    public static final String CREATE_TABLE_LOCAL_HOST_SQLITE = "CREATE TABLE IF NOT EXISTS localhost(id INTEGER PRIMARY KEY AUTOINCREMENT, local VARCHAR)";
//    public static final String INSERT_OR_IGONE_INTO_INPUT_PERSON = "INSERT OR IGNORE INTO inputperson(documentid,trainferid,check_default,tenNguoiNhan,nguoiNhanId,tenPhongBan,phongBanId,chucvu)";


    public static final String INSERT_OR_UPDATE_INTO_INPUT_PERSON = "INSERT OR REPLACE INTO inputperson";

    public static final String DATE_1_YEARS_FOR_TEST = "01/01/2017";
    public static final String STATIS_DPM = "STATIS_DPM";

    //    public static final String INSERT_OR_IGONE_INTO_SEND_WAITING = "INSERT OR UPDATE INTO sendwaiting(documentid,resourcecodeid,trainferid,firstPersonid,fisrtroomid,processcontent,checkemail,checkkhan,nguoinhanphu)";
//    public static final String SQLLITE_DOCUMENT_LIST =
//            "(urlna VARCHAR," +
//                    "total Integer," +
//                    " pagenumber Integer," +
//                    " title VACHAR, " +
//                    "ngaynhan VARCHAR," +
//                    " sumary VARCHAR," +
//                    " documentID VARCHAR primary key," +
//                    " resourceCodeId VARCHAR," +
//                    " oldornewdocument VARCHAR," +
//                    " seendocument VARCHAR," +
//                    " jobtype Integer," +
//                    " datemilisec Integer," +
//                    " overnetwork VARCHAR," +
//                    " urgency VARCHAR)";


    public static final String SQLLITE_DOCUMENT_LIST =
            "(summary VARCHAR," +
                    "jobType Integer," +
                    " receiveDate VARCHAR," +
                    " jobId Integer primary key, " +
                    "status Integer," +
                    " viewed VARCHAR," +
                    " resourceCodeId Integer," +
                    " overNetwork VARCHAR," +
                    " urgency VARCHAR," +
                    " title VARCHAR," +
                    " newX Integer," +
                    " labelCode Integer," +
                    " hasAttachFile VARCHAR, " +
                    " numberSymbol VARCHAR, " +
                    "receiveDateMilisec Integer)";
    //    public static final String CREATE_TABLE_SQLLITE_DOCUMENT_LIST = "CREATE TABLE IF NOT EXISTS listdocument(urlna VARCHAR,total Integer, pagenumber Integer, title VACHAR," +
//            " ngaynhan VARCHAR, sumary VARCHAR, documentID VARCHAR, resourceCodeId VARCHAR)";
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS";


    public static final String CREATE_TABLE_MODULE = "CREATE TABLE IF NOT EXISTS" + " " + INFOR_MODULE + "(modulecode varchar, modulename varchar, serverurl varchar, username varchar, password varchar)";


    public static final String CREATE_TABLE_NOTIFY_SQL = CREATE_TABLE + " " + NOTIFY_SQL + "(id INTEGER PRIMARY KEY AUTOINCREMENT, sender VARCHAR, body VARCHAR, title VARCHAR, objectId INTEGER, sound VARCHAR, dateSent INTEGER, labelCode VARCHAR, content VARCHAR)";
    //    +
//            " listdocument" +
//            "(urlna VARCHAR,total Integer, pagenumber Integer, title VACHAR," +
//            " ngaynhan VARCHAR, sumary VARCHAR, documentID VARCHAR, resourceCodeId VARCHAR, " +
//            "UNIQUE(urlna,total,pagenumber,title,ngaynhan,sumary,documentID,resourceCodeId))";


//    public static final String CREATE_TABLE_SQLLITE_INPUT_PERSON = "CREATE TABLE IF NOT EXISTS inputperson" +
//            "(documentid INTEGER, trainferid INTEGER, check_default VARCHAR, tenNguoiNhan VARCHAR, " +
//            "nguoiNhanId VARCHAR, tenPhongBan VARCHAR, phongBanId VARCHAR, chucvu VARCHAR," +
//            "UNIQUE(documentid,trainferid,check_default,tenNguoiNhan,nguoiNhanId,tenPhongBan,phongBanId,chucvu))";

    public static final String CREATE_TABLE_SQLLITE_INPUT_PERSON = "CREATE TABLE IF NOT EXISTS inputperson";
    public static final String BODY_SQLLITE_INPUT_PERSON = "(id integer primary key, jsonObject VARCHAR)";


//    public static final String CREATE_TABLE_SQLLITE_SEND_WAITING = CREATE_TABLE + " sendwaiting" +
//            "(documentid INTEGER, resourcecodeid INTEGER, trainferid INTEGER, firstPersonid INTEGER, " +
//            "fisrtroomid INTEGER, processcontent VARCHAR, checkemail VARCHAR, checkkhan VARCHAR, nguoinhanphu VARCHAR," +
//            "UNIQUE(documentid,resourcecodeid,trainferid,firstPersonid,fisrtroomid,processcontent,checkemail,checkkhan,nguoinhanphu))";

    public static final String CREATE_TABLE_SQLLITE_SEND_WAITING = CREATE_TABLE + " sendwaiting" + "(documentid INTEGER primary key, jsonRequest varchar)";


    public static final String CREATE_TABLE_SQLITE_LOOKUP_COMING = CREATE_TABLE + " " + LOOKUP_COMING + "(docReceiptId INTEGER primary key, total INTEGER, receiveDate VARCHAR, numberOfSymbol VARCHAR, docNumberFull VARCHAR, briefContent VARCHAR, datemilisec integer)";
    public static final String CREATE_TABLE_SQLITE_LOOKUP_SENT = CREATE_TABLE + " " + LOOKUP_SENT + "(docSendId INTEGER primary key, total INTEGER, publishDate VARCHAR, publishNumber VARCHAR, briefContent VARCHAR, datemilisec integer)";
    public static final String CREATE_TABLE_SQLITE_LOOKUP_INTERNAL = CREATE_TABLE + " " + LOOKUP_INTERNAL + "(docLocalId INTEGER primary key, total INTEGER, createDate VARCHAR, docNumber VARCHAR, briefContent VARCHAR, datemilisec integer)";
    public static final String CREATE_TABLE_SQLITE_WORK_ARISE_LIST = CREATE_TABLE + " " + WORK_ARISE + "(workAriseId INTEGER primary key, total INTEGER, createDate VARCHAR, workCode VARCHAR, content VARCHAR, title VARCHAR, orgCreateName VARCHAR, datemilisec integer)";
    // send waiting department
    public static final String SEND_WAITING_DEPARTMENT_TABLE = "SEND_WAITING_DEPARTMENT_TABLE";
    public static final String CREATE_TABLE_SENDWAITING_DEPARTMENT = CREATE_TABLE + " " + SEND_WAITING_DEPARTMENT_TABLE + "(jobid integer unique, jsonparam varchar)";
    public static final String INSERT_TABLE_SEND_WAITING_DEPARTMENT = "INSERT OR IGNORE INTO " + SEND_WAITING_DEPARTMENT_TABLE + "(jobid,jsonparam)";


}
