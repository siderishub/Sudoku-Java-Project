
import org.junit.Test;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.Assert.*;

public class ClassicPuzzlesTest {

    public ClassicPuzzlesTest() {
    }

    @Test
    public void choosePuzzle() {
        makeFile();
        ClassicPuzzles test1 = new ClassicPuzzles();
        ClassicPuzzles test = new ClassicPuzzles("Test");
        test.completedTheLast();
        assertEquals("Classic1", test.choosePuzzle());
        test.setUserName("Guest");
        assertEquals("allCompleted", test.choosePuzzle());
        assertEquals("allCompleted", test1.choosePuzzle());
    }

    @Test
    public void getLastCompleted() {
        makeFile();
        ClassicPuzzles test1 = new ClassicPuzzles("Test");
        assertEquals(0, test1.getLastCompleted(),0);
    }

    private void makeFile () {
        try {
            FileWriter writer = new FileWriter("files/completedClassic/userTest.txt");
            writer.write("0");
            writer.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Σφάλμα κατά την δημιουργία αρχείου για τον χρήστη.", "Σφάλμα αρχείου", JOptionPane.ERROR_MESSAGE);
        }
    }
}
