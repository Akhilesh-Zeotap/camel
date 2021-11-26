package com.expectation.api;

import com.expectation.models.TableList;
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
                .type(TableList.class)
                .route()
                .bean(ExpectationProcessor.class, "create");
    }
}
