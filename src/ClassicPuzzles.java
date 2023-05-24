import javax.swing.*;
import java.io.*;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ClassicPuzzles {

    private String FILENAME;
    private int lastCompleted;
    protected String preText = "files/completedClassic/user";
    protected String gameName = "Classic";

    /**
     *
     * @param userName
     */
    public ClassicPuzzles(String userName) {
        setUserName(userName);
        lastCompleted = 0;
    }

    /**
     *
     */
    public ClassicPuzzles() {
        setUserName("");
        lastCompleted = 0;
    }

    /**
     *
     * @return
     */
    public int getLastCompleted() {
        return lastCompleted;
    }


    /**
     * Ενημέρωση του ονόματος αρχείου
     * @param userName
     */
    public void setUserName(String userName) {
        if (userName.equals("")) {
            userName = "Guest";
        }
        FILENAME = preText + userName + ".txt";
    }

    /**
     *
     * @return το όνομα του αρχείου με το παζλ για τον χρήστη
     */
    public String choosePuzzle() {
        File temp = new File(FILENAME);
        if (temp.exists()) {
            try {
                Scanner s = new Scanner(Paths.get(FILENAME));
                String readFromFile = s.nextLine();
                try {
                    lastCompleted = Integer.parseInt(readFromFile) ;
                } catch (NoSuchElementException e) {
                    JOptionPane.showMessageDialog(null, "Σφάλμα κατά την ανάγνωση του αρχείου χρήστη. Λάθος περιεχόμενο αρχείου.", "Σφάλμα αρχείου", JOptionPane.ERROR_MESSAGE);
                    makeFile();
                    return gameName + "1";
                } catch (NumberFormatException nf) {
                    JOptionPane.showMessageDialog(null, "Σφάλμα κατά την ανάγνωση του αρχείου χρήστη. Λάθος περιεχόμενο αρχείου.", "Σφάλμα αρχείου", JOptionPane.ERROR_MESSAGE);
                    makeFile();
                    return gameName + "1";
                }
                //System.out.println(++lastCompleted);
                String newPuzzle = "files/" + gameName + "Sudoku/" + gameName + Integer.toString(lastCompleted + 1 ) + ".txt";
                if (new File(newPuzzle).exists()) {
                    return gameName + (++lastCompleted);
                } else {
                    JOptionPane.showMessageDialog(null,  "Συγχαρητήρια! Έχετε ολοκληρώσει όλα τα διαθέσημα παζλ!" , "Τέλος", JOptionPane.INFORMATION_MESSAGE);
                    return "allCompleted";
                }
            } catch (IOException e) {
                //e.printStackTrace();
                completed(0);
                JOptionPane.showMessageDialog(null, "Σφάλμα κατά την ανάγνωση του αρχείου χρήστη.", "Σφάλμα αρχείου", JOptionPane.ERROR_MESSAGE);
                return gameName + "1";
            }
        } else {
            makeFile();
            return gameName + "1";
        }
    }

    /**
     *
     * @param value το παζλ που ολοκληρώθηκε
     */
    private void completed (int value) {
        try {
            FileWriter writer = new FileWriter(FILENAME);
            writer.write(Integer.toString(value));
            writer.close();
        } catch (IOException e) {
            //e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Σφάλμα κατά την απθηκέυση της προόδου.", "Σφάλμα αρχείου", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     *
     */
    public void completedTheLast () {
        completed( lastCompleted );
    }

    /**
     * Δημιουργία αρχείου
     */
    private void makeFile () {
        try {
            FileWriter writer = new FileWriter(FILENAME);
            writer.write("0");
            writer.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Σφάλμα κατά την δημιουργία αρχείου για τον χρήστη.", "Σφάλμα αρχείου", JOptionPane.ERROR_MESSAGE);
        }
    }
}
