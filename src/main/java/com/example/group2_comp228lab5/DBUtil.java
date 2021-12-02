package com.example.group2_comp228lab5;

import java.sql.*;
import java.time.LocalDate;
import java.util.Date;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

public class DBUtil {

    // private values
    private static Connection connection = null;
    private static Statement statement = null;
    private static final String url = "jdbc:oracle:thin:@199.212.26.208:1521:SQLD";
    private static final String username = "COMP122W21_008_P_12";
    //private static final String username = "COMP214F21_010_P_2";
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
    public static void procPlayer(Integer player_id, String first_name, String last_name,
                                String address, String postal_code, String province,
                                Integer phone_number) throws SQLException{
        String sql = "";

        // check if data exists
        if (ValidateTable("player", "player_id", player_id)) {
            sql = "update player set first_name = '" + first_name + "', last_name = '" + last_name + "', address = '" + address + "', postal_code = '" + postal_code + "', province = '" + province + "', phone_number = " + phone_number + " where player_id = " + player_id;
        } else {
            sql = "insert into player values(" + player_id + ", '" + first_name + "', '" + last_name + "', '" + address + "', '" + postal_code + "', '" + province + "', " + phone_number + ")";
        }

        ExecuteQuery(sql);
    }
    // insertPlayerAndGame
    public static void procPlayerAndGame(Integer player_game_id, Integer player_id, Integer game_id, LocalDate playing_date, Integer score) throws SQLException{
        String sql = "";

        // check if data exists
        if (ValidateTable("playerandgame", "player_game_id", player_game_id)) {
            sql = "update playerandgame set player_id = " + player_id + ", game_id = " + game_id + ", playing_date = '" + playing_date + "', score = " + score;
        } else {
            sql = "insert into playerandgame values(" + player_game_id + ", " + player_id + ", " + game_id + ", '" + playing_date + "', " + score + ")";
        }

        ExecuteQuery(sql);
    }

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
