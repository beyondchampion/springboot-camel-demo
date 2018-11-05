package com.unicom.demo;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class HttpPollWithQuartzCamel {
    public static void main(String args[]) throws Exception {
        CamelContext context = new DefaultCamelContext();
        context.addRoutes(new RouteBuilder() {
            public void configure() {
                from("quartz://report?cron=10 * * * * ?&stateful=true")
                .to("http://localhost:8080/prjWeb/test.camelreq")
                .to("file:d:/temp/outbox?fileName=http.csv");
            }
        });
        context.start();
        boolean loop = true;
        while (loop) {
            Thread.sleep(25000);
        }
        context.stop();
    }
}

