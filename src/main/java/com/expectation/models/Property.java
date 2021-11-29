package com.expectation.models;

import java.util.List;

public class Property {
    private String tableName;
    private String tableDfName;
    private String columnName;
    private String columnValue;
    private String columnType;
    private List<String> columnProperties;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableDfName() {
        return tableDfName;
    }

    public void setTableDfName(String tableDfName) {
        this.tableDfName = tableDfName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnValue() {
        return columnValue;
    }

    public void setColumnValue(String columnValue) {
        this.columnValue = columnValue;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public List<String> getColumnProperties() {
        return columnProperties;
    }

    public void setColumnProperties(List<String> columnProperties) {
        this.columnProperties = columnProperties;
    }
}
