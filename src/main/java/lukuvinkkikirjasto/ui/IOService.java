package lukuvinkkikirjasto.ui;

import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;
import lukuvinkkikirjasto.dao.Database;
import lukuvinkkikirjasto.dao.SqlTipDao;
import lukuvinkkikirjasto.dao.TipDao;
import lukuvinkkikirjasto.domain.TipService;

public class IOService {

    private IO io;
    private TipService tipService;
    private Map<String, String> commands;
    
    public IOService(IO io) {
        this.io = io;
        this.commands = createCommands();
    }
    
    private TreeMap<String, String> createCommands() {
        TreeMap commands = new TreeMap<>();
        commands.put("1", "1: luo uusi lukuvinkki");
        commands.put("2", "2: tarkastele vinkkejä");
        return commands;
    }    
    
    public void suorita() {
        printInstructions();
        
        while (true) {
            io.print("Komento: "); 
            String command = io.nextLine();
            System.out.println("");
            if (!commands.keySet().contains(command)) {
                System.out.println("Komentoa ei tunnistettu.");
                printInstructions();
            }
            if (command.equals("1")) {
                createTip();
            }
            
        }       
    }
    
    private void createTip() {
        String otsikko = null;
        while (otsikko == null) {
            io.print("Otsikko: ");
            otsikko = io.nextLine();
            otsikko = validateTitle(otsikko);
        }
        io.print("Linkki: ");
        String linkki = io.nextLine();
        
        tipService.createTip(otsikko, linkki);
        
        io.print("Lukuvinkki lisätty!");
        io.print("");
    }
    
    private String validateTitle(String input) {
        String trimmedInput = input.trim();
        if (trimmedInput.length() == 0) {
            io.print("Otsikko ei voi olla tyhjä!");
            trimmedInput = null;
        }
        return trimmedInput;
    }
    
    private void printInstructions() {
        System.out.println("Valitse joku seuraavista komennoista:");
        for (Map.Entry<String, String> entry : commands.entrySet()) {
            io.print(entry.getValue());
        }    
        io.print("");
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
