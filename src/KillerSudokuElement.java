/**
 * Αποθηκεύει τα στοιχεία της KillerSudoku
 * @author Αντώνης Σιδέρης
 */
public class KillerSudokuElement {
    private int value;
    private int x, y;
    private int areaCode;

    /**
     * ο κονστράκτορας
     * @param x η γραμμή του σημείου
     * @param y η στήλη του σημείου
     * @param value η τιμή του σημείου
     * @param areaCode η περιοχή του σημείου
     */
    public KillerSudokuElement(int x, int y, int value, int areaCode) {
        this.x = x;
        this.y = y;
        this.value = value;
        this.areaCode = areaCode;
    }

    /**
     * ο κονστράκτορας που μηδενίζει την τιμή του σημείου
     * @param x η γραμμή του σημείου
     * @param y η στήλη του σημείου
     * @param areaCode η περιοχή του σημείου
     */
    public KillerSudokuElement(int x, int y, int areaCode) {
        this.x = x;
        this.y = y;
        this.areaCode = areaCode;
        value = 0;
    }

    /**
     * Επιστρεύει την τιμή
     * @return την τιμή
     */
    public int getValue() {
        return value;
    }

    /**
     * θέτει την τιμή
     * @param value η τιμή
     */
    public void setValue(int value) {
        this.value = value;
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
     * Επιστρεύει την περιοχή
     * @return την περιοχή
     */
    public int getAreaCode() {
        return areaCode;
    }


}
