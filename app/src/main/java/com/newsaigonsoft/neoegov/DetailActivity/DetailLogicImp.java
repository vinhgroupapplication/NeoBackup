package com.newsaigonsoft.neoegov.DetailActivity;

/**
 * Created by Vinh on 10/17/2017.
 */

public interface DetailLogicImp {
    void changeListView(String CaseofLoad);

    void setItemMenuContext(String resuilt);

    void SaveDraft(int workflowTransition);

    void ReadListDepartment(int workflowTransition);

    void ReadJsonInput(int isTap);

    void ReadPersonReturn(int workflowTransition);

    void ReadCancelList(int workflowTransition);
}


