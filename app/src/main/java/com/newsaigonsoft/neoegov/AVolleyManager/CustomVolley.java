package com.newsaigonsoft.neoegov.AVolleyManager;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vinh on 3/13/2018.
 */

public class CustomVolley extends StringRequest {

    Context mContext;
    InforRequest mInforRequest;

    public CustomVolley(Context context,InforRequest inforRequest, int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
        this.mContext = context;
        this.mInforRequest = inforRequest;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        Map<String, String> params = new HashMap<String, String>();
        params.put("param", mInforRequest.getRequest().toString());
        return params;
    }

    @Override
    public String getBodyContentType() {
        return "application/x-www-form-urlencoded;charset=utf-8";
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = new HashMap<>();
        String authorization = mInforRequest.getAccountID() + ":" + mInforRequest.getPassWord();
        String encodedAuth = "Basic " + mInforRequest.encodeString(authorization);
        headers.put("Authorization", encodedAuth);
        return headers;
    }

}
