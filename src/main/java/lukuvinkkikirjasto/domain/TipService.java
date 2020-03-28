
package lukuvinkkikirjasto.domain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import lukuvinkkikirjasto.dao.TagDao;
import lukuvinkkikirjasto.dao.TipDao;

public class TipService {
    private TipDao tipDao;
    private TagDao tagDao;

    public TipService(TipDao tipDao, TagDao tagDao) {
        this.tipDao = tipDao;
        this.tagDao = tagDao;
    }

    public List<Tip> getAll() throws SQLException {
        List<Tip> tips = tipDao.getAll();
        Collections.sort(tips);
        
        return tips;
    }
    
    
    public Tip createTip(Date date, String title, String link, String description, String tagsString) throws SQLException {
        String[] arr = tagsString.split(", ");
        
        Tip newTip = tipDao.create(date, title, link, description);

        for (String tag : arr) {
            Tag newTag = tagDao.getOrCreate(tag);
            newTip.addTag(newTag);
            this.tipDao.addTagForTip(newTip.getId(), newTag.getId());
        }

        return newTip;
    }
    
    public boolean deleteTip(int tipId) throws SQLException {
        List<Tip> tips = tipDao.getAll();
        boolean result = false;
        
        for (Tip tip : tips) {
            if (tip.getId() == tipId) {
                result = true;
                this.tipDao.deleteTip(tipId);
            }
        }
        
        return result;
    }

    
}
