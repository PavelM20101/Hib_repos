package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        // реализуйте алгоритм здесь
        userService.createUsersTable();

        userService.saveUser("Gennady", "Roy", (byte) 23);
        userService.saveUser("Vladimir", "Chaykov", (byte) 25);
        userService.saveUser("Marat", "Mavlid", (byte) 33);
        userService.saveUser("Vasily", "Prokofiev", (byte) 29);

        userService.removeUserById(15);

        userService.getAllUsers();

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
