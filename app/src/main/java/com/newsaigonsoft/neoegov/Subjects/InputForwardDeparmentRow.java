package com.newsaigonsoft.neoegov.Subjects;

import java.util.List;

import com.newsaigonsoft.neoegov.Adapter.ReceiversDepartmentAdapter;

/**
 * Created by Vinh on 8/9/2017.
 */

public class InputForwardDeparmentRow {
    boolean isDefault;
    int OrganizationId;
    String OrganizationName;
    List<ReceiversRow> arrFalse;
    ReceiversDepartmentAdapter AdapterTrue;
    List<ReceiversRow> arrTrue;

    public InputForwardDeparmentRow(boolean isDefault, int organizationId, String organizationName, List<ReceiversRow> arrFalse, ReceiversDepartmentAdapter adapterTrue, List<ReceiversRow> arrTrue) {
        this.isDefault = isDefault;
        OrganizationId = organizationId;
        OrganizationName = organizationName;
        this.arrFalse = arrFalse;
        AdapterTrue = adapterTrue;
        this.arrTrue = arrTrue;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public int getOrganizationId() {
        return OrganizationId;
    }

    public void setOrganizationId(int organizationId) {
        OrganizationId = organizationId;
    }

    public String getOrganizationName() {
        return OrganizationName;
    }

    public void setOrganizationName(String organizationName) {
        OrganizationName = organizationName;
    }

    public List<ReceiversRow> getArrFalse() {
        return arrFalse;
    }

    public void setArrFalse(List<ReceiversRow> arrFalse) {
        this.arrFalse = arrFalse;
    }

    public ReceiversDepartmentAdapter getAdapterTrue() {
        return AdapterTrue;
    }

    public void setAdapterTrue(ReceiversDepartmentAdapter adapterTrue) {
        AdapterTrue = adapterTrue;
    }

    public List<ReceiversRow> getArrTrue() {
        return arrTrue;
    }

    public void setArrTrue(List<ReceiversRow> arrTrue) {
        this.arrTrue = arrTrue;
    }
}
