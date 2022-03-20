package spadeintruders;

public class KontrolerRoju implements Kontroler {
    private boolean czyPrawo, czyDol, czyStrzal;
    
    public double predkoscRuchu;

    public KontrolerRoju() {
        this.czyPrawo = true;
        this.czyDol = false;
        this.czyStrzal = false;
        this.predkoscRuchu = 1;
    }

    @Override
    public boolean czyProsiWLewo() {
        return !this.czyPrawo;
    }

    @Override
    public boolean czyProsiWPrawo() {
        return this.czyPrawo;
    }

    @Override
    public boolean czyStrzela() {
        return this.czyStrzal;
    }

    public boolean czyProsiWDol() {
        return this.czyDol;
    }

    public void zmienKierunekX() {
        this.czyPrawo = !this.czyPrawo;
    }

    public void zmienKierunekY() {
        this.czyDol = !this.czyDol;
    }

    public void zmienPozwolenieNaStrzal() {
        this.czyStrzal = !this.czyStrzal;
    }
}
