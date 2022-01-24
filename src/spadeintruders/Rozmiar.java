package spadeintruders;

public class Rozmiar { //Prosta klasa przechowywująca rozmiary
    private int szerokosc;
    private int wysokosc;

    public Rozmiar(int szerokosc, int wysokosc) {
        this.szerokosc = szerokosc;
        this.wysokosc = wysokosc;
    }

    public int getSzerokosc() {
        return this.szerokosc;
    }

    public int getWysokosc() {
        return this.wysokosc;
    }
}
