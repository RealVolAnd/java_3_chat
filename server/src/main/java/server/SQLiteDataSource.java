package server;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLiteDataSource implements DataSource {
    private static Connection connection;
    private static Statement stmt;
    private static PreparedStatement psInsert;
    private static PreparedStatement psUpdate;

    public SQLiteDataSource() {
        try {
            connect();
            System.out.println("Connection to SQLite DB is OK!");
            prepareAllStatements();
            //fillDB();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    private static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:main_db.db");
        stmt = connection.createStatement();
    }

    private static void disconnect() {
        try {
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static void clearTable() {
        try {
            stmt.executeUpdate("DELETE FROM users;");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void fillDB() {
        try {
            connection.setAutoCommit(false);
            for (int i = 1; i <= 10; i++) {
                psInsert.setString(1, "login" + i);
                psInsert.setString(2, "pass" + i);
                psInsert.setString(3, "nick" + i);
                psInsert.addBatch();
            }
            psInsert.executeBatch();
            connection.setAutoCommit(true);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<UserData> getUsersData() {
        List<UserData> result = new ArrayList<>();
        try {
            ResultSet rs = stmt.executeQuery("SELECT login,password,nickname FROM users");
            while (rs.next()) {
                result.add(new UserData(rs.getString("login"), rs.getString("password"), rs.getString("nickname")));
            }
            rs.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public int changeUserNick(String login, String newNick) {
        try {
            psUpdate.setString(1, newNick);
            psUpdate.setString(2, login);
            psUpdate.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 1;
        }
        return 0;
    }

    @Override
    public int putUserData(UserData userdata) {
        try {
            psInsert.setString(1, userdata.login);
            psInsert.setString(2, userdata.password);
            psInsert.setString(3, userdata.nickname);
            psInsert.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 1;
        }
        return 0;
    }

    private static void prepareAllStatements() throws SQLException {
        psInsert = connection.prepareStatement("INSERT INTO users (login, password, nickname) VALUES (?, ?, ?);");
        psUpdate = connection.prepareStatement("UPDATE users SET nickname = ? WHERE login= ?;");
    }
}
