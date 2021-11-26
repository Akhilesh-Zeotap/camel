package com.expectation.models;

import java.util.List;

public class Table {
    private String  tableName;
    private String tableDfName;
    private List<Column> tableColumns;

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

    public List<Column> getTableColumns() {
        return tableColumns;
    }

    public void setTableColumns(List<Column> tableColumns) {
        this.tableColumns = tableColumns;
    }
}
