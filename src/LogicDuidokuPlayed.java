/**
 * Η κίνηση του υπολογιστή στη Duidoku
 * @author Αντώνης Σιδέρης
 */
public class LogicDuidokuPlayed {
    private int x, y, value;
    private boolean played;

    /**
     * Ο κονστράκτορας
     * @param x η γραμμή του σημείου
     * @param y η στήλη του σημείου
     * @param value η τιμή του σημείου
     */
    public LogicDuidokuPlayed(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
        played = true;
    }
    /**
     * Ο κονστράκτορας όταν δεν έχει βρεθεί σημείο
     * @param played αν έπαιξε
     */
    public LogicDuidokuPlayed(boolean played) {
        this.played = played;
        x = y = value = 0;
    }
    /**
     * Επιστρεύει την γραμμή
     * @return την γραμμή
     */
    public int getX() {
        return x;
    }
    /**
     * Επιστρεύει την στήλη
     * @return την στήλη
     */
    public int getY() {
        return y;
    }
    /**
     * Επιστρεύει την τιμή
     * @return την τιμή
     */
    public int getValue() {
        return value;
    }

    /**
     * Ελέγχει αν έπαιξε
     * @return αν έπαιξε
     */
    public boolean isPlayed() {
        return played;
    }
}
