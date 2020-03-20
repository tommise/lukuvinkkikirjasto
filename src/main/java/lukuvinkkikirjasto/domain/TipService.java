
package lukuvinkkikirjasto.domain;

import java.sql.SQLException;
import java.util.List;

import lukuvinkkikirjasto.dao.TipDao;

public class TipService {
    private TipDao tipDao;

    public TipService(TipDao tipDao) {
        this.tipDao = tipDao;
    }

    public List<Tip> getAll() throws SQLException {
        return tipDao.getAll();
    }
    
    public Tip createTip(String title, String link) throws SQLException {
       return tipDao.create(title, link);
    }

    
}
