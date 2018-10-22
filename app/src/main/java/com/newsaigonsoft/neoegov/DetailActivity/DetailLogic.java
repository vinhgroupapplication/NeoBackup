package com.newsaigonsoft.neoegov.DetailActivity;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.newsaigonsoft.neoegov.AVolleyManager.VolleyTask;
import com.newsaigonsoft.neoegov.AVolleyManager.VolleyTaskCompletedListenner;
import com.newsaigonsoft.neoegov.Adapter.DialogMenuDetailAdapter;
import com.newsaigonsoft.neoegov.Adapter.FileAttachAdapter;
import com.newsaigonsoft.neoegov.AsynTaskManager.AsyncTaskCompleteListener;
import com.newsaigonsoft.neoegov.GsonObject.GsonResuilt;
import com.newsaigonsoft.neoegov.Subjects.AttachFile;
import com.newsaigonsoft.neoegov.Subjects.ContentTasksRow;
import com.newsaigonsoft.neoegov.Subjects.DetailsRows;
import com.newsaigonsoft.neoegov.Subjects.FeedBackRow;
import com.newsaigonsoft.neoegov.Subjects.GroupMsgTasksRow;
import com.newsaigonsoft.neoegov.Subjects.HandleChangeRow;
import com.newsaigonsoft.neoegov.Subjects.InforTrainfer;
import com.newsaigonsoft.neoegov.Subjects.JsonKeyManager;
import com.newsaigonsoft.neoegov.Subjects.ReportTasksRow;
import com.newsaigonsoft.neoegov.Subjects.ResuiltObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.ATTACH_FILE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.ATTACK_URL;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.BASE_64;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.CLASSIFY;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.CONTENT_REPORT;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.CONTENT_TASKS;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.CREATE_DATE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DATA;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DATE_REPORT;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DETAILS;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DIGITAL_SIGNATURE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DOC_DEMURRAGE_TYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DOC_LOCAL_CONNECTS;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DOC_LOCAL_ID;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DOC_NOT_PROCESS_TYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DOC_NOT_SEEN_TYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DOC_NUMBER_REPORT;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DOC_RECEIPT_CONNECTS;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DOC_RECEIPT_ID;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DOC_SEND_CONNECTS;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DOC_SEND_ID;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.EXCHANGE_DATE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.EXECUTION_UNIT;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.EXECUTION_UNITS;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.FILE_ENTRY_ID;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.HANDLE_WAY_CHANGE_LOG;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.HOTLINE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.INFO_FEED_BACK;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.JOB_ID;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.KEYWORDS;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MODULE_LOOKUP_DOCUMENT;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MESSAGE_REFLECT_ID;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MESSAGE_TASKS;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MODULE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MODULE_WORK_ARISE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.NAME;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.NEOTYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.NUM_PAGE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.ORGANIZATION_NAME;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.OTHER_INFO;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MODULE_PROCESSING_WORKING;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.PROCESS_DEMURRAGE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.PROCESS_HANDLE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.PROCESS_ON_TIME;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.REPORTED_TASKS;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.ROW_PER_PAGE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.SCHEDULE_CONTENT;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.SCHEDULE_STATUS;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.STAFF;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.STYLE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TASK_ID;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TASK_PROCESS;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TASK_PROCESS_NEAR_DEMURRAGE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TASK_REPORTED;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TITLE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_CANCEL_LIST;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_DEPARTMENT_LIST_RECEIVE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_DETAIL_WORKING;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_HOME_LIST_DOCUMENT_SENT;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_HOT_DETAIL;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_INPUT_PERSON;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_LOOKUP_DETAIL_COMING;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_LOOKUP_DETAIL_INTERNAL;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_LOOKUP_DETAIL_SENT;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_SAVE_DRAFTS;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_TASK_DETAIL;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.USER_NAME;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.VALUE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.WORKFLOWTRANSITION;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.WORK_ARISE_ID;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.WORK_FLOW_TRAINSITION_ID;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.ACTION_SAVE_DRAIF;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.CONTENTTASK;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DETAIL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DETAIL_HOME_DOC_COMING;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DETAIL_HOME_DOC_SENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DETAIL_HOTLINE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DETAIL_LOOKUP_COMING;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DETAIL_LOOKUP_INTERNAL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DETAIL_LOOKUP_SENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DETAIL_PROCESS;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DETAIL_TASK;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DETAIL_WORK_ARISE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DIALOG_CENTER;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.FEEDBACK;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.FORWARD;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.HANDLER_PROCESS;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.HOT_PROCESS;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.HOT_PROCESS_DEMURRAGE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.HOT_PROCESS_ONDUE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LIST_CANEL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LIST_DEPARTMENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LIST_DOCUMENT_DEPARTMENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LIST_PERSON;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LIST_RETURN_PERSON;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LOOKUP_COMING;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LOOKUP_INTERNAL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LOOKUP_SENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.MSGTASK;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.NOTIFY_SCREEN;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.OTHER;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.REPORTTASK;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.RESOURCECODEID;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.STA_DOC_DEMURRAGE_FULL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.STA_DOC_NOT_PROCESS_FULL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.STA_DOC_PROCESS_ON_TIME_FULL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.STA_DOC_PROCESS_ON_TIME_FULL_ARISE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAG;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_DPM_DELAYS;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_DPM_INDUE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_DPM_ONTIME;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_DPM_OUT_OF_DATE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.WORK_ARISE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.nULL_STRING;

/**
 * Created by Vinh on 10/17/2017.
 */

public class DetailLogic extends DetailBase implements DetailLogicImp, AsyncTaskCompleteListener<ResuiltObject>, VolleyTaskCompletedListenner<ResuiltObject> {
    DetailView mDetailView;
    Context Context;
    String CaseOfLoadUi;
    InforTrainfer mInforTrainfer;
    boolean isRegisterTabhost = false;
    public JSONArray mArraySend;
    public JSONArray mArrayLocal;
    public JSONArray mArrayReceipt;
    List<DetailsRows> arrDetails;
    List<FeedBackRow> arrFeedback;
    List<DetailsRows> arrDetailLimit_1;
    List<DetailsRows> arrDetailLimit_2;
    public ArrayList<HandleChangeRow> arrHanleChange;
    int WorkflowTransition;

    //    TreeNode root;
//    TreeNode Parent;
    List<ContentTasksRow> arrContentTasks;
    List<ReportTasksRow> arrReportedTasks;
    ArrayList<GroupMsgTasksRow> arrMsgGroupTask;
    ArrayList<AttachFile> arrAtachFile;
    public JSONArray mArrayExecutionUnit;
//    SQLite mSqLiteNotify = new SQLite(this, NOTIFY_SQL, null, 1);


    public DetailLogic(DetailView mDetailView, Context context, InforTrainfer mInforTrainfer) {
        this.mDetailView = mDetailView;
        Context = context;
        this.mInforTrainfer = mInforTrainfer;
        getInforAccountFromShareReferenced(Context);
    }


    public JSONObject addJsonRequestHotLine() {
        JSONObject mJsonObject = new JSONObject();
        JSONObject mJsonObjectData;
        try {
            mJsonObject.put(MODULE, HOTLINE);
            mJsonObject.put(NEOTYPE, TYPE_HOT_DETAIL);
            mJsonObjectData = new JSONObject();
            mJsonObjectData.put(MESSAGE_REFLECT_ID, mInforTrainfer.getDocumentID());
            mJsonObject.put(DATA, mJsonObjectData.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mJsonObject;
    }

    public JSONObject addJsonRequestHomeDocSent() {
        JSONObject mJsonObject = new JSONObject();
        JSONObject mJsonObjectData;
        try {
            mJsonObject.put(MODULE, MODULE_LOOKUP_DOCUMENT);
            mJsonObject.put(NEOTYPE, TYPE_LOOKUP_DETAIL_SENT);
            mJsonObjectData = new JSONObject();
            mJsonObjectData.put(DOC_SEND_ID, mInforTrainfer.getDocumentID());
            mJsonObject.put(DATA, mJsonObjectData.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mJsonObject;
    }

    public JSONObject addJsonRequestHomeDocComing() {
        JSONObject mJsonObject = new JSONObject();
        JSONObject mJsonObjectData;
        try {
            mJsonObject.put(MODULE, MODULE_LOOKUP_DOCUMENT);
            mJsonObject.put(NEOTYPE, TYPE_LOOKUP_DETAIL_COMING);
            mJsonObjectData = new JSONObject();
            mJsonObjectData.put(DOC_RECEIPT_ID, mInforTrainfer.getDocumentID());
            mJsonObject.put(DATA, mJsonObjectData.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mJsonObject;
    }

    public JSONObject addJsonQuestLookupComing() {
        JSONObject mJsonObject = new JSONObject();
        JSONObject mJsonObjectData;
        try {
            mJsonObject.put(MODULE, MODULE_LOOKUP_DOCUMENT);
            mJsonObject.put(NEOTYPE, TYPE_LOOKUP_DETAIL_COMING);
            mJsonObjectData = new JSONObject();
            mJsonObjectData.put(DOC_RECEIPT_ID, mInforTrainfer.getDocumentID());
            mJsonObject.put(DATA, mJsonObjectData.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mJsonObject;
    }

    public JSONObject addJsonQuestLookupSent() {
        JSONObject mJsonObject = new JSONObject();
        JSONObject mJsonObjectData;
        try {
            mJsonObject.put(MODULE, MODULE_LOOKUP_DOCUMENT);
            mJsonObject.put(NEOTYPE, TYPE_LOOKUP_DETAIL_SENT);
            mJsonObjectData = new JSONObject();
            mJsonObjectData.put(DOC_SEND_ID, mInforTrainfer.getDocumentID());
            mJsonObject.put(DATA, mJsonObjectData.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mJsonObject;
    }

    public JSONObject addJsonQuestLookupInternal() {
        JSONObject mJsonObject = new JSONObject();
        JSONObject mJsonObjectData;
        try {
            mJsonObject.put(MODULE, MODULE_LOOKUP_DOCUMENT);
            mJsonObject.put(NEOTYPE, TYPE_LOOKUP_DETAIL_INTERNAL);
            mJsonObjectData = new JSONObject();
            mJsonObjectData.put(DOC_LOCAL_ID, mInforTrainfer.getDocumentID());
            mJsonObject.put(DATA, mJsonObjectData.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mJsonObject;
    }

    public JSONObject addJsonRequestTask() {
        JSONObject mJsonObject = new JSONObject();
        JSONObject mJsonObjectData;
        try {
            mJsonObject.put(MODULE, MODULE_LOOKUP_DOCUMENT);
            mJsonObject.put(NEOTYPE, TYPE_TASK_DETAIL);
            mJsonObjectData = new JSONObject();
            mJsonObjectData.put(TASK_ID, mInforTrainfer.getDocumentID());
            mJsonObject.put(DATA, mJsonObjectData.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mJsonObject;
    }

    public JSONObject addJsonRequestProcess() {
        JSONObject mJsonObject = new JSONObject();
        JSONObject mJsonObjectData;
        try {
            mJsonObject.put(MODULE, MODULE_PROCESSING_WORKING);
            mJsonObject.put(NEOTYPE, TYPE_DETAIL_WORKING);
            mJsonObjectData = new JSONObject();
            mJsonObjectData.put(JOB_ID, mInforTrainfer.getDocumentID());
            mJsonObject.put(DATA, mJsonObjectData.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mJsonObject;
    }

    public JSONObject addJsonRequestHandler() {
        JSONObject mJsonObject = new JSONObject();
        try {
            mJsonObject.put(MODULE, MODULE_LOOKUP_DOCUMENT);
            mJsonObject.put(NEOTYPE, PROCESS_HANDLE);
            JSONObject mData = new JSONObject();
            mData.put(JOB_ID, mInforTrainfer.getDocumentID());
            mJsonObject.put(DATA, mData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mJsonObject;
    }

    public void requestJsonDetail() {
        switch (mInforTrainfer.getIsScreen()) {
            case HOT_PROCESS:
            case HOT_PROCESS_DEMURRAGE:
            case HOT_PROCESS_ONDUE:
//                new NeoTask(Context, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(Context, DETAIL_HOTLINE, addJsonRequestHotLine().toString(), getLink() + URL_CENTER_6_1));
                new VolleyTask(Context, DETAIL_HOTLINE, addJsonRequestHotLine(), this);
                moduleRow = getModuleInfor(HOTLINE, Context);
                break;
            case DOC_NOT_SEEN_TYPE:
            case DOC_NOT_PROCESS_TYPE:
            case DOC_DEMURRAGE_TYPE:
                if (mInforTrainfer.getTypeHomeListDocument().equals(TYPE_HOME_LIST_DOCUMENT_SENT)) {
//                    new NeoTask(Context, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(Context, DETAIL_HOME_DOC_SENT, addJsonRequestHomeDocSent().toString(), getLink() + URL_CENTER_6_1));
                    new VolleyTask(Context, DETAIL_HOME_DOC_SENT, addJsonRequestHomeDocSent(), this);
                } else {
//                    new NeoTask(Context, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(Context, DETAIL_HOME_DOC_COMING, addJsonRequestHomeDocComing().toString(), getLink() + URL_CENTER_6_1));
                    new VolleyTask(Context, DETAIL_HOME_DOC_COMING, addJsonRequestHomeDocComing(), this);
                }
                moduleRow = getModuleInfor(MODULE_LOOKUP_DOCUMENT, Context);
                break;
            case STA_DOC_PROCESS_ON_TIME_FULL:
            case STA_DOC_NOT_PROCESS_FULL:
            case STA_DOC_DEMURRAGE_FULL:
            case LIST_DOCUMENT_DEPARTMENT:
            case LOOKUP_COMING:
//                new NeoTask(Context, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(Context, DETAIL_LOOKUP_COMING, addJsonQuestLookupComing().toString(), getLink() + URL_CENTER_6_1));
                new VolleyTask(Context, DETAIL_LOOKUP_COMING, addJsonQuestLookupComing(), this);
                moduleRow = getModuleInfor(MODULE_LOOKUP_DOCUMENT, Context);
                break;
            case LOOKUP_SENT:
//                new NeoTask(Context, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(Context, DETAIL_LOOKUP_SENT, addJsonQuestLookupSent().toString(), getLink() + URL_CENTER_6_1));
                new VolleyTask(Context, DETAIL_LOOKUP_SENT, addJsonQuestLookupSent(), this);
                moduleRow = getModuleInfor(MODULE_LOOKUP_DOCUMENT, Context);
                break;
            case LOOKUP_INTERNAL:
//                new NeoTask(Context, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(Context, DETAIL_LOOKUP_INTERNAL, addJsonQuestLookupInternal().toString(), getLink() + URL_CENTER_6_1));
                new VolleyTask(Context, DETAIL_LOOKUP_INTERNAL, addJsonQuestLookupInternal(), this);
                moduleRow = getModuleInfor(MODULE_LOOKUP_DOCUMENT, Context);
                break;
            case STA_DOC_PROCESS_ON_TIME_FULL_ARISE:
            case WORK_ARISE:
                new VolleyTask(Context, DETAIL_WORK_ARISE, addJsonRequestWorkArise(), this);
                moduleRow = getModuleInfor(MODULE_WORK_ARISE, Context);
                break;
            // statistical department
            case TAP_DPM_DELAYS:
            case TAP_DPM_INDUE:
            case TAP_DPM_ONTIME:
            case TAP_DPM_OUT_OF_DATE:
                // task
            case TASK_REPORTED:
            case TASK_PROCESS:
            case PROCESS_ON_TIME:
            case TASK_PROCESS_NEAR_DEMURRAGE:
            case PROCESS_DEMURRAGE:
//                new NeoTask(Context, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(Context, DETAIL_TASK, addJsonRequestTask().toString(), getLink() + URL_CENTER_6_1));
                new VolleyTask(Context, DETAIL_TASK, addJsonRequestTask(), this);
                moduleRow = getModuleInfor(MODULE_LOOKUP_DOCUMENT, Context);
                break;
            case NOTIFY_SCREEN:
            default:
//                new NeoTask(Context, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(Context, DETAIL_PROCESS, addJsonRequestProcess().toString(), getLink() + URL_CENTER_6_1));
                new VolleyTask(Context, DETAIL_PROCESS, addJsonRequestProcess(), this);
                moduleRow = getModuleInfor(MODULE_PROCESSING_WORKING, Context);
                break;
        }

    }




    private JSONObject addJsonRequestWorkArise() {
        JSONObject mJsonObject = new JSONObject();
        JSONObject mJsonObjectData;
        try {
            mJsonObject.put(MODULE, MODULE_WORK_ARISE);
            mJsonObject.put(NEOTYPE, JsonKeyManager.TYPE_DETAIL_WORK_ARISE);
            mJsonObjectData = new JSONObject();
            mJsonObjectData.put(WORK_ARISE_ID, mInforTrainfer.getDocumentID());
            mJsonObject.put(DATA, mJsonObjectData.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mJsonObject;
    }

    @Override
    public void changeListView(String CaseofLoad) {
        showProgressDialog(nULL_STRING, Context, DIALOG_CENTER, true);
        CaseOfLoadUi = CaseofLoad;
        if (isNetworkAvailable(Context)) {
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    new ReadJsonDetail().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, getLink() + URL_CENTER_6_1);
//                    new ReadJsonHandleProcess().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, getLink() + URL_CENTER_6_1);
//                }
//            });
            requestJsonDetail();
//            new NeoTask(Context, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(Context, HANDLER_PROCESS, addJsonRequestHandler().toString(), getLink() + URL_CENTER_6_1));
            new VolleyTask(Context, HANDLER_PROCESS, addJsonRequestHandler(), this);
        } else {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    upDateViewDetail(readFromFile(Context, String.valueOf(mInforTrainfer.getDocumentID())));
                }
            });

        }
    }

    @Override
    public void setItemMenuContext(String Resuilt) {
        DialogMenuDetailAdapter.ItemMenu[] arrMenu = getGson().fromJson(Resuilt, DialogMenuDetailAdapter.ItemMenu[].class);
        mDetailView.setVisible(arrMenu.length != 0 ? View.VISIBLE : View.GONE);
        List<DialogMenuDetailAdapter.ItemMenu> arrMenuDialog = Arrays.asList(arrMenu);
        mDetailView.getArrMenuDialog(arrMenuDialog);

//        JSONArray mJsonArray = null;
//        try {
//            mJsonArray = new JSONArray(Resuilt);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        arrContextMenu = new ArrayList<ContextMenuForwardRow>();
//        for (int i = 0; i < mJsonArray.length(); i++) {
//            try {
//                JSONObject mJsonObject = mJsonArray.getJSONObject(i);
//                int id = mJsonObject.getInt(ID);
//                boolean mDefault = mJsonObject.getBoolean(DEFAULT);
//                String name = mJsonObject.getString(NAME);
//                int type = mJsonObject.getInt(TYPE);
//                arrContextMenu.add(new ContextMenuForwardRow(id, mDefault, name, type));
//                Log.d(TAG, "" + id + mDefault + name + type);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//        mDetailView.getContextMenu(arrContextMenu);
//        if (!mJsonArray.toString().equals("[]")) {
//            mDetailView.VisibleButtonFoward();
//        } else {
//            mDetailView.GoneButtonFoward();
//        }
    }


    @Override
    public void ReadPersonReturn(int workflowTransition) {
        WorkflowTransition = workflowTransition;
        //        new ReadJsonPerSonReturn().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, getLink() + URL_CENTER_6_1);
//        new NeoTask(context, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(context, LIST_RETURN_PERSON, addJsonRequestPersonReturnList().toString(), getLink() + URL_CENTER_6_1));
        new VolleyTask(Context, LIST_RETURN_PERSON, addJsonRequestPersonReturnList(), this);
    }


    //    public void SaveDraft(Map<Integer, Integer> ContextMap) {
    @Override
    public void SaveDraft(int workflowTransition) {
        WorkflowTransition = workflowTransition;
//        mContextMap = ContextMap;
//        new SaveToDrafts().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
//        new NeoTask(context, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(context, ACTION_SAVE_DRAIF, addJsonRequestSaveDrafts().toString(), getLink() + URL_CENTER_6_1));
        new VolleyTask(Context, ACTION_SAVE_DRAIF, addJsonRequestSaveDrafts(), this);
    }


    @Override
    public void ReadListDepartment(int workflowTransition) {
        WorkflowTransition = workflowTransition;
//        new ReadJsonDepartment().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
//        new NeoTask(context, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(context, LIST_DEPARTMENT, addJsonRequestDepartment().toString(), getLink() + URL_CENTER_6_1));
        new VolleyTask(Context, LIST_DEPARTMENT, addJsonRequestDepartment(), this);

    }


    @Override
    public void ReadJsonInput(int workflowTransition) {
        WorkflowTransition = workflowTransition;
        //        new ReadJsonInputPerson().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, getLink() + URL_CENTER_6_1);
//        new NeoTask(context, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(context, LIST_PERSON, addJsonRequestListPerson().toString(), getLink() + URL_CENTER_6_1));
        new VolleyTask(Context, LIST_PERSON, addJsonRequestListPerson(), this);
    }

    @Override
    public void ReadCancelList(int workflowTransition) {
        WorkflowTransition = workflowTransition;
//        new NeoTask(context, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(context, LIST_CANEL, addJsonRequestCancelList().toString(), getLink() + URL_CENTER_6_1));
//        new ReadJsonCancelList().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, getLink() + URL_CENTER_6_1);
        new VolleyTask(Context, LIST_CANEL, addJsonRequestCancelList(), this);
    }


    private JSONObject addJsonRequestSaveDrafts() {
        JSONObject mJsonObject = new JSONObject();
        try {
            mJsonObject.put(MODULE, MODULE_PROCESSING_WORKING);
            mJsonObject.put(NEOTYPE, TYPE_SAVE_DRAFTS);
            JSONObject mData = new JSONObject();
            mData.put(JOB_ID, mInforTrainfer.getDocumentID());
//            mData.put(WORK_FLOW_TRAINSITION_ID, mContextMap.get(TAP_SAVE_DRAFTS));
            mData.put(WORK_FLOW_TRAINSITION_ID, WorkflowTransition);
            mData.put(RESOURCECODEID, mInforTrainfer.getResourceCodeId());
            mJsonObject.put(DATA, mData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mJsonObject;
    }

    private JSONObject addJsonRequestDepartment() {
        JSONObject mJsonObject = new JSONObject();
        try {
            mJsonObject.put(MODULE, MODULE_PROCESSING_WORKING);
            mJsonObject.put(NEOTYPE, TYPE_DEPARTMENT_LIST_RECEIVE);
            JSONObject mData = new JSONObject();
            mData.put(JOB_ID, mInforTrainfer.getDocumentID());
            mData.put(WORK_FLOW_TRAINSITION_ID, WorkflowTransition);
            mJsonObject.put(DATA, mData.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mJsonObject;
    }

    private JSONObject addJsonRequestPersonReturnList() {
        JSONObject mJsonObject = new JSONObject();
        try {
            mJsonObject.put(MODULE, MODULE_PROCESSING_WORKING);
            mJsonObject.put(NEOTYPE, TYPE_INPUT_PERSON);
            JSONObject mData = new JSONObject();
            mData.put(JOB_ID, mInforTrainfer.getDocumentID());
            mData.put(WORK_FLOW_TRAINSITION_ID, WorkflowTransition);
            mData.put(KEYWORDS, "");
            mData.put(ROW_PER_PAGE, -1);
            mData.put(NUM_PAGE, -1);
            mJsonObject.put(DATA, mData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mJsonObject;
    }

    public JSONObject addJsonRequestCancelList() {
        JSONObject mJsonObject = new JSONObject();
        try {
            mJsonObject.put(MODULE, MODULE_PROCESSING_WORKING);
            mJsonObject.put(NEOTYPE, TYPE_CANCEL_LIST);
            JSONObject mData = new JSONObject();
            mData.put(JOB_ID, mInforTrainfer.getDocumentID());
            mData.put(WORK_FLOW_TRAINSITION_ID, WorkflowTransition);
            mJsonObject.put(DATA, mData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mJsonObject;
    }

    private JSONObject addJsonRequestListPerson() {
        JSONObject mJsonObject = new JSONObject();
        try {
//                if (mInforTrainfer.getTrainferID() != null && mInforTrainfer.getDocumentID() != 0) {
            mJsonObject.put(MODULE, MODULE_PROCESSING_WORKING);
            mJsonObject.put(NEOTYPE, TYPE_INPUT_PERSON);
            JSONObject mData = new JSONObject();
            mData.put(JOB_ID, mInforTrainfer.getDocumentID());
            mData.put(WORK_FLOW_TRAINSITION_ID, WorkflowTransition);
//            switch (isTap) {
//                case TAP_FORWARD_PERSON:
//                    mData.put(WORK_FLOW_TRAINSITION_ID, Integer.parseInt(mInforTrainfer.getTrainferID()));
//                    break;
//                case TAP_REPORT_RESUILT:
//                    mData.put(WORK_FLOW_TRAINSITION_ID, Integer.parseInt(mInforTrainfer.getTrainferReport()));
//                    break;
//                case TAP_FORWARD_RELEASE:
//                    mData.put(WORK_FLOW_TRAINSITION_ID, Integer.parseInt(mInforTrainfer.getTrainferReleaseID()));
//                    break;
//                case TAP_14:
//                    mData.put(WORK_FLOW_TRAINSITION_ID, Integer.parseInt(mInforTrainfer.getTrainfer_14_ID()));
//                    break;
//            }
            mData.put(KEYWORDS, nULL_STRING);
            mData.put(ROW_PER_PAGE, -1);
            mData.put(NUM_PAGE, -1);
            mJsonObject.put(DATA, mData);
//                }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mJsonObject;
    }


    private void upDateViewDetail(String s) {
        Log.d(TAG, s);
        arrAtachFile = new ArrayList<AttachFile>();
        arrDetails = new ArrayList<DetailsRows>();
        arrDetailLimit_1 = new ArrayList<DetailsRows>();
        arrDetailLimit_2 = new ArrayList<DetailsRows>();
        arrFeedback = new ArrayList<FeedBackRow>();
        arrHanleChange = new ArrayList<HandleChangeRow>();
        arrContentTasks = new ArrayList<ContentTasksRow>();
        arrReportedTasks = new ArrayList<ReportTasksRow>();
        arrMsgGroupTask = new ArrayList<GroupMsgTasksRow>();
        try {
            JSONObject mJsonObjectFullData = new JSONObject(s);
            JSONObject mJsonObject = mJsonObjectFullData.getJSONObject(DATA);
            // check tab other show
            JSONObject mOther = null;
            if (!mJsonObject.isNull(OTHER_INFO)) {
                mOther = mJsonObject.getJSONObject(OTHER_INFO);
            }
            switch (mInforTrainfer.getIsScreen()) {
                // task
                case TASK_REPORTED:
                case TASK_PROCESS:
                case PROCESS_ON_TIME:
                case TASK_PROCESS_NEAR_DEMURRAGE:
                case PROCESS_DEMURRAGE:
                    // statistical department
                case TAP_DPM_DELAYS:
                case TAP_DPM_INDUE:
                case TAP_DPM_ONTIME:
                case TAP_DPM_OUT_OF_DATE:
                    switch (CaseOfLoadUi) {
                        case DETAIL:
                            setDetailUi(mJsonObject);
                            break;
                        case MSGTASK:
                            // get message tasks
                            if (mOther != null && !mOther.isNull(MESSAGE_TASKS)) {
                                JSONArray mJsonMessageTasks = mOther.getJSONArray(MESSAGE_TASKS);
                                int CountTotal = mJsonMessageTasks.length();
                                for (int i = 0; i < mJsonMessageTasks.length(); i++) {
                                    JSONObject mObject = mJsonMessageTasks.getJSONObject(i);
                                    JSONArray mArrayExecutionUnits = mObject.getJSONArray(EXECUTION_UNITS);
                                    String dateSend = mObject.getString("dateSend");
                                    if (!mArrayExecutionUnits.toString().equals("[]")) {
                                        arrMsgGroupTask.add(new GroupMsgTasksRow("Nhắc nhở lần " + (CountTotal) + " " + dateSend, mObject));
                                        CountTotal = CountTotal - 1;
                                        Log.d("JsonGroupTasks", mJsonObject.toString());
                                    }
                                }
                            }
                            mDetailView.setAdapterMsgGroupTask(arrMsgGroupTask, MSGTASK);
                            break;
                        case CONTENTTASK:
                            // get content Tasks
                            if (mOther != null && !mOther.isNull(CONTENT_TASKS)) {
                                JSONArray mJsonMessageTasks = mOther.getJSONArray(CONTENT_TASKS);
                                for (int i = 0; i < mJsonMessageTasks.length(); i++) {
                                    JSONObject mObject = mJsonMessageTasks.getJSONObject(i);
                                    String staff = mObject.getString(STAFF);
                                    String exchangeDate = mObject.getString(EXCHANGE_DATE);
                                    int classify = mObject.getInt(CLASSIFY);
                                    String content = mObject.getString(SCHEDULE_CONTENT);
                                    JSONArray mArrayAttachFile = mObject.getJSONArray(ATTACH_FILE);
                                    arrContentTasks.add(new ContentTasksRow(staff, exchangeDate, classify, content, mArrayAttachFile));
                                }
                            }
                            mDetailView.setAdapterContentTask(arrContentTasks, CONTENTTASK, moduleRow);
                            break;
                        case REPORTTASK:
                            if (mOther != null && !mOther.isNull(REPORTED_TASKS)) {
                                JSONArray mJsonReportTasks = mOther.getJSONArray(REPORTED_TASKS);
                                if (!mJsonReportTasks.toString().equals("[]")) {
                                    for (int i = 0; i < mJsonReportTasks.length(); i++) {
                                        JSONObject mObject = mJsonReportTasks.getJSONObject(i);
                                        String organizationName = mObject.getString(ORGANIZATION_NAME);
                                        String docNumberReport = mObject.getString(DOC_NUMBER_REPORT);
                                        String dateReport = mObject.getString(DATE_REPORT);
                                        String contentReport = mObject.getString(CONTENT_REPORT);
                                        JSONArray mArrayAttachFile = mObject.getJSONArray(ATTACH_FILE);
                                        arrReportedTasks.add(new ReportTasksRow(organizationName, docNumberReport, dateReport, contentReport, mArrayAttachFile));
                                    }
                                }
                            }
                            mDetailView.setAdapterReportTask(arrReportedTasks, REPORTTASK, moduleRow);
                            break;
                        default:
                            break;
                    }
                    break;
                case STA_DOC_PROCESS_ON_TIME_FULL:
                case STA_DOC_NOT_PROCESS_FULL:
                case STA_DOC_DEMURRAGE_FULL:
                case LIST_DOCUMENT_DEPARTMENT:
                case LOOKUP_COMING:
                case LOOKUP_SENT:
                case LOOKUP_INTERNAL:
                case DOC_NOT_SEEN_TYPE:
                case DOC_NOT_PROCESS_TYPE:
                case DOC_DEMURRAGE_TYPE:
                case NOTIFY_SCREEN:
                default:
                    switch (CaseOfLoadUi) {
                        case DETAIL:
                            setDetailUi(mJsonObject);
                            break;
                        case FORWARD:
                            break;
                        case FEEDBACK:
                            if (mOther != null && !mOther.isNull(INFO_FEED_BACK)) {
                                JSONArray mArrayFeedBack = mOther.getJSONArray(INFO_FEED_BACK);
                                for (int i = 0; i < mArrayFeedBack.length(); i++) {
                                    JSONObject mObject = mArrayFeedBack.getJSONObject(i);
                                    String content = mObject.getString(SCHEDULE_CONTENT);
                                    String organizationName = mObject.getString(ORGANIZATION_NAME);
                                    String userName = mObject.getString(USER_NAME);
                                    String createDate = mObject.getString(CREATE_DATE);
                                    JSONArray mArrayAttachFile = mObject.getJSONArray(ATTACH_FILE);
                                    arrFeedback.add(new FeedBackRow(content, organizationName, userName, createDate, mArrayAttachFile));
                                }
                            }
                            mDetailView.setAdapterFeedBack(arrFeedback, FEEDBACK, moduleRow);
                            closeProgressDialog();
                            break;
                        case OTHER:
                            if (mOther != null && !mOther.isNull(HANDLE_WAY_CHANGE_LOG)) {
                                JSONArray mArrayHandleChange = mOther.getJSONArray(HANDLE_WAY_CHANGE_LOG);
                                writeToFile(mArrayHandleChange.toString(), Context, HANDLE_WAY_CHANGE_LOG);
                            }
                            // get document connection reciver
                            if (mOther != null && !mOther.isNull(DOC_RECEIPT_CONNECTS)) {
                                mArrayReceipt = mOther.getJSONArray(DOC_RECEIPT_CONNECTS);
                                writeToFile(mArrayReceipt.toString(), Context, DOC_RECEIPT_CONNECTS);
                            }
                            // get document connection Send
                            if (mOther != null && !mOther.isNull(DOC_SEND_CONNECTS)) {
                                mArraySend = mOther.getJSONArray(DOC_SEND_CONNECTS);
                                writeToFile(mArraySend.toString(), Context, DOC_SEND_CONNECTS);
                            }
                            if (mOther != null && !mOther.isNull(DOC_LOCAL_CONNECTS)) {
                                mArrayLocal = mOther.getJSONArray(DOC_LOCAL_CONNECTS);
                                writeToFile(mArrayLocal.toString(), Context, DOC_LOCAL_CONNECTS);
                            }
                            if (mOther != null) {
                                mDetailView.CheckShowMenuOther(mOther, OTHER);
                            }
                            break;
                        default:
                            break;
                    }
                    break;

            }
        } catch (JSONException e) {
            closeProgressDialog();
            ShowErrorToast(Context);
//                String fileSave = readFromFile(DetailForwardActivity.this, mInforTrainfer.getDocumentID());
//                if (!fileSave.equals(nULL_STRING) && !fileSave.equals("Error!")) {
//                    upDateViewDetail(readFromFile(DetailForwardActivity.this, mInforTrainfer.getDocumentID()));
//                }
            e.printStackTrace();
        }
        closeProgressDialog();
    }

    @Override
    public void onTaskCompleted(String s, String CaseRequest) {
        refreshUI(s, CaseRequest);
    }

    private void refreshUI(String s, String CaseRequest) {
        if (mDetailView.isDestroy()) {
            return;
        }
        GsonResuilt mGsonResuilt = getGson().fromJson(s, GsonResuilt.class);
        switch (CaseRequest) {
            case HANDLER_PROCESS:
                if (!s.equals("Error!") && !s.equals(nULL_STRING)) {
                    writeToFile(s, Context, "handle" + mInforTrainfer.getDocumentID());
                }
                break;
            case LIST_CANEL:
                if (!s.equals("Error!") && !s.equals(nULL_STRING)) {
                    writeToFile(s, Context, "cancel_list" + mInforTrainfer.getDocumentID());
                    mDetailView.startIntent();
                } else {
                    mDetailView.showError();
                }
                break;
            case LIST_RETURN_PERSON:
                if (!s.equals("Error!") && !s.equals(nULL_STRING)) {
                    writeToFile(s, Context, "return_list" + mInforTrainfer.getDocumentID());
                    closeProgressDialog();
                    mDetailView.startIntent();
                } else {
                    mDetailView.showError();
                }
                break;
            case LIST_PERSON:
                if (mGsonResuilt.getMessage().equals("success"))
                    mDetailView.inseartInputPersonDatabase(s);
                mDetailView.startIntent();
                break;
            case LIST_DEPARTMENT:
                if (!s.equals("Error!") && !s.equals(nULL_STRING)) {
                    writeToFile(s, Context, "department" + mInforTrainfer.getDocumentID());
                    mDetailView.startIntent();
                } else {
                    mDetailView.showError();
                }
                break;
            case ACTION_SAVE_DRAIF:
                boolean mResuilt = mGsonResuilt.getCode() == 0 ? true : false;
                String msg = mResuilt ? "Yêu cầu thực hiện thành công" : "Yêu cầu thực hiện thất bại";
                mDetailView.DetleteRow(mResuilt);
                mDetailView.closeProgress();
                showDialogUpdateError(msg, Context);
                break;
            default:
                if (!s.equals(nULL_STRING) && !s.equals("Error!")) {
                    writeToFile(s, Context, String.valueOf(mInforTrainfer.getDocumentID()));
                }
                upDateViewDetail(s);
                break;
        }
    }

    private void setDetailUi(JSONObject mJsonObject) {
        try {
            if (!isRegisterTabhost) {
                mDetailView.initTabHost(mJsonObject);
                isRegisterTabhost = true;
            }
            // check tab other show
            if (!mJsonObject.isNull(EXECUTION_UNIT)) {
                JSONArray ExecutionUnit = mJsonObject.getJSONArray(EXECUTION_UNIT);
                mArrayExecutionUnit = new JSONArray(ExecutionUnit.toString());
                mDetailView.ExecutionUnitReuilt(mArrayExecutionUnit.toString());
            }
            JSONArray mArrayDetail = mJsonObject.getJSONArray(DETAILS);
            for (int i = 0; i < mArrayDetail.length(); i++) {
                JSONObject mObjectDetail = mArrayDetail.getJSONObject(i);
                String title = mObjectDetail.getString(TITLE);
                String style = mObjectDetail.getString(STYLE);
                String value = mObjectDetail.getString(VALUE);
                arrDetails.add(new DetailsRows(title, style, value));
                if (title.equals("Ngưởi xử lý")) {
                    mInforTrainfer.setProcessPerson(value);
                }
                if (title.equals("Cấp cơ quan")) {
                    mInforTrainfer.setProcessPosition(value);
                }
                if (title.equals("Cơ quan ban hành")) {
                    mInforTrainfer.setDepartmentPosition(value);
                }
                Log.d("VinhCNLog arrDetail: ", title + style + value);
            }

            mDetailView.setAdapterListDetais(arrDetails, DETAIL);
            JSONArray mArrayFileAttach = mJsonObject.getJSONArray(ATTACH_FILE);
            for (int i = 0; i < mArrayFileAttach.length(); i++) {
                JSONObject mObjectFileAttach = mArrayFileAttach.getJSONObject(i);
                String fileName = mObjectFileAttach.getString(NAME);
                String fileType = mObjectFileAttach.getString(TYPE);
                String fileURL = mObjectFileAttach.getString(ATTACK_URL);
                String Base64Code = "";
                if (!mObjectFileAttach.isNull(BASE_64)) {
                    Base64Code = mObjectFileAttach.getString(BASE_64);
                }
                boolean digitalSignature = false;
                long fileEntryId = 0;
                String status = "";
                if (!mObjectFileAttach.isNull(DIGITAL_SIGNATURE) && !mObjectFileAttach.isNull(FILE_ENTRY_ID) && !mObjectFileAttach.isNull(SCHEDULE_STATUS)) {
                    digitalSignature = mObjectFileAttach.getBoolean(DIGITAL_SIGNATURE);
                    fileEntryId = mObjectFileAttach.getLong(FILE_ENTRY_ID);
                    status = mObjectFileAttach.getString(SCHEDULE_STATUS);
                }
                arrAtachFile.add(new AttachFile(fileName, fileType, fileURL, Base64Code, digitalSignature, fileEntryId, status, moduleRow));
            }
            FileAttachAdapter attachAdapter = new FileAttachAdapter(Context, arrAtachFile);
            mDetailView.setAdapterListFileAttach(attachAdapter, arrAtachFile, DETAIL);
            if (!mJsonObject.isNull(WORKFLOWTRANSITION)) {
                JSONArray mArrayTransferLines = mJsonObject.getJSONArray(WORKFLOWTRANSITION);
//                for (int i = 0; i < mArrayTransferLines.length(); i++) {
//                    if (mArrayTransferLines != null) {
//                        JSONObject mObjectTrainferLine = mArrayTransferLines.getJSONObject(i);
//                        switch (mObjectTrainferLine.getInt(TYPE)) {
//                            case TAP_REPORT_RESUILT:
//                                mInforTrainfer.setTrainferReport(mObjectTrainferLine.getString(ID));
//                                break;
//                            case TAP_FORWARD_PERSON:
//                                mInforTrainfer.setTrainferID(mObjectTrainferLine.getString(ID));
//                                break;
//                            case TAP_FORWARD_DEPARTMENT:
//                                mInforTrainfer.setForwardDepartmentID(mObjectTrainferLine.getLong(ID));
//                                break;
//                            case TAP_CANCEL:
//                                mInforTrainfer.setWorkFlowStationCancel(mObjectTrainferLine.getLong(ID));
//                                break;
//                            case TAP_RETURN:
//                                mInforTrainfer.setWorkFlowStationReturn(mObjectTrainferLine.getLong(ID));
//                                break;
//                            case TAP_FORWARD_RELEASE:
//                                mInforTrainfer.setTrainferReleaseID(mObjectTrainferLine.getString(ID));
//                                break;
//                            case TAP_14:
//                                mInforTrainfer.setTrainfer_14_ID(mObjectTrainferLine.getString(ID));
//                                break;
//
//                        }
//                    }
//                }
                mDetailView.setVisibilitiesButtonForward(mArrayTransferLines.toString(), DETAIL);
            } else {
                mDetailView.setVisibilitiesButtonForward("[]", DETAIL);
            }
            if (mInforTrainfer.getIsScreen().equals(NOTIFY_SCREEN)) {
                mDetailView.deleteNotify();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onVolleyCompleted(String s, String CaseRequest) {
        refreshUI(s, CaseRequest);
    }

    @Override
    public void onVolleyError(String s) {
        mDetailView.closeProgress();
        mDetailView.ToastError(s);
    }

    //    class ReadJsonDetail extends AsyncTask<String, Integer, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            String Resuilt = "";
//            JSONObject mJsonObject = new JSONObject();
//            JSONObject mJsonObjectData;
//            try {
//                switch (mInforTrainfer.getIsScreen()) {
//                    case HOT_PROCESS:
//                    case HOT_PROCESS_DEMURRAGE:
//                    case HOT_PROCESS_ONDUE:
//                        mJsonObject.put(MODULE, HOTLINE);
//                        mJsonObject.put(NEOTYPE, TYPE_HOT_DETAIL);
//                        mJsonObjectData = new JSONObject();
//                        mJsonObjectData.put(MESSAGE_REFLECT_ID, mInforTrainfer.getDocumentID());
//                        mJsonObject.put(DATA, mJsonObjectData.toString());
//                        Resuilt = eventPostRequest(getModuleInfor(HOTLINE, Context), mJsonObject.toString(), getUserid(), getPass());
//                        break;
//                    case DOC_NOT_SEEN_TYPE:
//                    case DOC_NOT_PROCESS_TYPE:
//                    case DOC_DEMURRAGE_TYPE:
//                        if (mInforTrainfer.getTypeHomeListDocument().equals(TYPE_HOME_LIST_DOCUMENT_SENT)) {
//                            mJsonObject.put(MODULE, MODULE_LOOKUP_DOCUMENT);
//                            mJsonObject.put(NEOTYPE, TYPE_LOOKUP_DETAIL_SENT);
//                            mJsonObjectData = new JSONObject();
//                            mJsonObjectData.put(DOC_SEND_ID, mInforTrainfer.getDocumentID());
//                            mJsonObject.put(DATA, mJsonObjectData.toString());
//                        } else {
//                            mJsonObject.put(MODULE, MODULE_LOOKUP_DOCUMENT);
//                            mJsonObject.put(NEOTYPE, TYPE_LOOKUP_DETAIL_COMING);
//                            mJsonObjectData = new JSONObject();
//                            mJsonObjectData.put(DOC_RECEIPT_ID, mInforTrainfer.getDocumentID());
//                            mJsonObject.put(DATA, mJsonObjectData.toString());
//                        }
//                        Resuilt = eventPostRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, Context), mJsonObject.toString(), getUserid(), getPass());
//                        break;
//                    case STA_DOC_PROCESS_ON_TIME_FULL:
//                    case STA_DOC_NOT_PROCESS_FULL:
//                    case STA_DOC_DEMURRAGE_FULL:
//                    case LIST_DOCUMENT_DEPARTMENT:
//                    case LOOKUP_COMING:
//                        mJsonObject.put(MODULE, MODULE_LOOKUP_DOCUMENT);
//                        mJsonObject.put(NEOTYPE, TYPE_LOOKUP_DETAIL_COMING);
//                        mJsonObjectData = new JSONObject();
//                        mJsonObjectData.put(DOC_RECEIPT_ID, mInforTrainfer.getDocumentID());
//                        mJsonObject.put(DATA, mJsonObjectData.toString());
//                        Resuilt = eventPostRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, Context), mJsonObject.toString(), getUserid(), getPass());
//                        break;
//                    case LOOKUP_SENT:
//                        mJsonObject.put(MODULE, MODULE_LOOKUP_DOCUMENT);
//                        mJsonObject.put(NEOTYPE, TYPE_LOOKUP_DETAIL_SENT);
//                        mJsonObjectData = new JSONObject();
//                        mJsonObjectData.put(DOC_SEND_ID, mInforTrainfer.getDocumentID());
//                        mJsonObject.put(DATA, mJsonObjectData.toString());
//                        Resuilt = eventPostRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, Context), mJsonObject.toString(), getUserid(), getPass());
//                        break;
//                    case LOOKUP_INTERNAL:
//                        mJsonObject.put(MODULE, MODULE_LOOKUP_DOCUMENT);
//                        mJsonObject.put(NEOTYPE, TYPE_LOOKUP_DETAIL_INTERNAL);
//                        mJsonObjectData = new JSONObject();
//                        mJsonObjectData.put(DOC_LOCAL_ID, mInforTrainfer.getDocumentID());
//                        mJsonObject.put(DATA, mJsonObjectData.toString());
//                        Resuilt = eventPostRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, Context), mJsonObject.toString(), getUserid(), getPass());
//                        break;
//                    // statistical department
//                    case TAP_DPM_DELAYS:
//                    case TAP_DPM_INDUE:
//                    case TAP_DPM_ONTIME:
//                    case TAP_DPM_OUT_OF_DATE:
//                        // task
//                    case TASK_REPORTED:
//                    case TASK_PROCESS:
//                    case TASK_PROCESS_ON_TIME:
//                    case TASK_PROCESS_NEAR_DEMURRAGE:
//                    case TASK_PROCESS_DEMURRAGE:
//                        mJsonObject.put(MODULE, MODULE_LOOKUP_DOCUMENT);
//                        mJsonObject.put(NEOTYPE, TYPE_TASK_DETAIL);
//                        mJsonObjectData = new JSONObject();
//                        mJsonObjectData.put(TASK_ID, mInforTrainfer.getDocumentID());
//                        mJsonObject.put(DATA, mJsonObjectData.toString());
//                        Resuilt = eventPostRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, Context), mJsonObject.toString(), getUserid(), getPass());
//                        break;
//                    case NOTIFY_SCREEN:
//                    default:
//                        mJsonObject.put(MODULE, MODULE_PROCESSING_WORKING);
//                        mJsonObject.put(NEOTYPE, TYPE_DETAIL_WORKING);
//                        mJsonObjectData = new JSONObject();
//                        mJsonObjectData.put(JOB_ID, mInforTrainfer.getDocumentID());
//                        mJsonObject.put(DATA, mJsonObjectData.toString());
//                        Resuilt = eventPostRequest(getModuleInfor(MODULE_PROCESSING_WORKING, Context), mJsonObject.toString(), getUserid(), getPass());
//                        break;
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            String user = getUserid();
//            String pass = getPass();
//            Log.d(TAG, user + " " + pass);
//            return Resuilt;
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
////            s = "";
//            if (!s.equals(nULL_STRING) && !s.equals("Error!")) {
//                writeToFile(s, Context, String.valueOf(mInforTrainfer.getDocumentID()));
//            }
//            upDateViewDetail(s);
//            super.onPostExecute(s);
//        }
//    }
//    class ReadJsonHandleProcess extends AsyncTask<String, Integer, String> {
//        @Override
//        protected String doInBackground(String... params) {
//            JSONObject mJsonObject = new JSONObject();
//            try {
//                mJsonObject.put(MODULE, MODULE_LOOKUP_DOCUMENT);
//                mJsonObject.put(NEOTYPE, PROCESS_HANDLE);
//                JSONObject mData = new JSONObject();
//                mData.put(JOB_ID, mInforTrainfer.getDocumentID());
//                mJsonObject.put(DATA, mData);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
////            return makePostRequest(params[0], mJsonObject.toString(), getUserid(), getPass());
//            return eventPostRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, Context), mJsonObject.toString(), getUserid(), getPass());
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            if (!s.equals("Error!") && !s.equals(nULL_STRING)) {
//                writeToFile(s, Context, "handle" + mInforTrainfer.getDocumentID());
//            }
//            super.onPostExecute(s);
//        }
//    }
//    public void BrowerJson(JSONObject xJsonObject, TreeNode node) {
//        try {
//            JSONArray mJsonArrayChildTree = xJsonObject.getJSONArray(CHILDREN);
//            for (int i = 0; i < mJsonArrayChildTree.length(); i++) {
//                JSONObject mJsonObject = mJsonArrayChildTree.getJSONObject(i);
//                Log.d(TAG, mJsonObject + nULL_STRING);
//                if (!mJsonObject.isNull(CHILDREN)) {
//                    String mProcessor = String.valueOf((Html.fromHtml(mJsonObject.getString(PROCESSOR))));
//                    String mPosition = mJsonObject.getString(ROLE_NAME);
//                    String mDepartment = mJsonObject.getString(PROCESS_ORG);
//                    String mRequestProcessing = mJsonObject.getString(PROCESS_CONTENT);
//                    String mProcessingDay = mJsonObject.getString(PROCESS_DATE);
//                    String mReceivedDay = mJsonObject.getString(RECEIVE_DATE);
//                    TreeHolder.IconTreeItem nodeItem = new TreeHolder.IconTreeItem(R.drawable.ic_keyboard_arrow_right_black_24dp, mProcessor,
//                            mPosition, mDepartment, mRequestProcessing, mProcessingDay, mReceivedDay);
//                    TreeNode Child = new TreeNode(nodeItem).setViewHolder(new TreeHolder(Context));
//                    node.addChildren(Child);
//                    BrowerJson(mJsonObject, Child);
//                } else {
//                    String mProcessor = String.valueOf((Html.fromHtml(mJsonObject.getString(PROCESSOR))));
//                    String mPosition = mJsonObject.getString(ROLE_NAME);
//                    String mDepartment = mJsonObject.getString(PROCESS_ORG);
//                    String mRequestProcessing = mJsonObject.getString(PROCESS_CONTENT);
//                    String mProcessingDay = mJsonObject.getString(PROCESS_DATE);
//                    String mReceivedDay = mJsonObject.getString(RECEIVE_DATE);
//                    TreeHolder.IconTreeItem nodeItem = new TreeHolder.IconTreeItem(0, mProcessor,
//                            mPosition, mDepartment, mRequestProcessing, mProcessingDay, mReceivedDay);
//                    TreeNode Child = new TreeNode(nodeItem).setViewHolder(new TreeHolder(Context));
//                    node.addChildren(Child);
//                }
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//
    //                             read json tree and some information when tap tree item
//                            JSONObject mJsonTreeForward = mJsonObject.getJSONObject(TREEJOBCYCLE);
//                            String mProcessor = mJsonTreeForward.getString(PROCESSOR);
//                            String mPosition = mJsonTreeForward.getString(ROLE_NAME);
//                            String mDepartment = mJsonTreeForward.getString(PROCESS_ORG);
//                            String mRequestProcessing = mJsonTreeForward.getString(PROCESS_CONTENT);
//                            String mProcessingDay = mJsonTreeForward.getString(PROCESS_DATE);
//                            String mReceivedDay = mJsonTreeForward.getString(RECEIVE_DATE);
//                            Log.d(TAG, mProcessor);
//                            root = TreeNode.root();
//                            TreeHolder.IconTreeItem nodeItem = new TreeHolder.IconTreeItem(R.drawable.ic_keyboard_arrow_right_black_24dp, mProcessor, mPosition, mDepartment,
//                                    mRequestProcessing, mProcessingDay, mReceivedDay);
//                            Parent = new TreeNode(nodeItem).setViewHolder(new TreeHolder(Context));
//                            root.addChild(Parent);
//                            BrowerJson(mJsonTreeForward, Parent);
//                            AndroidTreeView tView = new AndroidTreeView(Context, root);
//                            tView.setDefaultContainerStyle(R.style.TreeNodeStyleCustom);
//                            tView.setUse2dScroll(true);
//                            mDetailView.SetViewList(tView, FORWARD);
//                            tView.expandAll();
//                            closeProgressDialog();

}
