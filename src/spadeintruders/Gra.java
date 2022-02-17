package spadeintruders;

import java.util.ArrayList;
import java.util.List;

public class Gra { //Klasa odpowiedzialna za przechowywanie informacji o naszej grze
    private Klatka klatka;
    private List<Obiekt> obiekty;
    private List<Wrog> roj;
    private Wejscie wejscie;
    private Gracz gracz;

    public Gra(int szerokosc, int wysokosc) {
        this.wejscie = new Wejscie();
        this.klatka = new Klatka(szerokosc, wysokosc, this.wejscie);

        this.obiekty = new ArrayList<>();
        this.roj = new ArrayList<>();

        this.gracz = new Gracz(
            (szerokosc/2)-(Stale.rozmiarPostaci/2),
            (wysokosc*15/16)-(Stale.rozmiarPostaci/2),
            Stale.rozmiarPostaci, Stale.rozmiarPostaci,
            new KontrolerGracza(this.wejscie)
        );
        this.obiekty.add(this.gracz); //Dodaemy obiekt gracza do listy istniejących obiektów

        KontrolerRoju kontrolerRoju = new KontrolerRoju();

        for(int j = 0; j < 5; j++) {
            for(int i = 25; i <= Stale.szerokoscEkranu-Stale.rozmiarPostaci-25; i += Stale.rozmiarPostaci+25) {
                if(j == 0 && i == 25) {
                    Wrog tymczas = new Wrog(
                        i, ((3*Stale.rozmiarPostaci)/2)+10+(j*(Stale.rozmiarPostaci+10)),
                        Stale.rozmiarPostaci, Stale.rozmiarPostaci,
                        kontrolerRoju,
                        true
                    );

                    this.obiekty.add(tymczas);
                    this.roj.add(tymczas);
                } else if(j == 4 && i == Stale.szerokoscEkranu-Stale.rozmiarPostaci-25) {
                    Wrog tymczas = new Wrog(
                        i, ((3*Stale.rozmiarPostaci)/2)+10+(j*(Stale.rozmiarPostaci+10)),
                        Stale.rozmiarPostaci, Stale.rozmiarPostaci,
                        kontrolerRoju, this.obiekty.get(this.obiekty.size()-1),
                        true
                    );

                    this.obiekty.add(tymczas);
                    this.roj.add(tymczas);
                } else {
                    Wrog tymczas = new Wrog(
                        i, ((3*Stale.rozmiarPostaci)/2)+10+(j*(Stale.rozmiarPostaci+10)),
                        Stale.rozmiarPostaci, Stale.rozmiarPostaci,
                        kontrolerRoju, this.obiekty.get(this.obiekty.size()-1)
                    );

                    this.obiekty.add(tymczas);
                    this.roj.add(tymczas);
                }
            }
        }
    }

    public void aktualizuj() {
        List<Obiekt> obiektyDoUsuniecia = new ArrayList<>();

        this.gracz.noweObiekty.forEach(obiekt -> this.obiekty.add(obiekt));
        this.gracz.noweObiekty.clear();

        this.roj.forEach(wrog -> wrog.aktualizujRoj());

        this.obiekty.forEach(obiekt -> {
            if(obiekt.czyPocisk() && obiekt.pozycja.getY()+obiekt.rozmiar.getWysokosc() <= 0) {
                obiektyDoUsuniecia.add(obiekt);
                obiekt = null;
            } else {
                obiekt.aktualizuj();
            }
        }); //Dla każdego obiektu w grze, zaktualizuj go

        this.obiekty.removeAll(obiektyDoUsuniecia);
        this.roj.removeAll(obiektyDoUsuniecia);
        obiektyDoUsuniecia.clear();
    }

    public void rysuj() {
        this.klatka.rysuj(this); //Rysuj naszą grę
    }

    public List<Obiekt> getObiekty() {
        return this.obiekty;
    }
}
