package com.newsaigonsoft.neoegov.Fragment.WebViewFragment;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import butterknife.ButterKnife;
import com.newsaigonsoft.neoegov.OfficalActivity.OfficalActivity;
import com.newsaigonsoft.neoegov.R;

import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DEVICE_MOBILE_TRUE;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.DIALOG_CENTER;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.LINK;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.TAG;
import static com.newsaigonsoft.neoegov.Subjects.KeyManager.nULL_STRING;

/**
 * Created by VinhCN on 4/24/2017.
 */

public class WebViewFragment extends Fragment {
    boolean mCheckShowDialog = false;
    public WebView mWebView;
    OfficalActivity mOfficalActivity;

    public static WebViewFragment newInstance() {
        WebViewFragment myFragment = new WebViewFragment();
        return myFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_webview, container, false);
        mOfficalActivity = (OfficalActivity) getActivity();
        ButterKnife.bind(this, view);
        mWebView = (WebView) view.findViewById(R.id.web_view);
        String mAddress = getArguments().getString(LINK);
        LoadingWebview(mAddress);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem itemSearchButton = menu.findItem(R.id.search_button);
        MenuItem itemCenterMenu = menu.findItem(R.id.center_menu);
        itemSearchButton.setVisible(false);
        itemCenterMenu.setVisible(false);
        super.onPrepareOptionsMenu(menu);
    }

    // Loading webview add start
    public void LoadingWebview(final String link) {
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(getActivity(), description, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                if (!((Activity) getActivity()).isFinishing()) {
                        if (!mCheckShowDialog){
                           mOfficalActivity.showProgressDialog(nULL_STRING, getActivity(),DIALOG_CENTER, true);
                            mCheckShowDialog = true;
                        }
//                }


            }

            @Override
            public void onPageFinished(WebView view, String url) {
                mOfficalActivity.closeProgressDialog();
                String webUrl = mWebView.getUrl();

            }
        });
        String rightLink = link + DEVICE_MOBILE_TRUE;
        mWebView.loadUrl(rightLink);
        Log.d(TAG, rightLink);

    }

    // Loading webview add end
}
