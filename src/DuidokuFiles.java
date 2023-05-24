import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Author George Michail 3292
 *
 * επιλογή αρχείου παζλ για ένα χρήστη
 */

public class DuidokuFiles {
    private String FILENAME;

    /**
     *
     * @param userName
     */
    public DuidokuFiles(String userName) {
        setUserName( userName );

    }

    /**
     *
     * @param userName
     */
    public void setUserName(String userName) {
        if (userName.equals("")) {
            userName = "Guest";
        }
        FILENAME = "files/DuidokuUsers/user" + userName + ".txt";
    }

    /**
     *
     */
    public void addWin() {
        int wins = getWins();
        int loses = getLosses();
        makeFile(++wins, loses);
    }

    /**
     *
     */
    public void addLoss() {
        int wins = getWins();
        int loses = getLosses();
        makeFile(wins, ++loses);
    }

    /**
     *
     * @return αριθμός νικών
     */
    public int getWins() {
        int wins;
        String input1;
        try {
            Scanner s = new Scanner(Paths.get(FILENAME));
            input1 = s.nextLine();
            return Integer.parseInt(input1);
        } catch (IOException e) {
            makeFile(0,0);
            return 0;
        }
    }

    /**
     *
     * @return αριθμός ηττών
     */
    public int getLosses() {
        int losses;
        String input1, input2;
        try {
            Scanner s = new Scanner(Paths.get(FILENAME));
            input1 = s.nextLine();
            input2 = s.nextLine();
            return Integer.parseInt(input2);
        } catch (IOException e) {
            makeFile(0,0);
            return 0;
        }
    }

    /**
     * Δημιουργεί καινούριο ή ενημερωμένο αρχείο για τον χρήστη
     * @param wins
     * @param loses
     */
    private void makeFile (int wins, int loses) {
        try {
            FileWriter writer = new FileWriter(FILENAME);
            writer.write(Integer.toString(wins) + "\n" + Integer.toString(loses));
            writer.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Σφάλμα κατά την δημιουργία αρχείου για τον χρήστη.", "Σφάλμα αρχείου", JOptionPane.ERROR_MESSAGE);
        }
    }
}
