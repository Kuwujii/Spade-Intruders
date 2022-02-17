package spadeintruders;

public class KontrolerRoju implements Kontroler {
    private boolean czyPrawo;
    private boolean czyDol;

    public KontrolerRoju() {
        this.czyPrawo = true;
        this.czyDol = false;
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
        return false;
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
}
