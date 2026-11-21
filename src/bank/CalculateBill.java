package bank;

/** Ein Interface gibt vor, welche Methoden eine Klasse anbieten muss.
 */
public interface CalculateBill {
    /**
     * Berechnet den wirksamen Betrag, ohne den gespeicherten Betrag zu verändern.
     * @return der Betrag aus Sicht des Bankkontos
     */
    double calculate();

}
