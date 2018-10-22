package com.newsaigonsoft.neoegov.GuideActivity;

import com.newsaigonsoft.neoegov.Adapter.GuideMenuAdapter;
import com.newsaigonsoft.neoegov.Subjects.SumManager;

import java.util.List;

class GuideBase extends SumManager {
    List<GuideMenuAdapter.Items> arrMenu, arrMenuTemporary;
    GuideMenuAdapter menuAdapter;
}
