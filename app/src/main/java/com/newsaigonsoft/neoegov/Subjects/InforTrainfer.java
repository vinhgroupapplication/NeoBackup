package com.newsaigonsoft.neoegov.Subjects;

/**
 * Created by Vinh on 5/9/2017.
 */

public class InforTrainfer extends ItemsDocument {

    String ProcessPerson = "";
    String DocumentNumber;
    String TypeHomeListDocument;
    String ProcessPosition;
    String DepartmentPosition;
    boolean OverNetWork;


    @Override
    public boolean isOverNetWork() {
        return OverNetWork;
    }

    @Override
    public void setOverNetWork(boolean overNetWork) {
        OverNetWork = overNetWork;
    }

    public String getProcessPerson() {
        return ProcessPerson;
    }

    public void setProcessPerson(String processPerson) {
        ProcessPerson = processPerson;
    }

    public String getDocumentNumber() {
        return DocumentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        DocumentNumber = documentNumber;
    }

    public String getTypeHomeListDocument() {
        return TypeHomeListDocument;
    }

    public void setTypeHomeListDocument(String typeHomeListDocument) {
        TypeHomeListDocument = typeHomeListDocument;
    }

    public String getProcessPosition() {
        return ProcessPosition;
    }

    public void setProcessPosition(String processPosition) {
        ProcessPosition = processPosition;
    }

    public String getDepartmentPosition() {
        return DepartmentPosition;
    }

    public void setDepartmentPosition(String departmentPosition) {
        DepartmentPosition = departmentPosition;
    }

    //    public long getForwardDepartmentID() {
//        return ForwardDepartmentID;
//    }
//
//    public void setForwardDepartmentID(long forwardDepartmentID) {
//        ForwardDepartmentID = forwardDepartmentID;
//    }
//
//    public long getWorkFlowStationCancel() {
//        return WorkFlowStationCancel;
//    }
//
//    public void setWorkFlowStationCancel(long workFlowStationCancel) {
//        WorkFlowStationCancel = workFlowStationCancel;
//    }
//
//    public long getWorkFlowStationReturn() {
//        return WorkFlowStationReturn;
//    }
//
//    public void setWorkFlowStationReturn(long workFlowStationReturn) {
//        WorkFlowStationReturn = workFlowStationReturn;
//    }
    //    long ForwardDepartmentID;
//    long WorkFlowStationCancel;
//    long WorkFlowStationReturn;
//    String TrainferIDReport;
//    String Trainfer_14_ID;
//    String TrainferReleaseID;
//    String TrainferID;
//    public String getTrainfer_14_ID() {
//        return Trainfer_14_ID;
//    }
//
//    public void setTrainfer_14_ID(String trainfer_14_ID) {
//        Trainfer_14_ID = trainfer_14_ID;
//    }
//
//    public String getTrainferReport() {
//        return TrainferIDReport;
//    }
//
//    public void setTrainferReport(String trainferIDPerson) {
//        TrainferIDReport = trainferIDPerson;
//    }
//
//    public String getTrainferID() {
//        return TrainferID;
//    }
//
//    public void setTrainferID(String trainferID) {
//        TrainferID = trainferID;
//    }
//
//
//    public String getTrainferIDReport() {
//        return TrainferIDReport;
//    }
//
//    public void setTrainferIDReport(String trainferIDReport) {
//        TrainferIDReport = trainferIDReport;
//    }
//
//    public String getTrainferReleaseID() {
//        return TrainferReleaseID;
//    }
//
//    public void setTrainferReleaseID(String trainferReleaseID) {
//        TrainferReleaseID = trainferReleaseID;
//    }
}
