package spadeintruders;

import java.util.ArrayList;
import java.util.List;

public class Gra { //Klasa odpowiedzialna za przechowywanie informacji o naszej grze
    private Klatka klatka;
    private List<Obiekt> obiekty;
    private Wejscie wejscie;
    private Gracz gracz;

    public Rozmiar rozmiarOkna;

    public Gra(int szerokosc, int wysokosc) {
        this.wejscie = new Wejscie();
        this.rozmiarOkna = new Rozmiar(szerokosc, wysokosc);
        this.klatka = new Klatka(this.rozmiarOkna, this.wejscie);

        this.obiekty = new ArrayList<>();
        this.gracz = new Gracz((szerokosc/2-25), (wysokosc*7/8)-25, 50, 50, new KontrolerGracza(this.wejscie));
        this.obiekty.add(this.gracz); //Dodaemy obiekt gracza do listy istniejących obiektów
    }

    public void aktualizuj() {
        List<Obiekt> obiektyDoUsuniecia = new ArrayList<>();

        this.gracz.noweObiekty.forEach(obiekt -> this.obiekty.add(obiekt));
        this.gracz.noweObiekty.clear();

        this.obiekty.forEach(obiekt -> {
            if(obiekt.czyPocisk() && obiekt.pozycja.getY()+obiekt.rozmiar.getWysokosc() <= 0) {
                obiektyDoUsuniecia.add(obiekt);
                obiekt = null;
            } else {
                obiekt.aktualizuj();
            }
        }); //Dla każdego obiektu w grze, zaktualizuj go

        this.obiekty.removeAll(obiektyDoUsuniecia);
        obiektyDoUsuniecia.clear();
    }

    public void rysuj() {
        this.klatka.rysuj(this); //Rysuj naszą grę
    }

    public List<Obiekt> getObiekty() {
        return this.obiekty;
    }
}
