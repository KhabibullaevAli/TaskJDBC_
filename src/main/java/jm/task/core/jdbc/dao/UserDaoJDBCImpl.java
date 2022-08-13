package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection;

    public UserDaoJDBCImpl() {
        connection = Util.getConnection();
    }

    public void createUsersTable() {
        try (PreparedStatement prepStat = connection.prepareStatement("CREATE TABLE IF NOT EXISTS `pp_bd_113`.`users` " +
                    "(`id` INT NOT NULL AUTO_INCREMENT," +
                    "`name` VARCHAR(45) NOT NULL," +
                    "`lastName` VARCHAR(45) NOT NULL," +
                    "`age` INT(3) NOT NULL, " +
                    "PRIMARY KEY (`id`), UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)")) {
            prepStat.execute();
        } catch (SQLException throwables) {
            System.out.println("Cant create UsersTable");
        }
    }

    public void dropUsersTable() {
        try (PreparedStatement prepStat = connection.prepareStatement("DROP TABLE `users`")) {
            prepStat.execute();
        } catch (SQLException throwables) {
            System.out.println("Cant dropUsersTable");
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement prepStatAddingUser = connection.prepareStatement("insert into users (name, lastName, age) values (?,?,?)")) {
            prepStatAddingUser.setString(1, name);
            prepStatAddingUser.setString(2, lastName);
            prepStatAddingUser.setInt(3, age);
            prepStatAddingUser.execute();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException throwables) {
            System.out.println("Cant saveUser");
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement prepStatRemovingUser = connection.prepareStatement("delete from users where id = ?")) {
            prepStatRemovingUser.setLong(1, id);
            prepStatRemovingUser.execute();
        } catch (SQLException throwables) {
            System.out.println("Cant removeUserById");
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new LinkedList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from users");
            while (resultSet.next()) {
                User user = new User();
                user.setId((long) resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge((byte) resultSet.getInt(4));
                list.add(user);
            }
        } catch (SQLException throwables) {
            System.out.println("cant getAllUsers");
        }
        return list;
    }

    public void cleanUsersTable() {
        try (PreparedStatement preparedStatement = connection.prepareStatement("truncate table users")) {
            preparedStatement.execute();
        } catch (SQLException throwables) {
            System.out.println("Cant cleanUsersTable");
        }
    }
}
