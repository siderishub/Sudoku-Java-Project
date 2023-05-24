import org.junit.Assert;
import org.junit.Test;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.Assert.*;

public class DuidokuFilesTest {
    public DuidokuFilesTest() {

    }

    @Test
    public void getWins() {
        makeFile();
        DuidokuFiles test = new DuidokuFiles("Test");
        test.addWin();
        assertEquals(1, test.getWins(), 0);
    }

    @Test
    public void getLosses() {
        makeFile();
        DuidokuFiles test = new DuidokuFiles("Test");
        test.addWin();
        test.addWin();
        test.addLoss();
        test.addLoss();
        assertEquals(2, test.getLosses(), 0);
    }

    private void makeFile () {
        try {
            FileWriter writer = new FileWriter("files/DuidokuUsers/userTest.txt");
            writer.write("0\n0");
            writer.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Σφάλμα κατά την δημιουργία αρχείου για τον χρήστη.", "Σφάλμα αρχείου", JOptionPane.ERROR_MESSAGE);
        }
    }
}