
package lukuvinkkikirjasto.dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import lukuvinkkikirjasto.domain.Tag;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

public class SqlTagDaoTest {
    
    TagDao tagDao;
    Database database;
    
    @Before
    public void setUp() throws Exception {
       database = new Database("jdbc:sqlite:vinkkitietokanta-test.db"); 
       database.createTables();        
       this.tagDao = new SqlTagDao(database);
    }

    @After
    public void cleanup() throws IOException {
        Files.deleteIfExists(Paths.get("vinkkitietokanta-test.db"));
    }

    @Test
    public void createCreates() throws Exception {
        String tag = "matkailu";
        Tag newTag = this.tagDao.getOrCreate(tag);
        assertNotNull(newTag.getId());
        assertEquals(tag, newTag.getTag());           
    }

    @Test
    public void createdTagCanBeFound() throws Exception {
        String tag = "matkailu";
        this.tagDao.getOrCreate(tag);
        this.tagDao.getOrCreate(tag);
        this.tagDao.getOrCreate(tag);
        Tag testTag = this.tagDao.find(tag);
        assertEquals(tag, testTag.getTag()); 
    }
}
