package database;

import com.codecool.shop.exception.DbCreateStructuresException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SqliteJDBCForTests {
    public static void run(Connection connection, String pathToFile) throws DbCreateStructuresException, IOException {
        String s;
        StringBuilder sb = new StringBuilder();
        try {
            FileReader fileReader = new FileReader(new File(pathToFile));
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((s = bufferedReader.readLine()) != null) {
                sb.append(s);
            }
            bufferedReader.close();
            String[] inst = sb.toString().split(";");
            Statement st = connection.createStatement();
            for(Integer i = 0; i<inst.length; i++) {
                if(!inst[i].trim().equals("")) {
                    st.executeUpdate(inst[i]);
                }
            }
        }
        catch(SQLException e) {
            throw new DbCreateStructuresException("Error during creating db structure.");
        }
    }
}