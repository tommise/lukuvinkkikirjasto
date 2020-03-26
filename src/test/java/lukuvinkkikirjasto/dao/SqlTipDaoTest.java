
package lukuvinkkikirjasto.dao;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import lukuvinkkikirjasto.domain.Tag;
import lukuvinkkikirjasto.domain.Tip;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

public class SqlTipDaoTest {
    
    TipDao tipDao;
    Database database;
    
    @Before
    public void setUp() throws Exception {
       Files.deleteIfExists(Paths.get("vinkkitietokanta-test.db"));
       database = new Database("jdbc:sqlite:vinkkitietokanta-test.db"); 
       database.createTables();        
       this.tipDao = new SqlTipDao(database);
    }

    @Test
    public void creatingANewTipCreatesTheTip() throws Exception {
        Date date = new Date();
        Tip newTip = this.tipDao.create(date, "title", "link", "test-description");
        assertNotNull(newTip);
        assertEquals(date, newTip.getDate());
        assertEquals("title", newTip.getTitle());
        assertEquals("link", newTip.getLink());
        assertEquals("test-description", newTip.getDescription());
    }

    @Test
    public void TipFromDatabaseHasTags() throws Exception {
        Date date = new Date();
        Tip newTip = this.tipDao.create(date, "title", "link", "test-description");
        Tag newTag = new SqlTagDao(database).getOrCreate("tagi");
        this.tipDao.addTagForTip(newTip.getId(), newTag.getId());
        assertEquals("tagi", tipDao.findByTitle("title").getTags().get(0).getTag());
    }

    @Test
    public void findByTitleReturnsNullIfNoData() throws Exception{
        Tip newTip = this.tipDao.findByTitle("xxxxxxxxxxxtitle");
        assertEquals(null, newTip);
    }
    
    @Test
    public void allNotesAreFetchedFromTheDatabase() throws Exception {
        this.tipDao.create(new Date(), "title", "link", "test-decription");
        this.tipDao.create(new Date(), "title2", "link2", "test-description2");
        
        List<Tip> tipList = tipDao.getAll();
        assertEquals(2, tipList.size()); 
    }
}
