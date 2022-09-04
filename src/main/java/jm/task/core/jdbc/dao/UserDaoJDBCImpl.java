package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable(){
        try(Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS tables.users ("
                    + "id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807), "
                    + "name VARCHAR(45) NOT NULL, "
                    + "lastName VARCHAR(45) NOT NULL, "
                    + "age SMALLINT NOT NULL, "
                    + "PRIMARY KEY (id))");
            System.out.println("The table was created");
        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        }
    }

    public void dropUsersTable() {
        try(Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS tables.users");
            System.out.println("The table has been deleted");
    } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

        public void saveUser(String name, String lastName, byte age) {
            try(Connection connection = Util.getConnection();
                PreparedStatement statement = connection.prepareStatement("INSERT INTO tables.users "
                + "(name, lastname, age) VALUES (?, ?, ?)")){
                statement.setString(1, name);
                statement.setString(2, lastName);
                statement.setByte(3, age);
                statement.executeUpdate();
                System.out.println("A user has been added");
            } catch (SQLException e){
                System.out.println(e.getMessage());
            }
    }

    public void removeUserById(long id) {
        try(Connection connection = Util.getConnection();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM tables.users WHERE id = ?")) {
            statement.setLong(1, id);
            statement.executeUpdate();
            System.out.println("The user has been deleted");
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("SELECT * FROM tables.users");
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));
                list.add(user);
            }
            System.out.println("A list of users has been received");
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return list;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()){
            statement.execute("DELETE FROM tables.users");
            System.out.println("The user table has been cleared");
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
