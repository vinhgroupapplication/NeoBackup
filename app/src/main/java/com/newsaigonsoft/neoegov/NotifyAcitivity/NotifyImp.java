package com.newsaigonsoft.neoegov.NotifyAcitivity;

import android.content.Intent;

import com.newsaigonsoft.neoegov.SQLite.SQLite;

public interface NotifyImp {

    void getListNotify(SQLite mSqLiteNotify);

    void openDetails(Intent intent, int position);
}
