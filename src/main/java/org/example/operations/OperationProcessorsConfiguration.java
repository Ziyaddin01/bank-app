package org.example.operations;

import org.example.account.AccountService;
import org.example.operations.processors.*;
import org.example.user.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class OperationProcessorsConfiguration {

    @Bean
    public CreateUserProcessor createUserProcessor(
            Scanner scanner,
            UserService userService
    ) {
        return new CreateUserProcessor(userService, scanner);
    }

    @Bean
    public CreateAccountProcessor createAccountProcessor(
            Scanner scanner,
            AccountService accountService,
            UserService userService

    ) {
        return new CreateAccountProcessor(userService, accountService, scanner);
    }
    @Bean
    public ShowAllUsersProcessor showAllUsersProcessor(
            UserService userService
    ) {
        return new ShowAllUsersProcessor(userService);
    }
    @Bean
    public CloseAccountProcessor closeAccountProcessor(
            UserService userService
    ) {
        return new CloseAccountProcessor(userService);
    }
    @Bean
    public DepositAccountProcessor depositAccountProcessor(
            Scanner scanner,
            AccountService accountService
    ) {
        return new DepositAccountProcessor(scanner, accountService);
    }
    @Bean
    public AccountTransferProcessor transferAccountProcessors(
            UserService userService
    ) {
        return new AccountTransferProcessor(userService);
    }
    @Bean
    public AccountWithdrawProcessor withDrawAccountProcessors(
            UserService userService
    ) {
        return new AccountWithdrawProcessor(userService);
    }
}
