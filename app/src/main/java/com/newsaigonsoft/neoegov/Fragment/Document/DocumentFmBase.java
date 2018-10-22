package com.newsaigonsoft.neoegov.Fragment.Document;

import android.app.Fragment;
import android.content.Intent;

import com.newsaigonsoft.neoegov.OfficalActivity.OfficalActivity;

import java.util.ArrayList;

/**
 * Created by Vinh on 3/5/2018.
 */

public class DocumentFmBase extends Fragment {
    public DocumentFmLogic mDocumentFmLogic;
    Intent intent;
    int numberOfPager;
    boolean isFirstLoading = false;
    ArrayList<String> arrNumberRow = new ArrayList<String>();
    boolean isClickSearch = false;
    OfficalActivity mOfficalActivity;

}
