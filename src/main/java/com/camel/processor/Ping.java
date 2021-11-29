package com.camel.processor;
import org.apache.camel.Exchange;
public class Ping {

    public String ping(Exchange exchange) {
//        System.out.println("Hello World");
        System.out.println(exchange.getIn().getHeaders());

        return "Pong";
    }
}
