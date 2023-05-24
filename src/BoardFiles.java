import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Η κλάση ασχολείται με την άντληση πληροφοριών απο τα αρχεία για την δημιουργεία των Sudoku
 * @author Αντώνης Σιδέρης
 */
public class BoardFiles {
    /**
     * Διαβάζει το αρχείο μία γραμμή την φορά και την αποθηκεύει σε έναν πίνακα
     * @param file το όνομα του αρχείου
     * @return τον πίνακα
     * @throws IOException όταν δέν βρεθεί το αρχείο
     */
    public int[][] sudokuFiles(String file) {
        int i=0,j;
        int [][] board = new int[9][9];
        try{
            Scanner s = new Scanner(Paths.get("files/ClassicSudoku/"+file+".txt"));
            while(s.hasNextLine()) {
                    String[] line = s.nextLine().split(" ");
                    for (j=0; j<9; j++) {
                        board[i][j] = Integer.parseInt(line[j]);
                    }
                    i=i+1;
            }
            s.close();
        }
        catch (IOException e) {
            for (i=0; i<9; i++){
                for (j=0; j<9; j++){
                    board[i][j] = 0;
                }
            }
        }
        return board;
    }
    /**
     * Διαβάζει το αρχείο μία γραμμή την φορά,η κάθε γραμμή περιέχει μία περιοχή του KillerSudoku
     * πρώτα το άθροισμα δεύτερο το χρώμα και μετά τις συντεταγμένες απο τα στοιχεία που βρίσκονται στην περιοχή,
     * και την αποθηκεύει σε ένα αντικείμενο KillerSudokuBoard
     * @param file το όνομα του αρχείου
     * @return το KillerSudokuBoard
     * @throws IOException όταν δέν βρεθεί το αρχείο
     */
    public KillerSudokuBoard killerFiles(String file) {
        int areacode=1,i;
        KillerSudokuBoard board = new KillerSudokuBoard();
        KillerSudokuArea area;
        KillerSudokuElement[][] elements = new KillerSudokuElement[9][9];
        try{
            Scanner s = new Scanner(Paths.get("files/KillerSudoku/"+file+".txt"));
            while(s.hasNextLine()) {
                String[] line = s.nextLine().split(" ");
                area=new KillerSudokuArea(Integer.parseInt(line[0]),Integer.parseInt(line[1]),areacode);
                i=2;
                while(i<line.length){
                    elements[Integer.parseInt(line[i])][Integer.parseInt(line[i+1])] = new KillerSudokuElement(Integer.parseInt(line[i]),Integer.parseInt(line[i+1]),areacode);
                    area.add(Integer.parseInt(line[i]),Integer.parseInt(line[i+1]));
                    i=i+2;
                }
                board.addArea(areacode,area);
                areacode=areacode+1;
                area=null;
            }
            board.addelements(elements);
            s.close();
        }
        catch (IOException e) {
            return null;
        }
        return board;
    }
}