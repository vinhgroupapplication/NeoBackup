package com.newsaigonsoft.neoegov.NotifyAcitivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;

import com.newsaigonsoft.neoegov.GsonObject.GsonNotify;
import com.newsaigonsoft.neoegov.SQLite.SQLite;
import com.newsaigonsoft.neoegov.Subjects.SumManager;

import java.util.ArrayList;
import java.util.List;

import me.leolin.shortcutbadger.ShortcutBadger;

import static com.newsaigonsoft.neoegov.Subjects.KeyManager.CREATE_TABLE_NOTIFY_SQL;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DOCUMENTID;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.IS_SCREEN;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.NOTIFY_SCREEN;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.NOTIFY_SQL;

public class NotifyLogic extends NotifyBase implements NotifyImp {
    Context context;
    NotifyView notifyView;

    public NotifyLogic(Context context, NotifyView notifyView) {
        this.context = context;
        this.notifyView = notifyView;
    }

    @Override
    public void getListNotify(SQLite mSqLiteNotify) {
        List<GsonNotify> arr = new ArrayList<>();
        mSqLiteNotify.QueryData(CREATE_TABLE_NOTIFY_SQL);
        Cursor cursor = mSqLiteNotify.GetData("SELECT * FROM " + NOTIFY_SQL);
        while (cursor.moveToNext()) {
            String sender = cursor.getString(1);
            String body = cursor.getString(2);
            String title = cursor.getString(3);
            int objectId = cursor.getInt(4);
            String sound = cursor.getString(5);
            long dateSent = cursor.getLong(6);
            String labelCode = cursor.getString(7);
            String content = cursor.getString(8);
            arr.add(new GsonNotify(sender, body, title, objectId, sound, dateSent, 1, labelCode, content));
        }
        notifyView.setListNotify(arr);
        arrayListNotify = arr;

    }



    @Override
    public void openDetails(Intent intent, int position) {
        long ObjectID  = arrayListNotify.get(position).getObjectId();
        intent.putExtra(IS_SCREEN, NOTIFY_SCREEN);
        intent.putExtra(DOCUMENTID, ObjectID);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }
}
