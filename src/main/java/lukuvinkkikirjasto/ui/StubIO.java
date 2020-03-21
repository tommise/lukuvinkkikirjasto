package lukuvinkkikirjasto.ui;

import java.util.ArrayList;
import java.util.List;

public class StubIO implements IO {
    private List<String> lines;
    private int index = 0;
    private List<String> prints;

    public StubIO(List<String> lines) {
        this.lines = lines;
        this.prints = new ArrayList<>();
    }

    @Override
    public String nextLine() {
        if(index < lines.size()) {
            return lines.get(index++);
        }
        return "";        
    }

    @Override
    public void print(String line) {
        prints.add(line.trim());
    }

    public List<String> getPrints() {
        return this.prints;
    }
}