package jm.task.core.jdbc.util;



import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {
    private static final String url = "jdbc:postgresql://localhost:5432/users_tables";
    private static final String username = "postgres";
    private static final String password = "12345";
    private static final String dialect = "org.hibernate.dialect.PostgresSQLDialect";
    private static final String driver = "org.postgresql.Driver";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public static SessionFactory getSessionFactory() {
        Configuration cfg = new Configuration()
                .setProperty("hibernate.connection.url", url)
                .setProperty("dialect", dialect)
                .setProperty("hibernate.connection.username", username)
                .setProperty("hibernate.connection.password", password)
                .setProperty("hibernate.connection.driver_class", driver);
        cfg.addAnnotatedClass(User.class);
        ServiceRegistry serviceRegistryBuilder = new StandardServiceRegistryBuilder()
                .applySettings(cfg.getProperties())
                .build();
        return cfg.buildSessionFactory(serviceRegistryBuilder);
    }
}
