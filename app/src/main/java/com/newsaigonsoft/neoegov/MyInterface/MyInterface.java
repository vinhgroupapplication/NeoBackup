package com.newsaigonsoft.neoegov.MyInterface;

/**
 * Created by Vinh on 7/28/2017.
 */

public interface MyInterface {
    void eventGetHomeListDocument(int TotalDocument, String isScreen, int statisticType, long OganizationID);
    void eventGetHomeListDocument(int TotalDocument, String isScreen, int statisticType, long OganizationID, long processerID);

}
