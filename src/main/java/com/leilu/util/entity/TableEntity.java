package com.leilu.util.entity;

import java.util.List;

/**
 * Created by leilu on 2018/3/28.
 */
public class TableEntity {
    /**
     * 表名
     */
    private String tableName;

    /**
     * 类名
     */
    private String className;

    /**
     * 首字母小写类名
     */
    private String unCapitalClassName;

    /**
     * 表注释
     */
    private String comment;

    /**
     * 引用
     */
    private List<String> imports;

    /**
     * 主键
     */
    private ColumnEntity pri;
    /**
     * 全部的列
     */
    private List<ColumnEntity> columns;

    /**
     * 除主键之外的列
     */
    private List<ColumnEntity> columnsWithoutPri;

    /**
     * 唯一索引列（包含id）
     */
    private List<ColumnEntity> uniqueColumns;

    /**
     * 普通索引列
     */
    private List<ColumnEntity> indexColumns;

    public ColumnEntity getPri() {
        return pri;
    }

    public void setPri(ColumnEntity pri) {
        this.pri = pri;
    }

    public List<ColumnEntity> getColumnsWithoutPri() {
        return columnsWithoutPri;
    }

    public void setColumnsWithoutPri(List<ColumnEntity> columnsWithoutPri) {
        this.columnsWithoutPri = columnsWithoutPri;
    }

    public List<ColumnEntity> getUniqueColumns() {
        return uniqueColumns;
    }

    public void setUniqueColumns(List<ColumnEntity> uniqueColumns) {
        this.uniqueColumns = uniqueColumns;
    }

    public List<String> getImports() {
        return imports;
    }

    public void setImports(List<String> imports) {
        this.imports = imports;
    }

    public String getUnCapitalClassName() {
        return unCapitalClassName;
    }

    public void setUnCapitalClassName(String unCapitalClassName) {
        this.unCapitalClassName = unCapitalClassName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<ColumnEntity> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnEntity> columns) {
        this.columns = columns;
    }

    public List<ColumnEntity> getIndexColumns() {
        return indexColumns;
    }

    public void setIndexColumns(List<ColumnEntity> indexColumns) {
        this.indexColumns = indexColumns;
    }
}
