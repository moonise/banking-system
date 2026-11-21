package bank;

/**
 * Abstrakte Oberklasse für Zahlungen und Überweisungen.
 * Speichert die gemeinsamen Daten und implementiert deren Vergleich und Textdarstellung.
 */
public abstract class Transaction implements CalculateBill {
    /** Datum der Transaktion als Text. */
    private String date;
    /** Ursprünglicher Betrag ohne berechnete Gebühren. */
    private double amount;
    /** Beschreibung der Transaktion. */
    private String description;

    /**
     * Initialisiert die gemeinsamen Attribute.
     * @param date das Datum als Text
     * @param amount der gespeicherte Betrag
     * @param description die Beschreibung
     */
    public Transaction(String date, double amount, String description) {
        setDate(date);
        // Die Unterklassen validieren besondere Betragsregeln selbst.
        this.amount = amount;
        setDescription(description);
    }

    /**
     * Kopiert die gemeinsamen Attribute einer Transaktion.
     * @param other die zu kopierende Transaktion, darf nicht null sein
     */
    //this(..) Damit wird ein anderer Konstruktor derselben Klasse aufgerufen.
    public Transaction(Transaction other) {
        this(other.date, other.amount, other.description);
    }

    /**
     * Liefert die gemeinsamen Daten einschließlich des berechneten Betrags.
     * @return die Textdarstellung der gemeinsamen Attribute
     */
    @Override
    public String toString() {
        return "Date=" + date + ", Amount=" + calculate()
                + ", Description=" + description;
    }

    /**
     * Vergleicht die konkrete Klasse und alle gemeinsamen Attribute.
     * Der gespeicherte Betrag wird verglichen, nicht der berechnete Betrag.
     * @param obj das Vergleichsobjekt, darf null sein
     * @return true bei gleicher Klasse und gleichen gemeinsamen Attributen
     */
    @Override
    public boolean equals(Object obj) {
        //wenn beide Referenzen auf dasselbe Objekt zeigen.
        if (this == obj) return true;
        //getClass() != obj.getClass() Hier wird geprüft, ob beide Objekte zur gleichen Klasse gehören.
        if (obj == null || getClass() != obj.getClass()) return false;
        Transaction other = (Transaction) obj;
        return Double.compare(amount, other.amount) == 0
                && (date == null ? other.date == null : date.equals(other.date))
                && (description == null ? other.description == null
                    : description.equals(other.description));
    }


    /**
     * Liefert einen zu equals passenden Hashwert der gemeinsamen Attribute.
     * @return der Hashwert der gemeinsamen Daten und der konkreten Klasse
     */
    @Override
    public int hashCode() {
        return java.util.Objects.hash(getClass(), date, amount, description);
    }

    /**
     * Liefert das Datum als Text.
     * @return der gespeicherte Wert von date
     */
    public String getDate() {
        return date;
    }

    /**
     * Setzt das Datum als Text ohne weitere Prüfung.
     * @param date der neue Wert
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Liefert den gespeicherten Betrag.
     * @return der gespeicherte Wert von amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Setzt den gespeicherten Betrag ohne weitere Prüfung.
     * @param amount der neue Wert
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Liefert die Beschreibung.
     * @return der gespeicherte Wert von description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setzt die Beschreibung ohne weitere Prüfung.
     * @param description der neue Wert
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
