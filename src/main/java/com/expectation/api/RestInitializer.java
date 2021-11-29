package com.expectation.api;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

public class RestInitializer extends RouteBuilder {
    @Override
    public void configure() {
        restConfiguration()
                .component("spark-rest")
                .host("localhost")
                .port(1234)
                .bindingMode(RestBindingMode.json)
                .dataFormatProperty("prettyPrint", "true");
    }
}
