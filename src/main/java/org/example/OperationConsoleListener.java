package org.example;

import org.example.account.AccountService;
import org.example.user.User;
import org.example.user.UserService;

import java.util.List;
import java.util.Scanner;

public class OperationConsoleListener {

    private final Scanner scanner;
    private final AccountService accountService;
    private final UserService userService;

    public OperationConsoleListener(Scanner scanner,
                                    AccountService accountService,
                                    UserService userService) {
        this.scanner = scanner;
        this.accountService = accountService;
        this.userService = userService;
    }

    public void listenUpdates() {
        System.out.println("Please type operations:\n");
        while (true) {
            var operationType = listenNextOperation();
            try {
                processNextOperation(operationType);
            }catch (Exception e) {
                System.out.printf(
                        "Error executing command %s: error=%s%n", operationType,
                        e.getMessage()
                        );
            }
        }
    }

    private String listenNextOperation() {
        System.out.println("Please type next operation:\n");
        return scanner.nextLine();
    }

    private void processNextOperation(String operation) {
        if (operation.equals("USER_CREATE")) {

            System.out.println("Enter login for new user:");
            String login = scanner.nextLine();
            User user = userService.createUser(login);
            System.out.println("User created" + user.toString());
        }
        else if(operation.equals("SHOW_ALL_USERS")) {
            List<User> users = userService.getAllUsers();
            System.out.println("List of users:");
            users.forEach(System.out::println);

        } else if (operation.equals("ACCOUNT_CREATE")) {

            System.out.println("Enter the user id for which to create an account:");
            int userId = Integer.parseInt(scanner.nextLine());
            var user = userService.findUserById(userId)
                            .orElseThrow(() -> new IllegalArgumentException("No such user with id=%s"
                                    .formatted(userId)));
            var account = accountService.createAccount(user);
            System.out.println("New account created with Id: %s for user: %s"
                    .formatted(account.getId(), user.getLogin()));
        } else {


            System.out.println("Invalid operation\n");

        }
    }
}

