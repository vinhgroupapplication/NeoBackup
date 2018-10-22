package com.newsaigonsoft.neoegov.Subjects;

import java.util.List;

import com.newsaigonsoft.neoegov.Adapter.ReceiversDepartmentAdapter;

/**
 * Created by Vinh on 8/30/2017.
 */

public class DialogFalseRow {
    List<ReceiversRow> arrListFalse;
    ReceiversDepartmentAdapter adapterTrue;
    ReceiversDepartmentAdapter adapterFalse;
    List<ReceiversRow> arrListTrue;

    public List<ReceiversRow> getArrListFalse() {
        return arrListFalse;
    }

    public void setArrListFalse(List<ReceiversRow> arrListFalse) {
        this.arrListFalse = arrListFalse;
    }

    public ReceiversDepartmentAdapter getAdapterTrue() {
        return adapterTrue;
    }

    public void setAdapterTrue(ReceiversDepartmentAdapter adapterTrue) {
        this.adapterTrue = adapterTrue;
    }

    public ReceiversDepartmentAdapter getAdapterFalse() {
        return adapterFalse;
    }

    public void setAdapterFalse(ReceiversDepartmentAdapter adapterFalse) {
        this.adapterFalse = adapterFalse;
    }

    public List<ReceiversRow> getArrListTrue() {
        return arrListTrue;
    }

    public void setArrListTrue(List<ReceiversRow> arrListTrue) {
        this.arrListTrue = arrListTrue;
    }
}
