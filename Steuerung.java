import java.util.ArrayList;

public class Steuerung {

    private static final int MAX_LAENGE = 400;
    private static final int HOEHE = 40;

    private static final int  START_POS = 20;
    private static final int  HILFS_POS = 440;
    private static final int  ZIEL_POS = 860;

    private int anzahl;
    private int anzahlSchritte;

    private ArrayList<Scheibe> scheiben = new ArrayList<>();

    public void start() {
        anzahl = EinfachEingabe.getInt("Wie viele Scheiben soll die Simulation haben? (0 < n < 11)", 5);
        if(anzahl <= 0 || anzahl > 10) {
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
            case 10:
            scheiben.add(createRectangle(40, 40, 200, 40*7, anzahl));
            case 9:
            scheiben.add(createRectangle(80, 40, 180, 40*8, anzahl-1));
            case 8:
            scheiben.add(createRectangle(120, 40, 160, 40*9, anzahl-2));
            case 7:
            scheiben.add(createRectangle(160, 40, 140, 40*10, anzahl-3));
            case 6:
            scheiben.add(createRectangle(200, 40, 120, 40*11, anzahl-4));
            case 5:
            scheiben.add(createRectangle(240, 40, 100, 40*12, anzahl-5));
            case 4:
            scheiben.add(createRectangle(280, 40, 80, 40*13, anzahl-6));
            case 3:
            scheiben.add(createRectangle(320, 40, 60, 40*14, anzahl-7));
            case 2:
            scheiben.add(createRectangle(360, 40, 40, 40*15, anzahl-8));
            case 1:
            scheiben.add(createRectangle(400, 40, 20, 40*16, anzahl-9));
            break;
        }
        for (Scheibe scheibe : scheiben) {
            scheibe.sichtbarMachen();
        }
    }

    private Scheibe createRectangle(int laenge, int hoehe, int xPos, int yPos, int id) {
        Scheibe scheibe = new Scheibe(id);
        scheibe.groesseAendern(laenge, hoehe);
        scheibe.positionAendern(xPos, yPos);
        return scheibe;
    }

    private void bewegeZu(Scheibe scheibe, int von, int zu) {
        scheibe.langsamHorizontalBewegen((zu + calcOffset(scheibe)) - scheibe.getXPos());
    }

    private int calcOffset(Scheibe scheibe) {
        return ((MAX_LAENGE - scheibe.getLaenge())/2);
    }

}
