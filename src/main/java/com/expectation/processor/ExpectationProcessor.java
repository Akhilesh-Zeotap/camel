package com.expectation.processor;

import com.expectation.constants.Constants;
import com.expectation.models.DataInput;
import com.expectation.models.Property;
import com.expectation.models.TableList;
import org.apache.camel.Exchange;
import org.apache.commons.lang.StringUtils;

public class ExpectationProcessor {
    public void create(Exchange exchange) {

        DataInput dataInput = exchange.getIn().getBody(DataInput.class);

        StringBuilder outputFile = GenerateExpectation.generateExpectation(
                dataInput.getProperties(),
                dataInput.getDpName(),
                dataInput.getDpRegion(),
                dataInput.getDpProduct());

        WriteFile.writeToFile(outputFile,
                dataInput.getDpRegion(),
                dataInput.getDpProduct(),
                StringUtils.capitalize(dataInput.getDpName()));
    }
}
