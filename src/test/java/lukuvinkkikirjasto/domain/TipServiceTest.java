package lukuvinkkikirjasto.domain;

import org.junit.*;

import lukuvinkkikirjasto.dao.MockTipDao;
import lukuvinkkikirjasto.dao.TipDao;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipServiceTest {
    TipService tipService;

    TipDao stubTipDaoThrowsException = new TipDao(){
        
        @Override
        public Tip create(String title, String link) throws SQLException {
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
    };

    @Before
    public void init() {
        TipDao stubTipDao = new MockTipDao(new ArrayList<>());
        this.tipService = new TipService(stubTipDao);
    }

    @Test
    public void tipServiceReturnsTipObject() throws SQLException {
        Tip tip = tipService.createTip("title", "link");
        assertEquals("title", tip.getTitle());
        assertEquals("link", tip.getLink());
    }

    @Test(expected = SQLException.class)
    public void tipServiceDoNotCatchException() throws SQLException {
        TipService service = new TipService(stubTipDaoThrowsException);
        Tip tip = service.createTip("title", "link");
        assertEquals(null, tip);
    }

    @Test
    public void getAllReturnsAddedTips() throws SQLException{
        tipService.createTip("title1", "link1");
        tipService.createTip("title2", "link2");
        List<Tip> tips = tipService.getAll();
        assertEquals(2, tips.size());
        assertTrue(tips.get(0).getClass() == Tip.class);
        assertEquals("title1", tips.get(0).getTitle());
        assertEquals("title2", tips.get(1).getTitle());
    }
}