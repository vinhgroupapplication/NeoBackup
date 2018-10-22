package com.newsaigonsoft.neoegov.Fragment.Detail;

import android.app.DownloadManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.newsaigonsoft.neoegov.Action.AddTrainfer.AddTrainferActivity;
import com.newsaigonsoft.neoegov.Action.Cancel.CancelActivity;
import com.newsaigonsoft.neoegov.Action.ChangeHandle.InputChangeHandleActivity;
import com.newsaigonsoft.neoegov.Action.ConfirmConpleted.ConfirmCompletedActivity;
import com.newsaigonsoft.neoegov.Action.Extend.ExtendActivity;
import com.newsaigonsoft.neoegov.Action.FeedBack.FeedBackActivity;
import com.newsaigonsoft.neoegov.Action.ForwardDepartment.InputForwardDepartmentActivity;
import com.newsaigonsoft.neoegov.Action.InputForward.InputForwardActivity;
import com.newsaigonsoft.neoegov.Action.Remind.RemindActivity;
import com.newsaigonsoft.neoegov.Action.Return.ReturnActivity;
import com.newsaigonsoft.neoegov.Adapter.DetailRowAdapter;
import com.newsaigonsoft.neoegov.Adapter.FileAttachAdapter;
import com.newsaigonsoft.neoegov.DetailActivity.DetailForwardActivity;
import com.newsaigonsoft.neoegov.OfficalActivity.OfficalActivity;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.SQLite.SQLite;
import com.newsaigonsoft.neoegov.Subjects.ContextMenuForwardRow;
import com.newsaigonsoft.neoegov.Subjects.DetailsRows;
import com.newsaigonsoft.neoegov.Subjects.KeyManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

import static com.newsaigonsoft.neoegov.R.id.other_button;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.EXECUTION_UNIT;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.STATISTIC_TYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.WORK_FLOW_TRAINSITION_ID;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DEPARTMENT_POSITION;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DIALOG_CENTER;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DOCUMENTID;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DOCUMENT_NUMBER;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.INSERT_OR_UPDATE_INTO_INPUT_PERSON;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.KEYURLNA;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LIST_DOCUMENT_SQLITE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LOOKUP_SCREEN;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.PROCESSPERSON;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.PROCESS_POSITION;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.REPORT_OR_FORWARD_OR_RELEASE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.REQUEST_STATIS_CODE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.RESOURCECODEID;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAG;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_14;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_ADD_TRAINFER;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_CANCEL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_CONFIRM_COMPLETED;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_EXTEND;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_FEED_BACK;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_FORWARD_DEPARTMENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_FORWARD_PERSON;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_FORWARD_RELEASE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_HANDLE_CHANGE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_REMIND_TASK;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_REPORT_RESUILT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_RETURN;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAP_SAVE_DRAFTS;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TITLE_ACTION_BAR;

/**
 * Created by VinhCN on 4/25/2017.
 */

public class DetailFragment extends DetailFmBase implements DetailFmView {

    @BindView(R.id.list_view)
    ListView lvDetails;
    @BindView(R.id.layout_button_forward)
    LinearLayout mLayoutButtonForward;
    @BindView(other_button)
    Button btnOther;
    @BindView(R.id.layout_list)
    LinearLayout lnList;


    public static DetailFragment newInstance(String TitleActionbar) {
        DetailFragment myFragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString("Title", TitleActionbar);
        myFragment.setArguments(args);
        return myFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        title = getArguments().getString("Title");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        mDetailForwardActivity = (DetailForwardActivity) getActivity();
        ButterKnife.bind(this, view);
        initView(view);
        mDetailFmLogic = new DetailFmLogic(this, mDetailForwardActivity, mDetailForwardActivity.getmInforTrainfer());
        mDetailFmHeader.lvFileAttach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //keep this code to read base 64
                mDetailForwardActivity.checkRunTimePermission(position, mDetailForwardActivity.getArrAtachFile(), mDetailForwardActivity);
//                String url = mDetailForwardActivity.mDetailLogic.getServerLink() + mDetailForwardActivity.getArrAtachFile().get(position).getFileURL();
//                Log.d(KeyManager.TAG, url);
//                Intent i = new Intent(Intent.ACTION_VIEW);
//                i.setData(Uri.parse(url));
//                startActivity(i);



            }
        });
        return view;
    }

    private void initView(View view) {
        header = mDetailForwardActivity.getLayoutInflater().inflate(R.layout.fragment_detail_header, null);
        lvDetails.addFooterView(header);
        mDetailFmHeader = new DetailFmHeader(header, mDetailForwardActivity);
    }


    public void setAdapterListFileAttach(FileAttachAdapter attachAdapter) {
        if (attachAdapter != null) {
            mDetailFmHeader.lvFileAttach.setAdapter(attachAdapter);
        }
        mDetailForwardActivity.setListViewHeightBasedOnChildren(mDetailFmHeader.lvFileAttach);
    }


    public void setAdapterListDetais(List<DetailsRows> arrDetails) {
        arrDetail_1 = new ArrayList<>();
        arrDetail_Full = arrDetails;
        for (int i = 0; i < 5; i++) {
            arrDetail_1.add(arrDetails.get(i));
        }
        detailAdapter = new DetailRowAdapter(mDetailForwardActivity, arrDetail_1);
        lvDetails.setAdapter(detailAdapter);
        closeProgress();
    }

    @Override
    public void closeProgress() {
        mDetailForwardActivity.closeProgressDialog();
    }

    @Override
    public void ToastError(String s) {
        Log.d(TAG, s);
        mDetailForwardActivity.ShowErrorToast(mDetailForwardActivity);
    }

    public void showMoreDetail() {
        if (mDetailFmHeader.tvShowMore.getText().toString().equals("Xem thêm")) {
            for (int i = 5; i < arrDetail_Full.size(); i++) {
                arrDetail_1.add(arrDetail_Full.get(i));
            }
            mDetailFmHeader.tvShowMore.setText("Thu gọn");
        } else {
            arrDetail_1.clear();
            for (int i = 0; i < 5; i++) {
                arrDetail_1.add(arrDetail_Full.get(i));
            }
            mDetailFmHeader.tvShowMore.setText("Xem thêm");
        }
        detailAdapter.notifyDataSetChanged();
    }

    //    class SaveToDrafts extends AsyncTask<String, Integer, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            JSONObject mJsonObject = new JSONObject();
//            try {
//                mJsonObject.put(MODULE, PROCESSING_WORKING);
//                mJsonObject.put(NEOTYPE, TYPE_SAVE_DRAFTS);
//                JSONObject mData = new JSONObject();
//                mData.put(JOB_ID, mDetailForwardActivity.getmInforTrainfer().getDocumentID());
//                mData.put(WORK_FLOW_TRAINSITION_ID, ContextMap.get(2));
//                mData.put(RESOURCECODEID, mDetailForwardActivity.getmInforTrainfer().getResourceCodeId());
//                mJsonObject.put(DATA, mData);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
////            return mDetailForwardActivity.makePostRequest(params[0], mJsonObject.toString(), mDetailForwardActivity.getUserid(), mDetailForwardActivity.getPass());
//            return mDetailForwardActivity.eventPostRequest(mDetailForwardActivity.getModuleInfor(PROCESSING_WORKING, mDetailForwardActivity), mJsonObject.toString(), mDetailForwardActivity.getUserid(), mDetailForwardActivity.getPass());
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            try {
//                JSONObject mJsonObject = new JSONObject(s);
//                boolean mResuilt = mJsonObject.getBoolean(DATA);
//                String msg = "";
//                if (mResuilt) {
//                    msg = "Yêu cầu thực hiện thành công";
//                    mSqLiteListDocument.QueryData("DELETE FROM '" + "M" + mDetailForwardActivity.getOnlyNumerics(OfficalActivity.urlNA) + "' WHERE documentID = '" + mDetailForwardActivity.getmInforTrainfer().getDocumentID() + "'");
//                } else {
//                    msg = "Yêu cầu thực hiện thất bại";
//                }
//                mDetailForwardActivity.closeProgressDialog();
//                mDetailForwardActivity.showDialogUpdateError(msg, mDetailForwardActivity);
//            } catch (JSONException e) {
//                mDetailForwardActivity.ShowErrorToast(getActivity());
//                e.printStackTrace();
//
//            }
//
//            super.onPostExecute(s);
//        }
//    }
//    public void setVisibilitiesButtonForward(String Resuilt) {
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
//        if (!mJsonArray.toString().equals(DONT_SHOW_BUTTON_FORWARD)) {
//            mLayoutButtonForward.setVisibility(View.VISIBLE);
//        } else {
//            mLayoutButtonForward.setVisibility(View.GONE);
//        }
//    }
//    public void setAdapterListFileAttach(FileAttachAdapter adapter) {
//        lvFileAttach.setAdapter(adapter);
//        mDetailForwardActivity.setListViewHeightBasedOnChildren(lvFileAttach);
//    }
//    public void setAdapterListDetais(DetailRowAdapter adapter) {
//        lvDetails_1.setAdapter(adapter);
//        mDetailForwardActivity.closeProgressDialog();
//    }
//    public void changeListView(boolean CheckUI) {
//        mDetailForwardActivity.showProgressDialog(nULL_STRING, this, DIALOG_CENTER);
//        Log.d(TAG, x);
//        if (mDetailForwardActivity.isNetworkAvailable(getActivity())) {
//            mDetailForwardActivity.runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    new ReadJsonDetail().execute(mDetailForwardActivity.getLink() + URL_CENTER_6_1);
//                }
//            });
//        } else {
//            upDateViewDetail(readFromFile(DetailForwardActivity.this, mInforTrainfer.getDocumentID()));
//        }
//
//    }

//    class ReadJsonDepartment extends AsyncTask<String, Integer, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            JSONObject mJsonObject = new JSONObject();
//            try {
//                mJsonObject.put(MODULE, PROCESSING_WORKING);
//                mJsonObject.put(NEOTYPE, TYPE_DEPARTMENT_LIST_RECEIVE);
//                JSONObject mData = new JSONObject();
//                mData.put(JOB_ID, mDetailForwardActivity.getmInforTrainfer().getDocumentID());
//                mData.put(WORK_FLOW_TRAINSITION_ID, mDetailForwardActivity.getmInforTrainfer().getForwardDepartmentID());
//                mJsonObject.put(DATA, mData.toString());
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
////            return mDetailForwardActivity.makePostRequest(params[0], mJsonObject.toString(), mDetailForwardActivity.getUserid(), mDetailForwardActivity.getPass());
//            return mDetailForwardActivity.eventPostRequest(mDetailForwardActivity.getModuleInfor(PROCESSING_WORKING, mDetailForwardActivity), mJsonObject.toString(), mDetailForwardActivity.getUserid(), mDetailForwardActivity.getPass());
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            if (!s.equals("Error!") && !s.equals(nULL_STRING)) {
//                mDetailForwardActivity.writeToFile(s, mDetailForwardActivity, "department" + mDetailForwardActivity.getmInforTrainfer().getDocumentID());
//                mDetailForwardActivity.closeProgressDialog();
//                startActivity(intent);
//            } else {
//                mDetailForwardActivity.closeProgressDialog();
//                mDetailForwardActivity.ShowErrorToast(getActivity());
//            }
//            super.onPostExecute(s);
//        }
//    }
//    class ReadJsonCancelList extends AsyncTask<String, Integer, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            JSONObject mJsonObject = new JSONObject();
//            try {
//                mJsonObject.put(MODULE, PROCESSING_WORKING);
//                mJsonObject.put(NEOTYPE, TYPE_CANCEL_LIST);
//                JSONObject mData = new JSONObject();
//                mData.put(JOB_ID, mDetailForwardActivity.getmInforTrainfer().getDocumentID());
//                mData.put(WORK_FLOW_TRAINSITION_ID, mDetailForwardActivity.getmInforTrainfer().getWorkFlowStationCancel());
//                mJsonObject.put(DATA, mData);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
////            return mDetailForwardActivity.makePostRequest(params[0], mJsonObject.toString(), mDetailForwardActivity.getUserid(), mDetailForwardActivity.getPass());
//            return mDetailForwardActivity.eventPostRequest(mDetailForwardActivity.getModuleInfor(PROCESSING_WORKING, mDetailForwardActivity), mJsonObject.toString(), mDetailForwardActivity.getUserid(), mDetailForwardActivity.getPass());
//
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            if (!s.equals("Error!") && !s.equals(nULL_STRING)) {
//                mDetailForwardActivity.writeToFile(s, mDetailForwardActivity, "cancel_list" + mDetailForwardActivity.getmInforTrainfer().getDocumentID());
//                mDetailForwardActivity.closeProgressDialog();
//                startActivity(intent);
//            } else {
//                mDetailForwardActivity.closeProgressDialog();
//                mDetailForwardActivity.ShowErrorToast(getActivity());
//            }
//
//            super.onPostExecute(s);
//        }
//    }
//    class ReadJsonPerSonReturn extends AsyncTask<String, Integer, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            JSONObject mJsonObject = new JSONObject();
//            try {
//                mJsonObject.put(MODULE, PROCESSING_WORKING);
//                mJsonObject.put(NEOTYPE, TYPE_INPUT_PERSON);
//                JSONObject mData = new JSONObject();
//                mData.put(JOB_ID, mDetailForwardActivity.getmInforTrainfer().getDocumentID());
//                mData.put(WORK_FLOW_TRAINSITION_ID, mDetailForwardActivity.getmInforTrainfer().getWorkFlowStationReturn());
//                mJsonObject.put(DATA, mData);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
////            return mDetailForwardActivity.makePostRequest(params[0], mJsonObject.toString(), mDetailForwardActivity.getUserid(), mDetailForwardActivity.getPass());
//            return mDetailForwardActivity.eventPostRequest(mDetailForwardActivity.getModuleInfor(PROCESSING_WORKING, mDetailForwardActivity), mJsonObject.toString(), mDetailForwardActivity.getUserid(), mDetailForwardActivity.getPass());
//
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            if (!s.equals("Error!") && !s.equals(nULL_STRING)) {
//                mDetailForwardActivity.writeToFile(s, mDetailForwardActivity, "return_list" + mDetailForwardActivity.getmInforTrainfer().getDocumentID());
//                mDetailForwardActivity.closeProgressDialog();
//                startActivity(intent);
//            } else {
//                mDetailForwardActivity.closeProgressDialog();
//                mDetailForwardActivity.ShowErrorToast(getActivity());
//            }
//            super.onPostExecute(s);
//        }
//    }
    //    public void showButtonMoreDetails(boolean isShow) {
//        if (isShow) {
//            btnShowMoreDetails.setVisibility(View.VISIBLE);
//        } else {
//            btnShowMoreDetails.setVisibility(View.GONE);
//        }
//
//    }
//    public void loginWebView(String url) {
//        mWebView.loadUrl(url);
//
//    }
    //    private void ReferenceView(View view) {
//        mWebView = (WebView) view.findViewById(R.id.web_view_login_for_pdf);
//        lvFileAttach = (NonScrollListView) view.findViewById(R.id.list_view_attach_file);
//        lvDetails_1 = (NonScrollListView) view.findViewById(R.id.list_view_details_1);
//        lvDetails_2 = (NonScrollListView) view.findViewById(R.id.list_view_details_2);
//        btnOther = (Button) view.findViewById(other_button);
//        btnAssignmentForward = (Button) view.findViewById(R.id.assignment_forward);
//        mLayoutButtonForward = (LinearLayout) view.findViewById(R.id.layout_button_forward);
//        layoutTryAgain = (RelativeLayout) view.findViewById(R.id.layout_try_again);
//        btnShowMoreDetails = (TextView) view.findViewById(btn_show_more_details);
//
//
//        btnShowMoreDetails.setOnClickListener(this);
//        lvDetails_2.setVisibility(View.GONE);
//        showButtonMoreDetails(false);
//    }
}
