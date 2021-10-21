package com.google.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * This class offers the menu of the management system.
 * You can CRUD the "student" table by the "menu" class.
 * @author Alex_John
 * @version 1.0
 */
public class Menu {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static String name;
    private static String password;

    /**
     * This method offers a welcome interface, then check your username and password by invoking the method "checkUser".
     * Finally, invoke the method "chooseFeature".
     * @throws Exception
     */
    public static void welcome() throws Exception {
        System.out.println("Welcome to student management system!");
        boolean checkUser = checkUser();
        while (!checkUser) {
            System.out.println("Please make sure your username and password are correct!");
            checkUser = checkUser();
        }

        System.out.println("Welcome " + name + "!\n");
        chooseFeature();
    }

    /**
     * After one execution, this method will ask you whether continue to execute the next execution.
     * @return if you enter "n" the "result" will be set 0, then the system will offline, if you enter "y" the "result" will be set -1, then reload the "chooseFeature" method.
     * @throws Exception
     */
    public static int isContinue() throws Exception {
        int result;
        String input;
        System.out.println("Are you want to continue?(y/n)");
        input = reader.readLine();

        while (!input.equals("y") && !input.equals("n")) {
            System.out.println("illegal enter! please try again!");
            input = reader.readLine();
        }

        if (input.equals("n")) {
            result = 0;
        } else {
            result = -1;
        }


        return result;
    }

    /**
     * Print all options, and get the user's input for using relevant functions.
     * After each execution, invoke the "isContinue" method to judge that if the user wants to execute the next.
     * @throws Exception
     */
    public static void chooseFeature() throws Exception {
        System.out.println("What would you want to do?");
        System.out.println("1.Check All   2.Delete Data   3.Update Data   4.Add Data   0.Exit");

        System.out.println("Please enter the invalid number: ");
        JDBCUtil.checkInput = reader.readLine();
        int input = JDBCUtil.isNumber(JDBCUtil.checkInput);

        while (input != 0) {
            switch (input) {

                case 1:
                    JDBCUtil.queryAll();
                    System.out.println("=======================");
                    input = isContinue();
                    break;

                case 2:
                    System.out.println(JDBCUtil.delete());
                    System.out.println("=======================");
                    input = isContinue();
                    break;

                case 3:
                    System.out.println(JDBCUtil.update());
                    System.out.println("=======================");
                    input = isContinue();
                    break;

                case 4:
                    System.out.println(JDBCUtil.insert());
                    System.out.println("=======================");
                    input = isContinue();
                    break;

                case -1:
                    chooseFeature();
                    break;

                default:
                    System.out.println("Wrong enter please check your input!");
                    chooseFeature();
                    break;
            }
        }

        System.out.println("Exit!");
        System.exit(1);

    }

    /**
     * Check the enter with the correct username and password.
     * @return a boolean value that stands for the check result.
     * @throws IOException
     */
    private static boolean checkUser() throws IOException {
        System.out.println("Please enter your username: ");
        name = reader.readLine();
        System.out.println("Please enter your password: ");
        password = (reader.readLine());

        if (name.equals(JDBCUtil.user) && password.equals(JDBCUtil.password)) {
            return true;
        }

        return false;
    }
}