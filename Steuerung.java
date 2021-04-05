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
    
    private int[] xStart = {20, 40, 60, 80, 100, 120, 140, 160, 180, 200};
    private int[] xHilfe = {440, 460, 480, 500, 520, 540, 560, 580, 600, 620};
    private int[] xZiel = {860, 880, 900, 920, 940, 960, 980, 1000, 1020, 1040};

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
            //bewegeZuZiel(scheibe);
            anzahlSchritte++;
        } else {
            
            scheibeBewegen(n-1, start, ziel, hilfe);
            scheibe = (Scheibe) start.pop();
            ziel.push(scheibe);
            anzahlSchritte++;
            bewegeZuHilfe(scheibe);
            scheibeBewegen(n-1, hilfe, start, ziel);
            bewegeZuZiel(scheibe);
        }
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
        switch(scheibe.getId()) {
            case 1:
            scheibe.positionAendern(20, 40*16);
            break;
            case 2:
            scheibe.positionAendern(40, 40*15);
            break;
            case 3:
            scheibe.positionAendern(60, 40*14);
            break;
            case 4:
            scheibe.positionAendern(80, 40*13);
            break;
            case 5:
            scheibe.positionAendern(100, 40*12);
            break;
            case 6:
            scheibe.positionAendern(120, 40*11);
            break;
            case 7:
            scheibe.positionAendern(140, 40*10);
            break;
            case 8:
            scheibe.positionAendern(160, 40*9);
            break;
            case 9:
            scheibe.positionAendern(180, 40*8);
            break;
            case 10:
            scheibe.positionAendern(200, 40*7);
            break;
        }
    }
    
    private void bewegeZuHilfe(Scheibe scheibe) {
        switch(scheibe.getId()) {
            case 1:
            scheibe.positionAendern(440, 40*16);
            break;
            case 2:
            scheibe.positionAendern(460, 40*15);
            break;
            case 3:
            scheibe.positionAendern(480, 40*14);
            break;
            case 4:
            scheibe.positionAendern(500, 40*13);
            break;
            case 5:
            scheibe.positionAendern(520, 40*12);
            break;
            case 6:
            scheibe.positionAendern(540, 40*11);
            break;
            case 7:
            scheibe.positionAendern(560, 40*10);
            break;
            case 8:
            scheibe.positionAendern(580, 40*9);
            break;
            case 9:
            scheibe.positionAendern(600, 40*8);
            break;
            case 10:
            scheibe.positionAendern(620, 40*7);
            break;
        }
    }
    
    private void bewegeZuZiel(Scheibe scheibe) {
        switch(scheibe.getId()) {
            case 1:
            scheibe.positionAendern(860, 40*16);
            break;
            case 2:
            scheibe.positionAendern(880, 40*15);
            break;
            case 3:
            scheibe.positionAendern(900, 40*14);
            break;
            case 4:
            scheibe.positionAendern(920, 40*13);
            break;
            case 5:
            scheibe.positionAendern(940, 40*12);
            break;
            case 6:
            scheibe.positionAendern(960, 40*11);
            break;
            case 7:
            scheibe.positionAendern(980, 40*10);
            break;
            case 8:
            scheibe.positionAendern(1000, 40*9);
            break;
            case 9:
            scheibe.positionAendern(1020, 40*8);
            break;
            case 10:
            scheibe.positionAendern(1040, 40*7);
            break;
        }
    }

}
