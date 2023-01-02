package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//        Class<?> aClass = Class.forName("com.mysql.jdbc.Driver");
//        System.out.println(aClass.getName());

        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306", "root", ""
        );
    }
}
