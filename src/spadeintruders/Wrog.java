package spadeintruders;

import java.awt.Image;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

public class Wrog extends Obiekt {
    private KontrolerRoju kontroler;
    private boolean pierwszy, pomin = true;

    public Pocisk nowyPocisk;

    public Wrog(int x, int y, int szerokosc, int wysokosc, KontrolerRoju kontroler, boolean pierwszy) {
        super(x, y, szerokosc, wysokosc);
        this.kontroler = kontroler;
        this.pierwszy = pierwszy;

        this.nowyPocisk = null;
    }

    public Wrog(int x, int y, int szerokosc, int wysokosc, KontrolerRoju kontroler) {
        super(x, y, szerokosc, wysokosc);
        this.kontroler = kontroler;
        this.pierwszy = false;
    }

    public void aktualizujRoj(Gracz gracz, List<Obiekt> noweObiekty) {
        if(this.pozycja.getY()+this.rozmiar.getWysokosc() >= gracz.pozycja.getY()) {
            noweObiekty.add(new EkranKoncaGry(false));
        } else {
            if(this.pierwszy) {
                if(!this.kontroler.czyStrzela() && Math.random() > 0.99) {
                    this.kontroler.zmienPozwolenieNaStrzal();
                }

                if(this.kontroler.czyProsiWDol() && !this.pomin) {
                    this.kontroler.zmienKierunekY();
                } else if(this.kontroler.czyProsiWDol() && this.pomin) {
                    this.pomin = !this.pomin;
                }
            }

            if((this.pozycja.getX() == 0 && this.kontroler.czyProsiWLewo())
            || (this.pozycja.getX()+Stale.rozmiarPostaci == Stale.szerokoscEkranu && this.kontroler.czyProsiWPrawo())) {
                this.kontroler.zmienKierunekX();
                this.kontroler.zmienKierunekY();
            }
        }
    }

    @Override
    public void aktualizuj() {
        int roznicaX = 0, roznicaY = 0;
        int doWykorzystania = (int)Math.round(this.kontroler.predkoscRuchu);

        while(doWykorzystania > 0) {
            if(this.kontroler.czyProsiWDol()) {
                if(doWykorzystania >= 5 && this.pozycja.getY()+this.rozmiar.getWysokosc()+10 <= Stale.wysokoscEkranu) {
                    roznicaY += 10;
                    doWykorzystania = 0;
                } else if(this.pozycja.getY()+this.rozmiar.getWysokosc()+(doWykorzystania*2) <= Stale.wysokoscEkranu) {
                    roznicaY += doWykorzystania*2;
                    doWykorzystania = 0;
                }
            }

            if(this.kontroler.czyProsiWLewo()) {
                if(this.pozycja.getX()-doWykorzystania >= 0) {
                    roznicaX -= doWykorzystania;
                    doWykorzystania = 0;
                } else {
                    roznicaX -= this.pozycja.getX();
                    doWykorzystania -= this.pozycja.getX();
                    break;
                }
            }

            if(this.kontroler.czyProsiWPrawo()) {
                if(this.pozycja.getX()+this.rozmiar.getSzerokosc()+doWykorzystania <= Stale.szerokoscEkranu) {
                    roznicaX += doWykorzystania;
                    doWykorzystania = 0;
                } else {
                    roznicaX += Stale.szerokoscEkranu-(this.pozycja.getX()+this.rozmiar.getSzerokosc());
                    doWykorzystania -= Stale.szerokoscEkranu-(this.pozycja.getX()+this.rozmiar.getSzerokosc());
                    break;
                }
            }
        }

        if(this.kontroler.czyStrzela() && Math.random() > 0.99) {
            this.nowyPocisk = new Pocisk(this.pozycja.getX()+(this.rozmiar.getSzerokosc()/2)-(Stale.szerokoscPocisku/2), this.pozycja.getY()-Stale.wysokoscPocisku, true);
            this.kontroler.zmienPozwolenieNaStrzal();
        }

        this.pozycja = new Pozycja(this.pozycja.getX()+roznicaX, this.pozycja.getY()+roznicaY);
    }

    @Override
    public Image getTekstura() {
        BufferedImage tekstura = new BufferedImage(this.rozmiar.getSzerokosc(), this.rozmiar.getWysokosc(), BufferedImage.TYPE_INT_RGB); //Tworzenie tekstury obiektu
        Graphics2D grafika = tekstura.createGraphics();

        grafika.setColor(Color.decode("#ff00ff"));
        grafika.fillRect(0, 0, this.rozmiar.getSzerokosc(), this.rozmiar.getWysokosc());

        grafika.dispose();
        return tekstura;
    }

    public void jestPierwszy() {
        this.pierwszy = true;
    }

    public boolean czyJestPierwszy() {
        return this.pierwszy;
    }

    public void informujOZgonie() {
        this.kontroler.predkoscRuchu += 0.05;
    }
}
