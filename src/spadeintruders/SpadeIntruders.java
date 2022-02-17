package spadeintruders;

public class SpadeIntruders {
    public static void main(String[] args) {
        new Thread(new PetlaGry(new Gra(Stale.szerokoscEkranu, Stale.wysokoscEkranu))).start();; //Tworzenie wątku obsługującego całą gre
    }
}