import java.util.ArrayList;
import java.util.Stack;
/**
 * Write a description of class Steuerung here.
 *
 * @author Lukas Breuer
 * @version 03.04.2021
 */
public class Steuerung
{
    private int anzahlScheiben = 0;
    private int anzahlSchritte = 0;
    private ArrayList<Scheibe> scheiben = new ArrayList<>();
    private Stack stapelStart = new Stack();
    private Stack stapelHilfe = new Stack();
    private Stack stapelZiel = new Stack();

    public void start() {
        anzahlScheiben = EinfachEingabe.getInt("Wie viele Scheiben soll die Simulation haben? (0 < n < 11)", 5);
        if (anzahlScheiben <= 0 || anzahlScheiben > 10) {
            start();
        } else {
            erstelleScheiben(anzahlScheiben);
            zeigeAlleScheiben();
            stapelStart.addAll(scheiben);
            scheibeBewegen(anzahlScheiben, stapelStart, stapelHilfe, stapelZiel);
        }
    }

    private void scheibeBewegen(int n, Stack start, Stack hilfe, Stack ziel) {
        Scheibe scheibe;
        if (n==1) {
            scheibe = (Scheibe) start.pop();
            ziel.push(scheibe);
        } else {
            scheibeBewegen(n-1, start, ziel, hilfe);
            scheibe = (Scheibe) start.pop();
            ziel.push(scheibe);
            scheibeBewegen(n-1, hilfe, start, ziel);
        }
        anzahlSchritte++;
        System.out.println("<=====(Start)=====>");
        p(start);
        System.out.println("<=====(Hilfe)=====>");
        p(hilfe);
        System.out.println("<=====(Ziel)=====>");
        p(ziel);
        System.out.println("                   ");
    }

    private void erstelleScheiben(int anzahl) {
        switch (anzahl) {
            case 10:
            scheiben.add(erzeugeRechteck(40, 40, 200, 40*7, 10));
            case 9:
            scheiben.add(erzeugeRechteck(80, 40, 180, 40*8, 9));
            case 8:
            scheiben.add(erzeugeRechteck(120, 40, 160, 40*9, 8));
            case 7:
            scheiben.add(erzeugeRechteck(160, 40, 140, 40*10, 7));
            case 6:
            scheiben.add(erzeugeRechteck(200, 40, 120, 40*11, 6));
            case 5:
            scheiben.add(erzeugeRechteck(240, 40, 100, 40*12,5));
            case 4:
            scheiben.add(erzeugeRechteck(280, 40, 80, 40*13, 4));
            case 3:
            scheiben.add(erzeugeRechteck(320, 40, 60, 40*14, 3));
            case 2:
            scheiben.add(erzeugeRechteck(360, 40, 40, 40*15, 2));
            case 1:
            scheiben.add(erzeugeRechteck(400, 40, 20, 40*16, 1));
            break;
        }
    }

    private Scheibe erzeugeRechteck(int laenge, int hoehe, int xPos, int yPos, int id) {
        Scheibe scheibe = new Scheibe(id);
        scheibe.groesseAendern(laenge, hoehe);
        scheibe.positionAendern(xPos, yPos);
        return scheibe;
    }

    private void zeigeAlleScheiben() {
        for (Rechteck r : scheiben) {
            r.sichtbarMachen();
        }
    }
    
    /*
     * debug Only
     */
    private void p(Stack s) {
        for (Object o : s) {
            Scheibe p = (Scheibe) o;
            System.out.println(p.getId());
        }
    }
    
    private void bewegeZuStart(Scheibe scheibe) {
        
    }
    
    private void bewegeZuHilfe(Scheibe scheibe) {
    
    }
    
    private void bewegeZuZiel(Scheibe scheibe) {
    
    }

}
