package com.newsaigonsoft.neoegov.AVolleyManager;

/**
 * Created by Vinh on 3/13/2018.
 */

public interface VolleyTaskCompletedListenner<ResuiltObject> {
    void onVolleyCompleted(String s, String CaseRequest);
    void onVolleyError(String s);
}
