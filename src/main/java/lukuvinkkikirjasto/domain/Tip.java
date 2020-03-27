
package lukuvinkkikirjasto.domain;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Tip implements Comparable<Tip> {
    
    private Date date;
    private String title;
    private String link;
    private String description;
    private List<Tag> tags;
    private int id;
    
    public Tip (Date date, String title, String link, String description, int id) {
        this.date = date;
        this.title = title;
        this.link = link;
        this.description = description;
        this.id = id;
        this.tags = new ArrayList<>();
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

    public String getDescription() {
        return this.description;
    }

    public List<Tag> getTags() {
        return this.tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public void addTag(Tag tag) {
        this.tags.add(tag);
    }
    
    @Override
    public String toString() {
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/YYYY HH:mm");
        String dateString = sdf.format(date);
        
        return  "Otsikko: " + this.title + "\n" + 
                "Linkki: " + this.link + "\n" +
                "Tallennettu: " + dateString + "\n" +
                "Kuvaus: " + this.description + "\n" +
                "Tagit: " + this.tags;
    }

    @Override
    public int compareTo(Tip t) {
        return t.getDate().compareTo(this.date);
    }
}