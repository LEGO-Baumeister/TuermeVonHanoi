import java.util.ArrayList;
/**
 * @author  Lukas Breuer
 * @version 21.04.2021
 */
public class Steuerung {

    private final int maxLaenge = 400;
    private final int hoehe = 40;

    private final int  startPos = 20;
    private final int  hilfsPos = 440;
    private final int  zielPos = 860;
    
    private boolean schnell = false;
    private int anzahl;
    /**
     * Die Anzahl der Schritte wird mit jedem Aufruf der rekursiven Methode um eins hochgezählt und am Ende in der Konsole ausgegeben.
     */
    private int anzahlSchritte;

    private ArrayList<Rechteck> scheiben = new ArrayList<>();
    
    public Steuerung() {
        start();
    }
    
    /**
     * Die Start-Methode wird am Anfang aufgerufen.
     * 
     * Zuerst muss der Nutzer angeben ob sich die Scheiben schnell oder langsam bewegen sollen.
     * Dann muss er die Anzahl der Scheiben angeben, wobei die Anzahl größer als 0 und kleiner als 14 sein muss.
     */
    public void start() {
        schnell = EinfachEingabe.getBoolean("Schneller Modus?", "Ja", "Nein");
        anzahl = EinfachEingabe.getInt("Wie viele Scheiben soll die Simulation haben? (0 < n < 14)", 5);
        if(anzahl <= 0 || anzahl > 13) {
            start();
        } else {
            erstelleScheiben(anzahl);
            hanoi(anzahl, startPos, hilfsPos, zielPos);
            System.out.print("Anzahl der benötigten Schritte: " + anzahlSchritte);
        }
    }
    
    /**
     * Rekursive Methode
     */
    private void hanoi(int n, int start, int hilfe, int ziel) {
        anzahlSchritte++;
        if(n==1) {
            bewegeZu(scheiben.get(n-1), start, ziel);
        } else {
            hanoi(n-1, start, ziel, hilfe);
            bewegeZu(scheiben.get(n-1), start, ziel);
            hanoi(n-1, hilfe, start, ziel);
        }
    }

    private void erstelleScheiben(int anzahl) {
        switch (anzahl) {
            case 13:
            scheiben.add(erzeugeScheibe(160, hoehe, 140, 40*4));
            case 12:
            scheiben.add(erzeugeScheibe(180, hoehe, 130, 40*5));
            case 11:
            scheiben.add(erzeugeScheibe(200, hoehe, 120, 40*6));
            case 10:
            scheiben.add(erzeugeScheibe(220, hoehe, 110, 40*7));
            case 9:
            scheiben.add(erzeugeScheibe(240, hoehe, 100, 40*8));
            case 8:
            scheiben.add(erzeugeScheibe(260, hoehe, 90, 40*9));
            case 7:
            scheiben.add(erzeugeScheibe(280, hoehe, 80, 40*10));
            case 6:
            scheiben.add(erzeugeScheibe(300, hoehe, 70, 40*11));
            case 5:
            scheiben.add(erzeugeScheibe(320, hoehe, 60, 40*12));
            case 4:
            scheiben.add(erzeugeScheibe(340, hoehe, 50, 40*13));
            case 3:
            scheiben.add(erzeugeScheibe(360, hoehe, 40, 40*14));
            case 2:
            scheiben.add(erzeugeScheibe(380, hoehe, 30, 40*15));
            case 1:
            scheiben.add(erzeugeScheibe(400, hoehe, 20, 40*16));
            break;
        }
        for (Rechteck scheibe : scheiben) {
            scheibe.sichtbarMachen();
        }
    }

    private Rechteck erzeugeScheibe(int laenge, int hoehe, int xPos, int yPos) {
        Rechteck scheibe = new Rechteck();
        scheibe.groesseAendern(laenge, hoehe);
        scheibe.positionAendern(xPos, yPos);
        return scheibe;
    }

    private void bewegeZu(Rechteck scheibe, int von, int zu) {
        if (schnell) {
            scheibe.langsamHorizontalBewegen((zu + verschiebungBerechnen(scheibe)) - scheibe.getXPos(), 10, 0);
        } else {
            scheibe.langsamHorizontalBewegen((zu + verschiebungBerechnen(scheibe)) - scheibe.getXPos(), 2, 200);
        }
    }

    private int verschiebungBerechnen(Rechteck scheibe) {
        return ((maxLaenge - scheibe.getLaenge())/2);
    }

}
