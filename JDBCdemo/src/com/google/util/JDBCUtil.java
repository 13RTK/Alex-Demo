package com.google.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Properties;

/**
 * This class offers some methods to operate the "student" table.
 * @author Alex_John
 * @version 1.0
 */
public class JDBCUtil {
    public static String drive;
    public static String url;
    public static String user;
    public static String password;
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static String checkInput;

    static {
        try {
            InputStream inputStream = ClassLoader.getSystemResourceAsStream("db.properties");
            Properties properties = new Properties();

            properties.load(inputStream);
            drive = properties.getProperty("driver");
            url = properties.getProperty("url");
            user = properties.getProperty("user");
            password = properties.getProperty("password");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get a connection object.
     * @return a connection with database
     * @throws SQLException
     */
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    /**
     * Get a statement object.
     * @return a statement with database
     * @throws SQLException
     */
    private static Statement getStatement() throws SQLException {
        return getConnection().createStatement();
    }

    /**
     * Get all the data in table "student", and print them.
     * @throws SQLException
     */
    public static void queryAll() throws SQLException {
        ResultSet resultSet = getStatement().executeQuery("SELECT * from student");

        while (resultSet.next()) {
            int studentID = resultSet.getInt(1);
            String studentName = resultSet.getString(2);
            String studentAddress = resultSet.getString(3);

            System.out.println("Student ID: " + studentID);
            System.out.println("Student Name: " + studentName);
            System.out.println("Student Address: " + studentAddress);
            System.out.println("===================================");
        }
    }

    /**
     * Check the param if it is a number.
     * @param input the param needs to check
     * @return a correct number
     * @throws Exception
     */
    public static int isNumber(String input) throws Exception {
        for (int i = 0; i < input.length(); i++) {
            if (!Character.isDigit(input.charAt(i))) {
                System.out.println("Please enter number!");
                input = reader.readLine();
                isNumber(input);
            }
        }

        return Integer.parseInt(input);
    }

    /**
     * Check the param if it is a string.
     * @param input the param needs to check
     * @throws Exception
     */
    public static void isString(String input) throws Exception {
        if (input == null) {
            System.out.println("Please enter again!");
            input = reader.readLine();
            isString(input);
        }
    }

    /**
     * Update the specified data by the user.
     * @return The result of this operation.
     * @throws Exception
     */
    public static String update() throws Exception {
        int oldId;
        int newId;
        String newName;
        String newAddress;

        String result = "";
        System.out.println("Please enter the id of the update data: ");
        checkInput = reader.readLine();
        oldId = isNumber(checkInput);

        System.out.println("Please set the new id: ");
        checkInput = reader.readLine();
        newId = isNumber(checkInput);

        System.out.println("Please set the new name: ");
        checkInput = reader.readLine();
        isString(checkInput);
        newName = checkInput;

        System.out.println("Please set the new address: ");
        checkInput = reader.readLine();
        isString(checkInput);
        newAddress = checkInput;

        int executeUpdate = getStatement().executeUpdate("update student set id=" + newId + ", name='" + newName + "', address='" + newAddress + "' where id =" + oldId + ";");

        result = executeUpdate > 0 ? "Update success" : "Update failed";

        return result;
    }

    /**
     * Delete the specified data by the user.
     * @return The result of this operation.
     * @throws Exception
     */
    public static String delete() throws Exception {
        int id;
        String result = "";

        System.out.println("Please enter the id of the delete data: ");
        checkInput = reader.readLine();

        id = isNumber(checkInput);
        int executeUpdate = getStatement().executeUpdate("delete from student where id=" + id + ";");

        result = executeUpdate > 0 ? "Delete success" : "Delete failed";

        return result;
    }

    /**
     * Insert the specified data by the user.
     * @return The result of this operation.
     * @throws Exception
     */
    public static String insert() throws Exception {
        int id;
        String name;
        String address;

        System.out.println("Please enter the id of the insert data: ");
        checkInput = reader.readLine();
        id = isNumber(checkInput);

        System.out.println("Please enter the name of the insert data: ");
        checkInput = reader.readLine();
        isString(checkInput);
        name = checkInput;

        System.out.println("Please enter the address of the insert data: ");
        checkInput = reader.readLine();
        isString(checkInput);
        address = checkInput;


        String result = "";

        int rows = 0;
        try {
            rows = getStatement().executeUpdate("insert into student (id, name, address) values(" + id + ", '" + name + "', '" + address + "');");
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        System.out.println("rows = " + rows);
        result = rows > 0 ? "Insert success" : "Insert failed";

        return result;
    }

//    public static void close(Connection connection, Statement statement, ResultSet resultSet) throws SQLException {
//        if (connection != null) {
//            connection.close();
//        }
//
//        if (statement != null) {
//            statement.close();
//        }
//
//        if (resultSet != null) {
//            resultSet.close();
//        }
//    }
}