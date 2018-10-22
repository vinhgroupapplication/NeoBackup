package com.newsaigonsoft.neoegov.Fragment.Detail;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.newsaigonsoft.neoegov.Adapter.DetailRowAdapter;
import com.newsaigonsoft.neoegov.DetailActivity.DetailForwardActivity;
import com.newsaigonsoft.neoegov.SQLite.SQLite;
import com.newsaigonsoft.neoegov.Subjects.ContextMenuForwardRow;
import com.newsaigonsoft.neoegov.Subjects.DetailsRows;

/**
 * Created by Vinh on 3/5/2018.
 */

public class DetailFmBase extends Fragment {
    String title;
    DetailForwardActivity mDetailForwardActivity;
    ArrayList<ContextMenuForwardRow> arrContextMenu;
    SQLite mSqLiteListDocument;
    DetailFmLogic mDetailFmLogic;
    View header;
    DetailFmHeader mDetailFmHeader;
    DetailRowAdapter detailAdapter;
    List<DetailsRows> arrDetail_1, arrDetail_Full;
}
