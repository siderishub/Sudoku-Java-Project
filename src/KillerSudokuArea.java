import java.util.ArrayList;
/**
 * Αποθηκεύει μια περιοχή της KillerSudoku
 * @author Αντώνης Σιδέρης
 */
public class KillerSudokuArea {
    private int sum;
    public ArrayList<BoardCoordinates> contains;
    private int code;
    private int areaColor;

    /**
     * Ο κονστράκτορας
     * @param sum το άθροισμα της περιοχής
     * @param color το χρώμα της περιοχής
     * @param code ο κωδικός της περιοχής
     */
    public KillerSudokuArea(int sum, int color, int code) {
        this.sum = sum;
        contains = new ArrayList<BoardCoordinates>();
        areaColor = color;
        this.code = code;
    }

    /**
     * Προσθήκη ενός σημείου του πίνακα στην περιοχή
     * @param e οι συντεταγμένες του σημείου
     */
    public void add(BoardCoordinates e) {
        contains.add(e);
    }

    /**
     * Προσθήκη ενός σημείου του πίνακα στην περιοχή
     * @param x η γραμμή του σημείου
     * @param y η στήλη του σημείου
     */
    public void add(int x, int y) {
        contains.add(new BoardCoordinates(x, y));
    }

    /**
     * Επιστρεύει τον αριθμό των στοιχείων του πίνακα που βρίσκονται στην περιοχή
     * @return τον αριθμό των στοιχείων
     */
    public int getNumberOfElements() {
        return contains.size();
    }

    /**
     * Επιστρεύει το άθροισμα
     * @return το άθροισμα
     */
    public int getSum() {
        return sum;
    }
    /**
     * Επιστρεύει ένα σημείο
     * @return το σημείο
     */
    public BoardCoordinates getElement(int index) {
        return contains.get(index);
    }
    /**
     * Επιστρεύει το χρώμα
     * @return το χρώμα
     */
    public int getAreaColor() {
        return areaColor;
    }
}
