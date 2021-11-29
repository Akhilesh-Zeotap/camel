package com.expectation.api;

import com.expectation.models.DataInput;
import com.expectation.processor.ExpectationProcessor;
import org.apache.camel.builder.RouteBuilder;

public class ExpectationApi extends RouteBuilder {
    @Override
    public void configure() {
        rest("expectation/ping")
                .get()
                .route()
                .setBody(constant("Hello From Expectation Api"));

        rest("expectation/create")
                .post()
                .type(DataInput.class)
                .route()
                .bean(ExpectationProcessor.class, "create");
    }
}
