
package lukuvinkkikirjasto.domain;

import java.sql.SQLException;
import java.util.List;

import lukuvinkkikirjasto.dao.TipDao;

public class TipService {
    private TipDao tipDao;

    public TipService(TipDao tipDao) {
        this.tipDao = tipDao;
    }

    //TYÖN ALLA
    public List<Tip> getAll() throws SQLException {
        return tipDao.getAll();
    }
    
    public Tip createTip(String title, String link) {
        /*
        if (!tipTitleIsValid(title)) {
            return null;
        }
        */
        try {
            return tipDao.create(title, link);
        } catch (SQLException e) {
            return null; 
        }       
    }

    /*
    private boolean tipTitleIsValid(String title){
        return title.length() > 0;
    }
    */
}
