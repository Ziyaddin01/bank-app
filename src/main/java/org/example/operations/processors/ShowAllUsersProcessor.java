package org.example.operations.processors;

import org.example.operations.OperationCommandProcessor;
import org.example.user.User;
import org.example.user.UserService;

import java.util.List;

public class ShowAllUsersProcessor implements OperationCommandProcessor {

    private final UserService userService;

    @Override
    public void processOperation() {
        List<User> users = userService.getAllUsers();
        System.out.println("List of users:");
        users.forEach(System.out::println);
    }

    public ShowAllUsersProcessor(UserService userService) {
        this.userService = userService;
    }
}