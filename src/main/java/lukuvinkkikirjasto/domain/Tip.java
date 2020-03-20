
package lukuvinkkikirjasto.domain;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Tip {
    
    private Date date;
    private String title;
    private String link;
    private int id;
    
    public Tip (Date date, String title, String link, int id) {
        this.date = date;
        this.title = title;
        this.link = link;
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }    
    
    public int getId() {
        return this.id;
    }  
    
    public Date getDate() {
        return this.date;
    }
    
    @Override
    public String toString() {
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/YYYY HH:mm");
        String dateString = sdf.format(date);
        
        return "Otsikko: " + this.title + "\n" + "Linkki: " + this.link + "\n"
                + "Tallennettu: " + dateString;
    }
}
/*
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        return "Date: " + this.date.format(formatter)
*/