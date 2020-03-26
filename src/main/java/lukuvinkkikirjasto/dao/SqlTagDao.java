package lukuvinkkikirjasto.dao;

import java.sql.SQLException;
import java.sql.*;

import lukuvinkkikirjasto.domain.Tag;

public class SqlTagDao implements TagDao {
    private Database database;

    public SqlTagDao(Database database) {
        this.database = database;
    }
    
    public Tag getOrCreate(String tag) throws SQLException {
        Tag newTag = find(tag);
        if (newTag != null) {
            return newTag;
        }   
        create(tag);        
        return find(tag);
    }

    private void create(String tag) throws SQLException{
        Connection connection = database.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Tag (tag) VALUES (?)");
        statement.setString(1, tag);
        statement.executeUpdate();
        statement.close();
        connection.close();
    }
    
    public Tag find(String tag) throws SQLException {
        Connection connection = database.getConnection();
            
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Tag WHERE tag = ?");
        statement.setString(1, tag);

        ResultSet rs = statement.executeQuery();
        
        boolean hasOne = rs.next();
        
        if (!hasOne) {
            rs.close();
            statement.close();
            connection.close();
            return null;
        }
        
        Tag newTag = new Tag(rs.getInt("id"), rs.getString("tag"));
        
        rs.close();
        statement.close();
        connection.close();

        return newTag;
    }
    
}