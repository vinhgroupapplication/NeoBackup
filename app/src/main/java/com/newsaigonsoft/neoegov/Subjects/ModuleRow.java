package com.newsaigonsoft.neoegov.Subjects;

/**
 * Created by Vinh on 10/11/2017.
 */

public class ModuleRow {
    String moduleCode;
    String moduleName;
    String serverUrl;
    String username;
    String password;

    public ModuleRow(String moduleCode, String moduleName, String serverUrl, String username, String password) {
        this.moduleCode = moduleCode;
        this.moduleName = moduleName;
        this.serverUrl = serverUrl;
        this.username = username;
        this.password = password;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
