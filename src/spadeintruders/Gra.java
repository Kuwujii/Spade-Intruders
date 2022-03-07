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
                        kontrolerRoju, true
                    );

                    this.obiekty.add(tymczas);
                    this.roj.add(tymczas);
                } else {
                    Wrog tymczas = new Wrog(
                        i, ((3*Stale.rozmiarPostaci)/2)+10+(j*(Stale.rozmiarPostaci+10)),
                        Stale.rozmiarPostaci, Stale.rozmiarPostaci,
                        kontrolerRoju
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

        this.roj.forEach(wrog -> {
            wrog.aktualizujRoj();

            if(wrog.nowyPocisk != null) {
                this.obiekty.add(wrog.nowyPocisk);
                wrog.nowyPocisk = null;
            }
        });

        this.obiekty.forEach(obiekt -> {
            if(obiekt.czyPocisk()) {
                if(obiekt.pozycja.getY()+obiekt.rozmiar.getWysokosc() <= 0 || obiekt.pozycja.getY() >= Stale.wysokoscEkranu) {
                    obiektyDoUsuniecia.add(obiekt);
                    obiekt = null;
                } else {
                    Obiekt trafiony = null;

                    if(((Pocisk)obiekt).czyWrogi()) {
                        trafiony = ((Pocisk)obiekt).aktualizuj(this.gracz);
                    } else {
                        trafiony = ((Pocisk)obiekt).aktualizuj(this.roj);
                    }

                    if(trafiony != null) {
                        if(trafiony != this.gracz && ((Wrog)trafiony).czyJestPierwszy() && this.roj.size() > 1) {
                            this.roj.get(this.roj.indexOf(trafiony)+1).jestPierwszy();
                        }

                        obiektyDoUsuniecia.add(trafiony);
                        obiektyDoUsuniecia.add(obiekt);
                        trafiony = null;
                    }
                }
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
