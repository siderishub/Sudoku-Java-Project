import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoardFilesTests {
    public BoardFilesTests() {

    }

    @Test
    public void sudokuFiles() {
        int[][] array = {{0,8,3,0,0,2,4,1,0},
                {2,0,4,0,0,5,0,0,0},
                {0,1,0,0,7,4,0,2,8},
                {3,0,0,4,9,0,1,5,0},
                {0,0,7,0,1,0,0,0,6},
                {9,0,0,7,5,3,0,8,0},
                {8,4,0,0,0,0,6,0,0},
                {5,0,0,0,4,0,0,3,1},
                {1,3,6,0,2,0,5,0,0}};
        int[][] array2 = {{0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},};
        BoardFiles b= new BoardFiles();
        Assert.assertArrayEquals(array,b.sudokuFiles("Classic1"));
        Assert.assertArrayEquals(array2,b.sudokuFiles("wrongname"));
    }
    @Test
    public void KillerFiles() {
        KillerSudokuBoard board = new KillerSudokuBoard();
        KillerSudokuBoard board2;
        KillerSudokuArea area;
        KillerSudokuElement[][] elements= new KillerSudokuElement[9][9];
        area=new KillerSudokuArea(3,1,1);
        elements[0][0] = new KillerSudokuElement(0,0,1);
        area.add(0,0);
        elements[0][1] = new KillerSudokuElement(0,1,1);
        area.add(0,1);
        board.addArea(1,area);
        board.addelements(elements);
        BoardFiles b= new BoardFiles();
        board2 = b.killerFiles("Killertest");
        assertEquals(board.getvalue(0,0),board2.getvalue(0,0));
        assertEquals(board.getvalue(0,1),board2.getvalue(0,1));
        assertEquals(board.getAreaOfElement(0,0),board2.getAreaOfElement(0,0));
        assertEquals(board.getAreaOfElement(0,1),board2.getAreaOfElement(0,1));
        assertEquals(board.getColorOfArea(1),board2.getColorOfArea(1));
        assertEquals(board.getColorOfElement(0,0),board2.getColorOfElement(0,0));
        assertEquals(board.getColorOfElement(0,1),board2.getColorOfElement(0,1));
        assertEquals(board.getAreaSum(1),board2.getAreaSum(1));
        assertEquals(board.getAreaNumberOfElements(1),board2.getAreaNumberOfElements(1));
        assertNull(b.killerFiles("wrongname"));
    }
}
