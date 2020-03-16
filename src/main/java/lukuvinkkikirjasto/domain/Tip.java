
package lukuvinkkikirjasto.domain;

public class Tip {
    
    private String title;
    private String link;
    private int id;
    
    public Tip (String title, String link, int id) {
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
    
    @Override
    public String toString() {
        return "Otsikko: " + this.title + "\n" + "Linkki: " + this.link;
    }
}
