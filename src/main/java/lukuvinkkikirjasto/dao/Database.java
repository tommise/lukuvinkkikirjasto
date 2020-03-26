
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
            +   "link VARCHAR(50),\n"
            +   "description VARCHAR(50)\n"      
            +   ");";

        String tagTable = "CREATE TABLE IF NOT EXISTS Tag (\n"
            + "id INTEGER PRIMARY KEY,\n"
            + "tag VARCHAR(50)\n"
            + ");";

        String tipTagTable = "CREATE TABLE IF NOT EXISTS TipTag (\n"
            + "tipid INTEGER NOT NULL,\n"
            + "tagid INTEGER NOT NULL,\n"
            + "FOREIGN KEY(tipid) REFERENCES Tip(id),"
            + "FOREIGN KEY(tagid) REFERENCES Tag(id)"
            + ");";

        Connection connection = getConnection();
        Statement statement = connection.createStatement();         
        statement.execute(tipTable);
        statement.execute(tagTable);
        statement.execute(tipTagTable);


        statement.close();
        connection.close();
    }
}
