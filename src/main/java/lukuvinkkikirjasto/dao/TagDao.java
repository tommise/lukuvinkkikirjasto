package lukuvinkkikirjasto.dao;

import java.sql.SQLException;

import lukuvinkkikirjasto.domain.Tag;

public interface TagDao {
    
    public Tag getOrCreate(String tag) throws SQLException;
    public Tag find(String tag) throws SQLException;
}