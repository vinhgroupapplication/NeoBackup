package com.newsaigonsoft.neoegov.Service;

import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.SQLite.SQLite;
import com.newsaigonsoft.neoegov.Subjects.SumManager;

import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DATA;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.CREATE_TABLE_SENDWAITING_DEPARTMENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.CREATE_TABLE_SQLLITE_SEND_WAITING;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.FALSE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LINK;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.PASSWORD_USER;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SELECT_SENDWAITING;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SELECT_SENDWAITING_DEPARTMENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SEND_WAITING_DEPARTMENT_SQLITE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SEND_WAITING_DEPARTMENT_TABLE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.SEND_WAITING_SQLITE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAG;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.URL_CENTER_6_1;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.USERNAME;

public class SendDocumentService extends Service {

    // Send document in background
    SumManager sumManager = new SumManager();
    SQLite mSqLite = new SQLite(this, SEND_WAITING_SQLITE, null, 1);
    SQLite mSqLiteSendWaitingDepartment = new SQLite(this, SEND_WAITING_DEPARTMENT_SQLITE, null, 1);
    String DocumentID;
    String DocumentIDDepartment;
    Cursor mCursor, mCursorCountRow, mCursorSendDepartment;
    String UserId;
    String UserPass;
    String mHost;
    private static final String LINK_LAST_LINK = "%7D";

    public SumManager getSumManager() {
        return sumManager;
    }

    public SendDocumentService() {

    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
//        if (sumManager.isDeteleNotifyTable()) {
//            mSqLiteNotify.QueryData("DELETE FROM " + NOTIFY_SQL);
//            Log.e("ClearFromRecentService", "DETELE NOTIFY");
//            sumManager.setDeteleNotifyTable(false);
//        }
//        Log.e("ClearFromRecentService", "END");
        super.onTaskRemoved(rootIntent);
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        UserId = sumManager.getDefaults(USERNAME, this);
        UserPass = sumManager.getDefaults(PASSWORD_USER, this);
        mHost = sumManager.getDefaults(LINK, this);
//        String HttpOrHttps = MainActivity.getDefaults(REMEMBERHTTPORHTTPS, this);
        if (intent != null) {
            String TRAINFERDOCUMENT = intent.getStringExtra("TRAINFERDOCUMENT");
            if (TRAINFERDOCUMENT != null) {
                if (TRAINFERDOCUMENT.equals("TRAINFERDOCUMENTOK")) {
//                    Toast.makeText(this, "Mở kết nối", Toast.LENGTH_SHORT).show();
                    mSqLite.QueryData(CREATE_TABLE_SQLLITE_SEND_WAITING);
                    mCursor = mSqLite.GetData(SELECT_SENDWAITING);
//                    mCursorCountRow = mSqLite.GetData(SELECT_COUNT_SENDWAITING);
//                    mCursorCountRow.moveToFirst();
//                    int iCount = mCursorCountRow.getInt(0);
                    mCursor.moveToFirst();
                    int iCount = mCursor.getCount();
                    if (iCount > 0) {
                        DocumentID = mCursor.getInt(0) + "";
                        Log.d(TAG, "Gửi first");
//                        Toast.makeText(this, "Gửi : " + DocumentID + " " + mCursor.getString(5), Toast.LENGTH_SHORT).show();
                        new SendMessageWaiting().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mHost + URL_CENTER_6_1);
                    }
                    mSqLiteSendWaitingDepartment.QueryData(CREATE_TABLE_SENDWAITING_DEPARTMENT);
                    mCursorSendDepartment = mSqLiteSendWaitingDepartment.GetData(SELECT_SENDWAITING_DEPARTMENT);
                    mCursorSendDepartment.moveToFirst();
                    if (mCursorSendDepartment.getCount() > 0) {
                        DocumentIDDepartment = mCursorSendDepartment.getString(0);
                        Log.d(TAG, "Gửi Department first");
                        Toast.makeText(this, "Gửi : " + mCursorSendDepartment.getInt(0), Toast.LENGTH_SHORT).show();
                        new SendMessageWaitingDepartment().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mHost + URL_CENTER_6_1);
                    }

                }

            }
        }
        return START_STICKY;
    }

    class SendMessageWaitingDepartment extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            String mRequest = mCursorSendDepartment.getString(1);
            return sumManager.makePostRequest(params[0], mRequest, UserId, UserPass);
        }

        @Override
        protected void onPostExecute(String s) {
            String resuilt = "";
            JSONObject mJsonObject = null;
            try {
                mJsonObject = new JSONObject(s);
                resuilt = mJsonObject.getString(DATA);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            int countCursor = mCursorSendDepartment.getCount();
            int positioncursor = mCursorSendDepartment.getPosition();
            if (resuilt.contains(FALSE)) {
                Log.d(TAG, "Nhận");
                Toast.makeText(SendDocumentService.this, getString(R.string.chuyen_that_bai), Toast.LENGTH_SHORT).show();
                if (positioncursor >= (countCursor - 1)) {
                    return;
                }
                mCursorSendDepartment.moveToNext();
                Log.d(TAG, "position" + positioncursor + "totalSQLite" + countCursor);
            } else {
                Toast.makeText(SendDocumentService.this, getString(R.string.chuyen_thanh_cong), Toast.LENGTH_SHORT).show();
                mSqLiteSendWaitingDepartment.QueryData("DELETE FROM " + SEND_WAITING_DEPARTMENT_TABLE + " WHERE jobid = '" + DocumentIDDepartment + "'");
                if (positioncursor >= (countCursor - 1)) {
                    mCursorSendDepartment.close();
                    return;
                }
                mCursorSendDepartment.moveToNext();
            }
            Log.d("VinhCNLog: ", "Gửi Second");
            DocumentIDDepartment = mCursorSendDepartment.getInt(0) + "";
            Toast.makeText(SendDocumentService.this, "Chuyển: " + DocumentIDDepartment + "", Toast.LENGTH_SHORT).show();
            new SendMessageWaitingDepartment().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mHost + URL_CENTER_6_1);
            super.onPostExecute(s);
        }
    }


    class SendMessageWaiting extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... params) {
//            JSONObject mJsonObject = new JSONObject();
//            try {
//                mJsonObject.put(MODULE, MODULE_PROCESSING_WORKING);
//                mJsonObject.put(NEOTYPE, TYPE_TRAINFER);
//                JSONObject mJsonObjectData = new JSONObject();
//                mJsonObjectData.put(JOB_ID, mCursor.getInt(0));
//                mJsonObjectData.put(WORK_FLOW_TRAINSITION_ID, mCursor.getInt(2));
//                mJsonObjectData.put(RESOURCECODEID, mCursor.getInt(1));
//                mJsonObjectData.put(RECEIVER_ID, mCursor.getInt(3));
//                mJsonObjectData.put(RECEIVER_ORG_ID, mCursor.getInt(4));
//                mJsonObjectData.put(TRAINFER_EMAIL, Boolean.valueOf(mCursor.getString(6)));
//                mJsonObjectData.put(URGENT, Boolean.valueOf(mCursor.getString(7)));
//                mJsonObjectData.put(SUBRECIPIENTS, mCursor.getString(8).replace("#", "'"));
//                mJsonObject.put(DATA, mJsonObjectData.toString());
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            return sumManager.makePostRequest(params[0], mJsonObject.toString(), UserId, UserPass);
            return sumManager.makePostRequest(params[0], mCursor.getString(1), UserId, UserPass);
        }

        @Override
        protected void onPostExecute(String s) {
            String resuilt = "";
            JSONObject mJsonObject = null;
            try {
                mJsonObject = new JSONObject(s);
                resuilt = mJsonObject.getString(DATA);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            int countCursor = mCursor.getCount();
            int positioncursor = mCursor.getPosition();
            if (resuilt.contains(FALSE)) {
                Log.d(TAG, "Nhận");
                Toast.makeText(SendDocumentService.this, getString(R.string.chuyen_that_bai), Toast.LENGTH_SHORT).show();
                if (positioncursor >= (countCursor - 1)) {
                    return;
                }
                mCursor.moveToNext();
                Log.d(TAG, "position" + positioncursor + "totalSQLite" + countCursor);
            } else {
                Toast.makeText(SendDocumentService.this, getString(R.string.chuyen_thanh_cong), Toast.LENGTH_SHORT).show();
                mSqLite.QueryData("DELETE FROM sendwaiting WHERE documentID = '" + DocumentID + "'");
                if (positioncursor >= (countCursor - 1)) {
                    mCursor.close();
                    return;
                }
                mCursor.moveToNext();
            }
            Log.d("VinhCNLog: ", "Gửi Second");
            DocumentID = mCursor.getInt(0) + "";
            Toast.makeText(SendDocumentService.this, "Chuyển: " + DocumentID + "", Toast.LENGTH_SHORT).show();
            new SendMessageWaiting().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mHost + URL_CENTER_6_1);
            super.onPostExecute(s);
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}

