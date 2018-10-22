package com.newsaigonsoft.neoegov.Subjects;

/**
 * Created by VinhCN on 4/25/2017.
 */

public class AttachFile {
    String FileName;
    String FileTyPe;
    String FileURL;
    String Base64Code;
    String FileOnlyName;
    boolean digitalSignature;
    long FileEntryId;
    String Status;
    ModuleRow ModuleRow;

    public AttachFile(String fileName, String fileTyPe, String fileURL,
                      String base64Code, boolean DigitalSignature, long fileEntryId, String status, ModuleRow moduleRow) {
        FileName = fileName;
        FileTyPe = fileTyPe;
        FileURL = fileURL;
        Base64Code = base64Code;
        digitalSignature = DigitalSignature;
        FileEntryId = fileEntryId;
        Status = status;
        ModuleRow = moduleRow;
    }

    public AttachFile(String fileName, String fileTyPe, String fileURL, String base64Code, String fileOnlyName) {
        FileName = fileName;
        FileTyPe = fileTyPe;
        FileURL = fileURL;
        Base64Code = base64Code;
        FileOnlyName = fileOnlyName;

    }

    public com.newsaigonsoft.neoegov.Subjects.ModuleRow getModuleRow() {
        return ModuleRow;
    }

    public void setModuleRow(com.newsaigonsoft.neoegov.Subjects.ModuleRow moduleRow) {
        ModuleRow = moduleRow;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public long getFileEntryId() {
        return FileEntryId;
    }

    public void setFileEntryId(long fileEntryId) {
        FileEntryId = fileEntryId;
    }

    public boolean isDigitalSignature() {
        return digitalSignature;
    }

    public void setDigitalSignature(boolean digitalSignature) {
        this.digitalSignature = digitalSignature;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    public String getFileTyPe() {
        return FileTyPe;
    }

    public void setFileTyPe(String fileTyPe) {
        FileTyPe = fileTyPe;
    }

    public String getFileURL() {
        return FileURL;
    }

    public void setFileURL(String fileURL) {
        FileURL = fileURL;
    }

    public String getBase64Code() {
        return Base64Code;
    }

    public void setBase64Code(String base64Code) {
        Base64Code = base64Code;
    }

    public String getFileOnlyName() {
        return FileOnlyName;
    }

    public void setFileOnlyName(String fileOnlyName) {
        FileOnlyName = fileOnlyName;
    }
}
