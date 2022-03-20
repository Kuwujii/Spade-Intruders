package spadeintruders;

import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferStrategy;

public class Klatka extends JFrame { //Klasa odpowiedzialna za rysowanie klatek w oknie gry
    private Canvas plotno; //Płótno na którym rysować będziemy klatki

    public Klatka(int szerokosc, int wysokosc, Wejscie wejscie) {
        setTitle("Spade Intruders");
        setDefaultCloseOperation(EXIT_ON_CLOSE); //Ustawiamy żeby program zakonczył się po kliknięciu X w rogu (domyślnie zamyka się okno ale program dalej działa w tle)

        this.plotno = new Canvas(); //Tworzymy płótno i ustawiamy jego rozmiary
        this.plotno.setPreferredSize(new Dimension(szerokosc, wysokosc));
        this.plotno.setFocusable(false);

        add(this.plotno); //Dodajemy płótno do okna
        addKeyListener(wejscie);
        pack();

        this.plotno.createBufferStrategy(2); //Ustalamy ile klatek "jednocześnie" rysować będzie program. Wartość ta musi być większa od 1 aby mieć klatke w zapasie i uniknąć szybkiego migania ekranu

        setLocationRelativeTo(null); //Ustawiamy okno na środku ekranu oraz robimy je widoczne
        setVisible(true);
    }

    public void rysuj(Gra gra, int fps, int ups) {
        BufferStrategy rysowaneKlatki = this.plotno.getBufferStrategy();
        Graphics grafika = rysowaneKlatki.getDrawGraphics();

        grafika.setColor(Color.decode("#000000")); //Rysujemy tło
        grafika.fillRect(0, 0, this.plotno.getWidth(), this.plotno.getHeight());

        gra.getObiekty().forEach(obiekt -> grafika.drawImage(obiekt.getTekstura(), obiekt.getPozycja().getX(), obiekt.getPozycja().getY(), null)); //Rysujemy wszystkie obiekty

        grafika.setFont(grafika.getFont().deriveFont(1));
        grafika.setColor(Color.decode("#ffffff"));
        grafika.drawString("FPS: "+fps, 10, 10);
        grafika.drawString("UPS: "+ups, 10, 20);

        grafika.dispose(); //Zwalniamy zasoby które zajmowały się rysowaniem ukończonych klatek
        rysowaneKlatki.show(); //Pokazujemy klatke
    }
}