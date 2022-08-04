package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserDao userDao = new UserDaoJDBCImpl();
        userDao.createUsersTable();
        userDao.saveUser("Vasya", "Ivanov", (byte) 5);
        userDao.saveUser("Masha", "Katina", (byte) 10);
        userDao.saveUser("Nic", "Barinov", (byte) 15);
        userDao.saveUser("Alina", "Katova", (byte) 20);
        userDao.removeUserById(2L);
        List<User> userList = userDao.getAllUsers();
        for (User u : userList) {
            System.out.println(u);
        }
        userDao.cleanUsersTable();
        userDao.dropUsersTable();


//        UserService userService = new UserServiceImpl();
//        userService.dropUsersTable();
//        userService.createUsersTable();
//        userService.saveUser("testName", "testLastName", (byte) 19);
//        List<User> userList = userService.getAllUsers();
//        for (User user:userList) {
//            System.out.println(user);
//        }
//        userService.cleanUsersTable();

    }
}
