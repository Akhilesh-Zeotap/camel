package com.expectation.processor;

import com.expectation.constants.Constants;
import com.expectation.models.Property;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenerateExpectation {
    public static StringBuilder generateExpectation(List<Property> properties, String dpName, String dpRegion, String dpProduct) {
        if (properties.isEmpty()) return new StringBuilder();
        Map<String, StringBuilder> mapValues = new HashMap<>();
        Map<String, String> tableDfMap = new HashMap<>();
        for (Property property : properties) {
            StringBuilder current = mapValues.getOrDefault(property.getTableName(), new StringBuilder());
            if (current.length() < 1) {
                current.append(getTableVariableName(dpName, property.getTableName(), dpRegion, dpProduct));
                current.append("    ").append(generateColumn(property.getColumnName(), property.getColumnValue(), property.getColumnProperties()));
            } else {
                current.append(",\n    ").append(generateColumn(property.getColumnName(), property.getColumnValue(), property.getColumnProperties()));
            }
            mapValues.put(property.getTableName(), current);
            tableDfMap.put(property.getTableName(), property.getTableDfName());
        }
        return new StringBuilder(String.format(Constants.IMPORTS, StringUtils.capitalize(dpName))).append(getCombinedTables(mapValues).append(getListOfDf(dpName, dpRegion, dpProduct, tableDfMap)));
    }


    private static String getListOfDf(String dpName, String dpRegion, String dpProduct, Map<String, String> tableDfMap) {
        StringBuilder rawDefInput = new StringBuilder();
        for (String tableName : tableDfMap.keySet()) {
            String currentVariable = String.format("%s_%s_%s_%s", dpName, tableName, dpRegion, dpProduct);
            rawDefInput.append(String.format("(\"%s\",%s),", tableDfMap.getOrDefault(tableName, ""), currentVariable));
        }
        rawDefInput.delete(rawDefInput.length() - 1, rawDefInput.length());
        return String.format(Constants.POST_EXPECTATION, dpRegion, dpProduct, rawDefInput);
    }

    private static StringBuilder getCombinedTables(Map<String, StringBuilder> mapValues) {
        StringBuilder combinedValues = new StringBuilder();
        for (StringBuilder value : mapValues.values()) {
            combinedValues.append(value).append(")\n\n");
        }
        return combinedValues;
    }


    private static String getTableVariableName(String dpName, String tableName, String dpRegion, String dpProduct) {
        String varName = dpName.toLowerCase() + "_" + tableName + "_" + dpRegion + "_" + dpProduct;
        return String.format(Constants.TABLE, varName);
    }

    private static StringBuilder generateColumn(String columnName, String columnValues, List<String> columnProperties) {
        StringBuilder column = new StringBuilder(String.format(Constants.COLUMN, columnName) + columnValues + "))");
        for (String columnProperty : columnProperties) {
            column.append(".").append(columnProperty);
        }
        return column;
    }

}
