package lukuvinkkikirjasto.domain;

import org.junit.*;

import lukuvinkkikirjasto.dao.TipDao;

import static org.junit.Assert.*;

public class TipServiceTest {
  TipService tipService;

  TipDao stubTipDao = new TipDao(){  
    @Override
    public Tip create(String title, String link){      
      return new Tip(title, link, 1);
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

  @Test
  public void ifEmptyTitleIsGivenTipServiceReturnsNull() {
    Tip tip = tipService.createTip("", "link");
    assertEquals(null, tip);
  }

}