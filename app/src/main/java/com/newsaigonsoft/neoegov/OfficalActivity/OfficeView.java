package com.newsaigonsoft.neoegov.OfficalActivity;

import android.view.View;

import com.newsaigonsoft.neoegov.Adapter.DocumentAdapter;
import com.newsaigonsoft.neoegov.Adapter.DocumentLookupAdapter;
import com.newsaigonsoft.neoegov.Adapter.HotLineAdapter;
import com.newsaigonsoft.neoegov.Adapter.SlideMenuAdapter;
import com.newsaigonsoft.neoegov.Fragment.Document.DocumentFragment;
import com.newsaigonsoft.neoegov.SQLite.SQLite;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

/**
 * Created by Vinh on 12/6/2017.
 */

public interface OfficeView {
    void setLayoutManagerRecycle();

    void setAdapterSlider(SlideMenuAdapter adapter);

    void closeProgress();

    void stopRequest();

    void createDatabaseOffline(String TableName);

    int getNumberOfPage();

    int getPositionPage();

    void showErrorDialog();

    void goToHomeScreen();

    void setPositionPage(int i);

    void setActionBarIcon(int ic_arrow_back_white_24dp);

    void changeUIDocument(String isScreen, String URLNa);

//    void eventChangeToLookupComing(int position, boolean isScroll);

    void TotalAndChangeUIDocument(String URLNA_COMEBACK);

//    void eventChangeToDocument(int positionPage, String s, String urlNA, boolean b);

    void setDocLookup(DocumentLookupAdapter adapterClickNumber);

    void visibleNotConnection(boolean b);

    void showDocEmpty(boolean b);

    void HotLineAdapter(HotLineAdapter adapterHotLine);

    SQLite getmSqLite();

    void ShowProgress();

//    void setActionBarHomeScreen();

    String getLookupScreen();

    long getOganizationID();

    int getStatisticType();

    String getTypeHomeListDocument();

    void setDocLookupHome(DocumentLookupAdapter adapterDocumentLookup);

    void setListDoc(DocumentAdapter adapterDocument);

    void setLookupScreen(String listDocumentDepartment);

    void setOganizationID(long oganizationID);

    void setStatisticType(int statisticType);

    void setNumberOfPage(int totalDocument);

    void setTypeHomeListDocument(String typeHomeListDocumentComing);

    void setStatisticTypeHotLine(String statisticType);

    void showProgress();

    void setAfterSearch(boolean b);

    void setCheckShowListSearch(boolean b);

    void setScreenNameInSide(String screenNameInside);

    void setTypeStatistical();

    int getCategory();

    String getStatisStartDate();

    String getStatisEndDate();

    DocumentFragment getDocumentFragment();

    String getStatisticTypeHotLine();

    void ToastError(String s);

    void disibleInputSearchTop(boolean b);

    void closeSwipe();

    void setLoading();

    long getDocComeBackNumber();

    void LockListDocument();

    void SetTreeList(AndroidTreeView tView, String forward, TreeNode root);

    void setHomeTitle(String pageName);

    void setMainTitle(String pageName);

    void SettingCountClick(View v);

    String getDateFirst();

    String getDateLast();

    void setProcesserID(long processerID);

    long getProcesserID();
}
