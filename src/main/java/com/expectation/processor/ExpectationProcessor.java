package com.expectation.processor;

import com.expectation.constants.Constants;
import com.expectation.models.TableList;
import org.apache.camel.Exchange;
import org.apache.commons.lang.StringUtils;

public class ExpectationProcessor {
    public void create(Exchange exchange) {

        TableList tableList = exchange.getIn().getBody(TableList.class);
        String dpName = StringUtils.capitalize(tableList.getDpName());
        StringBuilder outputFile = new StringBuilder(String.format(Constants.IMPORTS, dpName));

        String ans = InputGenerator.columnListGenerator(
                tableList.getProperties(),
                tableList.getDpName(),
                tableList.getDpRegion(),
                tableList.getDpProduct());

        outputFile.append(ans);
        WriteFile.writeToFile(outputFile,
                tableList.getDpRegion(),
                tableList.getDpProduct(),
                dpName);
    }
}
