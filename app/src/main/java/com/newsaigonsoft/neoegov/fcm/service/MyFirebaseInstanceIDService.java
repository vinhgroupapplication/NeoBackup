package com.newsaigonsoft.neoegov.fcm.service;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import com.newsaigonsoft.neoegov.Subjects.KeyManager;
import com.newsaigonsoft.neoegov.Subjects.SumManager;

import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TOKEN_ID;

/**
 * Created by VinhCN on 4/17/2017.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    KeyManager manager = new KeyManager();
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        // TODO: Implement this method to send any registration to your app's servers.
        System.out.println( manager.TAG + "Registration.onTokenRefresh TOKEN: " + refreshedToken );
        SumManager.setDefaults(TOKEN_ID, refreshedToken, this);
    }
}
