package spadeintruders;

import java.awt.Image;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

public class Pocisk extends Obiekt {
    private short predkosc;
    private String kolor;
    private boolean wrogi;

    public Pocisk(int x, int y, boolean wrogi) {
        super(x, y, Stale.szerokoscPocisku, Stale.wysokoscPocisku);

        this.wrogi = wrogi;

        if(this.wrogi) {
            this.predkosc = -10;
            this.kolor = "#ff0000";
        } else {
            this.predkosc = 10;
            this.kolor = "#00ff00";
        }
    }

    @Override
    public void aktualizuj() {
        if(this.pozycja.getY()+this.rozmiar.getWysokosc() > 0) {
            this.pozycja = new Pozycja(this.pozycja.getX(), this.pozycja.getY()-this.predkosc);
        }
    }

    @Override
    public Image getTekstura() {
        BufferedImage tekstura = new BufferedImage(this.rozmiar.getSzerokosc(), this.rozmiar.getWysokosc(), BufferedImage.TYPE_INT_RGB); //Tworzenie tekstury obiektu
        Graphics2D grafika = tekstura.createGraphics();

        grafika.setColor(Color.decode(this.kolor));
        grafika.fillRect(0, 0, this.rozmiar.getSzerokosc(), this.rozmiar.getWysokosc());

        grafika.dispose();
        return tekstura;
    }

    @Override
    public boolean czyPocisk() {
        return true;
    }

    public boolean czyWrogi() {
        return this.wrogi;
    }

    public Obiekt detekcjaTrafienia(Obiekt prawdopodobnyCel) {
        if(((
                this.pozycja.getX() >= prawdopodobnyCel.pozycja.getX() 
                && this.pozycja.getX() <= prawdopodobnyCel.pozycja.getX()+prawdopodobnyCel.rozmiar.getSzerokosc()
            ) || (
                this.pozycja.getX()+this.rozmiar.getSzerokosc() >= prawdopodobnyCel.pozycja.getX()
                && this.pozycja.getX()+this.rozmiar.getSzerokosc() <= prawdopodobnyCel.pozycja.getX()+prawdopodobnyCel.rozmiar.getSzerokosc()
        )) && ((
                this.pozycja.getY() >= prawdopodobnyCel.pozycja.getY()
                && this.pozycja.getY() <= prawdopodobnyCel.pozycja.getY()+prawdopodobnyCel.rozmiar.getWysokosc()
            ) || (
                this.pozycja.getY()+this.rozmiar.getWysokosc() >= prawdopodobnyCel.pozycja.getY()
                && this.pozycja.getY()+this.rozmiar.getWysokosc() <= prawdopodobnyCel.pozycja.getY()+prawdopodobnyCel.rozmiar.getWysokosc()
        ))) {
            return prawdopodobnyCel;
        } else {
            return null;
        }
    }

    public boolean czyTrafia(Obiekt obiekt) {
        if(obiekt == null) {
            return false;
        } else {
            return true;
        }
    }

    public Obiekt aktualizuj(Gracz prawdopodobnyCel) {
        this.aktualizuj();
        return detekcjaTrafienia(prawdopodobnyCel);
    }

    public Obiekt aktualizuj(List<Wrog> prawdopodobneCele) {
        this.aktualizuj();

        Wrog trafiony = null;

        if(prawdopodobneCele.stream().anyMatch(prawdopodobnyCel -> czyTrafia(detekcjaTrafienia(prawdopodobnyCel)))) {
            trafiony = prawdopodobneCele.stream().filter(prawdopodobnyCel -> czyTrafia(detekcjaTrafienia(prawdopodobnyCel))).findFirst().get();
        }

        return trafiony;
    }
}
