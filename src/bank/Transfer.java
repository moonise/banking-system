package bank;

/** Eine gebührenfreie Überweisung von einem Sender an einen Empfänger. */
public class Transfer extends Transaction {
    /** Name des Senders; darf null sein. */
    private String sender;
    /** Name des Empfängers; darf null sein. */
    private String recipient;

    /**
     * Erstellt eine Überweisung ohne Angaben zu Sender und Empfänger.
     * Ein ungültiger Anfangsbetrag wird abgelehnt;
     * der Betrag bleibt dann beim Anfangswert 0.0.
     * @param date das Datum als Text
     * @param amount der gewünschte positive Betrag
     * @param description die Beschreibung
     */
    public Transfer(String date, double amount, String description) {
        super(date, 0.0, description);

        setAmount(amount);
    }

    /**
     * Erstellt eine Überweisung mit Sender und Empfänger.
     * @param date das Datum als Text
     * @param amount der gewünschte positive Betrag
     * @param description die Beschreibung
     * @param sender der Sender oder null
     * @param recipient der Empfänger oder null
     */
    public Transfer(String date, double amount, String description,
                    String sender, String recipient) {
        this(date, amount, description);
        setSender(sender);
        setRecipient(recipient);
    }

    /**
     * Erstellt ein neues Objekt mit den Werten einer bestehenden Überweisung.
     * @param other die zu kopierende Überweisung, darf nicht null sein
     */
    public Transfer(Transfer other) {
        super(other);
        sender = other.sender;
        recipient = other.recipient;
    }

    /**
     * Prüft den Betrag und übernimmt nur positive Werte.
     * Bei ungültigen Werten bleibt der alte Betrag unverändert.
     * @param amount der gewünschte neue Betrag
     */
    @Override
    public void setAmount(double amount) {
        if (amount > 0) {
            super.setAmount(amount);
        } else {
            System.out.println("Fehler: Der Transfer-Betrag muss positiv sein.");
        }
    }

    /**
     * Liefert den Betrag ohne Gebühren und ohne ihn zu verändern.
     * @return der gespeicherte Betrag
     */
    @Override
    public double calculate() {
        return getAmount();
    }

    /**
     * Ergänzt die gemeinsamen Daten um Sender und Empfänger.
     * @return die Textdarstellung der Überweisung
     */
    @Override
    public String toString() {
        return "Transfer[" + super.toString()
                + ", Sender=" + sender + ", Recipient=" + recipient + "]";
    }

    /**
     * Vergleicht gemeinsame Attribute sowie Sender und Empfänger.
     * @param obj das Vergleichsobjekt, darf null sein
     * @return true, wenn alle Attribute übereinstimmen
     */
    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        Transfer other = (Transfer) obj;
        return (sender == null ? other.sender == null : sender.equals(other.sender))
                && (recipient == null ? other.recipient == null
                    : recipient.equals(other.recipient));
    }


    /**
     * Liefert einen zu equals passenden Hashwert für alle Attribute.
     * @return der Hashwert dieser Überweisung
     */
    @Override
    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), sender, recipient);
    }

    /**
     * Liefert den Sender.
     * @return der gespeicherte Wert von sender
     */
    public String getSender() {
        return sender;
    }

    /**
     * Setzt den Sender ohne weitere Prüfung.
     * @param sender der neue Wert
     */
    public void setSender(String sender) {
        this.sender = sender;
    }

    /**
     * Liefert den Empfänger.
     * @return der gespeicherte Wert von recipient
     */
    public String getRecipient() {
        return recipient;
    }

    /**
     * Setzt den Empfänger ohne weitere Prüfung.
     * @param recipient der neue Wert
     */
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

}
