package lukuvinkkikirjasto.domain;

import java.time.LocalDateTime;
import java.util.Date;
import static org.junit.Assert.*;

import org.junit.*;

public class TipTest {
    @Test
    public void toStringContainsTitleAndLink(){
        LocalDateTime now = LocalDateTime.now();
        Date date = java.sql.Timestamp.valueOf(now);
        Tip tip = new Tip(date, "test-title", "test-link", 1);
        assertTrue(tip.toString().contains("test-title"));
        assertTrue(tip.toString().contains("test-link"));
    }
}