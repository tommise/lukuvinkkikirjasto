
package lukuvinkkikirjasto.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import lukuvinkkikirjasto.domain.Tip;

public interface TipDao {
    
    Tip create(Date date, String title, String link, String description) throws SQLException;
    
    Tip findByTitle(String title) throws SQLException;
    
    List<Tip> getAll() throws SQLException;

    void addTagForTip(int tipId, int tagId) throws SQLException;
    
    boolean deleteTip(int id) throws SQLException;
    

}
