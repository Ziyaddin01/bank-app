package org.example.operations.processors;

import org.example.account.AccountService;
import org.example.operations.OperationCommandProcessor;
import org.example.user.UserService;

import java.util.Scanner;

public class CreateAccountProcessor implements OperationCommandProcessor {

    private final UserService userService;
    private final AccountService accountService;
    private final Scanner scanner;

    @Override
    public void processOperation() {
        System.out.println("Enter the user id for which to create an account:");
        int userId = Integer.parseInt(scanner.nextLine());
        var user = userService.findUserById(userId)
                .orElseThrow(() -> new IllegalArgumentException("No such user with id=%s"
                        .formatted(userId)));
        var account = accountService.createAccount(user);
        user.getAccountList().add(account);

        System.out.println("New account created with Id: %s for user: %s"
                .formatted(account.getId(), user.getLogin()));
    }

    public CreateAccountProcessor(UserService userService, AccountService accountService, Scanner scanner) {
        this.userService = userService;
        this.accountService = accountService;
        this.scanner = scanner;
    }
}
