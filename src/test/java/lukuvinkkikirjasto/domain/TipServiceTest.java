package lukuvinkkikirjasto.domain;

import org.junit.*;

import lukuvinkkikirjasto.dao.TagDao;
import lukuvinkkikirjasto.dao.TipDao;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TipServiceTest {
    TipService tipService;

    TagDao stubTagDao = new TagDao() {
        public Tag getOrCreate(String tag) throws SQLException {
            return new Tag(1, tag);
        };
        public Tag find(String tag) throws SQLException {
            return new Tag(1, tag);
        };
        
    };

    TipDao stubTipDaoThrowsException = new TipDao(){
        
        @Override
        public Tip create(Date date, String title, String link, String description) throws SQLException {
            throw new SQLException("error");
        }

        @Override
        public List<Tip> getAll() throws SQLException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Tip findByTitle(String title) throws SQLException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void addTagForTip(int tipId, int tagId) throws SQLException {
            // TODO Auto-generated method stub

        }

        @Override
        public boolean deleteTip(int id) throws SQLException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Tip findById(int tipId) throws SQLException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

 
    };

    @Before
    public void init() {
        TipDao stubTipDao = new MockTipDao(new ArrayList<>());
        this.tipService = new TipService(stubTipDao, stubTagDao);
    }

    @Test
    public void tipServiceReturnsTipObject() throws SQLException {
        LocalDateTime now = LocalDateTime.now();
        Date date = java.sql.Timestamp.valueOf(now);
        Tip tip = tipService.createTip(date,"title", "link", "test-description", "test-tag");
        assertEquals(date, tip.getDate());
        assertEquals("title", tip.getTitle());
        assertEquals("link", tip.getLink());
        assertEquals("test-description", tip.getDescription());
    }

    @Test(expected = SQLException.class)
    public void tipServiceDoNotCatchException() throws SQLException {
        TipService service = new TipService(stubTipDaoThrowsException, stubTagDao);
        LocalDateTime now = LocalDateTime.now();
        Date date = java.sql.Timestamp.valueOf(now);
        Tip tip = service.createTip(date, "title", "link", "description", "tagi");
        assertEquals(null, tip);
    }

    @Test
    public void getAllReturnsAddedTips() throws SQLException {
        LocalDateTime now = LocalDateTime.now();
        Date date = java.sql.Timestamp.valueOf(now);
        tipService.createTip(date, "title1", "link1", "test-decription1", "tagi");
        tipService.createTip(date, "title2", "link2", "test-decription2", "tagi");
        List<Tip> tips = tipService.getAll();
        assertEquals(2, tips.size());
        assertTrue(tips.get(0).getClass() == Tip.class);
        assertEquals("title1", tips.get(0).getTitle());
        assertEquals("title2", tips.get(1).getTitle());
    }
}