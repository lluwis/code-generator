package com.leilu.util.config;

import java.util.List;

/**
 * Created by leilu on 2018/3/27.
 */
public class GlobalConfig {
    /**
     * 工程路径
     */
    private String projectPath;

    /**
     * 工程基础包名
     */
    private String projectPackage;

    /**
     * 数据源配置
     */
    private DataSourceConfig dataSourceConfig;

    /**
     * 扩展配置
     */
    private ExtendConfig extendConfig;

    /**
     * 输出配置
     */
    private List<OutputConfig> outputConfigList;

    public ExtendConfig getExtendConfig() {
        return extendConfig;
    }

    public void setExtendConfig(ExtendConfig extendConfig) {
        this.extendConfig = extendConfig;
    }

    public String getProjectPath() {
        return projectPath;
    }


    public String getProjectPackage() {
        return projectPackage;
    }

    public void setProjectPackage(String projectPackage) {
        this.projectPackage = projectPackage;
    }

    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
    }

    public DataSourceConfig getDataSourceConfig() {
        return dataSourceConfig;
    }

    public void setDataSourceConfig(DataSourceConfig dataSourceConfig) {
        this.dataSourceConfig = dataSourceConfig;
    }

    public List<OutputConfig> getOutputConfigList() {
        return outputConfigList;
    }

    public void setOutputConfigList(List<OutputConfig> outputConfigList) {
        this.outputConfigList = outputConfigList;
    }
}
