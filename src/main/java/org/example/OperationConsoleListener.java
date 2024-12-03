package org.example;

import org.example.account.AccountService;
import org.example.operations.ConsoleOperationType;
import org.example.operations.OperationCommandProcessor;
import org.example.user.User;
import org.example.user.UserService;

import java.util.List;
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
        System.out.println("Please type operations:\n");
        while (true) {
            var operationType = listenNextOperation();
            processNextOperation(operationType);
        }
    }

    private ConsoleOperationType listenNextOperation() {
        System.out.println("Please type next operation:");
        while (true) {
            var nextOperation = scanner.nextLine();
            try {
                return ConsoleOperationType.valueOf(nextOperation);
            }catch (IllegalArgumentException e){
                System.out.println("No such command found");
            }
        }
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

