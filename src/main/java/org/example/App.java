package org.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App
{
    public static void main( String[] args )
    {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("org.example");
        context.getBean(OperationConsoleListener.class)
                .listenUpdates();
    }
}
