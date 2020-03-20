
package lukuvinkkikirjasto.dao;

import java.sql.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import lukuvinkkikirjasto.domain.Tip;

public class SqlTipDao implements TipDao {
    
    private Database database;
    
    public SqlTipDao(Database database) {
        this.database = database;
    }
     
    @Override
    public Tip create(Date date, String title, String link) throws SQLException {
        Connection connection = database.getConnection();  
        
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Tip (date, title, link) VALUES (?, ?, ?)");

        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        
        statement.setDate(1, sqlDate);
        statement.setString(2, title);
        statement.setString(3, link);

        statement.executeUpdate();

        statement.close();
        connection.close();

        return findByTitle(title);
    }
    
    @Override
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
        
        Tip tip = new Tip(rs.getDate("date"), rs.getString("title"), rs.getString("link"), rs.getInt("id"));
        
        rs.close();
        statement.close();
        connection.close();

        return tip;
    }

    @Override
    public List<Tip> getAll() throws SQLException {
        List<Tip> tips = new ArrayList<>();
        
        Connection connection = database.getConnection();
            
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Tip");

        ResultSet rs = statement.executeQuery();
        
        while (rs.next()) {
            Tip t = new Tip(rs.getDate("date"), rs.getString("title"), rs.getString("link"), rs.getInt("id"));
            tips.add(t);
        }
        
        rs.close();
        statement.close();
        connection.close();
        
        return tips;
    }

    
    
}
