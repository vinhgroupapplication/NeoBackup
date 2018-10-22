package com.newsaigonsoft.neoegov.GuideActivity;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.newsaigonsoft.neoegov.Adapter.GuideMenuAdapter;
import com.newsaigonsoft.neoegov.R;

import java.util.ArrayList;
import java.util.List;

public class GuideLogic extends GuideBase implements GuideImp {
    GuideView mGuideView;
    Context context;

    public GuideLogic(GuideView mGuideView, Context context) {
        this.mGuideView = mGuideView;
        this.context = context;
    }


    @Override
    public void getListMenu() {
        arrMenu = new ArrayList<>();
        arrMenuTemporary = new ArrayList<>();
        arrMenu.add(new GuideMenuAdapter().new Items("Đăng nhập hệ thống"));
        arrMenu.add(new GuideMenuAdapter().new Items("Làm cách nào để luân chuyển công việc cho cán bộ xử lý"));
        arrMenu.add(new GuideMenuAdapter().new Items("Có thể thay đổi cách thức xử lý của văn bản đã xử lý được không"));
        arrMenuTemporary.addAll(arrMenu);
        menuAdapter = new GuideMenuAdapter(arrMenu, context);
        mGuideView.setAdapterList(menuAdapter);
    }

    @Override
    public void filterName(String newText) {
        arrMenu.clear();
        for (int i = 0; i < arrMenuTemporary.size(); i++) {
            if (removeAccent(arrMenuTemporary.get(i).getMenuName().toUpperCase()).contains(removeAccent(newText.toUpperCase()))) {
                arrMenu.add(arrMenuTemporary.get(i));
            }
        }
        if (menuAdapter != null) {
            menuAdapter.notifyDataSetChanged();
        }
    }
}
