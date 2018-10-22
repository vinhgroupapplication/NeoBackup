package com.newsaigonsoft.neoegov.Fragment.Detail;

import android.content.Context;

import com.newsaigonsoft.neoegov.AVolleyManager.VolleyTaskCompletedListenner;
import com.newsaigonsoft.neoegov.AsynTaskManager.AsyncTaskCompleteListener;
import com.newsaigonsoft.neoegov.Subjects.InforTrainfer;
import com.newsaigonsoft.neoegov.Subjects.ResuiltObject;
import com.newsaigonsoft.neoegov.Subjects.SumManager;

/**
 * Created by Vinh on 10/19/2017.
 */

public class DetailFmLogic extends SumManager implements DetailFmImp, AsyncTaskCompleteListener<ResuiltObject>, VolleyTaskCompletedListenner<ResuiltObject> {

    DetailFmView mDetailFmView;
    Context context;
    InforTrainfer mInforTrainfer;


    public DetailFmLogic(DetailFmView mDetailFmView, Context context, InforTrainfer inforTrainfer) {
        this.mDetailFmView = mDetailFmView;
        this.context = context;
        this.mInforTrainfer = inforTrainfer;
        getInforAccountFromShareReferenced(context);
    }



    @Override
    public void onTaskCompleted(String s, String CaseRequest) {
    }



    @Override
    public void onVolleyCompleted(String s, String CaseRequest) {
    }

    @Override
    public void onVolleyError(String s) {
        mDetailFmView.closeProgress();
        mDetailFmView.ToastError(s);
    }


//    class ReadJsonCancelList extends AsyncTask<String, Integer, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            JSONObject mJsonObject = new JSONObject();
//            try {
//                mJsonObject.put(MODULE, MODULE_PROCESSING_WORKING);
//                mJsonObject.put(NEOTYPE, TYPE_CANCEL_LIST);
//                JSONObject mData = new JSONObject();
//                mData.put(JOB_ID, mInforTrainfer.getDocumentID());
//                mData.put(WORK_FLOW_TRAINSITION_ID, mInforTrainfer.getWorkFlowStationCancel());
//                mJsonObject.put(DATA, mData);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            return eventPostRequest(getModuleInfor(MODULE_PROCESSING_WORKING, context), mJsonObject.toString(), getUserid(), getPass());
//
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            if (!s.equals("Error!") && !s.equals(nULL_STRING)) {
//                writeToFile(s, context, "cancel_list" + mInforTrainfer.getDocumentID());
//                mDetailFmView.startIntent();
//            } else {
//                mDetailFmView.showError();
//            }
//            super.onPostExecute(s);
//        }
//    }
//class ReadJsonPerSonReturn extends AsyncTask<String, Integer, String> {
//
//    @Override
//    protected String doInBackground(String... params) {
//        JSONObject mJsonObject = new JSONObject();
//        try {
//            mJsonObject.put(MODULE, MODULE_PROCESSING_WORKING);
//            mJsonObject.put(NEOTYPE, TYPE_INPUT_PERSON);
//            JSONObject mData = new JSONObject();
//            mData.put(JOB_ID, mInforTrainfer.getDocumentID());
//            mData.put(WORK_FLOW_TRAINSITION_ID, mInforTrainfer.getWorkFlowStationReturn());
//            mData.put(KEYWORDS, "");
//            mData.put(ROW_PER_PAGE, -1);
//            mData.put(NUM_PAGE, -1);
//            mJsonObject.put(DATA, mData);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
////            return mDetailForwardActivity.makePostRequest(params[0], mJsonObject.toString(), mDetailForwardActivity.getUserid(), mDetailForwardActivity.getPass());
//        return eventPostRequest(getModuleInfor(MODULE_PROCESSING_WORKING, context), mJsonObject.toString(), getUserid(), getPass());
//
//    }
//
//    @Override
//    protected void onPostExecute(String s) {
//        if (!s.equals("Error!") && !s.equals(nULL_STRING)) {
//            writeToFile(s, context, "return_list" + mInforTrainfer.getDocumentID());
//            closeProgressDialog();
//            mDetailFmView.startIntent();
//        } else {
//            mDetailFmView.showError();
//        }
//        super.onPostExecute(s);
//    }
//}
    //    class ReadJsonInputPerson extends AsyncTask<String, Integer, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            JSONObject mJsonObject = new JSONObject();
//            try {
////                if (mInforTrainfer.getTrainferID() != null && mInforTrainfer.getDocumentID() != 0) {
//                mJsonObject.put(MODULE, MODULE_PROCESSING_WORKING);
//                mJsonObject.put(NEOTYPE, TYPE_INPUT_PERSON);
//                JSONObject mData = new JSONObject();
//                mData.put(JOB_ID, mInforTrainfer.getDocumentID());
//                switch (isTap) {
//                    case TAP_FORWARD_PERSON:
//                        mData.put(WORK_FLOW_TRAINSITION_ID, Integer.parseInt(mInforTrainfer.getTrainferID()));
//                        break;
//                    case TAP_REPORT_RESUILT:
//                        mData.put(WORK_FLOW_TRAINSITION_ID, Integer.parseInt(mInforTrainfer.getTrainferReport()));
//                        break;
//                    case TAP_FORWARD_RELEASE:
//                        mData.put(WORK_FLOW_TRAINSITION_ID, Integer.parseInt(mInforTrainfer.getTrainferReleaseID()));
//                        break;
//                }
//                mData.put(KEYWORDS, "");
//                mData.put(ROW_PER_PAGE, -1);
//                mData.put(NUM_PAGE, -1);
//                mJsonObject.put(DATA, mData);
////                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            return eventPostRequest(getModuleInfor(MODULE_PROCESSING_WORKING, context), mJsonObject.toString(), getUserid(), getPass());
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            try {
//                JSONObject mJsonResuilt = new JSONObject(s);
//                String message = mJsonResuilt.getString(MESSAGE);
//                if (message.equals("success")) {
//                    mDetailFmView.inseartInputPersonDatabase(s);
//                }
//                mDetailFmView.startIntent();
//            } catch (JSONException e) {
//                mDetailFmView.closeProgress();
//                ShowErrorToast(context);
//                e.printStackTrace();
//            }
//            super.onPostExecute(s);
//        }
//    }
    //    class ReadJsonDepartment extends AsyncTask<String, Integer, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            JSONObject mJsonObject = new JSONObject();
//            try {
//                mJsonObject.put(MODULE, MODULE_PROCESSING_WORKING);
//                mJsonObject.put(NEOTYPE, TYPE_DEPARTMENT_LIST_RECEIVE);
//                JSONObject mData = new JSONObject();
//                mData.put(JOB_ID, mInforTrainfer.getDocumentID());
//                mData.put(WORK_FLOW_TRAINSITION_ID, mInforTrainfer.getForwardDepartmentID());
//                mJsonObject.put(DATA, mData.toString());
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            return eventPostRequest(getModuleInfor(MODULE_PROCESSING_WORKING, context), mJsonObject.toString(), getUserid(), getPass());
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            if (!s.equals("Error!") && !s.equals(nULL_STRING)) {
//                writeToFile(s, context, "department" + mInforTrainfer.getDocumentID());
//                mDetailFmView.startIntent();
//            } else {
//                mDetailFmView.showError();
//            }
//            super.onPostExecute(s);
//        }
//    }
    //    class SaveToDrafts extends AsyncTask<String, Integer, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            JSONObject mJsonObject = new JSONObject();
//            try {
//                mJsonObject.put(MODULE, MODULE_PROCESSING_WORKING);
//                mJsonObject.put(NEOTYPE, TYPE_SAVE_DRAFTS);
//                JSONObject mData = new JSONObject();
//                mData.put(JOB_ID, mInforTrainfer.getDocumentID());
//                mData.put(WORK_FLOW_TRAINSITION_ID, mContextMap.get(TAP_SAVE_DRAFTS));
//                mData.put(RESOURCECODEID, mInforTrainfer.getResourceCodeId());
//                mJsonObject.put(DATA, mData);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            return eventPostRequest(getModuleInfor(MODULE_PROCESSING_WORKING, context), mJsonObject.toString(), getUserid(), getPass());
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            boolean mResuilt = false;
//            try {
//                JSONObject mJsonObject = new JSONObject(s);
//                int code = mJsonObject.getInt(CODE);
//                mResuilt = code == 0 ? true : false;
//                String msg = "";
//                if (mResuilt) {
//                    msg = "Yêu cầu thực hiện thành công";
//                } else {
//                    msg = "Yêu cầu thực hiện thất bại";
//                }
//                mDetailFmView.DetleteRow(mResuilt);
//                mDetailFmView.closeProgress();
//                showDialogUpdateError(msg, context);
//
//            } catch (JSONException e) {
//                mDetailFmView.showError();
//                e.printStackTrace();
//            }
//            super.onPostExecute(s);
//        }
//    }
//private void refreshUI(String s, String CaseRequest) {
//    switch (CaseRequest) {
//        case LIST_CANEL:
//            if (!s.equals("Error!") && !s.equals(nULL_STRING)) {
//                writeToFile(s, context, "cancel_list" + mInforTrainfer.getDocumentID());
//                mDetailFmView.startIntent();
//            } else {
//                mDetailFmView.showError();
//            }
//            break;
//        case LIST_RETURN_PERSON:
//            if (!s.equals("Error!") && !s.equals(nULL_STRING)) {
//                writeToFile(s, context, "return_list" + mInforTrainfer.getDocumentID());
//                closeProgressDialog();
//                mDetailFmView.startIntent();
//            } else {
//                mDetailFmView.showError();
//            }
//            break;
//        case LIST_PERSON:
//            try {
//                JSONObject mJsonResuilt = new JSONObject(s);
//                String message = mJsonResuilt.getString(MESSAGE);
//                if (message.equals("success")) {
//                    mDetailFmView.inseartInputPersonDatabase(s);
//                }
//                mDetailFmView.startIntent();
//            } catch (JSONException e) {
//                mDetailFmView.closeProgress();
//                ShowErrorToast(context);
//                e.printStackTrace();
//            }
//            break;
//        case LIST_DEPARTMENT:
//            if (!s.equals("Error!") && !s.equals(nULL_STRING)) {
//                writeToFile(s, context, "department" + mInforTrainfer.getDocumentID());
//                mDetailFmView.startIntent();
//            } else {
//                mDetailFmView.showError();
//            }
//            break;
//        case ACTION_SAVE_DRAIF:
//            boolean mResuilt;
//            try {
//                JSONObject mJsonObject = new JSONObject(s);
//                int code = mJsonObject.getInt(CODE);
//                mResuilt = code == 0 ? true : false;
//                String msg = "";
//                if (mResuilt) {
//                    msg = "Yêu cầu thực hiện thành công";
//                } else {
//                    msg = "Yêu cầu thực hiện thất bại";
//                }
//                mDetailFmView.DetleteRow(mResuilt);
//                mDetailFmView.closeProgress();
//                showDialogUpdateError(msg, context);
//            } catch (JSONException e) {
//                mDetailFmView.showError();
//                e.printStackTrace();
//            }
//            break;
//    }
//}
}
