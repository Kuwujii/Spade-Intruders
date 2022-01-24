package spadeintruders;

public class PetlaGry implements Runnable { //Klasa odpowiedzialna za główną pętlę naszej gry
    private Gra gra;

    private boolean dziala;
    private final double tempoAktualizowania = 1.0d/120.0d; //Narzucamy komputerowi ile aktualizacji silnik gry wykonuje w ciągu 1 sekundy (!= ilośc klatek na sekunde)

    private long czasNastępnychStatystyk;
    private int fps, ups;

    public PetlaGry(Gra gra) {
        this.gra = gra;
    }

    @Override
    public void run() {
        this.dziala = true;
        double akumulator = 0;
        long czas, czasOstatniejAktualizacji = System.currentTimeMillis();
        czasNastępnychStatystyk = System.currentTimeMillis()+1000;

        while(dziala) { //Nasza pętla gry
            czas = System.currentTimeMillis();
            double czasOstatniegoRysowania = (czas-czasOstatniejAktualizacji)/1000.0d;
            akumulator += czasOstatniegoRysowania;
            czasOstatniejAktualizacji = System.currentTimeMillis();

            while(akumulator >= tempoAktualizowania) { //Pętla pilnująca żeby ilość aktualizacji silnika gry nie przekroczyła podanej przez nas wartości
                this.aktualizuj();
                akumulator -= tempoAktualizowania;
            }

            this.rysuj();
            this.wypiszStatystyki();
        }
    }

    private void aktualizuj() {
        gra.aktualizuj();
        this.ups++;
    }

    private void rysuj() {
        gra.rysuj();
        this.fps++;
    }

    private void wypiszStatystyki() {
        if(System.currentTimeMillis() > this.czasNastępnychStatystyk) {
            System.out.println("FPS: "+this.fps+", UPS: "+this.ups);
            fps = 0;
            ups = 0;
            czasNastępnychStatystyk = System.currentTimeMillis()+1000;
        }
    }
}
