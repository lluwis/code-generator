package com.leilu.util.config;

import org.apache.velocity.Template;

/**
 * Created by leilu on 2018/3/28.
 */
public class OutputConfig {
    /**
     * 模板名称
     */
    private String templateName;

    /**
     * 输出基本路径
     */
    private String bathPath;

    /**
     * 包名
     */
    private String packageName;

    /**
     * 输出文件的后缀名
     */
    private String fileName;

    /**
     * 文件名是否大写
     */
    private boolean fileCapital=true;

    /**
     * 完整输出路径
     */
    private String fullPath;

    /**
     * 模板
     */
    private Template template;

    public boolean isFileCapital() {
        return fileCapital;
    }

    public void setFileCapital(boolean fileCapital) {
        this.fileCapital = fileCapital;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getBathPath() {
        return bathPath;
    }

    public void setBathPath(String bathPath) {
        this.bathPath = bathPath;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }
}
