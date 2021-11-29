package com.expectation.processor;

import com.expectation.models.DataInput;
import org.apache.camel.Exchange;
import org.apache.commons.lang.StringUtils;

public class ExpectationProcessor {
    public void create(Exchange exchange) {

        DataInput dataInput = exchange.getIn().getBody(DataInput.class);

        StringBuilder outputFile = GenerateExpectation.generateExpectation(
                dataInput.getProperties(),
                dataInput.getDpName().toLowerCase(),
                dataInput.getDpRegion().toLowerCase(),
                dataInput.getDpProduct().toLowerCase());

        WriteFile.writeToFile(outputFile,
                dataInput.getDpRegion().toLowerCase(),
                dataInput.getDpProduct().toLowerCase(),
                StringUtils.capitalize(dataInput.getDpName()));
    }
}
