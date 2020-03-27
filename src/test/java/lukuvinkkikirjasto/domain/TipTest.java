package lukuvinkkikirjasto.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static org.junit.Assert.*;

import org.junit.*;

public class TipTest {
    @Test
    public void toStringContainsDateTitleLinkAndDescription() throws ParseException{
        Date date = new Date();
        
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/YYYY HH:mm");
        String dateString = sdf.format(date);
        
        Tip tip = new Tip(date, "test-title", "test-link", "test-description", 1);
        tip.addTag(new Tag(1, "test-tag"));
        System.out.println(tip.toString());
        assertTrue(tip.toString().contains(dateString));
        assertTrue(tip.toString().contains("test-title"));
        assertTrue(tip.toString().contains("test-link"));
        assertTrue(tip.toString().contains("test-description"));
        assertTrue(tip.toString().contains("test-tag"));
        
    }
}