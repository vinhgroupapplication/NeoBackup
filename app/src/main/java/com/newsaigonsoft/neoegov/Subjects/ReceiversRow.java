package com.newsaigonsoft.neoegov.Subjects;

/**
 * Created by Vinh on 8/17/2017.
 */

public class ReceiversRow {
    boolean Default;
    String ReceiverId;
    String ReceiverName;
    String RoleName;
    boolean isDialog;
    boolean MainChecked;

    public ReceiversRow(boolean aDefault, String receiverId, String receiverName, String roleName, boolean isDialog, boolean mainChecked) {
        Default = aDefault;
        ReceiverId = receiverId;
        ReceiverName = receiverName;
        RoleName = roleName;
        this.isDialog = isDialog;
        MainChecked = mainChecked;
    }

    public boolean isDefault() {
        return Default;
    }

    public void setDefault(boolean aDefault) {
        Default = aDefault;
    }

    public String getReceiverId() {
        return ReceiverId;
    }

    public void setReceiverId(String receiverId) {
        ReceiverId = receiverId;
    }

    public String getReceiverName() {
        return ReceiverName;
    }

    public void setReceiverName(String receiverName) {
        ReceiverName = receiverName;
    }

    public String getRoleName() {
        return RoleName;
    }

    public void setRoleName(String roleName) {
        RoleName = roleName;
    }

    public boolean isDialog() {
        return isDialog;
    }

    public void setDialog(boolean dialog) {
        isDialog = dialog;
    }

    public boolean isMainChecked() {
        return MainChecked;
    }

    public void setMainChecked(boolean mainChecked) {
        MainChecked = mainChecked;
    }
}
