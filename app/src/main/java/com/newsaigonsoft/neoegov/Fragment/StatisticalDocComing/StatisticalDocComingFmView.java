package com.newsaigonsoft.neoegov.Fragment.StatisticalDocComing;

import com.newsaigonsoft.neoegov.Adapter.DepartmentAdapter;
import com.newsaigonsoft.neoegov.GsonObject.GsonStaComing;
import com.newsaigonsoft.neoegov.GsonObject.GsonStaComingList;
import com.newsaigonsoft.neoegov.GsonObject.GsonStatisPersonJoin;
import com.newsaigonsoft.neoegov.Subjects.ReceivePerson;
import com.newsaigonsoft.neoegov.Subjects.StatisticalFmRow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vinh on 10/23/2017.
 */

public interface StatisticalDocComingFmView {

    void showProgress();

//    void setViewStatistical(String DocDemurrage_Full, String TotalDoc_Full, String DocNotProcess_Full, String DocProcessedOnTime_Full, String RateOnTime, String Date);
    void setViewStatistical(GsonStaComing.DataBean mBean, String dateFirst, String date);

    void setListDepartment(List<GsonStaComingList.DataBean> arr, String dateFirstX, String Date);

    void closeProgress();

    void setStatisticalRow(StatisticalFmRow mStatisticalRows);

    void ToastError(String s);

    void setListPerson(List<GsonStatisPersonJoin.DataBean> statisCMPersonAdapter);

    void showLayoutDepartment(boolean b);

    void showLayoutFull(boolean b);

    void hideLayoutPersonJoin(boolean b);

    String getCodeMenu();

    void getSrrListDepartmentSearch(ArrayList<ReceivePerson> arrListDepartmentSearch);

    void setAdatperDepartmentName(DepartmentAdapter adapter, ArrayList<String> arrNameDepartment);

    void showErrorToast();

    String getOrganizationId();
}
