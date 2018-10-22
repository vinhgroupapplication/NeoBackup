package com.newsaigonsoft.neoegov.Fragment.Home;

/**
 * Created by Vinh on 10/20/2017.
 */

public interface HomeFmImp {
    void RequestData();
    void getOneMonth();
    void setDataForHomeScreenOfLine();
    void setMsgAfterTrainfer(String Resuilt, String InputComBack, String DocumentNumber, String inputName);
    void requestSchedule(String format);

    void OpenDetail(int position);
}
