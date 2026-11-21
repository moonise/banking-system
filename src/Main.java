import bank.Payment;
import bank.Transfer;

/** Testet die Konstruktoren sowie calculate, toString und equals. */
public class Main {
    /**
     * Führt die automatischen Transaktionsprüfungen aus.
     * @param args wird nicht verwendet
     */
    public static void main(String[] args) {
        // Alle drei Payment-Konstruktoren; Ein- und Auszahlung.
        Payment p1 = new Payment("10.11.2026", 1000, "Einzahlung ohne Gebühren");
        Payment p2 = new Payment("10.11.2026", 1000, "Einzahlung", 0.05, 0.10);
        Payment p3 = new Payment(p2);
        Payment p4 = new Payment("10.11.2026", -1000, "Auszahlung", 0.05, 0.10);

        // Alle drei Transfer-Konstruktoren.
        Transfer t1 = new Transfer("10.11.2026", 1000, "Überweisung");
        Transfer t2 = new Transfer("10.11.2026", 1000, "Überweisung", "Anna", "Ben");
        Transfer t3 = new Transfer(t2);




        System.out.println("calculate():");
        System.out.println("p1: " + p1.calculate());
        System.out.println("p2: " + p2.calculate());
        System.out.println("p3: " + p3.calculate());
        System.out.println("p4: " + p4.calculate());
        System.out.println("t1: " + t1.calculate());
        System.out.println("t2: " + t2.calculate());
        System.out.println("t3: " + t3.calculate());

        System.out.println("\ntoString():");
        System.out.println(p1.toString());
        System.out.println(p2.toString());
        System.out.println(p3.toString());
        System.out.println(p4.toString());
        System.out.println(t1.toString());
        System.out.println(t2.toString());
        System.out.println(t3.toString());

        // Inhaltliche Gleichheit testen.
        System.out.println("\nequals():");
        System.out.println("p2.equals(p3): " + p2.equals(p3));
        System.out.println("p2.equals(p4): " + p2.equals(p4));
        System.out.println("t2.equals(t3): " + t2.equals(t3));
        System.out.println("t1.equals(t2): " + t1.equals(t2));

        // Automatische Prüfungen: Ein Fehler beendet das Programm mit AssertionError.
        check("Einzahlung ohne Gebühren", p1.calculate() == 1000.0);
        check("Einzahlung mit Gebühren", p2.calculate() == 950.0);
        check("Auszahlung mit Gebühren", p4.calculate() == -1100.0);
        check("Transfer ohne Gebühren", t2.calculate() == 1000.0);
        check("calculate verändert amount nicht", p2.getAmount() == 1000.0
                && p4.getAmount() == -1000.0 && t2.getAmount() == 1000.0);
        check("Payment-Kopie ist eigenes Objekt", p2 != p3 && p2.equals(p3));
        check("Transfer-Kopie ist eigenes Objekt", t2 != t3 && t2.equals(t3));
        check("equals mit null", !p2.equals(null) && !t2.equals(null));
        check("equals mit anderem Typ", !p2.equals(t2) && !t2.equals(p2));
        check("equals mit sich selbst", p2.equals(p2) && t2.equals(t2));
        check("Gleiche Objekte haben gleiche Hashwerte",
                p2.hashCode() == p3.hashCode() && t2.hashCode() == t3.hashCode());
        check("toString zeigt berechneten Betrag",
                p2.toString().contains("Amount=950.0")
                && p4.toString().contains("Amount=-1100.0"));
        p3.setDescription("Geänderte Kopie");
        check("Payment-Kopie unabhängig", !p2.equals(p3)
                && p2.getDescription().equals("Einzahlung"));
        t3.setSender("Clara");
        check("Transfer-Kopie unabhängig", !t2.equals(t3) && t2.getSender().equals("Anna"));
        Payment zero = new Payment("10.11.2026", 0, "Nullbetrag", 0.05, 0.10);
        check("Nullbetrag", zero.calculate() == 0.0);
        Payment boundaries = new Payment("10.11.2026", 100, "Grenzwerte", 1, 0);
        check("Gebührensatz 1", boundaries.calculate() == 0.0);
        boundaries.setAmount(-100);
        check("Negativer Payment-Betrag per Setter", boundaries.calculate() == -100.0);
        boundaries.setIncomingInterest(-0.1);
        boundaries.setOutgoingInterest(1.1);
        check("Ungültige Gebühren ändern alte Werte nicht",
                boundaries.getIncomingInterest() == 1 && boundaries.getOutgoingInterest() == 0);
        t3.setAmount(-100);
        check("Ungültiger Transfer-Betrag ändert alten Wert nicht", t3.getAmount() == 1000);
        check("Nullfelder sind vergleichbar",
                new Transfer(null, 100, null).equals(new Transfer(null, 100, null)));
        System.out.println("\nAlle automatischen Prüfungen erfolgreich.");
    }

    /**
     * Meldet das Ergebnis einer Prüfung und bricht bei einem Fehler ab.
     * @param name die Bezeichnung der Prüfung
     * @param condition die erwartete wahre Bedingung
     */
    private static void check(String name, boolean condition) {
        if (!condition) {
            throw new AssertionError(name);
        }
        System.out.println("OK: " + name);
    }
}
