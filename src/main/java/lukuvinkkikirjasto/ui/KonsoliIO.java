package lukuvinkkikirjasto.ui;

import java.util.Scanner;

public class KonsoliIO implements IO {

    private Scanner lukija;
    
    public KonsoliIO() {
        this.lukija = new Scanner(System.in);
    }
    
    @Override
    public String nextLine() {
        return lukija.nextLine();
    }

    @Override
    public void print(String m) {
        System.out.println(m);
    }
    
}
