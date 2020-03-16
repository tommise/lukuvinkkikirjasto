package lukuvinkkikirjasto.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lukuvinkkikirjasto.domain.Tip;

public class MockTipDao implements TipDao {
    private List<Tip> tips;

    public MockTipDao(List<Tip> tips) {
        this.tips = tips;
    }

    @Override
    public Tip create(String title, String link) throws SQLException {
        Tip tip = new Tip(title, link, 1);
        tips.add(tip);
        return tip;
    }

    @Override
    public List<Tip> getAll() throws SQLException {
        return tips;
    }    

    @Override
    public Tip findByTitle(String title) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}