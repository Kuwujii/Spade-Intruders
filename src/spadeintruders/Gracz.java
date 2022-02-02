package spadeintruders;

import java.awt.Image;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Gracz extends Obiekt { //Klasa przeznaczona do tworzenia obiektu gracza na bazie klasy obiektu
    private Kontroler kontroler;
    private long czasOstatniegoStrzalu;

    public List<Obiekt> noweObiekty;

    public Gracz(int x, int y, int szerokosc, int wysokosc, Kontroler kontroler) {
        super(x, y, szerokosc, wysokosc);
        this.kontroler = kontroler;
        this.czasOstatniegoStrzalu = System.currentTimeMillis();
        this.noweObiekty = new ArrayList<>();
    }

    @Override
    public void aktualizuj() {
        int roznicaX = 0;

        if(this.kontroler.czyProsiWLewo()) {
            roznicaX -= 10;
        }
        if(this.kontroler.czyProsiWPrawo()) {
            roznicaX += 10;
        }

        if(this.kontroler.czyStrzela() && System.currentTimeMillis() >= this.czasOstatniegoStrzalu+100) {
            this.noweObiekty.add(new Pocisk(this.pozycja.getX()+(this.rozmiar.getSzerokosc()/2)-5, this.pozycja.getY()));
            this.czasOstatniegoStrzalu = System.currentTimeMillis();
        }

        if(this.pozycja.getX()+roznicaX >= 0 && this.pozycja.getX()+this.rozmiar.getSzerokosc()+roznicaX <= 1280) {
            this.pozycja = new Pozycja(this.pozycja.getX()+roznicaX, this.pozycja.getY());
        }
    }

    @Override
    public Image getTekstura() {
        BufferedImage tekstura = new BufferedImage(this.rozmiar.getSzerokosc(), this.rozmiar.getWysokosc(), BufferedImage.TYPE_INT_RGB); //Tworzenie tekstury obiektu
        Graphics2D grafika = tekstura.createGraphics();

        grafika.setColor(Color.decode("#ffffff"));
        grafika.fillRect(0, 0, this.rozmiar.getSzerokosc(), this.rozmiar.getWysokosc());

        grafika.dispose();
        return tekstura;
    }
    
}
