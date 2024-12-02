package org.example;

import org.example.account.AccountService;
import org.example.user.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
            UserService userService,
            AccountService accountService
    ) {
        return new OperationConsoleListener(scanner,accountService,userService);
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
