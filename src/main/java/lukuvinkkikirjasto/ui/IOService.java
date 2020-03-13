package lukuvinkkikirjasto.ui;

import lukuvinkkikirjasto.dao.Database;
import lukuvinkkikirjasto.dao.SqlTipDao;
import lukuvinkkikirjasto.dao.TipDao;
import lukuvinkkikirjasto.domain.TipService;

public class IOService {

    private IO io;
    private TipService tipService;
    
    public IOService(IO io) {
        this.io = io;
    }
    
    public void suorita() {
        instructions();
        while (true) {
            
            io.print("Otsikko: ");
            String otsikko = io.nextLine();
            io.print("Linkki: ");
            String linkki = io.nextLine();
            createTip(otsikko, linkki);
            break;
            
        }
    }
    
    private void instructions() {
        io.print("Tervetuloa lukuvinkkisovellukseen! \n"
                + "Lisää uusi vinkki, valitse 1 \n"
                + "Tarkastele lukuvinkkejä, valitse 2");
    }
    
    private void createTip(String otsikko, String linkki) {
        tipService.createTip(otsikko, linkki);
    }
    
    public static void main(String[] args) throws Exception {
        IOService ioservice = new IOService(new KonsoliIO());
        ioservice.init();  
    }
    
    private void init() throws Exception {
        
        Database db = new Database("jdbc:sqlite:vinkkitietokanta.db");
        db.createTables();
        TipDao tipDao = new SqlTipDao(db);
        TipService tipService = new TipService(tipDao);
        
        this.tipService = tipService;
        
        suorita();    
    }

}
