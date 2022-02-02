package spadeintruders;

import java.awt.Image;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Pocisk extends Obiekt {
    public Pocisk(int x, int y) {
        super(x, y, 10, 25);
    }

    @Override
    public void aktualizuj() {
        if(this.pozycja.getY()+this.rozmiar.getWysokosc() > 0) {
            this.pozycja = new Pozycja(this.pozycja.getX(), this.pozycja.getY()-10);
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

    @Override
    public boolean czyPocisk() {
        return true;
    }
}
