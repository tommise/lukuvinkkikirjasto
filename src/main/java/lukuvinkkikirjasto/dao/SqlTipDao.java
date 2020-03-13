
package lukuvinkkikirjasto.dao;

import java.sql.*;
import lukuvinkkikirjasto.domain.Tip;

public class SqlTipDao implements TipDao {
    
    private Database database;
    
    public SqlTipDao(Database database) {
        this.database = database;
    }
       
    public Tip create(String title, String link) throws SQLException {
        Connection connection = database.getConnection();  
        
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Tip (title, link) VALUES (?, ?)");

        statement.setString(1, title);
        statement.setString(2, link);

        statement.executeUpdate();

        statement.close();
        connection.close();

        return findByTitle(title);
    }
    
    public Tip findByTitle(String title) throws SQLException {
        Connection connection = database.getConnection();
            
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Tip WHERE title = ?");
        statement.setString(1, title);

        ResultSet rs = statement.executeQuery();
        
        boolean hasOne = rs.next();
        
        if (!hasOne) {
            rs.close();
            statement.close();
            connection.close();
            return null;
        }
        
        Tip tip = new Tip(rs.getString("title"), rs.getString("link"), rs.getInt("id"));
        
        rs.close();
        statement.close();
        connection.close();

        return tip;
    }
    
}
