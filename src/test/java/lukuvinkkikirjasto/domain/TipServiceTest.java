package lukuvinkkikirjasto.domain;

import org.junit.*;

import lukuvinkkikirjasto.dao.TipDao;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

public class TipServiceTest {
    TipService tipService;

    TipDao stubTipDao = new TipDao(){  
        
        @Override
        public Tip create(String title, String link){      
        return new Tip(title, link, 1);
        }

        @Override
        public List<Tip> getAll() throws SQLException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };

    TipDao stubTipDaoThrowsException = new TipDao(){
        
        @Override
        public Tip create(String title, String link) throws SQLException {
        throw new SQLException("error");
        }
    };

    @Before
    public void init() {
        this.tipService = new TipService(stubTipDao);
    }

    @Test
    public void tipServiceReturnsTipObject() {
        Tip tip = tipService.createTip("title", "link");
        assertEquals("title", tip.getTitle());
        assertEquals("link", tip.getLink());
    }

    /*
    @Test
    public void ifEmptyTitleIsGivenTipServiceReturnsNull() {
        Tip tip = tipService.createTip("", "link");
        assertEquals(null, tip);
    }
    */

    @Test
    public void tipServiceCatchesException() {
        TipService service = new TipService(stubTipDaoThrowsException);
        Tip tip = service.createTip("title", "link");
        assertEquals(null, tip);
    }

}