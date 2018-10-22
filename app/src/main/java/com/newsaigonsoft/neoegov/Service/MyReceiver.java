package com.newsaigonsoft.neoegov.Service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import static com.newsaigonsoft.neoegov.Subjects.KeyManager.BROADCASTLISTENNER;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.RELOADSLIDER;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.RELOADSLIDERNONOK;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.RELOADSLIDEROK;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TRAINFERDOCUMENT;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TRAINFERDOCUMENTOK;

/**
 * Created by Vinh on 5/9/2017. Connection listener
 */

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobile = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo active = manager.getActiveNetworkInfo();
        Intent intentSend = new Intent();
        intentSend.setAction(BROADCASTLISTENNER);
        if (active == null) { //no internet
            intentSend.putExtra(RELOADSLIDER, RELOADSLIDERNONOK);
        }
        if (mobile.isConnected() || wifi.isConnected()) { //mobile connetcion
            intentSend.putExtra(RELOADSLIDER, RELOADSLIDEROK);
            Intent serviceIntent = new Intent(context,SendDocumentService.class);
            serviceIntent.putExtra(TRAINFERDOCUMENT, TRAINFERDOCUMENTOK);
            context.startService(serviceIntent);
        }
        context.sendBroadcast(intentSend);
    }
}
