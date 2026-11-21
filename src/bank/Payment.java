package bank;

/** Eine Ein- oder Auszahlung mit Gebühren für eingehende und ausgehende Beträge.
 * extends bedeutet Vererbung. Payment erbt von Transaction. */
public class Payment extends Transaction {
    /** Gebührensatz für Einzahlungen im Intervall [0, 1]. */
    private double incomingInterest;
    /** Gebührensatz für Auszahlungen im Intervall [0, 1]. */
    private double outgoingInterest;

    /**
     * Erstellt eine Zahlung ohne Gebühren.
     * @param date das Datum als Text
     * @param amount der Betrag; negativ für eine Auszahlung
     * @param description die Beschreibung
     */
    public Payment(String date, double amount, String description) {
        super(date, amount, description);
    }

    /**
     * Erstellt eine Zahlung mit beiden Gebührensätzen.
     * @param date das Datum als Text
     * @param amount der Betrag; negativ für eine Auszahlung
     * @param description die Beschreibung
     * @param incomingInterest der Satz für Einzahlungen zwischen 0 und 1
     * @param outgoingInterest der Satz für Auszahlungen zwischen 0 und 1
     */
    public Payment(String date, double amount, String description,
                   double incomingInterest, double outgoingInterest) {
        this(date, amount, description);
        setIncomingInterest(incomingInterest);
        setOutgoingInterest(outgoingInterest);
    }

    /**
     * Erstellt ein neues Objekt mit den Werten einer bestehenden Zahlung.
     * @param other die zu kopierende Zahlung, darf nicht null sein
     */
    public Payment(Payment other) {
        // super(other) kopiert die gemeinsamen Attribute aus Transaction.
        // Danach werden die Payment-spezifischen Attribute kopiert
        super(other);
        incomingInterest = other.incomingInterest;
        outgoingInterest = other.outgoingInterest;
    }

    /**
     * Zieht bei Einzahlungen Gebühren ab und erhöht bei Auszahlungen den Abfluss.
     * Der gespeicherte Betrag bleibt unverändert.
     * @return der berechnete Betrag; bei einem Betrag von null ebenfalls null
     */
    @Override
    //calculate() gibt nur den berechneten Wert zurück.
    // Das Attribut amount wird nicht verändert.

    public double calculate() {
        if (getAmount() > 0) {
            return getAmount() * (1 - incomingInterest);
        }
        return getAmount() * (1 + outgoingInterest);
    }

    /**
     * Ergänzt die gemeinsamen Daten um die beiden Gebührensätze.
     * @return die Textdarstellung mit dem berechneten Betrag
     */
    @Override
    public String toString() {
        return "Payment[" + super.toString()
                + ", IncomingInterest=" + incomingInterest
                + ", OutgoingInterest=" + outgoingInterest + "]";
    }

    /**
     * Vergleicht gemeinsame Attribute und beide Gebührensätze.
     * @param obj das Vergleichsobjekt, darf null sein
     * @return true, wenn alle Attribute übereinstimmen
     */
    @Override
    public boolean equals(Object obj) {
        //damit die gemeinsamen Attribute nicht erneut verglichen werden müssen
        if (!super.equals(obj)) return false;
        Payment other = (Payment) obj;
        return Double.compare(incomingInterest, other.incomingInterest) == 0
                && Double.compare(outgoingInterest, other.outgoingInterest) == 0;
    }


    /**
     * Liefert einen zu equals passenden Hashwert für alle Attribute.
     * @return der Hashwert dieser Zahlung
     */
    @Override
    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), incomingInterest, outgoingInterest);
    }

    /**
     * Liefert den Gebührensatz incomingInterest.
     * @return der gespeicherte Satz zwischen 0 und 1
     */
    public double getIncomingInterest() {
        return incomingInterest;
    }

    /**
     * Überprüft und setzt den Gebührensatz.
     * Bei ungültigen Werten wird eine Meldung ausgegeben; der alte Wert bleibt.
     * @param incomingInterest der neue, endliche Satz zwischen 0 und 1
     */
    public void setIncomingInterest(double incomingInterest) {
        if (incomingInterest >= 0 && incomingInterest <= 1) {
            this.incomingInterest = incomingInterest;
        } else {
            System.out.println("Fehler: incomingInterest muss zwischen 0 und 1 liegen.");
        }
    }

    /**
     * Liefert den Gebührensatz outgoingInterest.
     * @return der gespeicherte Satz zwischen 0 und 1
     */
    public double getOutgoingInterest() {
        return outgoingInterest;
    }

    /**
     * Überprüft und setzt den Gebührensatz.
     * Bei ungültigen Werten wird eine Meldung ausgegeben; der alte Wert bleibt.
     * @param outgoingInterest der neue, endliche Satz zwischen 0 und 1
     */
    public void setOutgoingInterest(double outgoingInterest) {
        if (outgoingInterest >= 0 && outgoingInterest <= 1) {
            this.outgoingInterest = outgoingInterest;
        } else {
            System.out.println("Fehler: outgoingInterest muss zwischen 0 und 1 liegen.");
        }
    }
}
