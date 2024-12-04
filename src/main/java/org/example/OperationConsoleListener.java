package org.example;


import org.example.operations.ConsoleOperationType;
import org.example.operations.OperationCommandProcessor;
import java.util.Map;
import java.util.Scanner;

public class OperationConsoleListener {

    private final Scanner scanner;
    private final Map<ConsoleOperationType, OperationCommandProcessor> processorMap;


    public OperationConsoleListener(
            Scanner scanner,
            Map<ConsoleOperationType, OperationCommandProcessor> processorMap

            ) {
        this.scanner = scanner;
        this.processorMap = processorMap;
    }

    public void listenUpdates() {
        while (true) {
            var operationType = listenNextOperation();
            processNextOperation(operationType);
        }
    }
    public void start() {
        System.out.println("Console Listener started");
    }
    public void endListen() {
        System.out.println("Console Listener ended");
    }


    private ConsoleOperationType listenNextOperation() {
        System.out.println("\nPlease type next operation:");
        printAllAvailableOperations();
        while (true) {
            var nextOperation = scanner.nextLine();
            try {
                return ConsoleOperationType.valueOf(nextOperation);
            }catch (IllegalArgumentException e){
                System.out.println("No such command found");
            }
        }
    }

    private void printAllAvailableOperations() {
        processorMap.keySet()
                .forEach(System.out::println);
    }

    private void processNextOperation(ConsoleOperationType operation) {
        try {
            var processor = processorMap.get(operation);
            processor.processOperation();
        }catch (Exception e) {
            System.out.printf(
                    "Error executing command %s: error=%s%n",operation,
                    e.getMessage()
            );
        }
    }
}

