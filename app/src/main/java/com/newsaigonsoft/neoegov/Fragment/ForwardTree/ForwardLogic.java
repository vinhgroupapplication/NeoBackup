package com.newsaigonsoft.neoegov.Fragment.ForwardTree;

import android.content.Context;
import android.text.Html;
import android.util.Log;

import com.newsaigonsoft.neoegov.AVolleyManager.VolleyTask;
import com.newsaigonsoft.neoegov.AVolleyManager.VolleyTaskCompletedListenner;
import com.newsaigonsoft.neoegov.AsynTaskManager.AsyncTaskCompleteListener;
import com.newsaigonsoft.neoegov.GsonObject.GsonForwardTree;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.InforTrainfer;
import com.newsaigonsoft.neoegov.Subjects.JsonKeyManager;
import com.newsaigonsoft.neoegov.Subjects.ResuiltObject;
import com.newsaigonsoft.neoegov.Subjects.SumManager;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DATA;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DOC_DEMURRAGE_TYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DOC_NOT_PROCESS_TYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DOC_NOT_SEEN_TYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DOC_RECEIPT_ID;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DOC_SEND_ID;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.HOTLINE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.JOB_ID;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MODULE_LOOKUP_DOCUMENT;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MESSAGE_REFLECT_ID;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MODULE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MODULE_WORK_ARISE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.NEOTYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MODULE_PROCESSING_WORKING;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_FORWARD_LOOKUP_COMING;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_FORWARD_LOOKUP_INTERNAL;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_FORWARD_LOOKUP_SEND;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_FORWARD_PROCESS;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_HOME_LIST_DOCUMENT_COMING;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_HOME_LIST_DOCUMENT_SENT;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_HOT_FORWARD;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.WORK_ARISE_ID;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.FORWARD;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.HOT_PROCESS;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.HOT_PROCESS_DEMURRAGE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.HOT_PROCESS_ONDUE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LIST_DOCUMENT_DEPARTMENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LOOKUP_COMING;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LOOKUP_INTERNAL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LOOKUP_SENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.STA_DOC_DEMURRAGE_FULL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.STA_DOC_NOT_PROCESS_FULL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.STA_DOC_PROCESS_ON_TIME_FULL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.STA_DOC_PROCESS_ON_TIME_FULL_ARISE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TREE_HOME_STATISTICAL_COMING;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TREE_HOME_STATISTICAL_SEND;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TREE_HOTLINE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TREE_LOOKUP_COMING;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TREE_LOOKUP_INTERNAL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TREE_LOOKUP_SEND;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TREE_PROCESS;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TREE_WORK_ARISE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.WORK_ARISE;

/**
 * Created by Vinh on 11/3/2017.
 */

public class ForwardLogic extends SumManager implements ForwardImp, AsyncTaskCompleteListener<ResuiltObject>, VolleyTaskCompletedListenner<ResuiltObject> {

    ForwardView mForwardView;
    Context context;
    InforTrainfer mInforTrainfer;
    TreeNode root;
    TreeNode Parent;

    public ForwardLogic(ForwardView mForwardView, Context context, InforTrainfer InforTrainfer) {
        this.mForwardView = mForwardView;
        this.context = context;
        this.mInforTrainfer = InforTrainfer;
        getInforAccountFromShareReferenced(context);

    }

    @Override
    public void requestDataForwardTree() {
        mForwardView.showProgress();
        requestJsonTreeForward();
//        new ReadForwardTree().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void onTaskCompleted(String s, String CaseRequest) {
        refreshUI(s, CaseRequest);
    }

    private void refreshUI(String s, String CaseRequest) {
            Log.d("VinhCNLog: ", s);
            GsonForwardTree mGsonForwardTree = getGson().fromJson(s, GsonForwardTree.class);
            GsonForwardTree.DataBean mDataBean = mGsonForwardTree.getData();
            TreeHolder.IconTreeItem nodeItem;
            if (mDataBean.getProcesser()==null){
                mForwardView.closeProgress();
//                Toast.makeText(context, "Lỗi hệ thống", Toast.LENGTH_SHORT).show();
                return;
            }
            String mProcessor = mDataBean.getProcesser();
            String mPosition = mDataBean.getRoleName();
            String mDepartment = mDataBean.getProcessOrg();
            String mRequestProcessing = mDataBean.getProcessContent();
            String mProcessingDay = mDataBean.getProcessDate();
            String mReceivedDay = mDataBean.getReceiveDate();
            boolean isProcessing = mDataBean.isIsProcessing();
            root = TreeNode.root();
            if (mDataBean.getChildren() != null) {
                nodeItem = new TreeHolder.IconTreeItem(R.drawable.baseline_chevron_right_24, mProcessor, mPosition, mDepartment,
                        mRequestProcessing, mProcessingDay, mReceivedDay, isProcessing);
            } else {
                nodeItem = new TreeHolder.IconTreeItem(0, mProcessor, mPosition, mDepartment,
                        mRequestProcessing, mProcessingDay, mReceivedDay, isProcessing);
            }
            Parent = new TreeNode(nodeItem).setViewHolder(new TreeHolder(context));
            root.addChild(Parent);
            if (mDataBean.getChildren()!=null){
                BrowerJson(mDataBean, Parent);
            }
            AndroidTreeView tView = new AndroidTreeView(context, root);
            tView.setDefaultContainerStyle(R.style.TreeNodeStyleCustom);
            tView.setUse2dScroll(true);
            mForwardView.SetTreeList(tView, FORWARD);
            mForwardView.closeProgress();
            tView.expandAll();
    }

    private JSONObject addJsonTreeHotLine() {
        JSONObject mJsonObject = new JSONObject();
        JSONObject mData = new JSONObject();
        try {
            mJsonObject.put(MODULE, HOTLINE);
            mJsonObject.put(NEOTYPE, TYPE_HOT_FORWARD);
            mData.put(MESSAGE_REFLECT_ID, mInforTrainfer.getDocumentID());
            mJsonObject.put(DATA, mData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mJsonObject;
    }


    private JSONObject addJsonTreeHomeStatisComing() {
        JSONObject mJsonObject = new JSONObject();
        JSONObject mData = new JSONObject();
        try {
            mJsonObject.put(MODULE, MODULE_LOOKUP_DOCUMENT);
            mJsonObject.put(NEOTYPE, TYPE_FORWARD_LOOKUP_COMING);
            mData.put(DOC_RECEIPT_ID, mInforTrainfer.getDocumentID());
            mJsonObject.put(DATA, mData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mJsonObject;
    }

    private JSONObject addJsonTreeHomeStatisSent() {
        JSONObject mJsonObject = new JSONObject();
        JSONObject mData = new JSONObject();
        try {
            mJsonObject.put(MODULE, MODULE_LOOKUP_DOCUMENT);
            mJsonObject.put(NEOTYPE, TYPE_FORWARD_LOOKUP_SEND);
            mData.put(DOC_SEND_ID, mInforTrainfer.getDocumentID());
            mJsonObject.put(DATA, mData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mJsonObject;
    }

    private JSONObject addJsonTreeLookupComing() {
        JSONObject mJsonObject = new JSONObject();
        JSONObject mData = new JSONObject();
        try {
            mJsonObject.put(MODULE, MODULE_LOOKUP_DOCUMENT);
            mJsonObject.put(NEOTYPE, TYPE_FORWARD_LOOKUP_COMING);
            mData.put(DOC_RECEIPT_ID, mInforTrainfer.getDocumentID());
            mJsonObject.put(DATA, mData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mJsonObject;
    }


    private JSONObject addJsonTreeLookupSent() {
        JSONObject mJsonObject = new JSONObject();
        JSONObject mData = new JSONObject();
        try {
            mJsonObject.put(MODULE, MODULE_LOOKUP_DOCUMENT);
            mJsonObject.put(NEOTYPE, TYPE_FORWARD_LOOKUP_SEND);
            mData.put(DOC_SEND_ID, mInforTrainfer.getDocumentID());
            mJsonObject.put(DATA, mData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mJsonObject;
    }

    private JSONObject addJsonTreeLookupInternal() {
        JSONObject mJsonObject = new JSONObject();
        JSONObject mData = new JSONObject();
        try {
            mJsonObject.put(MODULE, MODULE_LOOKUP_DOCUMENT);
            mJsonObject.put(NEOTYPE, TYPE_FORWARD_LOOKUP_INTERNAL);
            mData.put(DOC_RECEIPT_ID, mInforTrainfer.getDocumentID());
            mJsonObject.put(DATA, mData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mJsonObject;
    }


    private JSONObject addJsonTreeProcess() {
        JSONObject mJsonObject = new JSONObject();
        JSONObject mData = new JSONObject();
        try {
            mJsonObject.put(MODULE, MODULE_PROCESSING_WORKING);
            mJsonObject.put(NEOTYPE, TYPE_FORWARD_PROCESS);
            mData.put(JOB_ID, mInforTrainfer.getDocumentID());
            mJsonObject.put(DATA, mData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mJsonObject;
    }

    public void requestJsonTreeForward() {
        String isCreen = mInforTrainfer.getIsScreen();
        switch (isCreen) {
            case HOT_PROCESS_ONDUE:
            case HOT_PROCESS:
            case HOT_PROCESS_DEMURRAGE:
//                new NeoTask(context, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(context, TREE_HOTLINE, addJsonTreeHotLine(), getLink() + URL_CENTER_6_1));
                new VolleyTask(context, TREE_HOTLINE, addJsonTreeHotLine(), this);
                break;
            case DOC_NOT_SEEN_TYPE:
            case DOC_NOT_PROCESS_TYPE:
            case DOC_DEMURRAGE_TYPE:
            case STA_DOC_PROCESS_ON_TIME_FULL:
            case STA_DOC_NOT_PROCESS_FULL:
            case STA_DOC_DEMURRAGE_FULL:
            case LIST_DOCUMENT_DEPARTMENT:
                switch (mInforTrainfer.getTypeHomeListDocument()) {
                    case TYPE_HOME_LIST_DOCUMENT_COMING:
//                        new NeoTask(context, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(context, TREE_HOME_STATISTICAL_COMING, addJsonTreeHomeStatisComing().toString(), getLink() + URL_CENTER_6_1));
                        new VolleyTask(context, TREE_HOME_STATISTICAL_COMING, addJsonTreeHomeStatisComing(), this);
                        break;
                    case TYPE_HOME_LIST_DOCUMENT_SENT:
//                        new NeoTask(context, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(context, TREE_HOME_STATISTICAL_SEND, addJsonTreeHomeStatisSent().toString(), getLink() + URL_CENTER_6_1));
                        new VolleyTask(context, TREE_HOME_STATISTICAL_SEND, addJsonTreeHomeStatisSent(), this);
                        break;
                }
                break;
            case LOOKUP_COMING:
//                new NeoTask(context, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(context, TREE_LOOKUP_COMING, addJsonTreeLookupComing().toString(), getLink() + URL_CENTER_6_1));
                new VolleyTask(context, TREE_LOOKUP_COMING, addJsonTreeLookupComing(), this);
                break;
            case LOOKUP_SENT:
//                new NeoTask(context, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(context, TREE_LOOKUP_SEND, addJsonTreeLookupSent().toString(), getLink() + URL_CENTER_6_1));
                new VolleyTask(context, TREE_LOOKUP_SEND, addJsonTreeLookupSent(), this);
                break;
            case LOOKUP_INTERNAL:
//                new NeoTask(context, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(context, TREE_LOOKUP_INTERNAL, addJsonTreeLookupInternal().toString(), getLink() + URL_CENTER_6_1));
                new VolleyTask(context, TREE_LOOKUP_INTERNAL, addJsonTreeLookupInternal(), this);
                break;
            case STA_DOC_PROCESS_ON_TIME_FULL_ARISE:
            case WORK_ARISE:
                new VolleyTask(context, TREE_WORK_ARISE,  addJsonTreeWorkArise(), this);
                break;
            default:
//                new NeoTask(context, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(context, TREE_PROCESS, addJsonTreeProcess().toString(), getLink() + URL_CENTER_6_1));
                new VolleyTask(context, TREE_PROCESS, addJsonTreeProcess(), this);
                break;
        }
    }

    private JSONObject addJsonTreeWorkArise() {
        JSONObject mJsonObject = new JSONObject();
        JSONObject mData = new JSONObject();
        try {
            mJsonObject.put(MODULE, MODULE_WORK_ARISE);
            mJsonObject.put(NEOTYPE, JsonKeyManager.TYPE_FORWARD_WORK_ARISE);
            mData.put(WORK_ARISE_ID, mInforTrainfer.getDocumentID());
            mJsonObject.put(DATA, mData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mJsonObject;
    }

    public void BrowerJson(GsonForwardTree.DataBean dataBean, TreeNode node) {
            List<GsonForwardTree.DataBean> dataBeans = dataBean.getChildren();
            for (int i = 0; i < dataBeans.size(); i++) {
                GsonForwardTree.DataBean dataBean1 = dataBean.getChildren().get(i);
                if (dataBean1.getChildren()!=null) {
                    String mProcessor = String.valueOf((Html.fromHtml(dataBean1.getProcesser())));
                    String mPosition = dataBean1.getRoleName();
                    String mDepartment = dataBean1.getProcessOrg();
                    String mRequestProcessing = dataBean1.getProcessContent();
                    String mProcessingDay = dataBean1.getProcessDate();
                    String mReceivedDay = dataBean1.getReceiveDate();
                    boolean isProcessing = dataBean1.isIsProcessing();
                    TreeHolder.IconTreeItem nodeItem = new TreeHolder.IconTreeItem(R.drawable.baseline_chevron_right_24, mProcessor,
                            mPosition, mDepartment, mRequestProcessing, mProcessingDay, mReceivedDay, isProcessing);
                    TreeNode Child = new TreeNode(nodeItem).setViewHolder(new TreeHolder(context));
                    node.addChildren(Child);
                    BrowerJson(dataBean1, Child);
                } else {
                    String mProcessor = String.valueOf((Html.fromHtml(dataBean1.getProcesser())));
                    String mPosition = dataBean1.getRoleName();
                    String mDepartment = dataBean1.getProcessOrg();
                    String mRequestProcessing = dataBean1.getProcessContent();
                    String mProcessingDay = dataBean1.getProcessDate();
                    String mReceivedDay = dataBean1.getReceiveDate();
                    boolean isProcessing = dataBean1.isIsProcessing();
                    TreeHolder.IconTreeItem nodeItem = new TreeHolder.IconTreeItem(0, mProcessor,
                            mPosition, mDepartment, mRequestProcessing, mProcessingDay, mReceivedDay, isProcessing);
                    TreeNode Child = new TreeNode(nodeItem).setViewHolder(new TreeHolder(context));
                    node.addChildren(Child);
                }
            }
    }

    @Override
    public void onVolleyCompleted(String s, String CaseRequest) {
        refreshUI(s, CaseRequest);
    }

    @Override
    public void onVolleyError(String s) {
        mForwardView.closeProgress();
        mForwardView.ToastError(s);
    }

    //    class ReadForwardTree extends AsyncTask<String, Integer, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            String Resuilt = nULL_STRING;
//            String isCreen = mInforTrainfer.getIsScreen();
//            JSONObject mJsonObject = new JSONObject();
//            JSONObject mData = new JSONObject();
//            try {
//                switch (isCreen) {
//                    case HOT_PROCESS_ONDUE:
//                    case HOT_PROCESS:
//                    case HOT_PROCESS_DEMURRAGE:
//                        mJsonObject.put(MODULE, HOTLINE);
//                        mJsonObject.put(NEOTYPE, TYPE_HOT_FORWARD);
//                        mData.put(MESSAGE_REFLECT_ID, mInforTrainfer.getDocumentID());
//                        mJsonObject.put(DATA, mData);
//                        Resuilt = eventPostRequest(getModuleInfor(HOTLINE, context), mJsonObject.toString(), getUserid(), getPass());
//                        break;
//                    case DOC_NOT_SEEN_TYPE:
//                    case DOC_NOT_PROCESS_TYPE:
//                    case DOC_DEMURRAGE_TYPE:
//                    case STA_DOC_PROCESS_ON_TIME_FULL:
//                    case STA_DOC_NOT_PROCESS_FULL:
//                    case STA_DOC_DEMURRAGE_FULL:
//                    case LIST_DOCUMENT_DEPARTMENT:
//                        switch (mInforTrainfer.getTypeHomeListDocument()) {
//                            case TYPE_HOME_LIST_DOCUMENT_COMING:
//                                mJsonObject.put(MODULE, MODULE_LOOKUP_DOCUMENT);
//                                mJsonObject.put(NEOTYPE, TYPE_FORWARD_LOOKUP_COMING);
//                                mData.put(DOC_RECEIPT_ID, mInforTrainfer.getDocumentID());
//                                mJsonObject.put(DATA, mData);
//                                Resuilt = eventPostRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, context), mJsonObject.toString(), getUserid(), getPass());
//                                break;
//                            case TYPE_HOME_LIST_DOCUMENT_SENT:
//                                mJsonObject.put(MODULE, MODULE_LOOKUP_DOCUMENT);
//                                mJsonObject.put(NEOTYPE, TYPE_FORWARD_LOOKUP_SEND);
//                                mData.put(DOC_SEND_ID, mInforTrainfer.getDocumentID());
//                                mJsonObject.put(DATA, mData);
//                                Resuilt = eventPostRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, context), mJsonObject.toString(), getUserid(), getPass());
//                                break;
//                        }
//                        break;
//                    case LOOKUP_COMING:
//                        mJsonObject.put(MODULE, MODULE_LOOKUP_DOCUMENT);
//                        mJsonObject.put(NEOTYPE, TYPE_FORWARD_LOOKUP_COMING);
//                        mData.put(DOC_RECEIPT_ID, mInforTrainfer.getDocumentID());
//                        mJsonObject.put(DATA, mData);
//                        Resuilt = eventPostRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, context), mJsonObject.toString(), getUserid(), getPass());
//                        break;
//                    case LOOKUP_SENT:
//                        mJsonObject.put(MODULE, MODULE_LOOKUP_DOCUMENT);
//                        mJsonObject.put(NEOTYPE, TYPE_FORWARD_LOOKUP_SEND);
//                        mData.put(DOC_SEND_ID, mInforTrainfer.getDocumentID());
//                        mJsonObject.put(DATA, mData);
//                        Resuilt = eventPostRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, context), mJsonObject.toString(), getUserid(), getPass());
//                        break;
//                    case LOOKUP_INTERNAL:
//                        mJsonObject.put(MODULE, MODULE_LOOKUP_DOCUMENT);
//                        mJsonObject.put(NEOTYPE, TYPE_FORWARD_LOOKUP_INTERNAL);
//                        mData.put(DOC_RECEIPT_ID, mInforTrainfer.getDocumentID());
//                        mJsonObject.put(DATA, mData);
//                        Resuilt = eventPostRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, context), mJsonObject.toString(), getUserid(), getPass());
//                        break;
//                    default:
//                        mJsonObject.put(MODULE, MODULE_PROCESSING_WORKING);
//                        mJsonObject.put(NEOTYPE, TYPE_FORWARD_PROCESS);
//                        mData.put(JOB_ID, mInforTrainfer.getDocumentID());
//                        mJsonObject.put(DATA, mData);
//                        Resuilt = eventPostRequest(getModuleInfor(MODULE_PROCESSING_WORKING, context), mJsonObject.toString(), getUserid(), getPass());
//                        break;
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            return Resuilt;
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            Log.d("VinhCNLog: ", s);
//            try {
//                JSONObject mJsonObject = new JSONObject(s);
//                JSONObject mJsonTreeForward = mJsonObject.getJSONObject(DATA);
//                String mProcessor = null;
//                TreeHolder.IconTreeItem nodeItem;
//                mProcessor = mJsonTreeForward.getString(PROCESSOR);
//                String mPosition = mJsonTreeForward.getString(ROLE_NAME);
//                String mDepartment = mJsonTreeForward.getString(PROCESS_ORG);
//                String mRequestProcessing = mJsonTreeForward.getString(PROCESS_CONTENT);
//                String mProcessingDay = mJsonTreeForward.getString(PROCESS_DATE);
//                String mReceivedDay = mJsonTreeForward.getString(RECEIVE_DATE);
//                Log.d(TAG, mProcessor);
//                root = TreeNode.root();
//                if (!mJsonTreeForward.isNull(CHILDREN)) {
//                    nodeItem = new TreeHolder.IconTreeItem(R.drawable.ic_keyboard_arrow_right_black_24dp, mProcessor, mPosition, mDepartment,
//                            mRequestProcessing, mProcessingDay, mReceivedDay);
//                } else {
//                    nodeItem = new TreeHolder.IconTreeItem(0, mProcessor, mPosition, mDepartment,
//                            mRequestProcessing, mProcessingDay, mReceivedDay);
//                }
//                Parent = new TreeNode(nodeItem).setViewHolder(new TreeHolder(context));
//                root.addChild(Parent);
//                BrowerJson(mJsonTreeForward, Parent);
//                AndroidTreeView tView = new AndroidTreeView(context, root);
//                tView.setDefaultContainerStyle(R.style.TreeNodeStyleCustom);
//                tView.setUse2dScroll(true);
//                mForwardView.SetTreeList(tView, FORWARD);
//                tView.expandAll();
//                mForwardView.closeProgress();
//            } catch (JSONException e) {
//                mForwardView.closeProgress();
//                e.printStackTrace();
//            }
//            super.onPostExecute(s);
//        }
//    }
//    private void refreshUI(String s, String CaseRequest) {
//        Log.d("VinhCNLog: ", s);
//        try {
//            JSONObject mJsonObject = new JSONObject(s);
//            JSONObject mJsonTreeForward = mJsonObject.getJSONObject(DATA);
//            String mProcessor = null;
//            TreeHolder.IconTreeItem nodeItem;
//            mProcessor = mJsonTreeForward.getString(PROCESSOR);
//            String mPosition = mJsonTreeForward.getString(ROLE_NAME);
//            String mDepartment = mJsonTreeForward.getString(PROCESS_ORG);
//            String mRequestProcessing = mJsonTreeForward.getString(PROCESS_CONTENT);
//            String mProcessingDay = mJsonTreeForward.getString(PROCESS_DATE);
//            String mReceivedDay = mJsonTreeForward.getString(RECEIVE_DATE);
//            boolean isProcessing = false;
//            if (!mJsonTreeForward.isNull(IS_PROCESSING))
//                isProcessing = mJsonTreeForward.getBoolean(IS_PROCESSING);
//            Log.d(TAG, mProcessor);
//            root = TreeNode.root();
//            if (!mJsonTreeForward.isNull(CHILDREN)) {
//                nodeItem = new TreeHolder.IconTreeItem(R.drawable.ic_keyboard_arrow_right_black_24dp, mProcessor, mPosition, mDepartment,
//                        mRequestProcessing, mProcessingDay, mReceivedDay, isProcessing);
//            } else {
//                nodeItem = new TreeHolder.IconTreeItem(0, mProcessor, mPosition, mDepartment,
//                        mRequestProcessing, mProcessingDay, mReceivedDay, isProcessing);
//            }
//            Parent = new TreeNode(nodeItem).setViewHolder(new TreeHolder(context));
//            root.addChild(Parent);
//            BrowerJson(mJsonTreeForward, Parent);
//            AndroidTreeView tView = new AndroidTreeView(context, root);
//            tView.setDefaultContainerStyle(R.style.TreeNodeStyleCustom);
//            tView.setUse2dScroll(true);
//            mForwardView.SetTreeList(tView, FORWARD);
//            tView.expandAll();
//            mForwardView.closeProgress();
//        } catch (JSONException e) {
//            mForwardView.closeProgress();
//            e.printStackTrace();
//        }
//    }
//
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
//                    boolean isProcessing = false;
//                    if (!mJsonObject.isNull(IS_PROCESSING))
//                        isProcessing = mJsonObject.getBoolean(IS_PROCESSING);
//                    TreeHolder.IconTreeItem nodeItem = new TreeHolder.IconTreeItem(R.drawable.ic_keyboard_arrow_right_black_24dp, mProcessor,
//                            mPosition, mDepartment, mRequestProcessing, mProcessingDay, mReceivedDay, isProcessing);
//                    TreeNode Child = new TreeNode(nodeItem).setViewHolder(new TreeHolder(context));
//                    node.addChildren(Child);
//                    BrowerJson(mJsonObject, Child);
//                } else {
//                    String mProcessor = String.valueOf((Html.fromHtml(mJsonObject.getString(PROCESSOR))));
//                    String mPosition = mJsonObject.getString(ROLE_NAME);
//                    String mDepartment = mJsonObject.getString(PROCESS_ORG);
//                    String mRequestProcessing = mJsonObject.getString(PROCESS_CONTENT);
//                    String mProcessingDay = mJsonObject.getString(PROCESS_DATE);
//                    String mReceivedDay = mJsonObject.getString(RECEIVE_DATE);
//                    boolean isProcessing = false;
//                    if (!mJsonObject.isNull(IS_PROCESSING))
//                        isProcessing = mJsonObject.getBoolean(IS_PROCESSING);
//                    TreeHolder.IconTreeItem nodeItem = new TreeHolder.IconTreeItem(0, mProcessor,
//                            mPosition, mDepartment, mRequestProcessing, mProcessingDay, mReceivedDay, isProcessing);
//                    TreeNode Child = new TreeNode(nodeItem).setViewHolder(new TreeHolder(context));
//                    node.addChildren(Child);
//                }
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//    }
}
