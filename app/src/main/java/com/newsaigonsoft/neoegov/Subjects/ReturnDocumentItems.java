package com.newsaigonsoft.neoegov.Subjects;

/**
 * Created by Vinh on 8/8/2017.
 */

public class ReturnDocumentItems {
    String ReturnId;
    String ReturnName;
    String ReturnDepartment;

    public ReturnDocumentItems(String returnId, String returnName, String returnDepartment) {
        ReturnId = returnId;
        ReturnName = returnName;
        ReturnDepartment = returnDepartment;
    }

    public String getReturnId() {
        return ReturnId;
    }

    public void setReturnId(String returnId) {
        ReturnId = returnId;
    }

    public String getReturnName() {
        return ReturnName;
    }

    public void setReturnName(String returnName) {
        ReturnName = returnName;
    }

    public String getReturnDepartment() {
        return ReturnDepartment;
    }

    public void setReturnDepartment(String returnDepartment) {
        ReturnDepartment = returnDepartment;
    }
}
