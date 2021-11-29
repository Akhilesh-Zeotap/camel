package com.camel.api;

import com.camel.models.Student;
import com.camel.processor.Ping;
import com.camel.processor.StudentService;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

public class RestAPI extends RouteBuilder {
    @Override
    public void configure() {
        restConfiguration()
                .component("spark-rest")
                .host("localhost")
                .port(1234)
                .bindingMode(RestBindingMode.json)
                .dataFormatProperty("prettyPrint", "true");

        rest("/ping")
                .get()
                .route()
                .bean(Ping.class, "ping");

        rest("/get-all")
                .get()
                .route()
                .bean(StudentService.class, "getAll");
        rest("/get-one/{id}")
                .get()
                .route()
                .bean(StudentService.class, "getOne");
        rest("/insert-one")
                .post()
                .type(Student.class)
                .route()
                .bean(StudentService.class, "insertOrUpdateOne");
    }
}
