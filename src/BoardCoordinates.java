/**
 * Αποθηκεύει τις συντεταγμένες ενός σημείου
 * @author Αντώνης Σιδέρης
*/
public class BoardCoordinates {
    public int x, y;

    /**
     * Ο κονστράκτοσας
     * @param x η γραμμή
     * @param y η στήλη
     */
    public BoardCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Κονστράκτορας χωρίς παραμέτρους
     */
    public BoardCoordinates() {
        x = y = 0;
    }
}
