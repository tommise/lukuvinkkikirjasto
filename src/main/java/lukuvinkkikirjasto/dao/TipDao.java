
package lukuvinkkikirjasto.dao;

import java.sql.SQLException;
import lukuvinkkikirjasto.domain.Tip;

public interface TipDao {
    
    Tip create(String title, String link) throws SQLException;
}
