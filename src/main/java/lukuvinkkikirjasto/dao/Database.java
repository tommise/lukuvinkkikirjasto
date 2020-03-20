
package lukuvinkkikirjasto.dao;

import java.sql.*;
import java.util.*;
import java.util.logging.*;

public class Database {
    
    private String databaseAddress;
    
    public Database(String databaseAddress) throws SQLException, ClassNotFoundException {
        this.databaseAddress = databaseAddress;
    }
    
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseAddress);
    }
    
    public void createTables() throws SQLException {
        String tipTable = "CREATE TABLE IF NOT EXISTS Tip (\n"
            +   "id INTEGER PRIMARY KEY,\n"
            +   "date DATE NOT NULL,\n"    
            +   "title VARCHAR(50) NOT NULL,\n"
            +   "link VARCHAR(50)\n"      
            +   ");";

        Connection connection = getConnection();
        Statement statement = connection.createStatement();         
        statement.execute(tipTable);

        statement.close();
        connection.close();
    }
}
