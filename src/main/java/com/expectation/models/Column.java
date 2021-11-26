package com.expectation.models;

import java.util.List;

public class Column {
    private String colName;
    private List<String> colValues;
    private String colDataType;
    private List<String> additionalColProperties;

    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName;
    }

    public List<String> getColValues() {
        return colValues;
    }

    public void setColValues(List<String> colValues) {
        this.colValues = colValues;
    }

    public String getColDataType() {
        return colDataType;
    }

    public void setColDataType(String colDataType) {
        this.colDataType = colDataType;
    }

    public List<String> getAdditionalColProperties() {
        return additionalColProperties;
    }

    public void setAdditionalColProperties(List<String> additionalColProperties) {
        this.additionalColProperties = additionalColProperties;
    }
}
