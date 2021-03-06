package lukuvinkkikirjasto.ui;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import lukuvinkkikirjasto.dao.Database;
import lukuvinkkikirjasto.dao.SqlTagDao;
import lukuvinkkikirjasto.dao.SqlTipDao;
import lukuvinkkikirjasto.dao.TagDao;
import lukuvinkkikirjasto.dao.TipDao;
import lukuvinkkikirjasto.domain.Tip;
import lukuvinkkikirjasto.domain.TipService;

public class IOService {

    private IO io;
    private TipService tipService;
    private Map<String, String> commands;
    
    public IOService(IO io, TipService tipService) {
        this.io = io;
        this.commands = createCommands();
        this.tipService = tipService;
    }
    
    private TreeMap<String, String> createCommands() {
        TreeMap commands = new TreeMap<>();
        commands.put("1", "1: luo uusi lukuvinkki");
        commands.put("2", "2: tarkastele vinkkejä");
        commands.put("3", "3: poista lukuvinkki");
        commands.put("x", "x: lopeta");
        return commands;
    }    
    
    public void runApp() {
        printInstructions();
        
        try {
            
            while (true) {
                io.print("Komento: "); 
                String command = io.nextLine();
                System.out.println("");
                if (command.isEmpty()) {
                    break;
                }
                if (!commands.keySet().contains(command)) {
                    System.out.println("Komentoa ei tunnistettu.");
                    printInstructions();
                }
                if (command.equals("1")) {
                    createTip();
                }
                if (command.equals("2")) {
                    listAllTips();
                }
                if (command.equals("3")) {
                    deleteTip();
                }
                if (command.equals("x")) {
                    break;
                }
            }     
        } catch (SQLException e) {
            io.print(e.getMessage());
            io.print("Tapahtui virhe. Ohjelma suljetaan. Yritä uudelleen myöhemmin!");
        }
    }
    
    private void deleteTip() throws SQLException {
        io.print("Poistettavan lukuvinkin id: ");
        String stringId = io.nextLine();
        int id = Integer.parseInt(stringId);
        boolean success = tipService.deleteTip(id);
        
        if (success == true) {
            io.print("Vinkki poistettu.");
        } else {
            io.print("Sinulla ei ole vinkkia annetulla id:lla.");
        }
        
        io.print("");
    }
    
    private void listAllTips() throws SQLException {
        List<Tip> tips = tipService.getAll();
        
        if (tips.size() == 0) {
            io.print("Sinulla ei ole yhtaan lukuvinkkia.");
            io.print("");
        } else {
            for (Tip t : tips) {
                io.print(t.toString());
                io.print("");
            }
        }
    }
    
    private void createTip() throws SQLException {
        String title = null;
        while (title == null) {
            io.print("Otsikko: ");
            title = io.nextLine();
            title = validateTitle(title);
        }
        io.print("Linkki: ");
        String link = io.nextLine();
        io.print("Kuvaus: ");
        String description = io.nextLine(); 
        io.print("tagit: ");
        String tags = io.nextLine();  
        
        tipService.createTip(currentTime(), title, link, description, tags);
        
        io.print("Lukuvinkki lisatty!");
        io.print("");
    }
    
    private Date currentTime() {
        LocalDateTime now = LocalDateTime.now();
        Date date = java.sql.Timestamp.valueOf(now);
        
        return date;
    }
    
    private String validateTitle(String input) {
        String trimmedInput = input.trim();
        if (trimmedInput.length() == 0) {
            io.print("Otsikko ei voi olla tyhja!");
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
    
    public static void main(String[] args) throws Exception {
        Database db = new Database("jdbc:sqlite:vinkkitietokanta.db");
        db.createTables();
        TipDao tipDao = new SqlTipDao(db);
        TagDao tagDao = new SqlTagDao(db);            
        TipService tipService = new TipService(tipDao, tagDao);
        IOService ioservice = new IOService(new KonsoliIO(), tipService);
        ioservice.runApp();  
    }
}
