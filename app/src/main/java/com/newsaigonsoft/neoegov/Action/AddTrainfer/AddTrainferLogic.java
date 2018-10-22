package com.newsaigonsoft.neoegov.Action.AddTrainfer;

import android.content.Context;
import android.widget.Toast;

import com.newsaigonsoft.neoegov.AVolleyManager.VolleyTask;
import com.newsaigonsoft.neoegov.AVolleyManager.VolleyTaskCompletedListenner;
import com.newsaigonsoft.neoegov.Adapter.AddTrainferAdapter;
import com.newsaigonsoft.neoegov.Adapter.TrainferSelectAdapter;
import com.newsaigonsoft.neoegov.AsynTaskManager.AsyncTaskCompleteListener;
import com.newsaigonsoft.neoegov.GsonObject.GsonResuilt;
import com.newsaigonsoft.neoegov.Subjects.AddTrainferRow;
import com.newsaigonsoft.neoegov.Subjects.AttachFile;
import com.newsaigonsoft.neoegov.Subjects.ResuiltObject;
import com.newsaigonsoft.neoegov.Subjects.SumManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.ATTACH_FILE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DATA;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.EXCHANGE_TASK_INFO_ID;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.EXTENSION;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MODULE_LOOKUP_DOCUMENT;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MODULE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.NAME;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.NEOTYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.SCHEDULE_CONTENT;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.SEND_DATA;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TASK_ID;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TYPE_ADD_TRANFER;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.ACTION_ADD_TRAINFER;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DIALOG_CENTER;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.FULLNAME_USER;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.nULL_STRING;

/**
 * Created by Vinh on 11/14/2017.
 */

public class AddTrainferLogic extends SumManager implements AsyncTaskCompleteListener<ResuiltObject>, VolleyTaskCompletedListenner<ResuiltObject> {

    AddTrainferView mAddTrainferView;
    Context context;
    JSONArray mJsonArrayAttach;
    boolean sendData = false;
    SimpleDateFormat mSimpleDateFormat;
    List<AddTrainferRow> arrTrainfer;
    ArrayList<AttachFile> arrFileAttachLogic;
    AddTrainferAdapter adapter;

    public AddTrainferLogic(AddTrainferView mAddTrainferView, Context context) {
        this.mAddTrainferView = mAddTrainferView;
        this.context = context;
        getInforAccountFromShareReferenced(context);
        mSimpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        arrTrainfer = new ArrayList<>();
    }

    public void setListSelectTrainfer(List<TrainferSelectAdapter.TrainferItems> listSelectTranferx) {
        listSelectTranferx = new ArrayList<>();
        listSelectTranferx.add(new TrainferSelectAdapter.TrainferItems("Trao đổi", true));
        listSelectTranferx.add(new TrainferSelectAdapter.TrainferItems("Đôn đốc", false));
//        listSelectTranferx.add(new TrainferSelectAdapter.TrainferItems("Thảo luận", false));
//        listSelectTranferx.add(new TrainferSelectAdapter.TrainferItems("Nhắc nhở", false));
//        listSelectTranferx.add(new TrainferSelectAdapter.TrainferItems("Khiển trách", false));
        mAddTrainferView.setFirstItem(listSelectTranferx);
    }

    public void requestAddTrainfer(boolean b, int length, ArrayList<AttachFile> arrFileAttach) {
        mJsonArrayAttach = new JSONArray();
        for (int i = 0; i < arrFileAttach.size(); i++) {
            JSONObject mJsonObject = new JSONObject();
            try {
                mJsonObject.put(NAME, arrFileAttach.get(i).getFileOnlyName());
                mJsonObject.put(EXTENSION, arrFileAttach.get(i).getFileTyPe());
                mJsonObject.put(SCHEDULE_CONTENT, arrFileAttach.get(i).getBase64Code().replace("\n", ""));
                mJsonArrayAttach.put(mJsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (length > 0) {
            mAddTrainferView.showProgress(nULL_STRING, this, DIALOG_CENTER, true);
//            new NeoTask(context, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new CaseManager(context, ACTION_ADD_TRAINFER, addJsonRequestAddTrainfer().toString(), getLink() + URL_CENTER_6_1));
//            new AddTranfer().execute(getLink() + URL_CENTER_6_1);
            new VolleyTask(context, ACTION_ADD_TRAINFER, addJsonRequestAddTrainfer(), this);
        } else {
            Toast.makeText(context, "Vui lòng nhập nội dung cần trao đổi", Toast.LENGTH_SHORT).show();
        }
        arrFileAttachLogic = arrFileAttach;
        sendData = b;
    }


    public void refreshUI(String s, String CaseRequest) {
        switch (CaseRequest) {
            case ACTION_ADD_TRAINFER:
                GsonResuilt mGsonResuilt = getGson().fromJson(s, GsonResuilt.class);
                switch (mGsonResuilt.getCode()) {
                    case 0:
                        String content = mAddTrainferView.getContent();
                        Calendar mCalendar = Calendar.getInstance();
                        String timeNow = mSimpleDateFormat.format(mCalendar.getTime());
                        if (!content.equals(nULL_STRING)) {
                            arrTrainfer.add(new AddTrainferRow(getDefaults(FULLNAME_USER, context), "[" + timeNow + "]", content, arrFileAttachLogic));
                            if (arrTrainfer.size() > 1) {
                                adapter.notifyDataSetChanged();
                            } else {
                                adapter = new AddTrainferAdapter(context, arrTrainfer);
                                mAddTrainferView.setListResuiltTrainfer(adapter);
                            }
                        }
                        break;
                    default:
                        Toast.makeText(context, "Yêu cầu xử lý không thành công", Toast.LENGTH_SHORT).show();
                        break;
                }
                mAddTrainferView.closeProgress();
                break;
        }
    }


    @Override
    public void onTaskCompleted(String s, String CaseRequest) {
        refreshUI(s, CaseRequest);
    }

    private JSONObject addJsonRequestAddTrainfer() {
        JSONObject mJsonObject = new JSONObject();
        try {
            mJsonObject.put(MODULE, MODULE_LOOKUP_DOCUMENT);
            mJsonObject.put(NEOTYPE, TYPE_ADD_TRANFER);
            JSONObject mData = new JSONObject();
//                mData.put(TASK_ID, TaskID);
            mData.put(TASK_ID, mAddTrainferView.getTaskID());
            mData.put(EXCHANGE_TASK_INFO_ID, 0);
//                mData.put(SCHEDULE_CONTENT, edtContent.getText().toString());
            mData.put(SCHEDULE_CONTENT, mAddTrainferView.getContent());
//                mData.put(TYPE, spnSelect.getSelectedItemPosition() + 1);
            mData.put(TYPE, mAddTrainferView.getSelectItem());
            mData.put(SEND_DATA, sendData);
            mData.put(ATTACH_FILE, mJsonArrayAttach);
            mJsonObject.put(DATA, mData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mJsonObject;
    }

    @Override
    public void onVolleyCompleted(String s, String CaseRequest) {
        refreshUI(s, CaseRequest);
    }

    @Override
    public void onVolleyError(String s) {
        mAddTrainferView.ToastError(s);
    }

//    class AddTranfer extends AsyncTask<String, Integer, String> {
//
//        @Override
//        protected String doInBackground(String... params) {
//            JSONObject mJsonObject = new JSONObject();
//            try {
//                mJsonObject.put(MODULE, MODULE_LOOKUP_DOCUMENT);
//                mJsonObject.put(NEOTYPE, TYPE_ADD_TRANFER);
//                JSONObject mData = new JSONObject();
////                mData.put(TASK_ID, TaskID);
//                mData.put(TASK_ID, mAddTrainferView.getTaskID());
//                mData.put(EXCHANGE_TASK_INFO_ID, 0);
////                mData.put(SCHEDULE_CONTENT, edtContent.getText().toString());
//                mData.put(SCHEDULE_CONTENT, mAddTrainferView.getContent());
////                mData.put(TYPE, spnSelect.getSelectedItemPosition() + 1);
//                mData.put(TYPE, mAddTrainferView.getSelectItem());
//                mData.put(SEND_DATA, sendData);
//                mData.put(ATTACH_FILE, mJsonArrayAttach);
//                mJsonObject.put(DATA, mData);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
////            return makePostRequest(params[0], mJsonObject.toString(), getUserid(), getPass());
//            return eventPostRequest(getModuleInfor(MODULE_LOOKUP_DOCUMENT, context), mJsonObject.toString(), getUserid(), getPass());
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            try {
//                JSONObject mJsonObject = new JSONObject(s);
//                int mResuilt = mJsonObject.getInt(CODE);
//                if (mResuilt == 0) {
//                    String content = mAddTrainferView.getContent();
//                    Calendar mCalendar = Calendar.getInstance();
//                    String timeNow = mSimpleDateFormat.format(mCalendar.getTime());
//                    if (!content.equals("")) {
//                        arrTrainfer.add(new AddTrainferRow(getDefaults(FULLNAME_USER, context), "[" + timeNow + "]", content, arrFileAttachLogic));
//                        if (arrTrainfer.size() > 1) {
//                            adapter.notifyDataSetChanged();
//                        } else {
//                            adapter = new AddTrainferAdapter(context, arrTrainfer);
//                            mAddTrainferView.setListResuiltTrainfer(adapter);
//                        }
//                    }
//                } else {
//                    Toast.makeText(context, "Yêu cầu xử lý không thành công", Toast.LENGTH_SHORT).show();
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            mAddTrainferView.closeProgress();
//            super.onPostExecute(s);
//        }
//    }
//    public void refreshUI(String s, String CaseRequest) {
//        switch (CaseRequest) {
//            case ACTION_ADD_TRAINFER:
//                GsonResuilt mGsonResuilt = gson.fromJson(s, GsonResuilt.class);
//                try {
//                    JSONObject mJsonObject = new JSONObject(s);
//                    int mResuilt = mJsonObject.getInt(CODE);
//                    if (mResuilt == 0) {
//                        String content = mAddTrainferView.getContent();
//                        Calendar mCalendar = Calendar.getInstance();
//                        String timeNow = mSimpleDateFormat.format(mCalendar.getTime());
//                        if (!content.equals("")) {
//                            arrTrainfer.add(new AddTrainferRow(getDefaults(FULLNAME_USER, context), "[" + timeNow + "]", content, arrFileAttachLogic));
//                            if (arrTrainfer.size() > 1) {
//                                adapter.notifyDataSetChanged();
//                            } else {
//                                adapter = new AddTrainferAdapter(context, arrTrainfer);
//                                mAddTrainferView.setListResuiltTrainfer(adapter);
//                            }
//                        }
//                    } else {
//                        Toast.makeText(context, "Yêu cầu xử lý không thành công", Toast.LENGTH_SHORT).show();
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                mAddTrainferView.closeProgress();
//                break;
//        }
//    }

}
