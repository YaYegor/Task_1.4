package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Yegor", "Strakhov", (byte) 21);
        userService.saveUser("Harrier", "DuBois", (byte) 44);
        userService.saveUser("Tequila", "Sunset", (byte) 44);
        userService.saveUser("Kras", "Mazov", (byte) 44);
        System.out.println(userService.getAllUsers().toString());
        userService.cleanUsersTable();
        userService.dropUsersTable();// реализуйте алгоритм здесь
    }
}
