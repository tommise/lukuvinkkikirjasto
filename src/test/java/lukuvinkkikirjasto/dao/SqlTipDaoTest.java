
package lukuvinkkikirjasto.dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import lukuvinkkikirjasto.domain.Tip;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

public class SqlTipDaoTest {
    
    TipDao tipDao;
    Database database;
    
    @Before
    public void setUp() throws Exception {
       database = new Database("jdbc:sqlite:vinkkitietokanta-test.db"); 
       database.createTables();
        
       this.tipDao = new SqlTipDao(database);
    }

    @After
    public void cleanup() throws IOException {
        Files.deleteIfExists(Paths.get("vinkkitietokanta-test.db"));
    }

    @Test
    public void creatingANewTipCreatesTheTip() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        Date date = java.sql.Timestamp.valueOf(now);
        Tip newTip = this.tipDao.create(date, "title", "link");
        
        assertNotNull(newTip);
    }
    
    @Test
    public void allNotesAreFetchedFromTheDatabase() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        Date date = java.sql.Timestamp.valueOf(now);
        this.tipDao.create(date, "title", "link");
        this.tipDao.create(date, "title2", "link2");
        
        List<Tip> tipList = tipDao.getAll();
        assertEquals(2, tipList.size()); 
    }
}
