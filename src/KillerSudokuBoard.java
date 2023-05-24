import java.util.HashMap;
/**
 * Αποθηκεύει τον πίνακα της KillerSudoku
 * @author Αντώνης Σιδέρης
 */
public class KillerSudokuBoard {
    private HashMap<Integer, KillerSudokuArea> areas;
    private KillerSudokuElement[][] elements;

    /**
     * ο κονστράκτορας
     */
    public KillerSudokuBoard() {
        areas = new HashMap<>();
        elements = new KillerSudokuElement[9][9];
    }

    /**
     * Προσθήκη μίας περιοχής
     * @param code ο κωδικός της περιοχής
     * @param area η περιοχή
     */
    public void addArea(int code,KillerSudokuArea area) {
        areas.put(code,area);
    }

    /**
     * Προσθήκη ενός πίνακα σημείων
     * @param elements τα σημεία
     */
    public void addelements(KillerSudokuElement[][] elements){
        this.elements = elements;
    }

    /**
     * Θέτει την τιμή ενός σημείου
     * @param x η γραμμή του σημείου
     * @param y η στήλη του σημείου
     * @param value η τιμή του σημείου
     */
    public void setvalue(int x,int y,int value){
        elements[x][y].setValue(value);
    }
    /**
     * Επιστρεύει την τιμή ενός σημείου
     * @param x η γραμμή του σημείου
     * @param y η στήλη του σημείου
     * @return την τιμή
     */
    public int getvalue(int x,int y){ return elements[x][y].getValue(); }

    /**
     * Επιστρεύει την περιοχή ενός σημείου
     * @param x η γραμμή του σημείου
     * @param y η στήλη του σημείου
     * @return την περιοχή
     */
    public int getAreaOfElement(int x, int y) { return elements[x][y].getAreaCode(); }
    /**
     * Getter για το χρώμα μίας περιοχής
     * @param code ο κωδικός του σημείου
     * @return το χρώμα
     */
    public int getColorOfArea(int code) {
        return areas.get(code).getAreaColor();
    }

    /**
     * Επιστρεύει το χρώμα ενός σημείου
     * @param x η γραμμή του σημείου
     * @param y η στήλη του σημείου
     * @return το χρώμα
     */
    public int getColorOfElement(int x, int y) {
        return getColorOfArea(getAreaOfElement(x, y));
    }

    /**
     * Επιστρεύει το άθροισμα της περιοχής ενός σημείου
     * @param x η γραμμή του σημείου
     * @param y η στήλη του σημείου
     * @return το άθροισμα
     */
    public int getElementAreaSum(int x, int y) { return getAreaSum(getAreaOfElement(x, y)); }

    /**
     * Επιστρεύει το τωρινό άθροισμα μίας περιοχής
     * @return το τωρινό άθροισμα
     */
    public int getAreaCurrentSum(int code) {
        int sum = 0;
        for (BoardCoordinates e : areas.get(code).contains) {
            sum += elements[e.x][e.y].getValue();
        }
        return sum;
    }

    /**
     * Επιστρεύει το άθροισμα μίας περιοχής
     * @return το άθροισμα
     */
    public int getAreaSum(int code) {
        return areas.get(code).getSum();
    }
    /**
     * Επιστρεύει το σύνολο των σημείων μίας περιοχής
     * @return το σύνολο
     */
    public int getAreaNumberOfElements(int code) {
        return areas.get(code).getNumberOfElements();
    }
}
