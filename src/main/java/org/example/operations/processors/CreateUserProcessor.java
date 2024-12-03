package org.example.operations.processors;

import org.example.operations.OperationCommandProcessor;
import org.example.user.User;
import org.example.user.UserService;

import java.util.Scanner;

public class CreateUserProcessor implements OperationCommandProcessor {

    private final UserService userService;
    private final Scanner scanner;

    @Override
    public void processOperation() {
        System.out.println("Enter login for new user:");
        String login = scanner.nextLine();
        User user = userService.createUser(login);
        System.out.println("User created" + user.toString());
    }

    public CreateUserProcessor(UserService userService, Scanner scanner) {
        this.userService = userService;
        this.scanner = scanner;
    }
}
