package org.android.model;

/**
 * Created by Administrator on 2015/2/7.
 */
public class RunningAppInfo {

    public String getAppLabel() {
        return appLabel;
    }

    public void setAppLabel(String appLabel) {
        this.appLabel = appLabel;
    }

    private String appLabel;

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    private String pkgName;

    private int pid;

    private String processName;



}
