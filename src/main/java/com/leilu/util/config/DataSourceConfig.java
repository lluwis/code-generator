package com.leilu.util.config;

import java.util.Map;

/**
 * Created by leilu on 2018/3/27.
 */
public class DataSourceConfig {
    private String url;
    private String driverName;
    private String userName;
    private String password;
    private Map<String,String> tableMap;

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }



    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Map<String, String> getTableMap() {
        return tableMap;
    }

    public void setTableMap(Map<String, String> tableMap) {
        this.tableMap = tableMap;
    }
}
