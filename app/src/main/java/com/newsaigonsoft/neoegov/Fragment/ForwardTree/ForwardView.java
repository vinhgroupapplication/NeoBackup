package com.newsaigonsoft.neoegov.Fragment.ForwardTree;

import com.unnamed.b.atv.view.AndroidTreeView;

/**
 * Created by Vinh on 11/3/2017.
 */

public interface ForwardView {
    void SetTreeList(AndroidTreeView tView, String forward);

    void showProgress();

    void closeProgress();

    void ToastError(String s);
}
