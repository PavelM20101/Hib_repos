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
        userService.saveUser("Alexander", "Vortov", (byte) 23);
        userService.saveUser("Pavel", "Mordvinov", (byte) 22);
        userService.saveUser("Eugeny", "Kalinichev", (byte) 24);
        userService.saveUser("Victor", "Martynov", (byte) 24);
//        userService.removeUserById(15);
//
//        userService.getAllUsers();
//
//        userService.cleanUsersTable();
//
//        userService.dropUsersTable();
    }
}
