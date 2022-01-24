package spadeintruders;

import java.util.ArrayList;
import java.util.List;

public class Gra { //Klasa odpowiedzialna za przechowywanie informacji o naszej grze
    private Klatka klatka;
    private List<Obiekt> obiekty;
    private Wejscie wejscie;

    public Gra(int szerokosc, int wysokosc) {
        this.wejscie = new Wejscie();
        this.klatka = new Klatka(szerokosc, wysokosc, this.wejscie);

        this.obiekty = new ArrayList<>();
        this.obiekty.add(new Gracz((szerokosc/2-25), (wysokosc*7/8)-25, 50, 50, new KontrolerGracza(this.wejscie))); //Dodaemy obiekt gracza do listy istniejących obiektów
    }

    public void aktualizuj() {
        this.obiekty.forEach(obiekt -> obiekt.aktualizuj()); //Dla każdego obiektu w grze, zaktualizuj go
    }

    public void rysuj() {
        this.klatka.rysuj(this); //Rysuj naszą grę
    }

    public List<Obiekt> getObiekty() {
        return this.obiekty;
    }
}
