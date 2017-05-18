package com.codecool.shop.dao;

import com.codecool.shop.exception.DbCreateStructuresException;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SqliteJDBCConnector {

    public static Connection connectToDb() throws SQLException {
        System.out.println("Connection to DB...");
        return DriverManager.getConnection("jdbc:sqlite:src/main/resources/database.db");
    }

    public static void runSql(Connection c, String path) throws DbCreateStructuresException, IOException {
        String s;
        StringBuilder sb = new StringBuilder();
        try {
            FileReader fr = new FileReader(new File(path));
            BufferedReader br = new BufferedReader(fr);
            while((s = br.readLine()) != null) {
                sb.append(s);
            }
            br.close();
            String[] inst = sb.toString().split(";");
            Statement st = c.createStatement();
            for(Integer i = 0; i<inst.length; i++) {
                if(!inst[i].trim().equals("")) {
                    st.executeUpdate(inst[i]);
                    System.out.println(">>"+inst[i]);
                }
            }
        }
        catch(SQLException e) {
            throw new DbCreateStructuresException("Error during creating db structure.");
        }
    }
}