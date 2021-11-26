package com.expectation.models;

import java.util.List;

public class TableList {

    private String dpName;
    private String dpRegion;
    private String dpProduct;
    private List<Table> properties;

    public String getDpName() {
        return dpName;
    }

    public void setDpName(String dpName) {
        this.dpName = dpName;
    }

    public String getDpRegion() {
        return dpRegion;
    }

    public void setDpRegion(String dpRegion) {
        this.dpRegion = dpRegion;
    }

    public String getDpProduct() {
        return dpProduct;
    }

    public void setDpProduct(String dpProduct) {
        this.dpProduct = dpProduct;
    }

    public List<Table> getProperties() {
        return properties;
    }

    public void setProperties(List<Table> properties) {
        this.properties = properties;
    }
}
