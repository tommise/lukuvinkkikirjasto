package lukuvinkkikirjasto.domain;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import lukuvinkkikirjasto.dao.TipDao;

public class MockTipDao implements TipDao {
    private List<Tip> tips;

    public MockTipDao(List<Tip> tips) {
        this.tips = tips;
    }

    @Override
    public Tip create(Date date, String title, String link, String description) throws SQLException {
        Tip tip = new Tip(date, title, link, description, 1);
        tips.add(tip);
        return tip;
    }

    @Override
    public List<Tip> getAll() throws SQLException {
        return tips;
    }

    @Override
    public Tip findByTitle(String title) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
                                                                       // Tools | Templates.
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


}
