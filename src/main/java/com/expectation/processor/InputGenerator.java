package com.expectation.processor;

import com.expectation.constants.Constants;
import com.expectation.models.Column;
import com.expectation.models.Table;

import java.util.List;

import static java.lang.String.format;
import static java.lang.String.join;

public class InputGenerator {


    public static String columnListGenerator(List<Table> tables, String dpName, String dpRegion, String dpProduct) {
        StringBuilder file = new StringBuilder();
        StringBuilder rawDefInput = new StringBuilder();
        for (Table table : tables) {
            String tableVariableName = dpName.toLowerCase() + "_" + table.getTableName() + "_" + dpRegion + "_" + dpProduct;
            rawDefInput.append(String.format("(\"%s\",%s),", table.getTableDfName(), tableVariableName));
            StringBuilder tableInput = new StringBuilder(format(Constants.TABLE, tableVariableName));
            for (Column column : table.getTableColumns()) {
                tableInput.append("    ").append(generateColumn(column.getColName(), column.getColValues(), column.getAdditionalColProperties()));
                tableInput.append(",\n");
            }
            tableInput.delete(tableInput.length() - 2, tableInput.length());
            tableInput.append(")\n\n");
            file.append(tableInput);
        }
        rawDefInput.delete(rawDefInput.length() - 1, rawDefInput.length());

        return file + String.format(Constants.POST_EXPECTATION, dpRegion, dpProduct, rawDefInput);
    }


    private static String generateColumn(String colName, List<String> colValues, List<String> additionalColumnProperties) {
        //Constants.COLUMN = "column(\"%s\", List(";

        StringBuilder column = new StringBuilder(format(Constants.COLUMN, colName));
        column.append(join("\",\"", colValues)).append("\"))");

        for (String colProperty : additionalColumnProperties) {
            column.append(".").append(colProperty);
        }
        return column.toString();
        //output = column("_c0", List(iPhone,Unknown,X88 Pro,SmartTV,To Be Filled By O.E.M.,HP Notebook,iPad)).withNull;
    }

}
