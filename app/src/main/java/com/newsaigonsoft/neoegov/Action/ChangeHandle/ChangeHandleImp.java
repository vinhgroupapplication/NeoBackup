package com.newsaigonsoft.neoegov.Action.ChangeHandle;

import com.newsaigonsoft.neoegov.Adapter.ChangeHandlerAdapter;

/**
 * Created by Vinh on 11/23/2017.
 */

public interface ChangeHandleImp {
    void requestHandleProcess();
    void RequestChangeHandle();
    ChangeHandlerAdapter getHandleAdapter();
    void itemClick(int position);
}
