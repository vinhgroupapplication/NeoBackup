package com.newsaigonsoft.neoegov.Fragment.Other;

import android.content.Context;

import org.json.JSONObject;

import java.util.ArrayList;

import com.newsaigonsoft.neoegov.Adapter.MenuOtherAdapter;
import com.newsaigonsoft.neoegov.R;
import com.newsaigonsoft.neoegov.Subjects.MenuOtherRow;

import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DOC_LOCAL_CONNECTS;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DOC_RECEIPT_CONNECTS;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.DOC_SEND_CONNECTS;
import static com.newsaigonsoft.neoegov.Subjects.JsonKeyManager.HANDLE_WAY_CHANGE_LOG;

/**
 * Created by Vinh on 10/23/2017.
 */

public class OtherFmLogic implements OtherFmImp {

    String[] arrMenuName = {"Liên kết văn bản đến", "Liên kết văn bản đi", "Liên kết văn bản nội bộ", "Thay đổi cách thức xử lý"};
    int[] arrImage = {R.drawable.files_to_x1, R.drawable.files_send_x1, R.drawable.files_local_x1, R.drawable.method_x1};
    OtherFmView mOtherFmView;
    Context context;

    public OtherFmLogic(OtherFmView mOtherFmView, Context context) {
        this.mOtherFmView = mOtherFmView;
        this.context = context;
    }

    @Override
    public void initMenu(ArrayList<MenuOtherRow> arrMenu, JSONObject mOther) {
        arrMenu = new ArrayList<>();
//        if (!mOther.isNull(DOC_RECEIPT_CONNECTS)){
        arrMenu.add(new MenuOtherRow(arrImage[0], arrMenuName[0], DOC_RECEIPT_CONNECTS));
//        }
//        if (!mOther.isNull(DOC_SEND_CONNECTS)){
        arrMenu.add(new MenuOtherRow(arrImage[1], arrMenuName[1], DOC_SEND_CONNECTS));
//        }
//        if (!mOther.isNull(DOC_LOCAL_CONNECTS)){
        arrMenu.add(new MenuOtherRow(arrImage[2], arrMenuName[2], DOC_LOCAL_CONNECTS));
//        }
        //        if (!mOther.isNull(HANDLE_WAY_CHANGE_LOG)){
        arrMenu.add(new MenuOtherRow(arrImage[3], arrMenuName[3], HANDLE_WAY_CHANGE_LOG));
//        }
//        for (int i = 0; i < arrMenuName.length; i++) {
//            arrMenu.add(new MenuOtherRow(arrImage[i], arrMenuName[i]));
//        }
        MenuOtherAdapter adapter = new MenuOtherAdapter(context, arrMenu);
        mOtherFmView.setListOther(adapter, arrMenu);
    }
}
