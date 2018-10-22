package com.newsaigonsoft.neoegov.AppInforActivity;

import android.content.Context;

import com.newsaigonsoft.neoegov.Adapter.GuideMenuAdapter;
import com.newsaigonsoft.neoegov.Adapter.InforAdapter;

import java.util.ArrayList;
import java.util.List;

public class AppInforLogic implements AppInforImp {
    AppInforView mAppInforView;
    Context context;
    List<InforAdapter.Items> arrMenu;
    InforAdapter menuAdapter;

    public AppInforLogic(AppInforView mAppInforView, Context context) {
        this.mAppInforView = mAppInforView;
        this.context = context;
    }

    @Override
    public void getListInfor() {
        arrMenu = new ArrayList<>();
//        arrMenu.add(new InforAdapter().new Items("Thông tin phiên bản", true));
//        arrMenu.add(new InforAdapter().new Items("Đánh giá ứng dụng", false));
        arrMenu.add(new InforAdapter().new Items("Về NewSaiGonSoft", false));
        menuAdapter = new InforAdapter(arrMenu, context);
        mAppInforView.setListInfor(menuAdapter);
    }
}
