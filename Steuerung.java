import java.util.ArrayList;

public class Steuerung {

    private static final int MAX_LAENGE = 400;
    private static final int HOEHE = 40;

    private static final int  START_POS = 20;
    private static final int  HILFS_POS = 440;
    private static final int  ZIEL_POS = 860;

    private boolean schnell = false;
    private int anzahl;
    private int anzahlSchritte;

    private ArrayList<Rechteck> scheiben = new ArrayList<>();
    
    public Steuerung() {
        start();
    }

    public void start() {
        schnell = EinfachEingabe.getBoolean("Schneller Modus?", "Ja", "Nein");
        anzahl = EinfachEingabe.getInt("Wie viele Scheiben soll die Simulation haben? (0 < n < 14)", 5);
        if(anzahl <= 0 || anzahl > 13) {
            start();
        } else {
            erstelleScheiben(anzahl);
            hanoi(anzahl, START_POS, HILFS_POS, ZIEL_POS);
        }
    }

    private void hanoi(int n, int start, int spare, int dest) {
        anzahlSchritte++;
        if(n==1) {
            bewegeZu(scheiben.get(n-1), start, dest);
        } else {
            hanoi(n-1, start, dest, spare);
            bewegeZu(scheiben.get(n-1), start, dest);
            hanoi(n-1, spare, start, dest);
        }
        System.out.print("Anzahl der Schritte: " + anzahlSchritte + "\r");
    }

    private void erstelleScheiben(int anzahl) {
        switch (anzahl) {
            case 13:
            scheiben.add(createRectangle(160, 40, 140, 40*4));
            case 12:
            scheiben.add(createRectangle(180, 40, 130, 40*5));
            case 11:
            scheiben.add(createRectangle(200, 40, 120, 40*6));
            case 10:
            scheiben.add(createRectangle(220, 40, 110, 40*7));
            case 9:
            scheiben.add(createRectangle(240, 40, 100, 40*8));
            case 8:
            scheiben.add(createRectangle(260, 40, 90, 40*9));
            case 7:
            scheiben.add(createRectangle(280, 40, 80, 40*10));
            case 6:
            scheiben.add(createRectangle(300, 40, 70, 40*11));
            case 5:
            scheiben.add(createRectangle(320, 40, 60, 40*12));
            case 4:
            scheiben.add(createRectangle(340, 40, 50, 40*13));
            case 3:
            scheiben.add(createRectangle(360, 40, 40, 40*14));
            case 2:
            scheiben.add(createRectangle(380, 40, 30, 40*15));
            case 1:
            scheiben.add(createRectangle(400, 40, 20, 40*16));
            break;
        }
        for (Rechteck scheibe : scheiben) {
            scheibe.sichtbarMachen();
        }
    }

    private Rechteck createRectangle(int laenge, int hoehe, int xPos, int yPos) {
        Rechteck scheibe = new Rechteck();
        scheibe.groesseAendern(laenge, hoehe);
        scheibe.positionAendern(xPos, yPos);
        return scheibe;
    }

    private void bewegeZu(Rechteck scheibe, int von, int zu) {
        if (schnell) {
            scheibe.langsamHorizontalBewegen((zu + calcOffset(scheibe)) - scheibe.getXPos(), 10, 0);
        } else {
            scheibe.langsamHorizontalBewegen((zu + calcOffset(scheibe)) - scheibe.getXPos(), 2, 200);
        }
    }

    private int calcOffset(Rechteck scheibe) {
        return ((MAX_LAENGE - scheibe.getLaenge())/2);
    }

}
