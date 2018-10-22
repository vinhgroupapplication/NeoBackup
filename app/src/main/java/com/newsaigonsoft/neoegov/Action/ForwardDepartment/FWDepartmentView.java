package com.newsaigonsoft.neoegov.Action.ForwardDepartment;

import android.widget.CheckBox;
import android.widget.ListView;

import com.newsaigonsoft.neoegov.Adapter.ChangeHandlerAdapter;
import com.newsaigonsoft.neoegov.Adapter.InputForwardDepartmentAdapter;
import com.newsaigonsoft.neoegov.Subjects.AttachFile;

import java.util.ArrayList;

/**
 * Created by Vinh on 12/20/2017.
 */

public interface FWDepartmentView {
    long getDocumentID();

    void setListHandle(ChangeHandlerAdapter adapterHandleProcess);

    void setAdapterReceivers(InputForwardDepartmentAdapter adapterReceivers);

    ArrayList<AttachFile> getArrAttachFile();

    CheckBox getCbRegulationDeadline();

    String getProcessDay();

    String getExpirationDate();

    int getWorkFlow();

    String getResourceCodeId();

    String getContent();

    boolean getCheckedEmail();

    boolean getCheckUrgent();

    boolean getSuperUrgent();

    boolean getCheckallowExtension();

    void insertTableWaiting();

    void closeProgress();

    void deleteData();

    void showProgress();

    String getScreen();

    void showToast(String s);

    String getDocumentNumber();

    void setExpirationDate(String dateFromMiliSec);

    String getInputName();

    ListView getListView();

    void handleGetNumDay(String dateChoose);

    void setDaysCount(String s);

    void ToastError(String s);

    boolean isCancelChangeTextRequest();

    void setCancelChangeTextRequest(boolean b);

    void CheckAllButton(boolean check);

    boolean isChooseHandleWay();

    boolean isChooseDueDate();
}
