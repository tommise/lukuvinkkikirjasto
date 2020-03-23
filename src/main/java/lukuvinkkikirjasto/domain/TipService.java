
package lukuvinkkikirjasto.domain;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import lukuvinkkikirjasto.dao.TipDao;

public class TipService {
    private TipDao tipDao;

    public TipService(TipDao tipDao) {
        this.tipDao = tipDao;
    }

    public List<Tip> getAll() throws SQLException {
        List<Tip> tips = tipDao.getAll();
        Collections.sort(tips);
        //return tipDao.getAll();
        return tips;
    }
    
    public Tip createTip(Date date, String title, String link, String description) throws SQLException {
       return tipDao.create(date, title, link, description);
    }

    
}
