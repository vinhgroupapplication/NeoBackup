package com.newsaigonsoft.neoegov.fcm.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import me.leolin.shortcutbadger.ShortcutBadger;

import com.newsaigonsoft.neoegov.GsonObject.GsonNotify;
import com.newsaigonsoft.neoegov.OfficalActivity.OfficalActivity;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.SQLite.SQLite;
import com.newsaigonsoft.neoegov.Subjects.SumManager;

import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.BODY;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.MESSAGE;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.NOTIFY_OBJECT_ID;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.PAYLOAD;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.TITLE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.BROADCASTLISTENNER;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.CREATE_TABLE_NOTIFY_SQL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.NOTIFICATION_UP_DATE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.NOTIFY_SQL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.NUMBER_NOTIFY;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.RELOADSLIDER;
import static com.newsaigonsoft.neoegov.Subjects.SumManager.getDefaults;
import static com.newsaigonsoft.neoegov.Subjects.SumManager.setDefaults;

/**
 * Created by VinhCN on 4/17/2017.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    SQLite mSqLiteNotify = new SQLite(this, NOTIFY_SQL, null, 1);
    SumManager manager  = new SumManager();
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
//        String mMessage = "";
//        String mTitle = "";
//        String mBody = "";
//        String mBadge = "";
//        String mSound = "";
        JSONObject mJsonObject = new JSONObject(remoteMessage.getData());
        try {
            String mJsonPayLoad = mJsonObject.getString(PAYLOAD);
            GsonNotify mGsonNotify = manager.getGson().fromJson(mJsonPayLoad, GsonNotify.class);
            //stub for test
//            long objectId = 268131;
            mSqLiteNotify.QueryData(CREATE_TABLE_NOTIFY_SQL);
            mSqLiteNotify.QueryData("INSERT INTO " + NOTIFY_SQL + " VALUES(null, '" + mGsonNotify.getSender() + "', '" + mGsonNotify.getBody() + "', '" + mGsonNotify.getTitle() + "', '" + mGsonNotify.getObjectId() + "', '" + mGsonNotify.getSound() + "', '" + mGsonNotify.getDateSent() + "', '" + mGsonNotify.getLabelCode() + "','" + mGsonNotify.getContent() + "')");
//            arrayListNotify.add(new NotifyRow(mTitle, mBody, false));
            // this 2 line for notification later. it always true. do not remove it
//            mBadge = mJsonObjectNotification.getString(BADGE);
//            mSound = mJsonObjectNotification.getString(SOUND);
//            mSound = "";
            Log.d("VinhCNLog: ", mJsonPayLoad + " " + mGsonNotify.getTitle() + " " + mGsonNotify.getBody() + " " + "" + " " + mGsonNotify.getSound() + " " + mGsonNotify.getObjectId());
            this.sendNotification(new NotificationData("", 1, mGsonNotify.getTitle(), mGsonNotify.getBody(), mGsonNotify.getSound()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        try {
//            mMessage = mJsonObject.getString(MESSAGE);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        if (mJsonObject != null) {
//
//            Handler handler = new Handler(Looper.getMainLooper());
//            final String finalMMessage = mMessage;
//            handler.post(new Runnable() {
//                @Override
//                public void run() {
//                    Toast.makeText(getApplicationContext(), "Notification coming", Toast.LENGTH_SHORT).show();
//                }
//            });

//        Random random = new Random();
//        int m = random.nextInt(9999 - 1000) + 1000;

//        }

    }

    private void sendNotification(NotificationData notificationData) {
//        MainActivity.mRefreshLogin = false;
        Intent intent = new Intent(this, OfficalActivity.class);
        intent.putExtra("buzz", "buzz");
//        Cursor cursor = mSqLiteNotify.GetData("SELECT * FROM " + NOTIFY_SQL);
//        int numberNotify =  cursor.getCount();
        int NumberNotify = 0;
        String StrNumberNotify = getDefaults(NUMBER_NOTIFY, this);
        if (StrNumberNotify == null) {
            StrNumberNotify = "1";
            NumberNotify = Integer.parseInt(StrNumberNotify);
        } else {
            NumberNotify = Integer.parseInt(StrNumberNotify);
            NumberNotify++;
        }
        setDefaults(NUMBER_NOTIFY, String.valueOf(NumberNotify), getApplicationContext());
        ShortcutBadger.applyCount(this, NumberNotify);
        intent.putExtra(NotificationData.TEXT, notificationData.getTextMessage());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder notificationBuilder = null;
        try {
            notificationBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.icon)
                    .setContentTitle(URLDecoder.decode(notificationData.getTitle(), "UTF-8"))
                    .setContentText(URLDecoder.decode(notificationData.getTextMessage(), "UTF-8"))
                    .setAutoCancel(true)
                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                    .setContentIntent(pendingIntent);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if (notificationBuilder != null) {
            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(notificationData.getId(), notificationBuilder.build());
        } else {
            Toast.makeText(this, "No nofity here", Toast.LENGTH_SHORT).show();
        }
        Intent i = new Intent();
        i.putExtra(RELOADSLIDER, NOTIFICATION_UP_DATE);
        i.setAction(BROADCASTLISTENNER);
        sendBroadcast(i);
    }
}
