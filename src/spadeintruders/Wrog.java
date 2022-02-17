package spadeintruders;

import java.awt.Image;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Wrog extends Obiekt {
    private KontrolerRoju kontroler;
    private boolean pierwszy = false, ostatni = false;
    private Obiekt poprzedni;

    public Wrog(int x, int y, int szerokosc, int wysokosc, KontrolerRoju kontroler, Obiekt poprzedni) {
        super(x, y, szerokosc, wysokosc);
        this.kontroler = kontroler;
        this.poprzedni = poprzedni;
    }

    public Wrog(int x, int y, int szerokosc, int wysokosc, KontrolerRoju kontroler, boolean pierwszy) {
        super(x, y, szerokosc, wysokosc);
        this.kontroler = kontroler;
        this.pierwszy = pierwszy;
    }

    public Wrog(int x, int y, int szerokosc, int wysokosc, KontrolerRoju kontroler, Obiekt poprzedni, boolean ostatni) {
        super(x, y, szerokosc, wysokosc);
        this.kontroler = kontroler;
        this.poprzedni = poprzedni;
        this.ostatni = ostatni;
    }

    public void aktualizujRoj() {
        if((this.pozycja.getX() == 0 && this.kontroler.czyProsiWLewo())
        || (this.pozycja.getX()+Stale.rozmiarPostaci == Stale.szerokoscEkranu && this.kontroler.czyProsiWPrawo())) {
            this.kontroler.zmienKierunekX();
            this.kontroler.zmienKierunekY();
        }
    }

    @Override
    public void aktualizuj() {
        int roznicaX = 0, roznicaY = 0;

        if(this.kontroler.czyProsiWLewo()) {
            roznicaX -= 1;
        }
        if(this.kontroler.czyProsiWPrawo()) {
            roznicaX += 1;
        }

        if(this.kontroler.czyProsiWDol()) {
            roznicaY += 1;
        }

        if(this.pozycja.getX() >= 0 && this.pozycja.getX()+this.rozmiar.getSzerokosc() <= Stale.szerokoscEkranu) {
            this.pozycja = new Pozycja(this.pozycja.getX()+roznicaX, this.pozycja.getY());
        }

        if(this.pozycja.getY()+this.rozmiar.getWysokosc() <= Stale.wysokoscEkranu) {
            this.pozycja = new Pozycja(this.pozycja.getX(), this.pozycja.getY()+roznicaY);
        }
    }

    @Override
    public Image getTekstura() {
        BufferedImage tekstura = new BufferedImage(this.rozmiar.getSzerokosc(), this.rozmiar.getWysokosc(), BufferedImage.TYPE_INT_RGB); //Tworzenie tekstury obiektu
        Graphics2D grafika = tekstura.createGraphics();

        grafika.setColor(Color.decode("#ff0000"));
        grafika.fillRect(0, 0, this.rozmiar.getSzerokosc(), this.rozmiar.getWysokosc());

        grafika.dispose();
        return tekstura;
    }

    public void jestPierwszy() {
        this.pierwszy = true;
    }

    public void jestOstatni() {
        this.ostatni = true;
    }

    public void ustawPoprzednika(Wrog poprzedni) {
        this.poprzedni = poprzedni;
    }
}