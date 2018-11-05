package com.unicom.demo;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * 运行后完成的工作是将d:/temp/inbox/下的所有文件移到d:/temp/outbox
 */
public class FileMoveWithCamel {
    public static void main(String args[]) throws Exception {
        CamelContext context = new DefaultCamelContext();
        context.addRoutes(new RouteBuilder() {
            public void configure() {
                //from("file:d:/temp/inbox?noop=true").to("file:d:/temp/outbox");
                from("file:d:/temp/inbox/?delay=30000").to("file:d:/temp/outbox");
            }
        });
        context.start();
        boolean loop =true;
        while(loop){
            Thread.sleep(25000);
        }
        context.stop();
    }
}

