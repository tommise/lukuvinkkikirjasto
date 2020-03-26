
package lukuvinkkikirjasto.dao;

import java.sql.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import lukuvinkkikirjasto.domain.Tag;
import lukuvinkkikirjasto.domain.Tip;

public class SqlTipDao implements TipDao {
    
    private Database database;
    
    public SqlTipDao(Database database) {
        this.database = database;
    }
     
    @Override
    public Tip create(Date date, String title, String link, String description) throws SQLException {
        Connection connection = database.getConnection();  
        
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Tip (date, title, link, description) VALUES (?, ?, ?, ?)");

        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        
        statement.setDate(1, sqlDate);
        statement.setString(2, title);
        statement.setString(3, link);
        statement.setString(4, description);

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
        
        Tip tip = new Tip(rs.getDate("date"), rs.getString("title"), rs.getString("link"),  rs.getString("description"), rs.getInt("id"));
        

        rs.close();
        statement.close();
        connection.close();
        
        tip.setTags(getTags(tip.getTitle()));

        return tip;
    }

    public List<Tag> getTags(String tipTitle) throws SQLException{
        List<Tag> tags = new ArrayList<>();

        Connection connection = database.getConnection();
         
        String query = "SELECT Tag.id, Tag.tag FROM Tag\n"
            + "JOIN TipTag ON TipTag.tagid = tag.id\n"
            + "JOIN Tip ON Tip.id = TipTag.tipid\n"
            + "WHERE Tip.title = ?;";
           
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, tipTitle);

        ResultSet rs = statement.executeQuery();
        
        while (rs.next()) {
            tags.add(new Tag(rs.getInt("id"), rs.getString("tag")));
        }
        
        rs.close();
        statement.close();        
        connection.close();

        return tags;
    }

    @Override
    public List<Tip> getAll() throws SQLException {
        List<Tip> tips = new ArrayList<>();
        
        Connection connection = database.getConnection();
            
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Tip");

        ResultSet rs = statement.executeQuery();
        
        while (rs.next()) {
            Tip t = new Tip(rs.getDate("date"), rs.getString("title"), rs.getString("link"),  rs.getString("description"), rs.getInt("id"));
            t.setTags(getTags(t.getTitle()));
            tips.add(t);
        }
        
        rs.close();
        statement.close();
        connection.close();
        
        return tips;
    }

    @Override
    public void addTagForTip(int tipId, int tagId) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO TipTag (tipid, tagid) VALUES (?, ?)");
        statement.setInt(1, tipId);
        statement.setInt(2, tagId);
        statement.executeUpdate();
        statement.close();
        connection.close();
    }
}
