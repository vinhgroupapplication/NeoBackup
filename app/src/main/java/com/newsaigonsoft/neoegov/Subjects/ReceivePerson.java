package com.newsaigonsoft.neoegov.Subjects;

/**
 * Created by VinhCN on 5/3/2017.
 */

public class ReceivePerson {
    boolean Default;
    String ReceivePersonName;
    String ReceiveRoomName;
    String ReceivePersonID;
    String ReceiveRoomID;
    String ReceiveRoleName;
    boolean MainPerson;


    public ReceivePerson(String receivePersonID, String receiveRoomID) {
        ReceivePersonID = receivePersonID;
        ReceiveRoomID = receiveRoomID;
    }

    public ReceivePerson(boolean aDefault, String receivePersonName, String receiveRoomName, String receivePersonID, String receiveRoomID, String receiveRoleName, boolean mainPerson) {
        Default = aDefault;
        ReceivePersonName = receivePersonName;
        ReceiveRoomName = receiveRoomName;
        ReceivePersonID = receivePersonID;
        ReceiveRoomID = receiveRoomID;
        ReceiveRoleName = receiveRoleName;
        MainPerson = mainPerson;
    }

    public boolean isDefault() {
        return Default;
    }

    public void setDefault(boolean aDefault) {
        Default = aDefault;
    }

    public String getReceivePersonName() {
        return ReceivePersonName;
    }

    public void setReceivePersonName(String receivePersonName) {
        ReceivePersonName = receivePersonName;
    }

    public String getReceiveRoomName() {
        return ReceiveRoomName;
    }

    public void setReceiveRoomName(String receiveRoomName) {
        ReceiveRoomName = receiveRoomName;
    }

    public String getReceivePersonID() {
        return ReceivePersonID;
    }

    public void setReceivePersonID(String receivePersonID) {
        ReceivePersonID = receivePersonID;
    }

    public String getReceiveRoomID() {
        return ReceiveRoomID;
    }

    public void setReceiveRoomID(String receiveRoomID) {
        ReceiveRoomID = receiveRoomID;
    }

    public String getReceiveRoleName() {
        return ReceiveRoleName;
    }

    public void setReceiveRoleName(String receiveRoleName) {
        ReceiveRoleName = receiveRoleName;
    }

    public boolean isMainPerson() {
        return MainPerson;
    }

    public void setMainPerson(boolean mainPerson) {
        MainPerson = mainPerson;
    }
}
