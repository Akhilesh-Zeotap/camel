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

    /*
    tableDfMap is Map of tableName with its corresponding Dataframe Name.
    Sample Generated Output :
    override def getRawDataDefinition(region: String, product: String): Map[String, SparkDataframe] = (region, product) match {
    case ("eu", "profile") => getSparkDataFrameMap[DColumn, SparkDataframe](List(("inputDf",onaudience_rawInput_eu_profile),("maid",onaudience_maidInput_eu_profile)))
    case _ => Map("emptyDataset" -> SparkDataframe())
  }
     */
    private static String getListOfDf(String dpName, String dpRegion, String dpProduct, Map<String, String> tableDfMap) {
        StringBuilder rawDefInput = new StringBuilder();
        for (String tableName : tableDfMap.keySet()) {
            String currentVariable = String.format("%s_%s_%s_%s", dpName, tableName, dpRegion, dpProduct);
            rawDefInput.append(String.format("(\"%s\",%s),", tableDfMap.getOrDefault(tableName, ""), currentVariable));
        }
        rawDefInput.delete(rawDefInput.length() - 1, rawDefInput.length());
        return String.format(Constants.POST_EXPECTATION, dpRegion, dpProduct, rawDefInput);
    }

    /*
    ->mapValues is map<tableName,Array of DColumn String of that table.
    Eg:=> Map<rawInput,val bidberry_rawInput_eu_profile: Array[DColumn] = Array(
    column("values", List(1,2,3,4)).WithNull,
    column("country", List("gbr", "esp", "deu", "fra", "ita")).WithJunk)>

    ->Generates combined Array DColumn for all the input tables.
     */
    private static StringBuilder getCombinedTables(Map<String, StringBuilder> mapValues) {
        StringBuilder combinedValues = new StringBuilder();
        for (StringBuilder value : mapValues.values()) {
            combinedValues.append(value).append("\n\n");
        }
        return combinedValues;
    }

    /*
    Generates variable name for DColumn Array. Eg => val bidberry_rawInput_eu_profile: Array[DColumn] = Array(
     */
    private static String getTableVariableName(String dpName, String tableName, String dpRegion, String dpProduct) {
        String varName = dpName.toLowerCase() + "_" + tableName + "_" + dpRegion + "_" + dpProduct;
        return String.format(Constants.TABLE, varName);
    }

    /*
    Generates column in the format :=> column("country", List("DEU", "GBR", "ITA", "FRA", "ESP")).WithNull.WithJunk
    Note: columnValues is String with values in the format: "a","b" (Mandatory) else the values will be considered as
    single values, Eg "a,b,c" will be considered as single value i.e. List("a,b,c") rather than 3 values i.e List("a","b","c")
     */
    private static StringBuilder generateColumn(String columnName, String columnValues, List<String> columnProperties) {
        StringBuilder column = new StringBuilder(String.format(Constants.COLUMN, columnName) + columnValues + "))");
        for (String columnProperty : columnProperties) {
            column.append(".").append(columnProperty);
        }
        return column.append(")");
    }

}
