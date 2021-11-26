package com.expectation.main;

import com.expectation.api.ExpectationApi;
import com.expectation.api.RestInitializer;
import org.apache.camel.CamelContext;
import org.apache.camel.component.http4.HttpComponent;
import org.apache.camel.impl.DefaultCamelContext;

public class Main {
    public static void main(String[] args) throws Exception {
        CamelContext camelContext = new DefaultCamelContext();

        camelContext.addComponent("http",new HttpComponent());
        camelContext.addRoutes(new RestInitializer());
        camelContext.addRoutes(new ExpectationApi());
        camelContext.start();

    }
}
