package spadeintruders;

import java.awt.Image;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.FontMetrics;

public class EkranKoncaGry extends Obiekt {
    private boolean wygrana;

    public EkranKoncaGry(boolean wygrana) {
        super(0, 0, Stale.szerokoscEkranu, Stale.wysokoscEkranu);
        this.wygrana = wygrana;
    }

    @Override
    public void aktualizuj() {}

    @Override
    public Image getTekstura() {
        BufferedImage tekstura = new BufferedImage(this.rozmiar.getSzerokosc(), this.rozmiar.getWysokosc(), BufferedImage.TYPE_INT_RGB); //Tworzenie tekstury obiektu
        Graphics2D grafika = tekstura.createGraphics();

        String kolor, tekst;

        if(this.wygrana) {
            kolor = "#001100";
            tekst = "Wygrana";
        } else {
            kolor = "#110000";
            tekst = "Przegrana";
        }

        grafika.setColor(Color.decode(kolor));
        grafika.fillRect(0, 0, this.rozmiar.getSzerokosc(), this.rozmiar.getWysokosc());

        grafika.setFont(grafika.getFont().deriveFont(grafika.getFont().getSize()*5.0f));
        grafika.setColor(Color.decode("#ffffff"));

        FontMetrics rozmiaryCzcionki = grafika.getFontMetrics();

        int x = (Stale.szerokoscEkranu-rozmiaryCzcionki.stringWidth(tekst))/2;
        int y = ((Stale.wysokoscEkranu-rozmiaryCzcionki.getHeight())/2)+rozmiaryCzcionki.getAscent();

        grafika.drawString(tekst, x, y);

        grafika.dispose();
        return tekstura;
    }
}
