package com.newsaigonsoft.neoegov;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;

import com.newsaigonsoft.neoegov.Fragment.Home.HomeFragment;
import com.newsaigonsoft.neoegov.Subjects.SumManager;

public class TestActivity extends SumManager {
    FragmentManager mFragmentManager = getFragmentManager();
    FragmentTransaction mFragmentTransaction;
    SumManager sumManager = new SumManager();
    TextView textView;
    TabHost tabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initTabSchedule();
    }


    private void initTabSchedule() {
        tabHost = (TabHost) findViewById(R.id.tabhost);
        tabHost.setup();
        // get width screen
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        // get all day of month
        Calendar start = Calendar.getInstance();
        start.set(Calendar.DAY_OF_MONTH, 1);
        Calendar end = Calendar.getInstance();
        end.set(Calendar.DAY_OF_MONTH, end.getActualMaximum(Calendar.DAY_OF_MONTH));
        end.add(Calendar.DAY_OF_YEAR, 1);
        while (start.before(end)) {
            TabHost.TabSpec tab = tabHost.newTabSpec(mSimpleDateFormat.format(start.getTime()));
            tab.setIndicator(createTabIndicator(start, width));
            tab.setContent(new EmptyTabFactory());
            tabHost.addTab(tab);
            start.add(Calendar.DAY_OF_MONTH, 1);
        }
        Calendar crDate = Calendar.getInstance();
        tabHost.setCurrentTabByTag(mSimpleDateFormat.format(crDate.getTime()));
        TextView tv = (TextView) tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).findViewById(R.id.tv_date_bottom);
        tv.setTextColor(Color.GREEN);
        tabHost.getTabWidget().setStripEnabled(false);
        tabHost.getTabWidget().setDividerDrawable(null);
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                int selectedPage = tabHost.getCurrentTab();
                for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
//                    View v = tabHost.getTabWidget().getChildAt(i);
//                    v.setBackgroundResource(R.drawable.tabs);
//                    TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
//                    tv.setTextColor(getResources().getColor(R.color.white));
                    TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(R.id.tv_date_bottom);
                    tv.setTextColor(selectedPage == i ? Color.RED : Color.BLACK);
//                    View tabView = tabHost.getCurrentTabView();
//                    int scrollPos = tabView.getLeft() - (mHorizontalScrollView.getWidth() - tabView.getWidth()) / 2;
//                    mHorizontalScrollView.smoothScrollTo(scrollPos, 0);

                }
            }
        });
    }

    private View createTabIndicator(Calendar calendar, int width) {
        View view = LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        TextView tvBottom = (TextView) view.findViewById(R.id.tv_date_bottom);
        TextView tvTop = (TextView) view.findViewById(R.id.tv_date_top);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tvBottom.getLayoutParams();
        params.width = (width / 7);
        tvTop.setLayoutParams(params);
        tvBottom.setText(String.valueOf(calendar.get(Calendar.DATE)));
        tvTop.setText(getNameOfDay(calendar.get(Calendar.DAY_OF_WEEK)));
        return view;
    }

    private class EmptyTabFactory implements TabHost.TabContentFactory {

        @Override
        public View createTabContent(String tag) {
            return new View(TestActivity.this);
        }

    }


    public String sendRequest(String url, String jsonParam) {
        HttpPost post = new HttpPost(url);
        String encoding = sumManager.encodeString("hienpv:123");
        post.setHeader("Authorization", "Basic " + encoding);
        HttpResponse httpResponse = null;
        HttpClient httpClient = new DefaultHttpClient();
        try {
//            if (jsonParam != null) {
//                MultipartEntity multipartEntity = new MultipartEntity();
//                multipartEntity
//                post.setEntity(reqEntity);
//            }
            httpResponse = httpClient.execute(post);
        } catch (ClientProtocolException e) {
            System.out.println("ERROR: Loi trong qua trinh gui len VPDKSDD");
        } catch (IOException e) {
            System.out.println("ERROR: Loi trong qua trinh gui len VPDKSDD");
        }
        return getRespone(httpResponse);
    }

    private static String getRespone(HttpResponse httpResponse) {
        String result = null;

        InputStream inputStream = null;
        try {
            inputStream = httpResponse.getEntity().getContent();
        } catch (IOException e) {
            return result;
        }
        if (inputStream != null) {
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String line;
            try {
                while ((line = br.readLine()) != null) {
                    line = line.trim();
                    sb.append(line);
                }
                result = sb.toString();
            } catch (IOException e) {
                System.out.println("ERROR: Loi trong qua trinh doc ket qua tra ve tu VPDKSDD");
            }
        }
        return result;
    }
}
