package lukuvinkkikirjasto.domain;

import static org.junit.Assert.*;

import org.junit.*;

public class TipTest {
    @Test
    public void toStringContainsTitleAndLink(){
        Tip tip = new Tip("test-title", "test-link", 1);
        assertTrue(tip.toString().contains("test-title"));
        assertTrue(tip.toString().contains("test-link"));
    }
}