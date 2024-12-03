package org.example;

import org.example.account.AccountService;
import org.example.operations.ConsoleOperationType;
import org.example.operations.OperationCommandProcessor;
import org.example.operations.processors.CreateAccountProcessor;
import org.example.operations.processors.CreateUserProcessor;
import org.example.operations.processors.ShowAllUsersProcessor;
import org.example.user.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }
    @Bean
    public OperationConsoleListener operationConsoleListener(
            Scanner scanner,
            CreateUserProcessor createUserProcessor,
            CreateAccountProcessor createAccountProcessor,
            ShowAllUsersProcessor showAllUsersProcessor
    ) {
        Map<ConsoleOperationType, OperationCommandProcessor> map =
                Map.of(
                        ConsoleOperationType.USER_CREATE, createUserProcessor,
                        ConsoleOperationType.ACCOUNT_CREATE, createAccountProcessor,
                        ConsoleOperationType.SHOW_ALL_USERS, showAllUsersProcessor
        );
        return new OperationConsoleListener(scanner, map);
    }

    @Bean
    public UserService userService(
            AccountService accountService
    ) {
        return new UserService(accountService);
    }

    @Bean
    public AccountService accountService() {
        return new AccountService();
    }
}
