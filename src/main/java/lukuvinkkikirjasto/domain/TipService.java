
package lukuvinkkikirjasto.domain;

import java.sql.SQLException;

import lukuvinkkikirjasto.dao.TipDao;

public class TipService {
  private TipDao tipDao;

  public TipService(TipDao tipDao) {
    this.tipDao = tipDao;
  }

  public Tip createTip(String title, String link) {
    if (!tipTitleIsValid(title)) {
      return null;
    }

    try {
      Tip tip = tipDao.create(title, link);
      return tip;
    } catch (SQLException e) {
      e.printStackTrace();
      return null; 
    }       
  }


  private boolean tipTitleIsValid(String title){
    return title.length() > 0;
  }
}
