package lukuvinkkikirjasto.domain;

public class Tag {
    private int id;
    private String tag;

    public Tag(int id, String tag) {
        this.id = id;
        this.tag = tag;
    }

    public int getId() {
        return id;
    }

    public String getTag() {
        return tag;
    }
}