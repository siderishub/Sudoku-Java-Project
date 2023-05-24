/**
 * Author George Michail 3292
 *
 * Επιλογή αρχείου με παζλ για ένα χρήστη
 */

public class KillerPuzzles extends ClassicPuzzles {

    /**
     *
     * @param userName
     */
    public KillerPuzzles(String userName) {
        super(userName);
        super.preText = "files/completedKiller/user";
        super.gameName = "Killer";
        //super.setUserName(userName);
    }
}
