package com.newsaigonsoft.neoegov.Action.AddTrainfer;

import com.newsaigonsoft.neoegov.Adapter.TrainferSelectAdapter;
import com.newsaigonsoft.neoegov.Subjects.SumManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vinh on 3/1/2018.
 */

public class BaseAddTrainfer extends SumManager {

    String titleActionBar;
    long TaskID;
    ArrayList<String> listSelectTranfer;

    AddTrainferLogic mAddTrainferLogic;
    List<TrainferSelectAdapter.TrainferItems> listSelectTranferNews;

}
