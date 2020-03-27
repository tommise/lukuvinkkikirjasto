
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
        //return tipDao.getAll();
        return tips;
    }
    
    public Tip createTip(Date date, String title, String link, String description, String tagsString) throws SQLException {
        List<String> tags = new ArrayList<>();
        tags.add(tagsString);
        Tip newTip = tipDao.create(date, title, link, description);

        for (String tag : tags) {
            Tag newTag = tagDao.getOrCreate(tag);
            newTip.addTag(newTag);
            this.tipDao.addTagForTip(newTip.getId(), newTag.getId());
        }

        return newTip;
    }

    
}
