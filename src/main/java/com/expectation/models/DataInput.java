package com.expectation.models;

import java.util.List;

public class DataInput {
    private String dpName;
    private String dpRegion;
    private String dpProduct;
    private List<Property> properties;

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

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }
}
