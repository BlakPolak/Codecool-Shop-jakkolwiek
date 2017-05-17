package com.codecool.shop.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SqliteJDBCConnector {

    public static Connection connection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/database.db");
        } catch (SQLException e) {
            System.out.println("Connection to DB failed");
        }

        return connection;
    }

    public static Connection connectToDb() throws SQLException {
        System.out.println("Connection to DB...");
        return DriverManager.getConnection("jdbc:sqlite:src/main/resources/database.db");
    }

    public static void runSql(Connection c, String path) throws SQLException {
        String s = new String();
        StringBuffer sb = new StringBuffer();
        try {
            FileReader fr = new FileReader(new File(path));
            BufferedReader br = new BufferedReader(fr);
            while((s = br.readLine()) != null) {
                sb.append(s);
            }
            br.close();
            String[] inst = sb.toString().split(";");
            Statement st = c.createStatement();
            for(int i = 0; i<inst.length; i++) {
                if(!inst[i].trim().equals("")) {
                    st.executeUpdate(inst[i]);
                    System.out.println(">>"+inst[i]);
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("*** Error : "+e.toString());
            e.printStackTrace();
            System.out.println("################################################");
            System.out.println(sb.toString());
        }
    }

}