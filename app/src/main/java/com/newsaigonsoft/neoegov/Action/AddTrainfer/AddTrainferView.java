package com.newsaigonsoft.neoegov.Action.AddTrainfer;

import android.widget.ArrayAdapter;

import com.newsaigonsoft.neoegov.Adapter.AddTrainferAdapter;
import com.newsaigonsoft.neoegov.Adapter.TrainferSelectAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vinh on 11/14/2017.
 */

public interface AddTrainferView {

    long getTaskID();

    String getContent();

    int getSelectItem();

    void setListResuiltTrainfer(AddTrainferAdapter adapter);

    void showProgress(String s, AddTrainferLogic addTrainferLogic, String dialogCenter, boolean b);

    void closeProgress();

    void ToastError(String s);

    void setFirstItem(List<TrainferSelectAdapter.TrainferItems> listSelectTranfer);
}
