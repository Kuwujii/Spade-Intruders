package spadeintruders;

import java.awt.Image;

public abstract class Obiekt { //Abstrakcyjna klasa obiektu w grze
    protected Pozycja pozycja;
    protected Rozmiar rozmiar;

    public Obiekt(int x, int y, int szerokosc, int wysokosc) {
        this.pozycja = new Pozycja(x, y);
        this.rozmiar = new Rozmiar(szerokosc, wysokosc);
    }

    public Pozycja getPozycja() {
        return this.pozycja;
    }

    public Rozmiar getRozmiar() {
        return this.rozmiar;
    }

    public boolean czyPocisk() {
        return false;
    }

    public abstract void aktualizuj(); //Abstrakcyjne klasy tego obiektu
    public abstract Image getTekstura();
}
