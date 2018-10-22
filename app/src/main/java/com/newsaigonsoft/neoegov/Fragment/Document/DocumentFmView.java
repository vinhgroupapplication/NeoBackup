package com.newsaigonsoft.neoegov.Fragment.Document;

import android.content.Intent;

/**
 * Created by Vinh on 10/24/2017.
 */

public interface DocumentFmView {
    void eventSearch(int positionSpinner, boolean visiblePagerBar);

    void getListSQLDocument(int i, String urlNA);

    void eventGetListSQL(String s);

    void eventChangeToLookup(String s);

    void eventChangePageDocument();

    void eventChangePageLookupComing();

    void closeProgress();

    void setRedMsgColor();

    void setNullCreenComeBack();

    void setBlueMsg();

    void visibleNotifyForward(boolean visible, String msg);

    void startMyIntent(Intent intent);

    void changePageLookup();

    Intent getIntentFromAcitivity();
}
