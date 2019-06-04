package com.leilu.util.entity;

/**
 * Created by leilu on 2018/3/28.
 */
public class ColumnEntity {
    /**
     * 列名
     */
    private String columnName;
    /**
     * 列类型
     */
    private String columnType;
    /**
     * 注释
     */
    private String comment;
    /**
     * 属性名
     */
    private String attrName;
    /**
     * 属性类型
     */
    private String attrType;

    /**
     * 是否支持范围查询
     */
    private boolean range=false;

    /**
     * 是否选择框
     */
    private boolean select=false;

    /**
     * 能否为空
     */
    private boolean nullable=true;
    /**
     * 大写属性名
     */
    private String capitalAttrName;

    public boolean isRange() {
        return range;
    }

    public boolean isSelect() {
        return select;
    }

    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public boolean getRange() {
        return range;
    }

    public void setRange(boolean range) {
        this.range = range;
    }

    public boolean getSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getAttrType() {
        return attrType;
    }

    public void setAttrType(String attrType) {
        this.attrType = attrType;
    }

    public String getCapitalAttrName() {
        return capitalAttrName;
    }

    public void setCapitalAttrName(String capitalAttrName) {
        this.capitalAttrName = capitalAttrName;
    }
}
