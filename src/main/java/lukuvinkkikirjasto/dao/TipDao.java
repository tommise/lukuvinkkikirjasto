
package lukuvinkkikirjasto.dao;

import java.sql.SQLException;
import java.util.List;
import lukuvinkkikirjasto.domain.Tip;

public interface TipDao {
    
    Tip create(String title, String link) throws SQLException;
    
    List<Tip> getAll() throws SQLException;
}
