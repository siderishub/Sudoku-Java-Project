/**
 * Author George Michail 3292
 *
 * Ανοίγει παράθυρα με τα στατιστικά για ένα χρήστη
 */

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

class ReadUserStatistics {

    private String userName;

    public ReadUserStatistics() {
    }

    /**
     *
     */
    public void readClassic() {
        setUserName();
        String FILENAME = "files/completedClassic/user" + userName + ".txt";
        File temp = new File(FILENAME);
        if (temp.exists()) {
            try {
                Scanner s = new Scanner(Paths.get(FILENAME));
                String readFromFile = s.nextLine();
                JOptionPane.showMessageDialog(null, "Ο Χρήστης έχει λύσει μέχρι και το " + readFromFile + "ο παζλ.", "Κλασσικό Sudoku", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Σφάλμα κατά την αναζήτηση του χρήστη.", "Κλασσικό Sudoku", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     *
     */
    public void readKiller() {
        setUserName();
        String FILENAME = "files/completedKiller/user" + userName + ".txt";
        File temp = new File(FILENAME);
        if (temp.exists()) {
            try {
                Scanner s = new Scanner(Paths.get(FILENAME));
                String readFromFile = s.nextLine();
                JOptionPane.showMessageDialog(null, "Ο Χρήστης έχει λύσει μέχρι και το " + readFromFile + "ο παζλ.", "Killer Sudoku", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Σφάλμα κατά την αναζήτηση του χρήστη.", "Killer Sudoku", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     *
     */
    public void readDuidoku() {
        setUserName();
        DuidokuFiles fileReader = new DuidokuFiles(userName);
        JOptionPane.showMessageDialog(null, "Ο Χρήστης έχει " + fileReader.getWins() + " νίκη/ες και " + fileReader.getLosses() + " ήττα/ες.", "Killer Sudoku", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Αλλάζει το όνομα χρήστη
     */
    private void setUserName() {
        userName = JOptionPane.showInputDialog("Εισάγετε όνομα χρήστη:");
        if (userName == null) {
            userName = "Guest";
        }
    }
}