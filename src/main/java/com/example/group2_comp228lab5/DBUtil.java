package com.example.group2_comp228lab5;

import java.sql.*;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

public class DBUtil {

    // private values
    private static Connection connection = null;
    private static Statement statement = null;
    private static final String url = "jdbc:oracle:thin:@199.212.26.208:1521:SQLD";
    private static final String username = "COMP122W21_008_P_12";
    private static final String password = "password";

    public static void dbConnect() throws SQLException{
        connection = DriverManager.getConnection(url, username, password);
        System.out.println("DB is connected");

        statement = connection.createStatement();
    }

    public static void dbDisconnect() throws SQLException{
        if (connection != null && !connection.isClosed()) {
            connection.close();
            System.out.println("DB is closed");
        }
    }

/*
    public static void dropTable(String table) throws  SQLException{
        dbConnect();

        String sql = "drop table " + table;
        statement.execute(sql);
        System.out.println(table + " is dropped");

        if (statement != null) {
            statement.close();
        }

        dbDisconnect();

    }
*/

/*

    public static void createTable(String table) throws  SQLException{
        dbConnect();

        String sql = "create table " + table + " (game_id number primary key, game_title varchar2(100))";
        statement.execute(sql);
        System.out.println(table + " is created");

        if (statement != null) {
            statement.close();
        }

        dbDisconnect();
    }
*/

    public static void procGame(Integer game_id, String game_title) throws SQLException{
        String sql = "";

        // check if data exists
        if (ValidateTable("game", "game_id", game_id)) {
            sql = "update game set game_title = '" + game_title + "' where game_id = " + game_id;
        } else {
            sql = "insert into game values(" + game_id + ", '" + game_title + "')";
        }

        ExecuteQuery(sql);
    }

    // insertPlayer

    // insertPlayerAndGame

    public static ResultSet query(String query) throws SQLException{
        CachedRowSet crs = RowSetProvider.newFactory().createCachedRowSet();

        dbConnect();

        ResultSet resultSet = statement.executeQuery(query);
        crs.populate(resultSet);

        if (statement != null) {
            System.out.println("Result: " + crs.size() + " data are found");
            statement.close();
        }

        dbDisconnect();

        return crs;
    }

    public static void delete(String table, String id_name, Integer id) throws SQLException{
        dbConnect();

        String sql = "delete from " + table + " where " + id_name + " = " + id;
        statement.executeUpdate(sql);

        if (statement != null) {
            System.out.println("Data is deleted");
            statement.close();
        }

        dbDisconnect();
    }

    private static boolean ValidateTable(String tableName, String idName, Integer id) throws  SQLException {
        // search data
        ResultSet rs = query("select * from " + tableName);

        while (rs.next()) {
            if (rs.getInt(idName) == id) {
                return true;
            }
        }

        return false;
    }

    private static void ExecuteQuery(String sql) throws SQLException{
        // insert or update
        dbConnect();

        statement.executeUpdate(sql);

        if (statement != null) {
            System.out.println("Succeeded: " + sql);
            statement.close();
        }

        dbDisconnect();
    }
}
